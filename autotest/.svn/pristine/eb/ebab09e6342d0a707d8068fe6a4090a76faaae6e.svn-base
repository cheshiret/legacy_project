/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-3-31
 */
public class RecgovShareOurDataPage extends RecgovCommonPage {
	
	private static RecgovShareOurDataPage _instance=null;
	
	private RecgovShareOurDataPage(){
		
	}
	
	public static RecgovShareOurDataPage getInstance(){
		if(_instance==null){
			_instance=new RecgovShareOurDataPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "marketingContainer");
	}

}
