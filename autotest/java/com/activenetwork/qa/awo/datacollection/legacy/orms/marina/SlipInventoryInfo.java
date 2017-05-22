package com.activenetwork.qa.awo.datacollection.legacy.orms.marina;

/**
 * This data collection is for slip inventory used in Slip Inventory List page in Inventory Manager
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Mar 4, 2013
 */
public class SlipInventoryInfo {
	// search criteria
	private String arrivalDate;
	private String slipNum;
	private String resNum;
	private String closureID;
	private String invStatus;
	private String fromDate;
	private String toDate;
	private String slipType;
	private String slipRelationType;
	private String rafting;
	private String marina;
	private String marinaRateType;
	private String nights;
	
	// slip inventory list info
	private String invID;
	private String invDateTime;
	private String slipName;
	private String nssParent;
	private String dockArea;
	private String startDate;
	private String endDate;
	private String bookingID;
	private String departureDate;
	private String boatLength;
	private String userName;
	private String userLocation;
	private String salesChannel;
	
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getSlipNum() {
		return slipNum;
	}
	public void setSlipNum(String slipNum) {
		this.slipNum = slipNum;
	}
	public String getResNum() {
		return resNum;
	}
	public void setResNum(String resNum) {
		this.resNum = resNum;
	}
	public String getInvStatus() {
		return invStatus;
	}
	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSlipType() {
		return slipType;
	}
	public void setSlipType(String slipType) {
		this.slipType = slipType;
	}
	public String getSlipRelationType() {
		return slipRelationType;
	}
	public void setSlipRelationType(String slipRelationType) {
		this.slipRelationType = slipRelationType;
	}
	public String getInvID() {
		return invID;
	}
	public void setInvID(String invID) {
		this.invID = invID;
	}
	public String getInvDateTime() {
		return invDateTime;
	}
	public void setInvDateTime(String invDateTime) {
		this.invDateTime = invDateTime;
	}
	public String getSlipName() {
		return slipName;
	}
	public void setSlipName(String slipName) {
		this.slipName = slipName;
	}
	public String getNssParent() {
		return nssParent;
	}
	public void setNssParent(String nssParent) {
		this.nssParent = nssParent;
	}
	public String getDockArea() {
		return dockArea;
	}
	public void setDockArea(String dockArea) {
		this.dockArea = dockArea;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBookingID() {
		return bookingID;
	}
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getBoatLength() {
		return boatLength;
	}
	public void setBoatLength(String boatLength) {
		this.boatLength = boatLength;
	}
	public String getClosureID() {
		return closureID;
	}
	public void setClosureID(String closureID) {
		this.closureID = closureID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public String getRaftingStatus() {
		return rafting;
	}
	public void setRaftingStatus(String rafting) {
		this.rafting = rafting;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	public String getMarina() {
		return marina;
	}
	public void setMarina(String marina) {
		this.marina = marina;
	}
	public String getMarinaRateType() {
		return marinaRateType;
	}
	public void setMarinaRateType(String marinaRateType) {
		this.marinaRateType = marinaRateType;
	}
	public String getNightsNum() {
		return nights;
	}
	public void setNightsNum(String nights) {
		this.nights = nights;
	}
}
