package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.add;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description:(DEFECT-60299) Verify purchase types in add pricing widget based on privileges' product groups and inventory type
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2560, 2570, 2580 --Product group, Inventory Type -- Privileges,None; Privileges,ConvPack Type; Inventory,ConvPack Type.
 * @SPEC:
 * Add Product Pricing UI - Purchase type Privilege Inventory Replacement [TC:099193]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class VerifyPurchaseTypes extends LicenseManagerTestCase {
	private String ddlOption1, ddlOption2, ddlOption3, ddlOption4, ddlOption5, prdType;
	private PrivilegeInfo privilege1, privilege2;


	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//Check purchase type when privilege has "Privileges" product group and None inventory type
		lm.gotoProductPricingPageFromListPage(prdType, privilege.code);
		verifyPurchaseTypes(Arrays.asList(ddlOption1, ddlOption2, ddlOption3, ddlOption4));

		//Check purchase type when privilege has "Privileges" product group and specified inventory type other than None
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoProductPricingPageFromListPage(prdType, privilege1.code);
		verifyPurchaseTypes(Arrays.asList(ddlOption1, ddlOption5, ddlOption2, ddlOption3));

		//Check purchase type when privilege has "Inventory" product group
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoProductPricingPageFromListPage(prdType, privilege2.code);
		verifyPurchaseTypes(Arrays.asList(ddlOption1));

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		prdType = "Privilege";

		privilege.code = "SU1";
		privilege.name = "PrivilegeForSetup1";

		privilege1 = new PrivilegeInfo();
		privilege1.code = "SU2";
		privilege1.name = "PrivilegeForSetup2";

		privilege2 = new PrivilegeInfo();
		privilege2.code = "SU3";
		privilege2.name = "PrivilegeForSetup3";

		ddlOption1 = "Original";
		ddlOption2 = "Renewal";
		ddlOption3 = "Replacement Permit";
		ddlOption4 = "Transfer";
		ddlOption5 = "Privilege Inventory Replacement";
	}

	private void verifyPurchaseTypes(List<String> purchaseTypes){
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget.getInstance();
		lm.gotoAddPrdPricingWidgetFromPricingListPg();

		List<String> typesFromUI = addProdPringWidget.getPurchaseTypes();
		if(purchaseTypes.toString().equals(typesFromUI.toString())){
			logger.info("Successfully verify purchase type DDL options.");
		}else throw new ErrorOnPageException("Failed to verify purchase type DDL options", purchaseTypes.toString(), typesFromUI.toString());

		lm.gotoPricingListPgFromFromAddPricingWidget();
	}
}
