/*
 * $Id: FldMgrPaymentDetailPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsPaymentDetailsPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrPaymentDetailPage</b>
	 * Generated     : <b>Sep 30, 2004 6:34:21 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/09/30
	 * @author CGuo
	 */
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPaymentDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPaymentDetailsPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPaymentDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsPaymentDetailsPage();
		}

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {//use the payment details trail as the pageMark
		return (browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Payment Details"))
		&&(browser.checkHtmlObjectExists(".class", "Html.TBODY", ".text", "QA"));
	}

	/**
	 * The method used to get fin session ID
	 * @return
	 */
	public String getFinSession(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction\\( \"FinSessionSumm\\.do\".*", false));
		String finSess = "";
		if(objs!=null&&objs.length>0){
			finSess = objs[0].getProperty(".text").toString();
			Browser.unregister(objs);
		}
		return finSess;
	}
	/**
	 * Get reservation Number
	 * @return
	 */
	public String getRevervNum() {
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("\\d-\\d+", false));
		IHtmlObject[] objs =browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Actions.*", false));
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(0, "Orders");
		String ordNum = table.getCellValue(row, 1);
		if(ordNum.contains("#")){
			ordNum = ordNum.split("\\#")[1].trim();
			ordNum = RegularExpression.getMatches(ordNum, "[1-9]-\\d+")[0].trim();
		}
		Browser.unregister(objs);
		return ordNum;
	}
	
	/** Get the order number array */
	public String[] getOrdNumsArray() {
		//RegularExpression reg=new RegularExpression(".*this payment has been allocated to order.*",false);
		
		RegularExpression reg=new RegularExpression("^Orders.*",false);
		String text=browser.getObjectText(".class","Html.TR",".text",reg);
		String[] numbers=RegularExpression.getMatches(text, "[1-9]-\\d{3,}");
		
		return StringUtil.distinctFilter(numbers);
	}
	
	/** Get the order numbers of all orders shown */
	public String getAllOrdNums() {
		String result = "";
		StringBuffer resultBuffer = new StringBuffer();
		String[] numbers=getOrdNumsArray();
		for(int i=0;i<numbers.length;i++) {
			resultBuffer.append(" "+numbers[i].trim());
		}

		result = resultBuffer.toString().trim();

		if (result.length() < 1)
			throw new ItemNotFoundException("Failed to get order numbers.");

		return result;
	}

	/**
	 * Click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Ok Button
	 *
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Click Apply Button
	 *
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Click Void Button
	 *
	 */
	public void clickVoidPayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void Payment");
	}
	
	/**
	 * Check if the Void Payment enable to be operated
	 * @return
	 */
	public boolean checkVoidPaymentEnable(){
		return browser.checkHtmlObjectExists(".text", "Void Payment",".class","Html.A");
	}
	
	public void clickVoidOrReturnPayment() {
		RegularExpression pattern=new RegularExpression("(Void|Return) Payment",false);
		browser.clickGuiObject(".class", "Html.A", ".text", pattern);
	}

	/**
	 * Click NSF button
	 *
	 */
	public void clickNSFPayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "NSF");
	}

	/**
	 * Click ChargeBack Button
	 *
	 */
	public void clickChargeBack() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Charge Back");
	}

	/**
	 * Click revers charge Back Button
	 *
	 */
	public void clickReversChargeBack() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Reverse Charge Back");
	}

	/**
	 * Click Return Button
	 *
	 */
	public void clickReturnPayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Return Payment");
	}

	/**
	 * Click Payments Link
	 *
	 */
	public void clickPayments() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Payments");
	}
	
	/**
	 * Click Reservation Number
	 * @param resNum reservation number
	 */
	public void clickResNum(String resNum){
		browser.clickGuiObject(".class", "Html.A", ".text", resNum);
	}

	/**
	 * Input reason
	 * @param reason
	 */
	public void setReason(String reason) {
		browser.setTextArea(".id", "payrefdetailaddnotes", reason);
	}

	/**
	 * Get payment status from specific table
	 * @return
	 */
	public String getPaymentStatus() {
		//	Value at cell (0,3) is: 'Status Received'
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Payment ID", false));
		String status = ((IHtmlTable)objs[1]).getCellValue(0, 2).split("Payment Status")[1].split("Payment Type")[0].trim();
		Browser.unregister(objs);
		return status;
	}

	/**
	 * Get Failed Reason from a DIV Object
	 * @return failed reason
	 */
	public String getFailedReason() {
	   	IHtmlObject []objs = browser.getHtmlObject(".class","Html.DIV",".id","finsession.save.payment.failed");
	   	String fReasn = objs[0].getProperty(".text").toString();
	   	Browser.unregister(objs);
	   	return fReasn;
	}
	
	/**
	 * Get payment type value from specific table
	 * @return
	 */
	public String getPaymentType() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Payment ID", false));
		String type = ((IHtmlTable)objs[1]).getCellValue(0, 2).split("Payment Type")[1].split("Payment Group")[0].trim();
		Browser.unregister(objs);
		return type;
	}

	/**
	 * Get payment Id value from specific table
	 * @return
	 */
	public String getPaymentID() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Payment ID", false));
		String paymentId = ((IHtmlTable)objs[1]).getCellValue(0, 2).split("Payment ID")[1].split("Payment Status")[0].trim();
		Browser.unregister(objs);
		return paymentId;
	}
	
	/**
	 * Get Credit Card Number from specific table
	 * @return
	 */
	public String getCreditCardNum() {
		  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
		  String text = objs[0].getProperty(".text").toString();
		  Browser.unregister(objs);
		  
		  String tempText = text.substring(text.indexOf("Credit Card Number")+"Credit Card Number".length(), text.length()).trim();
		  String cardNum = "";
		  if(tempText.contains("Credit Card")){
			  cardNum = tempText.substring(0, tempText.indexOf("Credit Card")).trim();  
		  }else if(tempText.contains("EFTConfigSchedule")){
			  cardNum = tempText.substring(0, tempText.indexOf("EFTConfigSchedule")).trim();
		  }else{
			  cardNum = tempText.substring(0, tempText.indexOf("Authorization")).trim();
		  }
		  
		  String firstFourNum = cardNum.substring(0,4);
		  String lastFourNum = cardNum.substring(cardNum.length()-4,cardNum.length());
		  return firstFourNum+lastFourNum;
	}
	/**
	 * Get Credit Card expiry Date
	 * @return
	 */
	public String getExpiryDate() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
	  String text = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  String tempText = text.substring(text.indexOf("Credit Card Expiry Date")+"Credit Card Expiry Date".length(), text.length()).trim();
	  
	  return tempText.substring(0, tempText.indexOf("Credit Card")).trim();
	}
	
	/**
	 * Get Credit Card Holder
	 * @return
	 */
	public String getCreditCardHolder() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
	  String text = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  String tempText = text.substring(text.indexOf("Credit Card Holder Name")+"Credit Card Holder Name".length(), text.length()).trim();
	  if(tempText.contains("Authorization")){
		  if(tempText.contains("Credit Card")&&(tempText.indexOf("Authorization")>tempText.indexOf("Credit Card"))){
			  return tempText.substring(0, tempText.indexOf("Credit Card")).trim();
		  }else if(tempText.contains("EFTConfigSchedule")&&(tempText.indexOf("Authorization")>tempText.indexOf("EFTConfigSchedule"))){
			  return tempText.substring(0, tempText.indexOf("EFTConfigSchedule")).trim();
		  }
		  return tempText.substring(0, tempText.indexOf("Authorization")).trim();
	  }
	  if(tempText.contains("Credit Card")&&tempText.indexOf("Orders")>tempText.indexOf("Credit Card")){
		  return tempText.substring(0, tempText.indexOf("Credit Card")).trim();
	  }
	  return tempText.substring(0, tempText.indexOf("Orders")).trim();
	}
	
	/**
	 * Get Check Number
	 * @return
	 */
	public String getCheckNum() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
	  String text = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  
	  if(text.indexOf("Check Number")>text.indexOf("Check Holder Name")){
		  String temp = text.substring(text.indexOf("Check Number")+"Check Number".length(), text.indexOf("Orders")).trim();
		  if(temp.contains("EFTConfigSchedule")){
			  temp = temp.substring(0, temp.indexOf("EFTConfigSchedule")).trim();
		  }
		  return temp;
	  }else{
		  String tempText = text.substring(text.indexOf("Check Number")+"Check Number".length(), text.length()).trim();
		  return tempText.substring(0, tempText.indexOf("Check")).trim();
	  }	  
	}
	
	/**
	 * Get check Date
	 * @return
	 */
	public String getCheckDate() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
	  String text = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  
	  if(text.indexOf("Check Holder Name")>text.indexOf("Check Number")&&text.indexOf("Check Number")>text.indexOf("Check Date")){
		  return text.substring(text.indexOf("Check Date")+"Check Date".length(), text.indexOf("Check Number")).trim();
	  }else{
		  return text.substring(text.indexOf("Check Date")+"Check Date".length(), text.indexOf("Check Holder Name")).trim();		  
	  }
	}
	
	/**
	 * Get check Holder Name
	 * @return
	 */
	public String getCheckHolder() {
	  IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*",false));
	  String text = objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
//	  text.contains("EFTConfigSchedule")&&SysInfo.getCurrentEnv().matches("qa2|qa4")&&text.indexOf("EFTConfigSchedule")>text.indexOf("Check Holder Name")
	  if(text.contains("EFTConfigSchedule")&&text.indexOf("EFTConfigSchedule")>text.indexOf("Check Holder Name")){
		  return text.substring(text.indexOf("Check Holder Name")+"Check Holder Name".length(),text.indexOf("EFTConfigSchedule")).trim();
	  }else{
		  if(text.indexOf("Check Number")>text.indexOf("Check Holder Name")){
			  String tempText = text.substring(text.indexOf("Check Holder Name")+"Check Holder Name".length(), text.length()).trim();
			  return tempText.substring(0, tempText.indexOf("Check")).trim();
		  }else{
			  return text.substring(text.indexOf("Check Holder Name")+"Check Holder Name".length(), text.indexOf("Orders")).trim();
		  }
	  }
	}
	
	/**
	 * Get warning message
	 * @return message
	 */
	public String getWarningMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "finsession.save.payment.failed");
		return objs[0].getProperty(".text");
	}
	
	/**
	 * Get payment amount
	 * @return payment amount
	 * 
	 * Add by Lesley Wang
	 */
	public String getPaymentAmount() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		
		return getPaymentAmount(text);
	}
	
	/**
	 * Get payment amount
	 * @return payment amount
	 * 
	 * @author Leslsy Wang
	 */
	private String getPaymentAmount(String text) {
		String amount = text.substring(text.indexOf("Amount") + "Amount".length(),
				text.indexOf("Change Tendered")).trim();
		return amount.substring(amount.indexOf("$")+1);
	}
	
	/**
	 * Retrieve Payment details Info
	 * @return Payment Object
	 */
	public Payment retrivePaymentDetails() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		Payment pay = new Payment();

//		pay.customer = ((ITable)objs[0]).getCellValue(2, 1).split("Name")[1].split("Phone #")[0].trim();
		pay.customer = text.substring(text.indexOf("Name") + "Name".length(),
				text.indexOf("Phone #")).trim();
//		String amount = text.substring(text.indexOf("Amount") + "Amount".length(),
//				text.indexOf("Change Tendered")).trim();
//		pay.amount = amount.substring(amount.indexOf("$")+1);
		pay.amount = getPaymentAmount(text);
		pay.collectUser = text.substring(
				text.indexOf("User") + "User".length(),
				text.indexOf("Pin User")).trim();
		pay.collectLocation = text.substring(
				text.indexOf("Collect Location") + "Collect Location".length(),
				text.indexOf("Collect Station")).trim();
		pay.paymentID = getPaymentID();
		pay.status = getPaymentStatus();
		pay.finSession = getFinSession();
		pay.belongOrderNum = getRevervNum();
		pay.payType = getPaymentType();
		return pay;
	}

	/**
	 * Verify payment is allocated to given order
	 * @param amount
	 * @param ordNum
	 */
	public void verifyAllocatePayToOrder(String ordNum){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Void Payment NSF Charge Back Reverse Charge Back.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		// expect is like below:(because of blank, so replace.)
		// $40.17  of this payment has been allocated to order #  2-20647042
		if(!text.replaceAll(" ", "").contains("thispaymenthasbeenallocatedtoorder#"+ordNum)){
			throw new ErrorOnPageException("Payment Is not Allocated To Order "+ordNum);
		}
	}
	
	/**
	 * Verify Payment details is same with the given Payment
	 * @param pay Payment
	 * @param isAdditionalPay mark this payment is additionalpayment or not
	 */
	public void verifyPaymentDetails(Payment pay,boolean isAdditionalPay) {
		Payment pay1 = retrivePaymentDetails();
		if(isAdditionalPay)
		{
		  	  if(pay.additionalPayType.equalsIgnoreCase("Personal Check"))
		  	  {
		  	    	pay.additionalPayType = "PER CHQ";
		  	    	if(!getCheckNum().equalsIgnoreCase(pay.additionalCheckNumber))
		  	    	{
			  	    	logger.error("Check Number " + getCheckNum()+ " is not correct!");
						throw new ItemNotFoundException("Check Number " + getCheckNum()+ " is not correct!");
		  	    	}
		  	    	if(!getCheckHolder().equalsIgnoreCase(pay.additionalCheckName))
		  	    	{
		  	    	  	logger.error("Check Holder " + getCheckHolder()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Check Holder " + getCheckHolder()+ " is not correct!");
		  	    	}
		  	    	if(!getCheckDate().equalsIgnoreCase(pay.additionalCheckDate))
		  	    	{
			  	    	logger.error("Check Date " + getCheckDate()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Check Date " + getCheckDate()+ " is not correct!");
		  	    	}
		  	  
		  	  }else if(pay.additionalPayType.equalsIgnoreCase("Money Order"))
		  	  {
		  	    	pay.additionalPayType = "MON ORD";
		  	    	if(!getCheckNum().equalsIgnoreCase(pay.additionalCheckNumber))
		  	    	{
			  	    	logger.error("Check Number " + getCheckNum()+ " is not correct!");
						throw new ItemNotFoundException("Check Number " + getCheckNum()+ " is not correct!");
		  	    	}	
		  	  }else if(pay.additionalPayType.equalsIgnoreCase("Travellers Check"))
		  	  {
		  	    	pay.additionalPayType = "TRAV CHK";
		  	  }else if(pay.additionalPayType.equalsIgnoreCase("MasterCard")||pay.additionalPayType.equalsIgnoreCase("Visa")||pay.additionalPayType.equalsIgnoreCase("Discover")||pay.additionalPayType.equalsIgnoreCase("American Express"))
		  	  {
		  	    	if(pay.additionalPayType.equalsIgnoreCase("MasterCard"))
		  	    	{
		  	    	  	pay.additionalPayType = "MC";
		  	    	}
		  	    	
		  	    	if(pay.additionalPayType.equalsIgnoreCase("Discover"))
		  	    	{
		  	    	  	pay.additionalPayType = "DISC";
		  	    	}
		  	    	
		  	    	if(pay.additionalPayType.equalsIgnoreCase("American Express"))
		  	    	{
		  	    	  	pay.additionalPayType = "AMEX";
		  	    	}
		  	    	
		  	    	String cardNum = pay.additionalCreditCardNumber.substring(0,4)+pay.additionalCreditCardNumber.substring(pay.additionalCreditCardNumber.length()-4,pay.additionalCreditCardNumber.length());
		  	    	if(!cardNum.equalsIgnoreCase(getCreditCardNum()))
		  	    	{
		  	    	  	logger.error("Credit Card Number " + getCreditCardNum()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Credit Card  Number " + getCreditCardNum()+ " is not correct!");
		  	    	}
		  	    	String expiryDate = pay.additionalExpiryMon+"/"+pay.additionalExpiryYear;
		  	    	if(!expiryDate.equalsIgnoreCase(getExpiryDate()))
		  	    	{
		  	    	  	logger.error("ExpiryDate " + getExpiryDate()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("ExpiryDate " + getExpiryDate()+ " is not correct!");
		  	    	}
		  	    	if(!pay.additionalCardHolder.equalsIgnoreCase(getCreditCardHolder()))
		  	    	{
		  	    	  	logger.error("Credit Card Holder " + getCreditCardHolder()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Credit Card Holder " + getCreditCardHolder()+ " is not correct!");
		  	    	}
		  	  }
			  if (!pay.additionalPayType.equals(pay1.payType)) {
				logger.error("Payment Type " + pay1.payType + " is not correct!");
				throw new ItemNotFoundException("Payment Type " + pay1.payType+ " is not correct!");
			  }
			  if (!pay.additionalAmount.equals(pay1.amount)) {
				logger.error("Payment amount " + pay1.amount + " is not correct!");
				throw new ItemNotFoundException("Payment amount " + pay1.amount
						+ " is not correct!");
			  }
			  
		}else
		{
			  if(pay.payType.equalsIgnoreCase("Personal Check"))
		  	  {
		  	    	pay.payType = "PER CHQ";
		  	    	if(!getCheckNum().equalsIgnoreCase(pay.checkNumber))
		  	    	{
			  	    	logger.error("Check Number " + getCheckNum()+ " is not correct!");
						throw new ItemNotFoundException("Check Number " + getCheckNum()+ " is not correct!");
		  	    	}
		  	    	if(!getCheckHolder().equalsIgnoreCase(pay.checkName))
		  	    	{
		  	    	  	logger.error("Check Holder " + getCheckHolder()+ " is not correct!");
		  	    	  	System.out.println(pay.checkName);
		  	    	  	throw new ItemNotFoundException("Check Holder " + getCheckHolder()+ " is not correct!");
		  	    	}
		  	    	if(!getCheckDate().contains(pay.checkDate)&&DateFunctions.compareDates(getCheckDate(),pay.checkDate)!=0)
		  	    	{
			  	    	logger.error("Check Date " + getCheckDate()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Check Date " + getCheckDate()+ " is not correct!");
		  	    	}
		  	  
		  	  }else if(pay.payType.equalsIgnoreCase("Money Order"))
		  	  {
		  	    	pay.payType = "MON ORD";
		  	    	if(!getCheckNum().equalsIgnoreCase(pay.checkNumber))
		  	    	{
			  	    	logger.error("Check Number " + getCheckNum()+ " is not correct!");
						throw new ItemNotFoundException("Check Number " + getCheckNum()+ " is not correct!");
		  	    	}	
		  	  }else if(pay.payType.equalsIgnoreCase("Certified Check"))
		  	  {
		  	    	pay.payType = "CERT CHQ";
		  	    	if(!getCheckNum().equalsIgnoreCase(pay.checkNumber))
		  	    	{
			  	    	logger.error("Check Number " + getCheckNum()+ " is not correct!");
						throw new ItemNotFoundException("Check Number " + getCheckNum()+ " is not correct!");
		  	    	}	
		  	  }else if(pay.payType.equalsIgnoreCase("Travellers Check"))
		  	  {
		  	    	pay.payType = "TRAV CHK";
		  	  }else if(pay.payType.equalsIgnoreCase("MasterCard")||pay.payType.equalsIgnoreCase("Visa")||pay.payType.equalsIgnoreCase("Discover")||pay.payType.equalsIgnoreCase("American Express"))
		  	  {
		  	    	if(pay.payType.equalsIgnoreCase("MasterCard"))
		  	    	{
		  	    	  	pay.payType = "MC";
		  	    	}
		  	    	if(pay.payType.contains("Discover"))
		  	    	{
		  	    	  	pay.payType = "DISC";
		  	    	}
		  	    	
		  	    	if(pay.payType.equalsIgnoreCase("American Express"))
		  	    	{
		  	    	  	pay.payType = "AMEX";
		  	    	}
		  	    	String cardNum = pay.creditCardNumber.substring(0,4)+pay.creditCardNumber.substring(pay.creditCardNumber.length()-4,pay.creditCardNumber.length());
		  	    	if(!cardNum.equalsIgnoreCase(getCreditCardNum()))
		  	    	{
		  	    	  	logger.error("Credit Card Number " + getCreditCardNum()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Credit Card  Number " + getCreditCardNum()+ " is not correct!");
		  	    	}
		  	    	String expiryDate = pay.expiryMon+"/"+pay.expiryYear;
		  	    	if(!expiryDate.equalsIgnoreCase(getExpiryDate()))
		  	    	{
		  	    	  	logger.error("ExpiryDate " + getExpiryDate()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("ExpiryDate " + getExpiryDate()+ " is not correct!");
		  	    	}
		  	    	if(!pay.cardHolder.equalsIgnoreCase(getCreditCardHolder()))
		  	    	{
		  	    	  	logger.error("Credit Card Holder " + getCreditCardHolder()+ " is not correct!");
		  	    	  	throw new ItemNotFoundException("Credit Card Holder " + getCreditCardHolder()+ " is not correct!");
		  	    	}
		  	  }
			  if ((!pay.payType.equalsIgnoreCase(pay1.payType))&&(!(pay.payType.contains(pay1.payType)))) //updated by pzhu, this condition: payType=Discover/JCB/UnionPay, but on the web page, displayes as 'DISC' 
			  {
				logger.error("Payment Type " + pay1.payType + " is not correct!");
				throw new ItemNotFoundException("Payment Type " + pay1.payType+ " is not correct!");
			  }
			  
			  if (!MiscFunctions.compareResult("Payment amount", Double.parseDouble(pay1.amount), Double.parseDouble(pay.amount))) {
			      throw new ItemNotFoundException("Payment amount " + pay1.amount + " is not correct!");
			  }
		}
		if (!pay.paymentID.equals(pay1.paymentID)) {
			logger.error("PaymentID " + pay1.paymentID + " is not correct!");
			throw new ItemNotFoundException("PaymentID " + pay1.paymentID + " is not correct!");
		}
		if(pay.status!=null&&pay.status.length()>0){
			if(!pay.status.equals(pay1.status)){
				throw new ItemNotFoundException("Payment Status "+pay1.status+" Not Correct.");
			}
		}
		if(pay.finSession!=null&&pay.finSession.length()>0){
			System.out.println(pay.finSession);
			System.out.println(pay1.finSession);
			if(!pay.finSession.equals(pay1.finSession)){
				throw new ItemNotFoundException("Payment Belongs to Fin Session "+pay1.finSession+" Not Correct.");
			}
		}
		if (!pay.belongOrderNum.equals(pay1.belongOrderNum)) {
			logger.error("Payment OrderNum " + pay1.belongOrderNum + " is not correct!");
			throw new ItemNotFoundException("Payment OrderNum " + pay1.belongOrderNum + " is not correct!");
		}
		
		if (!pay.customer.equals(pay1.customer)) {
			logger.error("Payment customer " + pay1.customer + " is not correct!");
			throw new ItemNotFoundException("Payment customer " + pay1.customer + " is not correct!");
		}
		if (!pay1.collectLocation.contains(pay.collectLocation)) {
			logger.error("Payment collectLocation " + pay1.collectLocation + " is not correct!");
			throw new ItemNotFoundException("Payment collectLocation " + pay1.collectLocation + " is not correct!");
		}
		if (!pay.collectUser.equals(pay1.collectUser)) {
			logger.error("Payment collectUser " + pay1.collectUser + " is not correct!");
			throw new ItemNotFoundException("Payment collectUser " + pay1.collectUser + " is not correct!");
		}
	}
	
	public String getAuthNum(){
		return browser.getObjectText(".class", "Html.DIV", ".text", new RegularExpression("^Authorization #", false)).replaceAll("Authorization #", StringUtil.EMPTY).trim();
	}
	
	
	
}
