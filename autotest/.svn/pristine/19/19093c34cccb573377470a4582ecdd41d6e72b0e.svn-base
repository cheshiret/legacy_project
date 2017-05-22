package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAvailableHuntsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) In Available hunts page, verify Privilege and related hunts info and the page control
 * 
 * @Preconditions: 
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1070
 * d_hf_add_privilege_prd:id=2300
 * d_hf_add_pricing:id=3192
 * d_hf_assi_pri_to_store:id=1550
 * d_hf_add_prvi_license_year:id=2340
 * d_hf_add_qty_control:id=1530
 * d_hf_add_hunt:id=200
 * d_hf_assign_priv_to_hunt:id=200
 * d_hf_add_hunt_license_year:id=250
 * d_hf_add_weapon:id=40
 * d_hf_add_hunt_location:id=60
 * d_hf_add_date_period:id=60
 * d_hf_add_hunt_quota:id=110
 * d_hf_add_species:id=40
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: TC100419: OTC/Leftover Privilege - Hunt Selection page details verifications
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 10, 2013
 */
public class HuntSelectionVerifications extends HFSKWebTestCase {
	private HFAvailableHuntsPage huntsPg = HFAvailableHuntsPage.getInstance();
	private HuntInfo hunt;
	private String pageTitle, privNameAndLicenseYear, listDirective, moreDetailsTitle, mapLink, listControlRegx1, listControlRegx2;
	private int totalResult;
	private LocationInfo location;
	private DatePeriodInfo datePeriod;

	public void execute() {
		//Make privilege to available hunt page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		hf.addPrivilegeFromPrdDetailsPg(privilege, huntsPg);

		//Verify Privilege and related hunts info and the page control
		verifyHuntInfoInPrivDetails();

		//Sign out
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00027@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri07_FN";
		cus.lName = "LeftoverPri07_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4162";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK LeftoverPriv006";
		privilege.licenseYear = hf.getFiscalYear(schema);

		hunt = new HuntInfo();
		hunt.setHuntCode("LH11");
		hunt.setHuntId(hf.getHuntIdByHuntCode(hunt.getHuntCode(), schema));
		hunt.setDescription("Leftover sale hunt 11");
		hunt.setSpecie("Black Bear");
		hunt.setSpecieSubType("Species Sub Type");
		hunt.setWeaponDes("Leftover Weapon");
		hunt.setWeapon(hunt.getWeaponDes());

		//Hunt location info
		location = new LocationInfo();
		location.setCode("LHL");
		location.setDescription("Leftover Hunt Location");
		location.setImage("Map of Hunt Location");
		hunt.setLocationInfo(location);
		hunt.setHuntLocationInfo(location.getCode() +"-"+ location.getDescription());

		//Hunt date period info
		datePeriod = new DatePeriodInfo();
		datePeriod.setCode("DPA");
		DatePeriodLicenseYearInfo ly = new DatePeriodLicenseYearInfo();
		ly.setLicenseYear(privilege.licenseYear);
		Dates date = ly.new Dates("Jan 01", "Dec 31", ""); 
		ly.setDates(date);
		datePeriod.setLicenseYears(ly);
		hunt.setDatePeriodInfo(datePeriod);
		hunt.setLicYear(privilege.licenseYear);
		hunt.setHuntDatePeriodInfo(datePeriod.getCode());

		//Page info
		pageTitle = "Available Hunts";
		privNameAndLicenseYear = privilege.name +"\\s+Licence Year:\\s+"+privilege.licenseYear;
		listDirective = "Please select the hunt you wish to add to cart:";
		moreDetailsTitle = "More Details";
		mapLink = "Map of Hunt Location";
		totalResult = 2;
		listControlRegx1 = "Page \\d+ Previous \\| Next Available Hunts: 1-"+totalResult+" of \\d+.*";
		listControlRegx2 = "Page \\d+ Previous \\| Next Available Hunts: "+(totalResult+1)+"-\\d+ of \\d+.*";
	}

	private void verifyHuntInfoInPrivDetails() {
		boolean result = MiscFunctions.compareString("Available Hunts page title", pageTitle, huntsPg.getPageTitle());
		result &= MiscFunctions.compareResult("Available Hunts page title description", true, huntsPg.isPageTitleDescExist());
		result &= MiscFunctions.matchString("Privilege Name",  huntsPg.getPrivileges(), privNameAndLicenseYear);
		result &= MiscFunctions.compareResult("Lisr Directive", listDirective, huntsPg.getListDirective());
		result &= huntsPg.compareHuntInfo(hunt);
		result &= MiscFunctions.compareResult("Hunt quota exists", true, huntsPg.checkHuntQuotaExists(hunt.getHuntId()));
		result &= MiscFunctions.compareString("More Details title", moreDetailsTitle, huntsPg.getHuntMoreDetailsTitle(hunt.getHuntId()));
		result &= MiscFunctions.compareString("Hunt location map link", mapLink, huntsPg.getHuntLocMapLinkText(hunt.getHuntId()));

		//Go to second page
		huntsPg.gotoNext();
		result &= MiscFunctions.compareResult("Hunt (ID:"+hunt.getHuntId()+") in second page", huntsPg.isHuntExisting(hunt.getHuntId()), false);
		result &= MiscFunctions.matchString("Second page result", huntsPg.getListControlContent(), listControlRegx2);

		//Go to previous page
		huntsPg.gotoPrevious();
		result &= MiscFunctions.compareResult("Hunt (ID:"+hunt.getHuntId()+") in first page", huntsPg.isHuntExisting(hunt.getHuntId()), true);
		result &= MiscFunctions.matchString("First page result", huntsPg.getListControlContent(), listControlRegx1);

		if (!result) {
			throw new ErrorOnPageException("Not all hunt infommation are correct in Available Hunts pag for huntID="+hunt.getHuntId()+". Please find details from previous logs.");
		}
	}
}
