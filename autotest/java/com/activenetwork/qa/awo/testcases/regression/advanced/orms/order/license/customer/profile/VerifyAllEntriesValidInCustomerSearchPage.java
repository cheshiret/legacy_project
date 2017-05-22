package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: Verify the business rule in customer search page
 * @Preconditions: No
 * @SPEC: Search Customer Profile
 * @Task#: Auto-508
 * 
 * @author SWang
 * @Date  May 31, 2011
 * 
 * update per TC 44185 
 */
public class VerifyAllEntriesValidInCustomerSearchPage  extends LicenseManagerTestCase{
	LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
	LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	String[] invaildDates=OrmsConstants.INVALID_DATES;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Verify customer class
		custSearchPg.verifyErrorMessWhenSelectEmptyCustomerClass(str0);

		//Date of birth
		custSearchPg.verifyOnDateOfBirth(invaildDates, null);

		//Zip
		custSearchPg.setDateOfBirth("");
//currently blocked by not fix DEFECT-54901
////		custSearchPg.setZip("1234");// less than 5 numbers and letters combined
//		custSearchPg.setZip("$"); // input a character other numbers, letters, a single dash, or a single space
//		this.verifyErrorMessage(str1);
//		custSearchPg.setZip("@12345-6789");//Contain the character other than number, letter, space and dash
//		this.verifyErrorMessage(str1);
////		custSearchPg.setZip("1 234- ");//Contain two spaces
//		custSearchPg.setZip("1 23 4");//Contain two spaces
//		this.verifyErrorMessage(str1);
//		custSearchPg.setZip("1-234-5");//Contain two dashes
//		this.verifyErrorMessage(str1);
		custSearchPg.setZip("1"); // input a number, should return results
		this.verifyNoErrorMeg();

		//Verify phone number
		custSearchPg.setZip("");
		custSearchPg.setPhoneNumber("1234");
		this.verifyErrorMessage(str2);

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";;
	}

	private void verifyErrorMessage(String expectMsg) {
		custSearchPg.clickSearch();
		ajax.waitLoading();
		String actualMsg = custSearchPg.getWarnMes();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}
	
	private void verifyNoErrorMeg() {
		custSearchPg.clickSearch();
		ajax.waitLoading();
		if (custSearchPg.checkWarMesExist()) {
			throw new ErrorOnPageException("The warning messsage field should NOT be displayed!");
		}
	}

	//Warning message
	private String str0 = "";
//	private String str1 = "ZIP/Postal Code must contain at least 5 numbers and letters combined, " +
//			"and must only contain numbers, letters, a single dash, or a single space. Please change your entries.";
	private String str1 = "ZIP/Postal Code must contain at least 1 number or letter, " +
			"and must only contain numbers, letters, a single dash, or a single space. " +
			"Please change your entries."; // update per Case TC 44185 
	private String str2 = "Phone Number must contain at least 5 numbers. Please re-enter.";
	
}
