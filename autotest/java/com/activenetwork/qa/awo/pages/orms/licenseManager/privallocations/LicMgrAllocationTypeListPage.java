package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivAllocationsCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Allocation Type List page: Admin -> Privilege Allocations -> Allocation Type
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class LicMgrAllocationTypeListPage extends
		LicMgrPrivAllocationsCommonPage {
	private static LicMgrAllocationTypeListPage instance=null;
	
	protected LicMgrAllocationTypeListPage(){}
	
	public static LicMgrAllocationTypeListPage getInstance(){
		if(instance == null){
			instance=new LicMgrAllocationTypeListPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] allocationTypeTable() {
		return Property.concatPropertyArray(table(), ".id", "allocationTypeGrid_LIST");
	}
	
	protected Property[] addAllocationTypesLink() {       
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("Add Allocation Type(s)?", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.allocationTypeTable());
	}
	
	public void clickAddAllocationTypes() {
		browser.clickGuiObject(this.addAllocationTypesLink());
	}
	
	private IHtmlObject[] allocationTypeTables() {
		IHtmlObject[] tables = browser.getHtmlObject(this.allocationTypeTable());
		if (tables.length < 1) {
			throw new ErrorOnPageException("Can't find allocation type tables...");
		}
		return tables;
	}
	
	private int getIDColnumIndex(IHtmlTable table) {
		return table.findColumn(0, "ID");
	}
	
	private int getAllocationTypeColnumIndex(IHtmlTable table) {
		return table.findColumn(0, "Allocation Type");
	}
	
	public boolean isAllocationTypeExist(String type) {
		IHtmlObject[] objs = this.allocationTypeTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int columnIndex = this.getAllocationTypeColnumIndex(table);
		int rowIndex = table.findRow(columnIndex, type);
		Browser.unregister(objs);
		return rowIndex > 0;
	}
	
	public String getAllocationTypeID(String type) {
		IHtmlObject[] objs = this.allocationTypeTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int columnIndex = this.getAllocationTypeColnumIndex(table);
		int rowIndex = table.findRow(columnIndex, type);
		String id = table.getCellValue(rowIndex, this.getIDColnumIndex(table));
		Browser.unregister(objs);
		return id;
	}
}
