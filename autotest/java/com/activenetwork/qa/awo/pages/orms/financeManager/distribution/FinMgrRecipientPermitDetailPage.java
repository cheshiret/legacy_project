/*
 * Created on Jan 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRecipientPermitDetailPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRecipientPermitDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRecipientPermitDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRecipientPermitDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRecipientPermitDetailPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Recipient Permit Details");
	}
	
	/**
	 * select recipient type from drop down list
	 */
	public void selectRecipientType(String type) {
	  	browser.selectDropdownList(".id", "recipient_type_id", type);
	}
	
	/**
	 * 
	 * @return selected recipient type
	 */
	public String getRecipientType() {
	  	return browser.getDropdownListValue(".id", "recipient_type_id", 0);
	}
	
	/**
	 * 
	 * @return Recipient value
	 */
	public String getRecipient() {
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Recipient Type Type.*", false));
	  	String text = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	return text.substring(text.indexOf("Name/Code")+"Name/Code".length(),text.indexOf("Effective Date")).trim();
	}
	
	/**
	 * Input effective date
	 * @param effectDate
	 */
	public void setEffectiveDate(String effectDate) {
	  	browser.setTextField(".id", "effective_date_ForDisplay", effectDate);
	}
	
	/**
	 * 
	 * @return effective date value from text box
	 */
	public String getEffectiveDate() {
	  	return browser.getTextFieldValue(".id", "effective_date_ForDisplay");
	}
	
	/**
	 * Input permit start date
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
	  	browser.setCalendarField(".id", "permit_start_date_ForDisplay", startDate);
	}
	
	/**
	 * 
	 * @return permit start date from text box
	 */
	public String getStartDate() {
	  	return browser.getTextFieldValue(".id", "permit_start_date_ForDisplay");
	}
	
	/**
	 * Input permit end date
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	  	browser.setTextField(".id", "permit_end_date_ForDisplay", endDate);
	}
	
	/**
	 * 
	 * @return permit end date from text box
	 */
	public String getEndDate() {
	  	return browser.getTextFieldValue(".id", "permit_end_date_ForDisplay");
	}
	
	/**
	 * Select disbursement frequency from drop down list
	 * @param frequency
	 */
	public void selectFrequency(String frequency) {
	  	browser.selectDropdownList(".id", "disburse_frequency_id", frequency);
	}
	
	/**
	 * 
	 * @return selected disbursement frequency
	 */
	public String getFrequency() {
	  	return browser.getDropdownListValue(".id", "disburse_frequency_id", 0);
	}
	
	/**
	 * 
	 * @return new added Recipient permit Id
	 */
	public String getRecipientPermitID() {
		IHtmlObject[] obj = browser.getTableTestObject(".text", new RegularExpression("^Update Recipient Permit.*", false));
		String scheduleId = ((IHtmlTable)obj[1]).getCellValue(0, 1).replaceAll("[a-zA-Z]", "");
		return scheduleId.trim();
	}
	
	/**
	 * Click apply button
	 *
	 */
	public void clickApply() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	/**
	 * click OK button
	 *
	 */
	public void clickOk() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void moveFocusOutOfCalendar(){
		browser.clickGuiObject(".class","Html.LABEL",".text","Effective Date");
	}
	
	/**
	 * Setup the permit details
	 * @param permit
	 * @return
	 */
	public String setUpPermitInfo(ScheduleData permit) {
	  	setEffectiveDate(permit.effectiveDate);
	  	this.moveFocusOutOfCalendar();
	  	setStartDate(permit.startDate);
	  	this.moveFocusOutOfCalendar();
	  	setEndDate(permit.endDate);
	  	this.moveFocusOutOfCalendar();
	  	if(null !=permit.frequency && !permit.frequency.equals("")){
	  		selectFrequency(permit.frequency);
	  	}	  	
	  	clickApply();
	  	waitLoading();
	  	String permitId = getRecipientPermitID();
	  	clickOk();
	  	return permitId;
	}
	
	/**
	 * This method used to verify permit detail info
	 * @param permit
	 */
	public void verifyPermitDetail(ScheduleData permit) {
	  	logger.info("Start to verify Permit Details.");
	  	
	  	if(!permit.scheduleId.equalsIgnoreCase(getRecipientPermitID())) {
	  	   throw new ItemNotFoundException("Recipient Permit Id " + getRecipientPermitID() + " not correct.");
	  	}
	  	if(!permit.recipientType.equalsIgnoreCase(getRecipientType())) {
	  	   throw new ItemNotFoundException("Recipient Type " + getRecipientType() + " not correct.");
	  	}
	  	if(!permit.recipient.equalsIgnoreCase(getRecipient())) {
	  	   throw new ItemNotFoundException("Recipient " + getRecipient() + " not correct.");
	  	}
	  	if(!permit.effectiveDate.equalsIgnoreCase(getEffectiveDate())) {
	  	   throw new ItemNotFoundException("Effective Date " + getEffectiveDate() + " not correct.");
	  	}
	  	if(!permit.startDate.equalsIgnoreCase(getStartDate())) {
	  	   throw new ItemNotFoundException("Permit Start Date " + getStartDate() + " not correct.");
	  	}
	  	if(!permit.endDate.equalsIgnoreCase(getEndDate())) {
	  	   throw new ItemNotFoundException("Permit End Date " + getEndDate() + " not correct.");
	  	}
	  	if(!permit.frequency.equalsIgnoreCase(getFrequency())) {
	  	   throw new ItemNotFoundException("Disbursement Frequency " + getFrequency() + " not correct.");
	  	}
	}
}
