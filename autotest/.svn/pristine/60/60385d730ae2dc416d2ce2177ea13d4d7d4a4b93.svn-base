/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Aug 31, 2011
 */
public class SysMgrTopMenuPage extends SysMgrCommonTopMenuPage {
    
	private static SysMgrTopMenuPage  instance=null;
	
	protected SysMgrTopMenuPage(){};
	
	public static SysMgrTopMenuPage getInstance(){
		if(instance==null){
			instance=new SysMgrTopMenuPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("^menu_\\d+",false));
	}
	
	

}
