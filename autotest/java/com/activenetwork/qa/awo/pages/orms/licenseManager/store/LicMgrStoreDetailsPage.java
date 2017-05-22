package com.activenetwork.qa.awo.pages.orms.licenseManager.store;



import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This page is extended by all related store pages.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 19, 2011
 */
public class LicMgrStoreDetailsPage extends LicMgrCommonTopMenuPage {
	private static LicMgrStoreDetailsPage _instance = null;
	
	protected LicMgrStoreDetailsPage() {}
	
	public static LicMgrStoreDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreDetailsPage();
		}
		
		return _instance;
	}
	
	protected Property[] custProfileAssociationTR() {
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Customer Profile Association.*", false));
	}
	
	protected Property[] custNumField() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("Input-\\d+\\.boundText", false));
	}
	
	protected Property[] custNumLink(String custNum) {
		return Property.concatPropertyArray(a(), ".text", custNum);
	}
	
	protected Property[] searchCustLink() {
		return Property.concatPropertyArray(a(), ".text", "Search");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TR",".text", new RegularExpression("(Agent|Issuer) Info( )?(Agent|Issuer) ID.*",false));		
	}
	
	/**
	 * Get the error message display on the top of the page
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		String toReturn = "";
		if(objs.length >= 1) {
			toReturn = objs[0].getProperty(".text").trim();
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public void clickDailySalesActivity() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Daily Sales Activity");
	}
	
	public boolean isDailySalesActivityBtnExisted() {
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Daily Sales Activity");
		if(objs.length < 1){
			flag = false;
		}
		
		Browser.unregister(objs);
		
		return flag;
	}
	
	public void clickAccountBalance() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Account Balance");
	}
	
	public void clickInvoices() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Invoices");
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change History");
	}
	
	public void clickHelpDeskCallLog() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Help Desk Call Log");
	}
	
	public String getStoreID() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Agent ID\\d+",false));
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", objs[objs.length-1]);
		String text = spanObjs[0].text();
		Browser.unregister(spanObjs);
		Browser.unregister(objs);
		return text.replaceAll("Agent ID", StringUtil.EMPTY);
	}
	
	public String getStoreStatus() {
		try {
			return browser.getDropdownListValue(".id", new RegularExpression("StoreView-\\d+\\.statusReasonIdPair", false), 0);
		} catch (ItemNotFoundException e) {
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Status.*", false));
			IHtmlObject closeStatusSpan = browser.getHtmlObject(".class", "Html.SPAN", objs[0])[0];
			String status = closeStatusSpan.text();
			
			Browser.unregister(objs);
			Browser.unregister(closeStatusSpan);
			return status.replaceAll("Status", StringUtil.EMPTY);
		}
	}
	
	
	public String getLocationClass() {
//		return browser.getDropdownListValue(".id", new RegularExpression("StoreView-\\d+\\.locClassView", false), 0);

		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Location Class.*",false));
		String text = spanObjs[0].text();
		Browser.unregister(spanObjs);
		return text.replaceAll("Location Class", StringUtil.EMPTY).trim();
	}
	
	public String getCreateDate() {
		return getAttributeValueByName("Create Date");
	}
	
	/**
	 * Set the store name.
	 * @param name - the value of name.
	 */
	public void setStoreName(String name){
		browser.setTextField(".id", new RegularExpression("StoreView-\\d+\\.name",false), name);
	}
	
	/**
	 * Get the store name.
	 * @return String - the value of name.
	 */
	public String getStoreName(){
		return browser.getTextFieldValue(".id", new RegularExpression("StoreView-\\d+\\.name",false));
	}
	
	/**
	 * Get the time zone.
	 * @return String - the value of time zone.
	 */
	public String getTimeZone(){
		return browser.getTextFieldValue(".id", new RegularExpression("StoreView-\\d+\\.timezoneId",false));
	}
	/**
	 * This method is used to parse the DIV object to get some attribute values
	 * @param attributeName
	 * @return
	 */
	private String getAttributeValueByName(String attributeName) {
		IHtmlObject objs[] = null;
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		
		if(null != attributeName && attributeName.length() > 0){
			objs = browser.getHtmlObject(property);
		} else {
			throw new ErrorOnDataException("Unknown attribute name.");
		}
		
		String attributeValue = "";
		if(objs.length == 1) {
			attributeValue = objs[0].getProperty(".text").split(attributeName)[1].trim();
		} else if(attributeName.equalsIgnoreCase("Location")) {
			attributeValue = objs[1].getProperty(".text").split(attributeName)[1].trim();
		} else {
			throw new ActionFailedException("Find 0 or multiple SPAN object named - " + attributeName + ".");
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	
	public String getCreateUser() {
		return this.getAttributeValueByName("Create User");
	}
	
	public String getAgency() {
		return this.getAttributeValueByName("Agency");
	}
	
	public String getRegion() {
		return this.getAttributeValueByName("Region");
	}
	
	public String getLocation() {
		return this.getAttributeValueByName("Location");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickAddressesAndContactsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Addresses & Contacts");
		ajax.waitLoading();
	}
	
	public void clickNotesAndAlertsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
		ajax.waitLoading();
	}
	
	public void clickFinancialConfigTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Financial Config");
	}
	
	public void clickEFTAdjustmentsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "EFT Adjustments");
		ajax.waitLoading();
	}
	
	public void clickEFTInvoicePaymentsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "EFT Invoice Payments");
	}
	
	public void clickProductAssignmentsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Product Assignments.*",false));
	}
	
	public void clickInventoryTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Inventory");
	}
	
	public void clickEquipmentTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Equipment");
		ajax.waitLoading();
	}
	
	/**
	 * Select the status.
	 * @param status - the value of status.
	 */
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("StoreView-\\d+\\.statusReasonIdPair",false), status);
	}
	/**
	 * Select the location class info.
	 * @param location class - the value of location class.
	 */
	public void selectLocationClass(String locationClass){
		browser.selectDropdownList(".id", new RegularExpression("StoreView-\\d+\\.locClassView",false), locationClass);
	}
	
	/**
	 * Set the store basic info
	 * 
	 * @param storeInfo
	 *            - the store info.
	 */
	
	public void SetStoreInfo(StoreInfo storeInfo) {
		if(storeInfo.status.length() > 0){
			this.selectStatus(storeInfo.status);
		}
		if(storeInfo.storeName.length() > 0){
			this.setStoreName(storeInfo.storeName);
		}
		
		/** location class can't be edit. Nicole 306
	    if(storeInfo.locationClass.length() > 0){
	    	this.selectLocationClass(storeInfo.locationClass);
	    }
	    */
	}
	/**
	 * Verify the store info.
	 * @param Contacts -  expected store info.
	 */
	public boolean compareStoreDetailInfo(StoreInfo expectedStore){
		return this.compareStoreDetailInfoStatusNotSure(expectedStore, true);
	}
	
	public boolean compareStoreDetailInfoStatusNotSure(StoreInfo expectedStore, boolean checkStatus){
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Store ID", expectedStore.storeID, getStoreID());
		if(checkStatus){
			pass &= MiscFunctions.compareResult("Store Status", expectedStore.status, getStoreStatus());
		}
		pass &= MiscFunctions.compareResult("Store Name", expectedStore.storeName, getStoreName());
		pass &= MiscFunctions.compareResult("Store Location Class", expectedStore.locationClass, getLocationClass());
		if (!StringUtil.isEmpty(expectedStore.createDate)) {
			pass &= MiscFunctions.compareResult("Store Create Date", expectedStore.createDate, getCreateDate());
		}
		if (!StringUtil.isEmpty(expectedStore.createUser)) {
			pass &= MiscFunctions.compareResult("Store Create User", expectedStore.createUser.replaceAll("\\s+", StringUtil.EMPTY), getCreateUser().replaceAll("\\s+", ""));
		}
		if(!StringUtil.isEmpty(expectedStore.agency)) {
			pass &= MiscFunctions.compareResult("Store Agency", expectedStore.agency, getAgency());
		}
		String temp = this.getRegion();
		if(!temp.contains(expectedStore.region.split(" ")[0])){
			pass &=false;
			logger.error("Store region "+ temp +" error");
		}
		/*temp = this.getLocation();
		if(!temp.equals(expectedStore.location)){
			pass &=false;
			logger.error("Store location "+ temp +" error");
		}*/
		return pass;	
	}
	
	public void setCustomerNum(String custNum) {
		browser.setTextField(custNumField(), custNum);
	}
	
	public void clickSearchCustomer() {
		browser.clickGuiObject(Property.atList(custProfileAssociationTR(), searchCustLink()));
	}
	
	public boolean isCustNumLinkExist(String custNum) {
		return browser.checkHtmlObjectExists(custNumLink(custNum));
	}
}
