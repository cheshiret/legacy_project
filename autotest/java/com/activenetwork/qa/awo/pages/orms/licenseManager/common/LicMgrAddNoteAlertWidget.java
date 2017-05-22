package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:Add note/alert widget for order.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 17, 2012
 */
public class LicMgrAddNoteAlertWidget extends DialogWidget {

	private static LicMgrAddNoteAlertWidget _instance = null;
	
	protected LicMgrAddNoteAlertWidget() {}
	
	public static LicMgrAddNoteAlertWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAddNoteAlertWidget();
		}
		
		return _instance;
	}

	/**Select note and alert type*/
	public void selectype(String type) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.messageType",false);
		browser.selectDropdownList(".class", "Html.SELECT",".id", regx, type,true);
	}
	
	/**Input note and alert*/
	public void setNoteAndAlert(String note) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.message",false);
		browser.setTextArea(".id", regx, note, true);
	}
	
	private void clickTypeSpace(){
		browser.clickGuiObject(".class","Html.TD",".text", "Note/Alert Type");
	}
	
	/**Input the start date*/
	public void setStartDate(String startdate) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.startDate_ForDisplay",false);
		browser.setTextField(".id", regx, startdate,true);
		this.clickTypeSpace();
	}

	/**Input the End date*/
	public void setEndDate(String enddate) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.endDate_ForDisplay",false);
		browser.setTextField(".id", regx, enddate,true);
		this.clickTypeSpace();
	}
	
	public void selectApp(){
		browser.selectCheckBox(".id", new RegularExpression("NoteAlertMsgView-\\d+.fieldApplicationIDs_\\d+", false));
	}
	
	public void unselectApp(){
		browser.unSelectCheckBox(".id", new RegularExpression("NoteAlertMsgView-\\d+.fieldApplicationIDs_\\d+", false));
	}
	
	/**This method set up all the information for noteAlert*/
	public void setNoteAlertInfo(NoteAndAlertInfo info) {
		selectype(info.type);
		ajax.waitLoading();
		this.waitLoading();

		if (info.text.length() > 0) {
			setNoteAndAlert(info.text);
		}
		
		if("Alert".equals(info.type)){
			// type is alert, need to set start and end date.
			if (null != info.startDate) {
				setStartDate(info.startDate);
			}
			if (null != info.endDate) {
				setEndDate(info.endDate);
			}
			// when type is note, can't be selected.
			if(null != info.application){
				if(!StringUtil.isEmpty(info.application.get("License Manager"))){
					if(!"true".equalsIgnoreCase(info.application.get("License Manager"))){
						this.unselectApp();
					} else {
						this.selectApp();
					}
				}
			}
		}
	}

	/**Check if there is any error message*/
	public boolean checkErrorExist(){
			return browser.checkHtmlObjectExists(".class","Html.DIV",".className", "message msgerror");
	}
	
	/**Get error message*/
	public String getErrorMess(){
		return browser.getObjectText(".class","Html.DIV",".className", "message msgerror");
	}
	
	/**Get content of noteAlert*/
	public String getNoteAlertText() {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.message",false);
		return browser.getTextAreaValue(".id", regx);
	}
}
