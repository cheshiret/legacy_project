package com.activenetwork.qa.awo.testcases.sanity.orms;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Dealer vehicle type all transaction work correctly
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-1317
 * 
 * @author qchen
 * @Date  Oct 25, 2012
 */
public class LM_VehicleDealer extends LicenseManagerTestCase {

	private DealerInfo dealer = new DealerInfo();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
//	private LicMgrVehicleTitleDetialsInfoPage titleDetailsPage = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private Customer toCust = new Customer();
	private String reverseReason, reverseNote;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. register Dealer
		lm.registerVehicleToOrderCart(cust, dealer);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_REGISTER_VEHICLE);
		lm.processOrderToOrderSummary(pay);
		String ordNums = summaryPage.getAllOrdNums();
		String[] tokens=ordNums.split(" ");
		String ordNum=tokens[0];
		dealer.registration.miNum = summaryPage.getMINum();
		lm.finishOrder();
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//2. duplicate registration
		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
//		if (MiscFunctions.isQA24()) {
//			lm.duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(OrmsConstants.REPLACEMENT_PURCHASE_TYPE, dealer.registration.product);
//		}
//		else {
			lm.duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(OrmsConstants.DUPLICATE_PURCHASE_TYPE, dealer.registration.product);	
//		}
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_DUPLICATE_VEHICLE_REGISTRATION);
		String ordNum1 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		lm.verifyOrderStatus(ordNum1, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//3. transfer this registration
		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(toCust, dealer);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TRANSFER_REGISTRATION);
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyOrderStatus(ordNum1, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		lm.verifyOrderStatus(ordNum2, OrmsConstants.ORD_STATUS_ACTIVE, schema);
		
		//4. reverse transfer
		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
		lm.reverseTransferToOrderCartFromDetailsPage(reverseReason, reverseNote);
		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_REVERSE_TRANSFER_REGISTRATION + " (" + ordNum2 + ")");
		lm.processOrderCart(pay);
		lm.verifyOrderStatus(ordNum2, OrmsConstants.ORD_STATUS_VOIDED, schema);
		
//		//5. title this Dealer registration
//		lm.gotoVehicleDetailsPgByMiNum(dealer.registration.miNum);
//		lm.titleVehicleToOrderCartFromVehicleDetailPage(dealer);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_TITLE_VEHICLE);
//		lm.processOrderToOrderSummary(pay);
//		String ordNum3 = summaryPage.getAllOrdNums();
//		dealer.title.titleNum = summaryPage.getTitleNum();
//		lm.finishOrder();
//		lm.verifyOrderStatus(ordNum3, OrmsConstants.ORD_STATUS_ACTIVE, schema);
//		
//		//6. release Active lien
//		lm.gotoVehicleTitleDetailPage(dealer.title.titleNum);
//		lm.gotoVehicleLienListPage();
//		lm.releaseVehicleLien(dealer.title.lienInfo.getDateOfRelease());
//		
//		//7. 'Set to Transferable', 'Reactivate' and 'Surrender' title
//		lm.setTitleToTransferable();
//		titleDetailsPage.verifyVehicleTitleStatus("Transferable");
//		lm.reactivateTitle();
//		titleDetailsPage.verifyVehicleTitleStatus("Active");
//		lm.surrenderTitle();
//		titleDetailsPage.verifyVehicleTitleStatus("Surrendered");
//		lm.gotoHomePage();
//		
//		//8. reverse order
//		lm.voidRegisterVehicleOrder(ordNum3, reverseReason, reverseNote);
//		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum1, reverseReason, reverseNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum, reverseReason, reverseNote);
		lm.processOrderCart(pay);
	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
//		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "19810223";
		cust.identifier.country = "Canada";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.lName = "Test-Sanity10";
		cust.fName = "QA-Sanity10";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Feb 23 1981";
		cust.status = OrmsConstants.ACTIVE_STATUS;
		
		toCust.identifier.identifierType = "Green Card";
		toCust.identifier.identifierNum = "19810323";
		toCust.identifier.country = "Canada";
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;;
		toCust.lName = "Test-Sanity11";
		toCust.fName = "QA-Sanity11";
		toCust.residencyStatus = "Non Resident";
		toCust.dateOfBirth = "Mar 23 1981";
		toCust.status = OrmsConstants.ACTIVE_STATUS;
		
		dealer.type = "Dealer";
		//vehicle registration info
		dealer.registration.product = "404 - BOAT REG- DEALER NUMBER";
		
//		/*
//		 * vehicle title info
//		 */
//		dealer.title.status = OrmsConstants.ACTIVE_STATUS;
//		dealer.title.customer = cust.lName + ", " + cust.fName + " " + cust.custNum;
//		dealer.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
//		dealer.title.product = "TV2 - TitleVehicleDealer";
//		dealer.title.setDealerValue("300");
//		dealer.title.lienInfo.setDateOfLien(DateFunctions.getToday(timeZone));
//		dealer.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
//		dealer.title.lienInfo.setLienAmount("100");
//		
//		//lien company info
//		LienCompanyDetailsInfo company = new LienCompanyDetailsInfo();
//		company.isAddNew = false;
//		company.lienCompanyName = "A. D. LEWIS AND JAN LEWIS";		
//		dealer.title.lienInfo.setLienCompanyDetailsInfo(company);
//		dealer.title.lienInfo.setDateOfRelease(DateFunctions.getToday(timeZone));
		
		reverseReason = "14 - Other";
		reverseNote = "Automation Sanity";
	}
}
