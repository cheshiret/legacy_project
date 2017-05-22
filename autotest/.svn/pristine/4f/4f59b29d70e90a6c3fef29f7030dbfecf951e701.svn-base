package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.notealert;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerNoteAndAlertPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Transfer a vehicle registration, the transfer to customer has an active alert.
 * When select that customer, the alert should be popped up.
 * @Preconditions:
 * 1.Need a privilege that can be purchased.
 * 2.Transfer to customer has an active alert, and current date is between alert start date and to date.
 * @SPEC:TC:020016
 * @Task#:AUTO-1273
 * 
 * @author nding1
 * @Date  Oct 18, 2012
 */
public class TransferVehicle extends LicenseManagerTestCase {
	private NoteAndAlertInfo alertInfo = new NoteAndAlertInfo();
	private BoatInfo boat = new BoatInfo();
	private Customer toCust = new Customer();
	private LicMgrCustomerNoteAndAlertPage addAlertPage = LicMgrCustomerNoteAndAlertPage.getInstance();
	
	@Override
	public void execute() {
		// add alert for customer.
        lm.loginLicenseManager(login);
		
		//go to customer education sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(toCust);		
		lm.gotoNotesAndAlertsSubTabFromCustomerDetailsPg();
		
		//clean all the note and alert
		addAlertPage.deactiveAllNoteAlert();
		alertInfo.id = lm.addNoteOrAlertForCustomer(alertInfo);

		// add alert for customer.
        lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, boat);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, true);
		boat.registration.miNum =  OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();

		// Transfer the registration
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		String alert = lm.selectCustForTransferVel(toCust);
		this.verifyAlertInfo(alert);
		
		// Clean Up
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(regOrdNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
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
		cust.dateOfBirth = "Aug 12 1986";
		cust.lName = "TEST-TransferRule222";
		cust.fName = "QA-TransferRule222";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.residencyStatus = "Non Resident";

		toCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.lName = "TEST-TransferRule224";
		toCust.fName = "QA-TransferRule224";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";
		
		alertInfo.type = "Alert";
		alertInfo.startDate = DateFunctions.getDateAfterToday(-2, timeZone);
		alertInfo.endDate = DateFunctions.getDateAfterToday(3, timeZone);
		alertInfo.text = "Are you happy?";
		alertInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		boat.type = "Boat";
		boat.hullIdSerialNum = "boa"+DataBaseFunctions.getEmailSequence();
		boat.manufacturerName = "Sony";
		boat.modelYear = "1993";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";

		boat.registration.product = "DZ1 - ViewVehicleRegistration";
		boat.registration.purchaseType = "Transfer";
		
		boat.operationReason = "14 - Other";
		boat.operationNote = "Automation test";
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
