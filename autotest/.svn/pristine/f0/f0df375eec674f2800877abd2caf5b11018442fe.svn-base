/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.bw.findpermits.exitdatesearch;

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
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
  * @Preconditions:
 * 1.the permit type should set both 'On Exit' and 'On Entry' depletion for quota types;
 * 2.the 'Entry Trail' and 'Exit Trail' should have same permit type as what I referred in Precondition 1;
 * 3. If the error message "Selected Exit: JM35 is restricted. Please select another Exit." is shown, please deactivate the related Restrict Entrance rule
 * @SPEC:TC:034105, TC:034114
 * @Task#:AUTO-1184
 * 
 * @Author asun
 * @Date  Aug 17, 2012
 */
public class ExitTrailSearch extends RecgovTestCase {
	RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
	BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
	String entryTral="River Trail AA08";
	@Override
	public void execute() {
		web.invokeURL(url);
		bw.signInBW(email, pw);
		bw.searchPermitInSearchPanel(permit);
		
		entranceList.verifyEnterDateBtnExisting();
		
		permit.exitDate=DateFunctions.getDateAfterToday(30);
		permit.groupSize="1";
		bw.gotoBookPermitPage(permit, false);
		bwBookPage.verifyFirstSectionType("Exit");
		bwBookPage.verifySecondSectionType("Entry");
		bwBookPage.verifyExitDate(permit.exitDate);
		permit.entryDate=permit.exitDate;
		this.bookPermitToOrderDetails();
		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.bw.url");
		email = TestProperty.getProperty("inyonationalforest.coop.email");
		pw = TestProperty.getProperty("inyonationalforest.coop.pwd");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		bd.parkId="72203";
		bd.park=DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.isUnifiedSearch=this.isUnifiedSearch();
		bd.interestInValue="Permits & Wilderness";
		bd.whereTextValue=bd.park;
		bd.contractCode="NRSO";
		
//		permit.entranceCode="Baxter Pass";
		permit.parkId=bd.parkId;
		permit.facility=bd.park;
		permit.isUnifiedSearch=bd.isUnifiedSearch;
		permit.contractCode="NRSO";
		
		permit.entrance="Mt. Whitney (Trailcrest Exit) JM35";
	
		permit.entranceTyp="Exit Trail";
		permit.permitType="Overnight Exiting Mt Whitney (Commercial)";
		permit.isRange=false;
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
	public void bookPermitToOrderDetails() {
		BwPermitOrderDetailsPage oederDetailsPage=BwPermitOrderDetailsPage.getInstance();
		logger.info("book permit to order details page.");
		bwBookPage.findEntryAvailability(entryTral,permit.entryDate);
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();
		oederDetailsPage.waitLoading();
		
	}
	
	


}
