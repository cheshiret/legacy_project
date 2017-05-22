package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author qchen
 * @Date  Oct 27, 2011
 */
public class LicMgrSupplyOrderSearchPage extends LicMgrOrderSearchCommonPage {

	private static LicMgrSupplyOrderSearchPage _instance = null;

	protected LicMgrSupplyOrderSearchPage() {}

	public static LicMgrSupplyOrderSearchPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSupplyOrderSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {		
//        boolean exist = browser.checkHtmlObjectExists(".text", "Action");
//        exist &= browser.checkHtmlObjectExists(".href", new RegularExpression(".*suppliesOrder$", false));
//		return exist;
		
		//Quentin[20140415] ui changes
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.orderNumber", false));
	}

	/**
	 * Click Approve Order
	 */
	public void clickApproveOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Approve Order");
	}

	/**
	 * Click Generate Pick List
	 */
	public void clickGeneratePickList() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Generate Pick List");
	}

	/**
	 * Click Fulfill Order
	 */
	public void clickFulfillOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fulfill Order");
	}

	/**
	 * Click View Fulfillment
	 */
	public void clickViewFulfillment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Fulfillment");
	}

	/**
	 * Set supply order number
	 * @param ordNum
	 */
	public void setOrderNumber(String ordNum) {
		browser.setTextField(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.orderNumber", false), ordNum);
	}

	/**
	 * Select supply order status
	 * @param status
	 */
	public void selectOrderStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.orderStatus", false), status);
	}

	/**
	 * Select fulfillment status
	 * @param fulfillStatus
	 */
	public void selectFulfillmentStatus(String fulfillStatus) {
		browser.selectDropdownList(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.fulfillmentStatus", false), fulfillStatus);
	}

	/**
	 * Select agent
	 * @param agent
	 */
	public void selectAgent(String agent) {
		browser.selectDropdownList(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.store", false), agent);
	}

	/**
	 * Set create from date value
	 * @param fromDate
	 */
	public void setCreateFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.createFromDate_ForDisplay", false), fromDate);
	}

	/**
	 * Set create to date value
	 * @param toDate
	 */
	public void setCreateToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression("SupplyOrderSearchCriteria-\\d+\\.createToDate_ForDisplay", false), toDate);
	}

	@Override
	public void setupOrderSearchCriteria(Object object) {

	}
	

	public void clickSuppliesOrdersLabel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Supplies Orders");
	}
	
	public void searchSuppliesOrderByOrderNum(String ordNum) {
		this.setOrderNumber(ordNum);
		this.clickGo();
		ajax.waitLoading();
	}
}
