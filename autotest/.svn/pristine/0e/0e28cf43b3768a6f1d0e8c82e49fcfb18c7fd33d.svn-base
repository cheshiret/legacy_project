package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.product.huntassignment;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.HuntAssignmentAttr;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
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
 * @Description: Assign Hunt to privilege lottery
 * @Preconditions:
 * 1. The lottery product exists.
 * 2. The hunt exists.
 * @SPEC: 
 * Assign Hunt to Privilege Lottery Product - UC [TC:044941]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=60
 * d_hf_add_hunt:id=340
 * @Task#: Auto-2062
 * 
 * @author Lesley Wang
 * @Date  Jan 15, 2014
 */
public class AssignHunt extends LicenseManagerTestCase {
	private Data<HuntAssignmentAttr> huntAssignment;
	private CheckPoint cp1;
	private TimeZone timeZone;
	
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
		
		// View the assignment details
		lm.editHuntAssignmentDetails(assignID, true, false);
		
		// Clean up data
		lm.deleteHuntAssignment(schema, hunt.getHuntCode(), lotteryPrd.getCode());
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//hunt info
		hunt.setHuntCode("AHL");
		hunt.setDescription("AssignHuntToLottery");
		
		//lottery product info
		lotteryPrd.setCode("PL3");
		
		//hunt assignment info
		huntAssignment = new Data<HuntAssignmentAttr>();
		HuntAssignmentAttr.HuntCode.setValue(huntAssignment, hunt.getHuntCode());
		HuntAssignmentAttr.HuntDes.setValue(huntAssignment, hunt.getDescription());
		HuntAssignmentAttr.Status.setValue(huntAssignment, "Active");
		String userName = DataBaseFunctions.getLoginUserName(login.userName);
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
		checkpoints.put(LicenseManager.Check_ID.editHuntAssignmentDetails, cp1);
	}

}
