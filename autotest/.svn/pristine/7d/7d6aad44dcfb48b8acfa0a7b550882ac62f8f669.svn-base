package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.species;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrSpeciesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Add species.(Delete the data from D_SPECIES table);
 * @Preconditions:  
 * @SPEC:  Add Species.
 * @Task#: Auto-867
 * @author jwang8  
 * @Date  Jan 16, 2012    
 */
public class AddSpecies extends LicenseManagerTestCase{
	LicMgrSpeciesConfigurationPage speciesConfPage = LicMgrSpeciesConfigurationPage.getInstance();
	private Species species = new Species();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);		
		lm.gotoProdConfPgFromTopMenu();
		
		species.speciesId = lm.addSpecies(species,true,true);
		this.verifySpeciesInfoSuccess(species);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		species.description = "Test" + DataBaseFunctions.getEmailSequence();
		species.creationUser = login.userName;
		species.creationLocation = login.location.split("/")[1];
		species.code =  String.valueOf(DataBaseFunctions.getEmailSequence()).substring(0, 3);
		species.locationAlias = "Unit";
	}
	
	public void verifySpeciesInfoSuccess(Species species){
		boolean pass = speciesConfPage.compareSpeciesInfo(species);
		if(!pass){
			throw new ErrorOnPageException("The species info is error.");
		}else{
			logger.info("Add species success");
		}
	}
}
