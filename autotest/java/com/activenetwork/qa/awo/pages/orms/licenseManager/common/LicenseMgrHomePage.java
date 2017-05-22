package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


public class LicenseMgrHomePage extends LicMgrCommonTopMenuPage {

	private static LicenseMgrHomePage _instance = null;

	public static LicenseMgrHomePage getInstance() {
		if (null == _instance) {
			_instance = new LicenseMgrHomePage();
		}

		return _instance;
	}

	protected LicenseMgrHomePage() {

	}

	@Override
	public boolean exists() {
		RegularExpression regx1 = new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.customerClassID", false);
		RegularExpression regx2 = new RegularExpression(
				"HFOrderSearchCriteria-\\d+\\.orderNumber", false);
		boolean flag1 = browser.checkHtmlObjectExists(".class", "Html.SELECT",
				".id", regx1);
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", regx2);
		
		//James[20130710] change from && to || as Vendor search may not exist in SK License home page
		//change back to &&, as it will confused with customer search page, change to use customer search and order search drop down combine as page mark
		return flag1 && flag2;
	}

	public void clickPurchasePrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Purchase (Privilege|Licence)", false), true);
	}

	public void clickPrivilegeQuickSale() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Privilege|Licence) Quick Sale", false), true);
	}
	
	public void clickPurchaseConsumables() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Purchase Consumables");
	}

	public void clickPurchaseInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Purchase Inventory");
	}
	
	public void clickVoidPrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void Privilege");
	}

	public void clickUndoVoidPrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Undo Void Privilege");
	}

	public void clickDuplicatePrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Duplicate Privilege");
	}

	public void clickViewUnreturnedDocs() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"View Unreturned Docs");
	}

	/**
	 * set customer first name
	 * 
	 * @param fname
	 * @Return void
	 * @Throws
	 */
	public void setCustomerFirstName(String fname) {
		RegularExpression regx = new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.firstName", false);
		browser.setTextField(".id", regx, fname);
	}

	/**
	 * set customer last name
	 * 
	 * @param fname
	 * @Return void
	 * @Throws
	 */
	public void setCustomerLastName(String lname) {
		RegularExpression regx = new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.lastName", false);
		browser.setTextField(".id", regx, lname);
	}

	/**
	 * Select the search target in quick orders search area
	 * 
	 * @param option
	 */
	public void selectOrderSearchFor(String option) {
		browser.selectDropdownList(".id", new RegularExpression(
				"HFOrderSearchCriteria-\\d+\\.searchTypeID", false), option);
	}

	/**
	 * Set the order number into the Order# text field in quick orders search
	 * area
	 * 
	 * @param ordNum
	 */
	public void setOrderNumber(String ordNum) {
		browser.setTextField(".id", new RegularExpression(
				"HFOrderSearchCriteria-\\d+\\.orderNumber", false), ordNum,
				true);
	}

	/**
	 * Set the receipt number into the Receipt# text field in quick orders
	 * search area
	 * 
	 * @param receiptNum
	 */
	public void setReceiptNumber(String receiptNum) {
		browser.setTextField(".id", new RegularExpression(
				"HFOrderSearchCriteria-\\d+\\.receiptNumber", false),
				receiptNum, true);
	}

	/**
	 * Click the 'Search' button in quick Orders search area
	 */
	public void clickSearchInOrders() {
		clickSearchButton("orders");
	}

	public void clickGoInVehicles() {
		// Property[] p1=Property.toPropertyArray(".class", "Html.TABLE",
		// ".text", new RegularExpression("^Vehicles.*", false));
		// Property[] p2=Property.toPropertyArray(".class", "Html.A", ".text",
		// "Go");
		// browser.clickGuiObject(Property.atList(p1,p2), true, 0);
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	/**
	 * Click the 'Advanced Search' link in quick Orders search area
	 */
	public void clickAdvancedSearchInOrders() {
		clickAdvancedSearchLink("orders");
	}

	/**
	 * Click the 'Search' button in each DIV area identifying by the DIV id
	 * 
	 * @param divID
	 */
	private void clickSearchButton(String divID) {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				divID.trim().toLowerCase());
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text",
				"Search");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	/**
	 * Click the 'Advanced Search' link in each DIV area identifying by the DIV
	 * id
	 * 
	 * @param divID
	 */
	private void clickAdvancedSearchLink(String divID) {
		IHtmlObject tops[] = browser.getHtmlObject(".class", "Html.DIV", ".id",
				divID.trim().toLowerCase());
		if (tops.length > 0) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					"Advanced Search", true, 0, tops[0]);
		} else {
			throw new ItemNotFoundException(
					"Can't find the Advanced Search link object.");
		}

		Browser.unregister(tops);
	}

	/**
	 * Click the 'Privilege Orders' link in quick Orders search area
	 */
	public void clickTodayPrivilegeOrders() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Privilege Orders",
				true);
	}

	/**
	 * Click the 'Vehicle Orders' link in quick Orders search area
	 */
	public void clickTodayVehicleOrders() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Vehicle Orders",
				true);
	}

	/**
	 * Click the 'Search' button in quick Customers search area
	 */
	public void clickSearchInCustomers() {
		clickSearchButton("customers");
	}

	/**
	 * Click the 'Advanced Search' link in quick Customers search area
	 */
	public void clickAdvancedSearchInCustomers() {
		clickAdvancedSearchLink("customers");
	}

	/**
	 * Click the 'Search' button in quick Vendors&STOREs search area
	 */
	public void clickSearchInVendorsStores() {
		clickSearchButton("vendors");
	}

	/**
	 * Click the 'Advanced Search' link in quick Vendors&STOREs search area
	 */
	public void clickAdvancedSearchInVendorsStores() {
		clickAdvancedSearchLink("vendors");
	}

	/**
	 * Click the 'Search' button in quick Vehicles search area
	 */
	public void clickSearchInVehicles() {
		clickSearchButton("vehicles");
	}

	/**
	 * Click the 'Advanced Search' link in quick Vehicles search area
	 */
	public void clickAdvancedSearchInVehicles() {
		clickAdvancedSearchLink("vehicles");
	}

	public void selectCustomerClass(String custClass) {
		browser.selectDropdownList(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.customerClassID", false),
				custClass);
	}

	public List<String> getCustomerClass() {
		return browser.getDropdownElements(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.customerClassID", false));

	}

	public void selectIdentifierType(String identifierType) {
		browser.selectDropdownList(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.searchBy", false),
				identifierType);
	}

	public void setIdentifierNum(String identifier) {
		browser.setTextField(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.searchByValue", false),
				identifier);
	}
	
	public void setZipCode(String zip) {
		browser.setTextField(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.zipCode", false), zip);
	}
	
	public void setLastName(String lName){
		browser.setTextField(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.lastName", false), lName);
	}
	
	public void setFirstName(String fName){
		browser.setTextField(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.firstName", false), fName);
	}

	public void setDateOfBirth(String dateOfBirth) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"HFCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay",
						false), dateOfBirth);
	}

	public void setBusinessName(String businessName) {
		browser.setTextField(".id", new RegularExpression(
				"HFCustomerSearchCriteria-\\d+\\.businessName", false),
				businessName);
	}

	/**
	 * Select Vendor or Store of search for drop down list in the 'Vendors &
	 * Stores' quick search area
	 * 
	 * @param value
	 */
	public void selectVendorOrStore(String value) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VendorSearchCriteria-\\d+\\.homeSearchTypeID", false), value);
	}

	/**
	 * Set store id or vendor number into the 'ID/#' field in 'Vendors & Stores'
	 * quick search area
	 * 
	 * @param number
	 */
	public void setStoreIDOrVendorNum(String number) {
		browser.setTextField(".id", new RegularExpression(
				"VendorSearchCriteria-\\d+\\.homeIdNumber", false), number);
	}

	/**
	 * Set name value in the 'Vendors & Stores' quick search area
	 * 
	 * @param name
	 */
	public void setVendorName(String name) {
		browser.setTextField(".id", new RegularExpression(
				"VendorSearchCriteria-\\d+\\.homeName", false), name);
	}

	public void setSearchCustomerCriteria(Customer cust){
		this.selectCustomerClass(cust.customerClass);
		ajax.waitLoading();
		this.selectIdentifierType(cust.identifier.identifierType);
		this.setIdentifierNum(cust.identifier.identifierNum);
		this.setZipCode(cust.physicalAddr.zip);
		this.setLastName(cust.lName);
		this.setFirstName(cust.fName);
		this.setDateOfBirth(cust.dateOfBirth);
		this.setBusinessName(cust.businessName);
	}

	/**
	 * Search vendor by vendor number from quick search area, and this method
	 * ends with vendor details page
	 * 
	 * @param vendorNum
	 */
	public void searchVendorByVendorNum(String vendorNum) {
		selectVendorOrStore("Vendor");
		setStoreIDOrVendorNum(vendorNum);
		clickSearchInVendorsStores();
		ajax.waitLoading();
	}

	/**
	 * Search vendor by vendor name from quick search area, but this method ends
	 * with vendor search/list page
	 * 
	 * @param vendorName
	 */
	public void searchVendorByVendorName(String vendorName) {
		selectVendorOrStore("Vendor");
		setVendorName(vendorName);
		clickSearchInVendorsStores();
		ajax.waitLoading();
	}

	public void selectNewVehicleType(String type) {
		String option = retrieveMatchedOption(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue|VehicleSearchCriteria-\\d+\\.vehicleTypeIDForNewVehicle",
				false), type+".*");
		browser.selectDropdownList(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue|VehicleSearchCriteria-\\d+\\.vehicleTypeIDForNewVehicle",
				false), option);
	}
	/**
	 * set customer number for quick renewal.
	 * @param cusomerNum
	 */
	public void setCustomerMDWFPforQuickRenewal(String cusomerNum){
		browser.setTextField(".id", "custNumber", cusomerNum);
	}
	
	public void setMIOrIDNumberForQuickRenewal(String miOrIDNum){
		browser.setTextField(".id", "vehicleNumber", miOrIDNum);
	}
	/**
	 * click quick renewal.
	 */
	public void clickQuickRenewal(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Quick Renewal");
	}
	
	public void clickRequestInspection(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Inspection");
	}
	
	
	/**
	 * Select the option 'Consumable' in 'Order Search For' list and set the order num
	 * 
	 * @author Lesley Wang
	 * @date Jun 4, 2012
	 */
	public void setConsumableOrderNum(String consumOrdNum) {
		this.selectOrderSearchFor("Consumable");
		this.setOrderNumber(consumOrdNum);
	}
	
	/**
	 * Click Quick Renewal link under Vehicles section.
	 * 
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void clickVehicleQuickRenewal() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "vehicles");
		browser.clickGuiObject(".class", "Html.A", ".text", "Quick Renewal", false, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean getBulletinByHeadLine(String headLine){
		IHtmlObject[] objs = browser.getTableTestObject(".text", headLine);
		if(objs.length < 1){
			logger.info("Bulletin with given headline("+headLine+") doesn't exist.");
			return false;
		} else {
			logger.info("Bulletin with given headline("+headLine+") exist.");
			return true;
		}
	}
	
	/**
	 * Private method to check the customer class radio button
	 * @param index
	 */
	private void checkCustomerClassRadioButton(int index) {
		if(index < 0) {
			index = 0;
		}
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), index);
	}
	
	/**
	 * Check the individual customer class radio button
	 */
	public void checkIndividualCustomerClass() {
		this.checkCustomerClassRadioButton(0);
	}
	
	/**
	 * Check the commercial customer class radio button
	 */
	public void checkCommercialCustomerClass() {
		this.checkCustomerClassRadioButton(1);
	}
	
	/**
	 * Check the trapper customer class radio button
	 */
	public void checkTrapperCustomerClass() {
		this.checkCustomerClassRadioButton(2);
	}
	
	/**
	 * Set the date of birth text field
	 * @param birthDate
	 */
	public void setDateOfBirthInPrivilegeSection(String birthDate) {
		RegularExpression idPattern=new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay", false);
		browser.setTextField(Property.toPropertyArray(".id", idPattern), birthDate, true, 0, (IHtmlObject)null, IText.Event.LOSEFOCUS);
	}
	
	/**
	 * Select the identifier type
	 * @param type
	 */
	public void selectIdentifierTypeInPrivilegeSection(String type) {
		//browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierType", false), type, true);
		browser.selectDropdownList(".id", new RegularExpression("(IdentifyCustomerSearchCriteria-\\d+\\.identifierType|IdentifyCustomerPage-\\d+\\.selectedIdentifierType)", false), type, true);
	}
	
	/**
	 * Set the identifier number
	 * @param identifierNum
	 */
	public void setIdentifierNumberInPrivilegeSection(String identifierNum) {
		browser.setTextField(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierNumber", false), identifierNum, true);
	}
	
	public boolean checkIdentifyCustomerAreaExistInPrevilegeSection(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierNumber", false));
	}
	/**
	 * check country whether exists
	 * @return
	 */
	public boolean isIdentifierCountryExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.country", false));
	}
	
	/**
	 * Check identifier state exists or not
	 * @return
	 */
	public boolean isIdentifierStateExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.state", false));
	}
	
	/**
	 * Select the customer's country
	 * @param country
	 */
	public void selectCustomerIdentifierCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.country", false), country, true);
	}
	
	/**
	 * Select the customer identifier state
	 * @param state
	 */
	public void selectCustomerIdentifierState(String state) {
		browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.state", false), state, true);
	}
	
	public void identifyCustomer(String customerClass, String dateOfBirth, String identifierType, String identifierNum,String identifierCountry,String identifierState) {
		if(customerClass.equalsIgnoreCase("Individual")) {
			checkIndividualCustomerClass();
		} else if(customerClass.equalsIgnoreCase("Business") || customerClass.equalsIgnoreCase("Outfitter")) {
			checkCommercialCustomerClass();
		} else if(customerClass.equalsIgnoreCase("Trapper")) {
			checkTrapperCustomerClass();
		} else {
			throw new ItemNotFoundException("Cann't find the Customer Class type - " + customerClass);
		}
		ajax.waitLoading();
		
		if(!StringUtil.isEmpty(dateOfBirth)) {
			setDateOfBirthInPrivilegeSection(dateOfBirth);
		}
		
		if(!StringUtil.isEmpty(identifierType)) {
			selectIdentifierTypeInPrivilegeSection(identifierType);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		setIdentifierNumberInPrivilegeSection(identifierNum);
		if( this.isIdentifierCountryExists() && !StringUtil.isEmpty(identifierCountry)) {
			selectCustomerIdentifierCountry(identifierCountry);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if(this.isIdentifierStateExists() && !StringUtil.isEmpty(identifierState)) {
			selectCustomerIdentifierState(identifierState);
			ajax.waitLoading();
			this.waitLoading();
		}
		//Jane[2014-4-22]:Comments below lines
//		this.clickPurchasePrivilege();
//		ajax.waitLoading();
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET", ".className", "message msgerror");
	}
	
	public boolean verifyDateOfBirthTextFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay", false))[0], invalidDates);
	}
	
	public boolean checkPrivilegeQuickSaleBtnExisted() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", new RegularExpression("(Privilege|Licence) Quick Sale", false));
	}
	
	public void setActivityRegistrtionOrderNum(String regOrdNum) {
		this.selectOrderSearchFor("Activity Registrtion");
		this.setOrderNumber(regOrdNum);
	}
	
}
