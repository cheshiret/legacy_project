package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
/**
 * @Description: (P)
 * 1. Make privilege which has one harvest document, print the document
 * 2. Go to Harvest tab to check Harvest 'Pending' status
 * 3. Go to privilege order details page to check Privilege order item 'Active' status
 * 4. Reverse privilege
 * 5. Go to Harvest tab to check Harvest 'Reversed' status
 * 6. Go to privilege order details page to check 'Reversed' privilege order
 * 
 * @Preconditions:
 * 1. Have Harvest template: Harvest DOC
 * 2. Have privilege product: "VHR-HarvestPrivilege"
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author SWang
 * @Date  2012-3-29
 * @Note Verified DEFECT-34121
 */
public class ReversePrivilegeHarvest extends LicenseManagerTestCase {
	private Harvest harvest = new Harvest();
	private Harvest searchHarvest = new Harvest();
	private LicMgrCustomerHarvestPage harvestPage = LicMgrCustomerHarvestPage.getInstance();
	private String orderNum;
	private String[] privilegeNums;

	public void execute() {
		lm.loginLicenseManager(login);

		//Make a privilege which has Harvest document order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay, false).split(" ")[0];

		//Go to Harvest tab to check Harvest 'Pending' status
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		this.verifyHarvestStatus(orderNum, "Pending");

		//Go to privilege order details page to check Privilege order item 'Active' status
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName, "Active");

		//Reverse privilege
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to Harvest tab to check Harvest 'Reversed' status
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Harvest");
		this.verifyHarvestStatus(orderNum, "Reversed");

		//Go to privilege order details page to check 'Reversed' privilege order
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName, "Reversed");

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//privilege product info
		privilege.code = "VHR";
		privilege.purchasingName = "VHR-HarvestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12342";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

	/**
	 * Verify harvest status according to order number
	 * @param orderNum
	 * @param harvestStatus: expected harvest status
	 */
	private void verifyHarvestStatus(String orderNum, String harvestStatus){
		logger.info("Start to verify harvest status associated with order number:"+orderNum);

		//Search harvest
		lm.searchHarvestInCustHarvestListPg(searchHarvest);

		//Verify only one harvest record
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		if(privilegeNums.length!=1){
			throw new ErrorOnDataException("The order:"+orderNum+" should only have one privilege number.");
		}
		logger.info("Successfully verify the order:"+orderNum+" only has one privilege number.");

		harvest = harvestPage.getHarvestListInfo(privilegeNums[0]);
		if(!harvest.status.equals(harvestStatus)){
			throw new ErrorOnDataException("The harvest status is wrong.", harvestStatus, harvest.status);
		}
		logger.info("Successfully verify harvest status is:"+harvestStatus);
	}

	/**
	 * Verify Order status for product in order details page
	 */
	private void verifyOrderItemStatus(String purchasingName,String status) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
			.getInstance();
		List<OrderItems> orderItems = privOrderDetailsPage.getOrderInfo(purchasingName);
		if(orderItems.size()!=1){
			throw new ErrorOnDataException("There should be only one order items.");
		}
		logger.info("Successfully verify only one order items.");

		logger.info("Verify Order items status for product:"+purchasingName+" is "+status);
		String sttausOnPage=orderItems.get(0).itemStatus;
		if(!status.equals(sttausOnPage)){
			throw new ErrorOnPageException("Order item status of product:"+purchasingName+" is wrong ",status,sttausOnPage);
		}
		logger.info("Successfully verify Order item status is:"+status);
	}
}
