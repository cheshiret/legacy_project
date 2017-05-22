package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.List;

public class PrivilegeInventory {
	
	public String inventoryType = "";
	
	public String inventoryTypeStatus = "";
	
	public String licenseYear = "";
	
	public String issueFromDate = "";
	
	public String issueToDate = "";
	
	public String inventoryNumber = "";
	
	public String inventoryNumFrom = "";
	
	public String inventoryNumTo = "";
	
	public String inventoryStatus = "";//used for search
	
	public String allocationStatus = "";//used for search
	
	//agent info is formed by agentID and agentName, used for search
	public String agentID = "";// used for search
	
	public String agentName = "";//used for search
	
	public List<PriInventoryItemInfo> inventoryInfos;
	
	//privilege inventory counts
	public int numOfAvailable = 0;
	
	public int numOfUnusableAvailable = 0;
	
	public int numOfUnusableReturned = 0;
	
	public int numOfUnusableWithdrawn = 0;
	
	public int numOfSold = 0;
}
