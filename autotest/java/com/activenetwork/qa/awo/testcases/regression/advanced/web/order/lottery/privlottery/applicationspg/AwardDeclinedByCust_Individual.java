package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: For award Declined by customer (Calculate age as of 'Specific Date') lottery application with individual Applicant type, 
 * check lottery attributes and entered hunts info in account overview, order history and lottery application page
 * @Preconditions: 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1380 --RCMP #|1R9Y4x4193
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3932--Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=660 --LAH10
 * d_hf_add_hunt_license_year:id=770
 * d_hf_assign_priv_to_hunt:id=540
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=290 --WL9
 * d_hf_add_lottery_license_year:id=200
 * d_hf_add_lottery_quantity_control:id=200
 * d_hf_assi_hunts_to_lottery:id=260
 * d_hf_add_lottery_execution_config:id=180 --WebLottery09Config
 * d_hf_add_lottery_schedule:id=120 --WebLottery09Processing
 * @SPEC:
 * Lottery Applications - Various status applications [TC:053099] 
 * Lottery Applications - Informational Message about awarded lottery [TC:053100]
 * @Task#:AUTO-2110
 * 
 * @author SWang
 * @Date  Mar 9, 2014
 */
public class AwardDeclinedByCust_Individual extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
        //Precondition: Make Award Lottery application
		prepareLotteryAppInLM();

		//Go to HFSK to accept award
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();
		hf.declineAward(lotteryOrderNum, cus.residencyStatus);
		
		//check lottery application attributes
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In lottery application page
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp);
		lotteryAppPg.verifyAcceptDeclineAwardMsgExist(lotteryOrderNum, acceptDeclineAwardMsg, false);
		
		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyOrderHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory);
		orderHistoryPg.verifyAcceptDeclineAwardMsgExist(acceptDeclineAwardMsg, false);
		
		//In Account overview page
		hf.gotoMyAccountPgFromHeaderBar();
		accountOverviewPg.verifyRecentLotteryOrderItemText(lotteryOrderNum, lotteryDetails_accountOverview);
		accountOverviewPg.verifyAcceptDeclineAwardMsgExist(acceptDeclineAwardMsg, false);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4193";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH10");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WL9");
		lottery.setDescription("WebLottery09");
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
		schedule.setExecutionConfig("WebLottery09Config");
		schedule.setLicenseYear(quota.getLicenseYear()); 
		schedule.setDescription("WebLottery09Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SPECIFIC_DATE);
		schedule.setSpecificDate(lotteryAppDate);
        schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(2));
        schedule.setAwardAcceptanceByTime("12:00");
        schedule.setAwardAcceptanceByAmPm("AM");
        
		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear(); 
		
		initializeAwardedLotteryHuntAttris();
		awardedHuntDetails = "Awarded Hunt "+awardedLotteryHuntAttris;
		acceptDeclineAwardMsg = "The acceptance deadline for this draw is "+DateFunctions.formatDate(schedule.getAwardAcceptanceByDate(), "E MMM dd yyyy")+".";
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+AWARDEDDECLINEDBYCUSTOMER_STATUS+" "+awardedHuntDetails+".*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+AWARDEDDECLINEDBYCUSTOMER_STATUS+" "+awardedHuntDetails+".*";
		lotteryDetails_orderHistory =lotteryDetails_accountOverview;
	}
}
