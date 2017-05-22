/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  2011-9-14
 */
public class LicMgrSystemConfigurationPage extends LicMgrCommonTopMenuPage {
	
	private static LicMgrSystemConfigurationPage _instance = null;
	
	protected LicMgrSystemConfigurationPage() {}
	
	public static LicMgrSystemConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSystemConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "System Configuration", ".className", "selected");
	}
	
	public void clickVendorStatusReasonMgtTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Vendor Status/Reason Mgt", true);
	}
	
	public void clickAgentStatusReasonMgtTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Agent Status/Reason Mgt", true);
	}
	
	public void clickEFTSchedulesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Invoice Schedules", true);
	}
	
	public void clickBondIssuersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Bond Issuers", true);
	}
	
	public void clickLienCompaniesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Lien Companies", true);
	}
	
	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	/**
	 * Get table object by table id. this method is used to get all table objects in all tabs
	 * @param tableId
	 * @return
	 */
	protected IHtmlTable getTableObject(Object tableId) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", tableId);
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Table object by ID=" + tableId);
		}
		
		IHtmlTable table = ((IHtmlTable)objs[0]);
		
		return table;
	}
	
	/**
	 * Get specific row values identified by column value in specific table
	 * @param tableId - used to get table object
	 * @param identifierColumnName - used to get column index
	 * @param identifierValue - used to get row index
	 * @return
	 */
	protected List<String> getTableRowValue(Object tableId, String identifierColumnName, String identifierValue) {
		IHtmlTable table = this.getTableObject(tableId);
		int columnIndex = table.findColumn(0, identifierColumnName);
		int rowIndex = table.findRow(columnIndex, identifierValue);
		List<String> rowValues = table.getRowValues(rowIndex);

		return rowValues;
	}
}
