/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPrintDocumentPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Jun 3, 2011
 */
public class LicMgrPrivilegePrintDocumentsPage extends LicMgrPrivilegeProductDetailsPage implements ILicMgrProductPrintDocumentPage{

	private static LicMgrPrivilegePrintDocumentsPage _instance = null;

	protected LicMgrPrivilegePrintDocumentsPage() {}

	public static LicMgrPrivilegePrintDocumentsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegePrintDocumentsPage();
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
	}

	public void selectStatus(String status) {
		if(status.trim().length()<1){
			browser.selectDropdownList(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.status", false), 0);
			return;
		}
		browser.selectDropdownList(".id", new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.status", false), status);
	}

	public boolean checkDocumetnTemplateExist(String template){
		RegularExpression regx=new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.template", false);
		boolean result = true;
		List<String> templateList = browser.getDropdownElements(".id", regx);
		if(templateList.size()>0){
			if(templateList.contains(template)){
				result = true;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	public void selectDocumentTemplate(String template) {
		RegularExpression regx=new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.template", false);
		if(template.trim().length()<1){
			browser.selectDropdownList(".id", regx, 0);
			return;
		}
		browser.selectDropdownList(".id", regx, template);
	}

	public void selectEquipmentType(String type) {
		RegularExpression regx=new RegularExpression("PrdFulfillmentDocListUISearchCriteria-\\d+\\.equipmentType", false);
		if(type.trim().length()<1){
			browser.selectDropdownList(".id", regx, 0);
		}
		browser.selectDropdownList(".id", regx, type);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void clickPrintDocumentID(String id){
		browser.clickGuiObject(".class", "Html.A",".text",id,true);
	}
	
	public IHtmlObject[] getDocumentGrid(){
		return browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));
	}

	/**
	 * Get document assignment info(document assignment ID, equipment type, purchase type)
	 * @param document
	 * @return
	 *       document assignment ID, equipment type, purchase type
	 */
	public List<String[]> getDocumentAssignmentInfo(LicMgrDocumentInfo document) {
		List<String[]> equipTypeAndpurchaseTypeAndIDs = new ArrayList<String []>();
		//equiptype initial value, purchaseType initial value
		if(document.equipType.length()>0 && document.purchaseType.length()>0) {
			equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType});
		}

		//equiptype initial value, purchaseTypes initial value
		if(document.equipType.length()>0  && null!=document.purchaseTypes && document.purchaseTypes.length>0){
			for(int i=0;i<document.purchaseTypes.length;i++){
				document.purchaseType = document.purchaseTypes[i];
				equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType});
			}
		}

		//equipTypes initial value, purchaseType initial value
		if(null != document.equipTypes && document.equipTypes.length > 0 && document.purchaseType.length()>0) {
			for(int i =0;i<document.equipTypes.length;i++){
				document.equipType = document.equipTypes[i];
				equipTypeAndpurchaseTypeAndIDs.add(new String[]{this.getPrintDocumentAssignmentID(document),document.equipType,document.purchaseType});
			}

		}

		//equipTypes initial value, purchaseTypes initial value
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

	/**
	 * Get document assignment ID via status, document template name, equipment type, purchase type, license year from, and Effective From Date
	 * @param document
	 * @return
	 */
	public String getPrintDocumentAssignmentID(LicMgrDocumentInfo document){
		return this.getPrintDocumentAssignmentID(document, true);
	}

	/**
	 * Get document assignment ID via status, document template name, equipment type, purchase type, license year from, and Effective From Date
	 * @param document
	 * @param withEffectiveFromDate whether or not use effectiveFromDate as search criteria to get print documents id.
	 * @return
	 */
	public String getPrintDocumentAssignmentID(LicMgrDocumentInfo document, boolean withEffectiveFromDate){
		IHtmlObject objs[] = this.getDocumentGrid();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Document Assignment table object.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		String id = "";
		for(int i=0;i<table.rowCount();i++){

			if(table.getCellValue(i, 1).equalsIgnoreCase(document.status.trim().length()<1?"Active":document.status)
					&& table.getCellValue(i, 2).equalsIgnoreCase(document.docTepl) 
					&& table.getCellValue(i, 3).equalsIgnoreCase(document.equipType) 
					&& table.getCellValue(i, 4).equalsIgnoreCase(document.purchaseType) 
					&& table.getCellValue(i, 5).equalsIgnoreCase(document.licYearFrom)){
				if (withEffectiveFromDate){
					if (table.getCellValue(i, 7).equalsIgnoreCase(DateFunctions.formatDate(document.effectiveFromDate, "E MMM d yyyy"))){
						id = table.getCellValue(i, 0);
						break;
					}
				}else{
					id = table.getCellValue(i, 0);
					break;
				}
			}  
		}

		Browser.unregister(objs);
		return id;
	}

	public List<String> getDocumentColumnNames(){
		IHtmlObject[] objs=this.getDocumentTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		List<String> list=table.getRowValues(0);
		Browser.unregister(objs);
		return list;
	}

	private IHtmlObject[] getDocumentTable(){
		IHtmlObject objs[] = this.getDocumentGrid();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Document Assignment table object.");
		}
		return objs;
	}

	public List<String> getColumnValues(String columnName){
		IHtmlObject[] objs=this.getDocumentTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int column=table.findColumn(0, columnName);
		List<String> list=table.getColumnValues(column);
		list.remove(0);//remove column name
		Browser.unregister(objs);
		return list;
	}
	
	public List<String> getDocumentIDColumnList(){
		return this.getColumnValues("ID");
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
			ajax.waitLoading();
		}else{
			this.uncheckShowCurrentRecordsOnly();
			ajax.waitLoading();
			this.selectStatus(status);
			if(checkDocumetnTemplateExist(template)){
				this.selectDocumentTemplate(template);
			}
			this.selectEquipmentType(equipType);
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	/**
	 * Checking whether the given fulfillment document exist in the license year list page or not
	 * @param document
	 * @return
	 */
	public boolean verifyDocumentExist(LicMgrDocumentInfo document){
		IHtmlObject objs[] = this.getDocumentGrid();

		IHtmlTable table = (IHtmlTable)objs[0];
		boolean flag = false;

		//should verify if there is no document under this now
		//then return false
		int statusColNum = table.findColumn(0, "Status");
		int documentTemplateColNum = table.findColumn(0, "Document Template");
		int equipmentTypeColNum = table.findColumn(0, "Equipment Type");
		int purchaseTypeColNum = table.findColumn(0, "Purchase Type");
		int licenseYearFromColNum = table.findColumn(0, "License Year From");
		int harvestDocumentColNum = table.findColumn(0, "Harvest Document");

		if (document.purchaseType.matches("Duplicate|Replacement")) {
			document.purchaseType = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		}

		logger.info("start checking whether the given fulfillment document exist in the Print Document list page or not with " +
				"Status= "+document.status+" Document Template= "+document.docTepl+" Equipment Type= "+document.equipType+" " +
				"Purchase Type= "+document.purchaseType+" License Year= "+document.licYearFrom+" Harvest Document= "+document.harvestDocument);
		for (int i = 1; i < table.rowCount(); i ++){
			if (table.getCellValue(i, statusColNum).equals(document.status)&&
					table.getCellValue(i, documentTemplateColNum).equals(document.docTepl) 
					&& table.getCellValue(i, equipmentTypeColNum).equals(document.equipType) 
					&& table.getCellValue(i, purchaseTypeColNum).equals(document.purchaseType) 
					&& table.getCellValue(i, licenseYearFromColNum).equals(document.licYearFrom) 
					&& table.getCellValue(i, harvestDocumentColNum).equals(document.harvestDocument)
					){
				flag = true;
				break;
			}
		}

		Browser.unregister(objs);
		return flag;
	}
	
	public String getDocumentID(LicMgrDocumentInfo document){
		IHtmlObject objs[] = this.getDocumentGrid();

		if (document.purchaseType.matches("Duplicate|Replacement")) {
			document.purchaseType = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement",  this.getContract()); //Lesley[20131111]: udpate due to DB change. Need contract info 
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		String documentID = "";
		for (int i = 1; i < table.rowCount(); i ++){
			if (table.getCellValue(i, 1).equals(document.status)&&
					table.getCellValue(i, 2).equals(document.docTepl) &&
					table.getCellValue(i, 3).equals(document.equipType) &&
					table.getCellValue(i, 4).equals(document.purchaseType) &&
					table.getCellValue(i, 5).equals(document.licYearFrom) &&
					table.getCellValue(i, 9).equals(document.harvestDocument)){
				documentID = table.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		return documentID;
	}
	
	public List<String> getPrintDocumentAssignmentIDColumn(){
		return this.getColumnValues("ID");
	}
	
	public String getActiveIDViaGiveDocumentID(LicMgrDocumentInfo document){
		IHtmlObject objs[] = this.getDocumentGrid();
		IHtmlTable table = (IHtmlTable)objs[0];
		String documentID = "";
		for (int i = 1; i < table.rowCount(); i ++){
			if (table.getCellValue(i, 0).compareTo(document.id)>0 && 
					table.getCellValue(i, 1).equals("Active") 
					&&  table.getCellValue(i, 2).equals(document.docTepl) 
					&&	table.getCellValue(i, 3).equals(document.equipType) 
//					&&	table.getCellValue(i, 4).equals(document.purchaseType) 
//					&& 	table.getCellValue(i, 5).equals(document.licYearFrom) 
//					&&  table.getCellValue(i, 9).equals(document.harvestDocument)
					){
				documentID = table.getCellValue(i, 0);
				break;
			}
		}

		Browser.unregister(objs);
		return documentID;
	}

	
	/**
	 * click document id.
	 * @param id
	 */
	public void clickDocumentId(String id){
	   browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	/**
	 * compare Print document list info.
	 * @param printDoc
	 * @return
	 */
	public boolean comparePrintDocListInfo(LicMgrDocumentInfo printDoc){
		boolean pass = true;
		IHtmlObject objs[] = this.getDocumentGrid();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(0, printDoc.id);
		List<String> rowInfo = table.getRowValues(row);
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
		if(!rowInfo.get(9).equals(printDoc.harvestDocument)){
			pass &= false;
			logger.error("Expected value is "+printDoc.harvestDocument +" but actual value is" + rowInfo.get(9));
		}
		if(!rowInfo.get(10).equals(printDoc.printOrd)){
			pass &= false;
			logger.error("Expected value is "+printDoc.printOrd +" but actual value is" + rowInfo.get(10));
		}
		if(!rowInfo.get(11).equals(printDoc.fulfillMethod)){
			pass &= false;
			logger.error("Expected value is "+printDoc.fulfillMethod +" but actual value is" + rowInfo.get(11));
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
					printDoc.id = this.getDocumentID(printDoc);
					pass &= this.comparePrintDocListInfo(printDoc);
				}
			}
		}else{
			pass &= this.comparePrintDocListInfo(printDoc);
		}
		return pass;
	}
	
	public List<String> getPrintDocumentAssignmentIdsForCleanUp(
			LicMgrDocumentInfo document) {
		IHtmlObject objs[] = this.getDocumentGrid();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Document Assignment table object.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ids = new ArrayList<String>();
		for(int i=1;i<table.rowCount();i++){						

	
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
	
	public List<String> getAllDocRecordsId(){
		IHtmlObject objs[] = this.getDocumentGrid();
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
	/**
	 * show active doc records.
	 * @param status
	 */
	public void showActiveDocRecords(){
		this.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		this.selectStatus("Active");
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
}
