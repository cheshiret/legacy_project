/*
 * Created on Jan 19, 2010
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
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrConfigSchedDetailPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrConfigSchedDetailPage _instance = null;
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrConfigSchedDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrConfigSchedDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrConfigSchedDetailPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Distribution Configuration Schedule Details");
	}
	
	/**
	 * This method used to select payment or refund radio button
	 * @param paymentOrRefund
	 */
	public void selectPaymentOrRefundRadio(String paymentOrRefund) {
	  	if(paymentOrRefund.equalsIgnoreCase("Payments")) {
	  	  browser.selectRadioButton(".id", "paymentrefund", ".value", "1");
	  	} else if (paymentOrRefund.equalsIgnoreCase("Refunds")) {
	  	  browser.selectRadioButton(".id", "paymentrefund", ".value", "2");
	  	} else {
	  	  throw new ItemNotFoundException("Unknown Transaction Type.");
	  	}
	}
	
	/**
	 * This method used to get selected transaction(payment/refund)
	 * @return
	 */
	public String getPaymentOrRefund() {
	  	IHtmlObject[] objs = browser.getRadioButton(".id", "paymentrefund");
	  	
	  	String value = "";
	  	for(IHtmlObject o:objs) {
	  		if(((IRadioButton)o).isSelected()) {
	  			value = o.getProperty(".value").toString();
	  		}
	  	}
	  	Browser.unregister(objs);
	  	
	  	if(value.equals("1")){
	  	  return "Payments";
	  	}else if(value.equals("2")){
	  	  return "Refunds";
	  	}else{
	  	  throw new ItemNotFoundException("Unknown Transaction Type.");
	  	}
	}
	
	/**
	 * Select payment/refund group
	 * @param payGroup
	 */
	public void selectPayGroup(String payGroup) {
	  	browser.selectDropdownList(".id", "paymentgroup", payGroup);
	  	waitLoading();
	}
	
	/**
	 * 
	 * @return selected payment/refund group
	 */
	public String getPayGroup() {
	  	return browser.getDropdownListValue(".id", "paymentgroup", 0);
	}
	
	/**
	 * Select payment or refund type
	 * @param payType
	 */
	public void selectPayType(String payType) {
	  	browser.selectDropdownList(".id", "paymenttypeID", payType);
	}
	
	/**
	 * 
	 * @return selected payment/refund type
	 */
	public String getPayType() {
	  	return browser.getDropdownListValue(".id", "paymenttypeID", 0);
	}
	
	/**
	 * Input effective date
	 * @param date
	 */
	public void setEffectDate(String date) {
	  	browser.setTextField(".id", "effective_date_ForDisplay", date);
	}
	
	/**
	 * 
	 * @return effective date value
	 */
	public String getEffectDate() {
	  	return browser.getTextFieldValue(".id", "effective_date_ForDisplay");
	}
	
	/**
	 * Select the Reconcilable(yes/no)
	 * @param yesOrNo
	 */
	public void selectReconcilable(String yesOrNo) {
	  	if(yesOrNo.equalsIgnoreCase("yes")) {
	  	  browser.selectRadioButton(".id", "isreconciled", ".value", "1");
	  	} else if (yesOrNo.equalsIgnoreCase("no")) {
	  	  browser.selectRadioButton(".id", "isreconciled", ".value", "2");
	  	} else {
	  	  throw new ItemNotFoundException("Unknown Reconcilable Type.");
	  	}
	}
	
	/**
	 * This method used to get selected Reconcilable(yes/no)
	 * @return
	 */
	public String getReconcilable() {
	  	IHtmlObject[] objs = browser.getRadioButton(".id", "isreconciled");
	  	String value = "";
	  	
	  	for(IHtmlObject o:objs) {
	  		if(((IRadioButton)o).isSelected()) {
	  			value = o.getProperty(".value").toString();
	  		}
	  	}
	  	Browser.unregister(objs);
	  	
	  	if(value.equals("1")){
	  	  return "Yes";
	  	}else if(value.equals("2")){
	  	  return "No";
	  	}else{
	  	  throw new ItemNotFoundException("Unknown Reconcilable Type.");
	  	}
	}
	
	/**
	 * Input reconciliation start date
	 * @param startDate
	 */
	public void setReconcilStartDate(String startDate) {
	  	browser.setTextField(".id", "reconcilestart_ForDisplay", startDate);
	}
	
	public String getReconcilStartDate() {
	  	return browser.getTextFieldValue(".id", "reconcilestart_ForDisplay");
	}
	
	public void selectDistributable(String yesOrNo) {
	  if(yesOrNo.equalsIgnoreCase("yes")) {
	  	  browser.selectRadioButton(".id", "isdistributed", ".value", "1");
	  	} else if (yesOrNo.equalsIgnoreCase("no")) {
	  	  browser.selectRadioButton(".id", "isdistributed", ".value", "2");
	  	} else {
	  	  throw new ItemNotFoundException("Unknown Distributed Type.");
	  	}
	}
	
	/**
	 * This method used to get selected Distrutable(yes/no)
	 * @return
	 */
	public String getDistributable() {
	  	IHtmlObject[] objs = browser.getRadioButton(".id", "isdistributed");
	  	String value = "";
	  	
	  	for(IHtmlObject o:objs) {
	  		if(((IRadioButton)o).isSelected()) {
	  			value = o.getProperty(".value").toString();
	  		}
	  	}
	  	Browser.unregister(objs);
	  	
	  	if(value.equals("1")){
	  	  return "Yes";
	  	}else if(value.equals("2")){
	  	  return "No";
	  	}else{
	  	  throw new ItemNotFoundException("Unknown Distributed Type.");
	  	}
	}
	
	/**
	 * Input distributed start date
	 * @param startDate
	 */
	public void setDistributedStartDate(String startDate) {
		browser.setTextField(".id", "distributestart_ForDisplay", startDate);
	}
	
	public void moveFocusOutOfCalendar(){
		browser.clickGuiObject(".class","Html.LABEL",".text","Distribution Configuration Schedule ID");
	}
	/**
	 * 
	 * @return distributed start date
	 */
	public String getDistributedStartDate() {
	  	return browser.getTextFieldValue(".id", "distributestart_ForDisplay");
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
	
	/**
	 * 
	 * @return new added configration schedule Id
	 */
	public String getScheduleID() {
		IHtmlObject[] obj = browser.getTableTestObject(".text", new RegularExpression("^Update Schedule.*", false));
		String scheduleId = ((IHtmlTable)obj[1]).getCellValue(0, 1).split("Distribution Configuration Schedule ID")[1].trim();
		return scheduleId;
	}
	
	/**
	 * Setup the configurtation schedule details
	 * @param schedule
	 * @return
	 */
	public String setUpConfigSchedule(ScheduleData schedule) {
	  	selectPaymentOrRefundRadio(schedule.paymentOrRefund);
	  	selectPayGroup(schedule.payGroup);
	  	
	  	selectReconcilable(schedule.reconcilable);
	  	selectDistributable(schedule.distributable);
	  	setDistributedStartDate(schedule.distributStartDate);
	  	moveFocusOutOfCalendar();
	  	setReconcilStartDate(schedule.reconcilStartDate);
	  	moveFocusOutOfCalendar();
	  	setEffectDate(schedule.effectiveDate);
	  	moveFocusOutOfCalendar();
	  	selectPayType(schedule.payType);
	  	
	  	clickApply();
	  	Browser.sleep(2);//added by pzhu
	  	waitLoading();
	 
	  	
	  	String scheduleId = getScheduleID();
	  	clickOk();
	  	return scheduleId;
	}
	
	/**
	 * This method used to verify configuration schedule detail info
	 * @param configuration schedule
	 */
	public void verifyScheduleDetail(ScheduleData schedule) {
	  	logger.info("Start to verify configuration schedule Details.");
	  	
	  	if(!schedule.scheduleId.equalsIgnoreCase(getScheduleID())) {
	  	   throw new ItemNotFoundException("Configuration schedule Id " + getScheduleID() + " not correct.");
	  	}
	  	if(!schedule.paymentOrRefund.equalsIgnoreCase(getPaymentOrRefund())) {
	  	   throw new ItemNotFoundException("PaymentOrRefund " + getPaymentOrRefund() + " not correct.");
	  	}
	  	if(!schedule.payGroup.equalsIgnoreCase(getPayGroup())) {
	  	   throw new ItemNotFoundException("Pay Group " + getPayGroup() + " not correct.");
	  	}
	  	if(!schedule.payType.equalsIgnoreCase(getPayType())) {
	  	   throw new ItemNotFoundException("Pay Type " + getPayType() + " not correct.");
	  	}
	  	if(!schedule.effectiveDate.equalsIgnoreCase(getEffectDate())) {
	  	   throw new ItemNotFoundException("Effective Date " + getEffectDate() + " not correct.");
	  	}
	  	if(!schedule.reconcilable.equalsIgnoreCase(getReconcilable())) {
	  	   throw new ItemNotFoundException("Reconcilable " + getReconcilable() + " not correct.");
	  	}
	  	if(!schedule.reconcilStartDate.equalsIgnoreCase(getReconcilStartDate())) {
	  	   throw new ItemNotFoundException("Reconcile Start Date " + getReconcilStartDate() + " not correct.");
	  	}
	  	if(!schedule.distributable.equalsIgnoreCase(getDistributable())) {
	  	   throw new ItemNotFoundException("Distributable " + getDistributable() + " not correct.");
	  	}
	  	if(!schedule.distributStartDate.equalsIgnoreCase(getDistributedStartDate())) {
	  	   throw new ItemNotFoundException("Distribute Start date " + getDistributedStartDate() + " not correct.");
	  	}
	}
}
