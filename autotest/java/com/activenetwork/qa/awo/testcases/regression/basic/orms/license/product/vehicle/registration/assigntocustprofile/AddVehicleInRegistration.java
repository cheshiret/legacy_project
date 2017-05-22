package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.assigntocustprofile;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:An Agent registers vehicle triggers <Add Vehicle> and that triggers <Assign Vehicle to Customer Profile>
 * @Preconditions:	
 * @SPEC: Assign vehicle to Customer Profile
 * @Task#:Auto-1005  TC:004963
 *  
 * @author Jasmine Wang
 * @Date  Junt 9, 2012
 */
public class AddVehicleInRegistration extends LicenseManagerTestCase{
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
	private String customerInfo = "";
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		String orderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoVehicleRegistrationsTabPg();
		registrationPg.verifyActiveOwnerCustExist(customerInfo, vehicle.registration.status);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "4152633";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 06 1985";
		cust.lName = "TEST-VehTitle";
		cust.fName = "QA-VehTitle";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.status = "Active";
		cust.ownerFromDate = DateFunctions.getToday();
		cust.ownerToDate = DateFunctions.getToday();
		customerInfo =  cust.lName+", " + cust.fName +" "+ cust.custNum;
		
		vehicleRTI.setPrdCode("REG");
		vehicleRTI.setPrdName("RegisterBoat");
		
		vehicle.hullIdSerialNum = "transfer" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		vehicle.registration.status = "Active";
	}

}
