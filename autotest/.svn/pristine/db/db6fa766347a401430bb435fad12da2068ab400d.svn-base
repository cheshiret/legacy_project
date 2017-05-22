package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:(P)  For Rejected (Account's age is not matched Agin Min/Max of Quota) lottery application with Group Applicant type,
 * check lottery details and entered hunts info in account overview, order history and lottery application page 
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1420--RCMP #|1R9Y4x4197
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3912--Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=890 --LAH14
 * d_hf_add_hunt_license_year:id=810
 * d_hf_assign_priv_to_hunt:id=590
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=330 --WLM
 * d_hf_add_lottery_license_year:id=240
 * d_hf_add_lottery_quantity_control:id=240
 * d_hf_assign_lottery_to_store=240
 * d_hf_assi_hunts_to_lottery:id=300
 * d_hf_add_lottery_execution_config:id=220 --WebLottery13Config
 * d_hf_add_lottery_schedule:id=160 --WebLottery13Processing
 * @SPEC:Lottery Applications - Various status applications [TC:053099] 
 * @Task#:AUTO-2110
 * 
 * @author SWang
 * @Date  Mar 9, 2014
 */
public class Rejected_Individual extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
		//Precondition: Make Rejected Lottery application in license manager
		prepareLotteryAppInLM();

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
		cus.identifier.identifierNum = "1R9Y4x4197";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-60)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH14");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLM");
		lottery.setDescription("WebLottery13");
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
		schedule.setExecutionConfig("WebLottery13Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery13Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear();
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+REJECTED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+REJECTED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_orderHistory =lotteryDetails_accountOverview;
	}
}
