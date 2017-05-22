package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.assigntocustprofile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:An Agent transfers vehicle triggers <Add Vehicle to Parent Vehicle> triggers <Assign Vehicle to Customer Profile>
 * @Preconditions:	
 * @SPEC: Assign vehicle to Customer Profile
 * @Task#:Auto-1005 TC:004963
 *  
 * @author Jasmine Wang
 * @Date  Junt 10, 2012
 */
public class AddVehicleToParentInTransfer extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private MotorInfo motor = null;
	private BoatInfo vehicle = new BoatInfo();
	private Customer tranToCust = new Customer();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private String transferCustInfo = "";
	private LicMgrVehicleMotorsPage vehicleMotoPg = LicMgrVehicleMotorsPage.getInstance();
	private LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		String registerOrderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();
		lm.finishOrder();
		
		//transfer registration vehicle, just transfer customer
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		vehicle.motors.add(motor);
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, vehicle);
		String transferOrderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		//Clear up the data.
		motor.id = vehicleMotoPg.getMotorBoatId(motor);
		this.verifyChildAssignToParentVehicleCustProf(motor.id, tranToCust.custNum);
		//Verify the current assigned customer profile.
		lm.gotoVehicleRegistrationsTabPg();
		registrationPg.verifyActiveOwnerCustExist(transferCustInfo, vehicle.registration.status);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferOrderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		lm.gotoVehicleOrderDetailPage(registerOrderNum);
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
		vehicle.newMotors.add(motor);
		
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		tranToCust.custNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		tranToCust.status = "Active";
		tranToCust.custId = lm.getCustomerIdByCustName(tranToCust.lName, tranToCust.fName, schema);
		
		transferCustInfo = tranToCust.lName+", " + tranToCust.fName +" "+ tranToCust.custNum;
	}
	
	/* verify parent vehicle have same customer profile info with child vehicle.
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
