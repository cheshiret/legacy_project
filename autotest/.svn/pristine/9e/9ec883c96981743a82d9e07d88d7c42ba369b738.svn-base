package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreInventoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewSoldPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: view sold privilege inventory list info
 * @Preconditions: 1. Vendor - 'Auto Vendor', store - 'WAL-MART'
 * 							2. Privilege Inventory: inventory type - 'ViewStoreSoldInventory'
 * 							3. Privilege Product: code - 'VSS', name - 'ViewStoreSoldInventory', allocated with above privilege inventory
 * @SPEC: View Privilege Inventory Sold at Store [TC:019666]
 * @Task#: AUTO-1216
 * 
 * @author qchen
 * @Date  Aug 23, 2012
 */
public class ViewSold extends LicenseManagerTestCase {
	
	private String adminLocation, vendorName, storeName;
	private LicMgrStoreViewSoldPrivilegeInventoryWidget soldInventoryWidget = LicMgrStoreViewSoldPrivilegeInventoryWidget.getInstance();
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private List<String> expected = new ArrayList<String>();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		privInventory.inventoryNumber = lm.getPrivilegeInventoryNumByOrdNum(schema, orderNum);
		expected.add(2, privInventory.inventoryNumber);
		expected.add(4, orderNum);
		
		//switch to admin location to view store sold privilege inventory
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		lm.gotoViewSoldPrivilegeInventory();
		soldInventoryWidget.searchSoldInventory(privInventory, cust.lName, cust.custNum);
		List<String> soldInventoryOnPage = soldInventoryWidget.getAllSoldInventories();
		this.compareSoldInventoryInfo(expected, soldInventoryOnPage);
		this.gotoInventoryTabFromViewSoldInventoryWidget();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		vendorName = "Auto Vendor";
		storeName = "WAL-MART";
		String storeId = lm.getAgentID(schema, storeName);
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + storeName;
		adminLocation = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//privilege inventory info
		privInventory.inventoryType = "ViewStoreSoldInventory";
		privInventory.inventoryTypeStatus = OrmsConstants.ACTIVE_STATUS;
		privInventory.licenseYear = lm.getFiscalYear(schema);
//		privInventory.inventoryStatus = OrmsConstants.PRIV_INV_STATUS_SOLD;
		privInventory.inventoryStatus = OrmsConstants.PRIV_INV_STATUS_USED;// updated by Nicole, Jue 24, 30402
		privInventory.agentID = storeId;
		privInventory.agentName = storeName;
		
		//privilege product info
		privilege.code = "VSS";
		privilege.name = "ViewStoreSoldInventory";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = privInventory.licenseYear;
		
		//customer info
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000025";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic25";
		cust.fName = "QA-Basic25";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		//expected sold privilege inventory info
		expected.add(privInventory.inventoryType);
		expected.add(privInventory.licenseYear);
		expected.add(privInventory.inventoryStatus);
		expected.add(DateFunctions.getToday(timeZone));
		expected.add(cust.lName + ", " + cust.fName);
		expected.add(cust.custNum);
	}
	
	private void compareSoldInventoryInfo(List<String> expected, List<String> actual) {
		boolean result = soldInventoryWidget.compareSoldInventory(expected, actual);
		if(!result) {
			throw new ErrorOnPageException("Sold Privilege Inventory info is incorrect.");
		} else logger.info("Sold Privilege Inventory info is correct.");
	}
	
	private void gotoInventoryTabFromViewSoldInventoryWidget() {
		soldInventoryWidget.clickOK();
		ajax.waitLoading();
		LicMgrStoreInventoryPage.getInstance().waitLoading();
	}
}
