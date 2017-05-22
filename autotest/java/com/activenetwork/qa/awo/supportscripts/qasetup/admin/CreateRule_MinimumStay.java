package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions.MinimumStayRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CreateRule_MinimumStay extends SetupCase {

	private LoginInfo login = null;
	private RuleDataInfo ruleData = null;
	
	@Override
	public void executeSetup() {
		new MinimumStayRuleFunction().execute(new Object[] {login, ruleData});
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = new LoginInfo();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
		dataTableName = "d_rule_minimum_stay";
		ids = "";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.contract = datasFromDB.get("contract_abbr");
		if (login.contract.contains("Contract")) {
			login.contract = login.contract.replace("Contract", StringUtil.EMPTY).trim().toUpperCase();
		}
		if(login.contract.equals("NRRS")) {
			login.contract = "NRSO";
		}
		login.location = datasFromDB.get("location_abbr");

		//rule data info
		ruleData = new RuleDataInfo("Minimum Stay");//IMPORTANT
		ruleData.location = datasFromDB.get("LocationName");
		ruleData.locationCategory = datasFromDB.get("LocationCategory");

		// Common attributes
		ruleData.status = datasFromDB.get("Active");
		ruleData.productCategory = datasFromDB.get("ProductCategory");
		ruleData.marinaRateType = datasFromDB.get("MarinaRateType");
		ruleData.ticketCategory = datasFromDB.get("TicketCategory");
		ruleData.productGroup = datasFromDB.get("ProductGroup");
		ruleData.loop = datasFromDB.get("AreaLoop");
		ruleData.product = datasFromDB.get("Product");
		ruleData.salesChannel = datasFromDB.get("SalesChannel");
		ruleData.customerType = datasFromDB.get("CustomerType");
		ruleData.customerPassType = datasFromDB.get("CustomerPassType");
		ruleData.outOfState = datasFromDB.get("OutOfState");
		ruleData.paymentType = datasFromDB.get("PaymentType");
		ruleData.customerMember = datasFromDB.get("CustomerMember");
		ruleData.associatedParty = datasFromDB.get("AssociatedParty");
		ruleData.comments = datasFromDB.get("Comments");

		if(!StringUtil.isEmpty(datasFromDB.get("StartDate"))) {
			ruleData.startDate = datasFromDB.get("StartDate");
		}
		if(!StringUtil.isEmpty(datasFromDB.get("EndDate"))) {
			ruleData.endDate = datasFromDB.get("EndDate");
		}
		if(!StringUtil.isEmpty(datasFromDB.get("EffectiveDate"))) {
			ruleData.effectiveDate = datasFromDB.get("EffectiveDate");
		}
		if (ruleData.comments.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_ON_1")
				|| ruleData.comments.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_OFF_1")) {
			ruleData.startDate = "06-01-" + (DateFunctions.getCurrentYear() + 1);
			ruleData.endDate = "06-05-" + (DateFunctions.getCurrentYear() + 1);
		}
		if (ruleData.comments.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_OFF_2")) {
			ruleData.startDate = "06-10-" + (DateFunctions.getCurrentYear() + 1);
			ruleData.endDate = "06-20-" + (DateFunctions.getCurrentYear() + 1);
		}

		//Minimum Stay specific attributes
		ruleData.transactionOccurrence = datasFromDB.get("TransactionOccurrence");
		ruleData.minimumStay = datasFromDB.get("MinimumStay");
		ruleData.minimumStayMon = datasFromDB.get("MinimumStayMon");
		ruleData.minimumStayTue = datasFromDB.get("MinimumStayTue");
		ruleData.minimumStayWed = datasFromDB.get("MinimumStayWed");
		ruleData.minimumStayThu = datasFromDB.get("MinimumStayThu");
		ruleData.minimumStayFri = datasFromDB.get("MinimumStayFri");
		ruleData.minimumStaySat = datasFromDB.get("MinimumStaySat");
		ruleData.minimumStaySun = datasFromDB.get("MinimumStaySun");
		ruleData.minimumStayHoliday = datasFromDB.get("MinimumStayHoliday");
		ruleData.unit = datasFromDB.get("Unit");
		ruleData.countStayBeyondRulePeriod = datasFromDB.get("CountStayBeyondRulePeriod");
		ruleData.level = datasFromDB.get("Level");
		ruleData.includeHolidayPeriod = datasFromDB.get("IncludeHolidayPeriod");
		ruleData.multiplesOnly = datasFromDB.get("MultiplesOnly");
		ruleData.minimumStayRequiredWhenStayIncludesStartDay = datasFromDB.get("MINSTAYREQSTYINCLUDSTARTDAY");
		ruleData.combineOverlappingSeasons = datasFromDB.get("MINISTAYCOMBINEOVERLAPSEASON");

		ruleData.permitType = datasFromDB.get("PermitType");
	}
}
