package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.ormsclientrulefunctions.AccessTimeRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 16, 2013
 */
public class CreateRule_AccessTime extends SetupCase {
	
	private LoginInfo login = new LoginInfo();
	private RuleDataInfo ruleData = null;
	
	@Override
	public void executeSetup() {
		new AccessTimeRuleFunction().execute(new Object[] {login, ruleData});
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		
		dataTableName = "d_rule_access_time";
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
		ruleData = new RuleDataInfo("Access Time");
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
		ruleData.season = datasFromDB.get("SeasonType");
		ruleData.customerPassType = datasFromDB.get("CustomerPassType");
		ruleData.outOfState = datasFromDB.get("OutOfState");
		ruleData.paymentType = datasFromDB.get("PaymentType");
		ruleData.customerMember = datasFromDB.get("CustomerMember");
		ruleData.associatedParty = datasFromDB.get("AssociatedParty");
		ruleData.comments = datasFromDB.get("Comments");
		// COMMON start/end/effective dates
//		ruleData.effectiveDate = DateFunctions.getToday();
//		ruleData.startDate = DateFunctions.getDateAfterToday(-10);
//		ruleData.endDate = DateFunctions.getDateAfterToday(750);

		ruleData.opentime = datasFromDB.get("OpenTime");
		ruleData.dailyopentime = datasFromDB.get("DailyOpenTime");
		ruleData.monOpenTime = datasFromDB.get("MonOpenTime");
		ruleData.tueOpenTime = datasFromDB.get("TueOpenTime");
		ruleData.wedOpenTime = datasFromDB.get("WedOpenTime");
		ruleData.thuOpenTime = datasFromDB.get("ThuOpenTime");
		ruleData.friOpenTime = datasFromDB.get("FriOpenTime");
		ruleData.satOpenTime = datasFromDB.get("SatOpenTime");
		ruleData.sunOpenTime = datasFromDB.get("SunOpenTime");
		ruleData.closeTime = datasFromDB.get("CloseTime");
		ruleData.dailyCloseTime = datasFromDB.get("DailyCloseTime");
		ruleData.monCloseTime = datasFromDB.get("MonCloseTime");
		ruleData.tueCloseTime = datasFromDB.get("TueCloseTime");
		ruleData.wedCloseTime = datasFromDB.get("WedCloseTime");
		ruleData.thuCloseTime = datasFromDB.get("ThuCloseTime");
		ruleData.friCloseTime = datasFromDB.get("FriCloseTime");
		ruleData.satCloseTime = datasFromDB.get("SatCloseTime");
		ruleData.sunCloseTime = datasFromDB.get("SunCloseTime");
	}
}
