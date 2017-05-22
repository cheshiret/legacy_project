package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Dec 6, 2011
 */
public class LicMgrVendorFinConfigSubPage extends LicMgrVendorDetailsPage {
	
	private static LicMgrVendorFinConfigSubPage instance=null;
	private String prefixReg = "^VendorFinancialConfigView-[0-9]*.";
	
	private LicMgrVendorFinConfigSubPage() {};
	
	public static LicMgrVendorFinConfigSubPage getInstance() {
		if(null == instance) {
			instance = new LicMgrVendorFinConfigSubPage();
		}
		return instance;
	}
	
	public boolean exists() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Save", false));

		return browser.checkHtmlObjectExists(".id","consumable_content") && browser.checkHtmlObjectExists(p);
	}

	public void clickSave() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save", true);
	}

	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true);
	}
	
	public void removeAllReportEmails() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", "Remove");
		if(objs.length > 0) {
			for(int i = 0; i < objs.length; i ++) {
				clickRemove();
				ajax.waitLoading();
			}
		}
	}

	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true);
	}
	
	public String getEFTType(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefixReg+"eftType",false),0);
	}
	
	public String getEFTSchedule(){
		return browser.getDropdownListValue(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false),0);
	}
	
	public String getFailedEFTEnforcement(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefixReg+"eftFailureEnforcement",false),0);
	}
	
	public String getVoidReturnChargeDays(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefixReg+"voidRtnChargeDays",false));
	}
	
	public String getAutoReturnVoidedDoc(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefixReg+"rtnVoidedDoc",false),0);
	}
	
	public String[] getReportNotificationEmails(){
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("^EmailDynamicTableView-[0-9]*.emailAddress",false));
		
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no Text which id = EmailDynamicTableView-[0-9]*.emailAddress");
		}
		String[] list = new String[objs.length];
		for(int i=0;i<objs.length;i++){
			list[i]=((IText)objs[i]).getText();
		}
		Browser.unregister(objs);
		
		return list;
	}
	
	public void selectEFTType(String EFTType) {
		if(StringUtil.isEmpty(EFTType)){
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"eftType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"eftType", false), EFTType);
		}
	}

	public boolean checkEFTScheduleExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefixReg+"eftSchedule", false));
	}
	public void selectEFTSchedule(String EFTSchedule) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"eftSchedule", false), EFTSchedule);
	}
	
	public void selectFailedEFTEnforcement(String failedEFTEnforcement) {
		if(StringUtil.isEmpty(failedEFTEnforcement)){
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"eftFailureEnforcement", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefixReg+"eftFailureEnforcement", false), failedEFTEnforcement);
		}
	}

	public void setVoidReturnChargeDays(String voidReturnChargeDays) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"voidRtnChargeDays", false), voidReturnChargeDays);
	}

	public void selectAutoReturnVoidedDoc(String autoReturnVoidedDoc) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"rtnVoidedDoc", false), autoReturnVoidedDoc);
	}
	
	/**
	 * If the report notification check box be checked or not, set all the value into map.
	 * @param rptNotification
	 * @return
	 */
	public HashMap<String, Boolean> isRptNotifiCheckBoxSelected() {
		HashMap<String, Boolean> rptNotificationOnPg = new HashMap<String, Boolean>();

		// get check box
		Property[] p = new Property[1];
		p[0] = new Property(".id",new RegularExpression("VendorFinancialConfigView-\\d+\\.rptNotifications_\\d+",false));
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(p);
		
		for(int i=0; i<checkBoxObjs.length; i++){

			// get text of the check box
			IHtmlObject[] lab = browser.getHtmlObject(".className", "trailing", ".for", checkBoxObjs[i].id());
			Boolean flag = ((ICheckBox)checkBoxObjs[i]).isSelected();
			// set text and isChecked into map
			rptNotificationOnPg.put(lab[0].text(), flag);
		}
		
		return rptNotificationOnPg;
	}

	public String getReportNotificationCheckBoxIDValue(String notification){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", notification);
		
		if(objs.length<1){
			throw new ItemNotFoundException("Notification " + notification + " SPAN object is nt found.");
		}
		
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.INPUT.checkbox");
		
		IHtmlObject[] checkBoxObjs = browser.getHtmlObject(p, objs[0]);
		if(checkBoxObjs.length<1){
			throw new ItemNotFoundException("Notification " + notification + " check box object is nt found.");
		}
		
		String value = checkBoxObjs[0].getProperty(".id");
		Browser.unregister(checkBoxObjs);
		Browser.unregister(objs);	
		return value;
	}
	
	public void selectReportNotification(String notification){
		String value = this.getReportNotificationCheckBoxIDValue(notification);	
		browser.selectCheckBox(".id", value);
	}
	
	public void unSelectReportNotification(String notification){
		String value = this.getReportNotificationCheckBoxIDValue(notification);	
		browser.unSelectCheckBox(".id", value);
	}

	public void setReportNotification(HashMap<String,Boolean> reportNotification){
		for(Entry<String, Boolean> e: reportNotification.entrySet()){
			if(e.getValue()){
				this.selectReportNotification(e.getKey());
			}else {
				this.unSelectReportNotification(e.getKey());
			}
		}
	}

	public void setReportNotificationEmails(List<String> emailAddress){
		
		String[] emails = this.getReportNotificationEmails();
		int index = 0;
		
		// if there already have email, click Add button first, then set the mail address.
		if(!"".equals(emails[0])) {
			this.clickAdd();
			ajax.waitLoading();
			index = emails.length;
		}
		
		for(int i = 0; i < emailAddress.size(); i++){
			browser.setTextField(".id", new RegularExpression("^EmailDynamicTableView-[0-9]*.emailAddress",false), emailAddress.get(i), i+index);
			if(i != (emailAddress.size()-1)) {
				this.clickAdd();
				ajax.waitLoading();
			}
		}
	}
	
	public void selectInvoiceFrequency(String InvoiceFrqncy){
		if(StringUtil.isEmpty(InvoiceFrqncy)){
			browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftFrequency",false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftFrequency",false), InvoiceFrqncy);
		}
	}
	
	public boolean checkInvoiceScheduleLableExists(){
		return browser.checkHtmlObjectExists(".class","Html.LABEL",".text","Invoice Schedule");
	}
	
	public void selectInvoiceSchedule(String invoiceSchedule){
		if(StringUtil.isEmpty(invoiceSchedule)){
			browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftScheduleView",false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftScheduleView",false), invoiceSchedule);
		}
		
	}
	
	public void selectBlankInvoiceSchedule(){
		browser.selectDropdownList(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftScheduleView",false), 0);
	}
	
	/**
	 * Edit each content on the financial config page
	 * 
	 * @param financialInfo
	 */
	public void editFinancialConfigInfo(LicMgrVendorFinConfigInfo financialInfo) {
		
		this.selectEFTType(financialInfo.eftType);
		this.selectInvoiceFrequency(financialInfo.invoiceFrequency);
		ajax.waitLoading();
		this.selectInvoiceSchedule(financialInfo.invoiceSchedule);
		ajax.waitLoading();
//		if(!StringUtil.isEmpty(financialInfo.invoiceSchedule)){
//			selectEFTSchedule(financialInfo.invoiceSchedule);
//		}
		this.selectFailedEFTEnforcement(financialInfo.failedEFTEnforcement);
		this.setVoidReturnChargeDays(financialInfo.voidReturnChargeDays);
		this.selectAutoReturnVoidedDoc(financialInfo.autoReturnVoidedDoc);
		this.setReportNotification(financialInfo.rptNotification);

		removeAllReportEmails();
		for(Entry<String, Boolean> entry: financialInfo.rptNotification.entrySet()) {
			if(entry.getValue()) {
				clickAdd();
				ajax.waitLoading();
				setReportNotificationEmails(financialInfo.reportEmails);
				break;
			}
		}
	}
	
	public String getInvoiceSchedule(){
		return browser.getDropdownListValue(".id", new RegularExpression("VendorFinancialConfigView-\\d+\\.eftScheduleView",false));
	}
	/**
	 * Verify if the content on the financial config page is the same as edit.
	 * 
	 * @param financialInfo
	 */
	public void verifyFinancialInfo(LicMgrVendorFinConfigInfo financialInfo) {

		String value = this.getEFTType();
		if(!value.equals(financialInfo.eftType)) {
			logger.error("EFT Type is not correct.The expect is:"+financialInfo.eftType+", but displayed is:"+value);
			throw new ErrorOnPageException("EFT Type is not correct.The expect is:"+financialInfo.eftType+", but displayed is:"+value);
		}
		
		value = this.getInvoiceSchedule();
		if(!value.trim().equals(financialInfo.invoiceSchedule.trim())) {
			logger.error("EFT Schedule is not correct.The expect is:"+financialInfo.invoiceSchedule+", but displayed is:"+value);
			throw new ErrorOnPageException("invoice Schedule is not correct.The expect is:"+financialInfo.invoiceSchedule+", but displayed is:"+value);
		}

		value = this.getFailedEFTEnforcement();
		if(!value.equals(financialInfo.failedEFTEnforcement)) {
			logger.error("Failed EFTEnforcement is not correct.The expect is:"+financialInfo.failedEFTEnforcement+", but displayed is:"+value);
			throw new ErrorOnPageException("Failed EFTEnforcement is not correct.The expect is:"+financialInfo.failedEFTEnforcement+", but displayed is:"+value);
		}

		value = this.getVoidReturnChargeDays();
		if(!value.equals(financialInfo.voidReturnChargeDays)) {
			logger.error("Void Return Charge Days is not correct.The expect is:"+financialInfo.voidReturnChargeDays+", but displayed is:"+value);
			throw new ErrorOnPageException("Void Return Charge Days is not correct.The expect is:"+financialInfo.voidReturnChargeDays+", but displayed is:"+value);
		}

		value = this.getAutoReturnVoidedDoc();
		if(!value.equals(financialInfo.autoReturnVoidedDoc)) {
			logger.error("Auto Return Voided Doc is not correct.The expect is:"+financialInfo.autoReturnVoidedDoc+", but displayed is:"+value);
			throw new ErrorOnPageException("Auto Return Voided Doc is not correct.The expect is:"+financialInfo.autoReturnVoidedDoc+", but displayed is:"+value);
		}

		HashMap<String, Boolean> rptNotificationOnPg = this.isRptNotifiCheckBoxSelected();
		if(!rptNotificationOnPg.equals(financialInfo.rptNotification)) {
			logger.error("Report Notification is not correct.");
			throw new ErrorOnPageException("Report Notification is not correct.");
		}

		String[] emails = this.getReportNotificationEmails();
		if(emails.length != financialInfo.reportEmails.size()) {
			logger.error("The number of mail is not correct.The expect is:"+financialInfo.reportEmails.size()+", and displayed is:"+emails.length);
			throw new ErrorOnPageException("The number of mail is not correct.The expect is:"+financialInfo.reportEmails.size()+", but displayed is:"+emails.length);
		}
		
		for(int i = 0 ; i < emails.length; i++) {
			if(!financialInfo.reportEmails.contains(emails[i])) {
				logger.error("Report Notification Emails are not correct.The expect is:"+financialInfo.reportEmails.get(i)+", but displayed is:"+emails[i]);
				throw new ErrorOnPageException("Report Notification Emails are not correct.The expect is:"+financialInfo.reportEmails.get(i)+", but displayed is:"+emails[i]);
			}
		}
	}
	
	public boolean compareFinancialInfo(LicMgrVendorFinConfigInfo financialInfo) {
		boolean passed = true;
		String value = this.getEFTType();
		passed &= MiscFunctions.compareResult("EFT Type:", financialInfo.eftType, 
				value);
		
		value = this.getEFTSchedule();
		passed &= MiscFunctions.compareResult("EFT Schedule:", financialInfo.invoiceSchedule, 
				value);
		
		value = this.getFailedEFTEnforcement();
		passed &= MiscFunctions.compareResult("Failed EFTEnforcement:", financialInfo.failedEFTEnforcement, 
				value);
		
		value = this.getVoidReturnChargeDays();
		passed &= MiscFunctions.compareResult("Void Return Charge Days:", financialInfo.voidReturnChargeDays, 
				value);
		
		value = this.getAutoReturnVoidedDoc();
		passed &= MiscFunctions.compareResult("Auto Return Voided Doc:", financialInfo.autoReturnVoidedDoc, 
				value);
		

		HashMap<String, Boolean> rptNotificationOnPg = this.isRptNotifiCheckBoxSelected();
		for(Map.Entry<String, Boolean> m:rptNotificationOnPg.entrySet()){
			if(m.getValue()){
				if((!financialInfo.rptNotification.containsKey(m.getKey()))||(!financialInfo.rptNotification.get(m.getKey())))
				{
					passed = false;
					logger.info("Report Notification is not correct:" + m.getKey() + " should not be selected, however, it is selected!");
				}
			}else{
				if(financialInfo.rptNotification.containsKey(m.getKey())){
					passed = false;
					logger.info("Report Notification is not correct:" + m.getKey() + " should be selected, however, it is unselected!");
				}
			}
		}
		
		String[] emails = this.getReportNotificationEmails();
		if(emails.length != financialInfo.reportEmails.size()) {
			logger.error("The number of mail is not correct.The expect is:"+financialInfo.reportEmails.size()+", and displayed is:"+emails.length);
		    passed = false;
		}
		for(int i = 0 ; i < emails.length; i++) {
			if(!financialInfo.reportEmails.contains(emails[i])) {
				logger.error("Report Notification Emails are not correct.The expect is:"+financialInfo.reportEmails.get(i)+", but displayed is:"+emails[i]);
				passed = false;
			}
		}
		return passed;
	}
}
