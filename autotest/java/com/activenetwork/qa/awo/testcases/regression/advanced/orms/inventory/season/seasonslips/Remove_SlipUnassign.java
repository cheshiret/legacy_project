/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.season.seasonslips;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSlipsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify unassgin slip to season work flow.slip don't assgin to season.
 * @Preconditions: need an active slip:SFAS,ATS
 * @LinkSetUp: d_inv_add_slip 1050 <SFAS>
 *             d_inv_add_slip 250 <ATS>
 * @SPEC: 	TC:043023
 * @Task#: Auto-1470
 * @author szhou
 * @Date  Mar 04, 2013
 */
public class Remove_SlipUnassign extends InventoryManagerTestCase {
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
		this.unassginSeasonSlipFromSeasonDetailsPg(season);
		this.verifyUnassginSuccessful(message);

		// assign slip
		this.assginSeasonSlip(season);
		// unassign slip
		this.changeSeasonData();
		this.unassginSeasonSlipFromSeasonDetailsPg(season);
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

	private void assginSeasonSlip(SeasonData season) {
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		seasonDetailsPg.clickSeasonSlipsTab();
		seasonSlipsPg.waitLoading();

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

	private void verifyUnassginSuccessful(String message) {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		String actual = seasonSlipsPg.getMessage();
		if (!message.equals(actual)) {
			throw new ErrorOnPageException("Assgin warning message", message,
					actual);
		}
	}

	private void unassginSeasonSlipFromSeasonDetailsPg(SeasonData season) {
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();
		InvMgrConfirmDialogWidget confirmPg = InvMgrConfirmDialogWidget
				.getInstance();
		PagingComponent turningPage = new PagingComponent();

		seasonDetailsPg.clickSeasonSlipsTab();
		seasonSlipsPg.waitLoading();

		if (season.prd_num != null && season.prd_num.length > 0) {
			seasonSlipsPg.searchSlipsByDock(season.m_Loop);
			for (int i = 0; i < season.prd_num.length; i++) {
				seasonSlipsPg.searchSlipBySlipNum(season.prd_num[i], season.m_Loop);
				seasonSlipsPg.selectSlip(season.prd_num[i],
						null, null);
				if (turningPage.firstExists()) {
					turningPage.clickFirst();
					browser.waitExists();
				}
			}
			seasonSlipsPg.clickUnassign();
			ajax.waitLoading();
			Object page = browser.waitExists(confirmPg, seasonSlipsPg);
			if (page == confirmPg) {
				confirmPg.clickOK();
				ajax.waitLoading();
				seasonSlipsPg.waitLoading();
			}
		}
	}

	private void changeSeasonData() {
		season.prd_num = new String[] { "SFAS", "ATS" };
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login information
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		facilityID = "552903"; // Jordan Lake State Rec Area

		// Season information
		season.m_PrdCategory = "Slip"; // This is the check point, please don't
										// change
		season.m_SeasonType = "Peak";
		season.m_StartDate = DateFunctions.getDateAfterToday(2020);
		season.m_EndDate = DateFunctions.getDateAfterToday(2021);
		season.m_Loop = "AutoDock";
		season.m_SiteType = "All";
		season.m_DisplayName = "RemoveSlipsWithSlipUnassgin";
		season.prd_num = new String[] { "SFAS" };
		season.m_Slip_ResPeriodType = new String[] { SLIP_RESERVATION_TYPE_TRANSIENT };
		season.m_Slip_Shared = new String[] { "No" };
		message = "At least one of the selected slip(s) is not assigned to the Season and cannot be unassigned. Please re-select the slips.";

	}

}
