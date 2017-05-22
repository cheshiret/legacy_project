package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivitySession;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrAddActivityPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrAddActivityPage _instance = new LicMgrAddActivityPage();
	}

	protected LicMgrAddActivityPage() {
	}

	public static LicMgrAddActivityPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] activityCode(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.productCode", false));
	}
	
	protected Property[] activityName(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.activityName", false));
	}
	
	protected Property[] activityStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.entityStatus", false));
	}
	
	protected Property[] capacity(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.activityCapacityConfigView\\.max", false));
	}
	
	protected Property[] activityGroup(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.activityGroup", false));
	}
	
	protected Property[] addNew(){
		return Property.concatPropertyArray(a(), ".text", "Add New");
	}
	
	protected Property[] activityType(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.educationCertificationCategory", false));
	}
	
	protected Property[] customerClass(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.customerClasses_\\d+", false));
	}
	
	protected Property[] customerClass(String id){
		return Property.toPropertyArray(".id", id);
	}
	
	protected Property[] customerClassLabel(String custClass){
		return Property.concatPropertyArray(label(), ".className", "trailing", ".for", new RegularExpression("ActivityView-\\d+\\.customerClasses_\\d+", false), ".text", custClass);
	}
	
	protected Property[] activityCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.activityCategory", false));
	}
	
	protected Property[] activitySubCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.activitySubCategory", false));
	}
	
	protected Property[] reportCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.reportCategory", false));
	}
	
	protected Property[] pricingNote(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.pricingNote", false));
	}
	
	protected Property[] activityProductDesc(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.activityProductDesc", false));
	}
	
	protected Property[] activityFacility(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.activityFacilityID", false));
	}
	
	protected Property[] facilityProdcut(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.facilityProdcutID", false));
	}
	
	protected Property[] applyToAllSessions(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityView-\\d+\\.applyToAllSessions", false));
	}
	
	protected Property[] primaryInstructor(){
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.primaryInstructor", false));
	}
	
	protected Property[] secondaryInstructor(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityInstructorView-\\d+\\.instructor\\.id", false));
	}
	
	protected Property[] idTextField(){                               
		return Property.toPropertyArray(".id", new RegularExpression("BaseActivityProductView-\\d+\\.id", false));
	}
	
	protected Property[] addInstructor(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Instructor");
	}
	
	protected Property[] date(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.activityDate_ForDisplay", false));
	}
	
	protected Property[] startTime(){                               
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.startTimeStr", false));
	}
	
	protected Property[] startTimeAMPM(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.startTimeAMPM", false));
	}
	
	protected Property[] endTime(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.endTimeStr", false));
	}
	
	protected Property[] endTimeAMPM(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.endTimeStrAMPM", false));
	}
	
	protected Property[] addSession(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Session");
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Cancel");
	}
	
	protected Property[] apply(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Apply");
	}
	
	protected Property[] errMsgDiv(){
		return Property.toPropertyArray(".id", "NOTSET");
	}
	
	protected Property[] activitySessionFacility(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.activityFacilityID", false));
	}
	
	protected Property[] sessionFacilityProdcut(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityDateView-\\d+\\.facilityId", false));
	}
	
	/** Page Object Property Definition END */
	public boolean exists() {
		return browser.checkHtmlObjectExists(activityCode());
	}
	
	public void setActivityCode(String prdCode){
		browser.setTextField(activityCode(), prdCode);
	}
	
	public String getActivityCode(){
		return browser.getTextFieldValue(activityCode());
	}
	
	public void setActivityName(String prdName){
		browser.setTextField(activityName(), prdName);
	}
	
	public String getActivityName(){
		return browser.getTextFieldValue(activityName());
	}
	
	public void setCapacity(String capacity){
		browser.setTextField(capacity(), capacity);
	}
	
	public String getCapacity(){
		return browser.getTextFieldValue(capacity());
	}
	
	public void selectActivityGroup(String activityGroup){
		browser.selectDropdownList(activityGroup(), activityGroup);
	}
	
	public String getActivityGroup(){
		return browser.getDropdownListValue(activityGroup(), 0);
	}
	
	public List<String> getActivityGroups(){
		return browser.getDropdownElements(activityGroup());
	}
	
	public void selectActivityType(String activityType){
		browser.selectDropdownList(activityType(), activityType);
	}
	
	public String getCustClassCheckBoxID(String custClass){
		IHtmlObject objs[] = browser.getHtmlObject(customerClassLabel(custClass));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find customer class label objects.");
		}
		String value = objs[0].getProperty(".for");
		Browser.unregister(objs);
		return value;
	}
	
	public void selectCustomerClass(String customerclass){
		browser.selectCheckBox(customerClass(getCustClassCheckBoxID(customerclass)));
	}
	
	public void selectDisplayCategory(String displayCategory){
		browser.selectDropdownList(activityCategory(), displayCategory);
	}
	
	public void selectDisplaySubCategory(String displaySubCategory){
		browser.selectDropdownList(activitySubCategory(), displaySubCategory);
	}
	
	public void selectReportCategory(String reportCategory){
		browser.selectDropdownList(reportCategory(), reportCategory);
	}

	public void setPricingNote(String pricingNote){
		browser.setTextField(pricingNote(), pricingNote);
	}
	
	public void setActivityLongDesc(String activityLongDesc){
		browser.setTextArea(activityProductDesc(), activityLongDesc);
	}
	
	public void selectFacility(String facility){
		browser.selectDropdownList(activityFacility(), facility);
	}
	
	public void selectFacilityPrd(String facilityPrd){
		browser.selectDropdownList(facilityProdcut(), facilityPrd);
	}
	
	public void selectApplyToAllSessions(){
		browser.selectCheckBox(applyToAllSessions());
	}
	
	public void unSelectApplyToAllSessions(){
		browser.unSelectCheckBox(applyToAllSessions());
	}
	
	public void selectPrimaryInstructor(String primaryInstructor){
		browser.selectDropdownList(primaryInstructor(), primaryInstructor);
	}
	
	public void selectSecondInstructor(String secondaryInstructor){
		browser.selectDropdownList(secondaryInstructor(), secondaryInstructor);
	}
	
	public void setDate(String date){
		this.setDate(date, 0);
	}
	
	public void setDate(String date, int index){
		browser.setTextField(date(), date, index);
	}
	
	public void setStartTime(String startTime){
		this.setStartTime(startTime, 0);
	}
	
	public void setStartTime(String startTime, int index){
		browser.setCalendarField(startTime(), startTime, index);
	}
	
	public void setStartTimeAMPM(String startTimeAMPM){
		this.setStartTimeAMPM(startTimeAMPM, 0);
	}
	
	public void setStartTimeAMPM(String startTimeAMPM, int index){
		browser.selectDropdownList(startTimeAMPM(), startTimeAMPM, index);
	}
	
	public void setEndTime(String endTime){
		this.setEndTime(endTime, 0);
	}
	
	public void setEndTime(String endTime, int index){
		browser.setCalendarField(endTime(), endTime, index);
	}
	
	public void setEndTimeAMPM(String endTimeAMPM){
		this.setEndTimeAMPM(endTimeAMPM, 0);
	}
	
	public void setEndTimeAMPM(String endTimeAMPM, int index){
		browser.selectDropdownList(endTimeAMPM(), endTimeAMPM, index);
	}
	
	public void selectSessionFacility(String facility, int index){
		browser.selectDropdownList(activitySessionFacility(), facility, index);
	}
	
	public void selectSessionFacilityPrd(String facilityPrd, int index){
		browser.selectDropdownList(sessionFacilityProdcut(), facilityPrd, index);
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(addNew());
	}
	
	public void clickOK(){
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(ok());
	}
	
//	private void moveFouse(){
//		browser.clickGuiObject(".class", "Html.TD", ".text", "Instructor");
//	}
	
	public void clickApply(){
		browser.clickGuiObject(apply());
	}
	
	public void setupActivityInfo(Data<ActivityAttr> activity){
		//Product Details
		setActivityCode(activity.stringValue(ActivityAttr.activityCode));
		setActivityName(activity.stringValue(ActivityAttr.activityName));
		setCapacity(activity.stringValue(ActivityAttr.capacity));
		selectActivityGroup(activity.stringValue(ActivityAttr.prdGroup));
		selectActivityType(activity.stringValue(ActivityAttr.activityType));
		String [] custClasses = (String[])(activity.get(ActivityAttr.custClasses));
//		for(int i=0; i<((String[]<Data<ActivityAttr>>)(ActivityAttr.custClasses.getStrValue(activity))).length; i++){
//			selectCustomerClass(activity.stringValue(ActivityAttr.custClasses)[i]);
//		}
//		ArrayList<Data<ActivitySessions>> aa = ((ArrayList<Data<ActivitySessions>>)(ActivityAttr.ActivitySessions.getValue(activity)));
//		List<Stirng> aa = ((ArrayList<Data<ActivityAttr>>)(ActivityAttr.ActivitySessions.getStrValue(activity)));
		for(int i=0; i<custClasses.length; i++){
				selectCustomerClass(custClasses[i]);
		}

		selectDisplayCategory(activity.stringValue(ActivityAttr.displayCategory));
		selectDisplaySubCategory(activity.stringValue(ActivityAttr.displaySubCategory));
		selectReportCategory(activity.stringValue(ActivityAttr.reportCategory));
		if(StringUtil.notEmpty(activity.stringValue(ActivityAttr.pricingNote))){
			setPricingNote(activity.stringValue(ActivityAttr.pricingNote));
		}
		if(StringUtil.notEmpty(activity.stringValue(ActivityAttr.activeLongDesc))){
			setActivityLongDesc(activity.stringValue(ActivityAttr.activeLongDesc));
		}

		//sessions
		if(activity.booleanValue(ActivityAttr.appliesToAllSessions)){
			selectApplyToAllSessions();
		}else{
			unSelectApplyToAllSessions();
		}
		ajax.waitLoading();
		this.waitLoading();
		//Instructor
		selectPrimaryInstructor(activity.stringValue(ActivityAttr.primaryInstructor));
		if(StringUtil.notEmpty(activity.stringValue(ActivityAttr.secondaryInstructor))){
			selectSecondInstructor(activity.stringValue(ActivityAttr.secondaryInstructor));
		}
		this.moveFocusOutOfDateComponent();
		Browser.sleep(5);
		if(!activity.booleanValue(ActivityAttr.appliesToAllSessions)) {
			//Multiply Activity Sessions
			ArrayList<Data<ActivitySession>> sessions = (ArrayList<Data<ActivitySession>>)(activity.get(ActivityAttr.activitySessions));
			//click Add Session button
			for(int i=0;i<sessions.size()-1;i++) {
				this.clickAddSession();
				ajax.waitLoading();
				this.waitLoading();
			}
			//fill values for sessions
			for(int i=0;i<sessions.size();i++) {
				Data<ActivitySession> session = sessions.get(i);
				this.setDate(session.stringValue(ActivitySession.Date), i);
				this.setStartTime(session.stringValue(ActivitySession.StartTime), i);
				this.setStartTimeAMPM(session.stringValue(ActivitySession.StartTimeAPM), i);
				this.setEndTime(session.stringValue(ActivitySession.EndTime), i);
				this.setEndTimeAMPM(session.stringValue(ActivitySession.EndTimeAPM), i);
				this.selectSessionFacility(session.stringValue(ActivitySession.Facility), i);
				ajax.waitLoading();
				this.waitLoading();
				this.selectSessionFacilityPrd(session.stringValue(ActivitySession.Product), i);
			}
			
		} else {
			selectFacility(activity.stringValue(ActivityAttr.facility));
			ajax.waitLoading();
			this.waitLoading();
			selectFacilityPrd(activity.stringValue(ActivityAttr.facilityPrd));
			ajax.waitLoading();
			this.waitLoading();
			//Single Activity Session
			setDate(activity.stringValue(ActivityAttr.activitySessions_Date));
			this.moveFocusOutOfDateComponent();
			setStartTime(activity.stringValue(ActivityAttr.activitySessions_StartTime));
			setStartTimeAMPM(activity.stringValue(ActivityAttr.activitySessions_startTimeAPM));
			setEndTime(activity.stringValue(ActivityAttr.activitySessions_EndTime));
			setEndTimeAMPM(activity.stringValue(ActivityAttr.activitySessions_EndTimeAPM));
		}
		
//		selectHandicappedAccessible(activity.stringValue(ActivityAttr.handicappedAccessible));
	}
	
	public String getId(){
		return browser.getTextFieldValue(idTextField());
	}
	
	public String getErrMsg(){
		String errMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(this.errMsgDiv());
		if(objs.length > 0){
			errMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return errMsg;
	}
	
	public void clickAddSession() {
		browser.clickGuiObject(addSession());
	}
}
