/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;

/**
 * @ScriptName CallMgrTourAlertsPage.java
 * @Date:Oct 11, 2010
 * @author asun
 */
public class OrmsTourAlertsPage extends OrmsPage {

	private static OrmsTourAlertsPage instance=null;
	
	private OrmsTourAlertsPage(){
		
	}
	
	public static OrmsTourAlertsPage getInstance(){
		if(instance == null){
			instance=new OrmsTourAlertsPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "tourAlerts");
	}
	
	public void clickOkButton(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}
	
	public void clickCancelButton(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}

}
