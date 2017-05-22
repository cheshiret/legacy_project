package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 2, 2013
 */
public class LicMgrReverseApplicationOrderWidget extends DialogWidget {
	private static LicMgrReverseApplicationOrderWidget _instance = null;
	
	private LicMgrReverseApplicationOrderWidget() {
		super("Reverse Reasons");
	}
	
	public static LicMgrReverseApplicationOrderWidget getInstance() {
		if(_instance == null) _instance = new LicMgrReverseApplicationOrderWidget();
		return _instance;
	}
	
	private Property[] reverseReason() {
		return Property.toPropertyArray(".id", new RegularExpression("VoidReasonSelection-\\d+\\.selectedReason", false));
	}
	
	private Property[] reverseNotes() {
		return Property.toPropertyArray(".id", new RegularExpression("VoidReasonSelection-\\d+\\.notes", false));
	}
	
	public void selectReverseReason(String reason) {
		browser.selectDropdownList(reverseReason(), reason);
	}
	
	public void setReverseNotes(String note) {
		browser.setTextArea(reverseNotes(), note);
	}
}
