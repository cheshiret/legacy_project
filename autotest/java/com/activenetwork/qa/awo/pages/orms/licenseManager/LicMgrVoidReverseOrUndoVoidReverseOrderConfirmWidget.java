package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Feb 12, 2011
 */
public class LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget extends DialogWidget{
	private static LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget _instance = null;
	
	protected LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget() {
	}
	
	public static LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		 boolean flag1=super.exists();
		 boolean flag2=browser.checkHtmlObjectExists(".id",new RegularExpression("(VoidReasonSelection-\\d+\\.notes)|(VoidActivityRegistrationBean-\\d+\\.reason)",false));
	     return flag1 && flag2;
	}
	
	public void selectVoidOrReverseRadio(String voidOrReverse){
		if(voidOrReverse.equalsIgnoreCase("Void")){
			browser.selectRadioButton(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false), 0);
		}else if(voidOrReverse.equalsIgnoreCase("Reverse")){
			browser.selectRadioButton(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false),1);
		}
		ajax.waitLoading();
	}
	
	public void selectVoidOrReverseReason(String reason){
		if(null != reason && reason.length() > 0) {
			browser.selectDropdownList(".id",new RegularExpression("(VoidReasonSelection-\\d+\\.selectedReason)|(VoidActivityRegistrationBean-\\d+\\.reason)",false),reason, true);
		} else {
			browser.selectDropdownList(".id",new RegularExpression("(VoidReasonSelection-\\d+\\.selectedReason)|(VoidActivityRegistrationBean-\\d+\\.reason)",false),1, true);
		}
	}
	
	public void setNotes(String note){
		browser.setTextArea(".id",new RegularExpression("(VoidReasonSelection-\\d+\\.notes)|(VoidActivityRegistrationBean-\\d+\\.notes)",false),note);
	}
	
	public void setupConfirmInfo(String action,String reason,String note){
		this.selectVoidOrReverseRadio(action);
		setupConfirmInfo(reason, note);
	}
	
	public void setupConfirmInfo(String reason, String note) {
		this.selectVoidOrReverseReason(reason);
		this.setNotes(note);
		clickOK();
		ajax.waitLoading();
	}
}