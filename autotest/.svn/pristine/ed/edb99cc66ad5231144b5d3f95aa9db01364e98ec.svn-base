package com.activenetwork.qa.awo.pages.orms.inventoryManager.list;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrListDetailCommonPage extends InvMgrTopMenuPage{
	private static InvMgrListDetailCommonPage _instance = null;
	protected InvMgrListDetailCommonPage(){}
	
	public static InvMgrListDetailCommonPage getInstance(){
		if(null == _instance){
			_instance = new InvMgrListDetailCommonPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "SubTabOfList");
	}
	
	public void clickListLabel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "List");
	}
	
	public void clickListDetailsLabel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "List Details");
	}
	
	public void clickListParticipationLabel(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "List Participation");
	}
	
	public String getListID(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("WaitingListProductView-\\d+\\.id\\:ZERO_TO_NEW",false));//Quentin[20131016] 3.05 UI changes
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not get List ID Span Object.");
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not get List ID SPAN Object.");
		}
		
		String text = spanObjs[spanObjs.length-1].text();
		Browser.unregister(divObjs);
		Browser.unregister(spanObjs);
		
		return text;
	}
	
	/**
	 * Get list name on the top of list details page
	 * @return
	 */
	public String getListName(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("WaitingListProductView-\\d+\\.name",false));
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not get List Name SPAN Object.");
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not get List Name SPAN Object.");
		}
		
		String text = spanObjs[0].text().replaceAll("List Name", StringUtil.EMPTY).trim();
		Browser.unregister(divObjs);
		Browser.unregister(spanObjs);
		
		return text;
	}
	
	public String getListStatus(){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("WaitingListProductView-\\d+\\.name",false));
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not get List Name Div Object.");
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not get List Name SPAN Object.");
		}
		
		String text = spanObjs[0].text();
		Browser.unregister(divObjs);
		Browser.unregister(spanObjs);
		
		return text;
	}
	
}
