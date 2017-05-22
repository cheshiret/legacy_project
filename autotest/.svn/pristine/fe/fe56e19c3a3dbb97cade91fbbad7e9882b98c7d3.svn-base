/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;

/**
 * @ScriptName LicMgrHarvestQuestionsListPage.java
 * @Date:Mar 23, 2011
 * @Description:
 * @author asun
 */
public class LicMgrHarvestQuestionsListPage extends LicMgrProductPage {

	private static  LicMgrHarvestQuestionsListPage instance=null;
	
	private LicMgrHarvestQuestionsListPage(){}
	
	public static LicMgrHarvestQuestionsListPage getInstance(){
		if(instance==null){
			instance=new LicMgrHarvestQuestionsListPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","harvest_questionaire_grid");
	}
	
	public void clickID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}

}
