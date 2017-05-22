package com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment;


import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

public class FinMgrConfirmDialogWidget extends DialogWidget{
	
	private static FinMgrConfirmDialogWidget instance = null;

	private FinMgrConfirmDialogWidget() {
		super("Please Confirm");
	}

	public static FinMgrConfirmDialogWidget getInstance() {
		if (instance == null) {
			instance = new FinMgrConfirmDialogWidget();
		}
		return instance;
	}

	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
  }
}
