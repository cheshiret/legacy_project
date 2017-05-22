package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the details info including title info, vehicle info and customer info in Vehicle Title details page
 * 							after titling a Dealer vehicle
 * @Preconditions:1. an existing Dealer registration product
 * 							2. an existing Dealer title product
 * 							DEFECT-35586
 * @SPEC: <<Title Vehicle.UCS>> TC:004712
 * @Task#: AUTO-1008
 * 
 * @author qchen
 * @Date  Jul 10, 2012
 */
public class TitleDealer extends LicenseManagerTestCase {

	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private VehicleRTI regVehicleRTI = new VehicleRTI();
	private DealerInfo dealer = new DealerInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//register vehicle
		lm.registerVehicleToOrderCart(cust, dealer);
		lm.processOrderToOrderSummary(pay);
		dealer.registration.miNum = summaryPage.getMINum();
		String ordNum = summaryPage.getAllOrdNums();
		lm.finishOrder();
		
		//title vehicle
		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(dealer);
		this.verifyTransactionType();
		lm.processOrderToOrderSummary(pay);
		dealer.title.titleNum = summaryPage.getTitleNum();
		String ordNum2 = summaryPage.getAllOrdNums();
		lm.finishOrder();
		
		//verify vehicle title list info
		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
		lm.gotoVehicleTitleTabPage();
		dealer.title.titleId = lm.getVehicleTitleIDByMiNum(schema, dealer.registration.miNum);
		dealer.id = lm.getVehicleIDByMiNum(dealer.registration.miNum, schema);
		this.verifyVehicleTitleListInfo(dealer.title);

		//verify vehicle title detail info
		lm.gotoVehicleTitleDetailPgFromTitleList(dealer.title.titleId);
		this.verifyVehicleTitleDetailInfo(dealer.title, dealer, cust);
		
		//clean up - void order
		lm.releaseVehicleLien(dealer.title.lienInfo.getDateOfRelease());//before reverse title order, have to release lien first
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum2, dealer.operationReason, dealer.operationNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum, dealer.operationReason, dealer.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		regVehicleRTI.setPrdCode("RV2");
		regVehicleRTI.setPrdName("RegisterVehicleDealer");
		regVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		regVehicleRTI.setPrdGroup("Registration");
		regVehicleRTI.setVehicleType("Dealer");
		
		titleVehicleRTI.setPrdCode("TV2");
		titleVehicleRTI.setPrdName("TitleVehicleDealer");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Dealer");
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000024";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic24";
		cust.fName = "QA-Basic24";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.custNum = lm.getCustomerNum(cust, schema);
		cust.status = OrmsConstants.ACTIVE_STATUS;
		
		dealer.type = "Dealer";
		dealer.status = OrmsConstants.ACTIVE_STATUS;
		//vehicle registration info
		dealer.registration.product = regVehicleRTI.getPrdCode() + " - " + regVehicleRTI.getPrdName();
		
		/*
		 * vehicle title info
		 */
		dealer.title.status = OrmsConstants.ACTIVE_STATUS;
		dealer.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
		dealer.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
		dealer.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		dealer.title.setDealerValue("300");
		dealer.title.lienInfo.setDateOfLien(DateFunctions.getToday(timeZone));
		dealer.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		dealer.title.lienInfo.setLienAmount("100");
		
		//lien company info
		LienCompanyDetailsInfo company = new LienCompanyDetailsInfo();
		company.isAddNew = true;
		company.isSameAsAbove = false;
		company.lienCompanyName = "LienCompany-" + DateFunctions.getCurrentTime();
		company.address = "Keji 2nd Road";
		company.city = "Xian";
		company.state = "Mississippi";
		company.zip = "36918";
		company.country = "United States";
		company.contactPhone = "02968685958";
		
		dealer.title.lienInfo.setLienCompanyDetailsInfo(company);
		dealer.title.numOfDuplicates = "0";
		dealer.title.numOfCorrections = "0";
	}
	
	/**
	 * verify transaction type info in order cart page
	 */
	private void verifyTransactionType() {
		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify Transaction Type is correct in order cart page.");
		String transactionOnPage = orderCartPage.getFirstTransactionName().split("\\(")[0].trim();
		if(!transactionOnPage.equals(OrmsConstants.TRANNAME_TITLE_VEHICLE)) {
			throw new ErrorOnPageException("Transaction type should be " + OrmsConstants.TRANNAME_TITLE_VEHICLE + ", but actual is: " + transactionOnPage);
		} else logger.info("The transaction type is really " + transactionOnPage);
	}
	
	/**
	 * verify vehicle title list info in Vehicle Details - Title sub page
	 * @param expected
	 */
	private void verifyVehicleTitleListInfo(TitleInfo expected) {
		LicMgrVehicleTitleListPage titleListPage = LicMgrVehicleTitleListPage.getInstance();
		
		logger.info("Verify vehicle title list info in Vehicle-Title sub page.");
		boolean result = titleListPage.compareTitleListInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Vehicle title list info is incorrect.");
		} else logger.info("Vehicle title list info is correct.");
	}
	
	/**
	 * verify vehicle title details info in vehicle title detail page
	 * @param expectTitle
	 * @param expectedVehicle
	 * @param expectedCust
	 */
	private void verifyVehicleTitleDetailInfo(TitleInfo expectTitle, DealerInfo expectedVehicle, Customer expectedCust) {
		LicMgrVehicleTitleDetialsInfoPage titleDetailPage = LicMgrVehicleTitleDetialsInfoPage.getInstance();
		
		logger.info("Verify vehicle title detial info in Title detail page.");
		boolean result = titleDetailPage.compareVehicleTitleDetailsInfo(expectTitle);
		result &= titleDetailPage.compareVehicleInfo(expectedVehicle);
		result &= titleDetailPage.compareVehicleCustomerInfo(expectedCust);
		
		if(!result) {
			throw new ErrorOnPageException("Vehicle title detail info is incorrect.");
		} else logger.info("Vehicle title detail info is correct.");
	}
}
