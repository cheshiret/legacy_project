package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View the vehicle title details.
 * @Preconditions:
 * @SPEC: Use Case Specification: View Vehicle Title Details
 * @Task#: Auto - 1011
 * 
 * @author Jwang8
 * @Date  Jun 06, 2012
 */
public class ViewVehicleTitleDetails extends LicenseManagerTestCase {
	private LicMgrVehicleTitleListPage vehicleTitelPg = LicMgrVehicleTitleListPage.getInstance();
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	String orderNumber1 = "";
	String orderNumber2 = "";
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		orderNumber1 = lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		orderNumber2 = lm.processOrderCartToOrderSummaryPage(pay, false);
		lm.finishOrder();
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoVehicleTitleTabPage();
		vehicle.title.titleId = vehicleTitelPg.getActiveTitleItemId();;
		lm.gotoVehicleTitleDetailPgFromTitleList(vehicle.title.titleId);
		this.verifyVehicleTitleDetailsInfo(vehicle.title);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(orderNumber1);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		
		lm.gotoVehicleOrderDetailPage(orderNumber2);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		vehicleRTI.setPrdCode("REG");
		vehicleRTI.setPrdName("RegisterBoat");
		
		vehicle.hullIdSerialNum = "search" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		
		vehicle.title.product = "TIT-TitleBoat";
		vehicle.title.purchaseType = "Original";
		vehicle.title.status = "Active";
		vehicle.title.activeLiens = "";
		vehicle.title.numOfDuplicates = "0";
		vehicle.title.numOfCorrections = "0";
		vehicle.title.boatValue = "2";
	
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "4152633";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 06 1985";
		cust.lName = "TEST-VehTitle";
		cust.fName = "QA-VehTitle";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
	
	/**
	 * verify vehicle title details info.
	 * @param titleInfo
	 */
	private void verifyVehicleTitleDetailsInfo(TitleInfo titleInfo){
		LicMgrVehicleTitleDetialsInfoPage titleDetialsInfo = LicMgrVehicleTitleDetialsInfoPage.getInstance();
		boolean pass = titleDetialsInfo.compareVehicleTitleDetailsInfo(titleInfo);
		if(!pass){
			throw new ErrorOnPageException("The vehicle title info error");
		}
	}

}
