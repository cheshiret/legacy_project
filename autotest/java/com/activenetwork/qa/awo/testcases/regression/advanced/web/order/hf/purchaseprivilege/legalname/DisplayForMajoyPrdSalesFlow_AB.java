package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.legalname;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFNewProductsAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFProductQuestionnairePage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify AB contract privilege legal name (not product name) displaying
 * 1. Start licence purchase flow until check out the shopping cart successfully
 *     Product list page, Product category page, Product category page, Education Page, Question page, New Product Added page, Shopping cart page, Confirmation page,
 * 2. Check under My Account 
 *     Current Licence section, Recent Orders section, Order History, Licence list, Licence Details
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1600 
 * D_HF_ADD_PRIVILEGE_PRD:id=3130,3140
 * D_HF_ADD_PRICING:id=4572,4582
 * D_HF_ASSI_PRI_TO_STORE:id=2330,2340 
 * D_HF_ADD_QTY_CONTROL:id=2310,2320
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3240,3250
 * D_HF_ADD_PRI_BUSINESS_RULE:id=540 
 * D_HF_ADD_QUESTION:id=180
 * D_HF_ASSI_QUESTION_TO_PRI:id=100
 * D_ASSIGN_FEATURE:id=6182
 * @SPEC:
 * HF Web - Product legal name setup [TC:118729] 
 * HF Web - Legal name displaying for majoy products sales flow (for automation) [TC:118734] 
 * @Task#:AUTO-2130 
 * 
 * @author SWang
 * @Date  Jul 14, 2014
 */
public class DisplayForMajoyPrdSalesFlow_AB extends HFABWebTestCase{
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
	private HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage.getInstance();
	private HFNewProductsAddedPage newPrdAddedPg = HFNewProductsAddedPage.getInstance();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private PrivilegeInfo priviNewProd = new PrivilegeInfo();
	private HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
	private HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private String licenceOrderNum, licenceNum1, licenceNum2, invalidateReason;
	private String[] licenceNums;

	public void execute() {
		//Precondition: Delete account's all educations
		hfab.deleteEducationRecords(schema, cus.fName, cus.lName);

		try{
			//Lookup account to account overview page
			hfab.signIn(url, cus.email, cus.password);
			hfab.gotoAccountOverviewPgFromYourAccountFoundPg();

			//Start licence purchase flow until check out the shopping cart successfully
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Product list page
			hfab.gotoLicenseCategoriesListPg(cus, true);
			catListPg.verifyPrivilegeExist(privilege.name, true);

			//Product category page
			catListPg.filterPrivilege(privilege.displayCategory, privilege.displaySubCategory, privilege.licenseYear);
			catListPg.verifyPrivilegeExist(privilege.name, true);

			//Product Details page
			hfab.gotoLicenseDetailsPgFromLicenseCatListPg(privilege.name);
			prdDetailsPg.verifyPrivilegeName(privilege.name);

			//Education Page
			hfab.addPrivilegeFromPrdDetailsPg(privilege, eduInfoPg);
			eduInfoPg.verifyProdName(privilege.name);

			//Question page
			hfab.gotoPrdQuestionPgFromEduInfomationPg();
			prdQuePg.verifyProdName(privilege.name);

			//New Product Added page
			hfab.gotoNewProdAddedPgFromProdQuestionPg(privilege);
			newPrdAddedPg.verifyPrivilegeName(priviNewProd.name);

			//Shopping cart page
			hfab.gotoShoppingCartPgFromNewProdAddedPg();
			shoppingCartPg.verifyPrivilegesExist(privilege, priviNewProd);

			//Confirmation page
			licenceOrderNum = hf.checkOutCartToConfirmationPage(pay).split(" ")[0].trim();
			licenceNums = hf.getPrivilegeNumsByOrdNum(schema, licenceOrderNum);
			licenceNum1 = licenceNums[0];
			licenceNum2 = licenceNums[1];
			confirmPg.verifyPrivOrdItemInfo(privilege, priviNewProd);

			//Check under My Account
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Current Licence section
			hfab.gotoMyAccountPgFromHeaderBar();
			accountOverviewPg.verifyLicenceNameExistUnderCurrentLicences(privilege.name, licenceNum1, true);
			accountOverviewPg.verifyLicenceNameExistUnderCurrentLicences(priviNewProd.name, licenceNum2, true);

			//Recent Orders section
			accountOverviewPg.verifyLicenceNameExistUnderUnderRecentOrders(privilege.name, licenceNum1, true);
			accountOverviewPg.verifyLicenceNameExistUnderUnderRecentOrders(priviNewProd.name, licenceNum2, true);

			//Order History
			hfab.gotoOrderHistoryPgFromMyAcctPanel();
			orderHistoryPg.verifyLicenceExist(privilege.name, licenceNum1, true);
			orderHistoryPg.verifyLicenceExist(priviNewProd.name, licenceNum2, true);

			//Licence list
			hfab.gotoCurrentLicencesListPgFromMyAcctPanel();
			currentLicensePg.verifyLicenceExist(privilege.name, licenceNum1, true);
			currentLicensePg.verifyLicenceExist(priviNewProd.name, licenceNum2, true);

			//Licence Details
			hfab.gotoLicDetailsPgFromCurtLicListPg(licenceNum1);
			licDetailsPg.verifyLicName(privilege.name);

			hfab.signOut();
		}finally{
			//Invalidate licence order in License Manager
			if(StringUtil.notEmpty(licenceOrderNum))
				new com.activenetwork.qa.awo.supportscripts.function.license.InvalidatePrivilegeOrderFunction().execute(loginLM, licenceOrderNum, invalidateReason);
		}

	}

	public void wrapParameters(Object[] param) {
		cus.custNum = hfab.getCustomerNumByEmail("hfab_00020@webhftest.com", schema);
		cus.email = cus.custNum;
		cus.residencyStatus = RESID_STATUS_ALBERTA;
		cus.fName = "HFAB_FN20";
		cus.lName = "HFAB_LN20";

		//Privilege parameters
		privilege.name = "AB Privilege Legal Name";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Fishing";  

		//Question
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questPrintText = "Question for testing privilege legal name display?";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		
		//New prod added
		priviNewProd.name = "AB Question Privilege Legal Name";
		priviNewProd.licenseYear = privilege.licenseYear;

		//Login License Manager
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
		loginLM.location = "AB - Call Center Manager - Auto/DOUBLE L CONFECTIONERY(Store Loc)";
		invalidateReason = "33 - Other";
	}
}

