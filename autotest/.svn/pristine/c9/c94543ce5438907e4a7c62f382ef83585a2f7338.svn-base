package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddFacilityFunction;
/**
 * @Description: Add facility
 * @author Phoebe
 * @Date  April 21, 2014
 */
public class AddFacility extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private FacilityData facilityInfo = new FacilityData();
	private AddFacilityFunction addFacilityFunc = new AddFacilityFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = facilityInfo;
		addFacilityFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		//Facility parameters
		facilityInfo.agency = datasFromDB.get("AGENCY");
		facilityInfo.region = datasFromDB.get("REGION");
		facilityInfo.facilityName = datasFromDB.get("FACILITY_NAME");
		facilityInfo.shortName = datasFromDB.get("SHORT_NAME");
		facilityInfo.mailingAddress = datasFromDB.get("ADDRESS");
		facilityInfo.mailingCityTown = datasFromDB.get("CITY_TOWN");
		facilityInfo.mailingState = datasFromDB.get("STATE");
		facilityInfo.mailingZipCode = datasFromDB.get("ZIPCODE");
		facilityInfo.mailingCountry = datasFromDB.get("COUNTRY");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_facility";
		ids = "10";
	}
}
