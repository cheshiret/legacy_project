package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 25, 2011
 */
public class LicMgrStoreEquipmentPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreEquipmentPage _instance = null;
	
	protected LicMgrStoreEquipmentPage() {}
	
	public static LicMgrStoreEquipmentPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreEquipmentPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists("", "", "", "");
	}
}
