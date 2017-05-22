package com.activenetwork.qa.awo.datacollection.legacy;

public class Closure {

	public String type = "";

	public String startDate = "";

	public String endDate = "";

	public String status = "";

	public String notificationDate = "";

	public String comment = "";
	
	public String closureID="";

    public String recurring = ""; 
    
  //You can find those columns value in table 'i_clo_schdl'
    public String affectedOrdInstr = "";
    
    public String affectedOrdInd = "";  
    
    public String createdAppID = "";
    
    public String createdUserID = "";
    
    public boolean assignAll = true;
    
	public String[] siteCodes = null;
	
	public String[] siteIds = null;
	
	public String[] siteNames = null;
	
	public String loop = "";
	
	public String occurencePattern = "";
	
	public String dayOfMonth = "";
	
	public String[] weekDays = null;
	
	public String productCategory = "";// default value
	
	public String[] slipCD;
}
