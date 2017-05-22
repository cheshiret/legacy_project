package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 11, 2012
 */
public class FinMgrMarkAsPaidWidget extends DialogWidget{
private static FinMgrMarkAsPaidWidget _instance = null;
	
	private FinMgrMarkAsPaidWidget() {
		
	}
	
	public static FinMgrMarkAsPaidWidget getInstance(){
		if(_instance == null){
			_instance = new FinMgrMarkAsPaidWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists();
	}
	
	public void setNote(String note){
		browser.setTextArea(".id", "paymentNote", note);
	}
}
