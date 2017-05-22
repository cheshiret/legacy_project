package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PocInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddWarehouseFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * 
 * @Description: add a new ware house.
 * @Preconditions:The add ware house rule feature has been assigned.
 * @SPEC:
 * @Task#:Auto-972
 * 
 * @author jwang8
 * @Date  Mar 27, 2012
 */
public class AddWarehouse extends SetupCase{

	private LoginInfo login=new LoginInfo();
	private WarehouseInfo whouseInfo = new WarehouseInfo();
	private AddWarehouseFunction addWarehouse =new AddWarehouseFunction();
	
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = whouseInfo;
		
		addWarehouse.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		whouseInfo.setAgency(datasFromDB.get("agency"));
		whouseInfo.setRegion( datasFromDB.get("region"));
		whouseInfo.setDistrict(datasFromDB.get("district"));
		whouseInfo.setName(datasFromDB.get("warehouseName"));
		whouseInfo.setDesctiption(datasFromDB.get("description"));
		Address address = new Address();
		address.address =datasFromDB.get("mailingAddress");
		address.city = datasFromDB.get("mailingCity");
		address.state = datasFromDB.get("mailingState");
		address.zip = datasFromDB.get("mailingZip");
		address.country = datasFromDB.get("mailingCountry");
		whouseInfo.setMailingAddress(address);
		
		PocInfo  pocInfo = new PocInfo();
		pocInfo.setFirstName(datasFromDB.get("primFirstName"));
		pocInfo.setLastName(datasFromDB.get("primLastName"));
		pocInfo.setPhone(datasFromDB.get("primPhone"));
		pocInfo.setFax(datasFromDB.get("primFax"));
		pocInfo.setEmail(datasFromDB.get("primEmail"));
		
		address.address = datasFromDB.get("primAddress");
		address.city = datasFromDB.get("primCity");
		address.state = datasFromDB.get("primState");
		address.zip = datasFromDB.get("primZip");
		address.country = datasFromDB.get("primCountry");
		pocInfo.setPocAddress(address);
		whouseInfo.setPrimaryPoc(pocInfo);
		
		pocInfo.setFirstName(datasFromDB.get("secPosFirstName"));
		pocInfo.setLastName(datasFromDB.get("secPosLastName"));
		pocInfo.setPhone(datasFromDB.get("secPosPhone"));
		pocInfo.setFax(datasFromDB.get("secPosFax"));
		pocInfo.setEmail(datasFromDB.get("secPosEmail"));
		
		address.address = datasFromDB.get("SecPosAddress");
		address.city = datasFromDB.get("SecPosCity");
		address.state = datasFromDB.get("SecPosState");
		address.zip = datasFromDB.get("SecPosZip");
		address.country = datasFromDB.get("SecPosCountry");
		pocInfo.setPocAddress(address);
		whouseInfo.setSecPoc(pocInfo);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_ware_house";
		
		queryDataSql = "";
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
