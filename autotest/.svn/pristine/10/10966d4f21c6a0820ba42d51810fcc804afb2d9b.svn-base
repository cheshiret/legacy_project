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
 * @Date  Oct 11, 2013
 */
public class LicMgrLotteryExecutionConfigListPage extends LicMgrLotteryProductCommonPage {

	private static LicMgrLotteryExecutionConfigListPage _instance = null;
	
	private LicMgrLotteryExecutionConfigListPage() {}
	
	public static LicMgrLotteryExecutionConfigListPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryExecutionConfigListPage();
		return _instance;
	}
	
	private Property[] addLotteryExecutionConfig() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add Lottery Execution Config");
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigSearchCriteria-\\d+\\.status", false));
	}
	
	private Property[] go() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Go|Search", false));
	}
	
	private Property[] executionConfigListTable() {
		return Property.toPropertyArray(".id", "execConfigListGrid");
	}
	
	public void clickAddLotteryExecutionConfig() {
		browser.clickGuiObject(addLotteryExecutionConfig());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(status(), index);
	}
	
	public void clickGo() {
		browser.clickGuiObject(go());
	}
	
	private IHtmlObject[] getExecutionConfigListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(executionConfigListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Execution Config list table object.");
		return objs;
	}
	
	private Property[] id(String id) {
		return Property.toPropertyArray(".class", "Html.A", ".text", id);
	}
	
	public void clickExecutionConfig(String id) {
		browser.clickGuiObject(id(id));
	}
}
