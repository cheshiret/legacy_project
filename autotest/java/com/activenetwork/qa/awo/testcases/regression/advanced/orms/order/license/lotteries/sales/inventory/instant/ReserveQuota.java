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
 * @Description: verify the instant lottery quota status after processing order cart shall be Booked
 * 						i_hunt_inv, i_hunt_inv_used
 * @Preconditions: 1. privilege lottery product - 'RIQ-ReserveInstantLotteryQuota'
 * 							2. hunt - 'RIQ-ReserveInstantLotteryQuotaHunt'
 * 							3. quota - 'RIQ-ReserveInstantLotteryQuota'
 * 							4. execution config - the successful rate MUST be 100%
 * 							5. processing associated with above execution config
 * 							6. permit - 'PFL-PrivilegeForLottery'
 * @SPEC: Lottery Inventory/Quota--Reserve Held Privilege Lottery Quota [TC:052699]
 * @Task#: AUTO-1827
 * 
 * @author qchen
 * @Date  Aug 29, 2013
 */
public class ReserveQuota extends LicenseManagerTestCase {

	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	private String reverseReason, reverseNote;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, lottery.getCode(), hunt.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		//1. make a lottery item into order cart
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		
		//2. verify the hold inventory
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1] - 1);
		lm.verifyHuntInventoryStatus(schema, hunt_license_year_quota_id, OrmsConstants.INV_USED_STATUS_HOLD);
		
		//3. process order cart, verify the inventory status
		String ordNum = lm.processOrderCart(pay);
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1] - 1);
		lm.verifyHuntInventoryStatus(schema, hunt_license_year_quota_id, OrmsConstants.INV_USED_STATUS_BOOKED);
		
		//clean up
//		lm.gotoApplicationOrderDetailsPageFromTopMenu(ordNum);
//		lm.reverseApplicationOrderToCart(reverseReason, reverseNote);
//		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		//hunt info
		hunt.setHuntCode("RIQ");
	
		//quota info
		quota.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("ReserveInstantLotteryQuota");
		
		//lottery info
		lottery.setCode("RIQ");
		lottery.setPurchasingName("RIQ-ReserveInstantLotteryQuota");
		lottery.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		List<String> huntCodes = new ArrayList<String>();
		huntCodes.add("RIQ");
		lottery.setHuntCodes(huntCodes);
		
		//customer info
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19770707";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19770707";
		cust.residencyStatus = "Non Resident";
		
		reverseReason = "004 - No Reason Available";
		reverseNote = "Automation";
	}
}