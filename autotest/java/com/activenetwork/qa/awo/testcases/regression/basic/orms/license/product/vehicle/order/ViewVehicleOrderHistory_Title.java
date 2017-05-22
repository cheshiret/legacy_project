/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:view order history work flow for Title vehicle
 *              check point:
 *              1.view history for title vehicle reservation
 * @Preconditions:
 * @SPEC:View Vehicle Order History 
 * @Task#:AUTO-1000
 * 
 * @author szhou
 * @Date  Jun 1, 2012
 */
public class ViewVehicleOrderHistory_Title extends LicenseManagerTestCase{
	private OrderInfo order = new OrderInfo();
	private OrderHistory expectHistory = new OrderHistory();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private VehicleRTI regVehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		/* data prepared */
		if (!lm.verifyProductExistInSys(schema, regVehicleRTI.getPrdCode(), regVehicleRTI.getPrdName())) {
			// add vehicle product
			lm.gotoVehicleSearchListPageFromTopMenu();
			lm.addVehicleProduct(regVehicleRTI);
			// add price
			ILicMgrProductPricingPage pricingPage = lm
					.gotoProductPricingPageFromListPage(pricing.prodType,
							pricing.prodCode);
			lm.addPricingForProduct(pricing, pricingPage);
			// assign location
			lm.gotoVehicleProductDetailsPage(regVehicleRTI.getPrdCode());
			lm.assignVehicleToStoresThruLocationClass("06-State Parks Agent");
			lm.gotoHomePage();
		}
		if (!lm.verifyProductExistInSys(schema, titleVehicleRTI.getPrdCode(), titleVehicleRTI.getPrdName())) {
			// add vehicle product for title
			lm.gotoVehicleSearchListPageFromTopMenu();
			lm.addVehicleProduct(titleVehicleRTI);
			// add price
			pricing.prodCode=titleVehicleRTI.getPrdCode();
			ILicMgrProductPricingPage pricingPage = lm
					.gotoProductPricingPageFromListPage(pricing.prodType,
							pricing.prodCode);
			lm.addPricingForProduct(pricing, pricingPage);
			// assign location
			lm.gotoVehicleProductDetailsPage(titleVehicleRTI.getPrdCode());
			lm.assignVehicleToStoresThruLocationClass("06-State Parks Agent");
			lm.gotoHomePage();
		}
		
		lm.switchLocationInHomePage("HF HQ Role - Auto-WAL-MART");
		// registration vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderToOrderSummary(pay);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();	
		lm.finishOrder();
		
		// title vehicle 
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
	    lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		order.orderNum = lm.processOrderCart(pay).split(" ")[0];

		/* go to vehicle order history page */
		lm.gotoVehicleOrderDetailPage(order.orderNum);
		lm.gotoVehicleOrderHistoryPage();

		/* verify vehicle order history */
		this.setHistoryValueForRegister(titleVehicleRTI.getPrdName());
        this.compareHistoryData(expectHistory);
		lm.logOutLicenseManager();
	}
	
	private void setHistoryValueForRegister(String prd){
		String[] info = lm.getRegisterVehicleOrderInfoUsingPrdname(schema, prd).get(0);
		
		expectHistory.transaction=TRANNAME_TITLE_VEHICLE;
		expectHistory.infoTransaction ="Title #: "+info[5]+",Title ID: "+info[18];
		expectHistory.transactionLocation="WAL-MART";
		expectHistory.user=DataBaseFunctions.getLoginUserName(login.userName);
	}

	private void compareHistoryData(OrderHistory history) {
		LicMgrVehicleOrderHistoryPage historyPage = LicMgrVehicleOrderHistoryPage
				.getInstance();
		
		OrderHistory actually=historyPage.getHistoryInfos().get(1);
		boolean result=true;
		result=MiscFunctions.compareResult("Transaction", history.transaction, actually.transaction);
		result&=MiscFunctions.compareResult("Information at time of transaction", history.infoTransaction , actually.infoTransaction );
		result&=MiscFunctions.compareResult("Transaction Location", history.transactionLocation, actually.transactionLocation);
		result&=MiscFunctions.compareResult("User", history.user, actually.user);
		
		if(!result){
			throw new ErrorOnPageException("Data comparing is not correct,please check the log file...");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* add vehicle */
		regVehicleRTI.setPrdCode("VRC");
		regVehicleRTI.setPrdName("ViewOrderHistoryTR");
		regVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		regVehicleRTI.setPrdGroup("Registration");
		regVehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		regVehicleRTI.setCustClass(map);
		regVehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		regVehicleRTI.setValidMonths("2");
		regVehicleRTI.setAdvanceRenewalDays("1");
		regVehicleRTI.setLateRenewal("1");
		regVehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map1 = new HashMap<String, Boolean>();
		map1.put("Personal Pleasure", true);
		regVehicleRTI.setBoatUseTyp(map1);
		regVehicleRTI.setMinLenthOfFt("7");
		regVehicleRTI.setMinLenthOfIn("5");
		regVehicleRTI.setMaxLenthOfFt("20");
		regVehicleRTI.setMaxLenthOfIn("14");
		
		titleVehicleRTI.setPrdCode("VHC");
		titleVehicleRTI.setPrdName("ViewOrderHistoryTT");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map2 = new HashMap<String, Boolean>();
		map2.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		titleVehicleRTI.setCustClass(map2);
		titleVehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		titleVehicleRTI.setValidMonths("2");
		titleVehicleRTI.setAdvanceRenewalDays("1");
		titleVehicleRTI.setLateRenewal("1");
		titleVehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map3 = new HashMap<String, Boolean>();
		map3.put("Personal Pleasure", true);
		titleVehicleRTI.setBoatUseTyp(map3);
		titleVehicleRTI.setMinLenthOfFt("7");
		titleVehicleRTI.setMinLenthOfIn("5");
		titleVehicleRTI.setMaxLenthOfFt("20");
		titleVehicleRTI.setMaxLenthOfIn("14");

		/* register vehicle */
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
		vehicle.registration.product = regVehicleRTI.getPrdCode() + " - " + regVehicleRTI.getPrdName();
		
		/* title vehicle */
		vehicle.title.boatValue="45";
		vehicle.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		vehicle.title.purchaseType = "Original";

		pricing.prodType = "Vehicle";
		pricing.prodCode = regVehicleRTI.getPrdCode();
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(365);
		pricing.vendorFee = "20.00";
		pricing.stateTransFee = "15.00";
		pricing.stateFee = "10.00";
		pricing.transFee = "3.00";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000019";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic19";
		cust.fName = "QA-Basic19";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
