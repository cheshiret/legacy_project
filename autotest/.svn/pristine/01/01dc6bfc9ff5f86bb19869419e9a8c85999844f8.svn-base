package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title.lien.motor;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
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
 * @Preconditions: Vehicle for title(TFL) should be exist.
 * @SPEC:Edit Lien.UCS
 * @Task#:Auto-1016
 * 
 * @author nding1
 * @Date  Jul 30, 2012
 */
public class Edit extends LicenseManagerTestCase {
	private boolean result = true;
	private MotorInfo motor = new MotorInfo();
	private LienInfo newLien = new LienInfo();
	private LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// title Motor vehicle WITH adding lien info
		lm.titleMotorToOrderCart(cust, motor);
		lm.processOrderToOrderSummary(pay);
		OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
		motor.title.titleNum = summaryPage.getTitleNum();
		String titleOrderNum = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		// go to title detail page
		lm.gotoVehicleTitleDetailPage(motor.title.titleNum);
		
		// go to lien list page
		lm.gotoVehicleLienListPage();
		newLien.setLienId(lienListPg.getActiveLienId());
		
		// edit lien
		lm.editVehicleLien(newLien);
		
		// verify lien info in lien list page.
		result &= lienListPg.verifyLienInfo(newLien);
		
		// verify lien detail info in edit lien page.
		lm.gotoLienDetailPage(newLien.getLienId());
		result &= this.verifyLienDetailInfo();
		
		// clean up
		motor.title.lienInfo.setDateOfRelease(motor.title.lienInfo.getDateOfLien());
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(titleOrderNum, motor.operationReason, motor.operationNote);
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

		// vehicle registration info
		motor.setSerialNum("AddMotorLien" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setManufacturerPrintName(motor.getManufacturerName());
		motor.setModelYear(DateFunctions.getCurrentYear()+"");
		motor.setHorsePower("18");
		motor.setMotorFuel("Diesel");

		// lien info
		LienInfo lien = new LienInfo();
		lien.setDateOfLien(DateFunctions.getToday(timeZone));
		lien.setStauts(ACTIVE_STATUS);
		lien.setDateOfRelease("");// When status is Active, release date is blank.
		lien.setLienAmount("25");
		
		// lien company info
		LienCompanyDetailsInfo companyInfo = new LienCompanyDetailsInfo();
		companyInfo.isAddNew = true;
		companyInfo.isSameAsAbove = false;
		companyInfo.lienCompanyName = "AddLienCompany" + DateFunctions.getCurrentTime();
		companyInfo.address = "Software park";
		companyInfo.city = "Xian";
		companyInfo.state = "Mississippi";
		companyInfo.zip = "71007";
		companyInfo.country = "United States";
		companyInfo.contactPhone = "125487963";
		lien.setLienCompanyDetailsInfo(companyInfo);

		// vehicle title info
		motor.title.status = OrmsConstants.ACTIVE_STATUS;
		motor.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.product = "TFM - TitleForMotor";
		motor.title.setMotorValue("216");
		motor.title.lienInfo = lien;
		
		// customer info
		cust.lName = "TEST-TransferRule13";
		cust.fName = "QA-TransferRule13";
		
		// new lien info
		newLien.setDateOfLien(DateFunctions.getToday(timeZone));
		newLien.setStauts(lien.getStauts());
		newLien.setDateOfRelease(lien.getDateOfRelease());
		newLien.setLienAmount("63");
		newLien.setCreationDateTime(DateFunctions.getToday(timeZone));
		newLien.setCreationUser(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName));
		
		companyInfo = new LienCompanyDetailsInfo();
		companyInfo.isAddNew = true;
		companyInfo.isSameAsAbove = false;
		companyInfo.lienCompanyName = "Editliencompany"+DateFunctions.getCurrentTime();
		companyInfo.address = "Keji Road";
		companyInfo.city = "Chendu";
		companyInfo.state = "Mississippi";
		companyInfo.zip = "85412";
		companyInfo.country = "United States";
		companyInfo.contactPhone = "326521479";
		newLien.setLienCompanyDetailsInfo(companyInfo);
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
