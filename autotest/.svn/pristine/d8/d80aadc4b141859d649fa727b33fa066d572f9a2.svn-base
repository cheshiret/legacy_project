package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.add;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Add privilege report category successfully
 * @Preconditions:Null
 * @SPEC:Add Privilege Report Category.doc
 * @Task#:AUTO-723
 * 
 * @author eliang
 * @Date  Aug 17, 2011
 */
public class VerifyAddSuccess extends LicenseManagerTestCase{
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	private LicMgrReportCategoriesConfigurationPage reportCategoryConfigPg = LicMgrReportCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget.getInstance();
	private boolean pass =true;
	String addedUser,addedLocation;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		// add privilege display sub category successful
		this.addReportCategoryToWidgetPg(report.description, report.displayOrder, report.groupNum);
		this.verifyAddSuccess();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//The maximum characters of description is 25 so the 26th and after ones are truncated. 
		String tempDescription = "AddReportCatTest"+DateFunctions.getCurrentTime();
		report.description = tempDescription.substring(0, 24);
		report.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		report.groupNum = new Long(DateFunctions.getCurrentTime()).toString().substring(9,13);
		
		addedUser = DataBaseFunctions.getLoginUserName(login.userName);
		addedLocation = login.location.split("/")[1];
	}
	
	private void addReportCategoryToWidgetPg(String description, String displayOrd, String groupNum){
		reportCategoryConfigPg.clickAdd();
		ajax.waitLoading();
		widget.waitLoading();
		widget.setReportCategoryInfo(description, displayOrd, groupNum);
	}
	
	private void verifyAddSuccess(){
		widget.clickOK();
		ajax.waitLoading();
		reportCategoryConfigPg.waitLoading();
		
//		Redundant code: can be removed after updating the code for getRowInfo() method.
//		if(!reportCategoryConfigPg.checkReportCategoryExists(report.description)){
//			pass &= false;
//			logger.error("Add Display category Failed.");
//		}
		
		List<String> rowInfo = reportCategoryConfigPg.getReportCategoryInfo(report.description, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Display category Failed.");
		}
		
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Display Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(report.description)){
			pass &= false;
			logger.error("Display Category Description "+rowInfo.get(1)+" not correct," + "should be " + report.description);
		}
		if(!rowInfo.get(2).equalsIgnoreCase(report.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+rowInfo.get(2)+" not correct," + "should be" + report.displayOrder);
		}
		if(!rowInfo.get(3).equalsIgnoreCase(report.groupNum)){
			pass &= false;
			logger.error("Display Category Display Order "+rowInfo.get(3)+" not correct," + "should be" + report.groupNum);
		}
		reportCategoryConfigPg.clickLink(rowInfo.get(0));
		widget.waitLoading();
		if(!widget.getDescription().equalsIgnoreCase(report.description)){
			pass &= false;
			logger.error("Display Category Description "+widget.getDescription()+" not correct." + ", actual is " + report.description);
		}
		if(!widget.getDisplayOrder().equalsIgnoreCase(report.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+widget.getDisplayOrder()+" not correct." + ", actual is " + report.displayOrder);
		}
		if(!widget.getGroupNum().equalsIgnoreCase(report.groupNum)){
			pass &= false;
			logger.error("Display Category Hiden in Sale "+widget.getGroupNum()+" not correct." + ", actual is " + report.groupNum);
		}
		if(!widget.getAddedUser().equalsIgnoreCase(addedUser)){
			pass &= false;
			logger.error("Added User "+widget.getAddedUser()+" not correct." + ", actual is " + addedUser);
		}
		if(!widget.getAddedLocation().equalsIgnoreCase(addedLocation)){
			pass &= false;
			logger.error("Added Location "+widget.getAddedLocation()+" not correct." + ", actual is" + addedLocation);
		}
		if(DateFunctions.compareToToday(widget.getAddedDate())!=0){
			pass &= false;
			logger.error("Added Date "+widget.getAddedDate()+" not Correct." + ", actual is " + DateFunctions.getToday());
		}
		widget.clickCancel();
		reportCategoryConfigPg.waitLoading();
	}
}
