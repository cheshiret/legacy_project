package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddDiscount extends SupportCase {
	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
			.getInstance();
	private LoginInfo login = new LoginInfo();
	private DiscountData discount;
	private String discountName = "";
	private String contract = "";
	private boolean loggedIn = false;

	@Override
	public void execute() {
		
		// Login Finance Manager
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			fin.logoutFinanceManager();
			loggedIn = false;
		}
		if ((!loggedIn) || (loggedIn && !discountPg.exists())) {
			fin.loginFinanceManager(login);
			fin.gotoDiscountPage();
			loggedIn = true;
		}

		// add new discount.
		discountName = fin.addNewDiscount(discount);
		// Activate Discount
		discountPg.changeDiscountStatus(discountName, "Active");

		// verify current discount's status and put out log information
		if (discountPg.isDiscountActive(discountName)
				&& discountName.length() > 0) {
			logMsg[1] = discountName;
			logMsg[4] = "Active";
			logMsg[5] = "Success";
		} else {
			logMsg[1] = discountName;
			logMsg[4] = "Inactive";
			logMsg[5] = "Fail";
		}

		contract = login.contract;
		
		//checkDataInDB();
	}
	
	public void checkDataInDB()
	{
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		db.resetSchema(schema);
		logger.info("Changed schema to -->>"+schema);
		String[] colNames = { "id", "cd", "name", "dscr", "active_ind",
				"auto_discnt_ind", "discnt_rate_type_id", "calc_use_current", "add_dscnt_ind", "display_ind",
				"unit_type_id", "fee_name" };

		String query = "select discount.id,"
		  +"discount.cd,"
		  +"discount.name,"
		  +"discount.dscr,"
		  +"decode(discount.active_ind,1,'yes',0,'no') as \"active_ind\", "
		  +"decode(discount.auto_discnt_ind,1,'yes',0,'no') as \"auto_discnt_ind\", "
		  +"decode(discount.discnt_rate_type_id,1,'percent',2, 'flat',3,'override') as \"discnt_rate_type_id\", "
		  +"calc_use_current,"
		  +"decode(discount.add_dscnt_ind,1,'yes',0,'no') as \"add_dscnt_ind\", "
		  +"decode(discount.display_ind,1,'yes',0,'no') as \"display_ind\", "
		  +"decode(discount.unit_type_id,1,'Per Unit of Stay',2,'Per Stay') as \"unit_type_id\", "
		  +"feetype.name as \"fee_name\" "
		+"from d_ref_discnt_type discount,d_ref_allowed_discnt disfee, d_ref_fee_type feetype "
		+"where disfee.discnt_type_id=discount.id " 
		+"and feeType.ID=disfee.fee_type_id "
		+"and discount.name ='"+discount.name +"'";
		logger.debug("Execute query: " + query);
		List<String[]> result = db.executeQuery(query, colNames);
		String printTmp = null;
		for(String[] tmp : result)
		{
			printTmp="";
			for (String s: tmp)
			{
				printTmp+=s+"||";
			}
			logger.info(printTmp);
		}
		
	}
	@Override
	public void getNextData() {
		discount = new DiscountData();
		// clear list content
		discount.feeTypes.clear();
		discount.behaviors.clear();
		// login info
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");

		// discount data
		discount.name = dpIter.dpString("discountName");
		discount.description = dpIter.dpString("description");
		discount.rateType = dpIter.dpString("rateType");

		String[] feeTypes = dpIter.dpString("feeTypes").split(",");
		for (int i = 0; i < feeTypes.length; i++) {
			discount.feeTypes.add(feeTypes[i]);
		}

		String[] behaviors = dpIter.dpString("behaviors").split(",");
		for (int j = 0; j < behaviors.length; j++) {
			discount.behaviors.add(behaviors[j]);
		}

		discount.rateUnit = dpIter.dpString("rateUnit");
		discount.promoCode = dpIter.dpString("promoCode");
		discount.promoDescription = dpIter.dpString("promoDescription");

		logMsg[0] = cursor + " ";
		logMsg[1] = "Unknown";
		logMsg[2] = dpIter.dpString("feeTypes");
		logMsg[3] = dpIter.dpString("behaviors");
		logMsg[4] = "Inactive";
		logMsg[5] = "Fail due to error";
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 33; // the start point in the data pool
		endpoint = 33; // the end point in the data pool

		dataSource = casePath + "/" + caseName;

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		login.envType = "QA";

		// log information
		logMsg = new String[6];
		logMsg[0] = "cursor";
		logMsg[1] = "disName";
		logMsg[2] = "disFeeTypes";
		logMsg[3] = "disBehaviors";
		logMsg[4] = "DisActiveOrInactive";
		logMsg[5] = "result";

	}

	@Override
	protected void processError(Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void finalize() {
		// TODO Auto-generated method stub
		
	}

}
