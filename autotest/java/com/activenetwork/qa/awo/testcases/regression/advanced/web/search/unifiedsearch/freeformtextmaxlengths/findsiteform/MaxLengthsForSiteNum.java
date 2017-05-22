package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.freeformtextmaxlengths.findsiteform;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 
 * Verify there are just the top 100 characters in the "Site #" field from "Find sites panel"
 * @Preconditions:
 * @SPEC:
 * UWP Unified Search_FacilitySearch_UC (Story M: max lengths for all free-form text)
 * UWP Unified Search_Search Form_UI (Story M: max lengths for all free-form text)
 * @Task#: AUTO-858
 * 
 * @author SWang
 * @Date  Dec 27, 2011
 */
public class MaxLengthsForSiteNum extends RecgovTestCase{
	private UwpUnifiedFindProductCommonPanel findSitesPanel = UwpUnifiedFindProductCommonPanel.getInstance();
	private String[] expectedSiteNumValues;

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);

		findSitesPanel.verifySiteNumInfo("");
		this.verifySiteNumText();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();

		bd.whereTextValue = "COCHITI AREA";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		bd.contractCode = "NRSO";
		bd.parkId = "73127";

		expectedSiteNumValues = new String[]{
				"1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789T", //101
		"A2b4c6d7e90~!@##%%&&()_+=-`||{}[];:'\"?/><,.acb1 Ac6d7e9.9..~!@#$%^&*()_+=-`||:;\"||,?[]<<>>.zhongguo!T A2b4c6d7e90"};//More than 101
	}

	private void verifySiteNumText(){
		logger.info("Verify validate 'Site #' field value.");
		String siteNum = "";

		for(String sampleData: expectedSiteNumValues){
			findSitesPanel.setSiteNumInfo(sampleData);
			siteNum = findSitesPanel.getSiteNumInfo();	

			logger.info("Sample data is: "+sampleData);
			sampleData = sampleData.split("T")[0];
			if(!siteNum.equals(sampleData)){
				logger.info("The actual 'Site #' value is:"+siteNum);
				throw new ErrorOnPageException("The expected 'Site #' value should be:"+sampleData);
			}
		}
	}
}
