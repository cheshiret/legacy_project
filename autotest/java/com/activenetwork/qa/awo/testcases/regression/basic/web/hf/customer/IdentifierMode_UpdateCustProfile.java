package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Update customer profile when the site is Identifier Mode
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 *  Customer exist. d_web_hf_signupaccount, id=890
 * @SPEC:Update Profile flow under authentificated by identifiers flow [TC:068450]
 * @Task#: Auto-1832
 * 
 * @author Lesley Wang
 * @Date  Jul 25, 2013
 */
public class IdentifierMode_UpdateCustProfile extends HFMOWebTestCase {
	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}				
		
		hf.deleteCustIden(schema, IDEN_OTHER_NUM_ID, cus.fName, cus.lName);
		
		// lookup account to account overview page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		
		// Add a new identifier to the profile
		this.resetCustIdentifier();
		hf.addIdentifier(cus.identifier);
		hf.signOut();
		
		// Lookup account by the new identifier to account overview page
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.verifyIdentifierInfo(cus.identifier, getMaskedIdentNum(cus.identifier.identifierNum));
		
		// Update existing identifier
		cus.identifier.identifierNum = "IMUpdAcct2";
		hf.updateIdentifier(cus.identifier.id, cus.identifier.identifierNum, cus.identifier.country, cus.identifier.state);
		
		// Update customer profile
		hf.updateProfile(cus);
		hf.signOut();
		
		// Lookup account by the updated identifier to account overview page
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.verifyIdentifierInfo(cus.identifier, getMaskedIdentNum(cus.identifier.identifierNum));
		updatePg.verifyAccountInfo(cus);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode05_FN"; // d_web_hf_signupaccount, id=890
		cus.lName = "IdentMode05_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		String tempStr = new DecimalFormat("000000000").format(new Random().nextInt(999999999));
		cus.mName = "IM04_" + tempStr;
		cus.custGender = "Female";
		cus.setDefaultSKMailingAddress();
		cus.mailAddrAsPhy = false;
		cus.physicalAddr.address = "2480 Meadowvale " + tempStr;
		cus.physicalAddr.city = "Saint Louis";
		cus.physicalAddr.state = "Missouri";
		cus.physicalAddr.county = "St. Louis city";
		cus.physicalAddr.country = "United States";
		cus.physicalAddr.zip = "63101";
		cus.hPhone = "7" + tempStr;
		cus.email = "hfmo_00023@webhftest.com";		
	}
	
	/** reset customer identifier info to lookup by identifier */
	private void resetCustIdentifier() {
		cus.identifier.id = OrmsConstants.IDEN_OTHER_NUM_ID; // state and country required
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, true);
		cus.identifier.identifierNum = "IMUpdAcct1";
		cus.identifier.country = "Canada";
		cus.identifier.state = "Manitoba";
		cus.identifier.stateCode = "MB";
	}
	
	private String getMaskedIdentNum(String identNum) {
		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		return StringUtil.encryptString(identNum, mask, hideNum, showNum);
	}
 }
