package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify mark privilege inventory as sold, and release sold privilege inventory
 * @Preconditions:Have an exiting privilege inventory info and Privilege:
 * 1. privilege inventory info:
 *   inventory type =  "For Pri Inv Sold";
 *   inventory type license year info: License year = current year;
 *   privilege inventory info: inventory issue From number = 001; inventory issue to number = 010
 *   and allocate this privilege inventory to one vendor and store: vendor = Auto555, store = WAL-MART
 * 2. privilege info:
 * privilege code = "PI1", privilege name = "MarkInvTypeSoldAndReleaseSold", privilege inventory = "For Pri Inv Sold"
 * @SPEC:Mark Privilege Inventory as Sold.UIS, Release Sold Privilege Inventory.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 21, 2012
 */
public class MarkPriInvSoldAndReleaseSold extends LicenseManagerTestCase{
	private String adminLocation=null;
	private String sellLocation=null;
	private PrivilegeInventory privilegeInventory = new PrivilegeInventory();
	private PriInventoryItemInfo priInventoryItem = new PriInventoryItemInfo();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String agentID = "", agentName = "";

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// clean up to avoid no inventory to purchase
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		/**
		 * Make a new privilege order
		 */
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);	
		String privilegeOrdNum = lm.processOrderCart(pay,false);
		if(privilegeOrdNum.contains(" ")){
			privilegeOrdNum = privilegeOrdNum.split(" ")[0];
		}
		
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		String privilegeItemNums = privOrderDetailsPage.getAllPrivilegesNum();
		
		/**
		 * verify privilege inventory info, status should be sold for inventory number = <<number1>>
		 */
		this.gotoPrivilegeInventoryPageInAdmin();		
		priInventoryItem.inventoryNum = privilege.inventoryNum;
		priInventoryItem.status = PRIV_INV_STATUS_USED;
		priInventoryItem.order = privilegeOrdNum;
		priInventoryItem.privielgeNumber = privilegeItemNums;
		this.verifyInventoryInfo(priInventoryItem);
		
		lm.switchLocationInHomePage(sellLocation);
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay,false);
		
		/**
		 * verify privilege inventory info, status should be Available for inventory number = <<number1>>
		 */
		this.gotoPrivilegeInventoryPageInAdmin();
		priInventoryItem.inventoryNum = privilege.inventoryNum;
		priInventoryItem.status = "Available";
		priInventoryItem.order = "";
		priInventoryItem.privielgeNumber = "";
		this.verifyInventoryInfo(priInventoryItem);
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);

		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		adminLocation="HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		sellLocation="HF HQ Role - Auto-WAL-MART";
		
		privilege.invType = "For Pri Inv Sold";
		privilege.purchasingName = "PI1-MarkInvTypeSoldAndRelease";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "";
		privilege.operateNote = "QA Automation";
		privilege.licenseYearForSearch = privilege.licenseYear;
		privilege.searchStatus = new String[]{ACTIVE_STATUS};
		
		privilegeInventory.inventoryType = privilege.invType;
		privilegeInventory.inventoryTypeStatus = "Active";
		privilegeInventory.licenseYear = privilege.licenseYear;	
		
		agentName = login.location.split("/")[1];
		agentID = lm.getAgentID(schema, agentName);
		priInventoryItem.agentInfo = agentID + " - " + agentName;
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule2";
		cust.lName = "TEST-TransferRule2";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
	}
	
	private void gotoPrivilegeInventoryPageInAdmin(){
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
		privilegeInventory.inventoryNumFrom = privilege.inventoryNum;
		privilegeInventory.inventoryNumTo = privilege.inventoryNum;
		lm.searchPrivilegeInventory(privilegeInventory);		
	}
	
	private void verifyInventoryInfo(PriInventoryItemInfo expInventoryInfo){
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage.getInstance();
		boolean result = true;
		logger.info("Verify inventory info.");
		
		result &= privilegeInventoryPg.verifyPrivilegeInventoryInfo(expInventoryInfo);
		
		if(!result){
			throw new ErrorOnPageException("Inventory list info is not correct, please check error log.");
		}else {
			logger.info("Inventory list info is corret from UI.");
		}		
	}
}
