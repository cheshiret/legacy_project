package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.supportscripts.function.license.RemoveCustAllSuspensions;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: p (32 minutes 31 seconds)
 * 1. Go to SK license manager to make 2 orders, one with 11 active licences, the other with 11 revoked licences;
 * 2. Go to HFSK web site licences page to verify page title and pagination controls in current and expired licences list page
 *    Total items count result in current first page
 *    In first page, previous link is not available, but next link is available
 *    Go to next page to check total items count result is updating
 *    In last page, previous link is available, but next link is not available
 *    Go to expired/current page, then go back to current/expired page to verify initial pagination
 *    
 * @Preconditions: 
 * d_web_hf_signupaccount, id=350, hfsk_00014@webhftest.com,  2000-01-16 
 * d_hf_add_privilege_prd, id=1870, 1880, 1890, 1900, 1910, 1920, 1930, 1940, 1950, 1960, 1970, CODE(SKA, SKB...SKK)
 * 
 * @SPEC: 
 * Current/Expired Tabs switching [TC:044617] 
 * Pagination Controls on View License page [TC:046332] 
 * View Licenses page UI [TC:046104]
 * License Details page - 'Previous' link [TC:046898]
 * @Task#: AUTO-1642, Auto-1719
 * 
 * @author SWang, Lesley Wang
 * @Date  May 28, 2013, Jun 19, 2013
 */
public class PaginationControls extends HFSKWebTestCase {
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private String initialCurrentPaginationControlText, initialExpiredPaginationControlText, pageTitle;
	private PrivilegeInfo privilege2, privilege3, privilege4, privilege5, privilege6, privilege7, privilege8, privilege9, privilege10, privilege11;
	private PrivilegeInfo[] privileges;

	public void execute() {
		//Precondition
		//* Add identifier for 1# account in HFSK
		hf.signIn(url, cus.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		hf.signOut();
		
		//* Remove added suspension for 1# account in LM
		new RemoveCustAllSuspensions().execute(new Object[] {loginLM, cusNew});

		//* Invalid all licenses for 1# account in LM
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYear, searchStatus, privilege.operateReason, privilege.operateNote);

		//Prepare licences in LM
		//#1, 11 revoked
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privileges);
		cus.orderNum = lm.processOrderCart(pay).split(" ")[0]; 
		String nums = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		String revokedPriNum1 = nums.substring(0, nums.indexOf(","));// first number
		String revokedPriNum2 = nums.substring(nums.lastIndexOf(",")+1); // last number
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, null);

		//#2, 11 active
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privileges);
		cus.orderNum = lm.processOrderCart(pay).split(" ")[0]; 
		nums = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		String activePriNum1 = nums.substring(0, nums.indexOf(",")); // first number
		String activePriNum2 = nums.substring(nums.lastIndexOf(",")+1); // last number
		lm.logOutLicenseManager();

		//Go to HFSK current licence list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();

		//Check point 1: verify page title
		currentLicensePg.verifyPageTitle(pageTitle);

		//Check point 2: verify pagination controls from current licences list page
		initialCurrentPaginationControlText = currentLicensePg.getHeaderPaginationControlText();
		verifyCurrentLicencePagination();
		
		// Check previous link on License Details page from current license list page
		verifyPrevLinkOnLicDetailsPg(activePriNum1, true);
		currentLicensePg.OperatePaging(true);
		verifyPrevLinkOnLicDetailsPg(activePriNum2, true);
		
		//Check point 3: verify pagination controls from expired licences list page
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		initialExpiredPaginationControlText = expiredLicensesListPg.getFooterPaginationControlText();
		verifyExpiredLicencePagination();
		
		// Check previous link on License Details page from expired license list page
		verifyPrevLinkOnLicDetailsPg(revokedPriNum1, false);
		expiredLicensesListPg.OperatePaging(true);
		verifyPrevLinkOnLicDetailsPg(revokedPriNum2, false);
		hf.signOut();
		
		//Invalid customer licenses in LM
		new com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, searchLicYear, searchStatus});
	}

	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		//Customer parameters
		cus.email = "hfsk_00014@webhftest.com";
		cus.dateOfBirth = "2000-01-16";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4133";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		// Customer info for removing suspensions
		cusNew.dateOfBirth = cus.dateOfBirth;
		cusNew.identifier.identifierType = IDENT_TYPE_HAL;
		cus.identifier.identifierNum = lm.getCustomerNumByEmail(cus.email, schema);

		//Privileges parameters
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.purchasingName = "SKA-HFSK License001";
		privilege2 = new PrivilegeInfo();
		privilege2.licenseYear = privilege.licenseYear;
		privilege2.purchasingName = "SKB-HFSK License002";	
		privilege3 = new PrivilegeInfo();
		privilege3.licenseYear = privilege.licenseYear;
		privilege3.purchasingName = "SKC-HFSK License003";
		privilege4 = new PrivilegeInfo();
		privilege4.licenseYear = privilege.licenseYear;
		privilege4.purchasingName = "SKD-HFSK License004";
		privilege5 = new PrivilegeInfo();
		privilege5.licenseYear = privilege.licenseYear;
		privilege5.purchasingName = "SKE-HFSK License005";
		privilege6 = new PrivilegeInfo();
		privilege6.licenseYear = privilege.licenseYear;
		privilege6.purchasingName = "SKF-HFSK License006";
		privilege7 = new PrivilegeInfo();
		privilege7.licenseYear = privilege.licenseYear;
		privilege7.purchasingName = "SKG-HFSK License007";
		privilege8 = new PrivilegeInfo();
		privilege8.licenseYear = privilege.licenseYear;
		privilege8.purchasingName = "SKH-HFSK License008";
		privilege9 = new PrivilegeInfo();
		privilege9.licenseYear = privilege.licenseYear;
		privilege9.purchasingName = "SKI-HFSK License009";
		privilege10 = new PrivilegeInfo();
		privilege10.licenseYear = privilege.licenseYear;
		privilege10.purchasingName = "SKJ-HFSK License010";
		privilege11 = new PrivilegeInfo();
		privilege11.licenseYear = privilege.licenseYear;
		privilege11.purchasingName = "SKK-HFSK License011";

		privileges = new PrivilegeInfo[]{privilege,privilege2,privilege3,privilege4,privilege5,privilege6,privilege7,privilege8,privilege9,privilege10,privilege11};

		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		searchLicYear = String.valueOf(DateFunctions.getCurrentYear());
		searchStatus = new String[]{"Active", "Revoked"};
		pay.payType = Payment.PAY_DEF_CASH;
		pageTitle = "Licences ?Summary of your hunting and and angling licences \\(grouped by year and ordered by name\\)\\. Switch between.*";
	}

	/**
	 * Verify current licence pagination
	 */
	private void verifyCurrentLicencePagination(){
		boolean result = true;

		//Total items count result in current first page
		String expectedValue = currentLicensePg.generateItemsCountResult(privileges.length, 1);
		String actualValue = currentLicensePg.getHeaderItemsCountResult();
		result &= MiscFunctions.compareResult("Total Items count result in current first page", expectedValue, actualValue);

		//In first page, previous link is not available, but next link is available
		result &= MiscFunctions.compareResult("Previous link is not available", currentLicensePg.isHeaderPreviousAvailable(), false);
		result &= MiscFunctions.compareResult("Next link is available", currentLicensePg.isHeaderNextAvailable(), true);

		//Go to next page to check total items count result is updating
		currentLicensePg.OperatePaging(true);
		expectedValue = currentLicensePg.generateItemsCountResult(privileges.length, 2);
		actualValue = currentLicensePg.getHeaderItemsCountResult();
		result &= MiscFunctions.compareResult("Total Items count result in current second page", expectedValue, actualValue);

		//In last page, previous link is available, but next link is not available
		result &= MiscFunctions.compareResult("Previous link is available", currentLicensePg.isHeaderPreviousAvailable(), true);
		result &= MiscFunctions.compareResult("Next link is not available", currentLicensePg.isHeaderNextAvailable(), false);

		//Go to expired page, then go back to current page to verify initial pagination
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		hf.gotoCurrentLicensesListPgFromExpiredLicencesListPg();
		result &= MiscFunctions.compareResult("Initial current pagination", initialCurrentPaginationControlText, currentLicensePg.getHeaderPaginationControlText());

		if(!result){
			throw new ErrorOnPageException("Failed to verify all check points in current licence list page.");
		}
		logger.info("Successfully verify all check points in current list page.");
	}

	/**
	 * Verify expired licence pagination
	 */
	private void verifyExpiredLicencePagination(){
		boolean result = true;

		//Total items count result in current first page
		String expectedValue = expiredLicensesListPg.generateItemsCountResult(privileges.length, 1);
		String actualValue = expiredLicensesListPg.getFooterItemsCountResult();
		result &= MiscFunctions.compareResult("Total Items count result in expired first page", expectedValue, actualValue);

		//In first page, previous link is not available, but next link is available
		result &= MiscFunctions.compareResult("Previous link is not available", expiredLicensesListPg.isHeaderPreviousAvailable(), false);
		result &= MiscFunctions.compareResult("Next link is available", expiredLicensesListPg.isHeaderNextAvailable(), true);

		//Go to next page to check total items count result is updating
		expiredLicensesListPg.OperatePaging(true);
		expectedValue = expiredLicensesListPg.generateItemsCountResult(privileges.length, 2);
		actualValue = expiredLicensesListPg.getHeaderItemsCountResult();
		result &= MiscFunctions.compareResult("Total Items count result in expired second page", expectedValue, actualValue);

		//In last page, previous link is available, but next link is not available
		result &= MiscFunctions.compareResult("Previous link is available", expiredLicensesListPg.isHeaderPreviousAvailable(), true);
		result &= MiscFunctions.compareResult("Next link is not available", expiredLicensesListPg.isHeaderNextAvailable(), false);

		//Go to current page, then go back to expired page to verify initial pagination
		hf.gotoCurrentLicensesListPgFromExpiredLicencesListPg();
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		result &= MiscFunctions.compareResult("Initial expired pagination", initialExpiredPaginationControlText, expiredLicensesListPg.getFooterPaginationControlText());

		if(!result){
			throw new ErrorOnPageException("Failed to verify all check points in expired licence list page.");
		}
		logger.info("Successfully verify all check points in expired list page.");
	}
	
	/** Verify Previous link on license details page from current list page */
	private void verifyPrevLinkOnLicDetailsPg(String priNum, boolean fromCurrentList) {
		String oriPageInfo, pageInfo;
		String msg = fromCurrentList ? "current list" : "expired list";
		if (fromCurrentList) {
			oriPageInfo = currentLicensePg.getHeaderItemsCountResult();
			hf.gotoLicDetailsPgFromCurtLicListPg(priNum);
		} else {
			oriPageInfo = expiredLicensesListPg.getHeaderItemsCountResult();
			hf.gotoLicDetailsPgFromExpiredLicListPg(priNum);
		}
		hf.gotoPreviousPgFromLicDetailsPg(true);
		
		if (fromCurrentList) {
			pageInfo = currentLicensePg.getHeaderItemsCountResult();
		} else {
			pageInfo = expiredLicensesListPg.getHeaderItemsCountResult();
		}
		
		if (!oriPageInfo.equals(pageInfo)) {
			throw new ErrorOnPageException("Previous link on License details page from " + msg + " works wrongly!", oriPageInfo, pageInfo);
		}
		logger.info("---Verify previous link on license details page from " + msg + " works correctly!");
	}
}
