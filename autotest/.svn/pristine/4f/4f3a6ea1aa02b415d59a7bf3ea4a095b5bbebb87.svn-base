package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:The sub page for order details page.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 17, 2012
 */
public class LicMgrNoteAndAlertListPage extends LicMgrOrderDetailsCommonPage {

	private static LicMgrNoteAndAlertListPage _instance = null;
	
	protected LicMgrNoteAndAlertListPage() {}
	
	public static LicMgrNoteAndAlertListPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrNoteAndAlertListPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		// using Add Note/Alert button as page mark
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	public void clickAddNoteAndAlert(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert", true);
	}

	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
	}
	
	public void clickDeactive(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}
	
	
	public void selectShowCurrentRecordsOnly(){
		browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+.checked", false));
	}
	
	public void unselectShowCurrentRecordsOnly(){
		browser.unSelectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+.checked", false));
	}
	
	public void setStatus(String status){
		if(StringUtil.isEmpty(status)){
			browser.selectDropdownList(".id", new RegularExpression("NoteAlertFilter-\\d+.status", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("NoteAlertFilter-\\d+.status", false), status);
		}
	}
	
	public void setType(String type){
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("NoteAlertFilter-\\d+.messageType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("NoteAlertFilter-\\d+.messageType", false), type);
		}
	}
	
	public String searchNoteAndAlert(NoteAndAlertInfo info){
		if(info.isShowCurrentRecordsOnly){
			this.selectShowCurrentRecordsOnly();
		} else {
			this.unselectShowCurrentRecordsOnly();
		}
		ajax.waitLoading();
		browser.waitExists();
		
		this.setStatus(info.status);
		this.setType(info.type);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
		
		String msg = StringUtil.EMPTY;
		if(isMsgExist()){
			msg = this.getMsg();// No results found.
		}
		return msg;
	}

	public boolean isMsgExist(){
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}
	
	public String getMsg(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public List<NoteAndAlertInfo> getNoteAlertList(){
		IHtmlTable table = this.getNoteAlertTable();
		
		List<NoteAndAlertInfo> list = new ArrayList<NoteAndAlertInfo>();
		NoteAndAlertInfo info;
		for(int i=1; i<table.rowCount(); i++){
			info = new NoteAndAlertInfo();
			info.id = table.getCellValue(i, 1);
			info.startDate = table.getCellValue(i, 2);
			info.endDate = table.getCellValue(i, 3);
			info.type = table.getCellValue(i, 4);
			info.text = table.getCellValue(i, 5);
			info.status = table.getCellValue(i, 6);
			info.createUser = table.getCellValue(i, 7);
			list.add(info);
		}
		return list;
	}

	private IHtmlTable getNoteAlertTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "notealertlist");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find note and alert list.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	private List<String> getColumnByName(String columnName){
		IHtmlTable table = this.getNoteAlertTable();

		int column = table.findColumn(0, columnName);
		List<String> columnList = table.getColumnValues(column);
		columnList.remove(0);
		return columnList;
	}
	
	public List<String> getStartDateList(){
		return this.getColumnByName("Start Date");
	}

	public List<String> getEndDateList(){
		return this.getColumnByName("End Date");
	}

	public List<String> getTypeList(){
		return this.getColumnByName("Type");
	}

	public List<String> getStatusList(){
		return this.getColumnByName("Status");
	}
	
	public String getNoteAlertID(){
		IHtmlTable table = this.getNoteAlertTable();
		return table.getCellValue(1,1);		
	}
	
	public void clickID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	public void verifyNoteAndAlertList(NoteAndAlertInfo info){
		boolean result = true;
		logger.info("Verify "+info.type+" info in search page.");
		
		List<NoteAndAlertInfo> infoList = this.getNoteAlertList();
		if(infoList.size() != 1){
			throw new ErrorOnPageException("Only added ONE just now.");
		} else {
			NoteAndAlertInfo actualInfo = infoList.get(0);
			result &= MiscFunctions.compareResult("ID", info.id, actualInfo.id);
			result &= MiscFunctions.compareResult("Start Date", info.startDate, actualInfo.startDate);
			result &= MiscFunctions.compareResult("End Date", info.endDate, actualInfo.endDate);
			result &= MiscFunctions.compareResult("Type", info.type, actualInfo.type);
			result &= MiscFunctions.compareResult("Text", info.text, actualInfo.text);
			result &= MiscFunctions.compareResult("Status", OrmsConstants.ACTIVE_STATUS, actualInfo.status);
			result &= MiscFunctions.compareResult("Creation User", info.createUser, actualInfo.createUser.replaceAll(" ", ""));
		}

		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void verifyNoteAndAlertAfterSearch(String searhcBy, String searchValue, String today){
		boolean result = true;
		if("Show Current Records Only".equalsIgnoreCase(searhcBy)){
			List<String> startDateList = this.getStartDateList();
			List<String> endDateList = this.getEndDateList();
			
			for(int i=0; i<startDateList.size(); i++){
				if(DateFunctions.compareDates(startDateList.get(i), today) <= 0){
					logger.info("Show current records correctly.");
				} else {
					result &= false;
					logger.error("Record which start date is:"+startDateList.get(i)+", and end date is:"+endDateList.get(i)+" is not current record.");
				}
			}
		} else if("Status".equalsIgnoreCase(searhcBy)){
			List<String> statusList = this.getStatusList();
			for(int i=0; i<statusList.size(); i++){
				result &= MiscFunctions.compareResult("No."+(i+1)+" Status", searchValue, statusList.get(i));
			}
		} else if("Type".equalsIgnoreCase(searhcBy)){
			List<String> typeList = this.getTypeList();
			for(int i=0; i<typeList.size(); i++){
				result &= MiscFunctions.compareResult("No."+(i+1)+" Type", searchValue, typeList.get(i));
			}
		}

		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void selectFirstNoteAndAlert(){
		browser.selectCheckBox(".id", new RegularExpression("NoteAlertMsgView-\\d+.selected", false), 0);
	}

	public void deactiveNoteAndAlert(){
		this.selectFirstNoteAndAlert();
		this.clickDeactive();
		this.waitLoading();
	}

	private void searchRecords(String status, String type){
		this.unselectShowCurrentRecordsOnly();
		ajax.waitLoading();
		
		if(!StringUtil.isEmpty(status)){
			this.setStatus(status);
		}
		
		if(!StringUtil.isEmpty(type)){
			this.setType(type);
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public List<String> getRowValueByID(String id){
		IHtmlTable table = this.getNoteAlertTable();
		int row = table.findRow(1, id);
		List<String> rowValue = table.getRowValues(row);
		return rowValue;
	}

	public void verifyStatus(String id, String type, String status){
		this.searchRecords(status, type);
		List<String> infoList = this.getRowValueByID(id);
		String actualStatus = infoList.get(6);
		if(!MiscFunctions.compareResult("Status", status, actualStatus)){
			throw new ErrorOnPageException("The status for "+id+" should be "+status);
		}
	}
}
