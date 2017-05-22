package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.custconfirmingemail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @Description: (DEFECT-44024) Add new account, then verify use name, full name, mailing address and phone number in ao.qaormstest E-mail
 * @Preconditions:
 * @SPEC: receive a welcome email when user sign up [TC:053780] 
 * @Task#: AUTO-1535
 * 
 * @author SWang
 * @Date  Mar 15, 2013
 */
public class SignUpNewAccount extends HFSKWebTestCase {
	private String host, username, password, emailSubject, useName, fullName, mailingAddress, phoneNum;
	private Date sendEmailStartDate = new Date();
	private Properties[] emails;

	public void execute() {
		hf.signUpNewAccount(url, cus);
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		//Add new account, verify result in email
		emails = hf.getEmailFromMailBox(host, username, password, emailSubject, sendEmailStartDate, 10, false);
		this.verifyNewAccountEmail();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "newaccount" + hf.getNextEmail();
		cus.password = "a<S#df";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		
		cus.fName = "FN_"+ new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.lName = "LN_"+ new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.hPhone = "12145778312";
		cus.mailingAddr.address = "NewCustEmail_ADD"+DateFunctions.getCurrentTime();
		cus.mailingAddr.city = "NewCustEmail_CITY";
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		
		sendEmailStartDate = DateFunctions.getCurrentDate(DataBaseFunctions.getContractTimeZone(schema));
		
		//important info to access mailbox
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Your New Account with Saskatchewan HAL website"; //"Your New Account with ReserveAmerica";

		//Expected customer information
		useName = "YouUserName:"+cus.email;
		fullName = "YourFullName:"+cus.fName+cus.lName;
		mailingAddress = "YourMailingAddress:"+cus.mailingAddr.address+","+cus.mailingAddr.city;
		phoneNum = "YourPhoneNumber:"+cus.hPhone;
	}

	/**
	 * Get all email, the send after sendDate and within 10m, verify if there is one email is matched all of testing data.
	 */
	public void verifyNewAccountEmail(){
		List<String> failedLogs = new ArrayList<String>();
		String emailContent = "";
		boolean findMatchingEmail = false;
		boolean result = true;

		for(int i=0; i<emails.length; i++){
			emailContent = emails[i].toString().replaceAll("\\s+", "");
			logger.info(i+" - Email Content:\n"+emailContent);

			if(emailContent.contains(mailingAddress)){
				logger.info("Successfully verify 'Mailing Address:'"+mailingAddress);
				findMatchingEmail = true;

				result = emailContent.contains(useName);
				logger.info(result?"Successfully verify 'User Name:'"+useName:failedLogs.add("Failed to verify 'User Name:'"+useName));

				result = emailContent.contains(fullName);
				logger.info(result?"Successfully verify 'Full Name:'"+fullName:failedLogs.add("Failed to verify 'Full Name:'"+fullName));

				result = emailContent.contains(phoneNum);
				logger.info(result?"Successfully verify 'Phone:'"+phoneNum:failedLogs.add("Failed to verify 'Phone:'"+phoneNum));

				if(failedLogs!=null && failedLogs.size()>0){
					throw new ErrorOnDataException("Not all customer confirming email content are correct. Please find details info from logs:\n"+failedLogs.toString());
				}

				break;
			}
		}

		if(!findMatchingEmail){
			throw new ErrorOnDataException("Can't find matched email.");
		}
	}
}
