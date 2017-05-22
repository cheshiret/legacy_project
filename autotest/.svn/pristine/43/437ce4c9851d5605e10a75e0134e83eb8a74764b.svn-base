package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrProductConfigurationDocumentTemplateDetailPage extends LicMgrTopMenuPage{
	private static LicMgrProductConfigurationDocumentTemplateDetailPage _instance = null;
	
	protected LicMgrProductConfigurationDocumentTemplateDetailPage(){
		
	}
	
	public static LicMgrProductConfigurationDocumentTemplateDetailPage getInstance(){
		if(_instance ==null){
			_instance = new LicMgrProductConfigurationDocumentTemplateDetailPage();
		}
		
		return _instance;
	}
	
	public boolean Exists(){
		/*return super.exists() 
		       && browser.checkHtmlObjectExists(".class", "Html.TABLE",".id", "profileList_LIST")*/
		      return browser.checkHtmlObjectExists(".class", "Html.A",".text", "Document Template Assignments");
	}
	
	/**
	 * Get column index identified by column name
	 * @param columnName
	 * @return
	 */
	public int getColumnIndexByName(String columnName) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "profileList_LIST");
		
		if(objs.length < 1) {
			throw new ErrorOnPageException("Can;t find Document Template Assignment table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int columnIndex = table.findColumn(0, columnName);
		
		Browser.unregister(objs);
		return columnIndex;
	}
	
	/**
	 * This method is used to compare document assignment info
	 * @param document
	 * @return
	 */
	public boolean compareTemplateAssignmentsInfo(LicMgrDocumentInfo document){
		boolean result = true;
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		int row = -1;
		List<String> value = new ArrayList<String>();
		PagingComponent turningPage = new PagingComponent();
		do {
			this.waitLoading();
			objs = browser.getTableTestObject(".id", "profileList_LIST");
			
			table = (IHtmlTable)objs[0];
			row = table.findRow(0, document.id);
		} while(row == -1 && turningPage.clickNext() );
		
		if(row == -1) {
			throw new ErrorOnPageException("Can't find Document record - " + document.id);
		}
		value = table.getRowValues(row);
		
		if(!value.get(table.findColumn(0, "Product Fulfillment Document ID")).equals(document.id)){
			result &= false;
		}
		
		if(!value.get(table.findColumn(0, "Product Code")).equals(document.prodCode)){
			result &= false;
			logger.error("Document assginment info about product code is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Product Name")).equals(document.prodName)){
			result &= false;
			logger.error("Document assginment info about product name is not correct. The expected is: " + document.prodName + ", but actual is " + value.get(table.findColumn(0, "Product Name")));
		}
		
		if(!value.get(table.findColumn(0, "Equipment Type")).equals(document.equipType)){
			result &= false;
			logger.error("Document assginment info about equipment type is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "License Year From")).equals(document.licYearFrom)){
			result &= false;
			logger.error("Document assginment info about license from year is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "License Year To")).equals(document.licYearTo)){
			result &= false;
			logger.error("Document assginment info about license to year is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Product Fulfillment Document Status")).equals(document.status)){
			result &= false;
			logger.error("Document assginment info about status is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Print Order")).equals(document.printOrd)){
			result &= false;
			logger.error("Document assginment info about print order is not correct.");
		}
		
		if(DateFunctions.compareDates(value.get(table.findColumn(0, "Effective From Date")), document.effectiveFromDate) != 0){
			result &= false;
			logger.error("Document assginment info about effective from date is not correct.");
		}
		
		if(DateFunctions.compareDates(value.get(table.findColumn(0, "Effective To Date")), document.effectiveToDate) != 0) {
			result &= false;
			logger.error("Document assginment info about effective to date is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Purchase Type")).equals(document.purchaseType)){
			result &= false;
			logger.error("Document assginment info about purchase type is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Fulfillment Method")).equals(document.fulfillMethod)){
			result &= false;
			logger.error("Document assginment info about fulfillment method is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Species")).trim().equals(document.species)){
			result &= false;
			logger.error("Document assginment info about species is not correct.");
		}
		
		int column = table.findColumn(0, "Season");
		if(column < 0){
			column = table.findColumn(0, "Hunting Season");
		}
		if(!value.get(column).trim().equals(document.huntSeason)){
			result &= false;
			logger.error("Document assginment info about hunting season is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Print From Date")).trim().equals(document.printFromDate)){
			result &= false;
			logger.error("Document assginment info about print from date is not correct.");
		}
		
		if(!value.get(table.findColumn(0, "Print To Date")).trim().equals(document.printToDate)){
			result &= false;
			logger.error("Document assginment info about print to date is not correct.");
		}
		turningPage.clickFirst();
		Browser.unregister(objs);
		return result;		
	}
	
	/**
	 * Verify document assignments display sorted order
	 * @param ids
	 * @return
	 */
	public boolean verifyDocumentAssignmentsDisplaySort(List<String> ids) {
		if(this.checkAssignmentsTabExist()){
			return this.verifyTableRecordsDisplaySort(".id", "profileList_LIST", ids);
		}else{
			return false;
		}
	}

	private boolean checkAssignmentsTabExist() {
	
		return browser.checkHtmlObjectExists(".class", "Html.A",".text", "Document Template Assignments");
	}
	
	public void unselectShowCurrentRecordsOnly(){
		browser.unSelectCheckBox(".id", new RegularExpression("DocumentTemplateAssignmentSearchCriteria-\\d+\\.currentRecordOnly",false));
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("DocumentTemplateAssignmentSearchCriteria-\\d+\\.status",false), status);
	}
	
	public void selectEqument(String eque){
		browser.selectDropdownList(".id", new RegularExpression("DocumentTemplateAssignmentSearchCriteria-\\d+\\.equipmentTypeId",false), eque);
	}
	
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void searchAssigmentDoc(String status,String eque){
		this.unselectShowCurrentRecordsOnly();
		ajax.waitLoading();
		this.selectStatus(status);
		this.selectEqument(eque);
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	

}
