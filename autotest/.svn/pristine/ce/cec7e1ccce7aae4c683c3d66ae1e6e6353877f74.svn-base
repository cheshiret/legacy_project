package com.activenetwork.qa.awo.pages.web.bw;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class BwChangeCompletePage extends UwpPage {

	private RegularExpression pageMark = new RegularExpression(
			"[Cancellation|Change] Completed! Permit #\\s*: [0-9]-[0-9]+", false);

	private static BwChangeCompletePage _instance = null;

	public static BwChangeCompletePage getInstance() {
		if (null == _instance)
			_instance = new BwChangeCompletePage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", pageMark);
	}
	
	public Property[] getChangeCompletedTableProp(){
		return Property.toPropertyArray(".id", "shoppingitems");
	}
	
	public Property[] getPrintThisPageLinkProp(){
		return Property.toPropertyArray(".id", "removethis", ".text", "Print This Page");
	}

	public Property[] getContinueToCurrentReservationsLinkProp(){
		return Property.toPropertyArray(".id", "removethis", ".text", "Continue to Current Reservations");
	}
	
	/**
	 * Vrify fee amount on page.
	 * @throws ItemNotFoundException - fee amount not calculated right
	 */
	public void verifyFees() throws ItemNotFoundException {
		RegularExpression regex = new RegularExpression("Item Details Transaction Details .*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", regex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		if (!(Math.abs(Double.parseDouble(fees[0])-Double.parseDouble(fees[1]) + Double.parseDouble(fees[2]))<.0000001))
			throw new ItemNotFoundException("Fee amount was not calculated right");
	}
	
	public String getOrderNum() {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".className", "ctr")).split("# :")[1].trim();
	}
	
	public void clickContinueToCurrentRes(){
		browser.clickGuiObject(getContinueToCurrentReservationsLinkProp());
	}
	
	public void clickPrintThisPage(){
		browser.clickGuiObject(getPrintThisPageLinkProp());
	}
	
	/**
	 * Check "Refund Detail" info exist
	 * @return
	 */
	public boolean checkRefundTableExist(){
		return browser.checkHtmlObjectExists(".id", "refund table");
	}
	
	/**
	 * Verify whether refund table exist or not
	 * @param flag  --true: exist
	 *              --false: doesn't exist
	 */
	public void verifyRefundTableExist(boolean flag){
		boolean actualExisted = this.checkRefundTableExist();
		if(actualExisted!=flag){
			throw new ErrorOnDataException("Refund table should "+(flag?"":"not")+" be existed.");
		}else{
			logger.info("Successfully verify Refund table "+(flag?"":"doesn't ")+"exist.");
		}
	}
	
	/**
	 * 1. Change Completed
	 * 2. Permit Reservation Confirmation Completed
	 * @param pageTitle
	 */
	public void verifyPageTitle(String pageTitle){
		RegularExpression regx = new RegularExpression(
				"^"+pageTitle+"! Permit #(\\s?): [0-9]-[0-9]+", false);
		boolean existed = browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", regx);
		if(!existed){
			throw new ErrorOnDataException("Page Title:"+pageTitle+" should be existed.");
		}
	}
	
	public boolean checkShoppingItemsExist(){
		RegularExpression regx = new RegularExpression("^Item Details Transaction Details.*", false);
		return browser.checkHtmlObjectExists(".id", "shoppingitems", ".text", regx);
	}
	
	public void verifyShoppingItemsExist(boolean flag){
		boolean actualExist = this.checkShoppingItemsExist();
		if(actualExist!=flag){
			throw new ErrorOnDataException("Shopping Items should "+(flag?"":"not")+" be exist.");
		}
	}
	
	/**
	 * Get cancellation completed table objects
	 * @return
	 */
	public IHtmlObject[] getCancellationCompletedTable(){
		IHtmlObject[] objs = browser.getTableTestObject(getChangeCompletedTableProp());
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Change completed table can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get change completed table content
	 * @return
	 */
	public String getChangeCompletedTableContent(){
		IHtmlObject[] objs = this.getCancellationCompletedTable(); 
		String content = this.getCancellationCompletedTable()[0].text();
		
		Browser.unregister(objs);
		return content;
	}
	
	public boolean isTransactionDetailsElementExisting(String amountTitle){
		String cancelResTableContent = this.getChangeCompletedTableContent();
		if(cancelResTableContent.contains(amountTitle)){
			return true;
		}else{
			return false;
		}
	}
	
	public BigDecimal getTransactionDetailsAmount(String amountTitle){
		String cancelResTableContent = this.getChangeCompletedTableContent();
		BigDecimal amount = BigDecimal.ZERO;
		
		String regx = "\\(?\\d+\\.\\d+\\)?"; //$33.00, $(33.00)
		Matcher m = Pattern.compile(regx).matcher(cancelResTableContent.split(amountTitle)[1]);
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
	
	public BigDecimal getReservationTotalAmount(){
		return getTransactionDetailsAmount("Reservation Total");
	}
	public BigDecimal getPaymentTotalAmount(){
		return getTransactionDetailsAmount("Payment Total");
	}
	public BigDecimal getBalanceAmount(){
		return getTransactionDetailsAmount("Balance");
	}
	
	public void verifyTransactionDetailsAmount(String amountTitle, BigDecimal expectedAmount){
		BigDecimal actualAmount = getTransactionDetailsAmount(amountTitle);
		if(actualAmount.compareTo(expectedAmount)!=0){
			throw new ErrorOnPageException(amountTitle+" amount is wrong!", expectedAmount, actualAmount);
		}
		logger.info(amountTitle+" amount is right as "+actualAmount);
	}
	
	public void verifyReservationTotalAmount(BigDecimal expectedAmount){
		verifyTransactionDetailsAmount("Reservation Total", expectedAmount);
	}
	public void verifyPaymentTotalAmount(BigDecimal expectedAmount){
		verifyTransactionDetailsAmount("Payment Total", expectedAmount);
	}
	public void verifyBalanceAmount(BigDecimal expectedAmount){
		verifyTransactionDetailsAmount("Balance", expectedAmount);
	}
	
	public void verifyTransactionDetailsInfo(BigDecimal resTotal, BigDecimal paymentTotal, BigDecimal balance){
		boolean result = MiscFunctions.compareResult("Reservation Total", resTotal, getReservationTotalAmount());
		result &= MiscFunctions.compareResult("Payment Total", paymentTotal, getPaymentTotalAmount());
		result &= MiscFunctions.compareResult("Balance", balance, getBalanceAmount());
		if(!result) throw new ErrorOnPageException("Failed to verify all transaction details info in Change Completed page.");
	}
}
