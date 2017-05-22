package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.authorized;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABWebTestCase;

/**
 * 
 * @Description:Verify Authorization privileges (Purchase Authorization Type:General and Check Business Rules in Sales Flow:unchecked) 
 * display or not in licence list page with different kinds of customers.
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1700,1710
 * D_HF_ADD_PRIVILEGE_PRD:id=3370,3380
 * D_HF_ADD_PRICING:id=4672,4682
 * D_HF_ASSI_PRI_TO_STORE:id=2590,2600
 * D_HF_ADD_QTY_CONTROL:id=2570,2580
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3500,3510
 * D_HF_ADD_PRI_BUSINESS_RULE:id=
 * AN1--Business rules:
 * Customer must be 'Alberta' RESIDENCY TYPE in order to purchase this LicENce (Submission)
 * Cannot host 'Other'  residency type UNLESS purchase authorization on file (Submission)
 * Customer can ONLY host hunter of 'Canada' and 'Other' Residency Type (Submission)
 * AN2--Business rules:
 * Customer must be AT LEAST 50 years old on the date of LicenCE purchase (Submission)
 * Customer must be "Other" RESIDENCY TYPE in order to purchase this LicENce (Submission)
 * Customer can ONLY host hunter of 'Other' Residency Type (Authorization)
 * Customer must be 'Alberta' RESIDENCY TYPE in order to be a Host (Authorization)
 * @SPEC:HFAB - Authorized Licenses (Hunter Host / No business rule checking ) displaying in product list [TC:121562] 
 * @Task#:Auto-2131          
 * 
 * @author SWang
 * @Date  Jun 10, 2014
 */
public class HunterHostWithoutBusinessRulesCheck extends HFABWebTestCase{
	private Customer cust1, cust2;
	private PrivilegeInfo privilege_AN1, privilege_AN2;
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();

	@Override
	public void execute() {
		//For the first customer, both AW1 and AW2 display
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust1, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_AN1,privilege_AN2}, new Boolean[]{true, true});
		hf.signOut();

		//For the second customer, both AW1 and AW2 display
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust2, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_AN1, privilege_AN2}, new Boolean[]{true, true});
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		loginLM.location = "AB - Call Center Manager - Auto/HOWARD'S STORE & BAKERY(Store Loc)-HOWARD'S STORE & BAKERY(Station)";

		// Customer Info 
		cust1 = new Customer();
		cust1.custNum = hfab.getCustomerNumByEmail("hfab_00022@webhftest.com", schema);
		cust1.email = cus.custNum;
		cust1.residencyStatus = RESID_STATUS_OTHER;

		cust2 = new Customer();
		cust2.custNum = hfab.getCustomerNumByEmail("hfab_00023@webhftest.com", schema);
		cust2.email = cus.custNum;
		cust2.residencyStatus = RESID_STATUS_ALBERTA;

		//privilege info 
		privilege_AN1 = new PrivilegeInfo();
		privilege_AN1.code = "AN1";
		privilege_AN1.name = "Auth_HunterHostNoRuleCh1";
		privilege_AN1.licenseYear = hf.getFiscalYear(schema);

		privilege_AN2 = new PrivilegeInfo();
		privilege_AN2.code = "AN2";
		privilege_AN2.name = "Auth_HunterHostNoRuleCh2";
		privilege_AN2.licenseYear = hf.getFiscalYear(schema);
	}
}

