/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: View residency status declaration page after user update profile
 * @Preconditions: 
 * The record has been inserted into the table x_prop in SK schema by support sql: 
 * insert into x_prop (id, name, namespace, type, value) values (CONTRACT_SEQ.nextval, 'ResidencyModel', 'Contract', 'Number', '2');
 * @SPEC: (Web) Residency Status Declaration page - After user update profile [TC:062245]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 15, 2013
 */
public class ViewAfterUserUpdateProfile extends HFSKWebTestCase {
	private HFUpdateAccountPage updateAccount = HFUpdateAccountPage.getInstance();
	private HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage.getInstance();
	private String msg, newEmail, maskedNum;
	private CustomerIdentifier newIdent = new CustomerIdentifier();
	
	@Override
	public void execute() {
		// Delete all identifier except HALID
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		hf.deleteCustIden(schema, newIdent.identifierTypeID, cus.email);
		
		hf.signIn(url, cus.email, cus.password);	
		
		// Verify residency status page after login in
		hf.gotoResidStatusDeclaPg();
		resStatusPg.verifyImportantInfoExist(false);
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
		
		// Verify residency status page not shown when the account info is not updated
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		this.gotoPrdCatePgWithoutResidStatusDeclaPg();

		// Verify residency status page is shown when update account info
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		this.updateProfileAddress(cus.mailingAddr.address);
		hf.gotoResidStatusDeclaPg();
		resStatusPg.verifyImportantInfo(msg);
		resStatusPg.selectResidentStatus(cus.residencyStatus, cus.identifier.identifierType);
		resStatusPg.verifyIdentifierInfo(cus.residencyStatus, cus.identifier, maskedNum);	
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
		
		// Verify residency status page is shown when update an identifier
		hf.gotoMyAccountPgFromHeaderBar();
		hf.updateIdentifierState(cus.identifier.identifierTypeID, cus.identifier.identifierNum, newIdent.state);
		hf.gotoResidStatusDeclaPg();
		resStatusPg.verifyImportantInfo(msg);
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
		
		// Verify residency status page is shown when add an identifier
		hf.gotoMyAccountPgFromHeaderBar();
		hf.addIdentifier(newIdent);
		hf.gotoResidStatusDeclaPg();
		resStatusPg.verifyImportantInfo(msg);
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
		
		// Verify residency status page is shown when update email if Sign In Mode is on
		if (hf.isSignInWorkFlows(url)) {
			hf.gotoMyAccountPgFromHeaderBar();
			hf.updateEmailAddress(cus.password, newEmail);
			hf.gotoResidStatusDeclaPg();
			resStatusPg.verifyImportantInfo(msg);
			hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
			hf.gotoMyAccountPgFromHeaderBar();
			hf.updateEmailAddress(cus.password, cus.email);
		}
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		cus.email = "nonmohsk0002@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_CAF;
		cus.identifier.identifierTypeID = IDEN_CAF_ID;
		cus.identifier.identifierNum =  new DecimalFormat("00000").format(new Random().nextInt(99999));
		cus.identifier.state = "Ontario";
		cus.identifier.isDeclarRequired = true;
		cus.mailingAddr.address = "str addr " + DateFunctions.getCurrentTime();
		msg = "Residency status you previously selected is no longer valid.*";
		newEmail = "update" + cus.email;
		
		newIdent.identifierType = IDENT_TYPE_RCMP;
		newIdent.identifierTypeID = IDEN_RCMP_ID;
		newIdent.identifierNum = new DecimalFormat("00000").format(new Random().nextInt(99999));
		newIdent.country = StringUtil.EMPTY;
		newIdent.state = "Alberta";
		
		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);
	}

	private void gotoPrdCatePgWithoutResidStatusDeclaPg() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
		
		header.clickPurchaseLicenseTab();
		catListPg.waitLoading();
	}
	
	private void updateProfileAddress(String address) {
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage.getInstance();
		updateAccount.setMailingStreetAddress(address);
		updateAccount.clickSubmitButton();
		accOverviewPg.waitLoading();
	}
	
}
