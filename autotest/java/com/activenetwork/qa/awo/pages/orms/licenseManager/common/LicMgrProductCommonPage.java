/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrProductPage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public abstract class LicMgrProductCommonPage extends LicMgrCommonTopMenuPage {
	
	public void clickPrivilegesTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text", new RegularExpression("Privileges|Licences", false));
	}
	
	public void clickConsumablesTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Consumables");
	}
	
	public void clickVehiclesTab(){
		IHtmlObject[] tops=browser.getHtmlObject(".class", "Html.DIV", ".id", "ProductsTab");
		if(tops.length<1){
			throw new ObjectNotFoundException("Can't find the 'Vehicles' link...");
		}
		browser.clickGuiObject(".class", "Html.SPAN",".text","Vehicles",false,0 ,tops[0]);
		Browser.unregister(tops);
	}
	
	public void clickHarvestQuestionsTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Harvest Questions");
	}
	
	public void clickSuppliesTab(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Supplies");
	}
	
	public String getWarningMessage(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".className", new RegularExpression("message msg(error|success)", false));
		
		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {// added by Peter Zhu, there will be more than one messages.
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
	
	public void clickSubTab(String subTab){
		browser.clickGuiObject(".class", "Html.A", ".text", subTab);
	}
}
