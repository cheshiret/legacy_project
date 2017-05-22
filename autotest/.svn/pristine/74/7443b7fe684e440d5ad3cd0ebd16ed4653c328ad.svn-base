package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MergeCustomerFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private boolean loggedIn = false;// Don't login.
	private String preContract = "";
	private String preLocation = "";

	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private Customer cust = new Customer();

	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	String schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		cust = (Customer)param[1];

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	@Override
	public void execute() {
		//License Manager only can switch in Home page
		if (!login.contract.equals(preContract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(preContract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(preLocation)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}

		preContract = login.contract;
		preLocation = login.location;

		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.mergeCustomFromCustomerDetailPg();
		lm.gotoHomePage();
		checkMergeStatusOfCustomerInDB(cust);
		newAddValue = cust.custNum;
	}

	public void checkMergeStatusOfCustomerInDB(Customer customer ){
		boolean passed = true;
		db.resetSchema(schema);
		logger.info("Changed schema to -->>"+schema);
		String[] colNames = { "cust_number"};
		String query = "SELECT hfcustomer0_.cust_number	FROM C_CUST_HFPROFILE hfcustomer0_,		  C_CUST customer1_		WHERE hfcustomer0_.CUST_ID = customer1_.CUST_ID		" +
				"AND hfcustomer0_.STATUS_ID =4		" +   //4 means means merged
				"AND customer1_.merge_ind = 1		" +	//1 means means merged
				"AND customer1_.L_NAME  ='"+customer.lName +"'		" +
				"AND customer1_.F_NAME  ='"+customer.fName +"'		" +
				"AND hfcustomer0_.BIRTHDAY     =to_date('"+customer.dateOfBirth+"','mm-dd-yyyy')	" +
				"ORDER BY hfcustomer0_.LAST_TXN_DATE DESC NULLS LAST";
		logger.info("Execute query: " + query);
		List<String[]> result = db.executeQuery(query, colNames);
		if(result.size()>=1)
		{
			logger.info("[PASSED]Found merged customer records: "+result.size()+" records!");
			cust.custNum = result.get(0)[0];
		}else{
			logger.info("[FAILED]Found merged customer records: "+result.size()+" records!");
			passed = false;
		}

		if(!passed){
			throw new ErrorOnPageException("Merged customer failed, please see the log above!");
		}
	}
}
