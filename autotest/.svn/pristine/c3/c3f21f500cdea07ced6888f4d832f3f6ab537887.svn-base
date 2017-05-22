package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public class SlipInfo {
	
	private String id;
	private String code;
	private String name;
	private String status;
	private String type;
	private String nssGroup;
	private String description;
	private boolean assigned = false;
	private String parentDockArea;
	private String relationType;
	private int numOfSlips;
	
	private String searchType;
	private String searchValue;
	
	//slip dimensions
	private int length;
	private int width;
	private int depth;
	
	//boat dimensions
	private int minBoatLength;
	private int maxBoatLength;
	private int boatSpacing;
	
	private boolean webVisible = true;
	private boolean isAssigntoList;
	
	private boolean showReservableOnly = true;// default value
	
	/*
	 * slip attributes
	 */
	private boolean accessibleSite;
	private int maxNumOfPeople;
	private int maxNumOfVehicle;
	//check-In/Out time
	private String checkInHour;
	private String checkInMinute;
	private String checkInAmPm;
	private String checkInTime;
	private String checkOutHour;
	private String checkOutMinute;
	private String checkOutAmPm;
	private String checkOutTime;
	//hookups
	private int electrictity;
	private boolean waterHookup = false;
	private boolean sewerHookup = false;
	private int full;
	//latitude
	private String latitudeChannelDirection;
	private int latitudeDegree;
	private int latitudeMinute;
	private int latitudeSecond;
	//longitude
	private String longitudeChannelDirection;
	private int longitudeDegree;
	private int longitudeMinute;
	private int longitudeSecond;
	
	private int basePricingLength;
	private double pricingLength;// this used to calculate slip price, it may be boat length or may be slip length
	private int seasonalContractLength;
	private int seasonalContractStartDate;
	
//	private boolean boatRequired = false;//this attribute has been removed in PRODUCT
	private String typeOfUse;
	
	//Slip Reservation Type
	private String reservationType;
	
	//Slip services
	private String[] services;
	
	public void setServices(String... services){
		int num = services.length;
		this.services = new String[num];
		for(int i=0; i< num; i++){
			this.services[i] = services[i];
		}
	}
	
	public String[] getServices(){
		return this.services;
	}
	
	/**
	 * 
	 * Slip Reservation Info
	 * 
	 */
	private String stateShortName;
	public String getStateShortName() {
		return stateShortName;
	}
	public void setStateShortName(String stateShortName) {
		this.stateShortName = stateShortName;
	}
	private String marina;
	private String season;
	private String arrivalDate;
	private String departureDate;
	private String preArrivalDate;
	private String preDepartureDate;
	private String beginingDate;
	private String endingDate;
	private String nightsOfDateRang;
	private int nights;
	private double boatLength;
	private double boatWidth;
	private double boatDepth;
	private int months;
	private String actionReason;//TODO TBV transfer, change date, etc..
	private boolean isSkipFulfill = true;
	private boolean isWeekendOnly = false;// default value
	
	private String marinaID;
	private String expectedDockingDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void setDescription(String dscr) {
		this.description = dscr;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
	public String getParentDockArea() {
		return parentDockArea;
	}
	public void setParentDockArea(String parentDockArea) {
		this.parentDockArea = parentDockArea;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getMinBoatLength() {
		return minBoatLength;
	}
	public void setMinBoatLength(int minBoatLength) {
		this.minBoatLength = minBoatLength;
	}
	public int getMaxBoatLength() {
		return maxBoatLength;
	}
	public void setMaxBoatLength(int maxBoatLength) {
		this.maxBoatLength = maxBoatLength;
	}
	public int getBoatSpacing() {
		return boatSpacing;
	}
	public void setBoatSpacing(int boatSpacing) {
		this.boatSpacing = boatSpacing;
	}
	public boolean isWebVisible() {
		return webVisible;
	}
	public void setWebVisible(boolean webVisible) {
		this.webVisible = webVisible;
	}
	
	public boolean isAccessibleSite() {
		return accessibleSite;
	}
	public void setAccessibleSite(boolean accessibleSite) {
		this.accessibleSite = accessibleSite;
	}
	public int getMaxNumOfPeople() {
		return maxNumOfPeople;
	}
	public void setMaxNumOfPeople(int maxNumOfPeople) {
		this.maxNumOfPeople = maxNumOfPeople;
	}
	public int getMaxNumOfVehicle() {
		return maxNumOfVehicle;
	}
	public void setMaxNumOfVehicle(int maxNumOfVehicle) {
		this.maxNumOfVehicle = maxNumOfVehicle;
	}
	public String getCheckInHour() {
		return checkInHour;
	}
	public void setCheckInHour(String checkInHour) {
		this.checkInHour = checkInHour;
	}
	public String getCheckInMinute() {
		return checkInMinute;
	}
	public void setCheckInMinute(String checkInMinute) {
		this.checkInMinute = checkInMinute;
	}
	public String getCheckInAmPm() {
		return checkInAmPm;
	}
	public void setCheckInAmPm(String checkInAmPm) {
		this.checkInAmPm = checkInAmPm;
	}
	public String getCheckOutHour() {
		return checkOutHour;
	}
	public void setCheckOutHour(String checkOutHour) {
		this.checkOutHour = checkOutHour;
	}
	public String getCheckOutMinute() {
		return checkOutMinute;
	}
	public void setCheckOutMinute(String checkOutMinute) {
		this.checkOutMinute = checkOutMinute;
	}
	public String getCheckOutAmPm() {
		return checkOutAmPm;
	}
	public void setCheckOutAmPm(String checkOutAmPm) {
		this.checkOutAmPm = checkOutAmPm;
	}
	public int getElectrictity() {
		return electrictity;
	}
	public void setElectrictity(int electrictity) {
		this.electrictity = electrictity;
	}
	public boolean isWaterHookup() {
		return waterHookup;
	}
	public void setWaterHookup(boolean waterHookup) {
		this.waterHookup = waterHookup;
	}
	public boolean isSewerHookup() {
		return sewerHookup;
	}
	public void setSewerHookup(boolean sewerHookup) {
		this.sewerHookup = sewerHookup;
	}
	public int getFull() {
		return full;
	}
	public void setFull(int full) {
		this.full = full;
	}
	public String getLatitudeChannelDirection() {
		return latitudeChannelDirection;
	}
	public void setLatitudeChannelDirection(String latitudeChannelDirection) {
		this.latitudeChannelDirection = latitudeChannelDirection;
	}
	public int getLatitudeDegree() {
		return latitudeDegree;
	}
	public void setLatitudeDegree(int latitudeDegree) {
		this.latitudeDegree = latitudeDegree;
	}
	public int getLatitudeMinute() {
		return latitudeMinute;
	}
	public void setLatitudeMinute(int latitudeMinute) {
		this.latitudeMinute = latitudeMinute;
	}
	public int getLatitudeSecond() {
		return latitudeSecond;
	}
	public void setLatitudeSecond(int latitudeSecond) {
		this.latitudeSecond = latitudeSecond;
	}
	public String getLongitudeChannelDirection() {
		return longitudeChannelDirection;
	}
	public void setLongitudeChannelDirection(String longitudeChannelDirection) {
		this.longitudeChannelDirection = longitudeChannelDirection;
	}
	public int getLongitudeDegree() {
		return longitudeDegree;
	}
	public void setLongitudeDegree(int longitudeDegree) {
		this.longitudeDegree = longitudeDegree;
	}
	public int getLongitudeMinute() {
		return longitudeMinute;
	}
	public void setLongitudeMinute(int longitudeMinute) {
		this.longitudeMinute = longitudeMinute;
	}
	public int getLongitudeSecond() {
		return longitudeSecond;
	}
	public void setLongitudeSecond(int longitudeSecond) {
		this.longitudeSecond = longitudeSecond;
	}
	public int getBasePricingLength() {
		return basePricingLength;
	}
	public void setBasePricingLength(int basePricingLength) {
		this.basePricingLength = basePricingLength;
	}
	public double getPricingLength() {
		return pricingLength;
	}
	public void setPricingLength(double pricingLength) {
		this.pricingLength = pricingLength;
	}
	public int getSeasonalContractLength() {
		return seasonalContractLength;
	}
	public void setSeasonalContractLength(int seasonalContractLength) {
		this.seasonalContractLength = seasonalContractLength;
	}
	public int getSeasonalContractStartDate() {
		return seasonalContractStartDate;
	}
	public void setSeasonalContractStartDate(int seasonalContractStartDate) {
		this.seasonalContractStartDate = seasonalContractStartDate;
	}
//	public boolean isBoatRequired() {
//		return boatRequired;
//	}
//	public void setBoatRequired(boolean boatRequired) {
//		this.boatRequired = boatRequired;
//	}
	public String getTypeOfUse() {
		return typeOfUse;
	}
	public void setTypeOfUse(String typeOfUse) {
		this.typeOfUse = typeOfUse;
	}
	public int getNumOfSlips() {
		return numOfSlips;
	}
	public void setNumOfSlips(int numOfSlips) {
		this.numOfSlips = numOfSlips;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getPreArrivalDate() {
		return preArrivalDate;
	}
	public void setPreArrivalDate(String preArrivalDate) {
		this.preArrivalDate = preArrivalDate;
	}
	public String getPreDepartureDate() {
		return preDepartureDate;
	}
	public void setPreDepartureDate(String preDepartureDate) {
		this.preDepartureDate = preDepartureDate;
	}
	public int getNights() {
		return nights;
	}
	public void setNights(int nights) {
		this.nights = nights;
	}
	public double getBoatLength() {
		return boatLength;
	}
	public void setBoatLength(double boatLength) {
		this.boatLength = boatLength;
	}
	public double getBoatWidth() {
		return boatWidth;
	}
	public void setBoatWidth(double boatWidth) {
		this.boatWidth = boatWidth;
	}
	public double getBoatDepth() {
		return boatDepth;
	}
	public void setBoatDepth(double boatDepth) {
		this.boatDepth = boatDepth;
	}
	public String getMarina() {
		return marina;
	}
	public void setMarina(String marina) {
		this.marina = marina;
	}
	public void setSeason(String season){
		this.season = season;
	}
	public String getSeason(){
		return this.season;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public String getActionReason() {
		return actionReason;
	}
	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
	public boolean isSkipFulfill(){
		return this.isSkipFulfill;
	}
	public void setSkipFulfill(boolean isSkipFulfill){
		this.isSkipFulfill = isSkipFulfill;
	}
	public String getMarinaID() {
		return marinaID;
	}
	public void setMarinaID(String marinaID) {
		this.marinaID = marinaID;
	}
	public String getNssGroup() {
		return nssGroup;
	}
	public void setNssGroup(String nssGroup) {
		this.nssGroup = nssGroup;
	}
	public String getExpectedDockingDate() {
		return expectedDockingDate;
	}
	public void setExpectedDockingDate(String expectedDockingDate) {
		this.expectedDockingDate = expectedDockingDate;
	}
	public void setBeginingDate(String beginingDate){
		this.beginingDate = beginingDate;
	}
	public String getBeginingDate(){
		return this.beginingDate;
	}
	public void setEndingDate(String endingDate){
		this.endingDate = endingDate;
	}
	public String getEndingDate(){
		return this.endingDate;
	}
	public void setNightsOfDateRange(String nightsOfDateRange){
		this.nightsOfDateRang = nightsOfDateRange;
	}
	public String getNightsOfDateRange(){
		return this.nightsOfDateRang;
	}
	public boolean isAssigntoList() {
		return isAssigntoList;
	}
	public void setAssigntoList(boolean isAssigntoList) {
		this.isAssigntoList = isAssigntoList;
	}
	public String getCheckInTime() {
		if(!StringUtil.isEmpty(checkInTime)) {
			return checkInTime;
		} else {
			return getCheckInHour() + ":" + getCheckInMinute() + " " + getCheckInAmPm();
		}
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		if(!StringUtil.isEmpty(checkOutTime)) {
			return checkOutTime;
		} else {
			return getCheckOutHour() + ":" + getCheckOutMinute() + " " + getCheckOutAmPm();
		}
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	
	public boolean isWeekendOnly() {
		return isWeekendOnly;
	}
	public void setWeekendOnly(boolean isWeekendOnly) {
		this.isWeekendOnly = isWeekendOnly;
	}
	public boolean isShowReservableOnly() {
		return showReservableOnly;
	}
	public void setShowReservableOnly(boolean showReservableOnly) {
		this.showReservableOnly = showReservableOnly;
	}
}
