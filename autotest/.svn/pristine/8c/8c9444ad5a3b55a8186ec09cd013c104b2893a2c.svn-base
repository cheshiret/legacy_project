/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.transfer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleCoOwnersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleViewPreviousCoOwnersWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Verify the transfer vehicle registration with Co-Owner.
 * @Preconditions:
 * @SPEC:Transfer vehicle.UCS
 * @Task#:Auto-1003
 * 
 * @author jwang8
 * @Date  Jun 25, 2012
 */
public class TransferVehicleWithCoOwner extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleCoOwnersPage coOwnerPg = LicMgrVehicleCoOwnersPage.getInstance();
	private OwnerInfo coOwnerInfo = null;
	private Customer tranToCust =null;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
				
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		//vehicle.registration.miNum = "MI0529AA";
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		
		coOwnerInfo.id = lm.addCoOwnerFromCoOwnerSubPg(coOwnerInfo);
		
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust, vehicle);
		String transferNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.gotoCoOwnerSubPgFromVehicleDetailsPg();
		coOwnerPg.verifyCoOwnerNotExist(coOwnerInfo.id );
		lm.gotoViewPreviousCoOwnerFromCoOwnerSubPg();
		this.verifyCoOwnerExistInPreviousView(coOwnerInfo.id);
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(transferNum);
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
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		
		tranToCust = new Customer();
		tranToCust.identifier.identifierType = "Tax ID";
		tranToCust.identifier.identifierNum = "4152634";
		tranToCust.lName = "TEST-VehTitle1";
		tranToCust.fName = "QA-VehTitle1";
		tranToCust.customerClass = "Individual";
		tranToCust.dateOfBirth = "Feb 07 1985";
		tranToCust.custNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		tranToCust.status = "Active";
		
		coOwnerInfo = new OwnerInfo();
		coOwnerInfo.firstName = "ftransfer";
		coOwnerInfo.lastName = "l"+DateFunctions.getCurrentTime();
		coOwnerInfo.coOwnerFrom =DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy") +" "+ DataBaseFunctions.getLoginUserName(login.userName); 
	}
	/**
	 * veirfy coOwner exist in previous view List
	 * @param coOwnerId
	 */
	private void verifyCoOwnerExistInPreviousView(String coOwnerId){
		LicMgrVehicleViewPreviousCoOwnersWidget viewPreviousPg = LicMgrVehicleViewPreviousCoOwnersWidget.getInstance();
		boolean isExist = viewPreviousPg.checkCoOwnerExistInPreviousView(coOwnerId);
		if(!isExist){
			throw new ErrorOnPageException("The co-Owner should be in previous view after transfer");
		}else{
			logger.info("co owner is in the prvious view");
		}
		viewPreviousPg.clickOK();
		ajax.waitLoading();
		coOwnerPg.waitLoading();
	}
}
