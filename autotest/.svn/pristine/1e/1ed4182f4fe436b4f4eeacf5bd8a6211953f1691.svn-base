/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;


/**
 * @ScriptName LicMgrTopMenuPage.java
 * @Date:Dec 24, 2010
 * @Description:
 * @author asun
 */
public class LicMgrTopMenuPage extends LicMgrCommonTopMenuPage {
	
	private static LicMgrTopMenuPage instance=null;
	
	protected LicMgrTopMenuPage(){
		
	}
	
	public static LicMgrTopMenuPage getInstance(){
		if(instance == null){
			instance=new LicMgrTopMenuPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
//		RegularExpression regx=new RegularExpression("licensemanager.leftmenu.*",false);
		return browser.checkHtmlObjectExists(".id","field_search_dropdown");
	}
}
