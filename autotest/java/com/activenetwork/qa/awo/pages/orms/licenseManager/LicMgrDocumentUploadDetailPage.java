/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentUploadInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
public class LicMgrDocumentUploadDetailPage extends LicMgrCommonTopMenuPage{
	private static LicMgrDocumentUploadDetailPage _instance = null;
	
	private LicMgrDocumentUploadDetailPage(){}
	
	public static LicMgrDocumentUploadDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrDocumentUploadDetailPage();
		}
		
		return _instance;
	}
	
	protected Property[] viewDocumentPrp(){
		return Property.addToPropertyArray(a(), ".text", "View Document");
	}
	
	protected Property[] statusPrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.active",false));
	}
	
	protected Property[] agentPrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentFileView-\\d+\\.store",false));
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
	
	protected Property[] spanByLablePrp(String labelName){
		return Property.addToPropertyArray(span(), ".text", new RegularExpression("^"+ labelName + ".*",false));
	}
	
	protected Property[] receivedDatePrp(){
		return Property.toPropertyArray(".id",new RegularExpression("DocumentFileView-\\d+\\.receivedDate_ForDisplay",false));
	}
	
	protected Property[] errorMessagePrp(){
		return Property.toPropertyArray(".id","NOTSET");
	}
	
	protected Property[] changeHistoryPrp(){
		return Property.addToPropertyArray(a(), ".text", "Change History");
	}
	
	protected Property[] uploadedByPrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentFileView-\\d+\\.userDisplay",false));
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.viewDocumentPrp());
	}
	
	private String getSpanTextByLabelName(String labelName){
		IHtmlObject[] objs = browser.getHtmlObject(this.spanByLablePrp(labelName));
		System.out.println(objs.length);
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any span object by label name = " + labelName );
		}
		String temp = objs[objs.length-1].text();
		String text = temp.replaceAll(labelName, "").trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getID(){
		return this.getSpanTextByLabelName("ID");
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.statusPrp(), 0);
	}
	
	public String getAgent(){
		return browser.getDropdownListValue(this.agentPrp(), 0);
	}
	
	public String getUploadedBy(){
		IHtmlObject[] objs = browser.getTextField(this.uploadedByPrp());
		if(objs.length<1){
			throw new ErrorOnPageException("Did not found Uploaded By text object.");
		}
		
		String value = objs[objs.length-1].getProperty(".value");
		Browser.unregister(objs);
		return value;
	}
	
	public String getUpladedDate(){
		IHtmlObject[] objs = browser.getHtmlObject(this.spanByLablePrp("Date Uploaded"));
		if(objs.length<1){
			throw new ErrorOnPageException("Did not get any span object by label name is Date Uploaded");
		}
		
		IHtmlObject[] textObjs = browser.getHtmlObject(input("text"), objs[objs.length-1]);
		if(textObjs.length<1){
			throw new ErrorOnPageException("Did not get any text object for Date Uploaded Span.");
		}
		
		String text = textObjs[textObjs.length-1].getProperty(".value");
		Browser.unregister(textObjs);
		Browser.unregister(objs);
		return text;
	}
	
	public String getReceivedDate(){
		IHtmlObject[] objs = browser.getHtmlObject(this.spanByLablePrp("Date Received"));
		if(objs.length<1){
			throw new ErrorOnPageException("Did not get any span object by label name is Date Received");
		}
		
		IHtmlObject[] textObjs = browser.getHtmlObject(input("text"), objs[objs.length-1]);
		if(textObjs.length<1){
			throw new ErrorOnPageException("Did not get any text object for Date Received Span.");
		}
		
		String text = textObjs[textObjs.length-1].getProperty(".value");
		Browser.unregister(textObjs);
		Browser.unregister(objs);
		return text;
	}
	
	public String getDocumentName(){
		return browser.getTextFieldValue(this.documentNamePrp());
	}
	
	public String getType(){
		return browser.getDropdownListValue(this.typePrp(), 0);
	}
	
	public String getNotes(){
		return browser.getTextAreaValue(this.notesPrp());
	}
	
	public DocumentUploadInfo getDocumentUploadInfo(){
		DocumentUploadInfo documentUploadInfo = new DocumentUploadInfo();
		
		documentUploadInfo.setID(this.getID());
		documentUploadInfo.setStatus(this.getStatus());
		if(this.isAgentExisting()){
			documentUploadInfo.setAgent(this.getAgent());
		}//for vehicle document upload info no agent info
		
		documentUploadInfo.setUploadedBy(this.getUploadedBy());
		documentUploadInfo.setUploadedDate(this.getUpladedDate());
		documentUploadInfo.setReceivedDate(this.getReceivedDate());
		documentUploadInfo.setDocumentName(this.getDocumentName());
		documentUploadInfo.setType(this.getType());
		documentUploadInfo.setNotes(this.getNotes());
		
		return documentUploadInfo;
	}
	
	public void compareDocumentUploadInfo(DocumentUploadInfo expDocumentUploadInfo){
		logger.info("Compare Document upload info. id = " + expDocumentUploadInfo.getID());
		DocumentUploadInfo actDocumentUploadInfo = this.getDocumentUploadInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("ID", expDocumentUploadInfo.getID(), actDocumentUploadInfo.getID());
		if(null != expDocumentUploadInfo.getAgent()){
			result &= MiscFunctions.compareResult("Agent", expDocumentUploadInfo.getAgent(), actDocumentUploadInfo.getAgent());
		}
		
		result &= MiscFunctions.compareResult("Status", expDocumentUploadInfo.getStatus(), actDocumentUploadInfo.getStatus());
		result &= MiscFunctions.compareResult("Document Name", expDocumentUploadInfo.getDocumentName(), actDocumentUploadInfo.getDocumentName());
		result &= MiscFunctions.compareResult("Type", expDocumentUploadInfo.getType(), actDocumentUploadInfo.getType());
		result &= MiscFunctions.compareResult("Upload Date", expDocumentUploadInfo.getUploadedDate(), actDocumentUploadInfo.getUploadedDate());
		result &= MiscFunctions.compareResult("Upload By", expDocumentUploadInfo.getUploadedBy().replaceAll("\\s+", ""), actDocumentUploadInfo.getUploadedBy().replaceAll("\\s+", ""));
		result &= MiscFunctions.compareResult("Receive Date", expDocumentUploadInfo.getReceivedDate(), actDocumentUploadInfo.getReceivedDate());
		
		if(!result){
			throw new ErrorOnPageException("Document Upload Info at list not correct, please check log.");
		}else logger.info("Document Upload Info at list is correct, please check log.");
		
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusPrp(), status);
	}
	
	public void clickOK(){
		browser.clickGuiObject(this.okButtonPrp());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelButtonPrp());
	}
	
	private void updateDocumentUploadStatus(String status){
		this.selectStatus(status);
		this.clickOK();
		ajax.waitLoading();
	}
	
	public void inactiveDocumentUpload(){
		this.updateDocumentUploadStatus(OrmsConstants.INACTIVE_STATUS);
	}
	
	public void activeDocumentUpload(){
		this.updateDocumentUploadStatus(OrmsConstants.ACTIVE_STATUS);
	}
	
	public void selectAgent(String agent){
		browser.selectDropdownList(this.agentPrp(), agent);
	}
	
	public void selectAgent(){
		browser.selectDropdownList(this.agentPrp(), 0);
	}
	
	public boolean isAgentExisting(){
		return browser.checkHtmlObjectExists(this.agentPrp());
	}
	
	public void setReceivedDate(String receivedDate){
		browser.setCalendarField(this.receivedDatePrp(), receivedDate);
	}
	
	public void setDocumentName(String documentName){
		browser.setTextField(this.documentNamePrp(), documentName);
	}
	
	public void selectType(String type){
		browser.selectDropdownList(this.typePrp(), type);
	}
	
	public void selectType(){
		browser.selectDropdownList(this.typePrp(), 0);
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
	
	public void setupDocumentUploadInfo(DocumentUploadInfo documentUploadInfo){
		if(StringUtil.notEmpty(documentUploadInfo.getStatus())){
			this.selectStatus(documentUploadInfo.getStatus());
		}
		
		if(this.isAgentExisting() && StringUtil.notEmpty(documentUploadInfo.getAgent())){
			this.selectAgent(documentUploadInfo.getAgent());
		}else this.selectAgent();
		
		if(StringUtil.notEmpty(documentUploadInfo.getDocumentName())){
			this.setDocumentName(documentUploadInfo.getDocumentName());
		}else this.setDocumentName(StringUtil.EMPTY);
		
		if(StringUtil.notEmpty(documentUploadInfo.getType())){
			this.selectType(documentUploadInfo.getType());
		}else this.selectType();
		
		if(StringUtil.notEmpty(documentUploadInfo.getNotes())){
			this.setNotes(documentUploadInfo.getNotes());
		}else this.setNotes(StringUtil.EMPTY);
		
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(Property.atList(this.errorMessagePrp()));
	}
	
	public void clickChangeHistory(){
		browser.clickGuiObject(this.changeHistoryPrp());
	}

}
