/*
 * $Id: OrmsChargePOSToResPage.java 7058 2009-01-28 18:59:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsChargePOSToResPage extends OrmsPage {

	/**
	 * Script Name   : OrmsChargePOSToResPage
	 * Generated     : Feb 1, 2008 8:28:18 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/01
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsChargePOSToResPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsChargePOSToResPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsChargePOSToResPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsChargePOSToResPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {//Shane: update page mark to support both slip and camping charge pos
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Charge POS:Charge To( Slip)? Reservation Search/List$",
						false));
		
//		return browser.checkHtmlObjectExists(".id", "resListUI_LIST");
	}

	/**Wait searched reservation exist*/
	public void schResWaitExist() throws PageNotFoundException {
		ajax.waitLoading();
		browser.searchObjectWaitExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Charge POS: ?Charge To( Slip)? Reservation Search/List$",
						false));
	}

	/**Click Select button*/
	public void clickSelect() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select");
	}

	/**Input Reserve Num*/
	public void setResNum(String res) {
		browser.setTextField(".id",new RegularExpression("ResToChargeSearchCriteria-*\\d*.reservationNumber",false), res);
	}

	/**Click Go button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
	}

	/**Select Reservation Status*/
	public void selectResStatus(String status) {
		browser.selectDropdownList(".id", "ResToChargeSearchCriteria.status",status);
	}

	/**
	 * Charge Reservation
	 * @param res
	 * @throws PageNotFoundException
	 */
	public void chargeToRes(String res) throws PageNotFoundException {
		this.setResNum(res);
		//this.selectResStatus(status);
		this.clickGo();
		this.schResWaitExist();
		this.clickSelect();
	}

}
