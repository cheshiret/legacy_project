package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.ibm.icu.math.BigDecimal;

/**
 * NOTE: pass on local
 * @Description:
 * Enter Mammoth Picnic Shelters into Where form with Day Use and Picnic areas interested in  + no other criteria and click Search
 * @Preconditions:
 * @SPEC: Story C
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 11, 2011
 * @Note: update cases because display another park as last park: FERN SPRINGS DAY USE PICNIC AREA with 236.05miles
 */
public class DayUseAndPicnicFilter extends RecgovTestCase {
//	UwpUnifiedSearch dayUse = new UwpUnifiedSearch();
	private String headerText = "Search Results: 1-10 of 63";
	private String lastParkName, lastParkdistance;
	private BigDecimal aroundDistance = new BigDecimal("100.00"); //("200.00");
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		
		//1: verify Mammoth Picnic Shelters display as target result
		this.verifyFirstFacility(bd.whereTextValue);
		
		//No need to verify this check point because it will be conflicted by the adding parks
//		//2: verify 61 Total Results display including the target
//		this.verifySearchResultSummaryInfo(headerText, true);
		
		//3:Verify the nearby results are within approximately 200 mile distance by paging through to the last page using the Next link.
//		this.verifyLastParkNameAndDistance(lastParkName, lastParkdistance) ;
		//Verify the last park has distance bigger than 200.00 miles
		verifyLastParkDistanceMoreThan200Miles();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = "MAMMOTH PICNIC SHELTERS";
		bd.parkId = "70920";
		bd.contractCode = "NRSO";
		bd.interestInValue = "Day use & Picnic areas";
		
		//last park name and distance info, this info recorded on the time of debugging this test case, it is possible to change.
		//whenever this value changed, we must make sure the last park name is around 200 miles(could be more than 200 miles), otherwise need to double check with Roman the boundary
		//miles search value changed or not.
		lastParkName = "FORREST W. BO WOOD (DAY USE)";
		lastParkdistance = "211.65";
	}
	
	/**
	 * verify the first facility displayed on the search result page match with the given search criteria.
	 * @param expectFacility
	 */
	private void verifyFirstFacility(String expectFacility){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String actualFacility = searchResult.getFirstParkName();

		if(!actualFacility.startsWith(expectFacility.toUpperCase())){
			throw new ErrorOnDataException("The expect park name is:" + expectFacility + "; while the current park name is:" + actualFacility);
		}
	}
	
//	/**
//	 * verify the search result list header info match with the given text.
//	 * @param expectMsg
//	 * @param isHeader
//	 */
//	private void verifySearchResultSummaryInfo(String expectMsg, boolean isHeader){
//		UnifiedParkViewAsListPage searchResult = UnifiedParkViewAsListPage.getInstance();
//		String actualMsg = "";
//		if(isHeader){
//			actualMsg = searchResult.getSearchResultsLabel();
//		}else{
//			actualMsg = searchResult.getViewHeaderNearValue();
//		}
//		
//		if (!actualMsg.equalsIgnoreCase(expectMsg)){
//			throw new ErrorOnDataException("The expect Message is:" + expectMsg + "; while the current message is:" + actualMsg);
//		}
//	}
//	
//	/**
//	 * Verify the last park within approximately 200(this may change) mile distance
//	 * @param standMiles
//	 */
//	private void verifyLastParkNameAndDistance(String parkName, String standMiles){
//		UnifiedParkViewAsListPage searchResult = UnifiedParkViewAsListPage.getInstance();
//		String parkN = parkName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
//		searchResult.gotoLastPage();
//		String actualPark = searchResult.getLastFacilityViewHeader();
//		String regex = parkN + ".*\\["  + lastParkdistance.replace(".", "\\.") + "miles\\*\\].*";
//		
//		if(!actualPark.matches(regex)){
//			throw new ErrorOnDataException("The last park name is wrong.",parkName,actualPark);
//		}else{
//			logger.info("Verify last park within given distance succesful.");
//		}
//	}
	
	private void verifyLastParkDistanceMoreThan200Miles(){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		searchResult.gotoLastPage();
		BigDecimal lastParkDistance = new BigDecimal(searchResult.getLastParkDistance());
//		if(lastParkDistance.compareTo(aroundDistance)!=1){//product changed than the parks only within 100m will display in view as list page
		if(lastParkDistance.compareTo(aroundDistance)==1){
			throw new ErrorOnDataException("The last park name has distance arount "+aroundDistance+" miles.", aroundDistance, lastParkDistance);
		}
		logger.info("Verify last park within given distance:"+aroundDistance+" succesful.");
	}
	

}
