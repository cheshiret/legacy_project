/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorEFTInvoice;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;



/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 27, 2012
 */

public class LicMgrVendorEFTInvoiceListPage extends LicMgrVendorEFTInvoiceTopMeunPage{
	private static LicMgrVendorEFTInvoiceListPage _instance = null;

	protected LicMgrVendorEFTInvoiceListPage() {
	}

	public static LicMgrVendorEFTInvoiceListPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVendorEFTInvoiceListPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return (super.exists())&&(browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "StoreInvoicesList_LIST"));
	}

	/**
	 * get total all EFT invoice records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<VendorEFTInvoice> getVendorInvoiceRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<VendorEFTInvoice> records = new ArrayList<VendorEFTInvoice>();
		int rows;
		int columns;
		VendorEFTInvoice eft;
		
		do{
		objs = browser.getTableTestObject(".id", "StoreInvoicesList_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Vendor invoice table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrVendorEFTInvoiceListPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new VendorEFTInvoice();
			eft.setInvoiceNumAndStatus(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Invoice Number",false))));
			eft.setInvoiceDateAndPeriodDate(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Invoice Date",false))));
			eft.setAgent(table.getCellValue(i, table.findColumn(0, new RegularExpression("^Agent",false))));
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
