package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 26, 2011
 */
public class EditPrivilegeLicenseYear extends LicenseManagerTestCase {
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private String editSellFromDate,newSellFromDate;
	private LicenseYear newLy = new LicenseYear();
	private LicenseYear editedLy = new LicenseYear();
	private LicenseYear ly = new LicenseYear();
	private String dateFormat = "EEE MMM d yyyy HH:mm a";

	public void execute() {
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		this.checkAndAddPrivilegeLicenseYears();//editedLy.id

		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(ly.licYear);
		privilege.index= batchEditLicenseYearPg.selectPrivilegeProductCheckBox(privilege.code , privilege.name, false);

		//Successfully edit license year data
		this.getEditedAndNewLicenseYearInfo();
		this.verifyEditedAndNewLicenseYearInfo();
		
		//clean up
		lm.deactivatePrivilegeLicenseYear(newLy.id);
		
		//Logout
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		privilege.code = "c1c";
		privilege.name = "BatchEditLicenseYearTest";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory2";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
		privilege.reportCategory = "Resident Licenses";
		privilege.displayOrder = "0";

		ly.status = "Active"; 
		ly.licYear = String.valueOf(DateFunctions.getCurrentYear());
		ly.locationClass = "04 - Commercial Agent";
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellFromTime = "12:00";
		ly.sellFromAmPm = "AM";
		ly.sellToDate = DateFunctions.getDateAfterToday(10);
		ly.sellToTime = "11:59";
		ly.sellToAmPm = "PM";
	}

	private void checkAndAddPrivilegeLicenseYears(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage
		.getInstance();
		if(!licenseYearPg.verifyLicenseYearExist(ly)){
			editedLy = lm.addLicenseYear(ly);
		}
		ly.id = licenseYearPg.getLicenseYearId(ly.locationClass, ly.licYear);

		lm.gotoPrivilegeListPgFromGivePage(licenseYearPg);
	}

	private void editLicenseYearSellFromDate(){
//		String lyId = ly.id ;
//		System.out.println(lyId);
//		ly = batchEditLicenseYearPg.getLicenseYearInfo(privilege.index);
//		ly.id = lyId;
//		batchEditLicenseYearPg.setLicenseYearInfo(privilege.index);
		editSellFromDate = batchEditLicenseYearPg.getSellFromDate(privilege.index);
		PrivilegeInfo privilege1 = new PrivilegeInfo();
		privilege1.index = privilege.index;
		privilege1.licYear.sellFromDate = DateFunctions.getDateAfterGivenDay(editSellFromDate, 1);
		
		batchEditLicenseYearPg.setBatchEditLicenseYearFields(privilege1);
		
		newSellFromDate = privilege1.licYear.sellFromDate;
		
		newLy.createUser = "Test-Auto,QA-Auto";
		newLy.createTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);	
		
		editedLy.lastUpdatedUser = "Test-Auto,QA-Auto";
		editedLy.lastUpdatedTime = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)),dateFormat);	
	}

	private void getEditedAndNewLicenseYearInfo(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
		.getInstance();
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();

		this.editLicenseYearSellFromDate();

		batchEditLicenseYearPg.clickOK();
		ajax.waitLoading();
		privilegeListPage.waitLoading();

		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();

		//The edited license year
		logger.info("The original license year id:"+ly.id);
		lm.gotoPrivilegeLicenseYearDetailPg(ly.id);
		editedLy = this.getLicenseYear();

		//The new license year
		//
		newLy.id = licenseYearPg.getActiveIDViaGiveLicenseYearID(ly);
		logger.info("The new license year id:"+newLy.id);
		String newID = newLy.id;
		lm.gotoPrivilegeLicenseYearDetailPg(newLy.id);
		newLy = this.getLicenseYear();
		newLy.id = newID;
	}

	private LicenseYear getLicenseYear(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editLicenseYearWidget = LicMgrPrivilegeEditLicenseYearWidget
		.getInstance();
		LicenseYear templeteLy = new LicenseYear();
		templeteLy.status = editLicenseYearWidget.getPrivilegeLicenseYearStatus();
		templeteLy.locationClass = editLicenseYearWidget.getPrivilegeLocationClass();
		templeteLy.licYear = editLicenseYearWidget.getLicenseYear();
		templeteLy.sellFromDate = editLicenseYearWidget.getSellFromDate();
		templeteLy.sellFromTime = editLicenseYearWidget.getSellFromTime();
		templeteLy.sellFromAmPm = editLicenseYearWidget.getSellFromAmPm();
		templeteLy.sellToDate = editLicenseYearWidget.getSellToDate();
		templeteLy.sellToTime = editLicenseYearWidget.getSellToTime();
		templeteLy.sellToAmPm = editLicenseYearWidget.getSellToAmPm();
		templeteLy.validFromDate = editLicenseYearWidget.getValidFromDate();
		templeteLy.validFromTime = editLicenseYearWidget.getValidFromTime();
		templeteLy.validFromAmPm = editLicenseYearWidget.getValidFromAmPm();
		templeteLy.validToDate = editLicenseYearWidget.getValidToDate();
		templeteLy.validToTime = editLicenseYearWidget.getValidToTime();
		templeteLy.validToAmPm = editLicenseYearWidget.getValidToAmPm();
		templeteLy.createUser = editLicenseYearWidget.getCreateUser();
		templeteLy.createLocation = editLicenseYearWidget.getCreateLocation();
		templeteLy.createTime = editLicenseYearWidget.getCreateTime();
		templeteLy.lastUpdatedUser = editLicenseYearWidget.getLastUpdateUser();
		templeteLy.lastUpdatedLocation = editLicenseYearWidget.getLastUpdateLocation();
		templeteLy.lastUpdatedTime = editLicenseYearWidget.getLastUpdateTime();

		editLicenseYearWidget.clickCancel();
		licenseYearPg.waitLoading();
		return templeteLy;
	}

	private void verifyEditedAndNewLicenseYearInfo(){
		//Status
		if(!newLy.status.equals("Active")){
			throw new ErrorOnDataException("The status of newer should be "+newLy.status);
		}
		if(!editedLy.status.equals("Inactive")){
			throw new ErrorOnDataException("The status of edited should be "+editedLy.status);
		}
		//Location class
		if(!newLy.locationClass.equals(editedLy.locationClass)){
			throw new ErrorOnDataException("Location Class of newer and edited should be equal as "+editedLy.locationClass);
		}
		//License Year
		if(!newLy.licYear.equals(editedLy.licYear)){
			throw new ErrorOnDataException("License Year of newer and edited should be equal as "+editedLy.licYear);
		}
		//Sell From Date
		if(!newLy.sellFromDate.equals(newSellFromDate)){
			throw new ErrorOnDataException("Sell From Date of newer should be equal as "+newSellFromDate);
		}
		//Sell From Time
		if(!newLy.sellFromTime.equals(editedLy.sellFromTime)){
			throw new ErrorOnDataException("Sell From Time of newer and edited should be equal as "+editedLy.sellFromTime);
		}
		//Sell From AmPm
		if(!newLy.sellFromAmPm.equals(editedLy.sellFromAmPm)){
			throw new ErrorOnDataException("Sell From AmPm of newer and edited should be equal as "+editedLy.sellFromAmPm);
		}
		//Sell To Date
		if(!newLy.sellToDate.equals(editedLy.sellToDate)){
			throw new ErrorOnDataException("Sell To Date of newer and edited should be equal as "+editedLy.sellToDate);
		}
		//Sell To Time
		if(!newLy.sellToTime.equals(editedLy.sellToTime)){
			throw new ErrorOnDataException("Sell To Time of newer and edited should be equal as "+editedLy.sellToTime);
		}
		//Sell To AmPm
		if(!newLy.sellToAmPm.equals(editedLy.sellToAmPm)){
			throw new ErrorOnDataException("Sell To AmPm of newer and edited should be equal as "+editedLy.sellToAmPm);
		}
		//Create User
		if(!newLy.createUser.equals(editedLy.lastUpdatedUser)){
			throw new ErrorOnDataException("Create User of newer should be equal to the Last Update User of edited as "+editedLy.lastUpdatedUser);
		}
		//Create Time
		if(!newLy.createTime.substring(0, 15).equals(editedLy.lastUpdatedTime.substring(0, 15))){
			throw new ErrorOnDataException("Create Time of newer should be equal to the Last Update Time of edited as "+editedLy.lastUpdatedTime);
		}
		
		
		//Last Update User
		if(!newLy.lastUpdatedUser.equals("")){
			throw new ErrorOnDataException("Last Update User of newer should be null.");
		}
		//Last Update Time
		if(!newLy.lastUpdatedTime.equals("")){
			throw new ErrorOnDataException("Last Update Time of newer should be null.");
		}
	}
	
}
