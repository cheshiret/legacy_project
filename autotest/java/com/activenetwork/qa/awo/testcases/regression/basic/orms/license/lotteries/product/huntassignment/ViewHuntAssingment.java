package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.product.huntassignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.HuntAssignmentAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: View hunt assignment
 * @Preconditions:
 * 1. The lottery product exists.
 * 2. The hunt exists.
 * @SPEC: 
 * View Hunt List - UI [TC:044948]
 * View Privilege Lottery Hunt List - UC [TC:053735]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=50
 * d_hf_add_hunt:id=340,350,360
 * @Task#: Auto-2062
 * 
 * @author Lesley Wang
 * @Date  Jan 17, 2014
 */
public class ViewHuntAssingment extends LicenseManagerTestCase {
	private Data<HuntAssignmentAttr> huntAssignment, huntAssignment2, huntAssignment3;
	private LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		lm.deleteHuntAssignment(schema, StringUtil.EMPTY, lotteryPrd.getCode());
		
		// Update Hunt Info firstly to avoid data changed
		lm.loginLicenseManager(login);
		lm.gotoHuntsListPg();
		lm.editHuntDetailsFromHuntsListPg(hunt);
		
		// Assign the hunt to the lottery product
		lm.gotoLotteriesProductListPgFromLotteriesHuntsListPg();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		this.assignHuntToLottery(huntAssignment, huntAssignment2, huntAssignment3);

		// filter by Active and verify the result
		huntsPg.filterHuntAssignments(ACTIVE_STATUS);
		huntsPg.verifyHuntAssignmentInfo(huntAssignment, huntAssignment2, huntAssignment3);
		
		// verify the result sort
		this.verifyHuntAssignmentSortOrder();
		
		// change one to inactive
		lm.dectivateHuntAssignment(HuntAssignmentAttr.AssignID.getStrValue(huntAssignment));
		HuntAssignmentAttr.Status.setValue(huntAssignment, "No");
		
		huntsPg.filterHuntAssignments(INACTIVE_STATUS);
		huntsPg.verifyHuntAssignmentInfo(huntAssignment);
		huntsPg.verifyHuntAssignmentExist(new String[] {HuntAssignmentAttr.AssignID.getStrValue(huntAssignment2), 
				HuntAssignmentAttr.AssignID.getStrValue(huntAssignment3)}, 
				new Boolean[] {false, false});
		
		// filter by Active and verify the inactive record not shown.
		huntsPg.filterHuntAssignments(ACTIVE_STATUS);
		huntsPg.verifyHuntAssignmentExist(new String[] {HuntAssignmentAttr.AssignID.getStrValue(huntAssignment2), 
				HuntAssignmentAttr.AssignID.getStrValue(huntAssignment3), 
				HuntAssignmentAttr.AssignID.getStrValue(huntAssignment)}, 
				new Boolean[] {true, true, false});
		
		// filter by Empty value and verify all records are shown.
		huntsPg.filterHuntAssignments(StringUtil.EMPTY);
		huntsPg.verifyHuntAssignmentExist(new String[] {HuntAssignmentAttr.AssignID.getStrValue(huntAssignment2), 
				HuntAssignmentAttr.AssignID.getStrValue(huntAssignment3), 
				HuntAssignmentAttr.AssignID.getStrValue(huntAssignment)}, 
				new Boolean[] {true, true, true});
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//lottery product info
		lotteryPrd.setCode("PL2");
			
		//hunt assignment info - with sub species, location, date period, and weapon
		huntAssignment = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.Status.setValue(huntAssignment, "Yes");
		HuntAssignmentAttr.HuntCode.setValue(huntAssignment, "VHA");
		HuntAssignmentAttr.HuntDes.setValue(huntAssignment, "ViewHuntAssignment");
		HuntAssignmentAttr.HuntSpecies.setValue(huntAssignment, "Pet");
		HuntAssignmentAttr.HuntSpeciesSubType.setValue(huntAssignment, "CAT - Miaomiaojiao");
		HuntAssignmentAttr.HuntWeapon.setValue(huntAssignment, "AH1 - Add hunt test");
		HuntAssignmentAttr.HuntLoc.setValue(huntAssignment, "HL1-HuntLocation1");
		HuntAssignmentAttr.HuntDatePeriod.setValue(huntAssignment, "HD1 - HuntDatePeriod1");
		
		// Hunt info - same as huntAssignment info
		hunt.setHuntCode("VHA");
		hunt.setDescription("ViewHuntAssignment");
		hunt.setAllowedApplicants("Individual");
		hunt.setSpecie("Pet");
		hunt.setSpecieSubType("CAT - Miaomiaojiao");
		hunt.setWeapon("AH1 - Add hunt test");
		hunt.setHuntLocationInfo("HL1-HuntLocation1");
		hunt.setHuntDatePeriodInfo("HD1 - HuntDatePeriod1");
		
		//hunt assignment info - without sub species, location, date period, or weapon
		huntAssignment2 = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.Status.setValue(huntAssignment2, "Yes");
		HuntAssignmentAttr.HuntCode.setValue(huntAssignment2, "AHL");
		HuntAssignmentAttr.HuntDes.setValue(huntAssignment2, "AssignHuntToLottery");
		HuntAssignmentAttr.HuntSpecies.setValue(huntAssignment2, "Pet");
		
		huntAssignment3 = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.Status.setValue(huntAssignment3, "Yes");
		HuntAssignmentAttr.HuntCode.setValue(huntAssignment3, "EHA");
		HuntAssignmentAttr.HuntDes.setValue(huntAssignment3, "EditHuntAssignment");
		HuntAssignmentAttr.HuntSpecies.setValue(huntAssignment3, "Pet");
	}

	@SuppressWarnings("unchecked")
	private void assignHuntToLottery(Data<HuntAssignmentAttr>... assignmentInfos) {
		for (Data<HuntAssignmentAttr> assignmentInfo : assignmentInfos) {
			lm.assignHuntToLotteryFromLotteryDetailsPg(HuntAssignmentAttr.HuntCode.getStrValue(assignmentInfo));
			String assignID = lm.getHuntAssignmentID(schema, HuntAssignmentAttr.HuntCode.getStrValue(assignmentInfo), lotteryPrd.getCode());
			HuntAssignmentAttr.AssignID.setValue(assignmentInfo, assignID);
		}
	}
	
	private void verifyHuntAssignmentSortOrder() {
		List<String> assignmentList = new ArrayList<String>();
		assignmentList.add(HuntAssignmentAttr.HuntCode.getStrValue(huntAssignment));
		assignmentList.add(HuntAssignmentAttr.HuntCode.getStrValue(huntAssignment2));
		assignmentList.add(HuntAssignmentAttr.HuntCode.getStrValue(huntAssignment3));
		Collections.sort(assignmentList);
		List<String> actualList = huntsPg.getAllHuntCodes();
		MiscFunctions.compareStringList("Hunt Assignment List Sort Order", assignmentList, actualList);
	}

}
