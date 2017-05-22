/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.ticket.searchtourfacility;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:Searh non-tour state  and verify the search result;
 * @Preconditions:RFT only
 * @SPEC:Search Tours with state include tour [TC:042231]
 * @Task#: 	AUTO-1181, AUTO-2132
 * 
 * @Author asun, SWang
 * @Date  Aug 13, 2012, May 22, 2014
 */
public class StateSearch extends RecgovTestCase {
	private UwpUnifiedSearch unifiedSearch=new UwpUnifiedSearch();
	private RecgovViewAsListPage listPage=RecgovViewAsListPage.getInstance();
	private String expectedValue, stateCode;
	private List<String> facilitiesFromDB, facilitiesFromUI;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(unifiedSearch);
		
		//Verify result header bar
		listPage.verifyResultNearHeaderText(expectedValue);
		
		//Verify search result parks
		facilitiesFromUI = listPage.getAllParkNames();
		listPage.verifyAllParkNames(facilitiesFromDB, facilitiesFromUI);
	}
 
	@Override
	public void wrapParameters(Object[] param) {
		unifiedSearch.whereTextValue="CALIFORNIA";
		stateCode = "CA";
		unifiedSearch.selectAutoCompleteOption=true;
		unifiedSearch.selectedAutoCompletedOption=unifiedSearch.whereTextValue;
		unifiedSearch.interestInValue=UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;
		
		expectedValue="Results within "+unifiedSearch.whereTextValue;
		
		facilitiesFromDB = new ArrayList<String>();
		facilitiesFromUI = new ArrayList<String>();
		facilitiesFromDB.addAll(web.queryContractFacilityNames(schema, stateCode, StringUtil.EMPTY, OrmsConstants.PRDCAT_TICKET, StringUtil.EMPTY));
	}
}
