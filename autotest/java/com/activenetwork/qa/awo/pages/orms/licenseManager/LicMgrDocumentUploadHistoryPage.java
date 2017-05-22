/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentUploadInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  Feb 26, 2014
 */
public class LicMgrDocumentUploadHistoryPage extends LicMgrCommonTopMenuPage{
	private static LicMgrDocumentUploadHistoryPage _instance = null;
	
	private LicMgrDocumentUploadHistoryPage () {}
	
	public static LicMgrDocumentUploadHistoryPage getInstance(){
		if(null ==_instance){
			_instance = new LicMgrDocumentUploadHistoryPage();
		}
		
		return _instance;
	}
	
	protected Property[] historyListTablePrp(){
		return Property.toPropertyArray(".id","documentuploadhistory_LIST");
	}
	
	protected Property[] spanByLablePrp(String labelName){
		return Property.addToPropertyArray(span(), ".text", new RegularExpression("^"+ labelName + ".*",false));
	}
	
	protected Property[] vendorInfoTRPrp(){
		return Property.addToPropertyArray(tr(), ".text", new RegularExpression("Vendor Info",false));
	}
	
	protected Property[] documentDetailsTRPrp(){
		return Property.addToPropertyArray(tr(), ".text", new RegularExpression("^Document Details.*",false));
	}
	
	protected Property[] returnToDocumentDetailsButtonPrp(){
		return Property.addToPropertyArray(a(), ".text", "Return to Document Details");
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.historyListTablePrp());
	}
	
	public List<ChangeHistory> getChangeHistoryInfo(){
		List<ChangeHistory> histories = new ArrayList<ChangeHistory>();
		
		IHtmlObject[] objs = browser.getTableTestObject(this.historyListTablePrp());
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find store change history table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		for(int i = 1; i < table.rowCount(); i ++) {
			ChangeHistory history = new ChangeHistory();
			history.changeDate = table.getCellValue(i, 0).trim();
			history.object = table.getCellValue(i, 1).trim();
			history.action = table.getCellValue(i, 2).trim();
			history.field = table.getCellValue(i, 3).trim();
			history.oldValue = table.getCellValue(i, 4).trim();
			history.newValue = table.getCellValue(i, 5).trim();
			history.user = table.getCellValue(i, 6).trim();
			history.location = table.getCellValue(i, 7).trim();
			
			histories.add(history);
		}
		
		return histories;
	}
	
	public void compareDocumentUploadChangeHistoryInfo(List<ChangeHistory> histories){
		List<ChangeHistory> actualHistories = this.getChangeHistoryInfo();
		
		logger.info("Compate documetn upload history info.");
		boolean result = true;
		if(histories.size() != actualHistories.size()){
			throw new ErrorOnPageException("History info size not correct. Expect size = " + histories.size() 
					+ ", but actually size = " +  actualHistories.size());
		}
		ChangeHistory history = new ChangeHistory();
		ChangeHistory actualHistory = new ChangeHistory();
		int count = 0;
		for(int i = 0; i < histories.size(); i ++) {
			history = histories.get(i);
			for(int j=0; j<actualHistories.size();j++){
				actualHistory = actualHistories.get(j);
				if(history.field.equalsIgnoreCase(actualHistory.field)){
					if(history.field.equalsIgnoreCase("Notes")){//when field is notes, Old Value and New Value columns shall display first 20 characters of the Note followed by an ellipsis
						if(history.oldValue.length()>20){
							history.oldValue = history.oldValue.substring(0, 20)+"...";
						}
						if(actualHistory.oldValue.length()>20){
							actualHistory.oldValue = actualHistory.oldValue.substring(0, 20)+"...";
						}
						
						if(history.newValue.length()>20){
							history.newValue = history.newValue.substring(0, 20)+"...";
						}
						if(actualHistory.newValue.length()>20){
							actualHistory.newValue = actualHistory.newValue.substring(0, 20)+"...";
						}
					}
					
					if(!history.equals(actualHistory)) {
						logger.error("Expected Change History - Field: " + history.field + ", Action: " + history.action + ", New Value: " + history.newValue
								+ " but actual record - Field: " + actualHistory.field + ", Action: " + actualHistory.action + ", New Value: " + actualHistory.newValue);
						result &= false;
					} else {
						logger.info("Actaul Change histroy record is correct with expected.");
					}
					
					count ++;
					break;
				}
			}
			
		}
		
		if(count != histories.size()){
			int notMatchFiled = histories.size()-count;
			throw new ErrorOnPageException("Have " + notMatchFiled +" History filed column info not match expect history filed column info.");
		}
		
		if(!result){
			throw new ErrorOnPageException("Change history recored not correct, please check log.");
		}
	}
	
	private String getSpanTextByLabelName(String labelName){
		IHtmlObject[] objs = browser.getHtmlObject(this.spanByLablePrp(labelName));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any span object by label name = " + labelName );
		}
		String temp = objs[objs.length-1].text();
		String text = temp.replaceFirst(labelName, "").trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getID(){
		return this.getSpanTextByLabelName("ID");
	}
	
	public String getAgent(){
		return this.getSpanTextByLabelName("Agent");
	}
	
	public boolean isAgentEixsts(){
		return browser.checkHtmlObjectExists(this.spanByLablePrp("Agent"));
	}
	
	public String getDocumentName(){
		return this.getSpanTextByLabelName("Document Name");
	}
	
	public String getType(){
		return this.getSpanTextByLabelName("Type");
	}
	
	public String getUploadDate(){
		return this.getSpanTextByLabelName("Date Uploaded");
	}
	
	public String getUploadBy(){
		return this.getSpanTextByLabelName("Uploaded By");
	}
	
	public String getVendorNum(){
		return this.getSpanTextByLabelName("Vendor #");
	}
	
	public String getVendorStatus(){
		IHtmlObject[] objs = browser.getHtmlObject(this.vendorInfoTRPrp());
		if(objs.length<1){
			throw new ErrorOnPageException("Did not get Vendor Info TR object.");
		}
		
		IHtmlObject[] statusSpanObj = browser.getHtmlObject(this.spanByLablePrp("Status"), objs[objs.length-1]);
		if(statusSpanObj.length<1){
			throw new ErrorOnPageException("Did not get Status object at Vendor Info TR object.");
		}
		
		String temp = statusSpanObj[statusSpanObj.length-1].text();
		String text = temp.replaceAll("Status", "").trim();
		
		Browser.unregister(objs);
		Browser.unregister(statusSpanObj);
		return text;
	}
	
	public String getDocumentStatus(){
		IHtmlObject[] objs = browser.getHtmlObject(this.documentDetailsTRPrp());
		if(objs.length<1){
			throw new ErrorOnPageException("Did not get Document Deatils TR object.");
		}
		
		IHtmlObject[] statusSpanObj = browser.getHtmlObject(this.spanByLablePrp("Status"), objs[objs.length-1]);
		if(statusSpanObj.length<1){
			throw new ErrorOnPageException("Did not get Status object at Document Deatils TR object.");
		}
		
		String temp = statusSpanObj[statusSpanObj.length-1].text();
		String text = temp.replaceAll("Status", "").trim();
		
		Browser.unregister(objs);
		Browser.unregister(statusSpanObj);
		return text;
	}
	
	public String getVendorName(){
		return this.getSpanTextByLabelName("Vendor Name");
	}
	
	public String getOwnerName(){
		return this.getSpanTextByLabelName("Owner Name");
	}
	
	public String getVendorType(){
		return this.getSpanTextByLabelName("Vendor Type");
	}
	
	public String getCreationDate(){
		return this.getSpanTextByLabelName("Creation Date");
	}
	
	public String getCreationUser(){
		return this.getSpanTextByLabelName("Creation User");
	}
	
	public DocumentUploadInfo getDocumentUploadInfo(){
		DocumentUploadInfo documentUploadInfo = new DocumentUploadInfo();
		
		documentUploadInfo.setID(this.getID());
		documentUploadInfo.setStatus(this.getDocumentStatus());
		if(this.isAgentEixsts()){
			documentUploadInfo.setAgent(this.getAgent());
		}
		documentUploadInfo.setDocumentName(this.getDocumentName());
		documentUploadInfo.setType(this.getType());
		documentUploadInfo.setUploadedDate(this.getUploadDate());
		documentUploadInfo.setUploadedBy(this.getUploadBy());
		
		return documentUploadInfo;
	}
	
	public void compareDocumentUploadDetailInfo(DocumentUploadInfo documentUploadInfo){
		DocumentUploadInfo actDocumentUploadInfo = this.getDocumentUploadInfo();
		
		logger.info("Compate document upload detail info at history page.");
		boolean result = true;
		result &= MiscFunctions.compareResult("ID", documentUploadInfo.getID(), actDocumentUploadInfo.getID());
		if(null != documentUploadInfo.getAgent()){
			result &= MiscFunctions.compareResult("Agent", documentUploadInfo.getAgent(), actDocumentUploadInfo.getAgent());
		}
		result &= MiscFunctions.compareResult("Status", documentUploadInfo.getStatus(), actDocumentUploadInfo.getStatus());
		result &= MiscFunctions.compareResult("Document Name", documentUploadInfo.getDocumentName(), actDocumentUploadInfo.getDocumentName());
		result &= MiscFunctions.compareResult("Type", documentUploadInfo.getType(), actDocumentUploadInfo.getType());
		result &= MiscFunctions.compareResult("Upload Date", documentUploadInfo.getUploadedDate(), actDocumentUploadInfo.getUploadedDate());
		result &= MiscFunctions.compareResult("Upload By", documentUploadInfo.getUploadedBy().replaceAll("\\s+", ""), actDocumentUploadInfo.getUploadedBy().replaceAll("\\s+", ""));
		
		if(!result){
			throw new ErrorOnPageException("Document Upload Info at history page not correct, please check log.");
		}else logger.info("Document Upload Info at history page is correct, please check log.");
	}
	
	public VendorInfo getVendorInfo(){
		VendorInfo vendorInfo = new VendorInfo();
		
		vendorInfo.number = this.getVendorNum();
		vendorInfo.status = this.getVendorStatus();
		vendorInfo.name = this.getVendorName();
		vendorInfo.ownerName = this.getOwnerName();
		vendorInfo.vendorType = this.getVendorType();
		vendorInfo.vendorCreationDate = this.getCreationDate();
		vendorInfo.vendorCreationUser = this.getCreationUser();
		
		return vendorInfo;
	}
	
	public void compareVendorInfo(VendorInfo vendorInfo){
		VendorInfo actVendorInfo = this.getVendorInfo();
		
		logger.info("Compare vendor detail info at document upload history page.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Vendor #", vendorInfo.number, actVendorInfo.number);
		result &= MiscFunctions.compareResult("Vendor Status", vendorInfo.status, actVendorInfo.status);
		result &= MiscFunctions.compareResult("Vendor Name", vendorInfo.name, actVendorInfo.name);
		result &= MiscFunctions.compareResult("Owner Name", vendorInfo.ownerName, actVendorInfo.ownerName);
		result &= MiscFunctions.compareResult("Vendor Type", vendorInfo.vendorType, actVendorInfo.vendorType);
		result &= MiscFunctions.compareResult("Vendor Creation Date", vendorInfo.vendorCreationDate, actVendorInfo.vendorCreationDate);
		result &= MiscFunctions.compareResult("Vendor Creation User", vendorInfo.vendorCreationUser.replaceAll("\\s+", ""), actVendorInfo.vendorCreationUser.replaceAll("\\s+", ""));
		
		if(!result){
			throw new ErrorOnPageException("Vendor Detail info not correct at document upload historypage, please check log.");
		}else logger.info("Vendor Detail info is correct at document upload historypage, please check log.");
	}
	
	public void clickReturnToDocumentDetailButton(){
		browser.clickGuiObject(this.returnToDocumentDetailsButtonPrp());
	}
	
}
