package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivitySession;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrActivityDetailsPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrActivityDetailsPage _instance = new LicMgrActivityDetailsPage();
	}

	protected LicMgrActivityDetailsPage() {
	}

	public static LicMgrActivityDetailsPage getInstance() {
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
		return Property.concatPropertyArray(span(), ".className", "trailing", ".for", new RegularExpression("ActivityView-\\d+\\.customerClasses_\\d+", false), ".text", custClass);
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
	
	protected Property[] storeAssignmentsTab() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("(Agent|Issuer|Location) Assignments", false));
	}
	protected Property[] removeButton() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Remove", false));
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
		browser.setTextField(activityGroup(), activityGroup);
	}
	
	public String getActivityGroup(){
		return browser.getDropdownListValue(activityGroup(), 0);
	}
	
	public List<String> getActivityGroups(){
		return browser.getDropdownElements(activityGroup());
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
	
	public void selectPrimaryInstructor(){
		browser.selectCheckBox(primaryInstructor());
	}
	
	public void selectSecondInstructor(){
		browser.selectCheckBox(secondaryInstructor());
	}
	public void selectSessionFacility(String facility, int index){
		browser.selectDropdownList(activitySessionFacility(), facility, index);
	}
	
	public void selectSessionFacilityPrd(String facilityPrd, int index){
		browser.selectDropdownList(sessionFacilityProdcut(), facilityPrd, index);
	}
	public void setDate(String date){
		setDate(date,0);
	}
	public void setDate(String date,int index){
		browser.setTextField(date(), date,index);
	}
	public void setStartTime(String startTime){
		setStartTime(startTime,0);
	}
	public void setStartTime(String startTime,int index){
		browser.setTextField(startTime(), startTime,index);
	}
	public void setStartTimeAMPM(String startTimeAMPM){
		setStartTimeAMPM(startTimeAMPM,0);
	}
	public void setStartTimeAMPM(String startTimeAMPM,int index){
		browser.selectDropdownList(startTimeAMPM(), startTimeAMPM,index);
//		browser.setTextField(startTimeAMPM(), startTimeAMPM,index);
	}
	
	public void setEndTime(String endTime){
		setEndTime(endTime,0);
	}
	
	public void setEndTime(String endTime,int index){
		browser.setTextField(endTime(), endTime,index);
	}
	public void setEndTimeAMPM(String endTimeAMPM){
		setEndTimeAMPM(endTimeAMPM,0);
	}
	public void setEndTimeAMPM(String endTimeAMPM,int index){
		browser.selectDropdownList(endTimeAMPM(), endTimeAMPM,index);
//		browser.setTextField(endTimeAMPM(), endTimeAMPM,index);
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
	
	public void clickApply(){
		browser.clickGuiObject(apply());
	}
	
	public void clickAgentAssignmentsTab() {
		browser.clickGuiObject(this.storeAssignmentsTab());
	}
	public void clickRemove(int index){
		browser.clickGuiObject(removeButton(), index);
	}
	public void clickAddSession() {
		browser.clickGuiObject(addSession());
	}
	//remove all session from activity. Note: Activity should have at least one session, so the first session cannot be removed
	public void removeAllSessions(){
		logger.info("Remove all sessions(except the first one) from Activity detail page");

		IHtmlObject[] objs = browser.getHtmlObject(removeButton());
		for(int i=0;i<objs.length;i++){
			clickRemove(i);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
	public void addSessionsToExistingActivity(List<Data<ActivitySession>> sessions){
		logger.info("Adding sessions to existing Activity Product in Activity detail page");
		boolean isAppliesToAllSession=isAppliesToAllSessionSelected();
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
			if(!isAppliesToAllSession) {
				this.selectSessionFacility(session.stringValue(ActivitySession.Facility), i);
				ajax.waitLoading();
				this.waitLoading();
				this.selectSessionFacilityPrd(session.stringValue(ActivitySession.Product), i);
			}
		}
			
		
	}
	public boolean isAppliesToAllSessionSelected(){
		IHtmlObject[] objs=browser.getCheckBox(this.applyToAllSessions());
		if(objs.length<1)
		{
			throw new ErrorOnPageException("Failed to get aplly to get all session check box");
		} 
		ICheckBox appliesToAllSession=(ICheckBox)objs[objs.length-1];
		boolean isSelected = appliesToAllSession.isSelected();
		Browser.unregister(objs);
		return isSelected;
		
	}
}
