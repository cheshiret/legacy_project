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
 * @Description: This test case used to verify slip product list in the assigning page.
 *               There has one season with Dock specific and Slip type specific  
 * @Preconditions: need Active non-deleted slip-specific ;<ANDS>
 *                      Active non-deleted NSS parent slips with active child slips;<ANDN>
 *                      Active non-deleted NSS parent slips with inactive child slips;<CNDNA>
 * @LinkSetUp: d_inv_add_slip 1080 <ANDS>
 *             d_inv_add_slip 1090 <ANDN>
 *             d_inv_add_slip 1100 <CNDNA>
 *             d_inv_add_nss_slip_child 70 <ANC>
 *             d_inv_add_nss_slip_child 80 <ANCI>
 * @SPEC: 	TC:044726
 * @Task#: Auto-1470
 * @author szhou
 * @Date  Mar 11, 2013
 */
public class Add_SchdWithSpecificDockSpecificSlip extends
InventoryManagerTestCase{
	private String facilityID;

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
		// verify product list
		this.gotoSeasonSlipDetailPage(season);
		this.verifyProductListDisplay(season.prd_num[0], season.m_Loop , true);
		this.verifyProductListDisplay(season.prd_num[1], season.m_Loop , false);
		this.verifyProductListDisplay(season.prd_num[2], season.m_Loop , true);

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

	private void verifyProductListDisplay(String slip, String dock,
			boolean isdisplay) {
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();

		seasonSlipsPg.searchSlipBySlipNum(slip, dock);
		if(isdisplay!=seasonSlipsPg.isSlipListInSlipTable()){
			throw new ErrorOnPageException(isdisplay ? "Slip should display in the slip table...":"Slip should not display in the slip table...");
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
		season.m_StartDate = DateFunctions.getDateAfterToday(2044);
		season.m_EndDate = DateFunctions.getDateAfterToday(2045);
		season.m_Loop = "AutoDock";
		season.m_SiteType = "Full attributes";
		season.m_DisplayName = "AddSeasonSlipsProductListAA";
		season.prd_num = new String[] { "ANDS","ANDN","CNDNA"};

	}
}
