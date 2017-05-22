package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IToggle;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 */
public class UwpSiteSearchPanel extends  UwpPage {
	
	private static UwpSiteSearchPanel _instance = null;

	public static UwpSiteSearchPanel getInstance() {
		if (null == _instance)
			_instance = new UwpSiteSearchPanel();

		return _instance;
	}

	public UwpSiteSearchPanel() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("campsitesearchform|interest_section",false));
	}
	
	/**
	 * Retrieve site search form test object.
	 */
	public IHtmlObject getSiteSearchForm() {
		IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "campsitesearchform");
		IHtmlObject siteSearchForm = (IHtmlObject) foundTOs[0];
		return siteSearchForm;
	}
	
	/**
	 * Fill in site name in search field.
	 * @param site - site name
	 */
	public void setSite(String site) {
		browser.setTextField(".id", "csite", site);
	}
	
	/**
	 * Fill in search from date.
	 * @param date - start date
	 */
	public void setStartDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", new RegularExpression("arvdate|campingDate", false), date);
	}
	
	/**
	 * Fill in search end date.
	 * @param date - end date
	 */
	public void setEndDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", "enddate", date);
	}
	
	/**
	 * Fill in length of stay.
	 * @param days - length of stay
	 */
	public void setLengthOfStay(String days) {
		if (days == null || days.length() < 1)
			days = "2";
		browser.setTextField(".id", "lengthOfStay", days);
	}
	
	/**
	 * Select range radio button to do range search.
	 */
	public void selectRangeRadioButton() {
		browser.selectRadioButton(".id", "rangeyes");
	}
	
	public boolean checkAndSelectRangeRadioButton() {
		IHtmlObject[] objs=browser.getRadioButton(".id", "rangeyes");
		boolean found=false;
		if(objs.length>0) {
			((IRadioButton)objs[0]).select();
			found=true;
		}
		
		Browser.unregister(objs);
		return found;
	}
	
	public void chooseFlexiableForWeeks(int weeks) {
		if(weeks>0 && weeks<=2) {
			weeks=2;
		} else {
			weeks=4;
		}
		String option=weeks<=0?"Not Flexible":"Flexible for next "+weeks+" weeks";
		
		browser.selectDropdownList(".id", "campingDateFlex",option);
	}
	
	/**
	 * Fill in range date search data.
	 * @param startDate - from date
	 * @param endDate - end date
	 */
	public void setRangeDate(String startDate, String endDate) {
		boolean rangeRadioExists=this.checkAndSelectRangeRadioButton();
		
		if (startDate == null || startDate.length() < 1) {
			startDate = DateFunctions.getDateStamp(false);
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		} else if (endDate == null || endDate.length() < 1) {
			startDate = startDate.replaceAll("-", "/");
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		}
		
		if(rangeRadioExists) {
			this.setStartDate(startDate);
			this.setEndDate(endDate);
		} else {//choose Flexible for xx weeks dropdown
			int days=DateFunctions.diffBetween(endDate,startDate);
			int weeks=days/7;
			chooseFlexiableForWeeks(weeks);
			this.setStartDate(startDate);
		}

		
	}
	
	/**
	 * Click specific radio button and set start date to do a specific search.
	 * @param arriveDate - arrival date
	 */
	public void setSpecificDate(String arriveDate) {
		browser.selectRadioButton(".id", "rangeno");
		
		this.setStartDate(arriveDate);
	}
	
	/**
	 * Select site type from dropdown list.
	 * @param item - site type
	 */ 
	public void selectSiteType(String item) {
		browser.selectDropdownList(".id", new RegularExpression("siteType|lookingFor", false), item);
	}
	
	/**
	 * Deselect sity type.
	 */
	public void deselectSiteType() {
		browser.selectDropdownList(".id", new RegularExpression("siteType|lookingFor", false), 0);
	}
	
	/**
	 * Select site loop from dropdown list.
	 * @param item - loop name
	 */ 
	public void selectLoop(String item) {
		browser.selectDropdownList(".id", "loop", item);
	}
	
	/**
	 * Deselect loop name.
	 */
	public void deselectLoop() {
		browser.selectDropdownList(".id", "loop", 0);
	}
	
	/**
	 * Select Spot With check box.
	 */
	public void selectCheckboxSpotWith() {
		browser.selectCheckBox(".id", "parkatt");
	}
	
	/**
	 * Deselect Spot With check box.
	 */
	public void deselectCheckboxSpotWith() {
		browser.unSelectCheckBox(".id", "parkatt");
	}
	
	/**
	 * Fill in length of equipments for spot with.
	 * @param length - length of equipment
	 */
	public void setEuipLengthForSpotWith(String length) {
		browser.setTextField(".id", "eqplen", length);
	}
	
	/**
	 * Fill in Maximum occupants for spot with field
	 * @param max - maximum number
	 */
	public void setMaxOccupantsForSpotWith(String max) {
		browser.setTextField(".id", "maxpeople", max);
	}
	
	/**
	 * Select Electric Hoop up check box.
	 */
	public void selectElectricHookupForSpotWith(String item) {
		browser.selectDropdownList(".id", "hookup", item);
	}
	
	/**
	 * Deselect Electric Hoop up check box.
	 */
	public void deselectElectricHookupForSpotWith() {
		browser.selectDropdownList(".id", "hookup", 0);
	}
	
	/**
	 * Select Water Hoop up check box.
	 */
	public void selectWaterHookupForSpotWith() {
		browser.selectCheckBox(".id", "waterh");
	}
	
	/**
	 * Deselect Water Hoop up check box.
	 */
	public void deselectWaterHookupForSpotWith() {
		browser.unSelectCheckBox(".id", "waterh");
	}
	
	/**
	 * Select Sewer Hoop up check box.
	 */
	public void selectSewerHookupForSpotWith() {
		browser.selectCheckBox(".id", "hook");
	}
	
	/**
	 * Deselect Sewer Hoop up check box.
	 */
	public void deselectSewerHoopupForSpotWith() {
		browser.unSelectCheckBox(".id", "hook");
	}	
	
	/**
	 * Select Pull Through Drive way check box.
	 */
	public void selectPullThroughDrivewayForSpotWith() {
		browser.selectCheckBox(".id", "pullt");
	}
	
	/**
	 * Deselect Pull Through Drive way check box.
	 */
	public void deselectPullThroughDrivewayForSpotWith() {
		browser.unSelectCheckBox(".id", "pullt");
	}
	
	/**
	 * Select Disable Accessible check box.
	 */
	public void selectAccessibleForSpotWith() {
		browser.selectCheckBox(".id", "adabox");
	}
	
	/**
	 * Deselect Disable Accessible check box.
	 */
	public void deselectAccessibleForSpotWith() {
		browser.unSelectCheckBox(".id", "adabox");
	}
	
	/**
	 * Select Pets Allowed check box.
	 */
	public void selectPetsAllowedForSpotWith() {
		browser.selectCheckBox(".id", "pets");
	}
	
	/**
	 * Deselect Pets Allowed check box.
	 */
	public void deselectPetsAllowedForSpotWith() {
		browser.unSelectCheckBox(".id", "pets");
	}
	
	/**
	 * Select warit for front spot with check box.
	 */
	public void selectWaterFrontForSpotWith() {
		browser.selectCheckBox(".id", "waterf");
	}
	
	/**
	 * Deselect warit for front spot with check box.
	 */
	public void deselectWaterFrontForSpotWith() {
		browser.unSelectCheckBox(".id", "waterf");
	}
	
	/**
	 * Fill in all search criteria.
	 * @param bd - booking data
	 */
	public void setupSearchCriteria(BookingData bd) {
		if(StringUtil.notEmpty(bd.lookFor) && StringUtil.isEmpty(bd.siteType)){//Sara[12/6/2013] in this method, lookfor and site type is same thing.
			bd.siteType = bd.lookFor;
		}
		
		if (StringUtil.isEmpty(bd.siteType))
			this.deselectSiteType();
		else
			this.selectSiteType(bd.siteType);

		//Sara[8/29/2013]: unified search is used for RA website so that not all defined loop in cases are existed in Loop DDL list
//		if (StringUtil.isEmpty(bd.loop)){
//			this.deselectLoop();
//		}else
//		  	this.selectLoop(bd.loop);
		if(isLoopDDLExist()){
			List<String> allLoops = getAllLoops();
			if(StringUtil.notEmpty(bd.loop) && allLoops.toString().contains(bd.loop)){
				this.selectLoop(bd.loop);
			}else this.deselectLoop();
		}
		
		
		this.setSite(bd.siteNo);
		this.setSiteCode(bd.siteNo);// for unify search on QA4
		
		if (bd.isRange) {//James[20140402] there is no range selection anymore
			if (bd.startDate.length() < 1 && bd.arrivalDate.length() > 0)
				bd.startDate = bd.arrivalDate;
			this.setRangeDate(bd.startDate, bd.endDate);
		} else {
			this.setSpecificDate(bd.arrivalDate);
		}

		this.setLengthOfStay(bd.lengthOfStay);
		
		if(bd.spotWith) {// for spot with search
		  	this.selectCheckboxSpotWith();
		  	
		  	if(null != bd.spotWithEquipLength && bd.spotWithEquipLength.length()>1)
		  	  	this.setEuipLengthForSpotWith(bd.spotWithEquipLength);
		  	
		  	if(null != bd.spotWithOccupants && bd.spotWithOccupants.length()>1)
		  	  	this.setMaxOccupantsForSpotWith(bd.spotWithOccupants);
		  	
		  	if(null == bd.spotWithElectricHookup || bd.spotWithElectricHookup.length()<1) {
		  	  	this.deselectElectricHookupForSpotWith();
		  	}else {
		  	  	this.selectElectricHookupForSpotWith(bd.spotWithElectricHookup);
		  	}
		  	 
		  	if(bd.spotWithWaterHookup)
		  	  	this.selectWaterHookupForSpotWith();
		  	
		  	if(bd.spotWithSewerHookup)
		  	  	this.selectSewerHookupForSpotWith();
		  	
		  	if(bd.spotWithPullthroughDriveway)
		  	  	this.selectPullThroughDrivewayForSpotWith();
		  	
		  	if(bd.spotWithAccessible)
		  	  	this.selectAccessibleForSpotWith();
		  	
		  	if(bd.spotWithPetsAllowed)
		  	  	this.selectPetsAllowedForSpotWith();
		  	
		  	if(bd.spotWithWaterFront)
		  	  	this.selectWaterFrontForSpotWith();
		}
	}

	/**
	 * Retrieve the start date.
	 * @return - start date
	 */
	public String getStartDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("arvdate|campingDate", false));
	}
	
	/**
	 * Retrieve the end date.
	 * @return - end date
	 */
	public String getEndDate() {
		if(browser.checkHtmlObjectExists(".id", "enddate")){
			return browser.getTextFieldValue(".id", "enddate");
		}
		return StringUtil.EMPTY;
	}
	
	/**
	 * Increase the range date search date 14 days more.
	 * Both start date and end date.
	 */
	public void increaseRangeDate() {

		String oldStart = getStartDate();
		oldStart = DateFunctions.formatDate(oldStart);
		String newStart = DateFunctions.getDateAfterGivenDay(oldStart, 14);
		String newEnd = DateFunctions.getDateAfterGivenDay(newStart, 14);

		this.setStartDate(newStart);
		this.setEndDate(newEnd);
	}
	
	/**
	 * Click on search campgound submit button.
	 */
	public void clickSearchCampsitesSubmit() {
//		browser.clickGuiObject(".id", "search", ".type", "submit");
		browser.clickGuiObject(".id", new RegularExpression("search|filter", false), ".type", "submit");
	}
	
	public void clickApplyFilter() {
		browser.clickGuiObject(".id", "filter", ".type", "submit");
	}
	
	/** Wait for page to load after clicking on park name or book button. */
	public void searchSiteWaitExists() {
	  	browser.searchObjectWaitExists(".class", "Html.DIV", ".id", new RegularExpression("contentArea|csiterst",false));
	}
	
	/**
	 * Click on camp site link to go to campsite list page.
	 */
	public void gotoCampsiteList() {
		browser.clickGuiObject(".id", "campList");
	}
	
	/**
	 * Click on Find Other Campgrounds link.
	 */
	public void findOtherCampgrounds() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Find [o|O]ther [C|c]ampgrounds", false));
	}
	
	/**
	 * Fill in site search criteria and click on search button.
	 * @param bd - booking data
	 */
	public void searchSite(BookingData bd) {
	  	this.setupSearchCriteria(bd);
	  	this.clickSearchCampsitesSubmit();
	  	this.searchSiteWaitExists();
	}
	
	public void setSiteCode(String siteCode) {
		browser.setTextField(".id", "siteCode", siteCode);
	}
	
	/**Below methods are about getting Search Criteria Values*/
	public String getSelectedSiteType() {
		return browser.getDropdownListValue(".id", new RegularExpression("siteType|lookingFor", false));
	}
	
	public boolean isLoopDDLExist(){
		return browser.checkHtmlObjectExists(".id", "loop");
	}
	
	public List<String> getAllLoops(){
		return browser.getDropdownElements(".id", "loop");
	}
	
	public String getSelectedLoop() {
		return browser.getDropdownListValue(".id", "loop");
	}
	
	public boolean isDatesRangeChecked() {
		return browser.isRadioButtonSelected(".id", "rangeyes");
	}
	
	public String getLengthOfStayValue() {
		return browser.getTextFieldValue(".id", "lengthOfStay");
	}
	
	public boolean isSpotWithChecked() {
		return browser.isCheckBoxSelected(".id", "parkatt");
	}
	
	public String getEqpLengthValue() {
		return browser.getTextFieldValue(".id", "eqplen");
	}
	
	public String getOccupantsValue() {
		return browser.getTextFieldValue(".id", "maxpeople");
	}
	
	public String getSelectedElectircHookup() {
		return browser.getDropdownListValue(".id", "hookup");
	}
	
	public boolean isWaterHookupChecked() {
		return browser.isCheckBoxSelected(".id", "waterh");
	}
	
	public boolean isSewerHookupChecked() {
		return browser.isCheckBoxSelected(".id", "hook");
	}
	
	public boolean isPullThroughDrivewayChecked() {
		return browser.isCheckBoxSelected(".id", "pullt");
	}
	
	public boolean isAccessibleChecked() {
		return browser.isCheckBoxSelected(".id", "adabox");
	}
	
	public boolean isPetsAllowedChecked() {
		return browser.isCheckBoxSelected(".id", "pets");
	}
	
	public boolean isWaterfrontChecked() {
		return browser.isCheckBoxSelected(".id", "waterf");
	}
	
	public String getSiteNum() {
//		return browser.getTextFieldValue(".id", "csite");
//		return browser.getTextFieldValue(".id", "siteCode");
		return browser.getTextFieldValue(".id", new RegularExpression("csite|siteCode", false));
	}
	
	public BookingData getSearchCriteriaInfo() {
		BookingData bd = new BookingData();
		bd.siteType = this.getSelectedSiteType();
		bd.loop = this.getSelectedLoop();
		bd.siteNo = this.getSiteNum();
		bd.state = this.getSelectedLoop();
//		bd.isRange = this.isDatesRangeChecked();
		bd.arrivalDate = this.getStartDate();
		bd.endDate = this.getEndDate();
		bd.lengthOfStay = this.getLengthOfStayValue();
		if(StringUtil.isEmpty(bd.endDate)&&StringUtil.notEmpty(bd.lengthOfStay)){
			bd.endDate = DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.parseInt(bd.lengthOfStay));
		}
		bd.spotWith = this.isSpotWithChecked();
		bd.equipLength = this.getEqpLengthValue();
		bd.occupants = this.getOccupantsValue();
		bd.electricHookupValue = this.getSelectedElectircHookup();
		bd.spotWithWaterHookup = this.isWaterHookupChecked();
		bd.spotWithSewerHookup = this.isSewerHookupChecked();
		bd.spotWithPullthroughDriveway = this.isPullThroughDrivewayChecked();
		bd.spotWithAccessible = this.isAccessibleChecked();
		bd.spotWithPetsAllowed = this.isPetsAllowedChecked();
		bd.spotWithWaterFront = this.isWaterfrontChecked();
		return bd;
	}
	
	/**
	 * Verify Search criteria info
	 */
	public void verifySearchCriteriaInfo(BookingData expBd) {
		BookingData actBd = this.getSearchCriteriaInfo();
		boolean result = MiscFunctions.compareString("Site Type", 
				(StringUtil.isEmpty(expBd.siteType) ? "Any camping spot" : expBd.siteType), actBd.siteType);
		result &= MiscFunctions.compareString("Loop", 
				(StringUtil.isEmpty(expBd.loop) ? "Any loop" : expBd.loop), actBd.loop);
		result &= MiscFunctions.compareString("Site #", expBd.siteNo, actBd.siteNo);
		result &= MiscFunctions.compareResult("Spot with", expBd.spotWith, actBd.spotWith);
		if (expBd.spotWith) {
			result &= MiscFunctions.compareString("equip Length", expBd.equipLength, actBd.equipLength);
			result &= MiscFunctions.compareString("occupants", expBd.occupants, actBd.occupants);
			result &= MiscFunctions.compareString("electric Hookup", 
					(StringUtil.isEmpty(expBd.electricHookupValue) ? "Not required" : expBd.electricHookupValue), actBd.electricHookupValue);
			result &= MiscFunctions.compareResult("WaterHookup", expBd.spotWithWaterHookup, actBd.spotWithWaterHookup);
			result &= MiscFunctions.compareResult("Sewer Hookup", expBd.spotWithSewerHookup, actBd.spotWithSewerHookup);
			result &= MiscFunctions.compareResult("Pull through Hookup", expBd.spotWithPullthroughDriveway, actBd.spotWithPullthroughDriveway);
			result &= MiscFunctions.compareResult("Accessible", expBd.spotWithAccessible, actBd.spotWithAccessible);
			result &= MiscFunctions.compareResult("Pets Allowed", expBd.spotWithPetsAllowed, actBd.spotWithPetsAllowed);
			result &= MiscFunctions.compareResult("WaterFront", expBd.spotWithWaterFront, actBd.spotWithWaterFront);
		}
		result &= MiscFunctions.compareResult("Date Range", expBd.isRange, actBd.isRange);
		result &= MiscFunctions.compareResult("Arrival Date", expBd.arrivalDate, actBd.arrivalDate);
		result &= MiscFunctions.compareString("Length of stay", expBd.lengthOfStay, actBd.lengthOfStay);
		
		if (!result) {
			throw new ErrorOnPageException("The search criterial is not expected! check logger error!");
		}
		logger.info("Search criteria info is correct!");
	}
}
