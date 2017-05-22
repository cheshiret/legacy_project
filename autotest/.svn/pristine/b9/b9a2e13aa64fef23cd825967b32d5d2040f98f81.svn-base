package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewaslist;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: p
 * The search result should be match filters(type, agency and letter)
 * @Preconditions:
 * @SPEC: Interatcions between filters (Type, Agency and Letter) below the search form [TC:043199] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang5
 * @Date  Oct 9, 2012
 */
public class FilterResult  extends RecgovTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String agencyFilterOption, letterFilterOption;
	private List<String> parksType = new ArrayList<String>();
	private List<String> parksAgency = new ArrayList<String>();
	private List<String> parksName = new ArrayList<String>();

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		
		//Filters(Type, agency, letter)
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, agencyFilterOption, letterFilterOption);
		
		//Get parks type, agency and name info
		parksType = viewAsListPg.getAllParkSiteTypeTitles();
		parksAgency = viewAsListPg.getAllParksAgency();
		parksName = viewAsListPg.getAllParkNames();
		
		//Verify the filter results: They are 'Camping & Day use' type, belong to agency of "US Army Corps of Engineers" and starting with letter "B"
		this.verifyParksType();
		viewAsListPg.verifyParksAgency(parksAgency, agencyFilterOption);
		this.verifyParkNamesStartWithLetterB();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.whereTextValue = "DURHAM, NC, USA";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
//		bd.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
//		bd.otherActivityName = "Biking";
		agencyFilterOption = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USARMYCORPSOFENGINEERS_ID, schema);
		letterFilterOption = "B";
	}
	
	/**
	 * Verify park type should be "Camping and Lodging" and "Day use and Picnic areas"
	 * 
	 */
	private void verifyParksType(){
		for(int i=0; i<parksType.size(); i++){
			if(!parksType.get(i).equals(UwpUnifiedSearch.PARKTYPE_CAMPINGANDLODGING) && 
					!parksType.get(i).equals(UwpUnifiedSearch.PARKTYPE_DAYUSEANDPICNICAREAS) ){
				throw new ErrorOnPageException("Park type is wrong! The actual one is:"+parksType.get(i)+", but the expected should be:"+UwpUnifiedSearch.PARKTYPE_CAMPINGANDLODGING+", or the one:"+UwpUnifiedSearch.PARKTYPE_DAYUSEANDPICNICAREAS);
			}
			logger.info("SUccessfully verify park type:"+parksType.get(i));
		}
	}
	
	/**
	 * Verify park names start with letter B
	 */
	private void verifyParkNamesStartWithLetterB(){
		for(int i=0; i<parksName.size(); i++){
			if(!parksName.get(i).startsWith(letterFilterOption)){
				throw new ErrorOnPageException(parksName.get(i)+" park name doesn't start with letter "+letterFilterOption);
			}
			logger.info("Successfully verify "+parksName.get(i)+" park name starts with letter "+letterFilterOption);
		}
	}
}
