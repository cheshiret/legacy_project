package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;

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
public class LicMgrSupplyOrderDetailsPage extends LicMgrOrderDetailsCommonPage {

	private static LicMgrSupplyOrderDetailsPage _instance = null;

	protected LicMgrSupplyOrderDetailsPage() {}

	public static LicMgrSupplyOrderDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSupplyOrderDetailsPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "com.reserveamerica.licensing.ui.order.supplies.SupplyOrderDetailUI");
	}

	public void clickVoidOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void Order");
	}

	public void clickCancelOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Order");
	}

	public void clickRejectOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reject Order");
	}

	public String getOrderStatus() {
		return this.getOrderAttributeValueByName("Order Status", 0);
	}

	public String getOrderType() {
		return this.getOrderAttributeValueByName("Order Type");
	}

	public String getFulfillmentStatus() {
		return this.getOrderAttributeValueByName("Fulfillment Status", 0);
	}

	public String getCreateDate() {
		return this.getOrderAttributeValueByName("Create Date", 0);
	}

	public String getCreateUser() {
		return this.getOrderAttributeValueByName("Create User", 0);
	}

	public String getBillingCustomerName() {
		return this.getOrderAttributeValueByName("Customer Name", 0);
	}

	public String getBillingCustomerPhoneNum() {
		return this.getOrderAttributeValueByName("Phone Number", 0);
	}

	public String getBillingCustomerEmailAddress() {
		return this.getOrderAttributeValueByName("Email Address", 0);
	}

	public void clickApply() {

	}

	public void clickCancel() {

	}

	public void setProductName(String string) {

	}

	public String getErrorMessage() {
		return null;
	}

	public void setProductDescription(String string) {

	}

	public void setProductCode(String string) {

	}

	public void setMaxDailyOrder(String string) {

	}

	public void setReorderThreshold(String string) {

	}
}
