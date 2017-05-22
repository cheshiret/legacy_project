package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
public class LicMgrFacilityDetailsPage extends LicMgrCommonTopMenuPage{
	static class SingletonHolder {
		protected static LicMgrFacilityDetailsPage _instance = new LicMgrFacilityDetailsPage();
	}

	protected LicMgrFacilityDetailsPage() {
	}

	public static LicMgrFacilityDetailsPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] agencyName(){
		return Property.toPropertyArray(".id", "agencyName");
	}

	protected Property[] regionName(){
		return Property.toPropertyArray(".id", "regionname");
	}

	protected Property[] facilityIDSpan(){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression("^Facility ID$", false));
	}

	protected Property[] facilityIDTextField(){
		return Property.concatPropertyArray(input("text"), ".value", new RegularExpression("^\\d+$", false));
	}

	protected Property[] facilityName(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.name", false));
	}

	protected Property[] shortName(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.shortName", false));
	}

	protected Property[] status(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.status", false));
	}

	protected Property[] publicLine(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.publicLine", false));
	}

	protected Property[] fax(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.fax", false));
	}

	protected Property[] email(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.email", false));
	}

	protected Property[] website(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.website", false));
	}

	protected Property[] timeZone(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.timeZone", false));
	}

	protected Property[] geographicRegion(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.geographicRegion", false));
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	protected Property[] apply(){
		return Property.concatPropertyArray(a(), ".text", "Apply");
	}

	protected Property[] errorMsg(){
		return Property.concatPropertyArray(span(),".className", "message msgerror");
	}
	
	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(),".className", "message msgerror", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] facilityAttriTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("ActvityFacilitySubTabs_tabpanel_\\d+", false), ".text", "Facility Attributes");
	}
	
	protected Property[] addressesTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("ActvityFacilitySubTabs_tabpanel_\\d+", false), ".text", "Addresses & Contacts");
	}
	
	protected Property[] facilityProTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("ActvityFacilitySubTabs_tabpanel_\\d+", false), ".text", "Facility Product");
	}
	
	protected Property[] linkDIV(){
		return Property.concatPropertyArray(div(), ".className", "link standard");
	}
	
	protected Property[] changeHistorySpan(){
		return Property.concatPropertyArray(span(), ".text", "Change History");
	}
	
	protected Property[] changeHistoryButton(){
		return Property.concatPropertyArray(a(), ".text", "Change History");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(agencyName()) && browser.checkHtmlObjectExists(regionName());
	}

	public String getTextFieldValue(String fieldLable, Property[] p){
		IHtmlObject[] objs = browser.getTextField(p);
		if(objs.length<1){
			throw new ObjectNotFoundException(fieldLable+" objects can't be found.");
		}
		String value = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return value;
	}
	
	public String getAgency(){
		return getTextFieldValue("Agency name", agencyName());
	}

	public String getRegion(){
		return getTextFieldValue("Region name", regionName());
	}

	public String getFacilityID(){
		IHtmlObject[] objs = browser.getHtmlObject(facilityIDTextField());
		if(objs.length<1){
			throw new ObjectNotFoundException("Facility ID objects can't be found.");
		}
		String value = objs[0].getProperty(".value");
		logger.info("Facility ID:"+value);
		Browser.unregister(objs);
		return value;
	}

	public void setFacilityName(String name){
		browser.setTextField(facilityName(),name);
	}

	public String getFacilityName(){
		return browser.getTextFieldValue(facilityName());
	}

	public void setFacilityShortName(String name){
		browser.setTextField(shortName(),name);
	}

	public boolean isFacilityShortNameDisabled(){
		IHtmlObject[] objs=browser.getHtmlObject(shortName());
		if(objs.length<1){
			throw new ErrorOnPageException("can't find shot name id object.");
		}
		return Boolean.parseBoolean(objs[0].getProperty(".isDisabled"));
	}

	public String getFacilityShortName(){
		return getTextFieldValue("Short name", shortName());
	}

	public void selectStatus(String status){
		browser.selectDropdownList(status(), status);
	}

	public String getStatus(){
		return browser.getDropdownListValue(status(), 0);
	}


	public void setPublicLine(String publicLine) {
		browser.setTextField(publicLine(), publicLine);
	}

	public String getPublicLine() {
		return browser.getTextFieldValue(publicLine());
	}

	public void setFax(String fax) {
		browser.setTextField(fax(), fax);
	}

	public String getFax() {
		return browser.getTextFieldValue(fax());
	}

	public void setEmail(String email) {
		browser.setTextField(email(), email);
	}

	public String getEmail() {
		return browser.getTextFieldValue(email());
	}

	public void setWebsite(String webiste) {
		browser.setTextField(website(), webiste);
	}

	public String getWebsite() {
		return browser.getTextFieldValue(website());
	}


	public void selectTimeZone(String timezone) {
		browser.selectDropdownList(timeZone(), timezone);
	}

	public String getTimeZone() {
		return browser.getDropdownListValue(timeZone(), 0);
	}

	public void selectGeographicRegion(String geographicRegion) {
		browser.selectDropdownList(geographicRegion(), geographicRegion);
	}

	public String getGeographicRegion() {
		return browser.getDropdownListValue(geographicRegion(), 0);
	}

	public List<String> getGeographicRegionElements() {
		return browser.getDropdownElements(geographicRegion());
	}

	public void clickOK(){
		browser.clickGuiObject(ok());
	}

	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}

	public void clickApply(){
		browser.clickGuiObject(apply());
	}

	public void updateFacilityDetails(FacilityData fd) {
		logger.info("Update facility information...");
		if(StringUtil.notEmpty(fd.facilityName))
			this.setFacilityName(fd.facilityName);
		if(StringUtil.notEmpty(fd.shortName) && !isFacilityShortNameDisabled())
			this.setFacilityShortName(fd.shortName);
		if(StringUtil.notEmpty(fd.status))
			this.selectStatus(fd.status);
		if(StringUtil.notEmpty(fd.publicLine )) 
			this.setPublicLine(fd.publicLine);
		if(StringUtil.notEmpty(fd.fax))
			this.setFax(fd.fax);
		if(StringUtil.notEmpty(fd.email)) 
			this.setEmail(fd.email);
		if(StringUtil.notEmpty(fd.website)) 
			this.setWebsite(fd.website);
		if(StringUtil.notEmpty(fd.timeZone))
			this.selectTimeZone(fd.timeZone);
		if(StringUtil.notEmpty(fd.geographicRegion) && this.getGeographicRegionElements().size() > 0) 
			this.selectGeographicRegion(fd.geographicRegion);
	}

	public FacilityData getFacilityData() {
		FacilityData facility = new FacilityData();
		facility.agency = this.getAgency();
		facility.region = this.getRegion();
		facility.facilityID = this.getFacilityID();
		facility.facilityName = this.getFacilityName();
		facility.shortName = this.getFacilityShortName();
		facility.status = this.getStatus();
		facility.publicLine = this.getPublicLine();
		facility.fax = this.getFax();
		facility.email=this.getEmail();
		facility.website = this.getWebsite();
		facility.timeZone = this.getTimeZone();
		facility.geographicRegion = this.getGeographicRegion();

		return facility;
	}

	public void verifyFacilityInfo(FacilityData expectedFacility) {
		boolean result = true;
		FacilityData actualFacility = this.getFacilityData();
		result &= MiscFunctions.compareResult("Agency", expectedFacility.agency, actualFacility.agency);
		result &= MiscFunctions.compareResult("Region", expectedFacility.region, actualFacility.region);
		result &= MiscFunctions.compareResult("Facility ID", expectedFacility.facilityID, actualFacility.facilityID);
		result &= MiscFunctions.compareResult("Facility Name", expectedFacility.facilityName, actualFacility.facilityName);
		result &= MiscFunctions.compareResult("Facility Short Name", expectedFacility.shortName, actualFacility.shortName);
		result &= MiscFunctions.compareResult("Status", expectedFacility.status, actualFacility.status);
		result &= MiscFunctions.compareResult("Public Line", expectedFacility.publicLine, actualFacility.publicLine);
		result &= MiscFunctions.compareResult("Fax", expectedFacility.fax, actualFacility.fax);
		result &= MiscFunctions.compareResult("Email", expectedFacility.email, actualFacility.email);
		result &= MiscFunctions.compareResult("Website", expectedFacility.website, actualFacility.website);
		result &= MiscFunctions.compareResult("Time Zone", expectedFacility.timeZone, actualFacility.timeZone);
		result &= MiscFunctions.compareResult("Geographic Region", expectedFacility.geographicRegion, actualFacility.geographicRegion);
		if(!result){
			throw new ErrorOnPageException("Failed to verify all check points are passed in facility details page. Please check the details from previous page.");
		}
		logger.info("Successfully verify all check points are passed in faclity details page.");
	} 

	public void updateFacilityDetailAndClickApply(FacilityData fd) {
		updateFacilityDetails(fd);
		clickApply();
		waitLoading();
	}
	
	public void clickAddressesTab(){
		browser.clickGuiObject(addressesTab());
	}
	
	public void clickFacilityAttriTab(){
		browser.clickGuiObject(facilityAttriTab());
	}
	
	public void clickFacilityProTab(){
		browser.clickGuiObject(facilityProTab());
	}
	
	public boolean isFacilityProTabExisted(){
		return browser.checkHtmlObjectExists(facilityProTab());
	}
	
	public void verifyFacilityProTabExisted(boolean existed){
		boolean fromUI = isFacilityProTabExisted();
		if(existed!=fromUI){
			throw new ErrorOnPageException("Facility product tab should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify facility product tab "+(existed?"":"displays ")+"doesn't exist.");
	}
	
	public boolean isChangeHistorySpanExisited(){
		return browser.checkHtmlObjectExists(Property.atList(linkDIV(), changeHistorySpan()));
	}
	
	public boolean isChangeHistoryButtonExisited(){
		return browser.checkHtmlObjectExists(Property.atList(linkDIV(), changeHistoryButton()));
	}
	
	public void verifyChangeHistoryButtonExisted(){
		if(!isChangeHistoryButtonExisited() || isChangeHistorySpanExisited()){
			throw new ErrorOnPageException("Change History button should display.");
		}
		logger.info("Successfully verify Change History button displays.");
	}
	
	public void verifyChangeHistorySpanExisted(){
		if(isChangeHistoryButtonExisited() || !isChangeHistorySpanExisited()){
			throw new ErrorOnPageException("Change History Span should display.");
		}
		logger.info("Successfully verify Change History span displays.");
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
	public boolean isErrorMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}
	
	public void verifyErrorMsgExisted(String msg, boolean isExist) {
		if (this.isErrorMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}
	
}


