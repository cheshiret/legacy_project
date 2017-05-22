
package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * This page is the common page for "View As List" and "View As Map";
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public abstract class UwpUnifiedSearchResultCommonPage extends RecgovCommonPage {


	protected RegularExpression greenNoMapPinSRC=new RegularExpression(".*images/maps/marker_NI_NoMap.png",false);
	protected RegularExpression blueNoMapPinSRC=new RegularExpression(".*images/maps/marker_NoMap.png",false);
	protected RegularExpression redNoMapPinSRC=new RegularExpression(".*images/maps/marker_NoMatch_NoMap.png",false);
	protected RegularExpression brownNoMapPinSRC=new RegularExpression(".*images/maps/marker_NA_NoMap.png",false);

	/**
	 * get facility type(Clickable) filters info, the return list including info like below
	  	All(62)
		Camping & Day Use(49)
		Permits(1)
		Tours(1)
		Other Recreation Facilities(11)
	 * @return
	 */
	public List<String> getAllClickableSearchTypeFiltersDisplayText(){
		List<String> filters = new ArrayList<String>();
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","filters"),new Property(".id","filters")};
		Property[] p2 = new Property[]{new Property(".class","Html.A")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length < 1){
			return filters;
		}
		for(int i =0; i < objs.length; i ++){
			filters.add(objs[i].text());
		}
		Browser.unregister(objs);
		return filters;
	}
	
	/**
	 * Get disabled facility type filters info, the return list including info like below
	 * Permits(0)
	 * Tours(0)
	 * @return
	 */
	public List<String> getAllDisabledSearchTypeFiltersDisplayText(){
		List<String> filters = new ArrayList<String>();
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","filters"),new Property(".id","filters")};
		Property[] p2 = new Property[]{new Property(".className","disabled")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length < 1){
			return filters;
		}
		for(int i =0; i < objs.length; i ++){
			filters.add(objs[i].text());
		}
		Browser.unregister(objs);
		return filters;
	}
	
	/**
	 * Get search type filter text for ALL
	 * @return
	 */
	public String getSearchTypeAllFilterText(){
		String allSearchTypeFilterText = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "all");
		if(objs.length<1){
			throw new ObjectNotFoundException("All search type filter object can't be found.");
		}else{
			allSearchTypeFilterText = objs[0].text();
		}
		
		Browser.unregister(objs);
		return allSearchTypeFilterText;
	}

	/**
	 * 
	 * @param Get the number of search type filter
	 * All(XX)
     * Camping & Day Use(X)
     * Permits(XX)
     * Tours(XX)
     * Other Recreation Facilities(X)
	 * @return
	 */
	public int getNumOfSearchTypeFilter(String searchTypeFilter){
		int numOfSearchTypeFilter = -1;
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","filters"),new Property(".id","filters")};
		Property[] p2 = new Property[]{new Property(".class","Html.A")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length < 1){
			return numOfSearchTypeFilter;
		}
		String numOfSearchTypeFilterString = objs[0].getProperty(".text");
		numOfSearchTypeFilter = Integer.valueOf(numOfSearchTypeFilterString.split("\\(")[1].replaceAll("\\)", ""));

		Browser.unregister(objs);
		return numOfSearchTypeFilter;
	}

	public abstract boolean checkNext();
	public abstract void clickNext();
	public abstract void clickPrevious();
	public abstract List<String> getAllClickableSearchNameFiltersText();
	public abstract String getResultNearHeaderText();
	public abstract boolean isGreenNoMapPinExists( );
	public abstract boolean isBlueNoMapPinExists( );
	public abstract boolean isRedNoMapPinExists( );
	public abstract boolean isBrownNoMapPinExists( );
	
	/**
	 * Verify result near header text 
	 * @param expectedValue: The gave expected result near header text
	 */
	public void verifyResultNearHeaderText(String expectedValue){
		String actualValue = this.getResultNearHeaderText();
		if(!actualValue.startsWith(expectedValue)){
			throw new ErrorOnDataException("The expected value is wrong",expectedValue,actualValue);
		}else{
			logger.info("Successfully verify result near header text.");
		}
	}

	public abstract boolean checkResultNearExist();
	public abstract void verifyResultNearHeaderExist(boolean existed);


	/**
	 * click the Search type filters. the Search Type filters in the following format
	   	All(62)
		Camping & Day Use(49)
		Permits(1)
		Tours(1)
		Other Recreation Facilities(11)

	 * @param searchTypeText
	 * @return
	 */
	public boolean clickSearchTypeFilter(String searchTypeText){
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".id","filters")};
		Property[] p2 = new Property[]{new Property(".class","Html.A"),new Property(".text", RegularExpression.convert(searchTypeText, false))};

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length < 1){
			return false;
		}
		objs[0].click();
		Browser.unregister(objs);
		return true;
	}

	public abstract boolean clickSearchNameFilter(String searchNameText);

	public void clickViewAsMap() {
//		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression(".*view as Map.*",false),true);
		browser.clickGuiObject(Property.concatPropertyArray(a(), ".title", "View as Map"));
	}

	public void clickViewAsList() {
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(".*view as List.*",false));
		browser.clickGuiObject(Property.concatPropertyArray(a(),".title", "View as List"));
	}

	/**
	 * click the "Show only available sites" hyper link which beneath the "View As List" and "View As Map" Tab.
	 */
	public void clickShowOnlyAvailableSites(){
		browser.clickGuiObject(".class","Html.A",".title","Show only sites that have availability for your dates.");
	}

	/**
	 * Click the "Show all results" hyper link which beneath the "View As List" and "View As Map" Tab.
	 */
	public void clickShowAllResults(){
		browser.clickGuiObject(".class","Html.A",".title","Show all results.");
	}
	
	public abstract String getWaringMes();

	public boolean isGreenNoMapPinExists(String facilityName){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false), ".class", "Html.IMG", ".src", "images/maps/marker_NI_NoMap.png"));
	}

	public boolean isBlueNoMapPinExists(String facilityName){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false), ".class", "Html.IMG", ".src", "images/maps/marker_NoMap.png"));
	}

	public boolean isRedNoMapPinExists(String facilityName){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false), ".class", "Html.IMG", ".src", "images/maps/marker_NoMatch_NoMap.png"));
	}

	public boolean isBrownNoMapPinExists(String facilityName){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false), ".class", "Html.IMG", ".src", "images/maps/marker_NA_NoMap.png"));
	}
	public abstract int getTotalSearchResultNum();
	public abstract void verifyTotalSearchResults(int expectResults);

	public abstract boolean checkSearchTypeFilterExist();

	/**
	 * Verify whether Search Type exist or not 
	 * @param expectedExisted
	 */
	public void verifySearchTypeFilterExist(boolean expectedExisted){
		boolean actualExisted = this.checkSearchTypeFilterExist();
		if(actualExisted!=expectedExisted){
			throw new ErrorOnDataException("Search type filter should "+(expectedExisted?"be":"not be")+" existed.");
		}else{
			logger.info("Successfully verify search type "+(expectedExisted?"":"doesn't")+" exist.");
		}
	}
	
	public abstract void verifySearchNameFilterExist(boolean expectedExisted);

	
	public String[] getAlphabeticalResult(String[] data){
		Arrays.sort(data);
		return data;
	}
	
	public String[] getAlphabeticalResult(List<String> data){
		List<String> beforeSort = data;
		String[] afterSort = new String[beforeSort.size()];

		for(int i=0; i<beforeSort.size(); i++){
			afterSort[i] = beforeSort.get(i);
		}
		Arrays.sort(afterSort);
		return afterSort;
	}
	
	public void verifyAlphabeticalOrderSort(List<String> data){
		List<String> actualSorting = data;
		String[] expectedSorting = new String[actualSorting.size()];

		for(int i=0; i<actualSorting.size(); i++){
			expectedSorting[i] = actualSorting.get(i);
		}

		Arrays.sort(expectedSorting);
		for(int i=0; i<actualSorting.size(); i++){
			if(!expectedSorting[i].equals(actualSorting.get(i).split(",")[0])){
				throw new ErrorOnDataException("Alphabetical sorting order is wrong!", expectedSorting[i], actualSorting.get(i));
			}
		}
	}

	public void verifyAlphabeticalOrderSort(String[] data){
		String[] actualSorting = data;
		String[] expectedSorting = new String[actualSorting.length];

		for(int i=0; i<actualSorting.length; i++){
			expectedSorting[i] = actualSorting[i];
		}

		Arrays.sort(expectedSorting);
		for(int i=0; i<actualSorting.length; i++){
			if(!expectedSorting[i].equals(actualSorting[i].split(",")[0])){
				throw new ErrorOnDataException("Alphabetical sorting order is wrong!", expectedSorting[i], actualSorting[i]);
			}
		}
	}
	
	public abstract List<String> geParkSiteTypeTitlesInFirstPg();
	public abstract boolean checkShowOnlyAvailableSitesLinkExist();
	public abstract boolean checkShowAllResultsLinkExist();
	
	/**
	 * Verify park name starts with ...
	 * @param parkName
	 * @param startsWith
	 */
	public void verifyParkNameStratsWith(String parkName, String startsWith){
		if(!parkName.startsWith(startsWith)){
			throw new ErrorOnPageException(parkName+" doesn't start with "+startsWith);
		}
		logger.info(parkName+" starts with "+startsWith);
	}
}
