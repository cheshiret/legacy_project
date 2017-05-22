package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.authorized;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify Authorization privileges (Purchase Authorization Type:General and Check Business Rules in Sales Flow:unchecked) 
 * display or not in licence list page with different kinds of customers.
 * @Preconditions:
 * License setup
 * Purchase Authorization: Required
 * Purchase Authorization Type :General 
 * Auth Qty per LY : 1
 * Check Business Rules in Sales Flow:unchecked
 * License Year: Current year and next year
 * Quantity Control:Max allowed 2 within same License Year
 * Business rule setup for this licence:
 * Customer must be AT LEAST 40 years old on the date of LicenCE purchase
 * Customer must be AB Resident in order to purchase this license
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1640,1650,1660
 * 1640 (AB Resident; 40Y) Not authorized for all license year
 * 1650 (Can Resident; 40Y) Only authorized for Licence Year 2015, not 2014
 * 1660 (AB Resident; 35Y) Authorized for both 2014 and 2015
 * D_HF_ADD_PRIVILEGE_PRD:id=3320
 * D_HF_ADD_PRICING:id=4632
 * D_HF_ASSI_PRI_TO_STORE:id=2540
 * D_HF_ADD_QTY_CONTROL:id=2400
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3330
 * D_HF_ADD_PRI_BUSINESS_RULE:id=530,540
 * D_HF_PURCHASE_AUTHORIZATION:id=10,20,30
 * D_ASSIGN_FEATURE:id=6432
 * @SPEC:HFAB - Authorized Licenses (General / no business rules checking) displaying in product list [TC:118726] 
 * @Task#:Auto-2131          
 * 
 * @author SWang
 * @Date  Jun 10, 2014
 */
public class GeneralWithoutBusinessRulesCheck extends HFABWebTestCase{
	private Customer cust1, cust2, cust3;
	private PrivilegeInfo privilege_CurrentY, privilege_NextY;
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	
	@Override
	public void execute() {
		//For the first customer, make sure 'Required' Authorization privilege not displaying for both current and next year
		//Because the account meets all business rule but no authorization on file
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust1, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_CurrentY, privilege_NextY}, new Boolean[]{false, false});
		hf.signOut();
		
		//For the second customer, make sure 'Required' Authorization privilege displaying for next year, not for current year
		//Because the account has a business rule violation but has the authorization for next year on file and the authorization setup as Do Not check business rules in sales flow
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust2, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_CurrentY, privilege_NextY}, new Boolean[]{false, true});
		hf.signOut();
		
		//For the last customer, make sure 'Required' Authorization privilege displaying for both current and next year
		//Because the account has a business rule violation but has the authorization for both current and next year on file and the authorization setup as Do Not check business rules in sales flow
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust3, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_CurrentY, privilege_NextY}, new Boolean[]{true, true});
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		loginLM.location = "AB - Call Center Manager - Auto/HOWARD'S STORE & BAKERY(Store Loc)-HOWARD'S STORE & BAKERY(Station)";
		
		// Customer Info
		cust1 = new Customer();
		cust1.custNum = hfab.getCustomerNumByEmail("hfab_00016@webhftest.com", schema);
		cust1.email = cust1.custNum;
		cust1.password = TestProperty.getProperty("web.login.pw");
		cust1.residencyStatus = RESID_STATUS_ALBERTA;
		
		cust2 = new Customer();
		cust2.custNum = hfab.getCustomerNumByEmail("hfab_00017@webhftest.com", schema);
		cust2.email = cust2.custNum;
		cust2.password = cust1.password;
		cust2.residencyStatus = RESID_STATUS_CANADA;
		
		cust3 = new Customer();
		cust3.custNum = hfab.getCustomerNumByEmail("hfab_00018@webhftest.com", schema);
		cust3.email = cust3.custNum;
		cust3.password = cust1.password;
		cust3.residencyStatus = RESID_STATUS_ALBERTA;
		
		//privilege info - individual 
		privilege_CurrentY = new PrivilegeInfo();
		privilege_CurrentY.code = "AGN";
		privilege_CurrentY.name = "Auth_GeneralNoRuleCheck";
		privilege_CurrentY.licenseYear = hf.getFiscalYear(schema);
		
		privilege_NextY = new PrivilegeInfo();
		privilege_NextY.code = "AGN";
		privilege_NextY.name = "Auth_GeneralNoRuleCheck";
		privilege_NextY.licenseYear = String.valueOf(DateFunctions.getYearAfterGivenYear(1, privilege_CurrentY.licenseYear));
	}
}