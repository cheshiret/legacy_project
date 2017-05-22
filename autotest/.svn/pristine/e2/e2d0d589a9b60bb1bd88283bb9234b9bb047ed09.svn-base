package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class LicMgrFacilityAttributesPage extends LicMgrFacilityDetailsPage{

	static class SingletonHolder {
		protected static LicMgrFacilityAttributesPage _instance = new LicMgrFacilityAttributesPage();
	}

	protected LicMgrFacilityAttributesPage() {
	}

	public static LicMgrFacilityAttributesPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] facilityAttriPageMark(){
		return Property.concatPropertyArray(div(), ".id", "content_ActvityFacilitySubTabs_tabpanel", ".text", new RegularExpression("^Brochure Info.*", false));
	}

	protected Property[] brochureInfoDesc(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.brochureInfoDesc", false));
	}

	protected Property[] drivingDirections(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.drivingDirections", false));
	}

	protected Property[] adaAccessible(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.adaAccessible", false));
	}

	protected Property[] latitudeDirection(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeDirection", false));
	}

	protected Property[] latitudeDegree(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeDegree", false));
	}

	protected Property[] latitudeMinute(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeMinute", false));
	}

	protected Property[] latitudeSecond(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeSecond", false));
	}

	protected Property[] longitudeDirection(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeDirection", false));
	}

	protected Property[] longitudeDegree(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeDegree", false));
	}

	protected Property[] longitudeMinute(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeMinute", false));
	}

	protected Property[] longitudeSecond(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeSecond", false));
	}

	protected Property[] hoursOfOperation(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.hoursOfOperation", false));
	}

	protected Property[] save(){
		return Property.concatPropertyArray(a(), "Save");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(facilityAttriPageMark());
	}

	public void setBrochureInfoDesc(String dscr){
		browser.setTextArea(brochureInfoDesc(), dscr);
	}

	public String getBrochureInfoDesc(){
		return browser.getTextAreaValue(brochureInfoDesc());
	}

	public void setDriveDirection(String direction){
		browser.setTextArea(drivingDirections(), direction);
	}

	public String getDriveDirection(){
		return browser.getTextAreaValue(drivingDirections());
	}

	public void selectAdaAccessible(boolean adaAccessble){
		browser.selectDropdownList(adaAccessible(), adaAccessble?"Yes":"No");
	}

	public String getAdaAccessible(){
		return browser.getDropdownListValue(adaAccessible(), 0);
	}

	public void selectLatitudeDirection(String latitudeDirection) {
		browser.selectDropdownList(latitudeDirection(), latitudeDirection, true);		
	}

	public String getLatitudeDirection() {
		return browser.getDropdownListValue(latitudeDirection(), 0);		
	}

	public void setLatitudeDegree(String latitudeDegree){
		browser.setTextField(latitudeDegree(), latitudeDegree);
	}

	public String getLatitudeDegree(){
		return browser.getTextFieldValue(latitudeDegree());
	}

	public void setLatitudeMinute(String latitudeMinute){
		browser.setTextField(latitudeMinute(), latitudeMinute);
	}

	public String getLatitudeMinute(){
		return browser.getTextFieldValue(latitudeMinute());
	}

	public void setLatitudeSecond(String latitudeSecond){
		browser.setTextField(latitudeSecond(), latitudeSecond);
	}

	public String getLatitudeSecond(){
		return browser.getTextFieldValue(latitudeSecond());
	}

	public void selectLongitudeDirection(String longitudeDirection) {
		browser.selectDropdownList(longitudeDirection(), longitudeDirection, true);		
	}

	public String getLongitudeDirection() {
		return browser.getDropdownListValue(longitudeDirection(), 0);		
	}

	public void setLongitudeDegree(String longitudeDegree){
		browser.setTextField(longitudeDegree(), longitudeDegree);
	}

	public String getLongitudeDegree(){
		return browser.getTextFieldValue(longitudeDegree());
	}

	public void setLongitudeMinute(String longitudeMinute){
		browser.setTextField(longitudeMinute(), longitudeMinute);
	}

	public String getLongitudeMinute(){
		return browser.getTextFieldValue(longitudeMinute());
	}

	public void setLongitudeSecond(String longitudeSecond){
		browser.setTextField(longitudeSecond(), longitudeSecond);
	}

	public String getLongitudeSecond(){
		return browser.getTextFieldValue(longitudeSecond());
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		browser.setTextArea(hoursOfOperation(), hoursOfOperation);
	}

	public String getHoursOfOperation() {
		return browser.getTextAreaValue(hoursOfOperation());
	}

	public void clickSave(){
		browser.clickGuiObject(save());
	}

	public void updateFacilityAttriInfo(FacilityData fd) {
		logger.info("Setup facility information...");
		if(StringUtil.notEmpty(fd.brochureDescription))
			this.setBrochureInfoDesc(fd.brochureDescription);
		if(StringUtil.notEmpty(fd.drivingDirection))
			this.setDriveDirection(fd.drivingDirection);
		this.selectAdaAccessible(fd.adaAccessible);
		if(StringUtil.notEmpty(fd.latitudeDirection))
			this.selectLatitudeDirection(fd.latitudeDirection);
		if(StringUtil.notEmpty(fd.latitudeDegrees )) 
			this.setLatitudeDegree(fd.latitudeDegrees);
		if(StringUtil.notEmpty(fd.latitudeMinutes))
			this.setLatitudeMinute(fd.latitudeMinutes);
		if(StringUtil.notEmpty(fd.latitudeSeconds))
			this.setLatitudeSecond(fd.latitudeSeconds);
		if(StringUtil.notEmpty(fd.longitudeDirection)) 
			this.selectLongitudeDirection(fd.longitudeDirection);
		if(StringUtil.notEmpty(fd.longitudeDegrees)) 
			this.setLongitudeDegree(fd.longitudeDegrees);
		if(StringUtil.notEmpty(fd.longitudeMinutes)) 
			this.setLongitudeMinute(fd.longitudeMinutes);
		if(StringUtil.notEmpty(fd.longitudeSeconds)) 
			this.setLongitudeSecond(fd.longitudeSeconds);
		if(StringUtil.notEmpty(fd.hourOfOperation))
			this.setHoursOfOperation(fd.hourOfOperation);
	}

	public FacilityData getFacilityAttriInfo() {
		FacilityData facility = new FacilityData();
		facility.brochureDescription = this.getBrochureInfoDesc();
		facility.drivingDirection = this.getDriveDirection();
		facility.adaAccessible = this.getAdaAccessible().equalsIgnoreCase("Yes")?true:false;
		facility.latitudeDirection = this.getLatitudeDirection();
		facility.latitudeDegrees = this.getLatitudeDegree();
		facility.latitudeMinutes = this.getLatitudeMinute();
		facility.latitudeSeconds = this.getLatitudeSecond();
		facility.longitudeDirection = this.getLongitudeDirection();
		facility.longitudeDegrees = this.getLongitudeDegree();
		facility.longitudeMinutes= this.getLongitudeMinute();
		facility.longitudeSeconds= this.getLongitudeSecond();
		facility.hourOfOperation= this.getHoursOfOperation();
		return facility;
	}

	public void verifyFacilityAttriInfo(FacilityData expectedFacility) {
		boolean result = true;
		FacilityData actualFacility = this.getFacilityAttriInfo();
		result &= MiscFunctions.compareResult("Brochure Info", expectedFacility.brochureDescription, actualFacility.brochureDescription);
		result &= MiscFunctions.compareResult("Driving Directions", expectedFacility.drivingDirection, actualFacility.drivingDirection);
		result &= MiscFunctions.compareResult("ADA Compliance Handicapped Accessible", expectedFacility.adaAccessible, actualFacility.adaAccessible);
		result &= MiscFunctions.compareResult("Latitude Direction", expectedFacility.latitudeDirection, actualFacility.latitudeDirection);
		result &= MiscFunctions.compareResult("Latitude Degrees", expectedFacility.latitudeDegrees, actualFacility.latitudeDegrees);
		result &= MiscFunctions.compareResult("Latitude Minutes", expectedFacility.latitudeMinutes, actualFacility.latitudeMinutes);
		result &= MiscFunctions.compareResult("Latitude Seconds", expectedFacility.latitudeSeconds, actualFacility.latitudeSeconds);
		result &= MiscFunctions.compareResult("Longitude Direction", expectedFacility.longitudeDirection, actualFacility.longitudeDirection);
		result &= MiscFunctions.compareResult("Longitude Degrees", expectedFacility.longitudeDegrees, actualFacility.longitudeDegrees);
		result &= MiscFunctions.compareResult("Longitude Minutes", expectedFacility.longitudeMinutes, actualFacility.longitudeMinutes);
		result &= MiscFunctions.compareResult("Longitude Seconds", expectedFacility.longitudeSeconds, actualFacility.longitudeSeconds);
		result &= MiscFunctions.compareResult("Hours of Operation", expectedFacility.hourOfOperation, actualFacility.hourOfOperation);
      
        if(!result){
        	throw new ErrorOnPageException("Failed to verify all check points in Facility Attributes page. Please check the details from previous logs.");
        }
        logger.info("Successfully verify all check points in Facility Attributes page.");
	}

	public void updateAddressesAndClickApply(FacilityData fd) {
		updateFacilityAttriInfo(fd);
		clickSave();
		waitLoading();
	}
}
