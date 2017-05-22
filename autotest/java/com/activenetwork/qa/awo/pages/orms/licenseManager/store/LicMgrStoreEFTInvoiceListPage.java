package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreEFTInvoice;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrStoreEFTInvoiceListPage extends LicMgrStoreEFTInvoiceTopMeunPage{
	private static LicMgrStoreEFTInvoiceListPage _instance = null;

	protected LicMgrStoreEFTInvoiceListPage() {
	}

	public static LicMgrStoreEFTInvoiceListPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrStoreEFTInvoiceListPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return (super.exists())&&(browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "StoreInvoicesList_LIST"));
	}
	
	public void gotoInvoiceDetailByInvoiceNum(String number){
		browser.clickGuiObject(".class", "Html.A",".text",number);
	}

	/**
	 * get total all EFT invoice records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<StoreEFTInvoice> getStoreInvoiceRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<StoreEFTInvoice> records = new ArrayList<StoreEFTInvoice>();
		int rows;
		int columns;
		StoreEFTInvoice eft;
		
		do{
		objs = browser.getTableTestObject(".id", "StoreInvoicesList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Store invoice table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrStoreEFTInvoiceListPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new StoreEFTInvoice();
			eft.setInvoiceNumAndStatus(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Invoice Number",false))));
			eft.setInvoiceDateAndPeriodDate(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Invoice Date",false))));
			eft.setInvoiceAmount(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Invoice Amount",false))));
			eft.setSales(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Sales",false))));
			eft.setVoidPendingDocReturn(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Voids Pending Doc Return",false))));
			eft.setChargedVoid(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Charged Voids",false))));
			eft.setCreditVoid(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Credited Voids",false))));
			eft.setDeductVendorFee(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Deducted Vendor Fee",false))));
			eft.setStoreEFTAdjustment(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Store EFT Adjustment",false))));
			eft.setDepositAdjustment(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Deposit Adjustment",false))));
			eft.setVoucherInternalGC(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Voucher/Internal GC",false))));
			eft.setPaymentApply(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Payment Applied",false))));
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
