package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.renewal;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When renew a vehicle registration, not select any vehicle on register product widget, verify error message.
 * @Preconditions:
 * At lease two vehicle can be renew.
 * @SPEC: Quick Vehicle Registration Renewal [TC:024168]
 * @Task#: Auto-1790
 * @LinkSetUp: d_hf_add_pricing:id=3572
 * @author nding1
 * @Date  Dec 5, 2013
 */
public class NotSelectVehicle extends LicenseManagerTestCase {
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget.getInstance();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	private String expectMsg;
	
	@Override
	public void execute() {
		// Register a vehicle product
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		String ordNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getMINum();
		lm.finishOrder();
		
		// Search the vehicle and go to Vehicle Details page
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoVehRegisProdWidgetFromVehDetaildPg();
		
		// Not select any vehicle and click OK directly.
		if(regWidget.exists()){
			regWidget.clickOK();
			ajax.waitLoading();
			regWidget.waitLoading();
		
			String actualMsg = regWidget.getErrorMsg();
			if(!actualMsg.matches(expectMsg)){
				throw new ErrorOnPageException("Should display error message: 'Please select a product'");
			} else {
				regWidget.clickCancel();
				ajax.waitLoading();
				browser.waitExists();
			}
		} else {
			throw new ErrorOnPageException("Should display error message: 'Please select a product'");
		}
		
		// clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum, vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.lName = "TEST-RenewVehicle";
		cust.fName = "QA-LicenseM3";
		
		vehicleRTI.setPrdCode("DTR");
		vehicleRTI.setPrdName("DuplicateVehicleTitleR");
		vehicle.hullIdSerialNum = "NotSelectVelRenew"+DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = Integer.toString(DateFunctions.getCurrentYear());
		vehicle.feet = "7";  
		vehicle.inches = "5";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.registration.purchaseType = "Renewal";
		vehicle.registration.searchType = "Order #";
		
		expectMsg =  "Please select a product.*";
	}
}