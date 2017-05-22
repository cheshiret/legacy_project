package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.shoppingcart.timer;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckoutPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (p) 
 * @Preconditions:
 * 1. Make sure the site HILL in "Angel Island SP" park available in booking date.
 * 2. Make sure lottery schedule is setup for loop Sites 001-034 in STONY BROOK STATE PARK.
 * @LinkSetUp:
 * d_inv_new_lottery_program:id=30
 * 
 * @SPEC:
 * TC068519:Shopping Cart Timer - Add Second Count
 * TC068520:Shopping Cart Timer - Time out Message
 * @Task#: Auto-1868
 * 
 * @author Swang
 * @Date  Aug 18, 2013
 */
public class VerifyTimeOutMes extends WebTestCase {
	private UwpShoppingCartPage shopCartPg = UwpShoppingCartPage.getInstance();
	private UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
	private int totalTime, oneMinute;
	private BookingData bdLottory;
	private String timerFormatWithMinAndSec, timerFormatWithSecOnly, holdExpiredMes, schemaCA, schemaNY, reHoldMes;

	public void execute() {
		web.checkAndReleaseInventory(schemaCA, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay)*3, false, bd.siteID);
		web.checkAndReleaseInventory(schemaNY, bdLottory.arrivalDate, Integer.parseInt(bdLottory.lengthOfStay), false, bdLottory.siteID);

		//Make order to shopping cart page
		web.invokeURL(url);
		web.signIn(email, pw);
		web.makeReservationToCart(bd);

		//The timer is started, displays minutes & seconds two parts
		shopCartPg.verifyTimerFormat(timerFormatWithMinAndSec);

		//The timer is restarted after click continue shopping link, then add one more lottery product to cart
		bd.arrivalDate = DateFunctions.getDateAfterToday(11);
		web.makeMoreReservations();
		//		web.makeReservationToCart(bdLottory); //Update it to below one line code because of Defect-43676, OOPS page displays for caomping lottery
		web.makeReservationToCart(bd);
		shopCartPg.verifyTimeRestart(totalTime);

		//The timer shows seconds only after the timer is less than one minute
		verifyTimerDisplaysSecondsOnly();

		//Hold expired message displays after time out
		verifyHoldExpiredMes();

		//click link 'Items In Cart: XX'
		//"No Longer on Hold" message displays for camping reservation
		//Doesn't display for lottery application
		web.gotoCheckoutPgFromShoppingCartPg();
		checkoutPg.verifyErrorMsgExist(reHoldMes, true);

		//Sign out
		web.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		//camping reservation info
		bd.contractCode = "CA";
		bd.parkId = "120003";
		schemaCA = DataBaseFunctions.getSchemaName(bd.contractCode, env);
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schemaCA); //Angel Island SP
		bd.arrivalDate = DateFunctions.getDateAfterToday(8);
		bd.lengthOfStay = "2";
		bd.siteNo = "HILL";
		bd.siteID = web.getProductID(bd.siteNo, bd.parkId, schemaCA);

		//lottery info
		bdLottory = new BookingData();
		bdLottory.state = "New York";
		bdLottory.contractCode = "NY";
		schemaNY = DataBaseFunctions.getSchemaName(bdLottory.contractCode, env);
		bdLottory.parkId = "261"; 
		bdLottory.park = DataBaseFunctions.getFacilityName(bdLottory.parkId, schemaNY); //STONY BROOK STATE PARK
		bdLottory.conType = "State";
		bdLottory.loop = "Sites 001-034";
		bdLottory.siteNo = "004";
		bdLottory.siteID = web.getProductID(bdLottory.siteNo, bdLottory.parkId, schemaNY);
		bdLottory.arrivalDate = DateFunctions.getDateAfterGivenDay(web.queryLotteryInventoryStartDate(schemaNY, bdLottory.park, bdLottory.loop), 2) ;
		bdLottory.lengthOfStay = "2";

		oneMinute = 60; // 1 minute
		totalTime = 15*oneMinute; // 15 minutes

		timerFormatWithSecOnly = "\\d+( )?sec.";
		timerFormatWithMinAndSec = "\\d+( )?min( )?\\d+( )?sec.";
		holdExpiredMes = "Hold Expired!";
		reHoldMes = "All the items in the cart were re-held successfully";
	}

	private void verifyTimerDisplaysSecondsOnly(){
		String timerFromUI = StringUtil.EMPTY;
		int remainTime = -1;
		boolean lessThanOneMin = false;

		//Loop to remain time less than one minute
		do{
			remainTime = shopCartPg.getRemainingTime();
		} while(remainTime >= oneMinute);

		//Verify
		timerFromUI = shopCartPg.getTimer();
		lessThanOneMin = timerFromUI.matches(timerFormatWithSecOnly) && !timerFromUI.matches(timerFormatWithMinAndSec);

		if(!lessThanOneMin){
			throw new ErrorOnPageException("Timer should only contain seconds, without minute.");	
		}
		logger.info("------Timer contains seconds only.!");
	}

	private void verifyHoldExpiredMes(){
		String timerFromUI = StringUtil.EMPTY;
		int remainTime = -1;
		boolean holdExpired = false;

		//Loop to hold expired
		do{
			remainTime = shopCartPg.getRemainingTime();
		}while(remainTime >1);

		//Verify
		timerFromUI = shopCartPg.getTimer();
		holdExpired = timerFromUI.equals(holdExpiredMes);

		if(!holdExpired){
			throw new ErrorOnPageException("Hold expired message should match:"+holdExpiredMes);	
		}
		logger.info("------Hold expired message matches:"+holdExpiredMes);
	}
}
