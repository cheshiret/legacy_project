package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddVoucherProgramFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @author Reed Wang
 * 
 */
public class AddVoucherProgram extends SetupCase {

	private VoucherProgram vp ;
	private LoginInfo login = new LoginInfo();
	private AddVoucherProgramFunction func = new AddVoucherProgramFunction();
	

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		vp = new VoucherProgram();
		vp.programeName = datasFromDB.get("programeName");
		vp.programeType =  datasFromDB.get("programeType");
		vp.createLocation = datasFromDB.get("createLocation");
		vp.locationCategoryForCreation = datasFromDB.get("locationCategoryForCreation");
		vp.productCategoryForCreation = datasFromDB.get("productCategoryForCreation");
		vp.emergencyCancellation = datasFromDB.get("emergencyCancellation");
		vp.redirectionToRefund = datasFromDB.get("redirectionToRefund");
		vp.redirectionToRefundWeb = datasFromDB.get("redirectionToRefundWeb");
		vp.locationForUse =  datasFromDB.get("locationForUse");
		vp.locationCategoryForUse = datasFromDB.get("locationCategoryForUse");
		vp.productCategoryForUse =  datasFromDB.get("productCategoryForUse");
		vp.sameBillCustomer =  datasFromDB.get("sameBillCustomer");
		vp.account = datasFromDB.get("account");
		vp.expireIndicator = Boolean.parseBoolean(datasFromDB.get("expireIndicator"));
		vp.expiryUnit = datasFromDB.get("expiryunit");
		vp.expiryPeriod = datasFromDB.get("expiryperiod");
		vp.expiryMethod = datasFromDB.get("expirymethod");
		vp.lineOfBusiness = datasFromDB.get("lineOfBusiness");
		vp.startDate = DateFunctions.getDateAfterToday(-1);
		vp.endDate = DateFunctions.getDateAfterToday(180);
	}

	@Override
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = vp;
		
		func.execute(args);
	}


	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_voucher_program";
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);		
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}
}
