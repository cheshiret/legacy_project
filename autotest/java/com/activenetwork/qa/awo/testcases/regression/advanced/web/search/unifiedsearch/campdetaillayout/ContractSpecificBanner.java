package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campdetaillayout;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/** @Description(P):
 * 1: verify the UI layout details with the following parts:
 * Contract -Specific Banner
 * Campground Name
 * 
 * @Preconditions:
 * @SPEC: Story Q 
 * @Task#: AUTO - 849
 * 
 * @author bzhang
 * @Date  Jan 4, 2012
 */
public class ContractSpecificBanner extends RecgovTestCase{

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		this.verifyUILayOut();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "NORTH PINES";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.stateCode = "CA";
		bd.contractCode ="NRSO";
		bd.parkId = "70927";
	}
	
	private void verifyUILayOut() {
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		
		//1: Verify "Contract-Specific Banner" is displayed as "Facility Details - <facility name>, <state code> - Recreation.gov - [NRSO]" on the top of the Campground Detail page in Browser.
		campGroundDetailPg.verifyContractSpecificBanner(bd.park, bd.stateCode, bd.contractCode);
		//2: Verify "Campground Name" displayed as:		<facility name>, <state code> 
		campGroundDetailPg.verifyCampGroundNameAndRelatedStateCode(bd.park, bd.stateCode);
	}

}
