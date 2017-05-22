package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (p) Single hunt is associated with leftover privilege
 * Verify hunt info in prd details page
 * Go to shopping cart page after click add to cart button in prd details page
 * 
 * @Preconditions:
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1010
 * d_hf_add_privilege_prd:id=2250
 * d_hf_add_pricing:id=3142
 * d_hf_assi_pri_to_store:id=1500
 * d_hf_add_prvi_license_year:id=2290
 * d_hf_add_qty_control:id=1480
 * d_hf_add_hunt:id=100
 * d_hf_assign_priv_to_hunt:id=100
 * d_hf_add_hunt_license_year:id=150
 * d_hf_add_weapon:id=40
 * d_hf_add_hunt_location:id=60
 * d_hf_add_date_period:id=60
 * d_hf_add_hunt_quota:id=40
 * d_hf_add_species:id=40
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC:
 * TC101734:OTC/Leftover Privilege - Only one hunt available for selection
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 4, 2013
 */
public class SingleHuntAsscociatedAndAvailable extends HFSKWebTestCase {
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFLicensePurchaseDetailsPage licenceDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private HuntInfo hunt;
	private LocationInfo location;
	private DatePeriodInfo datePeriod;
	private String secTitle, moreDetailsTitle, mapLink;

	public void execute() {
		//Make privilege to license details page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);

		//Check point 1: check hunt info in license details page
		verifyHuntInfoInPrivDetails();

		//Check point 2: go to shopping cart page after click 'Add to Cart' button in license details page
		hf.addPrivilegeFromPrdDetailsPg(privilege, shoppingCartPg);

		//Abandon and sign out
		hf.abandonCart();
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00021@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri01_FN";
		cus.lName = "LeftoverPri01_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4156";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK LeftoverPriv001";
		privilege.licenseYear = hf.getFiscalYear(schema);

		hunt = new HuntInfo();
		hunt.setHuntCode("LH1");
		hunt.setDescription("Leftover sale hunt 01");
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
		secTitle = "Hunt Information";
		moreDetailsTitle = "More Details";
		mapLink = "Map of Hunt Location";
	}

	private void verifyHuntInfoInPrivDetails() {
		boolean result = MiscFunctions.compareString("Hunt information section title", secTitle, licenceDetailsPg.getHuntInfoSecTitle());
		result &= licenceDetailsPg.compareHuntInfo(hunt);
		result &= MiscFunctions.compareResult("Hunt quota exists", true, licenceDetailsPg.checkHuntQuotaExists());
		result &= MiscFunctions.compareString("More Details title", moreDetailsTitle, licenceDetailsPg.getHuntMoreDetailsTitle());
		result &= MiscFunctions.compareString("Hunt location map link", mapLink, licenceDetailsPg.getHuntLocMapLinkText());

		if (!result) {
			throw new ErrorOnPageException("Not all hunt infommation are correct in Product Details page. Please find details from previous logs.");
		}
	}
}
