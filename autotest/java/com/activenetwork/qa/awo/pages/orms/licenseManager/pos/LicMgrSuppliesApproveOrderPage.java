/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Supplies Approve Order page, accessed by: click Approve Order button on supplies order search/list page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Sep 4, 2012
 */
public class LicMgrSuppliesApproveOrderPage extends LicMgrOrderSearchCommonPage {

	private static LicMgrSuppliesApproveOrderPage _instance = null;

	protected LicMgrSuppliesApproveOrderPage() {
	}

	public static LicMgrSuppliesApproveOrderPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrSuppliesApproveOrderPage();
		}

		return _instance;
	}
	
	@Override
	public void setupOrderSearchCriteria(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "suppliesOrderForApprovalList");
	}

	public void selectOrder(int index) {
		browser.selectCheckBox(".id", new RegularExpression(
				"ApproveSupplyOrderItemView-\\d+\\.orderSelected", false), index);
	}
	
	public void setApproveNote(int index, String notes) {
		browser.setTextField(".id", new RegularExpression(
				"ApproveSupplyOrderItemView-\\d+\\.notes", false), notes, index);
	}
	
	public void setQtyToApprove(int index, String qty) {
		browser.setTextField(".id", new RegularExpression(
				"ApproveSupplyOrderItemView-\\d+\\.quantityInput", false), qty, index);
	}
	
	public void clickApproveSelectedOrders() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Approve Selected Orders");
	}
	
	private IHtmlObject[] getApproveSupplyOrderListTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "suppliesOrderForApprovalGrid");
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find approve supplies order list tables.");
		}
		return objs;
	}
	
	private int getSupplyOrderRowIndex(String ordNum) {
		IHtmlObject[] objs = this.getApproveSupplyOrderListTables();
		int row = ((IHtmlTable)objs[0]).findRow(1, "Order #:"+ordNum);
		Browser.unregister(objs);
		return row;
	}
	
	private int getNumOfStoreSelectedCheckBoxes() {
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression(
				"ApproveSupplyOrderItemView-\\d+\\.storeSelected", false));
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public void selectSupplyOrderToApprove(String ordNum, String notes, String qty) {
		int trRowIndex = this.getSupplyOrderRowIndex(ordNum);
		int storeRows = this.getNumOfStoreSelectedCheckBoxes();
		int index = (trRowIndex - storeRows - 1) / 2;
		this.setApproveNote(index, notes);
		this.setQtyToApprove(index, qty);
		this.selectOrder(index);
	}
}
