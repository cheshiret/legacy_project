package com.activenetwork.qa.awo.vpData;

import java.util.regex.Pattern;

public class VpParkSeason extends VpData {

	public String id = "";

	public String status = "";

	public String seasonType = "";

	public String locName = "";

	public String siteType = "";

	public String startDate = "";

	public String endDate = "";

	public String numOfSites = "";

	public boolean compare(Object obj) {
		VpParkSeason ps = (VpParkSeason) obj;

		return Pattern.matches(this.id, ps.id)
				&& Pattern.matches(this.status, ps.status)
				&& Pattern.matches(this.seasonType, ps.seasonType)
				&& Pattern.matches(this.locName, ps.locName)
				&& Pattern.matches(this.siteType, ps.siteType)
				&& Pattern.matches(this.startDate, ps.startDate)
				&& Pattern.matches(this.endDate, ps.endDate)
				&& Pattern.matches(this.numOfSites, ps.numOfSites)
				&& ps.tested == this.tested;

	}

}
