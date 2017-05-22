/*
 * Created on Mar 11, 2010
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 * This data collection is used for creating new facilities data
 */
public class FacilityData {
  	//location parameters
  	public String contract = "";
  	public String stateCode = "";
	public String agency = "";
  	public String region = "";
  	public String district = "";
  	public String project = "";
  	
  	//details parameters, only required fields
  	public String facilityName = "";
  	public String facilityID = "";
  	public String alternateFacilityID = "";
  	public String shortName = "";	// should only contain 4 digital
  	public String status = "";
  	public String description = "";
  	public String country = "";
  	public String facility_state = "";
  	public String productCategory = "";
  	public String facilityType = "";
  	public String facilityPhotoSrc = "";
  	
  	public String mailingAddress = "";
  	public String mailingSupplementalAddress = "";
  	public String mailingCityTown = "";
  	public String mailingState = "";
  	public String mailingCounty = "";
  	public String mailingZipCode = "";
  	public String mailingCountry = "";
  	
  	public String directLine = "";
  	public String publicLine = "";
  	public String fax = "";
  	public String email = "";
  	public String website = "";
  	public String http = "";
  	
  	public String primaryPOCLastName = "";
  	public String primaryPOCFirstName = "";
  	public String primaryPOCPhone = "";
  	public String primaryPOCFax = "";
  	public String primaryPOCEmail = "";
  	public String primaryPOCAddress = "";
  	public String primaryPOCCityTown = "";
  	public String primaryPOCState = "";
  	public String primaryPOCZipCode = "";
  	public String primaryPOCCountry = "";
  	public String otherPOCLastName = "";
  	public String otherPOCFirstName = "";
  	public String otherPOCPhone = "";
  	public String otherPOCFax = "";
  	public String otherPOCEmail = "";
  	public String otherPOCAddress = "";
  	public String otherPOCCityTown = "";
  	public String otherPOCState = "";
  	public String otherPOCZipCode = "";
  	public String otherPOCCountry = "";
  	
  	public String timeZone = "";
  	public String geographicRegion = "";
  	
  	/*
  	 * common facility attributes
  	 */
  	public String alias = "";
  	public String brochureDescription = "";
  	
  	public boolean concessionaire = false;
  	
  	public boolean usesFieldApplication = false;
  	public String pageDefaultRowCount = "";
  	
  	public String financialSessionType = "";
  	public boolean openingFloatRequired = false;
  	public boolean closeBlindly = false;
  	public boolean transmittals = false;
  	public boolean transmittalTraceNumRequired = false;
  	
  	//latitude and longitude info
  	public String latitudeDirection = "";
  	public String latitudeDegrees = "";
  	public String latitudeMinutes = "";
  	public String latitudeSeconds = "";
  	
  	public String longitudeDirection = "";
  	public String longitudeDegrees = "";
  	public String longitudeMinutes = "";
  	public String longitudeSeconds = "";
  	
  	public String importantInfo = "";
  	public String otherPhoneNumbers = "";
  	public String radioChannel ="";
  	public boolean popularFacility = false;
  	public String receiptCopiesNum = "";
  	public String[] referralFacilities;
  	public String bookingWindowDesc = "";
  	public String seasonDateDesc = "";
  	public boolean hiddenOnWebSearch = false;
  	public boolean hideFacilityLocationOnWeb = false;
  	public String hourOfOperation = "";
  	
  	/*
  	 * facility attributes for Site
  	 */
  	public String brochureGeography = "";
  	public String brochureRecreation = "";
  	public String brochureFacilities = "";
  	public String brochureNearbyAttractions = "";
  	public String checkinTime = "";
  	public String checkinTimeAmPm = "";
  	public String checkoutTime = "";
  	public String checkoutTimeAmPm = "";
  	public String drivingDirection = "";
  	public String feeAndCancellationDescription = "";
  	public boolean collectCreditCardInfoDuringCheckin = false;
  	public boolean allowPreCheckIn = false;
  	public String checkinListSortOrder = "";
  	public String checkoutListSortOrder = "";
  	public String reservationSearchDefault = "";
  	
  	public boolean hasSitesWithElectircHookup = false;
  	public boolean hasSitesWithFullHookup = false;
  	public boolean hasSitesWithPetsAllowed = false;
  	public boolean hasSitesWithWaterFront = false;
  	public boolean isAvailable = false;
  	/*
  	 * facility attributes for Permit
  	 */
  	public boolean showExitAlerts = false;
  	public String commercialReservationsMadeFor = "";
  	public String confirmationPeriod = "";
  	public String confirmationPeriodStartTime = "";
  	public String confirmationPeriodStartTimeAmPm = "";
  	public boolean displayAvailabilityOnButtonCaption = false;
  	public String onlinePermitPrintPeriod = "";
  	public String onlinePermitPrintPeriodStartTime = "";
  	public String onlinePermitPrintPeriodStartTimeAmPm = "";
  	public boolean permitInventoryAllocationsApplicable = false;
  	public boolean randomReleaseOfInventoryAppliesToRevokes = false;
  	public boolean walkinAllocationsIncludeAdvancedAllocations = false;
  	public String printPermitInformation = "";
  	public String webFacilitySpecialMessage = "";
  	
  	/*
  	 * facility attributes for Ticket
  	 */
  	public boolean adaAccessible = false;
  	public String availabilityLagTime = "";
  	public String maximumGenericTickets = "";
  	public String maximumUnsoldTickets = "";
  	public String facilityFeesURL = "";
  	public boolean signatureLineOnTicketReceipt = false;
  	public boolean autoInvalidateTickets = false;
  	public boolean ticketMailOut = false;
  	public String tourFacilitySaleType = "";
  	
  	/*
  	 * facility attribute for POS
  	 */
  	public boolean personalCheckPaymentForGeneralPublicPOSOrders = false;
  	
  	/*
  	 * facility attribute for Activity
  	 */
  	public boolean enforceMinimumToConfirmRuleInField = false;
  	
  	
  	//In Facility Supplementary Info page
  	//Telecommunications
  	public String voiceLine = "";
  	public String faxLine = "";
  	public String faxLocation = "";
  	public String faxDistanceToPark = "";
  	//Internet
  	public String internetProvidedByRa = "";
  	public String hardwareProvidedByRa = "";
  	public String internetCommectivity = "";//0->Satellite,1->ISP,2->ADSL/T1,3->Wireless,4->Dial UP
  	//Staffing
  	public String fullTimeStaff = "";
  	public String seasonalStaff = "";
  	public String summerVisitsPerWeek = "";
  	public String winterVisitsPerWeek = "";
  	public String distanceTravelledPerVisit = "";
  	public boolean isSummer = false;
  	public boolean isWinter = false;
  	//Park Configuration
  	public String staffedEntrances = "";
  	public String nonStaffedEntrances = "";
  	public String remoteSites = "";
  	
  	/*
  	 * List Management, default value is 'No'
  	 * 1. Waiting List drop down list
  	 * 2. Transfer List drop down list
  	 * 3. List Entry With Slip Reservation drop down list
  	 */
  	private String isWaitingList = "No";
	private String isTransferList = "No";
  	private String listEntryWithSlipRes = "No";
  	
  	public String getIsWaitingList() {
		return isWaitingList;
	}

	public void setIsWaitingList(String isWaitingList) {
		this.isWaitingList = isWaitingList;
	}

	public String getIsTransferList() {
		return isTransferList;
	}

	public void setIsTransferList(String isTransferList) {
		this.isTransferList = isTransferList;
	}

	public String getListEntryWithSlipRes() {
		return listEntryWithSlipRes;
	}

	public void setListEntryWithSlipRes(String listEntryWithSlipRes) {
		this.listEntryWithSlipRes = listEntryWithSlipRes;
	}


  	public void initializeFacilityData(){
		facilityName = "Name of Facilit";
		shortName = StringUtil.getRandomString(4, true);
		status = "Active";
		mailingAddress = "KeJi 2 Rd No.68 Xi'an Software Park";
		mailingSupplementalAddress = mailingAddress;
		mailingCityTown = "Xi'an";
		mailingState = "Kentucky";
		mailingCounty = "Adair";
		mailingZipCode = "710075";
		mailingCountry = "United States";
		publicLine = "68685958";
		fax = "8888";
		email = "LoadTest.Astra@activenetwork.com";
		http = "www.active.com";
		primaryPOCLastName = "LoadTest";
		primaryPOCFirstName = "Astra";
		primaryPOCPhone = "02968685958";
		primaryPOCFax = "8888";
		primaryPOCEmail = "LoadTest.Astra@activenetwork.com";
		primaryPOCAddress = "Xi'an Software Park Qin Feng Ge E201";
		primaryPOCCityTown = "Xi'an";
		primaryPOCState = "Kentucky";
		primaryPOCZipCode = "710075";
		primaryPOCCountry = "United States";
		otherPOCLastName = primaryPOCLastName;
		otherPOCFirstName = primaryPOCFirstName;
		otherPOCPhone = primaryPOCPhone;
		otherPOCFax = primaryPOCFax;
		otherPOCEmail = primaryPOCEmail;
		otherPOCAddress = primaryPOCAddress;
		otherPOCCityTown = primaryPOCCityTown;
		otherPOCState = primaryPOCState;
		otherPOCZipCode = primaryPOCZipCode;
		otherPOCCountry = "United States";
		timeZone = "America/Chicago";
		geographicRegion = "Hills Region";
		brochureDescription = "BrochureDescription of Facility";
		drivingDirection = "DrivingDirection of facility";
		adaAccessible = true;
		latitudeDirection = "North";
		latitudeDegrees = "34";
		latitudeMinutes = "13";
		latitudeSeconds = "32";
		longitudeDirection = "East";
		longitudeDegrees = "108";
		longitudeMinutes = "52";
		longitudeSeconds = "37";
		hourOfOperation = "HourOfOperation of facility";
  	}
}
