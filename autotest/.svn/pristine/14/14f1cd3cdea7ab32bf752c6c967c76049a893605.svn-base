package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.transfer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegisterVehicleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Verify the transfer registration vehicle product list info.
 * @Preconditions:
 * @SPEC:Get Vehicle Registration Product List for Transfer.UCS
 * @Task#:Auto-1003
 * 
 * @author jwang8
 * @Date  Jun 18, 2012
 */
public class GetVehicleRegistProList extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleRegistrationWidget registrationPg = LicMgrVehicleRegistrationWidget.getInstance();
	private Customer tranToCust = new Customer();
	private String actionType = null;
	private String name = null;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		String orderNumber=lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		//transfer registration vehicle, just transfer customer
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		
	    //Go to transfer registration vehicle product list page. 
		this.gototransferRegistrationVehicleProductListPage(tranToCust, vehicle);
		registrationPg.verifyTransferRegistprocutListType(actionType);
		this.verifyRegistProductInList(name);
		
		//Do order clear up.
		lm.gotoVehicleOrderDetailPage(orderNumber);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "4152633";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 06 1985";
		cust.lName = "TEST-VehTitle";
		cust.fName = "QA-VehTitle";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		vehicleRTI.setPrdCode("REG");
		vehicleRTI.setPrdName("RegisterBoat");
		name = vehicleRTI.getPrdCode() + " - " +vehicleRTI.getPrdName();
		vehicle.hullIdSerialNum = "search" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";

		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		
		actionType = "Transfer";
	}
	
	private void gototransferRegistrationVehicleProductListPage(Customer customer,
			BoatInfo vehicle){
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
		.getInstance();
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
		.getInstance();
		LicMgrRegisterVehicleDetailsPage vehicleDetailPg = LicMgrRegisterVehicleDetailsPage
		.getInstance();
		vehicleDetailsPg.clickTransferButton();
		ajax.waitLoading();
		custSearchPg.waitLoading();
		
		custSearchPg.searchCust(customer);
		if (custSearchPg.exists()) {
			custSearchPg.clickCustomerFirstNumer();
		}
		ajax.waitLoading();
		custDetailPg.waitLoading();
			
		custDetailPg.clickOK();
		ajax.waitLoading();
		vehicleDetailPg.waitLoading();
		vehicleDetailPg.setupBoatDetails(vehicle);
		vehicleDetailPg.clickOK();
		ajax.waitLoading();
		registrationPg.waitLoading();
	}
	/**
	 * verify transfer rig
	 * @param productName
	 */
	private void verifyRegistProductInList(String productName){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		registrationPg.verifyProductExistingInList(productName, true);
		registrationPg.clickCancel();
		ajax.waitLoading();
		homePg.waitLoading();
	}

}
