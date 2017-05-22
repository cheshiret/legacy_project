package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsAddAndSearchListEntryPage extends OrmsPage{
	private static OrmsAddAndSearchListEntryPage _instance = null;

	private OrmsAddAndSearchListEntryPage() {}

	public static OrmsAddAndSearchListEntryPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsAddAndSearchListEntryPage();
		}

		return _instance;
	}
	
	private String prefix = "MarinaListEntrySearchCriteria-\\d+\\.";
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"entryNumber",false));
	}
	
	protected Property[] entryStatusDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "entryStatus",false));
	}
	protected Property[] facilityDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "searchFacilityId",false));
	}
	protected Property[] listNameDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "list",false));
	}
	protected Property[] listEntryTypeDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "entryType",false));
	}
	protected Property[] dockDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "dock",false));
	}
	protected Property[] slipDropDownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "slip",false));
	}
	
	protected Property[] okButton() {  
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("OK", false));
	}
	protected Property[] cancelButton(){
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	protected Property[] searchButton() {  
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	protected Property[] skipfulfillButton() {  
		return Property.toPropertyArray(".class", "Html.A", ".text", "Skip Fulfill");
	}
	protected Property[] addToListButton() {  
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add to List");
	}
	protected Property[] listEntryListTable(){
		return Property.toPropertyArray(".class", "Html.TABLE", ".id", new RegularExpression("grid_\\d+_LIST", false));
	}
	
	protected Property[] phoneNumberTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "phone", false));
	}

	protected Property[] lastNameTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "lastName", false));
	}

	protected Property[] firstNameTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "firstName", false));
	}

	protected Property[] emailTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "email", false));
	}

	protected Property[] orderStatusDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "orderStatus", false));
	}

	
	public boolean isObjEnabled(Property[] properties){
		return browser.checkHtmlObjectEnabled(properties);
	}
	
	public void verifySearchCriteriaStatus(boolean isEnable){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("List dropdown list", isEnable, this.isObjEnabled(this.listNameDropDownList()));
		passed &= MiscFunctions.compareResult("Dock dropdown list", isEnable, this.isObjEnabled(this.dockDropDownList()));
		passed &= MiscFunctions.compareResult("Slip dropdown list", isEnable, this.isObjEnabled(this.slipDropDownList()));
		if(!passed) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} 
		logger.info("All checkpoints are PASSED for search criterial drop down list status.");
	}
	
	
	
	public String getFacilityValueOfSearchCriteria(){
		return browser.getDropdownListValue(facilityDropDownList(), 0);
	}
	
	public List<String> getFacilityElementOfSearchCriteria(){
		return browser.getDropdownElements(this.facilityDropDownList());
	}
	
	public void selectFaility(String facility){
		if(StringUtil.isEmpty(facility)){
			browser.selectDropdownList(facilityDropDownList(), 0);
		} else {
			browser.selectDropdownList(facilityDropDownList(), facility);
		}
	}
	
	public String getListValueOfSearchCriteria(){
		return browser.getDropdownListValue(this.listNameDropDownList(), 0);
	}
	
	public List<String> getListElementOfSearchCriteria(){
		return browser.getDropdownElements(this.listNameDropDownList());
	}
	
	public void selectListName(String listName){
		if(StringUtil.isEmpty(listName)){
			browser.selectDropdownList(listNameDropDownList(), 0);
		} else {
			browser.selectDropdownList(listNameDropDownList(), listName);
		}
		
	}

	public void selectListType(String type){
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(listEntryTypeDropDownList(), 0);
		} else {
			browser.selectDropdownList(listEntryTypeDropDownList(), type);
		}
	}

	public void selectDockName(String dockName){
		if(StringUtil.isEmpty(dockName)){
			browser.selectDropdownList(dockDropDownList(), 0);
		} else {
			browser.selectDropdownList(dockDropDownList(), dockName);
		}
	}
	
	public void selectSlipName(String slipName){
		if(StringUtil.isEmpty(slipName)){
			browser.selectDropdownList(slipDropDownList(), 0);
		} else {
			browser.selectDropdownList(slipDropDownList(), slipName);
		}
	}
	
	public String getSlipValueOfSearchCriteria(){
		return browser.getDropdownListValue(this.slipDropDownList(), 0);
	}
	
	public List<String> getSlipElementOfSearchCriteria(){
		return browser.getDropdownElements(this.slipDropDownList());
	}
	
	public String getDockNameValueOfSearchCriteria(){
		return browser.getDropdownListValue(this.dockDropDownList(), 0);
	}
	
	public List<String> getDockNameElementOfSearchCriteria(){
		return browser.getDropdownElements(this.dockDropDownList());
	}
	
	public void selectEntryStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(entryStatusDropDownList(), status);
		}else{
			browser.selectDropdownList(entryStatusDropDownList(), 0);
		}
	}
	
	public void searchListEntry(ListInfo listInfo){
		this.setEntryNum(listInfo.getEntriesNum());
		this.setPhoneNumber(listInfo.getPhoneNumbers());
		this.setLastName(listInfo.getLastName());
		this.setFirstName(listInfo.getFirstName());
		this.setEmail(listInfo.getEmail());
		this.selectOrderStatus(listInfo.getOrderStatus());
		this.selectEntryStatus(listInfo.getEntryStatus());

		this.setAddListEntryInfo(listInfo);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setEntryNum(String entryNum){
		if(null != entryNum){
			browser.setTextField(".id", new RegularExpression(prefix +"entryNumber", false), entryNum, true);
		}
	}
	
	public void searchListEntryByEntryNum(String entryNum){
		this.selectEntryStatus(StringUtil.EMPTY);
		this.setEntryNum(entryNum);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setPhoneNumber(String phoneNum){
		if(null != phoneNum){
			browser.setTextField(phoneNumberTextField(), phoneNum);
		}
	}
	
	public void setLastName(String lastName){
		if(null != lastName){
			browser.setTextField(lastNameTextField(), lastName);
		}
	}
	
	public void setFirstName(String firstName){
		if(null != firstName){
			browser.setTextField(firstNameTextField(), firstName);
		}
	}
	
	public void setEmail(String email){
		if(null != email){
			browser.setTextField(emailTextField(), email);
		}
	}

	public void selectOrderStatus(String orderStatus){
		if(StringUtil.isEmpty(orderStatus)){
			browser.selectDropdownList(orderStatusDropdownList(), 0);
		} else {
			browser.selectDropdownList(orderStatusDropdownList(), orderStatus);
		}
	}
	
	public void setAddListEntryInfo(ListInfo listInfo){
		// TODO set other search criteria

		if(null != listInfo.getListName()){
			this.selectListName(listInfo.getListName());
			ajax.waitLoading();
		}

		if(null != listInfo.getListEntryType()){
			this.selectListType(listInfo.getListEntryType());
		}
		
		if(null != listInfo.getDock()){
			this.selectDockName(listInfo.getDock());
			ajax.waitLoading();
		}

		if(null != listInfo.getSlip()){
			this.selectSlipName(listInfo.getSlip());
		}
	}
	
	public void selectEntry(String entryNum)
	{
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+entryNum+".*",false));
		
		if(trs.length<1 )
		{
			PagingComponent pc=getPagingComponent();
			while(trs.length<1 && pc.clickNext()) {
				trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+entryNum+".*",false));			
			}
			if(trs.length<1)
				throw new ErrorOnPageException("Cannot find Entry #-->"+entryNum);
		}
		
		browser.selectRadioButton(Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false)),false, 0,trs[trs.length-1]);
		Browser.unregister(trs);
	}
	
	public void clickOK()
	{
		browser.clickGuiObject(okButton());
	}

	public void clickGo()
	{
		browser.clickGuiObject(searchButton());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancelButton());
	}
	
	public void clickAddToList(){
		browser.clickGuiObject(addToListButton(), true);
	}
	
	public void clickSkipFulfill(){
		browser.clickGuiObject(skipfulfillButton());
	}
	
	public boolean checkOKButtonExist(){
		return browser.checkHtmlObjectExists(okButton());
	}
	
	public boolean checkCancelButtonExist(){
		return browser.checkHtmlObjectExists(cancelButton());
	}
	
	public boolean checkSkipFulfillButtonExist(){
		return browser.checkHtmlObjectDisplayed(this.skipfulfillButton());
	}
	
	public boolean isAddToListEnabled(){
		return browser.checkHtmlObjectEnabled(this.addToListButton());
	}
	
	/**
	 * This method verify the 'ok','cancel','skip fulfill' button status is as expected(eg, They
	 * should show for 'Fulfill list entry' work flow, but not exist for 'Not Fulfill list entry' work flow)
	 * @param display
	 */
	public void verifyButtonDisplay(boolean isDisplay){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Ok button", isDisplay, this.checkOKButtonExist());
		passed &= MiscFunctions.compareResult("Cancel button", isDisplay, this.checkCancelButtonExist());
		passed &= MiscFunctions.compareResult("Skip fulfill button", isDisplay, this.checkSkipFulfillButtonExist());
		if(!passed) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} 
		logger.info("All checkpoints are PASSED for action button(ok,cancel,skip fulfill) status.");
	}
	
	public void clickFirstEntryNum(){
		browser.clickGuiObject(Property.getPropertyArray(".class", "Html.A", ".text", new RegularExpression("M2-\\d+", false)));
	}
	
	public void clickEntryNum(String entryNum){
		browser.clickGuiObject(".class", "Html.A", ".text",entryNum);
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".class", "Html.DIV", ".id", new RegularExpression("(error\\.list\\.entry\\.(fulfill)|(search)_\\d+)|(NOTSET)",false)).trim();
	}
	
	public Customer getCustInfo(String list){
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
		IHtmlTable table=(IHtmlTable)objs[0];
		
		int row=table.findRow(1, list);
		int custcol=table.findColumn(0, "Customer");
		int phonecol=table.findColumn(0, "Phone");
		int mailcol=table.findColumn(0, "Email");
		
		Customer cust=new Customer();
		cust.name=table.getCellValue(row, custcol);
		cust.phoneContact=table.getCellValue(row, phonecol);
		cust.email=table.getCellValue(row, mailcol);
		
		Browser.unregister(objs);
		return cust;
	}
	
	public void selectListByEntry(String entry){
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
		IHtmlTable table=(IHtmlTable)objs[0];
		
		int row=table.findRow(1, entry);
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), row-1);
		Browser.unregister(objs);
	}
	
	public boolean checkEntryNumExisted(String entryNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",entryNum);
	}
	
	public void gotoListEntryDetail(String entryNum){
		this.clickEntryNum(entryNum);
		ajax.waitLoading();
	}
	
	public void gotoFirstListEntryDetail(){
		this.clickFirstEntryNum();
		ajax.waitLoading();
	}
	
	/**
	 * This method is used to go to the list entry detial page when there are multiple entry with the same number but with different status
	 * The precondition is that there is only the entries with the only entry number is in the list
	 * @param entryNum
	 * @param status
	 */
	public void gotoListEntryDetailPg(String entryNum, String status){
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
		int rowNum = ((ITable)objs[0]).findRow(4, status); //4 is the column number for "ENTRY STATUS"
		browser.clickGuiObject(".class", "Html.A", ".text", entryNum, rowNum-1);
		ajax.waitLoading();
	}
	
	public void searchAndGotoListEntryDetail(String entryNum){
		setEntryNum(entryNum);
		clickGo();
		ajax.waitLoading();
		waitLoading();
		this.gotoListEntryDetail(entryNum);
	}
	
	public String getResStatus(String entryNum){
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
		int rowNum = ((IHtmlTable) objs[0]).findRow(0, entryNum);
		String status = ((IHtmlTable) objs[0]).getCellValue(rowNum, 4);
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 *The method execute the work flow that verify the entry's status in
	 * entry list search/list page
	 * @param resNum
	 * @param expectedStatus
	 */
	public void verifyEntryStatus(String entryNum, String expectedStatus) {
		logger
		.info("Verify the list entry's status in reservation search page.");
		this.searchListEntryByEntryNum(entryNum);
		String actualStatus = this.getResStatus(entryNum);
		if (!expectedStatus.equals(actualStatus)) {
			throw new ErrorOnPageException("The entry status is not correct",  expectedStatus, actualStatus);
		}
	}
	
	public IHtmlTable getListTableObj(){
		IHtmlObject[] objs = browser.getHtmlObject(this.listEntryListTable());
		if(objs.length < 1){
			throw new ErrorOnPageException("List entry list table can not be found!");
		}
		IHtmlTable crTable = (IHtmlTable)objs[0]; 
		return crTable;
	}
	
	public List<String> getListEntryInfo(int rowNum){
		List<String> res = new ArrayList<String>();
		IHtmlTable crTable = this.getListTableObj();
		return crTable.getRowValues(rowNum);
	}
	
	private List<String> getColumnValueByName(String colName){
		IHtmlTable listTable = this.getListTableObj();
		
		int col = listTable.findColumn(0, colName);
		if(col<0){
			throw new ItemNotFoundException("Can't find column by given name:"+colName);
		}
		
		List<String> colList = listTable.getColumnValues(col);
		colList.remove(0);// remove title
		return colList;
	}
	
	public List<String> getEntryNumColumnValue(){
		return this.getColumnValueByName("Entry #");
	}
	
	public void verifyColumnValue(String colName, String expectValue){
		List<String> colList = this.getColumnValueByName(colName);
		if(!colList.contains(expectValue)){
			throw new ErrorOnPageException(colName+" Column should contain value:"+expectValue);
		}
	}
	
	public String getEntryNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix +"entryNumber", false));
	}
	
	public String getPhoneNumber(){
		return browser.getTextFieldValue(phoneNumberTextField());
	}

	public String getLastName(){
		return browser.getTextFieldValue(lastNameTextField());
	}

	public String getFirstName(){
		return browser.getTextFieldValue(firstNameTextField());
	}
	
	public String getEmail(){
		return browser.getTextFieldValue(emailTextField());
	}
	
	public String getOrderStatus(){
		return browser.getDropdownListValue(orderStatusDropdownList(), 0);
	}

	public String getEntryStatus(){
		return browser.getDropdownListValue(entryStatusDropDownList(), 0);
	}
	
	public String getFacility(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"facility", false));
	}
	
	public String getListEntryType(){
		return browser.getDropdownListValue(listEntryTypeDropDownList(), 0);
	}
}
