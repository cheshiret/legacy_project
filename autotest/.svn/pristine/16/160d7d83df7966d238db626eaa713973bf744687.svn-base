package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class FinMgrReconcileCashTransactionPage extends FinanceManagerPage{

	protected FinMgrReconcileCashTransactionPage() {
	}

	private static FinMgrReconcileCashTransactionPage _instance = null;

	public static FinMgrReconcileCashTransactionPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrReconcileCashTransactionPage();
		return _instance;
	}

	/** Determine if the Cash Depositable Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text","Total Cash Amount");
	}
	
	public boolean isMatchCashTransactionButtonEnable(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Match Unreconciled Cash Depositable Transactions");
	}
	
	public void clickMatchUnreconCashTransactionButton(){
		browser.clickGuiObject(".class","Html.A",".text","Match Unreconciled Cash Depositable Transactions");
	}
	
	public boolean isReconcileCashTransactionWitoutMatchButtonEnable(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Reconcile Cash Depositable Transactions without Matching");
	}
	
	public void clickReconcileCashTransactionWitoutMatchButton(){
		browser.clickGuiObject(".class","Html.A",".text","Reconcile Cash Depositable Transactions without Matching");
	}
	
	public String parseCashSummaryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("Total Cash Amount.*", false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		String text = grid.getProperty(".text");
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * This method used to verify manual reconcile flag is given flag
	 * @param flag
	 */
	public void verifyManualReconcileFlag(String flag){
		String text = parseCashSummaryTable();
		String reconcileFlag = text.substring(text.indexOf("Manually Reconciled")+"Manually Reconciled".length()).trim();
		if(!reconcileFlag.equalsIgnoreCase(flag)){
			throw new ErrorOnPageException("Manually Reconciled Flag is not "+flag);
		}
	}
}
