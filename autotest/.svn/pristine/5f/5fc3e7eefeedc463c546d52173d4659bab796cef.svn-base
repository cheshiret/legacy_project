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
 * @Date  Jul 21, 2012
 */

public class FinMgrDailyEFTJobVoucherGCPage extends FinMgrDailyEFTJobsPage {
	
	private static FinMgrDailyEFTJobVoucherGCPage _instance = null;
	
	private FinMgrDailyEFTJobVoucherGCPage() {}
	
	public static FinMgrDailyEFTJobVoucherGCPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrDailyEFTJobVoucherGCPage();
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
		//DailyEFTJobRecordsSearchCriteria-331574995.voucherID
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.voucherID", false), id);
	}
	
	
	/**
	 * set GC Order number
	 * @param GCNum
	 */
	public void setGCOrderNum(String ordNum) {
		//DailyEFTJobRecordsSearchCriteria-60877000.gcOrderNumber
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.gcOrderNumber", false), ordNum);
	}
	
	/**
	 * set invoice number
	 * @param invoiceNum
	 */
	public void setInvoiceNumber(String invoiceNum) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.invoiceNum", false), invoiceNum);
	}
	
	/**
	 * set remittance number
	 * @param remittanceNum
	 */
	public void setRemittanceNumber(String remittanceNum) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.remittanceNum", false), remittanceNum);
	}
		
	/**
	 * set account code
	 * @param account
	 */
	public void setAccountCode(String account) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.accountCode", false), account);
	}
	
	/**
	 * set account description
	 * @param accountDscr
	 */
	public void setAccountDescription(String accountDscr) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.accountDescription", false), accountDscr);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**
	 * set all search criteria to search records
	 * 
	
	 */
	public void searchByCriteria(EFTVoucherInternalGCRecord eft) {
		
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
		if(!StringUtil.isEmpty(eft.getRemittanceNum()))
		{
			this.setRemittanceNumber(eft.getRemittanceNum());
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
		this.setRemittanceNumber(StringUtil.EMPTY);
		this.setAccountCode(StringUtil.EMPTY);
		this.setAccountDescription(StringUtil.EMPTY);

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
		objs = browser.getTableTestObject(".id", "dailyEFTJobDetailsList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Voucher Internal GC table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrDailyEFTJobVoucherGCPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			
			records.add(table.getRowValues(i));	
			
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
