package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeEditDisplayOrderPage extends LicMgrCommonTopMenuPage{
	
	private static LicMgrPrivilegeEditDisplayOrderPage _instance = null;
	
	protected LicMgrPrivilegeEditDisplayOrderPage(){}
	
	public static LicMgrPrivilegeEditDisplayOrderPage getInstance(){
		
		if(null == _instance){
			_instance = new LicMgrPrivilegeEditDisplayOrderPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		
		return browser.checkHtmlObjectExists(".id", 
				new RegularExpression("^PrivilegeProductLightView-\\d+\\.displayOrder.*",false));
	}
		
	public void setDisplayOrderForPrivilege(String privilegeCode, String order){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeProductGrid");
		
		Property[] trProperty = new Property[1];
		trProperty[0] = new Property(".class", "Html.TR");
		IHtmlObject[] trObjs = browser.getHtmlObject(trProperty, objs[0]);		

		IHtmlTable privilegeTable = (IHtmlTable) objs[0];		
		int row = privilegeTable.findRow(0, privilegeCode);
		
		Property[] displayOrderProperty = new Property[1];
		displayOrderProperty[0] = new Property(".id", new RegularExpression("^PrivilegeProductLightView-\\d+\\.displayOrder.*",false));
		IHtmlObject[] displayOrderObjs = browser.getHtmlObject(displayOrderProperty, trObjs[row]);
		
		String idOfDisplayOrderObj = displayOrderObjs[0].getProperty("id");
		browser.setTextField(".id", idOfDisplayOrderObj, order);
		
		Browser.unregister(displayOrderObjs);
		Browser.unregister(trObjs);
		Browser.unregister(objs);		
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
		ajax.waitLoading();
	}
	
	public void clickCancle(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
		ajax.waitLoading();
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "NOTSET").trim();
	}

}
