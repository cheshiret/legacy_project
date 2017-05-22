
package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.core.internal.dtree.ObjectNotFoundException;

import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpReservationDetailsPage extends UwpPage {
	private static UwpReservationDetailsPage _instance = null;

	public static UwpReservationDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpReservationDetailsPage();

		return _instance;
	}

	public UwpReservationDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "pagetitle",".text",new RegularExpression(".*Reservation Details",false));
	}

	/**
	 * Cancel a reservation.
	 */
	public void cancelReservation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Reservation",true);
	}

	public boolean cancelLinkExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel Reservation");
	}

	public boolean changeLinkExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Reservation");
	}
	
	/**
	 * Click change reservation link
	 */
	public void clickChangeReservationLink() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".id", "changethis");
		if(objs.length<1) {
			objs=browser.getHtmlObject(".class", "Html.A", ".text", "Change Reservation");
		}

		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void verifyNoChangeResLink(){
		boolean result = browser.checkHtmlObjectExists(".class", "Html.A", ".id", "changethis");
		if(result){
			throw new ErrorOnPageException("Failed to verify no Change Reservation link");
		}
		logger.info("Successfully verify no Change Reservation link.");
	}
	
	/**
	 * Click on request confirmation letter link.
	 */
	public void clickOnRequestConfirmLetter() {
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Request Confirmation Letter");
	}

	/**
	 * Click on Pay Balance link.
	 */
	public void clickOnPayBalance() {
		browser.clickGuiObject(".id", "payb");
	}

	/**
	 * Verify the message after clicking Request Confirm Letter link.
	 * @param email - current user's account
	 */
	public void verifyConfirmLetterMsg(String email) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contentArea");

		String content = objs[0].getProperty(".text");

		if (!content.startsWith("Confirmation Letter will be sent to " + email
				+ " within the next 15 minutes.")
				&& !content
				.startsWith("Confirmation Letter may take up to 15 minutes to deliver. Please check your inbox and try again later."))
			throw new ErrorOnPageException(
			"Confirmation letter message display error!");

		Browser.unregister(objs);
	}

	/**
	 * Retrieve site details. 
	 * @return - reservation no, arrival date, state, sitecode, park name
	 */
	public String getSiteDetails() {
		String toReturn = null;
		String str = browser.getTextFieldValue(".id", "sitedetails0");
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(UwpSchConstants.searchSiteDetails);
		Matcher m = p.matcher(str);

		if (m.matches()) { //return m.group(1).trim()+":"+m.group(2).trim() + ":"+m.group(3).trim()+ ":"+m.group(4).trim() + ":"+m.group(5).trim();
			String temp = m.group(5).trim();
			if (temp.indexOf(",") != -1) {
				temp = temp.substring(0, temp.indexOf(","));
			}

			toReturn = m.group(1).trim() + ":" + m.group(2).trim() + ":"
			+ m.group(3).trim() + ":" + temp;
		}

		java.util.regex.Pattern p1 = java.util.regex.Pattern
		.compile(UwpSchConstants.searchNonCheckInSiteDetails);
		Matcher m1 = p1.matcher(str);
		if (m1.matches()) { //return m.group(1).trim()+":"+m.group(2).trim() + ":"+m.group(3).trim()+ ":"+m.group(4).trim() + ":"+m.group(5).trim();
			String temp = m1.group(5).trim();
			if (temp.indexOf(",") != -1) {
				temp = temp.substring(0, temp.indexOf(","));
			}

			toReturn = m1.group(1).trim() + ":" + m1.group(2).trim() + ":"
			+ m1.group(3).trim() + ":" + temp;
		}

		return toReturn;
	}

	/**
	 * Retrieve order details info.
	 * @return - order details info
	 */
	public String getOrddetails() {
		String toReturn = null;

		String str = browser.getTextFieldValue(".id", "sitedetail");
		java.util.regex.Pattern p = java.util.regex.Pattern
		.compile(UwpSchConstants.searchOrderDetails);
		Matcher m = p.matcher(str);

		if (m.matches()) {
			toReturn = m.group(1).trim() + ":" + m.group(2).trim() + ":"
			+ m.group(3).trim() + ":" + m.group(4).trim();

			return toReturn;
		}
		p = java.util.regex.Pattern
		.compile(UwpSchConstants.searchOrderDetailsNone);
		m = p.matcher(str);

		if (m.matches()) {
			toReturn = m.group(1).trim() + ":" + m.group(2).trim() + ":0:"
			+ m.group(3).trim();

			return toReturn;
		}

		return null;
	}

	/**
	 * Retrieve the order total amount.
	 * @return - total
	 */
	public String getTotal() {
		String str = browser.getTextFieldValue(".id", "sitedetail3");
		java.util.regex.Pattern p = java.util.regex.Pattern
		.compile(UwpSchConstants.searchTotal);
		Matcher m = p.matcher(str);

		if (m.matches()) {
			String returnStr = m.group(1).trim();

			return returnStr;
		}
		return null;
	}

	public String getOrderStatus(){
		RegularExpression pattern = new RegularExpression("^Status:.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LI", ".text", pattern);
		String valueString = objs[0].text().split("Status:")[1].trim();
		
		Browser.unregister(objs);
		return valueString;
	}

	
	public void verifyPermitOrderStatus(String permitResStatus){
		String actualOrderStatusString = this.getOrderStatus();
		if(!permitResStatus.equals(actualOrderStatusString)){
			throw new ErrorOnDataException("The order status is incorrect. " +
					"Actual one:"+actualOrderStatusString+", Expect one:"+permitResStatus);
		}
	}
	
	public IHtmlObject[] getResDetailsLiElementObjs(String elementLabel){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LI", ".text", new RegularExpression("^"+elementLabel+".*", false));
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Reservation details LI element objects with lable:"+elementLabel+" can't be found.");
		}
		return objs;
	}
	
	public String getResDetailsElementValue(String elementLabel){
		IHtmlObject[] objs = this.getResDetailsLiElementObjs(elementLabel);
		String elementValue = objs[0].text().split(elementLabel+" ?:")[1].replace("$", "").trim(); //Balance : $0.00
		if(elementValue.contains("(")){ //$(31.20)
			elementValue = "-"+RegularExpression.getMatches(elementValue, "\\d+\\.\\d+")[0];
		}
		
		Browser.unregister(objs);
		return elementValue;
	}
	
	public String getArrivalDate(){
		return this.getResDetailsElementValue("Arrival Date");
	}
	
	public String getDepartureDate(){
		return this.getResDetailsElementValue("Departure Date");
	}
	
	public String getSiteNo(){
		return this.getResDetailsElementValue("Site, Loop").split(",")[0].trim();
	}
	
	public String getSiteLoop(){
		return this.getResDetailsElementValue("Site, Loop").split(",")[1].trim();
	}
	
    public BigDecimal getCampingFeeAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Camping Fee"));
    }
    
    public BigDecimal getResFeeAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Reservation Fee"));
    }
    
    public BigDecimal getTaxesAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Taxes"));
    }
    
    public BigDecimal getTotalAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Total"));
    }
    
    public BigDecimal getTotalPaymentAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Total Payment"));
    }
    
	public String getBalanceDue(){ //\ufffdBalance Due: $15.45 Pay Balance
		IHtmlObject[] objs = this.getResDetailsLiElementObjs("Balance");
		String elementValue = objs[0].text().split("Balance Due"+":")[1].replace("$", "").trim().replace("Pay Balance", "").trim();
		Browser.unregister(objs);
		return elementValue;
	}
	
	public String getBalance(){
		return this.getResDetailsElementValue("Balance");
	}
	
    public BigDecimal getCancellationPenaltyeAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Cancellation Penalty"));
    }
    
    public BigDecimal getAmountPaidAmount(){
    	return new BigDecimal(this.getResDetailsElementValue("Amount Paid"));
    }
    
	/**
	 * Verify balance amount
	 * @param expectedBalance
	 */
	public void verifyBalanceAmount(BigDecimal expectedBalance){
		BigDecimal actualBalance = new BigDecimal(this.getBalance());
		if(expectedBalance.compareTo(actualBalance)!=0){
			throw new ErrorOnDataException("Balance is wrong!", expectedBalance, actualBalance);
		}
		logger.info("Successfully verify balance:"+expectedBalance);
	}
	
	public String getTotalPayment(){
		return this.getResDetailsElementValue("Total Payment");
	}
	
	public List<BigDecimal> getPaymentDetailsInfo(){
		List<BigDecimal> paymentDetailsInfo = new ArrayList<BigDecimal>();
		paymentDetailsInfo.add(this.getCampingFeeAmount()); //Camping Fee
		paymentDetailsInfo.add(this.getResFeeAmount());//Reservation Fee
		paymentDetailsInfo.add(this.getTaxesAmount());//Taxes
		paymentDetailsInfo.add(this.getTotalAmount());//Total
		paymentDetailsInfo.add(this.getTotalPaymentAmount());//Total Payment
		paymentDetailsInfo.add(new BigDecimal(this.getBalance()));//Balance 

		return paymentDetailsInfo;
	}

	public void verifyPaymentDetailsInResDetailsPg(Map<String, BigDecimal> totalPriceInfo, boolean noBalance){
		boolean result = true;
		logger.info("Verify 'Payment Details' info in 'Reservation Details' page.");
		
		//Get fee amount in "Reservation Details" page
		List<BigDecimal> paymentDetailsInfo = this.getPaymentDetailsInfo();

		//Compare to
		logger.info("Compare price with system calculate results in 'Reservation details' page");
		result &= MiscFunctions.compareResult("Camping Fee", totalPriceInfo.get(FeeType.KEY_ATTR_FEES).add(totalPriceInfo.get(FeeType.KEY_USE_FEES)), paymentDetailsInfo.get(0));
		result &= MiscFunctions.compareResult("Reservation fee", totalPriceInfo.get(FeeType.KEY_TRANS_FEES), paymentDetailsInfo.get(1));
		result &= MiscFunctions.compareResult("Taxes", totalPriceInfo.get(FeeType.KEY_BASE_TAXES).add(totalPriceInfo.get(FeeType.KEY_TRANS_TAXES)), paymentDetailsInfo.get(2));
		result &= MiscFunctions.compareResult("Total price", totalPriceInfo.get(FeeType.KEY_TOTAL_PRICE), paymentDetailsInfo.get(3));
		if(noBalance){
			result &= MiscFunctions.compareResult("Total payment", BigDecimal.ZERO.subtract(totalPriceInfo.get(FeeType.KEY_TOTAL_PRICE)), paymentDetailsInfo.get(4));
			result &= MiscFunctions.compareResult("Balance",  BigDecimal.ZERO, paymentDetailsInfo.get(5));
		}else{
			throw new ErrorOnDataException("Please complete the else code.");
		}

		if(!result){
			throw new ErrorOnPageException("Not all the payment info are correct in 'Reservation details' page. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all payment info are correct in 'Reservation details' page.");
	}
	
	public List<BigDecimal> getRefundDetailsInfo(){
		List<BigDecimal> refundDetailsInfo = new ArrayList<BigDecimal>();
		refundDetailsInfo.add(this.getCancellationPenaltyeAmount()); //Cancellation Penalty
		refundDetailsInfo.add(this.getTaxesAmount());//Taxes
		refundDetailsInfo.add(this.getResFeeAmount());//Reservation Fee
		refundDetailsInfo.add(this.getTotalAmount());//Total
		refundDetailsInfo.add(this.getAmountPaidAmount());//Amount Paid
		refundDetailsInfo.add(new BigDecimal(this.getBalance()));//Balance 

		return refundDetailsInfo;
	}
	
	public void verifyRefundDetailsInResDetailsPg(Map<String, BigDecimal> totalPriceInfo, boolean noBalance, BigDecimal amountPaid){
		boolean result = true;
		logger.info("Verify 'Payment Details' info in 'Reservation Details' page.");
		
		//Get fee amount in "Reservation Details" page
		List<BigDecimal> paymentDetailsInfo = this.getRefundDetailsInfo();

		//Compare to
		logger.info("Compare price with system calculate results in 'Reservation details' page");
		result &= MiscFunctions.compareResult("Cancellation Penalty", totalPriceInfo.get(FeeType.KEY_FEE_PENALTIES), paymentDetailsInfo.get(0));
		result &= MiscFunctions.compareResult("Taxes", totalPriceInfo.get(FeeType.KEY_BASE_TAXES).add(totalPriceInfo.get(FeeType.KEY_TRANS_TAXES)), paymentDetailsInfo.get(1));
		result &= MiscFunctions.compareResult("Reservation fee", totalPriceInfo.get(FeeType.KEY_TRANS_FEES), paymentDetailsInfo.get(2));
		result &= MiscFunctions.compareResult("Total", totalPriceInfo.get(FeeType.KEY_TOTAL_PRICE), paymentDetailsInfo.get(3));
		if(noBalance){
			result &= MiscFunctions.compareResult("Amount Paid", BigDecimal.ZERO.subtract(totalPriceInfo.get(FeeType.KEY_TOTAL_PRICE)), paymentDetailsInfo.get(4));
			result &= MiscFunctions.compareResult("Balance",  BigDecimal.ZERO, paymentDetailsInfo.get(5));
		}else{
			result &= MiscFunctions.compareResult("Amount Paid", BigDecimal.ZERO.subtract(amountPaid), paymentDetailsInfo.get(4));
			result &= MiscFunctions.compareResult("Balance", totalPriceInfo.get(FeeType.KEY_TOTAL_PRICE).subtract(amountPaid), paymentDetailsInfo.get(5));
		}

		if(!result){
			throw new ErrorOnPageException("Not all the payment info are correct in 'Reservation details' page. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all payment info are correct in 'Reservation details' page.");
	}
	
	public void verifyRefundDetailsInResDetailsPg(Map<String, BigDecimal> totalPriceInfo, boolean noBalance){
		this.verifyRefundDetailsInResDetailsPg(totalPriceInfo, noBalance, null);
	}
	
	public IHtmlObject[] getSuccessMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg success");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Successful message objects can't be found.'");
		}
		return objs;
	}
    
	public String getSuccessMes(){
		IHtmlObject[] objs = this.getSuccessMesObjs();
		String mes = objs[0].text().trim();
		Browser.unregister(objs);
		return mes;
	}
	
	public void verifySuccessMes(String expectedMes){
		String actualMes = this.getSuccessMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("Successful messag is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify successful message:"+expectedMes);
	}
	
	protected Property[] printTicketBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".text", "Print Tickets");
	}
	
	protected Property[] reprintTicketBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".text", "Reprint Tickets");
	}
	
	public boolean isPrintTicketsBtnExist() {
		return browser.checkHtmlObjectExists(this.printTicketBtn());
	}
	
	public boolean isReprintTicketsBtnExist() {
		return browser.checkHtmlObjectExists(this.reprintTicketBtn());
	}
	
	public boolean isFacilityLinkExist(String park) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(park+".*", false));
	}
}
