/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.RegisterVehicleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: 1, Register a customer vehicle using an existing vehicle product, 2, Make payment for Register Vehicle.
 * @Preconditions: A vehicle product must be assigned to the Agents(using AssignVehicleToStore)
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  April 16, 2012
 * Used by: testCases.regression.basic.orms.finance.feeSchedule.rafee.edit.EditRAFeeScheduleForVehicleOrder
 */
public class RegisterVehicle extends SetupCase{
	private RegisterVehicleFunction functionRegisterVehicle = new RegisterVehicleFunction();
	private LoginInfo login = new LoginInfo();
	private BoatInfo vehicle = new BoatInfo();
	private Customer cust = new Customer();

	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = cust;
		args[2] = vehicle;
		functionRegisterVehicle.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_register_vehicle";
		this.queryDataSql = "";
	}

	public void readDataFromDatabase() {
		OwnerInfo coOwner = new OwnerInfo();
		MotorInfo motor = new MotorInfo(); 
		RegistrationInfo registration = new RegistrationInfo();

		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");

		vehicle.type = datasFromDB.get("vehicleType");
		vehicle.hullIdSerialNum = datasFromDB.get("hullIdSerialNum");
		vehicle.manufacturerName = datasFromDB.get("manufacturer");
		vehicle.builtYear = datasFromDB.get("builtYear");
		if(vehicle.builtYear != null && vehicle.builtYear.trim().length()<1){
			vehicle.builtYear = String.valueOf( DateFunctions.getCurrentYear());
		}
		System.out.println(vehicle.builtYear);
		vehicle.modelYear = datasFromDB.get("modelYear");
		vehicle.feet = datasFromDB.get("boatLengthFT"); 
		vehicle.inches = datasFromDB.get("boatLengthIN");
		vehicle.hullMaterial = datasFromDB.get("hullMaterial");
		vehicle.boatUse = datasFromDB.get("boatUse");
		vehicle.propulsion = datasFromDB.get("propulsion");
		vehicle.fuelType = datasFromDB.get("fuelType");
		vehicle.typeOfBoat = datasFromDB.get("boatType");
		vehicle.saltwaterUse = datasFromDB.get("saltWaterUse");
		vehicle.trailerSerial = datasFromDB.get("trailerSerial");
		vehicle.inventory = datasFromDB.get("inventory");

		String tmpInterstate = datasFromDB.get("interstateDetails");
		if(!tmpInterstate.equalsIgnoreCase(""))
		{
			String[] interInfo = tmpInterstate.split(",");//(a > b)? 1 : 0
			vehicle.interstate = (interInfo[0] != null)? interInfo[0]: "";
			vehicle.interstateVehicleSerial = (interInfo[1] != null)? interInfo[1]: "";
			vehicle.interstateState = (interInfo[2] != null)? interInfo[2]: "";

		}else{
			vehicle.interstate = "";
			vehicle.interstateVehicleSerial = "";
			vehicle.interstateState = "";
		}

		String tmpCoOwner = datasFromDB.get("coOwnerDetails");
		vehicle.coOwners.clear();
		if(!tmpCoOwner.equalsIgnoreCase(""))
		{
			vehicle.coOwners.clear();
			String[] coOwnerInfo = tmpCoOwner.split(",");//(a > b)? 1 : 0
			coOwner.firstName = (coOwnerInfo[0]!=null)? coOwnerInfo[0]:"";
			coOwner.midName = (coOwnerInfo[1]!=null)? coOwnerInfo[1]:"";
			coOwner.lastName = (coOwnerInfo[2]!=null)? coOwnerInfo[2]:"";
			coOwner.suffix = (coOwnerInfo[3]!=null)? coOwnerInfo[3]:"";
			coOwner.dateOfBirth = (coOwnerInfo[4]!=null)? coOwnerInfo[4]:"";
			coOwner.businessName = (coOwnerInfo[5]!=null)? coOwnerInfo[5]:"";
			coOwner.identifierType = (coOwnerInfo[6]!=null)? coOwnerInfo[6]:"";
			coOwner.identifierNum = (coOwnerInfo[7]!=null)? coOwnerInfo[7]:"";
			vehicle.coOwners.add(coOwner);
		}



		String tmpMotorInfo = datasFromDB.get("motorDetails");
		vehicle.motors.clear();
		if(!tmpMotorInfo.equalsIgnoreCase(""))
		{
			String[] motorInfo = tmpMotorInfo.split(",");
			motor.setSerialNum((motorInfo[0]!=null)? motorInfo[0]:"");
			motor.setManufacturerName((motorInfo[1]!=null)? motorInfo[1]:"");
			motor.setModelYear((motorInfo[2]!=null)? motorInfo[2]:"");
			motor.setHorsePower((motorInfo[3]!=null)? motorInfo[3]:"");
			motor.setMotorFuel((motorInfo[4]!=null)? motorInfo[4]:"");
			vehicle.motors.add(motor);

		}

		//for motor type
		vehicle.horsePower = datasFromDB.get("motorHorsePower");
		//		vehicle.lienDetails = datasFromDB.get("lienDetails"); //we haven`t implement split lien details... to do...

		registration.product = datasFromDB.get("registrationProducts");
		vehicle.registration =null;
		vehicle.registration = registration;

		cust.fName = datasFromDB.get("firstName");
		cust.lName = datasFromDB.get("lastName");
		cust.dateOfBirth = datasFromDB.get("dayOfBirth");

	}

}
