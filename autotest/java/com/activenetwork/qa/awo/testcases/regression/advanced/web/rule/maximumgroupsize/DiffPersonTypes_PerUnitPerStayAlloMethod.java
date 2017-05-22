/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumgroupsize;

import com.activenetwork.qa.awo.pages.web.bw.BwChangePermitDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the maximum group size rule for the permit type with "Per Unit Per Stay" and person types
 * @Preconditions: 1. Set a maximum group size rule for the permit type "3 Children, 2 Adults" on Desolation Wilderness Permit:
 * Product: Rubicon
 * Sales Channel: Web
 * Permit Type:  3 Children, 2 Adults
 * Person Type - Group Size: Adult - 5, Child - 4
 * 2. make sure the entrance has inventory and fee schedule.
 * @SPEC:  Business rules - Maximum Group Size with Person Type [TC:057603]
 * @Task#: Auto-1536
 * 
 * @author Lesley Wang
 * @Date  Mar 15, 2013
 */
public class DiffPersonTypes_PerUnitPerStayAlloMethod extends RecgovTestCase {

	private BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
	private BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
	private String expMsg,  adultMaxGroupSize, childMaxGroupSize, invalidGroupSize;
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		
		logger.info("1. On Permit Order Detail page, book permit with the group size larger than the max group size, and check the error message...");
		bw.makePermitOrderToCart(permit, true);
		bwOrdDetails.verifyTopErrorMes(expMsg);
		
		permit.personNums[0] = adultMaxGroupSize;
		permit.personNums[1] = invalidGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		bwOrdDetails.verifyTopErrorMes(expMsg);
	
		logger.info("2. Change group size and book permit correctly...");
		permit.personNums[1] = childMaxGroupSize;
		bw.gotoPermitShoppingCartFromOrderDetailPage(permit);
		String resNum = bw.checkOutCart(pay);
		
		logger.info("3. On Update permit page, update the group size larger than the max group size, and check the error message...");
		bw.gotoPermitDetailsFromHome(resNum, true);
		permit.personNums[0] = invalidGroupSize;
		bw.updatePermitOrderDetails(permit);
		updatePermitInfoPg.verifyErrorMsg(expMsg);
		
		permit.personNums[0] = adultMaxGroupSize;
		permit.personNums[1] = invalidGroupSize;
		bw.updatePermitOrderDetails(permit);
		updatePermitInfoPg.verifyErrorMsg(expMsg);
		
		bw.cancelPermitOrders(true, resNum);
		bw.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		adultMaxGroupSize = "5";
		childMaxGroupSize = "4";
		invalidGroupSize = String.valueOf(Integer.valueOf(adultMaxGroupSize) + 1); // 6
		expMsg = "The maximum group size is " + adultMaxGroupSize + " Adult, " + childMaxGroupSize + " Child. Please change the group size.";
		
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		permit.contractCode = "NRSO";
		permit.parkId = "72202"; // Desolation Wilderness Permit
		permit.facility = web.getFacilityName(permit.parkId, schema);
		permit.permitType = "3 Children, 2 Adults";
		permit.entrance = "06 Rubicon";
		permit.isUnifiedSearch = true;
		permit.chooseAnyOfPermitType = false;
		permit.isRange = false;
		permit.setAnyOfGroupSize = false;
		permit.entryDate = DateFunctions.getDateAfterToday(2);
		permit.numOfDays = "2";
		permit.groupSize = invalidGroupSize;
		permit.personTypes = new String[] {"Adult", "Child"};
		permit.personNums = new String[] {invalidGroupSize, childMaxGroupSize};
		
		bw.getRuleCondID(schema, "maximumgroupsize.DiffPersonTypes_PerUnitPerStayAlloMethod");
	}

}
