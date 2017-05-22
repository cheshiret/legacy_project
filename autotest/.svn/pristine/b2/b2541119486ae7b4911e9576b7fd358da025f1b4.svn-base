package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleLienListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description: Verify the title action history info.
 * @Preconditions:
 * @SPEC: View vehicle Title history
 * @Task#:
 * 
 * @author Jwang8
 * @Date  Jun 12, 2012
 */
public class ViewVehicleTitleHistory  extends LicenseManagerTestCase{
	private LicMgrVehicleTitleListPage vehicleTitelPg = LicMgrVehicleTitleListPage.getInstance();
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage vehicleTitleDetailsInfoPg = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private LicMgrVehicleLienListPage lienListPg = LicMgrVehicleLienListPage.getInstance();
	private OrderInfo orderInfo = null;
	private String orderNumber1 = "";
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		//this.setRegisterVehicleInfoFromUI();
		orderNumber1 = lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		//this.setTitleVehicleInfoFromUi();
		orderInfo.orderNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		orderInfo.receiptNum = ordSummaryPg.getReceiptNum();
		
		lm.finishOrder();
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoVehicleTitleTabPage();
		vehicle.title.titleId = vehicleTitelPg.getActiveTitleItemId();
		vehicleTitelPg.transferableTitle(vehicle.title.titleId);// set transferable 
		vehicleTitelPg.surrenderTitle(vehicle.title.titleId);//surrender Title
		vehicleTitelPg.showAll();
		vehicleTitelPg.reactivateTitle(vehicle.title.titleId);//Reactivate title
		lm.gotoVehicleTitleDetailPgFromTitleList(vehicle.title.titleId);
		vehicle.title.boatValue = "3";
		vehicleTitleDetailsInfoPg.editTitle(vehicle.title.boatValue);//Edit title
		lm.gotoVehicleLienListPage();
		
		this.initVehicleLienInfo();
		vehicle.title.lienInfo.setLienId(lm.addVehicleLien(vehicle.title.lienInfo));//Add lien
		lm.releaseVehicleLien(vehicle.title.lienInfo.getDateOfRelease());//Reactivate lien
		lienListPg.reactivateLien(vehicle.title.lienInfo.getLienId());
		lm.releaseVehicleLien(vehicle.title.lienInfo.getDateOfRelease());//Reactivate lien
		lm.gotoVehicleTitleHistoryFromDetailPg();
	
		this.verifyTitleHistoryInfo(orderInfo, vehicle);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(orderInfo.orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		lm.gotoVehicleOrderDetailPage(orderNumber1);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
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

		//Can't add the line info as can't do transferable if there is active lien belong to vehicle title
	
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "4152633";
		orderInfo = new OrderInfo();
		orderInfo.transactionList = new ArrayList<String>();
		orderInfo.transactionList.add("Title Vehicle");
		orderInfo.transactionList.add("Set Title To Transferable");
		orderInfo.transactionList.add("Surrender Title");
		orderInfo.transactionList.add("Reactivate Title");
		orderInfo.transactionList.add("Edit Title");
		orderInfo.transactionList.add("Add Lien");
		orderInfo.transactionList.add("Release Lien");
		orderInfo.transactionList.add("Reactivate Lien");
		
		orderInfo.transactionLocation =login.location.split("/")[1];
		orderInfo.transactionUser =  DataBaseFunctions.getLoginUserName(login.userName);
	}
      
	/*private void setRegisterVehicleInfoFromUI(){
		
		vehicle.miNum =  ordSummaryPg.getMINum();
		orderNumber1  =  ordSummaryPg.getAllOrdNums();
	}
	
	private void setTitleVehicleInfoFromUi(){
		vehicle.miNum = ordSummaryPg.getMINum();
		orderNumber2 = ordSummaryPg.getAllOrdNums();
		orderInfo.orderNum = orderNumber2;
		orderInfo.receiptNum = ordSummaryPg.getReceiptNum("Receipt #");
	}*/
	
	private void verifyTitleHistoryInfo(OrderInfo orderInfo,Vehicle vehicle){
		LicMgrVehicleTitleHistoryPage historyPg = LicMgrVehicleTitleHistoryPage.getInstance();
		boolean pass = historyPg.compareTitileHistoryInfo(orderInfo, vehicle);
		if(!pass){
			throw new ErrorOnPageException("The vehicle title history info error");
		}
	}
	/**
	 * Init vehicle lien info.
	 */
	private void initVehicleLienInfo(){
		vehicle.title.lienInfo = new LienInfo();
		vehicle.title.lienInfo.setDateOfLien(DateFunctions.getDateAfterToday(-1));//DateFunctions.getToday();
		vehicle.title.lienInfo.setLienAmount("2");
		LienCompanyDetailsInfo lienCompanyDetailsInfo = new LienCompanyDetailsInfo();
		lienCompanyDetailsInfo.isAddNew =true;
		lienCompanyDetailsInfo.lienCompanyName ="Lien" + DateFunctions.getCurrentTime(); //To do need a support script.
		lienCompanyDetailsInfo.address = "test";
		lienCompanyDetailsInfo.city = "test";
		lienCompanyDetailsInfo.state = "Mississippi";
		lienCompanyDetailsInfo.zip = "12345";
		lienCompanyDetailsInfo.contactPhone = "123456";
		vehicle.title.lienInfo.setLienCompanyDetailsInfo(lienCompanyDetailsInfo);
		
		vehicle.title.lienInfo.setDateOfRelease(vehicle.title.lienInfo.getDateOfLien());
	}
}
