/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.searchfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Searh non-tour state  and verify the search result;
 * @Preconditions:RFT only
 * @SPEC:Search Permits with state include permit [TC:042238]
 * @Task#: 	AUTO-1182
 * 
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class StateSearch extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String expectedValue;
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		listPage.verifyResultNearHeaderText(expectedValue);
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		unifiedSearch.whereTextValue="CALIFORNIA";
		unifiedSearch.selectAutoCompleteOption=true;
		unifiedSearch.selectedAutoCompletedOption=unifiedSearch.whereTextValue;
		unifiedSearch.interestInValue="Permits & Wilderness";
		
		expectedValue="Results within "+unifiedSearch.whereTextValue;
	
	}
}
