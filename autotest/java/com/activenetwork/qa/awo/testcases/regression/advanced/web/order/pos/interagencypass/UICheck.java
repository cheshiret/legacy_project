package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.pos.interagencypass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovInteragencyPassDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Go to Interagency annual pass landing page or Order details page to check Interagency pass's price, quantity and delivery method 
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
public class UICheck extends RecgovTestCase{
	private RecgovInteragencyPassDetailsPage interagencyPassDetailsPg = RecgovInteragencyPassDetailsPage.getInstance();
	private UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
	private BookingData bd = new BookingData();
	private List<String> quantity;
	private String passName, price;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		web.invokeURL(url);
		web.signIn(email, pw);

		//Verify interagency pass info during pass sale
		web.gotoViewAsListPage(bd);
		web.gotoInteragencyPassDetailsPgFromViewAsListPg(bd.contractCode, bd.parkId);
		verifyInteragencyPassInfo(interagencyPassDetailsPg);

		//Verify interagency pass info during upsell flow
		web.gotoHomePage();
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		verifyInteragencyPassInfo(orderDetails);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		bd.parkId = "70924";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"WAWONA";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		bd.arrivalDate = DateFunctions.getDateAfterToday(6);
		bd.lengthOfStay = "2";
		bd.contractCode = "NRSO";
		bd.siteNo = "010";
		bd.siteID = "204309";

		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		passName = "Interagency Annual Pass - Standard Delivery";

		quantity = new ArrayList<String>();
		quantity.addAll(Arrays.asList(new String[]{"1", "2", "3", "4", "5"}));
		price = String.valueOf(new BigDecimal(feeInfo.getPOSFeeAmountFromDB(passName, schema)).setScale(2));
	}

	private void verifyInteragencyPassInfo(Page page){
		if(page == orderDetails){
			orderDetails.selectBuyInteragencyPassCheckBox();
		}
		
		boolean result = MiscFunctions.compareResult("Quantity default value", quantity.get(0), (page==interagencyPassDetailsPg)?
				interagencyPassDetailsPg.getInteragencyQuantity():orderDetails.getInteragencyQuantity());
		
		if(page!=interagencyPassDetailsPg){
			quantity.add(0, StringUtil.EMPTY);
		}
		result &= MiscFunctions.compareResult("Quantities", quantity.toString(), (page==interagencyPassDetailsPg)?
				interagencyPassDetailsPg.getInteragencyQuantities().toString():orderDetails.getInteragencyQuantities().toString());
		
		result &= MiscFunctions.compareResult("Price", price, (page==interagencyPassDetailsPg)?
				interagencyPassDetailsPg.getPrice():orderDetails.getPrice());

		if(page==interagencyPassDetailsPg){
			interagencyPassDetailsPg.selectStandardDeliveryMethod();
		}else orderDetails.selectExpeditedDeliveryMethod();

		if(!result){
			throw new ErrorOnPageException("Failed to verify all interagency pass info during "+(page==interagencyPassDetailsPg?"pass sale":"upSell sale"));
		}else logger.info("Successfully verify all interagency pass info during "+(page==interagencyPassDetailsPg?"pass sale":"upSell sale"));
	}
}
