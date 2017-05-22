package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) After award decline by Customer, no award instruction message in account overview, order history and lottery application page
 * @Preconditions:
 * 1.Award lottery application
 * 2.Privilege has print document, Fulfillment Method is "Fulfilled By Email"
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1500--RCMP #|1R9Y4x425
 * d_hf_add_privilege_prd:id=2670 --LAF,Lottery App Pri 06
 * d_hf_add_print_doc:id=460
 * d_hf_add_pricing:id=4002,4072 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1890
 * d_hf_add_prvi_license_year:id=2790
 * d_hf_add_qty_control:id=1870
 * d_hf_add_hunt:id=970 --LAH22
 * d_hf_add_hunt_license_year:id=890
 * d_hf_assign_priv_to_hunt:id=670
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=510 --WLU
 * d_hf_add_lottery_license_year:id=320
 * d_hf_add_lottery_quantity_control:id=320
 * d_hf_assign_lottery_to_store:id=440
 * d_hf_assi_hunts_to_lottery:id=480
 * d_hf_add_lottery_execution_config:id=290 --WebLottery21Config
 * d_hf_add_lottery_schedule:id=330 --WebLottery21Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - Instructional text if Accepted Award is fulfilled by mail. [TC:058438]
 * @Task#: Auto-2110
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class DeclineAward_FulfilledByEmail extends HFSKLotteryApplicationTestCase {

	public void execute() {
		//Precondition: 
		//* Make Award Lottery application in License Manager
		prepareLotteryAppInLM();
		//* Declined award in HFSK
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();
		hf.declineAward(lotteryOrderNum, cus.residencyStatus);

		//Check no instructional message in account overview, order history and lottery application page
		generateInstructionalMsg();

		//In lottery application page
		verifyInstructionalAwardMsg(lotteryAppPg, instructionalAwardMsgReg, false);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		verifyInstructionalAwardMsg(orderHistoryPg, instructionalAwardMsgReg, false);

		//In Account overview page
		hf.gotoMyAccountPgFromHeaderBar();
		verifyInstructionalAwardMsg(accountOverviewPg, instructionalAwardMsgStringReg, false);



		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4205";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH22");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLU");
		lottery.setDescription("WebLottery21");
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
		schedule.setExecutionConfig("WebLottery21Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery21Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 06";
		privilege.licenseYear = quota.getLicenseYear();
	}
}

