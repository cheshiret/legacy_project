package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
/**
 * 
 * @Description: (P) Verify Lottery application attributes in Account overview, Order history and Lottery Application page when account has Points + Hunts Individual application
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1270 --RCMP #|1R9Y4x4182
 * d_hf_add_privilege_prd:id=2610, 2620 --LAB,Lottery App Pri 02; LAC,Lottery App Pri 03
 * d_hf_add_pricing:id=3722,3752, 3742 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1830,1840
 * d_hf_add_prvi_license_year:id=2720,2730
 * d_hf_add_qty_control:id=1810,1820
 * d_hf_add_hunt:id=450 --LAH1 
 * d_hf_add_hunt_license_year:id=460,470 --LAH2, LAH3
 * d_hf_assign_priv_to_hunt:id=340,350
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=150 --WL2
 * d_hf_add_lottery_license_year:id=70
 * d_hf_assign_lottery_to_store:id=70
 * d_hf_add_lottery_quantity_control:id=70
 * d_hf_assi_hunts_to_lottery:id=40
 * @SPEC:
 * Lottery Applications - Entered Hunts Details [TC:058296] 
 * Lottery Applications - Various status applications [TC:053099] 
 * Lottery Applications - Show/Hide Entered Hunts link [TC:057928] 
 * @Task#:Auto-1724, Auto-1725
 * 
 * @author Swang
 * @Date  Feb 11, 2014
 */
public class Entered_PointsAndHuntsIndividual extends HFSKLotteryApplicationTestCase {

	@Override
	public void execute() {
		//Precondition: Make "Entered" Points + Hunts Individual application
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cus, lottery);
		lotteryOrderNum = lm.processOrderCart(pay);
		lm.logOutLicenseManager();

		//Go to HFSK to check lottery application attributes
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		generateLotteryAppAndItsOrderHistoryAttrs();

		//In Account overview page
		accountOverviewPg.verifyRecentLotteryAppAttrs(lotteryOrderNum, lotteryDetails_accountOverview, huntInfo_First+" "+huntInfo_second);

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyLotteryAppHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory, huntInfo_First+" "+huntInfo_second);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp, huntInfo_First+" "+huntInfo_second);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4182";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = "1986-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//lottery info
		lottery.setCode("WL2");
		lottery.setDescription("WebLottery02");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setApplicantType("Individual");
		lottery.setPointTypes(Arrays.asList("AutoPointType"));
		lottery.setHuntCodes(Arrays.asList("LAH2", "LAH3"));

		huntInfo_First = "1st Choice"+initializeEnteredLotteryHuntAttris(lottery.getHuntCodes().get(0));
		huntInfo_second = "2nd Choice"+initializeEnteredLotteryHuntAttris(lottery.getHuntCodes().get(1));
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_PointType+" 1 "+label_ApplicationNum+" "+lotteryOrderNum+" Status: "+ENTERED_STATUS+" Show Entered WMZs.*";
		lotteryDetails_orderHistory =label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+label_PointType+" 1 "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) Status: "+ENTERED_STATUS+" Show Entered WMZs.*"; 
		lotteryDetails_accountOverview = lotteryDetails_orderHistory;
	}
}
