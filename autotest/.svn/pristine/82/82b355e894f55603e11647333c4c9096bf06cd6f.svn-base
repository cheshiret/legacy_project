package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

/**
 * @Description:Quieck renewal privilege which have a question.
 * @Preconditions:  1."PrivilegeQuickRenew" feature need to assign.
 * @SPEC:
 * @Task#::AUTO-1066 Spira Team case number is TC:037524
 * 
 * @author jwang8
 * @Date  2012-06-01
 */
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class QuickRenewalPrivilegeWithQuestion extends LicenseManagerTestCase{
    private String renewalValidToDay = "";
    private String renewalValidFromDay = "";
    private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
	.getInstance();


	public void execute() {
		lm.loginLicenseManager(login);
		//invalidate privilege.
        lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay,false);
		
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilege);
		lm.processOrderCart(pay,false);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		boolean pass = custPrivilegPg.compareRenewalPrivilegeValidDate(privilege.purchasingName,renewalValidFromDay, renewalValidToDay);
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
		
		cust.fName = "QA-Renewal1";
		cust.lName = "TEST-Renewal1";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 02 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);//"814618211";
		cust.residencyStatus = "Non Resident";
		
		privilege.purchasingName = "rpq-renealPriQuest";
		privilege.privilegeQuestions = new QuestionInfo[1];
		privilege.privilegeQuestions[0] = new QuestionInfo();
		privilege.privilegeQuestions[0].questDisplayText = "Quick renewal privilege with question";
		privilege.privilegeQuestions[0].questionType = "radio";
		privilege.privilegeQuestions[0].questAnswer = "Yes";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		renewalValidFromDay = privilege.validToDate;
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(renewalValidFromDay, 365);
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "QA Automation";
	}

}
