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
 * @Description: This test case used to verify assgin slip to season work flow.
 *               There has two season with overlapping dates.
 *               assign slip to first season with Slip Res Per Type as 'Transient'
 *               assign slip to second season with Slip Res Per Type as 'Lease' and got error message
 * @Preconditions: need an active slip:SFAS
 * @LinkSetUp: d_inv_add_slip 1050 <SFAS>
 * @SPEC: 	TC:044753
 * @Task#: Auto-1470
 * @author szhou
 * @Date  Mar 05, 2013
 */
public class Add_OverlappingDateWithSlipTypeDiff extends
		InventoryManagerTestCase {
	private String facilityID, message;
	private SeasonData overlapseason = new SeasonData();

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.goToBookingRulePg();
		// clean up
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				season.m_DisplayName);
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				overlapseason.m_DisplayName);

		// Add a new season
		season.m_SeasonID = im.addNewSeason(season, false);
		// assign slip
		this.gotoSeasonSlipDetailPage(season);
		this.assginSeasonSlip(season);
		this.gotoSeasonPageFromSeasonSlipPage();

		// Add another new season which date overlap
		season.m_SeasonID = im.addNewSeason(overlapseason, false);
		// assign slip
		this.gotoSeasonSlipDetailPage(season);
		this.assginSeasonSlip(overlapseason);
		this.verifyAssginSuccessful(message);

		// clean up
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				season.m_DisplayName);
		im.deleteSlipSeasonByDisplayName(schema, facilityID,
				overlapseason.m_DisplayName);
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
	
	
	private void gotoSeasonPageFromSeasonSlipPage() {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		seasonSlipsPg.clickSeasonsTab();
		ajax.waitLoading();
		seasonPg.waitLoading();
	}

	private void assginSeasonSlip(SeasonData season) {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		if (season.prd_num != null && season.prd_num.length > 0) {
			for (int i = 0; i < season.prd_num.length; i++) {
				seasonSlipsPg.searchSlipBySlipNum(season.prd_num[i],
						season.m_Loop);
				seasonSlipsPg
						.selectSlip(season.prd_num[i],
								season.m_Slip_ResPeriodType[i],
								season.m_Slip_Shared[i]);
				seasonSlipsPg.clickAssign();
				ajax.waitLoading();
				seasonSlipsPg.waitLoading();
			}
		}

	}

	private void verifyAssginSuccessful(String message) {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		String actual = seasonSlipsPg.getMessage();
		if (!message.equals(actual)) {
			throw new ErrorOnPageException("Assgin warning message", message,
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
		season.m_StartDate = DateFunctions.getDateAfterToday(2025);
		season.m_EndDate = DateFunctions.getDateAfterToday(2028);
		season.m_Loop = "All";
		season.m_SiteType = "All";
		season.m_DisplayName = "AddSeasonSlipsTransient";
		season.prd_num = new String[] { "SFAS" };
		season.m_Slip_ResPeriodType = new String[] { SLIP_RESERVATION_TYPE_TRANSIENT };
		season.m_Slip_Shared = new String[] { "No" };
		
		overlapseason.m_PrdCategory = "Slip";
		overlapseason.m_SeasonType = "Peak";
		overlapseason.m_StartDate = DateFunctions.getDateAfterToday(2026);
		overlapseason.m_EndDate = DateFunctions.getDateAfterToday(2029);
		overlapseason.m_Loop = "All";
		overlapseason.m_SiteType = "Full attributes";
		overlapseason.m_DisplayName = "AddSeasonSlipsLease";
		overlapseason.prd_num = new String[] { "SFAS" };
		overlapseason.m_Slip_ResPeriodType = new String[] { SLIP_RESERVATION_TYPE_LEASE };
		overlapseason.m_Slip_Shared = new String[] { "No" };
		message = "The following slip(s) are already assigned to other Season Schedules with overlapping dates and a different Slip Reservation Period Type: "
				+ season.prd_num[0] + " Please re-select the slips.";

	}

}
