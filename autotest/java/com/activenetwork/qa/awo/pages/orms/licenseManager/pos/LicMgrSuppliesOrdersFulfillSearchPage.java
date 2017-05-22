package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Supplies Orders fulfill page, Orders-->Supplies Orders --- > view fulfillment
 * 
 * @author pchen
 */
public class LicMgrSuppliesOrdersFulfillSearchPage extends LicMgrSupplyOrderSearchPage {
	private static LicMgrSuppliesOrdersFulfillSearchPage _instance = null;

	protected LicMgrSuppliesOrdersFulfillSearchPage() {
	}

	public static LicMgrSuppliesOrdersFulfillSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrSuppliesOrdersFulfillSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
//		boolean suppliesOrder = browser.checkHtmlObjectExists(".text", "Go",
//				".href", new RegularExpression("suppliesOrder$", false));
//		boolean searchFilter = browser.checkHtmlObjectExists(".text", "Filter");
//		return suppliesOrder && searchFilter;
		return browser.checkHtmlObjectExists(".id", "generate_suuplies_fulfilment_list_grid_LIST");
	}

	/**
	 * Select agent from drop down list
	 * 
	 * @param agent
	 */
	public void selectAgent(String agent) {
		browser.selectDropdownList(".id", new RegularExpression(
				"SupplyFulfillmentSearchCriteria-\\d+\\.store", false), agent);
	}

	/**
	 * Set up Fulfillment From Date
	 * 
	 * @param fromDate
	 */
	public void setFulfillmentFromDate(String fromDate) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"SupplyFulfillmentSearchCriteria-\\d+\\.fulfillmentFrom_ForDisplay",
						false), fromDate);
	}

	/**
	 * Set up Fulfillment To Date
	 * 
	 * @param toDate
	 */
	public void setFulfillmentToDate(String toDate) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"SupplyFulfillmentSearchCriteria-\\d+\\.fulfillmentTo_ForDisplay",
						false), toDate);
	}

	/**
	 * Set up Tracking number
	 * 
	 * @param trackNumber
	 */
	public void setTrackNumber(String trackNumber) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyFulfillmentSearchCriteria-\\d+\\.trackNumber", false),
				trackNumber);
	}

	/**
	 * Click Go button
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	/**
	 * Click Print Packing slip button
	 */
	public void clickPrintPackingSlip() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Print Packing slip");
	}

	/**
	 * Select all fulfillment on current page
	 */
	public void selectAllFulfillmentInList() {
		browser.selectCheckBox(".name", "all_slct");
	}
	/**
	 * This method select the first fulfillment in current list, usually used after search a fulfillment
	 */
	public void selectFulfillmentInCurrentList(){
		browser.selectCheckBox(".id", new RegularExpression(
				"SupplyFulfillmentSearchView-\\d+\\.selected", false));
	}
	
	/**
	 * Search fulfillment(s) according to the agent, fromDate, toDate or trackNumber
	 * @param agent
	 * @param fromDate
	 * @param toDate
	 * @param trackNumber
	 */
	public void searchFulfillment(String agent, String fromDate, String toDate, String trackNumber){
		if(StringUtil.notEmpty(agent)){
			this.selectAgent(agent);
		}
		if(StringUtil.notEmpty(fromDate)){
			this.setFulfillmentFromDate(fromDate);
		}
		if(StringUtil.notEmpty(toDate)){
			this.setFulfillmentToDate(toDate);
		}
		if(StringUtil.notEmpty(trackNumber)){
			this.setTrackNumber(trackNumber);
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
}
