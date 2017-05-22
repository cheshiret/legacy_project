/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateWebSignInPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
//import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFABWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Forget password in Web

 * @author Tony Chen
 * @Date  Aug 07, 2014
 */
public class ForgetPassword extends HFABWebTestCase {
	
//	private LoginInfo login = new LoginInfo();
//	private LicenseManager lm = LicenseManager.getInstance();
//	private HFCreateWebSignInPage createWebPg = HFCreateWebSignInPage.getInstance();
//	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
//	private String maskedNum;
	
	@Override
	public void execute() {
//		// Create an account in LM
//		lm.loginLicenseManager(login);
//		lm.gotoCustomerSearchPageFromCustomersTopMenu();
//		lm.createNewCustomerWithoutSearch(cus);
//		lm.logOutLicenseManager();		
//				
		// Go to HF AB Web to create a web account with the existing email
		hf.invokeURL(url);
		hf.gotoSignInPage();
		hf.gotoForgetYourPasswordPgFromSignInPg();
		hf.forgetYourPassword(cus.email, cus.custNum);
		
		//Next I need to load cust.email & cust.lname from an excel.
		
//		cus.dateOfBirth = DateFunctions.formatDate(cus.dateOfBirth, "yyyy-MM-dd");
//		hf.lookupAccount(cus);
//		hf.gotoCreateWebSignInPgFromAcctFoundPg();
//		createWebPg.verifyEmailAddr(cus.email);
//		hf.createWebAccountAfterLookUp(cus.email, cus.password, cus.password, StringUtil.EMPTY);
//		
//		// Verify the personal info and identifier info on Update Account page
//		hf.gotoUpdateAccountPgFromMyAccountPanel();
//		updatePg.verifyAccountInfo(cus);
//		updatePg.verifyIdentifierInfo(cus.identifier, maskedNum);
//		hf.signOut();
//		
//		// Sign in with the new created account
//		hf.signIn(cus.email, cus.password);	
//		hf.gotoMyAccountPgFromHeaderBar();
//		hf.signOut();
//		
//		// Clean up
//		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email.toLowerCase());
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for LM
//		login.url = AwoUtil.getOrmsURL(env);
//		login.userName = TestProperty.getProperty("orms.migr.user");
//		login.password = TestProperty.getProperty("orms.migr.psw");
//		//login.userName = TestProperty.getProperty("orms.fm.user");
//		//login.password = TestProperty.getProperty("orms.fm.pw");
//		login.contract = "AB Contract";
//		login.location = "imp-admin-role/Alberta HF Agency";
//		//login.location = "AB Admin/SASK";
		
		// Customer info
		cus.email = "HFweb" + DateFunctions.getCurrentTime() + "@test.com";
		cus.custNum = "123456789";
//		cus.password = "asdfasdf";
//		cus.hPhone = "8694528962";
//		cus.setDefaultValuesForHFWebSignUp();
//		cus.setDefaultCanadaAddress(); // set physical address
//		cus.dateOfBirth = DateFunctions.formatDate(cus.dateOfBirth, "yyyyMMdd");
//		cus.identifier.identifierType = 	IDENT_TYPE_NAME_CAF;
//		cus.identifier.identifierTypeID = IDEN_CAF_ID;
//		cus.identifier.identifierNum = new DecimalFormat("000000").format(new Random().nextInt(999999));
//		cus.identifier.state = "Nova Scotia";
//		cus.identifier.stateCode = "NS";
		
//		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
//		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
//		String mask = TestProperty.getProperty("identification.mask.character");
//		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);
	}
}
