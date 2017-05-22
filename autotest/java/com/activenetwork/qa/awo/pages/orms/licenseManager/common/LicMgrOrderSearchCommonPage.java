package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 27, 2011
 */
public abstract class LicMgrOrderSearchCommonPage extends LicMgrOrderCommonPage {
	
	/**
	 * Select orders search type
	 * @param searchType
	 */
	public void selectOrderSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.searchTypeID", false), searchType, true);
	}
	
	/**
	 * Set orders search value
	 * @param searchValue
	 */
	public void setOrderSearchValue(String searchValue) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.searchValue", false), searchValue, true);
	}
	
	/**
	 * Set orders product code
	 * @param productCode
	 */
	public void setOrdersProductCode(String productCode) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.productCode", false), productCode, true);
	}
	
	/**
	 * Select orders verification status
	 * @param verificationStatus
	 */
	public void selectOrdersVerificationStatus(String verificationStatus) {
		browser.selectDropdownList(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.verificationStatus", false), verificationStatus, true);
	}
	
	/**
	 * Select orders purchase type
	 * @param purchaseType
	 */
	public void selectOrdersPurchaseType(String purchaseType) {
		browser.selectDropdownList(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.purchaseType", false), purchaseType, true);
	}
	
	/**
	 * Select orders residency status
	 * @param residencyStatus
	 */
	public void selectOrdersResidencyStatus(String residencyStatus) {
		browser.selectDropdownList(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.residencyStatus", false), residencyStatus, true);
	}
	
	/**
	 * Set orders from date
	 * @param fromDate
	 */
	public void setOrdersFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.createFromDate_ForDisplay", false), fromDate, true);
	}
	
	public void clickBlankPg() {
		browser.clickGuiObject(".class", "Html.LABEL", ".text","Order From Date");
	}
	
	/**
	 * Set orders to date
	 * @param toDate
	 */
	public void setOrdersToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.createToDate_ForDisplay", false), toDate, true);
	}
	
	/**
	 * Set orders sales location
	 * @param salesLocation
	 */
	public void setOrdersSalesLocation(String salesLocation) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.salesLocation", false), salesLocation, true);
	}
	
	/**
	 * Set orders first name
	 * @param fName
	 */
	public void setOrdersOperatorFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.firstName", false), fName, true);
	}
	
	/**
	 * Set orders last name
	 * @param lName
	 */
	public void setOrdersOperatorLastName(String lName) {
		browser.setTextField(".id", new RegularExpression("OrderSearchCriteria-\\d+\\.lastName", false), lName, true);
	}
	
	/**
	 * Click search button for all 4 sub-pages
	 */
	public void clickSearch() {
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("SearchBar_\\d+",false));
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true, 0, objs1[0]);
		Browser.unregister(objs1);
	}
	
	/**
	 * Click Go button in Receipts sub-page
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
	}
	
	/**
	 * Select a specified  order according  order number
	 * @param OrdNum
	 */
	public void clickOrderNum(String OrdNum) {
		if(OrdNum == null || OrdNum.length() == 0) {
			throw new ItemNotFoundException("Please set the  Order Number.");
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", OrdNum, true);
	}
	
	
	
	public abstract void setupOrderSearchCriteria(Object object);
	
	/**
	 * Search order by order number
	 * @param ordNum
	 */
	public void searchOrderByNum(String ordNum) {
		this.selectOrderSearchType("Order #");
		this.setOrderSearchValue(ordNum);
		this.clickSearch();
		ajax.waitLoading();
	}
}
