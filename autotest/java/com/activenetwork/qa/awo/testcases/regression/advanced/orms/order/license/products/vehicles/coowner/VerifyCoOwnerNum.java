package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.coowner;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleAddCoOwnerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleViewPreviousCoOwnersWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case was designed to verify change 'ID' to 'Co-Owner #' in co-owner subpage 
 * @Preconditions:
 * @SPEC: TC038225
 * @Task#: Auto-977
 * 
 * @author Jane Wang
 * @Date  Mar 30, 2012
 */
public class VerifyCoOwnerNum extends LicenseManagerTestCase {
	private LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage.getInstance();
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private OwnerInfo coOwner, coOwner1;
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		//Register a vehicle with co-owner info
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		vehicle.hullIdSerialNum = ordSummaryPg.getMINum();
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.hullIdSerialNum);
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		//Verify co-owner info in co-owner sub page
		verifyCoOwnerInList(vehicle.coOwners.get(0));
		
		//Remove co-owner, and verify warning message
		String msg = lm.removeCoOwnerFromVehicleByID(coOwner.id, true);
		verifyRemoveMsg(msg);
		
		lm.gotoViewPreviousCoOwnerFromCoOwnerSubPg();
		//Verify removed co-owner displayed in previous co-owner sub page
		verifyCoOwnerInPreviousCoOwnerWidget();
		
		//Verify co-owner num display in add co-owner widget
		verifyCoOwnerNumDisplayInAddCoOwnerWidget();
		//Add new co-owner
		lm.addCoOwnerFromCoOwnerSubPg(coOwner1);
		//Verify new co-owner in co-owner sub page
		verifyCoOwnerInList(coOwner1);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		cust.dateOfBirth = "1984-6-5";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "123444";
		cust.identifier.country = "Canada";
		cust.fName = "QA-Coo";
		cust.lName = "TEST-CoOwner";
		cust.customerClass = "Individual";
		
//		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "coo"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "STEEL";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "SAIL";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		RegistrationInfo registration = new RegistrationInfo();
		registration.product = "tta - advTAN";
		vehicle.registration= registration;
		
		coOwner = new OwnerInfo();
		coOwner.firstName = "QA-CoOwnerTest0";
		coOwner.midName = "m";
		coOwner.lastName = "TEST-CoOwnerTest0";
		coOwner.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		coOwner.creationDateTime = DateFunctions.getToday("EEE MMM dd yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
		vehicle.coOwners.add(coOwner);
		
		coOwner1 = new OwnerInfo();
		coOwner1.firstName = "QA-CoOwnerTest1";
		coOwner1.lastName = "TEST-CoOwnerTest1";
		coOwner1.dateOfBirth = "1980-1-2";
		coOwner1.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		coOwner1.creationDateTime = DateFunctions.getToday("EEE MMM dd yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
	}
	
	/**
	 * Verify co-owner num display in add co-owner widget
	 */
	private void verifyCoOwnerNumDisplayInAddCoOwnerWidget(){
		LicMgrVehicleAddCoOwnerWidget addCoOwnerPg = LicMgrVehicleAddCoOwnerWidget.getInstance();
		
		coOwnerPg.clickAddCoOwner();
		ajax.waitLoading();
		addCoOwnerPg.waitLoading();
		
		addCoOwnerPg.verifyCoOwnerNumDisplay();
		addCoOwnerPg.clickCancel();
		ajax.waitLoading();
		coOwnerPg.waitLoading();
	}

	/**
	 * Verify co-owner num display in co-owner sub page
	 * Verify co-owner num display in edit co-owner widget
	 * Verify co-owner detail info in widget
	 * @param coOwner
	 */
	private void verifyCoOwnerInList(OwnerInfo coOwner){
		LicMgrVehicleEditCoOwnerWidget editPg = LicMgrVehicleEditCoOwnerWidget.getInstance();
		
		logger.info("Verify add co-owner success or not...");
		coOwner.id = coOwnerPg.getCoOwnerNumByCoOwnerInfo(coOwner);
		if(null == coOwner.id){
			throw new ErrorOnPageException("Could not find co-owner number on co-owner sub page");
		}
		
		lm.gotoEditCoOwnerWidgetByOwnerNum(coOwner.id);
		
		editPg.verifyCoOwnerNumDisplay();
		logger.info("Compare co-owner detail info for id:"+coOwner.id);
		if(!editPg.compareOwnerDetailInfo(coOwner)){
			throw new ErrorOnPageException("Co-owner detail info are Not Correct.");
		}
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
	}
	
	/**
	 * Verify warning message when remove co-owner 
	 */
	private void verifyRemoveMsg(String msg){
		String expectMsg = "Are you sure you wish to remove the Co-Owner with Co-Owner # \""+coOwner.id+"\" from the Boat ?";
		if(!msg.equals(expectMsg)){
			logger.error("The confirmation messge was wrong when remove co-owner");
			throw new ErrorOnPageException("Warning meg was wrong.", expectMsg, msg);
		}
		logger.info("Warning message displayed correctly when remove co-owner from vehicle");
	}
	
	/**
	 * Verify removed co-owner has been displayed in previous co-owner widget
	 */
	private void verifyCoOwnerInPreviousCoOwnerWidget(){
		LicMgrVehicleViewPreviousCoOwnersWidget preCoOwnerPg = LicMgrVehicleViewPreviousCoOwnersWidget.getInstance();

		preCoOwnerPg.verifyCoOwnerNumDisplay();
		
		logger.info("Compare co-owner detail info for id:"+coOwner.id);
		OwnerInfo ownerInfoUI = preCoOwnerPg.getPreviousCoOwnerInfoById(coOwner.id);
		if(null == ownerInfoUI){
			throw new ErrorOnDataException("Could not find co-owner info by id:"+coOwner.id);
		}
		if(!preCoOwnerPg.compareCoOwnerDetailInfo(coOwner, ownerInfoUI)){
			throw new ErrorOnPageException("Co-owner detail info are Not Correct in view previous co-owner page.");
		}
		logger.info("Verify co-owner detail info successfully in view previous co-owner page.");
		preCoOwnerPg.clickOK();
		coOwnerPg.waitLoading();
	}
}
