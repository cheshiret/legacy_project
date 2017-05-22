/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer.identifier;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Identifier from Update Account page and then update the identifier, view the display of the identifier number
 * @Preconditions: 
 * @SPEC: 
 * Add Identification [TC:045718]
 * 	Customer Identifier - Minimum number of character to mask Case 1 [TC:047833]
 * Customer Identifier - Minimum number of character to mask Case 2 [TC:047835]
 * Customer Identifier - Minimum number of character to mask Case 3 [TC:047966]
 * Customer Identifier - Minimum number of character to mask Case 4 [TC:047834]
 * @Task#: Auto-1620
 * 
 * @author Lesley Wang
 * @Date  Apr 24, 2013
 */
public class AddAndUpdateIdentifier extends HFSKWebTestCase {
	private CustomerIdentifier identCAF, identRCMP, identCANDL, identPs;
	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
	private int hideNum, showNum;
	private String mask;
	
	@Override
	public void execute() {	
		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);
		hf.signIn(url, cus.email, cus.password);
		
		// Add CAF identifier type with number length > hideNum+showNum, then update the identifier's number
		this.addAndUpdateIdentifier(identCAF, identCANDL.identifierNum, StringUtil.EMPTY, StringUtil.EMPTY);
	
		// Add RCMP identifier type with number length = hideNum, then update the identifier's state 
		this.addAndUpdateIdentifier(identRCMP, StringUtil.EMPTY, "New Brunswick", StringUtil.EMPTY);

		// Add CANDL identifier type with number length = hideNum+showNum, then update the identifier number and state
		this.addAndUpdateIdentifier(identCANDL, identPs.identifierNum, "New Brunswick", StringUtil.EMPTY);

		// Add Passport identifier type with number length < hideNum+showNum, then update the identifier without any change
		this.addAndUpdateIdentifier(identPs, StringUtil.EMPTY, StringUtil.EMPTY, "Canada");

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "addidentifier@test.com";
		cus.password = "asdfasdf";
		
		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		
		identCAF = this.initialIdentInfo(IDEN_CAF_ID, IDENT_TYPE_NAME_CAF, hideNum + showNum + 1, "British Columbia", StringUtil.EMPTY);
		identCANDL = this.initialIdentInfo(IDEN_CANDL_ID, IDENT_TYPE_NAME_CANDL, hideNum + showNum, "British Columbia", StringUtil.EMPTY);	
		identPs = this.initialIdentInfo(IDEN_PASSPORT_NUM_ID, IDENT_TYPE_NAME_PASSPORT, hideNum + showNum - 1, StringUtil.EMPTY, "United States");	
		identRCMP = this.initialIdentInfo(IDEN_RCMP_ID, IDENT_TYPE_NAME_RCMP, hideNum, "British Columbia", StringUtil.EMPTY);			
	}

	private CustomerIdentifier initialIdentInfo(String typeID, String typeName, int numLeg, String state, String country) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.identifierTypeID = typeID;
		ident.identTypeFullNm = typeName;
		ident.identifierType = typeName;
		ident.identifierNum = this.generateIdentNum(numLeg);
		ident.state = state;	
		ident.country = country;
		return ident;
	}
	
	private String generateIdentNum(int numLength) {
		String format = new DecimalFormat("0").format(Math.pow(10, numLength));
		int maxNum = Integer.parseInt(format) - 1;
		format = format.substring(1);
		return new DecimalFormat(format).format(new Random().nextInt(maxNum));
	}
	
	private void addAndUpdateIdentifier(CustomerIdentifier ident, String newIdentNum, String newState, String newCountry) {
		String maskedNum = StringUtil.encryptString(ident.identifierNum, mask, hideNum, showNum);
		hf.deleteCustIden(schema, ident.identifierTypeID, cus.email);
		
		logger.info("---Add and upate identifier type: " + ident.identTypeFullNm);
		hf.addIdentifier(ident);
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.verifyIdentifierInfo(ident, maskedNum);
		if (StringUtil.notEmpty(newIdentNum)) {
			ident.identifierNum = newIdentNum;
			maskedNum = StringUtil.encryptString(ident.identifierNum, mask, hideNum, showNum);
		}
		if (StringUtil.notEmpty(newCountry))
			ident.country = newCountry;
		if (StringUtil.notEmpty(newState))
			ident.state = newState;
		hf.updateIdentifier(ident.identifierTypeID, ident.identifierNum, ident.country, ident.state);
		updatePg.verifyIdentifierInfo(ident, maskedNum);
	}
}
