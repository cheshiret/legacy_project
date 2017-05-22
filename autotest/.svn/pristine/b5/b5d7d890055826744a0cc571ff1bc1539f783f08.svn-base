/*
 * $Id: VnuMgrTicketDetailPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsTicketDetailsPage extends OrmsPage {

	/**
	 * Script Name   : VnuMgrTicketDetailPage
	 * Generated     : Feb 19, 2007 2:13:16 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/02/19
	 */

	private static OrmsTicketDetailsPage _instance = null;

	private OrmsTicketDetailsPage() {
	}

	public static OrmsTicketDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketDetailsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression(".* Ticket Order Details$", false));
	}

	/**Click Transfer Ticket button*/
	public void clickTransferTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Transfer Tickets");
	}

	/**Click Add Ticket*/
	public void clickAddTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Tickets");
	}

	/**Click Add to cart*/
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add To Cart");
	}

	/**Click Cancel Ticket*/
	public void clickCancelTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Tickets");
	}

	/**Click Change Ticket Type*/
	public void clickChangeTicketType() {
		browser.clickGuiObject(".class", "Html.A", ".text","Change Ticket Type");
	}

	/**click Void Tickets*/
	public void clickVoidTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text","Void Ticket Order");
	}

	/**Click Manage tickets*/
	public void clickManageTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Manage Tickets");
	}

	/**Click Change Ticket Time*/
	public void clickChangeTicketTime() {
		browser.clickGuiObject(".class", "Html.A", ".text","Change Ticket Time");
	}

	/**Click Fees*/
	public void clickFees() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees");
	}

	/**Click History*/
	public void clickHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "History");
	}

	/**Click Note/Alert button*/
	public void clickNoteAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/**Click request conf letter*/
	public void clickRequestConfLetter() {
		browser.clickGuiObject(".class", "Html.A", ".text","Request Conf. Letter");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Print ticket*/
	public void clickPrintTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Tickets");
	}

	/**Click Print All Tickets*/
	public void clickPrintAllTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text","Print All Tickets");
	}

}
