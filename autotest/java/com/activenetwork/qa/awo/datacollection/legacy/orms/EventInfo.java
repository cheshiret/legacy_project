package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class EventInfo extends TestData {
	//	Events parameters
	public String eventName = "";
	
	public String eventType="";
	
	public String eventDescription="";
	
	public String eventIcon="";
	
	public String distanceCode="";
	
	public String publishDate="";

	public String eventStart = "";

	public String eventEnd = "";

	public String nights = "";

	public String eventID = "";

	public String negoPrice = "";

	public String eventAction = "";//Void, Close, Undo Close, Cancel, Negotiate Price

	//customer info
	public String lName = "";

	public String fName = "";
	
	public String email = "";
	
	public String fullName = "";
	
	public String phone = "";
	
	public String includeArea = "";

	public String organization = "";
	
	//other info
	public String posResNum = "";

	public String voidReason = "";

	public String cancelReason = "Cancellation";

	public String changeDateReason = "";

	public String note = "QA Auto Sanity";

	//change event dates
	public String newArrive = "";

	public String newEnd = "";

	public String newNights = "";
	
	public String eventNewName="";
	
	public String searchBy = "";
	
	public String status = "";
	
	public String location = "";
	
}
