package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title.lien.motor;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Release lien in lien list page.
 * @Preconditions:Boat for title(TFM) should be exist.
 * @SPEC:Release Lien.UCS
 * @Task#:Auto-1016
 * 
 * @author nding1
 * @Date  Aug 1, 2012
 */
public class Release extends LicenseManagerTestCase {
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
		

		motor.title.lienInfo.setLienId(lienListPg.getActiveLienId());
		// release lien
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());
		
		// verify status
		motor.title.lienInfo.setStauts(RELEASE_STATUS);
		result &= lienListPg.verifyLienInfo(motor.title.lienInfo);

		// clean up
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
		lien.setDateOfRelease(DateFunctions.getToday(timeZone));
		lien.setLienAmount("10");
		lien.setStauts("Active");
		
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
		companyInfo.contactPhone = "852147963";
		lien.setLienCompanyDetailsInfo(companyInfo);

		// vehicle title info
		motor.title.status = OrmsConstants.ACTIVE_STATUS;
		motor.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.product = "TFM - TitleForMotor";
		motor.title.setMotorValue("305");
		motor.title.lienInfo = lien;
		
		// customer info
		cust.lName = "TEST-TransferRule1111";
		cust.fName = "QA-TransferRule1111";
	}
}
