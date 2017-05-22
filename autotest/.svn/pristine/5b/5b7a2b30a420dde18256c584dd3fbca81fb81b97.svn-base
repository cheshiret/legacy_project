/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author raonqa
 *
 * Orms general alert page for Customer/Park/Site/Loop Alerts
 */
public class OrmsAlertPage extends OrmsPage {

	/**
	 * Script Name   : <b>OrmsAlertPage</b>
	 * Generated     : <b>Sep 21, 2004 3:33:17 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 1)
	 *
	 * @since  2009/04/29
	 * @author JDU
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsAlertPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsAlertPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsAlertPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsAlertPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		//change to use p2 <div class="message msgsuccess_alertbar" id="resAlert" name="resAlert">
		//in marina manager, the p2 div is <div class="message msgsuccess_alertbar" id="marinamanager.notealert.slipalert.alerts" name="marinamanager.notealert.slipalert.alerts">
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".className","mainWrapper",".id",new RegularExpression("(.*alert(s)?|common-event)$",false));
		Property[] p2=Property.toPropertyArray(".class","Html.DIV",".className","message msgsuccess_alertbar",".id",new RegularExpression(".+alert(s)?|parkevent$", false));
		
		Property[] marina=Property.toPropertyArray(".class","Html.DIV",".className","message msgsuccess_alertbar",".id","marinamanager.notealert.marinaalerts",".text","Marina Alerts");
		boolean exist= browser.checkHtmlObjectDisplayed(Property.atList(p1,p2)) ||
		               browser.checkHtmlObjectDisplayed(marina);
		//James: if you want to change this page mark, please send an email to me firstly
		
		return exist;
	  }

	/**Click Search*/
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", 1);

	}

	/**
	 * Select search status
	 * @param searchStatus
	 */
	public void selectSearchStatus(String searchStatus) {
		browser.selectDropdownList(".id", "searchStatus", searchStatus);
	}

	/** Click the "OK" button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("^OK", false), true);
	}

	/**Click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("^Cancel", false), true);
	}
	
	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	/**
	 * Select note type.
	 * @param type - note type
	 */
	public void selectNoteType(String type) {
		browser.selectDropdownList(".id", "noteAlertType", type);
	}
	
	/**
	 * Fill in alert start date.
	 * @param sDate - start date
	 */
	public void setAlertStartDate(String sDate) {
		if (browser.checkHtmlObjectExists(".id",
				"noteAlertStartDate_ForDisplay")) {
			browser.setTextField(".id", "noteAlertStartDate_ForDisplay", sDate);
		}
	}

	/**
	 * Fill in alert end date.
	 * @param eDate - end date
	 */
	public void setAlertEndDate(String eDate) {
		if (browser.checkHtmlObjectExists(".id", "noteAlertEndDate_ForDisplay"))
			browser.setTextField(".id", "noteAlertEndDate_ForDisplay", eDate);
	}

	/**
	 * Fill in note text.
	 * @param text - note
	 */
	public void setNoteText(String text) {
		browser.setTextArea(".id", "noteAlertText", text);
	}

	/** Check alert status to active. */
	public void checkActive() {
		browser.selectCheckBox(".id", "noteAlertActive");
	}

	/** Uncheck alert status to active. */
	public void uncheckActive() {
		browser.unSelectCheckBox(".id", "noteAlertActive");
	}
	
	/** Click on Apply link. */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	/** Click on Delete link. */
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/** Click on Deactivate link. */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click on Activate link. */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	public String getNoteOrAlertId()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Configure Note/Alert.*",false));
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return text.split("ID")[1].split(" ")[1];
	}
	
	public void clickOKtoBookThisEntrance() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("OK to Book this (Entrance|River|Trail)", false));
	}
	
	public void clickCancelthisEntranceChoice() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Cancel this (Entrance|River|Trail) Choice", false));
	}
	
	public String getState(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^State", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find state!");
		}
		
		return objs[0].getProperty(".text").replaceAll("State", StringUtil.EMPTY);
	}
	
	public void clickParkName(String parkName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(parkName, false));
	}
	
	public String getRuleWarnMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "alertsRuleWarn");
	}
	
	public boolean isMarinaAlertsExist(){
		return browser.checkHtmlObjectExists(".id", "marinamanager.notealert.marinaalerts");
	}
}
