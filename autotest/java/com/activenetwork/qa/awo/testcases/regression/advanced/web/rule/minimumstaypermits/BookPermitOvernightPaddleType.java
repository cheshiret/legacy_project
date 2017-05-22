package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumstaypermits;

import java.util.TimeZone;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Minimum Stay (Permits) rule takes effect to Permit Type
 * @Preconditions: Create a Minimum Stay (Permits) rule instance which Permit Type=Overnight Paddle, Minimum Stay = 2 day
 * @SPEC: Minimum Stay (Permits) Rule [TC:032206]
 * @Task#: AUTO-1195
 * 
 * @author qchen
 * @Date  Aug 17, 2012
 */
public class BookPermitOvernightPaddleType extends RecgovTestCase {
	
	private String ruleName, ruleComment, errorMsg;
	private int minimumStay;
	private boolean isRecgov = true;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		bw.signInRecGov(email, pw);
		//positive scenario: making a permit order with 'Overnight Paddle' permit type and stay length less than it set in rule will be blocked at permit order details page
		permit.permitType = "Overnight Paddle";
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay - 1);
		bw.makePermitOrderToCart(permit, isRecgov);
		bw.verifyBusinessRuleEffectiveAtOrderDetailsPage(ruleName, errorMsg, permit.entrance);
		bw.removeResFromOrderDetailsPg();
		
		//negative scenario#1: making a permit with the same type but stay length equals with it set in rule will succeed
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay);
		bw.makePermitOrderToCart(permit, isRecgov);
		bw.verifyBusinessRuleEffectiveAtOrderDetailsPage(ruleName, null, permit.entrance);
		
		//negative scenario#2: making a permit with the permit type other than 'Overnight Paddle' and stay length less than it set in rule will also succeed
		permit.permitType = "Overnight Motor";
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.entryDate, minimumStay - 1);
		bw.makePermitOrderToCart(permit, isRecgov);
		bw.verifyBusinessRuleEffectiveAtOrderDetailsPage(ruleName, null, permit.entrance);
		
		//clean up
		bw.gotoShoppingCartPg();
		bw.abandonCart();
		bw.signOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = bw.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		ruleName = "Minimum Stay (Permits)";
		ruleComment = "MinimumStayPermits_BookPermitOvernightPaddleType";
		web.getRuleCondID(schema, ruleComment);
		minimumStay = 1;
		errorMsg = "The minimum stay required is " + (minimumStay + 1) + " day(s).Please change the stay period.";
		
		permit.contractCode = "NRSO";
		permit.parkId = "72600";
		permit.facility = DataBaseFunctions.getFacilityName(permit.parkId, schema);//Boundary Waters Canoe Area Wilderness (Reservations)
		permit.entrance = "54 Seagull Lake (op,om)";
		permit.permitCategory = "Non Commercial";
		//permit.permitType = "Overnight Paddle";
		permit.isRange = false;
		TimeZone timeZone = DataBaseFunctions.getParkTimeZone(schema, permit.facility);
		permit.entryDate = DateFunctions.getDateAfterToday(15, timeZone);
		permit.groupSize = "1";
		permit.personTypes = new String[]{"Adult"};
		permit.personNums = new String[]{"3"};
		permit.groupMembers.initialParam(1);
	}
}
