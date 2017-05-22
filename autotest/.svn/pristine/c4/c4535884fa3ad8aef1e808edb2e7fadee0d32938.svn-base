package com.activenetwork.qa.awo.pages;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.DialogPage;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class GeneralSignOutPage extends HtmlMainPage {
	private RegularExpression signoutPattern=new RegularExpression("^Sign(\\s|\\-)Out$",false);
	private static GeneralSignOutPage _instance=null;
	
	private GeneralSignOutPage() {}
	
	public static GeneralSignOutPage getInstance() {
		if(_instance==null) {
			_instance=new GeneralSignOutPage();
		}
		
		return _instance;
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text",signoutPattern);
	}
	
	public boolean tryToSignOut() {
		return tryToSignOut(null,false);
	}
	
	public boolean tryToSignOut(DialogPage dialogToDismiss) {
		return tryToSignOut(dialogToDismiss,false);
	}
	
	public boolean tryToSignOut(boolean finSessionMayExists) {
		return tryToSignOut(null,finSessionMayExists);
	}
	
	/**
	 * this method will try to signout from the current login session. 
	 * The signout may not be successful as the signout link may not exist in every Orms or UWP pages.
	 */
	public boolean tryToSignOut(DialogPage dialogToDismiss, boolean finSessionMayExists) {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text",signoutPattern);
		if(objs.length>0) {
			logger.info("Sign out link is found.");
			objs[0].click();
			Browser.unregister(objs);
			if(dialogToDismiss!=null) {
				browser.dismissDialogs(2, dialogToDismiss, 1);
			}
			try {
				if(finSessionMayExists) {
					//leave Fin Session Open Adn Sign Out
					boolean finExists=browser.tentativeWaitExists(5, Property.toPropertyArray(".class","Html.A",".text","Leave Financial Session Open And Sign Out"));
					if(finExists) {
						browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".text","Leave Financial Session Open And Sign Out"));
					}
				}
			
				browser.waitDisappear(Browser.SHORT_SLEEP, Property.toPropertyArray(".class","Html.A",".text",signoutPattern));
				logger.info("Sign out is successful.");
				return true;
			} catch(Exception e) {
				logger.info("Sign out failed");
				return false;
			}
			
		} else {
			logger.info("Sign out link is not found.");
			return false;
		}		
	}

}
