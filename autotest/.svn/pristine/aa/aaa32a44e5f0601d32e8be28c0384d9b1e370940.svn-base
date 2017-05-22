package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 11, 2014
 */
public class LicMgrRevokeLicenceAwardWidget extends DialogWidget {
	private static LicMgrRevokeLicenceAwardWidget _instance = null;
	
	private LicMgrRevokeLicenceAwardWidget() {
		super("Revoke Licence Award");
	}
	
	public static LicMgrRevokeLicenceAwardWidget getInstance() {
		if(_instance == null) _instance = new LicMgrRevokeLicenceAwardWidget();
		return _instance;
	}
	
	private Property[] revokeAwardReason() {
		return Property.toPropertyArray(".id", new RegularExpression("AwardRevokeDialog-\\d+\\.selectedReason", false));
	}
	
	private Property[] revokeAwardNotes() {
		return Property.toPropertyArray(".id", new RegularExpression("AwardRevokeDialog-\\d+\\.notes", false));
	}
	
	public void selectRevokeAwardReason(String reason) {
		browser.selectDropdownList(revokeAwardReason(), reason);
	}
	
	public void setRevokeNotes(String note) {
		browser.setTextArea(revokeAwardNotes(), note);
	}
}
