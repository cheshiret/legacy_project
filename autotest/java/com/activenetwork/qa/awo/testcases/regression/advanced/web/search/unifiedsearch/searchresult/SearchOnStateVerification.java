package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: The expecting result get from the time when this script was created, it's possible that the expect result changed due to add/remove/rename.. facilities info.
 * when the actual result didn't match with the expect result, we must communicate with Lisha to make sure search function works ok. 
 * @Description:
 * 1: verify the search result match with expect given value, when people get to search result page via click sate info on suggestion page.
 * 2: verify the search result match with expect given value, when people get to search result page via choose the auto complete state info.
 * @Preconditions:
 * @SPEC:
 * Story AC - Add Google Addresses and State on Suggestions Page
 * @Task#: Auto-781
 * 
 * 
 * @author bzhang
 * @Date  Oct 26, 2011
 */
public class SearchOnStateVerification extends RecgovTestCase{

	private String url,ridbSchema;
	private String[] wheres, stateCode;
	private List<List<String>> recareaInDB = new ArrayList<List<String>>() ;
	private List<List<String>> recFacilityInDB =new ArrayList<List<String>>() ;
	private List<List<String>> facilityInDB = new ArrayList<List<String>>()  ;
	private int[] expectTotalNum = new int[2];

	public void execute() {
		web.invokeURL(url);
		//1: verify search result without select auto complete item.
		this.verifySearchResultMatchWithGivenValues(false);
//
//		//2: verify search result when select auto complete item.
//		this.verifySearchResultMatchWithGivenValues(true);
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env +".db.schema.prefix") + "NRRS";
		ridbSchema = TestProperty.getProperty(env +".ridb.schema");

		url = TestProperty.getProperty(env + ".web.recgov.url");
		//Sara[12122013] So many search results using SD and FL. It is too hard to check the different between DB and UI data. So change state as "NJ" which can match out testing.
		wheres = new String[]{"NEW JERSEY"}; //{"SOUTH DAKOTA", "FLORIDA"}; 
		stateCode = new String[]{"NJ"}; //{"SD", "FL"};

		//get total number for States 
		for(int i = 0; i < stateCode.length; i ++){
			facilityInDB.add(web.queryStateFacility(schema, stateCode[i])); //1
			recFacilityInDB.add(web.queryStateRIDBFacilities(ridbSchema, stateCode[i])); //2
			recareaInDB.add(web.queryStateRIDBAreas(ridbSchema, stateCode[i]));//16

			if(null != facilityInDB.get(i)){
				expectTotalNum[i] +=  facilityInDB.get(i).size();
			}
			if(null != recFacilityInDB.get(i)){
				expectTotalNum[i] +=  recFacilityInDB.get(i).size();
			}
			if(null != recareaInDB.get(i)){
				expectTotalNum[i] +=  recareaInDB.get(i).size();
			}
			logger.info("The total facilty number get from DB for State \"" +stateCode[i] +"\" is:" + expectTotalNum[i]);
		}
	}

	private void verifySearchResultMatchWithGivenValues(boolean selectAutoComplete){
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		RecgovViewAsListPage searchResultPg = RecgovViewAsListPage.getInstance();

		for(int i = 0 ; i < wheres.length; i ++){
			UwpUnifiedSearch unifSearch = new UwpUnifiedSearch();
			unifSearch.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
			unifSearch.whereTextValue = wheres[i];
			if (selectAutoComplete){
				unifSearch.selectAutoCompleteOption = true;
				unifSearch.selectedAutoCompletedOption =  wheres[i];
				//when select the auto complete items
				web.makeUnifiedSearch(unifSearch);
			}else{
				//when we don't select the auto complete items
				unifSearch.selectAutoCompleteOption = false;
				web.makeUnifiedSearch(unifSearch);
				suggestPg.clickFirstStateSuggestion();				
				searchResultPg.waitLoading();
			}

			//verify the search result, match with the given value.
			List<FacilityData> allFacilities = searchResultPg.getAllFacilityInfo();
			logger.info("State " + wheres[i] + " facilites total number displayed on the page are:" + allFacilities.size());

			if(expectTotalNum[i] != allFacilities.size()){
				//parse the data, find out the difference.
				List<String> recareaOnPg = new ArrayList<String>();
				List<String> recFacilityOnPg = new ArrayList<String>();
				List<String> facilityOnPg= new ArrayList<String>();

				//pares the facility info get on the page, group them into Facilities and Recarea.
				for(int j = 0 ; j < allFacilities.size(); j ++){
					if (allFacilities.get(j).productCategory.equalsIgnoreCase("facility")){
						facilityOnPg.add(allFacilities.get(j).facilityID);
					}else if(allFacilities.get(j).productCategory.equalsIgnoreCase("recarea")){
						recareaOnPg.add(allFacilities.get(j).facilityID);
					}else if(allFacilities.get(j).productCategory.equalsIgnoreCase("recfacility")){
						recFacilityOnPg.add(allFacilities.get(j).facilityID);
					}else{
						throw new ErrorOnDataException("The park:" + allFacilities.get(j).facilityName +" get on the page didn't belong to facility or recarea...");
					}
				}

				//find out the difference for Contract facilities.
				if(null != facilityInDB.get(i) && facilityOnPg.size() != facilityInDB.get(i).size()){
					this.parseMissingFacilities(facilityOnPg, facilityInDB.get(i));
				}

				//find out the difference for rec areas
				if(null != recareaInDB.get(i) && recareaOnPg.size() != recareaInDB.get(i).size()){
					this.parseMissingFacilities(recareaOnPg, recareaInDB.get(i));
				}

				//find out the differences for rec facilities
				if(null != recFacilityInDB.get(i) && recFacilityOnPg.size() != recFacilityInDB.get(i).size()){
					this.parseMissingFacilities(recFacilityOnPg, recFacilityInDB.get(i));
				}

				//parse done.
				throw new ErrorOnPageException("The expect Facility total number should be:" + expectTotalNum[i] + ", but there are " + allFacilities.size() + " facilites.");
			}else{
				logger.info("Total facility number displayed on page for state " + stateCode[i] + " verify successfully.");
			}
		}
	}

	/**
	 * Find out the missing facilities between DB count query and count number displayed on the page.
	 * @param facilityOnPg
	 * @param facilityInDB
	 * @return
	 */
	private List<String> parseMissingFacilities(List<String> facilityOnPg, List<String> facilityInDB){
		List<String> missingFacilities = new ArrayList<String>();

		List<String> largeList = null;
		List<String> smallList = null;
		String log = "";

		if(facilityOnPg.size() > facilityInDB.size()){
			largeList = facilityOnPg;
			smallList = facilityInDB;
			log = "Facility query missing from DB is:";
		}else{
			largeList = facilityInDB;
			smallList = facilityOnPg;
			log = "Facility query missing on page is:";
		}

		//loop the large list analyze the missing facility info.
		for(int i = 0; i < largeList.size(); i ++){
			if (!smallList.contains(largeList.get(i))){
				missingFacilities.add(largeList.get(i));
			}
		}
		for(int i = 0 ; i < missingFacilities.size(); i ++){
			logger.error(log + missingFacilities.get(i));
		}
		return missingFacilities;
	}
}
