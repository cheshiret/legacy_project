package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.OfficerInfo;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is the officer search page in license manager, Admin(drop down list)-->Officer MGT --- > Officers
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrOfficersSearchPage extends LicMgrOfficerMGTCommonPage{
	private static LicMgrOfficersSearchPage _instance = null;
	
	protected LicMgrOfficersSearchPage (){}
	
	public static LicMgrOfficersSearchPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrOfficersSearchPage();
		}
		
		return _instance;
	}
	private String prefix = "OfficerSearchCriteria-\\d+\\.";
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","officerList");
	}
	
	public void clickAddOfficer(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Officer", true);
	}
	
	public void setOfficerId(String officerId){
		browser.setTextField(".id", new RegularExpression(prefix+"officerId:ZERO_TO_NULL", false), officerId);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"status", false), status);
	}
	
	public void setFirstName(String fName){
		browser.setTextField(".id", new RegularExpression(prefix+"firstName", false), fName);
	}
	
	public void setLastName(String lName){
		browser.setTextField(".id", new RegularExpression(prefix+"lastName", false), lName);
	}
	
	public void setDateOfBirth(String birthDay){
		browser.setTextField(".id", new RegularExpression(prefix+"dob", false), birthDay);
	}
	
	public void setCurrentBadgeNum(String badgeNum){
		browser.setTextField(".id", new RegularExpression(prefix+"badgeNumber", false), badgeNum);
	}
	
	public void selectAssignment(String assignment){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"assignment", false), assignment);
	}
	
	public void setAddress(String address){
		browser.setTextField(".id", new RegularExpression(prefix+"address", false), address);
	}
	
	public void setCity(String cityTown){
		browser.setTextField(".id", new RegularExpression(prefix+"cityTown", false), cityTown);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"state", false), state);
	}
	
	public void selectCounty(String county){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"county", false), county);
	}
	
	public void setZipPostal(String zipCode){
		browser.setTextField(".id", new RegularExpression(prefix+"zipPostal", false), zipCode);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"country", false), country);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
	}
	
	public void clickFirstOfficerId(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+", false));
	}
	
	public void clickOfficerId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public void setSearchOfficerInfo(OfficerInfo officer){
		if(StringUtil.notEmpty(officer.id)){
			this.setOfficerId(officer.id);
		}
		if(StringUtil.notEmpty(officer.status)){
			this.selectStatus(officer.status);
		}
		if(StringUtil.notEmpty(officer.firstName)){
			this.setFirstName(officer.firstName);
		}
		if(StringUtil.notEmpty(officer.lastName)){
			this.setLastName(officer.lastName);
		}
		if(StringUtil.notEmpty(officer.birthday)){
			this.setDateOfBirth(officer.birthday);
		}
		if(officer.assignBadge&&StringUtil.notEmpty(officer.badge.badgeNum)){
			this.setCurrentBadgeNum(officer.badge.badgeNum);
		}
		if(StringUtil.notEmpty(officer.address)){
			this.setAddress(officer.address);
		}
		if(StringUtil.notEmpty(officer.zipCode)){
			this.setZipPostal(officer.zipCode);
		}
		if(StringUtil.notEmpty(officer.country)){
			this.selectCountry(officer.country);
		}
		if(StringUtil.notEmpty(officer.cityTown)){
			this.setCity(officer.cityTown);
		}
		if(StringUtil.notEmpty(officer.state)){
			this.selectState(officer.state);
		}
		if(StringUtil.notEmpty(officer.county)){
			this.selectCounty(officer.county);
		}
	}
	
	/**
	 * This method search an officer by the given filters
	 * @param officer
	 */
	public void searchOfficer(OfficerInfo officer){
		this.setSearchOfficerInfo(officer);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Search by officer's first name,last name and day of birth
	 * @param officer
	 */
	public void searchByOfficerBasicInfo(String fname, String lname, String birthday){
		if(StringUtil.notEmpty(fname)){
			this.setFirstName(fname);
		}
		if(StringUtil.notEmpty(lname)){
			this.setLastName(lname);
		}
		if(StringUtil.notEmpty(birthday)){
			this.setDateOfBirth(birthday);
		}
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * The method used to get specific row values
	 * @param identifier
	 *            ----one cell value in your expected row
	 * @return
	 */
	public List<String> getFirstSearchResultInfo() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "officerList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getRowValues(1);
		Browser.unregister(objs);
		return values;
	}
}
