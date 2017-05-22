/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: During register vehicle transaction, Add Vehicle / Edit Vehicle /Calculate Vehicle Order Price 
 * @Preconditions: 
* 				 A Vehicle product: V03-ProcessFeeAdjustments
 * 				
 * @SPEC:  Add Vehicle / Edit Vehicle /Calculate Vehicle Order Price
 * @Task#: AUTO-993
 * 
 * @author pzhu
 * @Date  May 29, 2012
 * 
 */
public class VehicleOrder extends LicenseManagerTestCase {


	private LoginInfo loginfFnm = new LoginInfo();
	OrmsOrderSummaryPage omOrdSumPg = OrmsOrderSummaryPage.getInstance();
	
	private RegistrationInfo registration = new RegistrationInfo();
	List<String[]> raFeeRecordBeforeCancel;
	List<String[]> raFeeRecordAfterCancel;

	private Customer[] cust = new Customer[2];
	private BoatInfo[] veh = new BoatInfo[2];
	private BoatInfo newVehicle = new BoatInfo();
	private boolean HnF = true;//For hunting and fishing
	private int ORDER_SIZE = 2;
	private String TOTAL_PRICE = "";
	
	
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);

		// make first registration of vehicle order.
		lm.registerVehicleToOrderCart(cust[0], veh[0]);
		
		//Test step 1: add new vehicle to order
		lm.addVehicleInOrderCartPage(cust[1], veh[1]);
		lm.processOrderToOrderSummary(pay);
		String[] orders = omOrdSumPg.getOrdNumsArray(this.HnF);
		
		//Check point 1: there should be 2 orders:
		if(this.ORDER_SIZE!=orders.length)
		{
			throw new TestCaseFailedException("Checking order size failed, on page there is "+orders.length+"order(s), there should be "+this.ORDER_SIZE);
		}
		logger.info("New register vehicle order number is "+orders[0]+" and "+orders[1]+", Check point 1 passed");
		
		
		//Check point 2: total price should be 40.0
		this.checkTotalPrice(TOTAL_PRICE);
		lm.finishOrder();
		
	
		//Test step 3: Edit new vehicle
		lm.gotoVehiclesDetailsPgBySerialNum(veh[0].hullIdSerialNum);
		lm.editVehicleInfo(newVehicle, "AUTO-929");
		lm.gotoHomePage();
		
		//Check point 3: Check edit result
		this.checkEditResult();
				
		
		//log out LM
		//lm.gotoHomePage();
		lm.logOutLicenseManager();
	}
	
	private void checkEditResult() {
		LicMgrVehicleDetailPage vehDetailPg = LicMgrVehicleDetailPage.getInstance();
		lm.gotoVehiclesDetailsPgBySerialNum(veh[0].hullIdSerialNum);
		BoatInfo result = vehDetailPg.getVehicleInfo();

		if(!result.status.equalsIgnoreCase(newVehicle.status))
		{
			throw new ErrorOnPageException("Checking edit result failed, status on page is "+result.status+", should be "+newVehicle.status );
		}
		
		if(!result.movedToState.equalsIgnoreCase(newVehicle.movedToState ))
		{
			throw new ErrorOnPageException("Checking edit result failed, movedToState on page is "+result.movedToState+", should be "+newVehicle.movedToState );
		}
		
		if(!result.boatUse.equalsIgnoreCase(newVehicle.boatUse ))
		{
			throw new ErrorOnPageException("Checking edit result failed, boatUse on page is "+result.boatUse+", should be "+newVehicle.boatUse );
		}
		logger.info("Check point 3 passed");
		
	}

	/**
	 * @param totalPrice
	 */
	private void checkTotalPrice(String totalPrice) {
		
		String total = omOrdSumPg.getTotalPrice(this.HnF);
		if(!(StringUtil.refactAmountValue(total).equalsIgnoreCase(StringUtil.refactAmountValue(totalPrice))))
		{
			throw new ErrorOnPageException("Checking total price failed, on page are "+total+", should be "+totalPrice); 
		}
		logger.info("Checking total price, on page are "+total+", should be "+totalPrice+" . Check point 2 passed");
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

		loginfFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginfFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginfFnm.contract = "MS Contract";
		loginfFnm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		veh[0] = new BoatInfo();
		veh[0].type = "Boat";
		veh[0].hullIdSerialNum = "VehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		veh[0].manufacturerName = "YAMA";
		veh[0].modelYear = "2012";
		veh[0].feet = "2";  //this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[0].inches = "2";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[0].hullMaterial = "Other";
		veh[0].boatUse = "Other";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[0].propulsion = "Other";
		veh[0].fuelType = "Other";
		veh[0].typeOfBoat = "Other";
		veh[0].builtYear = "2012";
		//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		//there are two blank spaces between CD(V03) and name(ProcessFeeAdjustments)
		registration.product = "V03 - ProcessFeeAdjustments";
		veh[0].registration = null;
		veh[0].registration = registration;

		veh[1] = new BoatInfo();
		veh[1].type = "Boat";
		veh[1].hullIdSerialNum = "VehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		veh[1].manufacturerName = "YAMA";
		veh[1].modelYear = "2012";
		veh[1].feet = "2";  //this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[1].inches = "2";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[1].hullMaterial = "Other";
		veh[1].boatUse = "Other";//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		veh[1].propulsion = "Other";
		veh[1].fuelType = "Other";
		veh[1].typeOfBoat = "Other";
		veh[1].builtYear = "2012";
		//this value should be equal to 'AddVehicleProduct.datapool', record 26(code: V03, name: ProcessFeeAdjustments)
		//there are two blank spaces between CD(V03) and name(ProcessFeeAdjustments)
		registration.product = "V03 - ProcessFeeAdjustments";
		veh[1].registration = registration;
		
		newVehicle.status = "Moved";
		newVehicle.movedToState = "Hawaii";
		newVehicle.boatUse = "OTHER";
		
		cust[0] = new Customer();
		cust[0].lName = "TEST-RAFee5";
		cust[0].fName = "QA-RAFee5";
		cust[0].dateOfBirth = "Jun 01 1980";
		
		cust[1] = new Customer();
		cust[1].lName = "TEST-RAFee1";
		cust[1].fName = "QA-RAFee1";
		cust[1].dateOfBirth = "Jun 01 1980";

		pay.payType = "Cash";
		TOTAL_PRICE = "$40.00";//(State Fee+Vendor Fee+Transaction Fee)*2=(5+10+5)*2=40// refer to 'AddPricing.datapool', record 139
		TOTAL_PRICE = "$" + new BigDecimal(lm.getVehCustPrice(schema, "V03", "1", "1")).multiply(new BigDecimal(2)).setScale(2);
	}
}
