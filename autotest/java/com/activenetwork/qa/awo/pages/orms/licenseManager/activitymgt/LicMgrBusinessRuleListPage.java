package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @author pchen
 *
 */
public class LicMgrBusinessRuleListPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrBusinessRuleListPage _instance = new LicMgrBusinessRuleListPage();
	}

	protected LicMgrBusinessRuleListPage() {
	}

	public static LicMgrBusinessRuleListPage getInstance() {
		return SingletonHolder._instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(activityBusinessRulResultGrid());
	}
	
	protected Property[] activityBusinessRulResultGrid(){
		return Property.concatPropertyArray(div(), ".id", "ActivityBusinessRuleGird_groupByGrid_with _pagingBar");
	}
	
	protected Property[] addBusinessRuleBtn(){
		return Property.concatPropertyArray(a(), ".text", "Add Business Rule");
	}
	
	public void clickAddBusinessRule(){
		browser.clickGuiObject(this.addBusinessRuleBtn());
	}

}
