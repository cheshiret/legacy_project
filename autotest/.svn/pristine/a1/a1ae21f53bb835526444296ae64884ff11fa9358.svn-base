package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.quota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Search quota by different criteria and verify the search result in Quota list
 * @Preconditions:
 * @SPEC: 
 * Search Criteria_ Status "Active" [TC:044455]
 * Search Criteria_Status "InActive" [TC:044457]
 * Search Criteria_Status "Null" [TC:044458]
 * Search Criteria_Specie" Bear" [TC:044456]
 * Complex Search [TC:044463]
 * Check the Sort of the UI [TC:044464]
 * @LinkSetUp:
 * d_hf_add_hunt_quota:id=160,170,180
 * d_hf_add_hunt:id=420,430,440
 * d_hf_add_lottery_prd:id=120,130
 * d_hf_assi_hunts_to_lottery:id=10,20
 * d_hf_add_species:id=10
 * @Task#: Auto-2066
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class ViewQuotaList extends LicenseManagerTestCase {
	private QuotaInfo quotaActive, quotaInactive, quotaDiffSpecies;
	private LicMgrQuotaListPage quotaListPg = LicMgrQuotaListPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoQuotaListPgFromLotteriesProdListPg();
		
		// Change one quota as Inactive status
		if (quotaListPg.checkQuotaExist(quotaInactive.getDescription())) {
			lm.editQuotaInfo(quotaInactive);
		}
		
		// Search by Active
		quotaListPg.searchQuota(ACTIVE_STATUS, StringUtil.EMPTY);
		this.verifyQuotaSearchResult("active status", true, true, false);
		quotaListPg.verifyQuotaInfo(quotaActive);
		quotaListPg.verifyQuotaInfo(quotaDiffSpecies);
		
		// Search by Inactive
		quotaListPg.searchQuota(INACTIVE_STATUS, StringUtil.EMPTY);
		this.verifyQuotaSearchResult("inactive status", false, false, true);
		quotaListPg.verifyQuotaInfo(quotaInactive); // Blocked by Defect-60354
		
		// Search by one species
		quotaListPg.searchQuota(StringUtil.EMPTY, quotaActive.getSpecie());
		this.verifyQuotaSearchResult("all status and one species", false, true, true);
		
		// Search by two species
		quotaListPg.searchQuota(StringUtil.EMPTY, quotaActive.getSpecie(), quotaDiffSpecies.getSpecie());
		this.verifyQuotaSearchResult("all status and two species", true, true, true);
		
		// Search by Active status and one species
		quotaListPg.searchQuota(ACTIVE_STATUS, quotaActive.getSpecie());
		this.verifyQuotaSearchResult("active status and one species", false, true, false);
		
		// Search by All status and all species
		quotaListPg.searchQuota(StringUtil.EMPTY, StringUtil.EMPTY);
		this.verifyQuotaSearchResult("all status and all species", true, true, true);
		
		// Verify sort order
		this.verifyQuotaSortOrder();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		// Quota with active status, two quota types, two assigned lottery products, and two assigned hunts
		QuotaType quotaType1_1 = new QuotaType();
		quotaType1_1.setQuotaType("ActiveQuotaType1");
		
		QuotaType quotaType1_2 = new QuotaType();
		quotaType1_2.setQuotaType("ActiveQuotaType2");
		
		List<QuotaType> types = new ArrayList<QuotaType> ();
		types.add(quotaType1_1);
		types.add(quotaType1_2);
		
		quotaActive = new QuotaInfo();
		quotaActive.setDescription("ActiveQuotaForSearch");
		quotaActive.setQuotaStatus(ACTIVE_STATUS);
		quotaActive.setSpecie("Pet");
		quotaActive.setQuotaTypes(types);
		quotaActive.setAssignedLotteryPrds("LSQ - LotteryForSearchQuota", "SQL - SearchQuotaLottery");
		quotaActive.setAssignedHunts("SAQ - SearchActiveQuotaHunt", "SA2 - SearchActiveQuotaHunt2");
		quotaActive.setQuotaId(lm.getQuotaIdByDesc(quotaActive.getDescription(), schema));
		
		// Quota with inactive status, one quota type
		QuotaType quotaType2 = new QuotaType();
		quotaType2.setQuotaType("InactiveQuotaType");
		
		List<QuotaType> types2 = new ArrayList<QuotaType> ();
		types2.add(quotaType2);
		
		quotaInactive = new QuotaInfo();
		quotaInactive.setDescription("SearchQuota_Inactive");
		quotaInactive.setQuotaStatus(INACTIVE_STATUS);
		quotaInactive.setSpecie("Pet");
		quotaInactive.setQuotaTypes(types2);
		quotaInactive.setQuotaId(lm.getQuotaIdByDesc(quotaInactive.getDescription(), schema));
		
		// Quota with active status, different species, one quota type, one assigned lottery products, and one assigned hunt
		QuotaType quotaType3 = new QuotaType();
		quotaType3.setQuotaType("SearchQuotaTypeA");
		
		List<QuotaType> types3 = new ArrayList<QuotaType> ();
		types3.add(quotaType3);
		
		quotaDiffSpecies = new QuotaInfo();
		quotaDiffSpecies.setDescription("SearchQuota_DiffSpecies");
		quotaDiffSpecies.setQuotaStatus(ACTIVE_STATUS);
		quotaDiffSpecies.setSpecie("Band-Tailed Pigeons");
		quotaDiffSpecies.setQuotaTypes(types3);
		quotaDiffSpecies.setAssignedLotteryPrds("SQL - SearchQuotaLottery");
		quotaDiffSpecies.setAssignedHunts("SQS - SearchQuotaDiffSpeciesHunt");
		quotaDiffSpecies.setQuotaId(lm.getQuotaIdByDesc(quotaDiffSpecies.getDescription(), schema));
	}
	
	private void verifyQuotaSearchResult(String msg, boolean diffSpeQuotaExist, boolean activeQuotaExist, boolean inactiveQuotaExist) {
		quotaListPg.verifyQuotaExist(quotaDiffSpecies.getDescription(), diffSpeQuotaExist);
		quotaListPg.verifyQuotaExist(quotaActive.getDescription(), activeQuotaExist);
		quotaListPg.verifyQuotaExist(quotaInactive.getDescription(), inactiveQuotaExist);
		logger.info("Verify search result correctly when search by " + msg);
	}
	
	private void verifyQuotaSortOrder() {
		List<Integer> orders = new ArrayList<Integer>();
		orders.add(quotaListPg.getSpeciesRowIndex(quotaDiffSpecies.getSpecie()));
		orders.add(quotaListPg.getQuotaRowIndex(quotaDiffSpecies.getDescription()));
		orders.add(quotaListPg.getSpeciesRowIndex(quotaActive.getSpecie()));
		orders.add(quotaListPg.getQuotaRowIndex(quotaActive.getDescription()));
		orders.add(quotaListPg.getQuotaRowIndex(quotaInactive.getDescription()));
		List<Integer> sortedList = new ArrayList<Integer>();
		sortedList.addAll(orders);
		Collections.sort(sortedList);
		if (!orders.equals(sortedList)) {
			throw new ErrorOnPageException("Quota Sort order is wrong!");
		}
		
		logger.info("Verify quota sort order correctly!");
	}
}
