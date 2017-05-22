package com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StcTransRecLocationsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: stock transfer receiveing locations page.
 * @Preconditions: Assign "SearchWarehouseStockTransferReceivingLocations" feature.
 * @SPEC: 
 * @Task#: TC:032104
 * 
 * @author Jasmine
 * @Date  Aug 25, 2012
 */

public class InvMgrStcTransRecLocationsPage extends InventoryManagerPage{

	private static InvMgrStcTransRecLocationsPage instance = null;
    
    private InvMgrStcTransRecLocationsPage(){};
    
    public static InvMgrStcTransRecLocationsPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrStcTransRecLocationsPage();
    	}
    	return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("WarehouseStockTransferRecvLocsSearchCriteria-\\d+\\.assigned",false));
	}
	/**
	 * select assignment status
	 * @param assignStatus
	 */
	public void selectAssignStatus(String assignStatus){		
		RegularExpression reg = new RegularExpression(	"WarehouseStockTransferRecvLocsSearchCriteria-\\d+\\.assigned",false);
		browser	.selectDropdownList(".id",	reg, assignStatus);
		
		//To resolve can't select "".
		if (assignStatus.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * select location status.
	 * @param locType
	 */
	public void selectLocationType(String locType){
		RegularExpression reg = new RegularExpression("WarehouseStockTransferRecvLocsSearchCriteria-\\d+\\.locationType",false);
		browser	.selectDropdownList(".id",	reg, locType);
		
		//To resolve can't select "".
		if (locType.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * set region.
	 * @param region
	 */
	public void setRegion(String region){
		browser.setTextField(".id", new RegularExpression("WarehouseStockTransferRecvLocsSearchCriteria-\\d+\\.regionName",false), region);
	}
	/**
	 * select agency.
	 * @param agency
	 */
	public void selectAgency(String agency){
		RegularExpression reg = new RegularExpression("WarehouseStockTransferRecvLocsSearchCriteria-\\d+\\.agencyLocation",false);
		browser	.selectDropdownList(".id",	reg, agency);
		//To resolve can't select "".
		if (agency.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}
	/**
	 * click assign select locations.
	 */
	public void clickAssignSelectLocations(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected Locations");
		
	}
	/**
	 * click unassign selected locations.
	 */
	public void clickUnassignSelectedLocations(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected Locations");
	}
	/**
	 * click go button.
	 */
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
	}
	/**
	 * clear up search criteria.
	 */
	public void clearUpSearchCriteria(){
		this.selectAssignStatus("");
		this.selectLocationType("");
		this.setRegion("");
		this.selectAgency("");
	}
	/**
	 * search stock transfer receiving locations.
	 * @param loctionInfo - the l
	 */
	public void searchStcTransRecLocations(StcTransRecLocationsInfo loctionInfo){
		if(null != loctionInfo.getAssignStatus()){
			this.selectAssignStatus(loctionInfo.getAssignStatus());
		}
		if(null != loctionInfo.getLocaionType()){
			this.selectLocationType(loctionInfo.getLocaionType());
		}
		if(StringUtil.notEmpty(loctionInfo.getRegion())){
			this.setRegion(loctionInfo.getRegion());
		}
		if(null != loctionInfo.getAgency()){
			this.selectAgency(loctionInfo.getAgency());
		}
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text","Next");
 		boolean toReturn = false;
 		if(objs.length>0){
 			String isDisable = objs[0].getProperty(".isDisabled");
 	 		if (isDisable.equals("false")) {
 	 			toReturn = true;
 	 			objs[0].click();
 	 		}
 	 		Browser.unregister(objs);
 	 		ajax.waitLoading();
 		}
 		return toReturn;
 	}
 	
 	private IHtmlObject[] getStcTransRecLocaionTable(){
 		IHtmlObject[] objs = browser.getTableTestObject(".id", "StcXferRecvLocations_LIST");
			if(objs.length<1){
				throw new ErrorOnDataException(
			"Can't find the specific locations");
			}
	    return objs;
 	}
	/**
	 * get warehouse list info.
	 * @return
	 */
	public List<List<String>> getStcTransRecLocaionsListInfo(){
		List<List<String>> locationsListInfo = new ArrayList<List<String>>();
 		List<String> locationsRowInfo = new ArrayList<String>();
 		do{
 			IHtmlObject[] objs = this.getStcTransRecLocaionTable();
 			IHtmlTable table =(IHtmlTable)objs[0];
 			if(table.rowCount()>1){
 				for(int i = 1;i<table.rowCount();i++){
 					locationsRowInfo = table.getRowValues(i);
 					locationsListInfo.add(locationsRowInfo);
 				}
 			}else{
 				throw new ErrorOnDataException(
				"No locations info is retrived!");
 			}
 		   Browser.unregister(objs);
 		}while(this.clickNext());
 		return locationsListInfo;
	}
	/**
 	 * Get the column index.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs=this.getStcTransRecLocaionTable();
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
 	public void verifyStcLocatinSearchResult(List<List<String>> locationsListInfo,String searchCriteria,String colName){
 		List<String> locationsRowInfo = new ArrayList<String>();
 		if(searchCriteria.length()>0){
 			int colIndex = this.getColIndex(colName);
 			if(locationsListInfo.size()>=1){
 				for(int i=0;i<locationsListInfo.size();i++){
 					locationsRowInfo = locationsListInfo.get(i);
 					String cellText = locationsRowInfo.get(colIndex).trim();
 					String expectedValue = searchCriteria.trim();
 					if(!cellText.equals(expectedValue)){
 	 					throw new ErrorOnDataException(searchCriteria
 								+ cellText+ " doesn't match "+expectedValue+"privilege info");
 					}
 				}
 			}
 		}
 	}
 	
 	/**
 	 * verify stock transfer receiving locations.
 	 * @param locaionInfo
 	 */
 	public void verifyStcLocatinSearchResult(StcTransRecLocationsInfo locaionInfo){
 		List<List<String>> locationList = this.getStcTransRecLocaionsListInfo();
 		if(StringUtil.notEmpty(locaionInfo.getAssignStatus())){
 			this.verifyStcLocatinSearchResult(locationList, locaionInfo.getAssignStatusMark(), "Assigned");
 		}else if(StringUtil.notEmpty(locaionInfo.getLocaionType())){
 			this.verifyStcLocatinSearchResult(locationList, locaionInfo.getRegion(), "Region");
 		}else if(StringUtil.notEmpty(locaionInfo.getAgency())){
 			this.verifyStcLocatinSearchResult(locationList, locaionInfo.getAgency(), "Agency");
 		}
 	}
 	/**
 	 * select assignment check box.
 	 * @param index
 	 */
 	public void selectAssignmentCheckBox(int index){
 		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
 	}
 	/**
 	 * get index by location name.
 	 * @param name
 	 * @return
 	 */
 	public int getIndexByLoationName(String name){
 		IHtmlObject[] objs = this.getStcTransRecLocaionTable();
 		IHtmlTable table =(IHtmlTable)objs[0];
 		int index = table.findRow(1, name);
 		return index;
 	}
 	/**
 	 * assign stock transfer receiving locations.
 	 */
 	public void selectStcLocation(String name){
 		int index = this.getIndexByLoationName(name);
 		if(index>=0){
 			this.selectAssignmentCheckBox(index-1);
 		}else{
 			throw new ErrorOnPageException("Can't find the "+name+" locaion");
 		}
 		
 	}
 	/**
 	 * assign stock transfer receiving locations.
 	 * @param name
 	 */
 	public void assingStcLocation(String name){
 		this.selectStcLocation(name);
 		this.clickAssignSelectLocations();
 		ajax.waitLoading();
 		this.waitLoading();
 	}
 	/**
 	 * unassign stock transfer receiving locations.
 	 * @param name
 	 */
 	public void unassignStcLocation(String name){
 		this.selectStcLocation(name);
 		this.clickUnassignSelectedLocations();
 		ajax.waitLoading();
 		this.waitLoading();
 	}
 	/**
 	 * get assignment 
 	 * @param name
 	 * @return
 	 */
 	public String getAssignmentStatusByLocaionName(String name){
 		IHtmlObject[] objs = this.getStcTransRecLocaionTable();
 		IHtmlTable table =(IHtmlTable)objs[0];
 		int index = table.findRow(1, name);
 		String assigment ="";
 		if(index>=0){
 			assigment = table.getCellValue(index, 2);
 		}else{
 			throw new ErrorOnPageException("Can't find the "+name+" locaion");
 		}
 		return assigment;
 		
 	}
 	/**
 	 * verify assignment status.
 	 * @param locationName
 	 * @param assignStatues
 	 */
 	public void verifyAssignmentStatus(String locationName,String assignStatues){
 		if(!MiscFunctions.compareResult("assignment status", assignStatues, this.getAssignmentStatusByLocaionName(locationName))){
 			throw new ErrorOnPageException("assignment status",assignStatues, this.getAssignmentStatusByLocaionName(locationName));
 		}else{
 			logger.info("location assignment status correct");
 		}
 	}
 	/**
 	 * get stock transfer Receiving locations name.
 	 * @return
 	 */
 	public List<String> getStcLocationNameInfo(){
 		List<String> locationNameList= new ArrayList<String>();
 		IHtmlObject[] objs = this.getStcTransRecLocaionTable();
 		IHtmlTable table =(IHtmlTable)objs[0];
 		for(int i=1;i<table.rowCount();i++){
 			locationNameList.add(table.getCellValue(i, 1));
 		}
 		return locationNameList;
 	}
}
