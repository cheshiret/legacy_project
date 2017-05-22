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
 * @Description: (P) Add identification, verify result in ao.qaormstest E-mail
 * @Preconditions:
 * @SPEC: receive an update confirmation email when user add identifier. [TC:053784] 
 * @Task#: AUTO-1535
 * 
 * @author SWang
 * @Date  Mar 19, 2013
 */
public class AddIdentification extends HFSKWebTestCase {
	private String host, username, password, emailSubject, useName, fullName;
	private CustomerIdentifier custIden = new CustomerIdentifier();
	private Date sendEmailStartDate = new Date();
	private Properties[] emails;

	public void execute() {
		//Delete used identifier from DB
//		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);
		hf.deleteCustIdenExceptGivenIdAndCustNum(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);


		hf.signIn(url, cus.email, cus.password);

		//Add identifier and get matched email
		hf.addIdentifier(custIden);
		emails = hf.getEmailFromMailBox(host, username, password, emailSubject, sendEmailStartDate, 10, false);

		//Verify result
		this.verifyAddingIdentificationEmail();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "addidentification@hfwebtest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.retypePassword = cus.password;
		cus.fName = "NewCustEmail_FN";
		cus.lName = "NewCustEmail_LN";

		custIden.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		custIden.identifierNum = "1R9Y4x4111";
		custIden.country = "Canada";

		sendEmailStartDate = DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema));

		//Important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Your Account with Saskatchewan HAL website has been updated"; //"Your Account with SK Ministry of Environment Has been Updated";

		//Expected customer information
		if(hf.isSignInWorkFlows(url)){
			useName = "YourUserName:"+cus.email;
		}else useName = "";//"YourUserName:";
		fullName = "YourFullName:"+cus.fName+cus.lName;
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
