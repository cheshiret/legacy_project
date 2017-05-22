package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Purchase multiple unlocked privileges and view them in Shopping cart page, payment confirmation page, order history/details page.
 * @Preconditions:
 * 1. Make sure the privileges and the hunts exists.
 * 2. Make sure the species, species sub type, the hunt location, the weapon, the hunt date period, the hunt parameters exist.
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=910
 * d_hf_add_privilege_prd:id=2170,2190
 * d_hf_add_pricing:id=3062,3082
 * d_hf_assi_pri_to_store:id=1420,1440
 * d_hf_add_prvi_license_year:id=2170,2180,2210,2220
 * d_hf_add_qty_control:id=1400,1420
 * d_hf_add_hunt:id=20,30,50,60
 * d_hf_assign_priv_to_hunt:id=20,30,50,60
 * d_hf_add_hunt_license_year:id=30,40,50,90,100
 * d_hf_add_species:id=30
 * d_hf_add_weapon:id=30
 * d_hf_add_hunt_location:id=30,50
 * d_hf_add_date_period:id=30,50
 * @SPEC:
 * Unlocked Privilege (multiple record) displaying on Shopping Cart page [TC:070820]
 * Unlocked Privilege displaying on Order History/Order Details page (My Account) [TC:068248]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 12, 2013
 */
public class Purchase_MultipleOrdItems extends HFMOUnlockedPrivTestCase {
	private PrivilegeInfo priv2, priv3;
	private HuntInfo hunt1, hunt2, hunt3, hunt4;
	private LocationInfo locWithoutSubLoc, locWithSubLoc;
	private DatePeriodInfo dateInfoWithoutCat, dateInfoWithCat;
	private HuntParameterInfo paramInfo, paramInfo2;
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private PrivilegeInfo[] privs;
	private HuntInfo[] hunts;
	
	@Override
	public void execute() {
		// Invalid all privileges
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, new String[]{privilege.licenseYear, priv2.licenseYear}, searchStatus, 
				privilege.operateReason, privilege.operateNote);
		lm.deactivateAllUnlockedPrivilege(cus);
		lm.logOutLicenseManager();	
		
		this.prepareUnlockedPriv(fileName, privs, cus.custNum, hunts);
		
		// Purchase multiply unlocked privileges and verify the order items in Shopping cart page and confirm page
		hf.invokeURL(url);
		hf.makePrivilegeOrderToCart(cus, privs, hunts, false);
		shoppingCartPg.verifyPrivOrdItemInfo(privilege, priv2, priv3);
		String ordNum = hf.checkOutCartToConfirmationPage(pay);
		confirmPg.verifyPrivOrdItemInfo(privilege, priv2, priv3);

		// Verify Hunt info in Order history page
		hf.gotoOrdHistPgFromOrdConfirmPg();
		orderHistoryPg.verifyHuntInfo(ordNum, privilege, priv2, priv3);
		
		// Verify Hunt info in Order Details page
		String licNum = hf.getPrivilegeNumByOrdNum(schema, ordNum).split(",")[0];
		hf.gotoOrderDetailsPgFromOrdHistPg(licNum, ordNum);
		ordDetailsPg.verifyHuntInfo(privilege, priv2, priv3);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv01_FN"; // d_web_hf_signupaccount, id=910
		cus.lName = "ULPriv01_LN";
		cus.dateOfBirth = "01/01/"+ DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		
		String pointTypeID = hf.getPointTypeCode(schema, "Deer");
		String currentYear = hf.getFiscalYear(schema);
		String nextYear = Integer.toString(DateFunctions.getYearAfterGivenYear(1, currentYear));
		
		// Hunt Location info
		locWithoutSubLoc = this.initializeHuntLocWithoutSubLoc();
		locWithSubLoc =this.initializeHuntLocWithSubLoc();
		
		// hunt date period - current license year, 1 date info with from and to date but without category
		dateInfoWithoutCat = this.initializeHuntDPWithoutCategory(currentYear);
		
		// hunt date period - current license year, 4 dates info: with from, to dates and category; with from and to dates; with from date and category; only with from date
		dateInfoWithCat = this.initializeHuntDPWithCategory(currentYear);
		
		// Hunt Parameters
		paramInfo = new HuntParameterInfo("hunt param001", "hunt param value001", false);
		paramInfo2 = new HuntParameterInfo("hunt param002", "hunt param value002", false);
			
		// Hunt Info: only set weapon, hunt location without sub location, 1 hunt parameter
		hunt = new HuntInfo();
		hunt.setHuntCode("H02");
		hunt.setDescription("HFMO Hunt002");
		hunt.setSpecie("DucksAuto");
		hunt.setPointTypeCode(pointTypeID);	
		hunt.setNumOfTagQty("1");
		hunt.setLicYear(currentYear);
		hunt.setWeaponCode("WP1");
		hunt.setWeaponDes("Short Gun");
		hunt.setWeapon(hunt.getWeaponCode(), hunt.getWeaponDes());
		hunt.setHuntLocationInfo(locWithoutSubLoc.getCode() + " - " + locWithoutSubLoc.getDescription());
		hunt.setLocationInfo(locWithoutSubLoc);
		hunt.setHuntParams(paramInfo);
		
		// Hunt Info: same with the first hunt, except the tag quantity
		hunt1 = new HuntInfo();
		hunt1.setHuntCode("H02");
		hunt1.setDescription("HFMO Hunt002");
		hunt1.setSpecie("DucksAuto");
		hunt1.setPointTypeCode(pointTypeID);	
		hunt1.setNumOfTagQty("2");
		hunt1.setLicYear(currentYear);
		hunt1.setWeaponCode("WP1");
		hunt1.setWeaponDes("Short Gun");
		hunt1.setWeapon(hunt1.getWeaponCode(), hunt1.getWeaponDes());
		hunt1.setHuntLocationInfo(locWithoutSubLoc.getCode() + " - " + locWithoutSubLoc.getDescription());
		hunt1.setLocationInfo(locWithoutSubLoc);
		hunt1.setHuntParams(paramInfo);
		
		// Hunt info: without any info
		hunt2 = new HuntInfo();
		hunt2.setHuntCode("H06");
		hunt2.setDescription("HFMO Hunt006");
		hunt2.setSpecie("DucksAuto");
		hunt2.setLicYear(currentYear);
		hunt2.setPointTypeCode(pointTypeID);	
		hunt2.setNumOfTagQty("1");
		
		// Hunt info: only set species sub type, date period
		hunt3 = new HuntInfo();
		hunt3.setHuntCode("H03");
		hunt3.setDescription("HFMO Hunt003");
		hunt3.setSpecie("DucksAuto");
		hunt3.setSpecieSubType("STDesB");
		hunt3.setHuntDatePeriodInfo(dateInfoWithoutCat.getCode() + " - " + dateInfoWithoutCat.getDescription());
		hunt3.setDatePeriodInfo(dateInfoWithoutCat);
		hunt3.setLicYear(currentYear);
		hunt3.setPointTypeCode(pointTypeID);	
		hunt3.setNumOfTagQty("1");
		
		// Hunt info: set all info, including species sub type, weapon, date period
		hunt4 = new HuntInfo();
		hunt4.setHuntCode("H05");
		hunt4.setDescription("HFMO Hunt005");
		hunt4.setSpecie("DucksAuto");
		hunt4.setSpecieSubType("STDesB");
		hunt4.setWeaponCode("WP1");
		hunt4.setWeaponDes("Short Gun");
		hunt4.setWeapon(hunt4.getWeaponCode(), hunt4.getWeaponDes());
		hunt4.setHuntLocationInfo(locWithSubLoc.getCode() + " - " + locWithSubLoc.getDescription());
		hunt4.setLocationInfo(locWithSubLoc);
		hunt4.setHuntDatePeriodInfo(dateInfoWithCat.getCode() + " - " + dateInfoWithCat.getDescription());
		hunt4.setDatePeriodInfo(dateInfoWithCat);
		hunt4.setLicYear(currentYear);
		hunt4.setHuntParams(paramInfo, paramInfo2);
		hunt4.setPointTypeCode(pointTypeID);	
		hunt4.setNumOfTagQty("1");
		
		// Privilege Info
		String today = DateFunctions.getToday("EEE MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		privilege.licenseYear = currentYear;
		privilege.code = "MOG";
		privilege.name = "HFMO HuntLic002";
		privilege.validFromDate = today;
		privilege.setHuntInfo(hunt, hunt1);
		
		priv2 = new PrivilegeInfo();
		priv2.code = privilege.code;
		priv2.name = privilege.name;
		priv2.licenseYear = nextYear;
		priv2.validFromDate = today;
		priv2.setHuntInfo(hunt);
		
		priv3 = new PrivilegeInfo();
		priv3.code = "MOI";
		priv3.name = "HFMO HuntLic004";
		priv3.licenseYear = privilege.licenseYear;
		priv3.validFromDate = today;
		priv3.setHuntInfo(hunt2, hunt3, hunt4);		
		
		privs = new PrivilegeInfo[] {privilege, privilege, priv2, priv3, priv3, priv3};
		hunts = new HuntInfo[] {hunt, hunt1, hunt, hunt2, hunt3, hunt4};
		
		// import file name
		fileName = "PurchaseUnlockedPri_Multiple";		
	}
}
