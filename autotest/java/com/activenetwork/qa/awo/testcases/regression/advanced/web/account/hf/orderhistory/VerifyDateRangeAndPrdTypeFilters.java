/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.orderhistory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify Date Range and Product Type Filter list on Order History page.
 * The options in the list are setup in /opt/sites/qa1/uwppl/docs/properties/config/member/HFMemberResources.properties
 * @Preconditions:
 * @SPEC:
 * Order History page - UI [TC:050347] step 5
 * Order History page - blank order list section [TC:055629] step 5 ~ 10
 * Order History - Time Period Pull-down [TC:050353]
 * @Task#: Auto-1722
 * 
 * @author Lesley Wang
 * @Date  May 27, 2013
 */
public class VerifyDateRangeAndPrdTypeFilters extends HFSKWebTestCase {
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private String headerText, msgWithoutOrders, label, prdTypeFilter_All, prdTypeFilter_Lic, currentYear;
	private List<String> options;
	@Override
	public void execute() {
		// Clean up
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYear, searchStatus, privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
		
		// Make a privilege order to show Date Range Filter, verify Date Range filter default values
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		hf.gotoOrdHistPgFromOrdConfirmPg();
		
		this.verifyDateRangeFilterDefaultValue();
		this.verifyPrdTypeFilterValue();
		this.verifyDateRangeAndPrdTypeFilterResult(true, true, true, false, prdTypeFilter_Lic);
		
		// Change the order date to a date as 31 days before current date and verify		
		hf.changeOrdDate(schema, cus.orderNum, DateFunctions.getDateAfterToday(-31));
		this.verifyDateRangeAndPrdTypeFilterResult(false, true, true, false, prdTypeFilter_All);
		
		// Change the order date to a date before 6 months and verify
		String dateBef60Mon = DateFunctions.getDateAfterToday(-190);
		boolean isCurYear = DateFunctions.getDateComponents(dateBef60Mon)[2] == Integer.valueOf(currentYear);
		hf.changeOrdDate(schema, cus.orderNum, dateBef60Mon);
		this.verifyDateRangeAndPrdTypeFilterResult(false, false, isCurYear, !isCurYear, prdTypeFilter_Lic);
		
		// Change the order date to a date before current year and verify	
		hf.changeOrdDate(schema, cus.orderNum, DateFunctions.getDateAfterToday(-370));
		this.verifyDateRangeAndPrdTypeFilterResult(false, false, false, true, prdTypeFilter_All);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistoryui02@test.com"; // d_web_hf_signupaccount, id=380
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP000";
		cus.identifier.state = "Ontario";
		searchLicYear = String.valueOf(DateFunctions.getCurrentYear());
		searchStatus = new String[]{"Active", "Revoked"};
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";
			
		// Privilege Info
		privilege.name = "HFSK License001";
		
		// Page info
		label = "View:";
		headerText = "History of all your purchases. Change the time period by using the pull-down menu below.";
		msgWithoutOrders = "There are no orders. Select a different time period above.";
		currentYear = Integer.toString((DateFunctions.getCurrentYear()));
		options = new ArrayList<String>();
		options.add("Last 30 days");
		options.add("Past 6 months");
		options.add(currentYear);
		options.add("Before " + currentYear);
		prdTypeFilter_All = "All";
		prdTypeFilter_Lic = "Licence";
	}

	private void verifyDateRangeFilterDefaultValue() {
		boolean result = true; 
		// Date Range label,  default option, and options in the list; 
		result &= MiscFunctions.compareString("Date Range Filter list label", label, ordHistPg.getDateRangeFilterLabel());
		result &= MiscFunctions.compareString("Default selected option", options.get(1), ordHistPg.getSelectedDateRange());
		List<String> actOpts = ordHistPg.getDateRangeFilterOpts();
		if (options.size() == actOpts.size() && actOpts.equals(options)) {
			result &= true;
			logger.info("Date Range options are correct as " + actOpts.toString());
		} else {
			result &= false;
			logger.error("Date Range options are wrong! Expect: " + options.toString() + "; Actual: " + actOpts.toString());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Date Range Filter Default Vlaue is wrong! Check logger error!");
		}
		logger.info("---Verify Date Range Filter Default Value correctly!");
	}
	
	private void verifyPrdTypeFilterValue() {
		boolean result = true; 
		result &= MiscFunctions.compareString("default product type filter", prdTypeFilter_All, ordHistPg.getSelectedPrdTypeFilter());
		String licNum = ordHistPg.getLicNumInPrdTypeFilter();
		ordHistPg.clickLicPrdTypeFilter();
		ordHistPg.waitLoading();
		String resultNum = ordHistPg.getNumOfAllResults();
		result &= MiscFunctions.compareString("Licence Type Filter Number", licNum, resultNum);
		result &= MiscFunctions.compareString("selected product type filter", prdTypeFilter_Lic, ordHistPg.getSelectedPrdTypeFilter());
		result &= MiscFunctions.compareString("All product type filter number", licNum, ordHistPg.getAllNumInPrdTypeFilter());
		
		if (!result) {
			throw new ErrorOnPageException("Product Type Filter Vlaue is wrong! Check logger error!");
		}
		logger.info("---Verify Prodcut Type Filter Default Value correctly!");
	}
	
	private void verifyDateRangeAndPrdTypeFilterResult(boolean isOrdShownForPast30D, boolean isOrdShownForLast6M, 
			boolean isOrdShownForCurYear, boolean isOrdShownBefCutYear, String prdType) {
		this.verifyDateRangeAndPrdTypeFilterResult(options.get(0), prdType, isOrdShownForPast30D);
		this.verifyDateRangeAndPrdTypeFilterResult(options.get(1), prdType, isOrdShownForLast6M);
		this.verifyDateRangeAndPrdTypeFilterResult(options.get(2), prdType, isOrdShownForCurYear);
		this.verifyDateRangeAndPrdTypeFilterResult(options.get(3), prdType, isOrdShownBefCutYear);
	}
	
	private void verifyDateRangeAndPrdTypeFilterResult(String opt, String prdType, boolean isOrdShown) {
		boolean result = true;
		
		ordHistPg.selectDateRangeFilter(opt);
		ordHistPg.waitLoading();
		// verify page after filter by date range
		result &= MiscFunctions.containString("header text", ordHistPg.getPageTitleAndCaption(), headerText);
		result &= MiscFunctions.compareResult("order list shown", isOrdShown, StringUtil.notEmpty(ordHistPg.getOrderListText()));
		result &= MiscFunctions.compareResult("no result message shown", !isOrdShown, ordHistPg.isNoResultsMsgExist());
		result &= MiscFunctions.compareResult("product filter shown", isOrdShown, ordHistPg.isPrdTypeFilterListExist());
		result &= MiscFunctions.compareResult("pagination control shown", isOrdShown, ordHistPg.isPageControlExist());
		if (!isOrdShown) {
			result &= MiscFunctions.compareString("message without order", msgWithoutOrders, ordHistPg.getNoResultsMsg());
		} else {
			// verify selected product filter when there are orders
			result &= MiscFunctions.compareString("default product type filter", prdTypeFilter_All, ordHistPg.getSelectedPrdTypeFilter());
			if (prdType.equalsIgnoreCase(prdTypeFilter_Lic)) {
				ordHistPg.clickLicPrdTypeFilter();
				ordHistPg.waitLoading();
			}
			hf.gotoLicDetailsPgFromOrdHistPg(privilege.privilegeId);
			hf.gotoPreviousPgFromLicDetailsPg(true);
			result &= MiscFunctions.compareString("Selected Date Range option", opt, ordHistPg.getSelectedDateRange());
			result &= MiscFunctions.compareString("default product type filter", prdType, ordHistPg.getSelectedPrdTypeFilter());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Filter Result is wrong for " + opt + " and " + prdType);
		}
		logger.info("---Verify Filter Result correctly for " + opt + " and " + prdType);
	}
}
