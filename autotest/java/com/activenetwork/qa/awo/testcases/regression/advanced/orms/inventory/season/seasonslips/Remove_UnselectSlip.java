/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.season.seasonslips;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSlipsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify unassgin slip to season work flow.don't selected any slip and click unassign.
 * @Preconditions: 
 * @LinkSetUp: 
 * @SPEC: 	TC:043022
 * @Task#: Auto-1470
 * @author szhou
 * @Date  Mar 04, 2013
 */
public class Remove_UnselectSlip extends InventoryManagerTestCase {
	private String facilityID, message;

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.goToBookingRulePg();
		// clean up
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				season.m_DisplayName);

		// Add a new season
		season.m_SeasonID = im.addNewSeason(season, false);
		// unassign slip
		this.gotoSeasonSlipDetailPage(season);
		this.unassginSeasonSlipWithoutSelectSlip();
		this.verifyUnassginSuccessful(message);

		// clean up
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				season.m_DisplayName);
		im.logoutInvManager();

	}
	
	private void gotoSeasonSlipDetailPage(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		seasonPg.searchSeason(season);
		seasonPg.clickSeasonID(season.m_SeasonID);
		seasonDetailsPg.waitLoading();
		seasonDetailsPg.clickSeasonSlipsTab();
		seasonSlipsPg.waitLoading();
	}

	private void unassginSeasonSlipWithoutSelectSlip() {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();
		
		seasonSlipsPg.clickUnassign();
		ajax.waitLoading();
		seasonSlipsPg.waitLoading();
	}

	private void verifyUnassginSuccessful(String message) {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		String actual = seasonSlipsPg.getMessage();
		if (!message.equals(actual)) {
			throw new ErrorOnPageException("Unassgin warning message", message,
					actual);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login information
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552903"; // Jordan Lake State Rec Area

		// Season information
		season.m_PrdCategory = "Slip"; // This is the check point, please don't change
		season.m_SeasonType = "Peak";
		season.m_StartDate = DateFunctions.getDateAfterToday(2005);
		season.m_EndDate = DateFunctions.getDateAfterToday(2006);
		season.m_Loop = "All";
		season.m_SiteType = "All";
		season.m_DisplayName = "RemoveSlipsUnselectedSlip";
		message="No slip was selected. Please select a slip to assign to or unassign from the season.";
	}

}
