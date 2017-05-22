package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class LicMgrStoreNotesAndAltersPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreNotesAndAltersPage _instance = null;
	
	protected LicMgrStoreNotesAndAltersPage() {}
	
	public static LicMgrStoreNotesAndAltersPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreNotesAndAltersPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	public void clickAddNoteAndAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	/**
	 * Check the 'Show Current Records only' check box
	 */
	public void checkShowCurretRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false));
	}
	
	/**
	 * Un-Check the 'Show Current Records only' check box
	 */
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false));
	}
	
	/**
	 * Select an option of 'Status' drop down list
	 * @param status
	 */
	public void selectStatus(String status) {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("NoteAlertFilter-\\d+\\.status", false));
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Status drop down list object.");
		}
		
		if(((ISelect)objs[0]).isEnabled()) {
			((ISelect)objs[0]).select(status);
		} else {
			throw new ErrorOnDataException("The Status drop down list is disabled.");
		}
	}
	
	/**
	 * Select option of 'Note/Alert Type' drop down list
	 * @param type
	 */
	public void selectNoteAlterType(String type) {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("NoteAlertFilter-\\d+\\.messageType", false));
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find 'Note/Alert Type' drop down list object.");
		}
		
		if(((ISelect)objs[0]).isEnabled()) {
			((ISelect)objs[0]).select(type);
		} else {
			throw new ErrorOnDataException("The 'Note/Alert Type' drop down list is disabled.");
		}
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
}
