package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EFTAdjustmentInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class LicMgrStoreEFTAdjustmentsPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreEFTAdjustmentsPage _instance = null;
	
	protected LicMgrStoreEFTAdjustmentsPage() {}
	
	public static LicMgrStoreEFTAdjustmentsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreEFTAdjustmentsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.adjustmentReason", false));
	}
	
	/**
	 * Select adjustment type
	 * @param adjustmentType
	 */
	public void selectAdjustmentType(String adjustmentType) {
		browser.selectDropdownList(".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.adjustmentType", false), adjustmentType);
	}
	
	/**
	 * Select adjustment reason
	 * @param adjustmentReason
	 */
	public void selectAdjustmentReason(String adjustmentReason) {
		browser.selectDropdownList(".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.adjustmentReason", false), adjustmentReason);
	}
	

	
	/**
	 * Select invoicing status
	 * @param status
	 */
	public void selectInvoicingStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.invoiceStatus", false), status);
	}
	
	/**
	 * Set value to Posted From Date
	 * @param fromDate
	 */
	public void setPostedFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.postFromDate_ForDisplay", false), fromDate);
	}
	
	/**
	 * Set value to Posted To Date
	 * @param toDate
	 */
	public void setPostedToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression("EftAdjustmentsUI-\\d+\\.postToDate_ForDisplay", false), toDate);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickAddEFTAdjustment(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add EFT Adjustment");
	}
	
	public void setSearchCriteria(EFTAdjustmentInfo eftAdjustment){
		if(null != eftAdjustment.getAdjustmentType() && eftAdjustment.getAdjustmentType().length()>0){
			this.selectAdjustmentType(eftAdjustment.getAdjustmentType());
		}
		
		if(null != eftAdjustment.getReason() && eftAdjustment.getReason().length()>0){
			this.selectAdjustmentReason(eftAdjustment.getReason());
		}
		
		if(null != eftAdjustment.getInvoicingStatus() && eftAdjustment.getInvoicingStatus().length()>0){
			this.selectInvoicingStatus(eftAdjustment.getInvoicingStatus());
		}
		
		if(null != eftAdjustment.getPostedFromDate() && eftAdjustment.getPostedFromDate().length()>0){
			this.setPostedFromDate(eftAdjustment.getPostedFromDate());
		}
		
		if(null != eftAdjustment.getPostedToDate() && eftAdjustment.getPostedToDate().length()>0){
			this.setPostedToDate(eftAdjustment.getPostedToDate());
		}
	}
	
	public List<String> getAdjustmentIDs(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "storeEftAdjustmentList_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found store EFT Adjustment list table object.");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		
		List<String> adjustmentIDs = table.getColumnValues(0);
		Browser.unregister(objs);
		adjustmentIDs.remove(0);
		
		return adjustmentIDs;
	}
}
