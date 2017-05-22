package com.activenetwork.qa.awo.supportscripts.support.licensemgr;


import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableListPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Mar 16, 2012
 */
public class AddConsumableProduct extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private ConsumableInfo consumableInfo = new ConsumableInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrConsumableListPage consumableListPage = LicMgrConsumableListPage.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();;
	
	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !consumableListPage.exists())){
			lm.loginLicenseManager(login);
			loggedIn=true;
			lm.gotoConsumableSearchListPageFromTopMenu();
		}
		
		// add consumable product basic information
		lm.addConsumableProduct(consumableInfo);
		this.verifyResult();
		
		
		
		// in order to run next data in data pool
		contract=login.contract;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 6; // the start point in the data pool
		endpoint = 6; // the end point in the data pool

		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "Code";
		logMsg[2] = "Name";
		logMsg[3] = "Result";
	}
	
	public boolean verifyResult(){
		
		 
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
			logger.info("Could not find the created consumable product in DB!!!!!");
			logMsg[3] = "Could not find the created consumable product in DB!!!!!";
			return false;
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
				logMsg[3] = "product ID is "+tmp[0];
				return true;					
			}else{
				logMsg[3] = "creation failed!!!, data in DB is "+printTmp+", please check.";
				logger.info("Verify created consumble product in DB failed!!!!!!!!!!!");
				return false;
			}
			
		}
		
		return false;

	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		consumableInfo.orderType = dpIter.dpString("orderType");
		consumableInfo.code = dpIter.dpString("code");
		consumableInfo.status = dpIter.dpString("status");
		consumableInfo.name = dpIter.dpString("name");
		consumableInfo.description = dpIter.dpString("description");
		consumableInfo.productGroup = dpIter.dpString("productGroup");
		if(dpIter.dpString("variablePrice").equalsIgnoreCase("yes"))
		{
			consumableInfo.variablePrice = true;
		}else{
			consumableInfo.variablePrice = false;
		}
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = consumableInfo.code;
		logMsg[2] = consumableInfo.name;
	}
}
