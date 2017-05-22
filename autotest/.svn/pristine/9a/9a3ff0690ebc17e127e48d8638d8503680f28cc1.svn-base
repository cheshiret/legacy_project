/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumgroupsize;

import com.activenetwork.qa.awo.pages.web.bw.BwChangePermitDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the minimum group size rule for the permit type with "Per Unit Per Stay" and person types
 * @Preconditions: 1. Set a minimum group size rule for the permit type "3 Children, 2 Adults" on Desolation Wilderness Permit:
 * Product: Rubicon
 * Sales Channel: Web
 * Permit Type:  3 Children, 2 Adults
 * Person Type - Group Size: Adult - 3, Child - 2
 * 2. make sure the entrance has inventory and fee schedule.
 * @SPEC: Business rules - Minimum Group Size with Person Type [TC:057599]
 * @Task#: Auto-1536
 * 
 * @author Lesley Wang
 * @Date  Mar 15, 2013
 */
public class DiffPersonTypes_PerUnitPerStayAlloMethod extends RecgovTestCase {

	private BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
	private BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
	private String expMsg,  adultMinGroupSize, childMinGroupSize, invalidGroupSize;
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		
		logger.info("1. On Permit Order Detail page, book permit with the group size less than the min group size, and check the error message...");
		bw.makePermitOrderToCart(permit, true);
		bwOrdDetails.verifyTopErrorMes(expMsg);
		
		permit.personNums[0] = adultMinGroupSize;
		permit.personNums[1] = invalidGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		bwOrdDetails.verifyTopErrorMes(expMsg);
	
		logger.info("2. Change group size and book permit correctly...");
		permit.personNums[1] = childMinGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		String resNum = bw.checkOutCart(pay);
		
		logger.info("3. On Update permit page, update the group size less than the min group size, and check the error message...");
		bw.gotoPermitDetailsFromHome(resNum, true);
		permit.personNums[0] = invalidGroupSize;
		bw.updatePermitOrderDetails(permit);
		updatePermitInfoPg.verifyErrorMsg(expMsg);
		
		permit.personNums[0] = adultMinGroupSize;
		permit.personNums[1] = invalidGroupSize;
		bw.updatePermitOrderDetails(permit);
		updatePermitInfoPg.verifyErrorMsg(expMsg);
		
		bw.cancelPermitOrders(true, resNum);
		bw.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		adultMinGroupSize = "3";
		childMinGroupSize = "2";
		invalidGroupSize = String.valueOf(Integer.valueOf(childMinGroupSize) - 1); // 1
		
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		permit.contractCode = "NRSO";
		permit.parkId = "72202"; // Desolation Wilderness Permit
		permit.facility = web.getFacilityName(permit.parkId, schema);
		permit.permitType = "3 Children, 2 Adults";
		permit.entranceCode = "06";
		permit.entranceName = "Rubicon";
		permit.entrance = permit.entranceCode + " " + permit.entranceName;
		permit.isUnifiedSearch = true;
		permit.chooseAnyOfPermitType = false;
		permit.isRange = false;
		permit.setAnyOfGroupSize = false;
		permit.entryDate = DateFunctions.getDateAfterToday(5);
		permit.numOfDays = "2";
		permit.groupSize = invalidGroupSize;
		permit.personTypes = new String[] {"Adult", "Child"};
		permit.personNums = new String[] {invalidGroupSize, childMinGroupSize};
		
		bw.getRuleCondID(schema, "minimumgroupsize.DiffPersonTypes_PerUnitPerStayAlloMethod");
		expMsg = "The minimum group size is " + adultMinGroupSize + " Adult, " + childMinGroupSize + " Child for " +
				permit.entranceCode + " - " + permit.entranceName + ". Please change the group size.";
	}
}
