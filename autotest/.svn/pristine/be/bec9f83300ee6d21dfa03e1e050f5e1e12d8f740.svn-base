package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify Edit Attribute Fee audit log details info when editing attribute fee's Calculation Method
 * @Preconditions: need an existing Slip product - 'EFS-Edit Fee Schedule' which hass been inserted into corresponding support table
 * @SPEC: Edit Attr Fee Schedule - Log [TC:049738]
 * @Task#: AUTO-1425
 * 
 * @author qchen
 * @Date  Jan 22, 2013
 */
public class EditAttrFeeSchdAuditLogs_Slip_CalculationMethod extends AdminManagerTestCase {

	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private FeeScheduleData schedule = new FeeScheduleData();
	private AuditLogInfo logs = new AuditLogInfo();
	private SlipFee fee = schedule.new SlipFee();
	private PricingBase originalArrival =new PricingBase();
	private PricingBase originalDeparture = new PricingBase();
	private PricingBase editArrival = new PricingBase();
	private PricingBase editDeparture = new PricingBase();
	private AdminMgrFeeAuditLogsPage logsPage = AdminMgrFeeAuditLogsPage.getInstance();
	
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		//add a new marina attribute fee schedule
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		
		//edit the new added attribute fee schedule's Fee section
		this.initializeEditingFeeScheduleInfo();
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		fnm.editFeeSchedule(schedule);
		fnm.logoutFinanceManager();
		
		am.loginAdminManager(login, false);
		am.gotoFeeAuditLogsPage();
		logs.searchValue = schedule.feeSchdId;
		logs.actionDetails = "Old Fee Schedule ID: " + schedule.feeSchdId + ", New Fee Schedule ID: " + schedule.feeSchdId
									+ ", Product Category: " + schedule.productCategory + ", Product Group: " + schedule.productGroup + ", Product: " + schedule.product.split("-")[1].trim()
									+ ", Fee Type: " + schedule.feeType
									+ " Pricing Based on Arrival - Percent(Arrival Start Date,Arrival End Date): " + originalArrival.percent + "%(" + originalArrival.startMonth + " " + originalArrival.startDate +", " + originalArrival.endMonth + " " + originalArrival.endDate + ") --> " + editArrival.percent + "%(" + editArrival.startMonth + " " + editArrival.startDate + ", " + editArrival.endMonth + " " + editArrival.endDate + ")"
									+ " Pricing Based on Departure - Percent(Arrival Start Date,Arrival End Date): " + originalDeparture.percent + "%(" + originalDeparture.startMonth + " " + originalDeparture.startDate + ", " + originalDeparture.endMonth + " " + originalDeparture.endDate + ") --> " + editDeparture.percent + "%(" + editDeparture.startMonth + " " + editDeparture.startDate + ", " + editDeparture.endMonth + " " + editDeparture.endDate + ")";
		logsPage.searchFeeAuditLogs(logs);
		logsPage.verifyFeeAuditLogs(logs);
		am.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = login.userName;
		loginFnm.password = login.password;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";
		String facility = fnm.getFacilityName(facilityID, schema);
		
		schedule.location = facility; 
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Attribute Fee";
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.dock = "All";
		schedule.productGroup = "Full attributes";
		schedule.product = "EFS-Edit Fee Schedule";
		schedule.effectDate = DateFunctions.getDateAfterToday(5);
		schedule.startInv = schedule.effectDate;
		schedule.endInv = schedule.effectDate;
		schedule.season = "All";
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.custType = "All";
		schedule.boatCategory = "All";
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "15";
		schedule.marinaRateType = OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL;
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";
		fee.perSeasonFee = "500.25";
		schedule.slipFees.add(fee);
		schedule.calculationMethod = "Percentage";
		
		originalArrival.startMonth = "Jan";
		originalArrival.startDate = "1";
		originalArrival.endMonth = "Mar";
		originalArrival.endDate = "31";
		originalArrival.percent = "40";
		originalDeparture.startMonth = "Apr";
		originalDeparture.startDate = "1";
		originalDeparture.endMonth = "Jun";
		originalDeparture.endDate = "30";
		originalDeparture.percent = "60";
		schedule.pricingBasedonArrival.add(originalArrival);
		schedule.pricingBasedonDeparture.add(originalDeparture);
		
		logs.searchType = "Action Details";
		logs.logArea = "Fee Schedule";
		logs.actionType = "Update";
		logs.dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		logs.action = logs.actionType + " " + logs.logArea;
		logs.affectedLocation = facility;
		logs.userName = loginFnm.userName;
		logs.application = "Finance";
		logs.stratDate = DateFunctions.getDateAfterToday(-2);
		logs.endDate = DateFunctions.getToday();
	}
	
	private void initializeEditingFeeScheduleInfo() {
		editArrival.startMonth = "Jul";
		editArrival.startDate = "2";
		editArrival.endMonth = "Sep";
		editArrival.endDate = "30";
		editArrival.percent = "45";
		editDeparture.startMonth = "Oct";
		editDeparture.startDate = "2";
		editDeparture.endMonth = "Dec";
		editDeparture.endDate = "31";
		editDeparture.percent = "55";
		
		schedule.pricingBasedonArrival.clear();
		schedule.pricingBasedonDeparture.clear();
		schedule.pricingBasedonArrival.add(editArrival);
		schedule.pricingBasedonDeparture.add(editDeparture);
	}
}
