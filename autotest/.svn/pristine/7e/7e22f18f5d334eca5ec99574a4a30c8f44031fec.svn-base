/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.recipient;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSendReportPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This script used to verify initial filter for recipient in report send to choose recipient page
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC100293,100294 
 * @Task#:Auto-2259
 * 
 * @author ssong
 * @Date  Jul 23, 2014
 */
public class VerifyDefaultFilter extends ResourceManagerTestCase{
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		//login contract level,check default filter is 'A' selected
		rm.selectOneRpt(rd.group, rd.reportName);
		checkDefaultFilter("A");
		
		rm.logoutResourceManager();
		
		//change to facility level,check default filer is 'All' selected
		login.location = "Administrator/BEAVER CREEK (IDAHO)";
		
		rm.loginResourceManager(login,false);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		checkDefaultFilter("All");
		
		rm.logoutResourceManager();
	}
	
	private void checkDefaultFilter(String defaultChar){
		 ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		 OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage
			.getInstance();
			
		rmCriteriaPg.setReportCriteria(rd);
		rmCriteriaPg.clickOk();
		rmSendRptPg.waitLoading();
		boolean correct = rmSendRptPg.checkDefaultRecipientFilterCorrect(defaultChar);
		if(!correct){
			throw new ErrorOnPageException("Default Recipient Filter is not "+defaultChar);
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
		rd.reportName = "7-Day Campers Report";
	
		rd.startDate = "07/01/2007";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}

}
