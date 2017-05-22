package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Inspect vehicle from license manager, verify order cart info and order summary info 
 * @Preconditions: 
 *               1. A vehicle inspection product: VOI - View Inspection Order Det
 *               2. this vehicle inspection product should have original fee
 *               3. this product should be assign to WAL-MART agent
 * @SPEC:  Order Cart - Vehicle.UI and Order Summary - Vehicle.UI
 * @Task#: AUTO-1001
 * 
 * @author vzhang
 * @Date  Jun 6, 2012
 * 
 */
public class OrderCartAndSummary_Inspection extends LicenseManagerTestCase{
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private String productItemInfo,licenseYear;
	private BoatInfo vehicle = new BoatInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		
		lm.inspectVehicleToOrderCartFromHomePg(cust, vehicle);
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		
		//verify order cart info
		this.verifyItemInfoInOrderCartPg();
		//verify order summary info
		lm.processOrderCartToOrderSummaryPage(pay, false);
		this.verifyItemInfoInOrderSummaryPg();
		
		lm.finishOrder();	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.fName = "QA-VehicleOrdCart";
		cust.lName = "Test-VehicleOrdCart";
		cust.dateOfBirth = "Jan 01 1980";
		cust.licenseType = "MDWFP #";
		cust.mailingAddr.address = "aac Street";
		cust.mailingAddr.supplementalAddr = "Auto test";
		cust.mailingAddr.zip = "12020";
		cust.mailingAddr.city = "Ballston Spa";
		cust.mailingAddr.state = "New York";
		cust.hPhone = "12345";
		cust.email ="";
		cust.organization = "";
		
		vehicle = new BoatInfo();
		vehicle.inspection.setAddress("Auto test for inspection ord");
		vehicle.inspection.setZip("39269");
		vehicle.inspection.setCountry("United States");
		vehicle.inspection.setCity("Jackson");
		vehicle.inspection.setState("Mississippi");
		vehicle.inspection.setCounty("Hinds");
		vehicle.inspection.setDayPhone("123456");
		vehicle.inspection.setInspectionReason("Boat Description");
//		vehicle.inspection.setProduct("VOI - View Inspection Order Det");
		vehicle.inspection.setProduct("VIO - ViewInspectionsOrder");
		vehicle.type = "Boat";
		
		licenseYear = lm.getFiscalYear(schema);
		productItemInfo = "(" + licenseYear + ")" + vehicle.inspection.getProduct().replace(" - ", "-") + "(Original)";
	}
	
	private void verifyItemInfoInOrderCartPg(){
		logger.info("Verify order items info in order cart page.");
		String expValue;
		boolean result = true;		
		
		//verify transaction name
		//expValue = "Inspect Vehicle";
		expValue = "Inspection";
		result &= ormsOrderCartPg.verifyTransactionName(expValue);
		
		//verify order item info
		result &= ormsOrderCartPg.verifyProductItemInfo(productItemInfo);
		
		//verify customer info
		result &= ormsOrderCartPg.verifyCustomerInfo(cust, null);
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order cart page, please check error log.");
		}
	}
	
	private void verifyItemInfoInOrderSummaryPg(){
		logger.info("Verify order items info in order summary page.");
		String expValue;
		boolean result = true;
		
		//verify transaction name
		//expValue = "Inspect Vehicle, Make Payment";
		expValue = "Inspection, Make Payment";
		result &= lmOrdSumPg.verifyTransactionInfo(expValue);
		
		//verify order item info
		result &= lmOrdSumPg.verifyProductItemInfo(productItemInfo);
		
		//verify customer info
		result &= lmOrdSumPg.verifyCustomerInfo(cust, null);
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
	}

}
