package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (P) Verify product related, education related, submit button, cancel and navigation link in "Education information required" page
 * @Preconditions:
 * d_web_hf_signupaccount, id=690, hfmo_00015@webhftest.com, Edu1_FN, Edu1_LN, Green Card|1R9Y4x4145|Canada| |
 * 
 * @SPEC:
 * Education Information page (for Licence) - UI [TC:047153] 
 * Education number field - Max. input length of 100 for free text input fields [TC:049351] 
 * 
 * @Task#: AUTO-1761
 * @author SWang
 * @Date  Jun 15, 2013
 */
public class EducationInfoPgUI extends HFMOWebTestCase {
	private String pageTitle, eduSectionTitle, eduSectionSubTitle, eduNumLabel, countryLabel, stateLabel, OneHundredharString, OneHundredAndOneCharString;
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();

	public void execute() {
		//Lookup account, make privilege to education information required page
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);

		//Check education information required page UI
		this.verifyEducationInfomationRequiredPgUI();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00015@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "Edu1_FN";
		cus.lName = "Edu1_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO License002";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";

		//UI parameters
		pageTitle = "Education Information Required ?Please provide your education information below\\.";
		eduSectionTitle = "Your Hunter Ed Required";
		eduSectionSubTitle = "Please provide the following information to verify your Hunter Ed";
		eduNumLabel = "Education Number:*";
		countryLabel = "Country:*";
		stateLabel = "State:*";
		OneHundredharString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789";
		OneHundredAndOneCharString = OneHundredharString + "T";
	}

	private void verifyEducationInfomationRequiredPgUI(){
		boolean passed = MiscFunctions.matchString("Education infomation required page title", eduInfoRequiredPg.getPageTitle(), pageTitle);
		passed &= MiscFunctions.compareResult("Product name", privilege.name.toUpperCase(), eduInfoRequiredPg.getProductName());
		passed &= MiscFunctions.compareResult("License Year", privilege.licenseYear, eduInfoRequiredPg.getLicenseYear());
		passed &= MiscFunctions.compareResult("Quantity", privilege.qty, eduInfoRequiredPg.getQuantity());
		passed &= MiscFunctions.compareResult("Education section title", eduSectionTitle, eduInfoRequiredPg.getEduSectionTitle());
		passed &= MiscFunctions.compareResult("Education section sub title", eduSectionSubTitle, eduInfoRequiredPg.getEduSectionSubTitle());
		passed &= MiscFunctions.compareResult("Education number label", eduNumLabel, eduInfoRequiredPg.getEduNumLabel());
		passed &= MiscFunctions.compareResult("Education Number text field", true, eduInfoRequiredPg.isEduNumTextFieldDisplayed());
		passed &= MiscFunctions.compareResult("Country label", countryLabel, eduInfoRequiredPg.getCountryLabel());
		passed &= MiscFunctions.compareResult("Country DDL", true, eduInfoRequiredPg.isCountryDDLDisplayed());
		passed &= MiscFunctions.compareResult("State label", stateLabel, eduInfoRequiredPg.getStateLabel());
		passed &= MiscFunctions.compareResult("State DDL", true, eduInfoRequiredPg.isStateDDLExists());
		passed &= MiscFunctions.compareResult("Submit button", true, eduInfoRequiredPg.isSubmitButtonDisplayed());
		passed &= MiscFunctions.compareResult("Cancel link", true, eduInfoRequiredPg.isCancelLinkDisplayed());
		passed &= MiscFunctions.compareResult("Return to product details link", true, eduInfoRequiredPg.isReturnToProductDetailsLinkDisplayed());

		eduInfoRequiredPg.setEduNum(OneHundredAndOneCharString);
		passed &= MiscFunctions.compareResult("Maximum length rule for Edu Num text field", OneHundredharString, eduInfoRequiredPg.getEduNum());

		if(!passed){
			throw new ErrorOnPageException("Failed to check all points in education information required page. Please check detauls from previous logs.");
		}
		logger.info("Successfully verify all check points in education infomation required page.");
	}
}
