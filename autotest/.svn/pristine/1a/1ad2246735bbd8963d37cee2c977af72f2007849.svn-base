package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Auth. by identifier Mode in MO contract
 * @Preconditions:
 * @SPEC: Create Profile - Prepopulation using information from imported files (Authenticated by Identifier Mode) [TC:068027] 
 * @Task#:Auto-1935
 * 
 * @author SWang
 * @Date  Oct 9, 2013
 */
public class PrepopulationWithImportedFiles extends HFMOWebTestCase {
	private HFCreateAccountPage createAcctPg = HFCreateAccountPage.getInstance();
	private HFUpdateAccountPage updateAccoutPg = HFUpdateAccountPage.getInstance();
	private CustomerIdentifier iden;
	private String defaultValue, lineNum, encryptIndNum1, encryptIndNum2, fullNameGender, mailCounty, regxLoginEmail;
	private String fileName, maskedNum1, maskedNum2;
	private List<CustomerIdentifier> idens;

	public void execute() {
		//Precondition
		hf.deleteCustAllIdentExceptCustNumUsingRegxLoginName(schema, regxLoginEmail);
		clearImportedData();
		insertImportedData();

		//Go to create account page
		//Check imported data prepopulate --with Clear link
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);
		verifyPrepoplatedInfoBasedOnImportedFile(true);

		//Check imported data prepopulate --without Clear link
		hf.gotoCreateAccountPage(url, cus);
		verifyPrepoplatedInfoBasedOnImportedFile(false);
		finishCreatingNewAccount();

		//Check imported data and identifier information in update account page
		hf.signOut();
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		verifyImportedFileDataInUpdateAccoutPg();
		updateAccoutPg.verifyIdentifierInfo(cus.identifier, maskedNum1);
		updateAccoutPg.verifyIdentifierInfo(iden, maskedNum2);
	}

	public void wrapParameters(Object[] param) {
		String tempStr = new DecimalFormat("00000").format(new Random().nextInt(99999));
		defaultValue = "Please select";

		cus.fName = "F_Prepopulation"+tempStr;
		cus.lName = "L_Prepopulation"+tempStr;
		cus.mName = "M_Prepopulation"+tempStr;
		cus.address = "SOUTH HANLEY - "+tempStr;
		cus.city = "Saint Louis";
		cus.stateID = "42";
		cus.zip = "63105";
		cus.custGender = "F";
		cus.dateOfBirth = "01-JAN-"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.heightFt = "65";
		cus.heightIn = "130";
		cus.eyeColorID = "5";
		lineNum = "1";

		cus.email = "PrepopulationWithImportedFiles"+tempStr+"@hf.com";
		regxLoginEmail = "PrepopulationWithImportedFiles%@hf.com";
		cus.state = "Missouri";
		cus.country = "United States";
		cus.county = defaultValue;
		cus.hPhone = "9053867600";
		fullNameGender = "Female";
		mailCounty = "St. Louis";

		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");

		//The first identifier
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum =  "2R9Y4x0000";
		cus.identifier.state = "Missouri";
		cus.identifier.stateCode = "MO";
		encryptIndNum1 = "78fa4f8f50b3c11612848e73186d85e0";
		maskedNum1 = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);

		//The second identifier
		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, false);
		iden.identifierNum = "197382465";
		encryptIndNum2 = "ff6cf2582aeb86232dc6d1e6b15b4c5a";
		maskedNum2 = StringUtil.encryptString(iden.identifierNum, mask, hideNum, showNum);

		idens = new ArrayList<CustomerIdentifier>();
		idens.add(cus.identifier);
		idens.add(iden);

		//External records
		fileName = "PrepopulationWithImportedFiles";
	}

	private void verifyPrepoplatedInfoBasedOnImportedFile(boolean clickClearUnderIdenDIV){
		boolean result = MiscFunctions.compareResult("First Name", cus.fName, createAcctPg.getFirstName());
		result &= MiscFunctions.compareResult("Middle Name", cus.mName, createAcctPg.getMiddleName());
		result &= MiscFunctions.compareResult("Last Name", cus.lName, createAcctPg.getLastName());
		result &= MiscFunctions.compareResult("Date of birth", DateFunctions.formatDate(cus.dateOfBirth, "MM/dd/yyyy"), createAcctPg.getDateOfBirth());
		result &= MiscFunctions.compareResult("Gender", fullNameGender, createAcctPg.getGender());
		result &= MiscFunctions.compareResult("Zip Code", cus.zip, createAcctPg.getZipCode());
		result &= MiscFunctions.compareResult("Mailing Street Address", cus.address, createAcctPg.getMailingStreetAddress());
		result &= MiscFunctions.compareResult("Mailing city", cus.city, createAcctPg.getMailingCity());
		result &= MiscFunctions.compareResult("Mailing state", cus.state, createAcctPg.getMailingState());
		result &= MiscFunctions.compareResult("Mailing County", cus.county, createAcctPg.getMailingCounty());
		result &= MiscFunctions.compareResult("Iden", createAcctPg.generateIdensInfo(idens), createAcctPg.getIdenInfo());

		if(clickClearUnderIdenDIV){
			createAcctPg.clickIdentClearLinkAndWait();
			result &= MiscFunctions.compareResult("Iden deop down after click Clear link", defaultValue, createAcctPg.getIdentType());
		}

		if(!result){
			throw new ErrorOnPageException("Not all prepoplated infomation are same as imported file in create account page. Please find details from previous logs.");
		}
	}

	private void verifyImportedFileDataInUpdateAccoutPg(){
		boolean result = MiscFunctions.compareResult("First Name", cus.fName, updateAccoutPg.getFirstName());
		result &= MiscFunctions.compareResult("Middle Name", cus.mName, updateAccoutPg.getMiddleName());
		result &= MiscFunctions.compareResult("Last Name", cus.lName, updateAccoutPg.getLastName());
		result &= MiscFunctions.compareResult("Date of birth", DateFunctions.formatDate(cus.dateOfBirth, "MM/dd/yyyy"), updateAccoutPg.getDateOfBirth());
		result &= MiscFunctions.compareResult("Gender", fullNameGender, updateAccoutPg.getGender());
		result &= MiscFunctions.compareResult("Zip Code", cus.zip, updateAccoutPg.getZipCode());
		result &= MiscFunctions.compareResult("Mailing Street Address", cus.address, updateAccoutPg.getMailingStreetAddress());
		result &= MiscFunctions.compareResult("Mailing city", cus.city, updateAccoutPg.getMailingCity());
		result &= MiscFunctions.compareResult("Mailing state", cus.state, updateAccoutPg.getMailingState());
		result &= MiscFunctions.compareResult("Mailing County", mailCounty, updateAccoutPg.getMailingCounty());

		if(!result){
			throw new ErrorOnPageException("Not all prepoplated infomation are same as imported file in create account page. Please find details from previous logs.");
		}
	}

	private void finishCreatingNewAccount(){
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();
		createAcctPg.setEmailAddress(cus.email);
		createAcctPg.selectMailingCounty(mailCounty);
		createAcctPg.setHomePhone(cus.hPhone);
		createAcctPg.clickCreateAccount();
		accOverviewPg.waitLoading();
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
