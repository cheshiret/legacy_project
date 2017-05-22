package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: p
 * 1. Agency filter options after searching correct names for all the 17 agencies are displayed with below query result
 * Get the correct names correct names for all the 17 agencies: 
 *  select orgname from organization where orgid not in (select ridb_ag_id from ag_mapping) and (orgid in (select orgid from orgfacility) or orgid in (select orgid from orgrecarea)) union select QA4_AUG02_NRRS.d_loc.dscr from QA4_AUG02_NRRS.d_loc where QA4_AUG02_NRRS.d_loc.id in (select orms_ag_id from ag_mapping) order by orgname;
 * 2. Agency filter sort alphabetical
 * @Preconditions:
 * @SPEC: Agency name showing for Agency Filter options [TC:043129] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Oct 8, 2012
 */
public class AgencyFilterOptionsValidation extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private List<String> agencyOptionsFromDB = new ArrayList<String>();
	private List<String> agencyOptionsFromUI = new ArrayList<String>();
	private String ridbbSchema;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);
		agencyOptionsFromUI = viewAsListPg.getFilterNames(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		
		//Agency filter options are displayed with below query result from DB
		this.verifyAgencyFilterOptionsDisplays();
		//Agency filter options sort alphabetical
		viewAsListPg.verifyAlphabeticalOrderSort(agencyOptionsFromUI);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		ridbbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		
		searchData.whereTextValue = "CA";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		agencyOptionsFromDB = web.getAllAgencyFiltersFromDB(ridbbSchema, schema);
	}
	
	/**
	 * Verify agency filter options display correct as the result querying from DB
	 */
	private void verifyAgencyFilterOptionsDisplays(){
		for(int i=0; i<agencyOptionsFromUI.size(); i++){
			if(!agencyOptionsFromDB.contains(agencyOptionsFromUI.get(i))){
				throw new ErrorOnPageException("Agency filter option:"+agencyOptionsFromUI.get(i)+" doesn't display correct in 'View As List' page.");
			}
			logger.info("Agency filter option:"+agencyOptionsFromUI.get(i)+" display correct in 'View As List' page.");
		}
	}
	
}

