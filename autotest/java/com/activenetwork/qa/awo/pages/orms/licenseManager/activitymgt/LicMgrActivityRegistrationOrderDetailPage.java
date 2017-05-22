package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the order detail page for activity registration
 * How to get this page:Click "Orders" from the top menu, and click "Activity Registration Orders"
 * @author Phoebe
 */
public class LicMgrActivityRegistrationOrderDetailPage extends LicMgrOrderDetailsCommonPage {
	private static LicMgrActivityRegistrationOrderDetailPage _instance = null;
	private LicMgrActivityRegistrationOrderDetailPage() {}
	public static LicMgrActivityRegistrationOrderDetailPage getInstance() {
		if(_instance == null) _instance = new LicMgrActivityRegistrationOrderDetailPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TD",".text", "Activity Registration Customer");
	}

	private Property[] feesBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Fees");
	}
	
	private Property[] voidBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Void");
	}
	
	public void clickFees(){
		browser.clickGuiObject(this.feesBtn());
	}
	
	public void clickVoid() {
		browser.clickGuiObject(voidBtn());
	}

	public void clickVoidByActivityName(String name) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+name+".*", false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find order item by activity name "+name);
		browser.clickGuiObject(".class", "Html.A", ".text", "Void", true, 0, objs[objs.length-1]);
		Browser.unregister(objs);
	}
}
