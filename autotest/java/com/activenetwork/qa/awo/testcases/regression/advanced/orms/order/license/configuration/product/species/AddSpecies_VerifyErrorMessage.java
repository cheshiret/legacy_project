package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.species;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSpeciesWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:This case is used to verify error message when add species
 * @Preconditions:
 * @SPEC:Add Species
 * @Task#:Auto-503
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSpecies_VerifyErrorMessage extends LicenseManagerTestCase{

	private LicMgrProductConfigurationPage prodConfPage = LicMgrProductConfigurationPage.getInstance();
	private String seq, msg1, msg2, msg3, msg4, msg5;
	private boolean passed = true;
	private Species species = new Species();
	
	public void execute() {
		lm.loginLicenseManager(login);		
		lm.gotoProdConfPgFromTopMenu();

		// 1. description is not specified.
		species.description = StringUtil.EMPTY;
		lm.addSpecies(species, true, true);
		this.verifyErrorMessage(msg1);

		// 2. the harvest designation is not specified
		species.description = "QA Test" + seq;
		species.speciesId = StringUtil.EMPTY;
		lm.addSpecies(species, false, true);
		this.verifyErrorMessage(msg2);
		
		// 3. the harvest designation is greater than 100
		species.speciesId = "100";
		lm.addSpecies(species, false, true);
		this.verifyErrorMessage(msg3);
		
		// 4. the harvest designation is less than 0
		species.speciesId = "0";
		lm.addSpecies(species, false, true);
		this.verifyErrorMessage(msg3);
		
		// 5. the description already exists
		species.speciesId = "1";
		String existDescription = this.getExistSeasonInfo("Description");
		species.description = existDescription;
		lm.addSpecies(species, true, true);
		this.verifyErrorMessage(msg4);
		
		// 6. the harvest designation already exists
		String exitstHarvDesign = this.getExistSeasonInfo("Harvest Designation");
		species.speciesId = exitstHarvDesign;
		species.description = "QA Test" + seq;
		lm.addSpecies(species, false, true);
		this.verifyErrorMessage(msg5);
		
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
		species.creationUser = login.userName;
		species.creationLocation = login.location.split("/")[1];
		species.code =  String.valueOf(DataBaseFunctions.getEmailSequence()).substring(0, 3);
		species.locationAlias = "Unit";
		
		msg1 = "The Species Description is required. Please specify the Description.";
		msg2 = "The Harvest Designation is required. Please specify the Harvest Designation.";
		msg3 = "The Harvest Designation entered is not valid. Please enter an integer value greater than 0 and less than 100.";
		msg4 = "The Species Description entered already exists. The Description must be unique.";
		msg5 = "The Harvest Designation entered already exists. The Harvest Designation must be unique.";	
	}
	
	private void verifyErrorMessage(String expectMsg){
		LicMgrAddSpeciesWidget addSpeciesWidget = LicMgrAddSpeciesWidget.getInstance();	
		
		logger.info("Verify error message.");
		String actualMsg = addSpeciesWidget.getErrorMessage();
		
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			logger.error("---The actual error message: '" + actualMsg
					+"' is not match the expected message: '" +expectMsg+"'");
			passed = false;
		}else {
			logger.info("The error message '" + expectMsg + "' is correct");
		}
		
		addSpeciesWidget.clickCancel();
		ajax.waitLoading();
		prodConfPage.waitLoading();		
	}
	
	/**
	 * get exist season info
	 * @param colName
	 * @return
	 */
	private String getExistSeasonInfo(String colName){
		List<String> colInfo = prodConfPage.getColumnValue("species", colName);
		String existValue = "";
		
		if(colInfo.size()>1){
			existValue = colInfo.get(colInfo.size()-1);
		}else {
			throw new ErrorOnPageException("Please prepare a season info.");
		}
		
		return existValue;
	}
}
