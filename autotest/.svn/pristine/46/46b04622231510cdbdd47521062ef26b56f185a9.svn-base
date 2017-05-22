/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.transfer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrViewPreviousMotorsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Transfer parent vehicle associated with Motor child vehicle.
 * @Preconditions:
 * @SPEC:Transfer vehicle.UCS
 * @Task#:Auto-1003
 * 
 * @author Jwang8
 * @Date  Jun 19, 2012
 */
public class TraneferVeichleWithMotor extends LicenseManagerTestCase{
	private LicMgrVehicleMotorsPage motorPg = LicMgrVehicleMotorsPage.getInstance();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private Customer tranToCust = new Customer();
	private MotorInfo motor = null;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		//transfer registration vehicle, just transfer customer
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		
		motor.id = lm.addVehicleMotor(motor);
		vehicle.motors.add(motor);//added by pzhu
		
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, vehicle);
		String transferOrderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		this.verifyTransferChildMotor(motor.id);
		this.verifyPreviousAssociatedMotorExist(motor.id);
		//Remove the added motor info.
		lm.removeVehicleMotor(vehicle.motors.get(0).id);
		//Clear up the order to release resource.
		//Need to confirm how to reverse a vehicle with a title transfer.
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferOrderNum);
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
		
		motor = new MotorInfo();
		motor.status ="Active";
		motor.setSerialNum("Motor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setModelYear(String.valueOf(DateFunctions.getCurrentYear()));
		motor.setHorsePower("2.00");
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
		//vehicle.motors.add(motor);
		
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		tranToCust.custNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		tranToCust.status = "Active";
	}
	/**
	 * verify transferred motor should exist in transferred customer's motor list.
	 * @param motorId
	 */
	public void verifyTransferChildMotor(String motorId){
		LicMgrVehicleMotorsPage motorPg = LicMgrVehicleMotorsPage.getInstance();
		boolean isExist = motorPg.checkMotorExist(motorId);
		if(!isExist){
			throw new ErrorOnPageException("The child motor vehicl should exist in motor list after tranferring");
		}
	}
	/**
	 * verify previous associated motor exist.
	 * @param motorId
	 */
	private void verifyPreviousAssociatedMotorExist(String motorId){
		LicMgrViewPreviousMotorsWidget viewPreviousMotorWgt = LicMgrViewPreviousMotorsWidget.getInstance();
		
		motorPg.clickViewPrevMotors();
		ajax.waitLoading();
		viewPreviousMotorWgt.exists();
		boolean isExist = viewPreviousMotorWgt.checkMotorExist(motorId);
		if(!isExist){
			throw new ErrorOnPageException("The child motor vehicl should exist in previous associated vehicles after tranferring");
		}
		viewPreviousMotorWgt.clickOK();
		ajax.waitLoading();
		motorPg.exists();
	}
}
