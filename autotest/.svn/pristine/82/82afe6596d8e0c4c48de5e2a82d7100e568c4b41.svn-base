/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile.custclassconfig;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed to verify business class customer should request inspection successfully in license manager
 * since customer class configuration has been set up in table C_CUST_CLASS_CFG;
 * @Preconditions: customer class configuration has been set up in table C_CUST_CLASS_CFG
   insert into C_CUST_CLASS_CFG (ID, APP_ID, PRD_GRP_CAT_ID, PRD_SUBCAT_ID, CUST_CLASS_ID) 
   values (GET_SEQUENCE('C_CUST_CLASS_CFG'),19,11,7,2);
   commit;
 * @SPEC: TC:038802
 * @Task#: Auto-1065
 * @author Jane Wang
 * @Date  Jun 6, 2012
 */
public class InspectionBoatWithBusinessCust_LM extends LicenseManagerTestCase {
	private BoatInfo boat = new BoatInfo(); 
	
	public void execute() {
		//Verify customer class config has been set up for business class customer to request inspection in license manager
		verifyPreConditionForCustClassConfig();
		lm.loginLicenseManager(login);
		lm.inspectVehicleToOrderCartFromHomePg(cust, boat);
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
		
		boat.inspection.setAddress("Auto test for request inspection with business class cust");
		boat.inspection.setZip("39269");
		boat.inspection.setCountry("United States");
		boat.inspection.setCity("Jackson");
		boat.inspection.setState("Mississippi");
		boat.inspection.setCounty("Hinds");
		boat.inspection.setDayPhone("123456");
		boat.inspection.setInspectionReason("Boat Description");
		boat.inspection.setProduct("INS - InspectionBoat");
	}
	
	/**
	 * //Verify customer class config has been set up for business class customer to request inspection in license manager
	 */
	private void verifyPreConditionForCustClassConfig(){
		logger.info("Verify pre-condition for test case.");
		if(!lm.verifyCustClassConfigPrecondition(schema, "19", "11", "7", "2", true)){
			logger.warn("Vehicle should be registered by business class customer in license manager.");
			lm.setupCustClassConfig(schema, "19", "11", "7", "2");
		}
		logger.info("The pre-condition for test case was set up correctly in DataBase.");
	}

}
