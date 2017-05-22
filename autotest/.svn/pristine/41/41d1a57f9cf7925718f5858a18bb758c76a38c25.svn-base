package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: (P) Verify no results message in HFSK Lottery application page when account has 'Reversed' status lottery order
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1330 --RCMP #|1R9Y4x4188
 * d_hf_add_privilege_prd:id=2600 --LAA,Lottery App Pri 01
 * d_hf_add_pricing:id=3712,3732 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1820
 * d_hf_add_prvi_license_year:id=2710
 * d_hf_add_qty_control:id=1800
 * d_hf_add_hunt:id=450 --LAH1 
 * d_hf_add_hunt_license_year:id=440
 * d_hf_assign_priv_to_hunt:id=330
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_lottery_prd:id=140 --WL1
 * d_hf_add_lottery_license_year:id=60
 * d_hf_assign_lottery_to_store:id=60
 * d_hf_add_lottery_quantity_control:id=60
 * d_hf_assi_hunts_to_lottery:id=30
 * d_hf_add_lottery_execution_config:id=50 --WebLottery01Config
 * d_hf_add_lottery_schedule:id=30 --WebLottery01Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - blank applications list section [TC:058282] 
 * @Task#:Auto-1724
 * 
 * @author SWang
 * @Date  Feb 10, 2014
 */
public class BlankApp_ReversedLotteryOrder extends HFSKLotteryApplicationTestCase {

	@Override
	public void execute() {
		//Precondition: Make Entered lottery application then to Reverse it
		//* make a lottery
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cus, lottery);
		lotteryOrderNum = lm.processOrderCart(pay);

		//* reverse lottery order
		lm.gotoApplicationOrderDetailsPageFromTopMenu(lotteryOrderNum);
		lm.reverseAppOrderToAppOrderDetailsPg(reverseReason, reverseNote);
		lm.logOutLicenseManager();
     
		//Go to HFSK lottery application page to check
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();
		
		//no results message in lottery application page
		lotteryAppPg.verifyNoResultMsg(noResultsMsg);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4188";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";
		
		//lottery info
		lottery.setCode("WL1");
		lottery.setDescription("WebLottery01");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList("LAH1"));
		lottery.setApplicantType("Individual");
	}
}
