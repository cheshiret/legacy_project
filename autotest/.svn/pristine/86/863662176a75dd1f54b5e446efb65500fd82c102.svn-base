/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  May 31, 2012
 */
public class VerifyEnforceSearchBeforeAddCust extends LicenseManagerTestCase {
	
	private String alertMsg = "Please search for the customer before adding a new customer to ensure the customer file does not already exist.";
	
	public void execute() {
		setEnforceSearchBeforeAddCustomerFromDB("true");
		//sleep
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.verifyEnforceSearchBeforeAddCust(cust.customerClass, alertMsg, true);
		lm.logOutLicenseManager();
		
		setEnforceSearchBeforeAddCustomerFromDB("false");
		//sleep
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.verifyEnforceSearchBeforeAddCust(cust.customerClass, alertMsg, false);
		lm.logOutLicenseManager();
		
		setEnforceSearchBeforeAddCustomerFromDB("true");
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		cust.customerClass = "Individual";
	}

	
	private void setEnforceSearchBeforeAddCustomerFromDB(String flag){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Update EnforceSearchBeforeAddCustomer as "+flag+" from database.");
		String update = "update X_PROP set value='"+flag+"' where name='EnforceSearchBeforeAddCustomer'";
		db.executeUpdate(update);		
	}
}
