package com.activenetwork.qa.awo.testcases.sanity.orms;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify Motor vehicle type all transactions work correctly
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-1317
 * 
 * @author qchen
 * @Date  Oct 25, 2012
 */
public class LM_VehicleMotor extends LicenseManagerTestCase {

	private MotorInfo motor = new MotorInfo();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private LicMgrVehicleDetailPage vehicleDetailsPage = LicMgrVehicleDetailPage.getInstance();
	private Customer toCust = new Customer();
	private String reverseReason, reverseNote;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. title Motor vehicle
		lm.titleMotorToOrderCart(cust, motor);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TITLE_VEHICLE);
		lm.processOrderToOrderSummary(pay);
		String ordNums = summaryPage.getAllOrdNums();
		String[] tokens=ordNums.split(" ");
		String ordNum=tokens[0];
		motor.title.titleNum = summaryPage.getTitleNum();
		motor.title.setVehicleIdMiNum(summaryPage.getVehicleID());
		lm.finishOrder();
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//2. duplicate title motor
		lm.gotoVehicleDetailPageByID(motor.title.getVehicleIdMiNum());
		lm.duplicateTitileVehicleToOrderCartFromRegistrationDetailPg(motor, "");
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_VEHICLE_TITLE);
		lm.processOrderToOrderSummary(pay);
		String ordNum1 = summaryPage.getAllOrdNums().split(" ")[0];
		motor.title.titleNum = summaryPage.getTitleNum();
		motor.title.setVehicleIdMiNum(summaryPage.getVehicleID());
		lm.finishOrder();
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		lm.verifyOrderStatus(ordNum1, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//3. release lien
		lm.gotoVehicleTitleDetailPage(motor.title.titleNum);
		lm.releaseVehicleLien(motor.title.lienInfo.getDateOfRelease());
		
		//4. set to transferable
		lm.setTitleToTransferable();
		titleDetailsPage.verifyVehicleTitleStatus("Transferable");
		
		//5. reactivate title
		lm.reactivateTitle();
		titleDetailsPage.verifyVehicleTitleStatus("Active");
		
		//6. surrender title
		lm.surrenderTitle();
		titleDetailsPage.verifyVehicleTitleStatus("Surrendered");
		
		//7. transfer
		lm.gotoVehicleDetailPageByID(motor.title.getVehicleIdMiNum());
		lm.transferMotorDealerToVehicleDetailsPageFromDetailsPage(toCust, motor);
		lm.gotoVehicleDetailPageByID(motor.title.getVehicleIdMiNum());
		vehicleDetailsPage.verifyVehicleCustomerInfo(toCust);
		
		//8. reverse transfer
		lm.gotoVehicleDetailPageByID(motor.title.getVehicleIdMiNum());
		lm.reverseTransferMotor();
		vehicleDetailsPage.verifyVehicleCustomerInfo(cust);
		
//		//9. register
//		lm.gotoVehicleDetailPageByID(motor.title.getVehicleIdMiNum());
//		lm.registerVehicleToOrderCart(cust, motor);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_REGISTER_VEHICLE);
//		lm.processOrderToOrderSummary(pay);
//		String ordNum2 = summaryPage.getAllOrdNums();
//		motor.registration.miNum = summaryPage.getMINum();
//		lm.verifyOrderStatus(ordNum2, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//10. reverse orders
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum1, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/LAKE LINCOLN STATE PARK(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "19801223";
		cust.identifier.country = "Canada";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.lName = "Test-Sanity8";
		cust.fName = "QA-Sanity8";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Dec 23 1980";
		cust.status = OrmsConstants.ACTIVE_STATUS;
		
		toCust.identifier.identifierType = "Green Card";
		toCust.identifier.identifierNum = "19810123";
		toCust.identifier.country = "Canada";
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;;
		toCust.lName = "Test-Sanity9";
		toCust.fName = "QA-Sanity9";
		toCust.residencyStatus = "Non Resident";
		toCust.dateOfBirth = "Jan 23 1981";
		toCust.status = OrmsConstants.ACTIVE_STATUS;
		
		//vehicle registration info
		motor.status = OrmsConstants.ACTIVE_STATUS;
		motor.setSerialNum("SanityMotor" + DateFunctions.getCurrentTime());
		motor.setManufacturerName("Yamaha");
		motor.setManufacturerPrintName(motor.getManufacturerName());
		motor.setModelYear(String.valueOf(DateFunctions.getCurrentYear()));
		motor.setHorsePower("999.99");
		motor.setMotorFuel("Gasoline");
		
		//vehicle title info
		motor.title.status = OrmsConstants.ACTIVE_STATUS;
		motor.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		motor.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		motor.title.product = "425 - MOTOR TITLE";
		motor.title.setMotorValue("998");
		motor.title.lienInfo.setDateOfLien(DateFunctions.getToday(timeZone));
		motor.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		motor.title.lienInfo.setLienAmount("100");
		//lien company info
		LienCompanyDetailsInfo company = new LienCompanyDetailsInfo();
		company.isAddNew = false;
		company.lienCompanyName = "A. D. LEWIS AND JAN LEWIS";
		motor.title.lienInfo.setLienCompanyDetailsInfo(company);
//		company.isSameAsAbove = false;
//		company.lienCompanyName = "LienCompany-" + DateFunctions.getCurrentTime();
//		company.address = "Keji 2nd Road";
//		company.city = "Xian";
//		company.state = "Mississippi";
//		company.zip = "36918";
//		company.country = "United States";
//		company.contactPhone = "02968685958";
		motor.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		
		reverseReason = "14 - Other";
		reverseNote = "Automation Sanity";
	}
}
