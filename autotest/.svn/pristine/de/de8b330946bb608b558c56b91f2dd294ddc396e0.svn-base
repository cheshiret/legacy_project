package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.accountoverview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (UWP-1681) 
 * 1. In account overview page, check "More draw applications" link displays or not in Awarded Lottery Application section
 * 2. Click this link to lottery application page to check related orders
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1630
 * D_HF_ADD_PRIVILEGE_PRD:id=3240 TLM-TestLotteryMorePriv 
 * D_HF_ADD_PRICING:id=4682
 * D_HF_ASSI_PRI_TO_STORE:id=2440
 * D_HF_ADD_QTY_CONTROL:id=2420 
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3350
 * D_HF_ADD_HUNT:id=1090 LAMH - Lottery App More Hunt 
 * D_HF_ADD_HUNT_LICENSE_YEAR:id=1210
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=810
 * D_HF_ADD_LOTTERY_PRD:id=580 WLW - WebLottteryForMoreTest 
 * D_HF_ADD_PRICING:id=4672
 * d_hf_add_lottery_license_year:id=460
 * d_hf_add_lottery_quantity_control:id=460
 * d_hf_assign_lottery_to_store:id=450
 * d_hf_assi_hunts_to_lottery:id=650
 * D_HF_ADD_LOTTERY_EXECUTION_CON:id=310
 * D_HF_ADD_LOTTERY_SCHEDULE:id=350
 * @SPEC:Account Overview page - Awarded Lottery Applications - 'More Lottery Application' link - displaying / not displaying [TC:057417] 
 * @Task#:AUTO-2174
 * 
 * @author Swang
 * @Date  Jul 24, 2014
 */
public class AwardedLotteryApp_MoreLink extends HFSKLotteryApplicationTestCase {
	List<String> lotteryOrderNumsTemp = new ArrayList<String>();
	
	@Override
	public void execute() {
		//Precondition1: Make 4 award lottery applications in License Manager
		prepareLotteryAppInLM(1);

		//Check1: Go to HFSK account overview page, "More draw applications" link displays and after click it can find these 4 orders
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		verifyMoreDrawAppLinkAndRelatedOrders(true, lotteryOrderNums, Arrays.asList(new String[]{AWARDED_STATUS,AWARDED_STATUS,AWARDED_STATUS,AWARDED_STATUS}));
		
		//Precondition2: Decline the lotteryOrderNums.get(3) lottery application in web
		hf.declineAward(lotteryOrderNum, cus.residencyStatus);
		
		//Check2: In account overview page, "More draw applications" link displays and after click it can find these 4 orders
		verifyMoreDrawAppLinkAndRelatedOrders(true, lotteryOrderNums, Arrays.asList(new String[]{AWARDED_STATUS,AWARDED_STATUS,AWARDED_STATUS,AWARDEDDECLINEDBYCUSTOMER_STATUS}));
		hf.signOut();
		
		//Precondition3: Reverse the lotteryOrderNums.get(2) lottery application in License Manager
		new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNums.get(2));
		
        //Check3: Go to HFSK account overview page, "More draw applications" link displays and after click it can find these 3 orders
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		verifyMoreDrawAppLinkAndRelatedOrders(true, lotteryOrderNums, Arrays.asList(new String[]{AWARDED_STATUS,AWARDED_STATUS,StringUtil.EMPTY,AWARDEDDECLINEDBYCUSTOMER_STATUS}));
		hf.signOut();
		
		//Precondition4: Reverse the lotteryOrderNums.get(1) lottery application in License Manager
		new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNums.get(1));

        //Check4: Go to HFSK account overview page, "More draw applications" link doesn't display and after click it can find these 2 orders
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		verifyMoreDrawAppLinkAndRelatedOrders(false, lotteryOrderNums, Arrays.asList(new String[]{AWARDED_STATUS,StringUtil.EMPTY,StringUtil.EMPTY,AWARDEDDECLINEDBYCUSTOMER_STATUS}));
		lotteryOrderNumsTemp = lotteryOrderNums;
		hf.signOut();
		
		//Precondition5: Prepare another Entered lottery application
		prepareLotteryAppInLM(false, false, 1);
		lotteryOrderNumsTemp.addAll(lotteryOrderNums);
		
        //Check5: Go to HFSK account overview page, "More draw applications" link displays and after click it can find these 3 orders
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		verifyMoreDrawAppLinkAndRelatedOrders(true, lotteryOrderNumsTemp, Arrays.asList(new String[]{AWARDED_STATUS,StringUtil.EMPTY,StringUtil.EMPTY,AWARDEDDECLINEDBYCUSTOMER_STATUS,ENTERED_STATUS}));
		hf.signOut();
		
		//Precondition6: Reverse this Entered lottery application
		new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNums.get(0));
		
        //Check6: Go to HFSK account overview page, "More draw applications" link doesn't display and after click it can find these 2 orders
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		verifyMoreDrawAppLinkAndRelatedOrders(false, lotteryOrderNumsTemp, Arrays.asList(new String[]{AWARDED_STATUS,StringUtil.EMPTY,StringUtil.EMPTY,AWARDEDDECLINEDBYCUSTOMER_STATUS,StringUtil.EMPTY}));
		hf.signOut();
		
		//Clean
		new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNumsTemp.get(0));
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4306";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAMH");

		//lottery info
		lottery.setCode("WLW");
		lottery.setDescription("WebLottteryForMoreTest");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(quota.getLicenseYear()); 
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
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
		schedule.setExecutionConfig("WebLotteryForMoreTestConfig");
		schedule.setLicenseYear(quota.getLicenseYear()); 
		schedule.setDescription("WebLotteryForMoreTestProcessing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);
		schedule.setSpecificDate(lotteryAppDate);
        schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(2));
        schedule.setAwardAcceptanceByTime("12:00");
        schedule.setAwardAcceptanceByAmPm("AM");
        
		//Privilege
		privilege.name = "TestLotteryMorePriv";
		privilege.licenseYear = quota.getLicenseYear();
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		lotteryAppDate = DateFunctions.formatDate(DateFunctions.getToday(timeZone), "E MMM dd yyyy");
		
		//Payment info
		pay.payType = Payment.PAY_VISA;
		pay.creditCardNumber = "4112344112344113"; 
		
		//Login License Manager
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
	}
	
	private void verifyMoreDrawAppLinkAndRelatedOrders(boolean moreDrawAppLinkExisted, List<String>lotteryOrders, List<String>lotteryOrderStatus){
		boolean result = MiscFunctions.compareResult("More Draw Application link", moreDrawAppLinkExisted, accountOverviewPg.isMoreApplicationsLinkExist());
		hf.LotteryAppPgFromAccountOverviewPg();
		for(int i=0; i<lotteryOrders.size(); i++){
			if(StringUtil.isEmpty(lotteryOrderStatus.get(i))){
				result &= MiscFunctions.compareResult("Order:"+lotteryOrders.get(i)+" doesn't exist", false, lotteryAppPg.isLotteryAppExist(lotteryOrders.get(i)));
			}
			lotteryAppPg.verifyLotteryAppExist(lotteryOrderNums.get(3), false);
			result &= MiscFunctions.compareResult("Order:"+lotteryOrders.get(i)+" status", true, lotteryAppPg.checkLotteryAppStatus(lotteryOrders.get(i), lotteryOrderStatus.get(i)));
		}
		if(!result){
			throw new ErrorOnPageException("Failed to verify all More Draw Application link and related orders");
		}else logger.info("Successfully verify all More Draw Application link and related orders");
	}
}

