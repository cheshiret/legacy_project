package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSpeciesWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSpeciesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: the support script is to add species.
 * 1. The harvest designation should be unique and the default value will be the max id of the existing record plus 1. 
 * 2. If there are already 99 species in the contract, no new species can be added.
 * 3. If the harvest designation and/or description of one record in the datapool are the same as the existing one in the system, the record won't be added.
 * 
 * @Preconditions:
 * 1. The contract, location and description should be required in the datapool.
 * 2. The description should be unique in the same contract.
 * 3. The harvesDesig should be unique, but not required in the datapool. If it is empty in the datapool, a default one will be used in the case. 
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2012
 */
public class AddSpecies extends SupportCase {	
	private boolean loggedIn = false;
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrSpeciesConfigurationPage speciesConfigPg = LicMgrSpeciesConfigurationPage.getInstance();
	private Species species = new Species();
	private boolean isUseDefaultHarvestDesig = false;
	private String contract = "";
	private String harvDesig;
	private String schema;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private String sql;
	private List<String> results;
	private int totalSpecies;
	
	@Override
	public void execute() {
		// Get the harvest designation. If there are 99 species, throws an exception.
		harvDesig = this.getHarvDesign();
		
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		// Login in
		if (!loggedIn || (loggedIn && !speciesConfigPg.exists())) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		contract = login.contract;
		
		// Go to Species Configuration page. The first tab on Product Configuration page is Species. 
		lm.gotoProdConfPgFromTopMenu();
		
		// Add new species
		logger.info("Adding the species with the harvest designation: " + harvDesig);
		lm.addSpecies(harvDesig, species.description, isUseDefaultHarvestDesig, true);
		
		// verify the result
		this.verifyResult();	 
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		species.speciesId = dpIter.dpString("harvestDesig").trim();
		species.description = dpIter.dpString("description").trim();
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = species.description;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 4;
		endpoint = 5;
		
		dataSource = casePath + "/" + caseName;
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[3];
		logMsg[0] = "Index";
		logMsg[1] = "Description";
		logMsg[2] = "Result";	
	}
	
	/**
	 * Verify the result. If add species widget doesn't exist, it is passed. Otherwise failed.
	 */
	public void verifyResult(){
		LicMgrAddSpeciesWidget addSpeciesWidget = LicMgrAddSpeciesWidget.getInstance();
		String speciesInfo = " Harvest Designation = " + species.speciesId +
				" Description = " + species.description;
		
		if (addSpeciesWidget.exists()) {
			logger.error("Add species failed:" + speciesInfo);				
			logMsg[2] = "Failed due to " + addSpeciesWidget.getErrorMessage();
			addSpeciesWidget.clickCancel();
			ajax.waitLoading();
			speciesConfigPg.waitLoading();
		} else {
			logMsg[2] = "PASSED";			
		} 
	}
	
	/**
	 * Get the value of the harvest designation. 
	 * 1. If it is not defined in the datapool and there are already 99 species, throws an error.
	 * 2. If it is not defined in the datapool and the max harvest designation is 99, but the total number of species is less than 99, try to get an available one.
	 * 3. If it is not defined in the datapool and the max harvest designation is not 99, use the default one (max + 1) for the new species.
	 * 4. If it is defined in the datapool. the specific one will be used.
	 * 
	 * @return
	 */
	public String getHarvDesign() {
		if (StringUtil.isEmpty(species.speciesId)) {
			schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split(" ")[0];
			db.resetSchema(schema);
			// Get the current default harvest designation. 
			sql = "Select max(hv_desig) as MAX from D_SPECIES";
			results = db.executeQuery(sql, "MAX");
			String maxHarvDesig = "";
			if (results.size() > 0) {
				maxHarvDesig = results.get(0);
			}
			logger.info("The max harvest designation is " + maxHarvDesig);
			
			// If the current max harvest designation is 99, find another available value
			if (Integer.parseInt(maxHarvDesig) == 99) {
				// Check the total number of species in DB
				sql = "Select Count(*) from D_SPECIES";
				results = db.executeQuery(sql, "COUNT(*)");
				totalSpecies = Integer.parseInt(results.get(0));		
				logger.info("total number of Species is " + totalSpecies);
				if (totalSpecies >= 99) {
					logMsg[2] = "Failed";
					throw new ErrorOnDataException("there are already 99 species and can not be added any new one.");
				} else {
					logger.info("The current default harvest designation is 100. Try to get an available one...");	
					sql = "Select HV_DESIG from D_SPECIES";
					results = db.executeQuery(sql, "HV_DESIG");
					String harvDesig;
					for (int startIndex = 1; startIndex < 100; startIndex++) {
						harvDesig = String.valueOf(startIndex);
						if (!results.contains(harvDesig)) {
							logger.info("The available harvest designation is " + harvDesig);
							return harvDesig;
						}							
					}
				}
			}
			
			// When the max harvest designation is not 99, return the empty harvest designation and set the isUseDefaultHarvestDesig as true to use the default value for the new species.
			isUseDefaultHarvestDesig = true;
			return species.speciesId;
		}
		
		return species.speciesId;	
	}
}
