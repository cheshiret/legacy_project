package com.activenetwork.qa.awo.supportscripts.qasetup.license;



import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddConsumableProductFunction;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Mar 16, 2012
 */
public class AddConsumableProduct extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private ConsumableInfo consumableInfo = new ConsumableInfo();
	protected int startpoint = 0;
	protected int cursor = 0;
	protected int endpoint = 0;
	protected String dpFileName;	
	private AddConsumableProductFunction addConsumProFunc = new AddConsumableProductFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = consumableInfo;
		addConsumProFunc.execute(args);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_addconsu_prd";
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		consumableInfo.orderType = datasFromDB.get("orderType");
		consumableInfo.code = datasFromDB.get("code");
		consumableInfo.status = datasFromDB.get("status");
		consumableInfo.name = datasFromDB.get("name");
		consumableInfo.description = datasFromDB.get("description");
		consumableInfo.productGroup = datasFromDB.get("productGroup");
		if(datasFromDB.get("variablePrice").equalsIgnoreCase("yes"))
		{
			consumableInfo.variablePrice = true;
		}else{
			consumableInfo.variablePrice = false;
		}
	}
}
