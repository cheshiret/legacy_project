package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivAllocationsCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jan 29, 2014
 */
public class LicMgrAllocationTypeLicYearPage extends
		LicMgrPrivAllocationsCommonPage {
	private static LicMgrAllocationTypeLicYearPage instance=null;
	
	protected LicMgrAllocationTypeLicYearPage(){}
	
	public static LicMgrAllocationTypeLicYearPage getInstance(){
		if(instance == null){
			instance=new LicMgrAllocationTypeLicYearPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] allocTypeLicYearTopTable() {
		return Property.concatPropertyArray(table(), ".id", "AllocationTypeLicYearListBar");
	}
	
	protected Property[] addAllocTypeLicYearLink() {
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("Add Allocation Type Licen(s|c)e Year", false));
	}
	
	protected Property[] allocationTypeLicYearTable() {
		return Property.concatPropertyArray(table(), ".id", "allocationTypeGrid_LIST");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.allocTypeLicYearTopTable());
	}
	
	public void clickAddAllocTypeLicYear() {
		browser.clickGuiObject(this.addAllocTypeLicYearLink());
	}
	
	public boolean isAddAllocTypeLicYearExist() {
		return browser.checkHtmlObjectExists(this.addAllocTypeLicYearLink());
	}
	
	private IHtmlObject[] allocationTypeLicYearTables() {
		IHtmlObject[] tables = browser.getHtmlObject(this.allocationTypeLicYearTable());
		if (tables.length < 1) {
			throw new ErrorOnPageException("Can't find allocation type license year tables...");
		}
		return tables;
	}
	
	public String getAllocationTypeLicYearID(String allocType, String licYear) {
		IHtmlObject[] objs = this.allocationTypeLicYearTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = -1;
		for (int i = 1; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 2).equals(allocType) && table.getCellValue(i, 3).equals(licYear)) {
				rowIndex = i;
				break;
			}
		}
		String id = table.getCellValue(rowIndex, 0);
		Browser.unregister(objs);
		return id;
	}
}
