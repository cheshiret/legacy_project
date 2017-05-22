package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddDatePeriodsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a new date period for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyDatePeriod' has been assigned
 * @author Phoebe
 * @Date  Nov 12, 2012
 */
public class AddHuntDatePeriodsFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private boolean loggedIn = false;
	private String location = "";
	private String contract = "";
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrDatePeriodsListPage dpListPg = LicMgrDatePeriodsListPage.getInstance();
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		datePeriod = (DatePeriodInfo)param[1];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	@Override
	public void execute() {
		//Login finance manager  
		if((!contract.equalsIgnoreCase(login.contract)||!location.equalsIgnoreCase(login.location))&& loggedIn && isBrowserOpened){
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		contract = login.contract;
		location = login.location;
		if((!loggedIn || !isBrowserOpened)){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!dpListPg.exists()){
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		}
		
		lm.addDatePeriods(datePeriod);
		
		this.verifyResult();
		newAddValue = datePeriod.getCode();
	}
 
	private void verifyResult(){	
		boolean passed = true;
		LicMgrAddDatePeriodsPage addPg = LicMgrAddDatePeriodsPage.getInstance();
		if(addPg.exists()){
			logger.error("[FAILED]Add quota failed, failed reson:" + addPg.getErrorMsg());
			addPg.clickCancel();
			ajax.waitLoading();
			dpListPg.waitLoading();
			passed = false;
		}else{
			String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split(" ")[0];
			db.resetSchema(schema);
			//Check date period has been added
			String date_period_sql = "select id from D_DATE_PERIOD  where code='"
					+ datePeriod.getCode() + "' and description='" + datePeriod.getDescription() + "'";
			List<String> rs =  db.executeQuery(date_period_sql, "ID");
			if (rs.size()<1){
				logger.error("Date period(CD:"+ datePeriod.getCode() +") added failed");
				passed = false;
			}else{
				logger.info("[PASSED]Date period(CD:"+ datePeriod.getCode() +") added successful!");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("[FAILED]Create new date period failed, please see the log above!");
		}
	}
}
