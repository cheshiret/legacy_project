package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.legalname;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
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
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify lottery legal name (not product name) displaying
 * 1. Start lottery application flow as a Group Leader until check out the shopping cart successfully
 *     Product list page, Product category page, Lottery Application Details page, Select Hunts page, Add Members page, Education Provide Number page, Shopping cart page, Confirmation page
 * 2. Check under My Account 
 *     Recent Orders section, Order History, Lottery Application
 * @Preconditions:
 * @LinkSetUp:
 * D_WEB_HF_SIGNUPACCOUNT:id=1580,1590
 * D_HF_ADD_PRIVILEGE_PRD:id=3120 
 * D_HF_ADD_PRICING:id=4552,4562
 * D_HF_ASSI_PRI_TO_STORE:id=2320 
 * D_HF_ADD_QTY_CONTROL:id=2300 
 * D_HF_ADD_PRVI_LICENSE_YEAR:id=3230
 * d_hf_add_hunt:id=1070 
 * d_hf_add_hunt_license_year:id=1190
 * D_HF_ASSIGN_PRIV_TO_HUNT:id=790 
 * D_HF_ADD_LOTTERY_PRD:id=560
 * d_hf_add_lottery_license_year:id=440 
 * d_hf_add_lottery_quantity_control:id=440
 * d_hf_assign_lottery_to_store:id=430
 * d_hf_assi_hunts_to_lottery:id=630
 * @SPEC:
 * HF Web - Product legal name setup [TC:118729] 
 * HF Web - Legal name displaying for majoy products sales flow (for automation) [TC:118734] 
 * @Task#:AUTO-2130 
 * 
 * @author SWang
 * @Date  Jul 14, 2014
 */
public class DisplayForMajoyPrdSalesFlow_SK extends HFSKLotteryApplicationTestCase{
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
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);
		hf.deleteEducationRecords(schema, cusNew.fName, cusNew.lName);
		
		try{
			//Go to lottery categories list page
			hf.signIn(url, cus.email, cus.password);
			hf.gotoLotteryCategoriesListPg(cus);

			//Start lottery application flow as a Group Leader until check out the shopping cart successfully
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Product list page
			lotteryCatListPg.verifyLotteryNameExist(lottery.getLegalName(), true);

			//Product category page
			lotteryCatListPg.filterPrivilege(lottery.getDisplayCategory(), lottery.getDisplaySubCategory(), lottery.getLicenseYear());
			lotteryCatListPg.verifyLotteryNameExist(lottery.getLegalName(), true);

			//Lottery Application Details page
			hf.gotoLotteryDetailsPgFromCatListPg(lottery.getLegalName());
			lotteryDetailsPg.verifyLotteryName(lottery.getLegalName());

			//Select Hunts page
			hf.applyLotteryAsGroupLeaderToAddHuntsPg();
			huntsSelectionPg.verifyLotteryName(lottery.getLegalName());

			//Add Members page
			huntsSelectionPg.addHuntChoices(hunt.getDescription());
			hf.submitLotteryHuntsToAddMembersPg();
			addMembersPg.verifyLotteryName(lottery.getLegalName());
			
			//Education Provide Number page
            hf.addNewMembersToEduInfoPg(Arrays.asList(cusNew));
            eduInfoPg.verifyProdName(lottery.getLegalName());
            
			//Shopping cart page
            hf.gotoShoppingCartPgEduInfoPg();
			shoppingCartPg.verifyLotteryCodeAndName(lottery.getCode(), lottery.getLegalName(), true);

			//Confirmation page
			lotteryOrderNum = hf.checkOutCartToConfirmationPage(pay);
			confirmPg.verifyLotteryCodeAndName(lottery.getCode(), lottery.getLegalName(), true);

			//Check under My Account
			//Make sure the legal name (not product name) displaying for this licence on each of following pages

			//Recent Orders section
			hf.gotoMyAccountPgFromHeaderBar();
			accountOverviewPg.verifyRecentLotteryOrderItemText(lotteryOrderNum, lotteryLegalNameRegx);

			//Order History
			hf.gotoOrderHistoryPgFromMyAcctPanel();
			orderHistoryPg.verifyLotteryName(lotteryOrderNum, lottery.getLegalName()); 

			//Lottery Application
			hf.gotoLotteryAppPgFromAccountOverviewPg();
			lotteryAppPg.verifyLotteryAppAttrs(lotteryOrderNum, lotteryLegalNameRegx);

			hf.signOut();
		}finally{
			//Reverse lottery order in License Manager
			if(StringUtil.notEmpty(lotteryOrderNum))
				new com.activenetwork.qa.awo.supportscripts.function.license.ReversePriviLotteryOrderFunction().execute(loginLM, lotteryOrderNum);
		}
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00501@webhftest.com";
		cus.fName = "SKLotteryPrivLegalName_FN";
		cus.lName = "SKLotteryPrivLegalName_LN";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4301";
		cus.identifier.state = "Ontario";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = RESID_STATUS_SK;

		cusNew.custNum = hfab.getCustomerNumByEmail("hfsk_00502@webhftest.com", schema);
		System.out.println("cusNew.custNum:"+cusNew.custNum);
		cusNew.fName = "SKLotteryPrivLegalName2_FN";
		cusNew.lName = "SKLotteryPrivLegalName2_LN";
		cusNew.dateOfBirth = cus.dateOfBirth;

		//Lottery parameters
		lottery.setCode("WLV");
		lottery.setDescription("WebLottery22");
		lottery.setLegalName("WebLottery22 Legal");
		lottery.setLicenseYear(hf.getFiscalYear(schema));
		lottery.setDisplayCategory("Angling");
		lottery.setDisplaySubCategory("Annual");
		lotteryLegalNameRegx = ".*"+lottery.getLegalName()+".*";

		//Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("LAH27");
		hunt.setDescription("Lottery App Hunt 27");

		//Payment info
		pay.payType = Payment.PAY_VISA;
		pay.creditCardNumber = "4112344112344113"; 

		//Void note and reason
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation Testing";

		//Login License Manager
		loginLM.url = AwoUtil.getOrmsURL(env);
		loginLM.userName = TestProperty.getProperty("orms.fm.user");
		loginLM.password = TestProperty.getProperty("orms.fm.pw");
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
	}
}
