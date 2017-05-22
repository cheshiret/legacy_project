/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.batchsetup;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:Thie script used to execute all required setup script for license manager,there are some dependency between different script
 * 				please pay attention when you update script running sequence
 * @Preconditions:
 * 
 * @author ssong
 * @Date  Aug 29, 2012
 */
public class SetupForLicenseMgr {

	private AutomationLogger logger = AutomationLogger.getInstance();
	private List<String> scriptNames = new ArrayList<String>();
	private List<String> exceptionList = new ArrayList<String>();
	private boolean failedonly=false;
	
	public SetupForLicenseMgr(){
		this.setupWithOutDependency();
		scriptNames.add("supportscripts.qasetup.license.AddVendor");
		scriptNames.add("supportscripts.qasetup.license.AddStore");
		scriptNames.add("supportscripts.qasetup.admin.AssignUserRoles");
		this.addAndAssignBankAccountToStore();
		this.addAndAssignBondToStore();
		
		scriptNames.add("supportscripts.qasetup.license.AddPrivInvTypeAndLicenseYear");
		scriptNames.add("supportscripts.qasetup.license.AddAndAllocatePrivilegeInventory");
		scriptNames.add("supportscripts.qasetup.license.AddPrivilegeProduct");
		scriptNames.add("supportscripts.qasetup.license.AddPricing");
		scriptNames.add("supportscripts.qasetup.license.AssignPriToStore");
		scriptNames.add("supportscripts.qasetup.license.AddPrivilegeLicenseYear");
		scriptNames.add("supportscripts.qasetup.license.AddQuantityControl");
		scriptNames.add("supportscripts.qasetup.license.AddQuestion");
		scriptNames.add("supportscripts.qasetup.license.AssignQuestionToPri");
		scriptNames.add("supportscripts.qasetup.license.AddPriBusinessRule");
		scriptNames.add("supportscripts.qasetup.license.AddVehicleProduct");
		scriptNames.add("supportscripts.qasetup.license.AssignVehicleToStore");
		scriptNames.add("supportscripts.qasetup.license.AddConsumableProduct");
		scriptNames.add("supportscripts.qasetup.license.AssignConsumableToStore");
		scriptNames.add("supportscripts.qasetup.license.AddSupply");
		scriptNames.add("supportscripts.qasetup.license.RegisterVehicle");
		scriptNames.add("supportscripts.qasetup.license.MergeCustomer");
		
	}
	
	public void addAndAssignBankAccountToStore(){
		scriptNames.add("supportscripts.qasetup.license.AddBankAccounts");
		scriptNames.add("supportscripts.qasetup.license.BankAccountStoreAssignment");
	}
	
	public void addAndAssignBondToStore(){
		scriptNames.add("supportscripts.qasetup.license.AddBonds");
		scriptNames.add("supportscripts.qasetup.license.BondStoreAssignment");
	}
	
	public void setupWithOutDependency(){
		scriptNames.add("supportscripts.qasetup.license.AddHuntingSeasons");
		scriptNames.add("supportscripts.qasetup.license.AddSpecies");
		scriptNames.add("supportscripts.qasetup.license.AddPrintDocumentTemplate");
		scriptNames.add("supportscripts.qasetup.license.AddPriPrintDocument");
		scriptNames.add("supportscripts.qasetup.license.AddBondIssuer");
		scriptNames.add("supportscripts.qasetup.license.AddCustomerProfile");
		scriptNames.add("supportscripts.qasetup.license.AddDisplayCategories");
		scriptNames.add("supportscripts.qasetup.license.AddSubDisplayCategories");
	}
	
	
	public void runSetupScript(Object[] args){
		for(String scriptName:scriptNames){
			String error = "";
			try {
				TestDriverUtil.callScript(scriptName, "failedonly="+failedonly);
			} catch (Exception e) {
				error = e.getMessage();
				logger.error(error);
				exceptionList.add("Met Exception when Run Script "+scriptName+", check detail from log!");
			}finally{
				if(StringUtil.isEmpty(error)){
					logger.info("All Passed when Execute "+scriptName);
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		new SetupForLicenseMgr().runSetupScript(args);
//		new SetupForLicenseMgr().reRunFailedRecords(args);
	}
}
