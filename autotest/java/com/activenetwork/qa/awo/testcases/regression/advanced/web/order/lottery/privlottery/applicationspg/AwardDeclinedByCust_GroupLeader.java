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
 * @Description: For award Declined by customer lottery application with group leader Applicant type, 
 * check lottery attributes and entered hunts info in account overview, order history and lottery application page
 * @Preconditions: 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1390 --RCMP #|1R9Y4x4194
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3942--Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=660 --LAH11
 * d_hf_add_hunt_license_year:id=780
 * d_hf_assign_priv_to_hunt:id=560
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=300 --WLJ
 * d_hf_add_lottery_license_year:id=210
 * d_hf_add_lottery_quantity_control:id=210
 * d_hf_assi_hunts_to_lottery:id=270
 * d_hf_add_lottery_execution_config:id=190 --WebLottery10Config
 * d_hf_add_lottery_schedule:id=130 --WebLottery10Processing
 * @SPEC:Lottery Applications - Various status applications [TC:053099] 
 * @Task#:AUTO-2110
 * 
 * @author SWang
 * @Date  Mar 9, 2014
 */
public class AwardDeclinedByCust_GroupLeader extends HFSKLotteryApplicationTestCase{

	@Override
	public void execute() {
		//Precondition: 
		//* Make Award Lottery application in license manager
		prepareLotteryAppInLM();

        //* Decline award in HFSK
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();
		hf.declineAward(lotteryOrderNum, cus.residencyStatus);

		//check lottery application attributes
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In lottery application page
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, groupMemberInfo, false, true);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, groupMemberInfo, false, true);

		//In Account overview page
		hf.gotoMyAccountPgFromHeaderBar();
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, groupMemberInfo, false, true);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4194";
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
		hunt.setHuntCode("LAH11");

		//quota info
		quota.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WLJ");
		lottery.setDescription("WebLottery10");
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
		schedule.setExecutionConfig("WebLottery10Config");
		schedule.setLicenseYear(quota.getLicenseYear());
		schedule.setDescription("WebLottery10Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = quota.getLicenseYear();

		initializeAwardedLotteryHuntAttris();
		awardedHuntDetails = "Awarded Hunt "+awardedLotteryHuntAttris;
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+AWARDEDDECLINEDBYCUSTOMER_STATUS+" "+awardedHuntDetails+" Show Group Members.*";
		lotteryDetails_orderHistory = label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+AWARDEDDECLINEDBYCUSTOMER_STATUS+" "+awardedHuntDetails+" Show Group Members.*";
		lotteryDetails_accountOverview = lotteryDetails_orderHistory;
	}
}
