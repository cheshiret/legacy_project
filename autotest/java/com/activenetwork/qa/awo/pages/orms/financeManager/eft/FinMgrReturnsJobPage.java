package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTReturnJobInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: This is EFT returns job search/list page:FM/LM --->EFT ---> Returns Jobs
 * @author pchen
 * @Date  August 15, 2012
 */
public class FinMgrReturnsJobPage extends FinanceManagerPage {
	
	private static FinMgrReturnsJobPage _instance = null;
	
	protected FinMgrReturnsJobPage() {}
	
	public static FinMgrReturnsJobPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrReturnsJobPage();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","returnJobGrid");
	}
	
	private String prefix = "EFTReturnJobSearchCriteria-\\d+\\.";
	/**
	 * Select search type
	 * @param type
	 */
	public void selectSearchType(String type){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchType",false), type);
	}
	
     /**
      * Set search value
      * @param value
      */
	public void setSearchValue(String value){
		browser.setTextField(".id", new RegularExpression(prefix+"searchvalue",false), value);
	}
	/**
	 * select search date kind
	 * @param date
	 */
	public void selectSearchDate(String date){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchDate",false), date);
	}
	
	/**
	 * Set from date
	 * @param date
	 */
	public void setFrom(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"fromDate_ForDisplay",false), date);
	}
	/**
	 * Set to date
	 * @param date
	 */
	public void setTo(String date){
		browser.setTextField(".id", new RegularExpression(prefix+"toDate_ForDisplay",false), date);
	}
	/**
	 * Select status of job
	 * @param status
	 */
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"status",false), status);
	}
	/**
	 * Select matchin status
	 * @param status
	 */
	public void selectMatchingStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"matchStatus",false), status);
	}
	/**
	 * Click search button
	 */
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	/**
	 * Set up search informations
	 * @param info
	 */
	public void setSearchData(EFTReturnJobInfo info){
		if(StringUtil.notEmpty(info.getSearchType())){
			this.selectSearchType(info.getSearchType());
			if(StringUtil.notEmpty(info.getSearchValue())){
				this.setSearchValue(info.getSearchValue());
			}else{
				throw new ActionFailedException("Please set the search value...");
			}
		}
		
		if(StringUtil.notEmpty(info.getSearchDate())){
			this.selectSearchDate(info.getSearchDate());
			if(StringUtil.isEmpty(info.getFromDate())&& StringUtil.isEmpty(info.getToDate())){
				throw new ActionFailedException("Please set the date value...");
			}
			if(StringUtil.notEmpty(info.getFromDate())){
				this.setFrom(info.getFromDate());
			}
			if(StringUtil.notEmpty(info.getToDate())){
				this.setTo(info.getToDate());
			}
		}
		
		if(StringUtil.notEmpty(info.getStatus())){
			this.selectStatus(info.getStatus());
		}
		
		if(StringUtil.notEmpty(info.getMatchingStatus())){
			this.selectMatchingStatus(info.getMatchingStatus());
		}
	}
	/**
	 * Click first job in list
	 */
	public void clickFirstReturnJob () {
		/*HtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","returnJobGrid_LIST");
		HtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[0];
		link.click();
		Browser.unregister(objs);
		Browser.unregister(objs1);*/
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+",false));
	}
    /**
     * Click return job with id
     * @param id
     */
	public void clickReturnJobWithId(String id){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","returnJobGrid_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",id);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}
	
	/**
	 * Set search filters and click search button to get search result
	 */
	public void searchReturnJob(EFTReturnJobInfo info){
		this.setSearchData(info);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * get all the values in one column on the page.
	 * 
	 * @param
	 * @return List of records.
	 */
	public List<String> getRecordsSpecialColumnValueOnPage(int col) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<String> values = new ArrayList<String>();
		int rows;
		int columns;
		do {
			objs = browser.getTableTestObject(".id","returnJobGrid_LIST");
			if (objs.length < 1) {
				throw new ItemNotFoundException(
						"Can't find return jobs records table object.");
			}
			table = (IHtmlTable) objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page FinMgrReturnsJobPage, "
					+ rows + " rows, " + columns + " columns.");
            values.addAll(table.getColumnValues(col));    
            Browser.unregister(objs);
		} while (gotoNext());		
		return values;
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<List<String>> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<List<String>> records = new ArrayList<List<String>>();
		int rows;
		int columns;
		
		do{
		objs = browser.getTableTestObject(".id", "returnJobGrid_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find return jobs records table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrReturnsJobPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			
			records.add(table.getRowValues(i));	
			
		}
				
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}
	
	
	/**
	 * If "Next" button is available, click it
	 */
	public boolean gotoNext() {
		IHtmlObject[] pageTables = browser.getHtmlObject(".class","Html.TABLE", ".id", new RegularExpression("pagingbar_\\d+",false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next", pageTables[0]);
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		Browser.unregister(pageTables);
		ajax.waitLoading();
		this.waitLoading();

		return toReturn;
	}
	
	/**
	 * This method set all the filter field to empty
	 */
	public void clearSearchCriteria() {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchType",false), 0);		
		this.setSearchValue(StringUtil.EMPTY);
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchDate",false), 0);
		this.setFrom(StringUtil.EMPTY);
		this.setTo(StringUtil.EMPTY);
		browser.selectDropdownList(".id", new RegularExpression(prefix+"status",false), 0);
		browser.selectDropdownList(".id", new RegularExpression(prefix+"matchStatus",false), 0);

	}
}
