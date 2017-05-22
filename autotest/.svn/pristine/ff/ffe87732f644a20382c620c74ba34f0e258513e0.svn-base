package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.setup;

import java.util.Random;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify initial UI and error messages during add Inventory product group privilege. 
 * After click Apply button to check having questions, print documents and business rule tabs
 * @LinkSetUp:
 * d_assign_feature:id=5202,5212,5222 --%PrivilegeProductQuestion%, %PrivilegeProductFulfillmentDocument%, %PrivilegeProductBusinessRule%
 * @SPEC:
 * Add Privilege Product - Product Group is Inventory [TC:084747]
 * QuestionTab- display on product group [TC:099252]
 * Print Document Tab - Dsiplay on product group [TC:099251]
 * Business Rule Tab- Display on product group [TC:099250]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class AddInventoryPrdGroupPriv extends LicenseManagerTestCase {
	private LicMgrCreateNewPriviledgePage createwPriPrdPg= LicMgrCreateNewPriviledgePage.getInstance();
	private String noneOption, msg, testData1, testData2, testData3, testData4;
	private Random r= new Random(); 
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoCreatePrivilegeProductPageFromPrivilegeListPage();

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

		privilege.code = "S" + String.valueOf(r.nextInt(99));
		privilege.name = privilege.code + " QA Auto";
		privilege.productGroup = "Inventory";
		privilege.customerClasses = new String[]{"Individual"};

		testData1 = StringUtil.EMPTY;
		testData2 = "0";
		testData3 = "@12";
		testData4 = "1";
		msg = "The Inventory Qty is required. Please enter an integer value greater than 0.";
	}

	private void verifyInitialUI(){
		createwPriPrdPg.selectProductGroup(privilege.productGroup);
		ajax.waitLoading();

		//Check UI
		boolean result = true;
		result &= MiscFunctions.compareResult("Product Code", true, createwPriPrdPg.isPrdCodeExist());
		result &= MiscFunctions.compareResult("Product Number", false, createwPriPrdPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Name", true, createwPriPrdPg.isPrdNameExist());
		result &= MiscFunctions.compareResult("Product Legal Name", true, createwPriPrdPg.isPrdLegalNameExist());
		result &= MiscFunctions.compareResult("Product Status", true, createwPriPrdPg.isPrdStatusExist());
		result &= MiscFunctions.compareResult("Hunts Required", false, createwPriPrdPg.isHuntsRequiredExist());
		result &= MiscFunctions.compareResult("Display Category", true, createwPriPrdPg.isDisplayCategoryExist());
		result &= MiscFunctions.compareResult("Display Sub-Category", true, createwPriPrdPg.isDisplaySubCategoryExist());
		result &= MiscFunctions.compareResult("Report Category", true, createwPriPrdPg.isReportCategoryExist());
		result &= MiscFunctions.compareResult("Customer class", true, createwPriPrdPg.isCustClassExist());
		result &= MiscFunctions.compareResult("Authorization Quantity", false, createwPriPrdPg.isAuthorizationQuantityExist());
		result &= MiscFunctions.compareResult("Valid From Date Calculation", false, createwPriPrdPg.isValidFromDateCalculationExist());
		result &= MiscFunctions.compareResult("Prompt Indicator", false, createwPriPrdPg.isPromptIndicatorExist());
		result &= MiscFunctions.compareResult("Valid To Date Calculation", false, createwPriPrdPg.isValidToDateCalculationExist());
		result &= MiscFunctions.compareResult("Valid Days/Years", false, createwPriPrdPg.isValidDateOrYearsExist());
		result &= MiscFunctions.compareResult("Renewal Days", false, createwPriPrdPg.isRenewalDaysExist());
		result &= MiscFunctions.compareResult("Valid To Age", false, createwPriPrdPg.isValidToAgeExist());
		result &= MiscFunctions.compareResult("Valid Date Printing", false, createwPriPrdPg.isvalidDatePrintingOptionsExist());
		result = MiscFunctions.compareResult("Inventory Type without None option", false, createwPriPrdPg.getInventoryTypeElement().contains(noneOption));
		result &= MiscFunctions.compareResult("Inventory Quantity Type", false, createwPriPrdPg.isInvQuantityTypeExist()); //Confirm with Sherry
		result &= MiscFunctions.compareResult("Inventory default value", "1", createwPriPrdPg.getInventoryQuantity());
		result &= MiscFunctions.compareResult("Inventory Quantity From", false, createwPriPrdPg.isInventoryQuantityFromExist());
		result &= MiscFunctions.compareResult("Inventory Quantity To", false, createwPriPrdPg.isInventoryQuantityToExist());
		result &= MiscFunctions.compareResult("Allow General Public not selected", false, createwPriPrdPg.isAllowGeneralPublicSelected());
		result &= MiscFunctions.compareResult("Allocation Type", false, createwPriPrdPg.isAllocationTypeExist());
		result &= MiscFunctions.compareResult("Allocation Privilege", false, createwPriPrdPg.isAllocationPrivExist());
		result &= MiscFunctions.compareResult("Landowner Privilege Indicator", false, createwPriPrdPg.isLandownerPriExist());
		result &= MiscFunctions.compareResult("Pricing Note", true, createwPriPrdPg.isPricingNoteExist());

		if(!result){
			throw new ErrorOnPageException("Initial UI for product group Inventory is wrong. Please check the details from previous logs.");
		}
		logger.info("Initial UI for product group Inventory is right");
	}

	private String getErrorMsg(){
		createwPriPrdPg.clickApply();
		ajax.waitLoading();
		return createwPriPrdPg.getErrorMsg();
	}

	private void verifyErrorMes(){
		createwPriPrdPg.setupPrivilegeInfo(privilege);
		createwPriPrdPg.setInventoryQty(testData1);
		boolean result = MiscFunctions.compareResult("Inventory Quantity doesn't be specified", msg, getErrorMsg());
		createwPriPrdPg.setInventoryQty(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity is zero", msg, getErrorMsg());
		createwPriPrdPg.setInventoryQty(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity is invalid", msg, getErrorMsg());
		if(!result){
			throw new ErrorOnPageException("Failed to check field value and error message when prd group is Inventory. Please check the details from previous logs.");
		}
		logger.info("Successfully verify field value and error message when prd group is Inventory. Please check the details");
	}
	
	private void noSpecifiedSubTabs(){
		LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		createwPriPrdPg.setInventoryQty(testData4);
		createwPriPrdPg.clickApply();
		ajax.waitLoading();
		privDetailsPg.waitLoading();

		privDetailsPg.verifyQuestionsTabExist(false);
		privDetailsPg.verifyPrintDocumentsTabExist(false);
		privDetailsPg.verifyBusinessRuleTabExist(false);
		privDetailsPg.verifyLandownerExist(false);
	}
}
