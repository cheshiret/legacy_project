package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddSupplyFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private SupplyInfo supply;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		supply = (SupplyInfo)param[1];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.addSupplyProudct(supply);
		verifyResult();
		newAddValue = supply.id;
	
	}
	
	public void verifyResult(){
		boolean passed = true;
		String schema = "MS";
		String env = TestProperty.getProperty("target_env");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + schema;
		db.resetSchema(schema);
		//Not included: "supply.availableToApp","supply.ofPanels","supply.qtyOnHand"
		String query = "select P_PRD.prd_id  as PRDID from P_PRD " +
				"left join P_PRD_GRP on P_PRD_GRP.prd_grp_id = P_PRD.prd_grp_id " +
				"left join P_PRD_SUPPLYINFO on P_PRD_SUPPLYINFO.prd_id = P_PRD.prd_id " +
				"left join D_FULFILLMENT_PARTY on D_FULFILLMENT_PARTY.id = P_PRD_SUPPLYINFO.fulfillment_party_id " +
		" where P_PRD.prd_cd = '" + supply.code + 
		"' and P_PRD.prd_name = '" +  supply.name +
		"' and P_PRD.prd_dscr = '" + supply.description +
		"' and P_PRD_GRP.prd_grp_name = '" + supply.productGroup +
		"' and P_PRD_SUPPLYINFO.supply_cost = '" + supply.supplyCost +
		"' and P_PRD_SUPPLYINFO.max_order_daily = '" + supply.maxDailyOrder +
		"' and P_PRD_SUPPLYINFO.shipping_cost = '" + supply.shippingCost +
		"' and P_PRD_SUPPLYINFO.reorder_email = '" + supply.recorderMail +
		"' and P_PRD_SUPPLYINFO.reorder_threshold = '" + supply.recorderThreshold +
		"' and D_FULFILLMENT_PARTY.description = '" + supply.fulfillmentParty + 
		"' and P_PRD.active_ind = 1";
	
		logger.info("Execute query: " + query);
		List<String> rs = db.executeQuery(query, "PRDID");
		if (rs.size()<1)
		{
			logger.error("[FAILED]Create new supply product failed, Supply Code:" + supply.code + ",Name:" + supply.name);
			passed = false;
		}else{
			supply.id = rs.get(0);
			logger.info("[PASSED]Create new supply product successful, Supply Code:" + supply.code + ",Name:" + supply.name);
		}
		if(!passed){
			throw new ErrorOnPageException("Create new supply product failed, please see the log above!");
		}
	}

}
