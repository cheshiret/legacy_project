package com.activenetwork.qa.awo.pages.orms.common.lottery;

import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsLotteryApplicationSearchPage extends OrmsPage{
	private static OrmsLotteryApplicationSearchPage _instance = null;//new PermitMgrSchOrderPage();

	public OrmsLotteryApplicationSearchPage() {

	}

	public static OrmsLotteryApplicationSearchPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsLotteryApplicationSearchPage();
		}
		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Application #");
		p[2] = new Property(".href",new RegularExpression(".*\"LightSearchPermitLotteryAppView\",1|0,\"appNumber\",.*",false));
		return browser.checkHtmlObjectExists(p);
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.searchFor", type);
	}
	
	public void setPhone(String phone){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.phone", phone);
	}
	
	public void setLastName(String lName){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.lastName", lName);
	}
	
	public void setFirstName(String fName){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.lastName", fName);
	}
	
	public void setEmailAddress(String email){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.email", email);
	}
	
	public void selectLotteryName(String lotteryName){
		browser.selectDropdownList(lotteryNameDropdownList(), lotteryName);
	}
	
	public void selectAwardedFacility(String facility){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.awardedFacility", facility);
	}
	
	public void selectAwardedGroup(String group){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.awardedProductGroup", group);
	}
	
	public void selectAwardedProduct(String product){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.awardedProduct", product);
	}
	
	public void setAwardedFromDate(String fDate){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.awardedFromDate_ForDisplay", fDate);
	}
	
	public void setAwardedToDate(String tDate){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.awardedToDate_ForDisplay", tDate);
	}
	
	public void setApplicationNum(String appNum){
		if(browser.checkHtmlObjectExists(".id", "PermitLotteryAppSearchCriteria.appNumber"))
		{
			browser.setTextField(".id", "PermitLotteryAppSearchCriteria.appNumber", appNum);
		}else if(browser.checkHtmlObjectExists(".id", "LotteryAppSearchCriteria.appNumber")){
			browser.setTextField(".id", "LotteryAppSearchCriteria.appNumber", appNum);		
		}
	}
	
	public void selectOrderStatus(String status){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.orderStatus", status);
	}
	
	public void selectLotteryStatus(String status){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.lotteryStatus", status);
	}
	
	public void selectDateType(String dateType){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.dateType", dateType);
	}
	
	public void setStartDate(String sDate){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.startDate_ForDisplay", sDate);
	}
	
	public void setEndDate(String eDate){
		browser.setTextField(".id", "PermitLotteryAppSearchCriteria.endDate_ForDisplay",eDate);
	}
	
	public void selectSalesChannel(String channel){
		browser.selectDropdownList(".id", "PermitLotteryAppSearchCriteria.salesChannel", channel);
	}
	
	public void setSourceID(String sourceID){
		browser.setTextField(".id","PermitLotteryAppSearchCriteria.inventoryID",sourceID);
	}
	
	public void clickApplicationNumber(String appNumber){
		browser.clickGuiObject(".class","Html.A",".text",appNumber);
	}
	
	public void clickGO(){
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".text","Search", ".id", "goAnchor"));
	}
	
	private Property[] displayDropdownList() {                        
		return Property.toPropertyArray(".id", new RegularExpression("LotteryAppSearchCriteria.displayFlag", false));
	}
	
	private Property[] lotteryNameDropdownList() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryAppSearchCriteria.lotteryId", false));
	}
	
	public List<String> getLotteryNames(){
		return browser.getDropdownElements(this.lotteryNameDropdownList());
	}
	
	public List<String> getDisplayElements(){
		return browser.getDropdownElements(this.displayDropdownList());
	}
	
	public String getLotteryName(){
		return browser.getDropdownListValue(this.lotteryNameDropdownList(), 0);
	}
	
	public String getDisplayValue(){
		return browser.getDropdownListValue(this.displayDropdownList(), 0);
	}
	
	public void selectDisplay(String displayType){
		browser.selectDropdownList(this.displayDropdownList(), displayType);
	}
	
	
//	/**Wait the search result exist*/
//	public void searchWaitExists() throws PageNotFoundException {
//		Property[] p1 = new Property[3];
//		p1[0] = new Property(".class", "Html.DIV");
//		p1[1] = new Property(".id", "NOTSET");
//		p1[2] = new Property(".text", new RegularExpression("^No result found that match Search Criteria.$", false));
//
//		Property[] p2 = new Property[2];
//		p2[0] = new Property(".class", "Html.A");
//		p2[1] = new Property(".id", "LightSearchPermitOrderView.permitNumber");
//		
//		browser.waitExists(Browser.LONG_SLEEP,p1,p2);
//	}
}
