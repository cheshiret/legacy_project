/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;


/**
 * @Description:It is for admin.do home page. 
 * The page is shown after sign in with a valid account and select role and location.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley
 * @Date  Jan 6, 2013
 */
public class AdminDoHomePage extends WebMaintenanceAppUserPanel {
	private static AdminDoHomePage _instance = null;

	public static AdminDoHomePage getInstance() {
		if (null == _instance)
			_instance = new AdminDoHomePage();

		return _instance;
	}
	
	protected AdminDoHomePage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".id", "admin");
	}
}
