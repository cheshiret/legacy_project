package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Feb 14, 2012
 */
public class InvMgrEntranceListPage extends InventoryManagerPage {

	private static InvMgrEntranceListPage _instance = null;

	public static InvMgrEntranceListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrEntranceListPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "EntranceSearchCriteria.searchBy");
	}
	
	public void clickAddNew() {
		browser.clickGuiObject(".class","Html.A",".text", "Add New", true);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text", "Search", true);
	}
	
	public void setSearchBy(String searchBy){
		browser.selectDropdownList(".id", "EntranceSearchCriteria.searchBy", searchBy, true);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", "EntranceSearchCriteria.searchByValue", searchValue, true);
	}

	public void setStatus(String status){
		browser.selectDropdownList(".id", "EntranceSearchCriteria.status", status, true);
	}
	
	public void setUpSearchCriteria(EntranceInfo entranceInfo){
		this.setSearchBy(entranceInfo.searchBy);
		this.setSearchValue(entranceInfo.searchValue);
		this.setStatus(entranceInfo.status);
	}
	
	public List<List<String>> getEntranceList(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Htnl.Table", ".text", new RegularExpression("Entrance Code.*", false));
		if(objs == null || objs.length < 0) {
			throw new ErrorOnPageException("Can't find the table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> entranceList = new ArrayList<List<String>>();
		if(table.rowCount() > 2){
			// 1st line is title.
			for(int row=1; row<table.rowCount();row++){
				entranceList.add(table.getRowValues(row));
			}
			
		}
		return entranceList;
	}
	
	public void clickEntranceCodeLink(String entranceCode){
		browser.clickGuiObject(".class", "Html.A", ".text", entranceCode);
	}
	
	/**
	 * Get notes info, includes successful message and error message
	 * @return
	 */
	public String getNotesInfo(){
		String notes = "";
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", 
				".id", new RegularExpression("NOTSET|com\\.reserveamerica\\.common\\.ValidationEx", false), 
				".className", new RegularExpression("message msg.*", false));
		
		IHtmlObject[] objs=browser.getHtmlObject(p);
		if(objs != null && objs.length > 0){
			notes=objs[0].getProperty(".text");
		}else{
			throw new ObjectNotFoundException("No notes object can be found.");
		}
		
		Browser.unregister(objs);
		return notes;
	}
	
	public void searchEntrance(EntranceInfo entranceInfo){
		this.setUpSearchCriteria(entranceInfo);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchEntranceByCode(String code){
		this.setSearchBy("Entrance Code");
		this.setSearchValue(code);
		this.clickGo();
		this.waitLoading();
	}

	public void clickQuotaTypesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Quota Types");	
	}
	
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	//TODO: identify how to ignore duplicated records displayed after search criteria setuped.
	public void selectEntranceCheckBox() {
		browser.selectCheckBox(".id", "row_0_checkbox");		
	}

	public void clickPermitTypesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Permit Types");	
	}
	
	public IHtmlObject[] getEntranceTable(){
		return browser.getTableTestObject(".text", new RegularExpression("^ ?Entrance Code ?Entrance Name ?Entrance Type.*", false));
	}
	
	public int getEntranceRowByCode(String entranceCode){
		int row=-1;
		IHtmlObject[] objs=this.getEntranceTable();
		if(objs.length<1){
			throw new ObjectNotFoundException("The table that text starts with 'Entrance Code' can't be found!");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		row = table.findRow(1, entranceCode);
		Browser.unregister(objs);
		return row;
	}
	
	public String getEntranceStatus(){
		IHtmlObject[] objs=this.getEntranceTable();
		
		if(objs.length<1){
			throw new ObjectNotFoundException("The table that text starts with 'Entrance Code' can't be found!");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		String status = table.getColumnValues(5).get(1);
		Browser.unregister(objs);
		return status;
	}
}
