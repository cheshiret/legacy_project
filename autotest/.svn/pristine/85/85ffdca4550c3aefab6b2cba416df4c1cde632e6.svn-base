package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddListFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddList extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String facilityID;
	private ListInfo list = new ListInfo();
	private AddListFunction addListFuc = new AddListFunction();

	@Override
	public void executeSetup() {		
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = facilityID;
		args[2] = list;
		addListFuc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("RoleLocation");
		
		facilityID = datasFromDB.get("FacilityID");
		
		list.setListName(datasFromDB.get("ListName"));
		list.setListStatus(datasFromDB.get("ListStatus"));
		list.setNumOfChoice(datasFromDB.get("NumOfChoice"));
		
		this.setSlipCode();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_list";
		queryDataSql = "select * from d_inv_add_list where id>10";

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
	}
	
	private void setSlipCode(){
		List<String> slipCodes = new ArrayList<String>();
		String code = datasFromDB.get("Slip_Code");
		if(StringUtil.notEmpty(code)){
			String[] codes = code.split(",");
			for(int i=0;i<codes.length;i++){
				slipCodes.add(codes[i]);
			}
		}
		list.setAssignedSlipCode(slipCodes);
	}

}
