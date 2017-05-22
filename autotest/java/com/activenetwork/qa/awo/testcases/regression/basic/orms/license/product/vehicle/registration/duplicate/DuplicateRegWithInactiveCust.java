package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.duplicate;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSystemErrorWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Register a vehicle, and then inactivate this customer.
 * Try to duplicate the registration, should display error message.
 * @Preconditions:
 * @SPEC: Duplicate vehicle registration [TC:004988]
 * @Task#: Auto-1790
 * @LinkSetUp:  d_hf_add_cust_profile:id=2430
 * 
 * @author nding1
 * @Date  Dec 4, 2013
 */
public class DuplicateRegWithInactiveCust extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
    private VehicleRTI vehicleRTI = new VehicleRTI();
    private BoatInfo vehicle = new BoatInfo();
    private String expectMsg;
	OrmsSystemErrorWidget errorWidget = OrmsSystemErrorWidget.getInstance();
    
	public void execute() {
		lm.loginLicenseManager(login);
		
		// activate the customer
		cust.status = ACTIVE_STATUS;
		lm.editCustomerStatus(cust);

		// register a vehicle
		lm.gotoHomePage();
		lm.registerVehicleToOrderCart(cust, vehicle);
		String orderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getMINum();
		lm.finishOrder();
		
		// deactivate the customer
		cust.status = INACTIVE_STATUS;
		lm.editCustomerStatus(cust);

		// try to duplicate registration
		lm.gotoHomePage();
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		vehicleDetailsPg.clickRegistration();
		ajax.waitLoading();
		errorWidget.waitLoading();
		this.verifyErrorMsg();
		
		// clean up
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		// activate the customer
		cust.status = ACTIVE_STATUS;
		lm.editCustomerStatus(cust);

		lm.logOutLicenseManager();
	}

	private void verifyErrorMsg(){
		OrmsSystemErrorWidget errorWidget = OrmsSystemErrorWidget.getInstance();
		if(errorWidget.exists()){
			String actualMsg = errorWidget.getErrorMsg();
			if(!MiscFunctions.compareResult("Error Message", expectMsg, actualMsg)){
				throw new ErrorOnPageException("Error message is not correct!!");
			} else {
				logger.info("Error message is correct!!");
				errorWidget.clickOK();
				browser.waitExists();
			}
		} else {
			throw new ErrorOnPageException("Should display error message "+expectMsg+" on system error widget!!");
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jan 1 1979";
		cust.lName = "TEST-NotAlwaysActive";
		cust.fName = "QA-NotAlwaysActive";
		cust.status = "Active";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		vehicleRTI.setPrdCode("DTR");
		vehicleRTI.setPrdName("DuplicateVehicleTitleR");
		vehicle.hullIdSerialNum = "DupInactiveCust" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1984";
		vehicle.feet = "11";
		vehicle.inches = "4";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.registration.status = "Active";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		vehicle.registration.purchaseType = ORIGINAL_PURCHASE_TYPE;

		expectMsg = "The transaction cannot be performed since the Customer Status is Inactive.";
	}
}