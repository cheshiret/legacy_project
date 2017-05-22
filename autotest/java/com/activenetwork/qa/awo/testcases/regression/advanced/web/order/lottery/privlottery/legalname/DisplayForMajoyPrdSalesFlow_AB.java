package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.legalname;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryAddGroupMembersPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (P) Verify lottery legal name (not product name) displaying
 * 1. Start lottery application flow as a Group Leader until check out the shopping cart successfully
 *     Product list page, Product category page, Lottery Application Details page, Select Hunts page, Add Members page, Education Provide Number page, Shopping cart page, Confirmation page
 * 2. Check under My Account 
 *     Recent Orders section, Order History, Lottery Application
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1610,1620
 * D_HF_ADD_PRIVILEGE_PRD:id=3150,4602
 * D_HF_ADD_PRICING:id=4592
 * D_HF_ASSI_PRI_TO_STORE:id=2350
 * D_HF_ADD_QTY_CONTROL:id=2330
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3260
 * d_hf_add_hunt_quota:id=340
 * d_hf_add_hunt:id=1080
 * d_hf_add_hunt_license_year:id=1200
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=800
 * D_HF_ADD_LOTTERY_PRD:id=570
 * d_hf_add_lottery_license_year:id=450
 * d_hf_add_lottery_quantity_control:id=450
 * d_hf_assign_lottery_to_store:id=440
 * d_hf_assi_hunts_to_lottery:id=640
 * @SPEC:
 * HF Web - Product legal name setup [TC:118729] 
 * HF Web - Legal name displaying for majoy products sales flow (for automation) [TC:118734] 
 * @Task#:AUTO-2130 
 * 
 * @author SWang
 * @Date  Jul 14, 2014
 */
public class DisplayForMajoyPrdSalesFlow_AB extends HFABLotteryApplicationTestCase{
	private HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
	private HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private String lotteryOrderNum, lotteryLegalNameRegx;
	private HFLotteryCategoriesListPage lotteryCatListPg = HFLotteryCategoriesListPage.getInstance();
	private HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage.getInstance();
	private HFLotteryHuntsSelectionPage huntsSelectionPg = HFLotteryHuntsSelectionPage.getInstance();
	private HFLotteryAddGroupMembersPage addMembersPg = HFLotteryAddGroupMembersPage.getInstance();
	private HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();

	public void execute() {
		//Precondition: Delete account's all educations
		hfab.deleteEducationRecords(schema, cus.fName, cus.lName);
		hfab.deleteEducationRecords(schema, cusNew.fName, cusNew.lName);
		
		try{
			//Go to lottery categories list page
			hfab.signIn(url, cus.email, cus.password);
			hfab.gotoLotteryCategoriesListPg(cus);
			
			//Start lottery application flow as a Group Leader until check out the shopping cart successfully
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Product list page
			lotteryCatListPg.verifyLotteryNameExist(lottery.getLegalName(), true);

			//Product category page
			lotteryCatListPg.filterPrivilege(lottery.getDisplayCategory(), lottery.getDisplaySubCategory(), lottery.getLicenseYear());
			lotteryCatListPg.verifyLotteryNameExist(lottery.getLegalName(), true);

			//Lottery Application Details page
			hfab.gotoLotteryDetailsPgFromCatListPg(lottery.getLegalName());
			lotteryDetailsPg.verifyLotteryName(lottery.getLegalName());

			//Select Hunts page
			hfab.applyLotteryAsGroupLeaderToAddHuntsPg();
			huntsSelectionPg.verifyLotteryName(lottery.getLegalName());

			//Education Provide Number page
			huntsSelectionPg.addHuntChoices(hunt.getDescription());
			hfab.chooseLotteryHuntsToEduInfoPg(hunt);
			eduInfoPg.verifyProdName(lottery.getLegalName());
			
			//Add Members page
			hfab.goToAddMemberPgFromEduInfoPg();
			addMembersPg.verifyLotteryName(lottery.getLegalName());

			//Shopping cart page
			hfab.addNewMembersToCart(Arrays.asList(cusNew));
			shoppingCartPg.verifyLotteryCodeAndName(lottery.getCode(), lottery.getLegalName(), true);

			//Confirmation page
			lotteryOrderNum = hf.checkOutCartToConfirmationPage(pay);
			confirmPg.verifyLotteryCodeAndName(lottery.getCode(), lottery.getLegalName(), true);

			//Check under My Account
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Recent Orders section
			hfab.gotoMyAccountPgFromHeaderBar();
			accountOverviewPg.verifyRecentLotteryOrderItemText(lotteryOrderNum, lotteryLegalNameRegx);

			//Order History
			hfab.gotoOrderHistoryPgFromMyAcctPanel();
			orderHistoryPg.verifyLotteryName(lotteryOrderNum, lottery.getLegalName()); 

			//Lottery Application
			hfab.gotoLotteryAppPgFromAccountOverviewPg();
			lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryLegalNameRegx);

			hfab.signOut();
		}finally{
			//Reverse lottery order in License Manager
			if(StringUtil.notEmpty(lotteryOrderNum))
				new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNum);
		}
	}

	public void wrapParameters(Object[] param) {
		cus.custNum = hfab.getCustomerNumByEmail("hfab_00021@webhftest.com", schema);
		cus.email = cus.custNum;
		cus.residencyStatus = RESID_STATUS_ALBERTA;
		cus.fName = "HFAB_FN21";
		cus.lName = "HFAB_LN21";

		cusNew.custNum = hfab.getCustomerNumByEmail("hfab_00022@webhftest.com", schema);
		cusNew.fName = "HFAB_FN22";
		cusNew.lName = "HFAB_LN22";
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";

		//Lottery parameters
		lottery.setCode("ABA");
		lottery.setDescription("AB_Lottery01");
		lottery.setLegalName("AB_Lottery01 Legal");
		lottery.setLicenseYear(hf.getFiscalYear(schema));
		lottery.setDisplayCategory("Fishing Draw");
		lottery.setDisplaySubCategory("Walleye");
		lotteryLegalNameRegx = ".*"+lottery.getLegalName()+".*";

		//Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("ABH1");
		hunt.setDescription("AB Lottery App Hunt 01");

		//Login License Manager
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
		loginLM.location = "AB - Call Center Manager - Auto/DOUBLE L CONFECTIONERY(Store Loc)";
	}
}

