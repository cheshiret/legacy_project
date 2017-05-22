package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @Date  Mar 11, 2014
 */
public class OrmsReceiptSearchPage extends OrmsPage {
	private static OrmsReceiptSearchPage _instance = null;
	
	private OrmsReceiptSearchPage() {}
	
	public static OrmsReceiptSearchPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsReceiptSearchPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(receiptNum());
	}
	
	private Property[] receiptNum() {
		return Property.toPropertyArray(".id", "receiptNumber");
	}
	
	private Property[] orderNum() {
		return Property.toPropertyArray(".id", "reservationNumber");
	}
	
	private Property[] receiptContains() {
		return Property.toPropertyArray(".id", "receiptContains");
	}
	
	private Property[] receiptFromDate() {
		return Property.toPropertyArray(".id", "receiptStartDate_ForDisplay");
	}
	
	private Property[] receiptToDate() {
		return Property.toPropertyArray(".id", "receiptEndDate_ForDisplay");
	}
	
	private Property[] customerPhone() {
		return Property.toPropertyArray(".id", "phone");
	}
	
	private Property[] includeAreaCode() {
		return Property.toPropertyArray(".id", "includeAreaCode");
	}
	
	private Property[] lastName() {
		return Property.toPropertyArray(".id", "lastName");
	}
	
	private Property[] firstName() {
		return Property.toPropertyArray(".id", "firstName");
	}
	
	private Property[] emailAddress() {
		return Property.toPropertyArray(".id", "email");
	}
	
	private Property[] advancedSearch() {
		return Property.toPropertyArray(".class","Html.A",".text", "Advanced Search");
	}
	
	private Property[] advancedSearchTable(){
		return Property.addToPropertyArray(table(), ".className", "advancedSearch");
	}
	
	private Property[] eventID(){
		return Property.toPropertyArray("id","eventId");
	}
	
	private Property[] eventName(){
		return Property.toPropertyArray("id","eventName");
	}
	
	private Property[] location() {
		return Property.toPropertyArray(".id", "receiptLocation");
	}
	
	private Property[] salesStation() {
		return Property.toPropertyArray(".id", "receiptStation");
	}
	
	private Property[] salesRegisterID() {
		return Property.toPropertyArray(".id", "registryID");
	}
	
	private Property[] createdBy() {
		return Property.toPropertyArray(".id", "createBy");
	}
	
	private Property[] barCode() {
		return Property.toPropertyArray(".id", "barCode");
	}
	
	private Property[] privilegeNumber() {
		return Property.toPropertyArray(".id", "privilegeNumId");
	}
	
	private Property[] vehicleNumber() {
		return Property.toPropertyArray(".id", "vehicleNumoridId");
	}
	
	private Property[] activityRegistrationNumber() {
		return Property.toPropertyArray(".id", "activityRegNumId");
	}
	
	private Property[] paymentType() {
		return Property.toPropertyArray(".id", "paymentType");
	}
	
	private Property[] checkNumber() {
		return Property.toPropertyArray(".id", "checkNumber");
	}
	
	private Property[] totalReceiptPriceGreaterThan() {
		return Property.toPropertyArray(".id", "startPrice");
	}
	
	private Property[] totalReceiptPriceLessThan() {
		return Property.toPropertyArray(".id", "endPrice");
	}
	
	private Property[] search() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
	}
	
	private Property[] receiptListTable() {
		return Property.toPropertyArray(".className", "searchResult");
	}
	
	private Property[] receiptNumLink(String receiptNum) {
		return Property.toPropertyArray(".class", "Html.A", ".text", receiptNum);
	}
	
	public void setReceiptNum(String receiptNum){
		browser.setTextField(receiptNum(), receiptNum);
	}
	
	public void setOrderNum(String ordNum) {
		browser.setTextField(orderNum(), ordNum);
	}
	
	public void selectReceiptContains(String contains) {
		browser.selectDropdownList(receiptContains(), contains);
	}
	
	public List<String> getElementsForReceiptContains(){
		return browser.getDropdownElements(receiptContains());
	}
	
	public void setReceiptFromDate(String fromDate) {
		browser.setCalendarField(receiptFromDate(), fromDate);
	}
	
	public void setReceiptToDate(String toDate) {
		browser.setCalendarField(receiptToDate(), toDate);
	}
	
	public void setCustomerPhone(String phone) {
		browser.setTextField(customerPhone(), phone);
	}
	public void setCustomerFirstName(String fName) {
		browser.setTextField(firstName(), fName);
	}
	public void setCustomerLastName(String lName) {
		browser.setTextField(lastName(), lName);
	}
	public void selectIncludeAreaCode() {
		browser.selectCheckBox(includeAreaCode());
	}
	
	public void unselectIncludeAreaCode() {
		browser.unSelectCheckBox(includeAreaCode());
	}
	
	public void setLastName(String lName) {
		browser.setTextField(lastName(), lName);
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(firstName(), fName);
	}
	
	public void setEmailAddress(String email) {
		browser.setTextField(emailAddress(), email);
	}
	
	public void clickAdvancedSearch() {
		IHtmlObject[] objs = browser.getHtmlObject(this.advancedSearchTable());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any advanced search table object.");
		}
		browser.clickGuiObject(advancedSearch(),true,0,objs[objs.length-1]);
		Browser.unregister(objs);
	}
	
	public void setEventID(String eventID){
		browser.setTextField(eventID(), eventID);
	}
	
	public void setEventName(String eventName){
		browser.setTextField(eventName(), eventName);
	}
	
	public void setLocation(String location) {
		browser.setTextField(location(), location);
	}
	
	public boolean isLocationIsDisplay(){
		return browser.checkHtmlObjectDisplayed(location());
	}
	
	
	public void setSalesStation(String station) {
		browser.setTextField(salesStation(), station);
	}
	
	public void setSalesReigisterID(String registerID) {
		browser.setTextField(salesRegisterID(), registerID);
	}

	public void setCreatedBy(String by) {
		browser.setTextField(createdBy(), by);
	}
	
	public void setBarcode(String barCode){
		browser.setTextField(barCode(), barCode);
	}
	
	public void setPrivilegeNumber(String privNum) {
		browser.setTextField(privilegeNumber(), privNum);
	}
	
	public void setVehicleNumberID(String vehicleNum) {
		browser.setTextField(vehicleNumber(), vehicleNum);
	}
	
	public void setActivityRegistrationNumber(String registrationNum) {
		browser.setTextField(activityRegistrationNumber(), registrationNum);
	}
	
	public void selectPaymentType(String paymentType) {
		browser.selectDropdownList(paymentType(), paymentType);
	}
	
	public void setCheckNumber(String checkNum) {
		browser.setTextField(checkNumber(), checkNum);
	}
	
	public void setTotalReceiptPriceGreaterThan(String startPrice) {
		browser.setTextField(totalReceiptPriceGreaterThan(), startPrice);
	}
	
	public void setTotalReceiptPriceLessThan(String endPrice) {
		browser.setTextField(totalReceiptPriceLessThan(), endPrice);
	}
	
	public void clickSearch(){
		IHtmlObject objs[] = this.getTransactionFrame();
		browser.clickGuiObject(search(), true, 0, (objs != null && objs.length > 0) ? objs[0] : null);
	}
	
	private IHtmlObject[] getReceiptTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(receiptListTable());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find receipt list table object.");
		}
		
		return objs;
	}
	
	public void searchByReceiptNum(String receiptNum){
		this.setReceiptNum(receiptNum);
		this.clickSearch();
		this.waitLoading();
	}
	
	public void searchByOrderNum(String ordNum) {
		this.setOrderNum(ordNum);
		this.clickSearch();
		this.waitLoading();
	}
	
	public void clickReceiptNumLink(String receiptNum) {
		browser.clickGuiObject(receiptNumLink(receiptNum));
	}
	
	public List<String> getSearchResultInfo(){
		IHtmlObject[] objs = this.getReceiptTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> result = table.getRowValues(1);
		Browser.unregister(objs);
		return result;
	}
	
	public void setupSearchReceiptInfo(ReceiptInfo receiptInfo){
		if(StringUtil.notEmpty(receiptInfo.id)){
			this.setReceiptNum(receiptInfo.id);
		}
		
		if(StringUtil.notEmpty(receiptInfo.searchOrderNum)){
			this.setOrderNum(receiptInfo.searchOrderNum);
		}
		
		if(StringUtil.notEmpty(receiptInfo.receiptContains)){
			this.selectReceiptContains(receiptInfo.receiptContains);
		}
		
		if(StringUtil.notEmpty(receiptInfo.fromDate)){
			this.setReceiptFromDate(receiptInfo.fromDate);
		}
		
		if(StringUtil.notEmpty(receiptInfo.toDate)){
			this.setReceiptToDate(receiptInfo.toDate);
		}
		
		if(StringUtil.notEmpty(receiptInfo.custPhone)){
			this.setCustomerPhone(receiptInfo.custPhone);
		}
		
		if(StringUtil.notEmpty(receiptInfo.custLastName)){
			this.setLastName(receiptInfo.custLastName);
		}
		
		if(StringUtil.notEmpty(receiptInfo.custFirstName)){
			this.setFirstName(receiptInfo.custFirstName);
		}
		
		if(StringUtil.notEmpty(receiptInfo.custEmail)){
			this.setEmailAddress(receiptInfo.custEmail);
		}
		
		if(receiptInfo.isAdvancedSearch){
			if(!isLocationIsDisplay()){
				this.clickAdvancedSearch();
				ajax.waitLoading();
			}
			
			if(StringUtil.notEmpty(receiptInfo.eventID)){
				this.setEventID(receiptInfo.eventID);
			}
			
			if(StringUtil.notEmpty(receiptInfo.eventName)){
				this.setEventName(receiptInfo.eventName);
			}
			
			if(StringUtil.notEmpty(receiptInfo.salesLocation)){
				this.setLocation(receiptInfo.salesLocation);
			}
			
			if(StringUtil.notEmpty(receiptInfo.salesStation)){
				this.setSalesStation(receiptInfo.salesStation);
			}
			
			if(StringUtil.notEmpty(receiptInfo.salesRegisterID)){
				this.setSalesReigisterID(receiptInfo.salesRegisterID);
			}
			
			if(StringUtil.notEmpty(receiptInfo.createdBy)){
				this.setCreatedBy(receiptInfo.createdBy);
			}
			
			if(StringUtil.notEmpty(receiptInfo.barcode)){
				this.setBarcode(receiptInfo.barcode);
			}
			
			if(StringUtil.notEmpty(receiptInfo.privilegeNum)){
				this.setPrivilegeNumber(receiptInfo.privilegeNum);
			}
			
			if(StringUtil.notEmpty(receiptInfo.vehicleNum)){
				this.setVehicleNumberID(receiptInfo.vehicleNum);
			}
			
			if(StringUtil.notEmpty(receiptInfo.activityRegistrationNum)){
				this.setActivityRegistrationNumber(receiptInfo.activityRegistrationNum);
			}
			
			if(StringUtil.notEmpty(receiptInfo.paymentType)){
				this.selectPaymentType(receiptInfo.paymentType);
			}
			
			if(StringUtil.notEmpty(receiptInfo.checkNumber)){
				this.setCheckNumber(receiptInfo.checkNumber);
			}
			
			if(StringUtil.notEmpty(receiptInfo.totalReceiptStartPrice)){
				this.setTotalReceiptPriceGreaterThan(receiptInfo.totalReceiptStartPrice);
			}
			
			if(StringUtil.notEmpty(receiptInfo.totalReceiptEndPrice)){
				this.setTotalReceiptPriceLessThan(receiptInfo.totalReceiptEndPrice);
			}
			
		}else{
			if(isLocationIsDisplay()){
				this.clickAdvancedSearch();
				ajax.waitLoading();
			}
		}
		
	}
	
	public void searchByReceiptInfo(ReceiptInfo receiptInfo){
		this.setupSearchReceiptInfo(receiptInfo);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private List<String> getColumnListValue(String columnName){
		IHtmlObject[] objs = this.getReceiptTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		
		int col = table.findColumn(0, columnName);
		List<String> columnListValue = table.getColumnValues(col);
		columnListValue.remove(0);
		
		Browser.unregister(objs);
		return columnListValue;
	}
	
	public List<String> getReceiptNumberColumnListValue(){
		return this.getColumnListValue("Receipt #");
	}
	
	public List<String> getOrderNumberColumnListValue(){
		return this.getColumnListValue("Orders");
	}
	
	public List<String> getReceiptDateTimeColumnListValue(){
		return this.getColumnListValue("Receipt Date & Time");
	}
	
	public List<String> getCustomerColumnListValue(){
		return this.getColumnListValue("Customer");
	}
	
	public List<String> getCustomerFirstColumnListValue(){
		List<String> customer = this.getColumnListValue("Customer");
		List<String> fistNames = new ArrayList<String>();
		for(int i=0;i<customer.size();i++){
			String firstName = customer.get(i).split(",")[1].trim();
			fistNames.add(firstName);
		}
		return fistNames;
	}
	
	public List<String> getCustomerLastColumnListValue(){
		List<String> customer = this.getColumnListValue("Customer");
		List<String> lastNames = new ArrayList<String>();
		for(int i=0;i<customer.size();i++){
			String lastName = customer.get(i).split(",")[0].trim();
			lastNames.add(lastName);
		}
		return lastNames;
	}
	
	public List<String> getLocationColumnListValue(){
		return this.getColumnListValue("Location");
	}
	
	public List<String> getTotalReceiptPriceColumnListValue(){
		return this.getColumnListValue("Total Receipt Price");
	}
    public String getOrderNum(){
    	return browser.getTextFieldValue(orderNum());
    }
	public void verifyReceiptInfoIsExisting(String receiptNum, boolean expIsExisting){
		logger.info("Verify receipt info whether is existing. Receipt Number = " + receiptNum);
		List<String> actReceiptNumList = this.getReceiptNumberColumnListValue();
		boolean isExisting = actReceiptNumList.contains(receiptNum);
		boolean result = MiscFunctions.compareResult("Receipt info existing info", expIsExisting, isExisting);
		if(!result){
			throw new ErrorOnDataException("Receipt info existing info not correct, please check.");
		}else logger.info("Receipt info existing info is correct.");
		
	}
	
	
    public IHtmlObject[] getReceiptNumLinkInSearchResultTable(String receiptNum) {
    	logger.info("Get Receipt Number "+receiptNum+"in search result table");

    	IHtmlObject []objs = browser.getHtmlObject(".className","searchResult");
    	if(null==objs||objs.length<=0){
    		throw new ErrorOnPageException("Could not found search result table");
    	}
    	IHtmlObject []tbodys = browser.getHtmlObject(".class","Html.TBODY",objs[objs.length-1]);
    	if(null==tbodys||tbodys.length<=0){
    		throw new ErrorOnPageException("Could not found search result table body");
    	}
    	IHtmlObject []receipRows = browser.getHtmlObject(".text",new RegularExpression(receiptNum,false));
    	if(null==receipRows||receipRows.length<=0){
    		throw new ErrorOnPageException("Receipt Number could not found in the search result table");
     	}
    	IHtmlObject [] receiptLinks =  browser.getHtmlObject(a(),receipRows[receipRows.length-1]);
    	Browser.unregister(objs);
    	Browser.unregister(tbodys);
    	Browser.unregister(receipRows);
    	
    	return receiptLinks;
    }
    public void clickReceipNumLink(String receiptNum){
    	logger.info("Click Receipt Number "+receiptNum+"in search result table");

    	IHtmlObject[] receiptLinks = getReceiptNumLinkInSearchResultTable(receiptNum);
    	if(null==receiptLinks||receiptLinks.length<=0){
    		throw new ErrorOnPageException("Receipt Number link could not found in the search result table");
     	}
    	receiptLinks[receiptLinks.length-1].click();
    }
    public void verifyOrderNumFilledIn(String expOrderNum){
    	logger.info("Verify orderNum "+expOrderNum+" filled in");
    	String actualOrderNum = getOrderNum();
    	boolean result = MiscFunctions.compareResult("Order Number filled in", expOrderNum, actualOrderNum);
    	if(!result){
    		throw new ErrorOnDataException("Order number is not filled in correctly");
    	}
    }
    //added by Summer
    public void searchByReceiptContainsInOrderNum(String orderNum,String containsIn){
    	logger.info("Search receipt contains in orderNum "+orderNum);
    	this.selectReceiptContains(containsIn);
    	this.setOrderNum(orderNum);
    	this.clickSearch();
    	ajax.waitLoading();
    }
    
    //added by Summer
    public void searchByCustomer(Customer cust,String phoneType){
    	logger.info("Search by customer"+cust.fName);
    	if(phoneType.equalsIgnoreCase("Home Phone")){
    		this.setCustomerPhone(cust.hPhone);
    	} else if(phoneType.equalsIgnoreCase("Mobile Phone")){
    		this.setCustomerPhone(cust.mPhone);
    	} else if(phoneType.equalsIgnoreCase("Business Phone")){
    		this.setCustomerPhone(cust.bPhone);
    	} else{
    		throw new ErrorOnDataException("Unknown phone type");
    	}
    	ajax.waitLoading();
    	if(StringUtil.notEmpty(cust.fName)){
    		this.setFirstName(cust.fName);
    		ajax.waitLoading();
    	}
    	
    	if(StringUtil.notEmpty(cust.lName)){
    		this.setLastName(cust.lName);
    		ajax.waitLoading();
    	}
    	if(StringUtil.notEmpty(cust.email)){
    		this.setEmailAddress(cust.email);
    		ajax.waitLoading();
    	}
    	this.clickSearch();
    	ajax.waitLoading();
    	this.waitLoading();
    }
    public void verifyReceipNumLinkExist(String receiptNum){
    	logger.info("Check Receipt Number "+receiptNum+"in search result table");

    	boolean result = browser.checkHtmlObjectDisplayed(".class","Html.A",".text",receiptNum);//getReceiptNumLinkInSearchResultTable(receiptNum);
    	if(!result){
    		throw new ErrorOnPageException("Receipt Number link could not found in the search result table");
     	}
    }
	
}
