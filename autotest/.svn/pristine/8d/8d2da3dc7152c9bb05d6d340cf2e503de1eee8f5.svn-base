package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This test case was designed to verify privilege list for quick sale and inventory
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=3000,3010,3020,3030
 * d_hf_add_pricing:id=4452,4462,4472
 * d_hf_add_prvi_license_year:id=3130,3140,3150,3160
 * d_hf_assi_pri_to_store:id=2220,2230,2240,2250
 * d_hf_add_qty_control:id=2200,2210,2220,2230
 * d_hf_priv_invtype_licyear:id=120,130
 * d_hf_add_allo_pri_inv:id=110
 * d_hf_add_cust_profile:id=3140
 * @SPEC: Get Privilege Product List for Purchase - SK Seals and Ledgers [TC:100114]
 * @Task#: Auto-2154
 * 
 * @author Jane Wang
 * @Date  May 12, 2014
 */
public class VerifyPrivListForPurchaseInventory extends LicenseManagerTestCase {
	private PrivilegeInfo priv01, priv02, priv03, priv04;
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.purchaseInventoryPrivilegeFromHomePage();
		addItemPg.verifyPrivExistence(priv01.code, priv01.name, priv01.licenseYear, false);// DEFECT-63511
		addItemPg.verifyPrivExistence(priv03.code, priv03.name, priv03.licenseYear, false);
		addItemPg.verifyPrivExistence(priv04.code, priv04.name, priv04.licenseYear, false);
		addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, true);
		lm.addPrivilegeItem(priv02);
		addItemPg.verifyPrivExistence(priv02.code, priv02.name, priv02.licenseYear, false);
		lm.goToCart();
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		//Privilege which customer class is Outfitter
		priv01 = new PrivilegeInfo();
		priv01.code = "IP4";
		priv01.name = "InventoryPrd04";
		priv01.purchasingName = priv01.code + "-" + priv01.name;
		priv01.licenseYear = lm.getFiscalYear(schema);
		
		//Privilege  with limited inventory
		priv02 = new PrivilegeInfo();
		priv02.code = "IP5";
		priv02.name = "InventoryPrd05";
		priv02.purchasingName = priv02.code + "-" + priv02.name;
		priv02.licenseYear = priv01.licenseYear;
		priv02.qty = "2";//IMPORTANT, do not change
		
		//Privilege with Not available inventory
		priv03 = new PrivilegeInfo();
		priv03.code = "IP6";
		priv03.name = "InventoryPrd06";
		priv03.purchasingName = priv03.code + "-" + priv03.name;
		priv03.licenseYear = priv01.licenseYear;
		
		//Privilege without Pricing
		priv04 = new PrivilegeInfo();
		priv04.code = "IP7";
		priv04.name = "InventoryPrd07";
		priv04.purchasingName = priv04.code + "-" + priv04.name;
		priv04.licenseYear = priv01.licenseYear;
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19790201";
		cust.identifier.identifierType = "Passport #";
		cust.identifier.identifierNum = "PASS19790201";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Saskatchewan Resident";

	}

}
