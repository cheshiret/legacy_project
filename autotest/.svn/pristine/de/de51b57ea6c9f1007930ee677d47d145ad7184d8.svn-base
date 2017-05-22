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
 * @Description: (P) In HFSK, check lottery application status, accept award page message, privilege and license year in shopping cart 
 * during accept, cancel accept Award lottery application or abandon shopping cart 
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1310 --RCMP #|1R9Y4x4186
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3782 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=600 --LAH4 
 * d_hf_add_hunt_license_year:id=470
 * d_hf_assign_priv_to_hunt:id=480
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=160 --WL3
 * d_hf_add_lottery_license_year:id=90
 * d_hf_add_lottery_quantity_control:id=90
 * d_hf_assi_hunts_to_lottery:id=50
 * d_hf_add_lottery_execution_config:id=60 --WebLottery03Config
 * d_hf_add_lottery_schedule:id=40 --WebLottery03Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - Accept Award [TC:058298] 
 * @Task#: Auto-1725
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class Award_GroupLeader extends HFSKLotteryApplicationTestCase {

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
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, groupMemberInfo, false, true);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, groupMemberInfo, false, true);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, groupMemberInfo, false, true);

		hf.declineAward(lotteryOrderNum, cus.residencyStatus);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4190";
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
		hunt.setHuntCode("LAH7");

		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WL6");
		lottery.setDescription("WebLottery06");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setApplicantType("Group (Create NEW Group)");
		lottery.setHuntCodes(Arrays.asList("LAH7"));
		List<String[]> groupMumbers = new ArrayList<String[]>();
		groupMumbers.add(new String[]{cusNew.custNum,cus.dateOfBirth});
		lottery.setGroupMembers(groupMumbers);

		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = "2014"; //lm.getFiscalYear(schema);
		licenseYear.sellFromDate = DateFunctions.getToday(timeZone);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";

		//lottery schedule info
		schedule.setExecutionConfig("WebLottery06Config");
		schedule.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		schedule.setDescription("WebLottery06Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		
		initializeAwardedLotteryHuntAttris();
		awardedHuntDetails = "Awarded Hunt "+awardedLotteryHuntAttris;
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+AWARDED_STATUS+" "+awardedHuntDetails+" Show Group Members.*";
		lotteryDetails_orderHistory =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+AWARDED_STATUS+" "+awardedHuntDetails+" Show Group Members.*"; 
		lotteryDetails_accountOverview = lotteryDetails_orderHistory;
	}
}