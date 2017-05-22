package com.activenetwork.qa.awo.datacollection.legacy.web;

import java.util.Random;

//import testAPI.dataCollection.uwp.UwpMemberProfile;

public class MemberProfile {

	/** NOTE: change this number if you want a new unique user to be created for a batch run of tests */
	public static final int STATIC_USER_ID = 98228;

	public static final int TOTAL_PARAMETERS = 17;

	public static final boolean USE_PRESET_PROFILE = true;

	public static final boolean USE_STATIC_USER_ID = true;

	private String alt_password = "shrt1pw";

//	private boolean usingAltPwd = false;

	public String email = "";

	public String password = "";

	public String title = "";

	public String firstName = "";

	public String initial = "";

	public String lastName = "";

	public String homePhone = "";

	public String workPhone = "";

	public String workExtension = "";

	public String cellPhone = "";

	public String organization = "";

	public String address = "";

	public String city = "";

	public String state = "";

	public String postalCode = "";

	public String country = "";

	public String optIn = "";

	private int totalParamsSet = 0;

	public MemberProfile() {
	}

	public MemberProfile(boolean preInit, int prevUniqueID) {
		this(preInit);
		email = "qa_auto" + prevUniqueID + "@reserveamerica.com";
		firstName = "QA-qa_auto" + prevUniqueID;
	}

	public MemberProfile(boolean preInit) {
		this();

		if (preInit == true) {
			boolean useStaticID = false;
			resetMemberProfile(useStaticID);
		}
	}

	public MemberProfile(boolean preInit, boolean useStaticID) {
		this();

		if (preInit == true) {
			resetMemberProfile(useStaticID);
		}
	}

	/** Reset MemberProfile object to contain original preset member profile data */
	public void resetMemberProfile(boolean useStaticID) {
		int userID;
		if (useStaticID == true) {
			userID = STATIC_USER_ID;
		} else {
			Random r=new Random();
			userID = r.nextInt(100000);
		}

		email = "qa_auto" + userID + "@reserveamerica.com";
		password = "test123again";
		title = "Mr";
		firstName = "QA-qa_auto" + userID;
		initial = "A";
		lastName = "Tester-Dude";
		homePhone = "905-286-6600";
		workPhone = "905-286-6600";
		cellPhone = "905-286-6600";
		workExtension = "0000";
		organization = "ReserveAmerica";
		address = "2480 meadowvale";
		city = "Mississauga";
		state = "Ontario";
		postalCode = "L5N8M6";
		country = "Canada";
		optIn = "";

		totalParamsSet = TOTAL_PARAMETERS;
	}

	/** Set MemberProfile object to contain alternative member profile data (except e-mail and password) */
	public void useAltMemberProfile() {
		title = "Mrs";
		firstName = "QA";
		initial = "D";
		lastName = "Testing";
		homePhone = "519-386-6600";
		workPhone = "519-386-6600";
		cellPhone = "519-386-6600";
		workExtension = "9090";
		organization = "Tickmaster";
		address = "3490 Deer Run Drive";
		city = "Calgary";
		state = "Alberta";
		postalCode = "T1R4R5";
		country = "Canada";

		totalParamsSet = TOTAL_PARAMETERS;
	}

	public int getTotalParametersSet() {
		return totalParamsSet;
	}

	/** Retrieves the stored alternate password from MemberProfile object */
	public String useAltPassword() {
//		usingAltPwd = true;
		return alt_password;
	}
}
