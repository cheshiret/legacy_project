package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

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
public class LicMgrDisplaySubCategoriesConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrDisplaySubCategoriesConfigurationPage _instance = null;
	
	protected LicMgrDisplaySubCategoriesConfigurationPage() {}
	
	public static LicMgrDisplaySubCategoriesConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrDisplaySubCategoriesConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Display Sub-Categories", ".className", "selected");
	}
	
	public boolean checkDisplaySubCategoryExists(String identifier) {
		return this.checkIdentifierExists(identifier, "displaycategorylist");
	}
	
	/**
	 * Get the all display categories and their corresponding display orders
	 * @return
	 */
	public Map<String, Integer> getDisplaySubCategoriesAndOrders() {
		ajax.waitLoading();
		IHtmlObject objs[] = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Display Sub-Category table object can't be found.");
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
	 * Get display sub category id by description
	 * @param description
	 * @return id
	 */
	public String getDisplaySubCategoryIdByDescription(String description){
		String record="";
		IHtmlObject [] objs = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length ==0) {
			throw new ObjectNotFoundException("Display Sub-Categories table object can't be found.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row=table.findRow(1, description);
		if(row<0){
			logger
			.info("The given description cannot be found.");
		}else{
			record = table.getCellValue(row, 0);
		}
	
		Browser.unregister(objs);
		return record;
	}
	
	/**
	 * This method used to get display sub-category list information by category id
	 * @param id
	 * @return
	 */
	public List<String> getDisplaySubCategoryInfoById(String id){
		List<String> values=null;
		IHtmlObject [] objs = browser.getTableTestObject(".id", "displaycategorylist");
		if(objs.length ==0) {
			throw new ObjectNotFoundException("Display Sub-Categories table object can't be found.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row=table.findRow(0, id);
		if(row<0){
			logger
			.info("The given id cannot be found.");
		}else{
			values=table.getRowValues(row);
		}
		
		Browser.unregister(objs);
		return values;
	}
	
	/**
	 * The method used to get display sub-category info from specific row
	 * 
	 * @param identifier
	 *            ---one cell value in your expected parse row.
	 * @param colName
	 *            ---the column name for your given identifier
	 * @return
	 */
	public List<String> getDisplaySubCategoryInfo(String identifier, String expectColName) {
		return this.getRowInfo("displaycategorylist", identifier, expectColName);
	}
	
	/**
	 * Verify whether the display sub-category brief information is correct with expected or not
	 * @param expectedDisplayCategory
	 */
	public void verifyDisplaySubCategoryInfo(PrivilegeDisplayCategory expectedDisplayCategory) {
//		boolean pass = true;
//		if(!this.checkDisplaySubCategoryExists(expectedDisplayCategory.displayOrder)) {
//			throw new ErrorOnPageException("Add Display Sub-Category Failed.");
//		}
//		List<String> rowInfo = this.getDisplaySubCategoryInfo(expectedDisplayCategory.description, "Description");
//		if(rowInfo==null||rowInfo.size()<1){
//			pass &= false;
//			logger.error("Add Display Sub-Category Failed.");
//		}
//		if(!rowInfo.get(0).matches("\\d+")){
//			pass &= false;
//			logger.error("Display Sub-Category ID "+rowInfo.get(0)+" not correct.");
//		}
//		if(!rowInfo.get(1).equalsIgnoreCase(expectedDisplayCategory.description)){
//			pass &= false;
//			logger.error("Display Sub-Category Description "+rowInfo.get(1)+" not correct.");
//		}
//		if(!rowInfo.get(2).equalsIgnoreCase(expectedDisplayCategory.displayOrder)){
//			pass &= false;
//			logger.error("Display Sub-Category Display Order "+rowInfo.get(2)+" not correct.");
//		}
//		
		if(!this.compareDisplaySubCategoryInfo(expectedDisplayCategory.description, expectedDisplayCategory.displayOrder)) {
			throw new ErrorOnPageException("Display Sub-Category brief information is incorrect.");
		} else {
			logger.info("Verify Display Sub-Category brief informatio correctly.");
		}
	}
	
	public boolean compareDisplaySubCategoryInfo(String subCategoryDes, String subCategoryOrder) {
		boolean pass = true;
		if(!this.checkDisplaySubCategoryExists(subCategoryOrder)) {
			throw new ErrorOnPageException("Add Display Sub-Category Failed.");
		}
		List<String> rowInfo = this.getDisplaySubCategoryInfo(subCategoryDes, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Display Sub-Category Failed.");
		}
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Display Sub-Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(subCategoryDes)){
			pass &= false;
			logger.error("Display Sub-Category Description "+rowInfo.get(1)+" not correct.");
		}
		if(!rowInfo.get(2).equalsIgnoreCase(subCategoryOrder)){
			pass &= false;
			logger.error("Display Sub-Category Display Order "+rowInfo.get(2)+" not correct.");
		}
		
		return pass;
	}
}
