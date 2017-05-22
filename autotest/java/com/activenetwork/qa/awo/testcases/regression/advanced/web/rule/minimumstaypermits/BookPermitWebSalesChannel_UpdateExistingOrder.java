package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumstaypermits;

import java.util.TimeZone;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Minimum Stay (Permits) rule takes effect on web sales channel during updating an existing order
 * @Preconditions: create a Minimum Stay (Permits) rule instance which Sales Channel = Web, Minimum Stay = 2 day
 * @SPEC: Minimum Stay (Permits) Rule [TC:032206]
 * @Task#: AUTO-1195
 * 
 * @author qchen
 * @Date  Aug 17, 2012
 */
public class BookPermitWebSalesChannel_UpdateExistingOrder extends RecgovTestCase {

	private String ruleName, ruleComment, errorMsg;
	private int minimumStay;
	private boolean isRecgov = true;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		bw.signInRecGov(email, pw);
		
		//precondition: making a permit order
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay);
		bw.makePermitOrderToCart(permit, isRecgov);
		permit.orderID = bw.checkOutCart(pay);
		
		//positive scenario: updating this permit order's exit date to make stay length less than it set in rule will be blocked at permit order details page
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay - 1);
		bw.gotoPermitDetailsFromHome(permit.orderID, isRecgov);
		bw.updatePermitOrderDetails(permit);
		bw.verifyBusinessRuleEffectiveAtChangeOrderDetailsPage(ruleName, errorMsg, permit.entrance);

		//negative scenario: updating this permit order's exit date to make stay length equals with it set in rule will succeed
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay);
		bw.gotoPermitDetailsFromHome(permit.orderID, isRecgov);
		bw.updatePermitOrderDetails(permit);
		bw.verifyBusinessRuleEffectiveAtChangeOrderDetailsPage(ruleName, null, permit.entrance);
		
		//clean up
		bw.cancelPermitOrders(isRecgov, permit.orderID);
		bw.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = bw.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		ruleName = "Minimum Stay (Permits)";
		ruleComment = "MinimumStayPermits_BookPermitWebSalesChannel";
		web.getRuleCondID(schema, ruleComment);
		minimumStay = 1;
		errorMsg = "The minimum stay required is " + (minimumStay + 1) + " day(s).Please change the stay period.";
		
		permit.contractCode = "NRSO";
		permit.parkId = "72600";
		permit.facility = DataBaseFunctions.getFacilityName(permit.parkId, schema);//Boundary Waters Canoe Area Wilderness (Reservations)
		permit.entrance = "48 Meeds Lake (op)";
		permit.permitCategory = "Non Commercial";
		permit.permitType = "Overnight Paddle";
		permit.isRange = false;
		TimeZone timeZone = DataBaseFunctions.getParkTimeZone(schema, permit.facility);
		permit.entryDate = DateFunctions.getDateAfterToday(20, timeZone);
		permit.groupSize = "1";
		permit.personTypes = new String[]{"Adult/Youth"};
		permit.personNums = new String[]{"3"};
		permit.groupMembers.initialParam(1);
	}
}
