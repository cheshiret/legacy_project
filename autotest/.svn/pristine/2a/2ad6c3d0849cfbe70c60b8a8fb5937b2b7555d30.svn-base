package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.hipreporting;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrAddHIPReportingScheduleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting.LicMgrHIPReportingSchedulesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify Add a HIP Reporting Schedule error message
 * @Preconditions:
 * @SPEC:TC:019648
 * @Task#:Auto-1304

 * @author VZhang
 * @Date Nov 7, 2012
 */

public class AddHIPReporting_Message extends LicenseManagerTestCase{
	private HIPReportingScheduleInfo existReportingScheduleInfo = new HIPReportingScheduleInfo();
	private HIPReportingScheduleInfo reportingScheduleInfo = new HIPReportingScheduleInfo();
	private String message_existLicYear,message_noPeriodStartDate,message_noPeriodEndDate,message_startGreaterThanEnd,
	message_overridePeriod,message_noExecution,message_exeLessThanPerStart,message_dupExecution,message_sameWithExistExe,
	message_noExeGreaterPerEnd,message_mulExeGreaterPerEnd,message_exeGreaterExe;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	@Override
	public void execute() {
		//clear up
		this.clearupHIPReportingScheduleByLicenseYear(reportingScheduleInfo.getLicenseYear(),schema);
		this.clearupHIPReportingScheduleByLicenseYear(existReportingScheduleInfo.getLicenseYear(),schema);
		
		lm.loginLicenseManager(login);
		//go to HIP Reporting common page
		lm.gotoHIPReportingCommonPageFromTopMenu();
		//go to HIP Reporting schedule list page
		lm.gotoHIPReportingSchedulesListPageFromHIPReprotingCommonPage();
		//add HIP Reporting schedule for existing
		lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(existReportingScheduleInfo);
		
		//verify message license year is existing
		message_existLicYear = "Another active HIP Reporting Schedule record " + existReportingScheduleInfo.getScheduleID() + " already exists for the same HIP Reporting License Year. " +
				"Duplicate active records are not allowed.";
		String actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_existLicYear, actMessage);
		
		//verify message period start date is blank
		reportingScheduleInfo.setPeriodStartDate("");
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noPeriodStartDate, actMessage);
		
		//verify message period start date is not valid
		reportingScheduleInfo.setPeriodStartDate("test@");
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noPeriodStartDate, actMessage);
		
		//verify message period end date is blank
		reportingScheduleInfo.setPeriodStartDate(existReportingScheduleInfo.getPeriodStartDate());
		reportingScheduleInfo.setPeriodEndDate("");
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noPeriodEndDate, actMessage);
		
		//verify message period end date is not valid
		reportingScheduleInfo.setPeriodEndDate("");
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noPeriodEndDate, actMessage);
		
		//verify message period start date is greater then period end date
		reportingScheduleInfo.setPeriodStartDate(existReportingScheduleInfo.getPeriodEndDate());
		reportingScheduleInfo.setPeriodEndDate(existReportingScheduleInfo.getPeriodStartDate());
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_startGreaterThanEnd, actMessage);
		
		//verify message period date is override with existing active schedule
		reportingScheduleInfo.setLicenseYear(String.valueOf(Integer.valueOf(existReportingScheduleInfo.getLicenseYear()) +1));
		reportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodStartDate(), 2));
		reportingScheduleInfo.setPeriodEndDate(existReportingScheduleInfo.getPeriodEndDate());
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodEndDate(), 1));
		message_overridePeriod = "Another active HIP Reporting Schedule record " + existReportingScheduleInfo.getScheduleID() + " already exists with Period Date Range that overlaps with this record's Period Date Range. " +
				"Overlapping active records are not allowed.";
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_overridePeriod, actMessage);
		
		//verify message execution date is blank
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getExecutionDates().get(1), -3));
		reportingScheduleInfo.setPeriodEndDate(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 3));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noExecution, actMessage);
		
		//verify message execution date is less than period start date
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(),-1));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_exeLessThanPerStart, actMessage);
		
		//verify message duplicate execution date
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(),1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(),1));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_dupExecution, actMessage);
		
		//verify message execution date is same with existing schedule
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(existReportingScheduleInfo.getExecutionDates().get(1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodEndDate(),5));
		message_sameWithExistExe = "At least one Report Execution Date entry is the same as a Report Execution Date entry in another active HIP Reporting Schedule record " + existReportingScheduleInfo.getScheduleID() +
				". Each Report Execution Date entry must be unique across all active HIP Reporting Schedules.";
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_sameWithExistExe, actMessage);
		
		//verify message execution date is less then period end date
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(),1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodEndDate(),-1));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_noExeGreaterPerEnd, actMessage);
		
		//verify message mulitip execution date is greater then period end date
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(),1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodEndDate(),1));
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodEndDate(),2));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_mulExeGreaterPerEnd, actMessage);
		
		//verify message execution date is greater than an existing HIP Reporting schedule execution date, 
		//and period start date of existing schedule is greater than period end date of new schedule
		reportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodStartDate(), -30));
		reportingScheduleInfo.setPeriodEndDate(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodStartDate(), -25));
		reportingScheduleInfo.getExecutionDates().clear();
		reportingScheduleInfo.getExecutionDates().add(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getExecutionDates().get(1),1));
		actMessage = lm.addHIPReportingScheduleFromHIPReportingSchedulesListPgWithClickOK(reportingScheduleInfo);
		this.verifyMessage(message_exeGreaterExe, actMessage);
		
		//clear up
		this.clearupHIPReportingScheduleByLicenseYear(reportingScheduleInfo.getLicenseYear(),schema);
		this.clearupHIPReportingScheduleByLicenseYear(existReportingScheduleInfo.getLicenseYear(),schema);
		
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
		
		existReportingScheduleInfo.setStatus(OrmsConstants.ACTIVE_STATUS);
		existReportingScheduleInfo.setLicenseYear(String.valueOf(Integer.valueOf(lm.getFiscalYear(schema)) + 5));
		existReportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), 61));
		existReportingScheduleInfo.setPeriodEndDate(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodStartDate(), 5));
		List<String> existExecutionDates = new ArrayList<String>();
		existExecutionDates.add(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodStartDate(), 1));
		existExecutionDates.add(DateFunctions.getDateAfterGivenDay(existReportingScheduleInfo.getPeriodEndDate(), 6));
		existReportingScheduleInfo.setExecutionDates(existExecutionDates);
		
		reportingScheduleInfo.setStatus(OrmsConstants.ACTIVE_STATUS);
		reportingScheduleInfo.setLicenseYear(String.valueOf(Integer.valueOf(lm.getFiscalYear(schema)) + 5));
		reportingScheduleInfo.setPeriodStartDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), 62));
		reportingScheduleInfo.setPeriodEndDate(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 5));
		List<String> executionDates = new ArrayList<String>();
		executionDates.add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 1));
		executionDates.add(DateFunctions.getDateAfterGivenDay(reportingScheduleInfo.getPeriodStartDate(), 6));
		reportingScheduleInfo.setExecutionDates(executionDates);
		
		message_noPeriodStartDate = "The Period Start Date is required. Please enter the Period Start Date using the format Ddd Mmm dd yyyy in the field provided.";
		message_noPeriodEndDate = "The Period End Date is required. Please enter the Period End Date using the format Ddd Mmm dd yyyy in the field provided.";
		message_startGreaterThanEnd = "The Period Start Date must not be later than the Period End Date. Please change your entries.";
		message_noExecution = "At least one Report Execution Date entry is not a valid date. Please enter the Report Execution Date using the format Ddd Mmm dd yyyy.";
		message_exeLessThanPerStart = "At least one Report Execution Date entry is on or before the Period Start Date. Each Report Execution Date entry must be later than the Period Start Date.";
		message_dupExecution = "Duplicate Report Execution Date entries have been specified. Each Report Execution Date entry must be unique.";
		message_noExeGreaterPerEnd = "None of the specified Report Execution Dates is greater than the specified Period End Date. Exactly one Report Execution Date entry must be later than the Period End Date.";
		message_mulExeGreaterPerEnd = "Multiple specified Report Execution Dates are greater than the specified Period End Date. Exactly one Report Execution Date entry must be later than the Period End Date.";
		message_exeGreaterExe = "At least one Report Execution Date entry is greater than a Report Execution Date entry in another active HIP Reporting Schedule. Conflicting Report Execution Dates are not allowed across all active HIP Reporting Schedules.";
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
	
	private void verifyMessage(String expMessage, String actMessage){
		LicMgrAddHIPReportingScheduleWidget addHIPReportingSchedulePg = LicMgrAddHIPReportingScheduleWidget.getInstance();
		LicMgrHIPReportingSchedulesListPage hipReportingScheduleListPg = LicMgrHIPReportingSchedulesListPage.getInstance();
		
		logger.info("Verify error message.");
		boolean result = MiscFunctions.compareResult("Error Message", expMessage, actMessage);
		if(!result){
			throw new ErrorOnPageException("Message is not correct.");
		}else logger.info("Message is correct");
		
		addHIPReportingSchedulePg.clickCancel();
		ajax.waitLoading();
		hipReportingScheduleListPg.waitLoading();
	}

}
