/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrSearchRegistrationsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the vehicle registration details when register a dealer 
 * and view the details page.
 * @Preconditions:
 * 1. Make sure the customer "TEST-Refund QA-Refund" and the vehicle product "atn - advTAN1" exist.
 * 2. Make sure the user has been assigned the features about Registration Vehicle and Search Vehicle Registrations.
 * @SPEC: <View Vehicle Registration Details>
 * @Task#: Auto-995
 * 
 * @author Lesley Wang
 * @Date  Jun 11, 2012
 */
public class ViewVehicleRegisDetails_Dealer extends LicenseManagerTestCase {
	private LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage.getInstance();
	private LicMgrSearchRegistrationsPage registrationsSearchPg = LicMgrSearchRegistrationsPage.getInstance();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private DealerInfo vehicle = new DealerInfo();
	
	@Override
	public void execute() {
		// Register a dealer vehicle and get the creation price and order number
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		vehicle.registration.creationPrice = ormsOrderCartPg.getTotalPriceAmount();;
		String ordNum = lm.processOrderCart(pay, false).split(" ")[0];

		// Search the registration and view the details
		lm.gotoSearchRegisPage();
		vehicle.registration.searchType = "Order #";
		vehicle.registration.searchValue = ordNum;
		registrationsSearchPg.searchRegistration(vehicle.registration);
		this.initializeRegInfo();
		lm.gotoRegisDetailPage(vehicle.registration.id);
		regisDetailPg.verifyAllVehicleRegisInfo(vehicle, cust);
		
		// Click the Vehicle MI # link and verify the # on the registration details page and the vehicles detail page
		this.clickAndVerifyVehicleMINum();
		
		// Back to Registration Details page from Vehicle Detail page
		lm.gotoVehicleRegistrationsTabPg();
		lm.gotoVehicleRegisDetailPgFromRegisTabPg(vehicle.registration.id);
		
		// Click the customer # link and verify the customer # and date of birth on the registration details page and the customer detail page
		this.clickAndVerifyCustomerInfo();
		
		// Clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum, "14 - Other", "Auto Test");
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.status = ACTIVE_STATUS;
		cust.dateOfBirth = "Jun 01 1986";
		
		vehicle.registration.product = "atn - advTAN1";
		vehicle.status = ACTIVE_STATUS;
		vehicle.type = "Dealer";
		vehicle.registration.status = ACTIVE_STATUS;
		vehicle.registration.numOfDuplicates = "0";
	}

	/**
	 * Get the registration ID, valid from date, valid to date on Registrations List page, 
	 * and set the values to the object regisInfo
	 */
	private void initializeRegInfo() {
		vehicle.registration.id = registrationsSearchPg.getFirstRegistrationID();
		vehicle.registration.validFromDate = registrationsSearchPg.getFirstRegistrationValidFromDate();
		vehicle.registration.validToDate = registrationsSearchPg.getFirstRegistrationValidToDate();
		logger.info("Initialize the registration info successfully! " +
				"The registration id=" + vehicle.registration.id + 
				" valid from date=" + vehicle.registration.validFromDate + 
				" valid to date=" + vehicle.registration.validToDate);
	}
	
	/**
	 * Click the Vehicle MI # link and 
	 * verify the # on the registration details page and the vehicles detail page
	 */
	private void clickAndVerifyVehicleMINum() {
		LicMgrVehicleDetailPage vehDetailPg = LicMgrVehicleDetailPage.getInstance();
		
		String miNum = regisDetailPg.getVehicleMINum();
		lm.clickVehicleMINumToVehicleDetailsPg();
		String vehNum = vehDetailPg.getVehicleNum();
		if (!miNum.equals(vehNum)) {
			throw new ErrorOnPageException("The vehicle # on Registration Details page (" + miNum + ") " +
					"is different from the one on Vehicle Details page (" + vehNum + ").");
		}
		logger.info("The vehicle MI # link works well and the MI # is displayed correct!");
	}
	
	/**
	 * Click the customer # link and verify the customer # and date of birth 
	 * on the registration details page and the customer detail page.
	 */
	private void clickAndVerifyCustomerInfo() {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
		
		String custNum = regisDetailPg.getVehicleCustNum();
		String birthDate = regisDetailPg.getVehicleCustBirthDate();
		lm.clickVehicleCustMDWFPNumToCustDetailsPg();
		String custNum1 = custDetailsPg.getCustomerNumber();
		String birthDate1 = custDetailsPg.getDateOfBirth();
		
		if (!custNum.equals(custNum1)) {
			throw new ErrorOnPageException("The customer # on Registration Details page (" + custNum + ") " +
					"is different from the one on Customer Details page (" + custNum1 + ").");
		}
		if (DateFunctions.compareDates(birthDate, birthDate1) != 0) {
			throw new ErrorOnPageException("The customer birth date on Registration Details page (" + birthDate + ") " +
					"is different from the one on Customer Details page (" + birthDate1 + ").");
		}
	}
}
