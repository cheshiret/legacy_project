/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.findpermits.exitdatesearch;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * 1.the permit type should set both 'On Exit' and 'On Entry' depletion for quota types;
 * 2.the 'Entry Trail' and 'Exit Trail' should have same permit type as what I referred in Precondition 1;
 * @SPEC:TC:034771
 * @Task#:AUTO-1184
 * 
 * @Author asun
 * @Date  Aug 17, 2012
 */
public class EntryDateLaterThanExitDate_ExitSearch extends RecgovTestCase {
	RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
	BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
	String entryTral="Laurel Lakes JM03";
	String msg="The exit date cannot be a date prior to the entry date.";
	@Override
	public void execute() {
		web.invokeURL(url);	
		web.signIn(email, pw);
		this.gotoPermitAvailibalityPageFromHomePg(permit);
		permit.entryDate=DateFunctions.getDateAfterGivenDay(permit.exitDate, 1);
		this.verifyErrorMessage();
		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url=TestProperty.getProperty(env+WEB_URL_RECGOV);
		
		email=web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		permit.parkId="72203";
		permit.facility=DataBaseFunctions.getFacilityName(permit.parkId, schema);
		permit.isUnifiedSearch=this.isUnifiedSearch();
		permit.contractCode="NRSO";
		
		permit.entrance="Mt. Whitney (Trailcrest Exit) JM35";
	
		permit.entranceTyp="Exit Trail";
		permit.permitType="Overnight Exiting Mt Whitney";
		permit.isRange=false;
		permit.exitDate=DateFunctions.getDateAfterToday(30);
		permit.groupSize="1";
	}

	/**
	 * Go to permit availability page from Home Page.
	 * 
	 * @param permit
	 */
	public void gotoPermitAvailibalityPageFromHomePg(PermitInfo permit) {
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel
				.getInstance();
		RecgovViewAsListPage parkViewAsListPage = RecgovViewAsListPage
				.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage = UwpUnifiedSearchSuggestionPage
				.getInstance();

		logger.info("Go to permit availability page from Home Page.");

		uwpHead.clickHomeLink();

		browser.waitExists(unifiedSearchPanel);

		unifiedSearchPanel.setupPermitSearchCriteria(permit);
		unifiedSearchPanel.clickSearch();
		Object page = browser.waitExists(suggestionPage, parkViewAsListPage);
		if (page == suggestionPage) {
			suggestionPage.clickFacilitySuggestion(permit.parkId,
					permit.contractCode);
			parkViewAsListPage.waitLoading();
		}
		parkViewAsListPage.clickParkName(permit.facility);

		bwSearchPanel.waitLoading();
		bwSearchPanel.searchPermit(permit);
		bwBookPage.waitLoading();

		if (bwBookPage.isLotterySelected()) {
			throw new ItemNotFoundException("The specified date "
					+ permit.entryDate + " only have lottery");
		}
	}
	/**
	 * 
	 */
	public void verifyErrorMessage() {
		BwPermitOrderDetailsPage oederDetailsPage=BwPermitOrderDetailsPage.getInstance();
		logger.info("book permit to order details page.");
		bwBookPage.findEntryAvailability(entryTral,permit.entryDate);
		bwBookPage.clickBookNow();
		bwBookPage.waitForProgressBarDisapear();
		Object page=browser.waitExists(oederDetailsPage,this.bwBookPage);
		
		if(page!=this.bwBookPage){
			throw new ActionFailedException("work flow should stay in book permit page");
		}
		bwBookPage.verifyErrorMessage(msg);
	}
	
	


}
