package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: verify search results header displayed between View As List page and View As Map page are the same.
 * 2: verify click next button no matter on "View As List" page or "View As Map" page will paging on the other page as well.
 * 3: verify each view as map page will display Pins start from A - J(10 record);
 * @Preconditions:
 * 1: there are at least 2 pages record displayed on View As Map page.
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 12, 2011
 */
public class ResultHeaderBetweenListAndMap extends RecgovTestCase {
	
	private String[] Pins;
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
//		this.verifyParkTotalDisplayNumber();//Sara[3/21/2014] Can't verify letter pin in View As Map page because it uses HTML5, Selenium can't handle
		this.verifySearchResultsInfo();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "NORTH PINES";
		bd.interestInValue = "Camping & Lodging";
		bd.contractCode ="NRSO";
		bd.parkId = "70927";
		
		//each pin associated with a different result has a different letter starting with A to J per pages of 10
		Pins = new String[]{"A","B","C","D","E","F","G","H","I","J"};
	}
	
	/**
	 * each pin associated with a different result has a different letter starting with A to J per pages of 10
	 */
	private void verifyParkTotalDisplayNumber(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		for(String startLetter: Pins){
			if(!mapPg.checkMapPinMatched(startLetter)){
				throw new ErrorOnPageException("There is no Pin start with letter:" + startLetter);
			}
		}
	}
	
	/**
	 * verify search results info displayed on different messages.
	 */
	private void verifySearchResultsInfo(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		int clickNextCounter = 0;
		
		logger.info("Start verify paging function between view as list and view as map page.");
		do{	
			int start = 1 + 10*clickNextCounter;
			int end = 10 + (10* clickNextCounter);
			//1: verify click next button on view as map page will result in paging function on list page as well.
			String searchMsgOnMap = mapPg.getSearchResultsLabel();
			
			int totalRecord = mapPg.getTotalSearchResultNum();
		    if(end > totalRecord){
		    	end = totalRecord;
		    }
		    
		   //verify Search Reults Message display "11-20 of XXX";
			if(!searchMsgOnMap.contains(start + "-" + end)){
				throw new ErrorOnDataException("The search results message should be: " + start + "-" + end +" of XXX");
			}
			//Click on View as List tab
			mapPg.clickViewAsList();
			listPg.waitLoading();
			String searchMsgOnList = listPg.getSearchResultsLabel();
			if(!searchMsgOnList.equals(searchMsgOnMap)){
				logger.error("Search Results Message displayed on Map  is:" + searchMsgOnMap);
				logger.error("Search Results Message displayed on List is:" + searchMsgOnList);
				throw new ErrorOnDataException("The search results message displayed on Map and List are inconsistent.");
			}
			//2: verify click next button on view as list page will result in paging function on view as map page as well.
			if (listPg.gotoNext()){
				clickNextCounter ++;
			}
			searchMsgOnList = listPg.getSearchResultsLabel();			
			listPg.clickViewAsMap();			
			mapPg.waitLoading();
			searchMsgOnMap = mapPg.getSearchResultsLabel();
			
			if(!searchMsgOnList.equals(searchMsgOnMap)){
				logger.error("Search Results Message displayed on Map  is:" + searchMsgOnMap);
				logger.error("Search Results Message displayed on List is:" + searchMsgOnList);
				throw new ErrorOnDataException("The search results message displayed on Map and List are inconsistent.");
			}
			clickNextCounter ++;
		}while (mapPg.gotoNext() && clickNextCounter<=3); //Sara[3/21/2014] Only check view as list first and third page, view as map second and forth page info, not all of the pages
	}

}
