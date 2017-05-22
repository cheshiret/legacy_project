package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.assigntocustprofile;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: An Agent <Add Vehicle to Parent Vehicle> triggers <Assign Vehicle to Customer Profile>
 * @Preconditions:	
 * @SPEC: Assign vehicle to Customer Profile
 * @Task#:Auto-1005 TC:004963
 *  
 * @author Jasmine Wang
 * @Date  Junt 9, 2012
 */
public class AddVehicleToParent extends LicenseManagerTestCase{
	private BoatInfo vehicle = new BoatInfo();
	private MotorInfo motor = null;
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
	private String customerInfo = "";
	private VehicleRTI vehicleRTI = new VehicleRTI();
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		String orderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		motor.id = lm.addVehicleMotor(motor);
		//Verify the motor id same with the parent vehicle's customer profile.
		this.verifyChildAssignToParentVehicleCustProf(motor.id , cust.custNum);
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
		cust.custId = lm.getCustomerIdByCustName(cust.lName, cust.fName, schema);
		customerInfo =  cust.lName+", " + cust.fName +" "+ cust.custNum;
		
		motor = new MotorInfo();
		motor.status ="Active";
		motor.setSerialNum("Motor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setModelYear(String.valueOf(DateFunctions.getCurrentYear()));
		motor.setHorsePower("2.0");
		motor.title.titleNum = "";
		motor.setMotorFuel("Gasoline");
		
		motor.title.setMotorValue(DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM dd yyyy")+" "+login.userName);
		
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
	/**
	 * verify parent vehicle have same customer profile info with child vehicle.
	 * @param motorId
	 * @param custId
	 */
	private void verifyChildAssignToParentVehicleCustProf(String motorId,String custNum){
		String expectedCustId = lm.getCustNumberByVehicleId(motorId, schema);
		if(!MiscFunctions.compareResult("Customer profile id", expectedCustId, custNum)){
		    throw new ErrorOnPageException("The child motor vehicle should have same customer profile");
		}else{
			logger.info("Parent vehicle assign customer profile to child vehicle successfully");
		}
	}

}
