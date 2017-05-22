package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRunReconciliationDialogPage extends HtmlPopupPage{

	private static FinMgrRunReconciliationDialogPage _instance;
	
	protected FinMgrRunReconciliationDialogPage () {
		super("title","activeworks | outdoors Finance Manager");
	}
	
	public static FinMgrRunReconciliationDialogPage getInstance() {
		if(null==_instance) {
			_instance=new FinMgrRunReconciliationDialogPage();
		}
		
		return _instance;
	}
	
	/**
	 * check ok button exist or not
	 * @return
	 */
	public boolean checkOkBtnExists() {
		return popup.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}

	public void clickDialogOkBtn() {
		popup.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickDialogCanceBtn() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
