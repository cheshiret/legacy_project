package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 28, 2012
 */
public class LicMgrSearchRegistrationsPage extends LicMgrCommonTopMenuPage {

	private String prefix = "VehicleRTIRegistrationAndTitleSearchCriteria-\\d+.";
	private static LicMgrSearchRegistrationsPage _instance = null;

	public static LicMgrSearchRegistrationsPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrSearchRegistrationsPage();
		}

		return _instance;
	}

	protected LicMgrSearchRegistrationsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".id", "SearchVehicleRegistrationUIGrid");
	}
	
	public void setSearchType(String searchType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchTypeId", false), searchType, true);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression(prefix+"searchValue", false), searchValue, true);
	}
	
	public void setProductCode(String productCode){
		browser.setTextField(".id", new RegularExpression(prefix+"productCode", false), productCode, true);
	}
	
	public void setStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleInstanceStatus", false), status, true);
	}

	public void setDateType(String dateType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleRegistrationDateType", false), dateType, true);
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", new RegularExpression(prefix+"fromDate_ForDisplay", false), fromDate, true);
	}
	
	public void setToDate(String toDate){
		browser.setTextField(".id", new RegularExpression(prefix+"toDate_ForDisplay", false), toDate, true);
	}
	
	public void setVehicleIDMI(String vehicleIDMI){
		browser.setTextField(".id", new RegularExpression("vehicleNumber", false), vehicleIDMI, true);
	}
	
	public void setHullID(String hullID){
		browser.setTextField(".id", new RegularExpression(prefix+"hullId", false), hullID, true);
	}
	
	public void setVehicleType(String vehicleType){
		if(!"".equals(vehicleType)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleTypeView", false), vehicleType, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleTypeView", false), 0, true);
		}
	}
	
	public void setVehicleSearchType(String vehicleSearchType){
		if(!"".equals(vehicleSearchType)){
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+.selectedAttrDef", false), vehicleSearchType, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+.selectedAttrDef", false), 0, true);
		}
	}

	public void setVehicleSearchValue(String vehicleSearchValue){
		if(!"".equals(vehicleSearchValue)){
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[\\d{4}\\]", false), vehicleSearchValue, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[\\d{4}\\]", false), 0, true);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void setSearchCriteria(RegistrationInfo searchInfo){
		if(null != searchInfo.searchType){
			this.setSearchType(searchInfo.searchType);
		}
		if(null != searchInfo.searchValue){
			this.setSearchValue(searchInfo.searchValue);
		}
		if(null != searchInfo.searchStatus){
			this.setStatus(searchInfo.searchStatus);
		}
		if(null != searchInfo.searchProductCd){
			this.setProductCode(searchInfo.searchProductCd);
		}
		if(null != searchInfo.searchDateType){
			this.setDateType(searchInfo.searchDateType);
		}
		if(null != searchInfo.searchFromDate){
			this.setFromDate(searchInfo.searchFromDate);
		}
		if(null != searchInfo.searchToDate){
			this.setToDate(searchInfo.searchToDate);
		}

		if(null != searchInfo.searchVehicleIDMI){
			this.setVehicleIDMI(searchInfo.searchVehicleIDMI);
		}
		if(null != searchInfo.searchHullID){
			this.setHullID(searchInfo.searchHullID);
		}
		if(null != searchInfo.searchVehicleType){
			this.setVehicleType(searchInfo.searchVehicleType);
		}
		if(null != searchInfo.searchVehicleSearchType){
			this.setVehicleSearchType(searchInfo.searchVehicleSearchType);
			ajax.waitLoading();
		}
		if((null != searchInfo.searchVehicleSearchType && !"".equals(searchInfo.searchVehicleSearchType))
				&& null != searchInfo.searchVehicleSearchValue){
			this.setVehicleSearchValue(searchInfo.searchVehicleSearchValue);
		}
	}
	
	public String searchRegistration(RegistrationInfo searchInfo){
		logger.info("Search registration...");
		this.setSearchCriteria(searchInfo);
		this.clickSearch();
		ajax.waitLoading();
		return this.getMessage();
	}
	
	public String getMessage() {
		String message = "";
		logger.info("Get message in search registrations page.");
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length > 0) {
			message = objs[0].getProperty(".text").toString();
		}
		Browser.unregister(objs);
		return message;
	}

	public IHtmlTable getRegisTable(){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "SearchVehicleRegistrationUIGrid_LIST");
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find registration list table....");
		}
		return (IHtmlTable)objs[0];
	}
	
	public List<RegistrationInfo> getSearchList(){
		logger.info("Get search result...");
		IHtmlTable table = this.getRegisTable();
		RegistrationInfo searchResult = new RegistrationInfo();
		List<RegistrationInfo> resultList = new ArrayList<RegistrationInfo>();
		if(table.rowCount()>0){
			for(int i=1; i<table.rowCount(); i++){
				searchResult.id = table.getCellValue(i, 0);
				searchResult.status = table.getCellValue(i, 1);
				searchResult.customer = table.getCellValue(i, 2);
				searchResult.vehicle = table.getCellValue(i, 3);
				searchResult.product = table.getCellValue(i, 4);
				searchResult.validFromDate = table.getCellValue(i, 5);
				searchResult.validToDate = table.getCellValue(i, 6);
				searchResult.numOfDuplicates = table.getCellValue(i, 7);
				resultList.add(searchResult);
			}
		}
		return resultList;
	}
	
	public void clickRegisID(String regisID){
		browser.clickGuiObject(".class", "Html.A", ".text", regisID);
	}
	
	public List<String> getColumnByName(String columnName){
		List<String> valueList = new ArrayList<String>();
		IHtmlTable table = this.getRegisTable();
		int col = table.findColumn(0, columnName);
		valueList = table.getColumnValues(col);
		valueList.remove(0);
		return valueList;
	}
	
	
	/**
	 * Get First Registration Instance's ID
	 * @return
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public String getFirstRegistrationID() {
		return this.getFirstRegistrationInfo("Registration ID");
	}
	
	/**
	 * Get First Registration Instance's Valid From Date
	 * @return
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public String getFirstRegistrationValidFromDate() {
		return this.getFirstRegistrationInfo("Valid From");
	}
	
	/**
	 * Get First Registration Instance's Valid To Date
	 * @return
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public String getFirstRegistrationValidToDate() {
		return this.getFirstRegistrationInfo("Valid To");
	}
	
	// Get first registration info per column name
	private String getFirstRegistrationInfo(String colNm) {
		List<String> validFromDateList = this.getColumnByName(colNm);
		return validFromDateList.get(0);
	}
}
