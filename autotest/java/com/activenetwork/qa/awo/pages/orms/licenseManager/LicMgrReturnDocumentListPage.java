package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.ReturnDocumentOrderInfo.ReturnDocumentOrderItem;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LicMgrReturnDocumentListPage extends LicMgrCommonTopMenuPage{
	private static LicMgrReturnDocumentListPage _instance = null;

	protected LicMgrReturnDocumentListPage (){}

	public static LicMgrReturnDocumentListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrReturnDocumentListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","docsPage_docList");
	}
	
	public void selectReturnedStatus(String returnSatus){
		browser.selectDropdownList(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.returnStatus",false), returnSatus);
	}
	
	public void setReceiptNum(String receiptNum){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.receiptNumber",false), receiptNum);
	}
	
	public void setOrderNum(String ordNum){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.orderNum",false), ordNum);
	}
	
	public void setStoreID(String storeID){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.storeId",false), storeID);
	}
	
	public void setRegisterID(String registerID){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.registerId",false), registerID);
	}
	
	public void setVendorNum(String vendorNum){
		browser.setTextField(".id", new RegularExpression("UnreturnedDocsSearchCriteria-\\d+\\.vendorNum",false), vendorNum);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
	}
	
	private IHtmlObject[] getTRObjectByOrdNum(String ordNum){
		IHtmlObject[] trObjs = browser.getHtmlObject(".class", "Html.TR", ".text",
				new RegularExpression("(| )Credit Sales Location.*Order\\:" + ordNum + ".*",false));
		
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
		
		IHtmlObject[] trOrdItemObjs = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(oddrow  )?child-of-docsPage_" + ordTrID + " initialized",false));
		
		IHtmlObject[] objs = this.getReturnDocumentOrderListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		
		ReturnDocumentOrderInfo returnDocuOrderInfo = new ReturnDocumentOrderInfo();
		String value = table.getCellValue(indexOfOrd + 1, 3);
		returnDocuOrderInfo.setReturnDueDate(value.split("Return Due Date:")[1].split("Customer:")[0].trim());
		returnDocuOrderInfo.setCustomerInfo(value.split("Receipt #:")[0].split("Return Due Date:")[1].trim());
		returnDocuOrderInfo.setReceiptNumInfo(value.split("Receipt #:")[1].trim());
		
		value = table.getCellValue(indexOfOrd + 1, 4);
		returnDocuOrderInfo.setOrdNum(value.split("Order:")[1].split("Status:")[0].trim());
		returnDocuOrderInfo.setReturnStatus(value.split("Status:")[1].trim());
		
		value = table.getCellValue(indexOfOrd + 1, 7);
		returnDocuOrderInfo.setPurchaseDate(value.split("Purchased at:")[1].split("Voided at:")[0].split("on")[1].trim());
		returnDocuOrderInfo.setPurchasedLocation(value.split("Purchased at:")[1].split("Voided at:")[0].split("on")[0].trim());
		returnDocuOrderInfo.setVoidedDate(value.split("Voided at:")[1].split("on")[1].trim());
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
}
