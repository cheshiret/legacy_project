/*
 * $Id: VnuMgrFeeDetailPg.java 6670 2008-11-18 22:46:37Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.venueManager;



import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @author CGuo
 */
public class VnuMgrFeeDetailPg extends VenueManagerPage {

	/**
	 * Script Name   : VnuMgrFeeDetailPg
	 * Generated     : Apr 18, 2007 10:56:47 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/04/18
	 */

	static private VnuMgrFeeDetailPg _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public VnuMgrFeeDetailPg getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new VnuMgrFeeDetailPg();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "fee_adjustment_notes");
	}

	/**
	 * Fill in notes field.
	 * @param notes
	 */
	public void setNotes(String notes) {
		browser.setTextField(".id", "fee_adjustment_notes", notes);
	}

	/** Click on OK to save the changes. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Change the specific fee rate for given fee type, will exchange it to the other
	 * when equals to 1.5 or 1.43 
	 * @param feeType - fee type
	 * @param rafeeRateInDB - fee rate in DB
	 * @param feeSchID - fee schedule id
	 * @param notes
	 */
	public void changeTicketFee(String feeType, double rafeeRateInDB,
			String feeSchID, String notes) {

		if (feeType.equals("ticket fee")) {
			feeType = "ticketfee_";
		} else if (feeType.equals("ticket item transaction fee")) {
			feeType = "item_tranfee_";
		} else if (feeType.equals("ticket item ra fee")) {
			feeType = "item_rafee_";
		} else if (feeType.equals("ticket order transaction fee")) {
			feeType = "order_tranfee_";
		} else {
			throw new RuntimeException("Unknown Fee Type for Ticket Item");
		}

		RegularExpression regex = new RegularExpression(feeType, false);

		IHtmlObject[] objs = browser.getHtmlObject(".id", regex, ".type",
				"text");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}
		if (objs.length > 1) {
			throw new RuntimeException("ambigous object recogintion");
		}
		IHtmlObject guiObj = objs[0];

		String[] feeSchedules = guiObj.getProperty(".id").toString().split("_");
		String feeScheduleID = feeSchedules[feeSchedules.length - 1];

		if (!feeScheduleID.equals(feeSchID)) {
			throw new RuntimeException("Fee Schedule ID does not match");
		}

		if (rafeeRateInDB == 1.5) {
			rafeeRateInDB = 1.43;
		} else if (rafeeRateInDB == 1.43) {
			rafeeRateInDB = 1.50;
		}
		((IText) guiObj).setText(rafeeRateInDB);

		refreshPage();
		waitLoading();

		this.setNotes(notes);

		refreshPage();

		waitLoading();

		this.clickOK();
	}

	/** Refresh the page by clicking on OK button. */
	public void refreshPage() {
		this.clickOK();
	}
	
	
	
}
