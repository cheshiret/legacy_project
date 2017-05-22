package com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrProductConfigurationPage extends LicMgrTopMenuPage {
	private static LicMgrProductConfigurationPage _instance = null;

	protected LicMgrProductConfigurationPage() {

	}

	public static LicMgrProductConfigurationPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrProductConfigurationPage();
		}

		return _instance;
	}

	protected Property[] speciesTable(){
		return Property.concatPropertyArray(table(), ".id", "species");
	}
	
	protected Property[] harvestDesignation(String harvestDesignation){
		return Property.concatPropertyArray(a(), ".text", harvestDesignation);
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Product Configuration", ".className", "selected");
	}

	public void clickSystemConfiguration() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"System Configuration");
	}

	public void clickSpeciesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Species");
	}

	public void clickSeasonsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Seasons");
	}

	public void clickProdQuestionsTab() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Product Questions");
	}

	public void clickDocTemplatesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Document Templates");
	}

	public void clickFiscalYearTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fiscal Year");
	}

	public void clickDisplayCategoriesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Display Categories");
	}

	public void clickDisplaySubCategoriesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Display Sub-Categories");
	}

	public void clickReportCategoriesTab() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Report Categories");
	}
	
	public void clickWeaponsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
						"Weapons");
	}

	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	public void switchToDisplayCategoryTab() {
		this.clickDisplayCategoriesTab();
		ajax.waitLoading();
	}
	
	public void switchToReportCategoryTab(){
		this.clickReportCategoriesTab();
		ajax.waitLoading();
	}

	public void switchToDisplaySubCategoriesTab(){
		this.clickDisplaySubCategoriesTab();
		ajax.waitLoading();
	}
	
	/**
	 * Get table object - this method can be used for all sub page in Product Configuration
	 * @param tableId
	 * @return
	 */
	protected IHtmlTable getTableObject(String tableId) {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.TABLE", ".id", tableId);
		IHtmlTable table = (IHtmlTable) obj[0];
		
		return table;
	}
	
	/**
	 * check whether tableId corresponding table object exists or not
	 * @param identifier
	 * @param tableId
	 * @return
	 */
	protected boolean checkIdentifierExists(String identifier, String tableId) {
		String text = browser.getObjectText(".class", "Html.TABLE", ".id",
				tableId);
		
		logger.debug("text from tableId" + text);
		
		return text.contains(identifier);
	}

	/**
	 * The method used to retrieve specific cell value
	 * 
	 * @param tableId
	 *            ---the table's id you want to parse
	 * @param conditionVal
	 *            ---one cell value which is used to get the target value
	 * @param conditionColName
	 *            ---one colName which  is the conditionVal in
	 * @param expectColName
	 *            ----the column name that  you want to get value
	 * @return return
	 */
	protected String getCellValue(String tableId, String conditionVal,String conditionColName, String expectColName) {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.TABLE", ".id",
				tableId);
		IHtmlTable table = (IHtmlTable) obj[0];
        int identifierCol=(conditionColName==null || conditionColName.trim().length()<1)?0:table.findColumn(0, conditionColName);
		
        int row = table.findRow(identifierCol, conditionVal);
		int col = table.findColumn(0, expectColName);
		String temp = table.getCellValue(row, col);
		Browser.unregister(obj);
		return temp;
	}

	protected boolean isCellExist(String tableId, String conditionVal,String conditionColName) {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.TABLE", ".id", tableId);
		IHtmlTable table = (IHtmlTable) obj[0];
        int identifierCol=(conditionColName==null || conditionColName.trim().length()<1)?0:table.findColumn(0, conditionColName);
		
        int row = table.findRow(identifierCol, conditionVal);
        boolean result = row==-1;
		Browser.unregister(obj);
		return result;
	}
	
	/**
	 * The method used to get specific row values
	 * 
	 * @param tableId
	 *            ---the table you want to parse
	 * @param colName
	 *            ---the column name for identifier
	 * @param identifier
	 *            ----one cell value in your expected row
	 * @return
	 */
	public List<String> getRowInfo(String tableId, String identifier,
			String colName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", tableId);
		IHtmlTable table = (IHtmlTable) objs[0];

		int colNum = table.findColumn(0, colName);
		if(colNum < 0){
			throw new ObjectNotFoundException("The colum of " + colName + "is not found in table: " + tableId);
		}
		int row = table.findRow(colNum, identifier);
		if(row < 0){
			throw new ObjectNotFoundException("The row which matches " + identifier + "is not found under colum: " + colName);
		}
		List<String> values = table.getRowValues(row);
		Browser.unregister(objs);

		return values;
	}

	protected List<String> getFirstRowInfo(String tableId) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", tableId);
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getRowValues(1);
		Browser.unregister(objs);

		return values;
	}

	/**
	 * This method used to get specific column content
	 * 
	 * @param tableId
	 * @param colName
	 * @return
	 */
	public List<String> getColumnValue(String tableId, String colName) {
		IHtmlTable table = this.getTableObject(tableId);

		int col = table.findColumn(0, colName);
		List<String> value = table.getColumnValues(col);

		return value;
	}

	public void clickLink(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public String getWarningMsg(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("warning mesage is not found.....");
	    }
	    String msg=objs[0].getProperty(".text").trim();
	    Browser.unregister(objs);
	    return msg;
	}
	
	public IHtmlObject[] getSpeciesTable(){
		IHtmlObject[] objs = browser.getTableTestObject(speciesTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find species table.");
		}  
		return objs;
	}
	
	public List<String> getAllSpecies(){
		IHtmlObject[] objs = getSpeciesTable();
		ITable table = (ITable)objs[0];
		int colNum = table.findColumn(0, "Description");
		List<String> species = table.getColumnValues(colNum);
		Browser.unregister(objs);
		return species;
	}
	
	public String getHarvestDesignation(String speciesCode, String speciesDesc){
		IHtmlObject[] objs = getSpeciesTable();
		String harvestDesignation = StringUtil.EMPTY;
		
		ITable table = (ITable)objs[0];
		for(int i=0; i<table.rowCount(); i++){
			if(table.getCellValue(i, 1).equals(speciesCode) && table.getCellValue(i, 2).equals(speciesDesc)){
				harvestDesignation = table.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		return harvestDesignation;
	} 
	
	public String clickHarvestDesignation(String speciesCode, String speciesDesc){
		String harvestDesignation = getHarvestDesignation(speciesCode, speciesDesc);
		if(StringUtil.notEmpty(harvestDesignation)){
			browser.clickGuiObject(harvestDesignation(harvestDesignation));
		}else throw new ErrorOnPageException("Can't find harvestDesignation when species code:"+speciesCode+" and description:"+speciesDesc);
		
		return harvestDesignation;
	}
}
