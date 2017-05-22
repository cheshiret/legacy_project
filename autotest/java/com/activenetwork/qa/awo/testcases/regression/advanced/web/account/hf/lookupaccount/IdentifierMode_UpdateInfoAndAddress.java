package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify Update Information and Update Address links on Customer Identity (Your Account Found) page.
 * 1. Update Customer Name info: 
 * 1) <first name> <middle name> <last name>
 * 2) <first name> <last name> <suffix>
 * 3) <first name> <middle name> <last name> <suffix>
 * 2. Update Address: partial home city info
 * 3. Update Customer Home phone: international number
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * customer exists. d_web_hf_signupaccount, id=880
 * @SPEC:
 * Confirm Identity page - Update Information link and Update Address link [TC:068019]
 * Confirm Identity page - Found profile which has email address entered already [TC:067989]
 * @Task#: Auto-1831
 * 
 * @author Lesley Wang
 * @Date  Jul 23, 2013
 * Related Defect: 46001, about home phone with 11 digits
 */
public class IdentifierMode_UpdateInfoAndAddress extends HFMOWebTestCase {
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}	
		
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		
		// update customer name: <first name> <middle name> <last name> <suffix>
		hf.updateInfoFromAcctFoundPg(cus);
		hf.signOut();
		hf.lookupAccount(cus);
		acctFoundPg.verifyAccountInformation(cus, false);
		
		// update customer name: <first name> <last name> <suffix>
		cus.mName = StringUtil.EMPTY;
		hf.updateInfoFromAcctFoundPg(cus);
		hf.signOut();
		hf.lookupAccount(cus);
		acctFoundPg.verifyAccountInformation(cus, false);
		
		// update customer name: <first name> <middle name> <last name> 
		cus.mName = "IdentMode04_MN";
		cus.suffix = "No Suffix";
		hf.updateInfoFromAcctFoundPg(cus);
		hf.signOut();
		hf.lookupAccount(cus);
		acctFoundPg.verifyAccountInformation(cus, false);
		
		// verify customer home address with county info
		acctFoundPg.verifyContactInformation(cus);
		
		// update customer home address and home phone: without county, 11 digits
		cus.mailAddrAsPhy = true;
		cus.physicalAddr = cus.mailingAddr;
		String temp = cus.hPhone;
		cus.hPhone = "1" + cus.hPhone;
		hf.updateAddrFromAcctFoundPg(cus);
		hf.signOut();
		cus.hPhone = temp;
		hf.lookupAccount(cus);
		acctFoundPg.verifyContactInformation(cus);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// customer info
		cus.fName = "IdentMode04_FN"; // d_web_hf_signupaccount, id=880
		cus.lName = "IdentMode04_LN";
		cus.mName = "IdentMode04_MN";
		cus.suffix = "SR";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.custNum = cus.identifier.identifierNum;
		cus.setDefaultSKMailingAddress();
		cus.mailAddrAsPhy = false;
		cus.physicalAddr.address = "2480 Meadowvale";
		cus.physicalAddr.city = "Saint Louis";
		cus.physicalAddr.state = "Missouri";
		cus.physicalAddr.county = "St. Louis city";
		cus.physicalAddr.country = "United States";
		cus.physicalAddr.zip = "63101";
		cus.hPhone = "7896540123";
		cus.email = "hfmo_00022@webhftest.com";
	}

}
