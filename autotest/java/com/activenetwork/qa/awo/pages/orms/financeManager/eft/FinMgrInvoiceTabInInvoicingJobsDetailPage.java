package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author szhou
 * 
 */
public class FinMgrInvoiceTabInInvoicingJobsDetailPage extends
		FinMgrInvoicingJobsDetailPage {
	static private FinMgrInvoiceTabInInvoicingJobsDetailPage _instance = null;

	protected FinMgrInvoiceTabInInvoicingJobsDetailPage() {
	}

	static public FinMgrInvoiceTabInInvoicingJobsDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrInvoiceTabInInvoicingJobsDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id",
				"eFTInvoiceJobDetailsList_LIST", ".text",
				new RegularExpression("Invoice Number.*", false));
	}
	
	public void setAgentName(String name) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.storeName", false),
				name);
	}
	
	public void selectVendor(String name) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.vendorID", false),name);
	}

	public void setAgentID(String id) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.storeID", false),
				id);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.invoicestatusId", false),status);
	}
	
	public void selectStatus(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.invoicestatusId", false),idx);
	}

	public void selectVendor(int idx) {
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.vendorID", false),idx);
	}
	
	public void setSearchCondtion(EFTInvoicingInfo info)
	{
		if(!StringUtil.isEmpty(info.getVendorName()))
		{
			this.selectVendor(info.getVendorName());
		}
		
		if(!StringUtil.isEmpty(info.getAgentNum()))
		{
			this.setAgentID(info.getAgentNum());
		}
		
		if(!StringUtil.isEmpty(info.getAgentName()))
		{
			this.setAgentName(info.getAgentName());
		}
		
		if(!StringUtil.isEmpty(info.getStatus()))
		{
			this.selectStatus(info.getStatus());
		}
	}
	
	public void clearSearchCondition()
	{
		this.selectVendor(0);
		this.setAgentID(StringUtil.EMPTY);
		this.setAgentName(StringUtil.EMPTY);
		this.selectStatus(0);
	}
	
	public void searchInvoice(EFTInvoicingInfo info)
	{
		this.clearSearchCondition();
		this.setSearchCondtion(info);
		this.clickGO();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickGO() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public List<String> getAllInvoiceNumber() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"eFTInvoiceJobDetailsList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.rowCount();
		List<String> number = new ArrayList<String>();
		for (int i = 1; i < row; i++) {
			String num = table.getCellValue(i, 0);
			number.add(num);
		}
		
		Browser.unregister(objs);

		return number;
	}
	

	public List<EFTInvoicingInfo> getAllInvoiceInfo() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"eFTInvoiceJobDetailsList_LIST", ".text",
				new RegularExpression("Invoice Number.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.rowCount();
		List<EFTInvoicingInfo> invoices = new ArrayList<EFTInvoicingInfo>();
		for (int i = 1; i < row; i++) {
			EFTInvoicingInfo info = new EFTInvoicingInfo();
			info.setInvoiceJobId(this.getInvoicingJobId());
			info.setInvoiceNum(table.getCellValue(i, 0));
			info.setInvoiceType(table.getCellValue(i, 1));
			info.setStatus(table.getCellValue(i, 2));
			info.setVendorNum(table.getCellValue(i, 3).split("\\-")[0].trim());
			info.setVendorName(table.getCellValue(i, 3).split("\\-")[1].trim());
			info.setAgentNum(table.getCellValue(i, 4).split("\\-")[0].trim());
			info.setAgentName(table.getCellValue(i, 4).split("\\-")[1].trim());
			info.setInvoiceGrouping(table.getCellValue(i, 5));
			info.setInvoiceDate(table.getCellValue(i, 6));
			info.setPeriodDate(table.getCellValue(i, 7));
			info.setAmount(table.getCellValue(i, 8));
			
			invoices.add(info);
		}
		
		Browser.unregister(objs);

		return invoices;
	}
	
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTInvoicingInfo> getInvoiceRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTInvoicingInfo> records = new ArrayList<EFTInvoicingInfo>();
		int rows;
		int columns;
		EFTInvoicingInfo eft;
		
		do{
		objs = browser.getTableTestObject(".id", "eFTInvoiceJobDetailsList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find invoice table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrInvoiceTabInInvoicingJobsDetailPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTInvoicingInfo();
			eft.setInvoiceNum(table.getCellValue(i, table.findColumn(0, "Invoice Number")));
			eft.setInvoiceType(table.getCellValue(i, table.findColumn(0, "Invoice Type")));
			eft.setStatus(table.getCellValue(i, table.findColumn(0, "Invoice Status")));
			eft.setVendorNumName(table.getCellValue(i, table.findColumn(0, "Vendor")));
			eft.setAgentNumName(table.getCellValue(i, table.findColumn(0, "Agent")));
			eft.setInvoiceGrouping(table.getCellValue(i, table.findColumn(0, "Account OR Invoice Group")));
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
