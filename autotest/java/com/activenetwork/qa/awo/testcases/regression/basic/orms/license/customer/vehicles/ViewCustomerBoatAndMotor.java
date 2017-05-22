/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.vehicles;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerVehiclesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to view customer vehicle info
 * The work flow was register a boat with motor, and title the boat, then view boat/motor info on boat detail page
 * @Preconditions:
 * @SPEC:View Customer Vehicles
 * @Task#:Auto-1002
 * 
 * @author Jane Wang
 * @Date  Jul 2, 2012
 */
public class ViewCustomerBoatAndMotor extends LicenseManagerTestCase {
	private BoatInfo boat = new BoatInfo();
	private MotorInfo boatMotor = new MotorInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		//go to customer detail page and register a vehicle
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(boat);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.registration.miNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
//		vehicle.registration.miNum = "MI0624AA";
		boat.motors.get(0).id = lm.getChildVehIDByMiNum(boat.registration.miNum, schema);
		boat.assoicatedVehicles = "Motor:"+boat.motors.get(0).id;
		//go to vehicle detail page and title the vehicle
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(boat);
		String titOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.title.titleNum = OrmsOrderSummaryPage.getInstance().getTitleNum();
		lm.finishOrder();
		//go to customer detail page and verify the vehicle detail info
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		verifyCustVehiclesInfo();
		//clean up the order
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(titOrdNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
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
		
		boat.status = OrmsConstants.ACTIVE_STATUS;
		boat.type = "Boat";
		boat.hullIdSerialNum = "NewBoat"+DataBaseFunctions.getEmailSequence();
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
		List<String> validDate = lm.registerVehicleValidDateCalc("REG", schema);
		boat.regExpiry = validDate.get(1);
		boat.title.product = "TIT - TitleBoat";
		boat.title.purchaseType = "Original";
		boat.title.boatValue = "10";
		
		boat.operationReason = "14 - Other";
		boat.operationNote = "QA Auto Regresssion Test";	
		
		boatMotor.setSerialNum("mot"+DataBaseFunctions.getEmailSequence());
//		boatMotor.serialNum = "mot18809";
		boatMotor.type = "Motor";
		boatMotor.setManufacturerName("YAMA");
		boatMotor.setModelYear("1990");
		boatMotor.setHorsePower("300");
		boatMotor.setMotorFuel("Gasoline");
		boatMotor.status = OrmsConstants.ACTIVE_STATUS;
		boat.motors.add(boatMotor);
	}
	
	private void verifyCustVehiclesInfo(){
		LicMgrCustomerVehiclesPage custVehiclePage = LicMgrCustomerVehiclesPage
				.getInstance();
		List<String> vehicles = new ArrayList<String>();
		vehicles.add(boat.type);
		custVehiclePage.selectVehicleTypes(vehicles);
		custVehiclePage.clickGoToSearch();
		ajax.waitLoading();
		custVehiclePage.waitLoading();
		custVehiclePage.verifyVehicleInfoInList(boat);
		
		vehicles.clear();
		vehicles.add("Motor");
		custVehiclePage.selectVehicleTypes(vehicles);
		custVehiclePage.clickGoToSearch();
		ajax.waitLoading();
		custVehiclePage.waitLoading();
		custVehiclePage.verifyVehicleInfoInList(boatMotor);
	}
}
