package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Purchase a unlocked privilege which has business rules setup:
 *               1) Customer must be AT LEAST parameter age on the date of
 *               Privilege purchase; 2) Customer must be a RESIDENT in order to
 *               purchase this License; 3) Cannot purchase IF parameter
 *               Privilege on file; 4) Customer cannot have parameter SUSPENSION
 *               type on file (Deny Sale)
 * @Preconditions: In web, if the customer's address is MO address and has a
 *                 resident proof identifier, the customer can be taken as a
 *                 Resident. select * from C_RSDY_PROOF_TYPE_SETTINGS where by
 *                 app_id=9; (9 means Web)
 * 
 *                 Make sure the privilege has the four business rule setup as
 *                 above
 * @LinkSetUp: d_web_hf_signupaccount:id=950 d_hf_add_privilege_prd:id=2200,2030
 *             d_hf_add_pricing:id=3092 d_hf_assi_pri_to_store:id=1450
 *             d_hf_add_prvi_license_year:id=2230 d_hf_add_qty_control:id=1430
 *             d_hf_add_hunt:id=80 d_hf_assign_priv_to_hunt:id=80
 *             d_hf_add_hunt_license_year:id=130
 *             d_hf_add_pri_business_rule:id=290,300,310,320
 *             d_hf_add_species:id=30
 * @SPEC: Unlocked Privilege sales flow - business rule [TC:068251]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date Aug 13, 2013
 */
public class Purchase_BusinessRule extends HFMOUnlockedPrivTestCase {
	private CustomerIdentifier resProofIdent = new CustomerIdentifier();
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
			.getInstance();
	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
	private HFAccountOverviewPage overviewPg = HFAccountOverviewPage
			.getInstance();
	private String dobNotPurchase;
	private PrivilegeInfo privForRule;
	private Address canadaAddr;

	@Override
	public void execute() {
		// Import 1 unlocked privilege record
		this.prepareUnlockedPriv(fileName, privilege, cus.custNum, hunt);

		// In LM, make sure the customer age is older than 18, has an identifier
		// with resident proof, no suspension, invalid all privileges
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, privilege.licenseYear,
				searchStatus, privilege.operateReason, privilege.operateNote);

		lm.gotoCustomerDetailFromTopMenu(cus);
		LicMgrCustomerDetailsPage.getInstance().changeDateOfBirth(
				cus.dateOfBirth);
		LicMgrCustomerAdressesPage.getInstance().updateCustomerAddrInfo(cus);

		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();

		// lm.gotoCustomerIdentifierSubTab();
		// lm.safeAddCustomerIdentifier(resProofIdent);

		lm.logOutLicenseManager();

		// In Web, verify the unlocked privilege is shown
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), true);

		// 1. Verify the rule: Customer must be AT LEAST parameter age on the
		// date of Privilege purchase, age=18
		// update DOB to younger than 18, not shown
		hf.updateCustDOB(dobNotPurchase);
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), false);

		// update DOB to 18, shown
		hf.updateCustDOB(cus.dateOfBirth);
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), true);

		// Comment due to Conservation Number is a residency proof, so every
		// account already has a residency proof identifier when the account is
		// created
		// 2. Verify the rule: Customer must be a RESIDENT in order to purchase
		// this License
		// remove the residency proof, privilege not shown
		// hf.deleteCustIden(schema, resProofIdent.id, cus.fName, cus.lName);
		// hf.lookupAccountToAcctOverviewPg(cus);
		// hf.gotoLicenseCategoriesListPg();
		// catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
		// hunt.getDescription(), hunt.getNumOfTagQty(), false);
		//
		// // add the residency proof identifier, privilege shown
		// hf.addIdentifier(resProofIdent);
		// hf.gotoLicenseCategoriesListPg();
		// catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
		// hunt.getDescription(), hunt.getNumOfTagQty(), true);
		// hf.signOut();

		// update customer address not MO address, privilege not shown
		this.updateCustAddr(canadaAddr);
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), false);

		// update customer address as MO address, privilege is shown
		this.updateCustAddr(cus.physicalAddr);
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), true);
		hf.signOut();

		// 3. Verify the rule: Customer cannot have parameter SUSPENSION type on
		// file (Deny Sale)
		// add a suspension in LM and verify not shown
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension);
		lm.logOutLicenseManager();

		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cus, false);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), false);
		hf.signOut();

		// remove the suspension and verify shown
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();
		lm.logOutLicenseManager();

		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cus, false);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), true);

		// 4. Verify the rule: Cannot purchase IF parameter Privilege on file
		// purchase the specific privilege, and verify not shown
		hf.makePrivilegeOrderToCart(cus, privForRule);
		hf.checkOutCart(pay);

		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), false);
		hf.signOut();

		// invalid the privilege and verify shown
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, privilege.licenseYear,
				searchStatus, privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();

		hf.invokeURL(url);
		hf.gotoLicenseCategoriesListPg(cus, false);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name,
				hunt.getDescription(), hunt.getNumOfTagQty(), true);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv05_FN"; // d_web_hf_signupaccount, id=950
		cus.lName = "ULPriv05_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-18);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema,
				IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;

		resProofIdent.id = IDEN_SOCIALSECURITY_NUM_ID;
		resProofIdent.identifierType = hf.getIdenTypeName(schema,
				resProofIdent.id, false, false);
		resProofIdent.identifierNum = "900909003";

		dobNotPurchase = "01/01/" + DateFunctions.getYearAfterCurrentYear(-17);

		// Address Info
		cus.physicalAddr.address = "2480 Meadowvale";
		cus.physicalAddr.city = "Saint Louis";
		cus.physicalAddr.state = "Missouri";
		cus.physicalAddr.county = "St. Louis city";
		cus.physicalAddr.zip = "63101";
		cus.physicalAddr.country = "United States";
		cus.mailingAddr = cus.physicalAddr;

		canadaAddr = new Address();
		canadaAddr.address = "2480 meadowvale";
		canadaAddr.city = "Mississauga";
		canadaAddr.state = "Ontario";
		canadaAddr.zip = "L5N8M6";
		canadaAddr.country = "Canada";

		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("H08");
		hunt.setDescription("HFMO Hunt008");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));
		hunt.setNumOfTagQty("1");

		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.code = "MOJ";
		privilege.name = "HFMO HuntLic005";

		privForRule = new PrivilegeInfo();
		privForRule.name = "HFMO License001";
		privForRule.licenseYear = privilege.licenseYear;
		
		// suspension info
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Fishing Violation";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to test business rule";

		fileName = "Purchase_BusinessRule";
	}

	private void updateCustAddr(Address address) {
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.setupMailingAddress(address);
		updatePg.unselectHomeAddDiffFromMailAdd();
		updatePg.clickSubmitButton();
		overviewPg.waitLoading();
	}
}
