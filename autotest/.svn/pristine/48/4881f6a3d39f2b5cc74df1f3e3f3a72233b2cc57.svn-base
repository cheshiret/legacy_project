/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.ticket.searchtourfacility;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Searh tour park from Auto-complete list and verify the search result;
 * @Preconditions:RFT only
 * @SPEC:Search Tours with tour facility from auto-complete list [TC:042228]
 * @Task#: 	AUTO-1181
 * 
 * @Author asun
 * @Date  Aug 13, 2012
 */
public class SearchFromAutoList extends RecgovTestCase {
	UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	private String parentName, ridbSchema;

	@Override
	public void execute() {
		web.invokeURL(url);
		web.makeUnifiedSearch(unifiedSearch);
		this.verifySearchResult();
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		url= TestProperty.getProperty(env + ".web.recgov.url");
		
		schema=DataBaseFunctions.getSchemaName("NRRS", env);
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		//BLANCHARD SPRINGS CAVERNS 
		unifiedSearch.parkId="72270";
		unifiedSearch.whereTextValue=web.getFacilityName(unifiedSearch.parkId, schema);
		unifiedSearch.interestInValue="Tours & Tickets";
		unifiedSearch.selectAutoCompleteOption=true;
		parentName = DataBaseFunctions.getRecreationAreaName("1038", ridbSchema); //web.getFacilityParentName(schema, unifiedSearch.parkId, 1);
		unifiedSearch.selectedAutoCompletedOption=unifiedSearch.whereTextValue+" ?, ?"+parentName+", ?AR";
		                                        //BLANCHARD SPRINGS CAVERNS , OZARK-ST. FRANCIS NATIONAL FORESTS, AR
	}
	
	   /**
     * Verify Park list page exist and the target park existing.
     */
	private void verifySearchResult() {
		RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
		logger.info("Verify Park list page exist and the target park existing.");
		if(!listPage.exists()){
			throw new ErrorOnPageException("there should be Park View As List page.");
		}
		
		listPage.verifyFirstParkName(unifiedSearch.whereTextValue);
		
		
	}

}
