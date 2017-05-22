package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.add;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddCertificationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the business rule and ui requirements
 * @Preconditions: Need an existing customer.
 * @SPEC: <<Add Customer Certification.UCS>>
 * @Task#: AUTO-709
 * 
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class VerifyBusinessRule extends LicenseManagerTestCase {
	private LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
	private LicMgrAddCertificationWidget addCertificationWidget = LicMgrAddCertificationWidget.getInstance();
	private Customer customer = new Customer();
	private Certification certification = new Certification();
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		certification.id = lm.addCustomerCertification(certification);
		
		//1. The System shall require the User to specify the Certification Type, selected from a list of "Non-Deleted" Certification Types
		//defined for the Customer Class (of the known Customer Profile) within the contract
		runningResult &= this.verifyCertificationTypesFromDB(schema);
		
		//2. The System shall set the Customer Certification ID to s system-generated unique number
		runningResult &= this.verifyCertificationIDIsUnique(certification.id, schema);
		
		//3. The System shall set the Deleted Indicator to "Non-Deleted"
		runningResult &= this.verifyCertificationDeletedIndicator(certification.id, schema);
		
		//4. Clear up environment
		lm.removeCustomerCertification(certification.id, true);
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("Checkpoints are NOT all passed, please refer the log for detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		customer.customerClass = "Individual";
		customer.fName = "QA-Advanced1";
		customer.lName = "TEST-Advanced1";
		customer.identifier.identifierType = "Tax ID";
		customer.identifier.identifierNum = "111111";
		
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTO" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = certification.effectiveFrom;
	}
	
	/**
	 * Verify the customer certification types are all correct with them in data base
	 * @param schema
	 * @return
	 */
	private boolean verifyCertificationTypesFromDB(String schema) {
		boolean result = true;
		logger.info("Verify whether the certification type options are correct with them in DB.");
		certificationPage.clickAddCertification();
		addCertificationWidget.waitLoading();
		List<String> typesUI = addCertificationWidget.getCertificationTypeElements();
		addCertificationWidget.clickCancel();
		certificationPage.waitLoading();
		List<String> typesDB = lm.getCustomerCertificationTypes(schema);
		if(typesUI.size() != typesDB.size()) {
			logger.error("Certification Types from UI are wrong with DB.");
			return false;
		}
		Collections.sort(typesUI);
		Collections.sort(typesDB);
		
		for(int i = 0; i < typesUI.size(); i ++) {
			if(!typesUI.get(i).equalsIgnoreCase(typesDB.get(i))) {
				logger.info("Certification Type - " + typesUI.get(i) + " is wrong with DB.");
				result &= false;
			}
		}
		
		return result;
	}
	
	/**
	 * Verify whether the certification id is system-generated unique or not
	 * @param id
	 * @param schema
	 * @return
	 */
	private boolean verifyCertificationIDIsUnique(String id, String schema) {
		boolean result = true;
		logger.info("Verify whether the Certification ID is a system-generated unique number or not.");
		logger.info("Reset schema as " + schema);
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query = "select count(id) as count from C_CUST_HFP_CERTIFICATION where id=" + id;
		List<String> count = db.executeQuery(query, "COUNT");
		if(Integer.parseInt(count.get(0)) != 1) {
			result &= false;
			logger.error("The ID of Certification record(ID=" + id + ") is NOT unique.");
		} else {
			logger.info("Certification ID - " + id + " is system-generated unique.");
		}

		return result;
	}
	
	/**
	 * Verify whether the deleted indicator of a specific new added certification record is Non-Deleted
	 * @param id
	 * @param schema
	 * @return
	 */
	private boolean verifyCertificationDeletedIndicator(String id, String schema) {
		boolean result = true;
		logger.info("Verify whether the Certification Delected Indicator is 'Non-Delected'.");
		logger.info("Reset schema as " + schema);
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query = "select deleted_ind from C_CUST_HFP_CERTIFICATION where id=" + id;
		List<String> deletedIndicator = db.executeQuery(query, "DELETED_IND");
		if(Integer.parseInt(deletedIndicator.get(0)) != 0) {
			result &= false;
			logger.error("The Deleted Indicator of Certification record(ID=" + id + ") is NOT 'Non-Deleted'.");
		} else {
			logger.info("Deleted Indicator of Certification - " + id + " is really 'Non-Deleted'.");
		}

		return result;
	}
}
