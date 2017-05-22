/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to view associated vehicle info. 
 * The work flow was register a boat with motor, and then view motor info on boat detail page motor sub table
 * @Preconditions:
 * @SPEC:View Associated Vehicles
 * @Task#:Auto-1002
 * 
 * @author Jane Wang
 * @Date  Jul 3, 2012
 */
public class ViewAssociatedVehicles extends LicenseManagerTestCase {
	private String format;
	private MotorInfo boatMotor;
	private BoatInfo boat;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(boat);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.registration.miNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
//		vehicle.registration.miNum = "MI0624AA";
		boatMotor.id = lm.getChildVehIDByMiNum(boat.registration.miNum, schema);
		boat.assoicatedVehicles = "Motor:"+boatMotor.id;
		
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		LicMgrVehicleMotorsPage vehicleMotorPg = LicMgrVehicleMotorsPage.getInstance();
		vehicleMotorPg.verifyVehicleMotorInfo(boatMotor);
		
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regOrdNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();		
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
			
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic00007";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic8";
		cust.fName = "QA-Basic8";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		boat = new BoatInfo();
		boat.type = "Boat";
		boat.status = OrmsConstants.ACTIVE_STATUS;
		boat.hullIdSerialNum = "BoatWithMotor"+DataBaseFunctions.getEmailSequence();
//		vehicle.hullIdSerialNum = "NewBoat18808";
		boat.manufacturerName = "YAMA";
		boat.modelYear = "1997";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		boat.registration.product = "REG - RegisterBoat";//just share the product register
		boat.registration.purchaseType = "Original";
		
		boat.operationReason = "14 - Other";
		boat.operationNote = "QA Auto Regresssion Test";	

		format = "EEE MMM d yyyy";
		boatMotor = new MotorInfo();
		boatMotor.setSerialNum("motor"+DataBaseFunctions.getEmailSequence());
//		boatMotor.serialNum = "mot18809";
		boatMotor.setManufacturerName("YAMA");
		boatMotor.setModelYear("1990");
		boatMotor.setHorsePower("300");
		boatMotor.setMotorFuel("Gasoline");
		boatMotor.status = OrmsConstants.ACTIVE_STATUS;
		boatMotor.setAssignmentDetails(DateFunctions.getToday(format)+" "+login.userName);
		boat.motors.add(boatMotor);
	}
}
