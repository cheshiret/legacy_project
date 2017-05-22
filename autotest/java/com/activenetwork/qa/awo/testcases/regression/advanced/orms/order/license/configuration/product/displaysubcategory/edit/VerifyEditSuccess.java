package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.edit;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:Verify if edit operation is successful
 * @Preconditions: None
 * @SPEC:Edit Privilege Display Sub-Category.doc
 * @Task#:AUTO-716
 * 
 * @author eliang
 * @Date  Sep 5, 2011
 */
public class VerifyEditSuccess extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
	private boolean pass = true;
	private String updatedUser,updatedLocation;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		category.id = displaySubCategoryConfigPg.getDisplaySubCategoryIdByDescription(category.description);
		//category.id = lm.addPrivilegeDisplaySubCategory(category, false);
		
		logger.debug("category id value is:" + category.id);
		
		if("".equalsIgnoreCase(category.id)){
			category.id = lm.addPrivilegeDisplaySubCategory(category, false);
		}
		
		logger.debug("category id value is:" + category.id);
		
		displaySubCategoryConfigPg.clickLink(category.id);
		widget.waitLoading();
		
		category.description = "Edit-"+DateFunctions.getCurrentTime();
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		
		//verify edit display category function work as design
		widget.setDisplaySubCategoryInfo(category.description, category.displayOrder);
		this.verifyEditedSuccess();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		category.description = ("EditSubCategoryTestSuccess").substring(0, 25);
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		
		updatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		updatedLocation = login.location.split("/")[1];
	}
	
	private void verifyEditedSuccess(){
		logger.info("Verify Edit Category.");
		
		widget.clickOK();
		displaySubCategoryConfigPg.waitLoading();
        ajax.waitLoading();
		List<String> rowInfo = displaySubCategoryConfigPg.getDisplaySubCategoryInfo(category.description, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Display category Failed.");
		}
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Display Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(category.description)){
			pass &= false;
			logger.error("Display Category Description "+rowInfo.get(1)+" not correct." + ", actual is: " + category.description);
		}
		if(!rowInfo.get(2).equalsIgnoreCase(category.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+rowInfo.get(2)+" not correct." + ", actual is: " + category.displayOrder);
		}
		displaySubCategoryConfigPg.clickLink(rowInfo.get(0));
		widget.waitLoading();
		if(!widget.getUpdatedUser().equalsIgnoreCase(updatedUser)){
			pass &= false;
			logger.error("Updated User "+widget.getUpdatedUser()+" not correct." + ", actual is: " + updatedUser);
		}
		if(!widget.getUpdatedLocation().equalsIgnoreCase(updatedLocation)){
			pass &= false;
			logger.error("Updated Location "+widget.getUpdatedLocation()+" not correct." + ", auctual is: " + updatedLocation);
		}
		if(DateFunctions.compareToToday(widget.getUpdatedDate())!=0){
			pass &= false;
			logger.error("Updated Date "+widget.getUpdatedDate()+" not Correct.");
		}
		widget.clickCancel();
		displaySubCategoryConfigPg.waitLoading();
	}
}
