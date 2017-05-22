package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title.lien.boat;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Release lien in lien list page.
 * @Preconditions:Boat for register(VFA) and title(TFL) should be exist.
 * @SPEC:Release Lien.UCS
 * @Task#:Auto-1016
 * 
 * @author nding1
 * @Date  Aug 1, 2012
 */
public class Release extends LicenseManagerTestCase {
	private boolean result = true;
	private BoatInfo boat = new BoatInfo();
	private LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// register vehicle
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderCartToOrderSummaryPage(pay, false);
		OrmsOrderSummaryPage orderSummaryPage = OrmsOrderSummaryPage.getInstance();
		boat.registration.miNum = orderSummaryPage.getMINum();
		String regisOrderNum = orderSummaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		// title vehicle
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(boat);
		lm.processOrderToOrderSummary(pay);
		boat.title.titleNum = orderSummaryPage.getTitleNum();
		String titleOrderNum = orderSummaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();

		// go to title detail page
		lm.gotoVehicleTitleDetailPage(boat.title.titleNum);
		
		// go to lien list page
		lm.gotoVehicleLienListPage();
		boat.title.lienInfo.setLienId(lienListPg.getActiveLienId());
		
		boat.title.lienInfo.setStauts(RELEASE_STATUS);
		// release lien
		lm.releaseVehicleLien(boat.title.lienInfo.getDateOfRelease());
		
		// verify status
		result &= lienListPg.verifyLienInfo(boat.title.lienInfo);
		
		// clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regisOrderNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(titleOrderNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		
		if(!result){
			throw new ErrorOnPageException("---Not each field of lien info is correct. Please check log above.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);

		// lien info
		LienInfo lien = new LienInfo();
		lien.setDateOfLien(DateFunctions.getToday(timeZone));
		lien.setStauts(ACTIVE_STATUS);
		lien.setDateOfRelease(DateFunctions.getToday(timeZone));
		lien.setLienAmount("75");
		
		LienCompanyDetailsInfo lienCompanyDetailsInfo = new LienCompanyDetailsInfo();
		lienCompanyDetailsInfo.isAddNew = true;
		lienCompanyDetailsInfo.isSameAsAbove = false;
		lienCompanyDetailsInfo.lienCompanyName = "Addliencompany"+DateFunctions.getCurrentTime();
		lienCompanyDetailsInfo.address = "Software park";
		lienCompanyDetailsInfo.city = "Xian";
		lienCompanyDetailsInfo.state = "Mississippi";
		lienCompanyDetailsInfo.zip = "65412";
		lienCompanyDetailsInfo.country = "United States";
		lienCompanyDetailsInfo.contactPhone = "102030605";
		lien.setLienCompanyDetailsInfo(lienCompanyDetailsInfo);
		
		// boat info for registration
		boat.hullIdSerialNum = "AddLien" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "YAMA";
		boat.manufacturerPrintName = boat.manufacturerName;
		boat.modelYear = DateFunctions.getCurrentYear()+"";
		boat.feet = "24";
		boat.inches = "9";
		boat.hullMaterial = "Steel";
		boat.boatUse = "PLEASURE";
		boat.propulsion = "OTHER";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Jet Boat";
		boat.registration.product = "VFA - VehicleForAddLien";
		
		// title info
		boat.title.boatValue = "90";
		boat.title.lienInfo = lien;
		boat.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		boat.title.setProductCode("TFL");
		boat.title.product = "TFL - TitleForLien";
		
		// customer info for registration
		cust.lName = "TEST-TransferRule23";
		cust.fName = "QA-TransferRule23";
	}	
}
