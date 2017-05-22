package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;


public class SysMgrProcessDailyEFTPage extends SysMgrCommonTopMenuPage{
	private static SysMgrProcessDailyEFTPage _instance=null;
	
    protected SysMgrProcessDailyEFTPage(){}
	
	public static SysMgrProcessDailyEFTPage getInstance(){
		if(_instance==null){
			_instance=new SysMgrProcessDailyEFTPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Process");
	}
	
	public void selectUseCheckBox(){
		browser.selectCheckBox(".id", "useCurrentPeriodEndTimeMinus15Min");
	}
	
	public void clickProcess(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Process");
	}
	
	public String getNote(){
		IHtmlObject objs[] = browser.getHtmlObject(".id", "NOTSET");
		String msg = objs[0].getProperty(".text").trim();
		return msg;
	}
}
