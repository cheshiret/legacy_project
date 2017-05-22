/*
 * Created on Mar 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy.web;

/**
 * @author jchen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UwpMemberProfile {
	public static final int TOTAL_PARAMETERS = 16;

	public String email = "";

	public String password = "";

	public String title = "";

	public String firstName = "";

	public String initial = "";

	public String lastName = "";

	public String homePhone = "";

	public String workPhone = "";

	public String workExtension = "";

	public String organization = "";

	public String address = "";

	public String city = "";

	public String state = "";

	public String postalCode = "";

	public String country = "";

	public String optIn = "";

	private int totalParamsSet = 0;

	public UwpMemberProfile() {
	}

	public UwpMemberProfile(boolean preInit) {
		this();

		if (preInit == true) {
			email = "qa_auto${seq}@reserveamerica.com";
			password = "test123again";
			title = "Mr";
			firstName = "QA-qa_auto${seq}";
			initial = "A";
			lastName = "Tester-";
			homePhone = "905-286-6600";
			workPhone = "905-286-6600";
			workExtension = "0000";
			organization = "ReserveAmerica";
			address = "2480 meadowvale";
			city = "Mississauga";
			state = "Ontario";
			postalCode = "L5N8M6";
			country = "Canada";
			optIn = "";

			totalParamsSet = UwpMemberProfile.TOTAL_PARAMETERS;
		}
	}

	public int getTotalParametersSet() {
		return totalParamsSet;
	}
}
