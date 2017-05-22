package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
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
 * @Description: This test case was designed to verify purchase privilege with fulfillment method
 * (1) System shall set product quantity to be 1 for product which inventory quantity is Range;
 * (2) Purchase privilege inventory for selecting fulfillment method as 'Select Immediately' ;
 * (3) Purchase privilege inventory for selecting fulfillment method as 'Use Customer's On-hand Inventory', and qty type is 'Fixed'/'Range';
 * (4) For 'Use Customer's On-hand Inventory', verify no inventory number was input, partial inventory number was input, and invalid inventory number was input;
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2910,2920,2930,2900
 * d_hf_add_pricing:id=4342,4352,4362,4332
 * d_hf_assi_pri_to_store:id=2130,2140,2150,2120
 * d_hf_add_prvi_license_year:id=3030,3040,3050,3020
 * d_hf_add_qty_control:id=2110,2120,2130,2150
 * d_hf_add_cust_profile:id=3110 
 * @SPEC: 
 * Purchase Privilege - SK Seals and Ledgers [TC:100125] -- Step3, Step14, Step15, Ste16
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 24, 2014
 */
public class PurchasePrivInvWithFulfillmentMethod extends LicenseManagerTestCase {

	private PrivilegeInfo priv01, priv02, priv03;
	private String errMsg01, errMsg02, errMsg03;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		//Verify privilege qty was 1 for qty_type=Range TC:100125 Step3
		verifyQtyForQtyTypeRangePrd(priv03.purchasingName, priv03.licenseYear);
		lm.gotoHomePage();
		
		//purchase privilege inventory -- get privilege number on hand
		lm.purchaseInventoryPrivilegeFromHomePage();
		lm.addPrivilegeItem(privilege);
		String[] nums = privilege.inventoryNum.split(",");
		priv02.inventoryNum = nums[0]+","+nums[1];
		priv03.inventoryNum = nums[2]+","+nums[3];
		lm.goToCart();
		lm.processOrderCart(pay);
		//purchase inventory pack;
		//priv01, fulfillment method 'Select Immediately'
		//priv02, fulfillment method 'Use Customer's On-hand Inventory', and qty type is 'Fixed'
		//priv03, fulfillment method 'Use Customer's On-hand Inventory', and qty type is 'Range'
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		priv01.inventoryNum = lm.addPrivilegeItem(priv01);
		verifyErrMsgForInvNum(priv02, "2", priv01.inventoryNum);
		cancelFromSelectFulfillmentMethodWidget();
		lm.addPrivilegeItem(priv02);

		verifyErrMsgForInvNum(priv03, "2", priv01.inventoryNum);
		cancelFromSelectFulfillmentMethodWidget();
		lm.addPrivilegeItem(priv03);
		lm.goToCart();
		String ordNum = lm.processOrderCart(pay);
		
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.gotoVoidUndoVoidPgFromPrivOrderDetailPg("Reverse");
		lm.operatePrivilegeOrdToCartFromVoidUndoVoidPg("Reverse", StringUtil.EMPTY, "14 - Other", "Automation Test");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";//Hudson Bay
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";

		//privilege for generic public customer
		privilege.code = "IP1";
		privilege.name = "InventoryPrd01";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "4";//IMPORTANT, do not change
		
		//privilege for qty type as 'Fixed' and fulfillment method as 'Select Immediately'
		priv01 = new PrivilegeInfo();
		priv01.code = "IF1";
		priv01.name = "InvFixed01";
		priv01.purchasingName = "IF1-InvFixed01";
		priv01.licenseYear = lm.getFiscalYear(schema);
		priv01.qty = "1";
		priv01.fulfillmentMethod = "Select Immediately";
		
		//privilege for qty type as 'Fixed' and fulfillment method as 'Use Customer's On-hand Inventory'
		priv02 = new PrivilegeInfo();
		priv02.code = "IF2";
		priv02.name = "InvFixed02";
		priv02.purchasingName = "IF2-InvFixed02";
		priv02.licenseYear = priv01.licenseYear;
		priv02.qty = "1";//IMPORTANT, do not change
		priv02.inventoryQty = "2";
		priv02.fulfillmentMethod = "Use Customer's On-hand Inventory";
		
		//privilege for qty type as 'Range' and fulfillment method as 'Use Customer's On-hand Inventory'
		priv03 = new PrivilegeInfo();
		priv03.code = "IR1";
		priv03.name = "InvRange01";
		priv03.purchasingName = "IR1-InvRange01";
		priv03.licenseYear = priv01.licenseYear;
		priv03.qty = "1";//IMPORTANT, do not change
		priv03.inventoryQty = "2";
		priv03.fulfillmentMethod = "Use Customer's On-hand Inventory";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19810601";
		cust.identifier.identifierType = "Passport #";
		cust.identifier.identifierNum = "PASS19810601";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Saskatchewan Resident";
		
		errMsg01 = ".*Inventory Number is required.*";
		errMsg02 = ".*Invalid Inventory Number.*";
		errMsg03 = ".*entered more than once.*";
	}
	
	private void verifyQtyForQtyTypeRangePrd(String privName, String licYear) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		
		List<String> qtyListUI = addItemPg.getQtyListForPrivilege(privName, licYear);
		if(qtyListUI==null || qtyListUI.size()>1 || !qtyListUI.get(0).equals("1"))
			throw new ErrorOnPageException("Privilege:"+privName+" qty displayed un-correctly on add item page.");
		logger.info("---Verify privilege:"+privName+" qty was only 1 successfully on add item page.");
	}
	
	private void verifyErrMsgForInvNum(PrivilegeInfo priv, String qty, String num) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrSelectFulfillmentMethodWidget selectFulfillmentLicWidget = LicMgrSelectFulfillmentMethodWidget.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		addItemPg.addProductToCart(priv.purchasingName, priv.licenseYear, priv.qty);
		ajax.waitLoading();
		selectFulfillmentLicWidget.waitLoading();
		//Select qty first
		selectFulfillmentLicWidget.selectInvQty(qty);
		ajax.waitLoading();
		//Use Customer's On-hand Inventory
		selectFulfillmentLicWidget.selectInvFulfillMethod(priv.fulfillmentMethod);
		ajax.waitLoading();
		
		String msgUI = "";
		logger.info("---1. Leave all inventory numbers as blank, and click OK---");
		selectFulfillmentLicWidget.clickOK();
		Object pages = browser.waitExists(selectFulfillmentLicWidget, privInvWidget, addItemPg);
		if(pages != selectFulfillmentLicWidget) 
			throw new ErrorOnPageException("Inventory Number is required error should exist on select fulfillment method page.");
		msgUI = selectFulfillmentLicWidget.getErrorMsg();
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(errMsg01))
			throw new ErrorOnPageException("Failed to verify error message for inventory num is required", errMsg01, msgUI);
		logger.info("---Verify error message for inventory num is required successfully.");
		
		logger.info("---2. Input partial inventory number, and click OK---");
		selectFulfillmentLicWidget.setInventoryNums(num, false);
		selectFulfillmentLicWidget.clickOK();
		pages = browser.waitExists(selectFulfillmentLicWidget, privInvWidget, addItemPg);
		if(pages != selectFulfillmentLicWidget) 
			throw new ErrorOnPageException("Inventory Number is required error should exist on select fulfillment method page.");
		msgUI = selectFulfillmentLicWidget.getErrorMsg();
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(errMsg01))
			throw new ErrorOnPageException("Failed to verify error message for inventory num is required", errMsg01, msgUI);
		logger.info("---Verify error message for inventory num is required successfully.");
		
		logger.info("---3. Input invalid inventory number, and click OK---");
		selectFulfillmentLicWidget.setInventoryNums("invalid123,"+num, false);
		selectFulfillmentLicWidget.clickOK();
		pages = browser.waitExists(selectFulfillmentLicWidget, privInvWidget, addItemPg);
		if(pages != selectFulfillmentLicWidget) 
			throw new ErrorOnPageException("Inventory Number is invalid error should exist on select fulfillment method page.");
		msgUI = selectFulfillmentLicWidget.getErrorMsg();
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(errMsg02))
			throw new ErrorOnPageException("Failed to verify error message for inventory num is invalid", errMsg02, msgUI);
		logger.info("---Verify error message for inventory num is invalid successfully.");
		
		logger.info("---4. Input duplicated inventory number, and click OK---");
		String nu = priv.inventoryNum.split(",")[0];
		selectFulfillmentLicWidget.setInventoryNums(nu+","+nu, false);
		selectFulfillmentLicWidget.clickOK();
		pages = browser.waitExists(selectFulfillmentLicWidget, privInvWidget, addItemPg);
		if(pages != selectFulfillmentLicWidget) 
			throw new ErrorOnPageException("Inventory Number is entered more than once error should exist on select fulfillment method page.");
		msgUI = selectFulfillmentLicWidget.getErrorMsg();
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(errMsg03))
			throw new ErrorOnPageException("Failed to verify error message for inventory num is entered more than once", errMsg03, msgUI);
		logger.info("---Verify error message for inventory num is entered more than once successfully.");
	}
	
	private void cancelFromSelectFulfillmentMethodWidget() {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrSelectFulfillmentMethodWidget selectFulfillmentLicWidget = LicMgrSelectFulfillmentMethodWidget.getInstance();
		
		logger.info("Cancel from select fulfillment method widget.");
		selectFulfillmentLicWidget.clickCancel();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}
}
