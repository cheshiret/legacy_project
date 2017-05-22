package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Quieck renewal privilege which need to input requirement education info.
 * @Preconditions:1."PrivilegeQuickRenew" feature need to assign.
 * 2.Business for privilege is added in id_hf_add_pri_business_rulel, ID:70
 * rule name:IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 * education type: Hunt Education
 * work action:Prompt for Education with Message 4002
 * @SPEC:
 * @Task#::AUTO-1066 Spira Team case number is TC:037524
 * 
 * @author jwang8
 * @Date  2012-06-04
 */
public class QuickRenewalPrivilegeWithEducation extends LicenseManagerTestCase{
	private String renewalValidToDay = "";
	private String renewalValidFromDay = "";
	private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage.getInstance();
	    
	public void execute() {
		lm.loginLicenseManager(login);

		// invalidate privilege, otherwise can't do renew!!!
        lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.gotoHomePage();
		
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay,false);
		
		//Remove the education info for case running next time.
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.manageEducationsForCustomer("Remove", null, cust.education);
		
		//lm.gotoHomePage();
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,cust,privilege);
		lm.processOrderCart(pay,false);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		boolean pass = custPrivilegPg.compareRenewalPrivilegeValidDate(privilege.purchasingName,renewalValidFromDay, renewalValidToDay);
		if(!pass){
			throw new ErrorOnPageException("renewal privilege valid data info error.");
		}
		//Clear up the education info.
        lm.gotoHomePage();
        lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.manageEducationsForCustomer("Remove", null, cust.education);
		
		lm.gotoHomePage();
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		cust.fName = "QA-Renewal3";
		cust.lName = "TEST-Renewal3";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "1984-04-30";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		cust.education.educationNum = "381020030";
		cust.education.state = "Mississippi";
		cust.education.country = "United States";
		
		privilege.code = "rpe";
		privilege.purchasingName="rpe-renewalPriEducation";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		renewalValidFromDay = privilege.validToDate;
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(renewalValidFromDay, 365);
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "QA Automation";
		privilege.cust = cust;
	}
}
