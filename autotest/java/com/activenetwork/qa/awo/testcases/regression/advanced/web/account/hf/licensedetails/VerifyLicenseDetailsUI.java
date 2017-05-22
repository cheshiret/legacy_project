package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licensedetails;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify License Details page UI without harvest reports section and without Replace button
 * @Preconditions:
 * 1. Make sure the privilege "STE - HFSK SortLicense04" exists and assigned to Web (location class= 14-Internet).
 * 2. Make sure the privilege has not harvest report setup.
 * 3. Make sure the privilege has not replacement price setup.
 * 4. Make sure the privilege has sub category "Annual"
 * @SPEC:
 * 	License Details page UI [TC:046334]
 * Order History section - single order [TC:046893]
 * Harvest Reports section - No havest report set up [TC:046894]
 * License Detail section - 'Replace Lost License' button [TC:046892]
 * @Task#: Auto-1718, Auto-1719
 * 
 * @author Lesley Wang
 * @Date  Jun 14, 2013
 */
public class VerifyLicenseDetailsUI extends HFSKWebTestCase {
	private String timeZoneCode, pageTitle, licDetailsTitle, ordDetailsTitle;
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private OrderInfo order = new OrderInfo();
	
	@Override
	public void execute() {
		// make a privilege order
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		order.orderNum = hf.checkOutCart(pay);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, order.orderNum);
		
		// View License Details page
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		this.verifyLicDetailsUI();
		if (!this.isValidDatesHide(url)) {
			this.verifyLicValidDates();
		}
		
		// Verify Print This Page button
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		licDetailsPg.printThisPage(true);
		
		// Verify Previous links
		hf.gotoPreviousPgFromLicDetailsPg(true);
		hf.gotoLicDetailsPgFromCurtLicListPg(privilege.privilegeId);
		hf.gotoPreviousPgFromLicDetailsPg(false);
		hf.gotoLicDetailsPgFromCurtLicListPg(privilege.privilegeId);
				
		hf.signOut();
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "viewlicensedetails@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "LicDet00";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";
		timeZoneCode = DataBaseFunctions.getTimeZoneString(schema);

		// Order Info
		order.orderDate = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		order.orderType = "Purchase";
		
		// Privilege Info
		privilege.name = "HFSK SortLicense04";
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		privilege.validFromDate = order.orderDate;
		privilege.validToDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(order.orderDate, 364) + " 11:59 PM", "E MMM dd yyyy");
		privilege.displaySubCategory = "Annual";
		
		pageTitle = "Licence Details\\s*Details of the licence. Print this page for your records. Please note that a printout of this page is not your licen(s|c)e and is for your records only.";
		licDetailsTitle = "Licence Details";
		ordDetailsTitle = "Order Details";
		
		//Sara[10/49/2013], HFSK, AMS
		pay.payType = Payment.PAY_VISA;
		pay.creditCardNumber = "4112344112344113"; 
	}
	
	private void verifyLicDetailsUI() {
		boolean result = true;
		result &= MiscFunctions.matchString("License Details page title and header text", licDetailsPg.getPageTitleAndCaption(), pageTitle);
		
		// check license details section
		result &= MiscFunctions.compareString("License Details section title", licDetailsTitle, licDetailsPg.getLicDetailsTitle());
		privilege.validFromDate +=  " 12:00 AM";
		result &= licDetailsPg.compareLicDetails(privilege, timeZoneCode);
		
		// check replace lost license button not shown
		result &= MiscFunctions.compareResult("Display of Replace Lost License button", false, licDetailsPg.isReplaceLostLicBtnExist());
		
		// check order history section
		result &= MiscFunctions.compareString("Order Details section title", ordDetailsTitle, licDetailsPg.getOrdDetailsTitle());
		result &= licDetailsPg.compareOrdDetails(order);
		
		// check harvest report section not shown
		result &= MiscFunctions.compareResult("Display of Harvest Reports section", false, licDetailsPg.isHarvestReportsSecExist());
		if (!result) {
			throw new ErrorOnPageException("License Details UI is wrong! Check logger error!");
		}
		logger.info("---Verify License Details UI correctly!");
	}

	/** verify valid dates are displayed as the same as the one in License order details and License history page */
	private void verifyLicValidDates() {
		String datesOnLicDetails = licDetailsPg.getLicenseValidDates();
		datesOnLicDetails = datesOnLicDetails.replace(timeZoneCode, StringUtil.EMPTY).trim();
		datesOnLicDetails = datesOnLicDetails.replaceAll(" ", StringUtil.EMPTY);
		
		hf.gotoOrderDetailsPgFromLicDetailsPg(order.orderNum);
		String datesOnOrdDetails = HFOrderDetailsPage.getInstance().getValidDates();
		datesOnOrdDetails = datesOnOrdDetails.replaceAll(" ", StringUtil.EMPTY);
		
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		String datesOnOrdHist = HFOrderHistoryPage.getInstance().getPriValidDates(order.orderNum, privilege.privilegeId);
		datesOnOrdHist = datesOnOrdHist.replaceAll(" ", StringUtil.EMPTY);
		
		if (!datesOnLicDetails.equals(datesOnOrdHist) || !datesOnLicDetails.equals(datesOnOrdDetails)) {
			throw new ErrorOnPageException("valid dates on different pages are wrong! " +
					"License Details: " + datesOnLicDetails + "; Order Details: " + datesOnOrdDetails + "; Order History: " + datesOnOrdHist);
		}
		logger.info("---Valid dates are the same on License details page, Order Details page, and Order History page!");
	}
}
