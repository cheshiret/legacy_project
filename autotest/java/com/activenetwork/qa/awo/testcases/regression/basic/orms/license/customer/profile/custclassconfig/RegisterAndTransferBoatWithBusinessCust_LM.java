/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.custclassconfig;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed to verify business class customer could register/transfer vehicle successfully in license manager
 * since customer class configuration has been set up in table C_CUST_CLASS_CFG;
 * 
 * Checkpoint1: Business class customer register vehicle successfully in license manager; 
 * Checkpoint2: Business class customer transfer vehicle successfully in license manager; 
 * 
 * @Preconditions: customer class configuration has been set up in table C_CUST_CLASS_CFG
   insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID) 
   values (GET_SEQUENCE('C_CUST_CLASS_CFG'),19,11,7,2);
   commit;
   
 * @SPEC: TC:038802
 * @Task#: Auto-1065
 * @author Jane Wang
 * @Date  Jun 5, 2012
 */
public class RegisterAndTransferBoatWithBusinessCust_LM extends LicenseManagerTestCase {
	private Customer cust1 = new Customer();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		//Verify customer class config has been set up for business class customer could register/transfer vehicle in license manager
		verifyPreConditionForCustClassConfig();
		
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		vehicle.hullIdSerialNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.hullIdSerialNum);
		lm.transferVehicleToOrderCartFromDetailsPage(cust1, vehicle);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		cust.customerClass = "Business";
		cust.lName = "QACustClassConfigTestB";
		cust.fName = "QACustClassConfigTestB";
		cust.businessName = "QACustClassConfigTestBB";
		
		cust1.customerClass = "Business";
		cust1.lName = "QACustClassConfigJTestB";
		cust1.fName = "QACustClassConfigJTestB";
		cust1.businessName = "QACustClassConfigTestBC";
		
//		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "ccc"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = "REG - RegisterBoat";//just share the product register
	}
	
	/**
	 * Verify customer class config has been set up for business class customer could register/transfer vehicle in license manager
	 */
	private void verifyPreConditionForCustClassConfig(){
		logger.info("Verify pre-condition for test case.");
		if(!lm.verifyCustClassConfigPrecondition(schema, "19", "11", "5", "2", true)){
			logger.warn("Vehicle should be registered by business class customer in license manager.");
			lm.setupCustClassConfig(schema, "19", "11", "5", "2");
		}
		logger.info("The pre-condition for test case was set up correctly in DataBase.");
	}
}
