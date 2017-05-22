/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTPaymentAllocationRecord;
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
 * @Date  Jul 18, 2012
 */

public class FinMgrDailyEFTJobPaymentAllocationPage extends FinMgrDailyEFTJobsPage {
	
	private static FinMgrDailyEFTJobPaymentAllocationPage _instance = null;
	
	private FinMgrDailyEFTJobPaymentAllocationPage() {}
	
	public static FinMgrDailyEFTJobPaymentAllocationPage getInstance() {
		if(null == _instance) {
			_instance = new FinMgrDailyEFTJobPaymentAllocationPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		//DailyEFTJobRecordsSearchCriteria-198091816.orderNum
		return browser.checkHtmlObjectExists(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.orderNum", false));
	}
	
	/**
	 * set order number
	 * @param ordNum
	 */
	public void setOrderNumber(String ordNum) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.orderNum", false), ordNum);
	}
	
	/**
	 * set payment id
	 * @param paymentId
	 */
	public void setPaymentID(String paymentId) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.paymentID", false), paymentId);
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
	 * select product category
	 * @param prodCategory
	 */
	public void selectProductCategory(String prodCategory) {
		browser.selectDropdownList(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.productCategoryID", false), prodCategory);
	}
	
	/**
	 * select product category
	 * @param prodCategory
	 */
	public void selectProductCategory(int id) {
		browser.selectDropdownList(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.productCategoryID", false), id);
	}
	/**
	 * set product code
	 * @param prodCode
	 */
	public void setProductCode(String prodCode) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.productCode", false), prodCode);
	}
	
	/**
	 * set product name
	 * @param prodName
	 */
	public void setProductName(String prodName) {
		browser.setTextField(".id", new RegularExpression("DailyEFTJobRecordsSearchCriteria-\\d+\\.productName", false), prodName);
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
	 * set all search criteria to search payment allocation records
	 * @param ordNum
	
	 */
	public void searchByCriteria(EFTPaymentAllocationRecord eft) {
		if(!StringUtil.isEmpty(eft.getOrdNum()))
		{
			this.setOrderNumber(eft.getOrdNum());
		}
		
		if(!StringUtil.isEmpty(eft.getPaymentID()))
		{
			this.setPaymentID(eft.getPaymentID());
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
		if(!StringUtil.isEmpty(eft.getProductCategory()))
		{
			this.selectProductCategory(eft.getProductCategory());
		}
		if(!StringUtil.isEmpty(eft.getProductCode()))
		{
			this.setProductCode(eft.getProductCode());
		}
		if(!StringUtil.isEmpty(eft.getProductName()))
		{
			this.setProductName(eft.getProductName());
		}
		if(!StringUtil.isEmpty(eft.getAccountCode()))
		{
			this.setAccountCode(eft.getAccountCode());
		}
		if(!StringUtil.isEmpty(eft.getAccountDescription()))
		{
			this.setAccountDescription(eft.getAccountDescription());
		}
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clearSearchCriteria() {
		
		this.setOrderNumber(StringUtil.EMPTY);
		this.setPaymentID(StringUtil.EMPTY);
		this.setInvoiceNumber(StringUtil.EMPTY);
		this.setRemittanceNumber(StringUtil.EMPTY);
		this.setAgentID(StringUtil.EMPTY);
		this.setAgentName(StringUtil.EMPTY);
		this.selectProductCategory(0);
		this.setProductCode(StringUtil.EMPTY);
		this.setProductName(StringUtil.EMPTY);
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
					throw new ItemNotFoundException("Can't find Payment Allocation table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrDailyEFTJobPaymentAllocationPage, "+rows+" rows, "+columns+" columns.");
		
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
