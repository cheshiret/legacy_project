package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;


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
 * @Date  Mar 5, 2012
 */
public class LicMgrPrivilegeReturnDocumentWidget extends DialogWidget {
	
	private static LicMgrPrivilegeReturnDocumentWidget _instance = null;
	
	private LicMgrPrivilegeReturnDocumentWidget() {}
	
	public static LicMgrPrivilegeReturnDocumentWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeReturnDocumentWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "markDlg_docList");
	}
	
	public void setComments(String comments) {
		browser.setTextField(".id", new RegularExpression("UnreturnedDocView-\\d+\\.comments", false), comments);
	}
}
