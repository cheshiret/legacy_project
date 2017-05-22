/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleEditCoOwnerWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to view vehicle coowner info
 * The work flow was register a boat with co-owner info, and then view co-owner info at vehicle detail page co-owner sub table
 * @Preconditions:
 * @SPEC:View Vehicle Co-Owners
 * @Task#:Auto-1002
 * 
 * @author Jane Wang
 * @Date  Jul 3, 2012
 */
public class ViewVehicleCoOwner extends LicenseManagerTestCase {
	private OwnerInfo coOwner;
	private BoatInfo boat;
	
	public void execute() {
		lm.loginLicenseManager(login);
		//goto customer detail page and register a vehicle with coowner
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(boat);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		boat.registration.miNum = OrmsOrderSummaryPage.getInstance().getMINum();
		lm.finishOrder();
//		vehicle.registration.miNum = "";
		boat.id = lm.getVehicleIDByMiNum(boat.registration.miNum, schema);
		coOwner.id = lm.getVehicleCoOwnerID(coOwner, boat.id, schema);
		
		//go to vehicle detail page and verify coowner info
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		verifyCoOwnerInfoInList();
		verifyCoOwnerDetailsInfo();
		lm.gotoCoOwnerSubPageFromEditCoOwnerWidget();
		
		//clean up the order
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regOrdNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();		
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
			
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic00007";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic8";
		cust.fName = "QA-Basic8";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		boat = new BoatInfo();
		boat.type = "Boat";
		boat.status = OrmsConstants.ACTIVE_STATUS;
		boat.hullIdSerialNum = "BoatWithCoOwner"+DataBaseFunctions.getEmailSequence();
//		vehicle.hullIdSerialNum = "NewBoat18808";
		boat.manufacturerName = "YAMA";
		boat.modelYear = "1997";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		boat.registration.product = "REG - RegisterBoat";//just share the product register
		boat.registration.purchaseType = "Original";
		List<String> validDate = lm.registerVehicleValidDateCalc("REG", schema);
		boat.regExpiry = validDate.get(1);

		boat.operationReason = "14 - Other";
		boat.operationNote = "QA Auto Regresssion Test";	
		
		coOwner = new OwnerInfo();
		coOwner.firstName = "QA-VehCoOwner";
		coOwner.midName = "m";
		coOwner.lastName = "TEST-VehCoOwner";
		coOwner.suffix = "I";
		coOwner.dateOfBirth = "1980-1-2";
		coOwner.businessName = "B";
		coOwner.identifierType = "MS Drivers License";
		coOwner.identifierNum = "999668";
		coOwner.identifierState = "Mississippi";
		coOwner.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		coOwner.creationDateTime = DateFunctions.getToday("EEE MMM d yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
		boat.coOwners.add(coOwner);
	}
	
	/**
	 * Verify co-owner info in list
	 */
	private void verifyCoOwnerInfoInList(){
		LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage.getInstance();
		logger.info("Verify co-owner info in co-owner sub page list.");
		Boolean result = coOwnerPg.verifyVehicleCoOwnerInfoInList(coOwner);
		if(!result){
			throw new ErrorOnPageException("Co-owner info was Not correct in co-owner list. Please check log for details.");
		}
		logger.info("Co-owner info was correct in co-owner list.");
	}
	
	/**
	 * Verify co-owner detail info
	 */
	private void verifyCoOwnerDetailsInfo(){
		LicMgrVehicleEditCoOwnerWidget editPg = LicMgrVehicleEditCoOwnerWidget.getInstance();
		lm.gotoEditCoOwnerWidgetByOwnerNum(coOwner.id);
		logger.info("Verify co-owner detail info for id:"+coOwner.id);
		if(!editPg.compareOwnerDetailInfo(coOwner)){
			throw new ErrorOnPageException("Co-owner detail info was Not Correct in co-owner details page. Please check log for details.");
		}
		logger.info("Co-owner detail info was Correct in co-owner details page.");
	}
}
