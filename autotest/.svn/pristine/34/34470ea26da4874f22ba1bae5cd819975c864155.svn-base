package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) No award instruction message in account overview, order history and lottery application page 
 * when used privilege has has print document, Fulfillment Method is "Print Immediately".
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1380 --RCMP #|1R9Y4x4203
 * d_hf_add_privilege_prd:id=2660 --LAE,Lottery App Pri 05
 * d_hf_add_print_doc:id=450
 * d_hf_add_pricing:id=3772,4052 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1880
 * d_hf_add_prvi_license_year:id=2780
 * d_hf_add_qty_control:id=1860
 * d_hf_add_hunt:id=950 --LAH20
 * d_hf_add_hunt_license_year:id=870
 * d_hf_assign_priv_to_hunt:id=650
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=490 --WLS
 * d_hf_add_lottery_license_year:id=300
 * d_hf_add_lottery_quantity_control:id=300
 * d_hf_assign_lottery_to_store:id=300
 * d_hf_assi_hunts_to_lottery:id=460
 * d_hf_add_lottery_execution_config:id=270 --WebLottery19Config
 * d_hf_add_lottery_schedule:id=310 --WebLottery19Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - Instructional text if Accepted Award is fulfilled by mail. [TC:058438]
 * @Task#: Auto-1725
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class Award_PrintImmediately extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
		//Precondition: Make Award Lottery application
		prepareLotteryAppInLM();
		generateInstructionalMsg();
		
		//Go to HFSK account overview page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();

		//No instructional message in lottery application, order history and account overview page
		//lottery application page
		lotteryAppPg.verifyLotteryAppStatus(lotteryOrderNum, AWARDED_STATUS);
		verifyInstructionalAwardMsg(lotteryAppPg, instructionalAwardMsgReg, false);
		
		//Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		verifyInstructionalAwardMsg(orderHistoryPg, instructionalAwardMsgReg, false);
		
		//Account overview page
		hf.gotoMyAccountPgFromHeaderBar();
		verifyInstructionalAwardMsg(accountOverviewPg, instructionalAwardMsgStringReg, false);

        hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4203";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH20");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLS");
		lottery.setDescription("WebLottery19");
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
		schedule.setExecutionConfig("WebLottery19Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery19Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 05";
		privilege.licenseYear = quota.getLicenseYear();
	}
}
