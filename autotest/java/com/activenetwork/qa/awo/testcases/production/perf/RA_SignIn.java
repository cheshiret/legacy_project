package com.activenetwork.qa.awo.testcases.production.perf;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCurrentResListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * The script is setup for Production testing after Production performance testing work done.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  2012-4-11
 */

public class RA_SignIn extends RATestCase {
	private String url;
	private UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
	private UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();

	public void execute() {
		web.invokeURL(url);

		//Verify Sign In using web customer account.
		web.signIn(email, pw);
		accountOVPg.waitLoading();
		
		//goto Current Reservations page. verify Current Reservations page is displayed.
		accountOVPg.gotoCurrentReservationPg();
		currentResPg.waitLoading();
		currentResPg.verifyReservationsTableExist();
	}

	public void wrapParameters(Object[] param) {
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		url = TestProperty.getProperty(env + ".web.ra.url");
	}
}
