package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.huntchoiceselection;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Verify filters on add hunts page
 * @Preconditions:
 * 1. Make sure the lottery "Lottery with parameter" only has hunts without weapon assigned
 * 2. Make sure the lottery "Lottery with hunts no HL" only has hunts without hunt location assigned
 * 3. Make sure the lottery "Lottery with hunts no Weapon HL" only has hunts without weapon or hunt location assigned
 * 4. Make sure the lottery "Lottery With Mult Hunts" has different hunts assigned
 * @SPEC: 
 * List Available Hunts - Filter (Weapon or Hunt Location group not showing) [TC:056528]
 * List Available Hunts - Filter (Group: Species; Weapon; Hunt Location) [TC:056517]
 * List Available Hunts - Hunts list UI and sorting [TC:056286]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1280
 * d_hf_add_species:id=40,50
 * d_hf_add_hunt_location:id=80
 * d_hf_add_weapon:id=50
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_pricing:id=3822
 * d_hf_assi_pri_to_store:id=1850
 * d_hf_add_prvi_license_year:id=2740
 * d_hf_add_qty_control:id=1830
 * d_hf_add_hunt:id=480,490,500,510,520,530,540,550,560,570,580,590
 * d_hf_add_hunt_license_year:id=510,520,530,540,550,560,570,580,590,600,610,620,640,650,660,1120
 * d_hf_assign_priv_to_hunt:id=360,370,380,390,400,410,420,430,440,450,460,470
 * d_hf_add_hunt_quota:id=210
 * d_hf_add_lottery_prd:id=200,220,230,240
 * d_hf_add_lottery_license_year:id=80,130,140,150
 * d_hf_assign_lottery_to_store:id=80,130,140,150
 * d_hf_add_lottery_quantity_control:id=80,130,140,150
 * d_hf_assi_hunts_to_lottery:id=90,190,200,210
 * @Task#: Auto-1773
 * 
 * @author Lesley Wang
 * @Date  Feb 14, 2014
 */
public class HuntsList_FilterAndSorting extends HFSKWebTestCase {
	private HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
	private String lottery_NoWeapon, lottery_NoHuntLoc, lottery_NoWeaponHuntLoc,
		filterTitle, clearFilterText, speciesFilter, speciesTypeFilter, weaponFilter, huntLocFilter;
	private HuntInfo hunt, huntNotMatchedFilter;
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		
		// Lottery with hunts which has not Weapon setup
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery_NoWeapon);	
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsFilterCategory(true, true, false, true);
		
		// Lottery with hunts which has not Hunt location setup
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery_NoHuntLoc);	
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsFilterCategory(true, true, true, false);
		
		// Lottery with hunts which has not Hunt location setup
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery_NoWeaponHuntLoc);	
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsFilterCategory(true, true, false, false);
		
		// Lottery with hunts which has Weapon/Hunt location setup
		hf.gotoLotteryDetailsPgFromAddHuntsPg();
		hf.gotoLotteryCategoriesListPg(cus);
		hf.gotoLotteryDetailsPgFromCatListPg(lottery.getDescription());	
		hf.applyLotteryAsIndividualToAddHuntsPg();
		this.verifyHuntsFilterCategory(true, true, true, true);
		
		// filter by Species and verify the filter display and result
		this.filterByItemAndVerify(hunt.getSpecie(), speciesFilter, hunt.getHuntCode(), huntNotMatchedFilter.getHuntCode());
		
		// filter by Species sub type and verify the filter display and result
		this.filterByItemAndVerify(hunt.getSpecieSubType(), speciesTypeFilter, hunt.getHuntCode(), huntNotMatchedFilter.getHuntCode());	
		
		// filter by Weapon and verify the filter display and result
		this.filterByItemAndVerify(hunt.getWeapon(), weaponFilter, hunt.getHuntCode(), huntNotMatchedFilter.getHuntCode());

		// filter by Hunt Sub Location and verify the filter display and result
		this.filterByItemAndVerify(hunt.getHuntLocationInfo(), huntLocFilter, hunt.getHuntCode(), huntNotMatchedFilter.getHuntCode());
		
		// Hunts sort by alphabetically by hunt description
		this.verifyHuntsSorting(huntNotMatchedFilter.getDescription(), hunt.getDescription());
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00048@webhftest.com";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4183";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.dateOfBirth = "1986-01-01";
		
		// lottery info
		lottery.setDescription("Lottery With Mult Hunts");
		lottery_NoWeapon = "Lottery with parameter";
		lottery_NoHuntLoc = "Lottery with hunts no HL";
		lottery_NoWeaponHuntLoc = "Lottery with hunts no Weapon HL";
		
		// hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("WLH9");
		hunt.setDescription("Web Lottery Hunt 009");
		hunt.setSpecie("Black Bear");
		hunt.setSpecieSubType("Species Sub Type");
		hunt.setWeapon("Lottery App Hunt Weapon");
		hunt.setHuntLocationInfo("SubLoc Category1: SubLoc CatValue1");
		
		huntNotMatchedFilter = new HuntInfo(); // hunt with different species
		huntNotMatchedFilter.setHuntCode("WLH12");
		huntNotMatchedFilter.setDescription("Web Lottery Hunt 012");
		
		// Filter info
		filterTitle = "Filter Your Hunts";
		clearFilterText = "[x]";
		speciesFilter = "Species";
		speciesTypeFilter = "Species Type";
		weaponFilter = "Weapon";
		huntLocFilter = "Hunt Location";
	}

	private void verifyHuntsFilterCategory(boolean isSpeciesShown, boolean isSubSpeciesShown, boolean isWeaponShown, boolean isHuntLocShown) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Filter Title", filterTitle, huntsPg.getFilterTitle());
		result &= MiscFunctions.compareResult("Species filter shown", isSpeciesShown, huntsPg.isSpeciesFilterCategoryExist());
		result &= MiscFunctions.compareResult("Sub Species filter shown", isSubSpeciesShown, huntsPg.isSubSpeciesFilterCategoryExist());
		result &= MiscFunctions.compareResult("Weapon filter shown", isWeaponShown, huntsPg.isWeaponFilterCategoryExist());
		result &= MiscFunctions.compareResult("Hunt location filter shown", isHuntLocShown, huntsPg.isHuntLocationFilterCategoryExist());
		if (!result) {
			throw new ErrorOnPageException("Hunts filter categories are wrong!");
		}
		logger.info("Successfully verify hunts filter categories!");
	}
	
	private void filterByItemAndVerify(String item, String category, String huntExist, String huntNotExist) {
		boolean result = true;
		
		// verify filter item link exist
		result &= MiscFunctions.compareResult(category + " filter link exist", true, huntsPg.isFilterOptionLinkExist(item));
		String filterNum = huntsPg.getFilterItemNum(item);
		
		huntsPg.filterHunts(item);
		// verify filter item after filter
		result &= MiscFunctions.compareResult(category + " filter link exist after click", false, huntsPg.isFilterOptionLinkExist(item));
		result &= MiscFunctions.compareResult(category + " filter selected text", item, huntsPg.getSelectFilterItem());
		result &= MiscFunctions.compareResult(category + " filter result number", filterNum, huntsPg.getNumOfAvailHunts());
		
		// verify filtered result
		result &= MiscFunctions.compareResult("hunt matched the filter exist", true, huntsPg.isHuntExist(huntExist));
		result &= MiscFunctions.compareResult("hunt not matched the filter exist", false, huntsPg.isHuntExist(huntNotExist));
		
		// verify clear filter link
		result &= MiscFunctions.compareString("Clear All filter text", clearFilterText+filterTitle, huntsPg.getFilterTitle());
		if (category.equalsIgnoreCase(speciesFilter)) {
			result &= MiscFunctions.compareString("clear species filter link", clearFilterText+speciesFilter, huntsPg.getClearSpeciesFilterLinkText());
			huntsPg.clearSpeciesFilter();
		} else if (category.equalsIgnoreCase(speciesTypeFilter)) {
			result &= MiscFunctions.compareString("clear species type filter link", clearFilterText+speciesTypeFilter, huntsPg.getClearSpeciesTypeFilterLinkText());
			huntsPg.clearSpeciesTypeFilter();
		} else if (category.equalsIgnoreCase(weaponFilter)) {
			result &= MiscFunctions.compareString("clear weapon filter link", clearFilterText+weaponFilter, huntsPg.getClearWeaponFilterLinkText());
			huntsPg.clearWeaponFilter();
		} else if (category.equalsIgnoreCase(huntLocFilter)) {
			result &= MiscFunctions.compareString("clear hunt location filter link", clearFilterText+huntLocFilter, huntsPg.getClearHuntLocFilterLinkText());
			huntsPg.clearHuntLocFilter();
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunts filter works wrongly!");
		}
		logger.info("Successfully verify hunts filter function!");
	}
	
	private void verifyHuntsSorting(String firstHuntDes, String secHuntDes) {
		huntsPg.showAllHunts();
		int first = huntsPg.getHuntIndex(firstHuntDes);
		int second = huntsPg.getHuntIndex(secHuntDes);
		if (first < 0 || second < 0) {
			throw new ErrorOnPageException("The hunt doesn't exist in add hunts page!");
		}
		if (first > second) {
			throw new ErrorOnPageException("The hunts sort wrongly. " + firstHuntDes + "'s index is " + first + ", " +  secHuntDes + "'s index is " + second);
		}
		logger.info("Successfully verify hunts sort!");
	}
}
