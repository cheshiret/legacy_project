package com.activenetwork.qa.awo.datacollection.legacy;

public class LoopInfoData {

	public String loopName = "";

	public String parent = "";

	public String description = "";
	
	public String loopID = "";
	
	//Loop site info
	public String[] loopSitesIDs = null;
	public String[] loopSitesCodes = null;
	public String[] loopSitesNames = null;
	public String[] loopOfSite = null;

	public void setLoopName(String loopName) {
		this.loopName = loopName;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "Loop/Area Name: " + loopName + "\r\n Parent: " + parent
				+ "\r\n Description: " + description;
	}

}
