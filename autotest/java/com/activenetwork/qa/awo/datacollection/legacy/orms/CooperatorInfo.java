/*
 * Created on Dec 1, 2009
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;


/**
 * @author QA
 * This class contains data for creating a cooperator.
 */
public class CooperatorInfo {
  	public String cooperatorCode = "";

	public String cooperatorName = "";

	public String district = "";
	
	public String town = "";
	
	public String primaryFirstName = "";

	public String primaryLastName = "";

	public String primaryPhone = "";
	
	public String secFirstName = "";

	public String secLastName = "";

	public String secPhone = "";

	public String street = "";

	public String email = "";

	public String fax = "";
	
	public String password = "";

	public String city = "";

	public String state = "";

	public String zip = "";

	public String country = "";

	public String phone = "";

	public String openingHour = "";

	public String closingHour = "";

	public String direction = "";

	public String permitIssueStation = "";

	public String longitudeDir = "";

	public String longitudeDeg = "";

	public String longitudeMin = "";

	public String longitudeSec = "";

	public String latitudeDir = "";

	public String latitudeDeg = "";

	public String latitudeMin = "";

	public String latitudeSec = "";
	
	public void setDefaultValues(){
	  	primaryFirstName = "QA";
	  	primaryLastName = "Tester";
	  	primaryPhone = "999-286-6600";
	  	street = "2480 meadowvale";
	  	city = "Mississauga";
	  	state = "Ontario";
	  	zip = "L5N8M6";
	  	country = "Canada";
	  	phone = "999-286-6600";
	}
}
