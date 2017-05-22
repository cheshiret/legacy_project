/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.voucher.search;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.pages.orms.financeManager.voucher.FinMgrVoucherProgramSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * @Description:This test case contains the scenario: 1.verify search criteria
 *                   on the voucher program search page 2.verify voucher program
 *                   list information on the voucher program search page
 * @Preconditions:
 * @SPEC:Search Voucher Program.UCS,Voucher Programs.UIS
 * @Task#:AUTO-762
 * 
 * @author szhou
 * @Date Dec 21, 2011
 */
public class VerifySearchVoucherProgramUI extends FinanceManagerTestCase {
	private VoucherProgram colName = new VoucherProgram();
	private List<String> searchCriteriaValue = null;
	private List<String> programTypesValue = null;
	private List<String> statusValue = null;
	private List<String> productCate = null;
	private List<String> colValue = null;
	private List<String> dateValue = null;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		// go to voucher program page
		fnm.gotoVouchersPage();
		fnm.gotoVoucherProgramPage();

		// Verify Voucher Program Search Criteria
		this.verifyVoucherProgramSearchCriteria();
		// Verify Voucher Program List Display Information
		this.verifyVoucherProgramListInfo(colName);

		fnm.logoutFinanceManager();
	}

	private void verifyVoucherProgramSearchCriteria() {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();
		List<String> value = null;

		// verify search criteria value(Voucher Program ID,Voucher Program
		// Name,Locations for Creation,Locations for Use,Account)
		value = searchPg.getSearchCriteriaValues();
		this.compareValues(searchCriteriaValue, value, "Search Criteria");
		// verify Program Types
		value = searchPg.getProgramTypeValues();
		this.compareValues(programTypesValue, value, "Program Types");
		// verify Statuses
		value = searchPg.getShowValues();
		this.compareValues(statusValue, value, "Status");
		// verify Product Categories for Creation
		value = searchPg.getProductCategooryForCreationValues();
		this.compareValues(productCate, value,
				"Product Category for Creation");
		// verify Emergency Cancellation
		value = searchPg.getEmergencyCancellationValues();
		this.compareValues(colValue, value, "Emergency Cancellation");
		// verify Redirection to Refund
		value = searchPg.getRedirectToRefundValues();
		this.compareValues(colValue, value, "Redirection to Refund");
		// verify Product Categories for Use
		value = searchPg.getProductCategooryForUseValues();
		this.compareValues(productCate, value, "Product Category for Use");
		// verify Same Billing Customer
		value = searchPg.getBillingCustomerValues();
		this.compareValues(colValue, value, "Same Billing Customer");
		// verify date
		value = searchPg.getDateValues();
		this.compareValues(dateValue, value, "Date");
	}

	private void compareValues(List<String> expectValue,
			List<String> actuallyValue, String type) {
		if (actuallyValue == null
				|| (actuallyValue.size() - 1) != expectValue.size()) {
			throw new ErrorOnDataException("'" + type
					+ "'dropdown list options size is not correct.");
		}
		for (String value : expectValue) {
			if (!actuallyValue.contains(value)) {
				throw new ErrorOnDataException("'" + type
						+ "'column don't contain rigt value:" + value + ".");
			}
		}

	}

	private void verifyVoucherProgramListInfo(VoucherProgram colName) {
		FinMgrVoucherProgramSearchPage searchPg = FinMgrVoucherProgramSearchPage
				.getInstance();

		VoucherProgram actually = searchPg.getVoucherProgramList(0);
		if (!colName.programId.equalsIgnoreCase(actually.programId)) {
			throw new ItemNotFoundException(
					"'Program ID' column don't display on the Voucher Program List.");
		}
		if (!colName.status.equalsIgnoreCase(actually.status)) {
			throw new ItemNotFoundException(
					"'Active' column don't display on the Voucher Program List.");
		}
		if (!colName.programeType.equalsIgnoreCase(actually.programeType)) {
			throw new ItemNotFoundException(
					"'Programe Type' column don't display on the Voucher Program List.");
		}
		if (!colName.programeName.equalsIgnoreCase(actually.programeName)) {
			throw new ItemNotFoundException(
					"'Programe Name' column don't display on the Voucher Program List.");
		}
		if (!colName.lineOfBusiness.equalsIgnoreCase(actually.lineOfBusiness)) {
			throw new ItemNotFoundException(
					"'Line Of Business' column don't display on the Voucher Program List.");
		}
		if (!colName.startDate.equalsIgnoreCase(actually.startDate)) {
			throw new ItemNotFoundException(
					"'Effective Start Date' column don't display on the Voucher Program List.");
		}
		if (!colName.endDate.equalsIgnoreCase(actually.endDate)) {
			throw new ItemNotFoundException(
					"'Effective End Date' column don't display on the Voucher Program List.");
		}
		if (!colName.createLocation.equalsIgnoreCase(actually.createLocation)) {
			throw new ItemNotFoundException(
					"'Location for Creation' column don't display on the Voucher Program List.");
		}
		if (!colName.productCategoryForCreation
				.equalsIgnoreCase(actually.productCategoryForCreation)) {
			throw new ItemNotFoundException(
					"'Product Category for Creation' column don't display on the Voucher Program List.");
		}
		if (!colName.emergencyCancellation
				.equalsIgnoreCase(actually.emergencyCancellation)) {
			throw new ItemNotFoundException(
					"'Emergency Cancellation' column don't display on the Voucher Program List.");
		}
		if (!colName.redirectionToRefund
				.equalsIgnoreCase(actually.redirectionToRefund)) {
			throw new ItemNotFoundException(
					"'Redirection To Refund' column don't display on the Voucher Program List.");
		}
		if (!colName.redirectionToRefundWeb
				.equalsIgnoreCase(actually.redirectionToRefundWeb)) {
			throw new ItemNotFoundException(
					"'Redirection To Refund(Web)' column don't display on the Voucher Program List.");
		}
		if (!colName.locationForUse.equalsIgnoreCase(actually.locationForUse)) {
			throw new ItemNotFoundException(
					"'Location For Use' column don't display on the Voucher Program List.");
		}
		if (!colName.productCategoryForUse
				.equalsIgnoreCase(actually.productCategoryForUse)) {
			throw new ItemNotFoundException(
					"'Product Category For Use' column don't display on the Voucher Program List.");
		}
		if (!colName.sameBillCustomer
				.equalsIgnoreCase(actually.sameBillCustomer)) {
			throw new ItemNotFoundException(
					"'Same Billing Customer' column don't display on the Voucher Program List.");
		}
		if (!colName.expiry.equalsIgnoreCase(actually.expiry)) {
			throw new ItemNotFoundException(
					"'Expiry' column don't display on the Voucher Program List.");
		}
		if (!colName.expiryPeriod.equalsIgnoreCase(actually.expiryPeriod)) {
			throw new ItemNotFoundException(
					"'Expiry Period' column don't display on the Voucher Program List.");
		}
		if (!colName.expiryPeriodCalculationMethod
				.equalsIgnoreCase(actually.expiryPeriodCalculationMethod)) {
			throw new ItemNotFoundException(
					"'Expiry Period Calculation Method' column don't display on the Voucher Program List.");
		}
		if (!colName.account.equalsIgnoreCase(actually.account)) {
			throw new ItemNotFoundException(
					"'Account' column don't display on the Voucher Program List.");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "VA Contract";
		login.location = "Administrator/Virginia Department of Conservation and Recreation";

		// initialize Voucher program info
		colName.programId = "Voucher Program ID";
		colName.status = "Active";
		colName.programeType = "Program Type";
		colName.programeName = "Voucher Program Name";
		colName.lineOfBusiness = "Line of Business";
		colName.startDate = "Effective Start Date";
		colName.endDate = "Effective End Date";
		colName.createLocation = "Location for Creation";
		colName.productCategoryForCreation = "Product Category for Creation";
		colName.emergencyCancellation = "Emergency Cancellation";
		colName.redirectionToRefund = "Redirection to Refund";
		colName.redirectionToRefundWeb = "Redirection to Refund (Web)";
		colName.locationForUse = "Location for Use";
		colName.productCategoryForUse = "Product Category for Use";
		colName.sameBillCustomer = "Same Billing Customer";
		colName.expiry = "Expiry";
		colName.expiryPeriod = "Expiry Period";
		colName.expiryPeriodCalculationMethod = "Expiry Period Calculation Method";
		colName.inactivityFeesIndicator = "Inactivity Fees Indicator";
		colName.account = "Account";

		searchCriteriaValue = new ArrayList<String>();
		searchCriteriaValue.add("Voucher Program ID");
		searchCriteriaValue.add("Voucher Program Name");
		searchCriteriaValue.add("Location for Creation");
		searchCriteriaValue.add("Location for Use as Payment");
		searchCriteriaValue.add("Account");

		programTypesValue = new ArrayList<String>();
		programTypesValue.add("Refund");
		programTypesValue.add("Gift Card");

		statusValue = new ArrayList<String>();
		statusValue.add("Active");
		statusValue.add("Inactive");

		productCate = new ArrayList<String>();
		productCate.add("All");
		productCate.add("Activity");
		productCate.add("Event");
		productCate.add("Facility");
		productCate.add("GiftCard");
		productCate.add("List");
		productCate.add("Lottery");
		productCate.add("Membership");
		productCate.add("POS");
		productCate.add("Permit");
		productCate.add("Privilege");
		productCate.add("Service");
		productCate.add("Site");
		productCate.add("Slip");
		productCate.add("Supply");
		productCate.add("Ticket");
		productCate.add("VehicleRTI");

		colValue = new ArrayList<String>();
		colValue.add("Yes");
		colValue.add("No");

		dateValue = new ArrayList<String>();
		dateValue.add("Effective Start Date");
	}

}
