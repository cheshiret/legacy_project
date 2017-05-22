/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author jdu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsSiteSearchPage extends OrmsPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSiteSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSiteSearchPage() {
		browser = Browser.getInstance();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSiteSearchPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSiteSearchPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression("^\\W*(Basic|Advanced)\\W*$", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", reg);
	}

	/**The site search page need long sleep to wait*/
	public void waitAdvExists() throws PageNotFoundException {
		waitAdvExists(Browser.LONG_SLEEP);
	}

	/**The site search page need long sleep to wait*/
	public void waitAdvExists(int seconds) throws PageNotFoundException {
		browser.searchObjectWaitExists(".class", "Html.INPUT.text", ".id","date_range_date_start", seconds);
	}

	/**
	 * Input park name as search criteria
	 * @param parkName
	 */
	public void setParkName(String parkName) {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.INPUT.text",".id","park");
		if(objs.length>0) {
			//if the park name field can't be edited, please make sure you assign "TransferReservationAllFacilities" Feature to the user in Admin Manager.
//			((IText) objs[0]).setText(parkName, IText.ONCHANGE);
			browser.setTextField(".id", "park", parkName, true);
		} else {
			Browser.unregister(objs);
			objs=browser.getDropdownList(".id","location.parkId");
			if(objs.length>0 && parkName.length() > 0) {
				((ISelect)objs[0]).select(parkName);
			}
		}
		
		Browser.unregister(objs);
				
//	  	if(browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id","park"))
//	  	{
//	    	browser.setTextField(".id", "park", parkName);  //for call manager
//	    	//click park name label to clear the dropdown
//			browser.clickGuiObject(".class","Html.LABEL",".text","Park Name",true);
//	  	} else if(browser.checkHtmlObjectExists(".class","Html.SELECT",".id","location.parkId"))
//		{
//	  		browser.selectDropdownList(".id","location.parkId",parkName);  //for field manager
//		}
		
	}
    
	/**Get park name in Call manager*/
	public String getParkNameFromCall(){
		String parkName ="";
		if(browser.checkHtmlObjectExists(".id", "park")){
			parkName=browser.getTextFieldValue(".id", "park"); //for call manager
			
		}
		
		return parkName;
	}
	/**Get park name in field manager*/
	public String getParkName() {
		String parkName ="";
//		if(browser.checkTestObjectExists(".id", "park")){
//			parkName=browser.getTextFieldValue(".id", "park"); //for call manager
//			
//		}
		if(browser.checkHtmlObjectExists(".id", "location.parkId")){
			if (parkName == null||"".equals(parkName))
				parkName = browser.getDropdownListValue(".id", "location.parkId", 0); //for field manager
		}

		return parkName;
	}

	/**
	 * Input arrival date
	 * @param date
	 */
	public void setArrivalDate(String date) {
//		browser.setTextField(".id","date_specific_date_start_ForDisplay",date, 0,IText.Event.LOSEFOCUS);
		browser.setTextField(".id", "date_specific_date_start_ForDisplay", date, true, 0, IText.Event.ONCHANGE,IText.Event.LOSEFOCUS);
	}

	/**
	 * get arrival date
	 * @return
	 */
	public String getArrivalDate() {
		RegularExpression reg = new RegularExpression("date_specific_date_start(_ForDisplay)?", false);
		return browser.getTextFieldValue(".id", reg);
	}

	/**
	 * input departure date
	 * @param date
	 */
	public void setDepartureDate(String date) {
		RegularExpression reg = new RegularExpression("date_specific_date_end(_ForDisplay)?", false);
		browser.setTextField(".id", reg, date, true, 0, IText.Event.ONCHANGE,IText.Event.LOSEFOCUS);
	}
	
	private void removeFocus(){
//		browser.clickGuiObject(".className", "label_row", ".text", "Park", true);
		browser.clickGuiObject(".className", "footer_branding",1);
	}
	
	public String getDepartureDate() {
		RegularExpression reg = new RegularExpression("date_specific_date_end(_ForDisplay)?", false);
		return browser.getTextFieldValue(".id",reg);
	}

	/**
	 * Input nights of number
	 * @param nights
	 */
	public void setNights(String nights) {
		IHtmlObject[] objs=browser.getTextField(".id","date_specific_nights");
		if(objs.length>0) {
//			((IText)objs[0]).setText(nights, IText.ONCHANGE);
			browser.setTextField(".id","date_specific_nights", nights, true);
		}
		Browser.unregister(objs);
	}

	/**
	 * Get night
	 * @return
	 */
	public String getNights() {
		return browser.getTextFieldValue(".id", "date_specific_nights");
	}

	/**Click Advanced button*/
	public void clickAdvanced() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Advanced(| >>)",false));//"Advanced >>"
	}
	
	/**Check Advanced button is existed*/
	public boolean isAdvancedExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Advanced(| >>)",false));//"Advanced >>"
	}
	
	/**Click Basic Button*/
	public void clickBasic() {
		browser.clickGuiObject(".class", "Html.A", ".text", "<< Basic");
	}
	//test code
	public void clickWrongAdv(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Basic >>");
	}

	/**Input advanced start date*/
	public void setBeginRange(String begin) {
		RegularExpression reg = new RegularExpression("date_range_date_start(_ForDisplay|)", false);
		browser.setTextField(".id", reg, begin, 0, IText.Event.ONCHANGE, IText.Event.LOSEFOCUS);
	}

	/**Input advanced end date*/
	public void setEndRange(String end) {
		RegularExpression reg = new RegularExpression("date_range_date_end(_ForDisplay|)", false);
		browser.setTextField(".id", reg, end, 0, IText.Event.ONCHANGE, IText.Event.LOSEFOCUS);
	}

	/**Input night*/
	public void setRangeNights(String nights) {
		browser.setTextField(".id", "date_range_nights", nights);
	}

	/**Select state*/
	public void selectState(String state) {
		if(StringUtil.notEmpty(state)) {
			browser.selectDropdownList(".id", "locationstate", state, true);
		}
	}
	
	/**Select site type*/
	public void selectSiteType(String siteType) {
		if(StringUtil.notEmpty(siteType)) {
			browser.selectDropdownList(".id", "siteType", siteType, true);
		}
	}

	/**Select area or loop*/
	public void setAreaLoop(String loop) {
		browser.setTextField(".id", "location.Loop", loop);
	}

	/**
	 * Input site number
	 * @param siteNum
	 */
	public void setSiteNumber(String siteNum) {
		browser.setTextField(".id", "productCd", siteNum);
	}

	/**
	 * select "Looking for" drop down
	 * @param item
	 */
	public void selectLookingFor(String item) {
		browser.selectDropdownList(".id", "lookingFor", item, true);
	}

	/**Select weekend only checkbox*/
	public void selectWeekendOnly() {
		browser.selectCheckBox(".id", "weekendsOnly");
	}

	/**
	 * Select electricity hookup
	 * @param item
	 */
	public void selectElectricityHookup(String item) {
		browser.selectDropdownList(".id", "selectedAttributeIdValues_218",item);
	}

	/**
	 * Select type of use
	 * @param item
	 */
	public void selectTypeOfUse(String item) {
		browser.selectDropdownList(".id", "unitOfStayType", item);
	}

	/**
	 * Get Type of use
	 * @return
	 */
	public String getTypeOfUse() {
		String type="";
		try {
			type=browser.getDropdownListValue(".id", "unitOfStayType", 0);
		} catch(Exception e) {
			String text = browser.getObjectText(".className", "inputwithrubylabel", ".text",new RegularExpression("Type of Use.+",false));
			type = (text==null)?text:text.replaceAll("Type of Use", "").trim();
		}
		return type;
	}

	/**
	 * Input number of occupants
	 * @param number
	 */
	public void setNumberOfOccupants(String number) {
		browser.setTextField(".id", "selectedAttributeIdValues_12", number);
	}

	/**
	 * Input vehicle length
	 * @param length
	 */
	public void setVechicleLength(String length) {
		browser.setTextField(".id", "selectedAttributeIdValues_109", length);
	}

	/**Select reservableonly*/
	public void unSelectReservableOnly() {
		if(browser.checkHtmlObjectExists(".class","Html.INPUT.checkbox",".id", "reservableOnly")){
			browser.unSelectCheckBox(".id", "reservableOnly",0);
		} else if(browser.checkHtmlObjectExists(".class","Html.INPUT.radio",".id","reservableOnly")){
			browser.selectRadioButton(".id","reservableOnly",1);
		}		
	}
	
	/**
	 * Get number which '.id' is 'reservableOnly'
	 * @return
	 */
	public int getReservationOnlyNum(){
		int objectLength = 0;
        IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", "reservableOnly");
        objectLength = objs.length;
        
        Browser.unregister(objs);
        return objectLength;
	}
	
	public void selectReservableOnly() {
		if(browser.checkHtmlObjectExists(".class","Html.INPUT.checkbox",".id", "reservableOnly")){
			browser.selectCheckBox(".id", "reservableOnly",0);
		} else if(browser.checkHtmlObjectExists(".class","Html.INPUT.radio",".id","reservableOnly")){
			browser.selectRadioButton(".id","reservableOnly",0);
		}		
	}
	
	public void unSelectReservableRadioOnly(){
		browser.selectRadioButton(".id","reservableOnly",1);
	}
	
	/** select radio 'Show Reservable Only'for camping lottery in Site Search page */
	public void selectShowReservableLotteryOnly(){
		if(checkSearchOptionsExit("Show Reservable & Lottery Only")){//1
			browser.selectRadioButton(".id","reservableOnly",1);
		}
	}
	
	/** select radio 'Show All 'for camping lottery in Site Search page */
	public void selectShowAll(){
		if(checkSearchOptionsExit("Show All")){//2
			browser.selectRadioButton(".id","reservableOnly",2);
		}
	}

	/**
	 * 
	 * @param index: 0:showReservableOnly, 1:showReservableOrLotteryOnly, 2:showAll
	 * @return
	 */
//	public boolean checkSearchOptionsExit(int index){
//		HtmlObject[] objs = browser.getHtmlObject(".id","reservableOnly");
//		boolean exit = false;
//		exit = browser.checkHtmlObjectExists(".id","reservableOnly", objs[index]);
//		return exit;
//	}
	
	public boolean checkSearchOptionsExit(String value){
		return browser.checkHtmlObjectExists(".className","orderItemDetailBlue", ".text", value);
	}
	
	/**
     * verify the Search Options in site search page 
     * @param showOption
     * @param defaultSelect
     * @param i: 0:showReservableOnly, 1:showReservableOrLotteryOnly, 2:showAll
     */
	public void verifySearchOption(boolean showOption, boolean defaultSelect, int i){
		IHtmlObject[] objs = browser.getHtmlObject(".id","reservableOnly");
		String searchOption = "";
		
		if(i==0){
			searchOption = "Show Reservable Only";
		}if(i==1){
			searchOption = "Show Reservable/Lottery Only";
		}if(i==2){
			searchOption = "Show All";
		}
		
		if(showOption){
			if(!objs[i].exists()){
				throw new ErrorOnDataException("The radio button"+ searchOption +"should exit.");
			}else if(defaultSelect){
				if(objs[i].getAttributeValue(".checked") == null){
					throw new ErrorOnDataException("The radio button"+ searchOption +"should be selected.");
				}
			}
		}else if(objs.length>0){
			throw new ErrorOnDataException("The radio button"+ searchOption +"should not exit.");
		}
		Browser.unregister(objs);
	}
	
	/**Select camping unit*/
	public void selectValidCampingUnit(String item) {
		browser.selectDropdownList(".id", "equipmentTypeId", item);
	}

	/**Select air condition*/
	public void selectAirCondition(String condition) {
		browser.selectDropdownList(".id", "selectedAttributeIdValues_229",
				condition);
	}

	/**Select ADA accessable*/
	public void selectadaAccessible() {
		browser.selectCheckBox(".id", "selectedAttributeIdValues_104");
	}

	/**Select cable TV*/
	public void selectcableTV() {
		browser.selectCheckBox(".id", "selectedAttributeIdValues_37");
	}

	/**Select Pet allowed type*/
	public void selectpetAllowed() {
		browser.selectCheckBox(".id", "selectedAttributeIdValues_220");
	}

	/**Select rental equipment*/
	public void selectequipmentRentals() {
		browser.selectCheckBox(".id", "selectedServiceActivityIds_32");
	}

	/**Select qty of bed*/
	public void setNumofBeds(String numofbed) {
		browser.setTextField(".id", "selectedAttributeIdValues_226", numofbed);
	}

	/**Input Drive of length*/
	public void setDriveoflength(String length) {
		browser.setTextField(".id", "selectedAttributeIdValues_11", length);
	}

	/**Select shade type*/
	public void selectShade(String shade) {
		browser.selectDropdownList(".id", "selectedAttributeIdValues_10",shade);
	}

	/**Select Horse stall */
	public void selectHorseStall() {
		browser.selectCheckBox(".id", "selectedAttributeIdValues_28");
	}

	/**Select proximity to water*/
	public void selectproximityToWater(String water) {
		browser.selectDropdownList(".id", "selectedAttributeIdValues_24",water);
	}

	/**Select water hookup*/
	public void selectWaterHookup() {
		browser.selectCheckBox(".id", "selectedAttributeIdValues_32");
	}
	
	/**Select water hookup*/
	public void selectFishing() {
		browser.selectCheckBox(".id", "selectedServiceActivityIds_3");
	}

	/**Select water sports*/
	public void selectWaterSports() {
		browser.selectCheckBox(".id", "selectedServiceActivityIds_28");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^OK$",false), true);
	}

	/** Search for a site using data stored in TestData and SiteInfoData objects */
	public void searchSite(SiteInfoData site) throws PageNotFoundException {
	  	setupSearchCriteria(site);
	  	browser.waitExists(LONG_SLEEP);
	  	clickOK();
	}
	
	public String getMessage(){
		IHtmlObject[] message=browser.getHtmlObject(".id", "NOTSET");
		String mess=message[0].getProperty(".text");
		return mess;
	}

	/**Input search criteria*/
	public void setupSearchCriteria(SiteInfoData site) {
		if(site.ShowReservableLotteryOnly){
			this.selectShowReservableLotteryOnly();
		}
		if(site.showAll){
			this.selectShowAll();
		}
	  	if(site.state!=null && site.state.length()>0)
	  	  	this.selectState(site.state);// Call manager for Idaho state need to select state
		if (site.advanced) { //advanced site search
			if(isAdvancedExist()){
				this.clickAdvanced();
			}else{
				this.clickWrongAdv();
			}
			if (site.rangeStart!=null &&site.rangeStart.length()>0) {
				this.setBeginRange(site.rangeStart);
			} else
				throw new ItemNotFoundException("Range start date was not defined.");

			if (site.rangeEnd!=null && site.rangeEnd.length()>0) {
				this.setEndRange(site.rangeEnd);
			} else
				throw new ItemNotFoundException("Range end date was not defined.");
//			browser.clickGuiObject(".class","Html.TD",".text","Date Range");

			if (site.dayNightNum!=null && site.dayNightNum.length()>0)
				this.setRangeNights(site.dayNightNum);
			else
				throw new ItemNotFoundException("Range day/night number was not defined.");

			if (site.airCondition!=null && site.airCondition.length()>0)
				this.selectAirCondition(site.airCondition);

			if (site.adaAccessible)
				this.selectadaAccessible();

			if (site.cableTV)
				this.selectcableTV();

			if (site.drivewayLength!=null && site.drivewayLength.length()>0)
				this.setDriveoflength(site.drivewayLength);

			if (site.petAllowed)
				this.selectpetAllowed();

			if (null != site.shade && site.shade.length()>0)
				this.selectShade(site.shade);

			if (site.equipmentRentals)
				this.selectequipmentRentals();

			if (site.horseStall)
				this.selectHorseStall();

			//			if(site.proximityToWater.length()>0)
			if (null != site.proximityToWater&& site.proximityToWater.length()>0)
				this.selectproximityToWater(site.proximityToWater);

			if (site.fishing)
			  	this.selectFishing();
			  
			if (site.waterHookup)
				this.selectWaterHookup();

			if (site.waterSports)
				this.selectWaterSports();

		} else { //basic site search
			if(!isAdvancedExist()){
				this.clickBasic();
			}
		
			if (site.arrivalDate!=null && site.arrivalDate.length()>0) {
				this.setArrivalDate(site.arrivalDate);
//				removeFocus();
			} else {
				site.arrivalDate = this.getArrivalDate();
			}
			if (site.departDate!=null && site.departDate.length()>0) {
				this.setDepartureDate(site.departDate);	
//				removeFocus();
			}else if (site.dayNightNum!=null && site.dayNightNum.length()>0) {
				site.departDate = DateFunctions.formatDate(site.arrivalDate,"M/d/yyyy");
				site.departDate = DateFunctions.getDateAfterGivenDay(site.departDate, Integer.parseInt(site.dayNightNum));
				setNights(site.dayNightNum);
				String departDate=this.getDepartureDate();
				if(!DateFunctions.isValidDate(departDate) || DateFunctions.compareDates(site.departDate, departDate)!=0) {
					logger.warn("Departure date "+departDate+" is not expected "+site.departDate);
					this.setDepartureDate(site.departDate);
//					removeFocus();
				}
			} else {
				site.dayNightNum = this.getNights();
			}

		}
		if(site.parkName!=null&&site.parkName.length()>0) {
			this.setParkName(site.parkName);
		} else {
			site.parkName = this.getParkName(); //get the default value
		}
		
		if (null != site.typeOfUse && site.typeOfUse.length()>0)
			this.selectTypeOfUse(site.typeOfUse); // formerly "Day"
		else
			site.typeOfUse = this.getTypeOfUse();

		if (null != site.siteNumber && site.siteNumber.length()>0)
			this.setSiteNumber(site.siteNumber);

		if (null != site.parkType && site.parkType.length()>0)
		  	this.selectLookingFor(site.parkType);
		
		if (null != site.siteType && site.siteType.length()>0)
			this.selectSiteType(site.siteType);

		if (null != site.areaName && site.areaName.length()>0)
			this.setAreaLoop(site.areaName);

		if (null != site.validCampingUnit && site.validCampingUnit.length()>0)
			this.selectValidCampingUnit(site.validCampingUnit);

		if (null != site.electricityHookup&& site.electricityHookup.length()>0)
			this.selectElectricityHookup(site.electricityHookup);

		if (null != site.baseNumPeople && site.baseNumPeople.length()>0)
			this.setNumberOfOccupants(site.baseNumPeople);

		if (null != site.vehicleLenth && site.vehicleLenth.length()>0)
			this.setVechicleLength(site.vehicleLenth);

		if(this.getReservationOnlyNum()==1){
			if (!site.reservable){
				unSelectReservableOnly();
			} else {
				selectReservableOnly();
			}
		}
	}

	/**
	 * Reset the search criteria to the default
	 */
	public void resetSearchCriteria() {
		this.setParkName("");
		this.selectTypeOfUse(""); // formerly "Day"
		this.setSiteNumber("");
		this.selectSiteType("");
		this.setAreaLoop("");
		this.selectValidCampingUnit("");
		if (this.isAdvancedSearch()) { //advanced site search
			this.setBeginRange("");
			this.setEndRange("");
			this.setRangeNights("");
		} else { //basic site search
			this.setArrivalDate("");
			this.setDepartureDate(StringUtil.EMPTY);
			this.setNights("");
		}
	}

	/**Check advanced button exist or not*/
	public boolean isAdvancedSearch() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"<< Basic");
	}
	
	/**
	 * Get alert message in the site search page
	 * @return
	 */
	public String getAlertMsgInSiteSearchPg(){
		IHtmlObject[] obj=browser.getHtmlObject(".id", "NOTSET");
		String alertMess=obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		
		return alertMess;
	}
	
	public void clickSlipSearchTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Slip Search", true);
	}
	
	public String getAutoCompleteParkName(){
		this.waitLoading();
		String text = browser.getObjectText(".id", new RegularExpression("Autocomplete_\\d+", false));
		return text;
	}
	
	public String getStateInTransferRes(){
		return browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
}
