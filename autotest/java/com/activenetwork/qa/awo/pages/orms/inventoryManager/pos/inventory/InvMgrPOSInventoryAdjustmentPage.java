package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryAdjust.SerializationInventoryRange;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 6, 2012
 */
public class InvMgrPOSInventoryAdjustmentPage extends InvMgrPOSInventoryManagementPage {
	
	private static InvMgrPOSInventoryAdjustmentPage _insance = null;
	
	private InvMgrPOSInventoryAdjustmentPage() {}
	
	public static InvMgrPOSInventoryAdjustmentPage getInstance() {
		if(_insance == null) {
			_insance = new InvMgrPOSInventoryAdjustmentPage();
		}
		
		return _insance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "content_Adjust Inventory");
	}
	
	public List<String> getSupplier(){
		List<String> value=browser.getDropdownElements(".id", new RegularExpression("POSInventoryAdjustmentBean-\\d+\\.supplier", false));
		return value;
	}
	
	public void selectFirstSupplier(){
		browser.selectDropdownList(".id", new RegularExpression("POSInventoryAdjustmentBean-\\d+\\.supplier", false), 1);
	}
	
	public void selectSupplier(String s){
		browser.selectDropdownList(".id", new RegularExpression("POSInventoryAdjustmentBean-\\d+\\.supplier", false), s);
	}

	public void clickAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public int getSerializationRangeNum(){
		IHtmlObject[] ranges = browser.getHtmlObject(".id", new RegularExpression("ProductSerialRangeView-\\d+\\.rangeFrom",false));
		int num = ranges.length;
		Browser.unregister(ranges);
		return num;
	}
	
	public void setSerializationRange(List<SerializationInventoryRange> ranges)
	{
		int alreadyExistRange = this.getSerializationRangeNum();
		for(int i=0; i<ranges.size()-alreadyExistRange; i++)
		{
			this.clickAdd();
			ajax.waitLoading();
		}
		
		IHtmlObject[] rangeTbl = browser.getHtmlObject(".id", "ranges_table");
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", rangeTbl[0]);
		
		for(int j=0; j<trs.length-1;j++)//the last one is "Add"
		{
			browser.setTextField(".id", new RegularExpression("ProductSerialRangeView-\\d+\\.rangeFrom",false),ranges.get(j).from , trs[j]);
			browser.setTextField(".id", new RegularExpression("ProductSerialRangeView-\\d+\\.rangeTo",false),ranges.get(j).to , trs[j]);
		}
		
		Browser.unregister(rangeTbl);
		Browser.unregister(trs);
	}
	
	public void setNotes(String text)
	{
		browser.setTextArea(".id", new RegularExpression("POSInventoryAdjustmentBean-\\d+\\.adjustmentNotes", false), text);
	}
	
	public void setDateSuppliesReceived(String date)
	{
		browser.setTextField(".id", new RegularExpression("POSInventoryAdjustmentBean-\\d+\\.dateReceived_ForDisplay", false), date);
		this.refresh();
	}

	private void refresh() {
		browser.clickGuiObject(".class", "Html.TD", ".text", new RegularExpression("^Date Supplies Recieved",false));
		
	}
	
}
