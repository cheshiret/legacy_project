/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.validateaddress;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check error messages when input invalid address to validate on Create Account page.
 * @Preconditions:
 * @SPEC: 
 * error message: Country is required [TC:048851] 
 * error message: Zip code is required. [TC:048852] 
 * zip code isn't effective and no other address info [TC:048853] 
 * zip code isn't effective [TC:048854] 
 * zip code isn't real [TC:048855] 
 * state isn't United States [TC:048856] 
 * @Task#: Auto-1534
 * 
 * @author Lesley Wang
 * @Date  Mar 6, 2013
 */
public class InvalidAddress_2 extends HFSKWebTestCase {

	private HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
	private String msg1, msg2, msg3, msg4, msg5;
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		
		logger.info("1-1 Mailing Address: Verify Validate Address buton is not shown when Country is not United States...");
		updateAccPg.selectMailingCountry("Canada");
		updateAccPg.verifyValidateMailingAddressExist(true);//false, Sara[10/28/2013], new feature for 3.05 release
		
		logger.info("1-2 Mailing Address: Verify Error Message when no zip is inputted...");
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMsgExist(msg1, true);		
		
		logger.info("1-3 Mailing Address: Verify Error message when no country is selected...");
		cus.mailingAddr.country = "Please select";
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMsgExist(msg2, true);		
		
		logger.info("1-4 Mailing Address: Verify error message when zip code is not real...");
		cus.mailingAddr.country = "United States";
		cus.mailingAddr.zip = "99999";
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMsgExist(msg3, true);		
		
		logger.info("1-5 Mailing Address: Verify error message when zip code is invalid...");
		cus.mailingAddr.zip = "0124";
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMsgExist(msg4, true);	
		
		cus.mailingAddr.zip = "a59#16";
		this.setAddressInfo(cus.mailingAddr);
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMsgExist(msg4, true);	
		
		logger.info("2-1 Home Address: Verify Validate Address buton is not shown when Country is not United States...");
		updateAccPg.selectHomeAddDiffFromMailAdd();
		updateAccPg.selectHomeCountry("Canada");
		updateAccPg.verifyValidateHomeAddressExist(true);//false, Sara[10/28/2013], new feature for 3.05 release
		
		logger.info("2-2 Home Address: Verify Error Message when no zip is inputted...");
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyMsgExist(msg1, true); // Defect-42088
		
		logger.info("2-3 Home Address: Verify Error message when no country is selected...");
		cus.physicalAddr.country = "Please select";
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyMsgExist(msg2, true);
		
		logger.info("2-4 Home Address: Verify error message when zip code is not real...");
		cus.physicalAddr.country = "United States";
		cus.physicalAddr.zip = "999999999";
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyMsgExist(msg3, true);		
		
		logger.info("2-5 Mailing Address: Verify error message when zip code is invalid...");
		cus.physicalAddr.zip = "1234567890";
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyMsgExist(msg5, true);		

		cus.physicalAddr.zip = "56-75abc90";
		this.setAddressInfo(cus.physicalAddr);
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyMsgExist(msg5, true);	
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "validateaddress@test.com";
		cus.password = "asdfasdf";
		
		cus.mailingAddr.country = "United States";
		cus.physicalAddr.country = "United States";
		
		msg1 = "Zip code is required.";
		msg2 = "Country is required.";
		msg3 = "We cannot find this postal/zip code. If the postal/zip code is correct, contact the call center.";
		String commonMsg = "Zip Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space. Please change your entries for Address Type ";
		msg4 = commonMsg + "Mailing Address.";
		msg5 = commonMsg + "Physical Address.";
	}

	private void setAddressInfo(Address addr) {
		addr.city = "Yazoo";
		addr.address = "2480 Meadowvale";
		addr.state = "Mississippi";
		addr.county = "Adams";
	}

}
