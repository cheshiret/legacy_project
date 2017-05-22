/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.searchfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Searh non-tour park  and verify the search result and error message;
 * @Preconditions:RFT only
 * @SPEC:Search Permits with state not include permit [TC:042239]
 * @Task#: 	AUTO-1182
 * 
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class NonPermitStateSearch extends RecgovTestCase {
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
		
		unifiedSearch.whereTextValue="SOUTH CAROLINA";
		unifiedSearch.selectAutoCompleteOption=true;
		unifiedSearch.selectedAutoCompletedOption=unifiedSearch.whereTextValue;
		unifiedSearch.interestInValue="Permits & Wilderness";
		
		warningMsg="No matching results were found for '"+unifiedSearch.whereTextValue+" ?'\\." +
				" To help you we've listed everything matching '"+unifiedSearch.interestInValue.replaceAll("&", "\\\\&")+"'\\.";
		heatertext="All "+unifiedSearch.interestInValue+" Facilities";// Issue Confirm
	}
}
