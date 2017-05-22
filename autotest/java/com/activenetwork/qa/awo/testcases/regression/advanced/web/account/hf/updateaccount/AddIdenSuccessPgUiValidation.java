package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFBigGameDrawTermsAndConditionsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHomePage;
import com.activenetwork.qa.awo.pages.web.hf.HFIdentificationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) 
 * 1. Verify verification confirmation message in identification added page;
 * 2. Verify the result pages after clicking "purchase a license", "apply for the big game draw", 2 "go to home page" links
 * 
 * @Preconditions:
 * d_web_hf_signupaccount, id=180, hfsk_00000@webhftest.com, 2000-01-01
 * 
 * @SPEC: Add Identifier Success Page [TC:055466] 
 * @Task#: Auto_1633
 * 
 * @author Swang
 * @Date  May 2, 2013
 */
public class AddIdenSuccessPgUiValidation extends HFSKWebTestCase {
	private HFIdentificationAddedPage identificationAddedPg = HFIdentificationAddedPage.getInstance();
	private HFResidencyStatusDeclarationPage residencyStatusDeclarationPg = HFResidencyStatusDeclarationPage.getInstance();
	private HFBigGameDrawTermsAndConditionsPage bigGameDrawTermsAndCondtionsPg = HFBigGameDrawTermsAndConditionsPage.getInstance();
	private HFHomePage homePg = HFHomePage.getInstance();
	private String verificationConfirmationMes;

	public void execute() {
		//Add identifier
		hf.signIn(url, cus.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);
		hf.addIdentifier(cus.identifier);

		//Verify verification confirmation message in identification added page
		this.verifyVerificationConfirmationMes(verificationConfirmationMes);

		//After click purchase a license link, go to residency status declaration page
		identificationAddedPg.clickPurchaseALicenceLink();
		residencyStatusDeclarationPg.waitLoading();

		//Sara[20140217] We can purchase lottery in HFSK using the url "http://qax-hfsk01.qa.reserveamerica.com/productCategoriesList.do?prodType=lottery&topTabIndex=Lottery"
//		//After click apply for the big game draw link, go to big game draw terms and conditions page
//		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);
//		hf.addIdentifier(cus.identifier);
//		identificationAddedPg.clickApplyForTheBigGameDrawLink();
//		bigGameDrawTermsAndCondtionsPg.waitLoading();

		//After click go to home page link, go to home page
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		identificationAddedPg.clickGotoHomepage();
		homePg.waitLoading();

		//After click go to home page link from left side, go to home page
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		identificationAddedPg.clickGoToHomePageLnkFromLeftSide();
		homePg.waitLoading();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.email = "hfsk_00000@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = "2000-01-01";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_OTHER_NUM_ID, true, true);
		cus.identifier.identifierNum = "1R9Y4x4121";
		cus.identifier.country = "Albania";

		verificationConfirmationMes = "Identification Added ?Your identification was added to your account successfully\\. You will now be able to use this identification for purchasing licences\\. Please provide either this identification or your assigned HAL ID if you purchase a hunting, angling or trapping licence at a ministry office, licence vendor, or call center in the future\\.";
	}

	public void verifyVerificationConfirmationMes(String expectedRegx){
		String mesFromUI = identificationAddedPg.getVerificationConfirmationMes();
		if(!mesFromUI.matches(expectedRegx)){
			throw new ErrorOnPageException("Verification confirmation message is wrong!", expectedRegx, mesFromUI);
		}
		logger.info("Successfully verify verification confirmation message.\n"+mesFromUI);
	}
}
