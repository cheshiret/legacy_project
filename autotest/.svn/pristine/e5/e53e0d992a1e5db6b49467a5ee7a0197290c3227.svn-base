package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InspectionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Inspections search page for vehicle How to go to the page: license manager-->
 * Vehicles ---> Inspections
 * 
 * @author Pchen
 * @date Aug 10, 2012
 */
public class LicMgrVehicleInspectionSearchPage extends LicMgrCommonTopMenuPage {
	private static LicMgrVehicleInspectionSearchPage _instance = null;

	private LicMgrVehicleInspectionSearchPage() {
	}

	public static LicMgrVehicleInspectionSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVehicleInspectionSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		boolean exist =  browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"inspection");
		exist &= browser.checkHtmlObjectExists(".class", "Html.A", ".text","Search");
		return exist;
	}
    /**
     * Select Search Type from drop down list
     * @param type
     */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.searchTypeId", false),
				type);
	}
    /**
     * Set Search value
     * @param value
     */
	public void setSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.searchValue", false),
				value);
	}
    /**
     * Select status from drop down list
     * @param status
     */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.statusId", false),
				status);
	}
    /**
     * Set Product Code
     * @param productCode
     */
	public void setProductCode(String productCode) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.productCode", false),
				productCode);
	}
    /**
     * Set Request From Date
     * @param fromDate
     */
	public void setRequestFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.fromDate_ForDisplay",
				false), fromDate);
	}
    /**
     * Set Request To Date
     * @param toDate
     */
	public void setRequestToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.toDate_ForDisplay",
				false), toDate);
	}
    /**
     * Select Country from drop down list
     * @param countyId
     */
	public void selectCountry(String countyId) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.countyId", false),
				countyId);
	}
    /**
     * Set Assigned Officer Last Name 
     * @param assOfficerLastName
     */
	public void setAssignedOfficerLastName(String assOfficerLastName) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.assOfficerLastName",
				false), assOfficerLastName);
	}
    /**
     * Set Hull ID/Serial #
     * @param hullIdSerialNumber
     */
	public void setHullId(String hullIdSerialNumber) {
		browser.setTextField(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.hullIdSerialNumber",
				false), hullIdSerialNumber);
	}
	/**
	 * Select 'Central' check box
	 */
	public void selectCentral() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^Central",true)); 
		if( centralDiv.length > 0){
			browser.selectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of Central doesn't found on page!");
		}		
	}
	/**
	 * Unselect 'Central' check box
	 */
	public void unSelectCentral() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^Central",true)); 
		if( centralDiv.length > 0){
			browser.unSelectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of Central doesn't found on page!");
		}	
	}
	/**
	 * Select 'North' check box
	 */
	public void selectNorth() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^North",true)); 
		if( centralDiv.length > 0){
			browser.selectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of North doesn't found on page!");
		}		
	}
	/**
	 * Unselect 'North' check box
	 */
	public void unSelectNorth() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^North",true)); 
		if( centralDiv.length > 0){
			browser.unSelectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of North doesn't found on page!");
		}		
	}
    /**
     * Select 'South' check box
     */
	public void selectSouth() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^South",true)); 
		if( centralDiv.length > 0){
			browser.selectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of South doesn't found on page!");
		}		
	}
	/**
	 * Unselect 'South' check box
	 */
	public void unSelectSouth() {
		IHtmlObject[] centralDiv = browser.getHtmlObject(
				".class", "Html.DIV", ".text", new RegularExpression("^South",true)); 
		if( centralDiv.length > 0){
			browser.unSelectCheckBox(".id", new RegularExpression(
					"VehicleInspectionSearchCriteria-\\d+\\.districts_\\[\\d+\\]",
					false), 0, centralDiv[0]);
		}else{
			throw new ErrorOnPageException("Checkbox of South doesn't found on page!");
		}		
	}
    /**
     * Select 'Exact Match' check box
     */
	public void selectExactMatch() {
		browser.selectCheckBox(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.exactMatch", false));
	}
	/**
	 * Unselect 'Exact Match' check box
	 */
	public void unselectExactMatch() {
		browser.unSelectCheckBox(".id", new RegularExpression(
				"VehicleInspectionSearchCriteria-\\d+\\.exactMatch", false));
	}
	/**
	 * Click Search button
	 */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
    /**
     * Click Print Inspection Form button
     */
	public void clickPrintInspectionForm() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Print Inspection Form");
	}
    /**
     * Click inspectionId
     * @param inspectionId
     */
	public void clickInspectionId(String inspectionId) {
		browser.clickGuiObject(".class", "Html.A", ".text", inspectionId);
	}
	/**
	 * Select all inspections in current page
	 */
	public void selectAllInspections(){
		browser.selectCheckBox(".name", "all_slct");
	}

	/**
	 * search vehicle inspection
	 * @param inspection
	 */
	public void searchInspection(InspectionInfo inspection) {
		if (StringUtil.notEmpty(inspection.searchType)) {
			selectSearchType(inspection.searchType);		
		    if (inspection.searchType.equals("Inspection ID")) {
			    setSearchValue(inspection.inspectionId);
	     	}else if(inspection.searchType.equals("Order #")){
	     		setSearchValue(inspection.orderNum);
	     	}else{
	     		setSearchValue(inspection.receiptNum);
	     	}
		}
		if (StringUtil.notEmpty(inspection.status)) {
			selectStatus(inspection.status);
		}
		if (StringUtil.notEmpty(inspection.productCode)) {
			setProductCode(inspection.productCode);
		}
		if (StringUtil.notEmpty(inspection.requestFromDate)) {
			setRequestFromDate(inspection.requestFromDate);
		}
		if (StringUtil.notEmpty(inspection.requestToDate)) {
			setRequestToDate(inspection.requestToDate);
		}
		if (StringUtil.notEmpty(inspection.requestFromDate)) {
			setRequestFromDate(inspection.requestFromDate);
		}
		if (StringUtil.notEmpty(inspection.getCountry())) {
			selectCountry(inspection.getCountry());
		}
		if (StringUtil.notEmpty(inspection.assOfficerLastName)) {
			setAssignedOfficerLastName(inspection.assOfficerLastName);
		}
		if (StringUtil.notEmpty(inspection.hullIdSerialNumber)) {
			setHullId(inspection.hullIdSerialNumber);
		}		
		if (inspection.inCentral) {
			selectCentral();
		}else{
			unSelectCentral();
		}
		if (inspection.inNorth) {
			selectNorth();
		}else{
			unSelectNorth();
		}
		if (inspection.inSouth) {
			selectSouth();
		}else{
			unSelectSouth();
		}
		clickSearch();
		ajax.waitLoading();
		this.waitLoading();
		}	
	
	public void printFormForInspectionInCurrentPage(){
		this.selectAllInspections();
		this.clickPrintInspectionForm();
	}
	
	/**
	 * Click the inspection id link which equal to the given id
	 * @param inspectionId
	 */
	public void clickInspectionWithId(String inspectionId){
		browser.clickGuiObject(".class", "Html.A", ".text",inspectionId);
	}
	
	/**
	 * Search inspection according to the id
	 * @param inspectionId
	 */
	public void searchInspectionById(String inspectionId) {
		setSearchValue(inspectionId);
		clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
}
