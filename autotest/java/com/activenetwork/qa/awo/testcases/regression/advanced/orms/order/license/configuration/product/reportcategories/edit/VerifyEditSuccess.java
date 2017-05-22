/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.edit;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case verify the steps taken by the User to edit an existing Privilege Report Category for the Contract.
 * @Preconditions:existing the report category that can be edited
 * @SPEC:Use Case Specification: Edit Privilege Report Category 
 * @Task#:AUTO-714
 * 
 * @author szhou
 * @Date  Aug 17, 2011
 */
public class VerifyEditSuccess extends LicenseManagerTestCase{
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		
		// add a report category for edit
		report.id=this.getReportCategoryId(report.description);
		if("".equals(report.id)){
			report.id=lm.addReportCategory(report,false);
		}
		
		// go to edit privilege report category
		this.resetReportCategoryInfo();
		lm.gotoEditPrivilegeReportCategory(report.id);
		
		// edit privilege information successful
		lm.editPrivilegeReportCategory(report, false);
		boolean succ=this.verifyEditReportCategorySuccess(report);
		
		if (!succ) {
			throw new ActionFailedException(
					"Fail to edit privilege report category...");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		TimeZone timeZone=DataBaseFunctions.getContractTimeZone(schema);
		report.description="EditReportCategoryForTest";
		report.displayOrder="800";
		report.groupNum="800";
		report.updateUser=DataBaseFunctions.getLoginUserName(login.userName);
		report.updateLocation=login.location.split("/")[1];
		report.updateDate=DateFunctions.formatDate(DateFunctions.getToday(timeZone),"EEE MMM dd yyyy");
		
	}

	private void resetReportCategoryInfo(){
		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		report.description="EditReportCategoryForTest";
		report.displayOrder=seq;
		report.groupNum=seq;
	}
	
	private String getReportCategoryId(String description){
		LicMgrReportCategoriesConfigurationPage reportCategoryConfPg = LicMgrReportCategoriesConfigurationPage
		.getInstance();
		
		String id=reportCategoryConfPg.getReportCategoryIdByDescription(description);
		
		return id;
	}
	
	private boolean verifyEditReportCategorySuccess(PrivilegeReportCategory Info) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
		.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget=LicMgrAddOrEditReportCategoryWidget.getInstance();
		boolean result=true;

		logger.info("Verify edit report category success or not ...");

		logger.info("View report category list ...");
		if (this.compareReportCategoryListInfo(Info)) {
			logger
					.info("Edit report category successful on the product configuration page.");
		} else {
			logger.error("Failed to Edit '" + Info.id
					+ " ' report category on the product configuration page.");
			result=false;
		}

		logger.info("View reprot category detail information ...");
		lm.gotoEditPrivilegeReportCategory(Info.id);
		if (widget.compareReportDetailInfo(Info)) {
			logger
					.info("Edit report category successful on the edit privilege report category page.");
		} else {
			logger.error("Failed to edit '" + Info.id
					+ " ' report category on the edit privilege report category page.");
			result=false;
		}

		widget.clickOK();
		ajax.waitLoading();
		prodConfPg.waitLoading();

		return result;
	}
	
	private boolean compareReportCategoryListInfo(PrivilegeReportCategory Info){
		LicMgrReportCategoriesConfigurationPage reportCategoryConfigPg = LicMgrReportCategoriesConfigurationPage
		.getInstance();
		boolean result = true;
		
		List<String> values=reportCategoryConfigPg.getReportCategoryInfoById(Info.id);
		if(!values.get(0).equals(Info.id)){
			logger.error("The expected id is not " + Info.id + ", but " + values.get(0));
			result &= false;
		}
        if(!values.get(1).equals(Info.description)){
        	logger.error("The expected description is not " + Info.description + ", but " + values.get(1));
			result &= false;
		}
        if(!values.get(2).equals(Info.displayOrder)){
        	logger.error("The expected display order is not " + Info.displayOrder + ", but " + values.get(2));
			result &= false;
		}
        if(!values.get(3).equals(Info.groupNum)){
        	logger.error("The expected group number is not "
					+ Info.groupNum + ", but " + values.get(3));
			result &= false;
	    }
		
		return result;
	}
	
}
