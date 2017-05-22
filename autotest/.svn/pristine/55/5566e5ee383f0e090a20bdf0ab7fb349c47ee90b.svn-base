package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 14, 2012
 */
public class CallMgrConfirmDialogWidget extends DialogWidget {
	
	private static CallMgrConfirmDialogWidget _instance = null;
	
	private CallMgrConfirmDialogWidget() {
		super("Please Confirm");
	}
	
	public static CallMgrConfirmDialogWidget getInstance() {
		if(_instance == null) {
			_instance = new CallMgrConfirmDialogWidget();
		}
		
		return _instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
