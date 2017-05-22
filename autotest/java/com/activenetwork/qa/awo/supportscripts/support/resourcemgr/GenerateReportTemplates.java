package com.activenetwork.qa.awo.supportscripts.support.resourcemgr;

import java.io.File;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolFactory;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import org.eclipse.hyades.models.common.datapool.impl.Common_DatapoolFactoryImpl;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.interfaces.testcase.Executable;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.TestProperty;


public class GenerateReportTemplates implements Executable, OrmsConstants {
	/**
	 * Script Name : <b>GenerateReportTemplates</b> Generated : <b>Aug 13, 2009
	 * 1:55:58 AM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/08/13
	 * @author QA
	 */
	
	private static AutomationLogger logger = AutomationLogger.getInstance(SupportCase.class);
	
	private ResourceManager rm = ResourceManager.getInstance();

	private LoginInfo login = new LoginInfo();

	private ReportData rd = new ReportData();

	private String caseName;

	private String fullCaseName;
	
	private String casePath;
	
	private String templatesPath;

	private String dpFileName ; // datapool File name

	private IDatapool dp ; // datapool object

	private IDatapoolIterator dpIter ; // datapool iterator

	public void runAndDownlodaReport(String templatesPath) {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, templatesPath);
		if(rd.deliveryMethod.equalsIgnoreCase("email")){
			rm.viewEmailReportOnline(rd.reportName, rd.reportFormat, templatesPath);
		}
	}

	public void readDataPool() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");

		rd.agencyID = dpIter.dpString("AgencyID");
		rd.agencyLocID = dpIter.dpString("AgencyLocID");
		rd.areaID = dpIter.dpString("AreaID");
		rd.attrFeeType = dpIter.dpString("AttrFeeType");
		rd.balanceType = dpIter.dpString("BalanceType");
		rd.batchStatus = dpIter.dpString("BatchStatus");
		rd.byTicketType = dpIter.dpString("ByTicketType");
		rd.callCenterID = dpIter.dpString("CallCenterID");
		rd.category = dpIter.dpString("Category");
		rd.cooperatorID = dpIter.dpString("CooperatorID");
		rd.collectionStatus = dpIter.dpString("CollectionStatus");
		rd.collectIssueLocation = dpIter.dpString("CollectIssueLocation");
		rd.customerType = dpIter.dpString("CustomerType");
		if (dpIter.dpString("DateType") != null)
			rd.dateType = dpIter.dpString("DateType");
		rd.deliveryMethod = dpIter.dpString("DeliveryMethod");
		rd.deliveryprotocolid = dpIter.dpString("deliveryprotocolid");
		rd.dfmDate = dpIter.dpString("dfmDate");
		rd.dfmSortOrder = dpIter.dpString("dfmSortOrder");
		rd.discountIDs = dpIter.dpString("DiscountIDs");
		rd.doNotEmail = dpIter.dpString("DoNotEmail");
		rd.doNotFax = dpIter.dpString("DoNotFax");
		rd.tourCategory = dpIter.dpString("tourCategory");
		rd.endDate = dpIter.dpString("EndDate");
		rd.endTime = dpIter.dpString("EndTime");
		rd.endTimeampm = dpIter.dpString("EndTimeampm");
		rd.exceptions = dpIter.dpString("Exceptions");
		rd.facilityHQID = dpIter.dpString("FacilityHQID");
		rd.facilityID = dpIter.dpString("FacilityID");
		rd.facilityLocID = dpIter.dpString("FacilityLocID");
		rd.group = dpIter.dpString("group");
		rd.includeAdjustments = dpIter.dpString("includeAdjustments");
		rd.includeTaxes = dpIter.dpString("IncludeTaxes");
		rd.locationID = dpIter.dpString("LocationID");
		rd.locationIDNoValidate = dpIter.dpString("LocationIDNoValidate");
		rd.maxLetters = dpIter.dpString("MaxLetters");
		rd.operator = dpIter.dpString("Operator");
		rd.orderBy = dpIter.dpString("OrderBy");
		rd.orderStatus = dpIter.dpString("OrderStatus");
		rd.park = dpIter.dpString("Park");
		rd.paymentGroup = dpIter.dpString("PaymentGroup");
		rd.paymentMethod = dpIter.dpString("paymentMethod");
		rd.paymentRefundStatus = dpIter.dpString("PaymentRefundStatus");
		rd.paymentStatus = dpIter.dpString("PaymentStatus");
		rd.productGroup = dpIter.dpString("ProductGroup");
		if (dpIter.dpString("ProductType") != null)
			rd.productType = dpIter.dpString("ProductType");
		rd.rAFeeAccount = dpIter.dpString("RAFeeAccount");
		rd.recipient_name = dpIter.dpString("recipient_name");
		rd.refund_Payment = dpIter.dpString("Refund_Payment");
		rd.refund_Status = dpIter.dpString("Refund_Status");
		rd.residenceArea = dpIter.dpString("residenceArea");
		rd.permitEntrance = dpIter.dpString("permitEntrance");
		rd.regionID = dpIter.dpString("RegionID");
		rd.regionLocID = dpIter.dpString("RegionLocID");
		rd.reportBy = dpIter.dpString("ReportBy");
		rd.reportByLocation = dpIter.dpString("ReportByLocation");
		rd.reportFormat = dpIter.dpString("ReportFormat");
		rd.reportLoc = dpIter.dpString("ReportLoc");
		rd.reportName = dpIter.dpString("reportName");
		rd.reportSubType = dpIter.dpString("ReportSubType");
		rd.showAllocation = dpIter.dpString("showAllocation");
		if (dpIter.dpString("ReportType") != null)
			rd.reportType = dpIter.dpString("ReportType");
		rd.reservationStatus = dpIter.dpString("ReservationStatus");
		rd.reservationType = dpIter.dpString("ReservationType");
		rd.salesChannel = dpIter.dpString("SalesChannel");
		rd.showEmptySites = dpIter.dpString("ShowEmptySites");
		rd.invoice = dpIter.dpString("invoice");
		rd.sortBy = dpIter.dpString("SortBy");
		rd.sortOrder = dpIter.dpString("SortOrder");
		rd.startDate = dpIter.dpString("StartDate");
		rd.startTime = dpIter.dpString("StartTime");
		rd.startTimeampm = dpIter.dpString("StartTimeampm");
		rd.stationID = dpIter.dpString("StationID");
		rd.state = dpIter.dpString("state");
		rd.custLocation = dpIter.dpString("custLocation");
		rd.supressEmptyLine = dpIter.dpString("SupressEmptyLine");
		rd.unitOfStay = dpIter.dpString("UnitOfStay");
		rd.voucherProgram = dpIter.dpString("VoucherProgram");
		rd.yesNoFlag = dpIter.dpString("YesNoFlag");
		rd.depositID_2 = dpIter.dpString("DepositID_2");
		rd.finSessionId_2 = dpIter.dpString("FinSessionId_2");
		rd.frequency = dpIter.dpString("frequency");
		rd.recipientType = dpIter.dpString("recipientType");
		rd.period = dpIter.dpString("period");
		rd.tourID = dpIter.dpString("tour");
		rd.tourName = dpIter.dpString("tourName");
		rd.lotteryName=dpIter.dpString("LotteryName");
		rd.lotterySched=dpIter.dpString("LotterySchd");
		rd.revenueLoc=dpIter.dpString("RevenueLoc");
		rd.residenceArea=dpIter.dpString("Area");
		rd.dateGroup=dpIter.dpString("DateGroup");
		rd.quotaInterval=dpIter.dpString("QuotaInterval");
		rd.transmittalID=dpIter.dpString("TransmittalID");
	}

	public int testMain(Object[] args) {
		initializeParameters();
		initializeDataPool();
		
		File file = new File(templatesPath);
		if (!file.exists()) {
			boolean success=file.mkdir();
			if(!success) {
				throw new RuntimeException("Failed to create directory "+templatesPath);
			}
		}
		int cursor = 0;
		int startpoint = 253;
		int endpoint = 257;

		while (!dpIter.dpDone()) {
			if (cursor < startpoint || cursor > endpoint) {
				cursor++;
				dpIter.dpNext();
				continue;	
			}
			readDataPool();
			runAndDownlodaReport(file.getAbsolutePath());
			
			dpIter.dpNext();
			cursor++;
		}
		return 0;
	}

	public void initializeDataPool() {
		if (dpFileName == null || dpFileName.length() < 0)
			return;

		if (!dpFileName.endsWith(".datapool"))
			dpFileName += ".datapool";

		File dpFile = new File(AwoUtil.PROJECT_PATH, dpFileName);
		IDatapoolFactory dpFactory = new Common_DatapoolFactoryImpl();

		dp = dpFactory.load(dpFile, true);
		dpIter = dpFactory
				.open(dp,
						"org.eclipse.hyades.datapool.iterator.DatapoolIteratorSequentialPrivate");
		dpIter.dpInitialize(dp);
	}
	
	public void initializeParameters() {
		fullCaseName = this.getClass().getName();
		int index = fullCaseName.lastIndexOf(".");
		caseName = fullCaseName.substring(index + 1);
		casePath = fullCaseName.substring(0, index).replaceAll("\\.", "/");
		
		logger.setLogger(caseName);

		dpFileName = casePath + "/" + caseName;;
		dp = null;
		dpIter = null;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.rm.user");
		login.password = TestProperty.getProperty("orms.rm.pw");

		String project_path = AwoUtil.PROJECT_PATH;
		if (project_path.contains("\\")) {
			templatesPath = project_path.substring(0, project_path
					.lastIndexOf("\\"))
					+ "\\ReportTemplates";

		} else {
			String path = project_path.substring(0,
					project_path.lastIndexOf("/")).replace("/", "\\");
			templatesPath = path.substring(0, path.lastIndexOf("\\"))
					+ "\\ReportTemplates";
		}
	}

}
