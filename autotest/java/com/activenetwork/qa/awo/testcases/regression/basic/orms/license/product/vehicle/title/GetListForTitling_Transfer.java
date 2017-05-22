package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.HashMap;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclePricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the system retrieves vehicle products which are satisfied conditions list for transferred titling
 * @Preconditions: this case will automatically prepare all needed precondition data
 * @SPEC: <<Get Vehicle Product List for Titling.UCS>> TC:004865
 * @Task#: AUTO-1008
 * 
 * @author qchen
 * @Date  Jun 29, 2012
 */
public class GetListForTitling_Transfer extends LicenseManagerTestCase{
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private BoatInfo boat = new BoatInfo();
	private Customer transferToCust = new Customer();
	private LicMgrVehiclePricingPage pricingPage = LicMgrVehiclePricingPage.getInstance();
	private PricingInfo titlePricing = new PricingInfo();
	private String locationClass, salesLocation, adminLocation;
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private TimeZone timeZone;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVehicleSearchListPageFromTopMenu();
		//create vehicle product('Title'), pricing, agent assignment
		lm.addVehicleProduct(titleVehicleRTI);
		lm.gotoVehicleProductDetailsPageFromListPage(titleVehicleRTI.getPrdCode());
		lm.addPricingForProduct(titlePricing, pricingPage);
		lm.assignVehicleToStoresThruLocationClass(locationClass);
		
		//switch to sales location in Home page
		lm.switchLocationInHomePage(salesLocation);
		//register vehicle
		lm.registerVehicleToOrderCart(cust, boat);
		lm.processOrderToOrderSummary(pay);
		boat.registration.miNum = summaryPage.getMINum();
		lm.finishOrder();
		
		//title vehicle
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(boat);
		lm.processOrderToOrderSummary(pay);
		boat.title.titleNum = summaryPage.getTitleNum();
		lm.finishOrder();
		
		/**
		 * Transfer process
		 */
		//set to transferable for title
		lm.gotoVehicleTitleDetailPage(boat.title.titleNum);
		LicMgrVehicleTitleDetialsInfoPage.getInstance().setToTransferable();
		
		//go to vehicle detail to transfer(registration & title)
		lm.gotoVehicleDetailsPgByMiNum(boat.registration.miNum);
		lm.transferVehicleToDetailsPage(transferToCust);
		lm.setupTransferVehicleDetailInfoToProductSelectionWidget(boat);
		//verify needed to transfer title vehicle product existing in the list
		boat.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		boat.title.purchaseType = OrmsConstants.TRANSFER_PURCHASE_TYPE;
		LicMgrVehicleRegistrationWidget.getInstance().
				verifyProductExistingInList(boat.title.product, boat.title.purchaseType, true);//IMPORTANT
		lm.selectTransferProductsToOrderCart(boat.registration.product, boat.title.product);
		lm.processOrderToOrderSummary(pay);
		boat.title.titleNum = summaryPage.getTitleNum();
		String ordNum = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		//clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum, boat.operationReason, boat.operationNote);
		lm.processOrderCart(pay);
		
		//switch to admin location to deactivate vehicle product
		lm.switchLocationInHomePage(adminLocation);
		lm.deactivateVehicleProduct(titleVehicleRTI.getPrdCode());
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		adminLocation = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		locationClass = "06-State Parks Agent";
		
		//title vehicle info
		titleVehicleRTI.setPrdCode(StringUtil.getRandomString(3, true));
		titleVehicleRTI.setPrdName("TransferTitleVehicleBoat");
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Boat");
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		titleVehicleRTI.setCustClass(map);
		
		//title vehicle pricing info
		titlePricing.prodType = "Vehicle";
		titlePricing.status = OrmsConstants.ACTIVE_STATUS;
		titlePricing.locationClass = locationClass.replaceAll("-", " - ");
		titlePricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		titlePricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		titlePricing.purchaseType = OrmsConstants.TRANSFER_PURCHASE_TYPE;//transfer
		titlePricing.effectFrom = DateFunctions.getToday(timeZone);
		titlePricing.effectTo = DateFunctions.getDateAfterGivenDay(titlePricing.effectFrom, 1);
		titlePricing.vendorFee = "1";
		titlePricing.stateTransFee = "1";
		titlePricing.stateFee = "1";
		titlePricing.transFee = "1";
		
		//vehicle info including Registration and Title info
		boat.hullIdSerialNum = "GetList" + DateFunctions.getCurrentTime();
		boat.manufacturerName = "Sony";
		boat.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		boat.feet = "99";
		boat.inches = "9";
		boat.hullMaterial = "Steel";
		boat.boatUse = "PLEASURE";
		boat.propulsion = "OTHER";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Jet Boat";
		//vehicle registration info
		boat.registration.product = "RV1 - RegisterVehicleBoat";
		//vehicle title info
		boat.title.product = "TV1 - TitleVehicleBoat";
		boat.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		boat.title.boatValue = "798";
		
		//original customer info
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "968695432";
		cust.lName = "TEST-GetListTitle1";
		cust.fName = "QA-GetListTitle1";
		cust.dateOfBirth = "Jun 09 1988";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		
		//transfer customer info
		transferToCust.identifier.identifierType = "Passport";
		transferToCust.identifier.identifierNum = "444123";
		transferToCust.lName = "TEST-DailySales";
		transferToCust.fName = "QA-Location";
		transferToCust.dateOfBirth = "Jun 01 1984";
		transferToCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
	}
}
