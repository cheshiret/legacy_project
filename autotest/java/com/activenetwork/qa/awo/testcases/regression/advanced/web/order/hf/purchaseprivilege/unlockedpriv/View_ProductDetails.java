package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: View Unlocked Privilege on product details page: 3 records for the same hunt, same privilege, 
 * and multiple license year (current, current+1, current+2)
 * 1. view unlocked privilege with full info: hunt attributes, hunt parameters, hunt location map link, 
 * 2. Update date period for one license year in LM, and view unlocked privilege in Web
 * 3. Update hunt attributes as empty for the hunt in LM, and view unlocked privilege in Web
 * 4. Update hunt location map as empty in LM and view unlocked privilege in Web
 * 5. Delete hunt parameters in LM and view unlocked privilege in Web
 * @Preconditions:
 * Make sure the privilege and the hunt have 3 license year records
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=880
 * d_hf_add_privilege_prd:id=2160
 * d_hf_add_pricing:id=3052
 * d_hf_assi_pri_to_store:id=1410
 * d_hf_add_prvi_license_year:id=2140,2150,2160
 * d_hf_add_qty_control:id=1390
 * d_hf_add_hunt:id=40
 * d_hf_assign_priv_to_hunt:id=40
 * d_hf_add_hunt_license_year:id=60,70,80
 * d_hf_add_species:id=30
 * d_hf_add_weapon:id=30
 * d_hf_add_hunt_location:id=40
 * d_hf_add_date_period:id=40

 * @SPEC: Unlocked Privilege displaying on product details page [TC:068246]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 6, 2013
 */
public class View_ProductDetails extends HFMOUnlockedPrivTestCase {
	private PrivilegeInfo priv2, priv3;
	private LocationInfo location;
	private DatePeriodInfo dateInfo;
	private List<DatePeriodLicenseYearInfo> licenseYears;
	private DatePeriodLicenseYearInfo ly1, ly2, ly3;
	private HuntParameterInfo paramInfo, paramInfo2;
	private HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private String secTitle, moreDetailsTitle, additInfoTitle, mapLink;
	private Dates date1, date1_2, date2, date3;
	@Override
	public void execute() {
		this.prepareUnlockedPriv(fileName, new PrivilegeInfo[] {privilege, priv2, priv3}, cus.custNum, hunt);
		
		//1. Update Hunt settings in LM to make sure the location has sub location and location image; date period has 3 LY; hunt has full settings
		this.updateSetupInLM(true, true, true, true, true, privilege.licenseYear, priv2.licenseYear, priv3.licenseYear);
		
		// Verify the unlocked privilege in Product Details page in Web
		hunt.setSpecieSubType("STDescA"); // only displayed sub type description in Web
		this.changeDatePeriodFormat();
		this.checkUnlockedPrivInWeb();
		
		//2. Update the hunt in LM to empty sub location info and location image, and remove date period other 2 LY records
		location.setImage(StringUtil.EMPTY);
		location.setSubLocations(null);
		licenseYears.remove(ly2);
		licenseYears.remove(ly3);
		this.updateSetupInLM(true, false, false, false, false, priv2.licenseYear, priv3.licenseYear);
		
		// Check in Web
		this.checkUnlockedPrivInWeb();
		
		//3. Update the hunt in LM to empty hunt components and hunt parameters settings
		hunt.setSpecieSubType(StringUtil.EMPTY);
		hunt.setHuntLocationInfo(StringUtil.EMPTY);
		hunt.setWeapon(StringUtil.EMPTY);
		hunt.setHuntDatePeriodInfo(StringUtil.EMPTY);
		hunt.getHuntParams().remove(paramInfo);
		hunt.getHuntParams().remove(paramInfo2);
		hunt.getLocationInfo().setImage(StringUtil.EMPTY);
		this.updateSetupInLM(false, false, true, true, false, new String[0]);

		// Check in Web
		this.checkUnlockedPrivInWeb();
	}

	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode04_FN"; // d_web_hf_signupaccount, id=880
		cus.lName = "IdentMode04_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.code = "MOF";
		privilege.name = "HFMO HuntLic001";

		priv2 = new PrivilegeInfo();
		priv2.code = privilege.code;
		priv2.name = privilege.name;
		priv2.licenseYear = String.valueOf(DateFunctions.getYearAfterGivenYear(1, privilege.licenseYear)); //Integer.toString(DateFunctions.getYearAfterCurrentYear(1));
		
		priv3 = new PrivilegeInfo();
		priv3.code = privilege.code;
		priv3.name = privilege.name;
		priv3.licenseYear = String.valueOf(DateFunctions.getYearAfterGivenYear(2, privilege.licenseYear)); //Integer.toString(DateFunctions.getYearAfterCurrentYear(2));
		
		// Hunt Location info
		location = new LocationInfo();
		location.setCode("HL2");
		location.setDescription("Hunt Loc002");
		location.setSpecie("DucksAuto");
		location.setLongDescription("Hunt Loc002 for automation testing");
		location.setImage("HuntLocImg_PrdDetailsTest.png");
		location.setLocationImgFilePath((AwoUtil.getProjectPath() + casePath + "/" + location.getImage()).replace("/", "\\"));
		
		LocationInfo.SubLocation subLocation = new LocationInfo.SubLocation("SubLoc Category1", "Sub Location Value1");
		LocationInfo.SubLocation subLocation2 = new LocationInfo.SubLocation("SubLoc Category2", "Sub Location Value2");
		List<LocationInfo.SubLocation> subLocations = new ArrayList<LocationInfo.SubLocation>();
		subLocations.add(subLocation);
		subLocations.add(subLocation2);
		location.setSubLocations(subLocations);
		
		// hunt date period
		dateInfo = new DatePeriodInfo();
		dateInfo.setCode("DP2");
		dateInfo.setDescription("Date Period 002");
		
		ly1 = new DatePeriodLicenseYearInfo(); // current license year, 2 dates info, one with category, one without
		ly1.setLicenseYear(privilege.licenseYear);
		date1 = ly1.new Dates(DateFunctions.getDateAfterToday(1), DateFunctions.getDateAfterToday(10), "date period category");
		date1_2 = ly1.new Dates(DateFunctions.getDateAfterToday(20), DateFunctions.getDateAfterToday(30), StringUtil.EMPTY);
		List<Dates> dates = new ArrayList<Dates>();
		dates.add(date1);
		dates.add(date1_2);
		ly1.setDates(dates);
		
		ly2 = new DatePeriodLicenseYearInfo();
		ly2.setLicenseYear(priv2.licenseYear);
		date2 = ly2.new Dates(DateFunctions.getDateAfterToday(2), StringUtil.EMPTY, "date period category");
		List<Dates> dates2 = new ArrayList<Dates>();
		dates2.add(date2);
		ly2.setDates(dates);
		
		ly3 = new DatePeriodLicenseYearInfo();
		ly3.setLicenseYear(priv3.licenseYear);
		List<Dates> dates3 = new ArrayList<Dates>();
		date3 = ly3.new Dates(DateFunctions.getDateAfterToday(3), StringUtil.EMPTY, StringUtil.EMPTY);
		dates3.add(date3);
		ly3.setDates(dates3);
		
		licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(ly1);
		licenseYears.add(ly2);
		licenseYears.add(ly3);
		dateInfo.setLicenseYears(licenseYears);

		// Hunt Parameters
		paramInfo = new HuntParameterInfo("hunt hour", "8am - 11am", false);
		paramInfo2 = new HuntParameterInfo("hunt hour2", "2pm - 4pm", false);
		
		// Hunt Info: hunt LY, hunt code
		hunt = new HuntInfo();
		hunt.setHuntCode("H04");
		hunt.setDescription("HFMO Hunt004");
		hunt.setSpecie("DucksAuto");
		hunt.setSpecieSubType("STA - STDescA");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
		hunt.setHuntLocationInfo(location.getCode() + "-" + location.getDescription());
		hunt.setWeaponCode("WP1");
		hunt.setWeaponDes("Short Gun");
		hunt.setWeapon(hunt.getWeaponCode(), hunt.getWeaponDes());
		hunt.setHuntDatePeriodInfo(dateInfo.getCode() + " - " + dateInfo.getDescription());
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntParams(paramInfo2, paramInfo);
		hunt.setDatePeriodInfo(dateInfo);
		hunt.setLocationInfo(location);
		hunt.setLicYear(privilege.licenseYear);
		
		// Login Info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
		
		// import file name
		fileName = "ViewUnlockedPri_PrdDetails";
		
		// page info
		secTitle = "Hunt Information";
		moreDetailsTitle = "More Details";
		additInfoTitle = "Additional Information";
		mapLink = "Map of Hunt Location";
	}
	
	private void updateSetupInLM(boolean editLocation, boolean addDPLY, boolean editHunt, boolean deactivateHuntParam, 
			boolean addHuntParam, String... removedDPLY) {
		lm.loginLicenseManager(loginLM);
		if (editLocation)
			lm.editHuntLocation(location);
		
		lm.gotoDatePeriodListPg();
		lm.gotoDatePeriodDetailPgFromListPg(dateInfo.getCode());
		if (removedDPLY != null && removedDPLY.length > 0)
			lm.deactivateDatePeriodLicenseYear(removedDPLY);
		if (addDPLY)
			lm.addDatePeriodLicenseYears(dateInfo.getLicenseYears());
		
		if (editHunt) {
			lm.gotoHuntsListPg();
			lm.editHuntDetailsFromHuntsListPg(hunt);
		}
		
		lm.gotoHuntParametersListPg(hunt.getHuntCode());
		if (deactivateHuntParam) {
			lm.deactivateHuntParameters(paramInfo.getHuntParamDes(), paramInfo2.getHuntParamDes());
		} 
		if (addHuntParam) {
			lm.addHuntParameters(paramInfo, paramInfo2);
		}
		
		lm.logOutLicenseManager();
	}
	
	private void checkUnlockedPrivInWeb() {
		hf.invokeURL(url);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege, hunt, false);
		hunt.setLicYear(privilege.licenseYear);
		this.verifyUnlockedPrivDetails();
		if (StringUtil.notEmpty(location.getImage()))
			this.verifyHuntLocMapLink();
		
		hf.makePrivilegeOrderToLicenseDetailPg(cus, priv2, hunt, true); // check the display of the same privilege, same hunt but different license year
		hunt.setLicYear(priv2.licenseYear);
		this.verifyUnlockedPrivDetails();	
		
		hf.makePrivilegeOrderToLicenseDetailPg(cus, priv3, hunt, true); // check the display of the same privilege, same hunt but different license year
		hunt.setLicYear(priv3.licenseYear);
		this.verifyUnlockedPrivDetails();	
		hf.signOut();
	}
	
	private void verifyUnlockedPrivDetails() {
		boolean result = true;
		result &= MiscFunctions.compareString("Hunt information section title", secTitle, prdDetailsPg.getHuntInfoSecTitle());
		boolean locImgExist = hunt.getLocationInfo() != null && StringUtil.notEmpty(hunt.getLocationInfo().getImage());
		if (hunt.getHuntParams() != null && hunt.getHuntParams().size() > 0 || locImgExist) {
			result &= MiscFunctions.compareString("More Details title", moreDetailsTitle, prdDetailsPg.getHuntMoreDetailsTitle());
			result &= MiscFunctions.startWithString("Additional Info title", prdDetailsPg.getHuntMoreDetails(), additInfoTitle);
		}
		if (locImgExist) {
			result &= MiscFunctions.compareString("hunt location map link", mapLink, prdDetailsPg.getHuntLocMapLinkText());
		}
		result &= prdDetailsPg.compareHuntInfo(hunt);
		
		if (!result) {
			throw new ErrorOnPageException("Unlocked Privilege Details is wrong in Product Details page!");
		}
		logger.info("---Successfully verify unlocked privilege details!");
	}
	
	private void verifyHuntLocMapLink() {
		boolean result = true;
		// before click, no image popup shown
		if (true == prdDetailsPg.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown before click the image link.");
		}		
		// click image link and image popup shown
		prdDetailsPg.clickHuntMoreInfoSpan();
		prdDetailsPg.clickHuntImageMapLink();
		if (false == prdDetailsPg.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should be shown after click the image link.");
		}
		if (false == prdDetailsPg.isHuntImageExist(location.getImage())) {
			result = false;
			logger.error("hunt location image should exist:" + location.getImage());
		}
		// close image popup
		prdDetailsPg.closeHuntImagePop();
		if (true == prdDetailsPg.isHuntImagePopDisplayed()) {
			result = false;
			logger.error("hunt location image pop should not be shown after close it.");
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Location Image link is wrong in Product Details page!");
		}
		logger.info("---Successfully verify Hunt Location Image link!");
	}
	
	private void changeDatePeriodFormat() {
		date1.setFromDate(DateFunctions.formatDate(date1.getFromDate(), "MMM dd"));
		date1.setToDate(DateFunctions.formatDate(date1.getToDate(), "MMM dd"));
		date1_2.setFromDate(DateFunctions.formatDate(date1_2.getFromDate(), "MMM dd"));
		date1_2.setToDate(DateFunctions.formatDate(date1_2.getToDate(), "MMM dd"));
		date2.setFromDate(DateFunctions.formatDate(date2.getFromDate(), "MMM dd"));
		date3.setFromDate(DateFunctions.formatDate(date3.getFromDate(), "MMM dd"));
	}
}
