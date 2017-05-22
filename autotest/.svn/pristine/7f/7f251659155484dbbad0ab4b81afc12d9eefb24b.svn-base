/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.ticket.searchtourfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Searh non-tour park  and verify the search result and error message;
 * @Preconditions:RFT only
 * @SPEC:Search Tours with non-tour facility [TC:042230]
 * @Task#: 	AUTO-1181
 * This case should be run on RFT, cause selenium could not click auto-completed box
 * @Author asun
 * @Date  Aug 13, 2012
 * 
 * @Update at 9/21/2012 by Sara because 
 * 1. Addition space in warning message 
 * 2. heart text it changed to "All Tours & Tickets Facilities" from "No matching result. All Tours & Tickets Facilities" after confirmation
 */
public class NonTourStateSearch extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String warningMsg;
	String heatertext;
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		listPage.verifyWarningMes(warningMsg);
		listPage.verifyResultNearHeaderText(heatertext);
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		unifiedSearch.whereTextValue="SOUTH CAROLINA";
		unifiedSearch.selectAutoCompleteOption=true;
		unifiedSearch.selectedAutoCompletedOption=unifiedSearch.whereTextValue;
		unifiedSearch.interestInValue="Tours & Tickets";
		
		warningMsg="No matching results were found for '"+unifiedSearch.whereTextValue+"'. To help you we've listed everything matching '"+unifiedSearch.interestInValue+"'.";
		heatertext="All Tours & Tickets Facilities"; //"'No matching result. All Tours & Tickets Facilities";// Issue Confirm
	}
}
