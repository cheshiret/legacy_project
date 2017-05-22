package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify first name, middle name, last name and suffix related values and error message
 * @Preconditions:
 * @SPEC: Customer Profile - Customer Name [TC:044147] 
 * @Task#: Auto-1170
 * 
 * @author Swang
 * @Date  Jul 10, 2013
 */
public class CustNameValidation extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String firstName, middleName, lastName, topErrorMes, errorMes1, errorMes2, errorMes3;
	private List<String> suffixes;

	public void execute() {
		//Go to create account page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoCreateAccountPgFromAccountLookupPg();

		initializeCustNames(StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, suffixes.get(3));
		hf.createAnAccount(cus);

		//Check point 1: Check "Suffix" DDL options and selected option
		createAccPg.verifySuffix(suffixes.get(3));
		createAccPg.verifySuffixes(suffixes);

		//Check point 2: Verify group error messages when both first and last name are blank
		createAccPg.verifyErrorMsgExist(topErrorMes, true);
		createAccPg.verifyCustNamesErrorMes(errorMes3, true);
		verifyCusNames(cus);

		//Check point3: Verify error message when only first name is blank
		initializeCustNames(StringUtil.EMPTY, middleName, lastName, suffixes.get(3));
		setupCustNames(cus);
		createAccPg.verifyCustNamesErrorMes(errorMes1, true);
		verifyCusNames(cus);

		//Check point 4: Verify error message when only last name is blank
		initializeCustNames(firstName, middleName, StringUtil.EMPTY, suffixes.get(3));
		setupCustNames(cus);
		createAccPg.verifyCustNamesErrorMes(errorMes2, true);
		verifyCusNames(cus);
	}

	public void wrapParameters(Object[] param) {
		cus.email = "createdAccount" + hf.getNextEmail();
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";

		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_OTHER;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.country = "Albania";

		suffixes = new ArrayList<String>();
		suffixes.add("Please select");
		suffixes.add("No Suffix");
		suffixes.add("JR");
		suffixes.add("SR");
		suffixes.add("I");
		suffixes.add("II");
		suffixes.add("III");
		suffixes.add("IV");
		suffixes.add("V");
		suffixes.add("VI");
		suffixes.add("VII");

		firstName = "FirstName";
		middleName = "MiddleName";
		lastName = "LastName";

		//Error messages parameters
		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = "First Name is required\\.";
		errorMes2 = "Last Name is required\\.";
		errorMes3 = errorMes1 +" ?"+ errorMes2;
	}

	//Initialize customer name parameters
	private void initializeCustNames(String fName, String mName, String lName, String suffix){
		cus.fName = fName;
		cus.mName = mName;
		cus.lName = lName;
		cus.suffix = suffix;
	}

	//Verify customer customer names, first name, middle name and last name
	private void verifyCusNames(Customer cus){
		boolean result = MiscFunctions.compareResult("First Name", cus.fName, createAccPg.getFirstName());
		result &= MiscFunctions.compareResult("Middle Name", cus.mName, createAccPg.getMiddleName());
		result &= MiscFunctions.compareResult("Last Name", cus.lName, createAccPg.getLastName());
		if(!result){
			throw new ErrorOnPageException("Failed to verify all customer names. Please find details from previous logs.");
		}
		logger.info("Successfully verify all customer names.");
	}

	//Setup customer names, first name, middle name and last name
	private void setupCustNames(Customer cus){
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();

		logger.info("Setup customer names info during create an account from 'Create an Account' page.");
		createAccPg.setupCustNames(cus);
		createAccPg.clickCreateAccount();
		browser.waitExists(accOverviewPg, createAccPg);
	}
}

