package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.headertabswitch;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: validate error message
 * 2: verify after search it goes to search result page.
 * 3: verify in search form on home page, the previous sample data and criteria entered in step1 are retained.
 * @Preconditions:
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 */
public class SecondSearchOnResult_01 extends RecgovTestCase{
	private String errorMsg = "";
	private BookingData bd1 = new BookingData();
	public void execute(){
		web.invokeURL(url);
		web.makeUnifiedSearch(bd);
		this.verifySearchCriteriaSwitchBetweenPages();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		errorMsg = "Where did you want to go? Enter at least 4 letters and you'll start seeing facilities and addresses that match your input.";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		String threshold = WebConfiguration.getInstance().getUIOption(Conf.rec_UIOptions, UIOptions.Threshold).split(",")[1];
		bd.whereTextValue = "**68".substring(0, Integer.valueOf(threshold)-1);
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//second search data info which used to setup search criteria with search.
		bd1.isUnifiedSearch = bd.isUnifiedSearch;
		bd1.whereTextValue = "BIRCH LAKE CAMPGROUND";
		bd1.parkId = "73749";
		bd1.contractCode = "NRSO";
		bd1.interestInValue = "Camping & Lodging";
		bd1.lookFor = "RV sites";
		bd1.length = "30";
		bd1.moreOptions = true;
		bd1.electricHookupValue = "Not Required";
		bd1.flexibleValue = "Not Flexible";
		bd1.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd1.lengthOfStay = "2";
		bd1.accessibilityNeeds = true;
		bd1.petsAllowed = true;
	}
	
	public void verifySearchCriteriaSwitchBetweenPages(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchHelpInfoPage helpPage=UwpUnifiedSearchHelpInfoPage.getInstance();
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		UwpSignInSignUpPage signInOrSignUpPg = UwpSignInSignUpPage.getInstance(); //Sara[12/3/2013] Update because of new sign in and sign up mode in rec.gov website
		
		//1: verify warning message on help page.
		helpPage.verifyErrorMes(errorMsg);
		
		//2.1: setup search criteria on help page click search button.
		this.gotoViewAsListPage(bd1);
		listPg.verifyFirstParkName(bd1.whereTextValue.toUpperCase());
		//2.2: switch to "my account" page via header TAB		
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();		
		signInOrSignUpPg.waitLoading();
		//2.3: goto home page via header TAB
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd1, true);
	}
}
