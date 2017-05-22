package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.huntassignment;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAssignHuntWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Assign Hunt Widget UI
 * @Preconditions:
 * 1. The lottery product exists.
 * 2. The hunt exists.
 * @SPEC: Assign Hunts Popup [TC:053736]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=70
 * d_hf_add_hunt:id=360,340,330,370
 * @Task#: Auto-2062
 * 
 * @author Lesley Wang
 * @Date  Jan 20, 2014
 */
public class VerifyAssignHuntUI extends LicenseManagerTestCase {
	private HuntInfo hunt2, hunt3, hunt4;
	private LicMgrAssignHuntWidget assignHunt = LicMgrAssignHuntWidget.getInstance();
	private LicMgrLotteryHuntsPage huntPg = LicMgrLotteryHuntsPage.getInstance();
	
	@Override
	public void execute() {
		lm.deleteHuntAssignment(schema, StringUtil.EMPTY, lotteryPrd.getCode());
		
		// Update Hunt Info
		lm.loginLicenseManager(login);
		lm.gotoHuntsListPg();
		this.updateHuntsInfo(hunt, hunt2, hunt3);
		
		// Go to Lottery product details page
		lm.gotoLotteriesProductListPgFromLotteriesHuntsListPg();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		lm.openAssignHuntWidgetFromLotteryDetailsPg();
		
		// Verify only the hunt with the same species as lottery product can be shown
		this.verifyHuntSpecies();
		assignHunt.verifyHuntExist(hunt4, false);
		
		// Verify Hunt Info in Assign Hunt UI
		assignHunt.verifyHuntInfo(hunt, hunt2, hunt3);
		this.closeAssignHuntWidget();
		
		// Select one hunt but click Cancel, verify no hunt assignment created.
		lm.assignHuntToLotteryFromLotteryDetailsPg(false, hunt.getHuntCode());
		lm.openAssignHuntWidgetFromLotteryDetailsPg();
		assignHunt.verifyHuntExist(hunt, true);
		this.closeAssignHuntWidget();
		
		// Select one hunt and click OK, verify the assigned hunt not shown in widget.
		lm.assignHuntToLotteryFromLotteryDetailsPg(hunt.getHuntCode());
		lm.openAssignHuntWidgetFromLotteryDetailsPg();
		assignHunt.verifyHuntExist(hunt, false);
		this.closeAssignHuntWidget();
		
		// Select All Hunts and click OK, verify no hunts displayed in widget
		this.assignAllHuntsToLottery();
		lm.openAssignHuntWidgetFromLotteryDetailsPg();
		this.verifyNoHuntExist();
		this.closeAssignHuntWidget();
		
		lm.logOutLicenseManager();
		
		lm.deleteHuntAssignment(schema, StringUtil.EMPTY, lotteryPrd.getCode());
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//lottery product info
		lotteryPrd.setCode("PL4"); // has two species: Pet and Ducks
		lotteryPrd.setSpecies(new String[] {"Pet", "Ducks"});
		
		//hunt info - with sub species, location, date period, and weapon
		hunt.setHuntCode("VHA");
		hunt.setDescription("ViewHuntAssignment");
		hunt.setAllowedApplicants("Individual");
		hunt.setSpecie("Pet");
		hunt.setSpecieSubType("CAT - Miaomiaojiao");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");
		
		//hunt info
		hunt2 = new HuntInfo();
		hunt2.setHuntCode("AHL");
		hunt2.setDescription("AssignHuntToLottery");
		hunt2.setSpecie("Pet");
		hunt2.setAllowedApplicants("Individual");
		
		hunt3 = new HuntInfo();
		hunt3.setHuntCode("VAU");
		hunt3.setDescription("VerifyAssignHuntUI");
		hunt3.setSpecie("Ducks");
		hunt3.setAllowedApplicants("Individual");
		
		hunt4 = new HuntInfo();
		hunt4.setHuntCode("RDQ");
		hunt4.setDescription("ReserveDrawLotteryQuotaHunt");
		hunt4.setSpecie("Band-Tailed Pigeons");
		
	}

	private void verifyHuntSpecies() {
		List<String> species = assignHunt.getAllSpecies();
		String[] lotterySpeciesArray = lotteryPrd.getSpecies();
		String lotterySpecies = "";
		for (int i = 0; i < lotterySpeciesArray.length; i++) {
			lotterySpecies += lotterySpeciesArray[i] + " ";
		}
		for (int i = 0; i < species.size(); i++) {
			String spe = species.get(i);
			if (!lotterySpecies.contains(spe)) {
				throw new ErrorOnPageException("The Species - " + spe + " should not be shown in Assign Hunt Widget!");
			}
		}
		logger.info("Verify Hunt Species correctly!");
	}
	
	private void closeAssignHuntWidget() {
		assignHunt.clickCancel();
		ajax.waitLoading();
		huntPg.waitLoading();
	}
	
	private void assignAllHuntsToLottery() {
		lm.openAssignHuntWidgetFromLotteryDetailsPg();
		assignHunt.selectAllCheckBox();
		assignHunt.clickOK();
		ajax.waitLoading();
		huntPg.waitLoading();
	}
	
	private void verifyNoHuntExist() {
		int numOfHunts = assignHunt.getNumOfHunts();
		if (numOfHunts != 0) {
			throw new ErrorOnPageException("There should be no hunts in the assign hunt widget!");
		}
		logger.info("Verify no hunts displayed in the assign hunt widget correctly!");
	}
	
	private void updateHuntsInfo(HuntInfo...huntInfos) {
		for (HuntInfo hunt : huntInfos) {
			lm.editHuntDetailsFromHuntsListPg(hunt);
		}
	}
}
