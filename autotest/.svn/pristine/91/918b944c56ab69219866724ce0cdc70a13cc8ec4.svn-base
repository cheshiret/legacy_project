package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.confletter;

import java.math.BigDecimal;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a privilege.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3.Verify the confirm letter info.
 * 
 *               Check customer first name and last name, order number, receipt number, privilege name ,total fee and valid date.

 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='false' where name like 'UppercaseOutput'>; 
 * 					3. A privilege product: 703-ProcessFeeAdjustments
 * 					4. Customer: TEST-Basic1/QA-Basic1 
 * 				
 * @debugHistory:1,Assign feature 'RequestConfirmationLetter' to 'HF HQ Role'.
 * 				 2,If feature above assigned, but button 'Request H&F Conf Letter' still did not appear, we should run SQL following:
 * 					UPDATE C_CONF_LETTER_CNFG SET ALLOW_FAX_IND = 1, ALLOW_EMAIL_IND = 1, ALLOW_PRINT_IND = 1 WHERE DELETED_IND =0 AND LOC_ID =1 AND LETTER_TYPE_ID=3
 * @SPEC:  TC02507
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  July 23, 2012
 * 
 */
public class ConfLetterByEmailContent extends LicenseManagerTestCase{
	Properties props[] = null;
	private String parkName = "";
	private String host, username, password, emailSubject,totalFee;
	public void execute() {
		lm.loginLicenseManager(login);
		// make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		pay.belongOrderNum =lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		logger.info("OrderNum is "+pay.belongOrderNum);

		lm.gotoPrivilegeOrderDetailPage(pay.belongOrderNum);
		String receiptNum = lm.gotoReceiptPageFromPrivilegeOrder();
		logger.info("receiptNum is "+receiptNum);
		
		lm.gotoConfimLetterPg();
		lm.sentReceiptConfirmationLetterByEmail("Receipt");
		emailSubject = "Receipt#: "+receiptNum+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		this.verifyPriOrderConfLetterInfo(cust, privilege, pay.belongOrderNum, receiptNum, totalFee);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		privilege.code = "703";
		privilege.name = "ProcessFeeAdjustments";
		privilege.purchasingName = "703-ProcessFeeAdjustments";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		DateFunctions.getToday("EEEEE, MMMMM d, yyyy");
		
		cust.lName = "TEST-Basic11";
		cust.fName = "QA-Basic11";
		cust.dateOfBirth = "Jun 08 1988";
		cust.email = "qtest-basic1";
		cust.residencyStatus = "Non Resident";
		
		cust.physicalAddr.address = "Xian";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		
		pay.payType = "Cash";
		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Confirmation Letter Email";
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
		totalFee =lm.getCustPriceByPrdCodeAndAppToAllState(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID);
	}
	/**
	 * verify privilege order confirm letter info.
	 * @param cust
	 * @param priInfo
	 * @param OrderNumber
	 * @param totalFee
	 */
	public void verifyPriOrderConfLetterInfo(Customer cust,PrivilegeInfo priInfo,String OrderNumber,String receiptNum,String totalFee){
		boolean isPass = true;
		logger.info("Verify if the the customer info, privileg info , order number and receipt number in confirmation letter file");
		String emailContent = props[0].getProperty("text");
		logger.info(emailContent);
		String temp = "NAME: "+cust.fName.toUpperCase()+" "+cust.lName.toUpperCase();
		if(!emailContent.contains(temp)){
			isPass = false;
			logger.error("customer name info "+cust.fName+"+ "+cust.lName+" error");
		}
		if(!emailContent.contains(new BigDecimal(totalFee).setScale(2).toString())){
			isPass = false;
			logger.error("Total price"+new BigDecimal(totalFee).setScale(2).toString()+" error");
		}
		if(!emailContent.contains("Order # "+OrderNumber)){
			isPass = false;
			logger.error("Order number "+OrderNumber+" error");
		}
		if(!emailContent.contains("Receipt # "+receiptNum)){
			isPass = false;
			logger.error("Receipt number "+receiptNum+" error");
		}
		// (2013) 703 - ProcessFeeAdjustments; Valid: Tue Aug 27 2013
		String year = lm.getFiscalYear(schema);
		String date = DateFunctions.getToday("EEE MMM d yyyy");
		if(!emailContent.contains("("+year+") "+priInfo.code+" - "+priInfo.name+"; Valid: "+date+"")){
			isPass = false;
			logger.error("Privilege info Error");
		}
		
		if(!isPass){
			throw new ErrorOnPageException("Confrim letter info error");
		}else{
			logger.info("Confirm letter content correct");
		}
		
	}

}
