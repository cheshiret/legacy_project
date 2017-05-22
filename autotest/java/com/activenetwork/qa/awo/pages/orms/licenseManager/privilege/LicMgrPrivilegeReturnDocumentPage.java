package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo.ReturnDocumentOrderItem;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 5, 2012
 */
public class LicMgrPrivilegeReturnDocumentPage extends LicMgrTopMenuPage {
	
	private static LicMgrPrivilegeReturnDocumentPage _instance = null;
	
	private LicMgrPrivilegeReturnDocumentPage() {}
	
	public static LicMgrPrivilegeReturnDocumentPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeReturnDocumentPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.returnStatus", false));
	}
	
	/**
	 * Select return status value
	 * @param status - Pending, Charged
	 */
	public void selectReturnStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.returnStatus", false), status);
	}
	
	public void selectReturnStatus(int index) {
		browser.selectDropdownList(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.returnStatus", false), index);
	}
	
	public void setReceiptNum(String receiptNum){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.receiptNumber",false), receiptNum);
	}
	
	/**
	 * Set order number value
	 * @param orderNum
	 */
	public void setOrderNum(String orderNum) {
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.orderNum", false), orderNum, true);
	}
	
	public void setStoreID(String storeID) {
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.storeId", false), storeID);
	}
	
	public void setRegisterID(String regID) {
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.registerId", false), regID);
	}
	
	public void setVendorNum(String vendorNum) {
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.vendorNum", false), vendorNum);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	/**
	 * Select corresponding check box identified by order number to return
	 * @param orderNum
	 */
	public void selectPrivilegeToReturn(String orderNum) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(".*Return Due Date.*" + orderNum, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find TR object.");
		}
		browser.selectCheckBox(Property.toPropertyArray(".id", new RegularExpression("UnreturnedDocView-\\d+\\.checked$", false)), 0, objs[0]);
		Browser.unregister(objs);
	}
	
	/**
	 * Click this button to make selected privilege's status as 'Returned'
	 */
	public void clickMarkAsReturned() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Mark as Returned");
	}
	
	public void searchReturnDocumentByOrdNum(String orderNum){
		this.setOrderNum(orderNum);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * return specific privilege document
	 * @param orderNum
	 */
	public void returnDocument(String orderNum) {
		this.selectPrivilegeToReturn(orderNum);
		this.clickMarkAsReturned();
		ajax.waitLoading();
	}

	public String getFirstOrderNum() {
		Property[] p1=new Property[]{new Property(".id","docsPage_docList")};
		IHtmlObject[] tables=browser.getTableTestObject(p1);
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find doc list table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int row=table.findRow(3, new RegularExpression("Return Due Date.*", false));
		String ordNum=RegularExpression.getMatches(table.getCellValue(row, 4),"\\d+-\\d+")[0];
		Browser.unregister(tables);
		return ordNum;
	}
	
	public String getReturnFirstDocStatus(){
		Property[] p1=new Property[]{new Property(".id","docsPage_docList")};
		IHtmlObject[] tables=browser.getTableTestObject(p1);
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find doc list table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int row=table.findRow(3, new RegularExpression("Return Due Date.*", false));
		String status=RegularExpression.getMatches(table.getCellValue(row,4),"Status:.*")[0].split(":")[1].trim();
		Browser.unregister(tables);
		return status;
	}

	public List<String> getPriviligeNumsByOrdNum(String ordNum) {
		List<String> list=new ArrayList<String>();
		Property[] p1=new Property[]{new Property(".id","docsPage_docList")};
		IHtmlObject[] tables=browser.getTableTestObject(p1);
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find doc list table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int rowCount=table.rowCount();
		int startRow=table.findRow(4, new RegularExpression("^Order: "+ordNum+".*", false))+1;
		int count=startRow;
		String value;//=table.getCellValue(count, 0);
		do{
			value=table.getCellValue(count, 1);
			if (value.matches("\\d+")) {
				list.add(value);
			}
			count++;
		}while(count<rowCount);
	    return list;
	}
	
	public List<String> getPriviligeTransactionTypeByOrdNum(String ordNum) {
		List<String> list=new ArrayList<String>();
		Property[] p1=new Property[]{new Property(".id","docsPage_docList")};
		IHtmlObject[] tables=browser.getTableTestObject(p1);
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find doc list table");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int rowCount=table.rowCount();
		int startRow=table.findRow(4, new RegularExpression("^Order: "+ordNum+".*", false))+1;
		int count=startRow;
		String value=table.getCellValue(count, 4);
		do{
			value=table.getCellValue(count, 4);
			if (!StringUtil.isEmpty(value)) {
				list.add(value);
			}
			count++;	
		}while(count<rowCount);
	    return list;
	}
	
	private IHtmlObject[] getTRObjectByOrdNum(String ordNum){
		IHtmlObject[] trObjs =browser.getHtmlObject(".class", "Html.TR", ".text",
				new RegularExpression("(| )Credit Sales Location.*Order\\:(| )" + ordNum + ".*",false));
		
		if(trObjs.length<1){
			throw new ItemNotFoundException("Did not found tr object by order number = " + ordNum);
		}
		
		return trObjs;
	}
	
	public void selectReturnDocOrdCheckBox(String ordNum){
		IHtmlObject[] trObjs = this.getTRObjectByOrdNum(ordNum);
		browser.selectCheckBox(".id", new RegularExpression("UnreturnedDocView-\\d+\\.checked",false), 0, true, trObjs[0]);
		Browser.unregister(trObjs);
	}
	
	public void unSelectReturnDocOrdCheckBox(String ordNum){
		IHtmlObject[] trObjs = this.getTRObjectByOrdNum(ordNum);
		browser.unSelectCheckBox(".id", new RegularExpression("UnreturnedDocView-\\d+\\.checked",false), 0, trObjs[0]);
		Browser.unregister(trObjs);
	}
	
	public void setCommentsByOrdNum(String ordNum,String comments){
		IHtmlObject[] trObjs = this.getTRObjectByOrdNum(ordNum);
		browser.setTextField(".id", new RegularExpression("UnreturnedDocView-\\d+\\.comments",false), comments, trObjs[0]);
		Browser.unregister(trObjs);
	}
	
	private IHtmlObject[] getReturnDocumentOrderListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","docsPage_docList");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not fund return document order list table object.");
		}
		
		return objs;
	}
	
	public ReturnDocumentOrderInfo getReturnDocumentOrderInfoByOrderNum(String ordNum){
		IHtmlObject[] trOrdObjs = this.getTRObjectByOrdNum(ordNum);
		
		String ordTrID = trOrdObjs[0].id();
		int indexOfOrd = Integer.valueOf(ordTrID.replaceAll("docsPage_docList_row_", ""));
		
		IHtmlObject[] trOrdItemObjs = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(oddrow  )?child-of-" + ordTrID + " initialized",false));
		IHtmlObject[] objs = this.getReturnDocumentOrderListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		
		ReturnDocumentOrderInfo returnDocuOrderInfo = new ReturnDocumentOrderInfo();
		String value = table.getCellValue(indexOfOrd + 1, 3);
		returnDocuOrderInfo.setReturnDueDate(value.split("Return Due Date:")[1].split("Customer:")[0].trim());
		returnDocuOrderInfo.setCustomerInfo(value.split("Receipt #:")[0].split("Customer:")[1].trim());
		returnDocuOrderInfo.setReceiptNumInfo(value.split("Receipt #:")[1].trim());
		
		value = table.getCellValue(indexOfOrd + 1, 4);
		returnDocuOrderInfo.setOrdNum(value.split("Order:")[1].split("Status:")[0].trim());
		returnDocuOrderInfo.setReturnStatus(value.split("Status:")[1].trim());
		
		value = table.getCellValue(indexOfOrd + 1, 6);
		returnDocuOrderInfo.setPurchaseDate(RegularExpression.getMatches(value.split("Purchased at:")[1].split("Voided at:")[0],"[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0].trim());
		returnDocuOrderInfo.setPurchasedLocation(value.split("Purchased at:")[1].split("Voided at:")[0].split("on")[0].trim());
		returnDocuOrderInfo.setVoidedDate(RegularExpression.getMatches(value.split("Voided at:")[1],"[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0].trim());
		returnDocuOrderInfo.setVoidedLocation(value.split("Voided at:")[1].split("on")[0].trim());
		
		List<ReturnDocumentOrderItem> returnDocOrdItemList = new ArrayList<ReturnDocumentOrderItem>();
		for(int i= indexOfOrd+2; i<indexOfOrd +2 + trOrdItemObjs.length; i++){
			ReturnDocumentOrderItem returnDocOrdItem = new ReturnDocumentOrderItem();
			returnDocOrdItem.setPrivilegeNum(table.getCellValue(i, 1));
			returnDocOrdItem.setProductInfo(table.getCellValue(i, 2));
			returnDocOrdItem.setLicenseYear(table.getCellValue(i, 3));
			returnDocOrdItem.setTransactionType(table.getCellValue(i, 4));
			returnDocOrdItem.setValidFromDate(table.getCellValue(i, 5));
			returnDocOrdItem.setValidToDate(table.getCellValue(i, 6));
			
			returnDocOrdItemList.add(returnDocOrdItem);
		}
		
		returnDocuOrderInfo.setReturnDocumentOrderItems(returnDocOrdItemList);
		
		Browser.unregister(objs);
		Browser.unregister(trOrdObjs);
		Browser.unregister(trOrdItemObjs);
		return returnDocuOrderInfo;
	}
	
	public void searchReturnDocumentOrderInfo(ReturnDocumentOrderInfo returnDocOrdInfo){
		if(StringUtil.notEmpty(returnDocOrdInfo.getReturnStatus())){
			this.selectReturnStatus(returnDocOrdInfo.getReturnStatus());
		}else{
			this.selectReturnStatus(0);
		}
		
		if(null != returnDocOrdInfo.getReceiptNum()){
			this.setReceiptNum(returnDocOrdInfo.getReceiptNum());
		}
		
		if(null != returnDocOrdInfo.getOrdNum()){
			this.setOrderNum(returnDocOrdInfo.getOrdNum());
		}
		
		if(null != returnDocOrdInfo.getStoreID()){
			this.setStoreID(returnDocOrdInfo.getStoreID());
		}

		if(null != returnDocOrdInfo.getRegisterID()){
			this.setRegisterID(returnDocOrdInfo.getRegisterID());
		}

		if(null != returnDocOrdInfo.getVendorNum()){
			this.setVendorNum(returnDocOrdInfo.getVendorNum());
		}

	}
	
	public boolean compareReturnDocumentOrderInfo(ReturnDocumentOrderInfo expReturnDocOrdInfo){
		ReturnDocumentOrderInfo actDocumentOrderInfo = this.getReturnDocumentOrderInfoByOrderNum(expReturnDocOrdInfo.getOrdNum());
		
		boolean result = true;
		if(expReturnDocOrdInfo.getReturnDocumentOrderItems().size() != actDocumentOrderInfo.getReturnDocumentOrderItems().size()){
			throw new ErrorOnPageException("Return document order item info is not correct. please check.");
		}
		for(int i=0; i<expReturnDocOrdInfo.getReturnDocumentOrderItems().size(); i++){
			result &= MiscFunctions.compareResult("Privilege number", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getPrivilegeNum(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getPrivilegeNum());
			result &= MiscFunctions.compareResult("Product info", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getProductInfo(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getProductInfo());
			result &= MiscFunctions.compareResult("License Year info", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getLicenseYear(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getLicenseYear());
			result &= MiscFunctions.compareResult("Transaction Type info", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getTransactionType(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getTransactionType());
			result &= MiscFunctions.compareResult("Valid From Date info", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getValidFromDate(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getValidFromDate());
			result &= MiscFunctions.compareResult("Valid To Date info", expReturnDocOrdInfo.getReturnDocumentOrderItems().get(i).getValidToDate(), 
					actDocumentOrderInfo.getReturnDocumentOrderItems().get(i).getValidToDate());
		}
		result &= MiscFunctions.compareResult("Return Due Date", expReturnDocOrdInfo.getReturnDueDate(), actDocumentOrderInfo.getReturnDueDate());
		result &= MiscFunctions.compareResult("Customer Info", expReturnDocOrdInfo.getCustomerInfo(), actDocumentOrderInfo.getCustomerInfo());
		result &= MiscFunctions.compareResult("Recipent Info", expReturnDocOrdInfo.getReceiptNumInfo(), actDocumentOrderInfo.getReceiptNumInfo());
		result &= MiscFunctions.compareResult("Order number", expReturnDocOrdInfo.getOrdNum(), actDocumentOrderInfo.getOrdNum());
		result &= MiscFunctions.compareResult("Return Status", expReturnDocOrdInfo.getReturnStatus(), actDocumentOrderInfo.getReturnStatus());
		result &= MiscFunctions.compareResult("Purchased At Date Info", expReturnDocOrdInfo.getPurchaseDate(), actDocumentOrderInfo.getPurchaseDate());
		result &= MiscFunctions.compareResult("Purchased At Location Info", expReturnDocOrdInfo.getPurchasedLocation(), actDocumentOrderInfo.getPurchasedLocation());
		result &= MiscFunctions.compareResult("Voided At Date Info", expReturnDocOrdInfo.getVoidedDate(), actDocumentOrderInfo.getVoidedDate());
		result &= MiscFunctions.compareResult("Voided At Location Info", expReturnDocOrdInfo.getVoidedLocation(), actDocumentOrderInfo.getVoidedLocation());
		
		return result;
	}
	
	public IHtmlObject[] getReturnDocumentOrderRowObject(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".id", new RegularExpression("docsPage_docList_row_\\d+",false), ".className", "label_title labelgroup   initialized parent"));
		return objs;
	}
	
	public boolean checkNoSearchResult(){
		boolean noSearchResult = true;
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		if(objs.length<1){
			noSearchResult = true;
		}else{
			noSearchResult = false;
		}
		Browser.unregister(objs);
		return noSearchResult;
	}
	
	public List<String> getReturnDocumentOrderStatusListValue(){
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		List<String> statusList = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] childrenObjs = objs[i].getChildren();
			IHtmlObject statusObjs = (IHtmlObject) childrenObjs[4];
			String status = statusObjs.text().split("Status:")[1].trim();
			statusList.add(status);
		}
		Browser.unregister(objs);
		return statusList;
	}
	
	public List<String> getReceiptListValue(){
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		List<String> receiptInfoList = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] childrenObjs = objs[i].getChildren();
			IHtmlObject statusObjs = (IHtmlObject) childrenObjs[3];
			String receiptInfo= statusObjs.text().split("Receipt #:")[1].trim();
			receiptInfoList.add(receiptInfo);
		}
		Browser.unregister(objs);
		return receiptInfoList;
	}
	
	public List<String> getPurchasedLocationListValue(){
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		List<String> purchaseLocationInfoList = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] childrenObjs = objs[i].getChildren();
			IHtmlObject purchaseObjs = (IHtmlObject) childrenObjs[6];
			String purchaseLocationInfo= purchaseObjs.text().split("Purchased at:")[1].split("Voided at:")[0].split("on")[0].trim();
			purchaseLocationInfoList.add(purchaseLocationInfo);
		}
		Browser.unregister(objs);
		return purchaseLocationInfoList;
	}
	
	public List<String> getVoidedLocationListValue(){
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		List<String> voidedLocationInfoList = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] childrenObjs = objs[i].getChildren();
			IHtmlObject voidObjs = (IHtmlObject) childrenObjs[6];
			String voidedLocationInfo= voidObjs.text().split("Voided at:")[1].split("on")[0].trim();
			voidedLocationInfoList.add(voidedLocationInfo);
		}
		Browser.unregister(objs);
		return voidedLocationInfoList;
	}
	
	public List<String> getOrdNumListValue(){
		IHtmlObject[] objs = this.getReturnDocumentOrderRowObject();
		List<String> ordNumInfoList = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			IHtmlObject[] childrenObjs = objs[i].getChildren();
			IHtmlObject ordNumObjs = (IHtmlObject) childrenObjs[4];
			String ordNumInfo= ordNumObjs.text().split("Order:")[1].split("Status:")[0].trim();
			ordNumInfoList.add(ordNumInfo);
		}
		Browser.unregister(objs);
		return ordNumInfoList;
	}
	
}

