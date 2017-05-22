package com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DocumentFulfillmentInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrDocumentFulfillmentCommonPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Document Fulfillment page. Admin -> Document Fulfillment
 * 
 * @author Lesley Wang
 * @Date  Sep 10, 2013
 */
public class LicMgrDocFulfillmentPage extends
		LicMgrDocumentFulfillmentCommonPage {
	private static LicMgrDocFulfillmentPage instance=null;
	
	protected LicMgrDocFulfillmentPage(){}
	
	public static LicMgrDocFulfillmentPage getInstance(){
		if(instance == null){
			instance=new LicMgrDocFulfillmentPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] docFulfillmentTable() {
		return Property.toPropertyArray(".class", "Html.Table", ".id", "documentFulfilmentGrid_LIST");
	}
	
	protected Property[] receiptDateFromTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", 
				".id", new RegularExpression("DocumentFulfillmentSearchCriteria-\\d+\\.receiptFromDate_ForDisplay", false));
	}
	
	protected Property[] receiptDateToTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", 
				".id", new RegularExpression("DocumentFulfillmentSearchCriteria-\\d+\\.receiptToDate_ForDisplay", false));
	}
	
	protected Property[] numOfPagesList() {
		return Property.toPropertyArray(".class", "Html.Select", 
				".id", new RegularExpression("DocumentFulfillmentSearchCriteria-\\d+\\.pageNumber", false));
	}
	
	protected Property[] showUSShippingAddress() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", 
				".id", new RegularExpression("DocumentFulfillmentSearchCriteria-\\d+\\.showUSShippingAddress", false));
	}
	
	protected Property[] showNonUSShippingAddress() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", 
				".id", new RegularExpression("DocumentFulfillmentSearchCriteria-\\d+\\.showNonUSShippingAddress", false));
	}
	
	protected Property[] goBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Go");
	}
	
	protected Property[] printDocumentsBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "printDocumentsButtonAnchor");
	}
	
	protected Property[] selectAllCheckBox() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", ".id", new RegularExpression("DocumentFulfillmentUI-\\d+\\.selectAll", false));
	}
	
	protected Property[] docFulfillmentItemCheckBox() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", ".id", new RegularExpression("ReceiptForDocumentFulfilmentVO-\\d+\\.selected", false));
	}
	
	protected Property[] docFulfillmentItemTR(String receiptNum) {
		return Property.toPropertyArray(".class", "Html.tr", ".text", new RegularExpression("^"+receiptNum+".*", false));
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.docFulfillmentTable());
	}
	
	public void setReceiptDateFrom(String date) {
		browser.setTextField(this.receiptDateFromTextField(), date);
	}
	
	public void setReceiptDateTo(String date) {
		browser.setTextField(this.receiptDateToTextField(), date);
	}
	
	public void selectNumOfPages(String num) {
		browser.selectDropdownList(this.numOfPagesList(), num);
	}
	
	public void checkShowUSShippingAddress() {
		browser.selectCheckBox(this.showUSShippingAddress());
	}
	
	public void unCheckShowUSShippingAddress() {
		browser.unSelectCheckBox(this.showUSShippingAddress(), 0);
	}
	
	public void checkShowNonUSShippingAddress() {
		browser.selectCheckBox(this.showNonUSShippingAddress());
	}
	
	public void unCheckShowNonUSShippingAddress() {
		browser.unSelectCheckBox(this.showNonUSShippingAddress(), 0);
	}
	
	public void clickGo() {
		browser.clickGuiObject(this.goBtn());
	}
	
	public void clickPrintDocuments() {
		browser.clickGuiObject(this.printDocumentsBtn());
	}
	
	public void setSearchCriteria(DocumentFulfillmentInfo docFulfillInfo) {
		if (docFulfillInfo.getReceiptDateFrom() != null) {
			this.setReceiptDateFrom(docFulfillInfo.getReceiptDateFrom());
		}
		
		if (docFulfillInfo.getReceiptDateTo() != null) {
			this.setReceiptDateTo(docFulfillInfo.getReceiptDateTo());
		}
		
		this.selectNumOfPages(docFulfillInfo.getNumOfPages());
		
		if (docFulfillInfo.isShowUSShippingAddr()) {
			this.checkShowUSShippingAddress();
		} else {
			this.unCheckShowUSShippingAddress();
		}
		
		if (docFulfillInfo.isShowNonUSShippingAddr()) {
			this.checkShowNonUSShippingAddress();
		} else {
			this.unCheckShowNonUSShippingAddress();
		}
	}
	
	public void searchDocFulfillment(DocumentFulfillmentInfo docFulfillInfo) {
		this.setSearchCriteria(docFulfillInfo);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void unSelectAll() {
		browser.unSelectCheckBox(this.selectAllCheckBox(), 0);
	}
	
	public boolean isReceiptNumExist(String receiptNum) {
		return browser.checkHtmlObjectExists(this.docFulfillmentItemTR(receiptNum));
	}
	
	public void selectDocFulfillment(String receiptNum) {
		IHtmlObject[] top = browser.getHtmlObject(this.docFulfillmentItemTR(receiptNum));
		if (top.length < 1) {
			throw new ObjectNotFoundException("Can't find document fulfillment record TR for " + receiptNum);
		}
		browser.selectCheckBox(this.docFulfillmentItemCheckBox(), 0, top[0]);
		Browser.unregister(top);
	}
}
