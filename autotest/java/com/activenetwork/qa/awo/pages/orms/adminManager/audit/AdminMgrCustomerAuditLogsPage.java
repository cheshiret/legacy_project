package com.activenetwork.qa.awo.pages.orms.adminManager.audit;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 15, 2012
 */
public class AdminMgrCustomerAuditLogsPage extends AdminMgrAuditLogsPage {
	private static AdminMgrCustomerAuditLogsPage _instance = null;
	
	private AdminMgrCustomerAuditLogsPage() {}
	
	public static AdminMgrCustomerAuditLogsPage getInstance() {
		if(_instance == null) {
			_instance = new AdminMgrCustomerAuditLogsPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "customerAuditLogSearchCriteria.contract");
	}
}
