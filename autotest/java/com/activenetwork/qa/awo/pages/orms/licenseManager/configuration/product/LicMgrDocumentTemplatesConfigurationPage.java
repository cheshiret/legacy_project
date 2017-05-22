package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
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
public class LicMgrDocumentTemplatesConfigurationPage extends LicMgrProductConfigurationPage {
	
	private  static LicMgrDocumentTemplatesConfigurationPage instance = null;
	
	private LicMgrDocumentTemplatesConfigurationPage() {};
	
	public static LicMgrDocumentTemplatesConfigurationPage getInstance() {
		if(instance == null) {
			instance = new LicMgrDocumentTemplatesConfigurationPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "document_template");
	}
	
	public boolean checkDocTemplateExists(String identifier) {
		return this.checkIdentifierExists(identifier, "document_template");
	}
	
    /**
     * get the 1st DOC name which 'Harvest Document' is 'yes'
     * @Return String
     * @Throws
     */
	public String getHarvestDocName(){
		return this.getCellValue("document_template", "Yes", "Harvest Document", "Document Template Name");
	}
	
	public List<String> getColumns(){
		IHtmlTable table=this.getDocTemplateTableObject();
		int columnCount=table.columnCount();
		List<String> columns= new ArrayList<String>();
		for(int i=0;i<columnCount;i++){
			columns.add(table.getCellValue(0, i));
		}
		Browser.unregister(table);
		return columns;
	}
	
	public IHtmlTable getDocTemplateTableObject(){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "document_template");
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("Can't find table with id="+"document_template");
	    }
	    return (IHtmlTable)objs[0];
	}
	
	public void verifySortByName(boolean isAsc){
		IHtmlTable table=this.getDocTemplateTableObject();
		List<String> namesOnpage=table.getColumnValues(0);
		
		namesOnpage.remove(0);
		
		if(namesOnpage.size()<2){
			throw new ErrorOnDataException("please make sure there are at least two existing records ");
		}
		
		List<String> names=new ArrayList<String>();
		for(int i=0;i<namesOnpage.size();i++){
			names.add(namesOnpage.get(i).toUpperCase());
		}
		Collections.sort(names);
		if(isAsc){
			for(int i=0;i<names.size();i++){
				if(!names.get(i).equalsIgnoreCase(namesOnpage.get(i))){
					throw new ErrorOnPageException("template name should be sort by "+(isAsc?"ASC":"DSC"));
				}
			}
		}else{
			int size=names.size();
			int j=size-1;
			for(int i=0;i<size;i++){
				
				if(!namesOnpage.get(i).equalsIgnoreCase(names.get(j))){
					throw new ErrorOnPageException("template name should be sort by "+(isAsc?"ASC":"DSC"));
				}
				j--;
			}
		}
	}
	
	/**
	 * get document template info in document template list
	 * @param identifier 
	 * @param colName
	 * @return
	 */
	public List<String> getDocTemplateInfo(String identifier, String colName) {
		return this.getRowInfo("document_template", identifier, colName);
	}
	
	/**
	 * click document template name link
	 * @param documentTemplateName
	 */
	public void clickDocumentTemplateName(String documentTemplateName){
		browser.clickGuiObject(".class", "Html.A",".text",documentTemplateName);
	}
	
	/**
	 *Click the template name lable.
	 */
	public void clickTemplateNameLabel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Document Template Name");
	}
	
	/**
	 * Get all the list item of document template.
	 */
	public List<DocumentTemplateInfo> getAllDocumentTemplateInfo(){
		List<DocumentTemplateInfo> list=new ArrayList<DocumentTemplateInfo>();
		IHtmlTable table=this.getDocTemplateTableObject();
		
		int row=table.rowCount();
		for(int i=1;i<row;i++){
			DocumentTemplateInfo templ=new DocumentTemplateInfo();
			templ.docTemplateName=table.getCellValue(i, 0);
			templ.templateType=table.getCellValue(i, 1);
			templ.maxReprintCount=table.getCellValue(i, 2);
			templ.combIndicator=table.getCellValue(i, 3);
			templ.maxLineCount=table.getCellValue(i, 4);
			templ.harvestDocument=table.getCellValue(i, 5);
			list.add(templ);
		}
		Browser.unregister(table);
		return list;
	}
	
	
	/**
	 * Compare the document template info.
	 * @param docuTempInfo - the document template info.
	 */
	public boolean compareDocuTempListInfo(DocumentTemplateInfo docuTempInfo){
		boolean pass = true;
		IHtmlObject[] obj = browser.getTableTestObject(".class", "Html.TABLE", ".id", "document_template");
		IHtmlTable table = (IHtmlTable) obj[0];
		int row = table.findRow(0, docuTempInfo.docTemplateName);
		List<String> list = table.getRowValues(row);
		Browser.unregister(obj);
		if(list.size() < 1){
			pass &= false;
			logger.error("Can't get the "+docuTempInfo.docTemplateName+"document templates info");
		}
		if(!docuTempInfo.docTemplateName.equals(list.get(0))){
			pass &= false;
			logger.error("Docment template name is not corrected");
		}
		if(!docuTempInfo.templateType.equals(list.get(1))){
			pass &= false;
			logger.error("Docment template Type is not corrected");
		}
		if(!docuTempInfo.maxReprintCount.equals(list.get(2))){
			pass &= false;
			logger.error("Docment template Maximum reprint count is not corrected");
		}
		if(!docuTempInfo.combIndicator.equalsIgnoreCase(list.get(3))){
			pass &= false;
			logger.error("Docment template combination indicator is not corrected");
		}
		if(!docuTempInfo.maxLineCount.equals(list.get(4))){
			pass &= false;
			logger.error("Docment template maximum line count is not corrected");
		}
		if(!docuTempInfo.harvestDocument.equals(list.get(5))){
			pass &= false;
			logger.error("Docment template harest document is not corrected");
		}
		return pass;
	}
	
	/**
	 * Check the document templates whether exist or not.
	 * @param docTempName - the document template name.
	 */
	public boolean checnDocTemplatesExist(String docTempName){
		boolean isExist = true;
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".id", "document_template");
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(0, docTempName);
		if(row !=-1){
			isExist = true;
		}else{
			isExist = false;
		}
		Browser.unregister(objs);
		return isExist;
	}
}
