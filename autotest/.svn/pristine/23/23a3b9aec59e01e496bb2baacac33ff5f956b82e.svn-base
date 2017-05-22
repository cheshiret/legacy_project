package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Add and Remove choices on Add Hunts page, and verify:
 * 1. Add Choice and Choice Added buttons on Add Hunts page.
 * 2. Hunts Widget display
 * @Preconditions:
 * 1. The hunts "Web Lottery Hunt 001", "Web Lottery Hunt 010", "Web Lottery Hunt 011" exist.
 * 2. All the hunts have been assigned to the lottery "Lottery With Mult Hunts".
 * @SPEC:  
 * List Available Hunts - Action button (Add Choice / Choice Added) [TC:056238]
 * Widget - No points - Add/Remove choice [TC:056239]
 * Widget - Points+Hunts - Add/Remove choice [TC:056240]
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
 * @Date  Feb 14, 2014
 */
public class HuntsWidget_AddAndRemoveChoice extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private String hunt1, hunt2, hunt3;
	private String widgetTitle, widgetHeader, widgetMsg, pointType;
	private List<String> widgetItemOrder;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());
		
		// Apply as individual without points
		this.applyLotteryAndVerifyHuntsWidget(false);
		
		// Submit as individual with points
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		this.applyLotteryAndVerifyHuntsWidget(true);
		
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
		lottery.setMaxChoices("5");
		pointType = "AutoPointType"; // The point type is added to the lottery.
		
		// Hunt Info
		hunt1 = "Web Lottery Hunt 001";
		hunt2 = "Web Lottery Hunt 010";
		hunt3 = "Web Lottery Hunt 011";
		
		// Widget info
		widgetTitle =  "My Hunts";
		widgetHeader = " of " + lottery.getMaxChoices() + " choices added";
		// The message is configurable in /opt/sites/qa3/uwppl/docs/properties/config/hf/HFPrivilegesResources.properties, key=lottery.hunts.widget.init.text_HFSK
		widgetMsg = "Please add WMZ selections. As you add choices,they will appear here and you can remove or re-sort them.";
		
		widgetItemOrder = new ArrayList<String>();
		widgetItemOrder.add("1st");
		widgetItemOrder.add("2nd");
		widgetItemOrder.add("3rd");
		widgetItemOrder.add("4th");
		
	}

	private void applyLotteryAndVerifyHuntsWidget(boolean isPurchasePoints) {
		if (isPurchasePoints) {
			hf.submitLotteryWithPointsAsIndividualToAddHuntsPg();
		} else {
			hf.applyLotteryAsIndividualToAddHuntsPg();
		}
		// Verify Hunts widget when no hunts added
		this.verifyHuntsWidget(false, isPurchasePoints, new String[0]);
		huntsPg.openHuntWidget();
		this.verifyHuntsWidget(true, isPurchasePoints, new String[0]);
		huntsPg.closeHuntWidget();
		
		// add hunt to choice widget 
		huntsPg.addHuntChoices(hunt1);
		this.verifyHuntsWidget(true, isPurchasePoints, hunt1);
		
		// add more hunts and verify widget
		huntsPg.addHuntChoices(hunt2, hunt3);
		this.verifyHuntsWidget(true, isPurchasePoints, hunt1, hunt2, hunt3);
		
		// click Choice Added X, verify hunt removed and button changed back
		huntsPg.removeHuntChoice(hunt1);
		this.verifyHuntsWidget(true, isPurchasePoints, hunt2, hunt3);
		
		// remove hunt from widget and check the widget
		huntsPg.addHuntChoices(hunt1);
		huntsPg.removeHuntChoicesFromHuntWidget(hunt3);
		this.verifyHuntsWidget(true, isPurchasePoints, hunt2, hunt1);
		
		// remove all, check result
		huntsPg.removeAllHuntChoicesFromHuntWidget();
		huntsPg.openHuntWidget();
		this.verifyHuntsWidget(true, isPurchasePoints, new String[0]);			
	}
	
	private void verifyHuntsWidget(boolean isOpened, boolean isPurchasePoints, String... huntDescs) {
		boolean result = true;
		int totalHunts = huntDescs.length;
		
		result &= MiscFunctions.compareResult("Hunt Widget Opened", isOpened, huntsPg.checkHuntWidgetOpened());
		result &= MiscFunctions.compareResult("Hunt Widget Closed", !isOpened, huntsPg.checkHuntWidgetClosed());
		result &= MiscFunctions.compareString("Hunt Widget Title", widgetTitle, huntsPg.getHuntWidgetTitle());
		if (isOpened) {
			// content title - # of # choices added
			String header = isPurchasePoints ? (totalHunts+1)+widgetHeader : totalHunts+widgetHeader;
			result &= MiscFunctions.compareString("Hunt Widget header", header, huntsPg.getHuntWidgetHeader());
			
			// remove choice link, no-hunts message, items, submit button
			boolean hasHunts = totalHunts > 0;
			result &= MiscFunctions.compareResult("Hunt Widget Remove Choices link exist", hasHunts, huntsPg.checkHuntWidgetRemoveChoicesLinkExist());
			result &= MiscFunctions.compareResult("Hunt Widget no hunts message exist", hasHunts, !huntsPg.getHuntWidgetText().contains(widgetMsg));
			result &= MiscFunctions.compareResult("Hunt Widget Item exist", hasHunts||isPurchasePoints, huntsPg.checkHuntWidgetItemExist());
			result &= MiscFunctions.compareResult("Hunt Widget Submit button enabled", hasHunts||isPurchasePoints, huntsPg.checkHuntWidgetSubmitBtnEnabled());
			
			// point type
			if (isPurchasePoints) {
				result &= MiscFunctions.compareResult("Hunt widget item order list enabled for point", false, huntsPg.checkHuntWidgetPointOrderListEnabled());
				result &= MiscFunctions.compareString("Hunt widget item order for point", widgetItemOrder.get(0), huntsPg.getHuntWidgetPointOrder(0));
				result &= MiscFunctions.compareString("Added Point Type Description", pointType, huntsPg.getHuntWidgetPointType());
				result &= MiscFunctions.compareResult("Hunt widget remove choice link for point exist", false, huntsPg.checkHuntWidgetRemoveChoiceLinkExist(pointType));
			}
			
			// added hunts
			if (hasHunts) {
				int startIndex = isPurchasePoints ? 1 : 0;
				result &= MiscFunctions.compareListString("Hunt Widget sort orders", widgetItemOrder.subList(startIndex, totalHunts+startIndex), 
						huntsPg.getHuntWidgetItemOrderOptions());
//				if (startIndex != totalHunts) {
//					result &= MiscFunctions.compareListString("Hunt Widget sort orders", widgetItemOrder.subList(startIndex, totalHunts), 
//							huntsPg.getHuntWidgetItemOrderOptions());
//				} else {
//					List<String> expOpts = huntsPg.getHuntWidgetItemOrderOptions();
//					result &= MiscFunctions.compareResult("Hunt Widget sort orders number", 1, expOpts.size());
//					result &= MiscFunctions.compareString("Hunt Widget sort orders", widgetItemOrder.get(startIndex), expOpts.get(0));
//				}
				result &= MiscFunctions.compareResult("Total added hunts", totalHunts+startIndex, huntsPg.getNumOfAddedHunts());
				for (int i = 0; i < totalHunts; i++) {
					String des = huntDescs[i];
					result &= MiscFunctions.compareString("Hunt widget item order", widgetItemOrder.get(i+startIndex), huntsPg.getHuntWidgetItemOrder(i));
					result &= MiscFunctions.compareString("Added Hunt Description", des, huntsPg.getHuntWidgetItemDesc(des));
					result &= MiscFunctions.compareResult("Hunt widget remove choice link exist", true, huntsPg.checkHuntWidgetRemoveChoiceLinkExist(des));
				}
			}
		} 
		
		if (!result) {
			throw new ErrorOnPageException("Hunts widget is displayed wrongly!");
		}
		logger.info("Successfully verify hunts widget!");
	}
}
