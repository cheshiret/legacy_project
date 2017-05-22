package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Create a new customer profile when the site is Identifier Mode
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * @SPEC: Create Profile flow under authenticated by identifier model [TC:067994]
 * @Task#: Auto-1832
 * 
 * @author Lesley Wang
 * @Date  Jul 25, 2013
 */
public class IdentifierMode_CreateAccount extends HFMOWebTestCase {
	private HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}				
		
		// Sign Up a new account by lookup
		hf.invokeURL(url);
		hf.createNewProfile(cus);
		hf.signOut();
		
		// Lookup the new Account by customer identifier and verify customer profile
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updAcctPg.verifyAccountInfo(cus);
		hf.signOut();
		
		// Delete customer identifier
		hf.deleteCustIden(schema, cus.identifier.id, cus.fName, cus.lName);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		String tempStr = new DecimalFormat("000000").format(new Random().nextInt(999999));
		cus.fName = "FN_IM"+tempStr; 
		cus.lName = "LN_IM"+tempStr;
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-20);
		cus.identifier.id = OrmsConstants.IDEN_GREENCARD_NUM_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, true);
		cus.identifier.identifierNum = tempStr;
		cus.identifier.country = "Canada";
		cus.mName = "IM04_" + tempStr;
		cus.custGender = "Male";
		cus.setDefaultSKMailingAddress();
		cus.mailAddrAsPhy = true;
		cus.hPhone = "6789" + tempStr;
		cus.email = hf.getNextEmail();			
	}

}
