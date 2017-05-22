package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.setup;

import java.util.Arrays;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (DEFECT-60352) Verify initial UI and error messages during edit Privileges product group privilege. 
 * check has questions, print documents, business rule and landowner sub tabs
 * @LinkSetUp:
 * d_assign_feature:id=5202,5212,5222 --%PrivilegeProductQuestion%, %PrivilegeProductFulfillmentDocument%, %PrivilegeProductBusinessRule%
 * d_hf_add_privilege_prd:id=2570 --prd group:Privilege, inventory type: ConvPack Type, select Landowner Licence check box
 * @SPEC:
 * Edit Privilege Product - Product Group is Privilege [TC:084801]
 * QuestionTab- display on product group [TC:099252]
 * Print Document Tab - Dsiplay on product group [TC:099251]
 * Business Rule Tab- Display on product group [TC:099250]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class EditPrivilegesPrdGroupPriv extends LicenseManagerTestCase {
	private LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
	private String ddlOption2, ddlOption3, testData5, msg1, msg2, msg3, msg4, testData1, testData2, testData3, testData4;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		//Verify initial UI and error messages when Privilege group is Privileges
		verifyInitialUI();
		verifyErrorMes();
		
		lm.gotoProductListFromProductDetailPage();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
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

		privilege.code = "SU2";
		privilege.name = "PrivilegeForSetup2";
		privilege.productGroup = "Privileges";

		ddlOption2 = "Fixed";
		ddlOption3 = "Range";

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
		boolean result = MiscFunctions.compareResult("Product Code", true, privDetailsPg.checkPrdCodeDisabled());
		result &= MiscFunctions.compareResult("Product Number", true, privDetailsPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Number", true, privDetailsPg.isPrdNumExist());
		result &= MiscFunctions.compareResult("Product Name", true, privDetailsPg.isPrdNameExist());
		result &= MiscFunctions.compareResult("Product Legal Name", true, privDetailsPg.isPrdLegalNameExist());
		result &= MiscFunctions.compareResult("Product group", true, privDetailsPg.checkPrdGroupDisabled());
		result &= MiscFunctions.compareResult("Product Status", true, privDetailsPg.isPrdStatusExist());
		result &= MiscFunctions.compareResult("Hunts Required", true, privDetailsPg.isHuntsRequiredExist());
		result &= MiscFunctions.compareResult("Display Category", true, privDetailsPg.isDisplayCategoryExist());
		result &= MiscFunctions.compareResult("Display Sub-Category", true, privDetailsPg.isDisplaySubCategoryExist());
		result &= MiscFunctions.compareResult("Report Category", true, privDetailsPg.isReportCategoryExist());
		result &= MiscFunctions.compareResult("Customer class", true, privDetailsPg.isCustClassExist());
		result &= MiscFunctions.compareResult("Authorization Quantity", true, privDetailsPg.isAuthorizationQuantityExist());
		result &= MiscFunctions.compareResult("Valid From Date Calculation", true, privDetailsPg.isValidFromDateCalculationExist());
		result &= MiscFunctions.compareResult("Prompt Indicator", true, privDetailsPg.isPromptIndicatorExist());
		result &= MiscFunctions.compareResult("Valid To Date Calculation", true, privDetailsPg.isValidToDateCalculationExist());
		result &= MiscFunctions.compareResult("Valid Days/Years", true, privDetailsPg.isValidDateOrYearsExist());
		result &= MiscFunctions.compareResult("Renewal Days", true, privDetailsPg.isRenewalDaysExist());
		result &= MiscFunctions.compareResult("Valid To Age", true, privDetailsPg.isValidToAgeExist());
		result &= MiscFunctions.compareResult("Valid Date Printing", true, privDetailsPg.isvalidDatePrintingOptionsExist());
		result &= MiscFunctions.compareResult("Inventory Type default value", true, privDetailsPg.isInventoryTypeExist());
		privDetailsPg.selectInventoryType(1);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("Inventory Quantity Type default value", ddlOption2, privDetailsPg.getInvQuantityType());
		result &= MiscFunctions.compareResult("Inventory Quantity Type options", Arrays.asList(ddlOption2, ddlOption3).toString(), privDetailsPg.getInvQuantityTypes().toString());
		result &= MiscFunctions.compareResult("Inventory Quantity", testData4, privDetailsPg.getInventoryQuantity());
		privDetailsPg.selectInvQuantityType(ddlOption3);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("Inventory Quantity From", testData2, privDetailsPg.getInventoryQuantityFrom());
		result &= MiscFunctions.compareResult("Inventory Quantity To", testData2, privDetailsPg.getInventoryQuantityTo());
		result &= MiscFunctions.compareResult("Allowed General Public", false, privDetailsPg.isAllowGeneralPublicExist());
		result &= MiscFunctions.compareResult("Allocation Type", true, privDetailsPg.isAllocationTypeExist());
		result &= MiscFunctions.compareResult("Allocation Privilege", true, privDetailsPg.isAllocationPrivExist());
		result &= MiscFunctions.compareResult("Landowner Privilege Indicator", true, privDetailsPg.isLandownerPriExist());
		result &= MiscFunctions.compareResult("Pricing Note", true, privDetailsPg.isPricingNoteExist());

		if(!result){
			throw new ErrorOnPageException("Initial UI for product group Privileges is wrong. Please check the details from previous logs.");
		}
		logger.info("Initial UI for product group Privileges is right");
	}

	private String getErrorMsg(){
		privDetailsPg.clickApply();
		ajax.waitLoading();
		return privDetailsPg.getErrorMsg();
	}

	private void verifyErrorMes(){
		boolean result = true;
		//Qty Type as Range
		privDetailsPg.selectInvQuantityType(ddlOption3);
		ajax.waitLoading();
		
		//Inventory Quantity From
		privDetailsPg.setInventoryQuantityFrom(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity From doesn't be specified", msg2, getErrorMsg());
		privDetailsPg.setInventoryQuantityFrom(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity From is zero", msg2, getErrorMsg());
		privDetailsPg.setInventoryQuantityFrom(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity From is invalid", msg2, getErrorMsg());

		//Inventory Quantity To
		privDetailsPg.setInventoryQuantityFrom(testData5);
		privDetailsPg.setInventoryQuantityTo(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity To doesn't be specified", msg3, getErrorMsg());
		privDetailsPg.setInventoryQuantityTo(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity To is zero", msg3, getErrorMsg());
		privDetailsPg.setInventoryQuantityTo(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity To is invalid", msg3, getErrorMsg());

		//Inventory Quantity From and To
		privDetailsPg.setInventoryQuantityTo(testData4);
		result &= MiscFunctions.compareResult("Qty To is less than the Qty From", msg4, getErrorMsg());
		privDetailsPg.setInventoryQuantityTo(testData5);
		result &= MiscFunctions.compareResult("Qty To equals to the Qty From", msg4, getErrorMsg());

		//Inventory quantity 
		privDetailsPg.selectInvQuantityType(ddlOption2);
		ajax.waitLoading();
		privDetailsPg.setInventoryQty(testData1);
		result &= MiscFunctions.compareResult("Inventory Quantity doesn't be specified", msg1, getErrorMsg());
		privDetailsPg.setInventoryQty(testData2);
		result &= MiscFunctions.compareResult("Inventory Quantity is zero", msg1, getErrorMsg());
		privDetailsPg.setInventoryQty(testData3);
		result &= MiscFunctions.compareResult("Inventory Quantity is invalid", msg1, getErrorMsg());

		if(!result){
			throw new ErrorOnPageException("Failed to check field value and error message when prd group is Privileges. Please check the details from previous logs.");
		}
		logger.info("Successfully verify field value and error message when prd group is Privileges. Please check the details");
	}

	private void verifySoldAsBonusOnlyIndicator(){
		LicMgrPriLandownerSubPage landOwnerPg = LicMgrPriLandownerSubPage.getInstance();
		lm.gotoLandownerTabFromPrivDetailsPg();
		if(landOwnerPg.isSoldAsBounsOnlyIndicatorExist()){
			logger.info("Successfully verify Sold As Bouns Only Indicator eixsts");
		}else throw new ErrorOnPageException("Failed to verify Sold As Bouns Only Indicator should exist");
	}

	private void haveQuestionsPrintDocumentsAndBusinessRuleTabs(){
		privDetailsPg.verifyQuestionsTabExist(true);
		privDetailsPg.verifyPrintDocumentsTabExist(true);
		privDetailsPg.verifyBusinessRuleTabExist(true);
	}
}
