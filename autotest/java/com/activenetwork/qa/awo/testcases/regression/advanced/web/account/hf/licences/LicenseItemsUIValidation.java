package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (P)
 * 1. Go to license manager, make one order with 2 active order items;
 * 2. Go to HFSK, to verify license elements in current and expired license page;
 * 3. Go to license manager, revoked previous 2 order items;
 * 4. Go to HFSK, to verify license elements in current and expired license page;
 * 
 * Valid date:
 * 1) If both of values for 'Valid from' and 'Valid to' existing, the label will be 'Valid Dates:', then '<valid from date> - <valid to date>;
 * 2) If there is only 'Valid from' value, the label will be :'Valid:', and value will be <valid from date>
 *
 *Type:
 * 1) The value is the value of subcategory set up in AWO for license product;
 * 2) The entire element is not displayed if blank set up for subcategory for the license product in AWO
 *
 * @Preconditions:
 * 1. d_web_hf_signupaccount, id=300, hfsk_00010@webhftest.com , 2000-01-12
 * 2. Licence(SKE, HFSK License005)
       "valid from" as "Based on Priv LY Record or Purchase Date (If Greater)"
       "valid to" as "Based on Priv Licence Year Record";
       "Display Sub-Category" as "Annual"
 * 3. Licence(SKF, HFSK License006)
       "valid from" as "Based on Priv LY Record or Purchase Date (If Greater)"
       "valid to" as "Valid From Date plus Valid Days/Years"
       "Valid Days" as "200"
       No "Display Sub-Category"
 * 4. Test data in support script     
		d_web_hf_signupaccount,id=300
		d_hf_add_privilege_prd, id=1910, 1920
		d_hf_add_pricing, id=2532, 2542, 2552, 2562, 2572, 2582
		d_hf_assi_pri_to_store, id=1160, 1170
		d_hf_add_qty_control, id=1140, 1150
		d_hf_add_prvi_license_year, id=1630, 1640
		d_hf_add_print_doc, id=290, 300

 * @SPEC: License/Permit items UI [TC:046105] 
 * @Task#: Auto-1641
 * 
 * @author SWang
 * @Date  May 23, 2013
 */
public class LicenseItemsUIValidation extends HFSKWebTestCase {
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private Suspension suspension = new Suspension();
	private String licenseName1, licenseName2, validValue1, validValue2, type, status, searchLicYear;
	private String[] searchStatus;
	private PrivilegeInfo privilege2;
	private TimeZone timeZone = null;

	public void execute() {
		//Precondition
		//* Add identifier for 1# account in HFSK
		hf.signIn(url, cus.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//* Invalid all licenses for 1# account in LM
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYear, searchStatus, privilege.operateReason, privilege.operateNote);

		//* Remove added suspension for 1# account in LM
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();

		//Make order with 2 order items one has valid from and display sub, the other has both valid from and to, but no display sub
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege, privilege2);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();

		//Check point 1: Verify licenses elements in current license page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		currentLicensePg.verifyLicenseInfo(licenseName1, validValue1, type, StringUtil.EMPTY);
		currentLicensePg.verifyLicenseInfo(licenseName2, validValue2, StringUtil.EMPTY, StringUtil.EMPTY);

		//Check point 2: Verify no licenses in expired license page
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		expiredLicensesListPg.verifyNoResults();
		hf.signOut();

		//Revoke order in license manager
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, null);
		lm.gotoHomePage();

		//Check point 3: Verify no licenses in current license page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		currentLicensePg.verifyNoResults();

		//Check point 4: Verify licenses elements in expired license page
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		expiredLicensesListPg.verifyLicenseInfo(licenseName1, validValue1, type, status);
		expiredLicensesListPg.verifyLicenseInfo(licenseName2, validValue2, StringUtil.EMPTY, status);
		hf.signOut();

		//Invalid customer licenses in LM
		new com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, searchLicYear, searchStatus});
	}

	public void wrapParameters(Object[] param) {
		//Login info for LM
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);

		//Customer info
		cus.email = "hfsk_00010@webhftest.com";
		cus.dateOfBirth = "2000-01-12";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4157";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//Privileges Info
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.purchasingName = "SKE-HFSK License005";
		privilege2 = new PrivilegeInfo();
		privilege2.licenseYear = privilege.licenseYear;
		privilege2.purchasingName = "SKF-HFSK License006";	

		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		licenseName1 = "HFSK License005";
		licenseName2 = "HFSK License006";
		boolean isHFWebHideValidDates = !WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.HFWebDisplayValidDates, MiscFunctions.getPLNameFromURL(url));
		if(isHFWebHideValidDates){
			validValue1 = StringUtil.EMPTY;
			validValue2 = StringUtil.EMPTY;
		}else{
			validValue1 = DateFunctions.formatDate(DateFunctions.getDateAfterToday(0, timeZone), "E MMM dd yyyy"); //Thu May 23 2013
			validValue2 = validValue1+"-"+DateFunctions.formatDate(DateFunctions.getDateAfterToday(200, timeZone), "E MMM dd yyyy");
		}
		type = "Annual";
		status = "Revoked";

		//Suspension Info
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		pay.payType = Payment.PAY_DEF_CASH;

		searchLicYear = String.valueOf(DateFunctions.getCurrentYear());
		searchStatus = new String[]{"Active", "Revoked"};
	}
}

