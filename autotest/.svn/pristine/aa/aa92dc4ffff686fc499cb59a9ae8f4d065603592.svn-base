package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

public class SysMgrEFTInvoicingPage extends SysMgrCommonTopMenuPage {
	private static SysMgrEFTInvoicingPage _instance = null;

	protected SysMgrEFTInvoicingPage() {
	}

	public static SysMgrEFTInvoicingPage getInstance() {
		if (_instance == null) {
			_instance = new SysMgrEFTInvoicingPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Run");
	}
	
	public void selectUseCheckBox(){
		browser.selectCheckBox(".id", "useCurrentPeriodEndTimeMinus15Min");
	}
	
	public void clickRun(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Run");
	}
	
	public String getNote(){
		IHtmlObject objs[] = browser.getHtmlObject(".id", "NOTSET");
		String msg = objs[0].getProperty(".text").trim();
		return msg;
	}
}
