package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.custconfirmingemail;

import java.util.Date;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Update identification, verify result in ao.qaormstest E-mail
 * @Preconditions:
 * @SPEC: receive a welcome email when user sign up and adds identifier. [TC:053786] 
 * @Task#: AUTO-1535
 * 
 * @author SWang
 * @Date  Mar 20, 2013
 */
public class UpdateIdentification extends HFSKWebTestCase {
	private String host, username, password, emailSubject, useName, fullName;
	private Date sendEmailStartDate = new Date();
	private CustomerIdentifier custIden = new CustomerIdentifier();
	private CustomerIdentifier newCustIden = new CustomerIdentifier();
	private Properties[] emails;

	public void execute() {
		//Add new account if there is no one
//		if (!hf.checkLoginNameExists(schema, cus.email)) {
		if(hf.getCustomerInfo(cus.email, schema).length<1){
			hf.signUpNewAccount(url, cus);
		} else hf.signIn(url, cus.email, cus.password);

		//Delete used identifier from DB
		hf.deleteCustIden(schema, IDEN_PASSPORT_NUM_ID, cus.email);

		//Add identifier
		hf.addIdentifier(custIden);

		//Update identifier and get matched email
		hf.gotoMyAccountPgFromHeaderBar();
		hf.updateIdentifier(newCustIden.identifierTypeID, newCustIden.identifierNum, newCustIden.country);
		emails = hf.getEmailFromMailBox(host, username, password, emailSubject, sendEmailStartDate, 10, false);

		//Verify result
		this.verifyAddingIdentificationEmail();
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "updateidentification@hfwebtest.com";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.fName = "NewCustEmail_FN";
		cus.lName = "NewCustEmail_LN";
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_RCMP_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "9555575";
		cus.identifier.state = "Manitoba";
		
		custIden.identifierTypeID = OrmsConstants.IDEN_PASSPORT_NUM_ID;
		custIden.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		custIden.identifierNum = "1R9Y4x4112";
		custIden.country = "Canada";
		
		newCustIden.identifierType = custIden.identifierType;
		newCustIden.identifierTypeID = custIden.identifierTypeID;
		newCustIden.identifierNum = "1R9Y4x4113";
		newCustIden.country = "United States";

		sendEmailStartDate = DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema));

		//Important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Your Account with Saskatchewan HAL website has been updated"; //"Your Account with SK Ministry of Environment Has been Updated";

		//Expected customer information
		fullName = "YourFullName:"+cus.fName+cus.lName;
		useName = ""; //"YourUserName:"+cus.email;
	}

	/**
	 * Get all email, the send after sendDate and within 10m, verify if there is one email is matched all of testing data.
	 */
	public void verifyAddingIdentificationEmail(){
		String emailContent = "";
		boolean findMatchingEmail = false;

		for(int i=0; i<emails.length; i++){
			emailContent = emails[i].toString().replaceAll("\\s+", "");
			logger.info(i+" - Email Content:\n"+emailContent);

			if(emailContent.contains(useName) && emailContent.contains(fullName)){
				logger.info("Successfully verify 'Add Identification' when use name:"+useName+" and full name:"+fullName);
				findMatchingEmail = true;

				break;
			}
		}

		if(!findMatchingEmail){
			throw new ErrorOnDataException("Failed to verify 'Add Identification' when use name:"+useName+" and full name:"+fullName);
		}
	}
}
