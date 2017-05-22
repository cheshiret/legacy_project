/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.HashMap;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the vehicle title product list for duplicate.  
 * @Preconditions:
 * 1. The user, the location, and the location class exist.
 * @SPEC: <Get Vehicle Title for Duplicate.UCS>
 * @Task#: Auto-1014
 * 
 * @author Lesley Wang
 * @Date  Jul 16, 2012
 */
public class GetListForTitling_Duplicate extends LicenseManagerTestCase {

	private VehicleRTI motorTitle = new VehicleRTI();
	private MotorInfo motor = new MotorInfo();
	private String admLocation, salesLocation, locClass;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// Create a product with original and duplicate prices, and assign to the location class
		lm.gotoVehicleSearchListPageFromTopMenu();
		lm.addVehicleProduct(motorTitle);
		lm.gotoProductPricingPageFromListPage(motorTitle.getVehicleType(), motorTitle.getPrdCode());
		//pricing for original
		lm.addPricingForVehicleProduct(pricing);
		//pricing for correct
		pricing.purchaseType = OrmsConstants.DUPLICATE_PURCHASE_TYPE;
		lm.addPricingForVehicleProduct(pricing);
		lm.assignVehicleToStoresThruLocationClass(locClass);
		
		// Title a motor vehicle with the product
		lm.switchLocationInHomePage(salesLocation);
		lm.titleMotorToOrderCart(cust, motor);
		String titleOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		motor.id = OrmsOrderSummaryPage.getInstance().getVehicleID();
		lm.finishOrder();
		
		// Duplicate the vehicle title
		lm.gotoVehicleDetailsPgByMiNum(motor.id);
		motor.title.purchaseType = OrmsConstants.DUPLICATE_PURCHASE_TYPE;
		lm.correctOrDuplicateTitleVehicleToTitleProductSelectionWidget();
		// verify the vehicle title product is shown in the product list for duplicate
		LicMgrVehicleRegistrationWidget.getInstance().
				verifyProductExistingInList(motor.title.product, motor.title.purchaseType, true);
		// Process the duplicate title transaction
		lm.selectTitleVehicleProductToOrderCart(motor);
		String dupOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		// Clean up
		lm.voidRegisterVehicleOrder(dupOrdNum, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(titleOrdNum, motor.operationReason, motor.operationNote);
		lm.processOrderCart(pay);
		
		// Switch to admin location to deactivate vehicle product
		lm.switchLocationInHomePage(admLocation);
		lm.deactivateVehicleProduct(motorTitle.getPrdCode());
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		String loc = TestProperty.getProperty("ms.admin.location");
		login.location = "HF Administrator/" + loc;
		admLocation = "HF Administrator - Auto-" + loc;
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		locClass = "06-State Parks Agent";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//title vehicle product info
		motorTitle.setPrdCode(StringUtil.getRandomString(3, true));
		motorTitle.setPrdName("DupTitleVehicleMotor");
		motorTitle.setPrdGroup("Title");
		motorTitle.setVehicleType("Motor");
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		motorTitle.setCustClass(map);
		
		//title vehicle pricing info
		pricing.prodType = "Vehicle";
		pricing.status = OrmsConstants.ACTIVE_STATUS;
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricing.effectFrom = DateFunctions.getToday(timeZone);
		pricing.vendorFee = "1.53";
		pricing.stateTransFee = "1.99";
		pricing.stateFee = "0.15";
		pricing.transFee = "0.26";
		pricing.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		
		//vehicle info
		motor.setSerialNum("MotorDupList" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("YAMA");
		motor.setModelYear(pricing.licYearFrom);
		motor.setHorsePower("5");
		motor.setMotorFuel("Other");
		
		//vehicle title info
		motor.title.product = motorTitle.getPrdCode() + " - " + motorTitle.getPrdName();
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.setMotorValue("2599");
		
		//customer info
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Jun 01 1986";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "29776637";
	}
}
