package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.NSSSlipInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
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
 * @author qchen
 * @Date  Oct 22, 2012
 */
public class OrmsSlipDetailsPage extends OrmsPage {

	private static OrmsSlipDetailsPage _instance = null;
	
	private OrmsSlipDetailsPage() {}
	
	public static OrmsSlipDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipDetailsPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", "SlipDetailsUITag");  //it is not suitable for it'll will passed even the UI has not been fully loaded 
		return browser.checkHtmlObjectExists(".id", "SlipDetailActionBar");
	}
	
	public void clickMap() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Map", true);
	}
	
	public void clickViewReservations() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Reservations", true);
	}
	
	public String getSlipNum() {
		return getAttributeValueByName("Slip #");
	}
	
	public String getSlipType() {
		return getAttributeValueByName("Slip Type");
	}
	
	public String getAreaDock() {
		return getAttributeValueByName("Area/Dock");
	}
	
	public String getFacility() {
		IHtmlObject divObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^Facility.*", false)));
		if(divObjs.length < 1) {
			throw new ItemNotFoundException("Can't find div objs with text begin with Facility");
		}
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", divObjs[0]);
		if(objs==null||objs.length<1){
			return getAttributeValueByName("Facility");
		}
		String text = objs[0].text();
		
		Browser.unregister(objs);
		Browser.unregister(divObjs);
		return text;
//		return getAttributeValueByName("Facility");
	}
	
	public String getState() {
		return getAttributeValueByName("State");
	}
	
	public boolean compareSlipLocationInfo(SlipInfo slip) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Slip #", slip.getCode() + " - " + slip.getName(), getSlipNum());
		result &= MiscFunctions.compareResult("Slip Type", slip.getType(), getSlipType());
		result &= MiscFunctions.compareResult("Area/Dock", slip.getParentDockArea(), getAreaDock());
		result &= MiscFunctions.compareResult("Facility", slip.getMarina(), getFacility());
		result &= MiscFunctions.compareResult("State", slip.getStateShortName(), getState());
		
		return result;
	}
	
	public String getSlipRelationType() {
		return getAttributeValueByName("Slip Relation Type");
	}
	
	public int getSlipLength() {
		return Integer.parseInt(getAttributeValueByName("Slip Length (ft)"));
	}
	
	public int getMinBoatLength() {
		return Integer.parseInt(getAttributeValueByName("Min Boat Length (ft)"));
	}
	
	public int getMaxBoatLength() {
		return Integer.parseInt(getAttributeValueByName("Max Boat Length (ft)"));
	}
	
	public int getSlipWidth() {
		return Integer.parseInt(getAttributeValueByName("Slip Width (ft)"));
	}
	
	public int getSlipDepth() {
		return Integer.parseInt(getAttributeValueByName("Slip Depth (ft)"));
	}
	
	public int getBoatSpacing() {
		return Integer.parseInt(getAttributeValueByName("Boat Spacing"));
	}
	
	public int getElectricityHookup() {
		return Integer.parseInt(getAttributeValueByName("Electricity Hookup"));
	}
	
	public int getFullHookup() {
		return Integer.parseInt(getAttributeValueByName("Full Hookup"));
	}
	
	public boolean getSewerHookup() {
		return getAttributeValueByName("Sewer Hookup").equals(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public boolean getWaterHookup() {
		return getAttributeValueByName("Water Hookup").equals(OrmsConstants.YES_STATUS) ? true: false;
	}
	
	public boolean compareSlipPhysicalInfo(SlipInfo slip) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Slip Relation Type", slip.getRelationType(), getSlipRelationType());
		result &= MiscFunctions.compareResult("Slip Length (ft)", slip.getLength(), getSlipLength());
		result &= MiscFunctions.compareResult("Min Boat Length (ft)", slip.getMinBoatLength(), getMinBoatLength());
		result &= MiscFunctions.compareResult("Max Boat Length (ft)", slip.getMaxBoatLength(), getMaxBoatLength());
		result &= MiscFunctions.compareResult("Slip Width (ft)", slip.getWidth(), getSlipWidth());
		result &= MiscFunctions.compareResult("Slip Depth (ft)", slip.getDepth(), getSlipDepth());
		result &= MiscFunctions.compareResult("Boat Spacing", slip.getBoatSpacing(), getBoatSpacing());
		result &= MiscFunctions.compareResult("Electricity Hookup", slip.getElectrictity(), getElectricityHookup());
		result &= MiscFunctions.compareResult("Full Hookup", slip.getFull(), getFullHookup());
		result &= MiscFunctions.compareResult("Sewer Hookup", slip.isSewerHookup(), getSewerHookup());
		result &= MiscFunctions.compareResult("Water Hookup", slip.isWaterHookup(), getWaterHookup());
		
		return result;
	}
	
	public String getChannelLatitudeDirection() {
		return getAttributeValueByName("ChannelLatitudeDir");
	}
	
	public int getChannelLatitudeDegree() {
		return Integer.parseInt(getAttributeValueByName("ChannelLatitudeDeg"));
	}
	
	public int getChannelLatitudeMinute() {
		return Integer.parseInt(getAttributeValueByName("ChannelLatitudeMin"));
	}
	
	public int getChannelLatitudeSecond() {
		return Integer.parseInt(getAttributeValueByName("ChannelLatitudeSec"));
	}
	
	public String getChannelLongitudeDirection() {
		return getAttributeValueByName("ChannelLongitudeDir");
	}
	
	public int getChannelLongitudeDegree() {
		return Integer.parseInt(getAttributeValueByName("ChannelLongitudeDeg"));
	}
	
	public int getChannelLongitudeMinute() {
		return Integer.parseInt(getAttributeValueByName("ChannelLongitudeMin"));
	}
	
	public int getChannelLongitudeSecond() {
		return Integer.parseInt(getAttributeValueByName("ChannelLongitudeSec"));
	}
	
	public String getCheckinTime() {
		return getAttributeValueByName("Checkin Time");
	}
	
	public String getCheckoutTime() {
		return getAttributeValueByName("Checkout Time");
	}
	
	public boolean compareSlipInfoInfo(SlipInfo slip) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Channel Latitude Direction", slip.getLatitudeChannelDirection(), getChannelLatitudeDirection());
		result &= MiscFunctions.compareResult("Channel Latitude Degree", slip.getLatitudeDegree(), getChannelLatitudeDegree());
		result &= MiscFunctions.compareResult("Channel Latitude Minute", slip.getLatitudeMinute(), getChannelLatitudeMinute());
		result &= MiscFunctions.compareResult("Channel Latitude Second", slip.getLatitudeSecond(), getChannelLatitudeSecond());
		result &= MiscFunctions.compareResult("Channel Longitude Direction", slip.getLongitudeChannelDirection(), getChannelLongitudeDirection());
		result &= MiscFunctions.compareResult("Channel Longitude Degree", slip.getLongitudeDegree(), getChannelLongitudeDegree());
		result &= MiscFunctions.compareResult("Channel Longitude Minute", slip.getLongitudeMinute(), getChannelLongitudeMinute());
		result &= MiscFunctions.compareResult("Channel Longitude Second", slip.getLongitudeSecond(), getChannelLongitudeSecond());
		result &= MiscFunctions.compareResult("Checkin Time", slip.getCheckInTime(), getCheckinTime());
		result &= MiscFunctions.compareResult("Checkout Time", slip.getCheckOutTime(), getCheckoutTime());
		
		return result;
	}
	
	public boolean getADAAccessible() {
		return getAttributeValueByName("ADA Accessible").equals(OrmsConstants.YES_STATUS);
	}
	
	public int getBasePricingLength() {
		return Integer.parseInt(getAttributeValueByName("Base Pricing Length"));
	}
	
	public int getMaximumNumberOfPeople() {
		return Integer.parseInt(getAttributeValueByName("Maximum Number of People"));
	}
	
	public int getMaximumNumberOfVehicles() {
		return Integer.parseInt(getAttributeValueByName("Maximum Number of Vehicles"));
	}
	
	public String getTypeOfUse() {
		return getAttributeValueByName("Type of Use");
	}
	
	public boolean compareSlipRestrictionInfo(SlipInfo slip) {
		boolean result = true;
		result &= MiscFunctions.compareResult("ADA Accessible", slip.isAccessibleSite(), getADAAccessible());
		result &= MiscFunctions.compareResult("Base Pricing Length", slip.getBasePricingLength(), getBasePricingLength());
		result &= MiscFunctions.compareResult("Type of Use", slip.getTypeOfUse(), getTypeOfUse());
		result &= MiscFunctions.compareResult("Maximum Number Of People", slip.getMaxNumOfPeople(), getMaximumNumberOfPeople());
		result &= MiscFunctions.compareResult("Maximum Number of Vehicle", slip.getMaxNumOfVehicle(), getMaximumNumberOfVehicles());
		
		return result;
	}
	
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
	
	public double getNightlyRate() {
		return Double.parseDouble(getAttributeValueByName("Nightly"));
	}
	
	public double getDailyRate() {
		return Double.parseDouble(getAttributeValueByName("Daily"));
	}
	
	public double getWeeklyRate() {
		return Double.parseDouble(getAttributeValueByName("Weekly"));
	}
	
	public double getMonthlyRate() {
		return Double.parseDouble(getAttributeValueByName("Monthly"));
	}
	
	/**
	 * get assumed boat length from: Note:Estimated rate assumes a boat length of 99 feet.
	 * @return
	 */
	public int getAssumedBoatLength() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetailBlue", ".text", new RegularExpression("Note:Estimated rate assumes a boat length of \\d+ feet.", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Assumed Boat Length object.");
		}
		String text = objs[0].text();
		String feet = RegularExpression.getMatches(text, "\\d+")[0].trim();
		Browser.unregister(objs);
		
		return Integer.parseInt(feet);
	}
	
	public boolean compareSlipDetailsInfo(SlipInfo slip) {
		return compareSlipDetailsInfo(slip, (InventoryInfo)null);
	}
	
	public boolean compareSlipDetailsInfo(SlipInfo slip, InventoryInfo... notesAlertsInfo) {
		boolean result = true;
		result &= compareSlipLocationInfo(slip);
		result &= compareSlipPhysicalInfo(slip);
		result &= compareSlipInfoInfo(slip);
		result &= compareSlipRestrictionInfo(slip);

		if(null != notesAlertsInfo && notesAlertsInfo.length > 0) {
			for(InventoryInfo info : notesAlertsInfo) {
				if(info.alertType.equalsIgnoreCase("Note")) {
					result &= compareSlipNotesInfo(info);
				} else if(info.alertType.equalsIgnoreCase("Alert")) {
					result &= compareSlipAlertInfo(info);
				}
			}
		}
		
		return result;
	}
	
	public boolean compareTransientRate(String label, double rate, int assumeBoatLength) {
		return compareTransientRates(new String[]{label}, new double[] {rate}, assumeBoatLength);
	}
	
	public boolean compareTransientRates(String labels[], double rates[], int assumeBoatLength) {
		if(labels.length != rates.length) {
			throw new ErrorOnPageException("Label length doesn't macth rates length.");
		}
		double actualRate = 0;
		boolean result = true;
		for(int i = 0; i < labels.length; i ++) {
			if(labels[i].equalsIgnoreCase("Nightly")) {
				actualRate = getNightlyRate(); 
			} else if(labels[i].equalsIgnoreCase("Daily")) {
				actualRate = getDailyRate();
			} else if(labels[i].equalsIgnoreCase("Weekly")) {
				actualRate = getWeeklyRate();
			} else if(labels[i].equalsIgnoreCase("Monthly")) {
				actualRate = getMonthlyRate();
			} else throw new ErrorOnPageException("Unknown rate type - " + labels[i]);
			
			//compare every rate
			result &= MiscFunctions.compareResult(labels[i] + " Rate", rates[i], actualRate);
		}
		
		//compare assumes boat length
		result &= MiscFunctions.compareResult("Assumes fee", assumeBoatLength, getAssumedBoatLength());
		
		return result;
	}
	
	public String getLeaseFromDate() {
		return getAttributeValueByName("From");
	}
	
	public String getLeaseToDate() {
		return getAttributeValueByName("To");
	}
	
	public double getLeaseOrSeasonalRate() {
		return Double.parseDouble(getAttributeValueByName("Rate:"));
	}
	
	/**
	 * compare Rate section info except Notes during LEASE slip reservation process
	 * @param fromDate
	 * @param toDate
	 * @param rate
	 * @param assumesBoatLength
	 * @return
	 */
	public boolean compareLeaseRate(String fromDate, String toDate, double rate, int assumesBoatLength) {
		boolean result = true;
		result &= MiscFunctions.compareResult("From Date", fromDate, getLeaseFromDate());
		result &= MiscFunctions.compareResult("To Date", toDate, getLeaseToDate());
		result &= MiscFunctions.compareResult("Lease Rate", rate, getLeaseOrSeasonalRate());
		result &= MiscFunctions.compareResult("Assumes Boat Length", assumesBoatLength, getAssumedBoatLength());
		
		return result;
	}
	
	public String getSeason() {
		return getAttributeValueByName("Season");
	}
	
	public String getSeasonDropdownListValue() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonId|DropdownExt-\\d+\\.selectedValue", false));
	}
	
	/**
	 * compare the Rates section info expected Notes during SEASONAL slip reservation process
	 * @param season
	 * @param rate
	 * @param assumesBoatLength
	 * @return
	 */
	public boolean compareSeasonalRate(String season, double rate, int assumesBoatLength) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Season", season, getSeason());
		result &= MiscFunctions.compareResult("Seasonal Rate", rate, getLeaseOrSeasonalRate());
		result &= MiscFunctions.compareResult("Assumes Boat Length", assumesBoatLength, getAssumedBoatLength());
		
		return result;
	}
	
	/**
	 * get all Notes info in Rates section
	 * Note:Estimated rate assumes a boat length of 20 feet. 
	 *	Note:Rate displayed here may vary from the final presented rate based on such
	 * things as the customer's state of residence and overall length of stay. Rates do not
	 *  include potential discounts and taxes. Special rates for non-standard length of stay
	 *  time (e.g. 5 days or 3 months) are also not reflected in these rates. 
	 * 
	 * @return
	 */
	public String[] getRatesNotes() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetailBlue", ".text", new RegularExpression("^Note:", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find any Rates-Note object.");
		}
		
		List<String> notesList = new ArrayList<String>();
		for(IHtmlObject obj : objs) {
			notesList.add(obj.text());
		}
		Browser.unregister(objs);
		
		String notes[] = notesList.toArray(new String[0]);
		return notes;
	}
	
	public boolean compareSlipRatesNotesInfo(String expectedNotes[]) {
		String actualNotes[] = getRatesNotes();
		if(!MiscFunctions.compareResult("Slip Rates Notes length", expectedNotes.length, actualNotes.length)) {
			//throw new ErrorOnPageException("Slip Rate Notes length is incorrect, please refer to log for details.");
			return false;
		}
		
		boolean result = true;
		for(int i = 0; i < expectedNotes.length; i ++) {
			result &= MiscFunctions.compareResult("Slip Rates Note " + (i + 1), expectedNotes[i], actualNotes[i]);
		}
		
		return result;
	}
	
	public boolean compareAvailabilityFromDate(String expectedArrivalDate) {
		String actualFromDate = getFromDate();
		String actualGridFirstDate = getAvailabilityGridFirstDate();
		boolean result = true;
		result &= MiscFunctions.compareResult("[Expected Arrival Date] compares to [From Date]", expectedArrivalDate, actualFromDate);
		result &= MiscFunctions.compareResult("[From Date] compares to [Availability Grid 1st Date]", actualFromDate, actualGridFirstDate);
		
		return result;
	}
	
	public boolean compareAvailabilityGridFirstDate(String expectedArrivalDate) {
		String actualGridFirstDate = getAvailabilityGridFirstDate();
		boolean result = true;
		result &= MiscFunctions.compareResult("[Expected Arrival Date] compares to [Availability Grid 1st Date]", expectedArrivalDate, actualGridFirstDate);
		
		return result;
	}
	
	public boolean compareSlipReservationDates(String arrival, String departure, int nightsOrMonths) {
		String actualArrival = getArrivalDate();
		String actualDeparture = getDepartureDate();
		int actualLengthOfStay = 0;
		
		boolean result = true;
		
		if(isDayTypeOfUse()) {//TRANSIENT-Day
			actualLengthOfStay = getDays();
		} else if(isDepartureDateTextFieldExists()) {//TRANSIENT-Overnight
			actualLengthOfStay = getNights();
		} else if(isDepartureDateAttributeExisting()) {//LEASE
			actualLengthOfStay = getMonths();
		}
//		else {//SEASONAL
//			actualLengthOfStay = DateFunctions.diffBetween(actualArrival, actualDeparture);
//		}
		
		result &= MiscFunctions.compareResult("Arrival Date", arrival, actualArrival);
		result &= MiscFunctions.compareResult("Departure Date", departure, actualDeparture);
		result &= MiscFunctions.compareResult("Nights/Months", nightsOrMonths, actualLengthOfStay);
		
		return result;
	}
	
	/**
	 * compare SEASONAL slip reservation dates section in slip details page
	 * @param season
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean compareSlipReservationDates(String season, String start, String end) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Season", season, getSeasonDropdownListValue());
		result &= MiscFunctions.compareResult("Season Start", start, getSeasonStartDate());
		result &= MiscFunctions.compareResult("Season End", end, getSeasonEndDate());
		
		return result;
	}
	
	public void updateFromDate(String updateDate) {
		setFromDate(updateDate);
		clickUpdateDisplay();
		ajax.waitLoading();
	}
	
	public void updateArrivalDate(String arrival) {
		setArrivalDate(arrival);
		ajax.waitLoading();
	}
	
	public boolean isDepartureDateAttributeExisting(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^Departure Date", false)));
	}
	
	private String getAttributeValueByName(String name) {
		String nameRegex;
		if(name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else if(name.matches("State|To|Season")) {
			nameRegex = "^(\\W*)?" + name;
		} else {
			nameRegex = name;
		}
		
		Property[] p1=Property.toPropertyArray(".class", "Html.SPAN", ".className", new RegularExpression("labelwithrubylabel|inputwithrubylabel|orderItemDetailBlue",false), ".text", new RegularExpression(nameRegex, false));
//		Property[] p2=Property.toPropertyArray(".class", "Html.SPAN");
		String spanValue = browser.getObjectText(p1);
		String value = spanValue.substring(name.length()).trim();
		return value;
	}
	
	private boolean isAttributeIsExisting(String name){
		String nameRegex;
		if(name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else if(name.matches("State|To|Season")) {
			nameRegex = "^(\\W*)?" + name;
		} else {
			nameRegex = name;
		}
		
		Property[] p1=Property.toPropertyArray(".class", "Html.SPAN", ".className", new RegularExpression("labelwithrubylabel|inputwithrubylabel",false), ".text", new RegularExpression(nameRegex, false));
		boolean isExisting = browser.checkHtmlObjectExists(p1);
		return isExisting;
	}
	
	public boolean isMonthlyRateIsExisting(){
		return this.isAttributeIsExisting("Monthly");
	}
	
	public boolean isWeeklyRateIsExisting(){
		return this.isAttributeIsExisting("Weekly");
	}
	
	public boolean isNightlyRateIsExisting(){
		return this.isAttributeIsExisting("Nightly");
	}
	
	public String getSlipReservationNum() {
		return getAttributeValueByName("Slip Reservation #");
	}
	
	public String getChangeFromMarina() {
		return getAttributeValueByName("Marina");
	}
	
	public String getChangeFromDockArea() {
		return getAttributeValueByName("Dock/Area");
	}
	
	public String getChangeFromSlipGroupNumName() {
		return getAttributeValueByName("Slip Group #");
	}
	
	public String getChangeFromSlipNumName() {
		return getAttributeValueByName("Slip # (Name)");
	}
	
	public String getChangeFromArrivalDate() {
		return getAttributeValueByName("Arrival");
	}
	
	public String getChangeFromDepartureDate() {
		return getAttributeValueByName("Departure");
	}
	
	public String getChangeFromNights() {
		return getAttributeValueByName("Nights");
	}
	
	public String getChangeFromMonths() {
		return getAttributeValueByName("Months");
	}
	
	/**
	 * verify the Change Reservation Dates section details info
	 * @param resNum
	 * @param changeFromSlip
	 */
	public void verifyChangeReservationDatesSlipInfo(String resNum, SlipInfo changeFromSlip) {
		logger.info("Verify 'Change Reservation Dates' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Change Reservation Dates - Slip Reservation #", resNum, getSlipReservationNum());
		result &= MiscFunctions.compareResult("Change Reservation Dates - Marina", changeFromSlip.getMarina(), getChangeFromMarina());
		result &= MiscFunctions.compareResult("Change Reservation Dates - Dock/Area", changeFromSlip.getParentDockArea(), getChangeFromDockArea());
		String actualChanegFromSlipNumName = StringUtil.EMPTY;
		if(changeFromSlip instanceof NSSSlipInfo) {
			actualChanegFromSlipNumName = getChangeFromSlipGroupNumName();
		} else {
			actualChanegFromSlipNumName = getChangeFromSlipNumName();
		}
		result &= MiscFunctions.compareResult("Change Reservation Dates - Slip # - Name", changeFromSlip.getCode() + "-" + changeFromSlip.getName(), actualChanegFromSlipNumName);
		result &= MiscFunctions.compareResult("Change Reservation Dates - Arrival Date", changeFromSlip.getArrivalDate(), getChangeFromArrivalDate());
		result &= MiscFunctions.compareResult("Change Reservation Dates - Departure Date", changeFromSlip.getDepartureDate(), getChangeFromDepartureDate());
		if(changeFromSlip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Change Reservation Dates - Nights", changeFromSlip.getNights(), Integer.parseInt(getChangeFromNights()));
		} else {
			result &= MiscFunctions.compareResult("Change Reservation Dates - Months", changeFromSlip.getMonths(), Integer.parseInt(getChangeFromMonths()));
		}
		
		if(!result) {
			throw new ErrorOnPageException("'Change Reservation Dates' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Change Reservation Dates' section info is correct.");
	}
	
	public void setFromDate(String date) {
		browser.setCalendarField(".id", new RegularExpression("FromDateBean-\\d+\\.fromDate_ForDisplay", false), date);
	}
	
	public String getFromDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("FromDateBean-\\d+\\.fromDate_ForDisplay", false));
	}
	
	public boolean isUpdateDisplayExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Update Display");
	}
	
	public void clickUpdateDisplay() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Update Display", true);
	}
	
	public boolean isArrivalDateTextFieldExists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.arrivalDate_ForDisplay", false));
	}
	
	public void setArrivalDate(String arrival) {
		browser.setCalendarField(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.arrivalDate_ForDisplay", false), arrival);
	}
	
	public String getArrivalDateTextFieldValue() {
		return browser.getTextFieldValue(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.arrivalDate_ForDisplay", false));
	}
	
	public boolean isDepartureDateTextFieldExists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.departureDate_ForDisplay", false));
	}
	
	public void setDepartureDate(String departure) {
		browser.setCalendarField(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.departureDate_ForDisplay", false), departure);
	}
	
	private String getDepartureDateTextFieldValue() {
		return browser.getTextFieldValue(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.departureDate_ForDisplay", false));
	}
	
	private String getUnitsLabelName() {
		return browser.getObjectText(".class", "Html.LABEL", ".for", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false));
	}
	
	public boolean isNightsExists() {
		String label = getUnitsLabelName();
		return !StringUtil.isEmpty(label) && label.equalsIgnoreCase("Nights");
	}
	
	public void setNights(int nights) {
		browser.setTextField(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false), String.valueOf(nights),0,IText.Event.LOSEFOCUS);
	}
	
	public int getNights() {
		return Integer.parseInt(browser.getTextFieldValue(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false)));
	}
	
	public int getDays() {
		return Integer.parseInt(browser.getObjectText(".class", "Html.SPAN", ".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false)).replace("Days", StringUtil.EMPTY));
	}
	
	/**
	 * 
	 * @return --- 'Nights' or 'Days'
	 */
	public String getLengthOfStayLabel() {
		String regex = "SlipAvailabilityResult-\\d+\\.units";
		Property nightsProperties[] = Property.toPropertyArray(".class", "Html.LABEL", ".for", new RegularExpression(regex, false));
		Property daysProperties[] = Property.toPropertyArray(".class", "Html.SPAN", ".id", new RegularExpression(regex, false));
		
		if(browser.checkHtmlObjectExists(nightsProperties)) {
			return browser.getObjectText(nightsProperties);
		} else if(browser.checkHtmlObjectExists(daysProperties)) {//TRANSIENT-Day
			return browser.getObjectText(daysProperties).replace("1", StringUtil.EMPTY).trim();
		}
		return null;
	}
	
	public boolean isDayTypeOfUse() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false));
	}
	
	public int getMonths() {
		return Integer.parseInt(browser.getDropdownListValue(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false)));
	}
	
	public boolean isArrivalDateComponentValid(String invalidDates[]) {
		Property[] properties = new Property[1];
		properties[0] = new Property(".id", 
				new RegularExpression("SlipAvailabilityResult-\\d+\\.arrivalDate_ForDisplay", false));
		return verifyAutomaticDateCorrection(properties, invalidDates);
	}
	
	public boolean isDepartureDateComponentValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.departureDate_ForDisplay", false))[0], invalidDates);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	//This is for call manager when rule breaked
	public void clickOverrideRule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Override Rule(s)", true);
	}
	
	/**
	 * update Slip Reservation Dates - Arrival Date, Departure Date and will automatically fill Nights based on them
	 * @param arrivalDate
	 * @param departureDate
	 * @return
	 */
	public int updateDates(String arrivalDate, String departureDate) {
		updateDates(arrivalDate, departureDate, -1);
		return getNights();
	}
	
	/**
	 * update Slip Reservation Dates - Arrival Date, Nights and will automatically fill Departure Date based on them
	 * @param arrivalDate
	 * @param nights
	 * @return
	 */
	public String updateDatesForTransient(String arrivalDate, int nights) {
		updateDates(arrivalDate, null, nights);
		return getDepartureDateTextFieldValue();
	}
	
	public void updateDates(String arrivalDate, String departureDate, int nights) {
		if(StringUtil.isEmpty(arrivalDate)) {
			arrivalDate = DateFunctions.getDateAfterGivenDay(departureDate, -nights);
		}
		if(!StringUtil.isEmpty(arrivalDate)) {
			setFromDate(arrivalDate);
			if(isUpdateDisplayExists()) {
				clickUpdateDisplay();
			}
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(arrivalDate)) {
			setArrivalDate(arrivalDate);	
			ajax.waitLoading();
			waitLoading();
			browser.clickGuiObject(".class", "Html.TD", ".text", "Rates");
		}
		if(!StringUtil.isEmpty(departureDate)) {
			setDepartureDate(departureDate);
			ajax.waitLoading();
			waitLoading();
		}
		if(NumberUtil.isGreaterThanZero(nights)) {
			setNights(nights);
//			ajax.waitLoading();
//			waitExists();
		}
	}
	
	public void updateDates(String arrivalDate, int months) {
		if(!StringUtil.isEmpty(arrivalDate)) {
			setArrivalDate(arrivalDate);
//			moveFocusOutOfDateComponent();
			ajax.waitLoading();
		}
		if(NumberUtil.isGreaterThanZero(months)) {
			selectMonths(months);
			ajax.waitLoading();
		}
	}
	
	public boolean isMonthsExists() {
		String label = getUnitsLabelName();
		return !StringUtil.isEmpty(label) && label.equalsIgnoreCase("Months");
	}
	
	public void selectMonths(int months) {
		browser.selectDropdownList(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.units", false), String.valueOf(months));
	}
	
	public String getArrivalDate() {
		String text;
		if(isDayTypeOfUse()) {//TRANSIENT - Day
			text = getAttributeValueByName("Arrival Date");
		} else if(isDepartureDateTextFieldExists() || isDepartureDateAttributeExisting()) {//TRANSIENT - Nights, and LEASE
			text = getArrivalDateTextFieldValue();
		} else {//SEASONAL
			text = getSeasonStartDate();
		}
		
		return text;
	}
	
	public String getSeasonStartDate() {
		return browser.getObjectText(".class","Html.SPAN",".id","seasonStartDateInput").replaceAll("Season Start", "").trim();
	}
	
	public String getDepartureDate() {
		String text;
		if(isDepartureDateTextFieldExists()) {
			text = getDepartureDateTextFieldValue();
		} else if(isDepartureDateAttributeExisting()){
//			text =  getAttributeValueByName("Departure Date");
			text =  this.getDepartureDateForResDates();
		} else {
			text = getSeasonEndDate();
		}
		
		return text;
	}
	
	public String getSeasonEndDate() {
		return browser.getObjectText(".class","Html.SPAN",".id","seasonEndDateInput").replaceAll("Season End", "").trim();
	}
	

	public boolean isAvailabilityStatusEnable(String text) {
		IHtmlObject[] top = browser.getHtmlObject(".class", "Html.DIV", ".id", "SeasonalSlipResvDatesBar");
		
		//Shane[20131127], for waiting/transfer list, it display as a link sometime
		boolean enable = browser.checkHtmlObjectEnabled(Property.toPropertyArray(".class","Html.A",".text",text),top[0]);
		Browser.unregister(top);
		
		return enable;
	}
	
	public String getAvailability() {
		String text = browser.getObjectText(".class","Html.SPAN",".className",new RegularExpression("labelwithrubylabel marina_status.*",false));
		if(StringUtil.notEmpty(text)){
			text = text.replaceAll("Availability", "").trim();
		}else{
			text = getAttributeValueByName("Availability");
		}
		return text;
	}
	
	public String getAvailailityForResDates(){
		String text = browser.getObjectText(".class","Html.SPAN",".text",new RegularExpression("^Availability.*",false));
		text = text.replaceAll("Availability", "").trim();
		return text;
	}
	
	public String getDepartureDateForResDates(){
		String text = browser.getObjectText(".class","Html.SPAN",".text",new RegularExpression("^Departure Date.*",false));
		text = text.replaceAll("Departure Date", "").trim();
		return text;
	}
	
	public int getNightsForResDates(){
		String text = getAttributeValueByName("Nights");
		return Integer.valueOf(text);
	}
	
	public int getDaysForResDates(){
		String text = getAttributeValueByName("Days");
		return Integer.valueOf(text);
	}
	
	public String getArrivalDateForResDates(){
		return getAttributeValueByName("Arrival Date");
	}
	
	public String getNextAvailDate() {
		return getAttributeValueByName("Next Avail Date");
	}
	
	private IHtmlObject[] getAvailabilityGridDates() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TD", ".className", "clndr");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find nay availability grid date object.");
		}
		
		return objs;
	}
	
	private IHtmlObject[] getAvailabilityGrids() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TD", ".id", new RegularExpression("r_\\d+_c_\\d+", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find nay availability grid object.");
		}
		
		return objs;
	}
	
	/**
	 * get the availability grid month(s), there 2 situations:
	 * 				----- 2 months: March-April 2013
	 * 				----- only 1 month: April 2013
	 * 
	 * @return 
	 * 				---- 2 months: {"March 2013", "April 2013"}
	 *				---- 1 month: {"April 2013"}
	 */
	public String[] getAvailabilityGridMonths() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "month");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find availability date DIV object.");
		}
		String text = objs[0].text();
		String years[] = RegularExpression.getMatches(text, "[0-9]{4}");
		String dates[] = text.replace(years[0], StringUtil.EMPTY).split("-");
		for(int i = 0; i < dates.length; i ++) {
			dates[i] = dates[i].trim() + " " + years[0].trim();
		}
		Browser.unregister(objs);
		
		return dates;
	}
	
	/**
	 * get the first availability grid date
	 * @return '31 March 2013'
	 */
	public String getAvailabilityGridFirstDate() {
		IHtmlObject objs[] = getAvailabilityGridDates();
		
		String text = objs[0].text();//get the 1st one
		String dates[] = RegularExpression.getMatches(text, "\\d+");
		String month = getAvailabilityGridMonths()[0];
		String date = dates[0].trim() + " " + month.trim();//31 March 2013
		
		Browser.unregister(objs);
		return date;
	}
	
	public int getAvailabilityGridNums() {
		IHtmlObject objs[] = getAvailabilityGrids();
		
		int num = objs.length;
		Browser.unregister(objs);
		
		return num;
	}
	
	private void clickAvailabilityGridDate(String date) {
		date = DateFunctions.formatDate(date, "EEE MMM d yyyy");//Wed Apr 3 2013
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.TD", ".id", new RegularExpression("r_\\d+_c_\\d+", false), ".date", date));
	}
	
	public void selectArrivalDateInGrid(String arrivalDate) {
		//TODO ALT+Click
	}
	
	public void selectDepartureDateInGrid(String departureDate) {
		clickAvailabilityGridDate(departureDate);
	}
	
	public void updateDateFromGrid(String departure) {
		selectDepartureDateInGrid(departure);
	}
	
	public String[] getSelectedAvailabilityGrids() {
		IHtmlObject objs[] = getAvailabilityGrids();
		List<String> dates = new ArrayList<String>();
		for(int i = 0; i < objs.length; i ++) {
			if(objs[i].getProperty(".className").contains("dslct")) {
				dates.add(objs[i].getProperty(".date"));
			}
		}
		
		Browser.unregister(objs);
		return dates.toArray(new String[0]);
	}
	
	public String[] getArrivalDepartureDatesFromAvailabilityGrid() {
		String allSelectedDates[] = getSelectedAvailabilityGrids();
		String dates[] = new String[2];
		dates[0] = allSelectedDates[0];
		dates[1] = DateFunctions.getDateAfterGivenDay(allSelectedDates[allSelectedDates.length - 1], 1);
		
		return dates;
	}
	
	public boolean compareDatesInGridWithFields(String arrival, String departure) {
//		Browser.sleep(3);
		String datesInGrid[] = getArrivalDepartureDatesFromAvailabilityGrid();
		String arrivalDateInField = getArrivalDateTextFieldValue();
		String departureDateInField = getDepartureDateTextFieldValue();
		int stayInGrid = getSelectedAvailabilityGrids().length;
		int stayInField = getNights();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("[Expected Arrival Date] compares to [Arrival Date in Grid]", arrival, datesInGrid[0]);
		result &= MiscFunctions.compareResult("[Expected Departure Date] compares to [Departure Date in Grid]", departure, datesInGrid[1]);
		result &= MiscFunctions.compareResult("[Arrival Date in Grid] compares to [Arrival Date in Field]", datesInGrid[0], arrivalDateInField);
		result &= MiscFunctions.compareResult("[Departure Date in Grid] compares to [Departure Date in Field]", datesInGrid[1], departureDateInField);
		result &= MiscFunctions.compareResult("[Nights in Grid] compares to [Nights in Field]", stayInGrid, stayInField);
		
		return result;
	}
	
	public String getErrorMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".classname", "message msgerror");
		if(objs.length<1){
			throw new ErrorOnPageException("Couldn't find error message on the top...");
		}
		String error = objs[0].getProperty(".text");
		for(int i=1; i<objs.length; i++){
			error += " " +objs[i].getProperty(".text");
		}
		return error;
	}
	
	/**
	 * This method is used to check that the date after the given date is not available for this slip
	 * @param fromDateIndex
	 */
	public void checkDateAvailable(int fromDateIndex, boolean isAvailable){
		boolean passed = true;
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "AvailGridContainer");
		IHtmlObject tds[] =  browser.getHtmlObject(".class", "Html.TD", ".id", new RegularExpression("r_0_c_\\d+", false), objs[0]);
		for(int i=fromDateIndex; i<tds.length; i++){
			String status = tds[i].getAttributeValue("status");
			if(!isAvailable&&!status.equalsIgnoreCase("X")){
				passed = false;
				logger.error("The " + i + "th date may avaliable!");
			}
			if(isAvailable&&status.equalsIgnoreCase("X")){
				passed = false;
				logger.error("The " + i + "th date may not avaliable!");
			}
		}
		Browser.unregister(objs);
		Browser.unregister(tds);
		if(!passed){
			throw new ErrorOnPageException("Some date may be wrong please check the log above!");
		}
	}
	
	public void verifySlipAvailabilityStatusOfSpecificDateGrid(String expectStatus, int nights){
		List<String> actStatus = this.getSlipAvailabilityStatusOfSpecificDateGrid(nights);
		boolean result = true;
		for(int i=0;i<actStatus.size();i++){
			result &= MiscFunctions.compareResult("Specific Slip Status of Date Grid", expectStatus, actStatus.get(i));
		}
		if(!result){
			throw new ErrorOnPageException("Specific Slip Status of Date Grid not correct.");
		}
	}
	
	public List<String> getSlipAvailabilityStatusOfSpecificDateGrid(int nights){
		IHtmlObject objs[] = this.getAvailabilityGrids();

		int checkedGridCount = nights;

		if(objs.length<checkedGridCount){
			throw new ItemNotFoundException("Slip Grid object not found.");
		}
		List<String> temps = new ArrayList<String>();
		for(int i=0;i<checkedGridCount;i++){
			String temp = objs[i].text();
			temps.add(temp);
		}
		
		Browser.unregister(objs);
		return temps;
	}
	
	private IHtmlObject[] getSlipGridObjects() {
		IHtmlObject[] tds = browser.getHtmlObject(".id", new RegularExpression("r_0_c_\\d{1,2}",false));
		if(tds.length < 1) throw new ItemNotFoundException("Cannot find Slip grid td objects.");
		
		return tds;
	}
	
	/**
	 * This method get the hightlight date for the special slip
	 * 
	 * @return
	 */
	public List<String> getHightLightAvailabilityDates() {
		IHtmlObject[] tds = this.getSlipGridObjects();
		List<String> result = new ArrayList<String>();
		for(int i=0; i< tds.length; i++){
			if(StringUtil.notEmpty(tds[i].getAttributeValue("insl"))){
				result.add(tds[i].getAttributeValue("date").trim());
			}
		}
		Browser.unregister(tds);
		return result;
	}
	
	/**
	 * get the slip grid status of specific date, such as 'A', 'a', 'W', .etc
	 * @param date
	 * @return
	 */
	public String getSlipGridStatus(String date) {
		IHtmlObject[] tds = this.getSlipGridObjects();
		
		String status = "";
		for(int i = 0; i < tds.length; i ++) {
			if(DateFunctions.compareDates(tds[i].getProperty(".date"), date) == 0) {
				status = tds[i].text();
				break;
			}
		}
		
		Browser.unregister(tds);
		
		return status;
	}
	
	public boolean isFacilityLinkExists(String facility) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", facility);
	}
	
	public void clickFacilityLink(String facility) {
		browser.clickGuiObject(".class", "Html.A", ".text", facility, true);
	}
	
	  private boolean checkButtonIsEnable(String name){
		  return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", name);
	  }
	  
	  public boolean isViewReservationsButtonEnabled(){
		  return checkButtonIsEnable("View Reservations");
	  }
	  
	  public boolean isApplyForLotteryButtonEnabled(){
		  return checkButtonIsEnable("Apply For Lottery");
	  }
	  
	  public void clickRates(){
		  browser.clickGuiObject(".class", "Html.TD", ".text", "Rates");
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
			result &= MiscFunctions.compareResult("Transfer from Marina", transferFromSlip.getMarina(), getChangeFromMarina());
			result &= MiscFunctions.compareResult("Transfer from Dock/Area", transferFromSlip.getParentDockArea(), getChangeFromDockArea());
			result &= MiscFunctions.compareResult("Transfer from Slip # - Name", transferFromSlip.getCode() + "-" + transferFromSlip.getName(), getChangeFromSlipNumName());
			result &= MiscFunctions.compareResult("Transfer from Arrival Date", transferFromSlip.getArrivalDate(), getChangeFromArrivalDate());
			result &= MiscFunctions.compareResult("Transfer from Departure Date", transferFromSlip.getDepartureDate(), getChangeFromDepartureDate());
			if(transferFromSlip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
				result &= MiscFunctions.compareResult("Transfer from Nights", transferFromSlip.getNights(), Integer.parseInt(getChangeFromNights()));
			} else {
				result &= MiscFunctions.compareResult("Transfer from Months", transferFromSlip.getMonths(), Integer.parseInt(getChangeFromMonths()));
			}
			
			if(!result) {
				throw new ErrorOnPageException("'Transfer From Slip' section info is NOT correct, please refer o log for details.");
			} else logger.info("'Transfer From Slip' section info is correct.");
		}
		//*******************For transfer******************//
		
	public boolean isOkButtonEnabled(){
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "OK");
	}
	
	public String getTrailBarContent(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TABLE", ".id", "_trail_bar_");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find trail bar table object.");
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public boolean compareSlipReservationDateForRafting(boolean isDayUse, SlipInfo slip, String reservationAvailablilityStatus, int nightsOrDays){
		boolean passed = true;
		
		String actArrivalDate = this.getArrivalDateForResDates();
		String departureDate = this.getDepartureDateForResDates();
		int actNightsOrDays = -1;
		if(isDayUse){
			actNightsOrDays = this.getDaysForResDates();
		}else{
			actNightsOrDays = this.getNightsForResDates();
		}
		
		String availabilityStatus = this.getAvailailityForResDates();
		boolean arrivalDateTextFieldExisting = this.isArrivalDateTextFieldExists();
		boolean departureDateTextFieldExisting = this.isDepartureDateTextFieldExists();
		
		
		passed &= MiscFunctions.compareResult("Arrive Dates", slip.getArrivalDate(), actArrivalDate);
		passed &= MiscFunctions.compareResult("Departure Dates", slip.getDepartureDate(), departureDate);
		passed &= MiscFunctions.compareResult("Nights or Days", nightsOrDays, actNightsOrDays);
		passed &= MiscFunctions.compareResult("Availablity Stats", reservationAvailablilityStatus, availabilityStatus);
		passed &= MiscFunctions.compareResult("Arrival Date text field is existing", false, arrivalDateTextFieldExisting);//used to verify arrival date is ready only
		passed &= MiscFunctions.compareResult("Departure Date text field is existing", false, departureDateTextFieldExisting);//used to verify arrival date is ready only
		
		return passed;
	}
}
