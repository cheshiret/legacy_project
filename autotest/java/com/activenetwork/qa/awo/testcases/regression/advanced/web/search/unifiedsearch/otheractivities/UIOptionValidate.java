package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.otheractivities;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1. Check Other Activities Name
 * 2. Check Other Activities Name sorting
 * Condition:
 * * Only select Other activities from Interest In drop down list
 * * Click Clear Search + Other activities
 * * Any recreation +  Other activities
 * 3. Check Other activities name can be multiple selected
 * @Preconditions:<option name="excluded-activities" value="4/5/6/9/7/14/8/15/16/18/24/26/22/"/>
 * @SPEC:
 * UWP Unified Search_FacilitySearch_UC (Search Form - Expansion of 'Other Activities')
 * UWP Unified Search_Search Form_UI (Search Form - Expansion of 'Other Activities')
 * @Task#: AUTO-766
 * 
 * @author SWang
 * @Date  Oct 13, 2011
 */
public class UIOptionValidate extends RecgovTestCase{
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearch otherActivities = new UwpUnifiedSearch();

	public void execute() {
		web.invokeURL(url);

		//Other activities
		this.checkOtherActivitiesName();

		//Clear Search + Other activities
		otherActivities.clickClearSearch = true;
		this.checkOtherActivitiesName();

		//All recreation + Other activities
		otherActivities.clickClearSearch = false;
		otherActivities.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		searchPanel.selectInterestIn(otherActivities.interestInValue);
		otherActivities.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		this.checkOtherActivitiesName();

		//Multiple selected for Other activities
		this.verifyMultiSelectableOtherActivitiesName();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env+".ridb.schema");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		//Unified Search 
		otherActivities.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		otherActivities.otherActivitiesName = web.getOtherActivitiesApplied(schema, WebConfiguration.getInstance().getUIOption(Conf.rec_UIOptions, UIOptions.ExcludedActivity));
	}

	/**Check Other Activities Name*/
	private void checkOtherActivitiesName(){
		if(otherActivities.clickClearSearch){
			searchPanel.clickClearSearch();
		}
		searchPanel.selectInterestIn(otherActivities.interestInValue);

		String[] applied = searchPanel.getOtheActivitiesName();
		logger.info("Other activities from UI:"+applied.toString());
		logger.info("other activities expected:"+otherActivities.otherActivitiesName.toString());
		if(applied.length==otherActivities.otherActivitiesName.length){
			for(int i=0; i<applied.length; i++){
				if(!applied[i].toUpperCase().equals(otherActivities.otherActivitiesName[i])){
					throw new ErrorOnDataException("The actual option "+applied[i]+" doesn't equal to the expect one "+
							otherActivities.otherActivitiesName[i]);
				}
			}
		}else throw new ErrorOnDataException("The expected length of Other Activities doesn't equal to the actual.",otherActivities.otherActivitiesName.length,applied.length);

	}

	/**Check Other activities name can be multiple selected*/
	private void verifyMultiSelectableOtherActivitiesName(){
		searchPanel.selectOtherActivitiesName(otherActivities.otherActivitiesName[0]);
		searchPanel.selectOtherActivitiesName(otherActivities.otherActivitiesName[1]);
		int unSelected = searchPanel.getOtheActivitiesName().length-2;
		searchPanel.verifyUnselectedOtherActivitiesCount(unSelected);
		if(!searchPanel.checkSelectOtherActivityName(otherActivities.otherActivitiesName[0]) ||
				!searchPanel.checkSelectOtherActivityName(otherActivities.otherActivitiesName[1])){
			throw new ErrorOnDataException("Both these two Check box "+otherActivities.otherActivitiesName[0]+","+otherActivities.otherActivitiesName[1]+" should be selected.");
		}
	}
}
