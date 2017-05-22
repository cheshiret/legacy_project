/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-27
 */
public class RecgovHomePage extends UwpHomePage {
	
	private static RecgovHomePage instance=null;
	
	private RecgovHomePage(){}
	
	public static RecgovHomePage getInstance(){
		if(instance==null){
			instance=new RecgovHomePage();
		}
		return instance;
	}
	
	public boolean exists() {
	    boolean flag1=browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "acm-slideshow-nav-container");
	    boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "footerLogo"); //Sara[11/5/2013]
	    boolean flag3=browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression(".*/recgovHome\\.do\\?topTabIndex=Home",false));
	    boolean flag4=browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "logoContainer");//Sara[20140220]
	    return (flag1&&flag3) || (flag2&&flag3) || (flag2&&flag4);
	}

}
