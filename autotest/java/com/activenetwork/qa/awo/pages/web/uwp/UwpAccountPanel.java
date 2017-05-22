/*
 * $Id: UwpAccountPanel.java 6822 2008-12-02 16:48:26Z i2k-net\raonqa $ 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * TODO: enter class description
 * 
 * @author jchen
 */
public class UwpAccountPanel extends UwpPage {
	private static UwpAccountPanel _instance = null;

	protected UwpAccountPanel() {
	}

	public static UwpAccountPanel getInstance() {
		if (null == _instance)
			_instance = new UwpAccountPanel();

		return _instance;
	}

	protected Property[] equipmentManagement(){
		return Property.concatPropertyArray(a(), ".id", "marinaEquipmentManagement");
	}
	
	protected Property[] printTicketsPermitsLink() {
		return Property.concatPropertyArray(a(), ".id", "printAtHome");
	}
	
	protected Property[] customerPassesLink() {
		return Property.concatPropertyArray(a(), ".id", "customerPasses");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("My Account|myAccount",false));
	}

	/**
	 * Click on 'Update Profile' link in Account Panel
	 */
	public void gotoUpdateProfile() {
		browser.clickGuiObject(".id", new RegularExpression("Update Profile|updateProfile",false));
	}

	/**
	 *Click on 'Update Email' link in Account Panel
	 */
	public void gotoUpdateEmail() {
		browser.clickGuiObject(".id",new RegularExpression("Update Email|updateEmail",false));
	}

	/**
	 *Click on 'Update Password' link in Account Panel
	 */
	public void gotoUpdatePassword() {
		browser.clickGuiObject(".id", new RegularExpression("Update Password|updatePassword",false));
	}

	/**
	 *Click on 'Current Reservations' link in Account Panel
	 */
	public void gotoCurrentReservations() {
		browser.clickGuiObject(".class","Html.A",".id", new RegularExpression("Current Reservations|currentReservations",false));
	}

	/**Go to lottery application list page.*/
	public void gotoLotteryApplications(){
		browser.clickGuiObject(".id", new RegularExpression("Lottery Applications|lotteryApplications",false));
	}
	
	/**
	 *Click on 'Past Reservations' link in Account Panel
	 */
	public void gotoPastReservations() {
		browser.clickGuiObject(".id",  new RegularExpression("Past Reservations|pastReservations",false));
	}

	/**
	 *Click on 'Account Overview' link in Account Panel
	 */
	public void gotoAccountOverview() {
		browser.clickGuiObject(".id", new RegularExpression("Current Reservations|currentReservations",false));
	}
	
	/**
	 *Click on 'Account Overview' link in Account Panel
	 */
	public void gotoRedeemableVouchers() {
		browser.clickGuiObject(".id", new RegularExpression("(R|r)edeemableVouchers", false)); //"RedeemableVouchers");
	}
	
	/**
	 * Get the link name of account panel for current activated page.
	 * @return - link text
	 */
	public String getActivatePageName(){
		IHtmlObject[] foundTOs = browser.getHtmlObject(".className", "accountside in");
		String pageName = "";
		if(foundTOs.length>0) {
			pageName = foundTOs[0].getProperty(".text");
		} else {
			throw new ErrorOnPageException("Pls check account panel links' class name.");
		}
		Browser.unregister(foundTOs);
		return pageName;
	}

	
	public void clickTicketPermitLink() {
		browser.clickGuiObject(".class","Html.A",".text","Print Tickets & Permits");
	}
	
	public void clickEquipmentManagement(){
		browser.clickGuiObject(equipmentManagement());
	}
	
	public void clickPrintTicketsPermits() {
		browser.clickGuiObject(printTicketsPermitsLink());
	}
	
	public void clickDiscountPasses(){
		browser.clickGuiObject(customerPassesLink());
	}
}
