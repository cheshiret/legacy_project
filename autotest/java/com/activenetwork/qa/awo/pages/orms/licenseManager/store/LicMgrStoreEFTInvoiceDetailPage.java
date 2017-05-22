/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DailyEFTSummary;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicePaymentApply;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author szhou
 *
 */
public class LicMgrStoreEFTInvoiceDetailPage extends LicMgrStoreEFTInvoiceTopMeunPage{
	private static LicMgrStoreEFTInvoiceDetailPage _instance = null;

	protected LicMgrStoreEFTInvoiceDetailPage() {
	}

	public static LicMgrStoreEFTInvoiceDetailPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrStoreEFTInvoiceDetailPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "invoiceDailySummaryList");
	}
	
	public void gotoEFTBreakdownByPostDate(String date){
		browser.clickGuiObject(".class", "Html.A",".text",date);
	}
	
	public void clickShowEFTInvoicePaymentApply(){
		browser.clickGuiObject(".class", "Html.A",".id","showPaymentButtonAnchor");
	}
	
	

	/**
	 * get total all EFT invoice Payments Applied records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTInvoicePaymentApply> getPaymentApplyRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTInvoicePaymentApply> records = new ArrayList<EFTInvoicePaymentApply>();
		int rows;
		int columns;
		EFTInvoicePaymentApply eft;
		
		
		objs = browser.getTableTestObject(".id", "invoiceDailySummaryList");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find store EFT invoice payment apply table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrStoreEFTInvoiceDetailPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTInvoicePaymentApply();
			eft.setAjustmentID(table.getCellValue(i, table.findColumn(0, "Invoice Adjustment ID")));
			eft.setPaymentID(table.getCellValue(i, table.findColumn(0, "Invoice Payment ID")));
			eft.setApplyAmount(table.getCellValue(i, table.findColumn(0, "Applied Amount")));
			eft.setApplyDateTime(table.getCellValue(i, table.findColumn(0, "Applied Date/Time")));
			eft.setApplyLocation(table.getCellValue(i, table.findColumn(0, "Applied Location")));
			eft.setApplyUser(table.getCellValue(i, table.findColumn(0, "Applied User")));
			records.add(eft);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * get total all Daily EFT Summary records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<DailyEFTSummary> getDailyEFTSummaryRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<DailyEFTSummary> records = new ArrayList<DailyEFTSummary>();
		int rows;
		int columns;
		DailyEFTSummary eft;
		
		
		objs = browser.getTableTestObject(".id", "invoiceDailySummaryList");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Daily EFT Summary table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrStoreEFTInvoiceDetailPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new DailyEFTSummary();
			eft.setPostedDate(table.getCellValue(i, table.findColumn(0, "Posted Date")));
			eft.setReceiptsNum(table.getCellValue(i, table.findColumn(0, "# of Receipts")));
			eft.setSales(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Sales",false))));
			eft.setVoidPendingDocReturn(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Voids Pending\\s+Doc Return",false))));
			eft.setChargeVoid(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Charged Voids",false))));
			eft.setCreditVoid(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Credited Voids",false))));
			eft.setDeductVendorFee(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Deducted\\s+Vendor Fees",false))));
			eft.setStoreEFTAdjustment(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Store EFT\\s+Adjustments",false))));
			eft.setDepositAdjustment(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Deposit\\s+Adjustments",false))));
			eft.setVoucherInternalGC(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Voucher /\\s+Internal GC",false))));
			eft.setNetAmount(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Net\\s+Amount",false))));
			records.add(eft);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * @param: postedDate, record of which the posted Date is 'postedDate'
	 * @param: type, (Sales/Voids Pending Doc Return/Charged Voids/...   this is the column name of list table)
	 * */
	public void clickPaymentAllocation(String postedDate, String type){
		IHtmlObject []objs = null;
		IHtmlObject []trObjs = null;
		IHtmlObject []tdObjs = null;
		IHtmlObject []target = null;
		IHtmlTable table = null;
		
		int typeColumn;
		
		objs = browser.getTableTestObject(".id", "invoiceDailySummaryList");
		table = (IHtmlTable)objs[0];
		
		typeColumn = table.findColumn(0, new RegularExpression("^"+type,false));
		
		if(typeColumn==-1)
		{
			throw new ErrorOnPageException("Cannot find column of which the name is-->"+type);
		}
		postedDate = DateFunctions.formatDate(postedDate, "EEE MMM d yyyy");
		trObjs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+postedDate,false),objs[0]);
		tdObjs = browser.getHtmlObject(".class", "Html.TD", trObjs[0]);
		target = browser.getHtmlObject(".class", "Html.A", tdObjs[typeColumn]);
		
		browser.clickGuiObject(".class", "Html.A", ".onclick",target[0].getProperty("onclick"));
				
		
		Browser.unregister(target);
		Browser.unregister(tdObjs);
		Browser.unregister(trObjs);
		Browser.unregister(objs);
		
		
	}
	
	
}
