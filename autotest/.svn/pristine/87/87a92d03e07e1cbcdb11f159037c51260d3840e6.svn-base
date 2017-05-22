package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify if the DOB is not specified, system will behave in 2 different ways 
 * 						between INDIVIDUAL and BUSINESS when editing customer profile
 * 						INDIVIDUAL - System pop up error message
 * 						BUSINESS - edit succeed
 * @Preconditions: Case will create customers by itself if customers didn't exist
 * @SPEC: PCR 2926, <<Edit Customer Profile.doc>>, TC003916 step27-30.
 * 				And for step 28&29, it needs to update Optional DOB indicator to FALSE/not configured, to avoid impact other cases, ignore these scenarios.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 14, 2012
 */
public class EditCustWithoutDOB extends LicenseManagerTestCase {
	private Customer businessCust = new Customer();
	private LicMgrCustomerDetailsPage custDetailPage = LicMgrCustomerDetailsPage.getInstance();
	private String expected = "";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//1. add 2 new customers
		cust.custNum = lm.createNewCustomer(cust);
		businessCust.custNum = lm.createNewCustomer(businessCust);
		
		//2. Step27. for customer class as "Individual", regardless of the configuration, if DOB not entered, system display error message
		lm.gotoCustomerDetailFromTopMenu(cust);
		cust.dateOfBirth = "";
		lm.addOrEditCustomerInfo(cust, custDetailPage);
		String msgOnPage = lm.finishAddOrEditCustomer();
		this.verifyErrorMsg("Date Of Birth", expected, msgOnPage);
		
		//3. Step 30. for customer class such as "Business", if the Optional Date Of Birth Indicator is not configured, user doesn't input DOB when editing customer profile
		//customer profile shall be edited successfully
		lm.gotoCustomerDetailFromTopMenu(businessCust);
		businessCust.dateOfBirth = "";
		lm.addOrEditCustomerInfo(businessCust, custDetailPage);
		msgOnPage = lm.finishAddOrEditCustomer();
		this.verifyErrorMsg("Business Customer ID", businessCust.custId, msgOnPage);
		
		//clean up
		lm.gotoCustomerDetails(businessCust.lName, businessCust.fName, businessCust.customerClass);
		businessCust.dateOfBirth = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		lm.addOrEditCustomerInfo(businessCust, custDetailPage);
		lm.finishAddOrEditCustomer();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//INDIVIDUAL customer info
		int sequence = DataBaseFunctions.getEmailSequence();
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "Passport" + String.valueOf(sequence);
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		cust.fName = "Edit" + sequence;
		cust.mName = "Individual" + sequence;
		cust.lName = "Customer" + sequence;
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.includeAreaCode = true;//if search a customer with phone number which has area code, select 'Include Area Code'
		cust.email = "editing.individual@reserveamerica.com";
		cust.custGender = "Male";
		cust.ethnicity = "Asian";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		
		//BUSINESS customer info
		sequence++;
		businessCust.customerClass = OrmsConstants.BUSINESS_CUSTOMER_CLASS;
		businessCust.identifier.identifierType = "MS Drivers License";
		businessCust.identifier.identifierNum = "Passport" + String.valueOf(sequence);
		businessCust.identifier.state = "Mississippi";
		businessCust.residencyStatus = "Non Resident";
		businessCust.dateOfBirth = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		businessCust.fName = "Edit" + sequence;
		businessCust.mName = "Business" + sequence;
		businessCust.lName = "Customer" + sequence;
		businessCust.businessName = "EditBusinessCustomer" + sequence;
		businessCust.hPhone = "02985250452";
		businessCust.bPhone = "02968685958";
		businessCust.includeAreaCode = true;
		businessCust.email = "editing.business@reserveamerica.com";
		businessCust.custGender = "Male";
		businessCust.ethnicity = "Asian";
		businessCust.physicalAddr.address = "Keji 2nd Road";
		businessCust.physicalAddr.city = "York";
		businessCust.physicalAddr.state = "Alabama";
		businessCust.physicalAddr.county = "Sumter";
		businessCust.physicalAddr.zip = "36925";
		businessCust.physicalAddr.country = "United States";
		
		expected = "Date Of Birth is required. Please specify the Date Of Birth.";
	}
	
	private void verifyErrorMsg(String dscr, String expected, String actual) {
		boolean result = MiscFunctions.compareResult(dscr, expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The actual error message is wrong with expected.");
		}
	}
}
