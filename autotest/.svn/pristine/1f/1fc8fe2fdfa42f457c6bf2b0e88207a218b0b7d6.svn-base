/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CarrierInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyInventoryTrackingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *@Description: Search and view a supply POS product inventory tracking of supply fulfillment record.
 * 1.  Search by criteria not matched the known record
 * 2. Search by criteria matched the known record and check the supply fulfillment record
 * @Preconditions: 
 * 1. The supply product "InvDecreaseTracking" exists 
 * 		and the product has a price 
 * 		and been assigned to WAL-MART.
 * 
 * 2. The following features have been assigned to the role HF Administrator:
 * 			ViewInternalSupplierPOSSupplies
 * 			ViewWildlifePOSProductInventoryChangeHistory
 * 			SubmitSuppliesOrder
 * 			ApproveSuppliesOrder
 * 			FulfillSuppliesOrder
 * @SPEC: POS Product Inventory Tracking [TC:028968]
 * @Task#: Auto-1213
 * 
 * @author Lesley Wang
 * @Date  Sep 4, 2012
 */
public class ViewInventoryTracking_FulfillSupplies extends
		LicenseManagerTestCase {
	private TimeZone timeZone;
	private String adminLoc;
	private CarrierInfo carrier = new CarrierInfo();
	private StoreInfo store = new StoreInfo();
	private LoginInfo admLogin = new LoginInfo();
	private RoleInfo role = new RoleInfo();
	private AdminManager adm = AdminManager.getInstance();
	
	@Override
	public void execute() {
		// Go to Admin Manager to unassign "ViewExternalSupplierPOSSupplies" feature.
		adm.loginAdminMgr(admLogin);
		adm.assignOrUnAssignRoleFeature(role,false);
		adm.logoutAdminMgr();
		
		// Go to Order Suppliers page to make a supplier order.
		lm.loginLicenseManager(login);
		lm.gotoOrderSuppliesListPgFromTopMenu();
		lm.addSuppliesProductToCart(supply.code, supply.name, "1");
		supply.supplyOrderNum = lm.processOrderCart(pay);

		// Switch location, search the supplier order and fulfill the order
		lm.switchLocationInHomePage(adminLoc);
		lm.gotoSuppliesOrderPgFromTop();
		if (!lm.isAutoApproveSupplyOrderConfigOn(schema)) {
			lm.approveSupplyOrder(supply.supplyOrderNum, "1", "Approve Supply Order");
			lm.gotoSuppliesOrderSearchPgFromOrderApprovePg();
		}
		lm.fulfillOrder(carrier, store, supply.supplyOrderNum);
		supply.transactionDateTime = DateFunctions.getToday(timeZone);
		
		// Search with TransactionDateTo less than today and check the supply fulfill record doesn't exist
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		lm.gotoSupplyInventoryTrackingPgFromDetailsPg();
		
		// Search with TransactionDateTo less than today and check the inventory adjustment record doesn't exist
		String yesterday = DateFunctions.getDateAfterToday(-1, timeZone);
		this.searchAndViewInventoryAdjustmentRecord(yesterday, yesterday, false);
		
		// Search with TransactionDateFrom and TransactionDateTo equal to Today, 
		// and check the inventory adjustment record info
		this.searchAndViewInventoryAdjustmentRecord(supply.transactionDateTime, supply.transactionDateTime, true);

		// click supplier order link 
		this.checkSupplyOrderDetailsPgExistAfterClickOrderLink(supply.supplyOrderNum);
		
		lm.logOutLicenseManager();	
		
		// Go to Admin Manager to assign "ViewExternalSupplierPOSSupplies" feature back.
		adm.loginAdminMgr(admLogin);
		adm.assignOrUnAssignRoleFeature(role,true);
		adm.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		String loc = TestProperty.getProperty("ms.admin.location");
		adminLoc = "HF Administrator - Auto-" + loc;
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		store.storeName = "WAL-MART";
		store.storeID = lm.getAgentIDByNameFromDB(schema, store.storeName);
		
		// Login info
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + store.storeName;
		
		// Supply info
		supply.code = "DEC";
		supply.name = "InvDecreaseTracking";
		supply.supplyReceivedDate = "";
		supply.adjustmentType = "Fulfill Supplies";
		supply.adjustmentQuantity = "-1";
		supply.adjustmentUser = DataBaseFunctions.getLoginUserName(login.userName);
		supply.adjustmentLocation = loc;
		
		// Carrier Info
		carrier.trackingNumber = String.valueOf(DateFunctions.getCurrentTime());
		carrier.shippingDate = DateFunctions.getToday(timeZone);
		carrier.shippingNotes = "view fulfill record";
		
		// Login info for Admin Manager
		admLogin.url = AwoUtil.getOrmsURL(env);
		admLogin.userName = TestProperty.getProperty("orms.adm.user");
		admLogin.password = TestProperty.getProperty("orms.adm.pw");
		admLogin.contract = login.contract;
		admLogin.location = "Administrator - Auto/" + TestProperty.getProperty("ms.admin.location");
		
		// Role info 
		role.roleName = "HF Administrator - Auto";
		role.feature = "ViewExternalSupplierPOSSupplies";
		role.application = "LicenseManager";
		
		pay.payType = "Cash";
	}
	
	private void searchAndViewInventoryAdjustmentRecord(String fromDate, String toDate, boolean isExist) {
		LicMgrSupplyInventoryTrackingPage invTrackingPg = LicMgrSupplyInventoryTrackingPage.getInstance();
		// Search
		invTrackingPg.searchInventoryTrackingRecords(fromDate, toDate);
		if (isExist) {
			// check the first info
			invTrackingPg.verifySupplyInventoryAdjustmentInfo(supply);
		} else {
			int row = invTrackingPg.getRowIndexByTranscationDate(supply.transactionDateTime);
			if (row > -1) {
				throw new ErrorOnPageException("The inventory adjustment record should NOT exist!");
			}
			logger.info("Correct! No related inventory adjustment record exist!");
		}
	}
	
	private void checkSupplyOrderDetailsPgExistAfterClickOrderLink(String orderNum) {
		LicMgrSupplyInventoryTrackingPage invTrackingPg = LicMgrSupplyInventoryTrackingPage.getInstance();
		LicMgrSupplyOrderDetailsPage detailsPg = LicMgrSupplyOrderDetailsPage.getInstance();
		logger.info("Click Supply order link:" + orderNum + "to Supply Order Details page...");
		
		invTrackingPg.clickSuppliesOrderNum(orderNum);
		ajax.waitLoading();
		detailsPg.waitLoading();
		boolean result = MiscFunctions.compareResult("Order num", orderNum, detailsPg.getOrderNum());
		result &= MiscFunctions.compareResult("Fulfillment Status", "Fulfilled", detailsPg.getFulfillmentStatus());
		
		if (!result) {
			throw new ErrorOnPageException("Go to a wrong supply order details page! Check logger error.");
		}
		logger.info("Supply order details page is shown correctly");
	}
}
