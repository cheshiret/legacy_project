package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (p) 
 * 1. Go to SK license manager to make 2 orders, one with 5 active licences, the other with 5 revoke licences
 * 2. Go to HFSK web site to verify licences sorting in current and expired list page
 * 1) Grouped by license year, ascending;
 * 2) With the group of the same license year, sorted by license name, ascending, case-insensitive;
 * 3) Within the group, for the same license year, for the same licenses, sorted by 'valid from' value, ascending;
 * 3. Go to licences details page after click licence name or number in current or expired licence list page
 * 
 * @Preconditions:
 * d_web_hf_signupaccount
 * id=360, hfsk_00015@webhftest.com, 2000-01-17
 * d_hf_add_privilege_prd, id=1980, 1990, 2000, 2010 and 2020

 * @SPEC: 
 * Grouping and sorting License/Permit items on Current/Expried tabs [TC:046089]
 * Click on license name / license number links on Current/Expired tab [TC:046333] 
 * @Task#: AUTO-1642
 * 
 * @author SWang
 * @Date  May 28, 2013
 */
public class GroupingAndSorting extends HFSKWebTestCase {
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private String validValue1, validValue2;
	private PrivilegeInfo privilege2, privilege3, privilege4, privilege5;
	private PrivilegeInfo[] privileges;
	private String[] searchLicYears;
	private List<String> sortLicences, sortValids;
	private TimeZone timeZone = null;

	public void execute() {
		//Precondition
		//* Remove added suspension for 1# account in LM
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus.customerClass, IDENT_TYPE_HAL, lm.getCustomerNumByEmail(cus.email, schema));
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();
		lm.logOutLicenseManager(); 

		//* Add identifier for 1# account in HFSK
		hf.signIn(url, cus.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//* Invalid all licenses for 1# account in LM
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYears, searchStatus, privilege.operateReason, privilege.operateNote);
		lm.gotoHomePage();

		//Prepared licences in LM
		//#1, 5 revoked
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privileges);
		lm.processOrderCart(pay); 
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, null);
		lm.gotoHomePage();

		//#2, 5 active
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privileges);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();

		//Go to HFSK web site
		hf.signIn(url, cus.email, cus.password);

		//Check point 1: licences sorting in current licence list page
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		verifyLicencesSorting(true);

		//Check point 2: licences sorting in expired licence list page
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		verifyLicencesSorting(false);

		//Check point 3: go to licence details paeg after click licence name or number in current or expired licence list page
		gotoLicencesDetailsPg(sortLicences.get(0));
		hf.signOut();

		//Invalid customer licenses in LM
		new com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, searchLicYear, searchStatus});
	}

	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);

		//Customer parameters
		cus.email = "hfsk_00015@webhftest.com";
		cus.dateOfBirth = "2000-01-17";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4134";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//Privileges parameters
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.purchasingName = "STA-HFSK SortLicense01";
		privilege2 = new PrivilegeInfo();
		privilege2.licenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		privilege2.purchasingName = "STB-HFSK SortLicense02";	
		privilege3 = new PrivilegeInfo();
		privilege3.licenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(2));
		privilege3.purchasingName = "STC-HFSK SortLicense03";
		privilege4 = new PrivilegeInfo();
		privilege4.licenseYear = privilege.licenseYear;
		privilege4.purchasingName = "STD-HFSK SortLicense04";
		privilege5 = new PrivilegeInfo();
		privilege5.licenseYear = privilege.licenseYear;
		privilege5.validFromDate = DateFunctions.getDateAfterToday(10, timeZone);
		privilege5.purchasingName = "STE-HFSK SortLicense04";

		privileges = new PrivilegeInfo[]{privilege,privilege2,privilege3,privilege4,privilege5};
		searchLicYears = new String[]{privilege.licenseYear, privilege2.licenseYear, privilege3.licenseYear};

		validValue1 = DateFunctions.formatDate(DateFunctions.getDateAfterToday(0, timeZone), "E MMM dd yyyy");
		validValue2 =DateFunctions.formatDate(privilege5.validFromDate, "E MMM dd yyyy")+"-"+DateFunctions.formatDate(DateFunctions.getDateAfterToday(374, timeZone), "E MMM dd yyyy");
		sortLicences = new ArrayList<String>();
		sortLicences.add("HFSK SortLicense01");
		sortLicences.add("HFSK SortLicense04");
		sortLicences.add("HFSK SortLicense04");
		sortLicences.add("HFSK SortLicense02");
		sortLicences.add("HFSK SortLicense03");
		sortValids = new ArrayList<String>();
		sortValids.add(validValue1);
		sortValids.add(validValue1);
		sortValids.add(validValue2);
		sortValids.add(validValue1);
		sortValids.add(validValue1);

		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		searchLicYear = String.valueOf(DateFunctions.getCurrentYear());
		searchStatus = new String[]{"Active", "Revoked"};
		pay.payType = Payment.PAY_DEF_CASH;
	}

	/**
	 * Verify licences sorting 
	 * @param isCurrentLicencesPg: true, verify in current licence list page
	 *                             false, verify in expired licence list page
	 */
	private void verifyLicencesSorting(boolean isCurrentLicencesPg){
		boolean result = true;
		List<String> licences = new ArrayList<String>();
		List<String> valids = new ArrayList<String>();

		if(isCurrentLicencesPg){
			logger.info("Verify licence sorting in current licence list page.");
			licences = currentLicensePg.getAllLicenses();
			valids = currentLicensePg.getAllValids();
		}else{
			logger.info("Verify licence sorting in expired licence list page.");
			licences = expiredLicensesListPg.getAllLicenses();
			valids = expiredLicensesListPg.getAllValids();
		}

		result &= MiscFunctions.compareResult("Licences sort", sortLicences.toString(), licences.toString());
		boolean displayHFWebValidDates = WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.HFWebDisplayValidDates, MiscFunctions.getPLNameFromURL(url));//UIOptionConfigurationUtil.isHFWebHideValidDates(MiscFunctions.getPLNameFromURL(url));
		if(displayHFWebValidDates){
			result &= MiscFunctions.compareResult("Valids sort", sortValids.toString(), valids.toString());
		}
		if(!result){
			throw new ErrorOnPageException("Licences sorting are wrong!");
		}
		logger.info("Successfully verify licences sorting.");
	}

	/**
	 * verify going to licence details page after click licence name or number
	 * @param licenceName
	 */
	private void gotoLicencesDetailsPg(String licenceName){
		HFLicenseDetailsPage licenseDetailsPg = HFLicenseDetailsPage.getInstance();

		//Click licence name in expired licence list page
		expiredLicensesListPg.clickLicenceName(licenceName);
		licenseDetailsPg.waitLoading();
		licenseDetailsPg.clickPrevious();
		expiredLicensesListPg.waitLoading();

		//Click licence number in expired licence list page
		expiredLicensesListPg.clickLicenceNum(licenceName);
		licenseDetailsPg.waitLoading();
		licenseDetailsPg.clickPrevious();

		hf.gotoCurrentLicensesListPgFromExpiredLicencesListPg();

		//Click licence name in current licence list page
		currentLicensePg.clickLicenceName(licenceName);
		licenseDetailsPg.waitLoading();
		licenseDetailsPg.clickPrevious();
		currentLicensePg.waitLoading();

		//Click licence number in current licence list page
		currentLicensePg.clickLicenceNum(licenceName);
		licenseDetailsPg.waitLoading();
	}
}
