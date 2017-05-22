package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.pos.interagencypass;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.InteragencyPassAttr;
import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Click the Marketing Spot on the bottom of the left side of facility details page to Interagency annual pass landing page
 * to purchase pass to shopping cart page to check pass quantity, amount and mailing fee amount
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
public class AnnualPassSale extends RecgovTestCase{
	private UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
	private UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
	private BookingData bd = new BookingData();
    private Data<InteragencyPassAttr> interagencyPass = new Data<InteragencyPassAttr>();
    private String posFeeAmount, posMailingFeeAmount;
    
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.gotoViewAsListPage(bd);
		web.gotoInteragencyPassDetailsPgFromViewAsListPg(bd.contractCode, bd.parkId);
		web.purchaseInteragencyPassToShoppingCartFromDetailsPg(interagencyPass, email, pw);
		
		verifyInteragencyPassInfo(shoppingCart);
		web.checkOutCartToConfirmationPage(pay);
		verifyInteragencyPassInfo(confirmPg);
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		bd.parkId = "70924";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"WAWONA";
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		bd.contractCode = "NRSO";

		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		interagencyPass.put(InteragencyPassAttr.quantity, "2");
		interagencyPass.put(InteragencyPassAttr.isExpeditedDeliveryMethod, false);
		interagencyPass.put(InteragencyPassAttr.interagencyPassName, "Interagency Annual Pass - 2-pass bundle -Standard Delivery");
		
		posMailingFeeAmount = String.valueOf(new BigDecimal(feeInfo.getPOSTransFeeFromDB(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName), schema)).setScale(2));
		posFeeAmount = String.valueOf(new BigDecimal(feeInfo.getPOSFeeAmountFromDB(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName), schema)).setScale(2));
	}
	
	private void verifyInteragencyPassInfo(Page page){
		boolean result = MiscFunctions.compareResult("Pass quantity", "1", (page==shoppingCart)?
				shoppingCart.getPOSQuantityByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)):
					confirmPg.getPOSQuantityByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)));
		
		result &= MiscFunctions.compareResult("Pass Amount", posFeeAmount, (page==shoppingCart)?
				shoppingCart.getPOSAmountByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)):
					confirmPg.getPOSAmountByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)));
		
		result &= MiscFunctions.compareResult("Pass Mailing Fee", posMailingFeeAmount, (page==shoppingCart)?
				shoppingCart.getPOSReservationFeeByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)):
					confirmPg.getPOSReservationFeeByName(interagencyPass.stringValue(InteragencyPassAttr.interagencyPassName)));
		
		if(!result){
			throw new ErrorOnPageException("Failed to verify all interagency pass information in "+(page==shoppingCart?"shopping cart page.":"confirmationt page"));
		}else logger.info("Successfully verify interagency pass information in "+(page==shoppingCart?"shopping cart page.":"confirmationt page"));
	}
}
