/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.validateaddress;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Input the valid address and click Validate Address button, check the pre-populate address info
 * @Preconditions:
 * @SPEC:
 * pre-populate address form with effective zip code [TC:048843] 
 * pre-populate address form again with effective zip code [TC:048844] 
 * overwrite address form with effective zip code [TC:048845] 
 * overwrite address form again with effective zip code [TC:048846] 
 * @Task#: Auto-1534
 * 
 * @author Lesley Wang
 * @Date  Mar 6, 2013
 */
public class AddressMatchedOneCounty_2 extends HFSKWebTestCase {

	private HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
	private Address validatedAddr1, validatedAddr2;
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		
		logger.info("1-1 Mailing Address: input valid country and zip code and validate address...");
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-2 Mailing Address: reset State to blank and then validate address again...");	
		updateAccPg.selectMailingState("Please select");
		updateAccPg.waitLoading();
		updateAccPg.verifyMailingCountyBlank();
		updateAccPg.validateMailingAddress();
		updateAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-3 Mailing Address: input address info and validate address, verify address info is overwritten...");
		this.setupAddressInfo(cus.mailingAddr);
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-4 Mailing Address: Re-input the address info and validate address again, verify address info...");
		this.reSetupAddressInfo(cus.mailingAddr);
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		
		logger.info("2-1 Home Address: input valid country and zip code and validate address");
		updateAccPg.selectHomeAddDiffFromMailAdd();
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyHomeAddressInfo(validatedAddr2);// Defect-42088
		
		logger.info("2-2 Home Address: reset State to blank and then validate address again...");	
		updateAccPg.selectHomeState("Please select");
		updateAccPg.waitLoading();
		updateAccPg.verifyHomeCountyBlank();
		updateAccPg.validateHomeAddress();
		updateAccPg.verifyHomeAddressInfo(validatedAddr2);

		logger.info("2-3 Home Address: input address info and validate address, verify address info is overwritten...");
		this.setupAddressInfo(cus.physicalAddr);
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyHomeAddressInfo(validatedAddr2);
		
		logger.info("2-4 Home Address: Re-input the address info and validate address again, verify address info...");
		this.reSetupAddressInfo(cus.physicalAddr);
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyHomeAddressInfo(validatedAddr2);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "validateaddress@test.com";
		cus.password = "asdfasdf";
		
		validatedAddr1 = new Address();
		validatedAddr1.country = "United States";
		validatedAddr1.zip = "96701";
		validatedAddr1.city = "Aiea";
		validatedAddr1.state = "Hawaii";
		validatedAddr1.county = "Honolulu";
		
		validatedAddr2 = new Address();
		validatedAddr2.country = "United States";
		validatedAddr2.zip = "03087"; //Sara[20140217]"03087-5600", update after discussing with Lesley because Postal Code must contain exactly 6 numbers and letters combined, and contain
		validatedAddr2.city = "Windham";
		validatedAddr2.state = "New Hampshire";
		validatedAddr2.county = "Rockingham";
		
		cus.mailingAddr.country = validatedAddr1.country;
		cus.mailingAddr.zip = validatedAddr1.zip;
		
		cus.physicalAddr.country = validatedAddr2.country;
		cus.physicalAddr.zip = validatedAddr2.zip;
	}

	private void setupAddressInfo(Address addr) {
		addr.city = "Bussey";
		addr.state = "Iowa";
		addr.county = "Monroe";
	}
	
	private void reSetupAddressInfo(Address addr) {
		addr.city = "Yulee";
		addr.state = "Florida";
		addr.county = "Nassau";
	}

}
