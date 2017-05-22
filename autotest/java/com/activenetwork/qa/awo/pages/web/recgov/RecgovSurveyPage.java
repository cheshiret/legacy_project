package com.activenetwork.qa.awo.pages.web.recgov;


import com.activenetwork.qa.awo.pages.UwpPage;

public class RecgovSurveyPage extends UwpPage {//ExternalWebPage {
	private static RecgovSurveyPage _instance=null;
	
	public static RecgovSurveyPage getInstance() {
		if(null==_instance) {
			_instance=new RecgovSurveyPage();
		}
		
		return _instance;
	}
	
//	protected RecgovSurveyPage() {
//		super("title",new RegularExpression(".*ForeSee.*Survey.*Page",false));
//	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","fsr_StickyWin");
		
//		HtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id","fsr_StickyWin");
//		if(objs.length>0) {
//			objs[0].getProperty(".style");
//		}
//		return false;
	}
	
	public void clickNoThanksButton(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "No thanks");
	}

}
