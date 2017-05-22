package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.accountoverview;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify there is no awarded lottery application section shown on account overview page when:
 * 1. No lottery application
 * 2. Has entered lottery application
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=10,1520
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_hunt:id=480
 * d_hf_add_hunt_license_year:id=510
 * d_hf_assign_priv_to_hunt:id=360
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @SPEC: Account Overview page - Awarded Lottery Application [TC:057416]
 * @Task#: Auto-1765
 * 
 * @author Lesley Wang
 * @Date  Mar 31, 2014
 */
public class AwardedLotteryApp_NotShown extends
		HFSKLotteryApplicationTestCase {
	private String emailWithoutLottery;
	private HFAccountOverviewPage overviewPg = HFAccountOverviewPage.getInstance();
	
	@Override
	public void execute() {
		// Verify the UI when login with an account without lottery application
		hf.signIn(url, emailWithoutLottery, cus.password);
		overviewPg.verifyNoLotteries();
		hf.signOut();
		
		// Apply a lottery application with another account and verify no awarded lottery application section shown
		hf.signIn(url, cus.email, cus.password);
		hf.submitLotteryWithoutPointsAsIndividualToCart(cus, lottery.getDescription(), hunt.getDescription());
		hf.checkOutCart(pay);
		
		hf.gotoMyAccountPgFromHeaderBar();
		overviewPg.verifyNoLotteries();
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		emailWithoutLottery = "validateaddress@test.com";
		cus.password = "asdfasdf";
		cus.email = "hfsk_00072@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4207";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		
		lottery.setDescription("Lottery With Mult Hunts");
		
		// Hunts Info
		hunt = new HuntInfo();
		hunt.setDescription("Web Lottery Hunt 001");
		hunt.setHuntCode("WLH1");
	}

}
