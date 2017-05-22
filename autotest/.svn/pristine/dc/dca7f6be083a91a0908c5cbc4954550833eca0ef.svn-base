package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**@Preconditions:
 * 1: POS product: Annual Camping Pass In-State/Annual Camping Pass Out-State/Annual Day Use Pass exist in NM contract
 * 
 * @Description:
 * 1. Make reservation.
 * 2. On order detail page, check information of Pass Offer.
 * 3. Go to shopping cart page, check POS amount and fee.
 * 4. Go to confirmation page, check POS amount and fee.
 * 5. clear reservation.
 * @SPEC:TC034891: NM pass offer custom HTML on NM PLW
 * 		UWP POSSalesNM_DisplayPassOffer_UI	 
 * @Task#:AUTO-1185
 * 
 * @author pzhu
 * @Date  Oct 08, 2012 
 * 
 */
public class POSSalesDisplayPassOffer_NM extends WebTestCase
{

	private String passOfferAmount;
	private String passOfferFee;
	private String passOfferTitle;
	private String[] content = new String[]
	        {"You can now purchase the New Mexico State Parks Passes online!",
			 "Select an Annual Day Use Pass and/or an Annual Camping Pass.",
			 "Annual Day Use Pass",
			 "Good for 12 months from month of purchase: all parks except Living Desert Zoo & Gardens",
			 "Annual Camping Pass",
			 "In State",
			 "Out of State",
			 "Good for 12 months from month of purchase",
			 "The following types of passes can only be purchased at the park: Replacement Pass; Senior Pass; Disabled Resident",
			 "Use the discount now!",
			 "To take advantage of the discount for this reservation, enter in the following code: NMPASS by selecting the corresponding pass in the Primary Occupant Profile section above."
			 };
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		web.checkPassOfferInfo(this.passOfferTitle, this.content);// check pass offer info.
		
		web.checkPOSInOrderDetailsPg(od);//fill in POS info
		web.fillInOrderDetails(od,bd.contractCode);// go to cart
		web.getAndVerifyPOSAmount(od.passOfferName, this.passOfferAmount);//verify pos amount in shopping cart
		web.getAndVerifyPOSFee(od.passOfferName, this.passOfferFee);//verify pos fee in shopping cart

		
		web.checkOutShoppingCart(pay);
		web.getAndVerifyPOSAmount(od.passOfferName, this.passOfferAmount);//verify pos amount in confirmation
		web.getAndVerifyPOSFee(od.passOfferName, this.passOfferFee);//verify pos fee in confirmation
		
		web.gotoResDetailsFromConfirm(1); // clean up
		web.cancelReservation(pay);
		web.signOut();
	}
	
	public String getAmountFromDB(String posName)
	{
		db.resetSchema(schema);
		logger.debug("Get Fee Amount of "+posName+" from DB table .");
		
		String query = "SELECT OTHERFEE.FEE_AMT " +
				"FROM P_FEE_SCHD SCHD,      " +
				"P_FEE_COND FEECOND,      " +
				"P_FEE FEE,      " +
				"P_CONDITION COND,      " +
				"P_OTHER_FEE_SCHD OTHERFEE    " +
				"WHERE SCHD.DELETE_IND = '0'   " +
				"AND SCHD.FEE_COND_ID  = FEECOND.ID    " +
				"AND FEECOND.FEE_ID    = FEE.FEE_ID    " +
				"AND FEECOND.COND_ID   = COND.ID   " +
				"AND OTHERFEE.FEE_SCHD_ID = SCHD.ID    " +
				"AND FEE.PRD_ID       IN      (SELECT PRD_ID      FROM P_PRD      WHERE UPPER(PRD_NAME) LIKE '%"+posName+"%'      )    " +
				"AND SCHD.SCHD_TYPE          <> 5    " +
				"AND SCHD.PRD_GRP_CAT_ID NOT IN (10,11,15)    " +
				"AND COND.SALES_CHANL_ID IN ( 2)    " +//Web
				"AND FEE.FEE_TYPE_ID IN ( 13 )    " +//POS Fee
				"ORDER BY SCHD.ID DESC NULLS LAST";	
		logger.info("Query: "+query);
		
		return db.executeQuery(query,"FEE_AMT").get(0);
		
	}
	
	public String getFeeFromDB(String posName)
	{
		db.resetSchema(schema);
		logger.debug("Get Fee Amount of "+posName+" from DB table .");
		
		String query = "SELECT OTHERFEE.FEE_AMT " +
				"FROM P_FEE_SCHD SCHD,      " +
				"P_FEE_COND FEECOND,      " +
				"P_FEE FEE,      " +
				"P_CONDITION COND,      " +
				"P_OTHER_FEE_SCHD OTHERFEE    " +
				"WHERE SCHD.DELETE_IND = '0'   " +
				"AND SCHD.FEE_COND_ID  = FEECOND.ID    " +
				"AND FEECOND.FEE_ID    = FEE.FEE_ID    " +
				"AND FEECOND.COND_ID   = COND.ID   " +
				"AND OTHERFEE.FEE_SCHD_ID = SCHD.ID    " +
				"AND FEE.PRD_ID       IN      (SELECT PRD_ID      FROM P_PRD      WHERE UPPER(PRD_NAME) LIKE '%"+posName+"%'      )    " +
				"AND SCHD.SCHD_TYPE          <> 5    " +
				"AND SCHD.TRAN_TYPE_ID        =8001  " +//Purchase POS
				"AND SCHD.PRD_GRP_CAT_ID NOT IN (10,11,15)    " +
				"AND FEE.FEE_TYPE_ID IN ( 4 )    " +//Transaction Fee
				"ORDER BY SCHD.ID DESC NULLS LAST";	
		logger.info("Query: "+query);
		
		return db.executeQuery(query,"FEE_AMT").get(0);
		
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.nm.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NM";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New Mexico";
		bd.park = "Bluewater Lake";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "2";
		bd.contractCode = "NM";

		bd.siteNo = "28";
		bd.siteID = "1029";
		
		

		od.passOfferName = "Annual Camping Pass"; 
		od.passOfferSubName = "In State";
		this.passOfferAmount = this.getAmountFromDB("Annual Camping Pass In-State".toUpperCase());
		this.passOfferFee = this.getFeeFromDB("Annual Camping Pass In-State".toUpperCase());
		this.passOfferTitle = "New Mexico State Parks Pass Offer";
	}
}
