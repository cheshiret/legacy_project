package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddConsumableProductFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private ConsumableInfo consumableInfo = new ConsumableInfo();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();;
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		consumableInfo = (ConsumableInfo)param[1];
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
		lm.gotoConsumableSearchListPageFromTopMenu();
		
		// add consumable product basic information
		lm.addConsumableProduct(consumableInfo);
		this.verifyResult();
		newAddValue = consumableInfo.id;
		
	}

	public void verifyResult(){
		boolean passed = false;
		String schema = "MS";
		String env = TestProperty.getProperty("target_env");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + schema;
		db.resetSchema(schema);
		logger.info("Changed schema to -->>"+schema);
		String[] colNames = { "prd_id", 
				"prd_name", 
				"prd_cd" ,
				"status", 
				"prd_type",
				"price_ind",
				"prd_subcat_id"};

		String query = "select prd_id, prd_name,prd_cd, " +
				"decode(active_ind, 1, 'active', 0, 'inactive' )as status , " +
				"decode(product_cat_id, 4, 'pos','others') as prd_type, " +
				"decode(variable_price_ind, 0, 'false', 1 ,'true') as price_ind," +
				"prd_subcat_id "+
				"from P_PRD where prd_cd = '"
				+consumableInfo.code+"'";
		logger.info("Execute query: " + query);
		List<String[]> rs = db.executeQuery(query, colNames);
		String printTmp = null;
		if (rs.size()==0)
		{
			logger.error("[FAILED]Could not find the created consumable product in DB!!!!!");
		}
		
		for(String[] tmp : rs)
		{
			printTmp="";
			for (String s: tmp)
			{
				printTmp+="["+s+"]";
			}
			logger.info(printTmp);
			
			
			if ((tmp[2].equalsIgnoreCase(consumableInfo.code))
					&& (tmp[3].equalsIgnoreCase(consumableInfo.status))
					&& (tmp[4].equalsIgnoreCase("pos"))	// prd_type: 3: site , 4: POS , 6: ticking, stored in table: D_REF_PRD_GRP_CAT
					&&(Boolean.toString(consumableInfo.variablePrice)).equalsIgnoreCase(tmp[5]))
			{
				logger.info("[PASSED]Created consumble product in DB failed! product_code is:" + consumableInfo.code);
				passed = true;
				consumableInfo.id = tmp[0];
				break;
			}
		}
		if(!passed){
			logger.error("[FAILED]Created consumble product in DB failed! product_code is:" + consumableInfo.code);
			throw new ErrorOnPageException("Add consumable product failed, please see the log above!");
		}
	}
}
