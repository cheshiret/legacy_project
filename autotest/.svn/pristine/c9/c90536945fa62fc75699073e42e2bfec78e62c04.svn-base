package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPrintDocumentPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class LicMgrVehiclePrintDocumentsPage extends LicMgrEditVehicleDetailsPage implements ILicMgrProductPrintDocumentPage  {

	private static LicMgrVehiclePrintDocumentsPage _instance = null;
	
	protected LicMgrVehiclePrintDocumentsPage() {}
	
	public static LicMgrVehiclePrintDocumentsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVehiclePrintDocumentsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Print Document");
	}
	
	public void clickAddPrintDocument() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Print Document");
	}
	
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.showingCurrentOnly", false));
	}
	
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.showingCurrentOnly", false));
	    ajax.waitLoading();
	}
	
	public void selectStatus(String status) {
		if(status.length()>0){
			browser.selectDropdownList(".id", statusDDList, status);	
		}else{
			browser.selectDropdownList(".id",statusDDList, 0);	
		}
	}
	
	public void selectDocumentTemplate(String template) {
		if(template.trim().length()>0)
			browser.selectDropdownList(".id", docTemplateDDList, template);
		else 
			browser.selectDropdownList(".id", docTemplateDDList, 0);
	}
	
	public void selectEquipmentType(String type) {
		if(type.trim().length()>0)
		   browser.selectDropdownList(".id",equipTypeDDList, type);
		else
			 browser.selectDropdownList(".id",equipTypeDDList, 0);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
		ajax.waitLoading();
	}
	
	public void clickPrintDocumentID(String id){
		browser.clickGuiObject(".class", "Html.A",".text",id,true);
	}
	
	public List<String> getDocumentColumnNames(){
		IHtmlObject[] objs=this.getDocumentTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		List<String> list=table.getRowValues(0);
		Browser.unregister(objs);
		return list;
	}
	
	public List<String> getColumnValues(String columnName){
		IHtmlObject[] objs=this.getDocumentTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int column=table.findColumn(0, columnName);
		List<String> list=table.getColumnValues(column);
		list.remove(0);
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * Get document assignment info(document assignment ID, equipment type, purchase type)
	 * @param document
	 * @return
	 *       document assignment ID, equipment type, purchase type
	 */
	public List<String[]> getDocumentAssignmentInfo(LicMgrDocumentInfo document) {
		List<String[]> equipTypeAndpurchaseTypeAndIDs = new ArrayList<String []>();
		
		//equipType initial info, purchaseType initial info
		if(document.equipType.length()>0 && document.purchaseType.length()>0) {
			equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType,});
		}
		
		//equipType initial info, purchaseTypes initial info
		if(document.equipType.length()>0  && null!=document.purchaseTypes && document.purchaseTypes.length>0){
			for(int i=0;i<document.purchaseTypes.length;i++){
				document.purchaseType = document.purchaseTypes[i];
				equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType,});
			}
		}
			
		//equipTypes initial info, purchaseType initial info
		if(null != document.equipTypes && document.equipTypes.length > 0 && document.purchaseType.length()>0) {
			for(int i =0;i<document.equipTypes.length;i++){
				document.equipType = document.equipTypes[i];
				equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType});
			}
			
		}
		
		//equipTypes initial info, purchaseTypes initial info
		if(null != document.equipTypes && document.equipTypes.length>0 && null != document.purchaseTypes && document.purchaseTypes.length>0){
			for(int i =0; i<document.equipTypes.length;i++){
				document.equipType = document.equipTypes[i];
				for(int j=0; j<document.purchaseTypes.length; j++){
					document.purchaseType = document.purchaseTypes[j];
					equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType});
				}
			}
		}
		
		if(equipTypeAndpurchaseTypeAndIDs.size() == 0) {
			throw new ItemNotFoundException("Can't find any assignment record.");
		}
		
		return equipTypeAndpurchaseTypeAndIDs;
	}
	
	public IHtmlObject[] getDocumentTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Document Assignment table object.");
		}
		return objs;
	}
	
	/**
	 * Get document assignment ID
	 * @param document
	 * @return
	 */
	public String getPrintDocumentAssignmentID(LicMgrDocumentInfo document){
		IHtmlObject objs[] =  getDocumentTable();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		String id = "";
		for(int i=0;i<table.rowCount();i++){						
			if(table.getCellValue(i, 1).equalsIgnoreCase(document.status.trim().length()<1?"Active":document.status)
					&& table.getCellValue(i, 2).equalsIgnoreCase(document.docTepl) 
					&& table.getCellValue(i, 3).equalsIgnoreCase(document.equipType) 
					&& table.getCellValue(i, 4).equalsIgnoreCase(document.purchaseType) 
					&& table.getCellValue(i, 5).equalsIgnoreCase(document.licYearFrom)  
					&& table.getCellValue(i, 7).equalsIgnoreCase(
							DateFunctions.formatDate(document.effectiveFromDate, "E MMM d yyyy"))){
				id = table.getCellValue(i, 0);
				break;
			}
			
		}
		return id;
		
	}

	@Override
	public List<String> getStatusDDListValues() {
		return browser.getDropdownElements(".id",statusDDList);
	}

	@Override
	public List<String> getDocTemplatesDDLIstValues() {
		return browser.getDropdownElements(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.template",false));
	}

	@Override
	public List<String> getEquipmentTypeDDListValues() {
		return browser.getDropdownElements(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.equipmentType",false));
	}

	@Override
	public boolean isShowCurrentlyOnlySelected() {
		return browser.isCheckBoxSelected(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.showingCurrentOnly",false));
	}
	
	@Override
	public void searchPrintDocument(boolean currentOnly, String status,
			String template, String equipType) {
		if(currentOnly){
			this.checkShowCurrentRecordsOnly();
		}else{
			this.uncheckShowCurrentRecordsOnly();
			this.selectStatus(status);
			this.selectDocumentTemplate(template);
			this.selectEquipmentType(equipType);
		}
		this.clickGo();
	}

	public List<String> getPrintDocumentAssignmentIDFOrCleanUp(
			LicMgrDocumentInfo document) {
		IHtmlObject objs[] = this.getDocumentTable();

		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ids = new ArrayList<String>();
		for(int i=0;i<table.rowCount();i++){						

			if(table.getCellValue(i, 1).equalsIgnoreCase(document.status.trim().length()<1?"Active":document.status)

					&& table.getCellValue(i, 2).equalsIgnoreCase(document.docTepl) 
					&& table.getCellValue(i, 3).equalsIgnoreCase(document.equipType) 
					&& table.getCellValue(i, 4).equalsIgnoreCase(document.purchaseType) 
					&& table.getCellValue(i, 5).equalsIgnoreCase(document.licYearFrom)){
				ids.add(table.getCellValue(i, 0));  
			}
		}

		Browser.unregister(objs);
		return ids;
	}
	
	/**
	 * compare Print document list info.
	 * @param printDoc
	 * @return
	 */
	public boolean comparePrintDocListInfo(LicMgrDocumentInfo printDoc){
		boolean pass = true;
		IHtmlObject objs[] = this.getDocumentTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(0, printDoc.id );
		List<String> rowInfo = table.getRowValues(row);
		Browser.unregister(objs);
		if(!rowInfo.get(0).equals(printDoc.id)){
			pass &= false;
			logger.error("Expected value is "+printDoc.id +" but actual value is" + rowInfo.get(0));
		}
		if(!rowInfo.get(1).equals(printDoc.status)){
			pass &= false;
			logger.error("Expected value is "+printDoc.status +" but actual value is" + rowInfo.get(1));
		}
		if(!rowInfo.get(2).equals(printDoc.docTepl)){
			pass &= false;
			logger.error("Expected value is "+printDoc.docTepl +" but actual value is" + rowInfo.get(2));
		}
		if(!rowInfo.get(3).equals(printDoc.equipType)){
			pass &= false;
			logger.error("Expected value is "+printDoc.equipType +" but actual value is" + rowInfo.get(3));
		}
		if(!rowInfo.get(4).equals(printDoc.purchaseType)){
			pass &= false;
			logger.error("Expected value is "+printDoc.purchaseType +" but actual value is" + rowInfo.get(4));
		}
		if(!rowInfo.get(5).equals(printDoc.licYearFrom)){
			pass &= false;
			logger.error("Expected value is "+printDoc.licYearFrom +" but actual value is" + rowInfo.get(5));
		}
		if(!rowInfo.get(6).equals(printDoc.licYearTo)){
			pass &= false;
			logger.error("Expected value is "+printDoc.licYearTo +" but actual value is" + rowInfo.get(6));
		}
		if(!rowInfo.get(7).equals(printDoc.effectiveFromDate)){
			pass &= false;
			logger.error("Expected value is "+printDoc.effectiveFromDate +" but actual value is" + rowInfo.get(7));
		}
		if(!rowInfo.get(8).equals(printDoc.effectiveToDate)){
			pass &= false;
			logger.error("Expected value is "+printDoc.effectiveToDate +" but actual value is" + rowInfo.get(8));
		}
		if(!rowInfo.get(9).equals(printDoc.printOrd)){
			pass &= false;
			logger.error("Expected value is "+printDoc.printOrd +" but actual value is" + rowInfo.get(9));
		}
		if(!rowInfo.get(10).equals(printDoc.fulfillMethod)){
			pass &= false;
			logger.error("Expected value is "+printDoc.fulfillMethod +" but actual value is" + rowInfo.get(10));
		}
		return pass;
	}
	
	/**
	 * compare print document list info.
	 * @param printDoc
	 * @return
	 */
	public boolean comparePrintDocAllListInfo(LicMgrDocumentInfo printDoc){
		boolean pass = true;
		if(printDoc.purchaseTypes.length>0&&printDoc.equipTypes.length>0){
			for(int i = 0;i<printDoc.purchaseTypes.length;i++){
				for(int j =0;j<printDoc.equipTypes.length;j++){
					printDoc.purchaseType = printDoc.purchaseTypes[i];
					printDoc.equipType = printDoc.equipTypes[j];
					printDoc.id = this.getPrintDocumentAssignmentID(printDoc);
					pass &= this.comparePrintDocListInfo(printDoc);
				}
			}
		}else{
			pass &= this.comparePrintDocListInfo(printDoc);
		}
		return pass;
	}
	
	/**
	 * show active doc records.
	 * @param status
	 */
	public void showActiveDocRecords(String status){
		this.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		this.selectStatus(status);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getAllDocRecordsId(){
		IHtmlObject objs[] = this.getDocumentTable();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Document Assignment table object.");
		}

			IHtmlTable table = (IHtmlTable)objs[0];
			List<String> ids = new ArrayList<String>();
			for(int i=1;i<table.rowCount();i++){						
	
			ids.add(table.getCellValue(i, 0));  
		}
			return ids;
	}
}
