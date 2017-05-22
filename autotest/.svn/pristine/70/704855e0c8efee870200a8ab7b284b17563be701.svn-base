/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 26, 2012
 */


import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

public class FinMgrHoldNoteWidget extends DialogWidget{
private static FinMgrHoldNoteWidget _instance = null;
	
	private FinMgrHoldNoteWidget() {
		
	}
	
	public static FinMgrHoldNoteWidget getInstance(){
		if(_instance == null){
			_instance = new FinMgrHoldNoteWidget();
		}
		
		return _instance;
	}
	
	public void setNote(String note){
		browser.setTextArea(".id", "paymentNote", note);
	}
}
