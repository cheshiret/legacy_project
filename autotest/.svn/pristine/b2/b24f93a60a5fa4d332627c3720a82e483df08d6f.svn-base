package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Facility does not associate with Parent Facility ( this is from the Probable Match sheet of Possible RIDB Match document )
 * Verify ASSATEAGUE ISLAND NATIONAL SEASHORE displays as Target result
 * Verify region name displays as part of Assateague Island National Seashore
 * Verify the name displays as plain text
 * Verify there is no onclick action
 * @Preconditions:
 * The facility we choose don't have parent facility info.
 * if one day it's has a parent facility, we need to find another facility which don't have parent facility info for this test case.
 * Use the following script to get a no parent facility.
 * The results with \ufffdnone\ufffd  in \ufffdparent_name\ufffd column are what we need. They display region name on search results page.
			 alter session set current_schema=qa301_NOV03_NRRS;
			
			(select a.name as facility_name, b.dscr as region_name, 'none' as parent_name from d_loc a, d_loc b  where a.delete_ind=0 and a.status_id=1 and
			a.cd not like '%|800|%' and a.level_num=40 and instr(a.cd, b.id)>0 and ((b.level_num=35 and b.cd like '%|70902|%') or
			(b.level_num=32 and b.cd not like '%|70902|%')) and 0=
			(select count(0) from qa301_nov03_ra_ridb.recareafacilitymapping c where c.facilityid=a.id)) 
			union all
			(select a.name as facility_name, b.dscr as region_name, recareaname as parent_name from d_loc a, d_loc b, qa301_nov03_ra_ridb.recarea c,
			qa301_nov03_ra_ridb.recareafacilitymapping d  where
			a.cd not like '%|800|%' and a.level_num=40 and instr(a.cd, b.id)>0 and ((b.level_num=35 and b.cd like '%|70902|%') or
			(b.level_num=32 and b.cd not like '%|70902|%')) and d.facilityid=a.id and d.recareaid=c.recareaid) order by facility_name

 * @SPEC: Story C
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 19, 2011
 */
public class NoAssociateParentFacility extends RecgovTestCase{
	String expectParentName;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifyFacilityInfo();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		
		bd.whereTextValue = "ASSATEAGUE ISLAND NATIONAL SEASHORE";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.parkId = "70989";
		bd.contractCode = "NRSO";
		expectParentName = "Assateague Island National Seashore";
	}
	
	private void verifyFacilityInfo(){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		//1: verify search park display as target result
		searchResult.verifyFirstParkName(bd.whereTextValue);
		//2: Verify region name displays as part of Assateague Island National Seashore
		String parentName = searchResult.getParentNameByParkName(bd.whereTextValue);
		if(!parentName.equalsIgnoreCase(expectParentName)){
			logger.error("The expect  parent name is:" + expectParentName);
			logger.error("The current parent name is:" + parentName);
			throw new ErrorOnPageException("The parent name displayed on the page didn't meet the expect value.");
		}
		logger.info("Verify parent name successfully for park:" + bd.whereTextValue);
		//3: Verify the name displays as plain text, there is no onclick action
		
		if(searchResult.checkParentNameClickable(bd.contractCode, bd.parkId)){
			throw new ErrorOnPageException("The parent name should NOT be displayed as a hyperlink.");			
		}else{
			logger.info("verify parent name display as plain text and no on click action successfully.");
		}
	}
}
