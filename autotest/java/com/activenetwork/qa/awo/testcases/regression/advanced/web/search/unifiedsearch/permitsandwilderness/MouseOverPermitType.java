package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1.Verify the top result displayed as required
 * 2.Mouse over the permit type links values
 * (compare with the description showing under 'Looking for' drop down list on 'Permit Area Facility Details' page)
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class MouseOverPermitType extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String[] PermitSearchPanelTypesDescription, permitTypesDiscription, permitTypes = null;

	public void execute() {
		web.invokeURL(url);

		this.gotoViewAsListPage(bd);
		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);

		this.getPermitTypesDescriptionFromSearchPanel();
		this.verifyPermitTypesDescriptionInParkViewAsList();

	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.whereTextValue = "Boundary Waters Canoe Area Wilderness (Reservations)";//Boundary Waters Canoe Area Wilderness (Reservations), Superior National Forest, MN

		bd.parkId = "72600";
		bd.contractCode = "NRSO";
	}

	private void getPermitTypesDescriptionFromSearchPanel(){
		BwSearchPanel permitSearchPanel = BwSearchPanel.getInstance();
		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstParkPermitTypes(0);
		permitSearchPanel.waitLoading();

		PermitSearchPanelTypesDescription = permitSearchPanel.getAllPermitTypesDescription();
	}

	private void verifyPermitTypesDescriptionInParkViewAsList(){
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		headerBar.clickHomeLink();
//		searchResult.waitExists();
		gotoViewAsListPage(bd);
		for(int i=0; i<permitTypes.length; i++){
			permitTypesDiscription = searchResult.getFirstParkPermitTypesDescription(permitTypes);
		}

		if(permitTypesDiscription.length==PermitSearchPanelTypesDescription.length){
			for(int j=0; j<permitTypesDiscription.length; j++){
				if(!permitTypesDiscription[j].equals(permitTypesDiscription[j])){
					throw new ErrorOnDataException("The actual permit type description "+PermitSearchPanelTypesDescription[j]+" " +
							"does equal to the expected one "+permitTypesDiscription[j]);
				}
			}
		}else throw new ErrorOnDataException("The expected length of list should be "+PermitSearchPanelTypesDescription.length);
	}
}
