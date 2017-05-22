package com.activenetwork.qa.awo.testcases.sanity.orms;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify Boat vehicle type all transactions work correctly
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-1317
 * 
 * @author qchen
 * @Date  Oct 25, 2012
 */
public class LM_VehicleBoat extends LicenseManagerTestCase {
	
	private BoatInfo boat = new BoatInfo();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private Customer toCust = new Customer();
	private String reverseReason, reverseNote;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. register a Boat vehicle
		lm.registerVehicleToOrderCart(cust, boat);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_REGISTER_VEHICLE);
		lm.processOrderToOrderSummary(pay);
		String ordNums = summaryPage.getAllOrdNums();
		String[] tokens=ordNums.split(" ");
		String ordNum=tokens[0];
		boat.registration.miNum = summaryPage.getMINum();
		lm.finishOrder();
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//2. duplicate registration
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(OrmsConstants.DUPLICATE_PURCHASE_TYPE, boat.registration.product);	
		
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_VEHICLE_REGISTRATION);
		String ordNum1 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		lm.verifyOrderStatus(ordNum1, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//3. transfer this registration
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(toCust, boat);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TRANSFER_REGISTRATION);
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyOrderStatus(ordNum1, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		lm.verifyOrderStatus(ordNum2, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//4. reverse transfer
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.reverseTransferToOrderCartFromDetailsPage(reverseReason, reverseNote);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_REVERSE_TRANSFER_REGISTRATION + " (" + ordNum2 + ")");
		lm.processOrderCart(pay);
		lm.verifyOrderStatus(ordNum2, OrmsConstants.ORD_STATUS_VOIDED, schema);
		
		//5. title this Boat registration
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(boat);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TITLE_VEHICLE);
		lm.processOrderToOrderSummary(pay);
		String ordNum3 = summaryPage.getAllOrdNums().split(" ")[0];
		boat.title.titleNum = summaryPage.getTitleNum();
		lm.finishOrder();
		lm.verifyOrderStatus(ordNum3, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//6. release Active lien
		lm.gotoVehicleTitleDetailPage(boat.title.titleNum);
		lm.gotoVehicleLienListPage();
		lm.releaseVehicleLien(boat.title.lienInfo.getDateOfRelease());
		
		//7. 'Set to Transferable', 'Reactivate' and 'Surrender' title
		lm.setTitleToTransferable();
		titleDetailsPage.verifyVehicleTitleStatus("Transferable");
		lm.reactivateTitle();
		titleDetailsPage.verifyVehicleTitleStatus("Active");
		lm.surrenderTitle();
		titleDetailsPage.verifyVehicleTitleStatus("Surrendered");
		lm.gotoHomePage();
		
		//8. inspect vehicle
		lm.inspectVehicleToOrderCartFromHomePg(cust, boat);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_INSPECT_VEHICLE);
		String ordNum4 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyOrderStatus(ordNum4, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//9. reverse order
		lm.voidRegisterVehicleOrder(ordNum4, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum3, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum1, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum, reverseReason, reverseNote);
		lm.processOrderCart(pay);
	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
//		login.location = "HF Sales Clerk/LAKE LINCOLN STATE PARK(Store Loc)";
		login.location = "HF HQ Role/LAKE LINCOLN STATE PARK(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "19801023";
		cust.identifier.country = "Canada";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.lName = "Test-Sanity6";
		cust.fName = "QA-Sanity6";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Oct 23 1980";
		cust.status = OrmsConstants.ACTIVE_STATUS;
		
		toCust.identifier.identifierType = "Green Card";
		toCust.identifier.identifierNum = "19801123";
		toCust.identifier.country = "Canada";
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;;
		toCust.lName = "Test-Sanity7";
		toCust.fName = "QA-Sanity7";
		toCust.residencyStatus = "Non Resident";
		toCust.dateOfBirth = "Nov 23 1980";
		toCust.status = OrmsConstants.ACTIVE_STATUS;
		
		//vehicle registration info
		boat.status = OrmsConstants.ACTIVE_STATUS;
		boat.hullIdSerialNum = "Sanity" + DateFunctions.getCurrentTime();
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
		boat.horsePower = "999";
		//vehicle registration info
		boat.registration.product = "406 - BOAT REG 40 FT AND OVER";
		
		//vehicle title info
		boat.title.status = OrmsConstants.ACTIVE_STATUS;
		boat.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		boat.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		//vehicle title info
		boat.title.product = "420 - BOAT TITLE";
		boat.title.boatValue = "300";
		boat.title.lienInfo.setDateOfLien(DateFunctions.getToday(timeZone));
		boat.title.lienInfo.setLienAmount("100");
		LienCompanyDetailsInfo company = new LienCompanyDetailsInfo();
		company.isAddNew = false;
		company.lienCompanyName = "A. D. LEWIS AND JAN LEWIS";
		boat.title.lienInfo.setLienCompanyDetailsInfo(company);
		boat.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		
		//vehicle inspection info
		boat.inspection.setProduct("450 - BOAT INSPECTION FEE");
		boat.inspection.setCopyPhysicalAddressFromCustomerProfile(true);
		boat.inspection.setAddress("Mississauga");
		boat.inspection.setZip("39296");
		boat.inspection.setCountry("United States");
		boat.inspection.setCity("Jackson");
		boat.inspection.setState("Mississippi");
		boat.inspection.setCounty("Hinds");
		boat.inspection.setDayPhone("02968685958");
		boat.inspection.setEveningPhone("02968685958");
		boat.inspection.setInspectionReason("Missing Hull Id Number");
		boat.inspection.setInspectionDetail("2");
		
		reverseReason = "14 - Other";
		reverseNote = "Automation Sanity";
	}
}
