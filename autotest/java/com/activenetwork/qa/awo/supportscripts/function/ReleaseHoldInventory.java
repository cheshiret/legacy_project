package com.activenetwork.qa.awo.supportscripts.function;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class ReleaseHoldInventory extends FunctionCase {
	InventoryManager im=InventoryManager.getInstance();
	LoginInfo login=new LoginInfo();
	String park,siteNum,dateFrom,dateTo, schema;
	Object[] params;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		
		for(int i=0;i<params.length;i++) {
			String siteID=TestDriverUtil.getParameter(param, "siteID");
			dateFrom=TestDriverUtil.getParameter(param, "from");
			dateTo=TestDriverUtil.getParameter(param, "to");
			siteNum=im.getSiteNumber(siteID, schema);
			park=im.getParkName(siteID, schema);
			
			im.gotoSiteInventoryListPage(park);
			im.releaseInventory(siteNum, dateFrom, dateTo);
		
		}
		
		im.logoutInvManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		String contractCode = TestDriverUtil.getParameter(param, "contract");
		schema=DataBaseFunctions.getSchemaName(contractCode,env);
		params=Arrays.copyOfRange(param, 1, param.length-1);
		
		
		
		

		login.contract = contractCode + " Contract";
		if (contractCode.equalsIgnoreCase("ORNG")) {
			login.contract = "Orange County Contract";
		} else if (contractCode.equalsIgnoreCase("MRV")) {
			login.contract = "Morgan RV Contract";
		} else if (contractCode.equalsIgnoreCase("LARC")) {
			login.contract = "Larimer County Contract";
		}

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		login.contract = DataBaseFunctions.getAdminContractRoleLoc(login.userName, schema);
		
	}

}
