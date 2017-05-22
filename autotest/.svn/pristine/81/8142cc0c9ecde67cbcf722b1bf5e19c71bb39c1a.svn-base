package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LotteryParticipationPage extends InvMgrCommonTopMenuPage{
	private static LotteryParticipationPage _instance = null;
	private String assignNew = "Assign New";


	public static LotteryParticipationPage getInstance() {
		if (null == _instance) {
			_instance = new LotteryParticipationPage();
		}

		return _instance;
	}

	protected LotteryParticipationPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^Participating Location Participating Product Groups Participating Products.*",false));
	}
	
	public void selectAll(){
		browser.clickGuiObject(".name","all_slct");
	}
	
	public void clickAssignNew(){
		browser.clickGuiObject(".class","Html.A",".text","Assign New");
	}
	
	public void clickUnassign(){
		browser.clickGuiObject(".class","Html.A",".text","Unassign");
	}
	
	public String getLotteryInfo(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Participating Location Participating Product Groups Participating Products.*",false));
		String lotteryInfo=((IHtmlTable)objs[0]).text();
		Browser.unregister(objs);
		
		return lotteryInfo;
	}
	
	public List<String> getLotteryParticipationInfo(String location){
		IHtmlObject[] objs=browser.getTableTestObject(".class","Html.TABLE",".text",new RegularExpression("^Participating Location Participating Product Groups Participating Products.*",false));
		int row = ((IHtmlTable)objs[0]).findRow(1, location);
		List<String> locationInfo = new ArrayList<String>();
		if(row != -1){
			locationInfo = ((IHtmlTable)objs[0]).getRowValues(row);
		}
		Browser.unregister(objs);
		
		return locationInfo;
	}
	
	public boolean checkLinkObjectExistsByText(String text){
		return browser.checkHtmlObjectExists(".class","Html.A",".text",text);
	}
	
	public boolean isAssignNewLinkEnabled(){
		return this.checkLinkObjectExistsByText(assignNew);
	}
	
}
