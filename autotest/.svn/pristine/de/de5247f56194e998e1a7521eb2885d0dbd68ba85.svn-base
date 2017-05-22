package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolFacilityPhotosPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolProductPhotosPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify result page after click "See Facility Details" and "See Entrance Detail" link 
 * @Preconditions: 
 * d_add_new_users, id=10
 * d_assign_user_roles, id=300
 * @SPEC: 
 * Click link of "See Facility Details" [TC:020491] 
 * Click Link of "See Site Detail" or "See Entrance Detail" or "See Tour Detail" [TC:020493] 
 * @Task#: AUTO-1542
 * 
 * @author SWang
 * @Date  Apr 11, 2013
 */
public class SeeFacilityAndEntranceDetail extends PhotoToolTestCase {
	private PhotoToolFacilityPhotosPage campgroundPhotosPg = PhotoToolFacilityPhotosPage.getInstance();
	private PhotoToolProductPhotosPage productPhotoPg = PhotoToolProductPhotosPage.getInstance();
	private String selectContract;

	public void execute() {
		//Login in with a valid account
		pt.invokeURL(url);
		pt.signIn(login);

		//Goto facility photo page, then click "See Facility Details" link to facility details page
		pt.gotoFacilityListPage(selectContract);
		pt.gotoFacilityPhotosPage(facility.contract, stateFilter, letterFilter, facility.facilityID);
		campgroundPhotosPg.clickSeeFacilityDetailsLink();
		pt.crossOverVerification(pt.PAGETITLE_FACILITY_DETAILS_PAGE, facility.facilityName);

		//Go to product photo page, then click "See Entrance Details" link to entrance details page
		pt.gotoPrdPhotosPgFromFacilityPhotosPage(prdID);
		productPhotoPg.clickSeeEntranceDetailsLink();
		pt.crossOverVerification(pt.PAGETITLE_ENTRANCE_DETAILS_PAGE, prdName);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		selectContract = "ID";
		facility.contract = "NRSO";
		stateFilter = "CA";
		letterFilter = "I";
		facility.facilityID = "72203"; //Inyo
		facility.facilityName = DataBaseFunctions.getFacilityName(facility.facilityID, schema);

		prdID = "315490";
		prdName = DataBaseFunctions.getSiteName(prdID, schema);
	}
}
