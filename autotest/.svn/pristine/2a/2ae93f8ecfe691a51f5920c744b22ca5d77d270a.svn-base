package com.activenetwork.qa.awo.pages.orms.inventoryManager.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSearchCriteria;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrListSearchPage extends InvMgrTopMenuPage{
	private static InvMgrListSearchPage _instance = null;
	protected InvMgrListSearchPage () {}
	
	public static InvMgrListSearchPage getInstance(){
		if(null == _instance){
			_instance = new InvMgrListSearchPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "waitingListSearchPanel");
	}
	
	private static final String LIST_ID_COL = "List ID";
	private static final String NAME_COL = "Name";
	private static final String STATUS_COL = "Status";
	private static final String PARTICIPATION_COL = "Participation";
	private static final String OPEN_DATE_COL = "Open Date";
	private static final String CLOSE_DATE_COL = "Close Date";
	private static final String NUM_OF_ENTRIES_COL = "# Entries";
	
	protected Property[] searchTypeDropDownList(){
		return Property.toPropertyArray(".id", 
				new RegularExpression("WaitingListSearchCriteria-\\d+\\.searchBy",false));
	}
	protected Property[] searchValueTextField(){
		return Property.toPropertyArray(".id", 
				new RegularExpression("WaitingListSearchCriteria-\\d+\\.searchValue",false));
	}
	protected Property[] ListStatusDropDownList(){
		return Property.toPropertyArray(".id", 
				new RegularExpression("WaitingListSearchCriteria-\\d+\\.status",false));
	}
	protected Property[] participationSlipNameTextField(){
		return Property.toPropertyArray(".id", 
				new RegularExpression("WaitingListSearchCriteria-\\d+\\.participatingProductName",false));
	}
	
	public boolean isObjectExist(Property[] property){
		return browser.checkHtmlObjectExists(property);
	}
	
	public boolean isSearchTypeDropDownListExist(){
		return this.isObjectExist(this.searchTypeDropDownList());
	}
	public boolean isSearchValueTextFieldExist(){
		return this.isObjectExist(this.searchValueTextField());
	}
	public boolean isListStatusDropDownListExist(){
		return this.isObjectExist(this.ListStatusDropDownList());
	}
	public boolean isParticipatingSlipNameTextFieldExist(){
		return this.isObjectExist(this.participationSlipNameTextField());
	}
	
	public String getSearchValue(){
		return browser.getTextFieldValue(searchValueTextField());
	}
	public String getParticipatingSlipName(){
		return browser.getTextFieldValue(participationSlipNameTextField());
	}
	public String getListStatusValue(){
		return browser.getDropdownListValue(ListStatusDropDownList(), 0);
	}
	
	public List<String> getSearchTypes(){
		return browser.getDropdownElements(searchTypeDropDownList());
	}
	
	public List<String> getListStatusDropdownListElement(){
		return browser.getDropdownElements(ListStatusDropDownList());
	}
	
	public void selectSearchType(String searchType){
		browser.selectDropdownList(searchTypeDropDownList(), searchType);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(searchValueTextField(), searchValue);
	}
	
	public void selectListStatus(String status){
		browser.selectDropdownList(ListStatusDropDownList(), status);
	}
	
	public void setParticipantSlipName(String partSlipName){
		browser.setTextField(participationSlipNameTextField(), partSlipName);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	public void setSearchCriteria(ListInfo list){
		if(StringUtil.notEmpty(list.getSearchType())){
			this.selectSearchType(list.getSearchType());
		}
		
		if(StringUtil.notEmpty(list.getSearchValue())){
			this.setSearchValue(list.getSearchValue());
		}
		
		if(StringUtil.notEmpty(list.getListStatus())){
			this.selectListStatus(list.getListStatus());
		}
		
		if(StringUtil.notEmpty(list.getSearchParticipantSlipName())){
			this.setParticipantSlipName(list.getSearchParticipantSlipName());
		}
	}
	
	public void selectSearchType(int index){
		browser.selectDropdownList(searchTypeDropDownList(), index);
	}
	
	public void selectListStatus(int index){
		browser.selectDropdownList(ListStatusDropDownList(), index);
	}
	public void setParticipatingSlipName(String value){
		browser.setTextField(participationSlipNameTextField(), value);
	}
	
	public void clearSearchCriteria()
	{
		this.selectSearchType(0);
		this.setSearchValue("");
		this.selectListStatus(0);
		this.setParticipatingSlipName("");
	}
	
	public void searchList(ListSearchCriteria list)
	{
		this.clearSearchCriteria();
		
		if(!StringUtil.isEmpty(list.searchType))
		{
			this.selectSearchType(list.searchType);
		}
		
		if(!StringUtil.isEmpty(list.searchValue))
		{
			this.setSearchValue(list.searchValue);
		}
		
		if(!StringUtil.isEmpty(list.listStatus))
		{
			this.selectListStatus(list.listStatus);
		}
		
		if(!StringUtil.isEmpty(list.participatingSlipName))
		{
			this.setParticipatingSlipName(list.participatingSlipName);
		}
		
		this.clickGo();
		ajax.waitLoading();
	}
	
	public void searchListByListNameAndStatus(String listName,String status){
		this.selectSearchType("List Name");
		this.setSearchValue(listName);
		this.selectListStatus(status);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchListByListID(String listID){
		this.selectSearchType("List ID");
		this.setSearchValue(listID);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickListID(String ListID){
		browser.clickGuiObject(".class", "Html.A",".text",ListID);
	}
	
	public boolean checkErrorMessageIsExisting(){
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public IHtmlObject[] getListTableObj(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "ListGrid_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get list table object.");
		}
		return objs;
	}
	
	public List<String> getColumnValue(String columnName){
		IHtmlObject[] objs = this.getListTableObj();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int column = table.findColumn(0, columnName);
		List<String> colValues= table.getColumnValues(column);
		colValues.remove(0);
		
		Browser.unregister(objs);
		return colValues;
	}
	
	public List<String> getListNameColumnValue(){
//		return this.getColumnValue("List Name");
		return this.getColumnValue("Name");// 30501 UI changed. Nicole
	}
	
	public List<String> getListIDColumnValue(){
		return this.getColumnValue("List ID");
	}
	
	public List<ListInfo> getListInfo(){
		List<ListInfo> lists = new ArrayList<ListInfo>();
		ListInfo listInfo = new ListInfo();
		
		IHtmlObject[] objs = this.getListTableObj();
		IHtmlTable table = (IHtmlTable)objs[0];

		for(int i=1; i<table.rowCount(); i++){
			// column 0 is check box
			listInfo.setListID(table.getCellValue(i, 1));
			listInfo.setListName(table.getCellValue(i, 2));
			listInfo.setListStatus(table.getCellValue(i, 3));
			listInfo.setParticipation(table.getCellValue(i, 4));
			listInfo.setOpenDate(table.getCellValue(i, 5));
			listInfo.setCloseDate(table.getCellValue(i, 6));
			listInfo.setEntriesNum(table.getCellValue(i, 7));
			lists.add(listInfo);
		}
		Browser.unregister(objs);
		return lists;
	}
	
	public void verifyListInfo(List<ListInfo> expectLists){
		List<ListInfo> actualLists = this.getListInfo();
		
		boolean result = true;
		if(MiscFunctions.compareResult("Size of list info", expectLists.size(), actualLists.size())){
			ListInfo expect = new ListInfo();
			ListInfo actual = new ListInfo();
			for(int i=0; i<actualLists.size(); i++){
				expect = expectLists.get(i);
				actual = actualLists.get(i);

				logger.info("No. "+(i+1)+" record.");
				result &= MiscFunctions.compareResult("List ID", expect.getListID(), actual.getListID());
				result &= MiscFunctions.compareResult("List Name", expect.getListName(), actual.getListName());
				result &= MiscFunctions.compareResult("List Status", expect.getListStatus(), actual.getListStatus());
				result &= MiscFunctions.compareResult("List Participation", expect.getParticipation(), actual.getParticipation());
				result &= MiscFunctions.compareResult("Open Date", expect.getOpenDate(), actual.getOpenDate());
				result &= MiscFunctions.compareResult("Close Date", expect.getCloseDate(), actual.getCloseDate());
				result &= MiscFunctions.compareResult("Entries Number", expect.getEntriesNum(), actual.getEntriesNum());
			}
		} else {
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void selectListByID(String listID){
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), ".value", listID);
	}
	
	public void clickClose(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Close List", true);
	}
	
	public String checkListExistOrNot(ListInfo listInfo){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		logger.info("Check list exist or not, if exist get ID, if not exist add a new one.");
		listSearchPg.searchListByListNameAndStatus(listInfo.getListName(), listInfo.getListStatus());
		List<String> list = listSearchPg.getListIDColumnValue();
		if(null != list && list.size() > 0){
			logger.info("Required list "+listInfo.getListName()+" exist!!");
			return list.get(0);
		} else {
			logger.info("Required list "+listInfo.getListName()+" doesn't exist!!");
			return null;
		}
	}
	
	public List<ListInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<ListInfo> records = new ArrayList<ListInfo>();
		int rows;
		int columns;
		ListInfo list;
		
		
		do{
			objs = browser.getTableTestObject(".id", "ListGrid_LIST");
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't Marina List table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page MarinaManagerWaitingListPage, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				list = new ListInfo();
				list.setListID(table.getCellValue(i, table.findColumn(0, "List ID".toUpperCase())));
				list.setListName(table.getCellValue(i, table.findColumn(0, "Name".toUpperCase())));
				list.setListStatus(table.getCellValue(i, table.findColumn(0, "Status".toUpperCase())));
				list.setParticipation(table.getCellValue(i, table.findColumn(0, "Participation".toUpperCase())));
				list.setOpenDate(table.getCellValue(i, table.findColumn(0, "Open Date".toUpperCase())));
				list.setCloseDate(table.getCellValue(i, table.findColumn(0, "Close Date".toUpperCase())));
				list.setEntriesNum(table.getCellValue(i, table.findColumn(0, "# Entries".toUpperCase())));
		

				records.add(list);			
			}

		}while(goNext());
		Browser.unregister(objs);
		
		return records;
	}
	
	/**
	 * If "Next" button is available, click it
	 *
	 */
	public boolean goNext() {
		IHtmlObject[] pageingBar = browser.getHtmlObject(".class", "Html.TABLE", ".className", "pagingBar");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next", pageingBar[0]);
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		ajax.waitLoading();
		Browser.unregister(pageingBar);
		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	public String getMessage()
	{
		String successMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET", false));
		if(objs != null && objs.length > 0){
			successMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return successMsg;
	}
	
	public boolean isListExists(String listName, String status) {
		this.searchListByListNameAndStatus(listName, status);
		if(!this.checkErrorMessageIsExisting()) return true;
		return false;
	}
	
	public String getListIDByName(String name) {
		IHtmlObject objs[] = getListTableObj();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int nameColIndex = table.findColumn(0, NAME_COL);
		int rowIndex = table.findRow(nameColIndex, name);
		int colIndex = table.findColumn(0, LIST_ID_COL);
		String id = table.getCellValue(rowIndex, colIndex);
		
		Browser.unregister(objs);
		return id;
	}
}
