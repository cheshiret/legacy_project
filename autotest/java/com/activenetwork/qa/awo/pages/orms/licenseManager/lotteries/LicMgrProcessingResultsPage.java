package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class LicMgrProcessingResultsPage extends LicMgrLotteriesCommonPage {
	private static LicMgrProcessingResultsPage _instance = null;
	
	private LicMgrProcessingResultsPage() {}
	
	public static LicMgrProcessingResultsPage getInstance() {
		if(_instance == null) _instance = new LicMgrProcessingResultsPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(processLottery());
	}
	
	private Property[] orderNum() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryEntrySearchCriteria-\\d+\\.orderNumber", false));
	}
	
	private Property[] MDWFPNum() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryEntrySearchCriteria-\\d+\\.customerNumber", false));
	}
	
	private Property[] firstName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryEntrySearchCriteria-\\d+\\.firstName", false));
	}
	
	private Property[] lastName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryEntrySearchCriteria-\\d+\\.lastName", false));
	}
	
	private Property[] reason() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryEntrySearchCriteria-\\d+\\.reasonCode", false));
	}
	
	private Property[] go() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	private Property[] processLottery() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Process Lottery");
	}
	
	private Property[] resultListTable() {
		return Property.toPropertyArray(".id", new RegularExpression("grid_\\d+_LIST", false));
	}
	
	private Property[] first() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "First");
	}
	
	private Property[] previous() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Previous");
	}
	
	private Property[] next() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Next");
	}
	
	private Property[] last() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Last");
	}
	
	private Property[] configurationTab() {
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("Configuration_\\d+", false), ".text", "Configuration");
	}
	
	private Property[] ExceptionTab() {
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("Configuration_\\d+", false), ".text", "Exceptions");
	}
	
	private Property[] resultTab() {
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("Configuration_\\d+", false), ".text", "Result");
	}
	
	public void setOrderNum(String num) {
		browser.setTextField(orderNum(), num);
	}
	
	public void setMDWFPNum(String num) {
		browser.setTextField(MDWFPNum(), num);
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(firstName(), fName);
	}
	
	public void setLastName(String lName) {
		browser.setTextField(lastName(), lName);
	}
	
	public void selectReason(String reason) {
		browser.selectDropdownList(reason(), reason);
	}
	
	public void clickGo() {
		browser.clickGuiObject(go());
	}
	
	public void clickProcessLottery() {
		browser.clickGuiObject(processLottery());
	}
	
	public void clickFirst() {
		browser.clickGuiObject(first());
	}
	
	public void clickPrevious() {
		browser.clickGuiObject(previous());
	}
	
	public void clickNext() {
		browser.clickGuiObject(next());
	}
	
	public void clickLast() {
		browser.clickGuiObject(last());
	}
	
	public void clickConfigurationTab(){
		browser.clickGuiObject(configurationTab());
	}
	
	public void clickExceptionsTab(){
		browser.clickGuiObject(ExceptionTab());
	}
	
	public void clickResultTab(){
		browser.clickGuiObject(resultTab());
	}
	
	private IHtmlObject[] getExceptionListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(resultListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Result list table object.");
		
		return objs;
	}
}
