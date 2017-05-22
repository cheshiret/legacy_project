/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vendorfeerpt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Check translation and drop down list options
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:067491,067494
 * @Task#:Auto-2197
 * 
 * @author ssong
 * @Date  May 22, 2014
 */
public class VerifySelectionCriteria extends ResourceManagerTestCase{

	private List<String> includeEFTOptions = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		checkUI();
		
		rm.logoutResourceManager();		
	}
	
	private void checkUI(){
		OrmsReportCriteriaPage criteriaPg = OrmsReportCriteriaPage.getInstance();
		
		String labelName = rm.getTranslationValueByKey(schema, "translatable.store");
		if(!criteriaPg.checkLabelNameExist(labelName)){
			throw new ErrorOnPageException("Label Name("+labelName+") Configure Not Correct.");
		}
		List<String> options = criteriaPg.getAllIncludeEFTAdjustmentOptions();
		if(!options.containsAll(includeEFTOptions)||!includeEFTOptions.containsAll(options)){
			throw new ErrorOnPageException("Include Store EFT Adjustments");
		}
		
		String methods = criteriaPg.getAllDeliveryMethod();
		if(!methods.contains("Online")){
			throw new ErrorOnPageException("Delivery Methods not contains 'Online'.");
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema=com.activenetwork.qa.awo.util.DataBaseFunctions.getSchemaName("MS",env);
		
		// report information
		rd.group = "All";
		rd.reportName = "Vendor Fee Report";

		includeEFTOptions.add("Yes");
		includeEFTOptions.add("No");
	}

}
