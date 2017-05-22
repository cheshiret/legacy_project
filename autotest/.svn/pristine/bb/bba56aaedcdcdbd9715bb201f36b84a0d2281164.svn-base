package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTDepositAdjustmentRecord;
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
 * @Date  Jul 19, 2012
 */

public class FinMgrDailyEFTJobDepositAdjustPage extends FinMgrDailyEFTJobsPage {
	
	private static FinMgrDailyEFTJobDepositAdjustPage _instance = null;
	
	private FinMgrDailyEFTJobDepositAdjustPage() {}
	
	public static FinMgrDailyEFTJobDepositAdjustPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrDailyEFTJobDepositAdjustPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^Filter\\s+Deposit ID.*",false));
	}
	
	/**
	 * set deposit number
	 * @param invoiceNum
	 */
	public void setDepositID(String id) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.depositID", false), id);
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
	 * set agent ID
	 * @param agentID
	 */
	public void setAgentID(String agentID) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.storeID", false), agentID);
	}
	
	/**
	 * set agent Name
	 * @param agentName
	 */
	public void setAgentName(String agentName) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.storeName", false), agentName);
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
	public void searchByCriteria(EFTDepositAdjustmentRecord eft) {
		
		if(!StringUtil.isEmpty(eft.getDepositeID()))
		{
			this.setDepositID(eft.getDepositeID());
		}
		
		if(!StringUtil.isEmpty(eft.getInvoiceNum()))
		{
			this.setInvoiceNumber(eft.getInvoiceNum());
		}
		if(!StringUtil.isEmpty(eft.getRemittanceNum()))
		{
			this.setRemittanceNumber(eft.getRemittanceNum());
		}
		if(!StringUtil.isEmpty(eft.getAgentID()))
		{
			this.setAgentID(eft.getAgentID());
		}
		if(!StringUtil.isEmpty(eft.getAgentName()))
		{
			this.setAgentName(eft.getAgentName());
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
		
		this.setDepositID(StringUtil.EMPTY);
		this.setInvoiceNumber(StringUtil.EMPTY);
		this.setRemittanceNumber(StringUtil.EMPTY);
		this.setAgentID(StringUtil.EMPTY);
		this.setAgentName(StringUtil.EMPTY);
		this.setAccountCode(StringUtil.EMPTY);
		this.setAccountDescription(StringUtil.EMPTY);

	}

	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTDepositAdjustmentRecord> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTDepositAdjustmentRecord> records = new ArrayList<EFTDepositAdjustmentRecord>();
		int rows;
		int columns;
		EFTDepositAdjustmentRecord eft;
		
		do{
		objs = browser.getTableTestObject(".id", "dailyEFTJobDetailsList_LIST");//dailyEFTJobDetailsList_LIST
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find deposit adjustment table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrDailyEFTJobDepositAdjustPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTDepositAdjustmentRecord();
			eft.setEFTRecordID(table.getCellValue(i, table.findColumn(0, "Daily Rec ID")));
			eft.setAdjustmentID(table.getCellValue(i, table.findColumn(0, "Adjustment ID")));
			eft.setDepositeID(table.getCellValue(i, table.findColumn(0, "Deposit ID")));
			eft.setAgentInfo(table.getCellValue(i, table.findColumn(0, "Agent")));
			eft.setRevenueLoc(table.getCellValue(i, table.findColumn(0, "Revenue Location")));
			eft.setAccountInfo(table.getCellValue(i, table.findColumn(0, "Account")));
			eft.setPostDate(table.getCellValue(i, table.findColumn(0, "Posted Date")));
			eft.setEFTConfigSchID(table.getCellValue(i, table.findColumn(0, "EFT Config Schedule ID")));
			eft.setInvoiceNum(table.getCellValue(i, table.findColumn(0, "Invoice Number")));
			eft.setRemittanceNum(table.getCellValue(i, table.findColumn(0, "Remittance Number")));
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
