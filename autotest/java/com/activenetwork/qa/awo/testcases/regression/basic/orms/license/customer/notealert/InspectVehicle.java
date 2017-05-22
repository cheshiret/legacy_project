package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.notealert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Inspect a vehicle, the customer has an active alert.
 * When select that customer, the alert should be popped up.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * 2.Customer has an active alert, and current date is between alert start date and to date.
 * 3.Feature '' should be assigned.
 * @SPEC:TC:020441
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 18, 2012
 */
public class InspectVehicle extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	private String vehicleType;
	private LicMgrCustomerNoteAndAlertPage addAlertPage = LicMgrCustomerNoteAndAlertPage.getInstance();
	
	@Override
	public void execute() {
		// add alert for customer.
        lm.loginLicenseManager(login);

		// go to customer note&alert sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);		
		lm.gotoNotesAndAlertsSubTabFromCustomerDetailsPg();
		
		// clean all the note and alert
		addAlertPage.deactiveAllNoteAlert();
		alertInfo.id = lm.addNoteOrAlertForCustomer(alertInfo);

        // verify alert info.
		lm.gotoHomePage();
		String alert = lm.selectCustForInspectVel(cust, vehicleType);
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
		cust.dateOfBirth = "19860101";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "999888777";// TEST-Transfer, QA-Transfer
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		alertInfo.type = "Alert";
		alertInfo.startDate = DateFunctions.getDateAfterToday(-2, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(3, timeZone);
		alertInfo.text = "Are you happy?";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		vehicleType = "Boat";
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
