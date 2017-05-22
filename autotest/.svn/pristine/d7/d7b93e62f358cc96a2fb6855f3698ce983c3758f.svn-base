package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;

public class FinMgrReconcileConfirmActionPage extends FinanceManagerPage{
	
	protected FinMgrReconcileConfirmActionPage() {
	}

	private static FinMgrReconcileConfirmActionPage _instance = null;

	public static FinMgrReconcileConfirmActionPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrReconcileConfirmActionPage();
		return _instance;
	}

	/** Determine if the reconcile deposit confirm action page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TEXTAREA",".id","_confirmationnote");
	}
	
	public void setNote(String note){
		browser.setTextArea(".id", "_confirmationnote", note);
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
}
