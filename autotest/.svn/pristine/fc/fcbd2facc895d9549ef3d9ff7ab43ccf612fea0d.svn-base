/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;


/**
 * @ScriptName LicMgrProductPage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public class LicMgrProductPage extends LicMgrProductCommonPage {

	private static LicMgrProductPage instance=null;
	
	protected LicMgrProductPage(){}
	
	public static LicMgrProductPage getInstance(){
		if(instance == null){
			instance=new LicMgrProductPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","ProductsTab");
	}

	//DO NOT ADD ANY LOGIC IN THIS METHOD!!!
	public boolean checkProductRecordExist(String prodCode) {
		// Do not change me, this method will be overrided by its child
		return true;
	}
	

}
