/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC: Events.UIS
 * @Task#:
 * 
 * @author bzhang
 * @Date  May 16, 2011
 */
public class OrmsEventCorporateBillingPage extends OrmsPage{
	
	static private OrmsEventCorporateBillingPage _instance = null;

	
	protected OrmsEventCorporateBillingPage() {
	}

	static public OrmsEventCorporateBillingPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsEventCorporateBillingPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("Bill Summary To Date", false));
	}
	
	public void clickBillSummaryToDateTAB() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Bill Summary To Date", false));
	}

	public void clickPrePrintedBillsTAB() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Previously Printed Bills", false));
	}
	
	public void clickMakePaymentBtn() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Make Payment", false));
	}
	
	/**Get Reservations Table*/
	public IHtmlObject[] getReservationsTable() {
		return browser.getTableTestObject(".id", new RegularExpression(
				"grid_[0-9]+", false), ".text", new RegularExpression(
				"^Invoice # ", false));
	}
	
	/**
	 * select the CheckBox based on given reservation #
	 * @param resNum
	 */
	public void selectCheckBoxByGivenReservationNum(String resNum) {
		//boolean result = false;
		IHtmlObject[] objs = this.getReservationsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = 0;
		
		//get the row number based on the given resNum
		for (int i =1; i<table.rowCount();i++){
			if (table.getCellValue(i, 2).equalsIgnoreCase(resNum))
				{rowNum=i; 
				 break;} 
		}
		
		if (rowNum == 0){
			throw new ErrorOnDataException("There is not reservation records matching the given reservation#" + resNum);
		}
		
		//get the InvoiceNum		
		String invoiceId= table.getCellValue(rowNum, 1).toString();
		Browser.unregister(objs);
		selectCheckBoxByGivenInvoiceNum(invoiceId);
	}
	
	/**
	 * select the CheckBox based on given Invoice #
	 * @param InvoiceNum
	 */
	public void selectCheckBoxByGivenInvoiceNum(String InvoiceNum) {
		String pattern  = InvoiceNum;
		browser.selectCheckBox(".value",  new RegularExpression(pattern + ".*",false));
	}
	
	/**
	 * select the first check box in Bill Summary To Date search list
	 * @param InvoiceNum
	 */
	public void selectFirstCheckBox() {
		browser.selectCheckBox(".id",  "order", true);
	}
}
