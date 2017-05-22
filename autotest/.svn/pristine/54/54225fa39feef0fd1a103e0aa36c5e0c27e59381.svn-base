/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Sign up an account when determine residency based on address model.
 * In address model, after sign up an account, the Account Overview page is shown. 
 * In address model, the Residency status declaration page can only be shown.
 * @Preconditions:
 * The record has been inserted into the table x_prop in SK schema by support sql: 
 * insert into x_prop (id, name, namespace, type, value) values (CONTRACT_SEQ.nextval, 'ResidencyModel', 'Contract', 'Number', '2');
 * @SPEC: (Web) Determine Residency Based on Address Model - after create a profile [TC:062214]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 12, 2013
 */
public class SignUpOnAddressModel extends HFSKWebTestCase {
	private HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();
	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
	private String msg;
	
	@Override
	public void execute() {
		logger.info("1. Sign up a new account and verify the successfully message...");
		String halID = hf.signUpNewAccount(url, cus);
		accOverviewPg.verifyTopMsg(msg);
		
		// Go to Residency Status Declaration page
		hf.gotoResidStatusDeclaPg();
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus);		
		hf.signOut();

		// Sign in and verify the info on Update Account page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.verifyHALID(halID);
		updatePg.verifyAccountInfo(cus);
		hf.signOut();
		
		// Clean up
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email.toLowerCase().trim());
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hf" + new Random().nextInt(99999) + "@testonaddrmode.com";;
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.fName = "KIRAN";
		cus.mName = "SEAN HARI";
		cus.lName = "KAVAKWA";
		cus.dateOfBirth = "1979-12-16";
		cus.custGender = "M";
		cus.eyeColor = "Black";
		cus.hairColor = "Black";
		cus.heightFt = "5";
		cus.heightIn = "7";
		cus.setDefaultSKMailingAddress();
		cus.hPhone = "8694528962";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		msg = "Your Account has been Created Successfully.";

	}
	
//	private void gotoAddIdenPgFromAccOverviewPg() {
//		HFAddIdentificationPage addIdentPg = HFAddIdentificationPage.getInstance();
//		logger.info("Click Add Identification link in the message to Add Identification page...");
//		accOverviewPg.clickAddIdentifierLink();
//		addIdentPg.waitLoading();
//	}

}
