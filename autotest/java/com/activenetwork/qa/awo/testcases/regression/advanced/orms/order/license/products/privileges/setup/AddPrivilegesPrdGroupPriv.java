package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.setup;

import java.util.Arrays;
import java.util.Random;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify initial UI and error messages during add Privileges product group privilege. 
 * After click Apply button to check having questions, print documents and business rule tabs
 * @LinkSetUp:
 * d_assign_feature:id=5202,5212,5222 --%PrivilegeProductQuestion%, %PrivilegeProductFulfillmentDocument%, %PrivilegeProductBusinessRule%
 * @SPEC:
 * Add Privilege Product - Product Group is Privilege [TC:084678]
 * QuestionTab- display on product group [TC:099252]
 * Print Document Tab - Dsiplay on product group [TC:099251]
 * Business Rule Tab- Display on product group [TC:099250]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class AddPrivilegesPrdGroupPriv extends LicenseManagerTestCase {
	private LicMgrCreateNewPriviledgePage createwPriPrdPg= LicMgrCreateNewPriviledgePage.getInstance();
	private String ddlOption1, ddlOption2, ddlOption3, testData5, msg1, msg2, msg3, msg4, testData1, testData2, testData3, testData4;
	private Random r= new Random(); 

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoCreatePrivilegeProductPageFromPrivilegeListPage();

		//Verify initial UI and error messages when Privilege group is Privileges
		verifyInitialUI();
		verifyErrorMes();

		//Verify "Sold As Bouns Only Indicator" under Landowner tab after click Apply button during add privilege
		verifySoldAsBonusOnlyIndicator();

		//Have questions, print documents and business rule tabs
		haveQuestionsPrintDocumentsAndBusinessRuleTabs();

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		privilege.code = String.valueOf(r.nextInt(999));
		privilege.name = privilege.code + " QA Auto";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.inventoryQtyType = "Range";
		privilege.isLandowner = true;

		ddlOption1 = "None";
		ddlOption2 =  "Fixed";
		ddlOption3 = privilege.inventoryQtyType;

		testData1 = StringUtil.EMPTY;
		testData2 = "0";
		testData3 = "@12";
		testData4 = "1";
		testData5 = "5";
		msg1 = "The Inventory Qty is required. Please enter an integer value greater than 0.";
		msg2 = "The Qty From is required. Please enter an integer value greater than 0.";
		msg3 = "The Qty To is required. Please enter an integer value greater than 0.";
		msg4 = "The Qty To must be greater than the Qty From. Please change your entries.";
	}

	private void verifyInitialUI(){
		boolean result = MiscFunctions.compareResult("Product Code", true, createwPriPrdPg.isPrdCodeExist());
		result &= MiscFunctions.compareResult("Product Number", true, createwPriPrdPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Number", true, createwPriPrdPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Name", true, createwPriPrdPg.isPrdNameExist());
		result &= MiscFunctions.compareResult("Product Legal Name", true, createwPriPrdPg.isPrdLegalNameExist());
		result &= MiscFunctions.compareResult("Product Status", true, createwPriPrdPg.isPrdStatusExist());
		result &= MiscFunctions.compareResult("Hunts Required", true, createwPriPrdPg.isHuntsRequiredExist());
		result &= MiscFunctions.compareResult("Display Category", true, createwPriPrdPg.isDisplayCategoryExist());
		result &= MiscFunctions.compareResult("Display Sub-Category", true, createwPriPrdPg.isDisplaySubCategoryExist());
		result &= MiscFunctions.compareResult("Report Category", true, createwPriPrdPg.isReportCategoryExist());
		result &= MiscFunctions.compareResult("Customer class", true, createwPriPrdPg.isCustClassExist());
		result &= MiscFunctions.compareResult("Authorization Quantity", true, createwPriPrdPg.isAuthorizationQuantityExist());
		result &= MiscFunctions.compareResult("Valid From Date Calculation", true, createwPriPrdPg.isValidFromDateCalculationExist());
		result &= MiscFunctions.compareResult("Prompt Indicator", true, createwPriPrdPg.isPromptIndicatorExist());
		result &= MiscFunctions.compareResult("Valid To Date Calculation", true, createwPriPrdPg.isValidToDateCalculationExist());
		result &= MiscFunctions.compareResult("Valid Days/Years", true, createwPriPrdPg.isValidDateOrYearsExist());
		result &= MiscFunctions.compareResult("Renewal Days", true, createwPriPrdPg.isRenewalDaysExist());
		result &= MiscFunctions.compareResult("Valid To Age", true, createwPriPrdPg.isValidToAgeExist());
		result &= MiscFunctions.compareResult("Valid Date Printing", true, createwPriPrdPg.isvalidDatePrintingOptionsExist());
		result &= MiscFunctions.compareResult("Inventory Type default value", ddlOption1, createwPriPrdPg.getInventoryType());
		createwPriPrdPg.selectInventoryType(1);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("Inventory Quantity Type default value", ddlOption2, createwPriPrdPg.getInvQuantityType());
		result &= MiscFunctions.compareResult("Inventory Quantity Type options", Arrays.asList(ddlOption2, ddlOption3).toString(), createwPriPrdPg.getInvQuantityTypes().toString());
		result &= MiscFunctions.compareResult("Inventory Quantity", testData4, createwPriPrdPg.getInventoryQuantity());
		createwPriPrdPg.selectInvQuantityType(ddlOption3);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("Inventory Quantity From", testData2, createwPriPrdPg.getInventoryQuantityFrom());
		result &= MiscFunctions.compareResult("Inventory Quantity To", testData2, createwPriPrdPg.getInventoryQuantityTo());
		result &= MiscFunctions.compareResult("Allowed General Public", false, createwPriPrdPg.isAllowGeneralPublicExist());
		result &= MiscFunctions.compareResult("Allocation Type", true, createwPriPrdPg.isAllocationTypeExist());
		result &= MiscFunctions.compareResult("Allocation Privilege", true, createwPriPrdPg.isAllocationPrivExist());
		result &= MiscFunctions.compareResult("Landowner Privilege Indicator", true, createwPriPrdPg.isLandownerPriExist());
		result &= MiscFunctions.compareResult("Pricing Note", true, createwPriPrdPg.isPricingNoteExist());

		if(!result){
			throw new ErrorOnPageException("Initial UI for product group Privileges is wrong. Please check the details from previous logs.");
		}
		logger.info("Initial UI for product group Privileges is right");
	}

	private String getErrorMsg(){
		createwPriPrdPg.clickApply();
		ajax.waitLoading();
		return createwPriPrdPg.getErrorMsg();
	}

	private void verifyErrorMes(){
		boolean result = true;
		createwPriPrdPg.setupPrivilegeInfo(privilege);

		//Inventory Quantity From
		createwPriPrdPg.setInventoryQuantityFrom(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity From doesn't be specified", msg2, getErrorMsg());
		createwPriPrdPg.setInventoryQuantityFrom(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity From is zero", msg2, getErrorMsg());
		createwPriPrdPg.setInventoryQuantityFrom(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity From is invalid", msg2, getErrorMsg());

		//Inventory Quantity To
		createwPriPrdPg.setInventoryQuantityFrom(testData5);
		createwPriPrdPg.setInventoryQuantityTo(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity To doesn't be specified", msg3, getErrorMsg());
		createwPriPrdPg.setInventoryQuantityTo(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity To is zero", msg3, getErrorMsg());
		createwPriPrdPg.setInventoryQuantityTo(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity To is invalid", msg3, getErrorMsg());

		//Inventory Quantity From and To
		createwPriPrdPg.setInventoryQuantityTo(testData4);
		result &= MiscFunctions.compareResult("Qty To is less than the Qty From", msg4, getErrorMsg());
		createwPriPrdPg.setInventoryQuantityTo(testData5);
		result &= MiscFunctions.compareResult("Qty To equals to the Qty From", msg4, getErrorMsg());

		//Inventory quantity 
		createwPriPrdPg.selectInvQuantityType(ddlOption2);
		ajax.waitLoading();
		createwPriPrdPg.setInventoryQty(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity doesn't be specified", msg1, getErrorMsg());
		createwPriPrdPg.setInventoryQty(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity is zero", msg1, getErrorMsg());
		createwPriPrdPg.setInventoryQty(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity is invalid", msg1, getErrorMsg());

		if(!result){
			throw new ErrorOnPageException("Failed to check field value and error message when prd group is Privileges. Please check the details from previous logs.");
		}
		logger.info("Successfully verify field value and error message when prd group is Privileges. Please check the details");
	}

	private void verifySoldAsBonusOnlyIndicator(){
		LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPriLandownerSubPage landOwnerPg = LicMgrPriLandownerSubPage.getInstance();
		createwPriPrdPg.setInventoryQty(testData4);
		createwPriPrdPg.clickApply();
		ajax.waitLoading();
		privDetailsPg.waitLoading();

		lm.gotoLandownerTabFromPrivDetailsPg();
		if(landOwnerPg.isSoldAsBounsOnlyIndicatorExist()){
			logger.info("Successfully verify Sold As Bouns Only Indicator eixsts");
		}else throw new ErrorOnPageException("Failed to verify Sold As Bouns Only Indicator should exist");
	}

	private void haveQuestionsPrintDocumentsAndBusinessRuleTabs(){
		LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		privDetailsPg.verifyQuestionsTabExist(true);
		privDetailsPg.verifyPrintDocumentsTabExist(true);
		privDetailsPg.verifyBusinessRuleTabExist(true);
	}
}
