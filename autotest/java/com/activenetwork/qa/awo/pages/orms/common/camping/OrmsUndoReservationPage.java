/*
 * $Id: FldMgrUndoResPage.java 7058 2009-01-28 18:59:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author Jdu
 */
public class OrmsUndoReservationPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrUndoResPage
	 * Generated     : Feb 24, 2005 3:18:32 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2009/05/29
	 */

	public static String ORD_NUM_REGEX = "2-\\d+";

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsUndoReservationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsUndoReservationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsUndoReservationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsUndoReservationPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression("Undo (No(-| )Show|Check-out|Check-in|Undocking|Docking)", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", reg);
	}

	/**Undo check in*/
	public void clickUndoCheckin() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Check-in");
	}
	
	/**Click Finish button*/
	public void clickFinish() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Finish");
	}

	/**Undo checkout*/
	public void clickUndoCheckout() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Check-out");
	}

	/**Undo no show*/
	public void clickUndoNoShow() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Undo No(-| )Show",false));
	}
	
	/**Undo Undocking*/
	public void clickUndoUndocking() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Undocking");
	}
	
	/**Undo Docking*/
	public void clickUndoDocking() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Docking");
	}

	/**Selct how many rows that displayed per page*/
	public void selectRowsPerPage(int rows) {
		browser.selectDropdownList(".id", "pagingBarRowsPerPage", rows
				+ " rows per page", true);
	}

	/**Select one order number*/
	public void selectOrder(String resID) {
		IHtmlObject[] objs = browser.getHtmlObject(".text", resID, ".class","Html.A");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}

		for (int i = 0; i < objs.length; i++) {

			IHtmlObject guiObj = objs[i];

			if (resID.equals("")) {
				resID = guiObj.getProperty(".text").toString();
			}

			if (guiObj.getProperty(".text").toString().equals(resID)) {
				selectSite(i);
				break;
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Undo checkout,input the reserve ID
	 * @param resID
	 * @throws PageNotFoundException
	 */
	public void searchResvation(String resID) throws PageNotFoundException {
		this.setResNum(resID);
		this.clickGo();
		this.waitLoading();
		this.clickUndoCheckout();
	}

	/**
	 * Undo checkin
	 * @param resID
	 * @throws PageNotFoundException
	 */
	public void undoCheckIn(String resID) throws PageNotFoundException {
		this.setResNum(resID);
		this.clickGo();
		this.waitLoading();
		if(isRadioButtonExists()){
			selectFirstResNum();
		}
		this.clickUndoCheckin();
	}
	
	public boolean isRadioButtonExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems|selected_rsv_index", false));
	}
	
	public void selectFirstResNum() {
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems|selected_rsv_index", false));
	}
	
	/**
	 * Undo checkout
	 * @param resID
	 * @throws PageNotFoundException
	 */
	public void undoCheckOut(String resID) throws PageNotFoundException {
		this.setResNum(resID);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
		if(this.isRadioButtonExists()) {
			this.selectFirstResNum();
		}
		this.clickUndoCheckout();
		ajax.waitLoading();
	}

	/***
	 * Undo No show
	 * @param resID
	 */
	public void undoNoShow(String resID) {
		this.setResNum(resID);
		this.clickGo();
		this.waitLoading();
		this.clickUndoNoShow();
	}

	/**Wait search*/
	public void searchWaitExists() {
		RegularExpression regex = new RegularExpression("[1-9]-[0-9]+", false);
		browser.searchObjectWaitExists(".class", "Html.A", ".text", regex);
	}

	/**Click Go button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(GO|Search)$", false));
	}

	/**
	 * Select site
	 * @param index
	 */
	public void selectSite(int index) {
		browser.selectRadioButton(".classIndex", String.valueOf(index));
	}

	/**
	 * Select reserve num
	 * @param resID
	 */
	public void setResNum(String resID) {
		browser.setTextField(".id", new RegularExpression("reservationNum|MarinaHomeOrderSearchCriteria-\\d+\\.orderNumber", false), resID);
	}
	
	public void searchByResId(String resID){
		this.setResNum(resID);
		this.clickGo();
		this.waitLoading();
	}

	public void undoUndocking(String resID) {
		searchByResId(resID);
		if(isRadioButtonExists()){
			selectFirstResNum();
		}
		this.clickUndoUndocking();
	}
	
	public void undoDocking(String resID) {
		searchByResId(resID);
		if(isRadioButtonExists()){
			selectFirstResNum();
		}
		this.clickUndoDocking();
	}
}
