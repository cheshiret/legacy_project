package com.activenetwork.qa.awo.datacollection.legacy.orms;

public class User {

	public static final int TOTAL_PARAMETERS = 13;

	// fpr account credential
	public String userName = "";

	public String password = "";

	public String confirmPassword = "";
	
	public boolean isUserMustChangePasswordAtNextLogin = false;
	
	public boolean isActive = true;

	public boolean isLocked = false;
	
	public String activeStatus = "";
	public String lockedStatus = "";
	
	public int numOfLocations = 0;
	
	public int numOfRoles = 0;
	
	public String roles[] = null;
	
	public String locations[] = null;
	
	// those for user detail
	public String fName = "";

	public String lName = "";

	public String mName = "";

	public String email = "";

	public String phone = "";

	public String fax = "";

	public String address = "";

	public String state = "";

	public String zip = "";

	public String comment = "";

	public String contract = "";
	
	public String scope = "";
	private int totalParamsSet = 1;

	public User() {
	}

	public User(boolean preInit) {
		this();

		if (preInit == true) {
			userName = "qa_auto_user";
			password = "test123again";
			email = userName + "@reserveamerica.com";

			fName = "QAuser";
			mName = "A";
			lName = "Tester-";
			phone = "905-286-6600";
			fax = "905-286-6699";

			address = "2480 meadowvale";
			//			city = "Mississauga";
			state = "Ohio";
			zip = "32553";
			comment = "Canada rocks!";

			totalParamsSet = TOTAL_PARAMETERS;
		}
	}

	public int getTotalParametersSet() {
		return totalParamsSet;
	}
	
	public class UserRoleLocationAssignment {
		public String role;
		public String location;
		public String locationLevel;
	}
}
