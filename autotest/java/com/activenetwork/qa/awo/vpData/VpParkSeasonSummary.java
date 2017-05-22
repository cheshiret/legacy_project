package com.activenetwork.qa.awo.vpData;


public class VpParkSeasonSummary extends VpData {

	public String parkName;

	public String startDate;

	public String endData;

	public boolean compare(Object obj) {
		//vpParkSeasonSummary pss = (vpParkSeasonSummary) obj;

//		return MiscFunctions.verifyObjects(this.seasons, pss.seasons,
//				"testAPI.vpData.vpParkSeason")
//				&& Pattern.matches(this.parkName, pss.parkName)
//				&& Pattern.matches(this.startDate, pss.startDate)
////				&& Pattern.matches(this.endData, pss.endData);
		return false;
				
	}
}
