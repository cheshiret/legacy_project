/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumgroupsize;

import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.pages.web.bw.BwChangePermitDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the minimum group size rule for the permit type with "Per Unit Per Stay" and All person type
 * @Preconditions: 1. Set a minimum group size rule for the permit type "3 Children, 1 Adult" on Desolation Wilderness Permit:
 * Product: Stony Ridge
 * Sales Channel: Web
 * Permit Type:  3 Children, 1 Adult
 * Person Type: All
 *  # Group Size: 2
 * 2. make sure the entrance has inventory and fee schedule.
 * @SPEC:  Business rules - Minimum Group Size [TC:057597]
 * @Task#: Auto-1536
 * 
 * @author Lesley Wang
 * @Date  Mar 15, 2013
 */
public class AllPersonType_PerUnitPerStayAlloMethod extends RecgovTestCase {

	private BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
	private BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
	private BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
	private String expMsg,  minGroupSize, invalidGroupSize;
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		
		logger.info("1. On Entranct Detail page, book permit with the group size less than the min group size, and check the error message...");
		bw.gotoBookPermitPage(permit, true);
		bw.bookPermitOnEntranceDetailsPage(permit);
		bwBookPage.verifyErrorMessage(expMsg);
		
		logger.info("2. On Permit Order Detail page, book permit with the group size less than the min group size, and check the error message...");
		permit.groupSize = minGroupSize;
		bw.bookPermitOnEntranceDetailsPage(permit);
		permit.groupSize = invalidGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		bwOrdDetails.verifyTopErrorMes(expMsg);
		
		logger.info("3. Change group size and book permit correctly...");
		permit.groupSize = minGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		String resNum = bw.checkOutCart(pay);
		
		logger.info("4. On Update permit page, update the group size less than the min group size, and check the error message...");
		bw.gotoPermitDetailsFromHome(resNum, true);
		permit.groupSize = invalidGroupSize;
		bw.updatePermitOrderDetails(permit);
		updatePermitInfoPg.verifyErrorMsg(expMsg);
		
		bw.cancelPermitOrders(true, resNum);
		bw.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		minGroupSize = "2";
		invalidGroupSize = String.valueOf(Integer.valueOf(minGroupSize) - 1); // 1
		
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		permit.contractCode = "NRSO";
		permit.parkId = "72202"; // Desolation Wilderness Permit
		permit.facility = web.getFacilityName(permit.parkId, schema);
		permit.permitType = "3 Children, 1 Adult";
		permit.entranceCode = "07";
		permit.entranceName = "Stony Ridge";
		permit.entrance = permit.entranceCode + " " + permit.entranceName;
		permit.isUnifiedSearch = true;
		permit.chooseAnyOfPermitType = false;
		permit.isRange = false;
		permit.setAnyOfGroupSize = false;
		permit.entryDate = DateFunctions.getDateAfterToday(5);
		permit.numOfDays = "2";
		permit.groupSize = invalidGroupSize;
		
		bw.getRuleCondID(schema, "minimumgroupsize.AllPersonTypes_PerUnitPerStayAlloMethod");
		expMsg = "The minimum group size is " + minGroupSize + " for " + permit.entranceCode + " - " + permit.entranceName + ". Please change the group size.";
	}

}
