/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFIdentificationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: (P) Verify Add Identification link on Update Account page and Cancel link on Add Identification page
 * The Add Identification link will not be shown if the account has all identifiers added.
 * @Preconditions: the account exists
 * @SPEC:
 *  Add Identification link on Update Hunt and Fish Record page [TC:045390]
 *  'Cancel' Link on Add Identification page [TC:046103]
 *  'Cancel' Link on Update Identification page [TC:046329] 
 *  Identification list on Update Record page [TC:046088] 
 * @Task#: Auto-1630, Auto-1631, Auto-1636
 * 
 * @author Lesley Wang, Sara Wang
 * @Date  Apr 28, 2013, May 30, 2013
 */
public class VerifyAddIdentificationAndCancelLinks extends HFSKWebTestCase {
	private HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
	private HFAddIdentificationPage addIdentPg = HFAddIdentificationPage.getInstance();
	private String identFieldText, num, num1, state, flNum, errorMes1, errorMes2, cusNum;
	private List<String> idenTypesFromDB, idenTypeIDsFromDB;
	private List<Boolean> statesSeqIndFromDB, countriesSeqIndFromDB;

	@Override
	public void execute() {
		//Delete all identifiers for 1# and 2#
		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);
		hf.deleteCustAllIdentExceptCustNum(schema, cusNew.email);

		//Add identifier to the 2# account
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.addIdentifier(cusNew.identifier);
		hf.signOut();

		//Sign in with 1# account
		hf.signIn(url, cus.email, cus.password);

		//Verify Add Identification link displayed at the bottom of the identifiers list
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		identFieldText = updAcctPg.getIdentifiersFieldText();
		this.verifyAddIdentLinkExist(true);

		//Verify Cancel link
		//did nothing
		//select identifier type, but leave all input fields blank
		//select identifier type, enter valid data for mandatory fields
		//select identifier type, enter invalid data and submit to get validation error message
		//select identifier type, enter exiting data and submit to get message of 'Same <identification> is used by another user'
		this.verifyCancelLink(StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY); 
		this.verifyCancelLink(IDENT_TYPE_NAME_CAF, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY); 
		this.verifyCancelLink(IDENT_TYPE_NAME_RCMP, num, state, StringUtil.EMPTY); 
		this.verifyCancelLink(IDENT_TYPE_NAME_RCMP, flNum, state, errorMes1); 
		this.verifyCancelLink(IDENT_TYPE_NAME_RCMP, cusNew.identifier.identifierNum, cusNew.identifier.state, errorMes2); 

		// Add all identifiers successfully and then verify no Add Identification link
		this.addAllIdentifiers(idenTypesFromDB);
		this.verifyAddIdentLinkExist(false);
		hf.gotoUpdateProfilePg();
		this.verifyIdenSortingInUpdateProfilePg();
		hf.signOut();

		//Go to License manager to deactivate one iden
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus.customerClass, IDENT_TYPE_HAL, cusNum);
		lm.gotoIdentifiersFromCustomerDetailsPg();
		lm.changeIdentifierStatus(cusNew.identifier.identifierType, num, "Deactivate");
		lm.logOutLicenseManager();

		//Go to HFSK to verify the inactive iden doesn't display in update profile page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updAcctPg.verifyIdenExisting(cusNew.identifier.identifierType, false);
		this.verifyAddIdentLinkExist(true);

		//Add that kinds of iden again, to check the added iden displays and no add identification link
		cusNew.identifier.identifierNum = num1;
		hf.addIdentifier(cusNew.identifier);
		hf.gotoUpdateProfilePg();
		updAcctPg.verifyIdenExisting(cusNew.identifier.identifierType, true);
		this.verifyAddIdentLinkExist(false);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		cus.fName = "HF_Add02";
		cus.lName = "Web_Ident02";
		cus.email = "addidentifier02@testnotsharable.com"; // d_web_hf_signupaccount, id=190, not sharable
		cus.password = "asdfasdf";
		cusNum = lm.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		cusNew.email = "addidentifier03@testnotsharable.com"; 
		cusNew.password = "asdfasdf";
		cusNew.identifier.identifierType = IDENT_TYPE_NAME_RCMP;
		cusNew.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cusNew.identifier.state = "Prince Edward Island";

		idenTypesFromDB = hf.getAllIdenTypesFromDB(schema);
		idenTypesFromDB.remove(0);
		idenTypeIDsFromDB = hf.getAllIdenTypeIDsFromDB(schema);
		idenTypeIDsFromDB.remove(0);
		statesSeqIndFromDB = hf.getAllStatesReqIndFromDB(schema, idenTypeIDsFromDB);
		countriesSeqIndFromDB = hf.getAllCountriesReqIndFromDB(schema, idenTypeIDsFromDB);

		num = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		num1 = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		flNum = "78978956.1234";
		state = "Prince Edward Island";

		errorMes1 = ".*must only contain numbers, embedded spaces or a dash.*";
		errorMes2 = ".*with the same number is used by another account.*";
	}

	private void verifyAddIdentLinkExist(boolean isExist) {
		String linkText = updAcctPg.getAddIdentLinkText();
		if (isExist && (StringUtil.isEmpty(linkText) || !identFieldText.endsWith(linkText))) {
			throw new ErrorOnPageException("Add Identfication link doesn't displayed on the bottom of the identfier list.", identFieldText, linkText);
		} else if (!isExist && updAcctPg.isAddIdentLinkExist()){
			throw new ErrorOnPageException("Add Identication link should not exist!");
		}
		logger.info("---Verify Add identification link correctly!");
	}

	private void verifyCancelLink(String identType, String identNum, String state, String errorMesRegx) {
		cus.identifier.identifierType = identType;
		cus.identifier.identifierNum = identNum;
		cus.identifier.state = state;

		hf.gotoAddIdentificationPgFromUptAcctPg();
		if (StringUtil.notEmpty(cus.identifier.identifierType)){
			if(StringUtil.notEmpty(errorMesRegx)){
				hf.addIdentifierFromAddIdentificationPg(cus.identifier);
				addIdentPg.verifyErrorMsgExist(errorMesRegx, true);
			}else{
				addIdentPg.addIdentification(cus.identifier);
			}
		}
		addIdentPg.clickCancelLink();
		updAcctPg.waitLoading();

		boolean result = MiscFunctions.compareResult("Error Msg exist", false, updAcctPg.topPageErrorMesDisplays());
		result &= MiscFunctions.compareString("identfication field text", identFieldText, updAcctPg.getIdentifiersFieldText());

		if (!result) {
			throw new ErrorOnPageException("Verify Cancel link wrongly when select " + identType);
		}
		logger.info("---Verify Cancel link correctly when select " + identType);
	}

	private void addAllIdentifiers(List<String> identTypes) {
		for (int i = 0; i < identTypes.size(); i++) {
			cus.identifier.identifierType = identTypes.get(i);
			logger.info("Add identifier type: " + cus.identifier.identifierType);
			hf.gotoAddIdentificationPg();
			addIdentPg.selectIdentifierType(cus.identifier.identifierType);
			if (cus.identifier.identifierType.equals(IDENT_TYPE_NAME_FL)) {
				addIdentPg.setIdentificationNum(flNum, cus.identifier.identifierType);	
			} else {
				addIdentPg.setIdentificationNum(num, cus.identifier.identifierType);
			}
			if (countriesSeqIndFromDB.get(i)) {
				addIdentPg.waitForCountrySync(identTypes.get(i));
				cus.identifier.country = addIdentPg.getCountries(cus.identifier.identifierType).get(1);
				addIdentPg.selectCountry(cus.identifier.country, cus.identifier.identifierType);
			}
			if (statesSeqIndFromDB.get(i)) {
				addIdentPg.waitForProvinceSync(identTypes.get(i));
				cus.identifier.state = addIdentPg.getStates(cus.identifier.identifierType).get(1);
				addIdentPg.selectState(cus.identifier.state, cus.identifier.identifierType);
			}
			addIdentPg.clickAddIdentificationButton();
			HFIdentificationAddedPage.getInstance().waitLoading();
		}
	}

	/**
	 *Identifiers sort by sales_priority number in c_identifier_type table
	 */
	private void verifyIdenSortingInUpdateProfilePg(){
		List<String> idenTypes = updAcctPg.getAllIdentifiersTypes();
		boolean result = MiscFunctions.compareResult("HAL ID type", IDENT_TYPE_HAL, idenTypes.get(0));
		idenTypes.remove(0);
		result &= MiscFunctions.compareResult("Iden types other than HAL ID", idenTypesFromDB.toString(), idenTypes.toString());
		if(!result){
			throw new ErrorOnPageException("Failed to verify iden types sorting in update account page.");
		}
		logger.info("Successfully verify iden types sorting in update account page.");
	}
}
