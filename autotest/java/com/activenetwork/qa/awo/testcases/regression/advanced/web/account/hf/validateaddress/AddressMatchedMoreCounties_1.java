/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.validateaddress;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Input the valid address which will match more counties and click Validate Address button, check the pre-populate address info
 * @Preconditions:
 * @SPEC:
 * remain county with effective zip code [TC:048847] 
 * more than one counties matching zip code and input address info match zip code [TC:048848] 
 * more than one counties matching zip code and input address info don't match zip code [TC:048849] 
 * County dropdown are redrawn based on the State selection [TC:048850] 
 * @Task#: Auto-1534
 * 
 * @author Lesley Wang
 * @Date  Mar 5, 2013
 */
public class AddressMatchedMoreCounties_1 extends
		HFSKWebTestCase {

	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private Address validatedAddr1;
	private List<String> counties, changedCounties = new ArrayList<String>();
	
	@Override
	public void execute() {
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);
		
		logger.info("1-1 Mailing Address: input valid country and zip code, then verify address info after validate address...");
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(validatedAddr1);
		counties = createAccPg.getMailingCountyOptions();
		
		logger.info("1-2 Mailing Address: input valid address fully (country, zip code, city, state, county), then verify address info after validate address...");
		this.setupMatchedAddressInfo(cus.mailingAddr);
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(cus.mailingAddr);
		
		logger.info("1-3 Mailing Address: input the address not matched zip code, and verify address info after valid addree...");	
		this.setupNotMatchedAddressInfo(cus.mailingAddr);
		createAccPg.validateMailingAddress(cus.mailingAddr);
		createAccPg.verifyMailingAddressInfo(validatedAddr1); 
		
		logger.info("1-4 Mailing Address: reset State and check the county dropdown options...");
		createAccPg.selectMailingState("New York");
		createAccPg.waitLoading();
		changedCounties = createAccPg.getMailingCountyOptions();
		this.verifyCountyListDifferent(counties, changedCounties);
	
		logger.info("2-1 Home Address: input valid country and zip code and validate address...");
		createAccPg.selectHomeAddDiffFromMailAdd();
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(validatedAddr1);// Defect-42088
		counties = createAccPg.getHomeCountyOptions();
		
		logger.info("2-2 Home Address: input valid address fully (country, zip code, city, state, county), then verify address info after validate address...");
		this.setupMatchedAddressInfo(cus.physicalAddr);
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(cus.physicalAddr);
		
		logger.info("2-3 Home Address: input the address not matched zip code, and verify address info after valid addree...");	
		this.setupNotMatchedAddressInfo(cus.physicalAddr);
		createAccPg.validateHomeAddress(cus.physicalAddr);
		createAccPg.verifyHomeAddressInfo(validatedAddr1); 
		
		logger.info("2-4 Home Address: reset State and check the county dropdown options...");
		createAccPg.selectHomeState("New York");
		createAccPg.waitLoading();
		changedCounties = createAccPg.getHomeCountyOptions();
		this.verifyCountyListDifferent(counties, changedCounties);
	}

	@Override
	public void wrapParameters(Object[] param) {
		validatedAddr1 = new Address();
		validatedAddr1.country = "United States";
		validatedAddr1.zip = "89444";
		validatedAddr1.city = "Wellington";
		validatedAddr1.state = "Nevada";
		validatedAddr1.county = "Please select";
		
		cus.mailingAddr.country = validatedAddr1.country;
		cus.mailingAddr.zip = validatedAddr1.zip;
		
		cus.physicalAddr.country = validatedAddr1.country;
		cus.physicalAddr.zip = validatedAddr1.zip;
		
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Alberta";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
	}

	private void setupMatchedAddressInfo(Address addr) {
		addr.city = "Wellington";
		addr.state = "Nevada";
		addr.county = "Lyon";
	}
	
	private void setupNotMatchedAddressInfo(Address addr) {
		addr.city = "Yulee";
		addr.state = "Florida";
		addr.county = "Nassau";
	}

	private void verifyCountyListDifferent(List<String> orgList, List<String> changedList) {
		if (orgList.equals(changedList)) {
			throw new ErrorOnPageException("The two country lists are the same!");
		}
		logger.info("The two country lists are different after change.");
	}
}
