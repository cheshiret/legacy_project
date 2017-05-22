package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.RecFacilityInfo;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**NOTE: this test case has been abandoned b/c. the script was supposed to test search result for 1000 miles. after the web.config changed to 200 miles for unified search, this script can't run any more.
 * mailed Roman, we have checked this checkpoints via some other scripts.
 * @Description:
 * @Preconditions:
 * 1: the distance was set to 1000 miles.
 * 2: the facilities in the wrapParameters methods haven't been added to the QA environment by the support script(addNewFacility).
 * @SPEC:
 * How to convert miles to degrees:
 * degrees = miles/ 3963.0 * 180/ 3.141592653589793;
 *
 * For 200 miles you get \ufffddegrees = 2.891535680700597	= 2 degree, 53.4 minutes, 5.5284505221492 seconds
 * For 50 miles you get degrees = 0.722883920175149	= 0 degree, 43.2 minutes, 10.3821126305364 seconds
 * For 1000 miles you get degrees = 14.457678403502983 = 14 degree, 27 minutes, 27.6422526107388 seconds
 * @Task#: AUTO-641
 *
 * @author bzhang
 * @Date  Jun 28, 2011
 */
public class SearchResultValidation extends WebTestCase {
	RecgovParkListPage uresultPg = RecgovParkListPage.getInstance();
	String facNamesInsideBoundary[] = new String[4];
	String facNamesOutSideBoundary[] = new String[4];
	String distanceBaseLine;
	Double distanceBaseLineAccuracyValue;

	@Override
	public void execute() {
		web.invokeURL(url);

		//verify the data inside the boundary
		for (int i = 0; i <facNamesInsideBoundary.length; i ++){
			this.searchFacility(facNamesInsideBoundary[i]);
			this.gotoFaclilityListPg(facNamesInsideBoundary[i]);
			this.verifySearchResult(facNamesInsideBoundary);
		}

		//verify the data outside the boundary exist
		for (int i = 0; i <facNamesOutSideBoundary.length; i ++){
			this.searchFacility(facNamesOutSideBoundary[i]);
			this.gotoFaclilityListPg(facNamesOutSideBoundary[i]);
			this.verifySearchResult(facNamesOutSideBoundary);
		}

	}

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		distanceBaseLine = TestProperty.getProperty(env + ".web.recgov.unifySearchDistanceBaseLine");

		// facilities inside the unify search boundary value
		facNamesInsideBoundary[0] = "QA AUTO PARK A" ;
		facNamesInsideBoundary[1] = "QA AUTO PARK B" ;
		facNamesInsideBoundary[2] = "QA AUTO PARK C" ;
		//facNames[3] = "QA AUTO PARK D" ;

		//facilities outside the unify search boundary value
		facNamesOutSideBoundary[0] = "QA AUTO PARK E" ;
		facNamesOutSideBoundary[1] = "QA AUTO PARK F" ;
		facNamesOutSideBoundary[2] = "QA AUTO PARK G" ;
		//facNamesOutSideBoundary[3] = "QA AUTO PARK H" ;

	}

	private void searchFacility(String facName){
		UwpUnifiedSearchPanel uPg = UwpUnifiedSearchPanel.getInstance();
		uPg.setWhere(facName);
		uPg.clickSearch();
		uresultPg.waitLoading();
	}

	private void gotoFaclilityListPg(String facName){
		uresultPg.gotoSearchResultListPage(facName);
	}


	/**
	 * compare the search result data with the expecting result.
	 * @param expectFacilities, the expected array for facilities.
	 * @param insideOrOutsideBoundary, true: verify the data inside the boundary value ; false: verify the data outside the boundary value
	 */
	private void verifySearchResult(String[] expectFacilities){
		List<RecFacilityInfo> facInfo = uresultPg.getSearchResultsFacilityInfo();

		boolean match = false;
		Double accuracy = this.getDistanceAccuracyValue();

		if (facInfo.size() == expectFacilities.length ){

			for (int i = 0; i < facInfo.size(); i ++){
				if (!facInfo.get(i).parkName.startsWith(expectFacilities[i])){
					break;
				}

				if (Double.parseDouble(facInfo.get(i).distance) > Double.parseDouble(distanceBaseLine) + accuracy ){
					break;
				}

				if (i== facInfo.size() -1){
					match = true;
				}
			}
		}else{
			throw new ErrorOnDataException("The search result total number didn't match the given facility numbers ");
		}

		if (!match){
			throw new ErrorOnPageException("The search result didn't match the given facility name ");
		}
	}

	private Double getDistanceAccuracyValue(){
		Double pi = 3.141592653589793;

		// if the distance base line is 1000 miles, we will push 3 degree to get the value which is just outside of 1000 boundary.
		if (Double.parseDouble(distanceBaseLine)==1000){
			distanceBaseLineAccuracyValue = pi/180*3*3963.0;
		}
		return distanceBaseLineAccuracyValue;
	}

}
