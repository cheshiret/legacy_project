/*
 * $Id: UwpAccountOverviewPage.java 6822 2008-12-02 16:48:26Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jchen
 */
public class UwpAccountOverviewPage extends UwpPage {

	private static UwpAccountOverviewPage _instance = null;

	protected UwpAccountOverviewPage() {
	}

	public static UwpAccountOverviewPage getInstance() {
		if (null == _instance)
			_instance = new UwpAccountOverviewPage();

		return _instance;
	}

	protected Property[] profileOverviewDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "contentin");
	}
	
	protected Property[] profileInfoSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "bold");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("[a|A]ccountOverview",false),".className","accountside in");
	}

	/**
	 * This methods goes to check whether the special string appears
	 * @param str - string will be checked
	 * @return True - appears; Flase - Not appear
	 */
	private boolean checkData(String str) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contentArea");
		String text = (String) objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		if (text.toUpperCase().matches(".*" + str.toUpperCase() + ".*"))
			return true;
		else
			return false;
	}

	/**
	 * This method goes to verify whether the special content appears on Account Overview page
	 * @param user - text will be checked
	 */
	public void verifyAccountOverview(String user) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("[a|A]ccountOverview",false),".className","accountside in");
		boolean same = true;
		
		if (objs.length<1)
			same = false;
		if (!checkData(user))
			same = false;
		Browser.unregister(objs);

		if (!same)
			throw new ItemNotFoundException("Failed to verify account overview.");
		else
			logger.info(user+" displays in account overview page.");
	}
	
	/**Go to current reservations page*/
	public void gotoCurrentReservationPg(){
//		browser.clickGuiObject(".class","Html.A",".text", "Current Reservations");
		// Use the top element to locate the link. By Lesley Wang
		IHtmlObject[] topDIVs = browser.getHtmlObject(".class", "Html.DIV", ".className", "accountbar");
		if (topDIVs.length < 1) {
			throw new ItemNotFoundException("The top DIV with className=accountbar can't be found! Please check!");
		}
		browser.clickGuiObject(".class","Html.A",".text", "Current Reservations", true, 0, topDIVs[0]);
	}
	
	/**Go to past reservations page*/
	public void gotoPastReservationPg(){
		browser.clickGuiObject(".class","Html.A",".text", "Past Reservations");
	}
	
	/**Go to Lottery Applications page*/
	public void gotoLotteryApplicationsPg(){
		browser.clickGuiObject(".class","Html.A",".text", "Lottery Applications");
	}
	
	/**Go to Pre-Registration page*/
	public void gotoPreRegistrationPg(){
		browser.clickGuiObject(".class","Html.A",".text", "Pre-Registration");
	}
	
	public String getAccountName() {
		return browser.getObjectText(Property.atList(this.profileOverviewDiv(), this.profileInfoSpan()));
	}
}
