package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.flex.Flex;
import com.activenetwork.qa.testapi.interfaces.flex.IFlex;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.Property;

public abstract class SupportCenterPage extends Page {
	protected IBrowser browser = Browser.getInstance();
	protected IFlex flex=Flex.getInstance();
		
	protected Property[] flexapp() {
		return Property.toPropertyArray(".class","FlexApplication","id","supportcenter");
	}
	
	protected Property[] logout() {
		return Property.toPropertyArray(".class", "Html.A",".text","Logout");
	}
	
	protected Property[] launchPad() {
		return Property.toPropertyArray(".class", "Html.A",".text","Launch Pad");
	}
	
	public void clickLogout(){
		browser.clickGuiObject(logout());
	}
	
	public void gotoLaunchPad(){
		browser.clickGuiObject(launchPad());
	}
}
