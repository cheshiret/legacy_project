package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.season.seasonslips;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSlipsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This test case used to verify slip search criteria in the assigning page.
 * @Preconditions: need Active non-deleted slip-specific ;<ANDS>
 * @LinkSetUp:  d_inv_add_slip 1080 <ANDS>
 * @SPEC: 	TC:043008
 * @Task#: Auto-1470
 * @author szhou
 * @Date  Mar 12, 2013
 */
public class Add_SlipFliter extends InventoryManagerTestCase {
	private String facilityID;
	private InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
			.getInstance();

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
		// assign slip
		this.gotoSeasonSlipDetailPage(season);
		this.assginSeasonSlip(season);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		// search by Slip Number
		this.gotoSeasonSlipDetailPage(season);
		season.m_Slip_searchslipnum="A";
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Slip Number", season.m_Slip_searchslipnum);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		
		// search by Show
		this.gotoSeasonSlipDetailPage(season);
		season.cleanUpSearchData();
		season.m_searchStatus="Assigned Slips";
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Assigned", YES_STATUS);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		// search by Parent Dock/Area
		season.cleanUpSearchData();
		season.m_Slip_searchDock=season.m_Loop;
		this.gotoSeasonSlipDetailPage(season);
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Dock/Area", season.m_Loop);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		// search by Slip Reservation Period Type
		season.cleanUpSearchData();
		season.m_Slip_searchResPeriodType=season.m_Slip_ResPeriodType[0];
		this.gotoSeasonSlipDetailPage(season);
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Slip Res. Per. Type", season.m_Slip_ResPeriodType[0]);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		// search by Shared
		season.cleanUpSearchData();
		season.m_Slip_searchShared=season.m_Slip_Shared[0];
		this.gotoSeasonSlipDetailPage(season);
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Shared", season.m_Slip_Shared[0]);
		this.gotoSeasonPageFromSeasonSlipPage();
		
		// search by multiple conditions
		this.gotoSeasonSlipDetailPage(season);
		season.m_searchStatus="Assigned Slips";
		season.m_Slip_searchDock=season.m_Loop;
		season.m_Slip_searchResPeriodType=season.m_Slip_ResPeriodType[0];
		seasonSlipsPg.searchSlipsByAllField(season);
		seasonSlipsPg.verifySlipColInfo("Assigned", YES_STATUS);
		seasonSlipsPg.verifySlipColInfo("Dock/Area", season.m_Loop);
		seasonSlipsPg.verifySlipColInfo("Slip Res. Per. Type", season.m_Slip_ResPeriodType[0]);
		seasonSlipsPg.verifySlipColInfo("Shared", season.m_Slip_Shared[0]);
	
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
		season.m_StartDate = DateFunctions.getDateAfterToday(2048);
		season.m_EndDate = DateFunctions.getDateAfterToday(2049);
		season.m_Loop = "AutoDock";
		season.m_SiteType = "All";
		season.m_DisplayName = "AddSeasonSlipsForFilter";
		season.prd_num = new String[] { "ANDS" };
		season.m_Slip_ResPeriodType = new String[] { SLIP_RESERVATION_TYPE_TRANSIENT };
		season.m_Slip_Shared = new String[] { "No" };

	}

}
