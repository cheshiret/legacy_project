
package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpCheckoutPage extends UwpPage {

	private RegularExpression cardNumReg = new RegularExpression("^cardnum_[0-9]+", false);
	
	private static UwpCheckoutPage _instance = null;

	public static UwpCheckoutPage getInstance() {
		if (null == _instance)
			_instance = new UwpCheckoutPage();

		return _instance;
	}

	public UwpCheckoutPage() {
	}

	protected Property[] errorMesProp(String errorMes){
		return Property.toPropertyArray(".className", "msg topofpage error", ".text", new RegularExpression(errorMes, false));
	}
	
	protected Property[] acknowledgement(){
		return Property.toPropertyArray(".className", "acknowledgement");
	}
	
	protected Property[] acknowlegeAccepted(){
		return Property.toPropertyArray(".name", "acknowlegeAccepted");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".name", "acknowlegeAccepted");//Not use chkout button as the previous page also use this as page mark
	}
	
	public void waitAjaxRefresh() {
		waitLoading();
		browser.sync();
	}
	
	/**
	 * click on 'Complete This Purchase' button
	 */
	public void clickCompleteThisPurchase() {
		browser.clickGuiObject(".id", "chkout");
	}
	
	/**
	 * click on 'continue to Shopping Cart' button
	 */
	public void gotoShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
	
	/**
	 * select to accept acknowlege statement
	 */
	public boolean isAcknowlegeAcceptedSelected(){
		return browser.isCheckBoxSelected(acknowlegeAccepted());
	}
	/**
	 * select to accept acknowlege statement
	 */
	public void selectAcknowlegeAcceptedon() {
		browser.selectCheckBox(acknowlegeAccepted());
		if(!isAcknowlegeAcceptedSelected()) //Sara[03172014] scroll bar effects Selenium to select check box
			browser.selectCheckBox(acknowlegeAccepted());
	}
	
	/**
	 * fill in all payment info
	 * @param pays - sets of payment
	 */
	public void setupPayments(Payment[] pays) {
		RegularExpression reg = new RegularExpression("cardTypeId_[0-9]+", false);
		IHtmlObject[] objs = browser.getDropdownList(".id", reg);

		if (pays.length < objs.length)
			throw new ItemNotFoundException("Need more payments. Expected "
					+ objs.length + " Actual " + pays.length);

		for (int i = 0; i < objs.length; i++) {
			List<String> options=((ISelect)objs[i]).getAllOptions();
			if(options.contains(pays[i].payType)) {
				((ISelect)objs[i]).select(pays[i].payType);
			} else {
				((ISelect)objs[i]).select(pays[i].reset(options.get(1)).payType);
			}
		}
		Browser.unregister(objs);

		reg = new RegularExpression("cardnum_[0-9]+", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[0].creditCardNumber);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("securityCode", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[i].securityCode);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("expmonth_[0-9]+", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[i].expiryMon);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("expyear_[0-9]+", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[0].expiryYear);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("fname_[0-9]+", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[0].fName);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("lname_[0-9]+", false);
		objs = browser.getTextField(".id",	reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(pays[0].lName);
		}
		Browser.unregister(objs);

		selectAcknowlegeAcceptedon();
	}
	
	/**
	 * Fill in all payment info
	 * @param pay - payment data
	 */
	public void setupPayment(Payment pay) {
		if(pay.isMinimumPayment){
			this.selectMinimumPayment();
		}
	  	selectAllPaymentTypes(pay);
		setAllCardNumbers(pay.creditCardNumber);
		setAllSecurityCodes(pay.securityCode);
		setAllExpireMonthes(pay.expiryMon);
		setAllExpireYears(pay.expiryYear);
		setAllCardHolderNames(pay.lName, pay.fName);
		setAllPostCode(pay.zip);
	}
	
	/**
	 * fill in post code
	 * @param code - post code
	 */
	public void setPostCode(String code) {
		if (code.length() < 1)
			code = "12345";
		browser.setTextField(".name", "postalCode", code);
	}
	
	/**
	 * fill in all post code fields with same code
	 * @param code - post code
	 */
	public void setAllPostCode(String code) {
		if (code.length() < 1)
			code = "12345";
		RegularExpression reg = new RegularExpression("ccPostCode_[0-9]+",
				false);
		IHtmlObject[] objs = browser.getTextField(".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(code);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * select the type of payment
	 * @param type - payment type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", "cardTypeId_1", type);
	}
	
	/**
	 * select the payment type where the field specified by index
	 * @param type - payment type
	 * @param index - field's index where you want to select
	 */
	public void selectPaymentType(String type, int index) {
		browser.selectDropdownList(".id", "cardTypeId_" + index, type);
	}
	
	/**
	 * select all payment type fields with the same type
	 * @param type - payment type
	 */
	public void selectAllPaymentTypes(Payment pay) {
		RegularExpression reg = new RegularExpression("cardTypeId_[0-9]+",
				false);
		IHtmlObject[] objs = browser.getDropdownList(".id", reg);
		for (int i = 0; i < objs.length; i++) {
			List<String> options=((ISelect)objs[i]).getAllOptions();
			if(options.contains(pay.payType)) {
				((ISelect)objs[i]).select(pay.payType);
			} else if(pay.flexible) {
				((ISelect)objs[i]).select(pay.reset(options.get(1)).payType);
			} else {
				throw new ItemNotFoundException("Expected payment type \""+pay.payType+"\" is not available");
			}
		}
		Browser.unregister(objs);
	}
	
	/**
	 * fill in all card number fields with the same number
	 * @param num - card number
	 */
	public void setAllCardNumbers(String num) {
		IHtmlObject[] objs = browser.getTextField(".id", cardNumReg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(num);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * fill in first card number field
	 * @param num - card number 
	 */
	public void setCardNumber(String num) {
		browser.setTextField(".id", "cardnum_1", num);
	}
	
	/**
	 * fill in the card number where field specified by index
	 * @param num - card number
	 * @param index - index of card number field
	 */
	public void setCardNumber(String num, int index) {
		browser.setTextField(".id", "cardnum_" + index, num);
	}
	
	/**
	 * Fill in all cards expire month fields with the same month
	 * @param mon - expire month
	 */
	public void setAllExpireMonthes(String mon) {
		RegularExpression reg = new RegularExpression("expmonth_[0-9]+", false);
		IHtmlObject[] objs = browser.getTextField( ".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(mon);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in card expire month where field specified by index
	 * @param mon - expire month
	 * @param index - index of card expire month field
	 */
	public void setExpireMonth(String mon, int index) {
		browser.setTextField(".id", "expmonth_" + index, mon);
	}
	
	/**
	 * Fill in the first card expire month field
	 * @param mon - expire month
	 */
	public void setExpireMonth(String mon) {
		browser.setTextField(".id", "expmonth_1", mon);
	}
	
	/**
	 * Fill in all cards expire year fields with the same year
	 * @param year - expire year
	 */
	public void setAllExpireYears(String year) {
		RegularExpression reg = new RegularExpression("expyear_[0-9]+", false);
		IHtmlObject[] objs = browser.getTextField(".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(year);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in card expire year where field specified by index
	 * @param year - expire year
	 * @param index - index of card expire year field
	 */
	public void setExpireYear(String year, int index) {
		browser.setTextField(".id", "expyear_" + index, year);
	}
	
	/**
	 * Fill in the first card expire year field
	 * @param year - expire year
	 */
	public void setExpireYear(String year) {
		browser.setTextField(".id", "expyear_1", year);
	}
	
	/**
	 * Fill in all card holder's first and last name with the same 
	 * @param lName - last name
	 * @param fName - first name
	 */
	public void setAllCardHolderNames(String lName, String fName) {
		RegularExpression reg = new RegularExpression("fname_[0-9]+", false);
		IHtmlObject[] objs = browser.getTextField(".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(fName);
		}
		Browser.unregister(objs);

		reg = new RegularExpression("lname_[0-9]+", false);
		objs = browser.getTextField(".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(lName);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in card holder info where field is specified by index
	 * @param lName - last name
	 * @param fName - first name
	 * @param index - index of card holder field
	 */
	public void setCardHolderName(String lName, String fName, int index) {
		browser.setTextField(".id", "fname_" + index, fName);
		browser.setTextField(".id", "lname_" + index, lName);
	}
	
	/**
	 * Fill in the first card holder field
	 * @param lName - last name
	 * @param fName - first name
	 */
	public void setCardHolderName(String lName, String fName) {
		browser.setTextField(".id", "fname_1", fName);
		browser.setTextField(".id", "lname_1", lName);
	}
	
	/**
	 * Fill in all security code fields with the same code
	 * @param code - security code
	 */
	public void setAllSecurityCodes(String code) {
		RegularExpression reg = new RegularExpression("^seccode_[0-9]+", false);
		IHtmlObject[] objs = browser.getTextField(".class","Html.INPUT.text", ".id", reg);
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(code);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in the security code where the field specified by index
	 * @param code - security code
	 * @param index - index of security code field
	 */
	public void setSecurityCode(String code, int index) {
		browser.setTextField(".id", "seccode_" + index, code, index);
	}
	
	/**
	 * Fill in the first security code field
	 * @param code - security code
	 */
	public void setSecurityCode(String code) {
		browser.setTextField(".id", "seccode_1", code);
	}

	/**
	 * This method goes to verify whether the shopping item payment info display correctly
	 *  eg. 1. several items from one contract - one payment item;
	 *      2. several items from several contracts - seperate payment item.
	 * @param numOfState - the number of the states which the parks belong to in this order
	 * @return true when shopping item is right, else false
	 */
	public boolean checkPaymentRules(int numOfState) {
		boolean toReturn = false;
		IHtmlObject[] shopItems = browser.getHtmlObject(".id","shoppingitems1");

		if (shopItems.length != numOfState) {
			throw new ErrorOnPageException("Payment items divided against rules.");
		} else {
			if (shopItems.length == 1)
				logger.info("--- Shopping items from the same contract.");
			else
				logger.info("--- Shopping items from " + shopItems.length
						+ " contract(s)");

			toReturn = true;
		}

		Browser.unregister(shopItems);
		return toReturn;
	}
	/**
	 * Select the 'Minimum Payment' radio button
	 */
	public void selectMinimumPayment() {
		browser.selectRadioButton(".id", "paymentAmountChoice_MINIMUM_1");
	}
	/**
	 * Select the 'Full Payment' radio button
	 */
	public void selectFullPayment() {
		browser.selectRadioButton(".id", "paymentAmountChoice_FULL_1");
	}

	/**
	 * Retrieve the payment field in shopping cart, often when one item is free
	 * @return - number of payment fields
	 */
	public int checkNumberofPaymentsField() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".id","ccInfo");
	  	int num=objs.length;
	  	
	  	Browser.unregister(objs);
	  	return num;
	}
	
	/**
	 * Retrieve the hidden payment fields, used for verify free reservation.
	 * @return - number of hidden payment fields
	 */
	public int getHiddenPaymentFields() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.hidden", ".id", cardNumReg);
		int num = objs.length;
		
		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * Retrieve the visible payment fields, normal reservation should have.
	 * @return - number of payment fields
	 */
	public int getVisiblePaymentFields() {
		IHtmlObject[] objs = browser.getTextField(".class","Html.INPUT.text", ".id", cardNumReg);
		int num = objs.length;
		
		Browser.unregister(objs);
		return num;
	}
	
	/** Click use a gift card link. */
	public void clickUseGiftCard() {
	  	browser.clickGuiObject(".class","Html.A",".text","Use a Gift Card");
	}
	
	/**
	 * Fill in first gift card number field.
	 * @param num - gift card number
	 */
	public void setGiftCardNum(String num) {
		browser.setTextField(".id", "giftcardnum_1", num);
	}
	
	/**
	 * Fill in first gift card security code field.
	 * @param num - gift card secutiry code
	 */
	public void setGiftCardSecCode(String code){
	  	browser.setTextField(".id", "giftcardseccode_1", code);
	}
	
	/** Verify whether or not need to set gift card security code, 
	 * true - need; false - no need*/
	public boolean needSecurityCode(){
		boolean need = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id","giftcardseccode_1");
		if(objs[0].getProperty(".type").equalsIgnoreCase("hidden"))
			need = false;
		
		Browser.unregister(objs);
		return need;
	}
	
	/** verify the gift card. */
	public void clickVerify() {
	  	browser.clickGuiObject(".class","Html.A",".text","Verify");
	}
	
	/**
	 * Fill in gift card info and verify card with security code
	 * @param cardNum - card number
	 * @param code - security code
	 */
	public void verifyGiftCard(String cardNum, String code) {
//		logger.info(" -- Verify gift card "+(code.length()>0 ?"with":"without")+" security code.");
		logger.info(" -- Verify gift card with security code.");
	  	clickUseGiftCard();
		setGiftCardNum(cardNum);
		setGiftCardSecCode(code);
		clickVerify();
	}
	
	/**
	 * Fill in gift card info and verify card without security code
	 * @param cardNum - card number
	 */
	public void verifyGiftCard(String cardNum) {
		logger.info(" -- Verify gift card without security code.");
		clickUseGiftCard();
		setGiftCardNum(cardNum);
		clickVerify();
	}
	
	/** Click link to undo gift card verification. */
	public void undoGiftCard() {
	  	browser.clickGuiObject(".class","Html.A",".text","Undo");
	}
	
	/**
	 * Retrieve the credit card remaining amount when pay other payment types.
	 * @return - credit card amount
	 */
	public String getCreditCardAmount() {
	  	IHtmlObject[] objs = browser.getTableTestObject(".id","equiptment");
	  	
	  	String amount="";
	  	for(int i=0; i<((IHtmlTable)objs[0]).rowCount(); i++) {
	  	  	if(((IHtmlTable)objs[0]).getCellValue(i,0).equals("Credit Card Amount")){
	  	  	  	amount=((IHtmlTable)objs[0]).getCellValue(i,1).replaceAll("\\$","").trim();
	  	  	  	break;
	  	  	}
	  	}
	  	
	  	Browser.unregister(objs);
	  	return amount;
	}
	
	/**
	 * Retrieve the gift card number. 
	 * @return - gift card number
	 */
	public String getGiftCardNum() {
	  	IHtmlObject[] objs=browser.getTableTestObject(".className","giftCardDisplay");
	  	String cardNum = ((IHtmlTable)objs[0]).getCellValue(1,0);
	  	
	  	Browser.unregister(objs);
	  	return cardNum;
	}
	
	/**
	 * Retrieve the voucher amount by given voucher id
	 * @param voucherID - voucher id
	 * @return - voucher amount
	 */
	public String getVoucherAmountByID(String voucherID) {
	  	IHtmlObject[] objs=browser.getTableTestObject(".className","voucher");
	  	
	  	String amount="";
	  	for(int i=0; i<((IHtmlTable)objs[0]).rowCount(); i++) {
	  	  	if(((IHtmlTable)objs[0]).getCellValue(i,1).equals(voucherID)) {
	  	  	  	amount=((IHtmlTable)objs[0]).getCellValue(i,0).split("\\$")[1].trim();
	  	  	}
	  	}
	  	
	  	if(amount.length()<1)
	  	  	throw new ItemNotFoundException("Not found voucher by given id.");
	  	
	  	Browser.unregister(objs);
	  	return amount;
	}
	
	/**
	 * Click on redeem voucher by given voucher id.
	 * @param voucherID - voucher id
	 */
	public void clickVoucherByID(String voucherID) {
//	  	HtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".href", 
//	  	    							new RegularExpression(".*redeemVoucher.*",false));
//	  	int counter=1;
//	  	for(int i=0; i<objs.length; i++) {
//	  	  	if(objs[i].getProperty(".href").toString().indexOf(voucherID)!=-1) {
//	  	  		((ILink)objs[i]).click();//click on link
//	  	  	  	counter++;
//	  	  	  	break;
//	  	  	}
//	  	}
//	  	if(counter==1)
//	  	  	throw new ItemNotFoundException("Not found voucher by given id");
//	  	Browser.unregister(objs);
		//app changed, invoke java script now
		browser.clickGuiObject(".class","Html.A",".href", 
	  	    							new RegularExpression(".*"+voucherID+".*",false));	
	}
	
	/**
	 * Undo redeem voucher by given voucher id.
	 * @param voucherID - voucher id
	 */
	public void undoVoucherByID(String voucherID) {
	  	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".href", 
	  	    							new RegularExpression(".*false",false));
	  	int counter=1;
	  	for(int i=0; i<objs.length; i++) {
	  	  	if(objs[i].getProperty(".href").toString().indexOf(voucherID)!=-1) {
	  	  		((ILink)objs[i]).click();//click on link
	  	  	  	counter++;
	  	  	  	break;
	  	  	}
	  	}
	  	if(counter==1)
	  	  	throw new ItemNotFoundException("Not found voucher by given id");
	  	Browser.unregister(objs);
	}
	
	/**Click on Copy card info from previous link*/
	public void clickCopyPreviousCardInfo(){
		browser.clickGuiObject(".class","Html.A",".href",new RegularExpression(".*copyCardInfo.*",false));
	}
	
	public boolean isErrorMsgExist(String errorMes) {
		return browser.checkHtmlObjectExists(errorMesProp(errorMes));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}

}
