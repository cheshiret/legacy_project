/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehiclePreviousOwnersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to view vehicle previous owner info. 
 * The work flow was register a vehicle with customer A, and transfer to customer B, view customer A info on vehicle detail page previous owner sub table
 * @Preconditions:
 * @SPEC:View Vehicle Previous Owners
 * @Task#:Auto-1002
 * 
 * @author Jane Wang
 * @Date  Jul 4, 2012
 */
public class ViewVehiclePreviousOwners extends LicenseManagerTestCase {
	private Customer tranToCust = new Customer();
	private BoatInfo boat;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(boat);
		lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.registration.miNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, boat);
		String transferOrderNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		lm.finishOrder();
//		vehicle.registration.miNum = "MI0638AA";
//		String transferOrderNum = "9-2555";
		
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.gotoVehiclePreviousOwnersPg();
		this.verifyVehiclePreviousOwner(cust);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferOrderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();		
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
			
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "569352641";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1987";
		cust.lName = "TEST-ViewVhclOwner1";
		cust.fName = "QA-ViewVhclOwner1";
		//cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.status = OrmsConstants.ACTIVE_STATUS;
		cust.ownerFromDate = DateFunctions.getToday();
		cust.ownerToDate = DateFunctions.getToday();
		
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.custNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		tranToCust.status = OrmsConstants.ACTIVE_STATUS;
		
		boat = new BoatInfo();
		boat.type = "Boat";
		boat.status = OrmsConstants.ACTIVE_STATUS;
		boat.hullIdSerialNum = "BoatWithCoOwner"+DataBaseFunctions.getEmailSequence();
//		vehicle.hullIdSerialNum = "BoatWithCoOwner19853";
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

		boat.operationReason = "14 - Other";
		boat.operationNote = "QA Auto Regresssion Test";	
	}
	
	/**
	 * Verify vehicle previous owner info
	 * @param preCust
	 */
	private void verifyVehiclePreviousOwner(Customer preCust){
		LicMgrVehiclePreviousOwnersPage previousOwnerPg = LicMgrVehiclePreviousOwnersPage.getInstance();
		
		logger.info("Verify co-owner info in co-owner sub page list.");
		Boolean result = previousOwnerPg.comparePreOwnersInfo(preCust);
		if(!result){
		   throw new ErrorOnPageException("The privious owner details info was Not correct. Please check log for details.");	
		}
		logger.info("co-owner info was correct in co-owner sub page list.");
	}
}
