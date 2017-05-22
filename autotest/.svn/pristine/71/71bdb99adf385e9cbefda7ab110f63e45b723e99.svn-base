/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 26, 2012
 */
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransmissionInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;



public class FinMgrInvoiceTransmissionPage extends FinMgrInvoiceDetailPage {

	private static FinMgrInvoiceTransmissionPage _instance = null;

	protected FinMgrInvoiceTransmissionPage() {
	}

	public static FinMgrInvoiceTransmissionPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrInvoiceTransmissionPage();
		}
		return _instance;
	}

	public boolean exists() {
		return (super.exists()&& 
		browser.checkHtmlObjectExists(".id", new RegularExpression("eftinvoicetrsmsList", false)));
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<EFTInvoiceTransmissionInfo> getTransmissionRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<EFTInvoiceTransmissionInfo> records = new ArrayList<EFTInvoiceTransmissionInfo>();
		int rows;
		int columns;
		EFTInvoiceTransmissionInfo eft;

		objs = browser.getTableTestObject(".id", "eftinvoicetrsmsList");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Invoice Transmission table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrInvoiceTransmissionPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			eft = new EFTInvoiceTransmissionInfo();
			eft.setCreateDate(table.getCellValue(i, table.findColumn(0, "Create Date/Time")));
			eft.setRecordID(table.getCellValue(i, table.findColumn(0, "ID")));
			eft.setStatus(table.getCellValue(i, table.findColumn(0, "Status")));
			eft.setTransDueDate(table.getCellValue(i, table.findColumn(0, "Transmission Due Date")));
			eft.setTransmissionID(table.getCellValue(i, table.findColumn(0, "Transmission ID")));
			eft.setTransJobID(table.getCellValue(i, table.findColumn(0, "Transmission Job ID")));
			eft.setInvoiceAdjID(table.getCellValue(i, table.findColumn(0, "Invoice Adjustment ID")));
			eft.setInvoicePaymentID(table.getCellValue(i, table.findColumn(0, "Invoice Payment ID")));
			eft.setApplyAmount(table.getCellValue(i, table.findColumn(0, "Applied Amount")));
			
			records.add(eft);			
		}

		Browser.unregister(objs);
		
		return records;
	}
	
	
}
