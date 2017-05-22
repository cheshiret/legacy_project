package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.product.huntassignment;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.HuntAssignmentAttr;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrViewHuntAssignmentWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.verification.CheckPoint;
import com.activenetwork.qa.testapi.verification.Checkable;

/**
 * @Description: Edit hunt assignment
 * @Preconditions: 
 * 1. The lottery product exists.
 * 2. The hunt exists.
 * @SPEC: 
 * View Hunt Assignment Detail Popup [TC:044949]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=60
 * d_hf_add_hunt:id=360
 * @Task#: Auto-2062
 * 
 * @author Lesley Wang
 * @Date  Jan 16, 2014
 */
public class EditHuntAssignment extends LicenseManagerTestCase {
	private Data<HuntAssignmentAttr> huntAssignment;
	private CheckPoint cp1;
	private String userName;
	private TimeZone timeZone;
	private LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
	
	@Override
	public void execute() {
		lm.deleteHuntAssignment(schema, hunt.getHuntCode(), lotteryPrd.getCode());
		
		// Assign the hunt to the lottery product
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		lm.assignHuntToLotteryFromLotteryDetailsPg(hunt.getHuntCode());
		String assignID = lm.getHuntAssignmentID(schema, hunt.getHuntCode(), lotteryPrd.getCode());
		HuntAssignmentAttr.AssignID.setValue(huntAssignment, assignID);
		HuntAssignmentAttr.CreationDateTime.setValue(huntAssignment, DateFunctions.getToday(timeZone));
		
		// Edit the assignment details - Change to Inactive and click OK
		lm.editHuntAssignmentDetails(assignID, false, true);
		HuntAssignmentAttr.Status.setValue(huntAssignment, "Inactive");
		HuntAssignmentAttr.ModifiedUser.setValue(huntAssignment, userName);
		HuntAssignmentAttr.ModifiedDateTime.setValue(huntAssignment, DateFunctions.getToday(timeZone));
	
		// Verify assignment info and then Edit the assignment details - Change to Active but click Cancel
		huntsPg.filterHuntAssignments(StringUtil.EMPTY);
		checkpoints.put(LicenseManager.Check_ID.editHuntAssignmentDetails, cp1);
		lm.editHuntAssignmentDetails(assignID, true, false);
		
		// Verify assignment info and then Edit the assignment details - Change to Active and click OK
		lm.editHuntAssignmentDetails(assignID, true, true);
		
		// View the assignment details
		HuntAssignmentAttr.Status.setValue(huntAssignment, "Active");
		HuntAssignmentAttr.ModifiedDateTime.setValue(huntAssignment, String.valueOf(DateFunctions.getToday(timeZone)));
		lm.editHuntAssignmentDetails(assignID, true, false);
		lm.logOutLicenseManager();
		
		// Clean up data
		lm.deleteHuntAssignment(schema, hunt.getHuntCode(), lotteryPrd.getCode());
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//hunt info
		hunt.setHuntCode("EHA");
		hunt.setDescription("EditHuntAssignment");
		
		//lottery product info
		lotteryPrd.setCode("PL3");
		
		//hunt assignment info
		huntAssignment = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.HuntCode.setValue(huntAssignment, hunt.getHuntCode());
		HuntAssignmentAttr.HuntDes.setValue(huntAssignment, hunt.getDescription());
		HuntAssignmentAttr.Status.setValue(huntAssignment, "Active");
		userName = DataBaseFunctions.getLoginUserName(login.userName);
		HuntAssignmentAttr.CreationUser.setValue(huntAssignment, userName);
		HuntAssignmentAttr.ModifiedUser.setValue(huntAssignment, StringUtil.EMPTY);
		HuntAssignmentAttr.ModifiedDateTime.setValue(huntAssignment, StringUtil.EMPTY);
	
		// Checkpoint
		cp1 = new CheckPoint(new Checkable() {
			@Override
			public void check(Data<?>... data) {
				LicMgrViewHuntAssignmentWidget.getInstance().verifyHuntAssignmentDetails(huntAssignment);
			}

			@Override
			public String getName() {
				return "VerifyHuntAssignmentDetails";
			}
		}, (Data<?>) null);
	}
}
