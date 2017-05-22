package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.PassInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsCustomerPassesPage extends OrmsPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCustomerPassesPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCustomerPassesPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCustomerPassesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCustomerPassesPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		//		RegularExpression reg=new RegularExpression(".* (View/Update Customer Details|Add Customer)$",false);
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
//				"Add Pass");
		return browser.checkHtmlObjectExists(passNumber());
	}
	
	private Property[] passNumber() {
		return Property.toPropertyArray(".id", "CustomerPassSearchCriteria.passNumber");
	}
	
	/**
	 * Click the Add Pass
	 */
	public void clickAddPasses(){
		browser.clickGuiObject(".class", "Html.A", ".text","Add Pass");
	}
	
	/**
	 * Check Pass number exist
	 */
	public boolean checkPassNumberExist(String passNum){
		return browser.checkHtmlObjectExists(".class","Html.A",".text",passNum);
	}
	
	/**
	 * Click the pass number
	 * @param passNum
	 */
	public void clickPassNumber(String passNum){
		browser.clickGuiObject(".class","Html.A",".text",passNum);
	}
	
	public void setPassNumber(String pasNum){
		browser.setTextField(".id", "CustomerPassSearchCriteria.passNumber", pasNum);
	}
	
	public void setOrderDetailSearchValue(String value){
		browser.setTextField(".id", "CustomerPassSearchCriteria.attributeSearchValue", value);
	}
	
	public void selectFulfillment(String fulfillment){
		browser.selectDropdownList(".id", "CustomerPassSearchCriteria.fulfillment", fulfillment);
	}
	
	public void selectPassStatus(String status){
		if(!StringUtil.isEmpty(status)) {
			browser.selectDropdownList(".id", "CustomerPassSearchCriteria.passStatus", status);
		} else {
			browser.selectDropdownList(".id", "CustomerPassSearchCriteria.passStatus", 0);
		}
	}
	
	public void selectOrderDetailSearchType(String type){
		browser.selectDropdownList(".id", "CustomerPassSearchCriteria.searchType", type);
	}
	public void selectPassName(String passName){
		browser.selectDropdownList(".id", "CustomerPassSearchCriteria.passType", passName);
	}
	
	public void setPassID(String passID){
		browser.setTextField(".id", "CustomerPassSearchCriteria.passID", passID);
	}
	
	public void setPassFileID(String fileID){
		browser.setTextField(".id", "CustomerPassSearchCriteria.passFileID", fileID);
	}
	
	public void selectDateType(String type){
		browser.selectDropdownList(".id", "CustomerPassSearchCriteria.dateType", type);
	}
	
	public void setFromDate(String fDate){
		browser.setTextField(".id", "CustomerPassSearchCriteria.fromDate_ForDisplay", fDate);
	}
	
	public void setToDate(String tDate){
		browser.setTextField(".id", "CustomerPassSearchCriteria.toDate_ForDisplay", tDate);
	}
	
	public void setUserName(String name){
		browser.setTextField(".id", "CustomerPassSearchCriteria.userName", name);
	}
	
	private Property[] emailAddress() {
		return Property.toPropertyArray(".id", "CustomerPassSearchCriteria.email");
	}
	
	public void setEmailAddress(String email) {
		browser.setTextField(emailAddress(), email);
	}
	
	public void setupSearchCriteria(Customer cust){
		setPassNumber(cust.passNumber);
		selectPassStatus(cust.passStatus);
		selectPassName(cust.pass);
		setPassID(cust.passID);
		setPassFileID(cust.passFileID);
		//Shane[20131121] comments below code as there are no Order detail search type and search value components in 3.05.00.177 build
//		selectOrderDetailSearchType(cust.searchType);
//		setOrderDetailSearchValue(cust.searchValue);
		selectFulfillment(cust.fulfillStatus);
		selectDateType(cust.passDateType);
		setFromDate(cust.passFromDate);
		setToDate(cust.passToDate);
		setUserName(cust.userName);
	}
	
	public void searchPassByPassNum(String passNum){
		setPassNumber(passNum);
		selectPassStatus(null);
		clickSearch();
		ajax.waitLoading();
	    waitLoading();
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Search$", false));
//		browser.clickGuiObject(".id","Search");
	}
	
	public boolean checkLast(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Last");
	}
	
	public void clickLast(){
		if(this.checkLast()){
			browser.clickGuiObject(".class","Html.A",".text","Last");
		}
	}
	
	public void clickDeactive(){
			browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	}
	
	public void selectPassByPassNum(String number){
		IHtmlObject[] objs=browser.getTableTestObject(".id","passesGrid_LIST");
		IHtmlTable ta=(IHtmlTable)objs[0];
		
		int row=ta.findRow(1, number);
		browser.selectCheckBox(".id","CustomerPassView.id", row-1);
		
	}
	
	public List<String> getPassInfo(String passCritera){
		IHtmlObject[] objs = this.getPassListTableObject();
		IHtmlTable ta=(IHtmlTable)objs[0];
		
		int statusCol=0;
		List<String> info=new ArrayList<String>();
		for(int i=0;i<ta.columnCount();i++){
			if(ta.getCellValue(0, i).equals(passCritera)){
				statusCol=i;
				break;
			}
		}
		
		for(int j=1;j<ta.rowCount();j++){
			info.add(ta.getCellValue(j, statusCol).toString());
		}
		
		return info;
	}

	private static final String PASS_NUMBER_COL = "Pass Number";
	private static final String PASS_STATUS_COL = "Pass Status";
	private static final String PASS_NAME_COL = "Pass Name";
	private static final String EXPIRY_DATE_COL = "Expiry Date";
	private static final String PURCHASE_TYPE_COL = "Purchase Type";
	private static final String FULFILLMENT_COL = "Fulfillment";
	private static final String PASS_DETAILS_COL = "Pass Details";
	private static final String CUSTOMER_COL = "Customer";
	private static final String PASS_HOLDER_COL = "Pass Holder";
	private static final String PROGRAM_NAME_COL = "Program Name";
	
	private IHtmlObject[] getPassListTableObject() {
		IHtmlObject[] objs = browser.getTableTestObject(".id","passesGrid_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find pass list table object.");
		}
		
		return objs;
	}
	
	public String getPassStatus(String passNum) {
		this.searchPassByPassNum(passNum);
		
		IHtmlObject[] objs = this.getPassListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, PASS_NUMBER_COL), passNum);
		int col = table.findColumn(0, PASS_STATUS_COL);
		String status = table.getCellValue(row, col);
		
		Browser.unregister(objs);
		return status;
	}
	
//	public String getPassNumber(String email, String status) {
//		this.setEmailAddress(email);
//		this.selectPassStatus(status);
//		this.clickSearch();
//		ajax.waitLoading();
//		this.waitLoading();
//		
//		IHtmlObject[] objs = this.getPassListTableObject();
//		IHtmlTable table = (IHtmlTable)objs[0];
//	}
	
	public String getPassFulfillmentStatus(String passNum) {
		IHtmlObject[] objs = this.getPassListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, PASS_NUMBER_COL), passNum);
		int col = table.findColumn(0, FULFILLMENT_COL);
		String status = table.getCellValue(row, col);
		
		Browser.unregister(objs);
		return status;
	}
	
	public PassInfo getCustPassInfo(String passNum) {
		this.searchPassByPassNum(passNum);
		
		IHtmlObject[] objs = this.getPassListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, PASS_NUMBER_COL), passNum);
		List<String> values = table.getRowValues(row);
		
		PassInfo pass = new Customer().new PassInfo();
		pass.setPassNumber(passNum);
		pass.setPassStatus(values.get(table.findColumn(0, PASS_STATUS_COL)));
		pass.setPassStatus(pass.getPassStatus().equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS) ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS);
		pass.setPassName(values.get(table.findColumn(0, PASS_NAME_COL)));
		pass.setExpiryDate(values.get(table.findColumn(0, EXPIRY_DATE_COL)));
		pass.setPurchaseType(values.get(table.findColumn(0, PURCHASE_TYPE_COL)));
		pass.setFulfillment(values.get(table.findColumn(0, FULFILLMENT_COL)));
		pass.setPassDetails(values.get(table.findColumn(0, PASS_DETAILS_COL)));
		pass.setCustomer(values.get(table.findColumn(0, CUSTOMER_COL)));
		pass.setPassHolder(values.get(table.findColumn(0, PASS_HOLDER_COL)));
		pass.setProgramName(values.get(table.findColumn(0, PROGRAM_NAME_COL)));
		
		Browser.unregister(objs);
		return pass;
	}
	
	public boolean comparePassInfo(PassInfo expected) {
		PassInfo actual = this.getCustPassInfo(expected.getPassNumber());
		
		boolean result = true;
		result &= MiscFunctions.compareResult(PASS_NUMBER_COL, expected.getPassNumber(), actual.getPassNumber());
		result &= MiscFunctions.compareResult(PASS_STATUS_COL, expected.getPassStatus(), actual.getPassStatus());
		result &= MiscFunctions.compareResult(PASS_NAME_COL, expected.getPassName(), actual.getPassName());
		result &= MiscFunctions.compareResult(EXPIRY_DATE_COL, expected.getExpiryDate(), actual.getExpiryDate());
		result &= MiscFunctions.compareResult(PURCHASE_TYPE_COL, expected.getPurchaseType(), actual.getPurchaseType());
		result &= MiscFunctions.compareResult(FULFILLMENT_COL, expected.getFulfillment(), actual.getFulfillment());
		result &= MiscFunctions.compareResult(PASS_DETAILS_COL, expected.getPassDetails(), actual.getPassDetails());
		result &= MiscFunctions.compareResult(CUSTOMER_COL, expected.getCustomer(), actual.getCustomer());
		result &= MiscFunctions.compareResult(PASS_HOLDER_COL, expected.getPassHolder(), actual.getPassHolder());
		result &= MiscFunctions.compareResult(PROGRAM_NAME_COL, expected.getProgramName(), actual.getProgramName());
		
		return result;
	}
	
	public void verifyPassInfo(PassInfo expected) {
		if(!comparePassInfo(expected)) throw new ErrorOnPageException("Customer Pass info is NOT correct.");
		logger.info("Customer Pass info is correct.");
	}
	
	private Property[] fulfilled() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Fulfilled");
	}
	
	private Property[] notFulfilled() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Not Fulfilled");
	}
	
	private Property[] printFulfillment() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Print Fulfillment");
	}
	
	public void clickFulfilled() {
		browser.clickGuiObject(fulfilled());
	}
	
	public void clickNotFulfilled() {
		browser.clickGuiObject(notFulfilled());
	}
	
	public void clickPrintFulfillment() {
		browser.clickGuiObject(printFulfillment());
	}
}
