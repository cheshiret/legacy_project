package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:Finding path: Fnm->EFT->invoicing jobs->invoicing job
 *                      detail->invoice detail page-> payment allocation records
 *                      page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date Jun 7, 2012
 */
public class FinMgrInvoicePaymentAllocationRecordsPage extends
		FinMgrInvoiceDetailPage {

	private static FinMgrInvoicePaymentAllocationRecordsPage _instance = null;

	private FinMgrInvoicePaymentAllocationRecordsPage() {
	}

	public static FinMgrInvoicePaymentAllocationRecordsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrInvoicePaymentAllocationRecordsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.orderNum", false));
	}

	/**
	 * set order number
	 * 
	 * @param ordNum
	 */
	public void setOrderNumber(String ordNum) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.orderNum", false),
				ordNum);
	}

	/**
	 * set payment id
	 * 
	 * @param paymentId
	 */
	public void setPaymentID(String paymentId) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.paymentID", false),
				paymentId);
	}

	/**
	 * set remittance number
	 * 
	 * @param remittanceNum
	 */
	public void setRemittanceNumber(String remittanceNum) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"DailyEFTJobRecordsSearchCriteria-\\d+\\.remittanceNum",
						false), remittanceNum);
	}

	/**
	 * select product category
	 * 
	 * @param prodCategory
	 */
	public void selectProductCategory(String prodCategory) {
		browser.selectDropdownList(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.productCategoryID",
				false), prodCategory);
	}

	public void selectBlankProductCategory() {
		browser.selectDropdownList(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.productCategoryID",
				false), 0, false);
	}

	/**
	 * set product code
	 * 
	 * @param prodCode
	 */
	public void setProductCode(String prodCode) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.productCode", false),
				prodCode);
	}

	/**
	 * set product name
	 * 
	 * @param prodName
	 */
	public void setProductName(String prodName) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.productName", false),
				prodName);
	}

	/**
	 * set account code
	 * 
	 * @param account
	 */
	public void setAccountCode(String account) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.accountCode", false),
				account);
	}

	/**
	 * set account description
	 * 
	 * @param accountDscr
	 */
	public void setAccountDescription(String accountDscr) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.accountDescription",
				false), accountDscr);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * set all search criteria to search payment allocation records
	 * 
	 * @param ordNum
	 * @param paymentId
	 * @param remittanceNum
	 * @param productCategory
	 * @param prodCode
	 * @param prodName
	 * @param accountCode
	 * @param accountDscr
	 */
	public void searchByCriteria(String ordNum, String paymentId,
			String remittanceNum, String prodCategory, String prodCode,
			String prodName, String accountCode, String accountDscr) {
		this.setOrderNumber(ordNum);
		this.setPaymentID(paymentId);
		this.setRemittanceNumber(remittanceNum);
		if (!"".equals(prodCategory)) {
			this.selectProductCategory(prodCategory);
		} else {
			this.selectBlankProductCategory();
		}
		this.setProductCode(prodCode);
		this.setProductName(prodName);
		this.setAccountCode(accountCode);
		this.setAccountDescription(accountDscr);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void searchPaymentAllocationByOrderNum(String ordNum) {
		this.searchByCriteria(ordNum, "", "", "", "", "", "", "");
	}

	public void searchPaymentAllocationByPaymentID(String paymentId) {
		this.searchByCriteria("", paymentId, "", "", "", "", "", "");
	}

	public void searchPaymentAllocationByRemittanceNum(String remittanceNum) {
		this.searchByCriteria("", "", remittanceNum, "", "", "", "", "");
	}

	public void searchPaymentAllocationByProdCategory(String prodCategory) {
		this.searchByCriteria("", "", "", prodCategory, "", "", "", "");
	}

	public void searchPaymentAllocationByProdCode(String prodCode) {
		this.searchByCriteria("", "", "", "", prodCode, "", "", "");
	}

	public void searchPaymentAllocationByProdName(String prodName) {
		this.searchByCriteria("", "", "", "", "", prodName, "", "");
	}

	public void searchPaymentAllocationByAccountCode(String accountCode) {
		this.searchByCriteria("", "", "", "", "", "", accountCode, "");
	}

	public void searchPaymentAllocationByAccountDscr(String accountDscr) {
		this.searchByCriteria("", "", "", "", "", "", "", accountDscr);
	}

	/**
	 * get total amount per order identified by order number
	 * 
	 * @param orderNums
	 * @return
	 */
	public Map<String, Double> getAmountsByOrderNum(String... orderNums) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<String> amounts = new ArrayList<String>();
		Map<String, Double> map = new HashMap<String, Double>();
		for (String ordNum : orderNums) {
			searchPaymentAllocationByOrderNum(ordNum);

			do {
				objs = browser.getTableTestObject(".id",
						"EFTInvoiceDetailsList_LIST");
				if (objs.length < 1) {
					throw new ItemNotFoundException(
							"Can't find Payment Allocation table object.");
				}

				table = (IHtmlTable) objs[0];

				for (int i = 1; i < table.rowCount(); i++) {
					amounts = table.getColumnValues(6);// Amount
				}

			} while (gotoNext());

			double amountPerOrder = 0;
			String temp = "";
			for (int i = 0; i < amounts.size(); i++) {
				temp = amounts.get(i);
				if (temp.contains("(")) {
					temp = temp.replaceAll("\\(", "").replaceAll("\\)", "")
							.replaceAll("$", "");
					amountPerOrder -= Double.parseDouble(temp);
				} else {
					amountPerOrder += Double.parseDouble(temp.replaceAll("$",
							""));
				}
			}

			map.put(ordNum, amountPerOrder);
		}

		Browser.unregister(table);
		Browser.unregister(objs);
		return map;
	}

	/**
	 * get sum of all amount for all orders
	 * 
	 * @param orderNums
	 * @return
	 */
	public double getAllAmountByOrderNum(String... orderNums) {
		Map<String, Double> amountsPerOrder = getAmountsByOrderNum(orderNums);
		double allAmount = 0;
		Set<Map.Entry<String, Double>> set = amountsPerOrder.entrySet();
		for (Iterator<Map.Entry<String, Double>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, Double> entry = it.next();
			allAmount += entry.getValue();
		}

		return allAmount;
	}

	/**
	 * If "Next" button is available, click it
	 * 
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}

	public boolean verifyContentsInTheColumn(String colName, String value) {
		boolean result = true;

		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int col = table.findColumn(0, colName);
		List<String> values = table.getColumnValues(col);

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i);
			if (!info.contains(value)) {
				result &= false;
				logger.info(info + " is not contain " + value + " at row#" + i
						+ 1);
			}
		}

		Browser.unregister(objs);
		return result;
	}

	public List<String> getRemittanceNumber() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getColumnValues(5);
		List<String> number = new ArrayList<String>();

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i).split(" ")[3].trim();
			number.add(info);
		}

		Browser.unregister(objs);
		return number;
	}

	public List<String> getAccountCode(String name) {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getColumnValues(2);
		List<String> number = new ArrayList<String>();

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i).split(name)[1].trim();
			number.add(info);
		}

		Browser.unregister(objs);
		return number;
	}

}
