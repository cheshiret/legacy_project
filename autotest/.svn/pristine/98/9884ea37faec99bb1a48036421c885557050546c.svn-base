/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.transfer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehiclePreviousOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify the transfer vehicle registration.
 * @Preconditions: 
 * @SPEC:Transfer vehicle.UCS
 * @Task#:Auto-1003
 * 
 * @author Jwang8
 * @Date  Jun 18, 2012
 */
public class TransferVehicleRegistration extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleRegistrationsPage registrationPg = LicMgrVehicleRegistrationsPage.getInstance();
	private Customer tranToCust = new Customer();
    private String transferCustInfo = null;
    private VehicleRTI vehicleRTI = new VehicleRTI();
    private BoatInfo vehicle = new BoatInfo();
    
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		//transfer registration vehicle, just transfer customer
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		//Go to transfer registration vehicle product list page. 
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, vehicle);
		String transferOrderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		//Verify transfer vehicle registration.
		// To verify the previous owner info.
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoVehiclePreviousOwnersPg();
		this.verifyTransferPreviousOwner(cust);
		//Verify the transferred customer and status.
		lm.gotoVehicleRegistrationsTabPg();
		registrationPg.verifyActiveOwnerCustExist(transferCustInfo,vehicle.registration.status);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferOrderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "4152633";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 06 1985";
		cust.lName = "TEST-VehTitle";
		cust.fName = "QA-VehTitle";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.status = "Active";
		cust.ownerFromDate = DateFunctions.getToday();
		cust.ownerToDate = DateFunctions.getToday();
		
		vehicleRTI.setPrdCode("REG");
		vehicleRTI.setPrdName("RegisterBoat");
		vehicle.hullIdSerialNum = "transfer" + DateFunctions.getCurrentTime();
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
		vehicle.registration.status = "Active";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		tranToCust.custNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		tranToCust.status = "Active";
		
		transferCustInfo = tranToCust.lName+", " + tranToCust.fName +" "+ tranToCust.custNum;
	}
/**
 * verify transfer previous Owner.
 * @param preCust
 */
	private void verifyTransferPreviousOwner(Customer preCust){
		LicMgrVehiclePreviousOwnersPage previousOwnerPg = LicMgrVehiclePreviousOwnersPage.getInstance();
		boolean pass = previousOwnerPg.comparePreOwnersInfo(preCust);
		if(!pass){
		   throw new ErrorOnPageException("The privious owner info error");	
		}
	}
}
