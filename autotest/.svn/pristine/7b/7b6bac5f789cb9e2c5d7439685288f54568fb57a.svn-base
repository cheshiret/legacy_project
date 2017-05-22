/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Events.UIS
 * @Task#:AUTO-560
 * 
 * @author bzhang
 * @Date  May 16, 2011
 */
public class OrmsEventHistoryPage extends OrmsPage{
	
	private static OrmsEventHistoryPage _instance = null;
	
	protected OrmsEventHistoryPage(){}
	
	public static OrmsEventHistoryPage getInstance(){
		if(_instance == null){
			_instance = new OrmsEventHistoryPage();
		} 
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Date & Time");
	}
	
	public IHtmlObject[] getEventHistoryTable(){
		return browser.getTableTestObject(Property.addToPropertyArray(table(), ".id", new RegularExpression("^grid_[0-9]",false)),getTransactionFrame()[0]);
	}
	
	public void clickReturnEventDetailBtn(){
		browser.clickGuiObject(".class","Html.A",".text", "Return Event Detail",true);
	}
	
	/**
	 * Click the first matching reservation number on event history page
	 * @param resNum
	 */
	public void clickResNum(String resNum){
		browser.clickGuiObject(".class", "Html.A", ".text", resNum,true);
	}
		
}
