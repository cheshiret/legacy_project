/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.callcenterpossetup;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductAssignmentPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to verify error messages when assign a POS product with specific revenue location to call center.
 * @Preconditions:
 * 1. Make sure the user qa-auto-adm has the permissions: SearchCallCenterPOSProductSetup, 
 * AssignCallCenterPOSProductSetup and EditCallCenterPOSProductSetup.
 * 2. Make sure the POS exists:
 *    ASC1       Assign To Call Error_1     Admissions        Assign To Call Error_1
 *    ASC2       Assign To Call Error_2     Admissions        Assign To Call Error_2
 * 3. Make sure no account schedule has been set up for product group 'Vending Machines':FM-->Account Schedules
 * @LinkSetUp:  d_inv_add_mpos  :id=40(PRODUCT='Assign To Call Error_1')
 				d_inv_add_mpos  :id=50(PRODUCT='Assign To Call Error_2')
 * @SPEC: TC032034
 * @Task#: Auto-1306
 * @author Phoebe Chen
 * @Date  Oct 22, 2012
 */
public class AssignToCallWithErrorMessage extends FinanceManagerTestCase {
	private FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage.getInstance();
	private FinMgrFeeSchDetailPage feeSchDetailPg = FinMgrFeeSchDetailPage.getInstance();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private POSInfo pos1 = new POSInfo();
	private POSInfo pos2 = new POSInfo();
	private FeeScheduleData fee = new FeeScheduleData();
	private FeeScheduleData confilictSchedule = new FeeScheduleData();
    private String unitPriceIsBlank;
    private String unitPriceIsLessThanZero;
    private String assignAlreadyAssigned;
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		
		//Set up a fee schedule for pos2, it is for the conflict test
		this.setUpConfictSchedule();

		fnm.gotoPosProductAssignmentPage();
		fnm.unassignPOSProduct(pos2);
				
		// Search the unassigned product
		assignPg.searchUnassignedPOSProduct(pos1);
		
		// Assign the product to Call Center with blank unitPrice, and verify the error message
		pos1.unitPrice = "";
		assignPg.assignPOSProd(pos1);
		this.verifyErrorMessage("UnitPriceIsBlank", unitPriceIsBlank);
		
		// Assign the product to Call Center with unitPrice less than zero, and verify the error message
		pos1.unitPrice = "-3.0";
		assignPg.assignPOSProd(pos1);
		this.verifyErrorMessage("UnitPriceIsLessThanZero", unitPriceIsLessThanZero);
		
		// Assign the product to Call Center with invalid unitPrice, and verify the error message
		pos1.unitPrice = "###";
		assignPg.assignPOSProd(pos1);
		this.verifyErrorMessage("UnitPriceIsInValid", unitPriceIsBlank);
		
		// Assign the product to Call Center without select any pos, and verify the pos status
		pos1.unitPrice = "5.0";
		this.assignPOSProdWithoutSelect(pos1);
		assignPg.verifyUnassigedPOSProductItemInList(pos1, true);
		
		// Assign the product who has conflict fee schedule already
		assignPg.searchPOSProduct(pos2);
		assignPg.assignPOSProd(pos2);
		pos2.assignStatus = YES_STATUS;
		assignPg.verifyAssignedPOSProductItemInList(pos2, true);		
		// Verify the new fee schedule in DB and UI, the conflict fee schedule is inactive now
		fee.feeSchdId = fnm.queryFeeScheduleID(fee, schema);
		fnm.gotoFeeMainPage();		
		fnm.searchToFeeScheduleDetailPg(fee.feeSchdId);
		feeSchDetailPg.verifyFeeScheduleDetails(fee);
		fnm.cancelFromFeeScheduleDetailsPage();
		feeMainPg.verifyStatus(confilictSchedule.feeSchdId, INACTIVE_STATUS);
		
		// Assign the product to call center who has already been assigned
		fnm.gotoPosProductAssignmentPage();
		assignPg.searchAssignedPOSProduct(pos2);
		assignPg.assignPOSProd(pos2);
		this.verifyErrorMessage("assignAlreadyAssigned", assignAlreadyAssigned);
		
		fnm.logoutFinanceManager();
	}

	private void setUpConfictSchedule() {
		FeeScheduleData productRelaSchedule = new FeeScheduleData();
		fnm.gotoFeeMainPage();
		//Add a new Fee schedule data
		productRelaSchedule.searchType = "Product";
		productRelaSchedule.searchValue = confilictSchedule.product;
		fnm.searchFeeSchedule(productRelaSchedule);
		fnm.deactivateAllFeeSchedules();
		confilictSchedule.feeSchdId = fnm.addNewFeeSchedule(confilictSchedule);
		// Activate RA Fee Schedule
		feeMainPg.changeScheduleStatus(confilictSchedule.feeSchdId, ACTIVE_STATUS);
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//login information
		String facilityID = "1";  //South Carolina State Park Service
		String facilityName = fnm.getFacilityName(facilityID, schema);
		login.contract = "SC Contract";
		login.location = "Administrator/" + facilityName;
		
		// POS1 info
		pos1.product = "Assign To Call Error_1";
		pos1.productDescription = pos1.product;
		pos1.productGroup = "Admissions";
		pos1.assignStatus = NO_STATUS;
		pos1.availableLocation = facilityName;
		pos1.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		pos1.variablePriceAllowed = NO_STATUS;
		pos1.partialQuantityAllowed = NO_STATUS;
		
		// POS2 info
		pos2.product = "Assign To Call Error_2";
		pos2.productDescription = pos2.product;
		pos2.productGroup = "Admissions";
		pos2.unitPrice = "8.80";
		pos2.availableLocation = facilityName;
		pos2.effectiveSalesStartDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(0,timeZone), "EEE MMM d yyyy");
		pos2.effectiveSalesEndDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2,timeZone), "EEE MMM d yyyy");
		pos2.variablePriceAllowed = NO_STATUS;
		pos2.partialQuantityAllowed = NO_STATUS;
		pos2.productID = fnm.getProductID("Product Name", pos2.product, null, schema);
		
		this.initializeFeeData(pos2);
		this.initializeConfilictSchedule(pos2);
		
		//Error message
		unitPriceIsBlank = 
	    		"The Unit Price for " + pos1.product +
		         " is required. Please enter the Unit Price in the field provided.";
		unitPriceIsLessThanZero =
				"The Unit Price for " + pos1.product + 
				" must have a value greater than or equal to $0.00. Please change your entries.";
		assignAlreadyAssigned =
				"Product " + pos2.product + 
				" selected has already been assigned to your location. Please unselect it.";
				
	}
	
	private void verifyErrorMessage(String type, String expectErrorMess) {
		boolean passed = true;
		String errorShow = assignPg.getErrorMsg();
		passed &= MiscFunctions.compareResult("Check error message for:" + type, expectErrorMess, errorShow);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}
	}
	
	/* Assign POS product with pos details */
	public void assignPOSProdWithoutSelect(POSInfo pos) {
		if (StringUtil.isEmpty(pos.productID)) {
			pos.productID = assignPg.getPOSProdID(pos.product);
		}
		assignPg.setupPOSDetails(pos);
		assignPg.unselectProductId(pos.productID);
		assignPg.clickAssignSelectedPosProducts();
		ajax.waitLoading();
		assignPg.waitLoading();
	}
	
	private void initializeFeeData(POSInfo pos) {
		logger.info("Initialize fee data...");
		fee.productCategory = PRDCAT_NAME_POS;
		fee.feeType = FEETYPE_NAME_POSFEE;
		fee.location = "SC parks";
		fee.salesChannel = SALESCHAN_NAME_CALLCENTER.replace(" ", "");
		fee.rate = pos.unitPrice;
		fee.productGroup = pos.productGroup;
		fee.product = pos.product;
		fee.season = "";
		fee.state = "";
		fee.loop = "";
		fee.activeStatusID = STATUS_ACTIVE;
		fee.feeTypeID = FEETYPE_POSFEE;
		fee.productCategoryID = PRDCAT_POS;
		fee.salesChannelID = SALESCHAN_CALLCENTER;
		fee.salesCategoryID = SALESCAT_ALL;
		fee.startInv = pos.effectiveSalesStartDate;
		fee.endInv = pos.effectiveSalesEndDate;	
		fee.productID = pos.productID;
		fee.locationID = fnm.getLocationID(schema, fee.location);
	}
	
	private void initializeConfilictSchedule(POSInfo pos){
	 	//initialize confilict Pos fee schedule data
		confilictSchedule.productCategory = PRDCAT_NAME_POS;
		confilictSchedule.feeType = FEETYPE_NAME_POSFEE;
		confilictSchedule.locationCategory = "Agency";
		confilictSchedule.location = "SC parks";;
		confilictSchedule.productGroup = pos.productGroup;
		confilictSchedule.product = pos.product;
		confilictSchedule.fromDate = pos.effectiveSalesStartDate;
		confilictSchedule.toDate = pos.effectiveSalesEndDate;
		confilictSchedule.salesChannel = "CallCenter";
		confilictSchedule.acctCode = "0799.3000.1300.--; Overage/Shortage";
		confilictSchedule.rate = "6.00";
	  	
		confilictSchedule.loop = "";
		confilictSchedule.state = "";
		confilictSchedule.season = "";
		confilictSchedule.custType = "";
	}
}
