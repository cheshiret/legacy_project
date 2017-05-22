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
 * @Description:Add lien when title motor.
 * @Preconditions: Motor for title(TFM) should be exist.
 * @SPEC:AddLien.UCS
 * @Task#:Auto-1016
 * 
 * @author nding1
 * @Date  Jul 30, 2012
 */
public class AddWhenTitle extends LicenseManagerTestCase {
	private MotorInfo motor = new MotorInfo();
	private boolean result = true;
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
		
		// get active lien ID
		motor.title.lienInfo.setLienId(lienListPg.getActiveLienId());
		
		// verify lien info in lien list page.
		result &= lienListPg.verifyLienInfo(motor.title.lienInfo);

		// go to lien detail page
		lm.gotoLienDetailPage(motor.title.lienInfo.getLienId());
		
		// verify lien detail info in edit lien page.
		result &= this.verifyLienDetailInfo();
		
		// clean up
		motor.title.lienInfo.setDateOfRelease(motor.title.lienInfo.getDateOfLien());
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(titleOrderNum, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
		
		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed. Please check log above.");
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
		motor.setHorsePower("21");
		motor.setMotorFuel("Diesel");

		// lien info
		LienInfo lien = new LienInfo();
		lien.setDateOfLien(DateFunctions.getToday(timeZone));
		lien.setStauts(ACTIVE_STATUS);
		lien.setDateOfRelease("");// When status is Active, release date is blank.
		lien.setLienAmount("10");
		lien.setCreationDateTime(DateFunctions.getToday(timeZone));
		lien.setCreationUser(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName));
		
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
		cust.lName = "TEST-TransferRule1111";
		cust.fName = "QA-TransferRule1111";
	}
	
	/**
	 * Verify lien detail info.
	 */
	private boolean verifyLienDetailInfo(){
		LicMgrEditVehicleLienDetailsWidget editLienPg = LicMgrEditVehicleLienDetailsWidget.getInstance();
		boolean result = true;
		
		logger.info("Verfiy lien detail info.");
		result &= editLienPg.verifyLienInfo(motor.title.lienInfo);
		editLienPg.clickCancel();
		ajax.waitLoading();
		lienListPg.waitLoading();
		return result;
	}
}