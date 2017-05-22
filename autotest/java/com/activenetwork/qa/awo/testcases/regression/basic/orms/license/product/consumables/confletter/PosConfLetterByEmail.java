package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.confletter;

import java.math.BigDecimal;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a consumable.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 *               Cust name, order number, receipt number, consumable fiscal year and consumable product name.
 * 				 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role - Auto"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='true' where name like 'UppercaseOutput'>;
 * 					3. A comsumable product: WL2 - POSForVoid
 * 					4. Customer: QA-RAFee2/TEST-RAFee2 
 * 				
 * @SPEC: TC 028745
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  Oct 08, 2012
 * 
 */
public class PosConfLetterByEmail extends LicenseManagerTestCase{
	private ConsumableInfo consumable = new ConsumableInfo();
	Properties props[] = null;
	private String parkName = "";
	private String host, username, password, emailSubject,totalFee;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		
		pay.belongOrderNum =  lm.processOrderCart(pay);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		logger.info(" Consumable order number is "+pay.belongOrderNum);
		//pay.belongOrderNum = "3-1218365";
		lm.gotoConsumableOrderDetailsPage(pay.belongOrderNum);
		String receiptNum = lm.gotoReceiptPageFromConsumableOrder();
		
		logger.info("receiptNum is "+receiptNum);
		
		lm.gotoConfimLetterPg();
		lm.sentReceiptConfirmationLetterByEmail("Receipt");
		emailSubject = "Receipt#: "+receiptNum+" - "+parkName;
				
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		if(props != null) {
			this.verifyConsumableConfLetterInfo(cust, consumable, pay.belongOrderNum, receiptNum, totalFee);
		}
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Confirmation Letter Email";
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
		
		
		consumable.name = "WL2 - POSForVoid";
		consumable.code = "WL2";
		consumable.quantity = "1";
		totalFee = lm.getCusumableCustPrice(schema, consumable.code);
		
		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";
	}
	
	public void verifyConsumableConfLetterInfo(Customer cust,ConsumableInfo consumable,String orderNum,String receiptNum,String totalFee){
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
		
		if(!isPass){
			throw new ErrorOnPageException("Confrim letter info error");
		}else{
			logger.info("Confirm letter content correct");
		}
		
	}
}
