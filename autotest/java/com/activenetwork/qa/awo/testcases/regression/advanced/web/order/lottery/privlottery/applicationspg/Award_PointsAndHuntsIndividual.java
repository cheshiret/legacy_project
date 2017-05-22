package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: (P) Verify Award Lottery application attributes in Account overview, Order history and Lottery Application page when account has Points + Hunts Individual application
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1340 --RCMP #|1R9Y4x4189
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3782 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=620 --LAH6 
 * d_hf_add_hunt_license_year:id=49
 * d_hf_assign_priv_to_hunt:id=500
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=180 --WL5
 * d_hf_add_lottery_license_year:id=110
 * d_hf_add_lottery_quantity_control:id=110
 * d_hf_assi_hunts_to_lottery:id=70
 * d_hf_add_lottery_execution_config:id=60 --WebLottery05Config
 * d_hf_add_lottery_schedule:id=40 --WebLottery05Processing
 * d_assign_feature :id=5232
 * @SPEC:
 * Lottery Applications - Award Hunt Details [TC:058297] 
 * Lottery Applications - Informational Message about awarded lottery [TC:053100]
 * @Task#: Auto-1725
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class Award_PointsAndHuntsIndividual extends HFSKLotteryApplicationTestCase {

	@Override
	public void execute() {
		//Precondition: Make Award Lottery application
		prepareLotteryAppInLM();

		//Go to HFSK to check lottery application attributes
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In Account overview page
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, huntInfo_First);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, huntInfo_First);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, huntInfo_First);

		hf.declineAward(lotteryOrderNum, cus.residencyStatus);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4189";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH6");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WL5");
		lottery.setDescription("WebLottery05");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(quota.getLicenseYear());
		lottery.setApplicantType("Individual");
		lottery.setPointTypes(Arrays.asList("AutoPointType"));
		lottery.setHuntCodes(Arrays.asList("LAH6"));
		
		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = quota.getLicenseYear();
		licenseYear.sellFromDate = DateFunctions.getToday(timeZone);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";

		//lottery schedule info
		schedule.setExecutionConfig("WebLottery05Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery05Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear();
		
		huntInfo_First = "1st Choice"+initializeAwardedLotteryHuntAttris();
//		awardedHuntDetails = "Awarded Hunt \\(1st Choice\\) AutoPointType Points deducted: "+awardedLotteryHuntAttris;
		awardedHuntDetails = "Awarded Hunt \\(1st Choice\\) "+label_Point+" "+awardedLotteryHuntAttris;
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_PointType+" 1 "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+AWARDED_STATUS+" "+awardedHuntDetails+" Show Entered WMZs.*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+label_PointType+" 1 "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+AWARDED_STATUS+" "+awardedHuntDetails+" Show Entered WMZs.*";
		lotteryDetails_orderHistory =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+label_PointType+" 1 "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+AWARDED_STATUS+" "+awardedHuntDetails+" Show Entered WMZs.*";
	}
}
