
package com.activenetwork.qa.awo.testcases.production.perf;

import com.activenetwork.qa.awo.pages.web.external.ThirdPartCampPage;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundDirectotyPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundSearchResultsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsByStatePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
public class RA_CampgroundDirectoryELS extends WebTestCase {
	private String url, stateName, linkName;
	private UwpHomePage hmPg = UwpHomePage.getInstance();
	private UwpCampgroundDirectotyPage cdPg = UwpCampgroundDirectotyPage.getInstance();
	private UwpCampgroundsByStatePage statePg = UwpCampgroundsByStatePage.getInstance();
	private UwpCampgroundSearchResultsPage seaResPg = UwpCampgroundSearchResultsPage.getInstance();
	private RAParkDetailsPage cpDetailsPg = RAParkDetailsPage.getInstance();
	private ThirdPartCampPage thirdCpPg = ThirdPartCampPage.getInstance();

	public void execute() {
		web.invokeURL(url);

		hmPg.waitLoading();
		hmPg.gotoCampgroundDirectory();
		cdPg.waitLoading();

		this.campgDirectoryVerification();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		linkName = "Encore/ELS";   //On the Campground Directory page, click the Encore/ELS link (not Encore/ELS Reservations).
	}

	private void campgDirectoryVerification() {
		logger.info("Verify link of agency '" + linkName + "'");		
		cdPg.clickOnAgencyLink(linkName);
		statePg.waitLoading();
		
		logger.info("Verify agency name displayed on the State page.");
		this.verifyInStateListPg(linkName); // get agency name displayed in page
		stateName = statePg.clickOnStateLink(); // get the state link name
		
		seaResPg.waitLoading();
		//verify page title and make sure there is course displayed on the search result page.
		logger.info("Verify agency name displayed on the page.");
		this.verifyInSearchResultsPg(linkName, stateName);
		seaResPg.clickOnFirstSeeDetails();
		cpDetailsPg.waitLoading();
		
		//click the "Make Reservations at Encore/els" hyperlink.
		String parkName = cpDetailsPg.getCampGroundNameAndRelatedStateCode();
		logger.info("the park name get on RA website is:" + parkName);
		cpDetailsPg.clickMakeResAtAgencyLink();
		//PS:there will be a second browser opened after we click the agency link, need to debug later time.
		thirdCpPg.waitLoading();
		//verify the park name match with the name displayed on third part website.
		thirdCpPg.verifyParkName(parkName.split(",")[0] + " Resort" );  //park name may change in the future. need to double check before running the test.
		
	}

	// verify the page title is the same as the selected link or with state name in the end;
	// and verify whether the Search Results display
	private void verifyInSearchResultsPg(String agencyName, String stateName) {
		String tmpPageTitle = seaResPg.getPageTitle();
		
		if (!tmpPageTitle.equals(agencyName + " - " + stateName)) {
			throw new ItemNotFoundException("Expect page title is:" +agencyName + " - " + stateName + "; but current value is:" + tmpPageTitle );
		}

		if (seaResPg.getParkNmuInFristResultPg() < 1) {
			throw new ItemNotFoundException("No park available, could not continue, please choose another state or manually verify the left checkpoints");
		}
	}

	// Verify the page title is the same as the selected link
	private void verifyInStateListPg(String linkName) {
		String pageTitle = statePg.getPageTitle();

		if (!pageTitle.equals(linkName)) {
			logger.error("expect  agency name is:" + linkName);
			logger.error("current agency name is:" + pageTitle);
			throw new ItemNotFoundException("Page with error for agency name.");
		}

	}




}
