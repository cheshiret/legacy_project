package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 10, 2012
 */
public class FinMgrInvoicePage extends FinanceManagerPage{

	private static FinMgrInvoicePage _instance = null;

	protected FinMgrInvoicePage() {
	}

	public static FinMgrInvoicePage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrInvoicePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.invTransStatus", false));
	}
	
	public void setSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.searchType", false), searchType);
	}
	public void setSearchType(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.searchType", false), idx);
	}
	
	
	public void setSearchValue(String searchValue) {
		browser.setTextField(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.searchValue", false), searchValue);
	}

	public void setSearchDate(String searchDate) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.searchDate", false), searchDate);
	}
	public void setSearchDate(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.searchDate", false), idx);
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+\\.dateFrom_ForDisplay",false), fromDate);
	}
	
	public void setToDate(String toDate){
		browser.setTextField(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+\\.dateTo_ForDisplay",false), toDate);
	}
	
	public void setInvoicingStatus(String invoicingStatus) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.status", false), invoicingStatus);
	}
	public void setInvoicingStatus(int idx ) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.status", false), idx);
	}

	public void setInvoiceTransStatus(String invoiceTransStatus) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.invTransStatus", false), invoiceTransStatus);
	}
	public void setInvoiceTransStatus(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.invTransStatus", false), idx);
	}

	public void setInvoiceType(String invoiceType) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.invType", false), invoiceType);
	}
	public void setInvoiceType(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoicesSearchCriteria-\\d+.invType", false), idx);
	}
	
	
	public void searchEFTInvoiceByInvoiceNum(String invoiceNum){
		this.setSearchType("EFT Invoice Number");
		this.setSearchValue(invoiceNum);
		this.clickSearch();
		this.waitLoading();
	}
	
	public void setUpSearchCriteria(EFTInvoicingInfo invoiceInfo){

		if(invoiceInfo.getSearchType() != null && !"".equals(invoiceInfo.getSearchType())){
			this.setSearchType(invoiceInfo.getSearchType());
			if(invoiceInfo.getSearchValue() != null && !"".equals(invoiceInfo.getSearchValue())){
				this.setSearchValue(invoiceInfo.getSearchValue());
			}else{
				throw new ErrorOnDataException("Please set the search value...");
			}
		}
		
		if(invoiceInfo.getSearchDate() != null && !"".equals(invoiceInfo.getSearchDate())){
			this.setSearchDate(invoiceInfo.getSearchDate());
			if(invoiceInfo.getFromDate() != null && !"".equals(invoiceInfo.getFromDate())){
				this.setFromDate(invoiceInfo.getFromDate());
			}else if(invoiceInfo.getToDate() != null && !"".equals(invoiceInfo.getToDate())){
				this.setToDate(invoiceInfo.getToDate());
			}else {
				throw new ErrorOnDataException("Please set the date value...");
			}
		}
		
		if(invoiceInfo.getStatus() != null && !"".equals(invoiceInfo.getStatus())){
			this.setInvoicingStatus(invoiceInfo.getStatus());
		}
		
		if(null != invoiceInfo.getTransmissionStatus()){
			this.setInvoiceTransStatus(invoiceInfo.getTransmissionStatus());
		}
	}
	
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true); 
	}
	
	public void clickInvoiceNumber(String invoiceNumber){
		browser.clickGuiObject(".class", "Html.A", ".text", invoiceNumber, true);
	}
	
	public void gotoFirstInvoiceDetailsPg() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","eftinvoicejobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[0];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}
	
	public void gotoLastInvoiceDetailsPg() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","eftinvoicejobList_LIST");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A",".text",new RegularExpression("\\d+",false), objs[0]);
		ILink link = (ILink) objs1[objs1.length-2];
		link.click();

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}

	/**
	 * 
	 */
	public void clearSearchCriteria() {
		
			this.setSearchType(0);
			this.setSearchValue(StringUtil.EMPTY);
			this.setSearchDate(0);
			this.setFromDate(StringUtil.EMPTY);
			this.setToDate(StringUtil.EMPTY);
			this.setInvoicingStatus(0);
			this.setInvoiceTransStatus(0);
			this.setInvoiceType(0);
		
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTInvoicingInfo> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTInvoicingInfo> records = new ArrayList<EFTInvoicingInfo>();
		int rows;
		int columns;
		EFTInvoicingInfo eft;
		
		do{
		objs = browser.getTableTestObject(".id", "eftinvoicejobList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find EFT invoice table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrInvoicePage, "+rows+" rows, "+columns+" columns.");
	
		for(int i = 1; i < rows; i ++) {
			eft = new EFTInvoicingInfo();
								
			//check tableisready.
			eft.setInvoiceNum(table.getCellValue(i, table.findColumn(0, "Invoice Number")));
			eft.setInvoiceJobId(table.getCellValue(i, table.findColumn(0, "Invoicing Job ID")));
			eft.setInvoiceType(table.getCellValue(i, table.findColumn(0, "Invoice Type")));
			eft.setStatus(table.getCellValue(i, table.findColumn(0, "Invoice Status")));
			eft.setTransmissionStatus(table.getCellValue(i, table.findColumn(0, "Transmission Status")));
			eft.setVendorNumName(table.getCellValue(i, table.findColumn(0, "Vendor")));
			eft.setAgentNumName(table.getCellValue(i, table.findColumn(0, "Agent")));
			eft.setInvoiceGrouping(table.getCellValue(i, table.findColumn(0, "Invoice Grouping")));
			eft.setInvoiceDate(table.getCellValue(i, table.findColumn(0, "Invoice Date")));
			eft.setPeriodDate(table.getCellValue(i, table.findColumn(0, "Period End Date")));
			eft.setAmount(table.getCellValue(i, table.findColumn(0, "Invoice Amount")));
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
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}

	/**
	 * Get invoice record by invoice number.
	 * @param invoiceNum
	 * @return
	 */
	public List<String> getInvoiceRecordByInvoiceNum(String invoiceNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "eftinvoicejobList_LIST");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find EFT Invoice records list.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		MiscFunctions.dumpTable(table);
		int row = table.findRow(0, invoiceNum);
		if(row < 0){
			throw new ItemNotFoundException("Can't find EFT Invoice records by given invoice number "+ invoiceNum);
		}
		return table.getRowValues(row);
	}
}
