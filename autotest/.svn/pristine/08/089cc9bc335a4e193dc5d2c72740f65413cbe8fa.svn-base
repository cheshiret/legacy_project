/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed to verify register vehicle(type:boat) detail info, including vehicle common info, customer info, coowner info and registeration info
 * @Preconditions:
 * @SPEC: Register Vehicle
 * @Task#:Auto-992
 *  
 * @author Jane Wang
 * @Date  Jun 8, 2012
 */
public class RegisterBoat extends LicenseManagerTestCase {
	private String prdCD;
	BoatInfo boat = new BoatInfo();
	public void execute() {
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		boat.registration.miNum =  OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
		
//		boat.registration.miNum = "MI0845AA";
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		verifyVehicleAndRegistrationDetailInfo();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000020";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic20";
		cust.fName = "QA-Basic20";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		boat.type = "Boat";
		boat.hullIdSerialNum = "boa"+DataBaseFunctions.getEmailSequence();
		boat.manufacturerName = "YAMA";
		boat.modelYear = "1997";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		prdCD = "REG";
		boat.registration.product = "REG - RegisterBoat";//just share the product register
		boat.registration.purchaseType = "Original";
		List<String> validDate = lm.registerVehicleValidDateCalc(prdCD, schema);
		boat.registration.validFromDate = validDate.get(0);
		boat.registration.validToDate = validDate.get(1);
		boat.registration.status = OrmsConstants.ACTIVE_STATUS;
		boat.registration.customer = cust.lName+", "+cust.fName+cust.custNum;
		boat.registration.numOfDuplicates = "0";
		
		boat.creationDate = DateFunctions.getToday();
		boat.creationUser = login.userName;
		
		OwnerInfo coOwner = new OwnerInfo();
		coOwner.firstName = "QA-CoOwnerTest";
		coOwner.midName = "m";
		coOwner.lastName = "TEST-CoOwnerTest";
		coOwner.suffix = "I";
		coOwner.businessName = "QABusinessTest";
		coOwner.dateOfBirth = "1988-1-1";
		coOwner.identifierType = "MS Drivers License";
		coOwner.identifierNum = "999666";
		coOwner.identifierState = "Mississippi";
		boat.coOwners.add(coOwner);

		MotorInfo boatMotor = new MotorInfo();
		boatMotor.setSerialNum("mot"+DataBaseFunctions.getEmailSequence());
		boatMotor.setManufacturerName("YAMA");
		boatMotor.setModelYear("1990");
		boatMotor.setHorsePower("300");
		boatMotor.setMotorFuel("Gasoline");
		boatMotor.status = OrmsConstants.ACTIVE_STATUS;
		boat.motors.add(boatMotor);
	}
	
	private void verifyVehicleAndRegistrationDetailInfo(){
		LicMgrVehicleDetailPage vehicleDetailPg = LicMgrVehicleDetailPage.getInstance();
		LicMgrVehicleMotorsPage motorDetailPg = LicMgrVehicleMotorsPage.getInstance();
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage.getInstance();
		LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
		
		boolean pass = true;
		//Verify vehicle detail common info
		pass &= vehicleDetailPg.verifyVehicleCommonInfo(boat);
		//Verify vehicle customer detail info
		pass &= vehicleDetailPg.verifyVehicleCustomerInfo(cust);
		//Verify vehicle motor detail info
		pass &= motorDetailPg.verifyVehicleMotorInfo(boat.motors.get(0));
		//Verify vehicle co-owner detail info
		lm.gotoVehicleDetailSubTable("CoOwner");
		pass &= coOwnerPg.verifyVehicleCoOwnerInfoInList(boat.coOwners.get(0));
		//Verify vehicle registration detail info
		lm.gotoVehicleDetailSubTable("Registrations");
		pass &= registrationPg.verifyVehicleRegistrationInfo(boat.registration);
		
		if(!pass){
			throw new ErrorOnPageException("Verify vehicle registration details info on page un-correctly. Please check error log for details.");
		}
	}
	
}
