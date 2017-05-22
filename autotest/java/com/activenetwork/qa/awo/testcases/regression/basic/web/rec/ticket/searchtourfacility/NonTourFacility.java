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
 * @Preconditions:
 * @SPEC:Search Tours with non-tour facility [TC:042230]
 * @Task#: 	AUTO-1181
 * 
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class NonTourFacility extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	String expectError;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		listPage.verifyFirstParkName(unifiedSearch.whereTextValue);
		listPage.verifyErrorMes(unifiedSearch.contractCode, unifiedSearch.parkId, expectError);
		listPage.verifyParksSortByDistance();
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		//BELOW DAM SOUTH CAROLINA 
		unifiedSearch.parkId="75452";
		unifiedSearch.whereTextValue=web.getFacilityName(unifiedSearch.parkId, schema);
		unifiedSearch.interestInValue="Tours & Tickets";
		unifiedSearch.contractCode="NRSO";
		
		expectError="No Tour found";
	
	}
}
