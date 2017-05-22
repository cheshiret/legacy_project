package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) Lookup account to created account page using the valid identifier
 * 1. In create account page, fill out other mandatory field, click create account button, verify error message 1;
 * 2. Fill out invalid identifier info, click create account button, verify error message 2.
 * @Preconditions: 
 * 1. Case will insert data into tables (c_imported_cust_identifier, c_imported_customer, X_EXTERNAL_DATA_FILE);
 * 2. Two identifiers related, one has invalid identifier number 
 * US Drivers License: 2R9Y4x0001
 * Social Security Number: ab124#*1
 * 
 * @SPEC:Create Profile - Idenfication from imported file - Validation error message (Authenticated by Identifier Mode) [TC:068031] 
 * @Task#:Auto-1935
 * 
 * @author SWang
 * @Date  Oct 10, 2013
 */
public class ErrorMesWithImportedFile_InvalidIdenNum extends HFMOWebTestCase {
	private HFCreateAccountPage createAcctPg = HFCreateAccountPage.getInstance();
	private CustomerIdentifier iden;
	private String defaultValue, lineNum, encryptIndNum1, encryptIndNum2, mailCounty, regxLoginEmail, errorMes1, errorMes2;
	private String fileName;

	public void execute() {
		//Precondition
		hf.deleteCustAllIdentExceptCustNumUsingRegxLoginName(schema, regxLoginEmail);
		clearImportedData();
		insertImportedData();

		//Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);

		//Error message 1 validation
		tryToFinishCreatingNewAccount();
		createAcctPg.verifyErrorMsgExist(errorMes1, true);
		verifyResultAfterClickClear();

		//Error message 2 validation
		tryToFinishCreateingNewAccountWithIden();
		createAcctPg.verifyErrorMsgExist(errorMes2, true);
	}

	public void wrapParameters(Object[] param) {
		String tempStr = new DecimalFormat("00000").format(new Random().nextInt(99999));
		defaultValue = "Please select";

		cus.fName = "F_Imported_Invalid_"+tempStr;
		cus.lName = "L_Imported_Invalid_"+tempStr;
		cus.mName = "M_Imported_Invalid_"+tempStr;
		cus.address = "SOUTH HANLEY - "+tempStr;
		cus.city = "Saint Louis";
		cus.stateID = "42";
		cus.zip = "63105";
		cus.custGender = "F";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.heightFt = "65";
		cus.heightIn = "130";
		cus.eyeColorID = "5";
		lineNum = "2";

		cus.email = "ErrorMesWithImported_InvalidIdenNum"+tempStr+"@hf.com";
		regxLoginEmail = "ErrorMesWithImported_InvalidIdenNum%@hf.com";
		cus.state = "Missouri";
		cus.country = "United States";
		cus.county = defaultValue;
		cus.hPhone = "9053867600";
		mailCounty = "St. Louis";

		//The first identifier
		cus.identifier.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum =  "2R9Y4x0001";
		cus.identifier.state = "Missouri";
		cus.identifier.stateCode = "MO";
		encryptIndNum1 = "95b29558102a7496feab944bb9687ce8";

		//The second identifier
		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, false);
		iden.identifierNum = "ab124#*1";
		encryptIndNum2 = "7e236b76813fd6fb5c1199657f248a78";

		//External records
		fileName = "PrepopulationWithImportedFiles";

		//Error messages
		errorMes1 = "Identification Information received from the State is not sufficient/not correct\\. Please Clear the information and enter manually";
		errorMes2 = iden.identifierType+" must contain exactly 9 digits\\.";
	}

	private void verifyResultAfterClickClear(){
		createAcctPg.clickIdentClearLinkAndWait();
		boolean result = MiscFunctions.compareResult("Iden deop down after click Clear link", defaultValue, createAcctPg.getIdentType());
		result &= MiscFunctions.compareResult("Error message 1", false, createAcctPg.isErrorMsgExist(errorMes1));
		if(!result){
			throw new ErrorOnPageException("Not all check points are passed in created account page after click Clear link.");
		}
	}

	private void tryToFinishCreatingNewAccount(){
		createAcctPg.setEmailAddress(cus.email);
		createAcctPg.selectMailingCounty(mailCounty);
		createAcctPg.setHomePhone(cus.hPhone);
		createAcctPg.clickCreateAccount();
		createAcctPg.waitLoading();
	}

	private void tryToFinishCreateingNewAccountWithIden(){
		createAcctPg.setupIden(iden);
		createAcctPg.clickCreateAccount();
		createAcctPg.waitLoading();
	}

	private void clearImportedData(){
		List<String> importedCustIds = hf.getImportCustIDs(schema, encryptIndNum1, encryptIndNum2);
		hf.deleteImportedCustIdenRecords(schema, encryptIndNum1, encryptIndNum2);
		if(importedCustIds.size()>0){
			hf.deleteImportedCustRecords(schema, importedCustIds);
		}
	}

	private void insertImportedData(){
		String externalFileDataID = hf.getExternalDataFileID(schema, fileName);
		String importedCustID = hf.insertImportedCustRecords(schema, cus, externalFileDataID, lineNum);
		hf.insertCustIdenRecords(schema, importedCustID, cus.identifier.id, encryptIndNum1, "42", "");
		hf.insertCustIdenRecords(schema, importedCustID, iden.id, encryptIndNum2, "", "");
	}
}
