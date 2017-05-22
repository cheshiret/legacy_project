/*
 * Created on Mar 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BookingData extends UwpUnifiedSearch {
	public static final String DEFAULT_CONTRACT = OrmsConstants.CONTRACT_NY;

	public static final int TOTAL_PARAMETERS = 7;

	public static final boolean USE_PRESET_DATA = true;

	//search criteria

	public String keyword = "";

	public String landMark = "";//belongs to special state
	
	public String near = "";

	public String agency = "";

	public String activity = "";

	public String state = "";//The state or province name
	
	public String switchToState = "";//the new state which want to switch to in state map page

	public String stateCode = "";//The short name for state or province in upper case

	public String park = ""; //Park name
	
	public int parkNameIndex = 0;
	
	public String typeOfUse = "";

	/**
	 * this is the park name string in .href property, for KOA search Tom's Campground
	 */
	public String parkstr = "";

	public String siteType = "";
	
	//Sara[12/5/2013]
	//1 - normal site; 21 - nsq site; 22 - nss site; 23 
	//web.getSiteRelationTypeID(bd.siteID, schema);
	public int siteRelationTypeID; 
	
	public String loop = "";

	public String area = "";

	public String siteNo = "";
	
	public String siteName ="";
	
	public String siteID = "";
	
	public String[] siteIDs ;

	public String conType = ""; //Contract type, options are "Federal","State" and "Private"
	
	public boolean isUpdateAble = true; // whether or not the selected park allow update on reservation detail page

	public boolean isProduction = false;//used for KOA
	
	public boolean ignoreStatus = false;//available or whatever the status of park/site is
	
	public boolean clickParkName = false;//whether or not click on park name link for whatever the status of park is
	
	public boolean searchInParkDetail = false;//whether or not click on park name link for whatever the status of park is
	
	public boolean clickSiteNum = false;//whether or not click on site number link for whatever the status of site is
	
	public boolean clickEnterDate = false;//whether or not search the site from site list, click Enter Date in site list
	
	public boolean clickBookNow = false;//whether or not click Book Now in park detail page
	
	public boolean adjusted = true;

	public boolean isRange = false; //search for a range of date or a specific date
	
	public boolean isArrivalDateBlank = false; //whether or not the arrival date should set to blank
	
	public boolean isLengthOfStayBlank = false; //whether or not the arrival date should set to blank
	
	public boolean enterDateButtonDisplays = false; //"Enter Date" button displays in site list page
	
	public boolean seeDetailsButtonDisplays= true; //"See Details" button displays in site list page

	public int maxLoop = 10; //for search in range of date

	//for parkwith search
	public boolean parkWith = false;

	public String parkWithAmenity = "";

	//for spotwith search
	public boolean spotWith = false;

	public String spotWithEquipLength = "";

	public String spotWithOccupants = "";

	public String spotWithElectricHookup = "";

	public boolean spotWithWaterHookup = false;

	public boolean spotWithSewerHookup = false;

	public boolean spotWithPullthroughDriveway = false;

	public boolean spotWithAccessible = false;

	public boolean spotWithPetsAllowed = false;

	public boolean spotWithWaterFront = false;

	//for work flow branches
	public int branch = 1; //default branch is clicking "See Details" button

	// brach 2 -  click park link and then click book now

	//for range search
	public String startDate = "";

	public String endDate = "";

	//for KOA
	public String adultNum = "";

	public String seniorNum="";
	
	public String childrenNum = "";

	public String freeNum = "";

	public String equip = "";

	public String equipLength = "";
	
	public String flexibleDate = "";
	
	public boolean isUnifiedSearch=false;
	
	public boolean isChangingDateOnly = false; //When making 
	
	public boolean isSearchingBySiteNum = false;

	
	//	private int totalParamsSet = 0;

	public BookingData() {
	}

	public BookingData(boolean preInit, int prevUniqueID) {
		this(preInit);
	}

	public BookingData(boolean preInit) {
		this();

		if (preInit == true) {
			initBookingData();
		}
	}

	/** Reset BookingData object to contain original preset booking data for default contract */
	public void initBookingData() {
		initBookingData(DEFAULT_CONTRACT);
	}

	/** Reset BookingData object to contain original preset booking data for specific contract */
	public void initBookingData(String contract) {
		if (contract.equals(OrmsConstants.CONTRACT_CO)) {
			state = "Colorado";
			park = "Arkansas Headwaters";
			area = "Five Points";
			siteNo = "002";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "CO";
		}

		if (contract.equals(OrmsConstants.CONTRACT_KY)) {
			state = "Kentucky";
			park = "Carr Creek State Park";
			area = "Sites 1-39";
			siteNo = "001";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "KY";
		}

		if (contract.equals(OrmsConstants.CONTRACT_MS)) {
			state = "Massachusetts";
			park = "Clarksburg State Park";
			area = "Clarksburg";
			siteNo = "013";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "MS";
		}

		if (contract.equals(OrmsConstants.CONTRACT_NE)) {
			state = "Nebraska";
			park = "Calamus SRA";
			area = "Valley View Flat";
			siteNo = "005-008";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "NE";
		}

		if (contract.equals(OrmsConstants.CONTRACT_NY)) {
			state = "New York";
			park = "Allegany State Park";
			area = "Cain Hollow Loop C";
			siteNo = "081";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "NY";
		}

		if (contract.equals(OrmsConstants.CONTRACT_RA)) {
			state = "New York";
			park = "Allegany State Park";
			area = "Diehl Cabin / Campsite Trail";
			siteNo = "001";
			arrivalDate = "01-29-2009"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "RA";
		}

		if (contract.equals(OrmsConstants.CONTRACT_RECGOV)) {
			state = "Colorado";
			park = "Aspen";
			area = "Aspen";
			siteNo = "010";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "RECGOV";
		}

		if (contract.equals(OrmsConstants.CONTRACT_SC)) {
			state = "South Carolina";
			park = "Aiken";
			area = "Campground";
			siteNo = "004";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "SC";
		}

		if (contract.equals(OrmsConstants.CONTRACT_WI)) {
			state = "Wisconsin";
			park = "Big Foot Beach State Park";
			area = "Sites 1-36,A-C";
			siteNo = "002";
			arrivalDate = "07-01-2008"; // DateFunctions.getCurrentDate()

			lengthOfStay = "7";
			conType = "WI";
		}
	}
}
