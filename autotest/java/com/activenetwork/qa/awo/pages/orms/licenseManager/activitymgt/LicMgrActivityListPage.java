package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class LicMgrActivityListPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrActivityListPage _instance = new LicMgrActivityListPage();
	}

	protected LicMgrActivityListPage() {
	}

	public static LicMgrActivityListPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] addActivityProduct(){
		return Property.toPropertyArray(".id", "Anchor", ".text", "Add Activity Product");
	}
	
	protected Property[] activityListGrid(){
		return Property.concatPropertyArray(div(), ".id", "AwoActivityListGrid");
	}
	
	/** Page Object Property Definition END */
	public boolean exists() {
		return browser.checkHtmlObjectExists(activityListGrid());
	}
	
	public void clickAddActivityProduct(){
		browser.clickGuiObject(addActivityProduct());
	}
	
	public void clickActivityCode(String code){
		PagingComponent paging = new PagingComponent();
		boolean exist = false;
		do{              
			exist = browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", code);
        } while(!exist && paging.clickNext());
		browser.clickGuiObject(".class", "Html.A", ".text", code);
	}
	
}
