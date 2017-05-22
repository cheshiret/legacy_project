package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:For Not awarded lottery application with group leader Applicant type, 
 * check lottery attributes and entered hunts in account overview, order history and lottery application page
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1450--RCMP #|1R9Y4x4200
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,4022--Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=920 --LAH17
 * d_hf_add_hunt_license_year:id=840
 * d_hf_assign_priv_to_hunt:id=620
 * d_hf_add_hunt_quota:id=330
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=460 --WLP
 * d_hf_add_lottery_license_year:id=270
 * d_hf_add_lottery_quantity_control:id=270
 * d_hf_assign_lottery_to_store=270
 * d_hf_assi_hunts_to_lottery:id=430
 * d_hf_add_lottery_execution_config:id=240 --WebLottery17Config
 * d_hf_add_lottery_schedule:id=280 --WebLottery17Processing
 * @SPEC:Lottery Applications - Various status applications [TC:053099] 
 * @Task#:AUTO-2110
 * 
 * @author SWang
 * @Date  Mar 9, 2014
 */
public class NotAwarded_GroupLeader extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
		//Precondition: Make not award Lottery application in license manager
		prepareLotteryAppInLM(2);

		//Go to HFSK, account overview, order history and lottery application page to check lottery application attributes and entered hunts info
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In Account overview page
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, groupMemberInfo, false, true);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, groupMemberInfo, false, true);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, groupMemberInfo, false, true);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4198";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//the second customer info
		cusNew.fName = "LotteryApp_FN7";
		cusNew.lName = "LotteryApp_LN7";
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"0101";
		cusNew.custNum = lm.getCustomerNumByCustName(cusNew.lName, cusNew.fName, schema);
		initializeGroupMemberInfo();

		//hunt info
		hunt.setHuntCode("LAH18");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLQ");
		lottery.setDescription("WebLottery17");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(quota.getLicenseYear());
		lottery.setApplicantType("Group (Create NEW Group)");
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		List<String[]> groupMumbers = new ArrayList<String[]>();
		groupMumbers.add(new String[]{cusNew.custNum,cusNew.dateOfBirth});
		lottery.setGroupMembers(groupMumbers);

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
		schedule.setExecutionConfig("WebLottery17Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery17Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear();
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+NOTAWARDED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_accountOverview =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+NOTAWARDED_STATUS+initializeEnteredLotteryHuntAttris()+".*";
		lotteryDetails_orderHistory =lotteryDetails_accountOverview;
	}
}
