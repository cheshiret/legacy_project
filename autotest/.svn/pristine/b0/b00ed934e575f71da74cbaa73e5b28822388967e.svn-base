/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.validateaddress;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

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
 * @Date  Mar 6, 2013
 */
public class AddressMatchedMoreCounties_2 extends
		HFSKWebTestCase {
	
	private HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
	private Address validatedAddr1;
	private List<String> counties, changedCounties = new ArrayList<String>();
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		
		logger.info("1-1 Mailing Address: input valid country and zip code and validate address...");
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(validatedAddr1); 
		counties = updateAccPg.getMailingCountyOptions();
		
		logger.info("1-2 Mailing Address: input valid address fully (country, zip code, city, state, county), then verify address info after validate address...");
		this.setupMatchedAddressInfo(cus.mailingAddr);
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(cus.mailingAddr);
		
		logger.info("1-3 Mailing Address: input the address not matched zip code, and verify address info after valid addree...");	
		this.setupNotMatchedAddressInfo(cus.mailingAddr);
		updateAccPg.validateMailingAddress(cus.mailingAddr);
		updateAccPg.verifyMailingAddressInfo(validatedAddr1); 
		
		logger.info("1-4 Mailing Address: reset State and check the county dropdown options...");
		updateAccPg.selectMailingState("New York");
		updateAccPg.waitLoading();
		changedCounties = updateAccPg.getMailingCountyOptions();
		this.verifyCountyListDifferent(counties, changedCounties);
		
		logger.info("2-1 Home Address: input valid country and zip code and validate address...");
		updateAccPg.selectHomeAddDiffFromMailAdd();
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyHomeAddressInfo(validatedAddr1);// Defect-42088
		counties = updateAccPg.getHomeCountyOptions();

		logger.info("2-2 Home Address: input valid address fully (country, zip code, city, state, county), then verify address info after validate address...");
		this.setupMatchedAddressInfo(cus.mailingAddr);
		updateAccPg.validateHomeAddress(cus.mailingAddr);
		updateAccPg.verifyHomeAddressInfo(cus.mailingAddr);
		
		logger.info("2-3 Home Address: input the address not matched zip code, and verify address info after valid addree...");	
		this.setupNotMatchedAddressInfo(cus.physicalAddr);
		updateAccPg.validateHomeAddress(cus.physicalAddr);
		updateAccPg.verifyHomeAddressInfo(validatedAddr1); 
		
		logger.info("2-4 Home Address: reset State and check the county dropdown options...");
		updateAccPg.selectHomeState("New York");
		updateAccPg.waitLoading();
		changedCounties = updateAccPg.getHomeCountyOptions();
		this.verifyCountyListDifferent(counties, changedCounties);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "validateaddress@test.com";
		cus.password = "asdfasdf";
		
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
