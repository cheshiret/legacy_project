package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.DockInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @Date  Sep 10, 2012
 */
public class InvMgrDockListPage extends InvMgrDockSlipCommonPage {
	
	private static InvMgrDockListPage _instance = null;
	
	private InvMgrDockListPage() {}
	
	public static InvMgrDockListPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrDockListPage();
		}
		
		return _instance;
	}
	
	private static final String DOCK_AREA_NAME_COL = "Dock/Area Name";
	private static final String PARENT_COL = "Parent";
	private static final String SLIPS_NUM_COL = "#Slips";
	private static final String DESCRIPTION_COL = "Description";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "DockListGrid_LIST");
	}
	
	public void clickAddNewDockArea() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Dock/Area", true);
	}
	
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete", true);
	}
	
	public void clickDockNameLink(String name) {
		browser.clickGuiObject(".class", "Html.A", ".text", name, true);
	}
	
	public void selectAllDocks() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void clickSlipNumberLink(String dockName){
		int row = getRowNumByDockName(dockName);
		IHtmlTable table = getDockListTableObject();
		String numOfSlips = table.getCellValue(row, 3);
		logger.info("Click #"+numOfSlips+" for Dock "+dockName);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(dockName, true));
		browser.clickGuiObject(".class", "Html.A", ".text", numOfSlips, true, 0, objs[0]);
	}
	
	private IHtmlTable getDockListTableObject() {
		String tableId = "Dock(sSlip)?ListGrid_LIST";
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(tableId, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Dock List table object - " + tableId);
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	public int getRowNumByDockName(String name) {
		IHtmlTable table = getDockListTableObject();
		
		int column = table.findColumn(0, DOCK_AREA_NAME_COL);
		int row = -1;
		do{
			table = getDockListTableObject();
			row = table.findRow(column, name);
			if(row < 0 && this.hasNext()) {
				clickNext();
				ajax.waitLoading();
				this.waitLoading();
			}else{
				break;
			}
		}while(row < 0);
		return row;
	}
	
	private boolean hasNext() {
		return browser.checkHtmlObjectEnabled(".class","Html.A",".text", "Next");
	}
	
	private void clickNext(){
		browser.clickGuiObject(".class","Html.A",".text", "Next");
	}

	public void selectDock(String name) {
		int rowIndex = getRowNumByDockName(name);
		
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), rowIndex - 1);
	}
	
	public void deleteDock(String name) {
		selectDock(name);
		clickDelete();
		ajax.waitLoading();
	}
	
//	public void deleteDocks(String... docks) {
//		for(String name :  docks) {
//			deleteDock(name);
//		}
//	}
	
	public DockInfo getDockInfoByName(String name){
		int row = this.getRowNumByDockName(name);
		
		// maybe turn page when get row, so get table after row!
		IHtmlTable table = getDockListTableObject();
		if(row < 0){
			throw new ItemNotFoundException("Can't find dock with given name:"+name);
		}
		List<String> dockList = table.getRowValues(row);// should exist only one record.
		DockInfo dock = new DockInfo();
		dock.setName(dockList.get(1));
		dock.setParent(dockList.get(2));
		dock.setNumOfAssignedSlips(Integer.valueOf(dockList.get(3)));
		dock.setDescription(dockList.get(4));
		return dock;
	}
	
	public void verifyDockInfo(DockInfo expectDockInfo){
		DockInfo actualDockInfo = this.getDockInfoByName(expectDockInfo.getName());
		boolean result = true;
		result &= MiscFunctions.compareString("Name", expectDockInfo.getName(), actualDockInfo.getName());
		result &= MiscFunctions.compareString("Parent", expectDockInfo.getParent(), actualDockInfo.getParent());
		result &= MiscFunctions.compareString("Number of Assigned Slip", expectDockInfo.getNumOfAssignedSlips()+"", actualDockInfo.getNumOfAssignedSlips()+"");
		result &= MiscFunctions.compareString("Description", expectDockInfo.getDescription(), actualDockInfo.getDescription());
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public String getFirstDockName(){
		IHtmlTable table = getDockListTableObject();
		return table.getCellValue(1, 1);
	}
	
	public void verifyDockExist(String dockName, boolean expectExist){
		logger.info("Verify dock:"+dockName+" exist in dock list page or not.");
		int row = this.getRowNumByDockName(dockName);
		if(expectExist){// should exist 
			if(row <= 0){
				throw new ErrorOnPageException("Dock "+dockName+" should be displayed in dock list page!");
			}
		} else {// should NOT exist
			if(row >= 0){
				throw new ErrorOnPageException("Dock "+dockName+" should NOT be displayed in dock list page!");
			}
		}
	}
	
	public boolean isDockExist(String dockName){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",dockName);
	}
}
