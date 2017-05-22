/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentUploadInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 25, 2014
 */
public class LicMgrVendorDocumentUploadSubPage extends LicMgrVendorDetailsPage{
	private static LicMgrVendorDocumentUploadSubPage _instance = null;
	
	private LicMgrVendorDocumentUploadSubPage(){}
	
	public static LicMgrVendorDocumentUploadSubPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrVendorDocumentUploadSubPage();
		}
		return _instance;
	}
	
	protected Property[] documentUploadListTablePrp(){
		return Property.toPropertyArray(".id", "documentUploadSearch_LIST");
	}
	
	protected Property[] uploadDocumentButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Upload Document");
	}
	
	protected Property[] statusPrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentFileSearchCriteria-\\d+\\.documentActive",false));
	}
	
	protected Property[] agentPrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentFileSearchCriteria-\\d+\\.store",false));
	}
	
	protected Property[] goButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Go");
	}
	
	protected Property[] idHeaderLinkPrp(){
		return Property.addToPropertyArray(a(), ".text", "ID");
	}
	
	protected Property[] viewButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "View");
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.documentUploadListTablePrp());
	}
	
	public boolean isUploadDocumentButtonExisting(){
		return browser.checkHtmlObjectExists(this.uploadDocumentButtonPrp());
	}
	
	public void clickUploadDocumentButton(){
		browser.clickGuiObject(this.uploadDocumentButtonPrp());
	}
	
	private IHtmlObject[] getDocumentUploadListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(this.documentUploadListTablePrp());
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get document upload list table object.");
		}
		
		return objs;
	}
	
	
	public String getDocumentUploadIDByDocumentName(String documentName){
		IHtmlObject[] objs = this.getDocumentUploadListTableObject();
		
		IHtmlTable tableObj = (IHtmlTable) objs[0];
		int col = tableObj.findColumn(0, "Document Name");
		int row = tableObj.findRow(col, documentName);
		int col_id = tableObj.findColumn(0, "ID");
		
		String id = tableObj.getCellValue(row, col_id);
		Browser.unregister(objs);
		return id;
	}
	
	public DocumentUploadInfo getDocumentUploadInfoByID(String id){
		IHtmlObject[] objs = this.getDocumentUploadListTableObject();
		
		IHtmlTable tableObj = (IHtmlTable) objs[0];
		
		int row = tableObj.findRow(0, id);
		
		DocumentUploadInfo documentUploadInfo = new DocumentUploadInfo();
		documentUploadInfo.setAgent(tableObj.getCellValue(row, 1));
		documentUploadInfo.setStatus(tableObj.getCellValue(row, 2));
		documentUploadInfo.setDocumentName(tableObj.getCellValue(row, 3));
		documentUploadInfo.setType(tableObj.getCellValue(row, 4));
		documentUploadInfo.setUploadedDate(tableObj.getCellValue(row, 5));
		documentUploadInfo.setUploadedBy(tableObj.getCellValue(row, 6));
		
		Browser.unregister(objs);
		return documentUploadInfo;
	}
	
	public void compareDocumentUploadInfo(DocumentUploadInfo expDocumentUploadInfo){
		logger.info("Compare Document upload info. id = " + expDocumentUploadInfo.getID());
		DocumentUploadInfo actDocumentUploadInfo = this.getDocumentUploadInfoByID(expDocumentUploadInfo.getID());
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Agent", expDocumentUploadInfo.getAgent(), actDocumentUploadInfo.getAgent());
		result &= MiscFunctions.compareResult("Status", expDocumentUploadInfo.getStatus(), actDocumentUploadInfo.getStatus());
		result &= MiscFunctions.compareResult("Document Name", expDocumentUploadInfo.getDocumentName(), actDocumentUploadInfo.getDocumentName());
		result &= MiscFunctions.compareResult("Type", expDocumentUploadInfo.getType(), actDocumentUploadInfo.getType());
		result &= MiscFunctions.compareResult("Upload Date", expDocumentUploadInfo.getUploadedDate(), actDocumentUploadInfo.getUploadedDate());
		result &= MiscFunctions.compareResult("Upload By", expDocumentUploadInfo.getUploadedBy().replaceAll("\\s+", ""), actDocumentUploadInfo.getUploadedBy().replaceAll("\\s+", ""));
		
		if(!result){
			throw new ErrorOnPageException("Document Upload Info at list not correct, please check log.");
		}else logger.info("Document Upload Info at list is correct, please check log.");
		
	}
	
	public void clickIdLink(String id){
		browser.clickGuiObject(Property.addToPropertyArray(a(), ".text", id));
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusPrp(), status);
	}
	
	public void selectStatus(){
		browser.selectDropdownList(this.statusPrp(), 0);
	}
	
	public List<String> getStatusDropdownList(){
		return browser.getDropdownElements(this.statusPrp());
	}
	
	public void selectAgent(String agent){
		browser.selectDropdownList(this.agentPrp(), agent);
	}
	
	public List<String> getAgentDropDownListElements(){
		return browser.getDropdownElements(this.agentPrp());
	}
	
	public void selectAgent(){
		browser.selectDropdownList(this.agentPrp(), 0);
	}
	
	public void clickGo(){
		browser.clickGuiObject(this.goButtonPrp());
	}
	
	public void setupSearchDocumentUploadCriteria(String status, String agent){
		if(StringUtil.notEmpty(status)){
			this.selectStatus(status);
		}else this.selectStatus();
		
		if(StringUtil.notEmpty(agent)){
			this.selectAgent(agent);
		}else this.selectAgent();
	}
	
	public void searchDocumentUpload(String status, String agent){
		this.setupSearchDocumentUploadCriteria(status, agent);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getColumnListValue(String columnName){
		IHtmlObject[] objs = this.getDocumentUploadListTableObject();

		IHtmlTable tableObj = (IHtmlTable) objs[0];
		int col = tableObj.findColumn(0, columnName);
		List<String> values = tableObj.getColumnValues(col);
		
		Browser.unregister(objs);
		values.remove(0);
		return values;
	}
	
	public List<String> getStatusListValue(){
		return this.getColumnListValue("Status");
	}
	
	public List<String> getAgentListValue(){
		return this.getColumnListValue("Agent");
	}
	
	public List<String> getIDListValue(){
		return this.getColumnListValue("ID");
	}
	
	public void compareColumnListValue(String columnName, String value){
		List<String> actListValues = this.getColumnListValue(columnName);
		boolean result = true;
		for(int i=0;i<actListValues.size();i++){
			result &= MiscFunctions.compareResult(columnName + " column list", value, actListValues.get(i));
		}
		
		if(!result){
			throw new ErrorOnPageException(columnName + " column list value not correct, please check log.");
		}
	}
	
	public void verifyListSorting(boolean isAsc, String columnName){
		List<String> values_exp = this.getColumnListValue(columnName);
		List<String> values_act = this.getColumnListValue(columnName);
		
		Collections.sort(values_exp);//asc
		if(!isAsc){
			Collections.reverse(values_exp);//desc
		}
		
		boolean result = MiscFunctions.compareListString(columnName + " column sorting value", values_exp, values_act);
		if(!result){
			throw new ErrorOnPageException(columnName + " column list sorting not correct. Please check log.");
		}
		
	}
	
	public void clickIDHeader(){
		browser.clickGuiObject(this.idHeaderLinkPrp());
	}
	
	public void clickViewByDocumentUploadID(String id){
		IHtmlObject[] objs = this.getDocumentUploadListTableObject();

		IHtmlTable tableObj = (IHtmlTable) objs[0];

		int row = tableObj.findRow(0, id);
		
		browser.clickGuiObject(this.viewButtonPrp(), row-1);
		Browser.unregister(objs);
		
	}

}
