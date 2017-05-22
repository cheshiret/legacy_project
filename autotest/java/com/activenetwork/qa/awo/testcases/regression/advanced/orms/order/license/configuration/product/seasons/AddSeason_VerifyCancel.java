package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.seasons;

import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify cancel action and default harvest designation business rule when add season
 * @Preconditions:
 * @SPEC:Add Hunting Season
 * @Task#:Auto-507
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSeason_VerifyCancel extends LicenseManagerTestCase{

	private String seq, description, printOrder;
	private boolean passed = true;
	private LicMgrProductConfigurationPage prodConfPage = LicMgrProductConfigurationPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoSeasonProdConfPgFromTopMenu();
		String harvestDesignation = lm.addSeasons(printOrder, description, false);
		this.verifyCancelInfo(harvestDesignation);
		this.verifyHarvestDesignation(harvestDesignation);
		
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
		
	}
	
	/**
	 * Verify cancel
	 * @param harvestDesignation
	 * @return
	 */
	private void verifyCancelInfo(String harvestDesignation){
		logger.info("Verify cancel action.");
		
		List<String> harvestDesignationList = prodConfPage.getColumnValue("huntingSeason", "Harvest Designation");
		if(harvestDesignationList.contains(harvestDesignation)){
			logger.error("The function of cancel is failed");
			passed = false;
		}else {
			logger.info("The function of cancel is successful");
		}			
	}
	
	/**
	 * verify error message
	 * @param defaultHarvDesign  "huntingSeason"
	 */
	private void verifyHarvestDesignation(String defaultHarvDesign){
		logger.info("Verify default havest designation.");
		
		List<String> harvDesignList = prodConfPage.getColumnValue("huntingSeason", "Harvest Designation");
		harvDesignList.remove(0);
		String harvDesignOfMaxFromUI = Collections.max(harvDesignList);
		int expectHarvDesign = Integer.parseInt(harvDesignOfMaxFromUI) + 1;
		
		if(Integer.parseInt(defaultHarvDesign)!= expectHarvDesign){
			logger.error("The actual default harvest designation '" + defaultHarvDesign
					+ "' is not match the expected default harvest designation '" + expectHarvDesign + "'");
			passed = false;
		}else {
			logger.info("The default harvest designation '" + defaultHarvDesign+ "' is correct");
		}
	}

}
