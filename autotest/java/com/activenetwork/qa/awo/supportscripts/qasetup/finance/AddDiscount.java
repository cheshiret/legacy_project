package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddDiscountFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddDiscount extends SetupCase {
	private DiscountData discount;
	private LoginInfo login = new LoginInfo();
	private AddDiscountFunction addDisFunc = new AddDiscountFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = discount;
		addDisFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		// login info
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");

		discount = new DiscountData();
		// clear list content
		discount.feeTypes.clear();
		discount.behaviors.clear();

		// discount data
		discount.name = datasFromDB.get("discountName");
		discount.description = datasFromDB.get("description");
		discount.rateType = datasFromDB.get("rateType");

		if(datasFromDB.get("feeTypes")!=null){
			String[] feeTypes = datasFromDB.get("feeTypes").split(",");
			for (int i = 0; i < feeTypes.length; i++) {
				discount.feeTypes.add(feeTypes[i]);
			}
		}
		if(datasFromDB.get("behaviors")!=null){
			String[] behaviors = datasFromDB.get("behaviors").split(",");
			for (int j = 0; j < behaviors.length; j++) {
				discount.behaviors.add(behaviors[j]);
			}
		}

		discount.rateUnit = datasFromDB.get("rateUnit");
		discount.promoCode = datasFromDB.get("promoCode");
		discount.promoDescription = datasFromDB.get("promoDescription");
		discount.maxUsagePerCust = datasFromDB.get("MAXUSAGEPERCUST");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_add_discount";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

}
