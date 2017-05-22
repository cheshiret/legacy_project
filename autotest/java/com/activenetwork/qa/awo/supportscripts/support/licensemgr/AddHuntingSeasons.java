/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Season;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSeasonsConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add the hunting seasons for License Manager, specific contract.
 * @Preconditions: #1. the print Order and description in datapool need to ensure is unique in table: D_HUNT_SEASON; 
 * #2. HarvestDesig should be null in datapool if your test cases did not request to specify the harvestDesig; 
 * #3. If harvestDesig is not null, please ensure the range is 01-99 and unique in table: D_HUNT_SEASON;
 * @SPEC:
 * @Task#:
 * @author fliu
 * @Date April 16th, 2012
 */
public class AddHuntingSeasons extends SupportCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private Season season = new Season();
	private String schema = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrSeasonsConfigurationPage seasonConfigPage = LicMgrSeasonsConfigurationPage
			.getInstance();
	private String owner = "";// who does own the schedule ? If null, case will ignore the column in datapool;
	private String testSuite = "";// The test suite you choose, If null, case will ignore the column in datapool;

	@Override
	public void execute() {
		// log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if ((!loggedIn)) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if ((loggedIn && !seasonConfigPage.exists())) {
			lm.gotoSeasonProdConfPgFromTopMenu();
		}

		// add hunting season under product configuration page
		// use default harvest designation as season harvest desig
		if(season.harvestDesignation.isEmpty()){
			season.harvestDesignation = lm.addSeasons(season.printOrder, season.despription, true);
			this.verifySeasonInfo(season);
		}
		// use specific harvest designation as season harvest desig
		else {
			// ensure the harvest designation from datapool is valid
			if( Integer.parseInt(season.harvestDesignation) > 0 && Integer.parseInt(season.harvestDesignation) < 100 ){

			db.resetSchema(schema);
			String sql = "select ID from D_HUNT_SEASON where HV_DESIG = '" + season.harvestDesignation + "'";

			List<String> IDs = db.executeQuery(sql, "ID");
			// ensure the valid harvest designation is unique
			if(IDs.size()==0){
				season.harvestDesignation = lm.addSeasons(season.harvestDesignation, season.printOrder, season.despription, false, true);
				this.verifySeasonInfo(season);
				}
			else{
				logMsg[3] = "Failed";
				throw new ErrorOnPageException("The season harvest designation must be unique. Please check the data you set into the datapool.");
				}
			}
		else{
			logMsg[3] = "Failed";
			throw new ErrorOnPageException("The season harvest designation cmust be 1-99. Please check the data you set into the datapool.");
		}
		}
		
		contract = login.contract;
		if(season.harvestDesignation.equalsIgnoreCase("99")){
			logger.warn("Next huntting season may cannot be added since harvest designation is not alowed bigger than 99.");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 1; // the start point in the data pool
		endpoint = 1; // the end point in the data pool

		dataSource = casePath + "/" + caseName;

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");

		logMsg = new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "printOrder";
		logMsg[2] = "Description";
		logMsg[3] = "Result";
	}

	@Override
	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+dpIter.dpString("contractForSchema");

		if (testSuite.trim().length() > 0 || owner.trim().length() > 0) {
			while (!testSuite.equalsIgnoreCase(dpIter.dpString("testSuite"))
					|| !owner.equalsIgnoreCase(dpIter.dpString("owner"))) {
				dpIter.dpNext();
			}
		}

		season.harvestDesignation = dpIter.dpString("harvestDesig");
		season.printOrder = dpIter.dpString("printOrder");
		season.despription = dpIter.dpString("description");
		season.creationUser = login.userName;
		season.creationLocation = login.location.split("/")[1];

		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = season.printOrder;
		logMsg[2] = season.despription;
	}

	/**
	 * @param expectedSeason
	 */
	public void verifySeasonInfo(Season expectedSeason){
		
		logger.info("Verify hunting season info.");
		ajax.waitLoading();
		seasonConfigPage.waitLoading();
		boolean pass = seasonConfigPage.compareSeasonInfo(expectedSeason);
		if(!pass){
			logMsg[3] = "Failed";
			throw new ErrorOnPageException("The sepecific season info is error. Saeson description is: " + season.despription + ", season print order is: " + season.printOrder);
		}else{
			logMsg[3] = "Pass";
			logger.info("Add season successfully!");
		}
	}
}
