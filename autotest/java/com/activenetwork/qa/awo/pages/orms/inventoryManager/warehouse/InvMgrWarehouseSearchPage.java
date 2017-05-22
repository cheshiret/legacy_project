package com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**  
 * @Description:  warehouse search page.
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 27, 2012   
 */
public class InvMgrWarehouseSearchPage extends InvMgrCommonTopMenuPage{
	
	public static InvMgrWarehouseSearchPage instance = null;
	
	private InvMgrWarehouseSearchPage(){};
	
	public static InvMgrWarehouseSearchPage getInstance(){
		if(null == instance){
			instance = new InvMgrWarehouseSearchPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("WarehouseSearchCriteria-\\d+\\.searchBy",false));
	}
	/**
	 * click add new button.
	 */
	public void clickAddNewButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	public void selectSearchType(String searchType){
		if(null !=searchType && searchType.trim().length()>1){
			browser.selectDropdownList(".id", new RegularExpression("WarehouseSearchCriteria-\\d+\\.searchBy",false), searchType);
		}else{
			browser.selectDropdownList(".id", new RegularExpression("WarehouseSearchCriteria-\\d+\\.searchBy",false), 0);
		}

	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression("WarehouseSearchCriteria-\\d+\\.searchByValue",false), searchValue);
	}
	
	/**
	 * Search warehouse name identifier by searchType/searchValue
	 * @param searchType
	 * @param searchValue
	 */
	public void searchWarehouse(String searchType, String searchValue) {
		this.setSearchCriteria(searchType, searchValue);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setSearchCriteria(String searchType,String searchValue){
		this.selectSearchType(searchType);
		if(null == searchValue){
			searchValue = "";
		}
		this.setSearchValue(searchValue);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$",false));
	}
	
	
	public void clickWarehouseID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public String getWarehouseID(String warehouseName){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "WarehouseSearchResultsGrid_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found warehouse table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, warehouseName);
		String value = table.getCellValue(row, 0);
		Browser.unregister(objs);
		return value;
	}
	/**
	 * click ware house id.
	 * @param id
	 */
	public void clickWareHouseId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	/**
	 * search warehouse by name.
	 * @param name
	 */
	public void searchWarehouseByName(String name){
		this.setSearchCriteria("Warehouse Name", name);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	/*
	 * search ware house by id
	 */
	public void searchWarehouseById(String warehouseId){
		this.setSearchCriteria("Warehouse ID", warehouseId);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * get warehouse list info.
	 * @return
	 */
	public List<List<String>> getWarehouseListInfo(){
		List<List<String>> wHouseListInfo = new ArrayList<List<String>>();
 		List<String> wHouseRowInfo = new ArrayList<String>();
 		do{
 			IHtmlObject[] objs = browser.getTableTestObject(".id", "WarehouseSearchResultsGrid_LIST");
 			if(objs.length<1){
 				throw new ErrorOnDataException(
				"Can't find the specific warehouse");
 			}
 			IHtmlTable table =(IHtmlTable)objs[0];
 			if(table.rowCount()>1){
 				for(int i = 1;i<table.rowCount();i++){
 					wHouseRowInfo = table.getRowValues(i);
 					wHouseListInfo.add(wHouseRowInfo);
 				}
 			}else{
 				throw new ErrorOnDataException(
				"No warehouse info is retrived!");
 			}
 		   Browser.unregister(objs);
 		}while(this.clickNext());
 		return wHouseListInfo;
	}
	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text",
 				new RegularExpression("Next", false));
 		
 		String isDisable = objs[0].getProperty(".isDisabled");

 		boolean toReturn = false;
 		if (isDisable.equals("true")) {
 			toReturn = true;
 			objs[0].click();
 		}
 		Browser.unregister(objs);
 		ajax.waitLoading();
 		return toReturn;
 	}
 	
 	/**
 	 * Get the column index.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
 				".id", "WarehouseSearchResultsGrid_LIST");
 		if (objs.length > 0) {
 			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
 			//System.out.println(cusTableGrid.getColumnValues(8));
 			colNum = cusTableGrid.findColumn(0, colName);
 		} else
 			throw new ObjectNotFoundException("Object can't find.");

 		Browser.unregister(objs);
 		return colNum;
 	}
 	/**
 	 * verify the p
 	 * @param wHouseListInfo
 	 * @param searchCriteria
 	 * @param colName
 	 */
 	public void verifyWhouseSearchResult(List<List<String>> wHouseListInfo,String searchCriteria,String colName){
 		List<String> wHouseRowInfo = new ArrayList<String>();
 		if(searchCriteria.length()>0){
 			int colIndex = this.getColIndex(colName);
 			if(wHouseListInfo.size()>=1){
 				for(int i=0;i<wHouseListInfo.size();i++){
 					wHouseRowInfo = wHouseListInfo.get(i);
 					String cellText = wHouseRowInfo.get(colIndex).trim();
 					String expectedValue = searchCriteria.trim();
 					if(!cellText.equals(expectedValue)){
 	 					throw new ErrorOnDataException(searchCriteria
 								+ wHouseRowInfo.get(colIndex)
 								+ " doesn't match "+expectedValue+"privilege info");
 					}
 				}
 			}
 		}
 	}
 	/**
 	 * verify warehouse search result.
 	 * @param whouse
 	 */
 	public void verifyWhouseSearchResult(WarehouseInfo whouse){
 		List<List<String>> whouseList = this.getWarehouseListInfo();
 		if(whouse.getwHouseSearchType().equals("Warehouse ID") &&StringUtil.notEmpty(whouse.getId())){
 			this.verifyWhouseSearchResult(whouseList, whouse.getId(), "Warehouse ID");
 		}else if(whouse.getwHouseSearchType().equals("Warehouse Name") && StringUtil.notEmpty(whouse.getName())){
 			this.verifyWhouseSearchResult(whouseList, whouse.getName(), "Warehouse Name");
 		}else if(whouse.getwHouseSearchType().equals("Region Name") && StringUtil.notEmpty(whouse.getRegion())){
 			this.verifyWhouseSearchResult(whouseList,whouse.getRegion(),"Parent Location");
 		}else if(whouse.getwHouseSearchType().equals("Agency Name") && StringUtil.notEmpty(whouse.getAgency())){
 			this.verifyWhouseSearchResult(whouseList,whouse.getAgency(),"Agency");
 		}
 	}
 	/**
 	 * verify warehouse search type list.
 	 * @return
 	 */
 	public List<String> getSearchTypeList(){
 		return browser.getDropdownElements(".id", new RegularExpression("WarehouseSearchCriteria-\\d+\\.searchBy",false));
 	}
 	/**
 	 * check add new button disable.
 	 * @return
 	 */
    public boolean checkAddNewButtonDisable(){
    	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Add New");
    	if(objs.length<1){
    		throw new ErrorOnPageException("No secific element was found");
    	}
    	if("true".equals(objs[0].getProperty("isDisabled"))){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    
    public List<WarehouseInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<WarehouseInfo> records = new ArrayList<WarehouseInfo>();
		int rows;
		int columns;
		WarehouseInfo info;
		
		
		do{
			objs = browser.getTableTestObject(".id", "WarehouseSearchResultsGrid_LIST");;
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't rule WarehouseInfo table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page InvMgrWarehouseSearchPage, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				info = new WarehouseInfo();
				info.setId(table.getCellValue(i, table.findColumn(0, "Warehouse ID")));
				info.setName(table.getCellValue(i, table.findColumn(0, "Warehouse Name")));
				info.setDesctiption(table.getCellValue(i, table.findColumn(0, "Description")));
				info.setRegion(table.getCellValue(i, table.findColumn(0, "Parent Location")));
				info.setAgency(table.getCellValue(i, table.findColumn(0, "Agency")));
				
				records.add(info);			
			}

		}while(gotoNext());
		
		Browser.unregister(objs);
		
		return records;
	}
	
	
	/**
	 * Check whether gotonext button exist, if exit,click it.
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		this.waitLoading();
		return toReturn;
	}
}
