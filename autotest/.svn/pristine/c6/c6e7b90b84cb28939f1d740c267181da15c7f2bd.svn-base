package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
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
 * @author nding
 * @Date  Apr 10, 2013
 */
public class OrmsMarinaDetailsPgae extends OrmsPage {

	private static OrmsMarinaDetailsPgae _instance = null;
	
	private OrmsMarinaDetailsPgae() {}
	
	public static OrmsMarinaDetailsPgae getInstance() {
		if(_instance == null) {
			_instance = new OrmsMarinaDetailsPgae();
		}
		
		return _instance;
	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", new RegularExpression("MarinaDetailSearchForm", false));//The search may not exist, eg:go to this page from reservation detail page
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Marina Name.*State.*Agency.*", false));
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Season Type.*Area/Dock.*Slip Type.*Dates*", false));
	}
	
	private String search_prefix = "SlipAvailabilitySearchCriteria-\\d+\\.";
	
	
	protected Property[] stateTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "state",false));
	}
	protected Property[] marinaNameTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "marinaName",false));
	}
	protected Property[] beginingTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "beginingDate_ForDisplay",false));
	}
	protected Property[] endingTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "endingDate_ForDisplay",false));
	}
	protected Property[] rangeNightsTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "rangeUnits",false));
	}
	
	protected Property[] arrivalTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "arrivalDate_ForDisplay", false));
	}
	protected Property[] departureTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "departureDate_ForDisplay", false));
	}
	protected Property[] nightsTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(search_prefix + "units", false));
	}
	
	protected Property[] preferralMarinaButton(){
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Referral Marinas", false));
	}
	
	//*******************For search section in the top of the page******************//
	public String getStateInSearchSection(){
		return browser.getDropdownListValue(stateTextField(), 0);
	}
	public String getMarinaNameInSearchSection(){
		return browser.getTextFieldValue(marinaNameTextField());
	}
	public String getArrivalDate(){
		return browser.getTextFieldValue(arrivalTextField());
	}
	public String getDepartureDate(){
		return browser.getTextFieldValue(departureTextField());
	}
	public String getNights(){
		return browser.getTextFieldValue(nightsTextField());
	}
	
	private boolean isObjectExist(Property[] properties){
		return browser.checkHtmlObjectExists(properties);
	}
	
	/**
	 * Click the lable of summary,so that the correspond information will show,
	 * the lables are "Description | Geography | Recreation | Facilities | Nearby Attactions | Key Amenities | Important Information | Directions"
	 * @param facilityId
	 * @param label
	 */
	public void clickLableInSummary(String facilityId, String label){
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".id", new RegularExpression("summary_" + facilityId + "_tab_\\d_title",false));
		properties[2] = new Property(".text", new RegularExpression("^"+ label +"$",false));
		browser.clickGuiObject(properties);
	}
	
	/**
	 * Get the content for summary, it is the displayed div content
	 * @param facilityId
	 * @return
	 */
	public String getSummaryInfoShown(String facilityId){
		Property[] properties = new Property[2];
		properties[0] = new Property(".class", "Html.DIV");
		properties[1] = new Property(".id", new RegularExpression("summary_" + facilityId + "_tab_\\d",false));
//		properties[2] = new Property(".style", StringUtil.EMPTY);
		IHtmlObject[] objs = browser.getHtmlObject(properties);
		int i=0;
		for(; i< objs.length; i++){
			String display = objs[i].getAttributeValue("style");
			if( StringUtil.isEmpty(display)){
				break;
			}
		}
		String content = objs[i].text();
		Browser.unregister(objs);
		return content;
	}
	
	public String getLableOfSumaryContentByClick(String facilityId, String label){
		this.clickLableInSummary(facilityId, label);
		String content = this.getSummaryInfoShown(facilityId);
		return content;
	}
	
	public FacilityData getSummaryInfoForMarina(String facilityId){
		FacilityData fd = new FacilityData();
		fd.brochureDescription = this.getLableOfSumaryContentByClick(facilityId, "Description");
		fd.brochureGeography = this.getLableOfSumaryContentByClick(facilityId, "Geography");
		fd.brochureRecreation = this.getLableOfSumaryContentByClick(facilityId, "Recreation");
		fd.brochureFacilities = this.getLableOfSumaryContentByClick(facilityId, "Facilities");
		fd.brochureNearbyAttractions = this.getLableOfSumaryContentByClick(facilityId, "Nearby Attactions");
		fd.importantInfo = this.getLableOfSumaryContentByClick(facilityId, "Important Information");
		fd.drivingDirection = this.getLableOfSumaryContentByClick(facilityId, "Directions");
		return fd;
	}
	
	public void veifySearchCriteriaUI(boolean isBasicSearch){
		boolean passed = true;
		//These only shown under advance search
		passed &= MiscFunctions.compareResult("Begining text field", !isBasicSearch, isObjectExist(this.beginingTextField()));
		passed &= MiscFunctions.compareResult("Ending text field", !isBasicSearch, isObjectExist(this.endingTextField()));
		passed &= MiscFunctions.compareResult("Range of Nights", !isBasicSearch, isObjectExist(this.rangeNightsTextField()));
		//These only shown under basic search
		passed &= MiscFunctions.compareResult("Arrival text field", isBasicSearch, isObjectExist(this.arrivalTextField()));
		passed &= MiscFunctions.compareResult("Departure text field", isBasicSearch, isObjectExist(this.departureTextField()));
		passed &= MiscFunctions.compareResult("Nights", isBasicSearch, isObjectExist(this.nightsTextField()));
		
		if(!passed) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} 
		logger.info("All checkpoints are PASSED for search criterial ui.");
	}
	
	protected Property[] slipTypeTable(){
		return Property.toPropertyArray(".id", new RegularExpression("grid_\\d+", false), ".text", new RegularExpression("Slip Type View Slips.*", false));
	}
	
	public IHtmlObject getSlipTypeTable(){
		IHtmlObject[] objs = browser.getTableTestObject(slipTypeTable());
		if(objs.length < 1){
			throw new ErrorOnPageException("There is no slip type table found in slips section.");
		}
		return objs[0];
	}
	
	public boolean isSlipSectionExist(){
		return browser.checkHtmlObjectExists(slipTypeTable());
	}
	
	/**
	 * The rules are: 
	 * 1.there are no Slip Types with at least 1 Slip, this section shall be blank.
	 * 2.there is more than one Slip Type listed, the System shall also include an "All Slips" option below the list of Slip Types
	 */
	public void verifySlipTypeTableUI(){
		boolean passed = true;
		ITable slipTypeTable = (ITable)this.getSlipTypeTable();
		int colNum = slipTypeTable.columnCount();
		if(colNum > 1){
			List<String> slipTypes = slipTypeTable.getColumnValues(0);
			slipTypes.remove(0);
			if(slipTypes.size() ==1 && slipTypes.get(0).equalsIgnoreCase("ALL SLIPS")){
				logger.info("The slip type is not correct, for if there is no slip type, it shall be set as blank");
				passed = false;
			}
			if(slipTypes.size() ==2 && slipTypes.contains("ALL SLIPS")){
				logger.info("The slip type is not correct, for if there is just one slip type, 'ALL SLIPS' should not shown.");
				passed = false;
			}
		}
		if(!passed){
			throw new ErrorOnPageException("There is no referral facilities for this marina, the button should not be shown.");
		}
		logger.info("Verify slip type ui correct!");
	}
	
	public IHtmlObject getMarinSectionTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("FormBar_\\d+", false) ,".text", new RegularExpression("^Marina.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Marina Name SPAN.");
		}
		return objs[0];
	}
	/**
	 * This 'Marina Name' is 'Marina Name' section which below search button.
	 * @return
	 */
	public String getMarinaName(){	
		IHtmlObject spans[] = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Marina Name.*", false), getMarinSectionTable());
		String marinaName = spans[0].text().trim();
		return marinaName.replaceAll("Marina Name", StringUtil.EMPTY).trim();
	}
	
	/**
	 * This 'State' is in 'Marina' section which below search button 
	 * @return
	 */
	public String getState(){
		IHtmlObject spans[] = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^State.*", false), getMarinSectionTable());
		String marinaName = spans[0].text().trim();
		return marinaName.replaceAll("State", StringUtil.EMPTY).trim();
	}
	
	public String getAgency(){
		return this.getAttributeValueByName("Agency");
	}
	
	public String getRegion(){
		return this.getAttributeValueByName("Region");
	}
	
	public String getCurrentSeason(){
		return this.getAttributeValueByName("Current Season");
	}
	
	protected Property[] descriptionLableForSeason(){
		return Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Description.*", false));
	}

	public boolean isDescriptionSpanForSeasonExist(){
		return browser.checkHtmlObjectExists(descriptionLableForSeason(), getMarinSectionTable());
	}

	public String getDescriptionForSeason(){
		IHtmlObject spans[] = browser.getHtmlObject(descriptionLableForSeason(), getMarinSectionTable());
		String marinaName = spans[0].text().trim();
		return marinaName.replaceAll("Description", StringUtil.EMPTY).trim();
	}
	
	private IHtmlObject getSeasonListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".className", "listView", ".text", new RegularExpression("Season Type.*" ,false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find table for season list.");
		}
		return objs[0];
	}
	
	public boolean isSearchSectionExist(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.DIV",".id", new RegularExpression("SearchBar_\\d+" ,false)
				, ".text",  new RegularExpression("^State.*Marina Name.*Arrival.*" ,false)));
	}
	
	public List<String> getInfoForSeason(String dock){
		ITable seasonTable = (ITable)this.getSeasonListTable();
		int row = seasonTable.findRow(1, dock);
		return seasonTable.getRowValues(row);
	}
	
	public String getCheckIn(){
		return this.getAttributeValueByName("Check In");
	}
	public String getCheckOut(){
		return this.getAttributeValueByName("Check Out");
	}
	public String getRadioChannel(){
		return this.getAttributeValueByName("Radio Channel");
	}
	public String getAddress(){
		return this.getAttributeValueByName("Address");
	}
	public String getPublicLine(){
		return this.getAttributeValueByName("Public Line");
	}
	
	
	
	public void clickMarinaResultsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Marina Results", false));
	}
	
	public void clickMap(){
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Map", false));
	}
	
	public void clickSearch(){
		IHtmlObject top[] = browser.getHtmlObject(".class", "Html.DIV",".id", new RegularExpression("SearchBar_\\d+", false));
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Search|Go", false), true, 0, top[0]);
	}
	
	/**
	 * Click slip type link
	 * @param slipType -- Full attributes or Slip Group and so on
	 */
	public void clickSlipTypeLink(String slipType){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(slipType, false), true);
	}
	
	public void clickAllSlips(){
		clickSlipTypeLink("All Slips");
	}

	public void clickReferralMarinas(){
		browser.clickGuiObject(preferralMarinaButton(), true, 0);
	}
	
	public boolean isReferralMarinaButtonExist(){
		return browser.checkHtmlObjectExists(preferralMarinaButton());
	}
	
	/**
	 * Click slip type link after each slip type
	 * @param slipType -- Full attributes or Slip Group and so on
	 */
	public void clickReservable(String slipType){
		// get TR which starts with the given slip type
		IHtmlObject top[] = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression("^"+slipType, false));
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Reservable", false), true, 0, top[0]);
	}
	
	//*******************For transfer******************//
	/**
	 * verify the transfer from slip reservation details info
	 * @param resNum
	 * @param transferFromSlip
	 */
	public void verifyTransferFromSlipInfo(String resNum, SlipInfo transferFromSlip) {
		logger.info("Verify 'Transfer From Slip' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Transfer from Slip Reservation #", resNum, getSlipReservationNum());
		result &= MiscFunctions.compareResult("Transfer from Marina", transferFromSlip.getMarina(), getTransferFromMarina());
		result &= MiscFunctions.compareResult("Transfer from Dock/Area", transferFromSlip.getParentDockArea(), getTransferFromDockArea());
		result &= MiscFunctions.compareResult("Transfer from Slip # - Name", transferFromSlip.getCode() + "-" + transferFromSlip.getName(), getTransferFromSlipNumName().replaceAll(" ", StringUtil.EMPTY));
		result &= MiscFunctions.compareResult("Transfer from Arrival Date", transferFromSlip.getArrivalDate(), getTransferFromArrivalDate());
		result &= MiscFunctions.compareResult("Transfer from Departure Date", transferFromSlip.getDepartureDate(), getTransferFromDepartureDate());
		if(transferFromSlip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Transfer from Nights", transferFromSlip.getNights(), Integer.parseInt(getTransferFromNights()));
		} else {
			result &= MiscFunctions.compareResult("Transfer from Months", transferFromSlip.getMonths(), Integer.parseInt(getTransferFromMonths()));
		}
		
		if(!result) {
			throw new ErrorOnPageException("'Transfer From Slip' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Transfer From Slip' section info is correct.");
	}

	private String getAttributeValueByName(String name) {
		String nameRegex;
		if(name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else {
			nameRegex = name;
		}
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression(nameRegex, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - " + name);
		}
		
		String text = objs[0].text().replace(name, StringUtil.EMPTY).trim();
		Browser.unregister(objs);
		
		return text;		
	}
	
	public String getSlipReservationNum() {
		return getAttributeValueByName("Slip Reservation #");
	}
	
	public String getTransferFromMarina() {
		return getAttributeValueByName("Marina");
	}
	
	public String getTransferFromDockArea() {
		return getAttributeValueByName("Dock/Area");
	}
	
	public String getTransferFromSlipNumName() {
		return getAttributeValueByName("Slip # (Name)");
	}
	
	public String getTransferFromArrivalDate() {
		return getAttributeValueByName("Arrival");
	}
	
	public String getTransferFromDepartureDate() {
		return getAttributeValueByName("Departure");
	}
	
	public String getTransferFromNights() {
		return getAttributeValueByName("Nights");
	}
	
	public String getTransferFromMonths() {
		return getAttributeValueByName("Months");
	}
	//*******************For transfer******************//

	public String getMessage() {
		Property[] p=Property.toPropertyArray(".class","Html.DIV",".id","NOTSET");
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String msg = objs[0].text();
		Browser.unregister(objs);
		return msg;
	}
	
	//*******************For Note and alert**********************************//
	public boolean isNotesSectionExists() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("Notes", false), ".name", "e_Glow"), Property.toPropertyArray(".class", "Html.TEXTAREA")));
	}

	public String getNotesValue() {
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("Notes", false), ".name", "e_Glow"), Property.toPropertyArray(".class", "Html.TEXTAREA")));
	}
	
	public String getNotesInfo(){
		return this.getAttributeValueByName("Note:");
	}
	
	public boolean isAlertsSectionExists() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("Alerts", false), ".name", "e_Glow"), Property.toPropertyArray(".class", "Html.TEXTAREA")));
	}
	
	public String getAlertValue() {
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("Alerts", false), ".name", "e_Glow"), Property.toPropertyArray(".class", "Html.TEXTAREA")));
	}
	
	private boolean compareNoteAlertInfo(InventoryInfo info) {
		String value = "";
		if(info.alertType.equalsIgnoreCase("Note")) {
			value = getNotesValue();
		} else {
			value = getAlertValue();
		}
		String values[] = value.split("\\|");
		String dates[] = values[0].split("-");
		boolean result = true;
		result &= MiscFunctions.compareResult(info.alertType + " Start Date", info.alertStartDate, dates[0].trim());
		result &= MiscFunctions.compareResult(info.alertType + " End Date", info.alertEndDate, dates[1].trim());
		result &= MiscFunctions.compareResult(info.alertType + " Description", info.description, values[1].trim());
		
		return result;
	}
	
	public boolean compareSlipNotesInfo(InventoryInfo noteInfo) {
		return compareNoteAlertInfo(noteInfo);
	}
	
	public boolean compareSlipAlertInfo(InventoryInfo alertInfo) {
		return compareNoteAlertInfo(alertInfo);
	}
	//***********************************For note and alert***********************************//
}
