package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: verify search using alias name, the target facility name displayed with "[matched on "JLGR"]"; which JLGR is alias name.
 * 2: verify search using park name, the target facility name DON't display with "[matched on "JLGR"]"; which JLGR is alias name.
 * @Preconditions:
 * @SPEC: Story S
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 13, 2011
 */
public class AliasMatchingFacility extends RecgovTestCase {
	
	private String aliasName;
	public void execute() {
		web.invokeURL(url);	
		//1: verify target result when there is only one facility match with the given alias name search data
		web.gotoUnifiedSearchSuggestionOrResultPage(aliasName, false);		
		this.verifyFacilityViewHeaderInfo(bd.whereTextValue, aliasName);
		
		//2: verify target result when there is only one facility match with the given facility name search data
		//need to invoke URL again to clear the cache.
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifyFacilityViewHeaderInfo(bd.whereTextValue, null);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		//(From Lisha)Since we have spelling check for auto-complete list, \ufffdANPT\ufffd has a suggestion from spelling check. 
		//So \ufffdANPT\ufffd is not a sample data for this test case anymore. Please use \ufffdJLGR\ufffd, \ufffdCG15\ufffd, \ufffdpfpa\ufffd or \ufffdBSPR\ufffd for \ufffdsingle exactly matching on alias\ufffd
		aliasName = "JLGR";
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "JUNIPER LAKE GROUP";
		bd.contractCode = "NRSO";
		bd.parkId = "75409";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}
	
	/**
	 * verify the view as list page, the Target facility's header info display in the certain format.
	 * 1:if there is only one facility match with the alias info we entered, the return format should be:FIERY FURNACE TOUR - ARCHES, Utah [matched on "ANPT"]
	 * 2:if there is only one facility match with the facility name we entered, the return format should be(without mached on message): FIERY FURNACE TOUR - ARCHES, Utah
	 * @param facilityName
	 * @param matchedOnName
	 */
	private void verifyFacilityViewHeaderInfo(String facilityName,String matchedOnName){
		RecgovViewAsListPage viewListPg = RecgovViewAsListPage.getInstance();
		String actualHeaderText = viewListPg.getFirstFacilityViewHeader();
		
		if(matchedOnName ==null ||matchedOnName.length() ==0 ){
			if (actualHeaderText.startsWith(facilityName.toUpperCase()) && !actualHeaderText.contains("matched on")){
				logger.info("verify facility view header text and format with only one facility name match successful");
			}else{
				//expect format should be: FIERY FURNACE TOUR - ARCHES, Utah 
				throw new ErrorOnPageException("The expect first facility view header text is incorrect.");
			}
		}else{
			if (actualHeaderText.startsWith(facilityName.toUpperCase()) && actualHeaderText.contains("[matched on \"" + matchedOnName + "\"]")){
				logger.info("verify facility view header text and format with only one alias name match successful");
			}else{
				//expect format should be: FIERY FURNACE TOUR - ARCHES, Utah [matched on "ANPT"]
				throw new ErrorOnPageException("The expect first facility view header text is incorrect.");
			}
		}
	}

}
