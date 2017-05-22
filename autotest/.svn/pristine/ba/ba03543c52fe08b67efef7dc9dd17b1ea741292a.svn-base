/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.confletter.hf.lottery;

import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case used to check lottery confirmation letter work via email in license manager
 *              The case scenario is: query an existing HF lottery order from system and request confirmation letter for it
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:049337
 * @Task#:Auto-2172
 * 
 * @author ssong
 * @Date  Apr 2, 2014
 */
public class RequestConfirmLetterByEmail_LM extends LicenseManagerTestCase{

	private String ordNum,receiptNum,method,customerInfo,product;
	private String host,username,password,emailSubject;
	private Properties props[] = null;
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoReceiptDetailsPage(receiptNum);
		lm.requestHFConfirmLetter(method);
		
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		verifyConfirmLetterContent();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyConfirmLetterContent(){
		OrmsReceiptDetailsPage detailPg = OrmsReceiptDetailsPage.getInstance();
		
		customerInfo = detailPg.getCustomerName().replaceAll(",", ", ").toUpperCase();
		product = detailPg.getPrivilegePurchasingName();
		String emailContent = props[0].getProperty("text");
		
		if(!emailContent.contains(ordNum)){
			throw new ErrorOnPageException("Confirmation Letter not Correct");
		}
		if(!emailContent.contains(customerInfo)){
			throw new ErrorOnPageException("Confirmation Letter not Correct,not contain expect customer info - "+customerInfo);
		}
		if(!emailContent.contains(product)){
			throw new ErrorOnPageException("Confirmation Letter not Correct,not contain expect privilege info - "+product);
		}
	}

	private String getHFLotteryOrder(){
		AwoDatabase dbCon = AwoDatabase.getInstance();
		
		dbCon.resetSchema(schema);
		String query = "select ord_num from o_order where ord_num like '17-%'";//prefix 17 stands for HF lottery order
		String ordNum = dbCon.executeQuery(query, "ord_num",0);
		return ordNum;		
	}
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		ordNum = getHFLotteryOrder();//query an existing HF lottery order
		
		receiptNum = lm.getReceiptNumByOrderNum(schema, ordNum);
		method = "Email";
		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject =receiptNum;
	}

}
