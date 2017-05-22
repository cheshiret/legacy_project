package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Enter "Beaver" in Where field;
 * 2. Pick up the results from auto-complete list via keyboard
 * Expected results:
 * It goes to search results page
 * @Preconditions:
 * @SPEC:
 * @Task#: DEFECT-29904 
 * 
 * @author SWang
 * @Date  Oct 21, 2011
 */
public class CheckSearchResultPgExist extends WebTestCase{
	private UwpUnifiedSearch unifiedSearch = new UwpUnifiedSearch();

	public void execute() {
		web.invokeURL(url);
		this.checkSearchResultPgExist();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		unifiedSearch.whereTextValue = "Beaver";
	}

	private void checkSearchResultPgExist(){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		RecgovViewAsListPage parkViewAsListPg = RecgovViewAsListPage.getInstance();
		searchPanel.setWhereByChar(unifiedSearch.whereTextValue);
		searchPanel.waitAutoCompleteBox(UwpUnifiedSearch.HEADING_FACILITIES);
		searchPanel.inputDownKey();
		searchPanel.inputEnterKey();
		searchPanel.clickSearch();

		Object page = browser.waitExists(parkViewAsListPg);
		if(page!=parkViewAsListPg){
			throw new ErrorOnDataException("Search Result page should display after pick up option from auto-complete option via keyboard.");
		}
	}
}
