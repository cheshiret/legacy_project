package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class InvMgrCommonTopMenuPage extends InventoryManagerPage {

	/** Click on Home link from top menu. */
	public void clickHome() {
		RegularExpression rex = new RegularExpression("right\\.menu\\.id\\.\\d+", false);
		browser.clickGuiObject(".id",rex,".text","Home");
	}

	/** Click on Change Request Mode link from top menu. */
	public void clickChangeRequestMode() {
		browser.clickGuiObject(".class", "Html.A",".text", "Change Request Mode");
	}
	
	/** Click on Change Immediate Mode link from top menu. */
	public void clickChangeImmediateMode() {
		browser.clickGuiObject(".class", "Html.A",".text", "Change Immediate Mode");
	}

	/** Click on Switch link from top menu. */
	public void clickSwitch() {
		RegularExpression rex = new RegularExpression("right\\.menu\\.id\\.\\d+", false);
		browser.clickGuiObject(".id",rex,".text","Switch");
	}

	/** Click on Sign Out link from top menu. */
	public void clickSignOut() {
//		RegularExpression rexId = new RegularExpression("right\\.menu\\.id\\.\\d+", false);
		RegularExpression rexText = new RegularExpression("[S|s]ign [O|o]ut", false);
		browser.clickGuiObject(".class","Html.A",".text",rexText);
	}

	/** Click on Select Facility link from top menu. */
	public void clickSelectFacility() {
		RegularExpression rex = new RegularExpression("[S|s]elect [F|f]acility", false);
		browser.clickGuiObject(".class","Html.A",".text",rex);
	}
	
	public void clickSelectWarehouse(){
		browser.clickGuiObject(".class","Html.A",".id","selectWarehouse");
	}

	/** Go to specified pages between facility details from top menu. 
	 *  @param pageName - the page name in the dropdown list
	 */
	public void gotoSpecifiedDetailPage(String pageName) {
		browser.selectDropdownList(".id", "page_name", pageName, true);
	}
	
	public String getDefaultPageName() {
		return browser.getDropdownListValue(".id", "page_name", 0);
	}
	
	public List<String> getTopDropDownListElements(){
		return browser.getDropdownElements(".id", "page_name");
	}
	
	/** Check top Menu exists or not */
	public boolean checkTopDropDownListExist() {
		return browser.checkHtmlObjectExists(".id", "page_name");
	}
	
	public boolean checkSpecificDetailsPageExist(String pageName){
	  boolean checkExist=false;
	  
	  String temp = browser.getObjectText(".id", "page_name");
	  if(temp.contains(pageName)){
	    	checkExist = true;
	  }
	  return checkExist;
	}
	
	public boolean checkSpecificDetailsPageSelect(String pageName){
		  boolean checkSelect =false;
		  
		  String temp = browser.getDropdownListValue(".id", "page_name", 0);
		  if(temp.equalsIgnoreCase(pageName)){
			  checkSelect = true;
		  }
		  return checkSelect;
	}
	
	public boolean checkSelectFacilityExist(){
		 return browser.checkHtmlObjectExists(".class","Html.A",".text","Select Facility");
		}
	
	public void clickMap(){
	    browser.clickGuiObject(".class","Html.A",".text","Map");
	}
	
	public void clickMarinaMap(){
	    browser.clickGuiObject(".class","Html.A",".text","Marina Map");
	}
	
	public void clickLotteries(){
		browser.clickGuiObject(".class","Html.A",".text","Lotteries");
	}
	
	public boolean checkPOSSetUpLinkExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "POS Master Setup (under construction)");
	}

	/**
	 * Verify whether is the change request mode.
	 * @return
	 */
	public boolean isChangeRequestMode() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A",".text", "Change Immediate Mode");
		Browser.unregister(objs);
		
		return objs.length>0;
	}

	public boolean isChangeRequestModeLinkExisted() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Request Mode");
	}
}
