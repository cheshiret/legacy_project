package com.activenetwork.qa.awo.supportscripts.support.resourcemgr;

import com.activenetwork.qa.awo.datacollection.legacy.DistCycleDetailsData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepOnePage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepThreePage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepTwoPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSelectReportPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;

public class AddDistCycleDetails extends SupportCase {
	/**
	 * Script Name : <b>AddDistCycleDetails</b> Generated : <b>Feb 2, 2010
	 * 2:58:33 AM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/02
	 * @author Sara Wang
	 */

	private LoginInfo login = new LoginInfo();
	private DistCycleDetailsData dd = new DistCycleDetailsData();
	private String scheduleID = "";
	private boolean logIn = false;// Don't login.
	private String flagForPeriodCloseDate = "no"; // flag in
	// ResMgrSchedulerDetailStepTwoPage
	private String flagEmailCompressCheckbox = "no"; // flag in
	// ResMgrSchedulerDetailStepTwoPage
	private String flagDailyWeekdaysRadio = "no"; // flag in
	// ResMgrSchedulerDetailStepThreePage
	private String flagMonthlyTheRadio = "no"; // flag in
	// ResMgrSchedulerDetailStepThreePage
	private String flagEndDate = "no"; // flag in
	// ResMgrSchedulerDetailStepThreePage

	private ResourceManager rm = ResourceManager.getInstance();
	private ResMgrSchedulerDetailStepOnePage rmScheDetailPgStepOnepg = ResMgrSchedulerDetailStepOnePage
			.getInstance();
	private ResMgrSchedulerDetailStepTwoPage rmScheDetailPgStepTwopg = ResMgrSchedulerDetailStepTwoPage
			.getInstance();
	private ResMgrSchedulerDetailStepThreePage rmScheDetailPgStepThreepg = ResMgrSchedulerDetailStepThreePage
			.getInstance();
	private ResMgrSchedulerPage rmSchedulerPg = ResMgrSchedulerPage
			.getInstance();

	public void wrapParameters(Object[] param) {

		startpoint = 0;// the start point in the data pool
		endpoint = 3;// the end point in the data pool

		dataSource = casePath + "/" + caseName;

		login.url = "https://raon-web1.qa.reserveamerica.com:6401/";
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
		login.envType = "QA";
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		dd.scheduleReportGroup = "All";
		dd.reportId = "Distribution Cycle Detail Report";// change data
		// collection

		// log informaiton
		logMsg = new String[10];
		logMsg[0] = "reportType";
		logMsg[1] = "format";

		logMsg[2] = "distLocation";
		logMsg[3] = "frequency";
		logMsg[4] = "subject";

		logMsg[5] = "frequency2";
		logMsg[6] = "startDate";
		logMsg[7] = "endDate";
		logMsg[8] = "scheduleId";
		logMsg[9] = "result";
	}

	public void execute() {

		// login in Resource Manager
		if (!logIn) {
			rm.loginResourceManager(login);
			logIn = true;
		}

		rm.gotoReportPage();

		// set infotmation in ResMgrSelectReportPage
		this.setInformationInRequestReport();

		// set information in SchedulerDetailStepOnePage
		this.setInformationInSchedulerDetailStepOnePage();

		// set information in SchedulerDetailStepTwoPage
		this.setInformationInSchedulerDetailStepTwoPage();

		// set information in SchedulerDetailStepThreePage
		this.setInformationInSchedulerDetailStepThreePage();

		// goto ResMgrSchedulerPage
		scheduleID = rmSchedulerPg.getScheduleID();
		logMsg[8] = scheduleID;

		if (rmSchedulerPg.exists()) {
			logMsg[9] = "Successful";
		}

	}

	public void getNextData() {

		// ResMgrSchedulerDetailStepOnePage information
		dd.reportType = dpIter.dpString("ReportType");
		dd.format = dpIter.dpString("Format");

		// ResMgrSchedulerDetailStepTwoPage information
		dd.distLocation = dpIter.dpString("DistLocation");
		dd.frequency = dpIter.dpString("Frequency");
		dd.period = dpIter.dpString("Period");
		dd.dateType = dpIter.dpString("DateType");
		flagForPeriodCloseDate = dpIter.dpString("FlagForAfterOrBeforeDate");
		dd.periodOnDay = dpIter.dpString("PeriodOnDay");
		dd.beforeAfterRunDate = dpIter.dpString("BeforeAfterRunDate");
		dd.recipientType = dpIter.dpString("RecipientType");
		dd.recipient = dpIter.dpString("Recipient");
		dd.state = dpIter.dpString("State");
		dd.district = dpIter.dpString("District");
		dd.fieldOffice = dpIter.dpString("FieldOffice");
		dd.park = dpIter.dpString("Park");
		dd.paymentGroup = dpIter.dpString("PaymentGroup");
		dd.email = dpIter.dpString("Email");
		dd.personList = dpIter.dpString("PersonList");
		dd.subject = dpIter.dpString("Subject");
		flagEmailCompressCheckbox = dpIter
				.dpString("FlagEmailCompressCheckbox");

		// ResMgrSchedulerDetailStepThreePage information
		dd.hour = dpIter.dpString("Hour");
		dd.min = dpIter.dpString("Min");
		dd.ampm = dpIter.dpString("AMPM");

		dd.frequency2 = dpIter.dpString("Frequency2");

		// Daily information
		flagDailyWeekdaysRadio = dpIter.dpString("FlagDailyWeekdaysRadio");

		dd.dailyDay = dpIter.dpString("DailyDay");

		// Weekly imformation
		dd.timesperweek = dpIter.dpString("TimesPerWeek");
		dd.dayOfWeek = dpIter.dpString("DayOfWeek");

		// Monthly information
		flagMonthlyTheRadio = dpIter.dpString("FlagMonthlyTheRadio");

		dd.dayDayCount = dpIter.dpString("DayDayCount");
		dd.dayMonthCount = dpIter.dpString("DayMonthCount");

		dd.theWeekdayCount = dpIter.dpString("TheWeekdayCount");
		dd.theWeekday = dpIter.dpString("TheWeekday");
		dd.theMonthCount = dpIter.dpString("TheMonthCount");

		dd.startDate = dpIter.dpString("StartDate");
		flagEndDate = dpIter.dpString("FlagEndDate");

		dd.endDate = dpIter.dpString("EndDate");
		dd.email2 = dpIter.dpString("Email2");
		dd.personList2 = dpIter.dpString("PersonList2");

		logMsg[0] = dd.reportType;
		logMsg[1] = dd.format;

		logMsg[2] = dd.distLocation;
		logMsg[3] = dd.frequency;
		logMsg[4] = dd.subject;

		logMsg[5] = dd.frequency2;
		logMsg[6] = dd.startDate;
		if (null != dd.endDate && dd.endDate.length() > 0) {
			logMsg[7] = dd.endDate;
		} else {
			logMsg[7] = "Null";
		}
		logMsg[8] = "scheduleId";
		logMsg[9] = "Fail";
	}

	// set infotmation in ResMgrSelectReportPage
	public void setInformationInRequestReport() {

		ResMgrSelectReportPage rmReportPg = ResMgrSelectReportPage.getInstance();

		rmReportPg.waitLoading();

		if (dd.scheduleReportGroup != null && dd.scheduleReportGroup.length() > 0) {
			rmReportPg.selectReportGroup(dd.scheduleReportGroup);
			rmReportPg.waitLoading();
		}

		if (dd.reportId != null && dd.reportId.length() > 0) {
			rmReportPg.selectReport(dd.reportId);
		}

		rmReportPg.clickSchedule();

		rmScheDetailPgStepOnepg.waitLoading();
	}

	// set information in SchedulerDetailStepOnePage
	public void setInformationInSchedulerDetailStepOnePage() {

		rmScheDetailPgStepOnepg.waitLoading();

		if (dd.reportType != null && dd.reportType.length() > 0) {
			rmScheDetailPgStepOnepg.selectScheduleType(dd.reportType);
			rmScheDetailPgStepOnepg.waitLoading();
		}

		if (dd.format != null && dd.format.length() > 0 ) {
			rmScheDetailPgStepOnepg.selectScheduleReportFormat(dd.format);
		}

		rmScheDetailPgStepOnepg.clickNext();

		rmScheDetailPgStepTwopg.waitLoading();
	}

	// set information in SchedulerDetailStepTwoPage
	public void setInformationInSchedulerDetailStepTwoPage() {

		rmScheDetailPgStepTwopg.waitLoading();

		if (dd.distLocation != null && dd.distLocation.length() > 0) {
			rmScheDetailPgStepTwopg.selectDistLocation(dd.distLocation);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.frequency != null && dd.frequency.length() > 0) {
			rmScheDetailPgStepTwopg.selectFrequency(dd.frequency);
		}

		if (dd.period != null && dd.period.length() > 0) {
			rmScheDetailPgStepTwopg.selectDisbursementPeriod(dd.period);
		}

		if (dd.dateType != null && dd.dateType.length() > 0) {
			rmScheDetailPgStepTwopg.selectDateType(dd.dateType);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (flagForPeriodCloseDate.equalsIgnoreCase("yes")) {
			rmScheDetailPgStepTwopg.selectBeforOrAfterRadio();
			rmScheDetailPgStepTwopg.waitLoading();

			rmScheDetailPgStepTwopg.setNumberOfStartDate(dd.periodOnDay);
			rmScheDetailPgStepTwopg
					.selectBeforeAfterStartDate(dd.beforeAfterRunDate);
		}

		if (dd.recipientType != null && dd.recipientType.length() > 0) {
			rmScheDetailPgStepTwopg.selectRecipientType(dd.recipientType);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.reportId != null && dd.reportId.length() > 0) {
			rmScheDetailPgStepTwopg.selectRecipient(dd.recipient);
		}

		if (dd.agency != null && dd.agency.length() > 0) {
			rmScheDetailPgStepTwopg.selectAgency(dd.agency);
		}

		if (dd.state != null && dd.state.length() > 0) {
			rmScheDetailPgStepTwopg.selectState(dd.state);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.district != null && dd.district.length() > 0) {
			rmScheDetailPgStepTwopg.selectDistrict(dd.district);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.fieldOffice != null && dd.fieldOffice.length() > 0) {
			rmScheDetailPgStepTwopg.selectFieldOffice(dd.fieldOffice);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.park != null && dd.park.length() > 0) {
			rmScheDetailPgStepTwopg.selectFacilityID(dd.park);
			rmScheDetailPgStepTwopg.waitLoading();
		}

		if (dd.paymentGroup != null && dd.paymentGroup.length() > 0) {
			rmScheDetailPgStepTwopg.selectPaymentGroup(dd.paymentGroup);
		}
		if (dd.email != null && dd.email.length() > 0) {
			rmScheDetailPgStepTwopg.addNewEmailAddr(dd.email);
		}

		if (dd.personList != null && dd.personList.length() > 0) {
			rmScheDetailPgStepTwopg.selectEmailAddressForAdd(dd.personList);
		}

		if (dd.subject != null && dd.subject.length() > 0) {
			rmScheDetailPgStepTwopg.setSchedulerEmailSubject(dd.subject);
		}

		if (flagEmailCompressCheckbox.equalsIgnoreCase("yes"))
			rmScheDetailPgStepTwopg.selectEmailCompressCheckbox();

		// reset periodOnDay becouse of reflesh page
		if (flagForPeriodCloseDate.equalsIgnoreCase("yes")) {
			rmScheDetailPgStepTwopg.setNumberOfStartDate(dd.periodOnDay);
			rmScheDetailPgStepTwopg
					.selectBeforeAfterStartDate(dd.beforeAfterRunDate);
		}

		rmScheDetailPgStepTwopg.clickNext();

		rmScheDetailPgStepThreepg.waitLoading();
	}

	// set information in SchedulerDetailStepThreePage
	public void setInformationInSchedulerDetailStepThreePage() {

		rmScheDetailPgStepThreepg.waitLoading();

		if (dd.hour != null && dd.hour.length() > 0) {
			rmScheDetailPgStepThreepg.selectHour(dd.hour);
		}

		if (dd.min != null && dd.min.length() > 0) {
			rmScheDetailPgStepThreepg.selectMinute(dd.min);
		}

		if (dd.ampm != null && dd.ampm.length() > 0) {
			rmScheDetailPgStepThreepg.selectAmpm(dd.ampm);
		}

		if (dd.frequency2.equalsIgnoreCase("Once")) {
			rmScheDetailPgStepThreepg
					.selectOnceDailyWeeklyMonthlyRadio(dd.frequency2);

		} else if (dd.frequency2.equalsIgnoreCase("Daily")) {

			rmScheDetailPgStepThreepg
					.selectOnceDailyWeeklyMonthlyRadio(dd.frequency2);
			rmScheDetailPgStepThreepg.waitLoading();

			if (flagDailyWeekdaysRadio.equalsIgnoreCase("yes")) {
				rmScheDetailPgStepThreepg.selectDailyWeekdaysRadio();

			} else {
				rmScheDetailPgStepThreepg.selectDailyEveryRadio();
				rmScheDetailPgStepThreepg.selectDailyDays(dd.dailyDay);
			}

		} else if (dd.frequency2.equalsIgnoreCase("Weekly")) {
			rmScheDetailPgStepThreepg
					.selectOnceDailyWeeklyMonthlyRadio(dd.frequency2);
			rmScheDetailPgStepThreepg.waitLoading();

			rmScheDetailPgStepThreepg.selectWeeklyTimesPerweek(dd.timesperweek);

			if (dd.dayOfWeek != null && dd.dayOfWeek.length() > 0) {
				rmScheDetailPgStepThreepg
						.selectWeeklyDayOfWeekCheckBox(dd.dayOfWeek);
			}

		} else if (dd.frequency2.equalsIgnoreCase("Monthly")) {
			rmScheDetailPgStepThreepg
					.selectOnceDailyWeeklyMonthlyRadio(dd.frequency2);
			rmScheDetailPgStepThreepg.waitLoading();

			if (flagMonthlyTheRadio.equalsIgnoreCase("yes")) {
				rmScheDetailPgStepThreepg.selectMonthlyTheRadio();

				if (dd.theWeekdayCount != null && dd.theWeekdayCount.length() > 0) {
					rmScheDetailPgStepThreepg
							.selectMonthlyTheWeekdayCount(dd.theWeekdayCount);
				}

				if (dd.theWeekday != null && dd.theWeekday.length() > 0 ) {
					rmScheDetailPgStepThreepg
							.selectMonthlyTheWeekday(dd.theWeekday);
				}

				if (dd.theMonthCount != null && dd.theMonthCount.length() > 0 ) {
					rmScheDetailPgStepThreepg
							.selectMonthlyTheMonthCount(dd.theMonthCount);
				}

			} else {
				rmScheDetailPgStepThreepg.selectMonthlyDayRadio();

				if (dd.dayDayCount != null && dd.dayDayCount.length() > 0) {
					rmScheDetailPgStepThreepg
							.selectMonthlyDayDayCount(dd.dayDayCount);
				}

				if (dd.dayMonthCount != null && dd.dayMonthCount.length() > 0 ) {
					rmScheDetailPgStepThreepg
							.selectMonthlyDayMonthCount(dd.dayMonthCount);
				}

			}

		}

		if (dd.frequency2.equalsIgnoreCase("Once")) {
			rmScheDetailPgStepThreepg.setOnceStartDate(dd.startDate);

		} else {
			rmScheDetailPgStepThreepg.setStartDate(dd.startDate);

			if (flagEndDate.equalsIgnoreCase("yes")) {
				rmScheDetailPgStepThreepg.selectEndDateRadio();
				rmScheDetailPgStepThreepg.setEndDate(dd.endDate);
			}
		}

		if (dd.email2 != null && dd.email2.length() > 0 ) {
			rmScheDetailPgStepThreepg.addNewEmailAddr(dd.email2);
		}

		if (dd.personList2 != null && dd.personList2.length() > 0) {
			rmScheDetailPgStepThreepg.selectEmailAddress(dd.personList2);
		}

		rmScheDetailPgStepThreepg.clickSaveSchedule();

		rmSchedulerPg.waitLoading();
	}

}
