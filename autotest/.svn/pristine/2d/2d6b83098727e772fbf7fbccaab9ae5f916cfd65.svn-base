package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.preregistration;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPreRegistrationListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1.Check PreCheckinPeriod(value=7 days)and PreCheckinAllowedWithDiscountProof(false)in DB
 * 2.Login inventory manager and set 'Allow Pre-Check In' as Yes
 * 3.Make three reservation
 *   Res 1: arrival date with '< system date+7'and hasn't discount
 *   Res 2: arrival date with '<=system date+7'and has discount
 *   Res 3: arrival date with '> system date+7'and hasn't discount
 * 4.Update PreCheckInStatusID In DB according to PreCheckinPeriod = 7 and PreCheckinAllowedWithDiscountProof = false
 * 	 Verify reservation with arrival date<=system date+7 in Pre-Registration list 
 * 5.Update PreCheckInStatusID In DB according to PreCheckinPeriod = 5 and PreCheckinAllowedWithDiscountProof = false
 * 	 Verify reservation with arrival date<=system date+5 in Pre-Registration list 
 * 6.Update PreCheckInStatusID In DB according to PreCheckinPeriod = 7 and PreCheckinAllowedWithDiscountProof = true
 * 	 Verify reservation with arrival date<=system date+7 and with discount in Pre-Registration list 
 * @Preconditions:
 * Need have an discount fee schedule
 * @SPEC: Verify eligible for pre check in reservations options [TC:001772]
 * @Task#: AUTO-681
 * 
 * @author SWang
 * @Date  Aug 16, 2011
 */
public class VerifyEligiblePreCheckInOptions extends WebTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIM = new LoginInfo();
	private InventoryInfo inventory = new InventoryInfo();
	private boolean[] resWithDiscount = new boolean[]{false, true, false};
	private String[] arrivalDates = new String[3];
	private String[] resNums = new String[3]; 
	private TimeZone timeZone;

	public void execute() {
		//Check PreCheckinPeriod(value=7 days)and PreCheckinAllowedWithDiscountProof(false)in DB
		this.checkEnvironmentSetUp();

		//Login inventory manager and set 'Allow Pre-Check In' as Yes
		im.loginInventoryManager(loginIM);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		this.updateAllowPreCheckIn("Yes");
		im.logoutInvManager();

		web.checkAndReleaseInventory(schema, bd.arrivalDate, 10, false, bd.siteIDs);
		//Make the first reservation with arrival date < system date+7 without discount
	
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		this.verifyFeesInCart(resWithDiscount[0]);
		resNums[0] = web.checkOutCart(pay);
		arrivalDates[0] = bd.arrivalDate;

		//Make the second reservation with arrival date = system date+7 with discount
		bd.arrivalDate = DateFunctions.getDateAfterToday(7, timeZone);
		bd.siteNo = "011";
		bd.siteID = bd.siteIDs[1];
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		web.fillInOrderDetails(od, bd.contractCode, false, true, false, "");
		this.verifyFeesInCart(resWithDiscount[1]);
		resNums[1] = web.checkOutCart(pay);
		arrivalDates[1] = bd.arrivalDate;

		//Make the third reservation with arrival date > system date+7 without discount
		bd.arrivalDate = DateFunctions.getDateAfterToday(8, timeZone);
		bd.siteNo = "012";
		bd.siteID = bd.siteIDs[2];
		web.makeReservationToCart(bd);
		this.verifyFeesInCart(resWithDiscount[2]);
		resNums[2] = web.checkOutCart(pay);
		arrivalDates[2] = bd.arrivalDate;

		//Verify only the first reservation are in Pre-Registration list
		//with PreCheckinPeriod=7 and PreCheckinAllowedWithDiscountProof = false
		this.checkResInfoInPreRegistrationPg(new boolean[]{true, false, false});

		//Verify only the first reservation is in Pre-Registration list
		//with PreCheckinPeriod=5 and PreCheckinAllowedWithDiscountProof = false
		im.updatePreCheckinPeriod(schema, "10");
		this.checkResInfoInPreRegistrationPg(new boolean[]{true, false, true});

		//Verify the first and second and third reservations are in Pre-Registration list, 
		//with PreCheckinPeriod=7 and PreCheckinAllowedWithDiscountProof = true
		im.updatePreCheckinPeriod(schema, "7");
		im.updatePreCheckinAllowedWithDiscountProof(schema, "true");
		this.checkResInfoInPreRegistrationPg(new boolean[]{false, true, false});

		im.updatePreCheckinAllowedWithDiscountProof(schema, "false");
		//Cancel reservation
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.cancelReservation(resNums, bd.contractCode, status, pay);
		web.signOut();

	}

	public void wrapParameters(Object[] param) {
		//Parameters for Inventory Manager
		loginIM.url = AwoUtil.getOrmsURL(env);
		loginIM.userName = TestProperty.getProperty("orms.im.user");
		loginIM.password = TestProperty.getProperty("orms.im.pw");
		loginIM.contract = "NY Contract";
		loginIM.location = "Administrator/Contract";

		//Parameters for Web
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		inventory.facilityID = "322";
		timeZone = DataBaseFunctions.getContractTimeZone(schema, inventory.facilityID);
		inventory.facilityName = DataBaseFunctions.getFacilityName(inventory.facilityID, schema); //"THOMPSONS LAKE STATE PARK";
		
		bd.siteIDs = new String[]{"249931","249932","249933"};
		bd.state = "New York";
		bd.park = inventory.facilityName;//"THOMPSONS LAKE STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5, timeZone);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";
		bd.loop = "Loop A";
		bd.siteNo = "010";
		bd.siteID = bd.siteIDs[0];
		
		od.promoCode = "12345"; //"ACCESS PASS"; //"12345";
	}

	private void checkEnvironmentSetUp(){
		if(!web.getPreCheckinPeriod(schema).equals("7")){
			throw new ErrorOnDataException("The default value of 'PreCheckinPeriod' should be 7.");
		}
		if(!web.getPreCheckinAllowedWithDiscountProof(schema).equals("false")){
			throw new ErrorOnDataException("The default value of 'PreCheckinAllowedWithDiscountProof' should be false.");
		}	
	}

	private void verifyFeesInCart(boolean exist) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		shoppingCart.checkFeeTypeExist("Access Pass", exist);
	}

	private void updatePreCheckInStatusIDsInDB(String[] resNums, String[] arrivaldates, boolean[] resWithDiscount){
		for(int i=0; i<resNums.length; i++){
			im.updatePreCheckInStatusID(schema, resNums[i], arrivaldates[i], resWithDiscount[i]);
		}
	}

	private void updateAllowPreCheckIn(String expectValue){
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
		.getInstance();
		inFacilityDetailPg.selectAllowPreCheckIn(expectValue);
		inFacilityDetailPg.clickApply();
		inFacilityDetailPg.waitLoading();
	}

	private void checkResInfoInPreRegistrationPg(boolean[] existed){
		UwpPreRegistrationListPage preCheckInPg = UwpPreRegistrationListPage.getInstance();

		this.updatePreCheckInStatusIDsInDB(resNums, arrivalDates, resWithDiscount);

		//Go to Pre-Registration list page
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection("Pre-Registration");

		//Check reservation numbers
		for(int i=0; i<resNums.length; i++){
			preCheckInPg.checkResNumsExist(resNums[i], existed[i]);
		}
	}
}