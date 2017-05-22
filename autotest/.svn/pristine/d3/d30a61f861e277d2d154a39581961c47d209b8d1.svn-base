/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: If violate business rule, customer can't purchase that privilege.
 * If not violate business rule, customer can purchase that privilege.
 * @Preconditions:
 * 1. Privilege(jan) has two business rules:
 * (1). Customer must be AT LEAST parameter age on the date of privilege purchase
 * parameter age is 18.
 * (2). Customer must be AT MOST parameter age on the date of privilege purchase
 * parameter age is 60.
 * @SPEC:
 * @Task#:AUTO-358
 * @LinkSetUp: d_hf_add_pri_business_rule:id=470,480
 *  d_hf_add_privilege_prd:id=2550
 *  d_hf_assi_pri_to_store:id=1800
 *  d_hf_add_pricing:id=3682
 *  d_hf_add_qty_control:id=1780
 *  d_hf_add_prvi_license_year:id=2690
 * 
 * @author nding1
 * @Date  Jan 24, 2014
 */
public class PrivilegeAgeRules_PurchaseDate extends LicenseManagerTestCase {
	private int paramAge;
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// verify Customer must be AT LEAST 18 years old age on the date of the privilege purchase -- Start
		// 1. customer age < parameter age(18), can't purchase this privilege.
		paramAge = 18;
		this.updateCustomerDOB(true);
		this.verifyPrivilegeAgeRule(false);

		// 2. customer age >= parameter age(18), can purchase this privilege.
		this.updateCustomerDOB(false);
		this.verifyPrivilegeAgeRule(true);
		// verify Customer must be AT LEAST 18 years old age on the date of the privilege purchase -- End
		
		// verify Customer must be AT MOST 60 years old age on the date of privilege purchase -- Start
		// 1. customer age <= parameter age(60), can purchase this privilege.
		paramAge = 60;
		this.updateCustomerDOB(true);
		this.verifyPrivilegeAgeRule(true);

		// 2. customer age > parameter age(60), can't purchase this privilege.
		this.updateCustomerDOB(false);
		this.verifyPrivilegeAgeRule(false);
		// verify Customer must be AT MOST 60 years old age on the date of privilege purchase -- End
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code="RCD";
		privilege.name="PriRulePurchaseDate";
		privilege.purchasingName=privilege.code+"-"+privilege.name;

		cust.residencyStatus="Non Resident";
		cust.customerClass="Individual";
		cust.identifier.identifierType="US Drivers License";
		cust.identifier.identifierNum="AUTO12345";
	}
	
	private void updateCustomerDOB(boolean isAdd){
		logger.info("Update DOB for customer.");
		
		int days = 0;
		if(isAdd){
			days = 1;
		}
		if(paramAge == 60){
			paramAge = paramAge+1;
		}
		
		String dob = DateFunctions.calculateDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), paramAge*(-1), days); 
		
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromTopMenu(cust);
		custDetailsPg.changeDateOfBirth(dob);
	}
	
	/**
	 * verify privilege business age rule 
	 * @param isExist
	 * @Return void
	 * @Throws
	 */
	private void verifyPrivilegeAgeRule(boolean isExist){
		LicMgrOrigPrivSaleAddItemPage page=LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		boolean flag=false;
		
		logger.info("Verify Privilege bussiness rule.....");
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		flag = page.isThisPrivilegeExist(privilege.purchasingName);
		if(flag!=isExist){
			throw new ErrorOnPageException("The privilege name="+privilege.purchasingName+" should "+(isExist?" ":"not ")+"exist");
		}
		logger.info("verify sucessfully.....");
	}
}