package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.sales.inventory.instant;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions: 1. privilege lottery product - 'RHQ-ReleaseHeldInstantLotteryQuota';
 * 							2. hunt - 'RHQ-ReleaseHeldInstantLotteryQuotaHunt'
 * 							3. quota - 'RHQ-ReleaseHeldInstantLotteryQuota'
 * @SPEC: Lottery Inventory/Quota-Release Held Privilege Lottery Quota [TC:052700]
 * @Task#: AUTO-1827
 * 
 * @author qchen
 * @Date  Aug 29, 2013
 */
public class ReleaseHeldQuota extends LicenseManagerTestCase {
	
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, lottery.getCode(), hunt.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		
		//1. make a lottery item into order cart
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		
		//2. verify the quota AVAILABLE quantity and hold inventory status
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1] - 1);//IMPORTANT
		lm.verifyHuntInventoryStatus(schema, hunt_license_year_quota_id, OrmsConstants.INV_USED_STATUS_HOLD);//IMPORTANT
		
		//3. cancel cart and verify the quota AVAILABLE quantity and hold inventory record shall be deleted
		lm.cancelCart();
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1]);//IMPORTANT
		lm.verifyHuntInventoryHoldRecordDeleted(schema, hunt_license_year_quota_id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		//hunt info
		hunt.setHuntCode("RHQ");
	
		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("ReleaseHeldInstantLotteryQuota");
		
		//lottery info
		lottery.setCode("RHQ");
		lottery.setPurchasingName("RHQ-ReleaseHeldInstantLotteryQuota");
		lottery.setLicenseYear(lm.getFiscalYear(schema));
		List<String> huntCodes = new ArrayList<String>();
		huntCodes.add("RHQ");
		lottery.setHuntCodes(huntCodes);
		
		//customer info
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19770707";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19770707";
		cust.residencyStatus = "Non Resident";
	}
}
