package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
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
public class AwardAcceptedByCust extends HFSKLotteryApplicationTestCase {

	@Override
	public void execute() {
		//Precondition: Make Award Lottery application
		prepareLotteryAppInLM();

		try{
		//Go to HFSK lottery application page to check
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();

		//Check lottery application status and purchase license page title during cancel to purchase license
		cancelToPurchaseLicense();

		//Purchase license to shopping cart page to check privilege name and license year
		hf.acceptAwardToCart(lotteryOrderNum, cus.residencyStatus);
		verifyPrivAndLicenseYearInCart();

		//Check lottery application status in lottery application page after abandon cart 
		hf.abandonCart();
		hf.gotoLotteryAppPgFromHeaderBar();
		lotteryAppPg.verifyLotteryAppStatus(lotteryOrderNum, AWARDED_STATUS);

		//Check lottery application status after successfully purchase license
		hf.acceptAwardToCart(lotteryOrderNum, cus.residencyStatus);
		hf.checkOutCart(pay);

		hf.gotoLotteryAppPgFromHeaderBar();
		lotteryAppPg.verifyLotteryAppStatus(lotteryOrderNum, AWARDEDACCEPTEDBYCUSTOMER_STATUS);
		}finally{
			if(!lotteryAppPg.exists()){
				hf.gotoLotteryAppPgFromHeaderBar();
			}
			if(lotteryAppPg.getLotteryAppStatus(lotteryOrderNum).equals(AWARDED_STATUS)){
				hf.declineAward(lotteryOrderNum, cus.residencyStatus);
			}
		}
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4186";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//hunt info
		hunt.setHuntCode("LAH4");

		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WL3");
		lottery.setDescription("WebLottery03");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setApplicantType("Individual");

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
		schedule.setExecutionConfig("WebLottery03Config");
		schedule.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		schedule.setDescription("WebLottery03Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);

		acceptAwardPgTitle = "Purchase License You are about to accept the draw award. The corresponding licence will be added to your cart and you will be redirected there.";
	}

	private void cancelToPurchaseLicense(){
		String purchaseLicensePgTitle = hf.gotoAcceptAwardPgFromLotteryAppPg(lotteryOrderNum);
		hf.gotoLotteryAppPgFromAcceptAwardPg();

		boolean result = MiscFunctions.compareResult("Purchase License page title", acceptAwardPgTitle, purchaseLicensePgTitle);
		result &= MiscFunctions.compareResult("Order status", AWARDED_STATUS, lotteryAppPg.getLotteryAppStatus(lotteryOrderNum));
		if(result){
			logger.info("Successfully verify purchase license page title and lottery application status during cancel to purchase license.");
		}else throw new ErrorOnPageException("Failed to verify purchase license page title and lottery application status during cancel to purchase license.");
	}

	private void verifyPrivAndLicenseYearInCart(){
		String result = shoppingCartPg.getPrivItemInfoByPrivNameAndLY(privilege.name, privilege.licenseYear);
		if(StringUtil.notEmpty(result)){
			logger.info("Successfully verify privilege name and license year in Shopping cart page.");
		}else throw new ErrorOnPageException("Failed to verify privilege name and license year in Shopping cart page.");
	}
}
