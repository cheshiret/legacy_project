package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import com.activenetwork.qa.testapi.util.DateFunctions;

public class SiteInfoData extends SiteAttributes {
	public String state;

	public String unitState;
	
	public String unitStateShortForm;

	public String unitMake;

	public String unitColor;

	public String parkName;
	
	public String parkID; // add by lesley
	
	public String areaName;

	public String vehicleModel;

	public String parkType = "";

	public String siteNumber; // siteCode

	public String siteId = "";

	public String nssChild;

	public String nssChildName;

	public boolean isNSSChild;

	public boolean isNSSSite = false;

	public String prdName = "";

	public String NSQDefaultAvailQty = "";

	public String NSQTotalQty = "";

	public String siteReserveType = "";

	public String payType;

	public String resChannel;
	public String sitePhoto;
	public String mapXCoordinate;
	public String mapYCoordinate;
	public String nssCount;
	public String nssAvailSitesCount;
	public String waterfront;
	public boolean withSewerHookup = false;
	// search parameters
	public String arrivalDate;

	public boolean arrivalDateMark;

	public String departDate;

	public String dayNightNum;

	// advanced site search parameters
	public boolean advanced = false;

	public boolean adaAccessible = false;

	public boolean waterHookup = false;

	public boolean cableTV = false;

	public boolean horseStall = false;

	public boolean petAllowed = false;

	public boolean reservable = false;

	public boolean reservableOnly = true;

	// three variables in Site Search page for camping lottery
	public boolean ShowReservableLotteryOnly = false;

	public boolean showAll = false;

	/**
	 * Flag to indicate if the arrival/departure date is forced during a site
	 * search
	 */
	public boolean fixedDate = true;
	public int rangloops = 12; // 12 loops
	public int dayloops = 180;
	public int rangeLength = 30; // 30 days range

	public String rangeStart;

	public String rangeEnd;
	// Total Site Number for site type.
	public int siteTypeTotalNum = 0;

	// book these days parameter
	public int maxLoop = 10;
	
	//site info on Rec.gov Site List page.
	public String onlineAvailability = "";
	

	public SiteInfoData() {
		state = "";
		unitState = "South Carolina";
		parkName = "";
		areaName = "";
		loopName = "";
		siteType = "";
		siteName = "";
		siteNumber = "";
		nssChild = "";
		nssChildName = "";
		isNSSChild = false;
		typeOfUse = "";
		arrivalDate = "";
		departDate = "";
		dayNightNum = "";
		unitMake = "";
		unitColor = "";

		rangeStart = "";
		rangeEnd = "";
	}

	public SiteInfoData(String parkName, String areaName, String siteType,
			String siteName, String siteNumber) {
		this.parkName = parkName;
		this.areaName = areaName;
		this.siteType = siteType;
		this.siteName = siteName;
		this.siteNumber = siteNumber;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	public static String convertSiteNameNumber(String snn)
			throws DataFormatException {
		String siteNumber = "";
		String[] siteNums = snn.split("-");
		if (siteNums.length < 2) {
			throw new DataFormatException("Site#(Name) format exception");
		}
		if (siteNums.length == 2) {
			siteNumber = siteNums[0];
		} else if (siteNums.length > 2) {
			siteNumber = siteNums[0] + "-" + siteNums[1];
		}

		return siteNumber;
	}

	public String toString() {

		return "Park Name: " + parkName + "\r\n Area Name: " + areaName
				+ "\r\n Site Name: " + siteName + "\r\n Site Number: "
				+ siteNumber + " \r\nSite Type: " + siteType;

	}

	/**
	 * Retrieve site initial site number and site name prefix from database
	 **/
	// public void setSiteNameAndNumFromDB(String testID) {
	// boolean hasIncrement = false;
	// setSiteNameAndNumFromDB(testID, hasIncrement);
	// }
	/**
	 * Retrieve site initial site number and site name prefix from database and
	 * increment if required (i.e. for new reservation) NOTE: value in the
	 * database should always point to currently available site #
	 **/
	// public void setSiteNameAndNumFromDB(String testID, boolean increment) {
	// String prevNum = "";
	// QATestDatabase qaDBconn = new QATestDatabase();
	// String dataKey = OrmsConstants.DATAKEY_INIT_SITENUM;
	// boolean skipTestID = false;
	//
	// // get prev. value (site #)
	// List<String> foundValues = qaDBconn.getValue(testID, dataKey,
	// skipTestID);
	// if ((foundValues != null) && !(foundValues.isEmpty())) {
	// prevNum = (String) foundValues.get(QATestDatabase.COL_INDEX_VALUE);
	// }
	//
	// // get value (site name prefix)
	// String sitePrefix = "";
	// dataKey = OrmsConstants.DATAKEY_INIT_SITEPREFIX;
	// foundValues = qaDBconn.getValue(testID, dataKey, skipTestID);
	// if ((foundValues != null) && !(foundValues.isEmpty())) {
	// sitePrefix = (String) foundValues
	// .get(QATestDatabase.COL_INDEX_VALUE);
	// }
	//
	// // send incremented value to DB and set current site # to that value
	// if (increment == true) {
	//
	// //String prevSiteName = prevNum + "" + sitePrefix;
	// String incNum = this.incrementSiteNumber(prevNum); //,prevSiteName
	// dataKey = OrmsConstants.DATAKEY_INIT_SITENUM;
	// qaDBconn.setValue(testID, dataKey, incNum);
	// this.siteNumber = incNum;
	// } else { // otherwise, set site # to site # found previously in DB
	// this.siteNumber = prevNum;
	// }
	//
	// this.siteName = sitePrefix + this.siteNumber;
	//
	// }
	/**
	 * Helper method that increments site number in instance variable for the
	 * reservation transfer test.
	 */
	public String incrementSiteNumber(String oldSiteNum) // , String oldSiteName
	{
		// String oldSiteNum = this.siteNumber;
		// String oldSiteName = this.siteName;

		// match letters and dash if prefixing site #
		Pattern p = Pattern.compile("\\b[A-Z]*[\\-]?");
		Matcher m = p.matcher(oldSiteNum); // i.e. S-002, 010, etc
		String found = "";

		while (m.find()) {
			found = m.group(0);
			break;
		}

		String prefix = found;
		String numToProcess = "";
		if (!found.equals("")) {
			found = m.replaceFirst(""); // remove prefix zeroes that were found
			numToProcess = found;
		} else {
			numToProcess = oldSiteNum;
		}

		int digitsLength = numToProcess.length(); // store length to zero-pad to

		// match zeros at the beginning of the site # (or pre-processed site #)
		p = Pattern.compile("\\b[0]*");
		m = p.matcher(numToProcess);
		found = "";

		while (m.find()) {
			found = m.group(0);
			break;
		}
		found = m.replaceFirst(""); // remove prefix zeroes that were found

		int siteNum = Integer.parseInt(found); // turn siteNum string into int
		siteNum++;
		String newSiteNum = Integer.toString(siteNum);
		for (int i = newSiteNum.length(); i < digitsLength; i++) {
			newSiteNum = "0" + newSiteNum; // zero-pad the site num again
		}

		newSiteNum = prefix + newSiteNum;

		return newSiteNum;
	}

	public void cleanup() {
		state = "";
		parkName = "";
		areaName = "";
		loopName = "";
		siteType = "";
		siteName = "";
		siteNumber = "";
		nssChild = "";
		isNSSChild = false;
		typeOfUse = "";
		arrivalDate = "";
		departDate = "";
		dayNightNum = "";

		rangeStart = "";
		rangeEnd = "";
	}

	public boolean countReachMax(int count) {
		if (advanced) {
			return count >= this.rangloops;
		} else {
			return count >= dayloops;
		}
	}

	public void setNextDates() {
		if (advanced) {
			rangeStart = DateFunctions.getDateAfterGivenDay(rangeStart,
					rangeLength);
			rangeEnd = DateFunctions
					.getDateAfterGivenDay(rangeEnd, rangeLength);

		} else {
			arrivalDate = DateFunctions.getDateAfterGivenDay(arrivalDate, 1);
			departDate = DateFunctions.getDateAfterGivenDay(arrivalDate,
					Integer.parseInt(dayNightNum));
		}
	}
}
