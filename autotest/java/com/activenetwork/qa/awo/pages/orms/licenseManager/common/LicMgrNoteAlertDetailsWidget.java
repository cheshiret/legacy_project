package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Click Note/Alert id to go to note/alert details page.
 * Main page is order details page.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 17, 2012
 */
public class LicMgrNoteAlertDetailsWidget extends DialogWidget {

	private static LicMgrNoteAlertDetailsWidget _instance = null;
	
	protected LicMgrNoteAlertDetailsWidget() {}
	
	public static LicMgrNoteAlertDetailsWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrNoteAlertDetailsWidget();
		}
		
		return _instance;
	}
	
	private String prefix = "NoteAlertMsgView-\\d+\\.";
	public String getType(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"messageType:CB_TO_NAME", false));
	}

	public String getID(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"ID", false));
	}
	
	public String getText(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix+"message", false));
	}
	
	public boolean getStatus(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"active", false));
	}
	
	public boolean getApp(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"fieldApplicationIDs_\\d+", false));
	}
	
	public String getStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"startDate_ForDisplay", false));
	}

	public String getEndDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"endDate_ForDisplay", false));
	}
	
	
	public void verifyAlertDetail(NoteAndAlertInfo info){
		boolean result = true;
		logger.info("Verify Alert details info.");
		
		result &= MiscFunctions.compareResult("ID", info.id, this.getID());
		result &= MiscFunctions.compareResult("Type", info.type, this.getType());
		result &= MiscFunctions.compareResult("Text", info.text, this.getText());
		if(info.type.equals("Alert")){
			result &= MiscFunctions.compareResult("Start Date", info.startDate, this.getStartDate());
			result &= MiscFunctions.compareResult("End Date", info.endDate, this.getEndDate());
		}
		result &= MiscFunctions.compareResult("Status", true, this.getStatus());

		String expectApp = info.application.get("License Manager");
		if(StringUtil.isEmpty(expectApp)){
			// default value is selected.
			result &= MiscFunctions.compareResult("Applications", true, this.getApp());
		} else {
			// default value is selected.
			result &= MiscFunctions.compareResult("Applications", expectApp, this.getApp());
		}
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
}
