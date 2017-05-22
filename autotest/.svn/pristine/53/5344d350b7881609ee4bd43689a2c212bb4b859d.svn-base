package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.setup;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Verify initial UI and error messages during edit Inventory product group privilege. 
 * check no questions, print documents, business rule and landowner sub tabs
 * @LinkSetUp:
 * d_assign_feature:id=5202,5212,5222 --%PrivilegeProductQuestion%, %PrivilegeProductFulfillmentDocument%, %PrivilegeProductBusinessRule%
 * d_hf_add_privilege_prd:id=2580 --Inventory,ConvPack Type.
 * @SPEC:
 * Edit Privilege Product - Product Group is Inventory [TC:084803]
 * QuestionTab- display on product group [TC:099252]
 * Print Document Tab - Dsiplay on product group [TC:099251]
 * Business Rule Tab- Display on product group [TC:099250]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class EditInventoryPrdGroupPriv extends LicenseManagerTestCase {
	private LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
	private String noneOption, msg, testData1, testData2, testData3;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		//Verify initial UI and error messages
		verifyInitialUI();
		verifyErrorMes();
		
		//No questions, print documents, business rule tabs and landowner sub tabs
		noSpecifiedSubTabs();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		privilege.code = "SU3";
		privilege.name = "PrivilegeForSetup3";
		privilege.productGroup = "Inventory";

		testData1 = StringUtil.EMPTY;
		testData2 = "0";
		testData3 = "@12";
		msg = "The Inventory Qty is required. Please enter an integer value greater than 0.";
	}

	private void verifyInitialUI(){
		boolean result = true;
		result &= MiscFunctions.compareResult("Product Code", true, privDetailsPg.checkPrdCodeDisabled());
		result &= MiscFunctions.compareResult("Product Number", false, privDetailsPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Name", true, privDetailsPg.isPrdNameExist());
		result &= MiscFunctions.compareResult("Product Legal Name", true, privDetailsPg.isPrdLegalNameExist());
		result &= MiscFunctions.compareResult("Product group", true, privDetailsPg.checkPrdGroupDisabled());
		result &= MiscFunctions.compareResult("Product Status", true, privDetailsPg.isPrdStatusExist());
		result &= MiscFunctions.compareResult("Hunts Required", false, privDetailsPg.isHuntsRequiredExist());
		result &= MiscFunctions.compareResult("Display Category", true, privDetailsPg.isDisplayCategoryExist());
		result &= MiscFunctions.compareResult("Display Sub-Category", true, privDetailsPg.isDisplaySubCategoryExist());
		result &= MiscFunctions.compareResult("Report Category", true, privDetailsPg.isReportCategoryExist());
		result &= MiscFunctions.compareResult("Customer class", true, privDetailsPg.isCustClassExist());
		result &= MiscFunctions.compareResult("Authorization Quantity", false, privDetailsPg.isAuthorizationQuantityExist());
		result &= MiscFunctions.compareResult("Valid From Date Calculation", false, privDetailsPg.isValidFromDateCalculationExist());
		result &= MiscFunctions.compareResult("Prompt Indicator", false, privDetailsPg.isPromptIndicatorExist());
		result &= MiscFunctions.compareResult("Valid To Date Calculation", false, privDetailsPg.isValidToDateCalculationExist());
		result &= MiscFunctions.compareResult("Valid Days/Years", false, privDetailsPg.isValidDateOrYearsExist());
		result &= MiscFunctions.compareResult("Renewal Days", false, privDetailsPg.isRenewalDaysExist());
		result &= MiscFunctions.compareResult("Valid To Age", false, privDetailsPg.isValidToAgeExist());
		result &= MiscFunctions.compareResult("Valid Date Printing", false, privDetailsPg.isvalidDatePrintingOptionsExist());
		result = MiscFunctions.compareResult("Inventory Type without None option", false, privDetailsPg.getInventoryTypeElement().contains(noneOption));
		result &= MiscFunctions.compareResult("Inventory Quantity Type", false, privDetailsPg.isInvQuantityTypeExist());
		result &= MiscFunctions.compareResult("Inventory Quantity", true, privDetailsPg.isInventoryQuantityExist());
		result &= MiscFunctions.compareResult("Inventory Quantity From", false, privDetailsPg.isInventoryQuantityFromExist());
		result &= MiscFunctions.compareResult("Inventory Quantity To", false, privDetailsPg.isInventoryQuantityToExist());
		result &= MiscFunctions.compareResult("Allow General Public not selected", false, privDetailsPg.isAllowGeneralPublicSelected());
		result &= MiscFunctions.compareResult("Allocation Type", false, privDetailsPg.isAllocationTypeExist());
		result &= MiscFunctions.compareResult("Allocation Privilege", false, privDetailsPg.isAllocationPrivExist());
		result &= MiscFunctions.compareResult("Landowner Privilege Indicator", false, privDetailsPg.isLandownerPriExist());
		result &= MiscFunctions.compareResult("Pricing Note", true, privDetailsPg.isPricingNoteExist());

		if(!result){
			throw new ErrorOnPageException("Initial UI for product group Inventory is wrong. Please check the details from previous logs.");
		}
		logger.info("Initial UI for product group Inventory is right");
	}

	private String getErrorMsg(){
		privDetailsPg.clickApply();
		ajax.waitLoading();
		return privDetailsPg.getErrorMsg();
	}

	private void verifyErrorMes(){
		privDetailsPg.setInventoryQty(testData1);
		boolean result = MiscFunctions.compareResult("Inventory Quantity doesn't be specified", msg, getErrorMsg());
		privDetailsPg.setInventoryQty(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity is zero", msg, getErrorMsg());
		privDetailsPg.setInventoryQty(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity is invalid", msg, getErrorMsg());
		if(!result){
			throw new ErrorOnPageException("Failed to check field value and error message when prd group is Inventory. Please check the details from previous logs.");
		}
		logger.info("Successfully verify field value and error message when prd group is Inventory. Please check the details");
	}
	
	private void noSpecifiedSubTabs(){
		privDetailsPg.verifyQuestionsTabExist(false);
		privDetailsPg.verifyPrintDocumentsTabExist(false);
		privDetailsPg.verifyBusinessRuleTabExist(false);
		privDetailsPg.verifyLandownerExist(false);
	}
}
