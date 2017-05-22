
package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This is "Cancel Permit Reservation" page. It displays "Proceed With Cancellation" button when clicking "Cancel Reservation" link in "Permit Reservation Details" page.
 * After clicking "Proceed With Cancellation" button, it will go to below 2 conditions
 * 1. Go to "Cancellation Completed!" page without error message;
 * 2. Still in "Cancel Permit Reservation" page with error message, such as "This Reservation cannot be cancelled. Please refer to the cancellation policy information."
 * @author jchen
 */
public class UwpCancellationPage extends UwpPage {
	private static UwpCancellationPage _instance = null;

	public static UwpCancellationPage getInstance() {
		if (null == _instance)
			_instance = new UwpCancellationPage();

		return _instance;
	}

	public UwpCancellationPage() {
	}
	
    //Cancel Permit Reservation Permit #: 6-1039433
	public boolean exists() {

		return browser.checkHtmlObjectExists(".id", "shophdr",
				".text",new RegularExpression("^Cancel.*#: ?M?\\d-\\d+.*",false)); //Sara[10/23/2013], for slip order, text as "Cancel Reservation Reservation #:M2-195"
//		return browser.checkHtmlObjectExists(".type", "submit", 
//		    ".text", "Proceed With Cancellation");
	}
	
	private static String TAB_ITEM = "Item";
	private static String TAB_DETAILS = "Details";
	private static String TAB_TRANSACTIONDETAILS = "Transaction Details";
	
	public Property[] getVoucherItemsOrderProp(){
		return Property.toPropertyArray(".className","voucher items order");
	}
	
	public Property[] getRefundTableProp(){
		return Property.toPropertyArray(".id", "refund table");
	}
	
	/**
	 *click on 'Proceed With Cancellation' button
	 */
	public void clickProceedWithCancellation() {
		browser.clickGuiObject(".type", "submit", 
			    ".text", "Proceed With Cancellation");
	}
	
	/**
	 * Verify whether or not the cancellation need to be paid the balance.
	 * @return true - need to pay; false - need not
	 */
	public boolean isRequiredPayBalance() {
	  	boolean toReturn = false;
	  	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id","suboptions"); //payment sub
	  	if(objs.length>0)
	  	  	toReturn = true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	public void selectCardType(String type) {
		browser.selectDropdownList(".id","cardtype", type, true);
	}
	
	public void setCardNumber(String number) {
		browser.setTextField(".name","cardNumber", number,true);
	}
	
	public void setExpireMonth(String month) {
		browser.setTextField(".name","expMonth",month);
	}
	
	public void setExpireYear(String year) {
		browser.setTextField(".name","expYear",year);
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(".name","firstName",fName);
	}
	
	public void setLastName(String lName) {
		browser.setTextField(".name","lastName",lName);
	}
	
	public void setupPayment(Payment pay) {
		if(pay!=null) {
			this.selectCardType(pay.payType);
			this.setCardNumber(pay.creditCardNumber);
			this.setExpireMonth(pay.expiryMon);
			this.setExpireYear(pay.expiryYear);
			this.setFirstName(pay.fName);
			this.setLastName(pay.lName);
		}
	}
	
	/**
	 * Get error message
	 * @return
	 */
	public String getErrorMes(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg error");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("'Error Message' object can't be found.");
		}
		
		String errorMes = objs[0].text().trim();
		logger.info("Get error message:"+errorMes);
		
		Browser.unregister(objs);
		return errorMes;
	}
	
	/**
	 * Verify error message
	 * @param errorMes_Expected
	 */
	public void verifyErrorMes(String errorMes_Expected){
		String errorMes_Actual = this.getErrorMes();
		if(!MiscFunctions.compareResult("Error Message", errorMes_Expected, errorMes_Actual)){
			throw new ErrorOnPageException("Error Messag is wrong!", errorMes_Expected, errorMes_Actual);
		}
		logger.info("Successfully verify Error Message:"+errorMes_Expected);
	}
	
	/**
	 * Get cancel reservation table objects
	 * @return
	 */
	public IHtmlObject[] getCancelResTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cancel reservation table can't be found.");
		}
		
		return objs;
	}
	
	public String getCancelResTableContent(){
		return browser.getObjectText(".id", "shoppingitems");
	}
	
	public String[] getCancelResTableTabContent(){
	     IHtmlTable table = (IHtmlTable)getCancelResTable()[0];
	     String[] colValues = new String[table.columnCount()];
	     
	     for(int i=0; i<table.columnCount(); i++){
	    	 colValues[i] = table.getCellValue(1, i);
	     }
	     Browser.unregister(table);
	     return colValues;
	}
	
	public void verifyCancelResTableInfo(String[] expectedResults){
		String[] actualResults = getCancelResTableTabContent();
		boolean passed = MiscFunctions.compareResult("Result length", expectedResults.length, actualResults.length);
		passed &= MiscFunctions.compareResult(TAB_ITEM, expectedResults[0], actualResults[0]);
		passed &= MiscFunctions.compareResult(TAB_DETAILS, expectedResults[1], actualResults[1]);
		passed &= MiscFunctions.compareResult(TAB_TRANSACTIONDETAILS, expectedResults[2], actualResults[2]);
		if(!passed){
			throw new ErrorOnPageException();
		}
	}
	
	public void verifyCancelResTableTabContent(String tableTab, String expectedValue){
		String[] results = getCancelResTableTabContent();
		String actualValue = "";
		if(tableTab.equals(TAB_ITEM)){
			actualValue = results[0];
		}else if(tableTab.equals(TAB_DETAILS)){
			actualValue = results[1];
		}else if(tableTab.equals(TAB_TRANSACTIONDETAILS)){
			actualValue = results[2];
		}else throw new ErrorOnDataException("Can't find matched table tab");
		
		if(!MiscFunctions.compareResult(tableTab + " content", expectedValue, actualValue)){
			throw new ErrorOnPageException();
		}
	}
	
	public void verifyItemContent(String expectedValue){
		verifyCancelResTableTabContent(TAB_ITEM, expectedValue);
	}
	public void verifyDetailsContent(String expectedValue){
		verifyCancelResTableTabContent(TAB_DETAILS, expectedValue);
	}
	public void vetifyTransDetailsContent(String expectedValue){
		verifyCancelResTableTabContent(TAB_TRANSACTIONDETAILS, expectedValue);
	}

	
	/**
	 * Get balance
	 * @return
	 */
	public BigDecimal getBalance(){
		String cancelResTableContent = getCancelResTableContent();
		BigDecimal balance = BigDecimal.ZERO;
		
		String regx = "\\(?\\d+:\\d+\\)?"; //$33.00, $(33.00)
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(cancelResTableContent.split("Balance")[1]);
		while(m.find()){
			String s = m.group();
			if(s.contains("(")){
				balance =BigDecimal.ZERO.subtract( new BigDecimal(s.replace("(", "\\(").replace(")", "\\)")));
			}else{
				balance = new BigDecimal(s);
			}
			break;
		}
		
		return balance;
	}
	
	/**
	 * Verify balance
	 * @param expectedBalance
	 */
	public void verifyBalance(BigDecimal expectedBalance){
		BigDecimal actualBalance = this.getBalance();
		if(expectedBalance.compareTo(actualBalance)!=0){
			throw new ErrorOnDataException("Balance is wrong!", expectedBalance, actualBalance);
		}
		logger.info("Successfully verify balance:"+expectedBalance);
	}
	
	public IHtmlObject[] getRefundDetailsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(getVoucherItemsOrderProp());
		if(objs.length<1)
			throw new ItemNotFoundException("No balance to refund as voucher.");

		return objs;
	}
	
	public void verifyReFundDetailsTableExist(boolean expectedResult){
		boolean actualValue = browser.checkHtmlObjectExists(getRefundTableProp());
		if(!MiscFunctions.compareResult("Refund table exists or not", expectedResult, actualValue)){
			throw new ErrorOnPageException();
		}
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
			throw new ErrorOnDataException("Not all the refund detail check points are passed. Please check details info from previous logs.");
		}
		 
		logger.info("Successfully verify all refund detail related check points.");
	}
	
	public boolean isTransactionDetailsElementExisting(String amountTitle){
		String cancelResTableContent = getCancelResTableContent();
		if(cancelResTableContent.contains(amountTitle)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Get the amount in 'Transaction Details' section
	 * @param amountTitle: Previous Payment, Cancellation Service Charge, Balance
	 * @return
	 */
	public BigDecimal getTransactionDetailsAmount(String amountTitle){
		String cancelResTableContent = getCancelResTableContent();;
		BigDecimal amount = BigDecimal.ZERO;
		
		String regx = "\\(?\\d+\\.\\d+\\)?"; //$33.00, $(33.00)
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(cancelResTableContent.split(amountTitle)[1]);
		while(m.find()){
			String s = m.group();
			if(s.contains("(")){
				amount =BigDecimal.ZERO.subtract(new BigDecimal(s.replace("(", "").replace(")", "")));
			}else{
				amount = new BigDecimal(s);
			}
			break;
		}
		
		return amount;
	}
	
	public BigDecimal getPreviousPaymentAmount(){
		return getTransactionDetailsAmount("Previous Payment");
	}
	public boolean isCancellationServiceChargeExisting(){
		return this.isTransactionDetailsElementExisting("Cancellation Service Charge");
	}
	public BigDecimal getCancellationServiceChargeAmount(){
		return getTransactionDetailsAmount("Cancellation Service Charge");
	}
	public boolean isLateCancellationFeeExisting(){
		return this.isTransactionDetailsElementExisting("Late Cancellation Fee");
	}
	public BigDecimal getLateCancellationFeeAmount(){
		return getTransactionDetailsAmount("Late Cancellation Fee");
	}
	public BigDecimal getBalanceAmount(){
		return getTransactionDetailsAmount("Balance");
	}
	
	public void verifyTransactionDetailsInfo(BigDecimal previousPaymentAmount, BigDecimal cancellationServiceCharge, BigDecimal lateCancellationFeeAmount, BigDecimal balanceAmount){
		boolean result = true;
		result &= MiscFunctions.compareResult("Previous Payment", previousPaymentAmount, this.getPreviousPaymentAmount());
		if(isCancellationServiceChargeExisting() && cancellationServiceCharge.compareTo(BigDecimal.ZERO)!=0){
			result &= MiscFunctions.compareResult("Cancellation Service Charge", cancellationServiceCharge, this.getCancellationServiceChargeAmount());
		}
		result &= MiscFunctions.compareResult("Balance", balanceAmount, this.getBalanceAmount());
		if(this.isLateCancellationFeeExisting() && lateCancellationFeeAmount.compareTo(BigDecimal.ZERO)!=0){
			result &= MiscFunctions.compareResult("Late Cancellation Fee", lateCancellationFeeAmount, this.getLateCancellationFeeAmount());
		}
		
		if(!result){
			throw new ErrorOnPageException("Not all the payment info are correct in 'Cancellation Completed' page. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all payment info are correct in 'Cancellation Completed' page.");
	}
	
	public void verifyTransactionDetailsInfo(BigDecimal previousPaymentAmount, BigDecimal cancellationServiceCharge, BigDecimal balanceAmount){
		this.verifyTransactionDetailsInfo(previousPaymentAmount, cancellationServiceCharge, BigDecimal.ZERO, balanceAmount);
	}
	
	public void verifyTransactionDetailsInfo(BigDecimal previousPaymentAmount, BigDecimal balanceAmount){
		this.verifyTransactionDetailsInfo(previousPaymentAmount, BigDecimal.ZERO, BigDecimal.ZERO, balanceAmount);
	}
}
