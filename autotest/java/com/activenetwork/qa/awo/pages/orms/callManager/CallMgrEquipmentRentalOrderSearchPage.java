package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class CallMgrEquipmentRentalOrderSearchPage extends CallMgrCommonTopMenuPage {
	private final String prefix = "FacilityRentalOrderSearchCriteria";
	
	static private CallMgrEquipmentRentalOrderSearchPage _instance = null;
	
	static public CallMgrEquipmentRentalOrderSearchPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrEquipmentRentalOrderSearchPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Equipment Rental Order Search/List");
	}
	
	protected Property[] orderNumTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.orderNumber", false));
	}
	
	protected Property[] facilityTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.facilityName", false));
	}
	
	protected Property[] equipNameTextField() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.equipmentName", false));
	}
	
	protected Property[] searchButton() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Search$", false));
	}
	
	protected Property[] orderListTable() {
		return Property.concatPropertyArray(this.table(), ".id", "OrderListGrid_LIST");
	}
	
	public void setOrderNum(String ordNum) {
		browser.setTextField(orderNumTextField(), ordNum);
	}
	
	public void setFacility(String facility) {
		browser.setTextField(facilityTextField(), facility);
		Property[] p = Property.concatPropertyArray(this.div(), ".className", "ac_results");
		browser.waitExists(p);
		browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
		Browser.sleep(3);
		browser.clickGuiObject(".class", "Html.LI", ".text", facility, true);
		ajax.waitLoading();
	}
	
	public void clickSearch() {
		browser.clickGuiObject(searchButton());
	}
	
	public IHtmlObject[] getOrderListTable() {
		IHtmlObject[] objs = browser.getHtmlObject(orderListTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Failed to find order list table on page.");
		return objs;
	}
 
	public void clickOrdNum(String ordNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum);
	}
}