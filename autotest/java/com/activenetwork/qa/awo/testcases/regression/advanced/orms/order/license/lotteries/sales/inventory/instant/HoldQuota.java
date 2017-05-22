package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.sales.inventory.instant;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the privilege lottery quota inventory will be held when it has been added to order cart
 * 						i_hunt_inv, i_hunt_inv_used
 * @Preconditions: 1. privilege lottery product - 'HIQ - HoldInstantLotteryQuota';
 * 							2. hunt - 'HIQ - HoldInstantLotteryQuotaHunt'
 * 							3. quota - 'HIQ - HoldInstantLotteryQuota'
 * @SPEC: Lottery Inventory/Quota--Hold Privilege Lottery Quota [TC:052698] STEP 2-5
 * @Task#: AUTO-1827
 * 
 * @author qchen
 * @Date  Aug 29, 2013
 */
public class HoldQuota extends LicenseManagerTestCase {
	
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	private String expectedMsg;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. ensure the privilege lottery has enough quota (greater than 0)
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, lottery.getCode(), hunt.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		if(quantities[1] < 1) {
			quantities[1] = NumberUtil.getRandomInt(10, 20);
			//update Quantity and Available quantity
			lm.addHuntInventoryQuantities(schema, hunt_license_year_quota_id, quantities[1]);
		}
		
		//2. make a lottery to hunt choice page
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.addPrivilegeLotteryItemToManageChoicePage(lottery);
		
		//3. update the quota Available Quantity to 0 from DB
		lm.updateHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, 0);//IMPORTANT
		
		//4. try to add choice to cart, verify the error message
		String actualMsg = lm.addPrivilegeLotteryItemToCartFromManageChoicePage();
		if(!MiscFunctions.compareResult("Error message displaye don Manage Choice page", expectedMsg, actualMsg)) throw new ErrorOnPageException("Error message displayed on Manage Choice is not correct.");
		
		//5. update the quota back as former valid quantity and continue to add to cart
		lm.updateHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1]);//IMPORTANT
		lm.addPrivilegeLotteryItemToCartFromManageChoicePage();
		
		//6. verify the held quota
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1] - 1);
		lm.verifyHuntInventoryStatus(schema, hunt_license_year_quota_id, OrmsConstants.INV_USED_STATUS_HOLD);
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		//hunt info
		hunt.setHuntCode("HIQ");
	
		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("HoldInstantLotteryQuota");
		
		//lottery info
		lottery.setCode("HIQ");
		lottery.setPurchasingName("HIQ-HoldInstantLotteryQuota");
		lottery.setLicenseYear(lm.getFiscalYear(schema));
		List<String> huntCodes = new ArrayList<String>();
		huntCodes.add("HIQ");
		lottery.setHuntCodes(huntCodes);
		
		//customer info
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19770707";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19770707";
		cust.residencyStatus = "Non Resident";
		
		expectedMsg = "There is no quota available for the hunt selected.";
	}
}
