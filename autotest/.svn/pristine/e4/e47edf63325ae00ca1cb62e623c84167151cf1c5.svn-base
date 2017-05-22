/*
 * Created on Dec 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsVoucherDetailsPage extends OrmsPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoucherDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoucherDetailsPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoucherDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoucherDetailsPage();
		}

		return _instance;
	}
  /**
   * Check this page is exists
   */
  public boolean exists() {
   return browser.checkHtmlObjectExists(".class","Html.TABLE",".text", new RegularExpression("Voucher( Details)?", false));
  }
  
  /**
	 * Get Voucher Id
	 * @return voucher id
	 */
	public String getVoucherId()
	{
	   	IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("Voucher Voucher ID.*",false));
	   	String text = objs[0].getProperty(".text").toString();
		String voucherId = text.substring(text.indexOf("Voucher ID")+"Voucher ID".length(),text.indexOf("Voucher Status")).trim();
	   	Browser.unregister(objs);
	   	return voucherId;
	}
	/**
	 * Get the voucher program name
	 * @return voucher program name
	 */
	public String getVoucherStatus(){
	    IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("^Voucher Voucher ID.*",false));
	   	String text = objs[0].getProperty(".text").toString();
		String voucherStatus = text.substring(text.indexOf("Voucher Status")+"Voucher Status".length(),text.indexOf("Customer")).trim();
	   	Browser.unregister(objs);
	   	return voucherStatus;
	}
	
	/**
	 * Get the voucher amount
	 * @return voucher amount
	 */
	public String getVoucherAmount(){
	    IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("^Actions.*",false));
	   	String text = objs[0].getProperty(".text").toString();
		String voucherAmount = text.substring(text.indexOf("Amount Original Amount")+"Amount Original Amount".length(),text.indexOf(" Balance")).trim();
	   	Browser.unregister(objs);
	   	return voucherAmount;
	}

	/**
	 * Get Customer Information
	 * @return customer info
	 */
	public String getCustomer()
	{
	  	IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("Voucher Voucher ID.*",false));
	   	String text = objs[0].getProperty(".text").toString();
		String customer = text.substring(text.indexOf("Customer")+"Customer".length()).trim().replaceAll(", ",",");
	   	Browser.unregister(objs);
	   	return customer;
	}
	
	/**
	 * Get Voucher Status
	 * @return
	 */
	public String getStatus()
	{
	  	IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("Voucher Voucher ID.*",false));
	   	String text = objs[0].getProperty(".text").toString();
		String status = text.substring(text.indexOf("Voucher Status")+"Voucher Status".length(),text.indexOf("Customer")).trim();
	   	Browser.unregister(objs);
	   	return status;
	}
	/**
	 * Get all voucher Details info from details page
	 * @return Voucher object
	 */
	public Voucher getAllVoucherDetails() {
		Voucher voucher = new Voucher();
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("Actions View History Convert.*",false));
	   	String text = objs[0].getProperty(".text").toString();
	   	voucher.voucherId = getVoucherId();
	   	voucher.customer = getCustomer();
	   	// Update by Lesley Wang
	   	voucher.voucherStatus = this.getVoucherStatus();
	   	voucher.voucherPrgID = StringUtil.getSubstring(text, "Voucher Program ID", "Voucher Program Name");
	   	voucher.voucherProgrameName = StringUtil.getSubstring(text, "Voucher Program Name", "Order Source Order");
	   	voucher.fromOrderNum = StringUtil.getSubstring(text, "Source Order", "Payment Source Payment");
	   	voucher.fromPaymentId = StringUtil.getSubstring(text, "Payment Source Payment", "Refund Source");
	   	voucher.fromRefundID = StringUtil.getSubstring(text, "Source Issued Refund", "Source Voucher");
	   	voucher.sourchVoucherID = StringUtil.getSubstring(text, "Source Voucher ID", "Amount");
	   	voucher.amount = StringUtil.getSubstring(text, "Original Amount", "Balance");
	   	voucher.user = StringUtil.getSubstring(text, "User", "Created Location");
	   	voucher.location = StringUtil.getSubstring(text, "Created Location", "Date & Time");
	   	Browser.unregister(objs);
   		return voucher;
	}
	
	/**
	 * Get Voucher Balance
	 * @return voucher balance
	 */
	public String getVoucherBalance()
	{
	  	IHtmlObject []objs = browser.getTableTestObject(".text",new RegularExpression("Actions View History Convert.*",false));
	   	String text = objs[0].getProperty(".text").toString();
	   	Browser.unregister(objs);
	  	String balance = text.substring(text.indexOf("Balance")+"Balance".length(),text.indexOf("Creation Info")).trim();
	  	return balance;
	}
	/**
	 * Compare Given Voucher Information is Equals voucher in details page
	 * @param voucher
	 */
	public void compareTwoVoucherDetails(Voucher voucher)
	{
	  	Voucher v2 = getAllVoucherDetails();
	  	if(!voucher.voucherId.equalsIgnoreCase(v2.voucherId))
	  	{
			throw new ItemNotFoundException("Voucher ID " + v2.voucherId+ " is not correct!");
	  	}
	  	if(!voucher.customer.equalsIgnoreCase(v2.customer))
	  	{
			throw new ItemNotFoundException("Voucher customer " + v2.customer+ " is not correct!");
	  	}
		if(!voucher.voucherProgrameName.equalsIgnoreCase(v2.voucherProgrameName))
	  	{
			throw new ItemNotFoundException("Voucher voucherProgrameName " + v2.voucherProgrameName+ " is not correct!");
	  	}
		if(!voucher.fromOrderNum.equalsIgnoreCase(v2.fromOrderNum))
	  	{
			throw new ItemNotFoundException("Voucher fromOrderNum " + v2.fromOrderNum+ " is not correct!");
	  	}
		if(!voucher.fromPaymentId.equalsIgnoreCase(v2.fromPaymentId))
	  	{
			throw new ItemNotFoundException("Voucher fromPaymentId " + v2.fromPaymentId+ " is not correct!");
	  	}
//		if(!voucher.amount.equalsIgnoreCase(v2.amount))
//	  	{
//			throw new ItemNotFoundException("Voucher amount " + v2.amount+ " is not correct!");
//	  	}
		if(!voucher.user.replaceAll(" ", "").equalsIgnoreCase(v2.user.replaceAll(" ", "")))
	  	{
			throw new ItemNotFoundException("Voucher Create user " + v2.user+ " is not correct!");
	  	}
		if(!voucher.location.equalsIgnoreCase(v2.location))
	  	{
			throw new ItemNotFoundException("Voucher Create location " + v2.location+ " is not correct!");
	  	}
		
		// Updated by Lesley Wang
		if (StringUtil.compareNumStrings(voucher.amount, v2.amount) != 0) {
			throw new ErrorOnPageException("Refund amount is not correct!", v2.amount, voucher.amount);
		}
		if(!StringUtil.isEmpty(voucher.voucherStatus) && !voucher.voucherStatus.equalsIgnoreCase(v2.voucherStatus)) {
			throw new ErrorOnPageException("Voucher Status is not correct!", v2.voucherStatus, voucher.voucherStatus);
		}
		if(!StringUtil.isEmpty(voucher.voucherPrgID) && !voucher.voucherPrgID.equalsIgnoreCase(v2.voucherPrgID)) {
			throw new ErrorOnPageException("Voucher Program ID is not correct!", v2.voucherPrgID, voucher.voucherPrgID);
		}
		if(!StringUtil.isEmpty(voucher.fromRefundID) && !voucher.fromRefundID.equalsIgnoreCase(v2.fromRefundID)) {
			throw new ErrorOnPageException("Voucher Source Issued Refund ID is not correct!", v2.fromRefundID, voucher.fromRefundID);
		}
		if(!StringUtil.isEmpty(voucher.sourchVoucherID) && !voucher.sourchVoucherID.equalsIgnoreCase(v2.sourchVoucherID)) {
			throw new ErrorOnPageException("Source Voucher ID is not correct!", v2.sourchVoucherID, voucher.sourchVoucherID);
		}
		logger.info("Voucher Details Information is Correct!");
	}
	
	/**
	 * Click View History Button
	 *
	 */
	public void clickViewHistory()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("View History",false));
	}
	
	/**
	 * Click Convert to Refund Button
	 *
	 */
	public void clickConvertToRefund()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Convert to Refund", true);
	}
	
	/**
	 * Click void voucher payment Button
	 *
	 */
	public void clickVoidVoucherPayment()
	{
		browser.clickGuiObject(".class","Html.A",".text","Void Voucher Payment");
	}

	/**
	 * Verify voucher status equals given status
	 * @param status
	 */
	public void verifyStatus(String status)
	{
		String actualStatus = getStatus();
	  	if(!status.equalsIgnoreCase(actualStatus))
	  	{
	  	  	throw new ItemNotFoundException("Status "+actualStatus+" not Correct, expect status is "+status);
	  	}
	}
	/**
	 * Verify Balance Num equals given balance
	 * @param balance
	 */
	public void verifyBalanceNum(String balance)
	{
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		String balanceFromUI = getVoucherBalance().replaceAll("\\$", "");
		if(!balance.replaceAll("\\$", "").equalsIgnoreCase(balanceFromUI))
	  	{
			throw new ErrorOnPageException("Balance Not Correct",balance,balanceFromUI);
	  	}
	}
	
	public void clickExpireVoucher()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Expire Voucher");
	}
	
	/**
	 * get the refund ID
	 * @return refund ID
	 */
	public String getRefundID(){
	   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Actions.*",false));
	   String temp=objs[0].getProperty(".text").toString();
	   String refundID=temp.substring(temp.indexOf("Refunded to Refund ID")+"Refunded to Refund ID".length()+1,temp.indexOf("Refunded Request Comments")).trim();
	   return refundID;
	}
	
	/**
	 * Click Vouchers tab
	 * 
	 * @author Lesley Wang
	 * @date May 28, 2012
	 */
	public void clickVouchersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Vouchers");
	}
	
	/**
	 * Get voucher refunded info
	 * @return
	 * @author Lesley Wang
	 * @date May 31, 2012
	 */
	public Voucher getVoucherRefundedInfo() {
		String refundedInfo = browser.getObjectText(".class","Html.TR",".text",new RegularExpression("^Refunded Info.*",false));
		Voucher v = new Voucher();
		v.refundedUser = StringUtil.getSubstring(refundedInfo, "User", "Refund Requesting Location");
		v.refundedLocation = StringUtil.getSubstring(refundedInfo, "Refund Requesting Location", "Date & Time");
		v.refundedToRefundID = StringUtil.getSubstring(refundedInfo, "Refunded to Refund ID", "Refunded Request Comments");
		v.refundedComments = refundedInfo.substring(refundedInfo.indexOf("Refunded Request Comments") + 
				"Refunded Request Comments".length()).trim();
		return v;
	}
	
	/**
	 * Verify voucher status, balance and refunded info of the given voucher
	 * @param v
	 * @author Lesley Wang
	 * @date May 31, 2012
	 */
	public void verifyVoucherRefundedInfo(Voucher v) {
		this.verifyStatus(v.voucherStatus);
		this.verifyBalanceNum(v.balance);
		Voucher v2 = this.getVoucherRefundedInfo();
		if (!v.refundedUser.replaceAll(" ", "").equalsIgnoreCase(v2.refundedUser.replaceAll(" ", ""))) {
			throw new ErrorOnPageException("The refunded user is not correct!", 
					v2.refundedUser, v.refundedUser);
		}
		if (!v.refundedLocation.equalsIgnoreCase(v2.refundedLocation)) {
			throw new ErrorOnPageException("The refunded location is not correct!", 
					v2.refundedLocation, v.refundedLocation);
		}
		if (!v.refundedToRefundID.equalsIgnoreCase(v2.refundedToRefundID)) {
			throw new ErrorOnPageException("The refunded to refund ID is not correct!", 
					v2.refundedToRefundID, v.refundedToRefundID);
		}
		if (!v.refundedComments.equalsIgnoreCase(v2.refundedComments)) {
			throw new ErrorOnPageException("The refunded comments are not correct!", 
					v2.refundedComments, v.refundedComments);
		}
		logger.info("The voucher refunded info is correct!");
	}
	
	private Property[] back() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Back");
	}
	
	public void clickBack() {
		browser.clickGuiObject(back());
	}
}
