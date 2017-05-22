package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.seasons;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddSeasonWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when add season
 * @Preconditions:
 * @SPEC:Add Season
 * @Task#:Auto-507
 * 
 * @author VZhang
 * @Date  Aug 31, 2011
 */
public class AddSeason_VerifyErrorMessage extends LicenseManagerTestCase{

	private String description, printOrder, msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8;
	private boolean passed = true;
	private LicMgrProductConfigurationPage prodConfPage = LicMgrProductConfigurationPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoSeasonProdConfPgFromTopMenu();
		lm.addSeasons(printOrder, "", true);//description is not specified
		this.verifyErrorMessage(msg1);
		
		lm.addSeasons("", printOrder, description, false, true);//harvest designation is not specified
		this.verifyErrorMessage(msg2);
		
		lm.addSeasons("111", printOrder, description, false, true);//harvest designation is greater than 100
		this.verifyErrorMessage(msg3);
		
		lm.addSeasons("0", printOrder, description, false, true);//harvest designation is less than 1
		this.verifyErrorMessage(msg3);
		
		//get exist description info
		description = this.getExistSeasonInfo("Description");
		lm.addSeasons(printOrder, description, true);//description already exists
		this.verifyErrorMessage(msg4);
		
		//get exist harvest designation info
		description = "QA Test" + DateFunctions.getCurrentTime();
		String existHarvDesig = this.getExistSeasonInfo("Harvest Designation");
		lm.addSeasons(existHarvDesig, printOrder, description, false, true);//harvest designation already exists
		this.verifyErrorMessage(msg5);
		
		lm.addSeasons("", description, true);//print order is not specified
		this.verifyErrorMessage(msg6);
		
		lm.addSeasons("0", description, true);//print order is not an integer value greater than 0
		this.verifyErrorMessage(msg7);
		
		//get exist print order
		printOrder = this.getExistSeasonInfo("Print Order");
		lm.addSeasons(printOrder, description, true);//print order already exists
		this.verifyErrorMessage(msg8);
		
		if(!passed){
			throw new ErrorOnPageException("There are some verificaiton points is not correct, please check error log");
		}
		
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi "
				+ "Department of Wildlife, Fisheries, and Parks";
		
		description = "QA Test" + DateFunctions.getCurrentTime();
		printOrder = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		
		msg1 = "The Season Description is required. Please specify the Description.";
		msg2 = "The Harvest Designation is required. Please specify the Harvest Designation.";
		msg3 = "The Harvest Designation entered is not valid. Please enter an integer value greater than 0 and less than 100.";
		msg4 = "The Season Description entered already exists. The Description must be unique.";
		msg5 = "The Harvest Designation entered already exists. The Harvest Designation must be unique.";
		msg6 = "The Print Order is required. Please specify the Print Order.";
		msg7 = "The Print Order entered is not valid. Please enter an integer value greater than 0.";
		msg8 = "The Print Order entered already exists. The Print Order must be unique.";			
	}
	
	/**
	 * verify error message
	 * @param expectMsg
	 */
	private void verifyErrorMessage(String expectMsg){
		LicMgrAddSeasonWidget addSeasonWidget = LicMgrAddSeasonWidget.getInstance();
		
		logger.info("Verify error message.");
		String actualMsg = addSeasonWidget.getErrorMessage();
		
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expected message: '" +expectMsg+"'");
			passed = false;
		}else {
			logger.info("The error message '" + expectMsg + "' is correct");
		}
		
		addSeasonWidget.clickCancel();
		ajax.waitLoading();
		prodConfPage.waitLoading();		
	}
	
	/**
	 * get exist season info
	 * @param colName
	 * @return
	 */
	private String getExistSeasonInfo(String colName){
		List<String> colInfo = prodConfPage.getColumnValue("huntingSeason", colName);
		String existValue = "";
		
		if(colInfo.size()>1){
			existValue = colInfo.get(colInfo.size()-1);
		}else {
			throw new ErrorOnPageException("Please prepare a season info.");
		}
		
		return existValue;
	}

}
