package com.activenetwork.qa.awo.pages.orms.systemManager;


import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Start Document Fulfillment page, system manager -> Utilities -> Start Document Fulfillment
 * @author Lesley Wang
 * @Date  Sep 10, 2013
 */
public class SysMgrDocumentFulfillmentPage extends SysMgrCommonTopMenuPage {
	
	private static SysMgrDocumentFulfillmentPage _instance=null;
	
	public static SysMgrDocumentFulfillmentPage getInstance(){
		if(_instance==null){
			_instance=new SysMgrDocumentFulfillmentPage();
		}
		return _instance;
	}
	
	protected SysMgrDocumentFulfillmentPage(){}

	protected Property[] startBtnProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Start",false));
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.startBtnProp());
	}
	
	public void clickStart() {
		browser.clickGuiObject(this.startBtnProp());
	}
}
