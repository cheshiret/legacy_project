/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.batchsetup;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;


/**
 * @Pre-condition:before run this batch setup script,need first manually run some below setup sql
 * 1. ormsAddQaUsersRoles_sanity_QA.sql
 * 2. activateLoadTestCustomers.sql
 * 3. updateAutoAccountPassword.sql
 * 4. updateAutoAccountPasswordExpiredDate.sql
 * 5. KOA park import follow http://wiki.reserveamerica.com/display/qa/KOA+-+Tom%27s+campground+setup+-+NEW
 * 6. SetupSlipAttribute.sql
 * 7. SetupCampingUnitForMM.sql
 * 8. SetupLeaseSearchMonthOptions.sql
 * 9. Update cooperator password after create cooperator
 * 10. update facility to support marina
 * 11. Setup permit type attribute direction of travel
 * @Description:This script is the setup set include all the setup job required by sanity test
 * @author ssong
 * @Date  Aug 28, 2012
 */
public class SetupForSanityTest {

	private AutomationLogger logger = AutomationLogger.getInstance();
	private List<String> scriptNames = new ArrayList<String>();
	private List<String> exceptionList = new ArrayList<String>();
	private boolean failedonly=false;
	
	public SetupForSanityTest(){
		scriptNames.add("supportscripts.qasetup.admin.AssignFeaturesInAdminManager");
		scriptNames.add("supportscripts.qasetup.db.SetupPos");
		scriptNames.add("supportscripts.qasetup.db.ResetPWForVeriFone");
		scriptNames.add("supportscripts.qasetup.inventory.AddMasterPosProduct");
		scriptNames.add("supportscripts.qasetup.finance.AssignPosProductToCallOrWeb");
		scriptNames.add("supportscripts.qasetup.inventory.AddNewLotteryProgram");
		scriptNames.add("supportscripts.qasetup.inventory.AddTourInventory");
		scriptNames.add("supportscripts.qasetup.inventory.AddDock");
		scriptNames.add("supportscripts.qasetup.inventory.AddSlipOrNSSGroup");
		scriptNames.add("supportscripts.qasetup.inventory.AddNSSChildSlip");
		scriptNames.add("supportscripts.qasetup.inventory.AddSlipSeason");
		scriptNames.add("supportscripts.qasetup.inventory.AddList");
		scriptNames.add("supportscripts.qasetup.inventory.CreateCorporator");
		scriptNames.add("supportscripts.qasetup.db.SetupPermitInventory");
		scriptNames.add("supportscripts.qasetup.finance.CreateBaseFeeSchedule");
		scriptNames.add("supportscripts.qasetup.finance.AddPermitUseFeeSchedule");
		scriptNames.add("supportscripts.qasetup.finance.AddUseOrAttrFeeSchedule");
		scriptNames.add("supportscripts.qasetup.finance.CreateAutoDiscount");
		scriptNames.add("supportscripts.qasetup.license.AddCustomerProfile");
		scriptNames.add("supportscripts.qasetup.license.AssignVehicleToStore");
	}
	
	public void runSetupScript(){
		for(String scriptName:scriptNames){
			String error = "";
			try {

				TestDriverUtil.callScript(scriptName, "testsuite=sanity:failedonly="+failedonly);
				TestDriverUtil.callScript("supportscripts.qasetup.admin.RulesCreation", "ruleType=Access Type:testsuite=sanity:failedonly="+failedonly);
				TestDriverUtil.callScript("supportscripts.qasetup.admin.RulesCreation", "ruleType=Maximum Window:testsuite=sanity:failedonly="+failedonly);
			} catch (Exception e) {
				error = e.getMessage();
				logger.error(error);
				exceptionList.add("Met Exception when Run Script "+scriptName+", check detail from log!");
			}finally{
				logger.info("Finished Execute "+scriptName+", check detail from log!");
			}
		}
	}
	
	public static void main(String[] args){
		new SetupForSanityTest().runSetupScript();
//		new SetupForSanityTest().reRunFailedRecords(args);
	}
	

}
