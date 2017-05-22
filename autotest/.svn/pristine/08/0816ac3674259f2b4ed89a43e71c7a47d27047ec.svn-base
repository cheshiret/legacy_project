package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.hipreporting;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrAddHIPReportingScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrEditHIPReportingScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrHIPReportingSchedulesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify Add a HIP Reporting Schedule and click remove execution date
 * @Preconditions:
 * @SPEC:TC:019648
 * @Task#:Auto-1304

 * @author VZhang
 * @Date Nov 1, 2012
 */
public class AddHIPReportingSchedule_Remove  extends LicenseManagerTestCase{
	private HIPReportingScheduleInfo reportingScheduleInfo = new HIPReportingScheduleInfo();
	private LicMgrEditHIPReportingScheduleWidget editHIPReportingSchedulePg = LicMgrEditHIPReportingScheduleWidget.getInstance();
	private LicMgrAddHIPReportingScheduleWidget addHIPReportingSchedulePg = LicMgrAddHIPReportingScheduleWidget.getInstance();
	private LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	@Override
	public void execute() {
		//clear up
		this.clearupHIPReportingScheduleByLicenseYear(reportingScheduleInfo.getLicenseYear(),schema);
		lm.loginLicenseManager(login);
		//go to HIP Reporting common page
		lm.gotoHIPReportingCommonPageFromTopMenu();
		//go to HIP Reporting schedule list page
		lm.gotoHIPReportingSchedulesListPageFromHIPReprotingCommonPage();
		//add HIP Reporting schedule, with three execution date, and not click OK
		this.addHIPReportingScheduleWithoutClickOK(reportingScheduleInfo);
		reportingScheduleInfo.getExecutionDates().remove(1);
		//reset execution date with two date, and will remove one execution date, and click OK
		String scheduleID = this.addHIPReportingScheduleBySetExecutionDateInfoWithClickRemvoe(reportingScheduleInfo.getExecutionDates());
		reportingScheduleInfo.setScheduleID(scheduleID);
		
		//go to HIP Reporting schedule detail page
		lm.gotoHIPReportingScheduleDetailPgFromHIPReportingScheduleListPg(reportingScheduleInfo.getScheduleID());
		//verify HIP Reporting detail info
		this.verifyHIPReportScheduleDetailInfo(reportingScheduleInfo);
		this.gotoHIPReportingScheduleListPgFromEditHIPReportingScheduleDetailPg();
		
		//clear up
		this.clearupHIPReportingScheduleByLicenseYear(reportingScheduleInfo.getLicenseYear(),schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityName = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		reportingScheduleInfo.setStatus(OrmsConstants.ACTIVE_STATUS);
		reportingScheduleInfo.setLicenseYear(String.valueOf(Integer.valueOf(lm.getFiscalYear(schema)) + 1));
		reportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), 13));
		reportingScheduleInfo.setPeriodEndDate(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 5));
		List<String> executionDates = new ArrayList<String>();
		executionDates.add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 1));
		executionDates.add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 2));
		executionDates.add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 6));
		reportingScheduleInfo.setExecutionDates(executionDates);	
		reportingScheduleInfo.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		reportingScheduleInfo.setCreationLocation(facilityName);
		reportingScheduleInfo.setCreationDate(DateFunctions.getToday(timeZone));
		reportingScheduleInfo.setLastUpdatedUser("");
		reportingScheduleInfo.setLastUpdatedLocation("");
		reportingScheduleInfo.setLastUpdatedDate("");
		
	}
	
	private void clearupHIPReportingScheduleByLicenseYear(String licenseYear, String schema){
		this.deleteHIPReportingScheduleExecutionDatesByScheduleLicenseYear(licenseYear, schema);
		this.deleteHIPReportingScheduleInfoByScheduleLicenseYear(licenseYear, schema);
	}
	
	private void deleteHIPReportingScheduleExecutionDatesByScheduleLicenseYear(String licenseYear, String schema){
		logger.info("Delete HIP Reporting schedule execution date info by License Year = " + licenseYear);
		db.resetSchema(schema);
		
		String sql = "delete from T_HIP_RPT_EXECUTE_DATE " +
		             "where T_HIP_RPT_EXECUTE_DATE.HIP_RPT_SHCDL_ID in " +
		             "(select ID from T_HIP_RPT_SCHDL where T_HIP_RPT_SCHDL.HIP_LICENSE_YEAR = " + licenseYear + ")";
		int num = db.executeUpdate(sql);
		logger.info("Delete " + num
				+ " HIP Reporting schedule execution date from db which schedule license year "
				+ licenseYear);
	}
	
	private void deleteHIPReportingScheduleInfoByScheduleLicenseYear(String licenseYear, String schema){
		logger.info("Delete HIP Reporting schedule Info by License Year = " + licenseYear);
		db.resetSchema(schema);

		String sql = "delete from T_HIP_RPT_SCHDL " +
		             "where HIP_LICENSE_YEAR = " + licenseYear;
		int num = db.executeUpdate(sql);
		logger.info("Delete " + num
				+ " HIP Reporting schedule info from db which schedule license year "
				+ licenseYear);
	}
	
	private void addHIPReportingScheduleWithoutClickOK(HIPReportingScheduleInfo scheduleInfo){
		logger.info("Add HIP Reporting scheduel with out click OK from HIP Reporting schedules list page.");
		hipReportingScheduleListPg.clickAddHIPReportingSchedules();
		ajax.waitLoading();
		addHIPReportingSchedulePg.waitLoading();
		addHIPReportingSchedulePg.setHIPReportingScheduleInfo(scheduleInfo);
	}
	
	private String addHIPReportingScheduleBySetExecutionDateInfoWithClickRemvoe(List<String> executionDates){
		logger.info("Add HIP Reporting scheduel with click by set execution date info with click remove.");
		
		addHIPReportingSchedulePg.setExecutionDates(executionDates);
		addHIPReportingSchedulePg.clickOK();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
		String value = hipReportingScheduleListPg.getHIPReportScheduleID(reportingScheduleInfo);
		
		return value;
	}
	
	private void verifyHIPReportScheduleDetailInfo(HIPReportingScheduleInfo expSchedule){
		logger.info("Verify HIP Reporting Schedule detail info.");
		boolean result = editHIPReportingSchedulePg.compareHIPReportingScheduleInfo(expSchedule);
		if(!result){
			throw new ErrorOnPageException("HIP Reporting Schedule detail info is not correct.");
		}else logger.info("HIP Reporting Schedule detail info is correct.");
	}
	
	private void gotoHIPReportingScheduleListPgFromEditHIPReportingScheduleDetailPg(){
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage.getInstance();
		
		logger.info("Go to HIP Reporting schedule list page from HIP Reporting schedule detial page by click Cancel.");
		editHIPReportingSchedulePg.clickCancel();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
	}


}
