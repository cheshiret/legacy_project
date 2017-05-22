package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.Activity;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify add and edit a ra fee schedule for activity
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=250,260
 * @SPEC:[TC:110135] Add RA Fee Schedule for Activity Product
 *       [TC:110136] Edit RA Fee Schedule for Activity Product 
 * @Task#: Auto-2136
 * @author Phoebe Chen
 * @Date  April 30, 2014
 */
public class VerifyUI extends FinanceManagerTestCase {
	private RaFeeScheduleData raFee = new RaFeeScheduleData();
	private RaFeeScheduleData raFee_dup = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private boolean passed = true;
	private String errMsg_effectiveDateIsEmpty, errMsg_accountIsEmpty, errMsg_rateIsEmpty,
	               errMsg_duplicateInfo, errMsg_overlaplicenseyear;
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//Clean data
		schMainPg.deactiveAllTheFeeForProduct(raFee.product);
		schMainPg.deactiveAllTheFeeForProduct(raFee_dup.product);
		
		fnm.addNewRaFeeScheduleToDetailPg(raFee);
		
		//Verify the UI
		this.verifyProductGroup();
		this.verifyTransactionType();
		
		//Set the effective date as empty
		raFee.effectDate = StringUtil.EMPTY;
		detailsPage.setupRaFeeSchDetailInfo(raFee,false);
		String actMsg = this.setUpEffectiveDateAsEmptyAndClickApply();
		passed &=  MiscFunctions.compareResult("Effecitve date is empty:", errMsg_effectiveDateIsEmpty, actMsg);
	
		//Set account as empty
		this.setUpRAFee(raFee);
		raFee.acctCode = "none";
		actMsg = detailsPage.setupRaFeeSchForAdd(raFee);
		passed &=  MiscFunctions.compareResult("Account code is empty:", errMsg_accountIsEmpty, actMsg);
		
		//Set rate as empty
		this.setUpRAFee(raFee);
		raFee.baseRate = StringUtil.EMPTY;
		actMsg = detailsPage.setupRaFeeSchForAdd(raFee);
		passed &=  MiscFunctions.compareResult("Rate is empty:", errMsg_rateIsEmpty, actMsg);
		
		if(!passed){
			throw new ErrorOnPageException("Not all the check point is correct, please check the log above!");
		}
		logger.info("All validate are correct!");
		
		//Verify for edit the active fee schedule the same as exist ones
		this.setUpRAFee(raFee);
		raFee.feeSchdId = this.setUpRAFeeInfoAndClickOk(raFee);
		raFee_dup.feeSchdId = fnm.addNewRaFeeSchedule(raFee_dup);
		//Active both fee schedule
		schMainPg.activeRaFeeSchedules(raFee.feeSchdId, raFee_dup.feeSchdId);
		
		//Set up error message for duplicate or overlap information
		errMsg_duplicateInfo = "The RA Fee Schedule " + raFee_dup.feeSchdId + " cannot be activated because another identical active RA Fee Schedule " + raFee.feeSchdId + " exists.";
		errMsg_overlaplicenseyear = "The RA Fee Schedule " + raFee_dup.feeSchdId + " cannot be activated because another similar active RA Fee Schedule " + raFee.feeSchdId + " already exists with overlapping "
			+ raFee.fromLicenseYear + " From and To entries. Please inactivate that record first or modify the + raFee.fromLicenseYear + Range of that record first to eliminate the overlap";
	
		//Update the second ra fee schedule the same as the first
		this.setUpRAFee(raFee_dup); 
		fnm.gotoRaFeeSchDetailPgFromRaFeeSchSearchPg(raFee_dup.feeId);
		actMsg = fnm.editRAFeeSchedule(raFee_dup);
		passed &=  MiscFunctions.compareResult("Update ra fee schedule with duplication information:", errMsg_duplicateInfo, actMsg);
		
		//Update the second ra fee schedule with overlapped license year
		raFee_dup.fromLicenseYear = "All";
		actMsg = detailsPage.setupRaFeeSchForEdit(raFee_dup);
		passed &=  MiscFunctions.compareResult("Update ra fee schedule with overlap license year:", errMsg_overlaplicenseyear, actMsg);
		
		fnm.logoutFinanceManager();
	}


	private void verifyTransactionType() {
		List<String> transType = detailsPage.getTransactionTypeOptions();
		if(transType.size()!=1||!transType.get(0).equalsIgnoreCase(TRANNAME_REGISTER_FOR_ACTIVITY)){
			throw new ErrorOnPageException("Transaction type is not correct", TRANNAME_REGISTER_FOR_ACTIVITY,	transType.toString());
		}
		logger.info("Transaction type element is correct!");
		
	}

	private void verifyProductGroup() {
		List<String> actProductGroups = detailsPage.getAllProductGroup();
		List<String[]> dbProductGroups = fnm
				.getProductGroupNameUsingFeeSchdDetailPg(schema, PRDCAT_ACTIVITY, PRDSUBCAT_PRDTYPE_ACTIVITY);
		if (actProductGroups.containsAll(dbProductGroups)&&dbProductGroups.containsAll(actProductGroups)) {
			throw new ErrorOnPageException("Product group is not correct", dbProductGroups.toString(),	actProductGroups.toString());
		}
		logger.info("Aviable product group element is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = fnm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "Administrator/" + facilityName;

		//Set up ra fee information
		raFee.location = facilityName;
		raFee.locationCategory = "Contract";
		
		this.setUpRAFee(raFee);
		
		//Set up another ra fee 
		raFee_dup.location = facilityName;
		raFee_dup.locationCategory = "Contract";
		raFee_dup.productCategory = "Activity";
		raFee_dup.productGroup = "FeeAddEditGroup";
		raFee_dup.product = "Activity RA fee 06";
		raFee_dup.effectDate = DateFunctions.getDateAfterToday(-2);
		raFee_dup.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		raFee_dup.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		raFee_dup.salesChannel = "CallCenter";
		raFee_dup.locationClass = "MDWFP Headquarters";
		raFee_dup.raFeeOption = "Order Confirmed (will be reversed due to non payment)";
		raFee_dup.tranType = "Register for Activity";
		raFee_dup.acctCode = "3461.3010.30000.9001.---.---.--.----.----.--------; RA Fees - Consumables";
		raFee_dup.applyRate = "Per Unit";
		raFee_dup.baseRate = "1.34";
		
		errMsg_effectiveDateIsEmpty = "An Effective Date for the RA Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided";
		errMsg_accountIsEmpty = "An Account for the RA Fee Schedule is required. Please select the Account from the list provided";
		errMsg_rateIsEmpty = "The Base Rate is required. Please enter the Base Rate in the field provided";
	}
	
	private void setUpRAFee(RaFeeScheduleData raFee){
		raFee.productCategory = "Activity";
		raFee.productGroup = "FeeAddEditGroup";
		raFee.product = "Activity RA fee 05";
		raFee.effectDate = DateFunctions.getDateAfterToday(-2);
		raFee.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		raFee.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear());
		raFee.salesChannel = "CallCenter";
		raFee.locationClass = "MDWFP Headquarters";
		raFee.raFeeOption = "Order Confirmed (will be reversed due to non payment)";
		raFee.tranType = "Register for Activity";
		raFee.acctCode = "3461.3010.30000.9001.---.---.--.----.----.--------; RA Fees - Consumables";
		raFee.applyRate = "Per Unit";
		raFee.baseRate = "1.34";
	}
	
	private String setUpEffectiveDateAsEmptyAndClickApply(){
		detailsPage.setEffectvieDateAsEmpty();
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		String errMsg = detailsPage.getErrorMsg();
		return errMsg;
	}
	
	private String setUpRAFeeInfoAndClickOk(RaFeeScheduleData raFee){
		String feeId = detailsPage.setupRaFeeSchForAdd(raFee);
		ajax.waitLoading();
		schMainPg.waitLoading();
		return feeId;
	}
}
