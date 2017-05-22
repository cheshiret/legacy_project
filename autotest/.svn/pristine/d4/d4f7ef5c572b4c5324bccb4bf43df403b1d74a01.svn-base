package com.activenetwork.qa.awo.pages.orms.common.lottery;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is void tick lottery page
 * @author Phoebe
 *
 */
public class OrmsVoidTicketLotteryOrderPage extends OrmsPage {

	private static OrmsVoidTicketLotteryOrderPage _instance = null;

	private OrmsVoidTicketLotteryOrderPage() {
	}

	public static OrmsVoidTicketLotteryOrderPage getInstance() {
		if (null == _instance)
			_instance = new OrmsVoidTicketLotteryOrderPage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.TD",".text","Void Ticket Order");
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
			browser.selectDropdownList(".id", new RegularExpression("TourOrderView-\\d+\\.voidReasonCode", false), reason);
		else
			browser.selectDropdownList(".id", new RegularExpression("TourOrderView-\\d+\\.voidReasonCode", false), 0);
	}

	/**
	 * Select void reason by reason index
	 * @param index
	 */
	public void selectReason(int index) {
		browser.selectDropdownList(".id", new RegularExpression("TourOrderView-\\d+\\.voidReasonCode", false), index);
	}

	/**
	 * Set notes 
	 * @param notes
	 */
	public void setNotes(String notes) {
		browser.setTextArea(".id", "TourOrderView.voidNotes", notes);
	}

	/**Click Voic ticket order*/
	public void clickVoidTicketOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void Order");
	}

	/**click Don't void*/
	public void clickDontVoid() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Void");
	}

}
