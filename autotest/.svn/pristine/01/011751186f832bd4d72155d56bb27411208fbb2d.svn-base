package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Verify the top results matching the target facility
 * 2. Verify the child permit facilities (no distance showing for child facilities) displaying as required
 * 3. Verify "Results part of 'parent name'" showing under the target parent facility
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class ResultWithChildFacility extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String childPermitsType, childPermitID, ridbSchema, nearHeaderText; 
	private List<String> parkNames = new ArrayList<String>();
	private List<String> childPermits = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		
		parkNames = searchResult.getAllParkNames();
		searchResult.gotoFirstPage();
		
		//Verify all child permits displays in view as list page
		childPermits = searchResult.getChildFacilities(bd.contractCode, bd.parkId, childPermitsType);
		searchResult.verifyAllParkNames(parkNames, childPermits, false);
		
		//Verify Child permit park with parent info
		this.verifyChildPermitWithParentInfo();
		
		//Verify result near header
		searchResult.verifyResultNearHeaderText(nearHeaderText);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.contractCode = "NRSO";
		bd.parkId = "2991";
		bd.whereTextValue = DataBaseFunctions.getRecreationAreaName(bd.parkId, ridbSchema); //YOSEMITE NATIONAL PARK , CA 
		bd.interestInValue = UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN;
		
		childPermitsType = "Permits and Wilderness";
		nearHeaderText = "All Permits & Wilderness Facilities [ * in straight line, not driving distance ]";
	}

	/**
	 * Verify Child permit park with parent info
	 */
	private void verifyChildPermitWithParentInfo(){
		for(int i=0; i<childPermits.size(); i++){
			childPermitID = DataBaseFunctions.getFacilityID(childPermits.get(i), schema);
			boolean withParent = searchResult.isParkWithPartOf(bd.contractCode, childPermitID, 	bd.whereTextValue);
			
			if(!withParent){
				throw new ErrorOnPageException("Tour:"+childPermits.get(i)+" should include parent:"+bd.whereTextValue+" info.");
			}else logger.info("Successfully tour:"+childPermits.get(i)+" includes parent:"+bd.whereTextValue+" info.");
		}
	}
}
