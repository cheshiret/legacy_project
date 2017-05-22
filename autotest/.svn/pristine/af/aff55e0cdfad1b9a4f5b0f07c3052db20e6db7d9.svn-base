package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.confletter;

import java.math.BigDecimal;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a consumable.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 *               Cust name, order number, receipt number, consumable fiscal year and consumable product name.
 * 				 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='true' where name like 'UppercaseOutput'>;
 * 					3. A vehicle product: V03 - ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee2/TEST-RAFee2 
 * 				
 * @SPEC: TC 024171
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  Oct 08, 2012
 * 
 */
public class DonationConfLetterByEmail extends LicenseManagerTestCase{
	private ConsumableInfo consumable = new ConsumableInfo();
	Properties props[] = null;
	private String parkName = "";
	private String host, username, password, emailSubject,totalFee;
	private String convenienceOrderNum;
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		
		pay.belongOrderNum =  lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			convenienceOrderNum = pay.belongOrderNum.split(" ")[1];
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		//convenienceOrderNum = "22-17928";
		//pay.belongOrderNum ="3-1124675";
		logger.info("Consumable order number is "+pay.belongOrderNum);
		//logger.info("convenience fee orders number is "+convenienceOrderNum);
		lm.gotoConsumableOrderDetailsPage(pay.belongOrderNum);
		String receiptNum = lm.gotoReceiptPageFromConsumableOrder();
		
		logger.info("receiptNum is "+receiptNum);
		
		lm.gotoConfimLetterPg();
		lm.sentReceiptConfirmationLetterByEmail("Receipt");
		emailSubject = "Receipt#: "+receiptNum+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		this.verifyConsumableConfLetterInfo(cust, consumable, pay.belongOrderNum, receiptNum, totalFee,convenienceOrderNum);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		consumable.code = "888";
		consumable.name= "888 - DonConfLettter";
		consumable.quantity = "1";
		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Confirmation Letter Email";
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
		totalFee ="18.00" /*lm.getCustPriceByPrdCodeAndAppToAllState(schema, consumable.code, ORIGINAL_PURCHASE_TYPE_ID)*/;
		
		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";
	}
	
	/**
	 * verify consumable confirm letter info.
	 * @param cust
	 * @param consumable
	 * @param orderNum
	 * @param receiptNum
	 * @param totalFee
	 */
	public void verifyConsumableConfLetterInfo(Customer cust,ConsumableInfo consumable,String orderNum,String receiptNum,String totalFee,String convenOrderNum){
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
		if(!emailContent.contains("Order # "+orderNum)){
			isPass = false;
			logger.error("Order number "+orderNum+" error");
		}
		if(!emailContent.contains("Receipt # "+receiptNum)){
			isPass = false;
			logger.error("Receipt number "+receiptNum+" error");
		}
		String year = lm.getFiscalYear(schema);
		if(!emailContent.contains("("+year+") "+consumable.name)){
			isPass = false;
			logger.error("Privilege info Error");
		}
//		if(!StringUtil.isEmpty(convenOrderNum) && !emailContent.contains("Order # "+convenOrderNum)){
//			isPass = false;
//			logger.error("convenience fee order number "+convenOrderNum+" info Error");
//		}
		
		if(!isPass){
			throw new ErrorOnPageException("Confrim letter info error");
		}else{
			logger.info("Confirm letter content correct");
		}
	}
}
