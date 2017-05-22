/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: The page class describes the Supply Inventory Tracking page.
 * The page opens after clicking the button Inventory Tracking on Supply product details page 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Sep 1, 2012
 */
public class LicMgrSupplyInventoryTrackingPage extends LicMgrProductCommonPage {
	private static LicMgrSupplyInventoryTrackingPage instance=null;
	
	protected LicMgrSupplyInventoryTrackingPage(){}
	
	public static LicMgrSupplyInventoryTrackingPage getInstance(){
		if(instance==null){
			instance=new LicMgrSupplyInventoryTrackingPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Input.text", ".id", 
					new RegularExpression("SupplyInvChangeHistorySearchCriteria-\\d+\\.createDateFrom", false));	
	}
	
	public String getTransactionDateFrom() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression("SupplyInvChangeHistorySearchCriteria-\\d+\\.createDateFrom", false));
	}
	
	public String getTransactionDateTo() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression("SupplyInvChangeHistorySearchCriteria-\\d+\\.createDateTo", false));
	}
	
	public void setTransactionDateFrom(String fromDate) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyInvChangeHistorySearchCriteria-\\d+\\.createDateFrom", false), fromDate);
	}
	
	public void setTransactionDateTo(String toDate) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyInvChangeHistorySearchCriteria-\\d+\\.createDateTo", false), toDate);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void searchInventoryTrackingRecords(String fromDate, String toDate) {
		logger.info("Search inventory tracking from " + fromDate + " to " + toDate);
		this.setTransactionDateFrom(fromDate);
		this.setTransactionDateTo(toDate);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getAdjustmentNoteIcons(IHtmlTable table, int index) {
		IHtmlObject[] rows = browser.getHtmlObject(".class", "Html.TR", table);
		if (rows == null || rows.length <= index) {
			throw new ItemNotFoundException("Can't find inventory tracking record TR " + index );
		}
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Img", ".src", "common/images_skin1/icon_task_list.gif", rows[index]);
		Browser.unregister(rows);
		return objs;
	}
	
	public String getAdjustmentNotes(IHtmlTable table, int index) {
		IHtmlObject[] objs = this.getAdjustmentNoteIcons(table, index);
		if (objs==null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find adjustment notes icon");
		}
		String alt = objs[0].getAttributeValue("alt");
		Browser.unregister(objs);
		return alt;
	}
	
	private IHtmlObject[] getInventoryTrackingTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", 
				new RegularExpression("^Transaction Date/Time.*", false));
		if (objs==null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find inventory tracking records table.");
		}
		return objs;
	}
	
	public SupplyInfo getFirstSupplyInventoryAdjustmentInfo() {
		SupplyInfo supply = new SupplyInfo();
		
		IHtmlObject[] objs = this.getInventoryTrackingTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> values = ((IHtmlTable)objs[0]).getRowValues(1);
		supply.transactionDateTime = values.get(0);
		supply.supplyReceivedDate = values.get(1);
		supply.adjustmentType = values.get(2);
		supply.supplyOrderNum = values.get(3);
		supply.adjustmentQuantity = values.get(4);
		supply.qtyOnHand = values.get(5);
		supply.adjustmentUser = values.get(6).replace(", ", ",");
		supply.adjustmentLocation = values.get(7);
		if (!supply.adjustmentType.equals("Fulfill Supplies")) {
			supply.adjustmentNotes = this.getAdjustmentNotes(table, 1);
		} else {
			supply.adjustmentNotes = values.get(8);
		}
		Browser.unregister(objs);
		return supply;
	}
	
	public boolean compareWithFirstSupplyInventoryAdjustmentInfo(SupplyInfo supply) {
		SupplyInfo actSupply = this.getFirstSupplyInventoryAdjustmentInfo();
		supply.transactionDateTime = DateFunctions.formatDate(supply.transactionDateTime, "EEE dd MMM yyyy");
		boolean result = actSupply.transactionDateTime.startsWith(supply.transactionDateTime);
		if (!result) {
			logger.error("Transaction date is wrong! Expected:" + supply.transactionDateTime + " Actual:" + actSupply.transactionDateTime);
		} else {
			logger.info("Tansaction date is correct as " + supply.transactionDateTime);
		}
		if (supply.supplyReceivedDate != null) {
			supply.supplyReceivedDate = DateFunctions.formatDate(supply.supplyReceivedDate, "EEE dd MMM yyyy");
			result &= MiscFunctions.compareResult("Supplies Received", supply.supplyReceivedDate,
					actSupply.supplyReceivedDate); 
		}
		
		result &= MiscFunctions.compareResult("Action", supply.adjustmentType,
				actSupply.adjustmentType); 
		if (supply.supplyOrderNum != null) {
			result &= MiscFunctions.compareResult("Supplies Order", supply.supplyOrderNum,
					actSupply.supplyOrderNum); 
		}
		result &= MiscFunctions.compareResult("Qty Changed", supply.adjustmentQuantity,
				actSupply.adjustmentQuantity); 
		if (supply.qtyOnHand != null) {
			result &= MiscFunctions.compareResult("New Qty On Hand", supply.qtyOnHand, actSupply.qtyOnHand); 
		}
		result &= MiscFunctions.compareResult("User", supply.adjustmentUser, actSupply.adjustmentUser); 
		result &= MiscFunctions.compareResult("Location", supply.adjustmentLocation,	actSupply.adjustmentLocation); 
		if (supply.adjustmentNotes != null) {
			result &= MiscFunctions.compareResult("Notes", supply.adjustmentNotes,	actSupply.adjustmentNotes); 
		}
		return result;
	}
	
	public void verifySupplyInventoryAdjustmentInfo(SupplyInfo supply) {
		boolean actResult = this.compareWithFirstSupplyInventoryAdjustmentInfo(supply);
		if (!actResult) {
			throw new ErrorOnPageException("The supply inventory adjustment info is wrong! Please check logger error!");
		}
		logger.info("The supply inventory adjustment info is correct!");
	}
	
	public int getRowIndexByTranscationDate(String transcationDate) {
		IHtmlObject[] objs = this.getInventoryTrackingTables();
		List<String> dates = ((IHtmlTable)objs[0]).getColumnValues(0);
		int row = -1;
		for (int i = 0; i < dates.size(); i++) {
			if (dates.get(i).startsWith(transcationDate)){
				row = i;
				break;
			}
		}
		return row;
	}
	
	public void clickSuppliesOrderNum(String ordNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum);
	}
}
