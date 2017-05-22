package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (p) Verify identifier state an country exist or not.If exist, check drop down list values
 * @Preconditions: No testing data needed.
 * @SPEC: TC068023: Create Profile - Identification section 
 * @Task#:Auto-1870
 * 
 * @author Swang
 * @Date  Sep 12, 2013
 */
public class IdentificationSection extends HFMOWebTestCase {
	private HFCreateAccountPage createAccountPg = HFCreateAccountPage.getInstance();
	private CustomerIdentifier socialSecNumIdent, usDirversLicIdent, passportIdent, otherIdent;
	private String defaultValue;

	public void execute() {
		//Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);

		//Iden without state and country
		verifyIdenCountryAndState(socialSecNumIdent.identifierType, null, null);

		//Iden has state only
		verifyIdenCountryAndState(usDirversLicIdent.identifierType, null, usDirversLicIdent.states);

		//Iden has country only
		verifyIdenCountryAndState(passportIdent.identifierType, passportIdent.countries, null);

		//Iden has state and coun
		verifyIdenCountryAndState(otherIdent.identifierType, otherIdent.countries, otherIdent.states);
	}

	public void wrapParameters(Object[] param) {
		String tempStr = new DecimalFormat("000000000").format(new Random().nextInt(999999999));
		defaultValue = "Please select";

		cus.setDefaultValuesForHFWebSignUp();
		cus.email = "hfmo_99999@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum =  tempStr;

		//No country and state
		socialSecNumIdent = new CustomerIdentifier();
		socialSecNumIdent.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		socialSecNumIdent.identifierType = hf.getIdenTypeName(schema, socialSecNumIdent.id, false, false);
		socialSecNumIdent.identifierNum = tempStr;

		//Have state only
		usDirversLicIdent = new CustomerIdentifier();
		usDirversLicIdent.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID; 
		usDirversLicIdent.identifierType = hf.getIdenTypeName(schema, usDirversLicIdent.id, true, false);
		usDirversLicIdent.identifierNum = tempStr;
		usDirversLicIdent.state = "Alabama";
		usDirversLicIdent.states.add(defaultValue);
		usDirversLicIdent.states.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, usDirversLicIdent.id, StringUtil.EMPTY));

		//Have country only
		passportIdent = new CustomerIdentifier();
		passportIdent.id = OrmsConstants.IDEN_PASSPORT_NUM_ID; 
		passportIdent.identifierType = hf.getIdenTypeName(schema, passportIdent.id, false, true);
		passportIdent.identifierNum = tempStr;
		passportIdent.country = "Canada";
		passportIdent.countries.add(defaultValue);
		passportIdent.countries.add("Canada");
		passportIdent.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, passportIdent.id));

		//Have country and state
		otherIdent = new CustomerIdentifier();
		otherIdent.id = OrmsConstants.IDEN_OTHER_NUM_ID; 
		otherIdent.identifierType = hf.getIdenTypeName(schema, otherIdent.id, true, true);
		otherIdent.identifierNum = tempStr;
		otherIdent.country = "Canada";
		otherIdent.state = "Alberta";
		otherIdent.countries.add(defaultValue);
		otherIdent.countries.add("Canada");
		otherIdent.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, otherIdent.id));
		otherIdent.states.add("Please select");
		otherIdent.states.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, otherIdent.id, StringUtil.EMPTY));
	}

	private void verifyIdenCountryAndState(String idenType, List<String> countries, List<String> states){
		boolean result = true;
		createAccountPg.selectIdentType(idenType);
		createAccountPg.clickIdentTypeLabel();

		//For Country DDL
		if(countries==null || countries.size()<1){
			result &= MiscFunctions.compareResult(idenType+" country field existing or not", false, createAccountPg.isIdentCountryListExist());
		}else {
			Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
			createAccountPg.waitForCountryync();
			result &= MiscFunctions.compareResult(idenType+" country DDL elements", countries.toString(), createAccountPg.getIdentCountries().toString());
		}

		//For State DDL
		if(states==null || states.size()<1){
			result &= MiscFunctions.compareResult(idenType+" state field existing or not", false, createAccountPg.isIdenStateListExist());
		}else {
			if(countries!=null && countries.size()>0){
				createAccountPg.selectIdentCountry(countries.get(1)); 
				createAccountPg.clickIdentTypeLabel();	
			}
			Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
			createAccountPg.waitForStateSync();
			result &= MiscFunctions.compareResult(idenType+" state DDL elements", states.toString(), createAccountPg.getIdentStates().toString());
		}
		if(!result){
			throw new ErrorOnPageException("Failed to verify all country and state related for Iden Type:"+idenType);
		}
	}
}
