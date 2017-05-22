package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.permit.findpermits;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) 
 * Search permit to BwSearchPanel page to check 
 * 1. 'Permit Type' and 'Entrance' drop down list default value
 * 2. 'Any Destination Zone' doesn't display when selecting permit type has only one entrance 
 * 3. 'Any Destination Zone' display when selecting permit type has more than one entrance 
 * @Preconditions: 
 * @SPEC:
 * 1. Remove of "Any River" option (rec.gov) [TC:043339] 
 * 2. Remove of "Any River" option (rec.gov - multi entrance) [TC:043341] 
 * @Task#: AUTO-1180
 * 
 * @author SWang
 * @Date  Aug 24, 2012
 */
public class RemoveOfAnyRiver extends RecgovTestCase{
	private BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
	private UwpUnifiedSearch permit = new UwpUnifiedSearch();
	private String defaultPermitTypeValue, defaultEntranceValue, selectedPermitType1, selectedPermitType2;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(permit);
		web.gotoFindPermitsPanelFromViewAsListPage(permit.contractCode, permit.parkId);

		//Verify default permit type and entrance
		bwSearchPanel.verifyPermitType(defaultPermitTypeValue);

		//Sara[20140710] Permit park "Desolation" has Itinerary permit type, so in Find Permit panel, Entrance drop down list is hidden default.
//		bwSearchPanel.verifyEntrance(defaultEntranceValue);

		//Doesn't display when selecting permit type has only one entrance 
		this.verifyAnyDesolationZoneOptionDisplays(selectedPermitType1, false);
		//Display when selecting permit type has more than one entrance 
		this.verifyAnyDesolationZoneOptionDisplays(selectedPermitType2, true);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		permit.contractCode = "NRSO";
		permit.parkId = "72203";
		permit.whereTextValue = DataBaseFunctions.getFacilityName(permit.parkId, schema);//"Desolation Wilderness"
		permit.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		selectedPermitType1 = "Auto Cross Country";
		selectedPermitType2 = "Auto Overnight";

		defaultPermitTypeValue = "Select Permit Type";
		defaultEntranceValue = "Any Trail";
	}


	/**
	 * Verify if 'Any Destination Zone' display in 'Entrance' drop down list
	 * @param permitType
	 * @param display  true:display
	 *                 false:doesn't display
	 */
	private void verifyAnyDesolationZoneOptionDisplays(String permitType, boolean display){
		bwSearchPanel.selectPermitType(permitType);
		bwSearchPanel.waitLoading();

		List<String> entrances = bwSearchPanel.getAllEntrance();
		boolean containAnyDesolationZone = entrances.contains(defaultEntranceValue);

		if(display!=(entrances.size()>1 && containAnyDesolationZone)){
			throw new ErrorOnPageException("'Any Desolation Zone should '"+(display?"":"not")+" display.");
		}
		logger.info("Successfully verify 'Any Desolation Zone '"+(display?"":"doesn't")+" display.");
	}
}
