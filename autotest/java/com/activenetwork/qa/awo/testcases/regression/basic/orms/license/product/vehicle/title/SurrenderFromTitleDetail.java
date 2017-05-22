/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test script used to check surrender vehicle title from title detail page
 * @Preconditions: a customer,a register vehicle and a title vehicle
 * @SPEC:<Surrender Vehicle Title>
 * @Task#:Auto-1009
 * 
 * @author ssong
 * @Date  Jun 27, 2012
 */
public class SurrenderFromTitleDetail extends LicenseManagerTestCase{

	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage
			.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage detailPg = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private MotorInfo motor = new MotorInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);

		// title a motor
		lm.titleMotorToOrderCart(cust, motor);
		lm.processOrderToOrderSummary(pay);
		motor.title.titleNum = lmOrdSumPg.getTitleNum();
		String ord_num = lmOrdSumPg.getAllOrdNums().split(" ")[0];
		lm.finishOrder();

		// surrender transferable vehicle title
		lm.gotoVehicleTitleDetailPage(motor.title.titleNum);
		lm.gotoVehicleLienListPage();
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());
		detailPg.setToTransferable();
		detailPg.surrenderTitle();
		if(!detailPg.compareVehTitleStatus(SURRENDERED_STATUS)){
			throw new ErrorOnPageException("Surrender Title Failed.");
		}
		this.verifyTransactionFromDB(ord_num, TRANTYPE_SURRENDER_VEHICLE_TITLE);
		
		// title motor
		lm.gotoHomePage();
		lm.titleMotorToOrderCart(cust, motor);
		lm.processOrderToOrderSummary(pay);
		motor.title.titleNum = lmOrdSumPg.getTitleNum();
		ord_num = lmOrdSumPg.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		// surrender active vehicle title
		lm.gotoVehicleTitleDetailPage(motor.title.titleNum);
		lm.gotoVehicleLienListPage();
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());
		detailPg.surrenderTitle();
		if(!detailPg.compareVehTitleStatus(SURRENDERED_STATUS)){
			throw new ErrorOnPageException("Surrender Title Failed.");
		}
		this.verifyTransactionFromDB(ord_num, TRANTYPE_SURRENDER_VEHICLE_TITLE);
				
		lm.logOutLicenseManager();
	}
	
	private void verifyTransactionFromDB(String ord_num,String tran_type){
		boolean result = true;
		String[] info = lm.getOrderTransInfoByOrderItemAndTransType(schema, ord_num, tran_type);

		result = MiscFunctions.compareResult("Transaction Date ",
				DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"),
				DateFunctions.getToday());
		result &= MiscFunctions.compareResult("Sales Channel ", info[5],
				SALESCHAN_FIELD);
		result &= MiscFunctions.compareResult("Location ", info[3], login.location.split("/")[1]);

		if (!result) {
			throw new ErrorOnPageException(
					"Order transaction info is not correct.Please check log file...");
		}
		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* register vehicle */
		motor.setSerialNum("SurrenderMotor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setModelYear("1997");
		motor.setMotorFuel("Gasoline");
		motor.setHorsePower("200");
		
		motor.title.setMotorValue("800");
		motor.title.lienInfo.setDateOfLien(DateFunctions.getDateAfterToday(-5));
		motor.title.lienInfo.setLienAmount("500");
		LienCompanyDetailsInfo comInfo = new LienCompanyDetailsInfo();
		comInfo.lienCompanyName = "AHFC";
		motor.title.lienInfo.setLienCompanyDetailsInfo(comInfo);
		motor.title.lienInfo.setDateOfRelease(DateFunctions.getToday());
		
		motor.title.product = "TV3 - TitleVehicleMotor";
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000023";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic23";
		cust.fName = "QA-Basic23";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
