package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Apr 25, 2013
 */
public class CallMgrErrorConfirmDialogWidget extends DialogWidget{
private static CallMgrErrorConfirmDialogWidget _instance = null;
	
	private CallMgrErrorConfirmDialogWidget() {
		super("Error");
	}
	
	public static CallMgrErrorConfirmDialogWidget getInstance() {
		if(_instance == null) {
			_instance = new CallMgrErrorConfirmDialogWidget();
		}
		
		return _instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
