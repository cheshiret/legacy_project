package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the details info including title info, vehicle info and customer info in Vehicle Title Details page
 * 						after titling a Motor vehicle
 * @Preconditions: 1. an existing Motor Vehicle product
 * 							2. register this Vehicle
 * @SPEC: <<Title Vehicle.UCS>> TC:004712
 * @Task#: AUTO-1008
 * 
 * @author qchen
 * @Date  Jun 26, 2012
 */
public class TitleMotor extends LicenseManagerTestCase {
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private MotorInfo motor = new MotorInfo();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//title Motor vehicle
		lm.titleMotorToOrderCart(cust, motor);
		this.verifyTransactionType();
		lm.processOrderToOrderSummary(pay);
		motor.title.titleNum = summaryPage.getTitleNum();
		String ordNum = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		//verify vehicle title list info
		lm.gotoVehiclesDetailsPgBySerialNum(motor.getSerialNum());
		lm.gotoVehicleTitleTabPage();
		motor.title.titleId = lm.getVehicleTitleIDBySerialNum(schema, motor.getSerialNum());
		motor.id = lm.getVehicleIDBySerialNum(schema, motor.getSerialNum());
		this.verifyVehicleTitleListInfo(motor.title);
		
		//verify vehicle title details info
		lm.gotoVehicleTitleDetailPgFromTitleList(motor.title.titleId);
		this.verifyVehicleTitleDetailInfo(motor.title, motor, cust);
		
		//clean up
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000024";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic24";
		cust.fName = "QA-Basic24";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.custNum = lm.getCustomerNum(cust, schema);
		cust.status = OrmsConstants.ACTIVE_STATUS;
		
		titleVehicleRTI.setPrdCode("TV3");
		titleVehicleRTI.setPrdName("TitleVehicleMotor");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Motor");
		
		//vehicle registration info
		motor.status = OrmsConstants.ACTIVE_STATUS;
		motor.setSerialNum("AutoMotor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("Yamaha");
		motor.setManufacturerPrintName(motor.getManufacturerName());
		motor.setModelYear(String.valueOf(DateFunctions.getCurrentYear()));
		motor.setHorsePower("999.99");
		motor.setMotorFuel("Gasoline");
		
		//vehicle title info
		motor.title.status = OrmsConstants.ACTIVE_STATUS;
		motor.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		motor.title.setMotorValue("998");
		motor.title.lienInfo.setDateOfLien(DateFunctions.getToday(timeZone));
		motor.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		motor.title.lienInfo.setLienAmount("100");
		
		//lien company info
		LienCompanyDetailsInfo company = new LienCompanyDetailsInfo();
		company.isAddNew = true;
		company.isSameAsAbove = false;
		company.lienCompanyName = "LienCompany-" + DateFunctions.getCurrentTime();
		company.address = "Keji 2nd Road";
		company.city = "Xian";
		company.state = "Mississippi";
		company.zip = "36918";
		company.country = "United States";
		company.contactPhone = "02968685958";
		
		motor.title.lienInfo.setLienCompanyDetailsInfo(company);
		motor.title.numOfDuplicates = "0";
		motor.title.numOfCorrections = "0";
	}
	
	/**
	 * verify transaction type info in order cart page
	 */
	private void verifyTransactionType() {
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify Transaction Type is correct in order cart page.");
		String transactionOnPage = orderCartPage.getFirstTransactionName().split("\\(")[0].trim();
		if(!transactionOnPage.equals(OrmsConstants.TRANNAME_TITLE_VEHICLE)) {
			throw new ErrorOnPageException("Transaction type should be " + OrmsConstants.TRANNAME_TITLE_VEHICLE + ", but actual is: " + transactionOnPage);
		} else logger.info("The transaction type is really " + transactionOnPage);
	}
	
	private void verifyVehicleTitleListInfo(TitleInfo expected) {
		LicMgrVehicleTitleListPage titleListPage = LicMgrVehicleTitleListPage.getInstance();
		
		logger.info("Verify vehicle title list info in Vehicle-Title sub page.");
		boolean result = titleListPage.compareTitleListInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Vehicle title list info is incorrect.");
		} else logger.info("Vehicle title list info is correct.");
	}
	
	private void verifyVehicleTitleDetailInfo(TitleInfo expectTitle, MotorInfo expectedVehicle, Customer expectedCust) {
		LicMgrVehicleTitleDetialsInfoPage titleDetailPage = LicMgrVehicleTitleDetialsInfoPage.getInstance();
		
		logger.info("Verify vehicle title detial info in Title detail page.");
		boolean result = titleDetailPage.compareVehicleTitleDetailsInfo(expectTitle);
		result &= titleDetailPage.compareVehicleInfo(expectedVehicle);
		result &= titleDetailPage.compareVehicleCustomerInfo(expectedCust);
		
		if(!result) {
			throw new ErrorOnPageException("Vehicle title detail info is incorrect.");
		} else logger.info("Vehicle title detail info is correct.");
	}
}
