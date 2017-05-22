/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

/**
 * @Description:
 * this page will display after click 'Get Involved' link in Navigation Bar of rec.gov
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-3-31
 */
public class RecgovGetInvolvedPage extends RecgovCommonPage {
	
	private static RecgovGetInvolvedPage _instance=null;
	
	private RecgovGetInvolvedPage(){
		
	}
	
	public static RecgovGetInvolvedPage getInstance(){
		if(_instance==null){
			_instance=new RecgovGetInvolvedPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "getinvolvedslideshow");
	}

}
