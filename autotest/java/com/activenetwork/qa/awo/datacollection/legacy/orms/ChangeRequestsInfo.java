/*
 * Created on Oct 20, 2010
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;


/**
 * @author Sara Wang
 * This class contains data for Change Requests
 */
public class ChangeRequestsInfo {
	//Change requests page info
	public String searchType = "";
	public String searchTypeIdOrValue = "";
	public String cRStatus = "";
	public String facilityName = "";
	public String searchDate = "";
	public String searchFromDate = "";
	public String searchToDate = "";
	public String cRID = "";
	public String numOfItems = "";
	
	//change requests detail page info
	public String cRApprovedDate = "";
	public String cRApprovedUser = "";
	public String cRApprovedLocation = "";
	public String cRRejectedDate = "";
	public String cRRejectedUser = "";
	public String cRRejectedLocation = "";
	public String cROpenDate = "";
	public String cROpenUser = "";
	public String cROpenLocation = "";
	public String cRClosedDate = "";
	public String cRClosedUser = "";
	public String cRClosedLocation = "";
	public String cRRequestComments = "";
	public String cRAddToComments = "";
	
	public String cRModifyStatus = "";
	public String cRWarningMessage = "";
	
	String requestTypes = "All";
	public ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
}
