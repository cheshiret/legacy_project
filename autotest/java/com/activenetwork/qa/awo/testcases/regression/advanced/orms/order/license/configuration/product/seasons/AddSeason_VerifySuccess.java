package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.seasons;

import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSeasonsConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify add season success 
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-507
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSeason_VerifySuccess extends LicenseManagerTestCase{

	private String seq, description, printOrder, createUser, createLocation;
	private boolean passed = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoSeasonProdConfPgFromTopMenu();
		String harvDesgin =  lm.addSeasons(printOrder, description, true);
		this.verifySeasonInfo(harvDesgin, "Description", description);
		this.verifySeasonInfo(harvDesgin, "Print Order", printOrder);
		this.verifySeasonInfo(harvDesgin, "Creation User", createUser);
		this.verifySeasonInfo(harvDesgin, "Creation Location", createLocation);
		
		if(!passed){
			throw new ErrorOnPageException("There are some verificaiton points is not correct, please check error log");
		}
		
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi "
				+ "Department of Wildlife, Fisheries, and Parks";
		
		seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		description = "QA Test" + seq;
		printOrder = seq;
		createUser = login.userName;
		createLocation = "Mississippi Department of Wildlife, Fisheries, and Parks";
		
	}
	
	/**
	 * verify season info
	 * @param harvestDesignation
	 * @param colName
	 * @param expectInfo
	 */
	private void verifySeasonInfo(String harvestDesignation, String colName, String expectInfo){
		LicMgrSeasonsConfigurationPage seasonConfigPage = LicMgrSeasonsConfigurationPage.getInstance();
		
		logger.info("Verify season info.");
		
		String actualInfo = seasonConfigPage.getSeasonInfo(harvestDesignation, colName);
		
		if(!actualInfo.equalsIgnoreCase(expectInfo)) {
			logger.error("The actual species info of " + colName + " '" + actualInfo
					+ "' is not match the expected info '" + expectInfo + "'");
			passed = false;
		}else {
			logger.info("The season info of " + colName + "'" + expectInfo + "' is correct for harvest designation " + harvestDesignation);
		}
		
	}

}
