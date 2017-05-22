/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Sign up a new account and sign in with the new account to view Home page, License Categories and Product List page
 * @Preconditions:
 * @SPEC:
 * Sign Up successfully check [TC:048806] 
 * normal sign in [TC:048830] 
 * Email Address case insensitive storing [TC:048807] 
 * Email Address case insensitive login [TC:048812] 
 * Sign in - account case insensitive [TC:048831] 
 * Sign in - view account [TC:048834] 
 * Sign in - view home page [TC:048835] 
 * Sign in - view privilege List [TC:048836] 
 * Sign in - view privilege Purchase Details [TC:048837] 
 * @Task#: Auto-1482, Auto-1483
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class SignUpAndSignInWithNewAccount extends HFSKWebTestCase {
	private HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();
	private String msg;
	
	@Override
	public void execute() {
		hf.invokeURL(url);
		logger.info("1. Sign up a new account and verify the successfully message...");
		hf.signUpNewAccount(url, cus);
		accOverviewPg.verifyTopMsg(msg);
		hf.signOut();
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email.toLowerCase().trim());
		
		logger.info("2. Login in with correct info and check the email and name in Account Overview page...");
		hf.signIn(cus.email.toUpperCase().trim(), cus.password);
		cus.lName = "L N__ame"; // continuous space should be reduced to 1
		accOverviewPg.verifyAccountEmailAndName(cus.email.trim(), cus.fName.trim(), cus.lName);// Product change after 3.04.01. email won't change to lower case
		
		logger.info("3. Sign in - View home page");
		hf.gotoHFHomePg();
		
		logger.info("4. Sign in - View License Categories List page and License Product List page...");
		hf.gotoLicenseCategoriesListPg(cus.residencyStatus);
		hf.gotoLicensesListPgFromLicenseCatListPg();
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "   HF" + hf.getNextEmail()  + " ";
		cus.password = "a<S#df";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.fName = "  FName  ";
		cus.lName = "  L  N__ame  ";
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Saskatchewan";
		msg = "Your Account has been Created Successfully";
	}
}
