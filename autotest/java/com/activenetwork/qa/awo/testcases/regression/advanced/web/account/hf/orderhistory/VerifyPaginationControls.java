/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.orderhistory;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify pagination control on order history page. 
 * When there are more than 10 orders, the page picker and previous/next links are shown.
 * @Preconditions:
 * @SPEC:
 * Order History page - UI [TC:050347] step 8
 * 	Pagination Controls on Order History page [TC:058276]
 * Order History - Interaction between Time period and type filter/ order sorting [TC:050508]
 * @Task#: Auto-1722
 * 
 * @author Lesley Wang
 * @Date  May 27, 2013
 */
public class VerifyPaginationControls extends HFSKWebTestCase {
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private String resultInfo, lastResultInfo, dateRangeOpt;
	@Override
	public void execute() {
		// Make sure there are only 10 privilege orders in LM
		this.prepare10Orders();
		
		// Verify the pagination controls and order sorting when there is single page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		this.verifyPaginationControl(false, true, true, resultInfo);
		this.verifyOrderSorting(false);
		
		// Make 1 order to show in multiple pages mode
		cus.residencyStatus = RESID_STATUS_SK;
		pay.payType = "Visa";
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.checkOutCart(pay);
		
		// Verify when there are multiple pages
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		resultInfo = "1-10 of 11";
		this.verifyPaginationControl(true, true, false, resultInfo);
		this.verifyOrderSorting(true);
		
		ordHistPg.gotoNextPage(false);
		this.verifyPaginationControl(true, false, true, lastResultInfo);
		ordHistPg.gotoPreviousPage(false);
		this.verifyPaginationControl(true, true, false, resultInfo);
		
		this.verifyAfterSelectPrdTypeFilterInLastPg();
		this.verifyAfterSelectDateRangeFilterInLastPg();
		this.verifyAfterViewLicDetailsInLastPg();
		this.verifyAfterClickOrdHistInLastPg();
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistoryui03@test.com"; // d_web_hf_signupaccount, id=390
		cus.password = "asdfasdf";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "0101";
		cus.residencyStatus = RESID_STATUS_SK + " - " + IDENT_TYPE_RCMP;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP001";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
			
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.purchasingName = "SKA-" + privilege.name;
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		
		pay.payType = "Cash*";
		
		// Page info
		resultInfo = "1-10 of 10";
		lastResultInfo = "11-11 of 11";
		dateRangeOpt = Integer.toString(DateFunctions.getCurrentYear());//"Last 30 days";
		
		searchStatus = new String[]{"Active", "Revoked"};
	}

	private void prepare10Orders() {
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoCustomerPrivilegesPage();
		int num =  LicMgrCustomerPrivilegePage.getInstance().getAllPrivilegeIDList().size();
		if (num > 10) {
			// invalid orders
			num = num - 10;
			logger.info("Invalidate " + num + " orders in LM....");
			for (int i = 0; i < num; i++) {
				lm.gotoCustomerPrivilegesPage();
				lm.invalidateFirstPrivilegeFromCustomerPrivilegPage(searchStatus, privilege.operateReason, privilege.operateNote);
				lm.gotoCustomerDetailsFromPrivilegeDetails();
			}
		} else if (num < 10) {
			// make orders to 10
			num = 10 - num;
			logger.info("Make " + num + " orders in web....");
			for (int i = 0; i < num; i++) {
				lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege);
				lm.processOrderCart(pay);
			}
		}
		lm.logOutLicenseManager();
	}
	
	private void verifyPaginationControl(boolean isMulPages, boolean isFirstPg, boolean isLastPg, String resultInfo) {
		boolean result = true;
		String msg = isMulPages ? "Multiple Pages" : "Single Page";
		result &= MiscFunctions.containString("Pagination Result Info", ordHistPg.getPageControlText(), resultInfo);
		result &= MiscFunctions.compareResult("page picker shown", isMulPages, ordHistPg.isPagePickerExist());
		result &= MiscFunctions.compareResult("Previous link", !isFirstPg, ordHistPg.isPagePreviousLinkExist());
		result &= MiscFunctions.compareResult("Next link", !isLastPg, ordHistPg.isPageNextLinkExist());
		result &= MiscFunctions.compareResult("footer page picker shown", isMulPages, ordHistPg.isPagePickerExist());
		result &= MiscFunctions.compareResult("footer Previous link", !isFirstPg, ordHistPg.isPagePreviousLinkExist());
		result &= MiscFunctions.compareResult("footer Next link", !isLastPg, ordHistPg.isPageNextLinkExist());
		
		if (!result) {
			throw new ErrorOnPageException("Pagination Result info is wrong in " + msg + " mode.");
		}
		logger.info("---Successfully verify Pagination Result Info!");
	}
	
	// verify orders sort by descending of order date
	private void verifyOrderSorting(boolean isMulPage) {
		List<String> dates = ordHistPg.getAllOrderDate();
		if (isMulPage) {
			ordHistPg.gotoNextPage(true);
			dates.addAll(ordHistPg.getAllOrderDate());
			ordHistPg.gotoPreviousPage(true);	
		}
		for (int i = 0; i < dates.size() - 1; i++) {
			String d1 = dates.get(i);
			String d2 = dates.get(i + 1);
			if (DateFunctions.compareDates(d1, d2) == -1) { // can equal or greater, not less
				throw new ErrorOnPageException("Order should be sorted by descending of order date! " + d1 + " should not before " + d2);
			}
		}
		logger.info("---Successfully verify order sort by descending of order date");
	}
	
	private void verifyPageContent(String expContent) {
		String actContent = ordHistPg.getOrderListText();
		if (!actContent.equals(expContent)) {
			throw new ErrorOnPageException("First Page Content is wrong!", expContent, actContent);
		}
		logger.info("---Successfully verify page content!");
	}
	
	// Verify first page will be shown after select another product type filter when view the last page
	private void verifyAfterSelectPrdTypeFilterInLastPg() {
		ordHistPg.filterByLicPrdType();
		String firstPgContent = ordHistPg.getOrderListText();
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.gotoPageFromPagePicker("2", true);
		ordHistPg.filterByLicPrdType();
		this.verifyPaginationControl(true, true, false, resultInfo);
		this.verifyPageContent(firstPgContent);
		logger.info("---Successfully verify first page shown after select product type filter when view the last page!");		
	}
	
	// Verify first page will be shown after select another date range filter in last page
	private void verifyAfterSelectDateRangeFilterInLastPg() {
		ordHistPg.filterByDateRange(dateRangeOpt);
		String firstPgContent = ordHistPg.getOrderListText();
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.gotoPageFromPagePicker("2", false);
		ordHistPg.filterByDateRange(dateRangeOpt);
		this.verifyPaginationControl(true, true, false, resultInfo);
		this.verifyPageContent(firstPgContent);
		logger.info("---Successfully verify first page shown after select date range filter when view the last page!");		
	}	
	
	//verify first page shown after view license details page and go back when view the last page
	private void verifyAfterViewLicDetailsInLastPg() {
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.gotoNextPage(true);
		String firstPgContent = ordHistPg.getOrderListText();
		hf.gotoLicDetailsPgFromOrdHistPg(StringUtil.EMPTY);
		hf.gotoPreviousPgFromLicDetailsPg(true);
		this.verifyPaginationControl(true, false, true, lastResultInfo);
		this.verifyPageContent(firstPgContent);
		logger.info("---Successfully verify first page shown after view license details page and go back when view the last page!");		
	}	
	
	// Verify first page will be shown after click Order History link in last page
	private void verifyAfterClickOrdHistInLastPg() {
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		String firstPgContent = ordHistPg.getOrderListText();
		ordHistPg.gotoNextPage(false);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		this.verifyPaginationControl(true, true, false, resultInfo);
		this.verifyPageContent(firstPgContent);		
		logger.info("---Successfully verify first page shown after click Order History when view the last page!");		
	}	
}
