package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * The only agency filter option will be displayed in plain text, no shade, no count number, not clickable, no 'x' next to filter title (Agency).
 * @Preconditions:
 * @SPEC: Agency Filter - UI [TC:043128] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 28, 2012
 */
public class OnlyAgencyFilterOptionUI extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String onlyAgencyOptionContentExpected;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyOnlyAgencyFilterName(onlyAgencyOptionContentExpected);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Boise National Forest";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		onlyAgencyOptionContentExpected = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USFORESTSERVICE_ID, schema);
	}
}
