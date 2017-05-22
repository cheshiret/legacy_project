package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class REC_RecreationAreaSearch extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_RecreationAreaSearch</b>
	 * Generated     : <b>Jul 22, 2009 4:49:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 * Note: Update expected recreation area name for DEFECT-33208 
	 */
	private String email, pw, url;

	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, pw);

		if(!bd.isUnifiedSearch){
			if (!web.gotoRecreationAreaDetailsPg(bd)) {
				throw new ItemNotFoundException("The area name in detail page is NOT the same!");
			}
		}else{
			web.gotoHomePage();
			this.gotoViewAsListPage(bd);

			this.gotoRecreationAreaDetailsPg();
			this.verifyAreaNameInDetailPg(bd.whereTextValue, bd.stateCode);
		}

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
			email = TestProperty.getProperty(env+".recgov.email");
			pw = TestProperty.getProperty(env+".recgov.pw");
		} else {
			email = web.getNextEmail();
			pw = TestProperty.getProperty("web.login.pw");
		}
		url = TestProperty.getProperty(env + ".web.recgov.url");
		//For none unified search
		bd.state = "Alaska";
		bd.activity = "Biking";
		bd.isUnifiedSearch = true;

		//For unified search
		bd.whereTextValue = "Alaska Maritime National Wildlife Refuge";
		bd.stateCode = "AK";

		bd.interestInValue = "Other Activities";
		bd.otherActivitiesName = new String[]{"Visitor Center"};

		bd.contractCode = "NRSO";
		bd.parkId = "1258";
	}

	private void gotoRecreationAreaDetailsPg(){
		RecgovViewAsListPage parkViewAsListPg = RecgovViewAsListPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		parkViewAsListPg.clickParkName(bd.whereTextValue);
		recreationAreaDetailsPg.waitLoading();
	}

	private void verifyAreaNameInDetailPg(String recreationAreaName, String stateCode){
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		recreationAreaDetailsPg.verifyAreaNameAndRelatedCode(recreationAreaName, stateCode);
	}
}
