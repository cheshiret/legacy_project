/*
 * Created on Nov 3, 2009
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

/**
 * @author vzhao This data collection class will provide all data about
 *         facilities.
 */
public class InventoryInfo extends TestData {
	public String searchType = "";

	public String searchValue = "";
	
	public String status = "Active";
	
	public boolean includeInPrintedPermit = false;
	
	public boolean includeInConfirmationLetter = false;
	
	public String application;
	public boolean selectApplication = false;
	
	public String[] applications;
	public boolean[] selectApplications;
	
	public String searchStatus = "";

	public String facilityID = "";

	public String facilityName = "";

	public String description = "";

	public String startDate = "";

	public String endDate = "";

	// tour paras
	public String tourType = "";

	public String tourValue = "";

	public String tourStatus = "";

	public String tourCode = "";

	public String tourName = "";

	// new tour inventory paras
	public String capacity = "";

	public String firstTime = "";

	public String firstTimeStamp = ""; // AM OR PM

	public String lastTime = "";

	public String lastTimeStamp = "";// AM OR PM
	
	public String openTime = "";
	
	public String openTimeAmPm = "";
	
	public String closeTime = "";
	
	public String closeTimeAmPm = "";
	
	public String lastEntryTime = "";
	
	public String lastEntryTimeAmPm = "";

	public String recuHour = "";

	public String recuMins = "";

	public String minOrgNum = "";

	public String maxOrgNum = "";

	public String minIndNum = "";

	public String maxIndNum = "";

	public String alertType = "";

	public String alertStartDate = "";

	public String alertEndDate = "";

	public String reserNum = "";
	
	public String siteNum = "";
	
	public String arrivalDate = "";
	
	public String appliedTo = "";
	
	public int appliesToIndex = 0;
	
	public String alertID = "";
	
	public boolean assignAll = true;
	
	public String[] siteCodes = null;
	
	public String[] siteIds = null;
	
	public String[] siteNames = null;
	
	public String[] loop = null;
	
	public String[] loopIDs = null;
	
	public String[] loopNames = null;

	//Search Note/Alert
	public String entrance;

	public String lookFor;
}
