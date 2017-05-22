/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.WeaponInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 19, 2012
 */

public class LicMgrWeaponsConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrWeaponsConfigurationPage _instance = null;
	
	protected LicMgrWeaponsConfigurationPage() {}
	
	public static LicMgrWeaponsConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrWeaponsConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "weaponList");
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<WeaponInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<WeaponInfo> records = new ArrayList<WeaponInfo>();
		int rows;
		int columns;
		WeaponInfo info;
		
		
		objs = browser.getTableTestObject(".id", "weapons");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Weapons table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrWeaponsConfigurationPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new WeaponInfo();
			info.setCode(table.getCellValue(i, table.findColumn(0, "Code")));
			info.setDescription(table.getCellValue(i, table.findColumn(0, "Description")));
			info.setCreateUser(table.getCellValue(i, table.findColumn(0, "Creation User")));
			info.setCreateLoc(table.getCellValue(i, table.findColumn(0, "Creation Location")));
			info.setCreateDateTime(table.getCellValue(i, table.findColumn(0, "Creation Date/Time")));			
			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	
}
