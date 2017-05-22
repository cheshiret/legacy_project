/*
 * Created on Jul 2, 2009
 *
 */
package com.activenetwork.qa.awo.keywords.orms.search;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsInvoiceSearchPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @author mchen
 * This class contains the most popular work flows for invoice searching.
 */
public class InvoiceSearch {
	private static InvoiceSearch _instance = null;

	protected static AutomationLogger logger = AutomationLogger.getInstance();

	protected String actualNum = "";

	public static InvoiceSearch getInstance() {
		if (null == _instance)
			_instance = new InvoiceSearch();

		return _instance;
	}

	/** The work flow is that set criteria and search invoice
	 * Start from homepage to invoice search page
	 * @param inv
	 * */
	public void invoiceSearch(InvoiceInfo inv) {

		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
		OrmsInvoiceSearchPage omInSearchPg = OrmsInvoiceSearchPage
				.getInstance();

		if (cmHmPg.exists()) {
			cmHmPg.clickCampingCall();
			cmTopMenuPg.waitLoading();
		}
		
		if(!omInSearchPg.exists()) {
			cmTopMenuPg.waitLoading();
			cmTopMenuPg.selectInvoice();
			cmTopMenuPg.clickSearch();
			omInSearchPg.waitLoading();
		}
		omInSearchPg.searchInvoice(inv);
		omInSearchPg.waitLoading();
	}

	/** Verify invoice according to invoice number
	 * @param invoice -- invoice number
	 * */

	public void verifySpecInvoiceInInvoiceSearchPg(String invoice) {
		OrmsInvoiceSearchPage omInSearchPg = OrmsInvoiceSearchPage
				.getInstance();
		List<List<String>> invoiceinfo = new ArrayList<List<String>>();
		invoiceinfo.clear();
		List<String> invoiceinforow = new ArrayList<String>();
		int colnum;

		omInSearchPg.waitLoading();
		omInSearchPg.setElementNum("50 rows per page");
		omInSearchPg.waitLoading();

		invoiceinfo = omInSearchPg.retriveInvoiceInfo();
		colnum = omInSearchPg.getColNum("Invoice #");

		if (invoiceinfo.size() > 0) {
			for (int i = 1; i < invoiceinfo.size(); i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				if (invoiceinforow.get(colnum).toString().equalsIgnoreCase(
						invoice)) {
					logger.info("success to find the invoice " + invoice);
					break;
				} else if (i == (invoiceinfo.size() - 1))
					throw new ItemNotFoundException("Fail to find the invoice "
							+ invoice);
			}
		} else
			throw new ItemNotFoundException("Fail to find the invoice "
					+ invoice);
	}

	/** verify searched invoice information in the invoice search page match the search criteria
	 * @param inv
	 **/
	public void verifyInvoiceInInvoiceSearchPg(InvoiceInfo inv) {
		OrmsInvoiceSearchPage omInSearchPg = OrmsInvoiceSearchPage
				.getInstance();
		List<List<String>> invoiceinfo = new ArrayList<List<String>>();
		List<String> invoiceinforow = new ArrayList<String>();
		int columnnum = 0;

		omInSearchPg.waitLoading();
		omInSearchPg.setElementNum("50 rows per page");
		omInSearchPg.waitLoading();

		invoiceinfo = omInSearchPg.retriveInvoiceInfo();

		if (null != inv.invoiceNum && inv.invoiceNum.length()>0) {
			columnnum = omInSearchPg.getColNum("Invoice #");
			for (int i=1;i<invoiceinfo.size();i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				if (!invoiceinforow.get(columnnum).toString().equalsIgnoreCase(
						inv.invoiceNum)){
					throw new ItemNotFoundException("The invoiceNum "
							+ invoiceinforow.get(columnnum)
							+ " doesn't match search criteria");
				}
			}
		}

		if (null != inv.invoiceFromDate && inv.invoiceFromDate.length()>0) {
			columnnum = omInSearchPg.getColNum("Invoice Created Date & Time");
			for (int i=1;i<invoiceinfo.size();i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				String createdDate = invoiceinforow.get(columnnum).toString();
							
				if(createdDate.equalsIgnoreCase("Invoice Created Date & Time")){
					continue;
				}
				String fromDate = DateFunctions.formatToFullDate(DateFunctions.parseDateString(createdDate,"EEE MMM d yyyy HH:mm aaa z"));

				if (!fromDate.toString().equalsIgnoreCase(
						DateFunctions.formatToFullDate(inv.invoiceFromDate))){
					throw new ItemNotFoundException("The invoice created date "
							+ invoiceinforow.get(columnnum)
							+ "doesn't match search criteria");
				}
			}
		}

		if (null != inv.firstName && inv.firstName.length()>0) {
			columnnum = omInSearchPg.getColNum("Customer Name");
			for (int i=1;i<invoiceinfo.size();i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				String customer = invoiceinforow.get(columnnum).toString();
				String[] firstN = customer.split(", ");
				if (!firstN[1].toString().equalsIgnoreCase(inv.firstName)){
					throw new ItemNotFoundException("The first name "
							+ invoiceinforow.get(columnnum)
							+ " doesn't match search criteria");
				}
			}
		}

		if (null != inv.lastName && inv.lastName.length()>0) {
			columnnum = omInSearchPg.getColNum("Customer Name");
			for (int i=1;i<invoiceinfo.size();i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				String customer = invoiceinforow.get(columnnum).toString();
				String[] lastN = customer.split(", ");
				if (!lastN[0].toString().equalsIgnoreCase(inv.lastName)){
					throw new ItemNotFoundException("The last name "
							+ invoiceinforow.get(columnnum)
							+ " doesn't match search criteria");
				}
			}
		}
		if (null != inv.invoicePhone && inv.invoicePhone.length()>0) {
			columnnum = omInSearchPg.getColNum("Customer Phone");
			for (int i=1;i<invoiceinfo.size();i++) {
				invoiceinforow = (List<String>) invoiceinfo.get(i);
				if (!invoiceinforow.get(columnnum).toString().equalsIgnoreCase(
						inv.invoicePhone)){
					throw new ItemNotFoundException("The phone"
							+ invoiceinforow.get(columnnum)
							+ "doesn't match search criteria");
				}
			}
		}
	}
}
