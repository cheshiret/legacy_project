package com.activenetwork.qa.awo.datacollection.legacy.orms;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

/**
 * The infomation needed to login to the system
 * @author raonqa
 */
public class LoginInfo extends TestData {

	public String url = "";

	public String userName = "";

	public String password = "";

	public String envType = "";

	//for call manager only
	public String startCall = "";

	public String contract = "";

	public String contractAbbrev = ""; // used as DB schema suffix

	public String location = "";

	public String station = "";

	public String park = "";
	
	public String role="";
}
