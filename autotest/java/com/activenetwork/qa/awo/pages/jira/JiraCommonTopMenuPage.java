package com.activenetwork.qa.awo.pages.jira;

import com.activenetwork.qa.awo.pages.JiraPage;

public abstract class JiraCommonTopMenuPage extends JiraPage {
	
	/**Click Logout Link*/
	public void clickLogout(){
	  browser.clickGuiObject(".class","Html.A",".text","Log Out");
	}
}
