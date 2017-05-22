/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.confletter.hf.lottery;

import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case used to check lottery confirmation letter work for add to batch in license manager
 *              The case scenario is: query an existing HF lottery order from system and request confirmation letter for it
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:049337
 * @Task#:Auto-2172
 * 
 * @author ssong
 * @Date  Apr 2, 2014
 */
public class RequestionConfirmLetter_RM extends ResourceManagerTestCase{

	private String ordNum,receiptNum,method,customerInfo,product;
	private String emailSubject;
	private Properties props[] = null;
	private LoginInfo loginLm = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private OrmsReceiptDetailsPage detailPg = OrmsReceiptDetailsPage.getInstance();
	private String summeryEmailSubject = "H&F Confirmation Report Summary";
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		//add to batch from license manager
		lm.loginLicenseManager(loginLm);
		
		lm.gotoReceiptDetailsPage(receiptNum);
		lm.requestHFConfirmLetter(method);
		customerInfo = detailPg.getCustomerName();
		product = detailPg.getPrivilegePurchasingName();
		
		lm.logOutLicenseManager();
		
		//run report from resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, StringUtil.EMPTY);
		//check 2 mails sent,one is summary report and another one is confirmation letter
		props = lm.getEmailFromMailBox(host, username, password, summeryEmailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		if(!props[0].getProperty("text").contains(receiptNum)){
			throw new ErrorOnPageException("No H&F Confirmation report summary email sent for Receipt#"+receiptNum);
		}
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		verifyConfirmLetterContent();
		
		rm.logoutResourceManager();
	}
	
	private void verifyConfirmLetterContent(){
		String emailContent = props[0].getProperty("text");
		
		if(!emailContent.contains(ordNum)){
			throw new ErrorOnPageException("Confirmation Letter not Correct");
		}
		if(!emailContent.contains(customerInfo.replaceAll(",", ", ").toUpperCase())){
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
		loginLm.url = AwoUtil.getOrmsURL(env);
		loginLm.userName = TestProperty.getProperty("orms.fm.user");
		loginLm.password = TestProperty.getProperty("orms.fm.pw");
		loginLm.contract = "MS Contract";
		loginLm.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		login.contract = loginLm.contract;
		login.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		ordNum = getHFLotteryOrder();//query an existing HF lottery order
		
		receiptNum = lm.getReceiptNumByOrderNum(schema, ordNum);
		method = "Batch";

		emailSubject =receiptNum;//letter subject
		
		rd.reportName = "Hunting and Fishing Confirmation Letter";
		rd.receiptNum = receiptNum;
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}

}
