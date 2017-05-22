package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Apply lottery without points, re-order the added hunts in Hunts Widget on Hunt Selection page, and verify the display of the hunts
 * @Preconditions:
 * 1. The hunts "Web Lottery Hunt 001", "Web Lottery Hunt 010", "Web Lottery Hunt 011" exist.
 * 2. All the hunts have been assigned to the lottery "Lottery With Mult Hunts".
 * @SPEC: Widget - Re-order the choice [TC:056241]
 * @LinkSetUp: 
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40
 * d_hf_add_hunt_location:id=60,90
 * d_hf_add_date_period:id=60,70
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480,570,580
 * d_hf_add_hunt_license_year:id=510,600,610
 * d_hf_assign_priv_to_hunt:id=360,450,460
 * d_hf_add_hunt_quota:id=210,270
 * d_hf_add_lottery_prd:id=200
 * d_hf_add_lottery_license_year:id=80
 * d_hf_assign_lottery_to_store:id=80
 * d_hf_add_lottery_quantity_control:id=80
 * d_hf_assi_hunts_to_lottery:id=90
 * @Task#: Auto-1774
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2014
 */
public class HuntsWidget_ReorderChoices_NoPoints extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
	private HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
	private HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
	private HuntInfo hunt1, hunt2, hunt3;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		hf.applyLotteryAsIndividualToAddHuntsPg();
		
		// Add Hunt Choices and re-order them, verify the hunt choices order in hunt widget
		huntsPg.addHuntChoices(hunt1.getDescription(), hunt2.getDescription(), hunt3.getDescription());
		huntsPg.reorderHuntChoices(hunt2.getDescription(), hunt3.getDescription(), hunt1.getDescription());
		this.verifyHuntWidgetChoicesOrder(hunt2.getDescription(), hunt3.getDescription(), hunt1.getDescription());
		
		// Submit to shopping cart and verify the hunt choices order in shopping cart page
		hf.submitLotteryHuntsToCart();
		cartPg.verifyLotteryHuntChoices(hunt2.getHuntCode(), hunt3.getHuntCode(), hunt1.getHuntCode());
		String ordNum = hf.checkOutCart(pay);
	
		// Verify the hunt choices order in lottery application page
		hf.gotoLotteryAppPgFromHeaderBar();
		String expHuntsInfo = this.generateHuntsInfo(hunt2.getHuntCode(), hunt3.getHuntCode(), hunt1.getHuntCode());
		lotteryAppPg.verifyEnteredHuntsText(ordNum, expHuntsInfo);
		
		// Verify the hunt choices order in order history page
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		orderHistoryPg.verifyEnteredHuntsText(ordNum, expHuntsInfo);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// Lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		
		// Hunts Info
		hunt1 = new HuntInfo();
		hunt1.setDescription("Web Lottery Hunt 001");
		hunt1.setHuntCode("WLH1");
		
		hunt2 = new HuntInfo();
		hunt2.setDescription("Web Lottery Hunt 010");
		hunt2.setHuntCode("WLH10");
		
		hunt3 = new HuntInfo();
		hunt3.setDescription("Web Lottery Hunt 011");
		hunt3.setHuntCode("WLH11");
	}

	private void verifyHuntWidgetChoicesOrder(String firstHunt, String secHunt, String thirdHunt) {
		boolean result = true;
		String[] order = new String[] {"1st", "2nd", "3rd"};
		result &= MiscFunctions.compareString("Order of hunt choice " + firstHunt, order[0], huntsPg.getHuntWidgetItemOrder(firstHunt));
		result &= MiscFunctions.compareString("Order of hunt choice " + secHunt, order[1], huntsPg.getHuntWidgetItemOrder(secHunt));
		result &= MiscFunctions.compareString("Order of hunt choice " + thirdHunt, order[2], huntsPg.getHuntWidgetItemOrder(thirdHunt));
		
		if (!result) {
			throw new ErrorOnPageException("Hunt widget choices order is wrong!");
		}
		logger.info("Successfully verify hunt choices order in hunt widget!");
	}
	
	private String generateHuntsInfo(String firstHunt, String secHunt, String thirdHunt) {
		String info = "1st Choice.*"+firstHunt+".*2nd Choice.*"+secHunt+".*3rd Choice.*"+thirdHunt + ".*";
		return info;
	}
}
