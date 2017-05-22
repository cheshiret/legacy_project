package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPurchaseAuthorizationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify 'Purchase Authorization' for privilege. 
 * If privilege 'Required' authorization,  and customer have purchase authorization records, this privilege will display on list page;
 * If privilege has 'Optional' authorization, and we have added authorization records on customer, 
 * this privilege will display in list page;
 * If privilege has 'Optional' authorization, and we have no authorization record on customer, 
 * this privilege will also display in list page;  
 * When did purchase, privilege has 'Optional' authorization will display on list page; Privilege has 'Required' authorization will not display on list page.
 * In this test case, we also verify the number for privilege on list page.
 * @Preconditions:
 * @LinkSetUp:
 * d_assign_feature:id=5972
 * d_hf_add_cust_profile:id=3130,3140
 * d_hf_add_privilege_prd:id=2960,2970,2980,2990
 * d_hf_add_prvi_license_year:id=3090,3100,3110,3120
 * d_hf_add_pricing:id=4412,4422,4432,4442
 * d_hf_assi_pri_to_store:id=2180,2190,2200,2210
 * d_hf_add_qty_control:id=2160,2170,2180,2190
 * d_hf_priv_invtype_licyear:id=110
 * d_hf_add_allo_pri_inv:id=100
 * @SPEC: Get Privilege Product List for Purchase- Purchase Authoriztion [TC:117016]
 * @Task#: Auto-2154
 * 
 * @author Jane Wang
 * @Date  May 07, 2014
 */
public class VerifyPrivListForPurchaseAuth extends LicenseManagerTestCase {

	private PrivilegeInfo priv01, priv02, priv03, priv04;
	private Data<PrivilegePurchaseAuthorization> privPurchaseAuth01, privPurchaseAuth02, privPurchaseAuth03, privPurchaseAuth04;
	private LicMgrCustomerPurchaseAuthorizationPage purchaseAuthPg = LicMgrCustomerPurchaseAuthorizationPage.getInstance();
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	private String nextLicYear;
	
	@Override
	public void execute() {
		String ordNum = null;
		boolean needCleanup = true;
		try {
			lm.loginLicenseManager(login);

			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			//clean up customer purchase authorization
			lm.gotoCustomerPurchaseAuthorizationPage();
			deactivateAllActiveCustPurchaseAuthorizationRecords();
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			//verify privilege existence on list page
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, false);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, true);
			//As we will always add privilege license year for fiscal year and next year, so priv03, priv04 will display for next license year
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, nextLicYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, nextLicYear, true);
			
			lm.gotoHomePage();
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoCustomerPurchaseAuthorizationPage();
			//add active/inactive purchase authorizations, and verify privilege existence on list page
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth01);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth02);
			purchaseAuthPg.deactivatePurchaseAuth(privPurchaseAuth02);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth03);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth04);
			purchaseAuthPg.deactivatePurchaseAuth(privPurchaseAuth04);
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, true);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, true);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, nextLicYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, nextLicYear, true);
			lm.addPrivilegeItem(priv01, priv03, priv04);
			lm.goToCart();
			//purchase privilege(use authorization)
			ordNum = lm.processOrderCart(pay);
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoCustomerPurchaseAuthorizationPage();
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			//verify privilege existence on list page after purchase privilege
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, false);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, true);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, nextLicYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, true);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, nextLicYear, true);
			
			//clean up order
			lm.gotoHomePage();
			lm.reversePrivilegeOrderToCleanUp(ordNum, "14 - Other", "Automation Test", pay);
			needCleanup = false;
			
			lm.gotoHomePage();
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoCustomerPurchaseAuthorizationPage();
			//add active purchase authorizations for more than one, and verify privilege existence on list page
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth01);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth04);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth04);
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			//verify privilege existence on list page after purchase privilege
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, true, 2);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, true, 1);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, nextLicYear, true, 1);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, true, 2);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, nextLicYear, true, 1);
			
			lm.gotoHomePage();
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			//clean up customer purchase authorization
			lm.gotoCustomerPurchaseAuthorizationPage();
			deactivateAllActiveCustPurchaseAuthorizationRecords();
			//add active purchase authorizations for license year and next year
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth01);
			PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth01, nextLicYear);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth01);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth03);
			PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth03, nextLicYear);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth03);
			lm.addPrivilegePurchaseAuthorization(privPurchaseAuth03);
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			//verify privilege existence on list page after purchase privilege
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, true, 1);
			addItemPg.verifyPrivExistence(priv01.code, priv01.name, nextLicYear, true, 1);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
			addItemPg.verifyPrivExistence(priv02.code, priv02.name, nextLicYear, false);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, true, 1);
			addItemPg.verifyPrivExistence(priv03.code, priv03.name, nextLicYear, true, 2);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, true, 1);
			addItemPg.verifyPrivExistence(priv04.code, priv04.name, nextLicYear, true, 1);
			lm.logOutLicenseManager();
		}finally {
			if(StringUtil.notEmpty(ordNum) && needCleanup) {
				lm.loginLicenseManager(login);
				lm.reversePrivilegeOrderToCleanUp(ordNum, "14 - Other", "Automation Test", pay);
				lm.logOutLicenseManager();
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		priv01 = new PrivilegeInfo();
		priv01.code = "RG1";
		priv01.name = "RequiredGeneral01";
		priv01.purchasingName = priv01.code + "-" + priv01.name;
		priv01.licenseYear = lm.getFiscalYear(schema);
		//for setup script, we will always add license year for fiscal year and next year
		nextLicYear = String.valueOf(Integer.parseInt(priv01.licenseYear)+1);
		
		priv02 = new PrivilegeInfo();
		priv02.code = "RH1";
		priv02.name = "RequiredHunterHost01";
		priv02.purchasingName = priv02.code + "-" + priv02.name;
		priv02.licenseYear = priv01.licenseYear;
		
		priv03 = new PrivilegeInfo();
		priv03.code = "OG1";
		priv03.name = "OptionalGeneral01";
		priv03.purchasingName = priv03.code + "-" + priv03.name;
		priv03.licenseYear = priv01.licenseYear;
		
		priv04 = new PrivilegeInfo();
		priv04.code = "OH1";
		priv04.name = "OptionalHunterHost01";
		priv04.purchasingName = priv04.code + "-" + priv04.name;
		priv04.licenseYear = priv01.licenseYear;
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.lName = "TEST-MSIndiv01";
		cust.fName = "QA-MSIndiv01";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		privPurchaseAuth01 = new Data<PrivilegePurchaseAuthorization>();
		PrivilegePurchaseAuthorization.AuthorizationType.setValue(privPurchaseAuth01, "General");
		PrivilegePurchaseAuthorization.AuthedPrivilege.setValue(privPurchaseAuth01, priv01.code+" - "+priv01.name);
		PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth01, priv01.licenseYear);
		PrivilegePurchaseAuthorization.AuthedReason.setValue(privPurchaseAuth01, "Regression Test");
		
		privPurchaseAuth02 = new Data<PrivilegePurchaseAuthorization>();
		PrivilegePurchaseAuthorization.AuthorizationType.setValue(privPurchaseAuth02, "Hunter Host");
		PrivilegePurchaseAuthorization.AuthedPrivilege.setValue(privPurchaseAuth02, priv02.code+" - "+priv02.name);
		PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth02, priv02.licenseYear);
		PrivilegePurchaseAuthorization.AuthedReason.setValue(privPurchaseAuth02, "Regression Test");
		PrivilegePurchaseAuthorization.HunterHostNum.setValue(privPurchaseAuth02, cust.custNum);
		PrivilegePurchaseAuthorization.HunterHostRelationship.setValue(privPurchaseAuth02, "Non-Relative");
		
		privPurchaseAuth03 = new Data<PrivilegePurchaseAuthorization>();
		PrivilegePurchaseAuthorization.AuthorizationType.setValue(privPurchaseAuth03, "General");
		PrivilegePurchaseAuthorization.AuthedPrivilege.setValue(privPurchaseAuth03, priv03.code+" - "+priv03.name);
		PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth03, priv03.licenseYear);
		PrivilegePurchaseAuthorization.AuthedReason.setValue(privPurchaseAuth03, "Regression Test");
		
		privPurchaseAuth04 = new Data<PrivilegePurchaseAuthorization>();
		PrivilegePurchaseAuthorization.AuthorizationType.setValue(privPurchaseAuth04, "Hunter Host");
		PrivilegePurchaseAuthorization.AuthedPrivilege.setValue(privPurchaseAuth04, priv04.code+" - "+priv04.name);
		PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.setValue(privPurchaseAuth04, priv04.licenseYear);
		PrivilegePurchaseAuthorization.AuthedReason.setValue(privPurchaseAuth04, "Regression Test");
		PrivilegePurchaseAuthorization.HunterHostNum.setValue(privPurchaseAuth04, cust.custNum);
		PrivilegePurchaseAuthorization.HunterHostRelationship.setValue(privPurchaseAuth04, "Non-Relative");
	}

	private void deactivateAllActiveCustPurchaseAuthorizationRecords() {
		LicMgrCustomerPurchaseAuthorizationPage custPurchaseAuthPg = LicMgrCustomerPurchaseAuthorizationPage.getInstance();
		logger.info("Deactivate all active purchase authorization records.");
		custPurchaseAuthPg.selectStatus("Active");
		custPurchaseAuthPg.clickGo();
		ajax.waitLoading();
		if(custPurchaseAuthPg.isDeactivateButtonEnable()) {
			custPurchaseAuthPg.selectAll();
			custPurchaseAuthPg.clickDeactivate();
			ajax.waitLoading();
			custPurchaseAuthPg.waitLoading();
		}
	}
}
