/*
 * Created on Dec 9, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.voucher;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VoucherProgram;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrVoucherProgramSearchPage extends FinanceManagerPage {

	/** A handle to the unique Singleton instance. */
	static private FinMgrVoucherProgramSearchPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrVoucherProgramSearchPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrVoucherProgramSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrVoucherProgramSearchPage();
		}

		return _instance;
	}

	/** Check this page is exists */
	public boolean exists() {
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				rex);
	}

	/** Click Go Button */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/** Click Add New Button */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/** Click Activate Button */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/** Click Deactivate Button */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click First voucher Program */
	public void clickFirstProgram() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("\\d+", false));
	}

	/** Click Voucher Tab */
	public void clickVouchersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Vouchers");
	}

	/**
	 * Click program
	 * 
	 * @param programID
	 */
	public void clickProgram(String programID) {
		browser.clickGuiObject(".class", "Html.A", ".text", programID);
	}

	/**
	 * Select given program in check box
	 * 
	 * @param programId
	 */
	public void selectProgramCheckBox(String programId) {
		Property[] ps = new Property[3];
		ps[0] = new Property(".class", "Html.INPUT.checkbox");
		ps[1] = new Property(".value", programId);
		/*
		 * When you search voucher program by id, "value" property of search value input(type=text) is the programId,
		 * just the same as this checkbox, the XPath of the checkbox is like "/HTML/BODY/FORM/TABLE/TBODY/TR/TD/DIV/INPUT[string(@id)='VoucherProgramSearchRequest.searchValue']",
		 * type=checkbox is not added as a condition, so it will find the search value input, add below property to make it could find the right checkbox.  
		 */
		ps[2] = new Property(".name", new RegularExpression("^row_\\d+_checkbox$", false));
		browser.selectCheckBox(ps);
	}

	/**
	 * Select Search Type
	 * 
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.searchBy", searchType);
	}

	/**
	 * Input Search value
	 * 
	 * @param searchValue
	 */
	public void setSearchValue(String searchValue) {
		browser.setTextField(".id", "VoucherProgramSearchRequest.searchValue",
				searchValue);
	}

	/** Select Search Date Type */
	public void selectDateType(String dateType) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.searchByDate", dateType);
	}

	/**
	 * Input Start date
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		browser.setTextField(".id",
				"VoucherProgramSearchRequest.fromDate_ForDisplay", startDate);
	}

	/**
	 * Input End Date
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		browser.setTextField(".id",
				"VoucherProgramSearchRequest.toDate_ForDisplay", endDate);
	}

	/**
	 * Select show Status
	 * 
	 * @param status
	 */
	public void selectShowStatus(String status) {
		browser.selectDropdownList(".id", "VoucherProgramSearchRequest.status",
				status);
	}

	/**
	 * Select program Type
	 * 
	 * @param programType
	 */
	public void selectProgramType(String programType) {
		browser.selectDropdownList(".id", "VoucherProgramSearchRequest.type",
				programType);
	}

	/**
	 * Select product category for creation
	 * 
	 * @param prod
	 */
	public void selectProdCatForCreation(String prod) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.creationPrdCat", prod);
	}

	/**
	 * Select allow emergency cancellation
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectEmergencyCancellation(String str) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.emergencyCancellation", str);
	}

	/**
	 * Select allow redirect to refund or not
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectRedirectionToRefund(String str) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.redirectToRefund", str);
	}

	/**
	 * Select Product Category For use
	 * 
	 * @param prod
	 */
	public void selectProdCatForUse(String prod) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.usePrdCat", prod);
	}

	/**
	 * Select is same billing customer or not
	 * 
	 * @param str
	 *            yes/no
	 */
	public void selectSameBillingCustomer(String str) {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.sameBillingCustomer", str);
	}

	/**
	 * Change voucher program status such as activate or deactivate
	 * 
	 * @param vpId
	 *            voucher program id
	 * @param toStatus
	 *            yes/no
	 */
	public void changeVoucherProgramStatus(String vpId, String toStatus) {
		logger.info("Start to Change Program Status for id=" + vpId);
		searchById(vpId);
		selectProgramCheckBox(vpId);
		String action = "";
		if (toStatus.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) {
			action = "Activate";
			clickActivate();
		} else if (toStatus.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS)) {
			action = "Deactivate";
			clickDeactivate();
		}
		logger.info(action + "voucher program - " + vpId);
		waitLoading();
	}

	public void verifyStatus(String id, String status) {
		logger.info("Verify Voucher Program Status!");

		searchById(id);
		verifyProgramInfo("Active", status);

	}

	/**
	 * This method is used to set up search criteria
	 * 
	 * @param vp
	 */
	public void setUpSearchCriteria(VoucherProgram vp) {
		clearSearchCriteria();
		if (null != vp.searchType && !vp.searchType.equals("")) {
			selectSearchType(vp.searchType);
			setSearchValue(vp.searchValue);
		}

		if (null != vp.dateType && !"".equals(vp.dateType)) {
			selectDateType(vp.dateType);
			setStartDate(vp.startDate);
			setEndDate(vp.endDate);
		}
		if (null != vp.programeStatus && !vp.programeStatus.equals("")) {
			selectShowStatus(vp.programeStatus);
		}
		if (null != vp.programeType && !vp.programeType.equals("")) {
			selectProgramType(vp.programeType);
		}
		if (null != vp.productCategoryForCreation
				&& !vp.productCategoryForCreation.equals("")) {
			selectProdCatForCreation(vp.productCategoryForCreation);
		}
		if (null != vp.emergencyCancellation
				&& !vp.emergencyCancellation.equals("")) {
			selectEmergencyCancellation(vp.emergencyCancellation);
		}
		if (null != vp.redirectionToRefund
				&& !vp.redirectionToRefund.equals("")) {
			selectRedirectionToRefund(vp.redirectionToRefund);
		}
		if (null != vp.productCategoryForUse
				&& !vp.productCategoryForUse.equals("")) {
			selectProdCatForUse(vp.productCategoryForUse);
		}
		if (null != vp.sameBillCustomer && !vp.sameBillCustomer.equals("")) {
			selectSameBillingCustomer(vp.sameBillCustomer);
		}
	}

	/**
	 * This method is used to clear all search criteria
	 * 
	 */
	public void clearSearchCriteria() {
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.searchBy", 0);
		setSearchValue("");
		selectDateType("");
		setStartDate("");
		setEndDate("");
		browser.selectDropdownList(".id", "VoucherProgramSearchRequest.status",
				0);
		browser.selectDropdownList(".id", "VoucherProgramSearchRequest.type", 0);
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.creationPrdCat", 0);
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.emergencyCancellation", 0);
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.redirectToRefund", 0);
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.usePrdCat", 0);
		browser.selectDropdownList(".id",
				"VoucherProgramSearchRequest.sameBillingCustomer", 0);
	}

	/**
	 * Search by voucher program name
	 * 
	 * @param name
	 */
	public void searchByName(String name) {
		clearSearchCriteria();
		selectSearchType("Voucher Program Name");
		setSearchValue(name);
		clickGo();
		waitLoading();
	}

	public void searchByNameAndExpiry(String programName, String expiry) {
		selectSearchType("Voucher Program Name");
		setSearchValue(programName);
		this.selectExpiry(expiry);
		clickGo();
		waitLoading();
	}
	
	/**
	 * @param expiry
	 */
	private void selectExpiry(String expiry) {
		browser.selectDropdownList(".id", "VoucherProgramSearchRequest.expiry", expiry);
		
	}

	public void searchByNameAndStatus(String programName, String status) {
		selectSearchType("Voucher Program Name");
		setSearchValue(programName);
		this.selectShowStatus(status);
		clickGo();
		waitLoading();
	}

	public boolean checkVoucherProgramActive(String voucherProgramName) {
		this.searchByNameAndStatus(voucherProgramName,
				OrmsConstants.ACTIVE_STATUS);
		return this.getAllVoucherProgramId().size() > 0;
	}

	/**
	 * Search by voucher program id
	 * 
	 * @param id
	 */
	public void searchById(String id) {
		selectSearchType("Voucher Program ID");
		setSearchValue(id);
		clickGo();
		this.waitLoading();
	}

	/**
	 * select all voucher programs check boxes
	 */
	public void selectAllVoucherProgramCheckboxes() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void selectVoucherProgramCheckBox(String voucherProgramID) {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".value",
				voucherProgramID);
	}

	/**
	 * Search Program By Status
	 * 
	 * @param status
	 */
	public void searchByStatusAndProgramType(String status, String programType) {
		selectShowStatus(status);
		selectProgramType(programType);
		clickGo();
		waitLoading();
	}

	/**
	 * This method is used to check given date is in the effective date range
	 * 
	 * @param givenDate
	 * @return -2-given date before start date,2-given date after end
	 *         date,-1,1,0(in date range)
	 */
	public int verifyGivenDateInEffectiveDateRange(String givenDate) {
		int startDateNum = getColNum("Effective Start Date");
		int endDateNum = getColNum("Effective End Date");
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		int value = 0;
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			String fromDate = DateFunctions.formatDate(
					tableGrid.getCellValue(i, startDateNum).toString(),
					"M/d/yyyy");
			String endDate = DateFunctions.formatDate(
					tableGrid.getCellValue(i, endDateNum).toString(),
					"M/d/yyyy");
			value = DateFunctions.compareDates(fromDate, givenDate)
					+ DateFunctions.compareDates(endDate, givenDate);
		}
		Browser.unregister(objs);
		return value;
	}

	/**
	 * Verfiy Modified Voucher Program Correct
	 * 
	 * @param vp
	 *            Voucher Program
	 */
	public void verifyModification(VoucherProgram vp) {
		logger.info("Verify All Modification is saved correct!");

		if (null != vp.dateType && !"".equals(vp.dateType)) {
			verifyProgramInfo("Effective Start Date", vp.startDate);
			verifyProgramInfo("Effective End Date", vp.endDate);
		}
		if (null != vp.programeStatus && !vp.programeStatus.equals("")) {
			if (vp.programeStatus.equalsIgnoreCase("Active")) {
				verifyProgramInfo("Active", "Yes");
			} else {
				verifyProgramInfo("Active", "No");
			}
		}
		if (null != vp.programeType && !vp.programeType.equals("")) {
			verifyProgramInfo("Program Type", vp.programeType);
		}
		if (null != vp.productCategoryForCreation
				&& !vp.productCategoryForCreation.equals("")) {
			verifyProgramInfo("Product Category for Creation",
					vp.productCategoryForCreation);
		}
		if (null != vp.emergencyCancellation
				&& !vp.emergencyCancellation.equals("")) {
			verifyProgramInfo("Emergency Cancellation",
					vp.emergencyCancellation);
		}
		if (null != vp.redirectionToRefund
				&& !vp.redirectionToRefund.equals("")) {
			verifyProgramInfo("Redirection to Refund", vp.redirectionToRefund);
		}
		if (null != vp.productCategoryForUse
				&& !vp.productCategoryForUse.equals("")) {
			if (vp.productCategoryForUse.equalsIgnoreCase("All")) {
				verifyProgramInfo("Product Category for Use",
						vp.productCategoryForUse);
			} else {
				verifyProgramInfo("Product Category for Use", "Multi");
			}
		}
		if (null != vp.sameBillCustomer && !vp.sameBillCustomer.equals("")) {
			verifyProgramInfo("Same Billing Customer", vp.sameBillCustomer);
		}
		if (null != vp.account && !vp.account.equals("")) {
			verifyProgramInfo("Account", vp.account);
		}
	}

	/**
	 * Get all programeId in the search list
	 * 
	 */
	public List<String> getAllVoucherProgramId() {
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = null;

		List<String> programIds = new ArrayList<String>();
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				programIds.add(tableGrid.getCellValue(i, 1));
			}

		} while (gotoNext());

		Browser.unregister(objs);
		return programIds;
	}

	/**
	 * This method is used to search and verify search result correct
	 * 
	 * @param vp
	 *            Voucher Program
	 */
	public void searchAndVerify(VoucherProgram vp) {
		setUpSearchCriteria(vp);
		clickGo();
		waitLoading();
		verifyProgramInSearchList(vp.programId);
		gotoFirstPg();
		if (null != vp.searchType && !vp.searchType.equals("")) {
			verifyProgramInfo(vp.searchType, vp.searchValue);
		}

		if (null != vp.dateType && !"".equals(vp.dateType)) {
			verifyDateInGivenRange("Effective Start Date", vp.startDate,
					vp.endDate);
		}
		if (null != vp.programeStatus && !vp.programeStatus.equals("")) {
			if (vp.programeStatus.equalsIgnoreCase("Active")) {
				verifyProgramInfo("Active", "Yes");
			} else {
				verifyProgramInfo("Active", "No");
			}
		}
		if (null != vp.programeType && !vp.programeType.equals("")) {
			verifyProgramInfo("Program Type", vp.programeType);
		}
		if (null != vp.productCategoryForCreation
				&& !vp.productCategoryForCreation.equals("")) {
			if (vp.productCategoryForCreation.equalsIgnoreCase("All")) {
				verifyProgramInfo("Product Category for Creation",
						vp.productCategoryForCreation);
			} else {
				verifyProgramInfo("Product Category for Creation", "Multi");
			}
		}
		if (null != vp.emergencyCancellation
				&& !vp.emergencyCancellation.equals("")) {
			verifyProgramInfo("Emergency Cancellation",
					vp.emergencyCancellation);
		}
		if (null != vp.redirectionToRefund
				&& !vp.redirectionToRefund.equals("")) {
			verifyProgramInfo("Redirection to Refund", vp.redirectionToRefund);
		}
		if (null != vp.productCategoryForUse
				&& !vp.productCategoryForUse.equals("")) {
			if (vp.productCategoryForUse.equalsIgnoreCase("All")) {
				verifyProgramInfo("Product Category for Use",
						vp.productCategoryForUse);
			} else {
				verifyProgramInfo("Product Category for Use", "Multi");
			}
		}
		if (null != vp.sameBillCustomer && !vp.sameBillCustomer.equals("")) {
			verifyProgramInfo("Same Billing Customer", vp.sameBillCustomer);
		}

	}

	/**
	 * Verify given program in the search list
	 * 
	 * @param programId
	 */
	public void verifyProgramInSearchList(String programId) {
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = null;
		boolean foundProgram = false;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 1).toString().equals(programId)) {
					foundProgram = true;
					break;
				}
			}
			if (foundProgram) {
				logger.info("Voucher Program " + programId
						+ " is in the search result!");
				break;
			}
			if (!hasNext() && !foundProgram) {
				Browser.unregister(objs);
				throw new ItemNotFoundException("Search Voucher Program Fail!");
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}

	/**
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyProgramInfo(String colName, String value) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {

				if (null != tableGrid.getCellValue(i, colNum)) {
					if (!tableGrid.getCellValue(i, colNum).toString().trim()
							.startsWith(value)) {
						Browser.unregister(objs);
						logger.error("Voucher Program Info "
								+ tableGrid.getCellValue(i, colNum)
								+ " is not Correct! ");
						throw new ItemNotFoundException(tableGrid.getCellValue(
								i, colNum)
								+ " is different with given value "
								+ value);
					}
				}
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}

	/**
	 * This method is used to verfiy given column date value is in the given
	 * date range
	 * 
	 * @param colName
	 * @param fromDate
	 * @param toDate
	 */
	public void verifyDateInGivenRange(String colName, String fromDate,
			String toDate) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = null;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (null != tableGrid.getCellValue(i, colNum)) {
					String date = DateFunctions.formatDate(tableGrid
							.getCellValue(i, colNum).toString(), "M/d/yyyy");
					int value = DateFunctions.compareDates(fromDate, date)
							+ DateFunctions.compareDates(toDate, date);
					if (value == 2 || value == -2) {
						Browser.unregister(objs);
						logger.error("Date " + date
								+ " is not in the given date range!");
						throw new ItemNotFoundException("Date " + date
								+ " is not in the given date range!");
					}
				}
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}

	/**
	 * This method is used to goto first page
	 * 
	 */
	public void gotoFirstPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"First");
		if (objs.length > 0) {
			ILink link = (ILink) objs[0];
			link.click();
		}
		Browser.unregister(objs);

		this.waitLoading();
	}

	/**
	 * Check whether there have next page,if have,click Next Button
	 * 
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			ILink link = (ILink) objs[0];
			link.click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	public boolean hasNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
		}
		Browser.unregister(objs);

		return toReturn;
	}

	/**
	 * Get Column Number by Column Name
	 * 
	 * @param colName
	 * @return Column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString()
						.equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	/**
	 * Check error message whether exist
	 * 
	 * @return
	 */
	public boolean errorMessageIsExist() {
		return browser.checkHtmlObjectExists(".id", "Messages", ".class",
				"Html.DIV");
	}

	/**
	 * Get error message
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		return browser.getObjectText(".id", "Messages", ".class", "Html.DIV");
	}

	public VoucherProgram getVoucherProgramList(int row) {
		VoucherProgram program = new VoucherProgram();
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		if (objs.length == 0) {
			throw new ItemNotFoundException(
					"Voucher Program List could not found .");
		}
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		program.programId = tableGrid.getCellValue(row, 1);
		program.status = tableGrid.getCellValue(row, 2);
		program.programeType = tableGrid.getCellValue(row, 3);
		program.programeName = tableGrid.getCellValue(row, 4);
		program.lineOfBusiness = tableGrid.getCellValue(row, 5);
		program.startDate = tableGrid.getCellValue(row, 6);
		program.endDate = tableGrid.getCellValue(row, 7);
		program.createLocation = tableGrid.getCellValue(row, 8);
		program.productCategoryForCreation = tableGrid.getCellValue(row, 9);
		program.emergencyCancellation = tableGrid.getCellValue(row, 10);
		program.redirectionToRefund = tableGrid.getCellValue(row, 11);
		program.redirectionToRefundWeb = tableGrid.getCellValue(row, 12);
		program.locationForUse = tableGrid.getCellValue(row, 13);
		program.productCategoryForUse = tableGrid.getCellValue(row, 14);
		program.sameBillCustomer = tableGrid.getCellValue(row, 15);
		program.expiry = tableGrid.getCellValue(row, 16);
		program.expiryPeriod = tableGrid.getCellValue(row, 17);
		program.expiryPeriodCalculationMethod = tableGrid.getCellValue(row, 18);
		program.inactivityFeesIndicator = tableGrid.getCellValue(row, 19);
		program.account = tableGrid.getCellValue(row, 20);

		Browser.unregister(objs);
		return program;
	}

	public List<String> getSearchCriteriaValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.searchBy");
		return values;
	}

	public List<String> getDateValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.searchByDate");
		return values;
	}

	public List<String> getShowValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.status");
		return values;
	}

	public List<String> getProgramTypeValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.type");
		return values;
	}

	public List<String> getProductCategooryForCreationValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.creationPrdCat");
		return values;
	}

	public List<String> getEmergencyCancellationValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.emergencyCancellation");
		return values;
	}

	public List<String> getRedirectToRefundValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.redirectToRefund");
		return values;
	}

	public List<String> getWebRedirectToRefundValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.webRedirectToRefund");
		return values;
	}

	public List<String> getProductCategooryForUseValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.usePrdCat");
		return values;
	}

	public List<String> getBillingCustomerValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.sameBillingCustomer");
		return values;
	}

	public List<String> getExpiryValues() {
		List<String> values = browser.getDropdownElements(".id",
				"VoucherProgramSearchRequest.expiry");
		return values;
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<VoucherProgram> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<VoucherProgram> records = new ArrayList<VoucherProgram>();
		int rows;
		int columns;
		VoucherProgram vp;
		RegularExpression rex = new RegularExpression(
				"Voucher Program ID Active Program Type.*", false);
		
		do{

		objs = browser.getTableTestObject(".text", rex);
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find voucher program table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page FinMgrVoucherProgramSearchPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			vp = new VoucherProgram();
			vp.programId = table.getCellValue(i, table.findColumn(0, "Voucher Program ID"));
			vp.status = table.getCellValue(i, table.findColumn(0, "Active"));
			vp.programeType = table.getCellValue(i, table.findColumn(0, "Program Type"));
			vp.programeName = table.getCellValue(i, table.findColumn(0, "Voucher Program Name"));
			vp.lineOfBusiness = table.getCellValue(i, table.findColumn(0, "Line of Business"));
			vp.startDate = table.getCellValue(i, table.findColumn(0, "Effective Start Date"));
			vp.endDate = table.getCellValue(i, table.findColumn(0, "Effective End Date"));
			vp.createLocation = table.getCellValue(i, table.findColumn(0, "Location for Creation"));
			vp.productCategoryForCreation = table.getCellValue(i, table.findColumn(0, "Product Category for Creation"));
			vp.emergencyCancellation = table.getCellValue(i, table.findColumn(0, "Emergency Cancellation"));
			vp.redirectionToRefund = table.getCellValue(i, table.findColumn(0, "Redirection to Refund"));
			vp.redirectionToRefundWeb = table.getCellValue(i, table.findColumn(0, "Redirection to Refund (Web)"));
			vp.locationForUse = table.getCellValue(i, table.findColumn(0, "Location for Use"));
			vp.productCategoryForUse = table.getCellValue(i, table.findColumn(0, "Product Category for Use"));
			vp.sameBillCustomer = table.getCellValue(i, table.findColumn(0, "Same Billing Customer"));
			vp.expireIndicator = ((table.getCellValue(i, table.findColumn(0, "Expiry")).equalsIgnoreCase("Yes"))?true:false);
			vp.expiryPeriod = table.getCellValue(i, table.findColumn(0, "Expiry Period"));
			vp.expiryMethod = table.getCellValue(i, table.findColumn(0, "Expiry Period Calculation Method"));
			vp.inactivityFeesIndicator = (table.getCellValue(i, table.findColumn(0, "Inactivity Fees Indicator")).equalsIgnoreCase("Yes"))?"true":"false";
			vp.account = table.getCellValue(i, table.findColumn(0, "Account"));
									
			records.add(vp);			
		}
				
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}
	
	
}
