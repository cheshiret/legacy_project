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
 * @Description:(p) Update password, then verify updated password in ao.qaormstest E-mail
 * @Preconditions:
 * @SPEC: receive an update confirmation email when user update email. [TC:053782] 
 * @Task#: AUTO-1535
 * 
 * @author SWang
 * @Date  Mar 18, 2013
 */
public class UpdatePassword extends HFSKWebTestCase {
	private String host, username, password, emailSubject, useName, updatedPw;
	private Date sendEmailStartDate = new Date();
	private Properties[] emails;

	public void execute() {
		//Add new account if there is no one
		if (!hf.checkLoginNameExists(schema, cus.email)) {
			hf.signUpNewAccount(url, cus);
			hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		}else{
			hf.signIn(url, cus.email, cus.password);
		}

		//Update password, get matched email
		hf.updatePassword(cus.password, updatedPw);
		//Update password to initial one
		hf.updatePassword(updatedPw, cus.password);
		
		//Get matched email
		emails = hf.getEmailFromMailBox(host, username, password, emailSubject, sendEmailStartDate, 10, false);

		//Verify matched email
		this.verifyUpdatePasswordEmail();
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "updatepassword@hfwebtest.com";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Saskatchewan";
		
		updatedPw = "fdsafdsa";
		sendEmailStartDate = DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema));

		//Important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Your Account with Saskatchewan HAL website has been updated"; //"Your Account with SK Ministry of Environment Has been Updated";

		useName = "YourUserName:"+cus.email;
	}

	/**
	 * Get all email, the send after sendDate and within 10m, verify if there is one email is matched all of testing data.
	 */
	public void verifyUpdateEmailEmail(){
		String emailContent = "";
		boolean findMatchingEmail = false;

		for(int i=0; i<emails.length; i++){
			emailContent = emails[i].toString().replaceAll("\\s+", "");
			logger.info(i+" - Email Content:\n"+emailContent);

			if(emailContent.contains(useName)){
				logger.info("Successfully verify 'Use Name:'"+useName);
				findMatchingEmail = true;
				break;
			}
		}

		if(!findMatchingEmail){
			throw new ErrorOnDataException("Can't find matched email.");
		}
	}
	/**
	 * Get all email, the send after sendDate and within 10m, verify if there is one email
	 */
	public void verifyUpdatePasswordEmail(){
		String emailContent = "";
		boolean findMatchingEmail = false;

		for(int i=0; i<emails.length; i++){
			emailContent = emails[i].toString().replaceAll("\\s+", "");
			logger.info(i+" - Email Content:\n"+emailContent);

			if(emailContent.contains(useName)){
				logger.info("Successfully verify updating password when 'Use Name:'"+cus.email);
				findMatchingEmail = true;
				break;
			}
		}

		if(!findMatchingEmail){
			throw new ErrorOnDataException("Can't find matched email.");
		}
	}
}
