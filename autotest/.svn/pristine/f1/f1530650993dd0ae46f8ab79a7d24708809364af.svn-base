package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:(P) 
		1. Enter sample data in 'Where' field;
		2. Check the header for state in auto-complete list 
		3. Check the state showing in auto-complete list 
		4. Check the order if multiple results returned

		Expected Result:
		-Header for state in auto-complete list - Within State
		-State showing in auto-complete list - Full name of the state 
		-Sorted alphabetically when multiple results returned

 * Note: Ignore test data (wash), which has the alphabetically sort "WASHINGTON D.C." in front of "WASHINGTON"
        The official (ISO) state name for Washington D.C is District of Columbia.
        The list of US states is static ( a javascript object). 
        That means the sorting is based on our arrangement. 
        Because the initial name was District Of Columbia., 
        this "state" has been positioned according to the 'D' letter.

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 13, 2012
 */
public class UI_AutoCompletedList extends RecgovTestCase {
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String[]> stateFullNames = new ArrayList<String[]>();

	public void execute(){
		web.invokeURL(url);
		this.verifyStateHeadingFullNameAndSorting();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValues = new String[]{"sout", "ca", "wy", "in", "ha"};
		stateFullNames.add(new String[]{"SOUTH CAROLINA", "SOUTH DAKOTA"});
		stateFullNames.add(new String[]{"CALIFORNIA"});
		stateFullNames.add(new String[]{"WYOMING"});
		stateFullNames.add(new String[]{"INDIANA"});
		stateFullNames.add(new String[]{"HAWAII"});
	}

	/**
	 * Verify State heading, full name, sorting in auto completed list
	 */
	private void verifyStateHeadingFullNameAndSorting(){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();

		logger.info("Start to verify State heading, full name, sorting in auto completed list.");
		for(int i=0; i<searchData.whereTextValues.length; i++){
			searchPanel.setWhere(searchData.whereTextValues[i]);
			searchPanel.triggerAutoCompleteBoxDisplay();

			//Check state heading: Within State
			List<String> autoCompletedOptions = searchPanel.getAutoCompleteOptions("Within State");

			//Sorted alphabetically
			searchPanel.verifySortByAlphabetical(autoCompletedOptions);

			//Check full name of the state
			if(autoCompletedOptions.size()!=stateFullNames.get(i).length){
				throw new ErrorOnDataException("Expected state name size doesn't equal to the actual.", 
						String.valueOf(stateFullNames.get(i)), String.valueOf(autoCompletedOptions.size()));
			}
			for(int j=0; j<autoCompletedOptions.size(); j++){
				if(!autoCompletedOptions.get(j).equals(stateFullNames.get(i)[j])){
					throw new ErrorOnDataException("The state full name is wrong!", stateFullNames.get(i)[j],autoCompletedOptions.get(j));
				}
			}
		}
	}
}
