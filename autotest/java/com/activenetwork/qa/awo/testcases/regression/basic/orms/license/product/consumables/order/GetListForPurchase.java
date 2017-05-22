package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.order;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: verify appropriate POS product in the purchase list
 * @Preconditions: need POS product could purchase
 *                 need active customer 
 * @LinkSetUp:d_hf_add_cust_profile 770 <TEST-Advanced1,QA-Advanced1>
 *            d_hf_addconsu_prd 170 <POSForPurchseList>
 * @SPEC: 	Get Subscription Product List [TC:024149] 
 * @Task#: AUTO-1456
 * @author szhou
 * @Date  Mar 25, 2013
 */
public class GetListForPurchase extends LicenseManagerTestCase {
	private String locationClass;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// Precondition#1. consumable Instance Potentially Available for Purchase
		lm.gotoConsumableSearchListPageFromTopMenu();
		if (!lm.checkConsumableExisted(consumable.code, schema)) {
			consumable.id = lm.createNewConsumableProduct(consumable);
		} else {
			consumable.id = lm.getProductID("Product Code", consumable.code,
					null, schema);
		}

		// Precondition#2. consumable Product is Not Assigned to the Sales Location
		lm.gotoConsumableAgentAssignPgFromTopMenu(consumable.id);
		lm.unassignPrivilegeFromStoresThruLocationClass(locationClass);
		lm.assignConsumableToStoresThruLocationClass(locationClass);
		this.gotoConsumableListPageFromAgentAssignPg();

		// Precondition#3. consumable Instance is Missing Pricing Info--add 'Original' pricing
		ILicMgrProductPricingPage pricingPage = lm
				.gotoProductPricingPageFromListPage(pricing.prodType,
						pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		lm.addPricingForProduct(pricing, pricingPage, true);

		lm.logOutLicenseManager();

		// make a original consumable order
		login.location = "HF HQ Role/WAL-MART";
		lm.loginLicenseManager(login);

		// purchase a consumable
		this.gotoConsumableAddItemPage(cust);
		// verify product in purchase list
		this.verifyProductInTheList(consumable.code+" - "+consumable.name);
		
		lm.logOutLicenseManager();
	}
	
	private void verifyProductInTheList(String prodName){
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
		.getInstance();
		
		int rowCount=consumAddItemPg.getProductIndex(prodName);
		if(rowCount<0){
			throw new ItemNotFoundException("Not Found Product "+prodName);
		}
	}

	private void gotoConsumableAddItemPage(Customer cust) {
		LicMgrOrigPrivSaleAddItemPage privAddItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		privAddItemPg.clickConsumables();
		ajax.waitLoading();
		consumAddItemPg.waitLoading();
	}

	private void gotoConsumableListPageFromAgentAssignPg() {
		LicMgrConsumableProductStoreAssignmentPage consumableStoreAssignmentPg = LicMgrConsumableProductStoreAssignmentPage
				.getInstance();
		LicMgrConsumableListPage cunsumableListPage = LicMgrConsumableListPage
				.getInstance();

		consumableStoreAssignmentPg.clickConsumablesTab();
		ajax.waitLoading();
		cunsumableListPage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19850224";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "111111";
		cust.residencyStatus = "Non Resident";
		cust.lName = "TEST-Advanced1";
		cust.fName = "QA-Advanced1";

		consumable.name = "POSForPurchseList";
		consumable.code = "PPL";
		consumable.orderType = "POS Sale";
		consumable.description = "POSForPurchseList";

		locationClass = "06-State Parks Agent";

		pricing.prodCode = consumable.code;
		pricing.prodType = "Consumable";
		pricing.status = "Active";
		pricing.locationClass = "06 - State Parks Agent";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(10);
		pricing.vendorFee = "20.00";
		pricing.stateTransFee = "15.00";
		pricing.stateFee = "10.00";
		pricing.transFee = "3.00";

	}

}
