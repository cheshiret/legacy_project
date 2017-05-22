package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;
/**
 * @Description:Verify renewal more than one privilege need to select and confirm the privilege.
 * @Preconditions: "PrivilegeQuickRenew" feature need to assign.
 * @SPEC:
 * @Task#:AUTO-1066 Spira Team case number is TC:037527
 * 
 * @author jwang8
 * @Date  2012-06-05
 */

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class QuickRenewalPrivilegeSelectionConfirm extends LicenseManagerTestCase{
    private PrivilegeInfo [] privilegeList = null;
	private String renewalValidToDay = "";
	private String renewalValidFromDay = "";
	private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
	.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		//the products can only be purchase one time by a specific customer, before purchasing invalidate former privileges
		lm.invalidatePrivilegePerCustomer(cust, privilegeList);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilegeList);
		lm.processOrderCart(pay,false);
		
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilegeList);
		lm.processOrderCart(pay,false);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Privileges");
		boolean pass = custPrivilegPg.comparePrivilegeValidDate(privilegeList[0].purchasingName,renewalValidFromDay, renewalValidToDay,1);
		if(!pass){
			throw new ErrorOnPageException("Purchase privilege "+privilegeList[0].purchasingName+"valid data info error.");
		}
		pass = custPrivilegPg.comparePrivilegeValidDate(privilegeList[1].purchasingName,renewalValidFromDay, renewalValidToDay,1);
		if(!pass){
			throw new ErrorOnPageException("Purchase privilege "+privilegeList[1].purchasingName+"valid data info error.");
		} else logger.info("Verify purchased privilege "+privilegeList[1].purchasingName+"valid data info correctly.");
		
		lm.gotoHomePage();
		lm.invalidatePrivilegePerCustomer(cust, privilegeList);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.fName = "QA-Renewal4";
		cust.lName = "TEST-Renewal4";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 04 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		privilegeList = new PrivilegeInfo[2];
		
		privilegeList[0] = new PrivilegeInfo();
		privilegeList[0].purchasingName = "rpc-renewalPriConfirm";
		privilegeList[0].licenseYear = fiscalYear;	
		privilegeList[0].qty = "1";
		privilegeList[0].validFromDate = DateFunctions.getToday();
		privilegeList[0].validToDate = DateFunctions.getDateAfterToday(365);
		privilegeList[0].operateReason = "21 - Other";
		//privilegeList[0].operateReason = "01 - Operator Error";
		privilegeList[0].operateNote = "QA Automation";
		
		privilegeList[1] = new PrivilegeInfo();
		privilegeList[1].purchasingName = "rpq-renealPriQuest";
		privilegeList[1].licenseYear = fiscalYear;	
		privilegeList[1].qty = "1";
		privilegeList[1].privilegeQuestions = new QuestionInfo[1];
		privilegeList[1].privilegeQuestions[0] = new QuestionInfo();
		privilegeList[1].privilegeQuestions[0].questDisplayText = "Quick renewal privilege with question";
		privilegeList[1].privilegeQuestions[0].questionType = "radio";
		privilegeList[1].privilegeQuestions[0].questAnswer = "Yes";
		privilegeList[1].validFromDate = privilegeList[0].validFromDate;
		privilegeList[1].validToDate = privilegeList[0].validToDate;
		privilegeList[1].operateReason = "21 - Other";
		//privilegeList[1].operateReason = "01 - Operator Error";
		privilegeList[1].operateNote = "QA Automation";
		
		renewalValidFromDay = privilegeList[0].validToDate;
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(renewalValidFromDay, 365);
	}

}
