package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
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
public class LicMgrConsumableOrderDetailsPage extends LicMgrOrderDetailsCommonPage {
	
	private static LicMgrConsumableOrderDetailsPage _instance = null;
	
	protected LicMgrConsumableOrderDetailsPage() {}
	
	public static LicMgrConsumableOrderDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrConsumableOrderDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "com.reserveamerica.licensing.order.configurable.view.ConsumableOrderSearchView");
	}
	
	/**
	 * Get consumable order status
	 * @return
	 */
	public String getStatus() {
		return this.getOrderAttributeValueByName("Status", 0);
	}
	
	/**
	 * Get the order creation date
	 * @return
	 */
	public String getCreationDate() {
		return this.getOrderAttributeValueByName("Creation Date", 0);
	}
	
	/**
	 * Get the order creation user
	 * @return
	 */
	public String getCreationUser() {
		return this.getOrderAttributeValueByName("Creation User", 0);
	}

	public String getOrderType() {
		return this.getOrderAttributeValueByName("Order Type");
	}
	
	public void clickVoidOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Void Order$", false));
	}
	
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart");
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
	/**
	 * get alert message.
	 * @return
	 */
	public String getAlertMsg(){
		String msg = "";
		IHtmlObject[] notSet = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(notSet.length>0)
		{
		 msg = notSet[0].text();
		}
		
		Browser.unregister(notSet);
		return msg; 
	}
	
	/**
	 * This method get all the order items in the order detail page
	 * @return
	 */
	public List<List<String>> getOrderItemsInfo(){
		List<List<String>> itemsInfo = new ArrayList<List<String>>();
		IHtmlObject[] orderTable = browser.getHtmlObject(".class", "Html.TABLE", ".className", "gridView");
		IHtmlTable table = (IHtmlTable)orderTable[0];
		for(int i=1; i< table.rowCount(); i++){
			itemsInfo.add(table.getRowValues(i));
		}
		Browser.unregister(orderTable);
		return itemsInfo;
	}
}
