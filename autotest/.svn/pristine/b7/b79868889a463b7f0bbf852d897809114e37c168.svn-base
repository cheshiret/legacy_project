package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 8, 2013
 */
public class OrmsAdjustFeeToPastPaidWidget extends DialogWidget {

	private static OrmsAdjustFeeToPastPaidWidget _instance = null;
	
	private OrmsAdjustFeeToPastPaidWidget() {
		super("Adjust fee to past paid");
	}
	
	public static OrmsAdjustFeeToPastPaidWidget getInstance() {
		if(_instance == null) _instance = new OrmsAdjustFeeToPastPaidWidget();
		return _instance;
	}
	
	public void setAdjustmentNote(String note) {
		browser.setTextArea(".id", new RegularExpression("(Marina)?AdjustFeesToPastPaidDialog.adjustmentNotes", false), note);
	}
}
