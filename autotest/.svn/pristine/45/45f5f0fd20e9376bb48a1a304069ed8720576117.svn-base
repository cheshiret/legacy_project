package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: Lookup account to created account page using the valid identifier
 * In create account page, fill out other mandatory field, click create account button, verify error message;
 * @Preconditions: 
 * 1. Case will insert data into tables (c_imported_cust_identifier, c_imported_customer, X_EXTERNAL_DATA_FILE);
 * 2. Two identifiers related, one is missing identifier state 
 * US Drivers License: 2R9Y4x0002, missing state
 * Social Security Number: 197382466
 * 
 * @SPEC:Create Profile - Idenfication from imported file - Validation error message (Authenticated by Identifier Mode) [TC:068031] 
 * @Task#:Auto-1935
 * 
 * @author SWang
 * @Date  Oct 10, 2013
 */
public class ErrorMesWithImportedFile_MissingState extends HFMOWebTestCase {
	private HFCreateAccountPage createAcctPg = HFCreateAccountPage.getInstance();
	private CustomerIdentifier iden;
	private String defaultValue, lineNum, encryptIndNum1, encryptIndNum2, mailCounty, regxLoginEmail, errorMes;
	private String fileName;
	private List<CustomerIdentifier> cusIdenList;

	public void execute() {
		//Precondition
		hf.deleteCustAllIdentExceptCustNumUsingRegxLoginName(schema, regxLoginEmail);
		clearImportedData();
		insertImportedData();

		//Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);

		//Error message validation
		tryToFinishCreatingNewAccount();
		createAcctPg.verifyErrorMsgExist(errorMes, true);

		//Verify missing state
		createAcctPg.verifyIdentifierInfo(cusIdenList);
	}

	public void wrapParameters(Object[] param) {
		String tempStr = new DecimalFormat("00000").format(new Random().nextInt(99999));
		defaultValue = "Please select";

		cus.fName = "F_Imported_Missing_"+tempStr;
		cus.lName = "L_Imported_Missing_"+tempStr;
		cus.mName = "M_Imported_Missing_"+tempStr;
		cus.address = "SOUTH HANLEY - "+tempStr;
		cus.city = "Saint Louis";
		cus.stateID = "42";
		cus.zip = "63105";
		cus.custGender = "F";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.heightFt = "65";
		cus.heightIn = "130";
		cus.eyeColorID = "5";
		lineNum = "3";

		cus.email = "ErrorMesWithImported_MissingState"+tempStr+"@hf.com";
		regxLoginEmail = "ErrorMesWithImported_MissingState%@hf.com";
		cus.state = "Missouri";
		cus.country = "United States";
		cus.county = defaultValue;
		cus.hPhone = "9053867600";
		mailCounty = "St. Louis";

		//The first identifier
		cus.identifier.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = "197382466";
		encryptIndNum1 = "33bfc80b772ad49589314f7a9c6f06f6";

		//The second identifier
		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, true, false);
		iden.identifierNum =  "2R9Y4x0002";
		iden.state = StringUtil.EMPTY;
		encryptIndNum2 = "fed9b75aca167435d78942426d8248a2";

		cusIdenList = new ArrayList<CustomerIdentifier>();
		cusIdenList.add(cus.identifier);
		cusIdenList.add(iden);

		//External records
		fileName = "PrepopulationWithImportedFiles";

		//Error messages
		errorMes = "Identification Information received from the State is not sufficient/not correct\\. Please Clear the information and enter manually";
	}

	private void tryToFinishCreatingNewAccount(){
		createAcctPg.setEmailAddress(cus.email);
		createAcctPg.selectMailingCounty(mailCounty);
		createAcctPg.setHomePhone(cus.hPhone);
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
		hf.insertCustIdenRecords(schema, importedCustID, cus.identifier.id, encryptIndNum1, "", "");
		hf.insertCustIdenRecords(schema, importedCustID, iden.id, encryptIndNum2, "", "");
	}
}

