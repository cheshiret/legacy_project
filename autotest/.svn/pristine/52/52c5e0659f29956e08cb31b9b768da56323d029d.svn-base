package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleMotorsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the list info and details info, vehicle info and customer info in Vehicle Title Details page after titling a Motor vehicle
 * 							which is associated to a boat registration
 * @Preconditions: 1. an existing Boat Vehicle product
 * 							2. an existing Motor Vehicle product
 * @SPEC: <<Title Vehicle.UCS>> TC:004712
 * @Task#: AUTO-1008
 * 
 * @author qchen
 * @Date  Jul 10, 2012
 */
public class TitleMotorAfterRegisteringBoat extends LicenseManagerTestCase {

	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private BoatInfo boat = new BoatInfo();
	private MotorInfo motor = new MotorInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//register Boat associate Motor
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderToOrderSummary(pay);
		boat.registration.miNum = summaryPage.getMINum();
		String ordNum = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		//title Motor from boat vehicle details page
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		motor.id = LicMgrVehicleMotorsPage.getInstance().getMotorBoatId(motor);
		lm.gotoVehicleDetailPageByID(motor.id);
		//title
		lm.titleVehicleToOrderCartFromVehicleDetailPage(motor);
		this.verifyTransactionType();
		lm.processOrderToOrderSummary(pay);
		motor.title.titleNum = summaryPage.getTitleNum();
		String ordNum2 = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		//verify vehicle title list info
		lm.gotoVehicleDetailPageByID(motor.id);
		lm.gotoVehicleTitleTabPage();
		motor.title.titleId = lm.getVehicleTitleIDBySerialNum(schema, motor.getSerialNum());
		this.verifyVehicleTitleListInfo(motor.title);
		
		//verify vehicle title detail info
		lm.gotoVehicleTitleDetailPgFromTitleList(motor.title.titleId);
		this.verifyVehicleTitleDetailInfo(motor.title, motor, cust);
		
		//clean up - void order
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum2, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
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
		
		//vehicle registration info
		boat.hullIdSerialNum = "Auto" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "Sony";
		boat.manufacturerPrintName = boat.manufacturerName;
		boat.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		boat.feet = "99";
		boat.inches = "9";
		boat.hullMaterial = "Steel";
		boat.boatUse = "PLEASURE";
		boat.propulsion = "OTHER";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Jet Boat";
		boat.horsePower = "0.0";
		//vehicle registration info
		boat.registration.product = "RV1 - RegisterVehicleBoat";
		
		//vehicle registration info
		motor.status = OrmsConstants.ACTIVE_STATUS;
		motor.setSerialNum("AutoMotor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMAHA");
		motor.setManufacturerPrintName(motor.getManufacturerName());
		motor.setModelYear(String.valueOf(DateFunctions.getCurrentYear()));
		motor.setHorsePower("999.99");
		motor.setMotorFuel("Gasoline");
		
		//vehicle title info
		motor.title.status = OrmsConstants.ACTIVE_STATUS;
		motor.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.product = "TV3 - TitleVehicleMotor";
		motor.title.setMotorValue("998");
		motor.title.boatValue = "998";//because in method 'setLineInfo(TitleInfo titleInfo)' of 'LicMgrAddVehicleLienWidget', it use this 'motor.title.boatValue' to set motor value again!!! 
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
		
		//IMPORTANT
		boat.motors.add(motor);
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
	
	/**
	 * verify vehicle title list info in Vehicle Details - Title sub page
	 * @param expected
	 */
	private void verifyVehicleTitleListInfo(TitleInfo expected) {
		LicMgrVehicleTitleListPage titleListPage = LicMgrVehicleTitleListPage.getInstance();
		
		logger.info("Verify vehicle title list info in Vehicle-Title sub page.");
		boolean result = titleListPage.compareTitleListInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Vehicle title list info is incorrect.");
		} else logger.info("Vehicle title list info is correct.");
	}
	
	/**
	 * verify vehicle title details info in vehicle title detail page
	 * @param expectTitle
	 * @param expectedVehicle
	 * @param expectedCust
	 */
	private void verifyVehicleTitleDetailInfo(TitleInfo expectTitle, Vehicle expectedVehicle, Customer expectedCust) {
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
