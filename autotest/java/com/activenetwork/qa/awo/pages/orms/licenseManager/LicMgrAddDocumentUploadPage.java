/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentUploadInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
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
public class LicMgrAddDocumentUploadPage extends LicMgrCommonTopMenuPage{
	private static LicMgrAddDocumentUploadPage _instance = null;
	
	private LicMgrAddDocumentUploadPage () {}
	
	public static LicMgrAddDocumentUploadPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddDocumentUploadPage();
		}
		return _instance;
	}
	
	protected Property[] fileLocationPrp(){
		return Property.toPropertyArray( ".id", "uploadDocumentName");
	}
	
	protected Property[] agentPrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentFileView-\\d+\\.store",false));
	}
	
	protected Property[] receivedDatePrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.receivedDate_ForDisplay",false));
	}
	
	protected Property[] documentNamePrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.name",false));
	}
	
	protected Property[] typePrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.type",false));
	}
	
	protected Property[] addNewPrp(){
		return Property.addToPropertyArray(a(), ".text", "Add New");
	}
	
	protected Property[] notesPrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.note",false));
	}
	
	protected Property[] okButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancelButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Cancel");
	}
	
	protected Property[] errorMessagePrp(){
		return Property.toPropertyArray(".id","NOTSET");
	}
	
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(fileLocationPrp());
	}
	
	public void selectAgent(String agent){
		browser.selectDropdownList(this.agentPrp(), agent);
	}
	
	public boolean isAgentExisting(){
		return browser.checkHtmlObjectExists(this.agentPrp());
	}
	
	public String getAgent(){
		return browser.getDropdownListValue(this.agentPrp(), 0);
	}
	
	public List<String> getAgentDropdrownListElements(){
		return browser.getDropdownElements(this.agentPrp());
	}
	
	public void setReceivedDate(String receivedDate){
		browser.setCalendarField(this.receivedDatePrp(), receivedDate);
	}
	
	public String getReceivedDate(){
		return browser.getTextFieldValue(this.receivedDatePrp());
	}
	
	public void setDocumentName(String documentName){
		browser.setTextField(this.documentNamePrp(), documentName);
	}
	
	public String getDocumentName(){
		return browser.getTextFieldValue(this.documentNamePrp());
	}
	
	public void selectType(String type){
		browser.selectDropdownList(this.typePrp(), type);
	}
	
	public String getType(){
		return browser.getDropdownListValue(this.typePrp(), 0);
	}
	
	public List<String> getTypeDropDownListElements(){
		return browser.getDropdownElements(this.typePrp());
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewPrp());
	}
	
	public void setNotes(String notes){
		browser.setTextArea(this.notesPrp(), notes);
	}
	
	public String getNotes(){
		return browser.getTextAreaValue(this.notesPrp());
	}
	
	public void setFileLocation(String fileLocation){
		browser.setTextField(this.fileLocationPrp(), fileLocation);
	}
	
	public void setupDocumentUploadInfo(DocumentUploadInfo documentUploadInfo){
		if(this.isAgentExisting() && StringUtil.notEmpty(documentUploadInfo.getAgent())){
			this.selectAgent(documentUploadInfo.getAgent());
		}
		
		if(StringUtil.notEmpty(documentUploadInfo.getReceivedDate())){
			this.setReceivedDate(documentUploadInfo.getReceivedDate());
		}
		
		if(StringUtil.notEmpty(documentUploadInfo.getDocumentName())){
			this.setDocumentName(documentUploadInfo.getDocumentName());
		}
		
		if(StringUtil.notEmpty(documentUploadInfo.getType())){
			this.selectType(documentUploadInfo.getType());
		}
		
		if(StringUtil.notEmpty(documentUploadInfo.getNotes())){
			this.setNotes(documentUploadInfo.getNotes());
		}
		
		if(StringUtil.notEmpty(documentUploadInfo.getFileLocation())){
			this.setFileLocation(documentUploadInfo.getFileLocation());
		}
	}
	
	public void clickOK(){
		browser.clickGuiObject(this.okButtonPrp());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelButtonPrp());
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(Property.atList(this.errorMessagePrp()));
	}
	
}
