package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.renewal;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleCustomerConfirmPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify customer profile info in renew vehicle process.
 * @Preconditions:
 * @SPEC:TC 036470 
 * @Task#:AUTO-1106
 * 
 * @author Jwang8
 * @Date  Jul 3, 2012
 */
public class VerifyRenewCusomerProfileInfo extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	private LicMgrVehicleCustomerConfirmPage confirmPg = LicMgrVehicleCustomerConfirmPage.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		String ordNum1 = ordSummaryPg.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		lm.gotoQuickRenewVehicleCustomerConfirmPg(vehicle.registration.miNum);
		this.setEditCusomerInfo();
		lm.editRenewVehicleCustomerProfileInfo(cust,vehicle.registration.product);
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];

		lm.gotoQuickRenewVehicleCustomerConfirmPg(vehicle.registration.miNum);
		this.verifyRenewVehiclCustomerInfo(vehicle, cust);
		this.verifyVehicleBoatInfoUnEdit();
		
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(ordNum2);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay, false);
		
		lm.gotoVehicleOrderDetailPage(ordNum1);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay, false);
		
		lm.logOutLicenseManager();
	}

	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "29776637";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 01 1986";
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		vehicleRTI.setPrdCode("Rew");
		vehicleRTI.setPrdName("RenewVehReg");
//		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "VehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = Integer.toString(DateFunctions.getCurrentYear());
		vehicle.feet = "2";  //this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		vehicle.inches = "0";//this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		vehicle.hullMaterial = "OTHER";
		vehicle.boatUse = "PLEASURE";//this value should be equal to the related record in 'AddVehicleProduct.datapool'
		vehicle.propulsion = "OTHER";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "Other";
		vehicle.creationUser = login.userName;
		vehicle.creationDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "EEE MMM d yyyy");
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.status = "Active";
	}
	/**
	 * verify renew vehicle customer info.
	 * @param vehicleInfo
	 * @param cust
	 */
	public void verifyRenewVehiclCustomerInfo(BoatInfo vehicleInfo,Customer cust){
		boolean pass = confirmPg.compareBoatAndCustomerInfo(vehicleInfo, cust);
		if(!pass){
			throw new ErrorOnPageException("Vehicle boat and renew customer info error");
		}else{
			logger.info("Vehicle boat and renew customer info are all correct");
		}
	}
/**
 * verify vehicle boat info unedit attribute.
 */
	public void verifyVehicleBoatInfoUnEdit(){
		//Click the edit button.
		confirmPg.clickEdit();
		ajax.waitLoading();
		confirmPg.waitLoading();
		boolean pass = confirmPg.checkBoatInfoDisableAttribute();
		if(!pass){
			throw new ErrorOnPageException("Vehicle boat info shold not edit in renew customer confirm page");
		}else{
			logger.info("Vehicle boat info can't ecit in renew customer confim page");
		}
	}
	/**
	 * set edit customer info.
	 */
	private void setEditCusomerInfo(){
		cust.status = "Active";
		cust.suffix = "SR";
		cust.hPhone = "4088333333";
		cust.bPhone = "4088222222";
		cust.mPhone = "4088111111";	
		cust.fax = "02178945";
		cust.email = "Liu@sina.com";	
		cust.custGender = "Male";
		cust.ethnicity = "Black";
		cust.solicitationIndcator = "Yes";
		
		cust.physicalAddr.address ="H"+ DateFunctions.getCurrentTime();
		cust.physicalAddr.supplementalAddr = "B" + DateFunctions.getCurrentTime();
		cust.physicalAddr.city = "Virginia Beach";
		cust.physicalAddr.state="Virginia";
		cust.physicalAddr.county="Virginia Beach city";
		cust.physicalAddr.zip = "23456";
		cust.physicalAddr.country="United States";	
		cust.physicalAddr.isValidate = true;
		cust.mailAddrAsPhy=false;
		
		cust.mailingAddr.address ="J"+DateFunctions.getCurrentTime();
		cust.mailingAddr.supplementalAddr = "N" + DateFunctions.getCurrentTime();
		cust.mailingAddr.city = "Houghton";
		cust.mailingAddr.state="Iowa";
		cust.mailingAddr.county="Lee";
		cust.mailingAddr.zip = "52631";
		cust.mailingAddr.country="United States";	
		cust.mailingAddr.isValidate = true;
	}
}
