package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.OfficerInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddOfficerFunction;
/**
 * @Description:This is for set up officer in license manager, it including two method:add officer, assign badge to officer
 * @Preconditions:If a badge need to be assigned to the officer, then the badge must be added first:AddBadge.java
 * @author pchen
 * @Date  Dec 03, 2012
 */
public class AddOfficer extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private OfficerInfo officer = new OfficerInfo();
	private AddOfficerFunction addOfficerFunc = new AddOfficerFunction();
	private boolean needAddNewOfficer;
	private boolean needAssignBadge;

	@Override
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = officer;
		args[2] = needAddNewOfficer;
		args[3] = needAssignBadge;
		addOfficerFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		needAddNewOfficer = Boolean.parseBoolean(datasFromDB.get("needaddnewofficer"));
		officer.firstName = datasFromDB.get("fname");
		officer.middleName = datasFromDB.get("mname");
		officer.lastName = datasFromDB.get("lname");
		officer.birthday = datasFromDB.get("dateofbirth");
		officer.address = datasFromDB.get("address");
		officer.zipCode = datasFromDB.get("zippostal");
		officer.country = datasFromDB.get("country");
		officer.supplementalAddress = datasFromDB.get("supplementaladdress");
		officer.cityTown = datasFromDB.get("citytown");
		officer.state = datasFromDB.get("state");
		officer.county = datasFromDB.get("county");
		officer.phone = datasFromDB.get("phone");
		officer.email = datasFromDB.get("email");
		
		needAssignBadge = Boolean.parseBoolean(datasFromDB.get("needassignbadge"));
		officer.badge.badgeNum = datasFromDB.get("badgenum");
		officer.badge.district = datasFromDB.get("district");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_officer";
		this.queryDataSql = "";
	}

}
