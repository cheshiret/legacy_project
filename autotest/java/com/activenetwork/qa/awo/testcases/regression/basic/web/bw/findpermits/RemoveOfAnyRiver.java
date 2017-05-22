package com.activenetwork.qa.awo.testcases.regression.basic.web.bw.findpermits;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * In BwSearchPanel page to check 
 * 1. 'Permit Type' and 'Entrance' drop down list default value
 * 2. 'Any Destination Zone' doesn't display when selecting permit type has only one entrance 
 * 3. 'Any Destination Zone' display when selecting permit type has more than one entrance 
 * @Preconditions: Permit type  "Auto Overnight (Commercial)" assign to 2 entrances "02 General Creek" and "DO3376 BackCountryDeso"
 * @SPEC:Remove of "Any River" option (Commercial Permits) [TC:043340] 
 * @Task#: AUTO-1180
 * 
 * @author SWang
 * @Date  Aug 24, 2012
 * 
 * Jane[2014-7-17]:Since facility 'Inyo National Forest - Wilderness Permits' has Itinerary Permit type, there is no entrance dropdown list unless select permit type 
 */
public class RemoveOfAnyRiver extends RecgovTestCase{
	private BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
	private String defaultPermitTypeValue, defaultEntranceValue, selectedPermitType1, selectedPermitType2;
	private String email1, pw1;
	
	public void execute() {
		web.invokeURL(url);
		bw.signInBW(email1, pw1);
		//Verify default permit type and entrance
		bwSearchPanel.verifyPermitType(defaultPermitTypeValue);
		bwSearchPanel.verifyEntrance(defaultEntranceValue);
		bw.signOut();
		
		bw.signInBW(email, pw);
		//Doesn't display when selecting permit type has only one entrance 
		this.verifyAnyDesolationZoneOptionDisplays(selectedPermitType1, false);
		//Display when selecting permit type has more than one entrance 
		this.verifyAnyDesolationZoneOptionDisplays(selectedPermitType2, true);
		bw.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.bw.url");
		email = TestProperty.getProperty("inyonationalforest.coop.email");
		pw = TestProperty.getProperty("inyonationalforest.coop.pwd");
		email1 = TestProperty.getProperty("mtwhitney.coop.email");
		pw1 = TestProperty.getProperty("mtwhitney.coop.pwd");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		selectedPermitType1 = "Auto Cross Country (Commercial)";
		selectedPermitType2 = "Auto Overnight (Commercial)";

		defaultPermitTypeValue = "Select Permit Type";
		defaultEntranceValue = "Any Entrance";
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
			throw new ErrorOnPageException("'Any Desolation Zone' should"+(display?"":" not")+" display.");
		}
		logger.info("Successfully verify 'Any Desolation Zone'"+(display?"":" doesn't")+" display.");
	}
}
