package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.duplicate;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSystemErrorWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Duplicate registration when transfer a vehicle registration to order cart page.
 * Verify error message.
 * @Preconditions:
 * @SPEC: Duplicate vehicle registration [TC:004988]
 * @Task#: Auto-1790
 * @LinkSetUp:  d_hf_add_cust_profile:id=2420
 * d_hf_add_pricing:id=3582
 * 
 * @author nding1
 * @Date  Dec 4, 2013
 */
public class DuplicateRegWhenTransfer extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private Customer tranToCust = new Customer();
    private VehicleRTI vehicleRTI = new VehicleRTI();
    private BoatInfo vehicle = new BoatInfo();
    private String expectMsg;
    private OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
    
	public void execute() {
		lm.loginLicenseManager(login);
		
		// registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();
		lm.finishOrder();
		
		// transfer registration vehicle to order cart page
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, vehicle);
		
		// request another registration on order cart page 
		this.requestRegOnOrderCartPg();
		this.verifyErrorMsg();
		lm.logOutLicenseManager();
	}

	private void requestRegOnOrderCartPg(){
		logger.info("---------Request Registertion on order cart page");
		orderCartPg.clickGoAddNewVehicleForRegistration();
		ajax.waitLoading();
		browser.waitExists();
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
				orderCartPg.waitLoading();
				lm.cancelCart();
			}
		} else {
			throw new ErrorOnPageException("Should display error message "+expectMsg+" on system error widget!!");
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "87542168";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "May 28 1979";
		cust.lName = "TEST-DupRegVehicle";
		cust.fName = "QA-LicenseM";
		cust.status = "Active";
		
		vehicleRTI.setPrdCode("DTR");
		vehicleRTI.setPrdName("DuplicateVehicleTitleR");
		vehicle.hullIdSerialNum = "DupTransfer" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "11";
		vehicle.inches = "5";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.registration.status = "Active";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "87542167";
		tranToCust.lName = "TEST-DupRegVehicle1";
		tranToCust.fName = "QA-LicenseM1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Sep 01 1979";
		tranToCust.status = "Active";
		
		expectMsg = "Please process the items in the cart first before initiating another transaction.";
	}
}