package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddBondsFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AddBonds extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private AddBondsFunction addBondsFunc = new AddBondsFunction();
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = bondInfo;
		addBondsFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_bonds";
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		bondInfo.belongVendorNum =datasFromDB.get("vendorNum");
		bondInfo.issuer=datasFromDB.get("bondIssuer");
		bondInfo.type=datasFromDB.get("bondType");
		bondInfo.bondNum=datasFromDB.get("bondNum");
		bondInfo.bondAmount=datasFromDB.get("bondAmount");
		bondInfo.effectiveFrom=datasFromDB.get("effectiveFrom");
		if(bondInfo.effectiveFrom.trim().length() < 1) {
			bondInfo.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		}
		bondInfo.effectiveTo = datasFromDB.get("effectiveTo");
		if(bondInfo.effectiveTo.trim().length() < 1) {
			bondInfo.effectiveTo=DateFunctions.getDateAfterToday(365);
		}
	}
}
