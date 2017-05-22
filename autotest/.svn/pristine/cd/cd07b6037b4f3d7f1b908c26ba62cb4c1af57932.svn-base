package com.activenetwork.qa.awo.supportscripts.qasetup.batchsetup;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

public class SetupAllRules {

	private List<String> scriptNames = new ArrayList<String>();
	private boolean failedOnly = false;
	private AutomationLogger logger = AutomationLogger.getInstance();
	private List<String> exceptionList = new ArrayList<String>();
	
	public SetupAllRules() {
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_AccessTime");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_AccessType");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_AssociateEntrance");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_BlockStay");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_InventoryHoldTimeout");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_IssuePermitRestriction");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxConsecutiveStay");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxGroupSize");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaximumStay");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaximumStayPerPeriod");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaximumTimeToReceivePayment");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaximumTotalStay");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaximumWindow");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfConcurrentReservations");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfConcurrentReservationsForSameCustPassNum");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfOrdersPerCall");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfOrdersTimesTicketsPerCallCart");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfOrdersTimesTicketsWithinInventoryPeriod");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfOrdersWithinABookingPeriod");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfOrdersWithinStayPeriod");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfPermitsPerPeriodNonProfitOrg");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfReservationsWithTheSameStartDate");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MaxNumOfStaysPerPeriod");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MinimumGroupSize");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MinimumStay");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_MinimumWindow");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_ProductRestrictedInUse");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_RestrictEntrance");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_SpecifiedStayStart");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_StayBeyondMaximumWindow");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_TimeRestrictionBeforeChangeOfDatesAllowed");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_TimeToClear");
		scriptNames.add("supportscripts.qasetup.admin.CreateRule_TransactionRestriction");
	}
	
	public void runSetupScript(Object[] args){
		for(String scriptName:scriptNames){
			String error = "";
			try {
				TestDriverUtil.callScript(scriptName, "failedonly="+failedOnly);
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
		new SetupAllRules().runSetupScript(args);
	}
}
