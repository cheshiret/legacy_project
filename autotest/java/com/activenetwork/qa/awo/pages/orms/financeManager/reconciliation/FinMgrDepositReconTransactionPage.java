package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
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

public class FinMgrDepositReconTransactionPage extends FinanceManagerPage{
	
	protected FinMgrDepositReconTransactionPage() {
	}

	private static FinMgrDepositReconTransactionPage _instance = null;

	public static FinMgrDepositReconTransactionPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrDepositReconTransactionPage();
		return _instance;
	}

	private static final String tableRex = " ?Payment ?Customer ?Collect Date.*";
	
	/** Determine if the deposit reconcilation transaction page exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.A",".text","Reconcile Non-Cash Depositable Transaction without Matching");
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression(tableRex,false));
	}
	
	public void selectPaymentRadio(){
		browser.selectRadioButton(".id", "_actionNonCashUnReconTransaction_");
	}
	
	public int getPaymentRadioNum(){
		IHtmlObject[] objs = browser.getRadioButton(".id", "_actionNonCashUnReconTransaction_");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	public boolean isMatchUnreconDepositTransactionButtonEnable(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Match Unreconciled Non-Cash Depositable Transaction");
	}
	
	public void clickMatchUnreconDepositTransactionButton(){
		browser.clickGuiObject(".class","Html.A",".text","Match Unreconciled Non-Cash Depositable Transaction");
	}
	
	public boolean isReconcileDepositTransactionWitoutMatchButtonEnable(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Reconcile Non-Cash Depositable Transaction without Matching");
	}
	
	public void clickReconcileDepositTransactionWitoutMatchButton(){
		browser.clickGuiObject(".class","Html.A",".text","Reconcile Non-Cash Depositable Transaction without Matching");
	}
	
	/**
	 * Get column Num for specific Column Name
	 * @param colName
	 * @return column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(tableRex, false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString().trim().equalsIgnoreCase(colName.trim())) {
					Browser.unregister(objs);
					return i;
				}
			}
		}else{
			throw new ItemNotFoundException("Non Cash Deposit Transactions Table Not Found.");
		}
		Browser.unregister(objs);
		return -1;
	}
	
	/**
	 * This method used to verify given column value are all given value
	 * @param colName column name
	 * @param value column value
	 */
	public void verifyPaymentList(String colName,String value){
		logger.info("Verify "+colName+" Column are all "+value);
		
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(tableRex, false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for(int i=1;i<tableGrid.rowCount();i++){
				String cellValue = tableGrid.getCellValue(i, colNum);
				if(!cellValue.equalsIgnoreCase(value)){
					throw new ErrorOnPageException("Cell Value is not equals given "+value);
				}
			}
		}else{
			throw new ErrorOnPageException("Parse Table Failed.");
		}
		Browser.unregister(objs);
	}
	
	public void clickCashSumarryTab(){
		browser.clickGuiObject(".class","Html.A",".text","Cash Depositable Summary");
	}
	
	public void clickDepositAdjustmentTab(){
		browser.clickGuiObject(".class","Html.A",".text","Non Cash Depositable Adjustments");
	}
	
	public String parseDepositTransactionTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("Deposit ID.*", false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		String text = grid.getProperty(".text");
		Browser.unregister(objs);
		
		return text;
	}
	
	public String getDepositStatus(){
		String text = parseDepositTransactionTable();
		String status = text.substring(text.indexOf("Status")+"Status".length(), text.indexOf("Created"));
		return status.trim();
	}
	
	public void clickDepositID(String depositId){
		browser.clickGuiObject(".class","Html.A",".text",depositId);
	}
}
