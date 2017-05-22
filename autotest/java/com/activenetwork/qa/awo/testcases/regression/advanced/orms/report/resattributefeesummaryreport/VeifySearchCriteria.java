/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.resattributefeesummaryreport;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  Aug 4, 2014
 */
public class VeifySearchCriteria extends ResourceManagerTestCase{
	private OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage.getInstance();
	private String errorMsg;

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//attribute value for selected attribute type have multiple attribute value
		rd.attrFeeType = "Shelter Size";
		this.checkAttributeValueOptionIsExisting(rd.attrFeeType,true);
		
		//attribute value for selected attribute type just have boolean values
		rd.attrFeeType = "Water Hookup";
		this.checkAttributeValueOptionIsExisting(rd.attrFeeType,false);
		
		String actMsg = this.setupReportSearchCriteria(rd.startDate, rd.endDate);
		this.verifyErrorMessage(errorMsg, actMsg);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "All";
		rd.reportName = "Reservation Attribute Fee Summary Report";
		rd.agencyID = "SC parks";
		rd.facilityID = "All";
		rd.startDate = DateFunctions.getToday();
		rd.endDate = DateFunctions.getDateAfterGivenDay(rd.startDate, 367);
		
		errorMsg = "period of 366 days";//Reporting period exceeds the maximum period of 366 days. Please refine the search criteria.
	}
	
	private String setupReportSearchCriteria(String startDate,String endDate){
		logger.info("Setup Report Search Criteria.");
		rmCriteriaPg.setStartDate(startDate);
		rmCriteriaPg.setEndDate(endDate);
		rmCriteriaPg.clickOk();
		rmCriteriaPg.waitLoading();
		String errorMessage = rmCriteriaPg.getErrorMsg();
		return errorMessage;
	}
	
	private void checkAttributeValueOptionIsExisting(String attributeType,boolean isExpExisting){
		logger.info("Check Attribute value option whether is existing by selected attribute type = " + attributeType);
		rmCriteriaPg.selectAttrFeeType(attributeType);
		rmCriteriaPg.waitLoading();
		
		boolean isAttributeValueExisting = rmCriteriaPg.isAttributeValuesExisting();
		boolean result = MiscFunctions.compareResult("Is Attribute Value option is Existing", isExpExisting, isAttributeValueExisting);
		if(!result){
			throw new ErrorOnPageException("Attribute Value option is Existing info not correct, please check log.");
		}else logger.info("Attribute Value option is Existing info is correct.");
		
	}
	
	private void verifyErrorMessage(String expMsg,String actMsg){
		logger.info("Verify error message.");
		boolean result = MiscFunctions.containString("Error Message", actMsg, expMsg);
		if(!result){
			throw new ErrorOnPageException("Error Message not correct, please check log.");
		}else logger.info("Error Message is correct.");
	}
}
