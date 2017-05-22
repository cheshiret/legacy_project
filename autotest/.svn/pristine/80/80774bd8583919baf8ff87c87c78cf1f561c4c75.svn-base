/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingJobInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author szhou
 *
 */
public class FinMgrInvoicingJobsPage extends FinanceManagerPage{
	static private FinMgrInvoicingJobsPage _instance = null;

	
	protected FinMgrInvoicingJobsPage() {
	}

	static public FinMgrInvoicingJobsPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrInvoicingJobsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","eftinvoicejobList_LIST");
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.searchType",false), type);
	}
	
	public void selectSearchType(int idx){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.searchType",false), idx);
	}
	
	public void selectSearchDate(String date){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.searchDate",false), date);
	}

	public void selectSearchDate(int idx){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.searchDate",false), idx);
	}
	
	public void setSearchValue(String value){
		browser.setTextField(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.searchValue",false), value);
	}
	
	public void setFrom(String date){
		browser.setTextField(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.dateFrom_ForDisplay",false), date);
	}
	
	public void setTo(String date){
		browser.setTextField(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.dateTo_ForDisplay",false), date);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.status",false), status);
	}
	
	public void selectStatus(int idx){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.status",false), idx);
	}
	
	public void selectFrequency(String frequency){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.frequency",false), frequency);
	}
	
	public void selectFrequency(int idx){
		browser.selectDropdownList(".id", new RegularExpression("EFTJobSearchCriteria-\\d+\\.frequency",false), idx);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	public void clearSearchCondition()
	{
		this.selectSearchType(0);
		this.setSearchValue(StringUtil.EMPTY);
		this.selectSearchDate(0);
		this.setFrom(StringUtil.EMPTY);
		this.setTo(StringUtil.EMPTY);
		this.selectStatus(0);
		this.selectFrequency(0);
	}
	
	public void searchInvoiceJob(EFTInvoicingJobInfo info)
	{
		this.clearSearchCondition();
		this.setSearchData(info);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setSearchData(EFTInvoicingJobInfo info){
		if(!StringUtil.isEmpty(info.getSearchType())){
			this.selectSearchType(info.getSearchType());
			if(!StringUtil.isEmpty(info.getSearchValue())){
				this.setSearchValue(info.getSearchValue());
			}else{
				throw new ErrorOnDataException("Please set the search value...");
			}
		}
		
		if(!StringUtil.isEmpty(info.getSearchDate())){
			this.selectSearchDate(info.getSearchDate());
			if(StringUtil.isEmpty(info.getFromDate()) && StringUtil.isEmpty(info.getToDate())){
				throw new ErrorOnDataException("Please set the date value...");
			}
			if(!StringUtil.isEmpty(info.getFromDate())){
				this.setFrom(info.getFromDate());
			}
			if(!StringUtil.isEmpty(info.getToDate())){
				this.setTo(info.getToDate());
			}
		}
		
		if(!StringUtil.isEmpty(info.getStatus())){
			this.selectStatus(info.getStatus());
		}
		
		if(!StringUtil.isEmpty(info.getFrequency())){
			this.selectFrequency(info.getFrequency());
		}
	}
	
	public void gotoFirstInvoiceJobDetailsPg() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","eftinvoicejobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}
	
	public void clickJobID(String jobID){
		browser.clickGuiObject(".class", "Html.A", ".text", jobID);
	}
	/*
	 * click Back button.
	 */
	public void clickBackButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTInvoicingJobInfo> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTInvoicingJobInfo> records = new ArrayList<EFTInvoicingJobInfo>();
		int rows;
		int columns;
		EFTInvoicingJobInfo eft;
		
		do{
		objs = browser.getTableTestObject(".id", "eftinvoicejobList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find invoice job table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrInvoicingJobsPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTInvoicingJobInfo();
			eft.setJobID(table.getCellValue(i, table.findColumn(0, "Job ID")));
			eft.setStatus(table.getCellValue(i, table.findColumn(0, "Status")));
			eft.setLocation(table.getCellValue(i, table.findColumn(0, "Location")));
			eft.setFrequency(table.getCellValue(i, table.findColumn(0, "Invoice Frequency")));
			eft.setStore(table.getCellValue(i, table.findColumn(0, "Store")));
			eft.setInvoiceAndPeriodDate(table.getCellValue(i, table.findColumn(0, "Invoice Date/Period End Date")));
			eft.setRunStartAndEndTime(table.getCellValue(i, table.findColumn(0, "Run Date/Time")));
			eft.setRunLocation(table.getCellValue(i, table.findColumn(0, "Run Location")));
			eft.setRunUser(table.getCellValue(i, table.findColumn(0, "Run User")));

			records.add(eft);			
		}
		
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}

	
	/**
	 * If "Next" button is available, click it
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] pageBar = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("pagingbar_\\d+",false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next", pageBar[0] );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
			ajax.waitLoading();
		}

		Browser.unregister(pageBar);
		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
}
