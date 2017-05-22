package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntLocationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntLocationsListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a new date period for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyHuntLocation' has been assigned
 * @author Phoebe
 * @Date  Nov 27, 2012
 */
public class AddHuntLocationFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private LocationInfo huntloc = new LocationInfo();
	private boolean loggedIn = false;
	private String location = "";
	private String contract = "";
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrHuntLocationsListPage huntLocLisPg = LicMgrHuntLocationsListPage.getInstance();
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		huntloc = (LocationInfo)param[1];
		
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
		if(!huntLocLisPg.exists()){
			lm.gotoLotteriesProductListPgFromTopMenu();
			lm.gotoHuntLocationListPgFromLotteriesProdListPg();
		}
		lm.addHuntLocation(huntloc);
		this.verifyResult();
		newAddValue = huntloc.getCode();
	}

	private void verifyResult() {
		boolean passed = true;
		LicMgrAddHuntLocationPage addPg = LicMgrAddHuntLocationPage.getInstance();
		if(addPg.exists()){
			logger.error("[FAILED]Add quota failed, failed reson:" + addPg.getErrorMsg());
			addPg.clickCancel();
			ajax.waitLoading();
			huntLocLisPg.waitLoading();
			passed = false;
		}else{
			String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split(" ")[0];
			db.resetSchema(schema);
			//Check hunt location has been added
			String location_sql = "select id from D_HUNT_LOCATION where code='"
					+ huntloc.getCode() + "' and description='" + huntloc.getDescription() + "'";
			List<String> rs =  db.executeQuery(location_sql, "ID");
			if (rs.size()!=1){
				logger.error("Hunt Location(CD:"+ huntloc.getCode() +") added failed");
				passed = false;
			}else{
				logger.info("[PASSED]Hunt Location(CD:"+ huntloc.getCode() +") added successful");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("[FAILED]Create new hunt location failed, please see the log above!");
		}
	}
}
