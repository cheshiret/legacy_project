package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify more details section for hunt in Add Hunts page
 * @Preconditions:
 * 1. The hunt "WLH11" setup with two parameters, quota "QuotaForWebHuntSelection" and map of hunt location
 * 2. The hunt "WLH12" setup without parameter, quota or map of hunt location
 * 3. The hunt "WLH8" setup without parameter, but with two quota license years "QuotaWithMultLY" and hunt location map
 * 4. The hunt "WLH7" setup with one parameter, and hunt location map, without quota
 * 5. The hunt "WLH6: setup with one parameter and one quota "QuotaForWebHuntSelection", without hunt location map
 * 6. All the hunts have been assigned to the lottery "Lottery With Mult Hunts".
 * 7. The hunt location "LHL-Leftover Hunt Location" has setup a location image.
 * 8. The hunt quota "QuotaWithMultLY" has setup two license years: previous year and current year
 * @SPEC: List Avaulable Hunts - More Details [TC:056219]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40,50
 * d_hf_add_hunt_location:id=60,80
 * d_hf_add_date_period:id=60
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=530,540,550,580,590
 * d_hf_add_hunt_license_year:id=560,570,580,610,620
 * d_hf_assign_priv_to_hunt:id=410,420,430,460,470
 * d_hf_add_hunt_quota:id=210,270
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * d_hf_add_hunt_parameter:id=30,40,50
 * d_hf_add_quota_license_year:id=20
 * @Task#: Auto-1774
 * 
 * @author Lesley Wang
 * @Date  Feb 14, 2014
 */
public class HuntsList_MoreDetails extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private HuntInfo hunt_AllInfo, hunt_NoInfo, hunt_NoParam, hunt_NoQuota, hunt_NoMap;
	private QuotaInfo quota_CrtYear, quota_PreYear, quota;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lottery.getDescription(), false);
		huntsPg.showAllHunts();
		
		// Verify more details section for hunt with parameter, quota and hunt location map
//		this.verifyHuntMoreDetails(hunt_AllInfo, "Hunt with all info", quota);
//		
//		// Verify more details section for hunt without parameter, quota or hunt location map
//		this.verifyHuntMoreDetails(hunt_NoInfo, "Hunt without more details", (QuotaInfo[])null);
		
		// Verify more details section for hunt without parameter, with two quota and hunt location map
		this.verifyHuntMoreDetails(hunt_NoParam, "Hunt only without parameter", quota_CrtYear, quota_PreYear);
		
		// Verify more details section for hunt with parameter and one quota, without hunt location map
		this.verifyHuntMoreDetails(hunt_NoMap, "Hunt only without location map", quota);
		
		// Verify more details section for hunt with parameter and hunt location map, without quota
		this.verifyHuntMoreDetails(hunt_NoQuota, "Hunt only without quota",  (QuotaInfo[])null);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		
		// Hunt Location Info with image
		LocationInfo huntLoc = new LocationInfo();
		huntLoc.setCode("LHL");
		huntLoc.setDescription("Leftover Hunt Location");
		huntLoc.setImage("HuntLocImg_PrdDetailsTest.png");
		
		// Hunt with parameter, quota and map of hunt location
		hunt_AllInfo = new HuntInfo();
		hunt_AllInfo.setHuntCode("WLH11");
		hunt_AllInfo.setDescription("Web Lottery Hunt 011");
		hunt_AllInfo.setLocationInfo(huntLoc);
		
		HuntParameterInfo huntParam = new HuntParameterInfo();
		huntParam.setHuntParamDes("huntparam des");
		huntParam.setHuntParamValue("param value");
		HuntParameterInfo huntParam2 = new HuntParameterInfo();
		huntParam2.setHuntParamDes("desc of param");
		huntParam2.setHuntParamValue("auto param value");
		hunt_AllInfo.setHuntParams(huntParam, huntParam2);
		
		// Hunt without parameter, quota or map of hunt location
		hunt_NoInfo = new HuntInfo();
		hunt_NoInfo.setHuntCode("WLH12");
		hunt_NoInfo.setDescription("Web Lottery Hunt 012");
		
		// hunt without parameter, with two quota and hunt location map
		hunt_NoParam = new HuntInfo();
		hunt_NoParam.setHuntCode("WLH8");
		hunt_NoParam.setDescription("Web Lottery Hunt 008");
		hunt_NoParam.setLocationInfo(huntLoc);
		
		// hunt with parameter and hunt location map, without quota
		hunt_NoQuota = new HuntInfo();
		hunt_NoQuota.setHuntCode("WLH7");
		hunt_NoQuota.setDescription("Web Lottery Hunt 007");
		hunt_NoQuota.setLocationInfo(huntLoc);
		HuntParameterInfo onehuntParam = new HuntParameterInfo();
		onehuntParam.setHuntParamDes("007 hunt param");
		onehuntParam.setHuntParamValue("007 param value");
		hunt_NoQuota.setHuntParams(onehuntParam);
		
		// hunt with parameter and one quota, without hunt location map
		hunt_NoMap = new HuntInfo();
		hunt_NoMap.setHuntCode("WLH6");
		hunt_NoMap.setDescription("Web Lottery Hunt 006");
		HuntParameterInfo huntParam006 = new HuntParameterInfo();
		huntParam006.setHuntParamDes("one hunt param");
		huntParam006.setHuntParamValue("006 hunt param value");
		hunt_NoMap.setHuntParams(huntParam006);
		
		// Quota Details
		int curYear = DateFunctions.getCurrentYear();
		quota_CrtYear = new QuotaInfo();
		quota_CrtYear.setDescription("QuotaWithMultLY");
		quota_CrtYear.setLicenseYear(Integer.toString(curYear));
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt_NoParam.getHuntCode(), quota_CrtYear.getDescription(), quota_CrtYear.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		QuotaType type = new QuotaType();
		type.setQuota(Integer.toString(quantities[0]));
		quota_CrtYear.setQuotaTypes(type);
		
		quota_PreYear = new QuotaInfo();
		quota_PreYear.setLicenseYear(Integer.toString(curYear-1));
		quota_PreYear.setDescription("QuotaWithMultLY");
		hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt_NoParam.getHuntCode(), quota_PreYear.getDescription(), quota_PreYear.getLicenseYear());
		quantities = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		QuotaType type2 = new QuotaType();
		type2.setQuota(Integer.toString(quantities[0]));
		quota_PreYear.setQuotaTypes(type2);
		
		quota = new QuotaInfo();
		quota.setDescription("QuotaForWebHuntSelection");
		quota.setLicenseYear(Integer.toString(curYear));
		hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt_NoMap.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		quantities = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		QuotaType type3 = new QuotaType();
		type3.setQuota(Integer.toString(quantities[0]));
		quota.setQuotaTypes(type3);
		
	}

	private void verifyHuntMoreDetails(HuntInfo hunt, String msg, QuotaInfo...quotas) {
		boolean result = true;
		boolean isHuntParamNull = hunt.getHuntParams() == null;
		boolean isHuntQuotaNull = quotas == null;
		boolean isHuntLocNull = hunt.getLocationInfo() == null;
		boolean isHuntLocImageNull = isHuntLocNull || StringUtil.isEmpty(hunt.getLocationInfo().getImage());
		String huntDes = hunt.getDescription();
		
		// More details link exists or not
		if (isHuntParamNull && isHuntQuotaNull && (isHuntLocNull || isHuntLocImageNull)) {
			result &= MiscFunctions.compareResult("More Details link exist", false, huntsPg.checkMoreDetailsLinkExist(huntDes));
		} else {
			// More details expand by default
			result &= MiscFunctions.compareResult("More Details hidden", false, huntsPg.checkMoreDetailsHidden(huntDes));
			
			// More details info - parameter
			if (!isHuntParamNull) {
				String expParams = "Additional Information " + hunt.getAllHuntParamInfo();
				result &= MiscFunctions.compareString("Hunt parameters", expParams, huntsPg.getHuntParameters(huntDes));
			} else {
				result &= MiscFunctions.compareResult("Hunt parameters exist", false, huntsPg.checkHuntParamExist(huntDes));
			}
			
			// More details info - quota
			if (!isHuntQuotaNull) {
				for (int i = 0; i < quotas.length; i++) {
					QuotaInfo quota = quotas[i];
					result &= MiscFunctions.compareString("Quota "+i+" license year",quota.getLicenseYear(), huntsPg.getHuntQuotaLicenseYear(huntDes, i));
					result &= MiscFunctions.compareString("Quota "+i+" quota",quota.getQuotaTypes().get(0).getQuota(), huntsPg.getHuntTotalQuota(huntDes, i));
				}
			} else {
				result &= MiscFunctions.compareResult("Hunt quota info exist", false, huntsPg.checkHuntQuotaInfoExist(huntDes));
			}
			
			// More details info - map of hunt location link
			if (!isHuntLocNull && !isHuntLocImageNull) {
				result &= MiscFunctions.compareResult("Hunt Location Map shown before click", false, huntsPg.checkHuntLocMapDisplayed(huntDes));
				huntsPg.clickHuntLocMapLink(huntDes);
				result &= MiscFunctions.compareResult("Hunt Location Map shown after click", true, huntsPg.checkHuntLocMapDisplayed(huntDes));
				huntsPg.closeHuntLocMapPop(huntDes);
			} else {
				result &= MiscFunctions.compareResult("Hunt location map link exist", false, huntsPg.checkHuntLocMapLinkExist(huntDes));
			}
				
			// Click more details and verify more details info not shown.
			huntsPg.clickMoreDetailsLink(hunt.getDescription());
			result &= MiscFunctions.compareResult("More Details hidden", true, huntsPg.checkMoreDetailsHidden(huntDes));
		}
		
		if (!result) {
			throw new ErrorOnPageException(msg + " more details are displayed wrongly!");
		}
		logger.info("---Successfully verify more details section for " + msg);
	}
}
