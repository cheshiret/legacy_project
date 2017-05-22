package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions.ProductRestrictedInUseRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CreateRule_ProductRestrictedInUse extends SetupCase {
	
	private LoginInfo login;
	private RuleDataInfo ruleData;
	
	@Override
	public void executeSetup() {
		new ProductRestrictedInUseRuleFunction().execute(new Object[] {login, ruleData});
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = new LoginInfo();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
		dataTableName = "d_rule_prod_restrict_in_use";
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
		ruleData = new RuleDataInfo("Product Restricted in Use");//IMPORTANT
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
		ruleData.season = datasFromDB.get("SeasonType");
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

		//Product Restricted in Use specific attributes
		String tempCustType = datasFromDB.get("CustomerType");
		if(!StringUtil.isEmpty(tempCustType)) {
			ruleData.customerTypes = this.splitTextBySemicolon(tempCustType);
		}
		String tempCustPass = datasFromDB.get("CustomerPassType");
		if(!StringUtil.isEmpty(tempCustPass)) {
			ruleData.customerPassTypes = this.splitTextBySemicolon(tempCustPass);
		}
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
