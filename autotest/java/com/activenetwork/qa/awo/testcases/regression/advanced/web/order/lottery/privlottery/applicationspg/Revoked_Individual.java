package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:(P) For revoked (from award status) lottery application with Individual Applicant type, 
 * check lottery attributes and entered hunts in account overview, order history and lottery application page
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1400 --RCMP #|1R9Y4x4195
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3952--Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=670 --LAH12
 * d_hf_add_hunt_license_year:id=790
 * d_hf_assign_priv_to_hunt:id=570
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=310 --WLK
 * d_hf_add_lottery_license_year:id=220
 * d_hf_add_lottery_quantity_control:id=220
 * d_hf_assign_lottery_to_store:id=220
 * d_hf_assi_hunts_to_lottery:id=280
 * d_hf_add_lottery_execution_config:id=200 --WebLottery12Config
 * d_hf_add_lottery_schedule:id=140 --WebLottery12Processing
 * @SPEC:Lottery Applications - Various status applications [TC:053099] 
 * @Task#: Auto-2110
 * 
 * @author SWang
 * @Date  Mar 9, 2014
 */
public class Revoked_Individual extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
		//Precondition: Make Revoke Lottery application in license manager
		prepareLotteryAppInLM(true);

		//Go to HFSK, account overview, order history and lottery application page to check lottery application attributes and entered hunts info
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In Account overview page
		accountOverviewPg.verifyRecentLotteryOrderItemText(lotteryOrderNum, lotteryDetails_accountOverview);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyOrderHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4195";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH12");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLK");
		lottery.setDescription("WebLottery11");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(quota.getLicenseYear()); 
		lottery.setApplicantType("Individual");
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));

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
		schedule.setExecutionConfig("WebLottery11Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery11Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear();
		huntInfo_First = "1st Choice"+initializeEnteredLotteryHuntAttris();
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+REVOKED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+REVOKED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_orderHistory =lotteryDetails_accountOverview;
	}
}
