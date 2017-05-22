package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title.lien.boat;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrEditVehicleLienDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Edit lien in lien detail widget.
 * @Preconditions: Boat for register(VFA) and title(TFL) should be exist.
 * @SPEC:Edit Lien.UCS
 * @Task#:Auto-1016
 * 
 * @author nding1
 * @Date  Jul 30, 2012
 */
public class Edit extends LicenseManagerTestCase {
	private boolean result = true;
	private BoatInfo boat = new BoatInfo();
	private LienInfo newLien = new LienInfo();
	private OrmsOrderSummaryPage orderSummaryPage = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// register vehicle
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderCartToOrderSummaryPage(pay, false);
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
		
		// edit lien
		newLien.setLienId(lienListPg.getActiveLienId());
		lm.editVehicleLien(newLien);
		
		// verify lien info in lien list page.
		result &= lienListPg.verifyLienInfo(newLien);
		
		// verify lien detail info in edit lien page.
		lm.gotoLienDetailPage(newLien.getLienId());
		result &= this.verifyLienDetailInfo();
		
		// clean up
		boat.title.lienInfo.setDateOfRelease(boat.title.lienInfo.getDateOfLien());
		lm.releaseVehicleLien(boat.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
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
		lien.setDateOfRelease("");// When status is Active, release date is blank.
		lien.setLienAmount("34");
		
		LienCompanyDetailsInfo lienCompanyDetailsInfo = new LienCompanyDetailsInfo();
		lienCompanyDetailsInfo.isAddNew = true;
		lienCompanyDetailsInfo.isSameAsAbove = false;
		lienCompanyDetailsInfo.lienCompanyName = "Addliencompany"+DateFunctions.getCurrentTime();
		lienCompanyDetailsInfo.address = "software park";
		lienCompanyDetailsInfo.city = "Xian";
		lienCompanyDetailsInfo.state = "Mississippi";
		lienCompanyDetailsInfo.zip = "71007";
		lienCompanyDetailsInfo.country = "United States";
		lienCompanyDetailsInfo.contactPhone = "789654789";
		lien.setLienCompanyDetailsInfo(lienCompanyDetailsInfo);
		
		// boat info for registration
		boat.hullIdSerialNum = "AddLien" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "YAMA";
		boat.manufacturerPrintName = boat.manufacturerName;
		boat.modelYear = DateFunctions.getCurrentYear()+"";
		boat.feet = "25";
		boat.inches = "4";
		boat.hullMaterial = "Steel";
		boat.boatUse = "PLEASURE";
		boat.propulsion = "OTHER";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Jet Boat";
		boat.registration.product = "VFA - VehicleForAddLien";
		
		// title info
		boat.title.boatValue = "785";
		boat.title.lienInfo = lien;
		boat.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		boat.title.setProductCode("TFL");
		boat.title.product = "TFL - TitleForLien";
		
		// customer info for registration
		cust.lName = "TEST-TransferRule15";
		cust.fName = "QA-TransferRule15";
		
		// new lien info
		newLien.setDateOfLien(DateFunctions.getToday(timeZone));
		newLien.setStauts(lien.getStauts());
		newLien.setDateOfRelease(lien.getDateOfRelease());
		newLien.setLienAmount("63");
		newLien.setCreationDateTime(DateFunctions.getToday(timeZone));
		newLien.setCreationUser(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName));
		
		lienCompanyDetailsInfo = new LienCompanyDetailsInfo();
		lienCompanyDetailsInfo.isAddNew = true;
		lienCompanyDetailsInfo.isSameAsAbove = false;
		lienCompanyDetailsInfo.lienCompanyName = "Editliencompany"+DateFunctions.getCurrentTime();
		lienCompanyDetailsInfo.address = "Keji Road";
		lienCompanyDetailsInfo.city = "Chendu";
		lienCompanyDetailsInfo.state = "Mississippi";
		lienCompanyDetailsInfo.zip = "30014";
		lienCompanyDetailsInfo.country = "United States";
		lienCompanyDetailsInfo.contactPhone = "7542698";
		newLien.setLienCompanyDetailsInfo(lienCompanyDetailsInfo);
	}
	
	/**
	 * Verify lien detail info.
	 */
	private boolean verifyLienDetailInfo(){
		LicMgrEditVehicleLienDetailsWidget editLienPg = LicMgrEditVehicleLienDetailsWidget.getInstance();
		boolean result = true;
		
		logger.info("Verfiy lien detail info.");
		result &= editLienPg.verifyLienInfo(newLien);
		editLienPg.clickCancel();
		ajax.waitLoading();
		lienListPg.waitLoading();
		return result;
	}
}
