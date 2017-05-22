/*
 * Created on Jan 22, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDistributionDetailPage extends FinanceManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDistributionDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDistributionDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDistributionDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrDistributionDetailPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Distribution Detail by Recipient");
	}
	
	/**
	 * Select search type from drop down list
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
	  	browser.selectDropdownList(".id", "srch.dtl.type", searchType);
	}
	
	public void setSearchValue(String searchValue) {
	  	browser.setTextField(".id", "srch.dtl.input", searchValue);
	}
	
	public void selectItemType(String itemType) {
	  	browser.selectDropdownList(".id", "srch.dtl.itemType", itemType);
	}
	
	public void selectTransactionType(String transactionType){
		browser.selectDropdownList(".id", "srch.dtl.tranType", transactionType);
	}
	
	public void clickGo() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search", false));
	}
	
	public void clickDistributionRecipientSummaryTab(){
		browser.clickGuiObject(".class","Html.A",".text","Distribution Recipient Summary");
	}
	
	/**
	 * This method used to search distribution details by order number and item type
	 * @param orderNum
	 * @param itemType
	 */
	public void searchByOrderAndFeeType(String orderNum,String itemType) {
		selectSearchType("Order");
		setSearchValue(orderNum);
		selectItemType(itemType);	
	  	clickGo();
	  	waitLoading();
	}
	
	/**
	 * This method used to search distribution detail by order number, item type and transaction type
	 * @param orderNum
	 * @param itemType
	 * @param transactionType
	 */
	public void searchByOrderItemTypeAndTransactionType(String orderNum,String itemType,String transactionType){
		selectTransactionType(transactionType);
		this.searchByOrderAndFeeType(orderNum, itemType);
	}
	
	/**
	 * This method used to check given order exists in distribution list
	 * @param orderNum
	 */
	public void checkGivenOrderExists(String orderNum) {
	  	if(!browser.checkHtmlObjectExists(".class", "Html.TD", ".text", orderNum)){
	  	  throw new ItemNotFoundException("Run Distribution Fail,not found given order " + orderNum);
	  	}
	  	logger.info("Given Order is in the distribution list");
	}

	/**
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyDistributionInfo(String colName, String value) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(
				"^Payment/Refund/RA Fee/Deposit.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		if (tableGrid.rowCount() > 1) {
			if (!tableGrid.getCellValue(1, colNum).trim().equals(value)) {
				Browser.unregister(objs);
				logger.error("Distribution Info "
						+ tableGrid.getCellValue(1, colNum)
						+ " is not Correct! ");
				throw new ItemNotFoundException(tableGrid.getCellValue(1,
						colNum) + " is different with given value " + value);
			}
		} else {
			Browser.unregister(objs);
			throw new ItemNotFoundException("No Distribution Info Found.");
		}
		Browser.unregister(objs);

		logger.info("Distribution Info Correct.");
	}

	/**
	 * Get Column Number by Column Name
	 * 
	 * @param colName
	 * @return Column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(
				"^Payment/Refund/RA Fee/Deposit.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	public void searchByOrderNum(String ordNum) {
		selectSearchType("Order");
		setSearchValue(ordNum);
		clickGo();
		waitLoading();
	}

	public int verifyDistributedFeeAmt(String feeType, String expectedValue) {
		String valueColName = "Revenue";
		if (feeType.contains(OrmsConstants.FEETYPE_NAME_TAX)) {
			valueColName = "Tax";
		} else if (feeType
				.equalsIgnoreCase(OrmsConstants.FEETYPE_NAME_RAFEE)) {
			valueColName = "Total Expense";
		}
		int colNum = getColNum(valueColName);
		RegularExpression reg = new RegularExpression(
				"^Payment/Refund/RA Fee/Deposit.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", reg);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int rowNum = 1;
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, tableGrid.findColumn(0, "Item Type"))
					.equalsIgnoreCase(feeType)) {
				rowNum = i;
				break;
			}
		}
		String value = tableGrid.getCellValue(rowNum, colNum)
				.replaceAll("\\(|\\)|\\$|,", "").trim();
		Browser.unregister(objs);
		if (!value.equals(expectedValue)) {
			logger.error("Distributed fee amount was wrong. Exptected:"
					+ expectedValue + " and Actual:" + value);
			if (Math.abs(new Double(value) - new Double(expectedValue)) > 0.01)
				return 2;
			else
				return 1;
		} else {
			logger.info("Distributed fee amount " + feeType
					+ " was correct as " + expectedValue);
			return 0;
		}
	}
}
