package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.camping.preregistration;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPreRegistrationListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * 1.x_prop table has two new properties:PreCheckinPeriod and PreCheckinAllowedWithDiscountProof
 * 2.PreCheckinPeriod , value=7 days PreCheckinAllowedWithDiscountProof =false
 * 3.This reservation shouldn't be shown in the pre check in reservations list.
 * Go to Inventory manager-> Choose park -> Under facility attributes-> Field Setting find Allow Pre-Check In. Setup it to No. 
 * Make a reservation for this park with arrival date within 7 days and 
 * Go to Pre Check In option under 'My reservations account' tab on the web site that supports pre check in
 * 4.The previously made reservation will be shown
 * Go back to Inventory manager - Change Allow Pre-Chech In option from No to Yes. 
 * Go to Pre Check In reservation list on the web.
 * @Preconditions:
 * @SPEC: Verify Pre Check In option for the park [TC:001775]
 * @Task#: AUTO-681,AUTO-2176(Update updatePreCheckInStatusID method)
 * 
 * @author SWang
 * @Date  Aug 15, 2011, May 20, 2014
 */
public class VerifyPreCheckInOption extends WebTestCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginIM = new LoginInfo();
	private String pageName = "Pre-Registration";

	public void execute() {
		//Check PreCheckinPeriod(value=7 days)and PreCheckinAllowedWithDiscountProof(false)in DB
		this.checkEnvironmentSetUp();

		//Login inventory manager and set 'Allow Pre-Check In' as No
		im.loginInventoryManager(loginIM);
		this.updateAllowPreCheckIn("No");
		im.logoutInvManager();

		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		//Make advanced reservation within 7 days
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		String resNum = web.checkOutCart(pay);
		
		//Verify this reservation can't display in Pre-Registration
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection(pageName);
		this.checkResNumInPreRegistrationPg(new String[]{resNum}, new boolean[]{false});

		//Login inventory manager and set 'Allow Pre-Check In' as Yes
		im.loginInventoryManager(loginIM);
		this.updateAllowPreCheckIn("Yes");
		im.logoutInvManager();
		
		//Verify this reservation display in Pre-Registration
		im.updatePreCheckInStatusID(schema, resNum, "1");
		
		web.invokeURL(url);
		web.signIn(email, pw);
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection(pageName);
		this.checkResNumInPreRegistrationPg(new String[]{resNum}, new boolean[]{true});

		//Cancel reservation
		String status = "Confirmed";
		web.gotoResDetailFromAccount(resNum, bd.contractCode, status);
		web.cancelReservation(pay);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		//Parameters for Inventory Manager
		loginIM.url = AwoUtil.getOrmsURL(env);
		loginIM.userName = TestProperty.getProperty("orms.im.user");
		loginIM.password = TestProperty.getProperty("orms.im.pw");
		loginIM.contract = "SC Contract";
		loginIM.location = "Administrator/DREHER ISLAND";

		//Parameters for Web
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "SC";

		bd.state = "South Carolina";
		bd.park = "DREHER ISLAND";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(2);
		bd.lengthOfStay = "2";
		bd.contractCode = "SC";
		bd.loop = "Camping Area A";
		bd.siteNo = "003";
		bd.siteID = "2332";
		
	}

	private void checkEnvironmentSetUp(){
		if(!web.getPreCheckinPeriod(schema).equals("7")){
			throw new ErrorOnDataException("The default value of 'PreCheckinPeriod' should be 7.");
		}
		if(!web.getPreCheckinAllowedWithDiscountProof(schema).equals("false")){
			throw new ErrorOnDataException("The default value of 'PreCheckinAllowedWithDiscountProof' should be false.");
		}	
	}

	private void updateAllowPreCheckIn(String expectValue){
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
		.getInstance();
		inFacilityDetailPg.selectAllowPreCheckIn(expectValue);
		inFacilityDetailPg.clickApply();
		inFacilityDetailPg.waitLoading();
	}

	private void checkResNumInPreRegistrationPg(String[] resNums, boolean[] existed){
		UwpPreRegistrationListPage preCheckInPg = UwpPreRegistrationListPage.getInstance();
		for(int i=0; i<resNums.length; i++){
			preCheckInPg.checkResNumsExist(resNums[0], existed[0]);
		}
	}
}
