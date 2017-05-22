package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.pos.interagencypass;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:Book site to order details page to find the Interagency annual pass DIV
 * and then purchase pass to shopping cart page to check pass quantity, amount and mailing fee amount
 * @Preconditions:
 * @LinkSetUp:
 * d_inv_add_mpos:id=440,450,460,470,480,490,500,510,520,530
 * d_fin_assi_pos:id=120,130,140,150,160,170,180,190,200,210
 * d_fin_tran_fee_sched:id=5600,5610,5620,5630,5640,5650,5660,5670,5680,5690
 * @SPEC:
 * Interagency Pass Set up [TC:108770] 
 * Interagency Annual Pass - Upsell flow [TC:115535] 
 * @Task#:AUTO-2124
 * 
 * @author SWang
 * @Date  Apr 23, 2014
 */
public class AnnualPassUpsellFlow extends RecgovTestCase{
	private UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
	private UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
	private BookingData bd = new BookingData();
    private String passName;
    private String posFeeAmount, posMailingFeeAmount;
    
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		//Make reservation
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		web.fillInOrderDetails(od, bd.contractCode, false);
		
		verifyInteragencyPassInfo(shoppingCart);
		String siteOrderNum = web.checkOutCartToConfirmationPage(pay);
		verifyInteragencyPassInfo(confirmPg);
		
		web.gotoReservationDetailsPgFromConfirmationPg(siteOrderNum);
		web.cancelReservation(pay);
        web.signOut();
	}

	public void wrapParameters(Object[] param) {
		bd.parkId = "70924";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"WAWONA";
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "NRSO";

		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		bd.siteNo = "010";
		bd.siteID = "204309";
		
		od.interagencyAnnualPassQuantity = "3";
		od.isExpeditedDeliveryMethod = true;
		passName = "Interagency Annual Pass -3-pass bundle- Expedited Delivery";
		
		posMailingFeeAmount = String.valueOf(new BigDecimal(feeInfo.getPOSTransFeeFromDB(passName, schema)).setScale(2));
		posFeeAmount = String.valueOf(new BigDecimal(feeInfo.getPOSFeeAmountFromDB(passName, schema)).setScale(2));
	}
	
	private void verifyInteragencyPassInfo(Page page){
		boolean result = MiscFunctions.compareResult("Pass quantity", "1", (page==shoppingCart)?
				shoppingCart.getPOSQuantityByName(passName):
					confirmPg.getPOSQuantityByName(passName));
		
		result &= MiscFunctions.compareResult("Pass Amount", posFeeAmount, (page==shoppingCart)?
				shoppingCart.getPOSAmountByName(passName):
					confirmPg.getPOSAmountByName(passName));
		
		result &= MiscFunctions.compareResult("Pass Mailing Fee", posMailingFeeAmount, (page==shoppingCart)?
				shoppingCart.getPOSReservationFeeByName(passName):
					confirmPg.getPOSReservationFeeByName(passName));
		
		if(!result){
			throw new ErrorOnPageException("Failed to verify all interagency pass information in "+(page==shoppingCart?"shopping cart page.":"confirmationt page"));
		}else logger.info("Successfully verify interagency pass information in "+(page==shoppingCart?"shopping cart page.":"confirmationt page"));
	}
}
