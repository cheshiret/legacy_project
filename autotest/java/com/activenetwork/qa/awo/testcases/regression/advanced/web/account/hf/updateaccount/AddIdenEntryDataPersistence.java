package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFIdentificationAddedPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43620 -> P) Verify Entry data persistence when switching between identifiers
 * 
 * @Preconditions:
 * d_web_hf_signupaccount
 * id=180, hfsk_00000@webhftest.com, 2000-01-01
 * id=200, hfsk_00001@webhftest.com, 2000-01-02
 * 
 * @SPEC: Add Identification page - Entry Data persistence when switching between identifiers [TC:045720] 
 * @Task#: Auto_1633
 * 
 * @author Swang
 * @Date  Apr 28, 2013
 */
public class AddIdenEntryDataPersistence extends HFSKWebTestCase {
	private HFIdentificationAddedPage identificationAddedPg = HFIdentificationAddedPage.getInstance();
	private HFAddIdentificationPage addIdentificationPg = HFAddIdentificationPage.getInstance();
	private String topPgErrorMes, useByAnotherAccountErrorMes, idenNum;
	private CustomerIdentifier iden1 = new CustomerIdentifier();
	private CustomerIdentifier iden2 = new CustomerIdentifier();

	public void execute() {
		//Delete used identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, cus.email);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, cusNew.email);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cusNew.email);

		//Add passport identification to 1# customer
		hf.signIn(url, cus.email, cus.password);
		hf.addIdentifier(iden1);
		hf.signOut();

		//Go to 2# customer to add identification 
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.gotoAddIdentificationPg();

		//Enter data for identification, Passport# and Other#
		addIdentificationPg.addIdentification(iden1);
		addIdentificationPg.addIdentification(iden2);

		//All the entry data and selections shall be retained when switch between the identifiers (Passport# and Other#)
		addIdentificationPg.selectIdentifierType(iden1.identifierType);
		addIdentificationPg.verifyIdenTypeValues(iden1);
		addIdentificationPg.selectIdentifierType(iden2.identifierType);
		addIdentificationPg.verifyIdenTypeValues(iden2);

		//Check tops error message with invalid identification number
		iden1.identifierNum = "123%%45";
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdentificationPg.verifyErrorMsgExist(topPgErrorMes, true);

		//Check only retain the current selected identifier information
		addIdentificationPg.verifyIdenTypeValues(iden1);
		addIdentificationPg.selectIdentifierType(iden2.identifierType);
		iden2.identifierNum = StringUtil.EMPTY;
		iden2.country = addIdentificationPg.getCountries(iden2.identifierType).get(0);
		iden2.state = StringUtil.EMPTY;
		addIdentificationPg.verifyIdenTypeValues(iden2);

		//Check error message when same identification used by another account
		iden1.identifierNum = "1R9Y4x4118";
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdentificationPg.verifyErrorMsgExist(useByAnotherAccountErrorMes, true);

		//Check only retain the current selected identifier information
		addIdentificationPg.verifyIdenTypeValues(iden1);
		addIdentificationPg.selectIdentifierType(iden2.identifierType);
		addIdentificationPg.verifyIdenTypeValues(iden2);

		//Successfully add identification
		iden1.identifierNum = idenNum;
		hf.addIdentifierFromAddIdentificationPg(iden1);		
		identificationAddedPg.waitLoading();

		//Go to add identification page again to check all the input and drop down list fields are cleared and reset 
		hf.gotoAddIdentificationPg();
		addIdentificationPg.verifyIdenTypeExists(iden1.identifierType, false);
		addIdentificationPg.selectIdentifierType(iden2.identifierType);
		addIdentificationPg.verifyIdenTypeValues(iden2);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.email = "hfsk_00000@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = "2000-01-01";

		cusNew.email = "hfsk_00001@webhftest.com";
		cusNew.password = cus.password;
		cusNew.dateOfBirth = "2000-01-02";

		idenNum = "1R9Y4x4120";

		iden1.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		iden1.identifierNum = "1R9Y4x4118";
		iden1.country = "Algeria";
		iden1.state = StringUtil.EMPTY;

		iden2.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_OTHER_NUM_ID, true, true);
		iden2.identifierNum = "1R9Y4x4119";
		iden2.country = "Canada";
		iden2.state = StringUtil.EMPTY;

		topPgErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		useByAnotherAccountErrorMes = ".*the same number is used by another account\\. Please contact the call center for more information";
	}
}
