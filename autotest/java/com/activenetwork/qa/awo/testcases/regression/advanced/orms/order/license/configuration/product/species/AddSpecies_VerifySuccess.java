package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.species;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSpeciesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify add species success 
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-503
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSpecies_VerifySuccess extends LicenseManagerTestCase{
	private String seq;
	private boolean passed = true;
	private Species species = new Species();
	
	public void execute() {
		lm.loginLicenseManager(login);		
		lm.gotoProdConfPgFromTopMenu();
		
		String harvestDesignation = lm.addSpecies(species, true, true);
		this.verifySpeciesInfo(harvestDesignation, "Description", species.description);
		this.verifySpeciesInfo(harvestDesignation, "Creation User", species.creationUser);
		this.verifySpeciesInfo(harvestDesignation, "Creation Location", species.creationLocation);
		
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
		species.description = "QA Test" + seq;
		species.creationUser = login.userName;
		species.creationLocation = login.location.split("/")[1];
		species.code =  seq;
		species.locationAlias = "Unit";
	}
	
	/**
	 * verify the species info from species list
	 * @param harvestDesignationa
	 * @param colName
	 * @param expectInfo
	 */
	private void verifySpeciesInfo(String harvestDesignation, String colName, String expectInfo){
		LicMgrSpeciesConfigurationPage speciesConfPage = LicMgrSpeciesConfigurationPage.getInstance();
		String actualInfo = speciesConfPage.getSpeciesInfo(harvestDesignation, colName);
		if(!actualInfo.equalsIgnoreCase(expectInfo)) {
			logger.error("---The actual species info of " + colName + " '" + actualInfo
					+ "' is not match the expected info '" + expectInfo + "'");
			passed = false;
		}else {
			logger.info("The species info of " + colName + "'" + expectInfo + "' is correct for harvest designation " + harvestDesignation);
		}
	}
}
