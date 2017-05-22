package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeSelectHuntPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This test case was designed to verify workflow for purchase leftover privilege
 * (1) Verify hunt info which was limited with quota and all info setup on select hunt page;
 * (2) Verify hunt which was limited no quota setup will not displayed on select hunt page;
 * (3) Make privilege order with hunt limited quota setup and unlimited setup;
 * (4) Verify hunt quota will reduce once order was processed successfully;
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2720
 * d_hf_add_pricing:id=4152
 * d_hf_assi_pri_to_store:id=1940 
 * d_hf_add_prvi_license_year:id=2840
 * d_hf_add_qty_control:id=1920
 * d_hf_add_hunt_quota:id=300,310
 * d_hf_add_species:id=40
 * d_hf_add_weapon:id=50
 * d_hf_add_hunt_location:id=60
 * d_hf_add_date_period:id=60
 * d_hf_add_hunt:id=1030,1040,1060
 * d_hf_assign_priv_to_hunt:id=750,760,780
 * d_hf_add_hunt_license_year:id=1150,1160,1180
 * d_hf_add_cust_profile:id=2920
 * 
 * @SPEC: 
 * Purchase Privilege - Leftover [TC:067952] 
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 8, 2014
 */
public class PurchaseLeftoverPrivilege extends LicenseManagerTestCase {

	private HuntInfo hunt01, hunt02, hunt03;
	private int qty;
	private String notFoundMsg = ".*No results found.*";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.addPrivilegeItem(privilege);
		verifyHuntInfo(hunt01);
		verifyHuntNotExisted(hunt02.getHuntCode(), notFoundMsg);
		verifyHuntExisted(hunt03.getHuntCode(), hunt03.getDescription());
		lm.selectHuntForPrivilegeSale(hunt01.getHuntCode());
		lm.goToCart();	
		String ordNum01 = lm.processOrderCart(pay);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.addPrivilegeItem(privilege);
		verifyHuntQuotaReduce(hunt01.getHuntCode(), String.valueOf(qty-Integer.parseInt(privilege.qty)));
		lm.selectHuntForPrivilegeSale(hunt03.getHuntCode());
		lm.goToCart();	
		String ordNum02 = lm.processOrderCart(pay);
		
		lm.reversePrivilegeOrderToCleanUp(ordNum01, "14 - Other", "Automation Test", pay);
		lm.reversePrivilegeOrderToCleanUp(ordNum02, "14 - Other", "Automation Test", pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		privilege.code = "LOP";
		privilege.purchasingName = "LOP-LeftoverPriv001";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "2";
		
		// Hunt with all info
		hunt01 =  new HuntInfo();
		hunt01.setHuntCode("LO1");
		hunt01.setDescription("Leftover hunt 01 limited with available quota");
		hunt01.setSpecie("Black Bear");
		hunt01.setSpecieSubType("Species Sub Type");
		hunt01.setWeaponDes("Lottery App Hunt Weapon");
		hunt01.setHuntLocationInfo("LHL");
		
		DatePeriodInfo datePeriodInfo = new DatePeriodInfo();
		datePeriodInfo.setCode("DPA");
		datePeriodInfo.setDescription(datePeriodInfo.getCode()+"(:Jan 01-Dec 31)");
		hunt01.setDatePeriodInfo(datePeriodInfo);
		
		//quota info
		QuotaInfo quota = new QuotaInfo();
		quota.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Leftover quota 01");
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaIDForPrivilege(schema, privilege.code, hunt01.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		qty = quantities[1];
		
		// Hunt with Limited Quota and not quota assigned
		hunt02 =  new HuntInfo();
		hunt02.setHuntCode("LO2");
		hunt02.setDescription("Leftover hunt 02 limited with NOT available quota");
		
		// Hunt with unLimited Quota
		hunt03 =  new HuntInfo();
		hunt03.setHuntCode("UL3");
		hunt03.setDescription("Leftover hunt 02 unlimited");
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19840601";
		cust.identifier.identifierType = "Passport #";
		cust.identifier.identifierNum = "19840601";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Saskatchewan Resident";
	}
	
	private void verifyHuntInfo(HuntInfo huntInfo) {
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();
		
		selectHuntPage.searchHunt(huntInfo.getHuntCode());
		HuntInfo huntUI = selectHuntPage.getHuntInfo();
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Hunt Code and Description", huntInfo.getHuntCode()+" "+huntInfo.getDescription(), huntUI.getDescription());
		passed &= MiscFunctions.compareResult("Hunt Species", huntInfo.getSpecieSubType(), huntUI.getSpecieSubType());
		passed &= MiscFunctions.compareResult("Hunt Weapon", huntInfo.getWeaponDes(), huntUI.getWeaponDes());
		passed &= MiscFunctions.compareResult("Hunt Date Period", huntInfo.getHuntDatePeriodInfo(), huntUI.getHuntDatePeriodInfo());
		passed &= MiscFunctions.compareResult("Hunt Location", huntInfo.getHuntLocationInfo(), huntUI.getHuntLocationInfo());
		passed &= MiscFunctions.compareResult("Hunt Quota", String.valueOf(qty), huntUI.getHuntQuotaDescription());
		
		if(!passed)
			throw new ErrorOnPageException("Failed to verify hunt details info on page. Please check log.");
		logger.info("---Verify hunt details info on page successfully.");
	}
	
	private void verifyHuntNotExisted(String huntCode, String notFoundMsg) {
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();
		
		logger.info("Verify hunt "+huntCode+" not exist on page.");
		selectHuntPage.searchHunt(huntCode);
		String msgUI = selectHuntPage.getErrorMessage();
		if(StringUtil.isEmpty(msgUI) || !msgUI.matches(notFoundMsg))
			throw new ErrorOnPageException("Hunt "+huntCode+" should not exist on page.");
		logger.info("---Verify hunt "+huntCode+" Not exist on page successfully.");
	}
	
	private void verifyHuntExisted(String huntCode, String huntDes) {
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();
		
		logger.info("Verify hunt "+huntCode+" exist on page.");
		boolean existed = selectHuntPage.checkHuntExisted(huntCode, huntDes);
		if(!existed)
			throw new ErrorOnPageException("Hunt "+huntCode+" should exist on page.");
		logger.info("---Verify hunt "+huntCode+" exist on page successfully.");
	}

	private void verifyHuntQuotaReduce(String huntCode, String expectedQuota) {
		LicMgrPrivilegeSelectHuntPage selectHuntPage = LicMgrPrivilegeSelectHuntPage.getInstance();
		
		logger.info("Verify hunt "+huntCode+" quota as "+expectedQuota);
		String quotaUI = selectHuntPage.getHuntQuota(huntCode);
		if(!quotaUI.equals(expectedQuota))
			throw new ErrorOnPageException("Hunt quota displayed un-correctly on page.", expectedQuota, quotaUI);
		logger.info("---Verify hunt quota on page successfully.");
	}
}
