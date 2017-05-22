package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpChangeReservationConfirmPage extends UwpPage {
	private static UwpChangeReservationConfirmPage _instance = null;

	public static UwpChangeReservationConfirmPage getInstance() {
		if (null == _instance)
			_instance = new UwpChangeReservationConfirmPage();

		return _instance;
	}

	public UwpChangeReservationConfirmPage() {
	  	timeout = Browser.VERY_LONG_SLEEP;
	}

	private RegularExpression cardNumReg = new RegularExpression("^cardnum_[0-9]+", false);
	
	protected Property[] originalAndNewResTable(){
		return Property.concatPropertyArray(table(), ".id", "table1");
	}
	
	protected Property[] refundDetailsTable(){
		return Property.concatPropertyArray(table(), ".className","voucher items order");
	}
	
	protected Property[] minimumPayment(){
		return Property.concatPropertyArray(label(), ".for", new RegularExpression("paymentAmountChoice_MINIMUM_\\d+", false));
	}
	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.BUTTON",".id", "chkout",".text","Confirm"));
	}
	
	public boolean isMinimumPaymentExist(){
		return browser.checkHtmlObjectExists(minimumPayment());
	}
	
	public BigDecimal getMinimumPaymentAmount(){
		String content = browser.getObjectText(minimumPayment());
		String amount = RegularExpression.getMatches(content, "\\d+\\.\\d+")[0]; //2.50
		return new BigDecimal(amount);
	}
	
	public void clickConfirm() {
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.BUTTON",".id", "chkout",".text","Confirm"));
	}
	
	public boolean checkPaymentInfomationExist(){
		return browser.checkHtmlObjectExists(".className","content shop",".text", new RegularExpression("^Payment Information.*", false));
	}
	
	public void verifyPaymentInformationExisted(){
		if(!checkPaymentInfomationExist()){
			throw new ErrorOnPageException("Payment information DIV should be display.");
		}
		logger.info("Successfully verify Payment information DIV displays.");
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
	 * Fill in all payment info
	 */
	public void setupPayment(Payment pay) {
		if(pay==null){
			pay = new Payment("visa");
		}
		if(pay.isMinimumPayment){
			this.selectMinimumPayment();
		}else if(pay.noMinimumPayment){
			this.verifyNoMinimumPaymentField(); //Verify no minimum payment field displays
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
	 * Select the 'Minimum Payment' radio button
	 */
	public void selectMinimumPayment() {
		browser.selectRadioButton(".id", "paymentAmountChoice_MINIMUM_1");
	}
	
	public boolean checkNoMinimumPaymentField(){
		return !browser.checkHtmlObjectDisplayed(".id", new RegularExpression("paymentAmountChoice_MINIMUM_\\d+", false));
	}
	
	public void verifyNoMinimumPaymentField(){
		if(!this.checkNoMinimumPaymentField()){
			throw new ErrorOnPageException("Minimum payment field should not be displayed.");
		}
		logger.info("Successfully verify minimum payment field doesn't display.");
	}
	
	public IHtmlObject[] getRefundDetailsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".className","voucher items order");
		if(objs.length<1)
			throw new ItemNotFoundException("No balance to refund as voucher.");

		return objs;
	}
	
	public boolean checkRefundDetailsTableExisted(){
		return browser.checkHtmlObjectExists(refundDetailsTable());
	}
	
	public void verifyRefundDetailsTableExisted(boolean existed){
	    boolean actualResult = checkRefundDetailsTableExisted();
	    if(existed!=actualResult){
	    	throw new ErrorOnPageException("Failed to verify Refund Details table "+(existed?"exists":"doesn't exist"));
	    }
	    logger.info("Successfully verify Refund Details table "+(existed?"exists":"doesn't exist"));
	}
	
	/**
	 * Get refund detail info
	 */
	public RefundInfo getRefundDetailInfo() {
		IHtmlObject[] objs = getRefundDetailsTable();
        IHtmlTable table = (IHtmlTable)objs[0];
        RefundInfo refund = new RefundInfo();
		
        refund.amount = table.getCellValue(1, 0).replace("$", "").trim();
        refund.paymentMethod = table.getCellValue(1, 1).trim();
        refund.refundMethod = table.getCellValue(1, 2).trim();
		
		Browser.unregister(objs);
		return refund;
	}
	
	/**
	 * Verify refund detail info
	 * @param refundAmount
	 * @param paymentMethod
	 * @param refundMethod
	 */
	public void verifyRefundDetailInfo(BigDecimal refundAmount, String paymentMethod, String refundMethod){
		boolean result = true;
		RefundInfo refund = this.getRefundDetailInfo();
		
		result &= MiscFunctions.compareResult("Refund Amount", refundAmount, new BigDecimal(refund.amount));
		result &= MiscFunctions.compareResult("Payment Method", paymentMethod, refund.paymentMethod);
		result &= MiscFunctions.compareResult("Refund Method", refundMethod, refund.refundMethod);
		
		if(!result){
			throw new ErrorOnDataException("Not all the refund detail check points are passed in 'Change Reservation Details' page. Please check details info from previous logs.");
		}
		 
		logger.info("Successfully verify all refund detail related check points in 'Change Reservation Details' page.");
	}
	
	
	/**
	 * Get payment amount 
	 * @param divClassName
	 * @param divStartingText
	 * @return
	 */
	public BigDecimal getPaymentAmount(String divClassName, String divStartingText){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", divClassName, ".text", new RegularExpression(""+divStartingText+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "money");

		IHtmlObject[] objs =browser.getHtmlObject(Property.atList(p1, p2));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException(divStartingText+" money object can't be found.");
		}
		String paymentValue = StringUtil.EMPTY;
		if(objs[0].text().contains("$")){
			paymentValue = objs[0].text().split("\\$")[1].trim(); //replace("$", "").trim(); 
		}else paymentValue = objs[0].text().replace("$", "").trim(); 
		BigDecimal paymentAmount = BigDecimal.ZERO;
		if(paymentValue.contains("(")){ //$(31.20)
			paymentAmount = BigDecimal.ZERO.subtract(new BigDecimal(RegularExpression.getMatches(paymentValue, "\\d+\\.\\d+")[0]));
		}else
			paymentAmount  = new BigDecimal(paymentValue);

		Browser.unregister(objs);
		return paymentAmount;
	}
	
	public BigDecimal getChangeFeeAmount() {
		return this.getPaymentAmount("subtotal", "Change Fee");
	}
	
	public BigDecimal getPreviousFeesAmount() {
		return this.getPaymentAmount("subtotal", "Previous Fees");
	}
	
	public BigDecimal getUseFeeAmount() {
		return this.getPaymentAmount("subtotal", "Use Fee");
	}
	
	public BigDecimal getNewCampingFeeAmount() {
		return this.getPaymentAmount("subtotal", "New Camping Fee");
	}
	
	public BigDecimal getNewTotalAmount() {
		return this.getPaymentAmount("subtotal", "New Total");
	}
	
	public BigDecimal getPreviousPaymentAmount() {
		return this.getPaymentAmount("subtotal", "Previous Payment");
	}
	
	public BigDecimal getBalanceAmount() {
		return this.getPaymentAmount("cartTotal", "Balance");
	}
	
	public BigDecimal getDiscAmount(String custTypeOrPassDiscountName) {
		return this.getPaymentAmount("subtotal", custTypeOrPassDiscountName);
	}
	
	public void verifyDiscAmount(String custTypeOrPassDiscountName, BigDecimal expectedValue){
		BigDecimal actualValue = getDiscAmount(custTypeOrPassDiscountName);
		if(expectedValue.compareTo(actualValue)!=0){
			throw new ErrorOnPageException("CustTypeOrPassDiscount:"+custTypeOrPassDiscountName+" Amount is wrong.", expectedValue, actualValue);
		}
		logger.info("Successfully verify CustTypeOrPassDiscount:"+custTypeOrPassDiscountName+" Amount is "+expectedValue+".");
	}
	
	/**
	 * For "Previous Fee", "Use Fee" and Discount, such as "Discount With Refund"
	 * @param previousFeeAmount
	 * @param useFeeAmount
	 * @param discName
	 * @param discAmount
	 */
	public void verifyNewResPayment(BigDecimal previousFeeAmount, BigDecimal useFeeAmount, String discName, BigDecimal discAmount){
		boolean result = true;
		result &= MiscFunctions.compareResult("Previous Fees", previousFeeAmount, getPreviousFeesAmount());
		result &= MiscFunctions.compareResult("Use Fee", useFeeAmount, getUseFeeAmount());
		result &= MiscFunctions.compareResult("Discount", discAmount, getDiscAmount(discName));
		if(!result){
			throw new ErrorOnPageException("New Reservation Payment are wrong! Please check the details from previous logs.");
		}
		logger.info("Successfully verify New Reservation Payment info.");
	}
	
	/**
	 * For "New Total", "Previous Payment" and "Balance"
	 * @param newTotal
	 * @param previousPayment
	 * @param balance
	 */
	public void verifyFinallyTotalPayment(BigDecimal newTotal, BigDecimal previousPayment, BigDecimal balance){
		boolean result = true;
		result &= MiscFunctions.compareResult("New Total", newTotal, this.getNewTotalAmount());
		result &= MiscFunctions.compareResult("Previous Payment", previousPayment, this.getPreviousPaymentAmount());
		result &= MiscFunctions.compareResult("Balance", balance, this.getBalanceAmount());
		if(!result){
			throw new ErrorOnPageException("Not all the payment info are correct in 'Change Reservation Details' page. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all payment info are correct in 'Change Reservation Details' page.");
	}
	
	public IHtmlObject[] getOriginalAndNewResTable(){
		IHtmlObject[] objs = browser.getTableTestObject(originalAndNewResTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Original and new reservation objects can't be found.");
		}
		
		return objs;
	}
	
	public List<String> getResInfo(boolean isNewRes){
		IHtmlObject[] objs = getOriginalAndNewResTable();
		ITable table = (ITable)objs[0];
		List<String> value = new ArrayList<String>();
		int row = 0;

		if(isNewRes){
			row=5;
		}else row=2;
		value.add(table.getCellValue(row, 1));
		value.add(table.getCellValue(row, 2));

		return value;
	}
	
	public void verifyResInfo(List<String> expectedResults, boolean isNewRes){
		List<String> actualResults = getResInfo(isNewRes);
		if(!actualResults.toString().equals(expectedResults.toString())){
			throw new ErrorOnPageException(isNewRes?"New":"Original"+ "reservation info is wrong.",expectedResults.toString(), actualResults.toString());
		}
		logger.info("Successfully verify "+(isNewRes?"New":"Original")+ "reservation info is right.\n"+expectedResults.toString());
	}
}
