package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.custconfirmingemail;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Forget password, then verify result in ao.qaormstest E-mail
 * @Preconditions:
 * @SPEC: get a email including new password when user use the 'forget password' feature [TC:053787] 
 * @Task#: AUTO-1535
 * 
 * @author SWang
 * @Date  Mar 19, 2013
 */
public class ForgetPassword extends HFSKWebTestCase {
	private String host, username, password, emailSubject, updatedPW, useName, lastName, encryptedPW;
	private Date sendEmailStartDate = new Date();
	private Properties[] emails;

	public void execute() {
		hf.invokeURL(url);

		//Add new account if there is no one
		if (!hf.checkLoginNameExists(schema, cus.email)) {
			hf.signUpNewAccount(cus);
			hf.signOut();
			hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		}

		//Update password to initial one from DB
		hf.updateCustPasswordInDB(schema, cus.email, encryptedPW);

		//Forget password, get matched email
		hf.gotoSignInPage();
		hf.forgetYourPassword(cus.email, cus.lName);
		emails = hf.getEmailFromMailBox(host, username, password, emailSubject, sendEmailStartDate, 10, false);

		//Verify matched email
		this.verifyForgetPasswordEmail();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "forgetpassword@hfwebtest.com";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.lName = "NewCustEmail_LN";
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Saskatchewan";
		
		encryptedPW = "6a204bd89f3c8348afd5c77c717a097a";
		sendEmailStartDate = DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema));
		
		//Important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Your Account with ReserveAmerica Has been Updated"; //"Your Account with Saskatchewan HAL website has been updated";

		//Expected customer information
		useName = "YourUserName:"+cus.email;
		lastName = "YourLastName:"+cus.lName;
		updatedPW = "YourNewPassword:";
	}

	/**
	 * Get all email, the send after sendDate and within 10m, verify if there is one email is matched all of testing data.
	 */
	public void verifyForgetPasswordEmail(){
		String emailContent = "";
		boolean findMatchingEmail = false;

		for(int i=0; i<emails.length; i++){
			emailContent = emails[i].toString().replaceAll("\\s+", "");
			logger.info(i+" - Email Content:\n"+emailContent);

			if(emailContent.contains(updatedPW) && emailContent.contains(useName) && emailContent.contains(lastName)){
				logger.info("Successfully verify 'Forget password' when use name:"+useName+" and last name:"+lastName);
				findMatchingEmail = true;
				break;
			}
		}

		if(!findMatchingEmail){
			throw new ErrorOnDataException("Can't find matched email.");
		}
	}
}
