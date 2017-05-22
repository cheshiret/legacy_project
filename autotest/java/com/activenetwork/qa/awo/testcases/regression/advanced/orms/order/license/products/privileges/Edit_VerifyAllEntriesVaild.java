/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditDisplayOrderPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;

/**
 * @Description:This case is used to verify all error message and editing dispaly order.
 * @Preconditions:
 * @SPEC:Edit Privilege Product.doc
 * @Task#:AUTO-670
 * @Defects:DEFECT-30359
 * @author asun
 * @Date Aug 4, 2011
 */
public class Edit_VerifyAllEntriesVaild extends LicenseManagerTestCase {

	private List<Exception> list = new ArrayList<Exception>();
	private int count = 0;
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, msg9,
			displayOrderMsg;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
      
		//The Privilege Product Name is not specified, i.e. left null/blank. 
		privilege.name = "";
		this.editAndVerifyMessage(privilege, msg1);
		privilege.name = "A9";
		
		//At least one Customer Class has not been selected.
		privilege.customerClasses = new String[] {};
		this.editAndVerifyMessage(privilege, msg2);
		privilege.customerClasses = new String[] { "Individual" };
        
		/**
         * The Valid From Date Calculation option selected is either Prompt 
		 * for Valid From Date or Prompt for Valid From Date & Time, and the
		 * Indicator to prompt Per Privilege or Per Transaction is not specified.
         */
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		privilege.promptIndicator = "";
		this.editAndVerifyMessage(privilege, msg3);
		privilege.promptIndicator = "Per Privilege";
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		privilege.promptIndicator = "";
		this.editAndVerifyMessage(privilege, msg3);
		privilege.promptIndicator = "Per Privilege";

		
		/**
		 * The Valid To Date Calculation option selected is 
		 * "Purchase Date plus Valid Days/Years", and neither 
		 * the Valid Days nor the Valid Years is specified.
		 */
		privilege.validToDateCalculation = "Valid From Date plus Valid Days/Years";
		privilege.validDaysYears = "";
		privilege.dateUnitOfValidToDate="Days";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate=" ";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.dateUnitOfValidToDate=" ";
		privilege.validDaysYears = " ";
		privilege.dateUnitOfValidToDate=" ";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate="Days";

		
		/**
		 * The Valid Days or Valid Years entry is not an integer value greater than 0.
		 */
		privilege.validToDateCalculation = "Valid From Date plus Valid Days/Years";
		privilege.validDaysYears = "www";
		privilege.dateUnitOfValidToDate="Days";
		this.editAndVerifyMessage(privilege, msg7);
		privilege.validDaysYears = "-1";
		privilege.dateUnitOfValidToDate="Days";
		this.editAndVerifyMessage(privilege, msg7);
		privilege.validDaysYears = "0";
		privilege.dateUnitOfValidToDate="Days";
		this.editAndVerifyMessage(privilege, msg7);
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "0";
		privilege.dateUnitOfValidToDate="Days";
		privilege.renewalDays = "1";
		this.editAndVerifyMessage(privilege, msg7);
		privilege.validDaysYears = "-1";
		privilege.dateUnitOfValidToDate="Days";
		this.editAndVerifyMessage(privilege, msg7);
		privilege.validDaysYears = "1";
		
		/**
		 * The Valid To Age entry is not an integer value greater than 0.
		 */
		privilege.validToDateCalculation = "Expires when the Customer reaches Valid To Age";
		privilege.validToAge = "www";
		this.editAndVerifyMessage(privilege, msg9);
		privilege.validToAge = "-1";
		this.editAndVerifyMessage(privilege, msg9);
		privilege.validToAge = "0";
		this.editAndVerifyMessage(privilege, msg9);
		privilege.validToAge = "1";

		/**
		 * The Valid To Date Calculation option selected is 
		 * "Valid To Date of Previous License plus Valid Days/Years",
		 * and neither the Valid Days nor the Valid Years is specified.
		 */
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "";
		privilege.dateUnitOfValidToDate = "Years";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = " ";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.dateUnitOfValidToDate="Days";
		privilege.validDaysYears = " ";
		privilege.dateUnitOfValidToDate = " ";
		this.editAndVerifyMessage(privilege, msg4);
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate="Days";

		/**
		 * The Renewal Days entry is not an integer value greater than 0.
		 */
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Days";
		privilege.renewalDays = "-1";
		this.editAndVerifyMessage(privilege, msg8);
		privilege.renewalDays = "0";
		this.editAndVerifyMessage(privilege, msg8);
		privilege.renewalDays = "www";
		this.editAndVerifyMessage(privilege, msg8);
		privilege.renewalDays = "1";

		/**
		 * The Valid To Date Calculation option selected is 
		 * "Valid To Date of Previous License plus Valid Days/Years", 
		 * and the Renewal Days is not specified, i.e. left null/blank.
		 */
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Days";
		privilege.renewalDays = "";
		this.editAndVerifyMessage(privilege, msg5);
		privilege.renewalDays = "3";

		
		/**
		 * The Valid To Date Calculation option selected is 
		 * "Expires when the Customer reaches Valid To Age", 
		 * and the Valid To Age is not specified, i.e. left null/blank
		 */
		privilege.validToDateCalculation = "Expires when the Customer reaches Valid To Age";
		privilege.validToAge = "";
		this.editAndVerifyMessage(privilege, msg6);
		privilege.renewalDays = "3";

		
		
		try {
			count++;
			privilege.displayOrder = "0";
			lm.gotoPrivilegeEditDisplayOrderPageFromPrivilegeListPage();
			lm.editDisplayOrderForPrivilege(privilege.code,
					privilege.displayOrder);
			this.verifyDisplayOrderErrorMessage(displayOrderMsg);
		} catch (Exception e) {
			gotoPricvilegeListpageFromDetailsPage();
			list.add(e);
		}

		try {
			count++;
			privilege.displayOrder = "5";
			lm.editDisplayOrderForPrivilege(privilege.code,
					privilege.displayOrder);
			this.verifyEditDisplayOrderSuccess();
		} catch (Exception e) {
			gotoPricvilegeListpageFromDetailsPage();
			list.add(e);
		}

		if (list.size() > 0) {
			for (Exception e : list) {
				e.printStackTrace();
			}
			throw new TestCaseFailedException("Total verifications:" + count
					+ ",failed:" + list.size());
		}

	}

	@Override
	public void wrapParameters(Object[] param) {
		// initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.code = "A9";
		privilege.name = "A9";
		privilege.status = "Active";
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Based on Priv License Year Record";
		privilege.validDatePrinting = new String[] { "Print Valid From Date" };
		privilege.customerClasses = new String[] { "Individual" };
		privilege.authorizationQuantity = "Return as Individual Privileges";
		privilege.invType = "None";
		privilege.displayCategory = "LifeTime";
		privilege.displaySubCategory = "Trip";
		privilege.reportCategory = "Lifetime Licenses";

		msg1 = "The Privilege Product Name is required. Please specify the Name.";
		msg2 = "At least one Customer Class must be selected. Please select the Customer Class from the list provided.";
		msg3 = "The indicator whether to prompt Per Privilege or Per Transaction is required. Please specify.";
		msg4 = "The Valid Days or Valid Years is required. Please specify the Valid Days or Valid Years.";
		msg5 = "The Renewal Days is required. Please specify the Renewal Days.";
		msg6 = "The Valid To Age is required. Please specify the Valid To Age.";
		msg7 = "The Valid Days or Valid Years entered is not valid. Please enter an integer value greater than 0.";
		msg8 = "The Renewal Days entered is not valid. Please enter an integer value greater than 0.";
		msg9 = "The Valid To Age entered is not valid. Please enter an integer value greater than 0.";
		displayOrderMsg = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
	}

	public void editAndVerifyMessage(PrivilegeInfo privilege, String msg) {
		count++;
		try {
			lm.editPrivilegeInfo(privilege);
			this.verifyErrorMessage(msg);
			logger.info("verify message correctly for #" + count);
		} catch (Exception e) {
			list.add(e);
			logger.error("verify message wrong for #" + count + " due to " + e.getMessage());
		} finally {
			gotoPricvilegeListpageFromDetailsPage();
		}
	}

	public void gotoPricvilegeListpageFromDetailsPage() {
		LicMgrPrivilegeProductDetailsPage detailsPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		detailsPage.clickCancel();
		privilegeListPage.waitLoading();
	}

	/**
	 * Verify error message
	 * 
	 * @param expectedMsg
	 */
	public void verifyErrorMessage(String expectedMsg) {
		LicMgrPrivilegeProductDetailsPage detailsPage = LicMgrPrivilegeProductDetailsPage
				.getInstance();

		logger.info("Verify error message..");
		if (!detailsPage.exists()) {
			throw new ObjectNotFoundException("The work flow should stay in "
					+ detailsPage.getClass().getSimpleName());
		}

		String msgOnPage = detailsPage.getWarningMessage();
		if (!msgOnPage.equals(expectedMsg)) {
			throw new ErrorOnPageException("The right message should be '"
					+ expectedMsg + "'");
		}
	}

	private void verifyDisplayOrderErrorMessage(String expectedErrorMessage) {
		LicMgrPrivilegeEditDisplayOrderPage privilegeEditDisplayOrderPage = LicMgrPrivilegeEditDisplayOrderPage
				.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		if (!privilegeEditDisplayOrderPage.exists()) {
			throw new ObjectNotFoundException(
					"Expected page should be edit privilege page and expected error message.");
		}

		String actualMessage = privilegeEditDisplayOrderPage.getErrorMessage();

		if (!actualMessage.equals(expectedErrorMessage)) {
			throw new ErrorOnPageException("Expected error message should be "
					+ expectedErrorMessage + ", but actually is "
					+ actualMessage);
		}

		privilegeEditDisplayOrderPage.clickCancle();
		privilegeListPage.waitLoading();
	}

	// verify edit display order success
	private void verifyEditDisplayOrderSuccess() {
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		if (!privilegeListPage.exists()) {
			throw new ObjectNotFoundException(
					"Expected page should be privilege list page, because edit display order success.");
		}

		if (!privilege.displayOrder.equals(privilegeListPage
				.getPrivilegeListInfoByColumnName(privilege.code,
						"Display Order"))) {
			throw new ErrorOnPageException("Edit display order failed.");
		}

	}

}
