/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryAdjust;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryAdjust.SerializationInventoryRange;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AdjustPOSInventoryFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Apr 25, 2014
 */
public class AdjustPOSInventory extends SetupCase{
	private  AdjustPOSInventoryFunction adjustPOSInventoryFunction = new AdjustPOSInventoryFunction();
	private LoginInfo login = new LoginInfo();
	private POSInfo pos = new POSInfo();
	private POSInventoryAdjust inventory = new POSInventoryAdjust();
	private String wareHouseName;
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[4];

		args[0] = login;
		args[1]= wareHouseName ;
		args[2]= pos ;
		args[3]=inventory;
		
		adjustPOSInventoryFunction.execute(args);
		
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("roleloaction");
		
		wareHouseName = datasFromDB.get("wareHouseName");
		pos.product=datasFromDB.get("pos_product");
		pos.inventoryType=datasFromDB.get("pos_inventorytype");
		inventory.setDateSuppliesReceived(datasFromDB.get("date"));
		
		List<SerializationInventoryRange> rangeInfo = new ArrayList<SerializationInventoryRange>();
		String froms = datasFromDB.get("rang_from");
		String tos = datasFromDB.get("rang_to");
		if(!StringUtil.isEmpty(froms) && !StringUtil.isEmpty(tos)) {
			
			String fromsArray[] = froms.split(",");
			String tosArray[] = tos.split(",");
			
			if(fromsArray.length != tosArray.length) throw new ErrorOnDataException("'rang_from' length shall be same as 'rang_to' length.");
			
			for(int i = 0; i < fromsArray.length; i ++) {
				SerializationInventoryRange serializationRange = new SerializationInventoryRange();
				serializationRange.from = fromsArray[i];
				serializationRange.to = tosArray[i];
				
				rangeInfo.add(serializationRange);
			}
		} else throw new ErrorOnDataException("'range_from' or 'rang_to' is empty.");
		
		inventory.setSerializationRange(rangeInfo);
		inventory.setNotes(datasFromDB.get("note"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_adjust_pos_inventory"; 
		ids = "80,90";
		
		env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
	}

}
