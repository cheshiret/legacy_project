package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

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
 * @author SWang
 * @Date  Jan 28, 2014
 */
public class LicMgrPrivilegeInventoryReplaceReasonWidget extends DialogWidget {
	private static LicMgrPrivilegeInventoryReplaceReasonWidget instance = null;

	private LicMgrPrivilegeInventoryReplaceReasonWidget() {
		super("Replace Reasons");
	}

	public static LicMgrPrivilegeInventoryReplaceReasonWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeInventoryReplaceReasonWidget();
		}
		return instance;
	}

	protected Property[] replaceReason(){
		return Property.toPropertyArray(".id", new RegularExpression("ReplaceInventoryReasonSelection-\\d+\\.selectedReason", false));
	}

	protected Property[] replaceNotes(){
		return Property.toPropertyArray(".id", new RegularExpression("ReplaceInventoryReasonSelection-\\d+\\.notes", false));
	}

	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(replaceReason());
	}
	
	public void selectReplaceReason(String reason){
		browser.selectDropdownList(replaceReason(), reason);
	}

	public void setReplaceNote(String note){
		browser.setTextField(replaceNotes(), note);
	}
	
	public void setReplaceReason(String reason, String note){
		selectReplaceReason(reason);
		setReplaceNote(note);
		clickOK();
		ajax.waitLoading();
	}
}
