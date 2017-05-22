/*
 * $Id: VnuMgrVoidTicketPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsVoidTicketOrderPage extends OrmsPage {

	/**
	 * Script Name   : VnuMgrVoidTicketPage
	 * Generated     : Feb 20, 2007 3:00:16 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/02/20
	 */

	private static OrmsVoidTicketOrderPage _instance = null;

	private OrmsVoidTicketOrderPage() {
	}

	public static OrmsVoidTicketOrderPage getInstance() {
		if (null == _instance)
			_instance = new OrmsVoidTicketOrderPage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression("\"doVoidTickets\"",
				false));
		return browser.checkHtmlObjectExists(p)||browser.checkHtmlObjectDisplayed(".class", "Html.TD",".text","Void Ticket Order");
	}

	/**
	 * Void ticket order
	 * @param reason
	 * @param notes
	 */
	public void voidTicketOrder(String reason, String notes) {
		this.selectReason(reason);
		if (notes.equals("")) {
			notes = "qa automation test";
		}
		this.setNotes(notes);
		this.clickVoidTicketOrder();

	}

	/**
	 * Select void reason by reason name
	 * @param reason
	 */
	public void selectReason(String reason) {
		if (reason != null && reason.length() > 0)
			browser.selectDropdownList(".id", "voidReason", reason);
		else
			browser.selectDropdownList(".id", "voidReason", 0);
	}

	/**
	 * Select void reason by reason index
	 * @param index
	 */
	public void selectReason(int index) {
		browser.selectDropdownList(".id", "voidReason", index);
	}

	/**
	 * Set notes 
	 * @param notes
	 */
	public void setNotes(String notes) {
		browser.setTextArea(".id", "voidNotes", notes);
	}

	/**Click Voic ticket order*/
	public void clickVoidTicketOrder() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression("\"doVoidTickets\"",false));
		browser.clickGuiObject(p);
	}

	/**click Don't void*/
	public void clickDontVoid() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Void");
	}
	
	public String getTourDateInvInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Tour Date.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("The span about tour inventory information for tour date is not correct!");
		}
		String content = objs[0].text().replace("Tour Date", "");
		return content;
	}

}
