package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FinancialConfig;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class LicMgrStoreFinancialConfigPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreFinancialConfigPage _instance = null;
	
	protected LicMgrStoreFinancialConfigPage() {}
	
	public static LicMgrStoreFinancialConfigPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreFinancialConfigPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdEnforcement", false));
	}
	
	/**
	 * Get payment types info
	 * Payment Group: Cash/Credit Card/Non Cash Depositable/Non Depositable,.etc
	 * Payment Type: Cash/MasterCard, Visa/Cretified Check, Money Order, Personal Check, Travellers Check/GIFT 4 PARKS
	 * @return
	 */
	public Map<String, String[]> getPaymentTypes() {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^Payment Group", false));
		if(objs.length < 2) {
			throw new ErrorOnDataException("Can't find Payment Types table obejct.");
		}

		IHtmlTable table = (IHtmlTable)objs[1];
		if(!table.getCellValue(0, 0).trim().equalsIgnoreCase("Payment Group") || !table.getCellValue(0, 1).trim().equalsIgnoreCase("Payment Type")) {
			throw new ErrorOnDataException("Table structure is wrong.");
		}
		Map<String, String[]> paymentTypes = new HashMap<String, String[]>();
		String types[] = null;
		for(int i = 1; i < table.rowCount(); i ++) {
			if(!table.getCellValue(0, 0).trim().equalsIgnoreCase("Payment Group") || !table.getCellValue(0, 1).trim().equalsIgnoreCase("Payment Type")) {
				throw new ErrorOnDataException("Table structure is wrong.");
			}
			types = table.getCellValue(i, 1).trim().split(", ");
			paymentTypes.put(table.getCellValue(i, 0).trim(), types);
		}
		
		Browser.unregister(objs);
		return paymentTypes;
	}
	
	/**
	 * Select payment types by checking corresponding check boxes
	 * @param types - it should be: POS, Cash, Certified Check, Charged, Credit Card, MoneyOrder, Personal Check, Purchase Order, Other
	 */
	public void selectPaymentTypes(String... types) {
		for(String type : types) {
			browser.clickGuiObject(".text", new RegularExpression("^" + type, false));
		}
	}
	
	/**
	 * Select an option of 'Sales Threshold Enforcement' drop down list
	 * @param salesThreshold
	 */
	public void selectSalesThresholdEnforcement(String salesThresholdEnforcement) {
		browser.selectDropdownList(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdEnforcement", false), salesThresholdEnforcement, true);
		ajax.waitLoading();
	}
	
	/**
	 * Set value to 'Threshold Amount' text field
	 * @param thresholdAmount
	 */
	public void setThresholdAmount(String thresholdAmount) {
		browser.setTextField(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdAmnt:ZERO_TO_NULL", false), thresholdAmount, true);
	}
	
	/**
	 * Select an option of 'Sales Threshold Action' drop down list
	 * @param salesThresholdAction
	 */
	public void selectSalesThresholdAction(String salesThresholdAction) {
		browser.selectDropdownList(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdAction", false), salesThresholdAction, true);
	}
	
	/**
	 * Select an option of 'Revoke If Bond Expired' drop down list
	 * @param value
	 */
	public void selectRevokeIfBondExpired(String value) {
		browser.selectDropdownList(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.revokeIfBondExpired", false), value, true);
	}
	
	/**
	 * Set value to 'Threshold Action Notification Email' text fields
	 * @param emial
	 * @param objIndex
	 */
	public void setThresholdActionNotificationEmail(String email, int objIndex) {
		browser.setTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress:CB_TO_NAME", false), email, true, objIndex);
	}
	
	public void setThresholdActionNotificationEmail(String email) {
		browser.setTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress:CB_TO_NAME", false), email, true);
	}
	
	public int getThresholdActionNotificationEmailCount() {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress:CB_TO_NAME", false));
		
		int toReturn = objs.length;
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Get threshold action notification emails
	 * @return
	 */
	public String[] getThresholdActionNotificationEmails() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress:CB_TO_NAME", false));
		String emails[] = new String[objs.length];
		for(int i = 0; i < emails.length; i ++) {
			emails[i] = objs[i].getProperty(".value").trim();
		}
		
		Browser.unregister(objs);
		return emails;
	}
	
	/**
	 * Get the 'email' corresponding object index
	 * @param email
	 * @return
	 */
	public int getThresholdActionNotificationEmailIndex(String email) {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("EmailDynamicTableView-\\d+\\.emailAddress:CB_TO_NAME*", false));
		if(objs.length < 0) {
			throw new ObjectNotFoundException("Can't find any Threshold Action Notification Email object.");
		}
		int toReturn = -1;
		for(int i = 0; i < objs.length; i ++) {
			String text = ((IText)objs[i]).getText();
			if(text.trim().equals(email)) {
				toReturn = i;
				break;
			}
		}
		
		if(toReturn == -1) {
			throw new ErrorOnPageException("Can't find Threshold Action Notification Email object by " + email);
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Click remove to remove the specific email text field
	 * @param index
	 */
	public void removeEmailTextFieldByIndex(int index) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", "Remove");
		if(index >= objs.length) {
			throw new ErrorOnPageException("There is only " + objs.length + " email text field objets. But index " + index + " is out of size.");
		}
		Browser.unregister(objs);
		
		this.clickRemove(index);
		ajax.waitLoading();
	}
	
	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	public void clickRemove(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}
	
	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	public void clickSave() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save");
	}
	
	public String getThresholdEnforcement(){
		return browser.getDropdownListValue(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdEnforcement",false), 0);
	}
	
	public String getThresholdAmount(){
		return browser.getTextFieldValue(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdAmnt:ZERO_TO_NULL",false));
	}
	
	public String getThresholdAction(){
		return browser.getDropdownListValue(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.thresholdAction",false));
	}
	
	public boolean checkRevokeIfBondExpiredExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.revokeIfBondExpired", false));
	}
	
	public String getRevokeIfBondExpired() {
		return browser.getDropdownListValue(".id", new RegularExpression("StoreFinancialConfigView-\\d+\\.revokeIfBondExpired", false));
	}
	
	public String[] getReportNotificationEmails(){
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("^EmailDynamicTableView-[0-9]*.emailAddress:CB_TO_NAME",false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no Text which id = EmailDynamicTableView-[0-9]*.emailAddress:CB_TO_NAME");
		}
		String[] list = new String[objs.length];
		for(int i=0;i<objs.length;i++){
			list[i]=((IText)objs[i]).getText().trim();
		}
		Browser.unregister(objs);
		
		return list;
	}
	
	public boolean isCheckBoxSelectedByIndex(int i){
		IHtmlObject objs[] = browser.getCheckBox(".id", new RegularExpression("^StoreFinancialConfigView-[0-9]*.deferredPmtType_[0-9]*", false));
		
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no check box which id = StoreFinancialConfigView-\\d\\.deferredPmtType_\\d");
		}
		
		boolean flag = ((ICheckBox)objs[i]).isSelected();
		
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean getDefferedPmtType(String pmtType){
		List<String> pmtTypes = new ArrayList<String>();
		pmtTypes.add("POS");
		pmtTypes.add("Cash");
		pmtTypes.add("Certified Check");
		pmtTypes.add("Charged");
		pmtTypes.add("Credit Card");
		pmtTypes.add("Money Order");
		pmtTypes.add("Personal Check");
		pmtTypes.add("Purchase Order");
		pmtTypes.add("Other");
		
		boolean flag = false;
		int index = pmtTypes.indexOf(pmtType);
		
		switch(index){
		case 0:
			flag = isCheckBoxSelectedByIndex(0);
			break;
		case 1:
			flag = isCheckBoxSelectedByIndex(1);
			break;
		case 2:
			flag = isCheckBoxSelectedByIndex(2);
			break;
		case 3:
			flag = isCheckBoxSelectedByIndex(3);
			break;
		case 4:
			flag = isCheckBoxSelectedByIndex(4);
			break;
		case 5:
			flag = isCheckBoxSelectedByIndex(5);
			break;
		case 6:
			flag = isCheckBoxSelectedByIndex(6);
			break;
		case 7:
			flag = isCheckBoxSelectedByIndex(7);
			break;
		case 8:
			flag = isCheckBoxSelectedByIndex(8);
			break;
		}
		
		return flag;
	}
	
	/**
	 * Set up store financial config detail info
	 * @param config
	 */
	public void setupFinancialConfigInfo(FinancialConfig config) {
		if(config.deferredPaymentTypes != null && config.deferredPaymentTypes.length > 0) {
			this.selectPaymentTypes(config.deferredPaymentTypes);
		}
		this.selectSalesThresholdEnforcement(config.thresholdEnforcement);
		if(config.thresholdEnforcement.equals("Threshold Amount")) {
			this.setThresholdAmount(config.thresholdAmount);
		}
		this.selectSalesThresholdAction(config.thresholdAction);
		if(this.checkRevokeIfBondExpiredExists()) {
			this.selectRevokeIfBondExpired(config.revokeIfBondExpired ? "Yes" : "No");
		}
		int existingEmailCount = this.getThresholdActionNotificationEmailCount();
		//if the existing notification email text fields less than email count to input, click Add button
		if(existingEmailCount < config.notificationEmails.length) {
			for(int i = 0; i < config.notificationEmails.length - existingEmailCount; i ++) {
				this.clickAdd();
				ajax.waitLoading();
			}
		}
		//if the existing notification email text fields greater than email count to input, click Remove button
		if(existingEmailCount > config.notificationEmails.length) {
			for(int i = 0; i < existingEmailCount - config.notificationEmails.length; i ++) {
				this.clickRemove();
				ajax.waitLoading();
			}
		}
		//set the all notification email to corresponding text field
		for(int i = 0; i < config.notificationEmails.length; i ++) {
			this.setThresholdActionNotificationEmail(config.notificationEmails[i], i);
		}
		
		int removeIndex = -1;
		List<String> tempList = new ArrayList<String>();
		for(int i = 0 ; i < config.notificationEmailsToRemove.length; i ++) {
			removeIndex = this.getThresholdActionNotificationEmailIndex(config.notificationEmailsToRemove[i]);
			this.removeEmailTextFieldByIndex(removeIndex);
			for(int j = 0; j < config.notificationEmails.length; j ++) {
				if(!config.notificationEmails[j].equals(config.notificationEmailsToRemove[i])) {
					tempList.add(config.notificationEmails[j]);
				}
			}
		}
		config.notificationEmails = tempList.toArray(new String[0]);
	}
	
	/**
	 * Get the financial config info
	 * @return
	 */
	public FinancialConfig getFinancialConfigInfo() {
		FinancialConfig config = new FinancialConfig();
		
		config.thresholdEnforcement = this.getThresholdEnforcement();
		config.thresholdAmount = this.getThresholdAmount();
		config.thresholdAction = this.getThresholdAction();
		if(this.checkRevokeIfBondExpiredExists()) {
			config.revokeIfBondExpired = this.getRevokeIfBondExpired().equals("Yes") ? true : false;
		}
		config.notificationEmails = this.getReportNotificationEmails();
		
		return config;
	}
	
	/**
	 * Compare store financial config info
	 * @param expectedConfig
	 * @return
	 */
	public boolean compareFinancialConfigInfo(FinancialConfig expectedConfig) {
		boolean result = true;
		FinancialConfig actualConfig = this.getFinancialConfigInfo();
		if(!actualConfig.thresholdEnforcement.equals(expectedConfig.thresholdEnforcement)) {
			logger.error("Expected Sales Threshold Enforcement is " + expectedConfig.thresholdEnforcement + ", but actual value is " + actualConfig.thresholdEnforcement);
			result &= false;
		}
		if(!actualConfig.thresholdAmount.equals(expectedConfig.thresholdAmount)) {
			logger.error("Expected Threshold Amount($) is " + expectedConfig.thresholdAmount + ", but actual value is " + actualConfig.thresholdAmount);
			result &= false;
		}
		if(!actualConfig.thresholdAction.equals(expectedConfig.thresholdAction)) {
			logger.error("Expected Sales Threshold Action is " + expectedConfig.thresholdAction + ", but actual value is " + actualConfig.thresholdAction);
			result &= false;
		}
		if(checkRevokeIfBondExpiredExists()) {
			if(actualConfig.revokeIfBondExpired != expectedConfig.revokeIfBondExpired) {
				logger.error("Expected Revoke If Bond Expired is " + expectedConfig.revokeIfBondExpired + ", but actual value is " + actualConfig.revokeIfBondExpired);
				result &= false;
			}
		}
		for(int i = 0; i < actualConfig.notificationEmails.length; i ++) {
			if(!actualConfig.notificationEmails[i].equals(expectedConfig.notificationEmails[i])) {
				logger.error("Expected one of Thresold Action Notification Email is " + expectedConfig.notificationEmails[i] + ", but actual value is " + actualConfig.notificationEmails[i]);
				result &= false;
			}
		}
		
		return result;
	}
}
