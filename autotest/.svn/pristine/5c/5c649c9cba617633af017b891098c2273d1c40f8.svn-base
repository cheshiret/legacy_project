package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @author jchen
 */
public class UwpSearchPanel extends UwpPage {
	
	private static UwpSearchPanel _instance = null;

	public static UwpSearchPanel getInstance() {
		if (null == _instance)
			_instance = new UwpSearchPanel();

		return _instance;
	}

	public UwpSearchPanel() {
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", new RegularExpression("reservationSearchForm|interest_section",false));
		return browser.checkHtmlObjectExists(".id", new RegularExpression("reservationSearchForm|interest_section|searchform",false));//Sara[20140220], "searchform" for plw nh
	}
	
	/**
	 * Fill in park search value.
	 * @param park - park name
	 */
	public void setPark(String park) {
		browser.setTextField(".id", "pname", park);
	}
	
	/**
	 * Fill in site number in site search panel.
	 * @param num - site number
	 */
	public void setSiteNumber(String num) {
		browser.setTextField(".id", new RegularExpression("csite|siteCode",false), num);
	}
	
	public void setStartDateValue(String date){
		browser.setTextField(".id", new RegularExpression("(arvdate|campingDate)", false), date, 0, IText.Event.ONCHANGE,IText.Event.LOSEFOCUS);//LOSEFOCUS
	}
	
	/**
	 * Fill in search from date.
	 * @param date - start date
	 */
	public void setStartDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		this.setStartDateValue(date);
	}
	
	/** Leave start date blank.*/
	public void removeStartDate(){
	  	browser.setTextField(".id", "arvdate", "");
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
	
	public void setLengthOfStayValue(String days){
		browser.setTextField(".id", "lengthOfStay", days);
	}
	/**
	 * Fill in length of stay.
	 * @param days - length of stay
	 */
	public void setLengthOfStay(String days) {
		if (days == null || days.length() < 1)
			days = "2";
		this.setLengthOfStayValue(days);
	}
	
//	public void removeFocus(){
//		browser.clickGuiObject(".id", new RegularExpression("unifSearchForm|campsitesearchform", false));//new RegularExpression("csite|siteCode",false));
//	}
	
	/**
	 * Select search state.
	 * @param state - state
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "pstate", state);
	}
	
	/**
	 * Deselect search state.
	 */
	public void deselectState() {
		browser.selectDropdownList(".id", "pstate", 0);
	}
	
	/**
	 * Select range radio button to do range search.
	 */
	public void selectRangeRadioButton() {
		browser.selectRadioButton(".id", "rangeyes");
	}
	
	/**
	 * Fill in range date search data.
	 * @param startDate - from date
	 * @param endDate - end date
	 */
	public void setRangeDate(String startDate, String endDate) {
		this.selectRangeRadioButton();

		if (startDate == null || startDate.length() < 1) {
			startDate = DateFunctions.getDateStamp(false);
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		} else if (endDate == null || endDate.length() < 1) {
			startDate = startDate.replaceAll("-", "/");
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		}

		this.setStartDate(startDate);
		this.setEndDate(endDate);
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
		browser.selectDropdownList(".id", "siteType", item);
	}
	
	/**
	 * De-select sity type.
	 */
	public void deselectSiteType() {
		browser.selectDropdownList(".id", "siteType", 0);
	}
	
	/**
	 * Select site loop from dropdown list.
	 * @param item - site type
	 */ 
	public void selectLoop(String item) {
		browser.selectDropdownList(".id", "loop", item);
	}
	
	/**
	 * Deselect sity loop.
	 */
	public void deselectLoop() {
		browser.selectDropdownList(".id", "loop", 0);
	}
	
	/**
	 * Fill in Possible Location.
	 */
	public void setPossibleLocation(String txt) {
		browser.setTextField(".id", "landmarkName", txt);
	}
	
	/**
	 * Fill in blank to clear Possible Location field.
	 */
	public void clearPossibleLocation() {
		setCityOrZip("");
	}
	
	/**
	 * Fill in city zip.
	 */
	public void setCityOrZip(String txt) {
		browser.setTextField(".id", "landmarkName", txt);
	}
	
	/**
	 * Fill in blank to clear city zip field.
	 */
	public void clearCityOrZip() {
		setCityOrZip("");
	}
	
	/**
	 * Select Park With check box.
	 */
	public void selectCheckboxParkWith() {
		browser.selectCheckBox(".id", "parkamm");
	}
	
	/**
	 * Deselect Park With check box.
	 */
	public void deselectCheckboxParkWith() {
		browser.unSelectCheckBox(".id", "parkamm");
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
	 * Select amenity item for park with, will deselect when item is none.
	 * @param item - amenity item
	 */
	public void selectAmenityForParkWith(String item) {
		if (item.length() < 1)
			this.deselectAmenityForParkWith();
		else
			browser.selectDropdownList(".id", "amenity", item);
	}
	
	/**
	 * Deselect amenity for park with.
	 */
	public void deselectAmenityForParkWith() {
		browser.selectDropdownList(".id", "amenity", 0);
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
	 * Deselect wait for front spot with check box.
	 */
	public void deselectWaterFrontForSpotWith() {
		browser.unSelectCheckBox(".id", "waterf");
	}
	
	/**
	 * Try to deselect the given item and ignore any exceptions
	 * @param item - can be SiteType, State, Loop
	 */
	public void tryToDeselect(String item) {
		try {
			if(item.equalsIgnoreCase("SiteType")) {
				deselectSiteType();
			} else if(item.equalsIgnoreCase("State")) {
				deselectState();
			} else if(item.equalsIgnoreCase("Loop")) {
				deselectLoop();
			} else {
				throw new ItemNotFoundException("Item "+item+" is unknown");
			}
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Fill in all search criteria.
	 * @param bd - booking data
	 * @param isSearchByState - whether or not to find koa park by searching state only
	 */
	public void setupSearchCriteria(BookingData bd) {
		logger.info("Setup search criterias");
		
		if (bd.siteType == null || bd.siteType.length() < 1)
			tryToDeselect("SiteType");
		else {
			logger.info("siteType="+bd.siteType);
			selectSiteType(bd.siteType);
		}
		
		if (bd.isRange) {
			logger.info("Do range date search");
			if (bd.startDate.length() < 1 && bd.arrivalDate.length() > 0) {
				logger.info("range start date="+bd.arrivalDate);
				bd.startDate = bd.arrivalDate;
			}
			
			if(!isRangeBtnExisting()){
				logger.info("range flexible date="+bd.flexibleDate);
			    this.selectFlexibleDate(bd.flexibleDate);
			    this.setStartDate(bd.startDate);
			}else{
				logger.info("range start date="+bd.startDate+", end date="+bd.endDate);
				this.selectRangeRadioButton();
				this.setRangeDate(bd.startDate, bd.endDate);
			}
		} else {
			logger.info("Do specific date search");
			if (!bd.isArrivalDateBlank){
				logger.info("specific date="+bd.arrivalDate);
				this.setSpecificDate(bd.arrivalDate);
			}else{
				this.setStartDateValue("");
			}
		}

		if (bd.state == null || bd.state.length() < 1)
			tryToDeselect("State");
		else {
			logger.info("state="+bd.state);
			selectState(bd.state);
		}
		
		this.setPossibleLocation(bd.near);

		if(bd.contractCode.equalsIgnoreCase("KOA") && !TestProperty.getProperty("target_env").equalsIgnoreCase("live")) {
		  	//force to use Tom_S_Campground for KOA except search park only by state name
		  	bd.park=TestProperty.getProperty("web.koa.park");
	  	}
		logger.info("park="+bd.park);
		this.setPark(bd.park);

		if (bd.loop == null || bd.loop.length() < 1)
			tryToDeselect("Loop");
		else {
			logger.info("loop="+bd.loop);
			this.selectLoop(bd.loop);
		}
		
		if(!bd.contractCode.equals("KOA")) {
			logger.info("site number="+bd.siteNo);
			this.setSiteNumber(bd.siteNo);//can not search specified site for KOA park
		}
		
		if(!bd.isLengthOfStayBlank){
			logger.info("length of stay="+bd.lengthOfStay);
			this.setLengthOfStay(bd.lengthOfStay);
//			this.removeFocus();
		}else{
			this.setLengthOfStayValue("");
		}

		if (bd.parkWith) {
			logger.info("select ParkWith check box with amenity="+bd.parkWithAmenity);
			this.selectCheckboxParkWith();
			this.selectAmenityForParkWith(bd.parkWithAmenity);
		}
		
		if(bd.spotWith) {// for spot with search
			logger.info("Select SpotWith checkbox");
		  	this.selectCheckboxSpotWith();
		  	
		  	if(null != bd.spotWithEquipLength && bd.spotWithEquipLength.length()>1) {
		  		logger.info("SpotWith EquipLength="+bd.spotWithEquipLength);
		  		this.setEuipLengthForSpotWith(bd.spotWithEquipLength);
		  	}
		  	if(null != bd.spotWithOccupants && bd.spotWithOccupants.length()>1)
		  		logger.info("SpotWith MaxOccupants="+bd.spotWithOccupants);
		  	  	this.setMaxOccupantsForSpotWith(bd.spotWithOccupants);
		  	
		  	if(null == bd.spotWithElectricHookup || bd.spotWithElectricHookup.length()<1) {
		  	  	this.deselectElectricHookupForSpotWith();
		  	}else {
		  		logger.info("SpotWith ElectricHookup="+bd.spotWithElectricHookup);
		  	  	this.selectElectricHookupForSpotWith(bd.spotWithElectricHookup);
		  	}
		  	 
		  	if(bd.spotWithWaterHookup) {
		  		logger.info("SpotWith WaterHookup");
		  	  	this.selectWaterHookupForSpotWith();
		  	}
		  	if(bd.spotWithSewerHookup) {
		  		logger.info("SpotWith SewerHookup");
		  	  	this.selectSewerHookupForSpotWith();
		  	}
		  	if(bd.spotWithPullthroughDriveway) {
		  		logger.info("SpotWith PullThroughDriveway");
		  		this.selectPullThroughDrivewayForSpotWith();
		  	}
		  	if(bd.spotWithAccessible) {
		  		logger.info("SpotWith Accessible");
		  	  	this.selectAccessibleForSpotWith();
		  	}
		  	
		  	if(bd.spotWithPetsAllowed)  {
		  		logger.info("SpotWith PetsAllowed");
		  	  	this.selectPetsAllowedForSpotWith();
		  	}
		  	
		  	if(bd.spotWithWaterFront) {
		  		logger.info("SpotWith WaterFront");
		  	  	this.selectWaterFrontForSpotWith();
		  	}
		}
	}
	
	private void selectFlexibleDate(String date) {
		if(date.length()<1){
			browser.selectDropdownList(".id", "campingDateFlex", "Flexible for next 2 weeks");	
		}else{
			browser.selectDropdownList(".id", "campingDateFlex", date);	
		}			
	}

	public void findCampgroundsViaMapSearch(BookingData bd){
		this.setupSearchCriteria(bd);		
		this.doMapSearch(bd.conType);
	}
	
	/**
	 * Click on search submit button and 
	 * click on contract type link to show specified type of parks.
	 * @param conType - contract type
	 */
	public void doSearch(String conType) {
		this.clickSearch();
		this.searchParkWaitExists();
			
		if(conType ==null || conType.length()<1) {
		  	return;
		}
		
		if (conType.equalsIgnoreCase("state"))
			this.clickState();
		else if (conType.equalsIgnoreCase("federal"))
			this.clickFederal();
		else if (conType.equalsIgnoreCase("private"))
			this.clickPrivate();
		else if (conType.equalsIgnoreCase("county"))
			this.clickState();
		else if (conType.equalsIgnoreCase("regional"))
			this.clickRegional();
		else if (conType.equalsIgnoreCase("other"))
			this.clickOther();
		else
			throw new ItemNotFoundException(conType + " is unknown");
		this.searchParkWaitExists();
	}
	
	public void doMapSearch(String conType) {
		this.clickSearch();
		this.searchMapWaitExists();
			
		if(conType ==null || conType.length()<1) {
		  	return;
		}
		
		if (conType.equalsIgnoreCase("state"))
			this.clickState();
		else if (conType.equalsIgnoreCase("federal"))
			this.clickFederal();
		else if (conType.equalsIgnoreCase("private"))
			this.clickPrivate();
		else if (conType.equalsIgnoreCase("county"))
			this.clickState();
		else if (conType.equalsIgnoreCase("regional"))
			this.clickRegional();
		else if (conType.equalsIgnoreCase("other"))
			this.clickOther();
		else
			throw new ItemNotFoundException(conType + " is unknown");
		this.searchMapWaitExists();
	}
	
	/**
	 * Retrieve the start date.
	 * @return - start date
	 */
	public String getStartDate() {
		return browser.getTextFieldValue(".id", "arvdate");
	}
	
	/**
	 * Retrieve the end date.
	 * @return - end date
	 */
	public String getEndDate() {
		return browser.getTextFieldValue(".id", "enddate");
	}
	
	/**
	 * Increase the range date search date 30 days more.
	 * Both start date and end date.
	 */
	public void increaseRangeDate() {

		String oldStart = getStartDate();
		oldStart = DateFunctions.formatDate(oldStart);
		String newStart = DateFunctions.getDateAfterGivenDay(oldStart, 30);
		String newEnd = DateFunctions.getDateAfterGivenDay(newStart, 30);

		this.setStartDate(newStart);
		this.setEndDate(newEnd);
	}
	
	/**
	 * Increase the Arrival Date to 30 days more.
	 */
	public void increaseSpecificDate() {
		String oldArrivalDate = getStartDate();
		oldArrivalDate = DateFunctions.formatDate(oldArrivalDate);
		String newArrivalDate = DateFunctions.getDateAfterGivenDay(oldArrivalDate, 30);
		this.setStartDate(newArrivalDate);
	}
	/**
	 * Search site. set up search criteria and do specific or range date search.
	 * @param bd - booking data
	 */
	public void searchSite(BookingData bd) {
		this.setupSearchCriteria(bd);
		if (bd.isRange)
			this.searchSiteWithDateRange(bd, bd.maxLoop);
		else
			this.searchSiteWithSpecificDate(bd, bd.maxLoop);
	}
	
	/**
	 * Search site with specific date. method starts from search panel, 
	 * end at camp site details page.
	 * @param bd - booking data
	 * @param maxLoop - max loop for increasing arrival date 3 days more
	 */
	public void searchSiteWithSpecificDate(BookingData bd, int maxLoop) {
		int count = 0;
		this.doSearch(bd.conType);
		this.searchParkWaitExists();
		
		IHtmlObject[] parks = getAvailableParks(bd.park, bd.contractCode);

		while (parks.length < 1) {
			if (!gotoNextResultPage()) {
				this.increaseSpecificDate();
				this.doSearch(bd.conType);
				count++;
				if (count > maxLoop) {
					throw new ItemNotFoundException("There is no inventory in "
							+ maxLoop * 3 + " days starting from "
							+ bd.arrivalDate);
				}
			}
			this.searchParkWaitExists();
			parks = getAvailableParks(bd.park,bd.contractCode);
		}

		parks[0].click();
		Browser.unregister(parks);
		this.searchSiteWaitExists();

		//select the first available site
		//already handle the KOA park name when searching available parks
		if(bd.contractCode.equalsIgnoreCase("KOA")) {
			RegularExpression href=new RegularExpression("siteId="+bd.siteID,false);
			browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".href",href,".text",new RegularExpression("See Details|Select",false)),true);//changed to select from 3.04.01.6
		} else {
			IHtmlObject[] sites=getAvailableSites();
			sites[0].click();
			Browser.unregister(sites);
		}
	}
	
	/**
	 * Search park with Date Range. This method starts from search panel,
	 * end at camp site details page.
	 * @param bd - booking data
	 * @param maxLoop - maximum loop for increasing arrival date 2 weeks days more
	 */
	public void searchSiteWithDateRange(BookingData bd, int maxLoop) {
		int count = 0;
		this.doSearch(bd.conType);
		this.searchParkWaitExists();
		
		IHtmlObject[] parks = getAvailableParks(bd.park, bd.contractCode);
		
		while (parks.length < 1) {
			if (!gotoNextResultPage()) {
				this.increaseRangeDate();
				this.doSearch(bd.conType);
				count++;
				if (count > maxLoop) {
					throw new ItemNotFoundException("There is no inventory in "
							+ maxLoop * 14 + " days starting from "
							+ bd.arrivalDate);
				}
			}
			this.searchParkWaitExists();
			parks = getAvailableParks(bd.park,bd.contractCode);
		}

		if(bd.contractCode.equalsIgnoreCase("KOA")) {
		  	bd.branch=3;
		}
		//select the first available park
		switch (bd.branch) {
		case 1: //click See Details
		default:
			parks[0].click();
			Browser.unregister(parks);
			this.searchInventoryWaitExists();
			//select the first available site
			IHtmlObject[] invs = this.getAvailableInventorys();
			count = 0;
			while (invs.length < 1) {
				Browser.unregister(invs);
				count++;
				if (count > maxLoop)
					throw new ItemNotFoundException("There is no inventory in "
							+ maxLoop * 14 + " days starting from "
							+ bd.arrivalDate);

				goNext2Weeks();
				searchInventoryWaitExists();
				invs = this.getAvailableInventorys();
			}
			invs[0].click();
			Browser.unregister(invs);
			break;
		case 2: //click park link
			String text = parks[0].getProperty(".href").toString();
			String parkname = text.split("/", 6)[4].replaceAll("_", " ");
			Browser.unregister(parks);
			IHtmlObject[] parklinks = browser.getHtmlObject(".class",
					"Html.A", ".text", parkname);
			parklinks[0].click();
			Browser.unregister(parklinks);
			browser.searchObjectWaitExists(".class", "Html.BUTTON", ".text",
					"Book Now");
			browser.clickGuiObject(".class", "Html.BUTTON", ".text",
					"Book Now");
			browser.searchObjectWaitExists(".class", "Html.A", ".text",
					"See Details");
			browser.clickGuiObject(".class", "Html.A", ".text", "See Details",
					true);
			break;
			
		case 3: //for koa
		  	parks[0].click();
			Browser.unregister(parks);
			searchSiteListWaitExists();
			
			//already handle the KOA park name when searching available parks
//			HtmlObject[] sites=getAvailableSites();
//			sites[0].click();
//			Browser.unregister(sites);
			RegularExpression href=new RegularExpression("siteId="+bd.siteID,false);
			browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".href",href,".text",new RegularExpression("(See Details)|(Select)", false)),true);
			break;
		}
	}
	
	/**
	 * Get all available inventories.
	 * @return
	 */
	public IHtmlObject[] getAvailableInventorys() {
		RegularExpression reg = new RegularExpression("^(A|A [0-9]+)$", false);
		return browser.getHtmlObject(".class", "Html.A", ".text", reg);
	}
	/**
	 * Click on go to Next 2 Weeks link.
	 */
	public void goNext2Weeks() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next 2 weeks >");
	}
	/**
	 * Click on to go to Next results Page.
	 * @return - Link's href
	 */
	public boolean gotoNextResultPage() {
		boolean has = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id", "resultNext");
		if (objs.length > 0){
			String href = objs[0].getProperty(".href");
			if(href!=null){
				has = href.toString().length() > 0;
			}
		}

		if (has)
			objs[0].click();
		Browser.unregister(objs);
		return has;
	}
	
	/**
	 * Get all available park test objects. if not production, the only get Tom's Campground for KOA
	 * @return
	 */
	public IHtmlObject[] getAvailableParks(String parkName,String conCode) {
		conCode = conCode.toUpperCase();
		RegularExpression reg;
		String park=null;
		if(parkName!=null && parkName.length()>0) {
		  	park=parkName.replaceAll("\\(|\\)","").replaceAll("( |'|&| |-)+","_");
		} 

		if (conCode.equalsIgnoreCase("KOA") && 
					!TestProperty.getProperty("target_env").equalsIgnoreCase("live")) {
			park="Toms_Campground";//force to use this park when KOA test in QA env
		}
		
		if(park!=null) {
			reg = new RegularExpression(".*/" + park+ ".*contractCode=" + conCode + "&.*",false);
		} else {
		  	reg = new RegularExpression(".*&contractCode=" + conCode + "&.*",false);
		}
		return browser.getHtmlObject(".href", reg, ".className",  new RegularExpression("book (now|elsewhere)", false)); // Lesley[20130924]: change for ELS external park);
	}
	
	/**
	 * Get all available park/site with Book Now on them objects.
	 * @return - Test Object[]
	 */
	public IHtmlObject[] getAvailableSites() {
		return browser.getHtmlObject(".class", "Html.A", ".className","book now");
	}
	
	public IHtmlObject[] getAvailableSitesForKOA() {
		RegularExpression href=new RegularExpression(".*/Tom_S_Campground/r/koaCampsiteDetails.do\\?contractCode=KOA.*parkId=196754",false);
		Property[] p=Property.toPropertyArray(".class", "Html.A", ".className","book now",".href",href);
		return browser.getHtmlObject(p);
	}
	
	/**
	 * Wait page to load by waiting Online Availablity link.
	 */
	public void searchParkWaitExists() {
		browser.searchObjectWaitExists(".class", "Html.TABLE", ".id", "shoppingitems", "ParkSearchResult");
//		browser.searchObjectWaitExists(".class", "Html.A", ".text", "Online Availability", "ParkSearchResult");
	}
	
	public void searchMapWaitExists() {
		browser.searchObjectWaitExists(".id", "searchcgrounds", ".className", "all search");
	}
	
	/**
	 * Wait for page to load by waiting Site Type link.
	 */
	public void searchSiteWaitExists() {
		RegularExpression reg = new RegularExpression("Site (T|t)ype", false);
		browser.searchObjectWaitExists(".class", "Html.A", ".text", reg, "SearchSiteResult");
	}
	
	public void searchSiteListWaitExists() {
	  	browser.searchObjectWaitExists(".class", "Html.TABLE", ".id", "shoppingitems", "CampsiteList");
	}
	
	/**
	 * Wait for page to load.
	 */
	public void searchInventoryWaitExists() {
		browser.searchObjectWaitExists(".class", "Html.TABLE", ".id", "calendar");
	}
	
	/**
	 * Wait for page to load after click park type link, state, other, private...
	 */
	public void parkTypeClickWaitExists(){
		RegularExpression reg = new RegularExpression("All \\([0-9]+\\)",false);
		browser.searchObjectWaitExists(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Click on State contract link to show all state parks.
	 */
	public void clickState() {
		RegularExpression reg = new RegularExpression("State \\([0-9]+\\)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Click on Federal contract link to show all Federal parks.
	 */
	public void clickFederal() {
		RegularExpression reg = new RegularExpression("Federal \\([0-9]+\\)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Click on Private contract link to show all private parks.
	 */
	public void clickPrivate() {
		RegularExpression reg = new RegularExpression("Private \\([0-9]+\\)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Click on Regional Contract link to show all regional parks.
	 */
	public void clickRegional() {
		RegularExpression reg = new RegularExpression("Regional \\([0-9]+\\)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	public void clickOther() {
		RegularExpression reg = new RegularExpression("Other \\([0-9]+\\)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Click on search campgound/campsite submit button.
	 */
	public void clickSearch() {
//		RegularExpression reg = new RegularExpression("^(homesearchbtn|search)$",	false);//Sara[3/26/2014]
		RegularExpression reg = new RegularExpression("^(homesearchbtn|search|filter)$",	false);
		browser.clickGuiObject(".id", reg, ".type", "submit");
	}
	
	/**
	 * Click on park name link.
	 */
	public void clickOnParkName() {
		RegularExpression reg = new RegularExpression(
		    ".*/campgroundDetails\\.do\\?contractCode=.+&parkId=[0-9]+.*",false);
		browser.clickGuiObject(".class", "Html.A", ".href", reg);
	}
	
	/** Wait for page to load after clicking on park name or book button. */
	public void parkDetailWaitExists() {
	  	browser.searchObjectWaitExists(".class", "Html.DIV", ".id", "contentArea");
	}
	
	/**
	 * Click on first available site.
	 * @param conCode - contract code
	 */
	public void searchFirstAvailableSites(String conCode) {
		RegularExpression reg = new RegularExpression(
				".*siteId=\\d+&contractCode=" + conCode + "&.*", false);
		IHtmlObject[] foundTOs = browser.getHtmlObject(".class", "Html.A",
				".href", reg);
		foundTOs[0].click();//click on first available site
		Browser.unregister(foundTOs);
	}
	/**
	 * Verify whether the error message display on page.
	 * @return true - found; false - not found
	 */
	public boolean isErrorExisting() {
		boolean toReturn = false;

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "msg1");
		if (objs.length > 0)
			toReturn = true;
		Browser.unregister(objs);
		return toReturn;
	}
	/**
	 * Get the error message when the search date is less than min window.
	 * @return - error message
	 */
	public String getErrorMsg() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "msg1");
		String msg = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return msg;
	}
	/**
	 * Get the first park name in park search results page.
	 * @return - park name
	 */
	public String getFirstParkName() {
		RegularExpression reg = new RegularExpression(
				".*campgroundDetails.do?.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", reg);

		String parkName = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return parkName;
	}
	/**
	 * Click on to book first park in park search results page.
	 * @return - clicked park name
	 */
	public String clickOnFirstPark() {
		RegularExpression reg = new RegularExpression("book (now|next|elsewhere)", false);
		IHtmlObject[] parks = browser.getHtmlObject(".class", "Html.A", ".className", reg);

		String href = parks[0].getProperty(".href").toString();
		int startIndex = href.indexOf("/camping/")+9;
		int endIndex = href.indexOf("/r/");
		String parkName = href.substring(startIndex, endIndex);
		
		((ILink)parks[0]).click();
		Browser.unregister(parks);
		return parkName;
	}
	/**
	 * Click on camp site link to go to campsite list page.
	 */
	public void gotoCampsiteList() {
		browser.clickGuiObject(".id", "campList");
	}
	/**
	 * Check whether the campsite list object exists.
	 * @return
	 */
	public boolean checkCampsiteListExists() {
		return browser.checkHtmlObjectExists(".id", "shoppingitems");
	}
	
	/**
	 * Click on first available park. if is not production, then just test 
	 * on Tom's Campground for KOA park.
	 * @param contractCode - contract code
	 * @param isProduction - use to control KOA park
	 */
	public void bookOnSpecifyAvailPark(BookingData bd, String parkName, String contractCode) {
		IHtmlObject[] parks = getAvailableParks(bd.park, bd.contractCode);
		
		while (parks.length < 1) {
			if (!gotoNextResultPage()) {
				throw new ItemNotFoundException("No site available for the given date.");
			}

			this.searchParkWaitExists();
			parks = getAvailableSites();
		}

		parks[0].click();
		Browser.unregister(parks);
	}
	
	/**
	 * Click on Find Other Campgrounds link.
	 */
	public void findOtherCampgrounds() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Find [o|O]ther [C|c]ampgrounds", false));
	}
	
	/**
	 * Click on 'See Details' button to go to site list page for available park;
	 * As we will check and release the inventory for test cases, so the park supposed to be available
	 * @param park - park name, can be blank, will go to first available park
	 * @param contractCode - contract code
	 */
	public void gotoSiteList(String park, String contractCode) {
		IHtmlObject[] parks = getAvailableParks(park, contractCode);
		if (parks.length < 1) {
			throw new ItemNotFoundException("No inventories for park " + park);
		}
		
		parks[0].click();
		Browser.unregister(parks);
	}
	
	/**
	 * Click on first available park. if is not production, then just test 
	 * on Tom's Campground for KOA park.
	 * @param contractCode - contract code
	 * @param isProduction - use to control KOA park
	 */
	public void clickOnFirstAvailablePark(String contractCode, boolean isProduction) {
		this.searchParkWaitExists();
		IHtmlObject[] parks = getAvailableParks(contractCode, isProduction);
		while (parks.length < 1) {
			if (!gotoNextResultPage()) {
				throw new ItemNotFoundException("No park available for the given date.");
			}

			this.searchParkWaitExists();
			parks = getAvailableSites();
		}

		parks[0].click();
		Browser.unregister(parks);
	}
	/**
	 * Click on first available park.
	 * @param contractCode - contract code
	 */
	public void clickOnFirstAvailablePark(String contractCode) {
		clickOnFirstAvailablePark(contractCode, false);
	}
	
	/**
	 * Get all available park test objects.
	 * @param conCode - contract code
	 * @return
	 */
	public IHtmlObject[] getAvailableParks(String conCode) {
		return getAvailableParks(conCode, false);
	}
	
	/**
	 * Get all available park test objects.
	 * @param conCode
	 * @param isProduction
	 * @return
	 */
	public IHtmlObject[] getAvailableParks(String conCode, boolean isProduction) {
	  	return getAvailableParks("", conCode, isProduction);
	}
	
	/**
	 * Get all available park test objects. if not production, the only get Tom's Campground for KOA
	 * @return
	 */
	public IHtmlObject[] getAvailableParks(String parkName,String conCode, boolean isProduction) {
		conCode = conCode.toUpperCase();
		RegularExpression reg;
		String park=null;
		if(parkName!=null && parkName.length()>0) {
		  	park=parkName.replaceAll("\\(|\\)","").replaceAll("( |'|&|-)+","_");
		} 
		
		if (conCode.equalsIgnoreCase("KOA") && !isProduction) {
			park="Toms_Campground";
		}
		
		if(park!=null) {
			reg = new RegularExpression(".*/" + park+ "/.*&contractCode=" + conCode + "&.*",false);
		} else {
		  	reg = new RegularExpression(".*&contractCode=" + conCode + "&.*",false);
		}
		
		return browser.getHtmlObject(".href", reg, ".className", "book now");
	}
	
	/**
	 * Verify whether the range radio button existing.
	 * @return true - found; false - not found
	 */
	public boolean isRangeBtnExisting() {
		boolean toReturn = false;

		IHtmlObject[] objs = browser.getHtmlObject(".id", "rangeyes");
		if (objs.length > 0)
			toReturn = true;
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**Below methods are about getting Search Criteria Values*/
	public String getSelectedSiteType() {
		return browser.getDropdownListValue(".id", "siteType");
	}
	
	public boolean isStateListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", "pstate");
	}
	
	public String getSelectedState() {
		return browser.getDropdownListValue(".id", "pstate");
	}
	
	public String getParkNameValue() {
		return browser.getTextFieldValue(".id", "pname");
	}
	
	public boolean isDatesRangeChecked() {
		IHtmlObject[] objs = browser.getRadioButton(".id", "rangeyes");
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find date range radio button");
		}
		boolean isChecked = ((IRadioButton)objs[0]).isSelected();
		Browser.unregister(objs);
		return isChecked;
	}
	
	public String getLengthOfStayValue() {
		return browser.getTextFieldValue(".id", "lengthOfStay");
	}
	
	public boolean isParkWithChecked() {
		return browser.isCheckBoxSelected(".id", "parkamm");
	}
	
	public String getSelectedParkWithAmenity() {
		return browser.getDropdownListValue(".id", "amenity");
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

	public BookingData getSearchCriteriaInfo() {
		BookingData bd = new BookingData();
		bd.siteType = this.getSelectedSiteType();
		if (this.isStateListExist())
			bd.state = this.getSelectedState();
		bd.park = this.getParkNameValue();
		bd.isRange = this.isDatesRangeChecked();
		bd.arrivalDate = this.getStartDate();
		bd.endDate = this.getEndDate();
		bd.lengthOfStay = this.getLengthOfStayValue();
		bd.parkWith = this.isParkWithChecked();
		bd.parkWithAmenity = this.getSelectedParkWithAmenity();
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
		boolean result = MiscFunctions.compareString("Site Type", expBd.siteType, actBd.siteType);
		if (!StringUtil.isEmpty(expBd.state)) {
			result &= MiscFunctions.compareString("State", expBd.state, actBd.state);
		}
		result &= MiscFunctions.compareString("Park", 
				(StringUtil.isEmpty(expBd.park) ? "(optional)" : expBd.park), actBd.park);
		result &= MiscFunctions.compareResult("Date Range", expBd.isRange, actBd.isRange);
		result &= MiscFunctions.compareResult("Arrival Date", expBd.arrivalDate, actBd.arrivalDate);
		result &= MiscFunctions.compareString("Length of stay", expBd.lengthOfStay, actBd.lengthOfStay);
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
		
		if (!result) {
			throw new ErrorOnPageException("The search criterial is not expected! check logger error!");
		}
		logger.info("Search criteria info is correct!");
	}
}
