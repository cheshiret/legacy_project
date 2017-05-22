package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.notealert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Purchase a privilege with customer who has alert. When identify customer, the alert should be popped up.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * 2.Customer has an active alert, and current date is between alert start date and to date.
 * @SPEC:TC:020448
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 16, 2012
 */
public class PurchasePrivilege extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	private LicMgrCustomerNoteAndAlertPage addAlertPage = LicMgrCustomerNoteAndAlertPage.getInstance();
	private LicenseMgrHomePage homePage = LicenseMgrHomePage.getInstance();
	private String alert;
	
	@Override
	public void execute() {
		// add alert for customer.
        lm.loginLicenseManager(login);
		
		//go to customer education sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);		
		lm.gotoNotesAndAlertsSubTabFromCustomerDetailsPg();
		
		//clean all the note and alert
		addAlertPage.deactiveAllNoteAlert();
		alertInfo.id = lm.addNoteOrAlertForCustomer(alertInfo);
		
		// verify alert info
		lm.gotoHomePage();
		if(homePage.checkIdentifyCustomerAreaExistInPrevilegeSection()) {
			homePage.identifyCustomer(cust.customerClass, cust.dateOfBirth, cust.identifier.identifierType, cust.identifier.identifierNum, cust.identifier.country, StringUtil.EMPTY);
			homePage.clickPurchasePrivilege();
			ajax.waitLoading();
			alert = lm.selectCustForPurchasePri(cust);
		} else {
			lm.gotoIdentifyCustomerPage();
			alert = lm.selectCustForPurchasePri(cust);
		}
		
		this.verifyAlertInfo(alert);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.userName = TestProperty.getProperty("orms.fm.user");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19871006";
		cust.lName = "Test-WhoHasAlert";
		cust.fName = "Auto-WhoHasAlert";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "852147963";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		alertInfo.type = "Alert";
		alertInfo.startDate = DateFunctions.getDateAfterToday(-2, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(1, timeZone);
		alertInfo.text = "Where is the nearest subway station?";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	private void verifyAlertInfo(String alert){
		boolean result = true;
		String[] actualAlert = alert.split(":")[1].split("\\|");
		result &= MiscFunctions.compareResult("Effective Date", DateFunctions.formatDate(alertInfo.startDate, "MM/dd/yyyy")+" - "+DateFunctions.formatDate(alertInfo.endDate, "MM/dd/yyyy"), actualAlert[0].trim());
		result &= MiscFunctions.compareResult("Create User", alertInfo.createUser, actualAlert[1].trim());
		result &= MiscFunctions.compareResult("Text", alertInfo.text, actualAlert[2].trim());

		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
}
