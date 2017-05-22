package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-45734 and Defect-51986 have been fixed) In create account page, verify contact related labels, DDL values, error messages
 * @Preconditions: No
 * @SPEC:
 * Customer Profile - Phone Numbers [TC:044148] 
 * Customer Profile - Phone Contact Preference [TC:044149] 
 * Customer Profile - Phone Contact Time [TC:044186] 
 * 
 * @Task#: AUTO-1771
 * @author SWang
 * @Date  Jul 3, 2013
 * Note: the requirement has been changed per Defect-51986: 
 * 1)	Both preference number and preference time are optional with no preference as default value
 * 2)	If preference number is selected, then the selected phone number type has to be specified. Otherwise, error message will be displayed
 */
public class ContactNumsValidation extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String validValue, invalidValue1, invalidValue2, topErrorMes, errorMes3, errorMes4, errorMes5, 
		errorMes6, errorMes7, errorMes8, errorMes9, errorMes10; //invalidValue3, errorMes1, errorMes2, 
	private List<String> contactNumbers, contactTimes;

	public void execute() {
		//Go to create account page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoCreateAccountPgFromAccountLookupPg();

		//Check point 1: Verify all contact numbers labels
		contactLabelsValidation();

		//Check point 2: Verify preferred contact phones and times
		createAccPg.verifyPreferredContactNum(contactNumbers.get(0));
		createAccPg.verifyPreferredContactNums(contactNumbers);
		createAccPg.verifyPreferredContactTime(contactTimes.get(0));
		createAccPg.verifyPreferredContactTimes(contactTimes);

		//Check point 3: Verify error message when home phone, work phone, cell phone and fax have been specified and contains a character other than a number, an embeded space, a bracket, a dash, or a single 'x' (case insensitive)
		// Lesley[20130917] according to the spec "UWP Create Account", Non-digit character, except + in the very beginning, will be ignored.
//		initializeContactNumbers(invalidValue1, contactNumbers.get(1), contactTimes.get(2));
//		hf.createAnAccount(cus);
//		createAccPg.verifyErrorMsgExist(topErrorMes, true);
//		createAccPg.verifyHomePhoneErrorMes(errorMes1, true); //DEFECT-45734
//		createAccPg.verifyWorkPhoneErrorMes(errorMes1, true); //DEFECT-45734
//		createAccPg.verifyCellPhoneErrorMes(errorMes1, true);
//		createAccPg.verifyFaxErrorMes(errorMes2, true);

		//Check point 4: Verify selected preferred contact number and time display
		initializeContactNumbers(validValue, contactNumbers.get(1), contactTimes.get(2));
		hf.setupAccountContactNumbers(cus);
		createAccPg.verifyPreferredContactNum(contactNumbers.get(1));
		createAccPg.verifyPreferredContactTime(contactTimes.get(2));

		//Check point 5: Verify error message when home phone, work phone, cell phone and fax is not between 10 and 11 digits
		initializeContactNumbers(invalidValue2, contactNumbers.get(1), contactTimes.get(2));
		hf.setupAccountContactNumbers(cus);
		createAccPg.verifyErrorMsgExist(topErrorMes, true);
		createAccPg.verifyHomePhoneErrorMes(errorMes3, true); 
		createAccPg.verifyWorkPhoneErrorMes(errorMes4, true);
		createAccPg.verifyCellPhoneErrorMes(errorMes5, true);
		createAccPg.verifyFaxErrorMes(errorMes6, true);

		//Check point 6: Verify error message when "Preferred contact number" has been specified as home phone, not for home, work and cell phone, fax
		initializeContactNumbers(StringUtil.EMPTY, contactNumbers.get(1), contactTimes.get(0));
		hf.setupAccountContactNumbers(cus);
		createAccPg.verifyHomePhoneErrorMes(errorMes7, true); 
		createAccPg.verifyWorkPhoneErrorMes(errorMes4, false);
		createAccPg.verifyCellPhoneErrorMes(errorMes5, false);
		createAccPg.verifyFaxErrorMes(errorMes6, false);

		//Check point 7: Per Defect-51986: Verify NO error message when "Preferred contact number" has been specified, not for and preferred contact time
		initializeContactNumbers(validValue, contactNumbers.get(1), contactTimes.get(0));
		hf.setupAccountContactNumbers(cus);
		createAccPg.verifyPreferredContactNumberErrorMes(errorMes8, false);

		//Check point 8: Per Defect-51986: Verify NO error message when "Preferred contact Time" has been specified, and "Preferred contact number" and home, work and cell phone and fax have not been specified
		initializeContactNumbers(StringUtil.EMPTY, contactNumbers.get(0), contactTimes.get(1));
		hf.setupAccountContactNumbers(cus);
		createAccPg.verifyHomePhoneErrorMes(errorMes10, true); 
		createAccPg.verifyPreferredContactNumberErrorMes(errorMes9, false);

		//Check point 9: Verify error message when "Preferred contact Time", home, work and cell phone and fax have been specified, and "Preferred contact number"has not been specified
		initializeContactNumbers(validValue, contactNumbers.get(0), contactTimes.get(1));
		hf.setupAccountContactNumbers(cus);
//		createAccPg.verifyHomePhoneErrorMes(errorMes7, false); 
		createAccPg.verifyPreferredContactNumberErrorMes(errorMes9, false); 
		
		// Lesley[20130917] according to the spec "UWP Create Account", Non-digit character, except + in the very beginning, will be ignored. 
		initializeContactNumbers(invalidValue1, contactNumbers.get(1), contactTimes.get(2));
		String custNum = hf.createAnAccount(cus);
		if (!custNum.matches("\\d+")) {
			throw new ErrorOnPageException("The customer should be created correctly!");
		}
		hf.signOut();
		hf.deleteCustIdentByCustNum(schema, IDEN_OTHER_NUM_ID, custNum);
	}

	public void wrapParameters(Object[] param) {
		cus.email = "createdAccount" + hf.getNextEmail();
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultSKMailingAddress();
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";

		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_OTHER;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.country = "Albania";

		//Preferred contact phones and times parameters
		contactNumbers = new ArrayList<String>();
		contactNumbers.add("No preference");
		contactNumbers.add("Home Phone");
		contactNumbers.add("Work Phone");
		contactNumbers.add("Cell Phone");
		contactTimes = new ArrayList<String>();
		contactTimes.add("No preference");
		contactTimes.add("Business Hour - Morning");
		contactTimes.add("Business Hour - Afternoon");
		contactTimes.add("Evening");

		validValue = "(813) 723-1178";
		invalidValue1 = validValue+"@A";
		invalidValue2 = validValue+"93";
//		invalidValue3 = validValue+"8";

		//Error messages parameters
		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
//		errorMes1 = "Phone \\(Business, Home, or Mobile\\) must contain only numbers, spaces, brackets, dashes or a single x \\(to denote extension number\\)\\. Please re-enter\\.";
//		errorMes2 = "Fax must contain only numbers, spaces, brackets, dashes or a single x \\(to denote extension number\\)\\. Please re-enter\\.";
		errorMes3 = "Home Phone # is invalid\\. Please enter a Home Phone # between 10 and 11 digits\\.";
		errorMes4 = "Work Phone # is invalid\\. Please enter a Work Phone # between 10 and 11 digits\\.";
		errorMes5 = "Cell Phone # is invalid\\. Please enter a Cell Phone # between 10 and 11 digits\\.";
		errorMes6 = "Fax # is invalid\\. Please enter a Fax # between 10 and 11 digits.";
		errorMes7 = "Home Phone # is required\\.";
		errorMes8 = "Preferred contact Time is required.";
		errorMes9 = "Preferred contact Number is required\\.";
		errorMes10 = "At least one phone number is required\\. Please specify either Home Phone #, Work Phone #, or Cell Phone #\\.";
	}

	private void initializeContactNumbers(String phoneAndFax, String contactNumber, String contactTime){
		cus.hPhone = phoneAndFax;
		cus.bPhone = phoneAndFax;
		cus.mPhone = phoneAndFax;
		cus.fax = phoneAndFax;
		cus.phoneContact = contactNumber;
		cus.contactTime = contactTime;
	}

	private void contactLabelsValidation(){
		boolean passed = MiscFunctions.compareResult("Home phone label", true, createAccPg.isHomePhoneExisted());
		passed &= MiscFunctions.compareResult("Work phone label", true, createAccPg.isWorkPhoneExisted());
		passed &= MiscFunctions.compareResult("Work phone extension label", true, createAccPg.isWorkPhoneExtensionExisted());
		passed &= MiscFunctions.compareResult("Cell phone", true, createAccPg.isCellPhoneExisted());
		passed &= MiscFunctions.compareResult("Fax label", true, createAccPg.isFaxExisted());
		passed &= MiscFunctions.compareResult("Preferred contract number", true, createAccPg.isPreferredContractNumExisted());
		passed &= MiscFunctions.compareResult("Preferred contract time", true, createAccPg.isPreferredContractTimeExisted());
		if(!passed){
			throw new ErrorOnPageException("Failed to verify all contact numbers labels. Please find details from previous logs.");
		}
		logger.info("Successfully verify all contact numbers labels.");
	}
}
