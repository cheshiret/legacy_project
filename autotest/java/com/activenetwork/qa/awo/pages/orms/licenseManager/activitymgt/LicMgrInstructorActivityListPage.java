package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: In License Manger Instructor page, click "Activity List" tab will display this page
 * URL:.*LicenseMgrActivityPage.page#
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 29, 2014
 */
public class LicMgrInstructorActivityListPage extends LicMgrInstructorDetailsPage{
	static class SingletonHolder {
		protected static LicMgrInstructorActivityListPage _instance = new LicMgrInstructorActivityListPage();
	}

	protected LicMgrInstructorActivityListPage() {
	}

	public static LicMgrInstructorActivityListPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] activityListGridTable(){
		return Property.concatPropertyArray(table(), ".id", "AwoActivityListGrid_LIST");
	}                                                                

	protected Property[] oddRowTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}
	
	protected Property[] showCurrentRecordsOnly(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivitySearchCriteria-\\d+\\.showCurrentRecordsOnly", false));
	}
	
	protected Property[] activityStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivitySearchCriteria-\\d+\\.productStatus", false));
	}
	
	protected Property[] facilityName(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivitySearchCriteria-\\d+\\.facilityName", false));
	}
	
	protected Property[] activityStartDate(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivitySearchCriteria-\\d+\\.fromDate_ForDisplay", false));
	}
	
	protected Property[] go(){
		return Property.toPropertyArray(".id", "Anchor", ".text", "Go");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(activityListGridTable());
	}
	
	public void selectShowCurrentRecordsOnly(){
		browser.selectCheckBox(showCurrentRecordsOnly());
	}
	
	public void unSelectShowCurrentRecordsOnly(){
		browser.unSelectCheckBox(showCurrentRecordsOnly());
	}
	
	public void setFacility(String facility){
		browser.setTextField(facilityName(), facility);
	}
	
	public void selectActivityStatus(String status){
		browser.selectDropdownList(activityStatus(), status);
	}
	
	public void setActivityStartDate(String startDate){
		browser.setTextField(activityStartDate(), startDate);
	}
	
	public void clickGo(){
		browser.clickGuiObject(go());
	}
	
	public IHtmlTable getInstructorActivityListTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(activityListGridTable());
		IHtmlTable table = null;
		if(objs.length>0){
			table = (IHtmlTable) objs[0];	
		}else throw new ObjectNotFoundException("Can't find instructor activity list list objects.");

		return table;
	}

	public List<Data<ActivityAttr>> getInstructorActivityListInfo() {
		List<Data<ActivityAttr>> list = new ArrayList<Data<ActivityAttr>>();
		String activityCodeAndName = StringUtil.EMPTY;
		IHtmlTable table = getInstructorActivityListTableObjs();
		for (int i = 1; i < table.rowCount(); i++) {
			Data<ActivityAttr> activity = new Data<ActivityAttr>();
			activity.put(ActivityAttr.activityStatus, table.getCellValue(i, 0).trim());
			activityCodeAndName = table.getCellValue(i, 1);
			activity.put(ActivityAttr.activityCode, activityCodeAndName.split("-")[0].trim());
			activity.put(ActivityAttr.activityName, activityCodeAndName.split("-")[1].trim());
			activity.put(ActivityAttr.activitySessions_Date, table.getCellValue(i, 2).trim());
			activity.put(ActivityAttr.activitySessionsTime, table.getCellValue(i, 3).trim());
			activity.put(ActivityAttr.facility, table.getCellValue(i, 4).trim());
			activity.put(ActivityAttr.capacity, table.getCellValue(i, 5).trim());
			list.add(activity);
		}
		Browser.unregister(table);
		return list;
	}
	
	public void verifyInstructorActivityListInfo(List<Data<ActivityAttr>> activities) {
		List<Data<ActivityAttr>> activitiesFromUI = this.getInstructorActivityListInfo();
		boolean result = MiscFunctions.compareResult("Size of list", activities.size(), activitiesFromUI.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<activities.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Activity status", activities.get(i).get(ActivityAttr.activityStatus), activitiesFromUI.get(i).get(ActivityAttr.activityStatus));
				result &= MiscFunctions.compareResult(i+"-Activity code", activities.get(i).get(ActivityAttr.activityCode), activitiesFromUI.get(i).get(ActivityAttr.activityCode));
				result &= MiscFunctions.compareResult(i+"-Activity name", activities.get(i).get(ActivityAttr.activityName), activitiesFromUI.get(i).get(ActivityAttr.activityName));
				result &= MiscFunctions.compareResult(i+"-Activity dates", activities.get(i).get(ActivityAttr.activitySessions_Date), activitiesFromUI.get(i).get(ActivityAttr.activitySessions_Date));
				result &= MiscFunctions.compareResult(i+"-Activity time", activities.get(i).get(ActivityAttr.activitySessionsTime), activitiesFromUI.get(i).get(ActivityAttr.activitySessionsTime));
				result &= MiscFunctions.compareResult(i+"-Activity Facility", activities.get(i).get(ActivityAttr.facility), activitiesFromUI.get(i).get(ActivityAttr.facility));
				result &= MiscFunctions.compareResult(i+"-Activity Capacity", activities.get(i).get(ActivityAttr.capacity), activitiesFromUI.get(i).get(ActivityAttr.capacity));
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in instructor activity list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in instructor activity list page.");
		}
	}
	
	public void verifyInstructorActivityListInfo(Data<ActivityAttr> activity) {
		List<Data<ActivityAttr>> activitiesFromUI = this.getInstructorActivityListInfo();
		Data<ActivityAttr> activityFromUI = new Data<ActivityAttr>();
		activityFromUI = activitiesFromUI.get(activitiesFromUI.size()-1);
		boolean result = MiscFunctions.compareResult("Activity status", activity.get(ActivityAttr.activityStatus), activityFromUI.get(ActivityAttr.activityStatus));
				result &= MiscFunctions.compareResult("Activity code", activity.get(ActivityAttr.activityCode), activityFromUI.get(ActivityAttr.activityCode));
				result &= MiscFunctions.compareResult("Activity name", activity.get(ActivityAttr.activityName), activityFromUI.get(ActivityAttr.activityName));
				result &= MiscFunctions.compareResult("Activity dates", true, DateFunctions.compareDates(activity.get(ActivityAttr.activitySessions_Date).toString(), activityFromUI.get(ActivityAttr.activitySessions_Date).toString())==0);
				result &= MiscFunctions.startWithString("Activity time", activityFromUI.get(ActivityAttr.activitySessionsTime).toString(), activity.get(ActivityAttr.activitySessionsTime).toString());
				result &= MiscFunctions.startWithString("Activity Facility", activityFromUI.get(ActivityAttr.facility).toString(), activity.get(ActivityAttr.facility).toString());
				result &= MiscFunctions.endWithString("Activity Facility product", activityFromUI.get(ActivityAttr.facility).toString(), activity.get(ActivityAttr.facilityPrd).toString());
				result &= MiscFunctions.compareResult("Activity Capacity", activity.get(ActivityAttr.capacity), activityFromUI.get(ActivityAttr.capacity));
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in instructor activity list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in instructor activity list page.");
	}
	
	public List<String> getFirstPgActivityCodesAndNames(){
		List<String> namesAndCodes = new ArrayList<String>();
		IHtmlTable table = getInstructorActivityListTableObjs();
		namesAndCodes = table.getColumnValues(1);
		namesAndCodes.remove(0);
		return namesAndCodes;
	}
	
	public void verifyFirstPgActivityCodesAndNames(List<String> codesAndNames){
		List<String> codesAndNamesFromUI = getFirstPgActivityCodesAndNames();
		if(codesAndNames.toString().equals(codesAndNamesFromUI)){
			logger.info("Successfully verify instructor activity codes and names.");
		}else throw new ErrorOnPageException("Failed to verify instructor activity codes and names.", codesAndNames.toString(), codesAndNamesFromUI.toString());
	}
	
	public void verifyFirstPgActivityCodesAndNames(String codesAndNamesRegx){
		List<String> codesAndNamesFromUI = getFirstPgActivityCodesAndNames();
		if(codesAndNamesFromUI.toString().matches(codesAndNamesRegx)){
			logger.info("Successfully verify instructor activity codes and names.");
		}else throw new ErrorOnPageException("Failed to verify instructor activity codes and names.", codesAndNamesRegx, codesAndNamesFromUI.toString());
	}
	
	public void searchInstructorActivity(Data<ActivityAttr> activity){
		unSelectShowCurrentRecordsOnly();
		ajax.waitLoading();
		selectActivityStatus(activity.stringValue(ActivityAttr.activityStatus));
		setFacility(activity.stringValue(ActivityAttr.facility));
		setActivityStartDate(activity.stringValue(ActivityAttr.activitySessions_Date));
		clickGo();
		ajax.waitLoading();
	}
	
}

