package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions.MinimumGroupSizeRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CreateRule_MinimumGroupSize extends SetupCase {
	
	private LoginInfo login = null;
	private RuleDataInfo ruleData = null;
	
	@Override
	public void executeSetup() {
		new MinimumGroupSizeRuleFunction().execute(new Object[] {login, ruleData});
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = new LoginInfo();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
		dataTableName = "d_rule_min_group_size";
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
		ruleData = new RuleDataInfo("Minimum Group Size");//IMPORTANT
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

		//Minimum Group Size specific attributes
		ruleData.transactionType = datasFromDB.get("TransactionType");
		ruleData.transactionOccurrence = datasFromDB.get("TransactionOccurrence");
		ruleData.permitType = datasFromDB.get("PermitType");
		ruleData.personTypes = this.splitTextBySemicolon(datasFromDB.get("PersonTypes"));
		ruleData.personTypesNums = this.splitTextBySemicolon(datasFromDB.get("PersonTypeNums"));
	}
	
	private String[] splitTextBySemicolon(String text) {
		String[] list;
		if (text.contains(";")) {
			list = text.split(";", 0);
		} else {
			list = new String[] { text };
		}
		return list;
	}
}
