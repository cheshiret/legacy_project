package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrEditSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSpeciesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: the support script is to add species.
 * 1. The harvest designation should be unique and the default value will be the max id of the existing record plus 1. 
 * 2. If there are already 99 species in the contract, no new species can be added.
 * 3. If the harvest designation and/or description of one record in the database are the same as the existing one in the system, the record won't be added.
 * 
 * @Preconditions:
 * 1. The contract, location and description should be required in the database.
 * 2. The description should be unique in the same contract.
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 21, 2012
 */
public class AddSpeciesFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrSpeciesConfigurationPage speciesConfigPg = LicMgrSpeciesConfigurationPage.getInstance();
	private Species species = new Species();
	private boolean isUseDefaultHarvestDesig = false;
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	private String harDesId;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[1];
		species = (Species)param[2];
		isUseDefaultHarvestDesig = (Boolean)param[3];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	@Override
	public void execute() {
		// Get the harvest designation. If there are 99 species, throws an exception.
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
		lm.gotoProdConfPgFromTopMenu();
//		if(MiscFunctions.isQA24()){
//			harDesId = lm.addSpecies(species, isUseDefaultHarvestDesig, true);
//		}else{
//			harDesId = lm.addSpecies(species.speciesId, species.description, isUseDefaultHarvestDesig, true);
		if(speciesConfigPg.getAllSpecies().toString().contains(species.description)){
			species.speciesId = harDesId = lm.editSpeciesSubType(species);
		}else species.speciesId = harDesId = lm.addSpecies(species, isUseDefaultHarvestDesig);
//	}
		this.verifyResult();			
		newAddValue = harDesId;
	}
	
	/**
	 * Verify the result. If add species widget doesn't exist, it is passed. Otherwise failed.
	 */
	public void verifyResult(){
		boolean passed = false;
		LicMgrAddSpeciesWidget addSpeciesWidget = LicMgrAddSpeciesWidget.getInstance();
		LicMgrEditSpeciesWidget editSpeciesWidget = LicMgrEditSpeciesWidget
				.getInstance();
		String speciesInfo = " Harvest Designation = " + species.speciesId +
				" Description = " + species.description;
		
		if (addSpeciesWidget.exists()) {
			logger.error("Add species failed:" + speciesInfo);	
			String errMsg = addSpeciesWidget.getErrorMessage();
			addSpeciesWidget.clickCancel();
			ajax.waitLoading();
			speciesConfigPg.waitLoading();
			throw new ErrorOnPageException("Add species failed:" + speciesInfo + ", due to:" + errMsg);
		} else if (editSpeciesWidget.exists()) {
			logger.error("Edit species failed:" + speciesInfo);	
			String errMsg = editSpeciesWidget.getErrorMessage();
			editSpeciesWidget.clickCancel();
			ajax.waitLoading();
			speciesConfigPg.waitLoading();
			throw new ErrorOnPageException("Edit species failed:" + speciesInfo + ", due to:" + errMsg);
		}else {
			List<String> speInfo = speciesConfigPg.getRowInfo("species", harDesId, "Harvest Designation");
			if(species.speciesId.equalsIgnoreCase(speInfo.get(0))&&species.code.equalsIgnoreCase(speInfo.get(1))
					&&species.description.equalsIgnoreCase(speInfo.get(2))&&species.locationAlias.equalsIgnoreCase(speInfo.get(3)))
				{
				    passed = true;
				    logger.info("[PASSED]Specie(desc:"+species.description + ") added sucessful!");
				}
		} 
		if(!passed){
			logger.error("[FAILED]Specie(desc:"+species.description + ") added failed!");
			throw new ErrorOnPageException("Add species failed:" + speciesInfo);
		}
	}
	
}
