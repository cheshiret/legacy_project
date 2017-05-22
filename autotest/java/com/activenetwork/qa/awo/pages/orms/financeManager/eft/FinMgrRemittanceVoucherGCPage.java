/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTVoucherInternalGCRecord;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 3, 2012
 */



public class FinMgrRemittanceVoucherGCPage extends FinMgrRemittanceDetailPage {
	
	private static FinMgrRemittanceVoucherGCPage _instance = null;
	
	private FinMgrRemittanceVoucherGCPage() {}
	
	public static FinMgrRemittanceVoucherGCPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrRemittanceVoucherGCPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^Filter\\s+Voucher ID.*",false));
	}
	
	/**
	 * set Voucher ID
	 * @param voucher id
	 */
	public void setVoucherID(String id) {
		//EFTRemittanceTransactionsSearchCriteria-67282270.voucherID
		browser.setTextField(".id", new RegularExpression("EFTRemittanceTransactionsSearchCriteria-\\d+\\.voucherID", false), id);
	}
	
	
	/**
	 * set GC Order number
	 * @param GCNum
	 */
	public void setGCOrderNum(String ordNum) {
		//EFTRemittanceTransactionsSearchCriteria-1236713568.gcOrderNumber
		browser.setTextField(".id", new RegularExpression("EFTRemittanceTransactionsSearchCriteria-\\d+\\.gcOrderNumber", false), ordNum);
	}
	
	/**
	 * set invoice number
	 * @param invoiceNum
	 */
	public void setInvoiceNumber(String invoiceNum) {
		//EFTRemittanceTransactionsSearchCriteria-2098201710.invoiceNum
		browser.setTextField(".id", new RegularExpression("EFTRemittanceTransactionsSearchCriteria-\\d+\\.invoiceNum", false), invoiceNum);
	}
	
		
	/**
	 * set account code
	 * @param account
	 */
	public void setAccountCode(String account) {
		//EFTRemittanceTransactionsSearchCriteria-647172583.accountCode
		browser.setTextField(".id", new RegularExpression("EFTRemittanceTransactionsSearchCriteria-\\d+\\.accountCode", false), account);
	}
	
	/**
	 * set account description
	 * @param accountDscr
	 */
	public void setAccountDescription(String accountDscr) {
		//EFTRemittanceTransactionsSearchCriteria-24930426.accountDescription
		browser.setTextField(".id", new RegularExpression("EFTRemittanceTransactionsSearchCriteria-\\d+\\.accountDescription", false), accountDscr);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**
	 * set all search criteria to search records
	 * 
	
	 */
	public void searchByCriteria(EFTVoucherInternalGCRecord eft) {
		this.clearSearchCriteria();
		
		if(!StringUtil.isEmpty(eft.getVoucherID()))
		{
			this.setVoucherID(eft.getVoucherID());
		}
		

		if(!StringUtil.isEmpty(eft.getGCOrdNum()))
		{
			this.setGCOrderNum(eft.getGCOrdNum());
		}
		
		if(!StringUtil.isEmpty(eft.getInvoiceNum()))
		{
			this.setInvoiceNumber(eft.getInvoiceNum());
		}
			
		if(!StringUtil.isEmpty(eft.getAccountCode()))
		{
			this.setAccountCode(eft.getAccountCode());
		}
		
		if(!StringUtil.isEmpty(eft.getAccountDesc()))
		{
			this.setAccountDescription(eft.getAccountDesc());
		}
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clearSearchCriteria() {
		
		this.setVoucherID(StringUtil.EMPTY);
		this.setGCOrderNum(StringUtil.EMPTY);
		this.setInvoiceNumber(StringUtil.EMPTY);
		this.setAccountCode(StringUtil.EMPTY);
		this.setAccountDescription(StringUtil.EMPTY);

	}

	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTVoucherInternalGCRecord> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTVoucherInternalGCRecord> records = new ArrayList<EFTVoucherInternalGCRecord>();
		int rows;
		int columns;
		EFTVoucherInternalGCRecord eft;
		
		do{
		objs = browser.getTableTestObject(".id", "DailyEFTGrid_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Voucher GC record  table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrRemittanceVoucherGCPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTVoucherInternalGCRecord();
			eft.setDailyRecIDAndDailyJobID(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Daily Rec ID.*", false))));
			eft.setAllocIDAndVoucherIDAndGCOrdNum(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Allocation ID /.*", false))));
			eft.setRevenueLocAndAccount(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Revenue Location /.*", false))));
			eft.setAllocTransAndLocation(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Allocation Transaction /.*", false))));
			eft.setDailyEFTTypeAndPostDate(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Daily EFT Type /.*", false))));
			eft.setInvoiceNum(table.getCellValue(i, table.findColumn(0, "Invoice Number")));
			eft.setAmount(table.getCellValue(i, table.findColumn(0, "Amount")));
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
}
