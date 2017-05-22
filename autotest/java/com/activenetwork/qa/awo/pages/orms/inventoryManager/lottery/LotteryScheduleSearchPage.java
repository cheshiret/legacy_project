package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LotteryScheduleSearchPage extends InvMgrCommonTopMenuPage{

	private static LotteryScheduleSearchPage _instance = null;


	public static LotteryScheduleSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LotteryScheduleSearchPage();
		}

		return _instance;
	}

	protected LotteryScheduleSearchPage() {
	}

	public boolean exists() {//LotteryScheduleSearchCriteria.lotteryScheduleIdStr
	    return browser.checkHtmlObjectExists(".id", new RegularExpression("LotteryScheduleSearchCriteria.lotteryScheduleId(:ZERO_TO_NULL|Str)", false));
	}
	
	public void setLotteryScheduleID(String scheduleID){
		browser.setTextField(".id", "LotteryScheduleSearchCriteria.lotteryScheduleId:ZERO_TO_NULL", scheduleID);
	}
	
	public void selectShow(String show){
		browser.selectDropdownList(".id", "LotteryScheduleSearchCriteria.active", show);
	}
	
	public void clickGO(){
		browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(".class","Html.A",".text","Add New");
	}
	
	public String getFirstScheduleID(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","lotteryScheduleListView_LIST");
		IHtmlTable tabObj=(IHtmlTable)objs[0];
		
		String firstSchduleID=tabObj.getCellValue(1, 1);
		Browser.unregister(objs);
		
		return firstSchduleID;
	}
	
	/**
	 * The method used to get the first complete schedule execute date
	 * @return
	 */
	public String getFirstCompleteScheduleExecuteDate(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","lotteryScheduleListView_LIST");
		IHtmlTable tabObj=(IHtmlTable)objs[0];
		String executeDate = "";
		for(int i=0;i<tabObj.rowCount();i++){
			if(tabObj.getCellValue(i,9)!=null&&tabObj.getCellValue(i,9).equalsIgnoreCase("Completed")){
				executeDate = tabObj.getCellValue(i, 5);
				break;
			}
		}
		Browser.unregister(objs);
		return executeDate.trim();
	}
	
	public void clickFirstScheduleID(){
		String scheduleID=this.getFirstScheduleID();
		this.clickLotteryScheduleID(scheduleID);
//		browser.clickGuiObject(".class","Html.A",".text",scheduleID);
	}
	
	public void clickLotteryScheduleID(String lotteryScheduleID){
		browser.clickGuiObject(".class","Html.A",".text",lotteryScheduleID);
	}
	
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate", true);
	}
	
	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}
	
	public void selectAllSchedules() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void checkScheduleByID(int index) {
		browser.selectCheckBox(".id","LotteryScheduleView.id", index);
	}
	/**
	 * get lottery table.
	 * @return
	 */
	public IHtmlObject[] getLotteryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","lotteryScheduleListView_LIST");
		if(objs.length<1){
			throw new ErrorOnPageException("No specific table was exist");
		}
		return objs;
	}
	
	public String getLotteryStatus(String scheduleId){
		IHtmlObject[] objs = this.getLotteryTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, scheduleId);
		String status = table.getCellValue(row, 2);
		Browser.unregister(objs);
		return status;
	}

	public void checkScheduleByID(String ID) {
		IHtmlObject[] objs = this.getLotteryTable();
		IHtmlTable tabObj=(IHtmlTable)objs[0];
		
		int index = -1;
		for(int i=0;i<tabObj.rowCount();i++){
			if(tabObj.getCellValue(i,1)!=null&&tabObj.getCellValue(i,1).equalsIgnoreCase(ID)){
				index = i - 1;
				break;
			}
		}
		if(index == -1) {
			throw new ItemNotFoundException("Lottery Schedule " + ID + " not found.");
		}
		
		browser.selectCheckBox(".id","LotteryScheduleView.id", index);
		Browser.unregister(objs);
	}
	
	public void activeScheduleByID(String id) {
		this.checkScheduleByID(id);
		this.clickActive();
		this.waitLoading();
	}
	
	public void deactivateScheduleByID(String id) {
		this.checkScheduleByID(id);
		this.clickDeactive();
		this.waitLoading();
	}
	
	public void activeScheduleByID(int index) {
		this.checkScheduleByID(index);
		this.clickActive();
		this.waitLoading();
	}
	
	public void checkAllSchedule() {
		Property[] p1=Property.toPropertyArray(".class","Html.TABLE",".id","lotteryScheduleListView_LIST");
		Property[] p2=Property.toPropertyArray(".class", "Html.THEAD");
		Property[] p3=Property.toPropertyArray(".class", "Html.INPUT.checkbox");
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2,p3));
		
		objs[0].click();
		Browser.unregister(objs);
	}

	public void deactiveAllSchedule() {
		checkAllSchedule();
		clickDeactive();
		this.waitLoading();
	}
	
	/**
	 * Deactivate all active lottery schedules
	 */
	public void deactivateAllSchedules() {
		this.selectShow(OrmsConstants.ACTIVE_STATUS);
		this.clickGO();
		this.waitLoading();
		this.selectAllSchedules();
		this.clickDeactive();
		this.waitLoading();
	}
	/**
	 * search lottery schedules by status.
	 * @param status
	 */
	public void searchLotteryScheduleByStatus(String status){
		this.selectShow(status);
		this.clickGO();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchLotterySchedule(String id, String status){
		if(null != status){
			this.selectShow(status);
		}
		if(null != id){
			this.setLotteryScheduleID(id);
		}
		this.clickGO();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * check and deactive lottery schedules.
	 */
	public void checkAndDeactivLotterySchedules(){
		this.searchLotteryScheduleByStatus("Active");
		IHtmlObject[] objs = this.getLotteryTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount()<=1){
			logger.info("No lottery schedules records was found");
		}else{
			for(int i=1;i<table.rowCount();i++){
				
			}
			this.selectAllSchedules();
			this.clickDeactive();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	/**
	 * Get lottery schedule info. 
	 * @return
	 */
	public List<LotterySchedule> getLotteryScheduleInfo(){
		IHtmlObject[] objs = this.getLotteryTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<LotterySchedule> scheduleList = new ArrayList<LotterySchedule>();
		LotterySchedule schedule;
		
		for(int i=1; i<table.rowCount(); i++){
			schedule = new LotterySchedule();
			schedule.id = table.getCellValue(i, 1);
			schedule.status = table.getCellValue(i, 2);
			schedule.appStartDate = table.getCellValue(i, 3);
			schedule.appEndDate = table.getCellValue(i, 4);
			schedule.executeDate = table.getCellValue(i, 5);
			schedule.invStartDate = table.getCellValue(i, 6);
			schedule.invEndDate = table.getCellValue(i, 7);
			schedule.invExclusionDate = table.getCellValue(i, 8);
			schedule.executionStatus = table.getCellValue(i, 9);
			scheduleList.add(schedule);
		}
		return scheduleList;
	}
	
	public void verifyScheduleInfo(LotterySchedule expectSchedule){
		logger.info("Verify schedule info in schedule list page.");
		
		// search and get actual schedule info
		this.searchLotterySchedule(expectSchedule.id, null);
		LotterySchedule actualSchedule = this.getLotteryScheduleInfo().get(0);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Status", expectSchedule.status, actualSchedule.status);
		result &= MiscFunctions.compareResult("App Start date time", expectSchedule.appStartDateTime, actualSchedule.appStartDate);
		result &= MiscFunctions.compareResult("App End date time", expectSchedule.appEndDateTime, actualSchedule.appEndDate);
		result &= MiscFunctions.compareResult("Execution Date time", expectSchedule.executeDateTime, actualSchedule.executeDate);
		result &= MiscFunctions.compareResult("Inventory Start Date", expectSchedule.invStartDate, actualSchedule.invStartDate);
		result &= MiscFunctions.compareResult("Inventory End Date", expectSchedule.invEndDate, actualSchedule.invEndDate);
		result &= MiscFunctions.compareResult("Inventory Exclusion Date", expectSchedule.invExclusionDate, actualSchedule.invExclusionDate);
		result &= MiscFunctions.compareResult("Execution Status", expectSchedule.executionStatus, actualSchedule.executionStatus);
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public List<String> getScheduleID(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".id","lotteryScheduleListView_LIST");
		IHtmlTable tabObj=(IHtmlTable)objs[0];
		List<String> schduleID=tabObj.getColumnValues(0);
		schduleID.remove(0);
		Browser.unregister(objs);
		return schduleID;
	}
	
}
