package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;

public class ForeseeSurveyAdminPage extends UwpPage {

	private static ForeseeSurveyAdminPage _instance=null;

	public static ForeseeSurveyAdminPage getInstance() {
		if(null==_instance) {
			_instance=new ForeseeSurveyAdminPage();
		}

		return _instance;
	}

	private ForeseeSurveyAdminPage() {

	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","controls");
	}

	public void setSamplingPercentage(int percent) {
		browser.setTextField(".class","Html.INPUT.text",Integer.toString(0),true);
	}

	public void clickClearButton() {
		browser.clickGuiObject(".class","Html.INPUT.button",".value","Clear",true);
	}

	public void clickSetButton() {
		browser.clickGuiObject(".class","Html.INPUT.button",".value","Set",true);
	}

}
