package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: (P) Verify Lottery application attributes in Account overview, Order history and Lottery Application page when account has points only application
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1260
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
 * Lottery Applications - Various status applications [TC:053099] 
 * @Task#:Auto-1724
 * 
 * @author Swang
 * @Date  Feb 11, 2014
 */
public class Entered_PointOnly extends HFSKLotteryApplicationTestCase {

	public void execute() {
		//Precondition: Make "Entered" point only lottery application
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
		accountOverviewPg.verifyRecentLotteryOrderItemText(lotteryOrderNum, lotteryDetails_accountOverview);
		accountOverviewPg.verifyNoLotteries();

		//In Order History page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyOrderHistoryAttrs(lotteryOrderNum, lotteryDetails_orderHistory);

		//In lottery application page
		hf.gotoLotteryAppPgFromAccountOverviewPg();
		lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryDetails_lotteryApp);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4181";
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

		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		lotteryAppDate = DateFunctions.formatDate(DateFunctions.getToday(timeZone), "E MMM dd yyyy");
	}

	public void generateLotteryAppAndItsOrderHistoryAttrs(){
		lotteryDetails_lotteryApp = lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+lotteryAppDate+" "+label_PointType+" 1 "+label_ApplicationNum+" "+lotteryOrderNum;
		lotteryDetails_orderHistory = label_BigGameDrawOrderNum+" "+lotteryOrderNum+" "+lotteryAppDate+" "+lottery.getDescription()+" \\("+lottery.getLicenseYear()+"\\) "+label_PointType+" 1 "+label_ApplicationNum+" "+lotteryOrderNum+" "+label_ApplicationFee+" \\$\\d+\\.\\d+";
		lotteryDetails_accountOverview = lotteryDetails_orderHistory;
	}
}
