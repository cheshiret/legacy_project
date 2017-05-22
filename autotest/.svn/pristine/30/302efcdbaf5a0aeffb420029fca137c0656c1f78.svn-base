package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.callcenterpossetup;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductAssignmentPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to verify error messages when unassign a POS product with specific revenue location to call center.
 * @Preconditions:
 * 1. Make sure the user qa-auto-adm has the permissions: SearchCallCenterPOSProductSetup, 
 * AssignCallCenterPOSProductSetup and EditCallCenterPOSProductSetup.
 * 2. Make sure the POS exists:
 *    ASCUE      Assign To Call UnError    Admissions     Assign To Call UnError(Specific location) 
 * @SPEC: TC032044
 * @LinkSetUp:  d_inv_add_mpos :id=490(PRODUCT='Assign To Call UnError')
 * @Task#: Auto-1306
 * @author Phoebe Chen
 * @Date  Oct 25, 2012
 */
public class UnAssignToCallWithErrorMessage extends FinanceManagerTestCase {
	private FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage.getInstance();
	private POSInfo pos = new POSInfo();
	private FeeScheduleData fee = new FeeScheduleData();
	private TimeZone timeZone;
	private String unassignAlreadyunAssigned;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoPosProductAssignmentPage();
		//Need to unassignPos first so that can assign the pos with the special date(start/end)
		fnm.unassignPOSProduct(pos);
		// Prepare data:assign the product to Call Center and verify the info in list
		fnm.assignPOSProdToCallCenter(pos);
		pos.assignStatus = YES_STATUS;
	
		// Unassign the product to Call Center without select any pos, and verify the pos status
		this.unassignPOSProdWithoutSelect(pos);
		assignPg.verifyAssignedPOSProductItemInList(pos, true);
		
		// Prepare data:unassign the pos
		fnm.unassignPOSProduct(pos);
		//unAssign a unassigned pos product, check the error message
		fnm.unassignPOSProduct(pos);
		this.verifyErrorMessage("unassignAlreadyunAssigned", unassignAlreadyunAssigned);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//login information
		String facilityID = "1";  //South Carolina State Park Service
		String facilityName = fnm.getFacilityName(facilityID, schema);
		login.contract = "SC Contract";
		login.location = "Administrator/" + facilityName;
		
		// POS info
		pos.product = "Assign To Call UnError";
		pos.productDescription = pos.product;
		pos.availableLocation = facilityName;
		pos.productGroup = "Admissions";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		pos.effectiveSalesEndDate = pos.effectiveSalesStartDate;
		pos.unitPrice = "16.89";
		pos.variablePriceAllowed = NO_STATUS;
		pos.partialQuantityAllowed = NO_STATUS;
		pos.productID = fnm.getProductID("Product Name", pos.product, null, schema);
		pos.searchByAssignStatus = "";
		this.setFeeData(pos);
		
		unassignAlreadyunAssigned = "Product Assign To Call UnError is not assigned to your location. Please unselect it.";
	}
	
	private void setFeeData(POSInfo pos) {
		// Fee Info
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
		
	/* unassign POS product without select the pos */
	public void unassignPOSProdWithoutSelect(POSInfo pos) {
		OrmsConfirmDialogWidget dialogPg = OrmsConfirmDialogWidget
				.getInstance();
		if (StringUtil.isEmpty(pos.productID)) {
			pos.productID = assignPg.getPOSProdID(pos.product);
		}
		assignPg.searchPOSProduct(pos);
		assignPg.unselectProductId(pos.productID);
		assignPg.clickUnassignSelectedPosProducts();
		ajax.waitLoading();
		Object page = browser.waitExists(dialogPg, assignPg);
		if (page == dialogPg) {
			dialogPg.clickOK();
			ajax.waitLoading();
			assignPg.waitLoading();
		}
		assignPg.waitLoading();
	}
	
	private void verifyErrorMessage(String type, String expectErrorMess) {
		boolean passed = true;
		String errorShow = assignPg.getErrorMsg();
		passed &= MiscFunctions.compareResult("Check error message for:" + type, expectErrorMess, errorShow);
		if(!passed){
			throw new ErrorOnPageException("Error message shown wrong, please check the log above!");
		}
	}
}
