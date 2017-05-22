/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify Add Identifier Page UI
 * @Preconditions:
 * @SPEC: 
 * Add identifier Page UI [TC:044140]
 * 	Max. input length of 100 for free text input fields [TC:044605]
 * @Task#: Auto-1630
 * 
 * @author Lesley Wang
 * @Date  Apr 27, 2013
 */
public class VerifyAddIdentifierPageUI extends HFSKWebTestCase {
	private HFAddIdentificationPage addIdentPg = HFAddIdentificationPage.getInstance();
	private String pageTitle, stateLabel, countryLabel, OneHundredAndOneCharString, OneHundredharString, idenId;
	private List<String> idenTypesFromDB;

	@Override
	public void execute() {
//		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);
		hf.deleteCustIdenExceptGivenIdAndCustNum(schema, idenId, cus.email);
		hf.signIn(url, cus.email, cus.password);

		hf.gotoAddIdentificationPg();
		this.verifyPageUI();
		this.verifyIdentNumMaxInputLength();
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "updatecustprofile@test.com"; // d_web_hf_signupaccount, id=110
		cus.password = "asdfasdf";

		pageTitle = "Add Identification\\s*Please add an identification. Once one of the following identifications is added to your account you will be able to purchase licences.*available to you.*";
		idenTypesFromDB = hf.getAllIdenTypesFromDB(schema);
		//Sara[20140717] Remove customer number
		System.out.println(idenTypesFromDB.get(0).equalsIgnoreCase("HAL ID"));
		if(idenTypesFromDB.get(0).equalsIgnoreCase("HAL ID")){
			idenTypesFromDB.remove(0);
		}
		stateLabel = "Province";
		countryLabel = "Country";
        idenId = OrmsConstants.IDEN_SKDL_ID;
		OneHundredharString = "1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 00";
		OneHundredAndOneCharString = OneHundredharString + "9";
	}

	private void verifyPageUI() {
		boolean result = true;
		// page title and description
		result &= MiscFunctions.matchString("page title and description", addIdentPg.getPageTitle(), pageTitle);
		// identification list
		result &= MiscFunctions.compareString("identification list", idenTypesFromDB.toString(), addIdentPg.getAllIdenTypes().toString());
		// submit button and cancel link
		result &= MiscFunctions.compareResult("add identification button exist", true, addIdentPg.isAddIdentBtnExist());
		result &= MiscFunctions.compareResult("cancel link exist", true, addIdentPg.isCancelLinkExist());
		// no radio button pre-selected
		result &= MiscFunctions.compareResult("none radio button selected", true, addIdentPg.isNoneRadioBtnSelected());
		// check identifier info field labels
		result &= MiscFunctions.compareResult("identifier number label not exist", false, addIdentPg.isIdentNumLableExist());
		result &= MiscFunctions.compareString("identifier state label", stateLabel, addIdentPg.getIdentStateLable());
		result &= MiscFunctions.compareString("identifier country label", countryLabel, addIdentPg.getIdentCountryLable());
		if (!result) {
			throw new ErrorOnPageException("Add Identification UI is wrong! Check logger error!");
		}
		logger.info("---Verify Add Identification UI correctly!");
	}

	// Verify identifier number text field max input length
	private void verifyIdentNumMaxInputLength() {
		boolean result = true;
		for (int i = 0; i < idenTypesFromDB.size(); i++) {
			String identType = idenTypesFromDB.get(i);
			addIdentPg.selectIdentifierType(identType);
			addIdentPg.setIdentificationNum(OneHundredAndOneCharString, identType);
			String idenMaxLength = addIdentPg.getIdenMaxLength(identType);
			result &= MiscFunctions.compareString(idenTypesFromDB.get(i) + " text field max input length=100", (StringUtil.isEmpty(idenMaxLength))?OneHundredharString:OneHundredharString.substring(0, Integer.valueOf(addIdentPg.getIdenMaxLength(identType))), addIdentPg.getIdenNum(identType));
		}
		if (!result) {
			throw new ErrorOnPageException("Identifier Number max input length as 100 is wrong! Check logger error!");
		}
		logger.info("---Verify Identifier Number max input length as 100 correctly!");
	}
}