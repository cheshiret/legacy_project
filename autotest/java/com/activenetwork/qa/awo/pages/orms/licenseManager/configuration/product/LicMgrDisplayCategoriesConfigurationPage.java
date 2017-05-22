package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 20, 2011
 */
public class LicMgrDisplayCategoriesConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrDisplayCategoriesConfigurationPage _instance = null;
	
	protected LicMgrDisplayCategoriesConfigurationPage() {}
	
	public static LicMgrDisplayCategoriesConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrDisplayCategoriesConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Display Categories", ".className", "selected");
	}
	
	public boolean checkDisplayCategoryExists(String identifier) {
		return this.checkIdentifierExists(identifier, "displaycategorylist");
	}
	
	public List<String> getFirstDisplayCategory() {
		return this.getFirstRowInfo("displaycategorylist");
	}
	
	/**
	 * Get the all display categories and their corresponding display orders
	 * @return
	 */
	public Map<String, Integer> getDisplayCategoriesAndOrders() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Display Category table object can't be found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		Map<String, Integer> displayCategoriesOrders = new HashMap<String, Integer>();
		for(int i = 1; i < table.rowCount(); i ++) {
			displayCategoriesOrders.put(table.getCellValue(i, 1), Integer.parseInt(table.getCellValue(i, 2)));
		}
		
		Browser.unregister(objs);
		return displayCategoriesOrders;
	}
	
	/**
	 * The method used to get display category info from specific row
	 * 
	 * @param identifier
	 *            ---one cell value in your expected parse row.
	 * @param colName
	 *            ---the column name for your given identifier
	 * @return
	 */
	public List<String> getDisplayCategoryInfo(String identifier, String expectColName) {
		return this.getRowInfo("displaycategorylist", identifier, expectColName);
	}
	
	/**
	 * Get display category id identified by display order or display description
	 * @param displayOrderOrDscr
	 * @return
	 */
	public String getDisplayCategoryId(String displayOrderOrDscr) {
		IHtmlTable table = this.getTableObject("displaycategorylist");
		int columnIndex = 0;
		int rowIndex = 0;
		if(displayOrderOrDscr.matches("[0-9]+")) {
			columnIndex = 2;
		} else {
			columnIndex = 1;
		}
		rowIndex = table.findRow(columnIndex, displayOrderOrDscr);
		String id = table.getCellValue(rowIndex, 0);//ID column index is 0
		
		return id;
	}
	
	/**
	 * Get the max display order
	 * @return
	 */
	public int getMaxDisplayOrder() {
		List<String> displayOrders = this.getColumnValue("displaycategorylist", "Display Order");
		displayOrders.remove(0);
		Collections.sort(displayOrders);
		int maxOrder = Integer.parseInt(displayOrders.get(displayOrders.size() - 1));
		return maxOrder;
	}
	
	/**
	 * Verify whether the display category brief information is correct with expected or not
	 * @param expectedDisplayCategory
	 */
	public void verifyDisplayCategoryInfo(PrivilegeDisplayCategory expectedDisplayCategory) {
		boolean pass = true;
		if(!this.checkDisplayCategoryExists(expectedDisplayCategory.displayOrder)) {
			throw new ErrorOnPageException("Add Display category Failed.");
		}
		List<String> rowInfo = this.getDisplayCategoryInfo(expectedDisplayCategory.description, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Display category Failed.");
		}
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Display Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(expectedDisplayCategory.description)){
			pass &= false;
			logger.error("Display Category Description "+rowInfo.get(1)+" not correct.");
		}
		if(!rowInfo.get(2).equalsIgnoreCase(expectedDisplayCategory.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+rowInfo.get(2)+" not correct.");
		}
		if(!rowInfo.get(3).equalsIgnoreCase(expectedDisplayCategory.hiddenInSalesFlow)){
			pass &= false;
			logger.error("Display Category Hidden In Sales Workflow "+rowInfo.get(3)+" not correct.");
		}
		
		if(!pass) {
			throw new ErrorOnPageException("Display Category brief information is incorrect.");
		} else {
			logger.info("Verify Display Category brief informatio correctly.");
		}
	}
}
