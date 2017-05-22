package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Season;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSeasonWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSeasonsConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add the hunting seasons for License Manager, specific contract.
 * @Preconditions: #1. the print Order and description in datapool need to ensure is unique in table: D_HUNT_SEASON; 
 * #2. HarvestDesig should be null in datapool if your test cases did not request to specify the harvestDesig; 
 * #3. If harvestDesig is not null, please ensure the range is 01-99 and unique in table: D_HUNT_SEASON;
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 21, 2012
 */
public class AddHuntingSeasonFunction extends FunctionCase {
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	public LoginInfo login = new LoginInfo();
	public Season season = new Season();
	public String schema = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrSeasonsConfigurationPage seasonConfigPage = LicMgrSeasonsConfigurationPage
			.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		login.contract = (String)param[0];
		login.location = (String)param[1];
		schema = (String)param[2];
		season = (Season)param[3];
		season.creationUser = login.userName;
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
		lm.gotoSeasonProdConfPgFromTopMenu();
		
		// add hunting season under product configuration page
		// use default harvest designation as season harvest desig
		if(StringUtil.isEmpty(season.harvestDesignation)){
			season.harvestDesignation = lm.addSeasons(season.printOrder, season.despription, true);
			this.verifyAddSeasonCompletely();
			this.verifySeasonInfo(season);
		} else { // use specific harvest designation as season harvest desig
			// ensure the harvest designation from datapool is valid
			if( Integer.parseInt(season.harvestDesignation) > 0 && Integer.parseInt(season.harvestDesignation) < 100 ){
				db.resetSchema(schema);
				String sql = "select ID from D_HUNT_SEASON where HV_DESIG = '" + season.harvestDesignation + "'";

				List<String> IDs = db.executeQuery(sql, "ID");
				// ensure the valid harvest designation is unique
				if(IDs.size()==0) {
					season.harvestDesignation = lm.addSeasons(season.harvestDesignation, season.printOrder, season.despription, false, true);
					this.verifyAddSeasonCompletely();
					this.verifySeasonInfo(season);
				} else {
					throw new ErrorOnPageException("The season harvest designation must be unique. Please check the data you set into the datapool.");
				}
			} else{
				throw new ErrorOnPageException("The season harvest designation cmust be 1-99. Please check the data you set into the datapool.");
			}
		}
		
		if(season.harvestDesignation.equalsIgnoreCase("99")){
			logger.warn("Next huntting season may cannot be added since harvest designation is not alowed bigger than 99.");
		}
		newAddValue = season.harvestDesignation;
	
	}
	
	/**
	 * Verify add season completely, that is the add season widget can't exist after add season. 
	 * 
	 * @author Lesley Wang
	 * @date Nov 12, 2012
	 */
	private void verifyAddSeasonCompletely() {
		LicMgrAddSeasonWidget addSeasonWidget = LicMgrAddSeasonWidget.getInstance();
		if (addSeasonWidget.exists()) {
			String errMeg = addSeasonWidget.getErrorMessage();
			addSeasonWidget.clickCancel();
			seasonConfigPage.waitLoading();
			throw new ErrorOnPageException("Fail to add the sepecific season (Saeson description=" + season.despription 
					+ "). Due to: " + errMeg);
		}
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
			throw new ErrorOnPageException("The sepecific season info is error. Saeson description is: " + season.despription + ", season print order is: " + season.printOrder);
		}else{
			logger.info("Add season successfully!");
		}
	}

}
