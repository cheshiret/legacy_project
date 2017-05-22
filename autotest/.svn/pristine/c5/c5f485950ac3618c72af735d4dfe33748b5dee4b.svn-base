package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:After change the status to inactive, the other active record will be generated. Verify the date, user and location
 * @Preconditions:Edit Privilege License Year
 * @SPEC:Edit Privilege License Year
 * @Task#:AUTO-588
 * 
 * @author eliang
 * @Date  May 30, 2011
 */
public class ChangeToInactiveWithOtherChange extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editYearPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilege.code, "License Year");
		String existId = lm.addLicenseYear(ly).id;
		
		ly.sellToDate  = DateFunctions.getDateAfterToday(4);
		this.inactiveLicenseYear(existId);
		String generatedId = this.getInvalidLicenseYearId(schema, ly).get(0);
		
		//Verify ids are different
		this.verifyIdUnique(generatedId, schema);
		
		this.gotoInactiveLicenseYearDetail(existId);
		//Verify other update information
		this.verifyUpdateMsg();
		addYearPg.clickCancel();
		
		//Inactive License year
		this.inactiveLicenseYear(generatedId);
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code = "NL1";
		privilege.name = "AddSuccess";
		
		ly.licYear = Integer.toString(DateFunctions.getCurrentYear());
		ly.locationClass = "04 - Commercial Agent";
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellToDate = DateFunctions.getDateAfterToday(3);
		ly.sellFromTime = "12:00";
		ly.sellToTime = "11:59";
		ly.validFromDate = DateFunctions.getDateAfterToday(10);
		ly.validToDate = DateFunctions.getDateAfterToday(11);
		ly.validFromTime = "12:00";
		ly.validToTime = "11:59";
		ly.validFromAmPm = "AM";
		ly.validToAmPm = "PM";
	}

	public void gotoLicenseYearId(String id){
		licenseYearPg.clickLicenseYear(id);
		ajax.waitLoading();
	}
	
	public void inactiveLicenseYear(String id){
		licenseYearPg.clickLicenseYear(id);
		ajax.waitLoading();
		editYearPg.selectStatus("Inactive");
		editYearPg.clickOK();
		ajax.waitLoading();
	}
	
	public void gotoInactiveLicenseYearDetail(String licId){
		licenseYearPg.unSelectShowCurrentRecordsOnly();
		licenseYearPg.clickGo();
		ajax.waitLoading();
		
		licenseYearPg.clickLicenseYear(licId);
		ajax.waitLoading();
	}
	
	public void verifyUpdateMsg(){
		if(editYearPg.getPrivilegeLicenseYearStatus().equalsIgnoreCase("Inactive")&&editYearPg.getLastUpdateUser().equalsIgnoreCase(DataBaseFunctions.getLoginUserName(login.userName))&& editYearPg.getLastUpdateTime().matches(DateFunctions.getToday())&&editYearPg.getLastUpdateLocation().equalsIgnoreCase(login.location.split("/")[1])){
			throw new ErrorOnDataException("the update information is incorrect");
		}
	}
	
	public List<String> getInvalidLicenseYearId(String schema, LicenseYear ly){
		db.resetSchema(schema);
		db.connect();
		
		String query = "SELECT ID from P_PRD_YEAR where Active_IND = 0 and year = "+ly.licYear+
		"and prd_id = " +
		"(select prd_id from p_prd where prd_cd='" + privilege.code +
		"'and prd_name='" + privilege.name +
		"') and location_class_id = (select id from D_LOCATION_CLASS where code = " + ly.locationClass.split("-")[0].trim() + " )" +
		"order by id DESC";
		System.out.println(query);
		List<String> ids =db.executeQuery(query, "ID");
		return ids;
	}
	
	public void verifyIdUnique(String id, String schema){
		db.resetSchema(schema);
		db.connect();
		String query = "select count(*) count from P_PRD_YEAR where id = "+id;
		
		List<String> count =db.executeQuery(query, "count");
		if(count.size()<1||!count.get(0).equalsIgnoreCase("1")){
			throw new ErrorOnDataException("The license year id generated should be unique!");
		}
	}
}
