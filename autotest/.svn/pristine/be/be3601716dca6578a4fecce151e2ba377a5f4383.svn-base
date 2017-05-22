package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Quick renewal privilege with normal business flow.
 * @Preconditions:"PrivilegeQuickRenew" feature need to assign.
 * @SPEC:
 * @Task#:AUTO-1066 Spira Team case number is TC:037527
 * 
 * @author jwang8
 * @Date  2012-06-04
 */
public class QuickRenewalNormalPrivilege extends LicenseManagerTestCase{
    private String renewalValidToDay = "";
    private String renewalValidFromDay = "";
    private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
	.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay,false);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		boolean pass = custPrivilegPg.comparePurcasePrivilegeValidDate(privilege.purchasingName,privilege.validFromDate, privilege.validToDate);
		if(!pass){
			throw new ErrorOnPageException("Purchase privilege valid data info error");
		}
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilege);
		lm.processOrderCart(pay,false);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		pass = custPrivilegPg.compareRenewalPrivilegeValidDate(privilege.purchasingName,renewalValidFromDay, renewalValidToDay);
		if(!pass){
			throw new ErrorOnPageException("renewal privilege valid data info error.");
		}
		
		lm.gotoHomePage();
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.fName = "QA-Renewal";
		cust.lName = "TEST-Renewal";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 01 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);//"814618203";
		cust.residencyStatus = "Non Resident";
		
		privilege.purchasingName = "rew-quickRenewal";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		renewalValidFromDay = DateFunctions.getDateAfterGivenDay(privilege.validFromDate, 365);
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(privilege.validToDate, 365);
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "QA Automation";
	}

}
