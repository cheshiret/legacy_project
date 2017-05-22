/*
 * Created on May 1, 2009
 */

package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinLocationPage;
import com.activenetwork.qa.awo.pages.orms.common.bulletin.OrmsBulletinSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsDHTMLReportPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrJreportPopupPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepOnePage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepThreePage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerDetailStepTwoPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSelectReportPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSendReportPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrTodayRequestRptPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrRecipientListDetailsPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrRecipientsMainPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.recipient.ResMgrSelectFacilityMainPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.ExcelParser;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author raonqa
 * 
 */
public class ResourceManager extends Orms {
	private static ResourceManager _instance = null;

	public static ResourceManager getInstance() {
		if (null == _instance)
			_instance = new ResourceManager();

		return _instance;
	}

	private ResourceManager() {

	}

	/** Login ResourceManager */
	public void loginResourceManager(LoginInfo login) {
		loginResourceManager(login,true);
	}

	/**
	 * This method is a overload method,used for improve performance
	 * 
	 * @param login
	 * @param newBrowser
	 *            -determined whether we need to open a new browser
	 */
	public void loginResourceManager(LoginInfo login, boolean newBrowser) {
		ResMgrHomePage resHmPg = ResMgrHomePage.getInstance();
		login(login,"Resource Manager",newBrowser);
		resHmPg.waitLoading();
	}

	/** Logout ResourceManager */
	public void logoutResourceManager() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();

		logger.info("logout Resource Manager.");

		rmTopMnPg.clickSignOut();
		ormsHmPg.waitLoading();
	}

	/**
	 * This method used goto Report recipient main page
	 * 
	 */
	public void gotoReportRecipientPage() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		ResMgrRecipientsMainPage mainPg = ResMgrRecipientsMainPage
		.getInstance();

		logger.info("Goto Report Recipient Main Page.");

		rmTopMnPg.clickReportRecipients();
		mainPg.waitLoading();
	}

	/**
	 * This method used goto Scheduler main page
	 * 
	 */
	public void gotoSchedulerPage() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		ResMgrSchedulerPage repSchedulePg = ResMgrSchedulerPage.getInstance();

		logger.info("Goto Scheduler Main Page.");

		rmTopMnPg.clickScheduler();
		repSchedulePg.waitLoading();
	}

	/**
	 * This method used goto report main page
	 * 
	 */
	public void gotoReportPage() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		ResMgrSelectReportPage rmReportPg = ResMgrSelectReportPage
		.getInstance();

		logger.info("Goto report Main Page.");

		rmTopMnPg.clickReports();
		rmReportPg.waitLoading();
	}

	/**
	 * This method used to add a new Report Recipient
	 * 
	 * @param rd
	 */
	public void addNewReportRecipient(ReportData rd) {
		ResMgrRecipientsMainPage mainPg = ResMgrRecipientsMainPage
		.getInstance();
		ResMgrSelectFacilityMainPage selectFacilPg = ResMgrSelectFacilityMainPage
		.getInstance();
		ResMgrRecipientListDetailsPage detailPg = ResMgrRecipientListDetailsPage
		.getInstance();

		logger.info("Start to Add a New Report Recipient.");

		mainPg.clickAddNew();
		selectFacilPg.waitLoading();
		selectFacilPg.searchByFacilityName(rd.park);
		selectFacilPg.selectFirstFacilityRadio();
		selectFacilPg.gotoNextStep();
		detailPg.waitLoading();
		detailPg.setupRecipientInfo(rd);
		mainPg.waitLoading();
	}

	/**
	 * This method used to add a new report scheduler
	 * 
	 * @param scheduleType
	 *            -Scheduler Type
	 * @param rd
	 *            -ReportData
	 * @return new added schedule Id
	 */
	public String addNewReportScheduler(ScheduleData schedule, ReportData rd) {
		ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();
		ResMgrSchedulerDetailStepOnePage stepOnePg = ResMgrSchedulerDetailStepOnePage
		.getInstance();
		ResMgrSchedulerDetailStepTwoPage stepTwoPg = ResMgrSchedulerDetailStepTwoPage
		.getInstance();
		ResMgrSchedulerDetailStepThreePage stepThreePg = ResMgrSchedulerDetailStepThreePage
		.getInstance();

		logger.info("Start to Add New Report Scheduler.");

		schedulePg.clickAddNew();
		stepOnePg.waitLoading();
		stepOnePg.enterInfoForStepOne(schedule.scheduleType, rd);
		stepOnePg.clickNext();
		stepTwoPg.waitLoading();
		stepTwoPg.enterInfoForStepTwo(rd, schedule.scheduleType);
		stepTwoPg.setSchedulerEmailSubject(rd.emailSubject);
		stepTwoPg.clickNext();
		stepThreePg.waitLoading();
		stepThreePg.enterInfoForStepThree(schedule);
		stepThreePg.clickSaveSchedule();
		ajax.waitLoading();
		schedulePg.waitLoading();
		schedule.scheduleId = schedulePg.getScheduleID();

		return schedule.scheduleId;
	}

	
	public void searchAndActivateScheduleByID(String id)
	{
		ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();
		
		logger.info("Search and Activate schedule, id of which is-->"+id);
		schedulePg.searchByScheduleId(id);
		schedulePg.activateSchedule(id);		
	}
	
	/**
	 * This method used to switch to Job page from schedule page.
	 * 
	 */
	public void switchToScheduleJobPage() {
		ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();
		ResMgrSchedulerJobPage jobPg = ResMgrSchedulerJobPage.getInstance();

		logger.info("Switch to Schedule Job Page.");

		schedulePg.clickJobs();
		jobPg.waitLoading();
	}

	/** Goto Bulletins Main Page. */
	public void gotoBulletinsPage() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		OrmsBulletinSearchPage buletinPage = OrmsBulletinSearchPage
		.getInstance();

		logger.info("Goto Bulletin Page.");

		rmTopMnPg.clickBulletins();
		buletinPage.waitLoading();
	}

	/**
	 * This method used to add a new bulletin
	 * 
	 * @param bulletin
	 * @return- new added bulletin id
	 */
	public String addNewBulletin(BulletinInfo bulletin) {
		OrmsBulletinSearchPage buletinPg = OrmsBulletinSearchPage.getInstance();
		OrmsBulletinLocationPage locPg = OrmsBulletinLocationPage.getInstance();
		OrmsBulletinDetailPage detailPg = OrmsBulletinDetailPage.getInstance();

		logger.info("Start to Add New Bulletin Info.");

		buletinPg.clickAddNewBulletin();
		locPg.waitLoading();
		locPg.searchLocation(bulletin.location, bulletin.locCategory);
		locPg.clickSelect();
		detailPg.waitLoading();
		String bulletinId = detailPg.setupBulletinDetail(bulletin);
		buletinPg.waitLoading();

		return bulletinId;
	}

	/**
	 * This method used to go back home page
	 */
	public void goHomePage() {
		ResMgrTopMenuPage rmTopMnPg = ResMgrTopMenuPage.getInstance();
		ResMgrHomePage homePg = ResMgrHomePage.getInstance();

		logger.info("Goto Home Page.");

		rmTopMnPg.clickHome();
		homePg.waitLoading();
	}

	/**
	 * This method used to verify bulletin display correct
	 * 
	 * @param headLine
	 *            -bulletin headline
	 * @param shouldExist
	 *            - mark bulletin should display or not
	 */
	public void verifyBulletinDisplay(String headLine, boolean shouldExist) {
		ResMgrHomePage homePg = ResMgrHomePage.getInstance();

		logger.info("Verify Bulletin displayed in Resource Home Page.");

		if (shouldExist && !homePg.checkBulletinExists(headLine)) {
			throw new ErrorOnPageException("Bulletin " + headLine
					+ " not displayed in Current Page.");
		}
		if (!shouldExist && homePg.checkBulletinExists(headLine)) {
			throw new ErrorOnPageException("Bulletin " + headLine
					+ " should not displayed in Current Page.");
		}
	}

	/**
	 * This method used to get report date error message
	 * 
	 * @param rd
	 * @return error msg
	 */
	public String getReportDateErrorMesg(String startDate, String endDate) {
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();

		logger.info("Get error message for report date");
		
		alertPg.setDismissible(false);

		String alertMsg = "";
		if (null != startDate && startDate.length() > 0) {
			rmCriteriaPg.setStartDate(startDate);
		}
		if (null != endDate && endDate.length() > 0) {
			alertMsg = rmCriteriaPg.setEndDateWithInvalidDate(endDate);
			if(!StringUtil.isEmpty(alertMsg) && !alertMsg.contains("YYYYMMDD")) {
				//ignore Date Component validation message: Invalid date. The date format should be: YYYYMMDD
				return alertMsg;
			}
		}

		Object page0 = browser.waitExists(alertPg,rptCriteriaPg);
		
		if(page0 == alertPg){
			alertMsg = alertPg.text();
			alertPg.dismiss();
			rmCriteriaPg.waitLoading();
		}else{
			rmCriteriaPg.clickOk();
			Object page = browser.waitExists(alertPg, rmSendRptPg, rmOnlineReport, rptCriteriaPg);
			if(page == rptCriteriaPg){
				alertMsg = rptCriteriaPg.getErrorMsg();
			}else if (page == alertPg) {
				alertMsg = alertPg.text();
				alertPg.dismiss();
			}
		}
		
		return alertMsg;
	}

	/**
	 * This method used to verify report date
	 * 
	 * @param startDate
	 *            , endDate, errorMessage
	 */
	public void verifyReportDate(String startDate, String endDate,
			String errorMessage) {
		logger.info("Veriy Report Date Correct.");
		String msg = getReportDateErrorMesg(startDate, endDate).replaceAll(":  ", ": ");
		if (!msg.matches(".*"+errorMessage+".*")) {
			logger.info("Expect error message: "+errorMessage);
			logger.info("Actual error message: "+msg);
			throw new ErrorOnDataException("The actual error message is different from the expect one.");
		}
	}

	/**
	 * This method used to get lottery name error message
	 * 
	 * @param lotteryName
	 * @return error message
	 */
	public String getLotteryNameErrorMesg(String lotteryName) {
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();

		logger.info("Get error message for lottery name");

		String alertMsg = "";
		if (null != lotteryName && lotteryName.length() > 0) {
			rmCriteriaPg.setLotteryName(lotteryName);
		}
		rmCriteriaPg.clickOk();
		Object page = browser.waitExists(rmCriteriaPg, rmSendRptPg,
				rmOnlineReport);
		if (page == rmCriteriaPg) {
			alertMsg = rmCriteriaPg.getErrorMsg();
		}

		return alertMsg;
	}
	
	public String checkReportCriteriaValidation(ReportData rd){
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
		
		rmCriteriaPg.setReportCriteria(rd);
		rmCriteriaPg.waitLoading();
		rmCriteriaPg.clickOk();
		String alertMsg = "";
		Object page = browser.waitExists(alertPg, rmSendRptPg, rmOnlineReport, rptCriteriaPg);
		if(page == rptCriteriaPg){
			alertMsg = rptCriteriaPg.getErrorMsg();
		}else if (page == alertPg) {
			alertMsg = alertPg.text();
			alertPg.dismiss();
		}
		return alertMsg;
	}

	/**
	 * This method used to verify the lottery name error message
	 * 
	 * @param lotteryName
	 * @param errorMessage
	 */
	public void verifyLotteryNameErrorMesg(String lotteryName,
			String errorMessage) {
		logger.info("Verify Report Lottery Name Correct");

		if (!getLotteryNameErrorMesg(lotteryName).matches(errorMessage)) {
			throw new ErrorOnDataException("Error message doesn't correct");
		}
	}

	/**
	 * This method used to get the lottery schedule error message
	 * 
	 * @param lotterySchedule
	 * @return error message
	 */
	public String getLotteryScheduleErrorMesg(String lotterySchedule) {
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();

		logger.info("Ger error message for lottery schedul");

		String alertMsg = "";

		if (null != lotterySchedule && lotterySchedule.length() > 0) {
//			rmCriteriaPg.setStartDate(lotterySchedule);
			rmCriteriaPg.selectLotterySchedule(lotterySchedule); //Update by Sara at 5/15/2013, this should select lottery schedule, not set start date
			rmCriteriaPg.waitLoading();
		}

		rmCriteriaPg.clickOk();
		Object page = browser.waitExists(rmCriteriaPg, rmSendRptPg,
				rmOnlineReport);

		if (page == rmCriteriaPg) {
			alertMsg = rmCriteriaPg.getErrorMsg();
		}

		return alertMsg;
	}

	/**
	 * This method used to verify the lottery schedule error message
	 * 
	 * @param lotterySchedul
	 * @param errorMessage
	 */
	public void verifyLotteryScheduleErrorMesg(String lotterySchedul,
			String errorMessage) {
		logger.info("Verify Report Lottery Schedule Correct");

		if (!getLotteryScheduleErrorMesg(lotterySchedul).matches(errorMessage)) {
			throw new ErrorOnDataException("Error message doesn't correct");
		}
	}

	/**
	 * This method used to go to today request panel
	 */
	public void gotoTodayRequestRptPg() {
		ResMgrHomePage rmHmPg = ResMgrHomePage.getInstance();
		ResMgrTodayRequestRptPage todayRequestPg = ResMgrTodayRequestRptPage
		.getInstance();

		logger.info("Goto Today Requested Reports Page.");

		rmHmPg.clickTodayRequestedReports();
		todayRequestPg.waitLoading();
	}

	/**
	 * This method used to check given report exist in the drop down list
	 * 
	 * @param reportName
	 * @return
	 */
	public boolean checkReportExists(String reportName) {
		ResMgrSelectReportPage rmSelectRptPg = ResMgrSelectReportPage
		.getInstance();

		logger.info("Start to Check " + reportName + " Exists.");

		rmSelectRptPg.selectReportGroup("All");
		rmSelectRptPg.waitLoading();

		return rmSelectRptPg.checkReportExists(reportName);
	}

	/**
	 * This method executes the work flow of select one report The work flow
	 * starts from the ResMgrHomePage and ends at the ResMgrSelectReportPage The
	 * work flow's execute sequence is ResMgrHomePage-->ResMgrSelectReportPage
	 * 
	 * @param rd
	 *            ReportData
	 */
	public void selectOneRpt(String group, String reportName) {
		ResMgrHomePage rmHmPg = ResMgrHomePage.getInstance();
		ResMgrSelectReportPage rmSelectRptPg = ResMgrSelectReportPage
		.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();

		logger.info("Select " + reportName + " on the report selection page");

		rmHmPg.clickReports();
		rmSelectRptPg.waitLoading();
		
		rmSelectRptPg.clickRequestReport();
		rmSelectRptPg.waitLoading();		

		if (group != null && !"".equals(group)) {
			rmSelectRptPg.selectReportGroup(group);
			rmSelectRptPg.waitLoading();
		} else
			logger.info("No group given,by default!");

		rmSelectRptPg.selectReport(reportName);
		rmSelectRptPg.clickOK();

		rmCriteriaPg.waitLoading();
	}

	public void selectLotteryName(String lotteryName) {
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();

		logger.info("Select " + lotteryName
				+ " on the lottery results report page");

		if (lotteryName != null && !"".equals(lotteryName)) {
			rmCriteriaPg.selectLotteryName(lotteryName);
		} else
			logger.info("No lottery name given, by default!");
		rmCriteriaPg.waitLoading();
	}

	/**
	 * Run a report output some log info The work flow starts from the
	 * ResMgrReportCriteriaPage and ends at the ResMgrHomePage
	 * 
	 * @param path
	 *            is the file path we want to generate file, for DHTML report,
	 *            it used to determine whether we need to generate template
	 *            files, if it is null, we will not generate file for DHTML
	 *            report
	 */
	public String runSpecialRpt(ReportData rd, String path) {
		ResMgrHomePage rmHmPg = ResMgrHomePage.getInstance();
		ResMgrSelectReportPage rmSelectRptPg = ResMgrSelectReportPage
		.getInstance();
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		FileDownloadDialogPage filePg = FileDownloadDialogPage.getInstance();

		logger.info("Run " + rd.reportName);

		String fileName = "";
		rmCriteriaPg.setReportCriteria(rd);

		// output log
		String info = rd.reportName + "<>";
		info += (rd.reportType.length() > 0 ? rd.reportType + "  " : "")
		+ rd.reportFormat + " ";
		info += (rd.productType.length() > 0 ? rd.productType + "  " : "")
		+ rd.productType + " ";
		info += (rd.dateType.length() > 0 ? "on " + rd.dateType + " " : "")
		+ "will be generated ";
		info += "via " + rd.deliveryMethod;

		logger.info(info);
		rmCriteriaPg.waitLoading();
		rmCriteriaPg.clickOk();
		if (rd.deliveryMethod.equalsIgnoreCase("email")) {
			rmSendRptPg.waitLoading();
			if(StringUtil.isEmpty(rd.emailSubject)){
				rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
			}
			rmSendRptPg.setEmailSubject(TestProperty.getProperty("mail.report.subject.keyword", "")+rd.emailSubject);
			rmSendRptPg.clickOK();
			rmSelectRptPg.waitLoading();
			rmSelectRptPg.clickHome();
		} else if (rd.deliveryMethod.equalsIgnoreCase("online")) {
			File file = new File(path);
			if (rd.reportFormat.equalsIgnoreCase("pdf")
					|| rd.reportFormat.equalsIgnoreCase("xls")
					|| rd.reportFormat.equalsIgnoreCase("csv")|| rd.reportFormat.equalsIgnoreCase("text")) {
				filePg.setDismissible(false);
				browser.waitExists(rmOnlineReport,filePg);
				if (!file.exists()) {
					boolean success = file.mkdir();
					if (!success) {
						throw new ItemNotFoundException(
						"Failed To Make Directory.");
					}
				}
				fileName = file.getAbsolutePath()+ "\\";
				rd.fileName = rd.fileName.replaceAll(" ", "");
				if (rd.reportFormat.equalsIgnoreCase("pdf")) {
					fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", "")
							+ rd.reportType.replaceAll(" ", ""):fileName+rd.fileName;
					fileName = fileName + ".pdf";
				} else if (rd.reportFormat.equalsIgnoreCase("xls")) {
					fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ (rd.reportName + rd.dateType).replaceAll(" ", "")
							+ rd.productType
							+ rd.reportType.replaceAll(" ", "")
							+ rd.reportBy.replaceAll(" ", ""):fileName+rd.fileName;
					fileName = fileName + ".xls";
				} else if (rd.reportFormat.equalsIgnoreCase("csv")){
					fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", ""):fileName+rd.fileName;
					fileName = fileName + ".csv";
				}else{
					fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", ""):fileName+rd.fileName;
					fileName = fileName + ".txt";
				}
//				downloadReport(fileName);  Shane[20140310],due to IE9 download issue,skip download report logic
			} else if (rd.reportFormat.equalsIgnoreCase("DHTML")) {
				OrmsDHTMLReportPage dhtmlReport=OrmsDHTMLReportPage.getInstance();
				dhtmlReport.waitLoading();
				boolean generateTemplate = Boolean.parseBoolean(TestProperty.getProperty("generate.dhtml.rpt.tmp"));//initialize from test property file
				if(!generateTemplate){// do not need to generate template file,just return template file name
					return (StringUtil.isEmpty(rd.fileName))? rd.reportName.replaceAll(" ", "")
							+ rd.reportType.replaceAll(" ", "")+ ".dat":fileName+rd.fileName+ ".dat";
				}
				String templatePath = TestProperty.getProperty("orms.report.template.path");//initialize from test property
				String templateName = (StringUtil.isEmpty(rd.fileName))?templatePath+ "\\"+ rd.reportName.replaceAll(" ", "")
						+ rd.reportType.replaceAll(" ", "")+ ".dat":templatePath+rd.fileName+ ".dat";
				try {
					File f = new File(templateName);
					if(!f.exists()){
						f.createNewFile();
					}
					dhtmlReport.parseInto(templateName, false); // save report
					// information for
					// dhtml report
				} catch (IOException e) {
					throw new ItemNotFoundException(e.getMessage());
				}
				File file1 = new File(templatePath + "\\Snapshot");
				if (!file1.exists()) {
					boolean success = file1.mkdir();
					if (!success) {
						throw new ItemNotFoundException(
						"Failed To Make Directory.");
					}
				}
				String snapShop = (StringUtil.isEmpty(rd.fileName))? rd.reportName.replaceAll(" ", ""):rd.fileName;
				dhtmlReport.getSnapshotForEachPage(file1.getAbsolutePath()
						+ "\\" + snapShop, false); // catch
				// snapshot
				// for
				// each
				// page
				// of
				// the
				// report
				System.exit(2);//end test case,as current is generate template mode,skip left check points
			}
			//rmOnlineReport.close();
			rmCriteriaPg.waitLoading();
			rmCriteriaPg.clickHome();
		}
		rmHmPg.waitLoading();
		return fileName;
	}

	/**
	 * This method used to run Adhoc report
	 * 
	 * @param rd
	 * @param table
	 *            -- store the column info you want in the report data
	 * @param groupByTable
	 *            determine the final report group by and order by
	 * @param path
	 *            --path to store the report content
	 */
	public void runAdHocReport(ReportData rd,
			Hashtable<String, List<String>> table,
			Hashtable<String, List<String>> groupByTable, String path) {
		OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		ResMgrJreportPopupPage popupPg = ResMgrJreportPopupPage.getInstance();
		OrmsDHTMLReportPage dhtmlRpt =OrmsDHTMLReportPage.getInstance();

		logger.info("Run a " + rd.reportName);

		rmCriteriaPg.setReportCriteria(rd);
		rmCriteriaPg.clickOk();
		rmOnlineReport.waitLoading();
		dhtmlRpt.waitLoading();
		dhtmlRpt.clickInteractiveView();
		dhtmlRpt.newReport();
		popupPg.waitLoading();
		popupPg.generateCustomizeReport(rd, table, groupByTable);
		dhtmlRpt.pagingSynchronize(LONG_SLEEP);
		rmOnlineReport.waitLoading();

		if (path.equals("")) { // do not need to generate template file
			return;
		}
		try {
			dhtmlRpt.parseInto(path + "\\" + rd.reportName.replaceAll(" ", "")
					+ ".dat", true);
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		dhtmlRpt.getSnapshotForEachPage(path + "\\"
				+ rd.reportName.replaceAll(" ", ""), true); // catch snapshot
		// for each page of
		// the report
	}

	/**
	 * This method used to verify Adhoc report
	 * 
	 * @param templatePath
	 * @param reportName
	 * @param resultPath
	 * @return
	 */
	public boolean verifyAdHocReport(String templatePath, String reportName,
			String resultPath) {
		OrmsDHTMLReportPage report = OrmsDHTMLReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();

		logger.info("Verify " + reportName);

		boolean isCorrect = true;
		try {
			
			List<String> resultMsg = report.matchFrom(templatePath + "\\"
					+ reportName.replaceAll(" ", "") + ".dat", true);
			if (resultMsg.size() > 0) {
				File file = new File(resultPath);
				if (!file.exists()) {
					boolean mkDir = file.mkdirs();
					if (!mkDir) {
						throw new ErrorOnDataException(
						"Fail to Make Directory.");
					}
				}
				for (int i = 0; i < resultMsg.size(); i++) {
					FileUtil.writeLog(resultPath + "\\"
							+ reportName.replaceAll(" ", "") + ".log",
							resultMsg.get(i));
				}
				report.getSnapshotForEachPage(resultPath + "\\"
						+ reportName.replaceAll(" ", "") + "_page", true); // catch
				// snapshot
				// for
				// each
				// page
				// of
				// the
				// report
				logger.error(reportName + " Content Not Correct.");
				isCorrect = false;
			}
			report.close();
			rmCriteriaPg.waitLoading();
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}
	
	public boolean skipVerifyOnlineReport(String templatesPath, String comparedPath){
		logger.info("Skip Online Report Verify due to IE9 download issue.");
		return true;
	}

	/**
	 * Verify One Report by compare the report with given template file
	 * 
	 * @param templatesPath
	 * @param comparedPath
	 */
	public boolean verifyPdfReport(String templatesPath, String comparedPath) {
		logger.info("Verify PDF format Report.");
		boolean isCorrect = true;
		try {
			if (!PDFParser.verifyRptRunDate(comparedPath)) {
				isCorrect = false;
			}
			File file = new File(comparedPath);
			List<String> list = PDFParser.comparePDFFile(templatesPath + "/"
					+ file.getName(), comparedPath);
			for (Object l : list) {
				if (l instanceof String) {
					isCorrect = false;
				} else if (l instanceof List<?>) {
					if (((List<?>) l).size() > 0) {
						logger.error("PDF Format Report Content Not Correct.");
						isCorrect = false;
						return isCorrect;
					}
				} else {
					throw new RuntimeException("Unknown Object Type.");
				}
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * Verify Excel format report content
	 * 
	 * @param templatesPath
	 * @param comparePath
	 */
	public boolean verifyExcelReport(String templatesPath, String comparePath) {
		logger.info("Verify Excel Format Report Content.");

		boolean isCorrect = true;
		List<String> resultMsg = this.compareExcelReport(templatesPath, comparePath);
		if (resultMsg.size() > 0) {
			logger.error("Excel Format Report Content Not Correct.");
			isCorrect = false;
		}

		return isCorrect;
	}
	
	public List<String> compareExcelReport(String templatesPath, String comparePath){
		logger.info("Compare Excel Report from ["+templatesPath+"] and ["+comparePath+"].");
		
		File file = new File(comparePath);
		if (!file.exists()) {
			throw new ItemNotFoundException("File not exist for "+comparePath);
		}
		String fromPath = templatesPath + "\\" + file.getName();
		List<String> resultMsg = ExcelParser.match(fromPath, comparePath);
		return resultMsg;
	}

	/**
	 * This method used to verify text/csv report content correct
	 * 
	 * @param templatePath
	 * @param comparePath
	 * @return
	 */
	public boolean verifyTextReport(String templatePath, String comparePath) {
		logger.info("Verify Text Format Report Content.");

		boolean isCorrect = true;
		File file = new File(comparePath);
		if (!file.exists()) {
			return false;
		}
		String existFile = templatePath + "\\" + file.getName();
		try {
			List<String> resultMsg = FileUtil.compareFile(existFile,
					comparePath);
			if (resultMsg.size() > 0) {
				logger.error("Text/CSV Format Report Content Not Correct.");
				isCorrect = false;
			} else {
				logger.info("Report content Correct.");
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * This method used to verify DHTML report content correct
	 * 
	 * @param templatesPath
	 */
	public boolean verifyDHTMLReport(String templatesPath, String comparePath,
			String resultPath, String fileName) {
		OrmsDHTMLReportPage report = OrmsDHTMLReportPage.getInstance();
		ResMgrHomePage rmHmPg = ResMgrHomePage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();

		logger.info("Verify DHTML Report Content.");

		boolean isCorrect = true;
		try {
			
			List<String> resultMsg = report.matchFrom(templatesPath + "\\"
					+ fileName, false);
			if (resultMsg.size() > 0) {
				File file1 = new File(resultPath);
				if (!file1.exists()) {
					boolean mkDir = file1.mkdirs();
					if (!mkDir) {
						throw new ErrorOnDataException(
						"Fail to Make Directory.");
					}
				}
				for (int i = 0; i < resultMsg.size(); i++) {
					FileUtil.writeLog(resultPath + "\\"
							+ fileName.replace(".dat", ".log"), resultMsg
							.get(i));
				}
				report.getSnapshotForEachPage(resultPath + "\\"
						+ fileName.replace(".dat", "_page"), false); // catch
				// snapshot
				// for
				// each
				// page
				// of
				// the
				// report
				logger.error("DHTML Report Content Not Correct.");
				isCorrect = false;
			}
			report.close();
			rmCriteriaPg.waitLoading();
			rmCriteriaPg.clickHome();
			rmHmPg.waitLoading();
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * This method used to parse mail box to get specific mail
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param attachmentPath
	 * @param emailSubject
	 * @return property contain attachment info
	 */
	public Properties[] getReportFromMailBox(String host, String username,
			String password, String attachmentPath, String emailSubject) {
		return getReportFromMailBox(host, username, password, attachmentPath,null, emailSubject);
	}

	public Properties[] getReportFromMailBox(String host, String username,
			String password, String attachmentPath,String saveAsName, String emailSubject) {
		logger.info("Get report from email box");

		Email e = new Email();
		e.connect(host, username, password);
		RegularExpression subjectPattern = new RegularExpression(emailSubject,
				false);
		String reportFolder=TestProperty.getProperty("mail.report.folder");
		Properties[] pros = e.searchEmail(reportFolder,subjectPattern, attachmentPath,saveAsName,
				CHECK_REPORT_IN_MAILBOX_THRESHOLD,TestProperty.getBooleanProperty("mail.report.delete", false));
		e.disconnect();
		return pros;
	}
	/**
	 * This method used to verify reports which are checked out from mail box
	 * 
	 * @param templatePath
	 * @param pro
	 * @return
	 */
	public boolean verifyEmailReport(String templatePath, Properties[] pros) {
		logger.info("Verify Reports Retrieved From Mail Box.");

		boolean isCorrect = true;
		if (pros == null || pros.length < 1) {
			logger.error("No Email Found.");
			return false;
		} else {
			int count = Integer.parseInt(pros[0].getProperty("attach_count"));
			if (count < 1) {
				logger.error("No Attachment Found.");
				return false;
			}

			for (int i = 0; i < count; i++) {
				String fileName = pros[0].getProperty("attach_" + (i + 1));
				String fileType = fileName.substring(
						fileName.lastIndexOf(".") + 1).trim();
				if (fileType.equalsIgnoreCase("xls")) {
					isCorrect &= verifyExcelReport(templatePath, fileName);
				} else if (fileType.equalsIgnoreCase("pdf")) {
					isCorrect &= verifyPdfReport(templatePath, fileName);
				} else if (fileType.equalsIgnoreCase("csv")
						|| fileType.equalsIgnoreCase("txt")) {
					isCorrect &= verifyTextReport(templatePath, fileName);
				} else if (fileType.equalsIgnoreCase("zip")) {
					File file = new File(fileName);
					isCorrect &= verifyExcelReport(templatePath, unzipReport(
							fileName, file.getParent()));
				} else {
					throw new ItemNotFoundException("Unknown File Type.");
				}
			}

		}
		return isCorrect;
	}

	/**
	 * This method used to verify report results is correct or not
	 * 
	 * @param result
	 */
	public void verifyReportCorrect(boolean result) {
		logger.info("Report content comparsion");

		if (result) {
			logger.info("Report Content Correct.");
		} else {
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
	}

	/**
	 * This method used to unzip report
	 * 
	 * @param zipFile
	 * @param toPath
	 * @return unzip report name
	 */
	public String unzipReport(String zipFile, String toPath) {
		File file = new File(zipFile);
		File file1 = new File(zipFile.substring(0, zipFile.indexOf("."))
				+ ".xls");
		File newFile = null;
		try {
			String[] fileNames = FileUtil.unzip(zipFile, toPath);
			newFile = new File(toPath + "/" + fileNames[fileNames.length - 1].replaceAll("\\d{2,}", ""));//Shane[20130829] replace dynamic date in the zip file
			file.delete();
			newFile.renameTo(file1);
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return file1.getAbsolutePath();
	}

	/**
	 * This method used to download report of delivery method is email
	 * 
	 * @param reportName
	 * @param reportFormat
	 * @param path
	 * @return
	 */
	public String viewEmailReportOnline(String reportName, String reportFormat,
			String path) {
		ResMgrHomePage rmHmPg = ResMgrHomePage.getInstance();
		ResMgrTodayRequestRptPage todayRptPg = ResMgrTodayRequestRptPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		String fileName = "";

		logger.info("View Email Report Online.");

		this.gotoTodayRequestRptPg();
		reportName = reportName.replaceAll(" ", "");
		todayRptPg.viewReport(reportName, reportFormat);
		File file = new File(path);
		rmOnlineReport.waitLoading();

		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException("Failed To Make Directory.");
			}
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			fileName = file.getAbsolutePath() + "\\" + reportName + ".pdf";
		} else if (reportFormat.equalsIgnoreCase("xls")) {
			fileName = file.getAbsolutePath() + "\\" + reportName + ".xls";
		}
		downloadReport(fileName);
		rmOnlineReport.close();
		todayRptPg.waitLoading();
		todayRptPg.clickHome();
		rmHmPg.waitLoading();

		return fileName;
	}

	/**
	 * download a report
	 * 
	 * @param path
	 *            file path and name
	 */
	public void downloadReport(String path) {
		FileDownloadDialogPage filePage = FileDownloadDialogPage.getInstance();
		File file = new File(path);
		if (file.exists()) {
			boolean delFile = file.delete();
			if (!delFile) {
				logger.error("Fail to Delete File.");
			}
		}
		filePage.downloadSaveFile(path);

		int time = 1;
		while (!file.exists()) {
			Browser.sleep(1);
			time++;
			if (time > 120)
				break;
		}
		if (file.exists()) {
			logger.info("Download Report cost " + time + " seconds!");
		} else {
			logger.error("Can not download Report in " + time + " seconds!");
		}

		if (filePage.exists()) {
			filePage.clickClose();
		}
	}

	/**
	 * Verify report selection criteria
	 * 
	 * @param objId
	 * @param expected
	 * @param defaultOpt
	 * @param firstOpt
	 */
	public void VerifyReportSelectionCriteria(String objId,
			List<String> expected, String defaultOpt, String firstOpt) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();

		logger.info("Verify dropdown list options.");

		// Verify if default value is correct.
		if(!objId.contains("FacilityID")){//ignore verify park default value
			String defaultValue = rptCriteriaPg
			.getDefaultValueForDropDownList(objId);
			if (!defaultValue.equals(defaultOpt)) {
				throw new ErrorOnDataException(objId + " default value is not '"
						+ defaultOpt + "'.");
			}
		}
	

		String options = rptCriteriaPg.getAllSelectionCriteria(objId);
		String[] optionsArray = options.split("#");
		if(!objId.contains("FacilityID")){//ignore verify park default value
			// Verify first value is correct
			if (!optionsArray[0].equals(firstOpt)) {
				throw new ErrorOnDataException(objId + "First Option Is Not'"
						+ firstOpt + "'.");
			}
		}

		// Verify all options for each sections.
		for (int i = 0; i < expected.size(); i++) {
			if (rptCriteriaPg.checkObjectExist("SalesChannel")) {
				String salesaChanenl = rptCriteriaPg
				.getDefaultValueForDropDownList("SalesChannel");
				if (objId.equals("SalesLocation")
						&& salesaChanenl.equals("Call Center")) {
					if (!optionsArray[0].equals("All")) {
						throw new ErrorOnDataException(objId + "Not Contain'"
								+ expected.get(0) + "' Option.");
					}
					for (int j = 1; j < optionsArray.length; j++) {
						if (!(optionsArray[j].matches(".*Call Center") || optionsArray[j]
						                                                               .matches(".*Work-at-home"))) {
							throw new ErrorOnDataException(objId
									+ "Did Not Match.");
						}
					}
				}
			} else {
				if (objId.equals("ExcludePaymentGrp")) {
					if (!optionsArray[0].equals("None")) {
						throw new ErrorOnDataException(objId
								+ "First Option Is Not 'None'");
					}
					for (int j = 1; j < optionsArray.length; j++) {
						if (!optionsArray[i].equals(expected.get(i))) {
							throw new ErrorOnDataException(objId
									+ " Not Contain '" + expected.get(i)
									+ "' Option.");
						}
					}
				} else {
					if (!options.contains(expected.get(i))) {
						throw new ErrorOnDataException(objId + " Not Contain '"
								+ expected.get(i) + "' Option.");
					}
				}
			}
		}
	}
	
	/**
	 *This method start from the bulletin search page and end at bulletin detail page, the workflow is search the bulletin according to the information 
	 *of bulletin and click the first search result to go to the bulletin detail page
	 * @param bulletin
	 */
	public void gotoBulletinDetailPage(String headline) {
		OrmsBulletinSearchPage bulletinSchPg = OrmsBulletinSearchPage
				.getInstance();
		OrmsBulletinDetailPage bulletinDetailPg = OrmsBulletinDetailPage.getInstance();
		logger.info("Start to search a Bulletin and go to the detail page of the first search reault.");
		bulletinSchPg.searchByHeadLine(headline);
		ajax.waitLoading();
		bulletinSchPg.waitLoading();
		bulletinSchPg.clickFirstbulletinId();
		bulletinDetailPg.waitLoading();			
	}
}
