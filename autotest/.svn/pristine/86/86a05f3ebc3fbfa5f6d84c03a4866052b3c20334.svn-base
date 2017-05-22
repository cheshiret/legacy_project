/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transactiondetailrevenue;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This case do some UI validation and selection criteria check
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:028929
 * @Task#:AUTO-2201
 * 
 * @author ssong
 * @Date  May 7, 2014
 */
public class VerifySelectionCriteria extends ResourceManagerTestCase{

	private OrmsReportCriteriaPage criteriaPg = OrmsReportCriteriaPage.getInstance();
	private String msg1,msg2,msg3;
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		validateUI();		
		
		rm.logoutResourceManager();		
	}
	
	private void validateUI(){
		criteriaPg.setStartDate(DateFunctions.getDateAfterToday(-1));
		criteriaPg.setEndDate(DateFunctions.getToday());
		//check no account set in contract and select 'All' Agency,choose report by 'Revenue Location'
		//as default Agency is 'All' and default report by is 'Revenue Location'
		verifyErrorMsg(msg1);
		//check no account set in contract,'All' Agency,report by 'Transaction Location' then Break Account section should be hidden
		criteriaPg.selectTransactionDetailReportBy("Transaction Location");
		criteriaPg.waitLoading();
		if(criteriaPg.isBreakAccountCodesExists()){
			throw new ErrorOnPageException("Break Account Codes Dropdown should be hidden.");
		}
		//Break Account 'Yes',Account ID not selected from display column
		criteriaPg.selectAgencyID("BLM");
		criteriaPg.selectBreakAccountCodes("Yes");
		verifyErrorMsg(msg2);
		//Sub total by a column,but that column not selected in the Display column
		criteriaPg.selectBreakAccountCodes("No");
		criteriaPg.selectSubTotalBy(new String[]{"Deposit ID"});
		verifyErrorMsg(msg3);		
	}
	
	private void verifyErrorMsg(String msg){
		criteriaPg.clickOk();
		criteriaPg.waitLoading();
		String actual_msg = criteriaPg.getErrorMsg();
		if(!actual_msg.matches(".*"+msg+".*")){
			throw new ErrorOnPageException("Error msg not correct",msg,actual_msg);
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "All";
		rd.reportName = "Transaction Detail Revenue Report";
		
		msg1 = "Report cannot be run for All Agencies in NRRS Contract. Please select an Agency";
		msg2 = "Break Account column has been selected as \"Yes\", but Account ID is not selected as Display Column. Please select Account ID as one of the Display Columns or set Break Account Column to \"No\"";
		msg3 = "Report cannot Sub-Total by columns not selected for displaying in the report. Please remove columns Deposit ID from Sub-Total By Selection";
	}

}
