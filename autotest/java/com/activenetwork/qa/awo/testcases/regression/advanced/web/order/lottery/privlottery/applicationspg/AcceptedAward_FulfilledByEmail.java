package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Verify award instruction message only display in account overview and lottery application when lottery application has Award status
 * @Preconditions: 
 * 1.Award lottery application
 * 2.Privilege has print document, Fulfillment Method is "Fulfilled By Email"
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1390 --RCMP #|1R9Y4x4204
 * d_hf_add_privilege_prd:id=2670 --LAF,Lottery App Pri 06
 * d_hf_add_print_doc:id=460
 * d_hf_add_pricing:id=4002,4062 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1890
 * d_hf_add_prvi_license_year:id=2790
 * d_hf_add_qty_control:id=1870
 * d_hf_add_hunt:id=960 --LAH21
 * d_hf_add_hunt_license_year:id=880
 * d_hf_assign_priv_to_hunt:id=660
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=500 --WLT
 * d_hf_add_lottery_license_year:id=310
 * d_hf_add_lottery_quantity_control:id=310
 * d_hf_assign_lottery_to_store:id=310
 * d_hf_assi_hunts_to_lottery:id=470
 * d_hf_add_lottery_execution_config:id=280 --WebLottery20Config
 * d_hf_add_lottery_schedule:id=320 --WebLottery20Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - Instructional text if Accepted Award is fulfilled by mail. [TC:058438]
 * @Task#: Auto-2110
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class AcceptedAward_FulfilledByEmail extends HFSKLotteryApplicationTestCase{

	public void execute() {
		//Precondition: Make Award Lottery application in License Manager
		prepareLotteryAppInLM();
		generateInstructionalMsg();

		//Go to HFSK account overview page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();

		//For award lottery, have instructional message in account overview and lottery application page, not in order history page
		//Account overview page
		verifyInstructionalAwardMsg(accountOverviewPg, instructionalAwardMsgStringReg, true);

		//Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		verifyInstructionalAwardMsg(orderHistoryPg, instructionalAwardMsgReg, false);

		//lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		verifyInstructionalAwardMsg(lotteryAppPg, instructionalAwardMsgReg, true);

		//Accept award and then check instructional message doesn't display in previous three pages
		hf.acceptAwardToCart(lotteryOrderNum, cus.residencyStatus);
		hf.checkOutCart(pay);
		hf.gotoMyAccountPgFromHeaderBar();

		//In Account overview page
		verifyInstructionalAwardMsg(accountOverviewPg, instructionalAwardMsgStringReg, false);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		verifyInstructionalAwardMsg(orderHistoryPg, instructionalAwardMsgReg, false);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		verifyInstructionalAwardMsg(lotteryAppPg, instructionalAwardMsgReg, false);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4204";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH21");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLT");
		lottery.setDescription("WebLottery20");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(quota.getLicenseYear()); 
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setApplicantType("Individual");

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
		schedule.setExecutionConfig("WebLottery20Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery20Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 06";
		privilege.licenseYear = quota.getLicenseYear();
	}
}
