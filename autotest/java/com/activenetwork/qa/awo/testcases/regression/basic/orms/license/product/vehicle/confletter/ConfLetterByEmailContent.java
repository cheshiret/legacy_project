package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.confletter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 1. Purchase a vehicle.
 * 				 2. Go to receipt detail page. Request H&F Confirmation Letter.
 * 				 3. Check confirmation letter, following part should be upper case.
 *               Cust name, order number, receipt number, 
 * 				 
 * 				
 * @Preconditions: 1. In Admin Manager,assign rule's feature "RequestConfirmationLetter" to rule"HF HQ Role"
 * 					2. set 'TRUE' for UpperCase: <update X_PROP set value='true' where name like 'UppercaseOutput'>;
 * 					3. A vehicle product: V03 - ProcessFeeAdjustments
 * 					4. Customer: QA-RAFee2/TEST-RAFee2 
 * 				
 * @SPEC: TC 028745
 * @Task#: AUTO-1272
 * 
 * @author Jasmine
 * @Date  Oct 08, 2012
 * 
 */
public class ConfLetterByEmailContent extends LicenseManagerTestCase{
	Properties props[] = null;
	private String parkName = "";
	private String host, username, password, emailSubject,totalFee;
	private BoatInfo vehicle = new BoatInfo();
	public void execute() {
		lm.loginLicenseManager(login);
		// make a register vehicle order.
		lm.registerVehicleToOrderCart(cust, vehicle);
		pay.belongOrderNum  = lm.processOrderCart(pay, false);
		if(pay.belongOrderNum.contains(" ")){
			pay.belongOrderNum = pay.belongOrderNum.split(" ")[0];
		}
		logger.info("New register vehicle order number is "+pay.belongOrderNum);
		
		lm.gotoVehicleOrderDetailPage(pay.belongOrderNum);
		
		String receiptNum = lm.gotoReceiptPageFromVehicleOrder();
		logger.info("receiptNum is "+receiptNum);
		
		lm.gotoConfimLetterPg();
		lm.sentReceiptConfirmationLetterByEmail("Vehicle");
	    emailSubject = "Receipt#: "+receiptNum+" - "+parkName;
		props = lm.getEmailFromMailBox(host, username, password, emailSubject, OrmsConstants.CHECK_NOTIFICATION_IN_MAILBOX_TIMEDIFF);
		this.verifyPrVehicleOrderConfLetterInfo(cust, vehicle.registration.product, pay.belongOrderNum, receiptNum, totalFee);
		lm.logOutLicenseManager();
	}
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		
		host = TestProperty.getProperty("mail.pop3.host");
		username = TestProperty.getProperty("mail.pop3.user");
		password = TestProperty.getProperty("mail.pop3.pw");
		emailSubject = "Confirmation Letter Email";
		parkName = "Mississippi Department of Wildlife, Fisheries, and Parks";
		totalFee ="20" /*lm.getCustPriceByPrdCodeAndAppToAllState(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID)*/;

		vehicle.hullIdSerialNum = "UPPERCASE"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "2012";
		vehicle.feet = "2";  //this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.inches = "2";//this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.hullMaterial = "Other";
		vehicle.boatUse = "Other";//this value should be equal to 'AddVehicleProduct.datapool', record 26
		vehicle.propulsion = "Other";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "Other";
		//this value should be equal to 'AddVehicleProduct.datapool', record 26
		//there are two blank spaces between CD(V03) and name(ProcessFeeAdjustments)
		vehicle.registration.product = "V03 - ProcessFeeAdjustments";

		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";
	}
	
	private void verifyPrVehicleOrderConfLetterInfo(Customer cust,String proName,String orderNumber, String receiptNum,String totalFee){
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
		if(!emailContent.contains("Order # "+orderNumber)){
			isPass = false;
			logger.error("Order number "+orderNumber+" error");
		}
		if(!emailContent.contains("Receipt # "+receiptNum)){
			isPass = false;
			logger.error("Receipt number "+receiptNum+" error");
		}
		String year = lm.getFiscalYear(schema);
		String date = DateFunctions.getToday("MMM d yyyy");   
		String nextMonthLastDay = this.getLastDateOfNextMonth(DateFunctions.getCurrentYear());
		if(!emailContent.contains("("+year+") "+proName+" (Original); Valid: "+date+" to "+nextMonthLastDay)){
			isPass = false;
			logger.error("Privilege info Error");
		}
		if(!isPass){
			throw new ErrorOnPageException("Confrim letter info error");
		}else{
			logger.info("Confirm letter content correct");
		}
	}
	/*
	 * get last date of give month of year.
	 * yearNum - the number of current e.g this year is 2012.
	 * month -  the number of current e.g OCT is 9.
	 */
	private String getLastDateOfMonth(int yearNum,int monthNum){
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.YEAR, yearNum);
		calendar.set(calendar.MONTH, monthNum);
		int day = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		String date = DateFunctions.formatDate(String.valueOf(calendar.get(calendar.MONTH)+1)+"/"+String.valueOf(day)+"/"+String.valueOf(calendar.get(calendar.YEAR)),"MMM d yyyy");
		return date;
	}
	/**
	 * get last date of Next month.
	 * @param year
	 * @return
	 */
	private String getLastDateOfNextMonth(int year){
		Calendar calendar = Calendar.getInstance();
		int nextMonth = calendar.get(calendar.MONTH)+1;
		String lastDay = this.getLastDateOfMonth(year, nextMonth);
		return lastDay;
	}
}
