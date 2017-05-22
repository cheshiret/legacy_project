package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

public class InvMgrConfirmDialogWidget extends DialogWidget{
	
	private static InvMgrConfirmDialogWidget instance = null;

	private InvMgrConfirmDialogWidget() {
		super("Please Confirm");
	}

	public static InvMgrConfirmDialogWidget getInstance() {
		if (instance == null) {
			instance = new InvMgrConfirmDialogWidget();
		}
		return instance;
	}

	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
  }
}
