/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitDateRangeAvailablityPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check error messages when search entrance without group size or length of stay
 * @Preconditions: There are 3 permit types with the following setup in Desolation Wilderness Permit
 * permit type = 4 Children, 2 Adults; group size = applicable, quota allocation method = Per Unit
 * permit type = 4 Children, 1 Adult; group size = applicable, quota allocation method = Per Stay
 * permit type = 3 Children, 1 Adult; group size = applicable, quota allocation method = Per Unit/Per Stay
 * @SPEC:  Length of Stay - Search Entrance [TC:056162]
 * @Task#: Auto-1536
 * 
 * @author Lesley Wang
 * @Date  Mar 12, 2013
 */
public class SearchWithInvalidData extends RecgovTestCase {
	
	private RecgovEntranceListPage entranctListPg = RecgovEntranceListPage.getInstance();
	private UwpPermitDateRangeAvailablityPage dateRangePg = UwpPermitDateRangeAvailablityPage.getInstance();
	private String noGroupSizeMsg, noLenOfStayMsg, noLenOfStayRangeMsg;

	public void execute() {
		web.invokeURL(url);
		bw.makePermitToSearchPanel(permit, true);
		logger.info("1. Select the permit type with Per Unit allocation method, and search without group size, then check error message...");
		bw.searchPermitInSearchPanel(permit);
		entranctListPg.verifyWarningMsg(noGroupSizeMsg);

		logger.info("2. Select the permit type with Per Stay allocation method, and search without length of stay, then check error message...");
		permit.permitType = "4 Children, 1 Adult";
		permit.groupSize = "1";
		bw.searchPermitInSearchPanel(permit);
		entranctListPg.verifyWarningMsg(noLenOfStayMsg);
		
		permit.isRange = true;
		bw.searchPermitInSearchPanel(permit);
		dateRangePg.verifyPrompMessage(noLenOfStayRangeMsg);
		
		logger.info("3. Select the permit type with Per Unit/Per Stay allocation method, and search without length of stay, then check error message...");
		permit.permitType = "3 Children, 1 Adult";
		bw.searchPermitInSearchPanel(permit);
		dateRangePg.verifyPrompMessage(noLenOfStayRangeMsg);
		
		permit.isRange = false;
		bw.searchPermitInSearchPanel(permit);
		entranctListPg.verifyWarningMsg(noLenOfStayMsg);
		
		logger.info("4. Select the permit type with Per Unit/Per Stay allocation method, and search without group size, then check error message...");
		permit.permitType = "3 Children, 1 Adult";
		permit.groupSize = "";
		bw.searchPermitInSearchPanel(permit);
		entranctListPg.verifyWarningMsg(noGroupSizeMsg);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		permit.contractCode = "NRSO";
		permit.parkId = "72202"; // Desolation Wilderness Permit
		permit.facility = web.getFacilityName(permit.parkId, schema);
		permit.permitType = "4 Children, 2 Adults";
		permit.isUnifiedSearch = true;
		permit.chooseAnyOfPermitType = false;
		permit.isRange = false;
		permit.setAnyOfGroupSize = false;
		permit.entryDate = DateFunctions.getDateAfterToday(2);
		permit.exitDate = DateFunctions.getDateAfterToday(4);
		
		noGroupSizeMsg = "Please enter the Group Size";
		noLenOfStayMsg = "Please enter the Length of Stay";
		noLenOfStayRangeMsg = "^Please specify Length of Stay.*";
	}
}
