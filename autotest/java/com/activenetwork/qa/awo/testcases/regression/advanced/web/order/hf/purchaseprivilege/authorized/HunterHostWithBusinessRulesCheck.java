package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.authorized;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABWebTestCase;
/**
 * 
 * @Description: Verify Authorization privileges (Purchase Authorization Type:General and Check Business Rules in Sales Flow:checked) 
 * display or not in licence list page with different kinds of customers.
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1680,1690
 * Address in United States, 50 years old
 * D_HF_ADD_PRIVILEGE_PRD:id=3350,3360
 * D_HF_ADD_PRICING:id=4652,4662
 * D_HF_ASSI_PRI_TO_STORE:id=2570,2580
 * D_HF_ADD_QTY_CONTROL:id=2550,2560
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3480,3490
 * D_HF_ADD_PRI_BUSINESS_RULE:id=570,580,590,600,610,620,630
 * AW1--Business rules:
 * Customer must be 'Alberta' RESIDENCY TYPE in order to purchase this LicENce (Submission)
 * Cannot host 'Other'  residency type UNLESS purchase authorization on file (Submission)
 * Customer can ONLY host hunter of 'Canada' and 'Other' Residency Type (Submission)
 * AW2--Business rules:
 * Customer must be AT LEAST 50 years old on the date of LicenCE purchase (Submission)
 * Customer must be "Other" RESIDENCY TYPE in order to purchase this LicENce (Submission)
 * Customer can ONLY host hunter of 'Other' Residency Type (Authorization)
 * Customer must be 'Alberta' RESIDENCY TYPE in order to be a Host (Authorization)
 * D_ASSIGN_FEATURE:id=6432
 * D_HF_PURCHASE_AUTHORIZATION:id=100
 * @SPEC:HFAB - Authorized Licenses (Hunter Host / Check Business rule in sales flow ) displaying in product list [TC:122470]
 * @Task#:Auto-2131          
 * 
 * @author SWang
 * @Date  Jun 10, 2014
 */
public class HunterHostWithBusinessRulesCheck extends HFABWebTestCase{
	private Customer cust1, cust2;
	private PrivilegeInfo privilege_AW1, privilege_AW2;
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();

	@Override
	public void execute() {
		//For the first customer, AW2 doesn't displays but AW1 displays
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust1, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_AW1,privilege_AW2}, new Boolean[]{true, false});
		hf.signOut();

		//For the second customer, both AW1 and AW2 display
		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cust2, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(new PrivilegeInfo[]{privilege_AW1, privilege_AW2}, new Boolean[]{true, true});
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		loginLM.location = "AB - Call Center Manager - Auto/HOWARD'S STORE & BAKERY(Store Loc)-HOWARD'S STORE & BAKERY(Station)";

		// Customer Info 
		cust1 = new Customer();
		cust1.custNum = hfab.getCustomerNumByEmail("hfab_00020@webhftest.com", schema);
		cust1.email = cus.custNum;
		cust1.residencyStatus = RESID_STATUS_OTHER;

		cust2 = new Customer();
		cust2.custNum = hfab.getCustomerNumByEmail("hfab_00021@webhftest.com", schema);
		cust2.email = cus.custNum;
		cust2.residencyStatus = RESID_STATUS_ALBERTA;

		//privilege info 
		privilege_AW1 = new PrivilegeInfo();
		privilege_AW1.code = "AW1";
		privilege_AW1.name = "Auth_HunterHostcheckRule1";
		privilege_AW1.licenseYear = hf.getFiscalYear(schema);

		privilege_AW2 = new PrivilegeInfo();
		privilege_AW2.code = "AW2";
		privilege_AW2.name = "Auth_HunterHostcheckRule2";
		privilege_AW2.licenseYear = hf.getFiscalYear(schema);
	}
}
