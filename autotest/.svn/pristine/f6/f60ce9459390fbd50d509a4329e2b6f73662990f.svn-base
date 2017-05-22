/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.storestatusreport;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:TC:019679
 * @Task#:Auto-1394
 * 
 * @author ssong
 * @Date  Mar 14, 2013
 */
public class RunInLocWithOutChartOfAccount extends ResourceManagerTestCase{

	private String warningMsg = "This report cannot be accessed at your logged-in location level. Please access this report via a different Role/Location.";
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		this.checkReportValidation();
		
		rm.logoutResourceManager();		
	}
	
	private void checkReportValidation(){
		OrmsReportCriteriaPage pg = OrmsReportCriteriaPage.getInstance();
		
		pg.selectStoreStatus(rd.storeStatus);
		pg.selectReportFormat(rd.reportFormat);
		pg.selectDeliveryMethod(rd.deliveryMethod);
		pg.clickOk();
		pg.waitLoading();
		String msg = pg.getErrorMsg();
		if(!msg.equals(warningMsg)){
			throw new ErrorOnPageException("Report Validate Msg Not Correct",warningMsg,msg);
		}
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/AutoWarehouse";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		// report information
		rd.group = "All";
		rd.reportName = "Store Status Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.storeStatus = new String[]{"Active","Inactive","Revoked","Closed"};
		rd.fileName = "StoreStatusReport"+"_CheckError";
		
		rd.reportFormat = "CSV";
		rd.deliveryMethod = "Online";
	}

}
