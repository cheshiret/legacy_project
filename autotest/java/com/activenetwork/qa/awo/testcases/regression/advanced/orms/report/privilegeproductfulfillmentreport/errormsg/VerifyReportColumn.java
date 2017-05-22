/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.privilegeproductfulfillmentreport.errormsg;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:TC:041355,041356
 * @Task#:AUTO-1391
 * 
 * @author ssong
 * @Date  Feb 4, 2013
 */
public class VerifyReportColumn extends ResourceManagerTestCase{

	private List<String> errorMessage = new ArrayList<String>();
	
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//For 'lifetime' fixed length type, customer attribute should be selected. 
		String msg = rm.checkReportCriteriaValidation(rd);
		this.verifyErrorMsgCorrect(msg, errorMessage.get(0));
		
		//for others, it should not be selected.
		rm.selectOneRpt(rd.group, rd.reportName);
		rd.fixedLengthType = "Youth";
		rd.customerAttribute = "Gender";
		msg = rm.checkReportCriteriaValidation(rd);
		this.verifyErrorMsgCorrect(msg, errorMessage.get(1));
		
		//for 'senior' type, 'SW' is required
		rm.selectOneRpt(rd.group, rd.reportName);
		rd.fixedLengthType = "Senior";
		rd.customerAttribute = "";
		msg = rm.checkReportCriteriaValidation(rd);
		this.verifyErrorMsgCorrect(msg, errorMessage.get(2));
		
		//'WMA' not part of senior
		rm.selectOneRpt(rd.group, rd.reportName);
		rd.otherPrivilegeProducts = new String[]{"107 - RES WMA USER PERMIT(WMA)","127 - RES LIFETIME SALTWATER(SW)"};
		msg = rm.checkReportCriteriaValidation(rd);
		this.verifyErrorMsgCorrect(msg, errorMessage.get(3));
		
		//logout resource manager
		rm.logoutResourceManager();	
	}
	
	private void verifyErrorMsgCorrect(String msg,String expectString){
		if(!msg.equalsIgnoreCase(expectString)){
			throw new ErrorOnPageException("There is should have a Warining msg displayed",expectString,msg);
		}
	}
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Privilege Product Fulfillment Report";
		rd.reportByType = "Fixed Length";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.privilegeProduct = new String[] { "102 - ARCHERY/PRIM/CROSSBOW" };
		rd.fixedLengthType = "Lifetime";

		rd.yesNoFlag = "No";
		rd.includeAP = "No";
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rd.endDate = DateFunctions.getDateAfterToday(-1);
		rd.reportFormat = "TEXT";
		
		errorMessage.add("The following column(s) \"Customer Profile Attribute\" is required for the selected Fixed Length Type, Lifetime. Please select it before running the report.");
		errorMessage.add("The following column(s),\"Customer Profile Attribute\", is not part of the selected Fixed Length Type, Youth. Please de-select it before running the report.");
		errorMessage.add("The following column(s) SW, are required for the selected Fixed Length Type, Senior. Please select them before running the report.");
		errorMessage.add("The following column(s) WMA, are not part of the selected Fixed Length Type, Senior. Please de-select them before running the report.");
	}
	
}
