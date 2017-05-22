/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This case is used to verify adding a privilege product for big game
 * @Preconditions:
 * @SPEC: TC046725
 * @Task#: AUTO-1235
 * 
 * @author szhou
 * @Date  Sep 24, 2012
 */
public class AddPrivilegeForBigGame extends LicenseManagerTestCase {
	private static final String DEF_VALUE = "No";

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		// add privilege product
		lm.gotoCreatePrivilegeProductPageFromPrivilegeListPage();
		// verify indicator 'Available via Application Only' default value
		this.verifyIndicatorValue();

		// add product for test indicator
		this.createProductForBigGame(privilege);
		// verify indicator select 'yes' value
		this.checkFieldNotEditableAfterIndicatorChoose();

		// add product successfully for big game
		this.addPrivilegeProduct(privilege);

		// verify privilege brief info in list page
		this.resetPrivilegeContent();
		lm.verifyPrivilegeListInfo(privilege);
		// verify privilege detail info in details page
		lm.verifyPrivilegeDetailInfo(privilege);

		// clean up
		lm.changePrivilegeProductStatus(privilege.code,
				OrmsConstants.INACTIVE_STATUS);
		lm.logOutLicenseManager();
	}
	
	private void resetPrivilegeContent(){
		privilege.validFromDateCalculation = "Based on Priv LY Record or Purchase Date (If Greater)";
		privilege.promptIndicator = "";
		privilege.validToDateCalculation = "Based on Priv License Year Record";
		privilege.validDaysYears = "0";
		privilege.dateUnitOfValidToDate = "";
		privilege.renewalDays = "0";
		privilege.validToAge = "0";
	}
	
	private void addPrivilegeProduct(PrivilegeInfo privilege){
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();
		
		createwPrivilegeProductPage.setupPrivilegeInfo(privilege);
		createwPrivilegeProductPage.clickOk();
		
		ajax.waitLoading();
		privilegeListPage.waitLoading();
	}

	private void checkFieldNotEditableAfterIndicatorChoose() {
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();

		boolean result = true;
		// verify Valid From Date Calculation is not editable and value is "Based on Priv LY Record or Purchase Date (If Greater)"
		String value = "Based on Priv LY Record or Purchase Date (If Greater)";
		if (!createwPrivilegeProductPage
				.checkValidFromDateCalculationIsDisabled()
				|| !value.equals(createwPrivilegeProductPage
						.getValidFromDateCalculation())) {
			logger.error("Valid From Date Calculation could editable or the value of this dropdown list is not correct; Expect value is "
					+ value
					+ ";but actually value is"
					+ createwPrivilegeProductPage.getValidFromDateCalculation());
			result &= false;
		}

		// verify Prompt Indicator is not editable and value is null
		value = "";
		if (!createwPrivilegeProductPage.checkPromptIndicatorIsDisabled()
				|| !value.equals(createwPrivilegeProductPage.getPromptIndicator())) {
			logger.error("Prompt Indicator could editable or the value of this dropdown list is not correct; Expect value is blank;but actually value is"
					+ createwPrivilegeProductPage.getPromptIndicator());
			result &= false;
		}

		// verify Valid To Date Calculation is not editable and value is "Based on Priv LY Record"
		value = "Based on Priv License Year Record";
		if (!createwPrivilegeProductPage
				.checkValidFromDateCalculationIsDisabled()
				|| !value.equals(createwPrivilegeProductPage
						.getValidToDateCalculation())) {
			logger.error("Valid To Date Calculation could editable or the value of this dropdown list is not correct; Expect value is "
					+ value
					+ ";but actually value is"
					+ createwPrivilegeProductPage.getValidToDateCalculation());
			result &= false;
		}

		// verify Valid Days/Years is not editable and value is 0
		value = "0";
		if (!createwPrivilegeProductPage.checkValidDateOrYearsIsDisabled()
				|| !value.equals(createwPrivilegeProductPage.getValidDateOrYears())) {
			logger.error("Valid Days/Years could editable or the value of this dropdown list is not correct; Expect value is 0;but actually value is"
					+ createwPrivilegeProductPage.getValidFromDateCalculation());
			result &= false;
		}

		// verify Renewal Days is not editable and value is 0
		if (!createwPrivilegeProductPage.checkRenewalDaysIsDisabled()
				|| !value.equals( createwPrivilegeProductPage.getRenewalDays())) {
			logger.error("Renewal Days could editable or the value of this dropdown list is not correct; Expect value is 0;but actually value is"
					+ createwPrivilegeProductPage.getValidFromDateCalculation());
			result &= false;
		}
		
		if(!result){
			throw new ErrorOnPageException("Please see log file to check error message...");
		}
	}

	private void createProductForBigGame(PrivilegeInfo privilege) {
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();

		if (privilege.validFromDateCalculation.trim().length() > 0) {
			createwPrivilegeProductPage
					.selectValidFromDateCalculation(privilege.validFromDateCalculation);
			ajax.waitLoading();
		}

		if (!createwPrivilegeProductPage.checkPromptIndicatorIsDisabled()) {
			createwPrivilegeProductPage
					.selectPromptIndicator(privilege.promptIndicator);
			ajax.waitLoading();
		}

		if (privilege.validToDateCalculation.length() > 0) {
			createwPrivilegeProductPage
					.selectValidToDateCalculation(privilege.validToDateCalculation);
			ajax.waitLoading();
		}

		if (!createwPrivilegeProductPage.checkValidDateOrYearsIsDisabled()) {
			createwPrivilegeProductPage
					.setValidDateOrYears(privilege.validDaysYears);
		}

		if (!createwPrivilegeProductPage.checkDateUnitOfValidToDateIsDisabled()) {
			if (privilege.dateUnitOfValidToDate.trim().length() > 0) {
				createwPrivilegeProductPage
						.selectDateUnitOfValidToDate(privilege.dateUnitOfValidToDate);
				ajax.waitLoading();
			} else {
				// used to verify error message
				createwPrivilegeProductPage.selectDateUnitOfValidToDate(0);
			}
		}

		if (!createwPrivilegeProductPage.checkRenewalDaysIsDisabled()) {
			createwPrivilegeProductPage.setRenewalDays(privilege.renewalDays);
		}

		if (!createwPrivilegeProductPage.checkValidToAgeIsDisable()) {
			createwPrivilegeProductPage.setValidToAge(privilege.validToAge);
		}

		createwPrivilegeProductPage
				.selectHuntsRequired(privilege.huntsRequired);
		ajax.waitLoading();
	}

	private void verifyIndicatorValue() {
		LicMgrCreateNewPriviledgePage createwPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();

		String value = createwPrivilegeProductPage
				.getHuntsRequiredValue();
		if (!DEF_VALUE.equals(value)) {
			throw new ErrorOnPageException(
					"The default value is not correct. Expect value is "
							+ DEF_VALUE + ";But Actually value is " + value
							+ ".");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddPrivilege_BigGame";
		privilege.productGroup = "Privileges";
		privilege.status = "Active";
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "30";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[] { "Print Valid From Date" };
		privilege.customerClasses = new String[] { "Individual" };
		privilege.authorizationQuantity = "Return as Single Privilege with Quantity";
		privilege.invType = "Winter Paddlefish Tags";
		privilege.displayCategory = "Saltwater Fishing";
		privilege.displaySubCategory = "Applications";
		privilege.reportCategory = "Non-Resident Licenses";
		privilege.huntsRequired = "Yes";

	}

}
