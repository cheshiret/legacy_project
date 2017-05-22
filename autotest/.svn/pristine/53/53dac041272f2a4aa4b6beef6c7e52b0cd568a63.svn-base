package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * ScriptName: LicMgrAddVendorBankAccountWidget
 * Description:
 * @date: Mar 28, 2011-1:44:34 AM
 * @author QA-qchen
 *
 */
public class LicMgrAddVendorBankAccountWidget extends DialogWidget {
	public static LicMgrAddVendorBankAccountWidget _instance = null;
	
	protected LicMgrAddVendorBankAccountWidget() {}
	
	public static LicMgrAddVendorBankAccountWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAddVendorBankAccountWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("VendorBankAccountView-\\d+\\.acctType", false));
	}
	
	/**
	 * Select a option in Account Type drop down list
	 * @param type
	 */
	public void selectAccountType(String type) {
		if(null == type || type.length() == 0) {
			throw new ErrorOnDataException("The Account Type value can't be null.");
		}
		browser.selectDropdownList(".id", new RegularExpression("VendorBankAccountView-\\d+\\.acctType", false), type, true);
	}
	
	/**
	 * Set routing number
	 * @param routingNum
	 */
	public void setRoutingNum(String routingNum) {
		browser.setTextField(".id", new RegularExpression("VendorBankAccountView-\\d+\\.routingNum", false), routingNum, true);
		ajax.waitLoading();
	}
	
	/**
	 * Set account number
	 * @param accountNum
	 */
	public void setAccountNum(String accountNum) {
		browser.setTextField(".id", new RegularExpression("VendorBankAccountView-\\d+\\.acctNum", false), accountNum, true);
		ajax.waitLoading();
	}
	
	/**
	 * Get Account Type elements list
	 * @return
	 */
	public List<String> getAccountTypeElements() {
		return browser.getDropdownElements(".id", new RegularExpression("VendorBankAccountView-\\d+\\.acctType", false));
	}
	
	/**
	 * Get account routing number value
	 * @return
	 */
	public String getRoutingNum() {
		return browser.getTextFieldValue(".id", new RegularExpression("VendorBankAccountView-\\d+\\.routingNum", false));
	}
	
	/**
	 * Get account number value
	 * @return
	 */
	public String getAccountNum() {
		return browser.getTextFieldValue(".id", new RegularExpression("VendorBankAccountView-\\d+\\.acctNum", false));
	}
	
	/**
	 * Check whether a specific object is editable or not
	 * @param name - "Status"/"Prenote Status"
	 * @return
	 */
	private boolean checkEditableByName(String name) {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + name, false));
		IHtmlObject objs[] = browser.getHtmlObject(property);
		boolean isEditable = true;
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find and attribute by the arribute name - " + name);
		}
		
		int index = 0;
		if(name.equalsIgnoreCase("Status")) {
			index = 1;
		}
		
		IHtmlObject[] spanObj = browser.getHtmlObject(".class", "Html.SPAN",objs[index]);
		isEditable = Boolean.parseBoolean(spanObj[spanObj.length-1].getProperty("isContentEditable").trim());
		
		Browser.unregister(spanObj);
		Browser.unregister(objs);
		
		return isEditable;
	}
	
	public boolean checkStatusEditable() {
		return this.checkEditableByName("Status");
	}
	
	public boolean checkPrenoteStatusEditable() {
		return this.checkEditableByName("Prenote Status");
	}
	
	/**
	 * Get the attribute value by name
	 * @param attributeName
	 * @return
	 */
	private String getValueByName(String attributeName) {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		IHtmlObject objs[] = browser.getHtmlObject(property);
		String value = "";
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find and attribute by the arribute name - " + attributeName);
		}
		
		if(attributeName.equalsIgnoreCase("Status")) {
			value = objs[1].getProperty(".text").split(attributeName)[1].split("\\*")[1].trim();
		} else {
			value = objs[0].getProperty(".text").split(attributeName)[1].trim();
		}
		
		return value;
	}
	
	/**
	 * Get ID value
	 * @return
	 */
	public String getID() {
		return getValueByName("ID");
	}
	
	/**
	 * Get status value
	 * @return
	 */
	public String getStatus() {
		return getValueByName("Status");
	}
	
	/**
	 * Get the prenote status value
	 * @return
	 */
	public String getPrenoteStatus() {
		return getValueByName("Prenote Status");
	}
	
	/**
	 * Get the error message displayed at the widget top
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
	public void setBankName(String bankName){
		browser.setTextField(".id", new RegularExpression("VendorBankAccountView-\\d+.bankName", false), bankName);
	}

	public void setBankBranchName(String banBranchkName){
		browser.setTextField(".id", new RegularExpression("VendorBankAccountView-\\d+.branchName", false), banBranchkName);
	}
	
	/**
	 * Set bank account details information in add vendor bank account widget
	 * @param bankAccount
	 */
	public void setBankAccountInfo(VendorBankAccountInfo bankAccount) {
		if(null != bankAccount) {
			selectAccountType(bankAccount.accountType);
			setRoutingNum(bankAccount.routingNum);
			setAccountNum(bankAccount.accountNum);
			if(null != bankAccount.getBankName()){
				this.setBankName(bankAccount.getBankName());
			}
			if(null != bankAccount.getBankBranchName()){
				this.setBankBranchName(bankAccount.getBankBranchName());
			}
		} else {
			throw new ActionFailedException("The value of VendorBankAccountInfo is null. Please double check it.");
		}
	}
}
