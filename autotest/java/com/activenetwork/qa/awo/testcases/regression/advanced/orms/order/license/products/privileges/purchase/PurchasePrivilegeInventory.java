package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrSelectFulfillmentMethodWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This test case was designed to verify error message for purchase privilege inventory
 * (1) Verify customer name was 'General Public' for purchase privilege inventory
 * (2) Verify privilege inventory fromQty and toQty attribute on UI;
 * (3) Verify un-matched error message when selecting inventory qty which was not matched with inventory fromQty and toQty
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2900,2950
 * d_hf_add_pricing:id=4332,4402
 * d_hf_assi_pri_to_store:id=2120,2170
 * d_hf_add_prvi_license_year:id=3020,3070
 * d_hf_add_qty_control:id=2100,2150
 * d_hf_add_cust_profile:id=3110
 * d_hf_priv_invtype_licyear:id=100
 * d_hf_add_allo_pri_inv:id=90
 * @SPEC: 
 * Purchase Privilege - SK Seals and Ledgers [TC:100125] -- Step1, Step2, Ste19, Step20, Step22
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 23, 2014
 */
public class PurchasePrivilegeInventory extends LicenseManagerTestCase {

	private PrivilegeInfo priv01, priv02;
	private String errMsg01;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.purchaseInventoryPrivilegeFromHomePage();
		lm.addPrivilegeItem(priv01);
		lm.goToCart();
		verifyCustNameOnOrdCartPg();
		lm.processOrderCart(pay);
		//TODO If the Product Group of the Privilege Product is "Inventory", the Valid To Date & Time of the Privilege instance shall be null/blank, i.e. the Privilege instance shall not expire.
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.addPrivilegeItem(priv02);
		verifyInvQtyForRange(priv02);
		selectImmediatelyFulfillmentMethod();
		deselectFirstAvailableInventory();
		verifyErrMsgForUnmatchedSelectedInvNums(errMsg01, priv02.inventoryQty);
		selectNumOfAvailableInventory(Integer.parseInt(priv02.inventoryQty));
		gotoAddItemPgFromPrivInvWidget();
		lm.goToCart();
		String ordNum = lm.processOrderCart(pay);
		
		lm.reversePrivilegeOrderToCleanUp(ordNum, "14 - Other", "Automation Test", pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		priv01 = new PrivilegeInfo();
		priv01.code = "IP1";
		priv01.name = "InventoryPrd01";
		priv01.purchasingName = priv01.code + "-" + priv01.name;
		priv01.licenseYear = lm.getFiscalYear(schema);
		
		priv02 = new PrivilegeInfo();
		priv02.code = "IR2";
		priv02.name = "InvRange02";
		priv02.purchasingName = priv02.code + "-" + priv02.name;
		priv02.licenseYear = lm.getFiscalYear(schema);
		priv02.inventoryQty = "2";
		priv02.inventoryQtyFrom = "2";
		priv02.inventoryQtyTo = "3";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19810601";
		cust.identifier.identifierType = "Passport #";
		cust.identifier.identifierNum = "PASS19810601";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Saskatchewan Resident";
		
		errMsg01 = ".*select.*";
	}

	private void verifyCustNameOnOrdCartPg() {
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify customer name on order cart page.");
		
		String custName = ormsOrderCartPg.getCustName();
		if(StringUtil.isEmpty(custName) || !custName.equals("General Public"))
			throw new ErrorOnPageException("Customer Name should be 'General Public' on order cart page.");
		logger.info("---Verify customer name as 'General Public' successfully on order cart page.");
	}
	
	private void verifyInvQtyForRange(PrivilegeInfo priv) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage	.getInstance();
		LicMgrSelectFulfillmentMethodWidget selectFulfillmentLicWidget = LicMgrSelectFulfillmentMethodWidget.getInstance();
		
		logger.info("Verify privilege inventory qty for qty type as Range product.");
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		selectFulfillmentLicWidget.waitLoading();
		
		List<String> listValue = selectFulfillmentLicWidget.getSelectedInvQtyList();
		if(listValue==null || !listValue.get(0).equals(priv02.inventoryQtyFrom) || !listValue.get(listValue.size()-1).equals(priv02.inventoryQtyTo))
			throw new ErrorOnPageException("Failed to verify inventory qty selection choices for product "+priv.code);
		logger.info("---Verify inventory qty from "+priv.inventoryQtyFrom+" to "+priv.inventoryQtyTo+" for product "+priv.code);
	}
	
	private void selectImmediatelyFulfillmentMethod() {
		LicMgrSelectFulfillmentMethodWidget selectFulfillmentLicWidget = LicMgrSelectFulfillmentMethodWidget.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		selectFulfillmentLicWidget.selectInvFulfillMethod("Select Immediately");
		ajax.waitLoading();
		selectFulfillmentLicWidget.clickOK();
		ajax.waitLoading();
		privInvWidget.waitLoading();
	}
	
	private void deselectFirstAvailableInventory() {
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		privInvWidget.deselectFirstAvailableInventory();
		ajax.waitLoading();
	}
	
	private void selectNumOfAvailableInventory(int num) {
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		privInvWidget.selectNumsOfAvailableInventory(num);
	}
	
	private void verifyErrMsgForUnmatchedSelectedInvNums(String msg, String num) {
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		privInvWidget.clickOK();
		ajax.waitLoading();
		
		String msgUI = privInvWidget.getErrorMsg();
		msg = msg + num + ".*inventory.*";
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(msg))
			throw new ErrorOnPageException("Un-matched selected inventory number error message was wrong on page.", msg, msgUI);
		logger.info("---Verify un-matched selected inventory number error message on page successfully.");
	}
	
	private void gotoAddItemPgFromPrivInvWidget() {
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage	.getInstance();
		
		privInvWidget.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}
}
