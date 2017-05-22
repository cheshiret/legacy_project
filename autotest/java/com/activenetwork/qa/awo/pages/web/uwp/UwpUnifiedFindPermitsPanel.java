package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @author SWang
 * @Date  Nov 2, 2011
 */
public class UwpUnifiedFindPermitsPanel extends RecgovCommonPage{
	private static UwpUnifiedFindPermitsPanel _instance = null;

	protected UwpUnifiedFindPermitsPanel() {
	}

	public static UwpUnifiedFindPermitsPanel getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedFindPermitsPanel();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "component container form");
	}

	/**
	 * Get permit type Value from Looking for drop down list
	 * @return
	 */
	public String getPermitTypeValue() {
		return browser.getDropdownListValue(".id", "permitTypeId", 0);
	}

	/**
	 * get all permit type elements values from Looking for drop down list
	 * @return
	 */
	public List<String> getAllPermitTypes(){
		return browser.getDropdownElements(".id", "permitTypeId");
	}

	/**
	 * select Permit type from Looking for drop down list
	 * @param permitType
	 */
	public void selectPermitType(String permitType) {
		browser.selectDropdownList(".id", "permitTypeId", permitType);
	}

	/**
	 * Select permit type by index of type items.
	 * @param index  - index of type
	 */
	public void selectPermitType(int index) {
		browser.selectDropdownList(".id", "permitTypeId", index);
	}

	/**
	 * Verify Permit Type value
	 * @param permitType
	 */
	public void verifyPermitTypeValue(String permitType) {
		if(!this.getPermitTypeValue().equals(permitType)){
			throw new ErrorOnPageException("Current 'Permit Type' value should be '"+permitType+"'");
		}
	}

	public String getEntrance(){
		return browser.getDropdownListValue(".id", "entrance", 0);
	}

	public void verifyEntrance(String expectedEntrance){
		String actualValue = this.getEntrance();
		if(!actualValue.equals(expectedEntrance)){
			throw new ErrorOnDataException("The expected Entrance should be "+expectedEntrance+", but the actual value is "+actualValue);
		}
	}

	public List<String> getAllEntrance(){
		return browser.getDropdownElements(".id", "entrance");
	}

	/**
	 * Select entrance by entrance item index.
	 * @param index - index of entrance item
	 */
	public void selectEntrance(int index) {
		browser.selectDropdownList(".id", "entrance", index);
	}

	/**
	 * Select entrance by entrance name
	 * @param entranceName
	 */
	public void selectEntrance(String entranceName) {
		browser.selectDropdownList(".id", "entrance", entranceName);
	}

	/** Click on button to do permit search */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id",
		"permitAvailabilitySearchButton");
	}

	/**
	 * Verify Permit Type elements
	 * @param expectedpermitTypes
	 */
	public void verifyAllPermitType(String[] expectedpermitTypes){
		List<String> permitTypes = this.getAllPermitTypes();
		String permitType_Expect = "";
		if(!permitTypes.get(0).equals("Select Permit Type")){
			throw new ErrorOnDataException("The first itme of Looking for drop down list shuld be 'Selected Permit Type'");
		}
		if(permitTypes.size()-1==expectedpermitTypes.length){
			for(int i=0; i<expectedpermitTypes.length; i++){
				//In "View as list" page, the permit name from DB
				//In "Find permits" page, the permit type will be merged to be one space when it has more than one spaces in the middle of permit type name
				permitType_Expect = expectedpermitTypes[i].replaceAll("\\s+", " ");
				if(!permitTypes.get(i+1).equals(permitType_Expect)){
					throw new ErrorOnDataException("permit type is wrong!", permitType_Expect, permitTypes.get(i+1));
				}
			}
		}else throw new ErrorOnDataException("Length of compared list doesn't equal.");
	}

	/**
	 * Get permit type description
	 * @return
	 */
	public String getPermitTypeDiscription(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "permitTypeDescId");
		String permitTypeDiscription = objs[0].text();

		Browser.unregister(objs);
		return permitTypeDiscription;
	}

	/**
	 * Get permit types description
	 * @return
	 */
	public String[] getAllPermitTypesDescription(){
		List<String> permitTypes = this.getAllPermitTypes();
		String[]permitTypesDiscription = new String[permitTypes.size()-1];
		for(int i=1; i<permitTypes.size(); i++){
			this.selectPermitType(permitTypes.get(i));
			permitTypesDiscription[i-1] = this.getPermitTypeDiscription();
		}
		return permitTypesDiscription;
	}

	public void verifyAllEntrance(List<String> expectedEntrance){
		List<String> nORepeatableEntrance = new ArrayList<String>();
		List<String> entranceInPanel = this.getAllEntrance();
		String entrance_Expected = "";

		for(int i=0; i<expectedEntrance.size()-1; i++){
			if(!expectedEntrance.get(i).equals(expectedEntrance.get(i+1))){
				nORepeatableEntrance.add(expectedEntrance.get(i));
			}

			if(i==expectedEntrance.size()-2 ){
				//04, 04, 05, 05  Add the first 05 into List
				if(expectedEntrance.get(expectedEntrance.size()-2).equals(expectedEntrance.get(expectedEntrance.size()-1))){
					nORepeatableEntrance.add(expectedEntrance.get(expectedEntrance.size()-2));
				}else{
					//04, 04, 06, 05  Add the last 05 into List
					nORepeatableEntrance.add(expectedEntrance.get(expectedEntrance.size()-1));
				}
			}
		}

		if(nORepeatableEntrance.size()==0){
			nORepeatableEntrance = expectedEntrance;
		}

		//If only one entrance or more than one same entrances display in "entrance list" page
		//it should have only one entrance in "Entrance" drop down list in "Find permits" search panel
		if(nORepeatableEntrance.size()!=1 && !entranceInPanel.get(0).equals("Any Entrance")&&
				!entranceInPanel.get(0).equals("Any Trail") &&
				!entranceInPanel.get(0).equals("Any Destination Zone") &&
				!entranceInPanel.get(0).equals("Any Permit Zone") &&
				!entranceInPanel.get(0).equals("Any River")){
			throw new ErrorOnDataException("The first item of this drop down list shoud be: Any Entrance or Any Trail.");
		}

		if(nORepeatableEntrance.size()==1){
			entrance_Expected = nORepeatableEntrance.get(0);
			if(!entrance_Expected.equals(entranceInPanel.get(0))){
				throw new ErrorOnDataException("Entrance name is wrong.", entrance_Expected, entranceInPanel.get(0));
			}
			logger.info("Successfully verify entrance option as: "+expectedEntrance.get(0));
		}else if(entranceInPanel.size()-1==nORepeatableEntrance.size()){
			for(int i=0; i<nORepeatableEntrance.size(); i++){
				entrance_Expected = nORepeatableEntrance.get(i);
				if(!entranceInPanel.get(i+1).equals(entrance_Expected)){
					throw new ErrorOnDataException("Entrance is wrong!",  entrance_Expected, entranceInPanel.get(i+1));
				}
			}
		}else throw new ErrorOnDataException("The length of compared list doesn't equal.", String.valueOf(nORepeatableEntrance.size()), String.valueOf(entranceInPanel.size()-1));
	}

	public boolean isGroupSizeDisplayed() {
		return !browser.checkHtmlObjectExists(".className","hiddenoptions hide",".id","groupSizeDivId");
	}

	public void setGroupSize(String size) {
		if(size==null || size.length()<1)
			size="1";

		browser.setTextField(".id","groupSize", size);
	}

	/**
	 * Set specific radio button and fill in entry date.
	 * @param entryDate - entry date
	 */
	public void setSpecificDate(String entryDate) {
		browser.selectRadioButton(".id", "rangeno");
		browser.setTextField(".id", "entryStartDate", entryDate, true, 0);
	}

	/**
	 * Fill in start and end date for range search.
	 * @param startDate  - start date
	 * @param endDate - end date
	 */
	public void setRangeDate(String startDate, String endDate) {
		browser.selectRadioButton(".id", "rangeyes");

		if (startDate == null || startDate.length() < 1) {
			startDate = DateFunctions.getDateStamp(false);
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		} else if (endDate == null || endDate.length() < 1) {
			startDate = startDate.replaceAll("-", "/");
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		}

		browser.setTextField(".id", "entryStartDate", startDate);
		browser.setTextField(".id", "entryEndDate", endDate);
	}
	
}
