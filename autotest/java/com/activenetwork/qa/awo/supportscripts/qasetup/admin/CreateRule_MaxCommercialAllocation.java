package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions.MaxCommercialAllocationRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CreateRule_MaxCommercialAllocation extends SetupCase {
	
	private LoginInfo login = null;
	private RuleDataInfo ruleData = null;
	
	@Override
	public void executeSetup() {
		new MaxCommercialAllocationRuleFunction().execute(new Object[] {login, ruleData});
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
		dataTableName = "";//TODO no support table
		ids = "";
	}

	@Override
	public void readDataFromDatabase() {
		//login info
		login = new LoginInfo();
		login.contract = datasFromDB.get("contract_abbr");
		if (login.contract.contains("Contract")) {
			login.contract = login.contract.replace("Contract", StringUtil.EMPTY).trim().toUpperCase();
		}
		if(login.contract.equals("NRRS")) {
			login.contract = "NRSO";
		}
		login.location = datasFromDB.get("location_abbr");

		//rule data info
		ruleData = new RuleDataInfo("Maximum Commercial Allocation");//IMPORTANT
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

		if (null != datasFromDB.get("StartDate")
				&& datasFromDB.get("StartDate").length() > 0) {
			ruleData.startDate = datasFromDB.get("StartDate");
		}
		if (null != datasFromDB.get("EndDate")
				&& datasFromDB.get("EndDate").length() > 0) {
			ruleData.endDate = datasFromDB.get("EndDate");
		}
		if (null != datasFromDB.get("EffectiveDate")
				&& datasFromDB.get("EffectiveDate").length() > 0) {
			ruleData.effectiveDate = datasFromDB.get("EffectiveDate");
		}

		//Maximum Commercial Allocation specific attributes
		ruleData.quotaType = datasFromDB.get("QuotaType");
		ruleData.commercialUser = datasFromDB.get("Commercial User");
		ruleData.reservationStatus = datasFromDB.get("ReservationStatus");
		ruleData.maximumNumber = Integer.parseInt(datasFromDB.get("MaximumNumber"));
		ruleData.countStayBeyondRulePeriod = datasFromDB.get("CountStayBeyondRulePeriod");
	}
}
