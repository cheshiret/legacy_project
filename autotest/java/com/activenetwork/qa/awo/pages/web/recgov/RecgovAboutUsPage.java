/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-3-31
 */
public class RecgovAboutUsPage extends RecgovCommonPage {

	private static RecgovAboutUsPage _instance=null;
	
	private RecgovAboutUsPage(){}
	
	public static RecgovAboutUsPage getInstance(){
		if(_instance==null){
			_instance=new RecgovAboutUsPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(new Property[]{new Property(".class", "Html.DIV"),new Property(".id","acm-content"),new Property(".text",new RegularExpression("About Us.*",false))});
	}
	
	public void clickParticipatingPartnersLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Participating Partners");
	}
}
