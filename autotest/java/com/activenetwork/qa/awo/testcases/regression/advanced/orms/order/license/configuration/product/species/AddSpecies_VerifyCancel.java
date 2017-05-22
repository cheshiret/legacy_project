package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.species;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify cancel action and default harvest designation business rule when add species
 * @Preconditions:
 * @SPEC:Add Species
 * @Task#:Auto-503
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSpecies_VerifyCancel extends LicenseManagerTestCase{

	private String seq, description;
	private boolean passed = true;
	private LicMgrProductConfigurationPage prodConfPage = LicMgrProductConfigurationPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);		
		lm.gotoProdConfPgFromTopMenu();
		
		String defaultHarvestDesignation = lm.addSpecies(description, false);
		this.verifyCancelAction(defaultHarvestDesignation);
		this.verifyHarvestDesignation(defaultHarvestDesignation);
		
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
	}
	
	/**
	 * verify the cancel function
	 * @param harvestDesignation
	 * @return
	 */
	private void verifyCancelAction(String harvestDesignation){		
		logger.info("Verify cancel action.");
		
		List<String> harvestDesignationList = prodConfPage.getColumnValue("species", "Harvest Designation");
				
		if(harvestDesignationList.contains(harvestDesignation)){
			logger.error("The function of cancel is failed");
			passed = false;
		}else {
			logger.info("The function of cancel is successful");
		}
	}
	
	/**
	 * verify the default harvest designation
	 * @param defaultHarvDesig
	 */
	private void verifyHarvestDesignation(String defaultHarvDesig){	
		logger.info("Verify default havest designation.");
		
		List<String> harvestDesignationList = prodConfPage.getColumnValue("species", "Harvest Designation");
		harvestDesignationList.remove(0);		
		String harvDesigOfMaxFromUI = Collections.max(harvestDesignationList);
		int expectHarvDesig = Integer.parseInt(harvDesigOfMaxFromUI) + 1;
		
		if(Integer.parseInt(defaultHarvDesig)!= expectHarvDesig){
			logger.error("The actual default harvest designation '" + defaultHarvDesig
					+ "' is not match the expected default harvest designation '" + expectHarvDesig + "'");
			passed = false;
		}else {
			logger.info("The default harvest designation '" + defaultHarvDesig+ "' is correct");
		}
	}
}
