/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.validateaddress;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

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
 * @Date  Mar 5, 2013
 */
public class AddressMatchedOneCounty_1 extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private Address validatedAddr1, validatedAddr2;
	@Override
	public void execute() {
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);
		
		logger.info("1-1 Mailing Address: input valid country and zip code and validate address...");
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-2 Mailing Address: reset State to blank and then validate address again...");	
		createAccPg.selectMailingState("Please select");
		createAccPg.waitLoading();
		createAccPg.verifyMailingCountyBlank();
		createAccPg.validateMailingAddress();
		createAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-3 Mailing Address: input address info and validate address, verify address info is overwritten...");
		this.setupAddressInfo(cus.mailingAddr);
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		logger.info("1-4 Mailing Address: Re-input the address info and validate address again, verify address info...");
		this.reSetupAddressInfo(cus.mailingAddr);
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(validatedAddr1);
		
		
		logger.info("2-1 Home Address: input valid country and zip code and validate address");
		createAccPg.selectHomeAddDiffFromMailAdd();
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(validatedAddr2);// Defect-42088
		
		logger.info("2-2 Home Address: reset State to blank and then validate address again...");	
		createAccPg.selectHomeState("Please select");
		createAccPg.waitLoading();
		createAccPg.verifyHomeCountyBlank();
		createAccPg.validateHomeAddress();
		createAccPg.verifyHomeAddressInfo(validatedAddr2);

		logger.info("2-3 Home Address: input address info and validate address, verify address info is overwritten...");
		this.setupAddressInfo(cus.physicalAddr);
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(validatedAddr2);
		
		logger.info("2-4 Home Address: Re-input the address info and validate address again, verify address info...");
		this.reSetupAddressInfo(cus.physicalAddr);
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(validatedAddr2);
	}

	@Override
	public void wrapParameters(Object[] param) {
		validatedAddr1 = new Address();
		validatedAddr1.country = "United States";
		validatedAddr1.zip = "96701";
		validatedAddr1.city = "Aiea";
		validatedAddr1.state = "Hawaii";
		validatedAddr1.county = "Honolulu";
		
		validatedAddr2 = new Address();
		validatedAddr2.country = "United States";
		validatedAddr2.zip = "03087";//-5600
		validatedAddr2.city = "Windham";
		validatedAddr2.state = "New Hampshire";
		validatedAddr2.county = "Rockingham";
		
		cus.mailingAddr.country = validatedAddr1.country;
		cus.mailingAddr.zip = validatedAddr1.zip;
		
		cus.physicalAddr.country = validatedAddr2.country;
		cus.physicalAddr.zip = validatedAddr2.zip;
		
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Alberta";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
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
