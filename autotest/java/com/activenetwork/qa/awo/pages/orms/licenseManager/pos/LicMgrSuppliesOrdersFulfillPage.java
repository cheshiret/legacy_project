package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CarrierInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * Supplies Orders fulfill page, Orders-->Supplies Orders --- > fulfill order
 * @author pchen
 */
public class LicMgrSuppliesOrdersFulfillPage extends LicMgrSupplyOrderSearchPage {
	private static LicMgrSuppliesOrdersFulfillPage _instance = null;

	protected LicMgrSuppliesOrdersFulfillPage() {
	}

	public static LicMgrSuppliesOrdersFulfillPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrSuppliesOrdersFulfillPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		boolean suppliesOrder = browser.checkHtmlObjectExists(".text", "Go",
				".href", new RegularExpression(".*fullfillorder$", false));
		boolean carrierInformation = browser.checkHtmlObjectExists(".text",
				"Carrier Information");
		return suppliesOrder && carrierInformation;
	}

	/**
	 * Select agent from drop down list
	 * 
	 * @param agent
	 */
	public void selectAgent(String agent) {
		browser.selectDropdownList(
				".id",
				new RegularExpression("ApproveSupplyOrderItemView-\\d+.HFOrderItemInfo.creationStore", false),
				agent);
	}

	/**
	 * Select address type from drop down list
	 * 
	 * @param addressType
	 */
	public void selectShipTo(String addressType) {
		browser.selectDropdownList(".id", new RegularExpression(
				"ShippingInfo-\\d+\\.addressType", false), addressType);

	}

	/**
	 * Select recipient from drop down list
	 * 
	 * @param recipient
	 */
	public void selectRecipient(String recipient) {
		browser.selectDropdownList(".id", new RegularExpression(
				"ShippingInfo-\\d+\\.contact", false), recipient);

	}

	/**
	 * Select carrier from drop down list
	 * 
	 * @param carrier
	 */
	public void selectCarrier(String carrier) {
		browser.selectDropdownList(".id", new RegularExpression(
				"PackingSlipView-\\d+\\.carrierID", false), carrier);
	}

	public void setTrackNum(String trackingNumber){
		browser.setTextField(".id", new RegularExpression(
				"PackingSlipView-\\d+\\.trackingNumber", false),
				trackingNumber);
	}
	/**
	 * Set Shipping Date
	 * @param shippingDate
	 */
	public void setShippingDate(String shippingDate) {
		browser.setTextField(".id", new RegularExpression(
				"PackingSlipView-\\d+\\.shippingDate_ForDisplay", false),
				shippingDate);
	}

	/**
	 * Set Shipping Notes
	 * @param shippingNote
	 */
	public void setShippingNote(String shippingNote) {
		browser.setTextField(".id", new RegularExpression(
				"PackingSlipView-\\d+\\.shippingNotes", false), shippingNote);
	}
	/**
	 * Select all product in current page
	 */
	public void selectAllProductOnPage(){
		browser.selectCheckBox(".name", "all_slct");
	}
    /**
     * Click Fulfill Selected Items button
     */
	public void clickFulfillSelectedItems(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Fulfill Selected Items");
	}
    /**
     * Click Go button
     */
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	/**
	 * Set up fulfill information
	 * @param agent
	 * @param shipTo
	 * @param recipient
	 * @param carrier
	 */
	public void setupCarrierInfo(CarrierInfo carrier)
	{
		if(StringUtil.notEmpty(carrier.carrier)){
			this.selectCarrier(carrier.carrier);
		}
		if(StringUtil.notEmpty(carrier.trackingNumber)){
			this.setTrackNum(carrier.trackingNumber);
		}
		if(StringUtil.notEmpty(carrier.shippingDate)){
			this.setShippingDate(carrier.shippingDate);
		}
		if(StringUtil.notEmpty(carrier.shippingNotes)){
			this.setShippingNote(carrier.shippingNotes);
		}
		
	}
	
	/**
	 * Get supplies orders tables
	 * @return
	 * @author Lesley Wang
	 * @date Oct 19, 2012
	 */
	private IHtmlObject[] getSuppliesOrdersTables() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "suppliesOrderForFulfillGrid");
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException("Can't find supplies orders table");
		}
		return objs;
	}
	
	/**
	 * select supply order by index
	 * @param index
	 * @author Lesley Wang
	 * @date Oct 19, 2012
	 */
	public void selectSupplyOrder(int index) {
		browser.selectCheckBox(".id", new RegularExpression("ApproveSupplyOrderItemView-\\d+.headerSelected", false), index);
	}
	
	/**
	 * select supply order by order number
	 * @param orderNum
	 * @author Lesley Wang
	 * @date Oct 19, 2012
	 */
	public void selectSupplyOrderByOrderNum(String orderNum) {
		IHtmlObject[] objs = this.getSuppliesOrdersTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(5, orderNum);
		if (rowIndex < 1) {
			throw new ErrorOnPageException("No supply order with the num=" + orderNum + " in the list");
		}
		this.selectSupplyOrder(rowIndex - 1);
		Browser.unregister(objs);
		logger.info("Select the supply order with num=" + orderNum);
	}
}
