/*
 * $Id: FldMgrRefundDetailPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.activenetwork.qa.awo.datacollection.legacy.RefundInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsRefundDetailsPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrRefundDetailPage
	 * Generated     : Aug 29, 2005 3:32:40 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/08/29
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsRefundDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsRefundDetailsPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsRefundDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsRefundDetailsPage();
		}

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use Apply button as the pageMark
		//the .href property is
		//javascript:invokeAction(%20"OpMgrRefundDetails.do",%20"AddnoteRefundDetails",%20"PayRefundWorker",%20"28489268"%20%20)
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression("\"OpMgrRefundDetails\\.do\",.+\"AddnoteRefundDetails\"", false));
		p[2] = new Property(".text",  "Apply");
		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * Click Ok button
	 *
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Apply Button
	 *
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Click ReIssue Button
	 *
	 */
	public void clickReIssue() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Re-issue");
	}
	
	/**
	 * Check if the Re-issue enable to be opereated
	 * @return
	 */
	public boolean checkReIssueEnable(){
		return browser.checkHtmlObjectExists(".text", "Re-issue",".class","Html.A");
	}

	/**
	 * Click Issue Button
	 */
	public void clickIssue() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Issue");
	}

	/**
	 * Click Approve Button
	 */
	public void clickApprove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Approve");
	}

	/**
	 * Check Approve Button exists or not
	 * @return
	 */
	public boolean checkApproveExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Approve");
	}

	/**
	 * Check Issue Button exists or not
	 * @return
	 */
	public boolean checkIssueExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Issue");
	}

	/**
	 * Click Issue To Voucher Button
	 *
	 */
	public void clickIssueToVoucher() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Issue To Voucher");
	}

	/**
	 * Click Transfer As Payment Button
	 *
	 */
	public void clickTransferAsPayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Transfer as Payment");
	}
	
	/**
	 * Click Refunds Link
	 *
	 */
	public void clickRefundsTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Refunds");  
	}
	
	/**
	 * Click IssueRefunds Link
	 *
	 */
	public void clickIssueRefundsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Issue Refunds");  
	}

	/**
	 * Input refund note
	 * @param notes
	 */
	public void setRefundNotes(String notes) {
		this.selectNoteType("Refund Note");
		this.setNewNotes(notes);
		this.clickApply();
	}

	/**
	 * Select and Input Field Note
	 * @param notes
	 */
	public void setFieldRefundNotes(String notes) {
		this.selectNoteType("Field Comment");
		this.setNewNotes(notes);
		this.clickApply();
	}

	/**
	 * Select and Input support Note
	 * @param notes
	 */
	public void setSupportRefundNotes(String notes) {
		this.selectNoteType("Support Comment");
		this.setNewNotes(notes);
		this.clickApply();
	}

	/**
	 * Input new Note
	 * @param notes
	 */
	public void setNewNotes(String notes) {
		browser.setTextArea(".id", "new_note", notes);
	}

	/**
	 * Select Note Type
	 * @param type
	 */
	public void selectNoteType(String type) {
		browser.selectDropdownList(".id", "new_note_type", type);
	}
	
	/**
	 * Get the refund detail text
	 * @return
	 */
	private String getRefundDetailText(){
		RegularExpression rex = new RegularExpression("Refund\\W*Refund ID.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		
		Browser.unregister(objs);
		
		return text;
	}
	/**
	 * Get refund Id from specific Table
	 * @return refund Id
	 */
	public String getRefundID() {
		String refundID = "";
		String text = getRefundDetailText();
		
		refundID = text.substring(text.indexOf("ID") + "ID".length(), text.indexOf("Status")).trim();
		return refundID;
	}

	/**
	 * Get refund status from specific table
	 * @return refund status
	 */
	public String getRefundStatus() {
		String status = "";
		String text = getRefundDetailText();
		logger.info("text is: " + text);
		status = text.substring(text.indexOf("Status") + 6, text.indexOf("Refund Type")).trim();
		logger.info("status is " + status);
		return status;
	}

	/**
	 * Get Refund Type from specific Table
	 * @return refund type
	 */
	public String getRefundType() {
		String refundType = "";
		String text = getRefundDetailText();
		refundType = text.substring(text.indexOf("Refund Type") + 11, text.indexOf("Refund Group")).trim();
		return refundType;
	}

	public boolean checkRefundNoteExists(String rex){
		RegularExpression reg = new RegularExpression(".*"+rex,false);
		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA",".text",reg);
	}
	
	public String getWarningMsg(){
		IHtmlObject [] objs = browser.getHtmlObject(".id", "eventReIssueRefund");
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return msg;
	}
	/**
	 * Get Order num show this refund belong which Order
	 * @return Order Number
	 */
	public String getRefundOrderNum() {
//	  	HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("\\d-\\d+", false));
	  	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Orders\\s*\\d-\\d+", false));
	  	
//	  	if (objs.length < 1) { // If the order is a privilege order, the order number is only displayed as text, not a button.
//	  		objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("\\d-\\d+", false));
//	  		
//	  	}
//	  	String ordNum = objs[0].getProperty(".text").toString();
	  	String text = objs[0].getProperty(".text");
	  	String ordNum = text.substring(text.indexOf("Orders") + "Orders".length()).trim();
	  	Browser.unregister(objs);
	  	return ordNum;
	}
	
	/**
	 * Get PaymentID show which payment generate this refund
	 * @return payment id
	 */
	public String getRefundPaymentId() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction\\(.*\"PaymentDetail\\.do\",.*\"GetPaymentDetails\",.*\"PayRefundWorker\".*", false));
	  	String paymentId = objs[0].getProperty(".text").toString().trim();
	  	Browser.unregister(objs);
	  	return paymentId;
	}
	/**
	 * Retrieve Refund Details information
	 * @return Refund Info
	 */
	public RefundInfo retriveRefundDetails() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Actions Approve Approve and Issue To Gift Card.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		
		RefundInfo refund = new RefundInfo();
		refund.amount = text.substring(text.indexOf("Amount") + "Amount".length(), text.indexOf("Batch ID")).trim();
		refund.customer = text.substring(text.indexOf("Name") + "Name".length(), text.indexOf("Phone")).trim();
		// Get the request user name. Update by Lesley Wang
		refund.requestingUser = StringUtil.getSubstring(text, "User", "Pin User");

		String pinUser = StringUtil.getSubstring(text, "Pin User", "Requesting Location");
		if (!StringUtil.isEmpty(pinUser)) {
			String[] userNames = pinUser.split(",");
			refund.user = userNames[1].trim() + ", " + userNames[0].trim();
		}
//		String[] userNames = text.substring(text.indexOf("Pin User") + "Pin User".length(), text.indexOf("Requesting Location")).trim().split(",");
//		refund.user = userNames[1].trim() + ", " + userNames[0];
		
		refund.collectLocation = text.substring(text.indexOf("Requesting Location")+ "Requesting Location".length(), text.indexOf("Date & Time")).trim();
		
		// Get the request date&time. Update by Lesley Wang
		String requestingInfo = text.substring(text.indexOf("Requesting Info"));
		refund.requestingDateTime = StringUtil.getSubstring(requestingInfo, "Date & Time", "Phone");
		
		refund.refundID = getRefundID();
		refund.status = getRefundStatus();
		refund.refundType = getRefundType();
		refund.fromOrderNum = getRefundOrderNum();
		refund.fromPaymentId = getRefundPaymentId();
		//convert the shortcut to full name
		if (refund.refundType.equalsIgnoreCase("PER CHQ")) {
			refund.refundType = "Personal Check";
		} else if (refund.refundType.equalsIgnoreCase("TRAV CHK")) {
			refund.refundType = "Travellers Check";
		} else if (refund.refundType.equalsIgnoreCase("CERT CHQ")) {
			refund.refundType = "Certified Check";
		} else if (refund.refundType.equalsIgnoreCase("MON ORD")) {
			refund.refundType = "Money Order";
		} else if (refund.refundType.equalsIgnoreCase("VCHR")) {
			refund.refundType = "Voucher";
		}

		// Get the approving info. Update by Lesley Wang
		String approvingInfo = StringUtil.getSubstring(text, "Approving Info", "Void Info");
		refund.approvingUser = StringUtil.getSubstring(approvingInfo, "User", "Approving Location");
		refund.approvingLocation = StringUtil.getSubstring(approvingInfo, "Approving Location", "Date & Time");
		refund.approvingDateTime = StringUtil.getSubstring(approvingInfo, "Date & Time", "Phone");
		// Get the refund note. Update by Lesley Wang
		refund.refundNote = StringUtil.getSubstring(text, "Refund Note", "Field Refund Note");
		
		// Get the void info. Update by Lesley Wang
		String voidInfo = StringUtil.getSubstring(text, "Void Info", "Issuing Info");
		refund.voidUser = StringUtil.getSubstring(voidInfo, "User", "Void Location");
		refund.voidLocation = StringUtil.getSubstring(voidInfo, "Void Location", "Date & Time");
		refund.voidDateTime = StringUtil.getSubstring(voidInfo, "Date & Time", "Phone");
		
		return refund;
	}
	
	public RefundInfo getRefundIssueInfo(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Status.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		//as the refund issue parts are always changed from UI,so add logic to get sub-string for refund issued part
		String issueInfo = text.substring(text.indexOf("Issued"));
		issueInfo = issueInfo.substring(0, issueInfo.indexOf("Re-Issue Reason") + "Re-Issue Reason".length());
		
		RefundInfo refund = new RefundInfo();
		refund.status = "Issued";
		refund.issuedStatus = refund.status;
		// the issue info is different based on the refund type
		if (issueInfo.contains("Gift Card #")) {
			refund.refundType = issueInfo.substring(issueInfo.indexOf("Refund Type")+"Refund Type".length(),issueInfo.indexOf("Gift Card #")).trim();
			refund.issuedToGiftCardNum = issueInfo.substring(issueInfo.indexOf("Gift Card #")+"Gift Card #".length(),issueInfo.indexOf("Gift Card Program ID")).trim();
			refund.issuedGiftCardOrd = issueInfo.substring(issueInfo.indexOf("Gift Card Order")+"Gift Card Order".length(),issueInfo.indexOf("User")).trim();
			refund.issuedGiftCardPrgID = StringUtil.getSubstring(issueInfo, "Gift Card Program ID", "Gift Card Program Name");
			refund.issuedGiftCardPrgName = StringUtil.getSubstring(issueInfo, "Gift Card Program Name", "Gift Card Order");
		} else if (issueInfo.contains("Voucher ID")) {
			// refund type is Voucher and should include Voucher ID, Voucher Program ID, Voucher Program Name
			refund.refundType = StringUtil.getSubstring(issueInfo, "Refund Type", "Voucher ID");
			refund.issuedVoucherID = StringUtil.getSubstring(issueInfo, "Voucher ID", "Voucher Program ID");
			refund.issuedVoucherProgramID = StringUtil.getSubstring(issueInfo, "Voucher Program ID", "Voucher Program Name");
			refund.issuedVoucherProgramNm = StringUtil.getSubstring(issueInfo, "Voucher Program Name", "User");
		} else if (issueInfo.contains("Credit Card Expiry Date")) {
			// refund type is one of credit card
			refund.refundType = StringUtil.getSubstring(issueInfo, "Refund Type", "Credit Card Expiry Date");
			refund.issuedCCExpiryDate = StringUtil.getSubstring(issueInfo, "Credit Card Expiry Date", "Credit Card Holder Name");
			refund.issuedCCHolderNm = StringUtil.getSubstring(issueInfo, "Credit Card Holder Name", "Credit Card Number");
			if (issueInfo.contains("Credit Card Swiped")) {
				refund.issuedCCNum = StringUtil.getSubstring(issueInfo, "Credit Card Number", "Credit Card Swiped");
			} else {
				refund.issuedCCNum = StringUtil.getSubstring(issueInfo, "Credit Card Number", "EFTConfigSchedule");
			}
		} else {
			// refund type is CASH. Lesley[20130719]: Update when debug ProcessImmediateRefundIssuance_Privilege, payment type=Personal Check
			refund.refundType = StringUtil.getSubstring(issueInfo, "Refund Type", "EFTConfigSchedule");
		}
		refund.issuedUser = issueInfo.substring(issueInfo.indexOf("User")+"User".length(),issueInfo.indexOf("Issuing Channel")).trim();
		refund.issueChanel = issueInfo.substring(issueInfo.indexOf("Issuing Channel")+"Issuing Channel".length(),issueInfo.indexOf("Issuing Location")).trim();
		refund.issuedLocation = issueInfo.substring(issueInfo.indexOf("Issuing Location")+"Issuing Location".length(),issueInfo.indexOf("Refund Station")).trim();
		// Get the issued date. by Lesley Wang
		refund.issuedDate = StringUtil.getSubstring(issueInfo, "Refund Station", "Phone");
		refund.issuedDate = refund.issuedDate.substring(refund.issuedDate.indexOf("Date") + "Date".length()).trim();
		
		return refund;
	}

	/**
	 * Get all refund voided issuing info in refund details page
	 * @return
	 * @author Lesley Wang
	 * @date May 25, 2012
	 */
	public RefundInfo getRefundVoidedIssueInfo(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Status.*", false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		
		// try to get the number of StatusVoided
		List<String> voidedIssueInfos = new ArrayList<String>();
		String voidedIssueInfo;
//		int index = text.indexOf("StatusVoided");
//		int index2 = "Re-Issue Reason".length();
//		while(index!=0 && index!=index2 && index!=text.length()) {
//			text = text.substring(index).trim();
//			voidedIssueInfo = StringUtil.getSubstring(text, "Status", "Re-Issue Reason");
//			voidedIssueInfos.add(voidedIssueInfo);
//			index = text.indexOf("Re-Issue Reason") + index2;
//		}
		while (!StringUtil.isEmpty(text) && text.contains("Voided")) {
			text = text.substring(text.indexOf("Voided"));
			voidedIssueInfo = StringUtil.getSubstring(text, "Voided", "Re-Issue Reason");
			voidedIssueInfos.add(voidedIssueInfo);
			text = text.substring(text.indexOf("Re-Issue Reason") + "Re-Issue Reason".length());
		}
		
		RefundInfo refund = new RefundInfo();
		String[] comLabels = new String[] {"User", "Issuing Channel", "Issuing Location",
				"Refund Station", "Date", "Phone", "Email"};
		String[] cardLabels = new String[] {"Refund Type", "Credit Card Expiry Date", "Credit Card Holder Name", 
				"Credit Card Number", "EFTConfigSchedule"};
		for (int j=0; j<voidedIssueInfos.size(); j++) {
			Properties p = new Properties();
			String s = voidedIssueInfos.get(j);
			String temp="";
			p.setProperty("Status", "Voided");
			if (s.contains("Credit Card Expiry Date")) {
				for (int i=0; i<cardLabels.length-1; i++) {
					temp = StringUtil.getSubstring(s, cardLabels[i], cardLabels[i+1]);
					p.setProperty(cardLabels[i], temp);
				}		
			} else { // Non credit card refund type.
				temp = StringUtil.getSubstring(s, cardLabels[0], comLabels[0]);
				p.setProperty(cardLabels[0], temp);
			}
			
			s = s.substring(s.indexOf(comLabels[0]));
			for (int i=0; i<comLabels.length - 1; i++) {
				temp = StringUtil.getSubstring(s, comLabels[i], comLabels[i+1]);
				p.setProperty(comLabels[i], temp);
			}	
			refund.allVoidedIssueInfos.add(p);
		}
		return refund;
	}
	
	/**
	 * Compare two refund Info,verify the refund details correct
	 * @param refund compared refund Info
	 */
	public void verifyRefundDetails(RefundInfo refund) {
		RefundInfo refund1 = retriveRefundDetails();
		if (!refund.refundID.equals(refund1.refundID)) {
			throw new ErrorOnPageException("RefundID is not correct!", refund1.refundID, refund.refundID);
		}
		if (!refund.fromOrderNum.equalsIgnoreCase(refund1.fromOrderNum)) {
			throw new ErrorOnPageException("Refund Order Num is not correct!", refund1.fromOrderNum, refund.fromOrderNum);
		}
		if (!refund.fromPaymentId.equalsIgnoreCase(refund1.fromPaymentId)) {
			throw new ErrorOnPageException("Refund Payment Id is not correct!", refund1.fromPaymentId, refund.fromPaymentId);
		}
		if (StringUtil.compareNumStrings(refund.amount, refund1.amount) != 0) {
			throw new ErrorOnPageException("Refund amount is not correct!", refund1.amount, refund.amount);
		}
		if (!StringUtil.isEmpty(refund.customer) && !refund.customer.equalsIgnoreCase(refund1.customer)) {
			throw new ErrorOnPageException("Refund customer is not correct!", refund1.customer, refund.customer);
		}
		if (!StringUtil.isEmpty(refund.user) && !refund.user.replaceAll(" ", "").equalsIgnoreCase(refund1.user.replaceAll(" ", ""))) {// update to Pin User from Request User
			throw new ErrorOnPageException("Refund Pin User is not correct!", refund1.user, refund.user);
		} 
		// Check the requesting user name
		if (!StringUtil.isEmpty(refund.requestingUser) && !refund.requestingUser.equalsIgnoreCase(refund1.requestingUser)) {
			throw new ErrorOnPageException("Refund Request User Name is not Correct!", 
					refund1.requestingUser, refund.requestingUser);
		}
		if (!StringUtil.isEmpty(refund.collectLocation) && !refund.collectLocation.equalsIgnoreCase(refund1.collectLocation)) {
			throw new ErrorOnPageException("Refund Request location is not Correct!",
					refund1.collectLocation, refund.collectLocation);
		}
		// Check refund status
		if (!StringUtil.isEmpty(refund.status) && !refund.status.equalsIgnoreCase(refund1.status)) {
			throw new ErrorOnPageException("Refund Status is not Correct!", refund1.status, refund.status);
		}
		// Check the requesting date & time
		if (!StringUtil.isEmpty(refund.requestingDateTime) && !refund1.requestingDateTime.contains(refund.requestingDateTime)) {
			throw new ErrorOnPageException("Refund Requesting Date & Time is not Correct!", 
					refund1.requestingDateTime, refund.requestingDateTime);
		}
		// Check the approving info, including approved user, location, date & time, comments
		if (!StringUtil.isEmpty(refund.approvingUser) && 
				!refund.approvingUser.replaceAll(" ", "").equalsIgnoreCase(refund1.approvingUser.replaceAll(" ", ""))) {
			throw new ErrorOnPageException("Refund Approving User is not Correct!", 
					refund1.approvingUser, refund.approvingUser);
		}
		if (!StringUtil.isEmpty(refund.approvingLocation) && !refund.approvingLocation.equalsIgnoreCase(refund1.approvingLocation)) {
			throw new ErrorOnPageException("Refund Approving Location is not correct", 
					refund1.approvingLocation, refund.approvingLocation);
		}
		if (!StringUtil.isEmpty(refund.approvingDateTime) && !refund1.approvingDateTime.contains(refund.approvingDateTime)) {
			throw new ErrorOnPageException("Refund Approving Date & Time is not Correct!", 
					refund1.approvingDateTime, refund.approvingDateTime);
		}
		// Check the refund note
		if (!StringUtil.isEmpty(refund.refundNote) && !refund1.refundNote.contains(refund.refundNote)) {
			throw new ErrorOnPageException("Refund Note is not correct!", refund.refundNote, refund1.refundNote);
		}
		
		// Check the void info, including void user, location, date & time
		if (!StringUtil.isEmpty(refund.voidUser) && 
				!refund.voidUser.replaceAll(" ", "").equalsIgnoreCase(refund1.voidUser.replaceAll(" ", ""))) {
			throw new ErrorOnPageException("Refund Void User is not Correct!", 
					refund1.voidUser, refund.voidUser);
		}
		if (!StringUtil.isEmpty(refund.voidLocation) && !refund.voidLocation.equalsIgnoreCase(refund1.voidLocation)) {
			throw new ErrorOnPageException("Refund Void Location is not correct", 
					refund1.voidLocation, refund.voidLocation);
		}
		if (!StringUtil.isEmpty(refund.voidDateTime) && !refund1.voidDateTime.contains(refund.voidDateTime)) {
			throw new ErrorOnPageException("Refund Void Date & Time is not Correct!", 
					refund1.voidDateTime, refund.voidDateTime);
		}
		
		// Check the Issued info, including issued user, date & time, status, refund type, 
		if (!StringUtil.isEmpty(refund.issuedStatus)) {
			this.verifyRefundIssueInfo(refund);
		}
		// Check the voided issuing info
		if (refund.allVoidedIssueInfos.size() > 0) {
			this.verifyRefundVoidedIssueInfo(refund);
		}
	}
	
	/**
	 * Verify the refund issuing info
	 * @param refund
	 * 
	 * Add by Lesley Wang
	 */
	public void verifyRefundIssueInfo(RefundInfo refund) {
		logger.info("Verifing refund issued info...");
		RefundInfo refund1 = this.getRefundIssueInfo();
		
		if (!refund.issuedStatus.equalsIgnoreCase(refund1.issuedStatus)) {
			throw new ErrorOnPageException("Refund Issue Status is not correct!", refund1.issuedStatus, refund.issuedStatus);
		}
		if (!refund.refundType.equalsIgnoreCase(refund1.refundType)) {
			throw new ErrorOnPageException("Refund Type is not correct!", refund1.refundType, refund.refundType);
		}
		if (!refund.issuedUser.replaceAll(" ", "").equalsIgnoreCase(refund1.issuedUser.replaceAll(" ", ""))) {
			throw new ErrorOnPageException("Refund Issued User is not correct!", refund1.issuedUser, refund.issuedUser);
		}
		if (!refund.issueChanel.equalsIgnoreCase(refund1.issueChanel)) {
			throw new ErrorOnPageException("Refund Issued Channel is not correct!", refund1.issueChanel, refund.issueChanel);
		}
		if (!refund.issuedLocation.equalsIgnoreCase(refund1.issuedLocation)) {
			throw new ErrorOnPageException("Refund Issued Channel is not correct!", refund1.issuedLocation, refund.issuedLocation);
		}
//		if (!StringUtil.isEmpty(refund.issuedDate) && !refund1.issuedDate.contains(refund.issuedDate)) {
//			throw new ErrorOnPageException("Refund Issued Date is not correct!", refund1.issuedDate, refund.issuedDate);
//		}// TODO
		
		// Check the issue info when the refund is issued to a gift card
		if (!StringUtil.isEmpty(refund.issuedToGiftCardNum) && 
				!refund.issuedToGiftCardNum.endsWith(refund1.issuedToGiftCardNum.substring(refund1.issuedToGiftCardNum.length()-4))) {
			throw new ErrorOnPageException("Refund Issued to gift card number is not correct!", refund1.issuedToGiftCardNum,
					refund.issuedToGiftCardNum);
		}
		if (!StringUtil.isEmpty(refund.issuedGiftCardPrgID) && 
				!refund.issuedGiftCardPrgID.equalsIgnoreCase(refund1.issuedGiftCardPrgID)) {
			throw new ErrorOnPageException("Refund Issued to gift card program id is not correct!", refund1.issuedGiftCardPrgID,
					refund.issuedGiftCardPrgID);
		}
		if (!StringUtil.isEmpty(refund.issuedGiftCardPrgName) && 
				!refund.issuedGiftCardPrgName.equalsIgnoreCase(refund1.issuedGiftCardPrgName)) {
			throw new ErrorOnPageException("Refund Issued to gift card program name is not correct!", refund1.issuedGiftCardPrgName,
					refund.issuedGiftCardPrgName);
		}
		if (!StringUtil.isEmpty(refund.issuedGiftCardOrd) && 
				!refund.issuedGiftCardOrd.equals(refund1.issuedGiftCardOrd)) {
			throw new ErrorOnPageException("Refund Issued gift card order is not correct!", refund1.issuedGiftCardOrd,
					refund.issuedGiftCardOrd);
		}
		// Check the issue info when the refund type is one of credit card
		if (!StringUtil.isEmpty(refund.issuedCCExpiryDate) &&
				!refund.issuedCCExpiryDate.equals(refund1.issuedCCExpiryDate)) {
			throw new ErrorOnPageException("Refund Issued credit card expiry date is not correct!", refund1.issuedCCExpiryDate,
						refund.issuedCCExpiryDate);
		}
		if (!StringUtil.isEmpty(refund.issuedCCHolderNm) &&
				!refund.issuedCCHolderNm.equalsIgnoreCase(refund1.issuedCCHolderNm)) {
			throw new ErrorOnPageException("Refund Issued credit card holder name is not correct!", refund1.issuedCCHolderNm,
					refund.issuedCCHolderNm);
		}
		// Check the first four numbers and the last four ones
		if (!StringUtil.isEmpty(refund.issuedCCNum)) {
			this.verifyCreditCardNums(refund.issuedCCNum, refund1.issuedCCNum);
		}
		
		// Check the issue info when the refund type is voucher
		if (!StringUtil.isEmpty(refund.issuedVoucherID) && 
				!refund.issuedVoucherID.equals(refund1.issuedVoucherID)) {
			throw new ErrorOnPageException("Refund Issued voucher id is not correct!", refund1.issuedVoucherID,
					refund.issuedVoucherID);
		}
		if (!StringUtil.isEmpty(refund.issuedVoucherProgramID) && 
				!refund.issuedVoucherProgramID.equalsIgnoreCase(refund1.issuedVoucherProgramID)) {
			throw new ErrorOnPageException("Refund Issued voucher program id is not correct!", refund1.issuedVoucherProgramID,
					refund.issuedVoucherProgramID);
		}
		if (!StringUtil.isEmpty(refund.issuedVoucherProgramNm) && 
				!refund.issuedVoucherProgramNm.equalsIgnoreCase(refund1.issuedVoucherProgramNm)) {
			throw new ErrorOnPageException("Refund Issued voucher program name is not correct!", refund1.issuedVoucherProgramNm,
					refund.issuedVoucherProgramNm);
		}
	}
	
	/**
	 * Verify refund voided issuing info in Refund Details page
	 * @param refund
	 * @author Lesley Wang
	 * @date May 25, 2012
	 */
	public void verifyRefundVoidedIssueInfo(RefundInfo refund) {
		logger.info("Verifing refund voided issuing info...");
		RefundInfo refund1 = this.getRefundVoidedIssueInfo();
		
		for (int i=0; i<refund1.allVoidedIssueInfos.size(); i++) {
			Properties p = refund.allVoidedIssueInfos.get(i);
			Properties p1 = new Properties();
			// Find the info item with the same refund type
			for (int j = i; j < refund.allVoidedIssueInfos.size(); j++) {
				if (p.getProperty("Refund Type").equalsIgnoreCase(refund1.allVoidedIssueInfos.get(j).getProperty("Refund Type"))) {
					p1 = refund1.allVoidedIssueInfos.get(j);
					if (j!=i) {
						refund1.allVoidedIssueInfos.set(j, refund1.allVoidedIssueInfos.get(i));
						refund1.allVoidedIssueInfos.set(i, p1);
					}
					break;
				}
			}
			if (p1.isEmpty()) {
				throw new ErrorOnPageException("Refund voided issue type '" + p.getProperty("Refund Type") + "' is not found in UI!");
			}
			if (!p.getProperty("Status").equalsIgnoreCase(p1.getProperty("Status"))) {
				throw new ErrorOnPageException("Refund voided issue status is not correct!", p1.getProperty("Status"),
						p.getProperty("Status"));
			}
			if (p.contains("Credit Card Expiry Date")) {
				if (!p.getProperty("Credit Card Expiry Date").equalsIgnoreCase(p1.getProperty("Credit Card Expiry Date"))) {
					throw new ErrorOnPageException("Refund voided issue Credit Card Expiry Date is not correct!", 
							p1.getProperty("Credit Card Expiry Date"), p.getProperty("Credit Card Expiry Date"));
				}
				if (!p.getProperty("Credit Card Holder Name").equalsIgnoreCase(p1.getProperty("Credit Card Holder Name"))) {
					throw new ErrorOnPageException("Refund voided issue Credit Card Holder Name is not correct!", 
							p1.getProperty("Credit Card Holder Name"), p.getProperty("Credit Card Holder Name"));
				}
				String ccNum = p.getProperty("Credit Card Number");
				if (ccNum != null) {
					this.verifyCreditCardNums(ccNum, p1.getProperty("Credit Card Number"));
				}
			}
			if (!p.getProperty("User").replaceAll("\\s+", StringUtil.EMPTY).equalsIgnoreCase(p1.getProperty("User").replaceAll("\\s+", StringUtil.EMPTY))) {
				throw new ErrorOnPageException("Refund voided issue User is not correct!", 
						p1.getProperty("User"), p.getProperty("User"));
			}
			if (!p.getProperty("Issuing Channel").equalsIgnoreCase(p1.getProperty("Issuing Channel"))) {
				throw new ErrorOnPageException("Refund voided Issuing Channel is not correct!", 
						p1.getProperty("Issuing Channel"), p.getProperty("Issuing Channel"));
			}
			if (!p.getProperty("Issuing Location").equalsIgnoreCase(p1.getProperty("Issuing Location"))) {
				throw new ErrorOnPageException("Refund voided Issuing Location is not correct!", 
						p1.getProperty("Issuing Location"), p.getProperty("Issuing Location"));
			}
			if (!p1.getProperty("Date").contains(p.getProperty("Date"))) {
				throw new ErrorOnPageException("Refund voided Issuing Date is not correct!", 
						p1.getProperty("Date"), p.getProperty("Date"));
			}
		}
	}
	
	/**
	 * verify the first four numbers and the last four ones of the credit card numbers
	 * @param actualNum1
	 * @param expectedNum2
	 * @author Lesley Wang
	 * @date May 25, 2012
	 */
	private void verifyCreditCardNums(String actualNum1, String expectedNum2) {
		String firstFourNums = expectedNum2.substring(0, 4);
		String lastFourNums = expectedNum2.substring(actualNum1.length() - 4);
		String actLastFourNums = actualNum1.substring(actualNum1.length() - 4);
		if (!actualNum1.startsWith(firstFourNums) && !actLastFourNums.equals(lastFourNums)) {
			throw new ErrorOnPageException("Refund Issued credit card number is not correct!", expectedNum2,
					actualNum1);
		}
	}
	
	public String getGiftCardNum(){
	    String giftCardNum = "";
		RegularExpression rex = new RegularExpression("Status.+Refund Type.+Gift Card Number.*",
				false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		String temp = text.substring(text.indexOf("Gift Card Number") + "Gift Card Number".length()).trim();
		String[] arr=temp.split(" ");
		giftCardNum=arr[0];
		Browser.unregister(objs);
		return giftCardNum;
	}

	/**
	 * Check Transfer as Payment Button exists or not
	 * @return
	 */
	public boolean checkTransferasPaymentExists() {
		
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Transfer as Payment");
	}
}
