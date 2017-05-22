package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.season;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Season;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSeasonsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**  
 * @Description: Add hunting season.
 * @Preconditions:  
 * @SPEC:  Add hunting season.
 * @Task#: Auto-867
 * @author jwang8  
 * @Date  Jan 16, 2012    
 */
public class AddSeason extends LicenseManagerTestCase{
	
	private Season season = new Season();
    private LicMgrSeasonsConfigurationPage seasonConfigPg = LicMgrSeasonsConfigurationPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoSeasonProdConfPgFromTopMenu();
		season.harvestDesignation =  lm.addSeasons(season.printOrder, season.despription, true);
		this.verifySeasonInfoSuccess(season);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		season.despription = "test" + DataBaseFunctions.getEmailSequence();
		season.printOrder = String.valueOf(DataBaseFunctions.getEmailSequence());
		season.creationUser = login.userName;
		season.creationLocation = login.location.split("/")[1];
	}
	
	/**
	 *
	 * @param expectedSeason
	 */
	public void verifySeasonInfoSuccess(Season expectedSeason){
		boolean pass = seasonConfigPg.compareSeasonInfo(expectedSeason);
		if(!pass){
			throw new ErrorOnPageException("The sepecific season info is error.");
		}else{
			logger.info("Add season successfully");
		}
	}
}
