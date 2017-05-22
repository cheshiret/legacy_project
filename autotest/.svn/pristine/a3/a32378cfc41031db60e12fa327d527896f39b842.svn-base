package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.Map.Entry;

import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrCustomerAddNoteAlertPage extends DialogWidget{
	private static LicMgrCustomerAddNoteAlertPage instance=null;

	private LicMgrCustomerAddNoteAlertPage(){}

	public static LicMgrCustomerAddNoteAlertPage getInstance(){
		if(instance==null){
			instance=new LicMgrCustomerAddNoteAlertPage();
		}
		return instance;
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
	
	/**Input the start date*/
	public void setStartDate(String startdate) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.startDate_ForDisplay",false);
		browser.setCalendarField(".id", regx, startdate,true);
	}

	/**Input the End date*/
	public void setEndDate(String enddate) {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.endDate_ForDisplay",false);
		browser.setCalendarField(".id", regx, enddate,true);
	}
	/**This method set up all the information for noteAlert*/
	public void setNoteAlertInfo(NoteAndAlertInfo noteAlertInfo) {
		selectype(noteAlertInfo.type);
		ajax.waitLoading();
		waitLoading();
		if (StringUtil.notEmpty(noteAlertInfo.text)) {
			setNoteAndAlert(noteAlertInfo.text);
		}
		if (StringUtil.notEmpty(noteAlertInfo.text)) {
			setStartDate(noteAlertInfo.startDate);
		}
		if (StringUtil.notEmpty(noteAlertInfo.endDate)) {
			setEndDate(noteAlertInfo.endDate);
		}
		
		// when type is note, can't be selected.
		if(null != noteAlertInfo.application){
			for(Entry<String,String> e : noteAlertInfo.application.entrySet()){
				if(e.getValue().equalsIgnoreCase("true")){
					selectApplication(e.getKey());
				}else{
					unSelectApplication(e.getKey());
				}
			}
		}
	}
	
	private String getApplicationLabelForValue(String application){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", application);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Application Label object. Application = " + application);
		}
		String value = objs[0].getProperty(".for");
		Browser.unregister(objs);
		
		return value;
	}
	
	public void selectApplication(String application){
		String id = this.getApplicationLabelForValue(application);
		browser.selectCheckBox(".id", id);
	}
	
	public void unSelectApplication(String application){
		String id = this.getApplicationLabelForValue(application);
		browser.unSelectCheckBox(".id", id);
	}

	/**Check if there is any error message*/
	public boolean checkErrorExist(){
			return browser.checkHtmlObjectExists(".class","Html.DIV",".className", "message msgerror");
		}
	/**Get error message*/
	public String getErrorMess(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".className", "message msgerror");
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find error message.");
		}
		String message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
	/**Get content of noteAlert*/
	public String getNoteAlertText() {
		RegularExpression regx=new RegularExpression("NoteAlertMsgView-\\d+\\.message",false);
		IHtmlObject objs[] = browser.getTextArea(Property.toPropertyArray(".id", regx));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find message object.");
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;		
	}
	
}
