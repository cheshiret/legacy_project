package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * Check point to check \ufffdNo matching result\ufffd message in \ufffdView As List\ufffd page when select \ufffdInterest in\ufffd as \ufffdOther Activities\ufffd,  \ufffdDay use & Picnic areas\ufffd and \ufffdCamping & Lodging\ufffd.
 * @author SWang
 * @Date  May 31, 2012
 *
 */
public class NoMatchingResultMesValidation extends WebTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String warningMes;

	public void execute() {
		web.invokeURL(url);
		web.makeUnifiedSearch(searchData);
		viewAsListPg.verifyWarningMes(warningMes);

		searchData.interestInValue = "Day use & Picnic areas";
		searchData.flexibleValue = "Flexible for next 2 weeks";
		searchData.arrivalDate = DateFunctions.getDateAfterToday(0);
		searchData.lengthOfStay = "2";
		web.invokeURL(url);
		web.makeUnifiedSearch(searchData);
		viewAsListPg.verifyWarningMes(warningMes);

		searchData.interestInValue = "Camping & Lodging";
		searchData.lookFor = "Cabins";
		web.invokeURL(url);
		web.makeUnifiedSearch(searchData);
		viewAsListPg.verifyWarningMes(warningMes);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		searchData.whereTextValue = "north pole";
		searchData.selectAutoCompleteOption = true;
		searchData.selectedAutoCompletedOption = "NORTH POLE, AK 99705, USA";


		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		searchData.otherActivitiesName = new String[]{"Auto Touring","Biking", "Boating"};
		warningMes= "There are no matching results based on the information you have provided. Please modify your search and try again.";
	}
}
