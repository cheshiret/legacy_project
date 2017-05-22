package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddFeePenaltyFunction;

/**
 * Description : Functional Test Script
 * 
 * @since 2010/02/03
 * @author dsui
 */
public class AddFeePenalty extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private FeePenaltyData fp = new FeePenaltyData();
	private AddFeePenaltyFunction addFeePenaltyFunc = new AddFeePenaltyFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_add_fee_penalty";
		queryDataSql = "";

	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = fp;
		addFeePenaltyFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("Contract_attr") + " Contract";
		login.location = datasFromDB.get("Role");

		fp.locationCategory = datasFromDB.get("Location Category");
		fp.location = datasFromDB.get("Location");
		fp.productCategory = datasFromDB.get("Product Category");
		fp.feeType = datasFromDB.get("feeType");
		fp.loop = datasFromDB.get("Loops");
		fp.productGroup = datasFromDB.get("Product Group");
		fp.product = datasFromDB.get("Product");
		fp.effectDate = datasFromDB.get("Effective Date");
		fp.startInv = datasFromDB.get("Start Date");
		fp.endInv = datasFromDB.get("End Date");
		fp.tranType = datasFromDB.get("Tran Type");
		fp.tranOccur = datasFromDB.get("TranOccur");
		fp.accountCode = datasFromDB.get("AccountCode");
		fp.includeTax = datasFromDB.get("IncludeTax");
		fp.permitCategory = datasFromDB.get("PermitCategory");
		fp.permitType = datasFromDB.get("PermitType");
		fp.units = datasFromDB.get("Units");
		fp.value = datasFromDB.get("Value");
		fp.minimumUnitOfStay = datasFromDB.get("MINIMUM_UNIT_STAY");
		fp.minimumNumOfDayBeforeArrivalDay = datasFromDB.get("MINIMUM_NUM_DAYS_BEFORE_ARRIVA");
		fp.marinaRateType = datasFromDB.get("MARINARATETYPE");
	}
}
