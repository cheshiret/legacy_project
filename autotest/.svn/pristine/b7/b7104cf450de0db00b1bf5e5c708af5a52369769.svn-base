/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTRemittanceInfo;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
public class FinMgrRemittanceTabInInvoicingJobsDetailPage extends
		FinMgrInvoicingJobsDetailPage {
	static private FinMgrRemittanceTabInInvoicingJobsDetailPage _instance = null;

	protected FinMgrRemittanceTabInInvoicingJobsDetailPage() {
	}

	static public FinMgrRemittanceTabInInvoicingJobsDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRemittanceTabInInvoicingJobsDetailPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id",
				"eFTInvoiceJobDetailsList_LIST", ".text",
				new RegularExpression("Remittance Number.*", false));
	}
	
	public void selectVendor(String vendorName)
	{
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.vendorID",false), vendorName);
	}
	
	public void selectVendor(int idx)
	{
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.vendorID",false), idx);
	}
	public void setAgentID(String id) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.storeID", false),
				id);
	}

	public void setAgentName(String name) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.storeName", false),
				name);
	}

	public void selectStatus(String status)
	{
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.remittancestatusId",false), status);
	}
	
	public void selectStatus(int idx)
	{
		browser.selectDropdownList(".id", new RegularExpression("EFTInvoiceJobDetailSearchCriteria-\\d+\\.remittancestatusId",false), idx);
	}

	public void setAccountCode(String code) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.accountCode", false),
				code);
	}
	
	public void setAccountDscr(String dscr) {
		browser.setTextField(".id", new RegularExpression(
				"EFTInvoiceJobDetailSearchCriteria-\\d+\\.accountName", false),
				dscr);
	}
	
	public void clickGO() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	public List<EFTRemittanceInfo> getAllRemittanceInfo() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"eFTInvoiceJobDetailsList_LIST", ".text",
				new RegularExpression("Remittance Number.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.rowCount();
		List<EFTRemittanceInfo> remittance = new ArrayList<EFTRemittanceInfo>();
		for (int i = 1; i < row; i++) {
			EFTRemittanceInfo info = new EFTRemittanceInfo();
			info.setRemittanceNumber(table.getCellValue(i, 0));
			info.setStatus(table.getCellValue(i, 1));
			info.setVendorName(table.getCellValue(i, 2).split("\\-")[1]);
			info.setStoreName(table.getCellValue(i, 3).split("\\-")[1]);
			info.setAccountCode(table.getCellValue(i, 4));
			info.setAmount(table.getCellValue(i, 5));
			info.setInvoiceID(table.getCellValue(i, 7));
			remittance.add(info);
		}

		Browser.unregister(objs);
		
		return remittance;
	}
	
	public List<EFTRemittanceInfo> getAllRemittanceInfoByInvoiceNum(String schema,String number,boolean deductVendorFee,String... order) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"eFTInvoiceJobDetailsList_LIST", ".text",
				new RegularExpression("Remittance Number.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.rowCount();
		List<EFTRemittanceInfo> remittance = new ArrayList<EFTRemittanceInfo>();
		for (int i = 1; i < row ; i++) {
			if(number.equals(table.getCellValue(i, 7))){
				EFTRemittanceInfo info = new EFTRemittanceInfo();
				info.setRemittanceNumber(table.getCellValue(i, 0));
				info.setStatus(MiscFunctions.getRemittanceStatusCode(table.getCellValue(i, 1)));
				info.setVendorName(table.getCellValue(i, 2).split("\\-")[1].trim());
				info.setStoreName(table.getCellValue(i, 3).split("\\-")[1].trim());
				info.setAccountCode(table.getCellValue(i, 4));
				if(deductVendorFee && info.getAccountCode().contains("Vendor Fee"))
					info.setAmount("0");
				else
					info.setAmount(this.getRemittanceAmount(schema, this.getInvoicingJobId(), table.getCellValue(i, 0), order));
				info.setInvoiceID(table.getCellValue(i, 7));
				info.setInvoiceJobID(this.getInvoicingJobId());
				info.setRemittanceDate(this.getInvoiceDate());
				info.setPeriodDate(this.getPeriodEndDate());
				remittance.add(info);
			}
		}

		Browser.unregister(objs);
		
		return remittance;
	}
	
	
	
	public String getRemittanceAmount(String schema, String invoiceJobId, String remittanceId,String... ordNum) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		
		db.resetSchema(schema);
		String orders="";
		if (null !=ordNum) {
			for(int i=0;i<ordNum.length;i++){
				if(i==0){
					orders = "'" + ordNum[i] + "'";
				}else{
					orders = orders + ",'" + ordNum[i] + "'";
				}
			}	
		} 
		
		String sql = "select AMOUNT from F_DEFT where EFT_INVOICE_JOB_ID=" + invoiceJobId + " and EFT_REMITTANCE_ID=" + remittanceId ;
		
		if(orders.length()>2){
			sql = sql +" and ORD_NUM in ("+orders.trim()+")";
		}		 
		
		List<String> results = db.executeQuery(sql, "AMOUNT");
		BigDecimal amount=BigDecimal.ZERO;
		for(String amt:results){
			if(amt.contains("-")){
				amount=amount.subtract(new BigDecimal(amt.split("\\-")[1]));
			}else{
				amount=amount.add(new BigDecimal(amt));
			}
		}
		
		return amount.toString();
	}
	
	
	
	
	//=========================
	public void clearSearchCondition()
	{
		this.selectVendor(0);
		this.setAgentID(StringUtil.EMPTY);
		this.setAgentName(StringUtil.EMPTY);
		this.selectStatus(0);
		this.setAccountCode(StringUtil.EMPTY);
		this.setAccountDscr(StringUtil.EMPTY);
	}
	
	public void setSearchCondtion(EFTRemittanceInfo info)
	{
		if(!StringUtil.isEmpty(info.getVendorName()))
		{
			this.selectVendor(info.getVendorName());
		}
		
		if(!StringUtil.isEmpty(info.getStoreID()))
		{
			this.setAgentID(info.getStoreID());
		}
		
		if(!StringUtil.isEmpty(info.getStoreName()))
		{
			this.setAgentName(info.getStoreName());
		}
		
		if(!StringUtil.isEmpty(info.getStatus()))
		{
			this.selectStatus(info.getStatus());
		}
		
		if(!StringUtil.isEmpty(info.getAccountCode()))
		{
			this.setAccountCode(info.getAccountCode());
		}
		
		if(!StringUtil.isEmpty(info.getAccountDesc()))
		{
			this.setAccountDscr(info.getAccountDesc());
		}		
		
		
	}
	
	public void searchRemittance(EFTRemittanceInfo info)
	{
		this.clearSearchCondition();
		this.setSearchCondtion(info);
		this.clickGO();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTRemittanceInfo> getRemittanceRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTRemittanceInfo> records = new ArrayList<EFTRemittanceInfo>();
		int rows;
		int columns;
		EFTRemittanceInfo eft;
		
		do{
		objs = browser.getTableTestObject(".id", "eFTInvoiceJobDetailsList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find remittance table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrRemittanceTabInInvoicingJobsDetailPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTRemittanceInfo();
			eft.setRemittanceNum(table.getCellValue(i, table.findColumn(0, "Remittance Number")));
			eft.setStatus(table.getCellValue(i, table.findColumn(0, "Remittance Status")));
			eft.setVendorNumAndName(table.getCellValue(i, table.findColumn(0, "Vendor")));
			eft.setStoreIDAndName(table.getCellValue(i, table.findColumn(0, "Agent")));
			eft.setAccountCodeAndDesc(table.getCellValue(i, table.findColumn(0, "Account")));
			eft.setAmount(table.getCellValue(i, table.findColumn(0, "Remittance Amount")));
			eft.setInvoiceID(table.getCellValue(i, table.findColumn(0, "Invoice Number")));
			
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
