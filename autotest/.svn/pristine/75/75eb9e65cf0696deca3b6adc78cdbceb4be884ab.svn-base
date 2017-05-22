
package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
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
 * @author jchen
 */
public class UwpCancellationConfirmationPage extends UwpPage {
	private static UwpCancellationConfirmationPage _instance = null;

	public static UwpCancellationConfirmationPage getInstance() {
		if (null == _instance)
			_instance = new UwpCancellationConfirmationPage();

		return _instance;
	}

	public UwpCancellationConfirmationPage() {
		this.timeout=Browser.LONG_SLEEP;
	}

	private static String TAB_ITEM = "Item";
	private static String TAB_DETAILS = "Details";
	private static String TAB_TRANSACTIONDETAILS = "Transaction Details";
	
	protected Property[] voucherItemsOrder(){
		return Property.toPropertyArray(".className","voucher items order");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "shophdr",".text",new RegularExpression("^Cancellation Completed.*",false));
	}
	
	/**
	 * Verify whether or not this is Cancellation confirmation page, because there will be 3 pages used
	 * in 1 keywords and have no unique page mark to identify.
	 * @return true - cancellation confirm page, false - not
	 */
	public boolean isCancelConfirmPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String str = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);

		if (str.matches(".*Cancellation Completed.*"))
			return true;
		else
			return false;
	}
	
	/**
	 * verify whether the cancellation completed
	 * @return true - complete; false - incomplete
	 */
	public boolean cancelCompleted() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "shophdr");
		String str = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);

		if (str.matches(".*Cancellation Completed.*"))
			return true;
		else
			return false;
	}
	
	public IHtmlObject[] getVoucherDetailsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(voucherItemsOrder());
		if(objs.length<1)
			throw new ItemNotFoundException("No balance to refund as voucher.");

		return objs;
	}
	
	public void verifyReFundDetailsTableExist(boolean expectedResult){
		boolean actualValue = browser.checkHtmlObjectExists(voucherItemsOrder());
		if(expectedResult!=actualValue){
			throw new ErrorOnPageException("Refund table should "+(expectedResult?"":"not ")+" exist.");
		}
	}
	
	/**
	 * Retrieve the voucher id and amount from cancellation confirmation page.
	 * @return - voucher data
	 */
	public Voucher getVoucherDetailsInfo() {
	  IHtmlObject[] objs = getVoucherDetailsTable();
	  	
	  	Voucher vc = new Voucher();
	  	String cell=((IHtmlTable)objs[0]).getCellValue(1, 2);
	  	
	  	if(cell.indexOf("Voucher")==-1)
	  	  	throw new ItemNotFoundException("Refund method is not Voucher.");
	  	
	  	vc.voucherId = cell.split(" ")[0].replaceAll("Voucher\\(|\\)","");//voucher id
	  	vc.amount = cell.split("\\$")[1].trim(); // voucher amount
	  	
	  	Browser.unregister(objs);
	  	return vc;
	}
	
	/**
	 * Get refund detail info
	 */
	public RefundInfo getRefundDetailInfo() {
		IHtmlObject[] objs = getVoucherDetailsTable();
        IHtmlTable table = (IHtmlTable)objs[0];
        RefundInfo refund = new RefundInfo();
		
        refund.amount = table.getCellValue(1, 0).replace("$", "").trim();
        refund.paymentMethod = table.getCellValue(1, 1).trim();
        refund.refundMethod = table.getCellValue(1, 2).trim().split("\\(")[0];
		
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
	
	/**
	 * Wait for page to load when cancel reservation failed. */
	public void cancelFailedWaitExists() {
	  	browser.searchObjectWaitExists(".class","Html.DIV",".id","contentArea");
	}
	
	protected Property[] errMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "msg error");
	}
	/**
	 * Retrieve the error message when cancel reservation failed.
	 * @return - error message
	 */
	public String getErrorMessage() {
	  	return browser.getObjectText(errMsgDiv());
	}
	
	/**Go to current reservations page*/
	public void gotoCurrentReservationsPg(){
		browser.clickGuiObject(".id", "reservationLink");
	}
	
	/**
	 * Get cancellation completed table objects
	 * @return
	 */
	public IHtmlObject[] getCancellationCompletedTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cancellation completed table can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get cancellation completed table content
	 * @return
	 */
	public String getCancellatonCompletedTableContent(){
		IHtmlObject[] objs = this.getCancellationCompletedTable(); 
		String content = objs[0].text();
		
		Browser.unregister(objs);
		return content;
	}
	
	public String[] getCancellatonCompletedTableDetailsContent(){
	     IHtmlTable table = (IHtmlTable)getCancellationCompletedTable()[0];
	     String[] colValues = new String[table.columnCount()];
	     
	     for(int i=0; i<table.columnCount(); i++){
	    	 colValues[i] = table.getCellValue(1, i);
	     }
	     Browser.unregister(table);
	     return colValues;
	}
	
	public boolean isTransactionDetailsElementExisting(String amountTitle){
		String cancelResTableContent = this.getCancellatonCompletedTableContent();
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
		String cancelResTableContent = this.getCancellatonCompletedTableContent();
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
	
	/**
	 * Verify balance
	 * @param expectedBalance
	 */
	public void verifyBalance(BigDecimal expectedBalance){
		BigDecimal actualBalance = this.getBalanceAmount();
		if(expectedBalance.compareTo(actualBalance)!=0){
			throw new ErrorOnDataException("Balance is wrong!", expectedBalance, actualBalance);
		}
		logger.info("Successfully verify balance:"+expectedBalance);
	}
	
	public List<BigDecimal> getTransactionDetailsInfo(){
		List<BigDecimal> transactionDetailsInfo = new ArrayList<BigDecimal>();
		transactionDetailsInfo.add(this.getPreviousPaymentAmount());
		transactionDetailsInfo.add(this.getCancellationServiceChargeAmount());
		if(this.isLateCancellationFeeExisting()){
			transactionDetailsInfo.add(this.getLateCancellationFeeAmount());
		}
		transactionDetailsInfo.add(this.getBalanceAmount());
		
		return transactionDetailsInfo;
	}
	
	public void verifyTransactionDetailsInfo(BigDecimal previousPaymentAmount, BigDecimal cancellationServiceCharge, BigDecimal lateCancellationFeeAmount, BigDecimal balanceAmount){
		boolean result = true;
		result &= MiscFunctions.compareResult("Previous Payment", previousPaymentAmount, this.getPreviousPaymentAmount());
		result &= MiscFunctions.compareResult("Cancellation Service Charge", cancellationServiceCharge, this.getCancellationServiceChargeAmount());
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
	
	
	public void verifyCancelResTableInfo(String[] expectedResults){
		String[] actualResults = getCancellatonCompletedTableDetailsContent();
		boolean passed = MiscFunctions.compareResult("Result length", expectedResults.length, actualResults.length);
		passed &= MiscFunctions.compareResult(TAB_ITEM, expectedResults[0], actualResults[0]);
		passed &= MiscFunctions.compareResult(TAB_DETAILS, expectedResults[1], actualResults[1]);
		passed &= MiscFunctions.compareResult(TAB_TRANSACTIONDETAILS, expectedResults[2], actualResults[2]);
		if(!passed){
			throw new ErrorOnPageException();
		}
	}
	
	public void verifyTransactionDetails(String expectedResult){
		String actualResult = getCancellatonCompletedTableDetailsContent()[2];
		if(!MiscFunctions.compareResult(TAB_TRANSACTIONDETAILS, expectedResult, actualResult)){
			throw new ErrorOnPageException(TAB_TRANSACTIONDETAILS+" is wrong!");
		}
	}
}
