package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumstaypermits;

import java.util.TimeZone;

import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (DEFECT-42268->P) verify the Minimum Stay (Permits) rule takes effect on permit type with 'Per Unit/Per Stay' allocation method
 * @Preconditions: Create a Minimum STay (Permits) rule instance which Product=Rubicon, Permit Type=3 Children, 2 Adults, Minimum Stay=2 day
 * 
 * @SPEC: Business rules - Minimum Stay [TC:057578] 
 * @Task#: AUTO-1537
 * 
 * @author SWang
 * @Date  Mar 21, 2013
 */
public class BookPermit_ExitDate_Mandatory extends RecgovTestCase {
	private BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
	private String errorMsg;
	private BWCooperator bw = BWCooperator.getInstance();
	private TimeZone timeZone = null;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		//Positively, making a permit which the stay length is 1 day, less than minimum stay 2 days, will be blocked at permit order details page
		bw.makePermitToOrderDetailsPage(permit, true);
		bwBookPage.verifyErrorMessage(errorMsg);

		//Negatively, making a permit which the stay length equals minimum stay will succeed
		permit.numOfDays = "2";
		bw.makePermitOrderToCart(permit, true);
		permit.orderID = bw.checkOutCartToConfirmationPg(pay);

		//Cancel permit order
		bw.gotoPermitReservationDetailsFromConfirm(permit.orderID);
		bw.cancelPermitOrderFromDetails();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		permit.isUnifiedSearch = this.isUnifiedSearch();

		//Permit info
		permit.parkId = "72202";
		permit.contractCode = "NRSO";
		permit.facility = DataBaseFunctions.getFacilityName(permit.parkId, schema);//"Desolation";
		permit.permitType = "3 Children, 2 Adults";
		permit.entrance = "06 Rubicon";
		permit.isRange = false;
		timeZone = TimeZone.getTimeZone(permit.parkId);
		permit.entryDate = DateFunctions.getDateAfterToday(5, timeZone);
		permit.numOfDays= "1";
		permit.groupSize = "3";
		permit.personTypes = new String[]{"Adult", "Child"};
		permit.personNums = new String[]{permit.groupSize, "2"};

		bw.getRuleCondID(schema, "web.minimumstay.MakePermitMandatoryExitDate");
		errorMsg = "The minimum stay required is 2 day(s).Please change the stay period.";
	}
}

