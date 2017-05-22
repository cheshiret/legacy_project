/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrChangingInventoryTypeConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The Inventory Type was changed to an option other than "None"
 *               and the Privilege Product has at least one associated "Active"
 *               Product Pricing record with "Transfer" Purchase Type and with
 *               either License/Fiscal Year From equal to "All" or
 *               License/Fiscal Year To greater than or equal to the Current
 *               Year, and with either Effective To Date equal to null/blank or
 *               Effective To Date greater than or equal to the Current Date.
 * 
 *               System displays a warning message (Message 23) prompting the
 *               User to confirm the change.
 * 
 *               User confirms the change and their desire to proceed with the
 *               update.
 * 
 *               User cancels the change. System prompts the User to re-enter.
 * 
 *               This use case continues,System updates the
 *               Privilege Product with the changes made by the User.
 * 
 * @Preconditions:
 * @SPEC:Edit Privilege Product.doc
 * @Task#:AUTO-670
 * @Defects:DEFECT-30399
 * 
 * @author asun
 * @Date Aug 8, 2011
 */
public class Edit_ChangeInventoryType extends
		LicenseManagerTestCase {
	private LicMgrPrivilegePricingPage pricingPage = LicMgrPrivilegePricingPage
			.getInstance();
	private String msg;
	private String otherType = "Slat Trap";

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		lm.deactivateAllProductPricings(pricingPage);
		this.changeInventoryType("None");

		// click 'cancel' when doing confirmation
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.changeInventoryType(otherType);
		this.verifyConfirmation(msg, false);

		// click 'OK' when doing confirmation
		this.changeInventoryType(otherType);
		this.verifyConfirmation(msg, true);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		msg = "The Inventory Type has been changed to one that does not allow the Privilege to be transferred. Any existing Transfer Pricing records for this Privilege Product will not be used. Are you sure you want to continue?";

		privilege.code = "A10";
		pricing.status = "Active";
		pricing.prodType = "Privilege";
		pricing.locationClass = "01 - MDWFP Headquarters";
		pricing.licYearFrom = "All";
		pricing.purchaseType = "Transfer";
		pricing.effectFrom = DateFunctions.getToday(DataBaseFunctions
				.getContractTimeZone(schema));
		pricing.vendorFee = "1";
		pricing.stateTransFee = "1";
		pricing.stateFee = "2";
		pricing.transFee = "2";
	}

	/**
	 * change Inventory type in privilege details page
	 * 
	 * @param type
	 */
	public void changeInventoryType(String type) {
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		privilegeDetailsPg.selectInventoryType(type);
		privilegeDetailsPg.clickApply();
		ajax.waitLoading();
	}

	/**
	 * verify option and message when do confirmation
	 * 
	 * @param msg
	 * @param isOk
	 */
	public void verifyConfirmation(String msg, boolean isOk) {
		LicMgrChangingInventoryTypeConfirmWidget confirmWidget = LicMgrChangingInventoryTypeConfirmWidget
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
				.getInstance();

		String option = "";

		// boolean isExisting=false;
		logger.info("verify confirmation....");
		if (!confirmWidget.exists()) {
			throw new PageNotFoundException("Can't find confirm widget..");
		}

		String msgFromPage = confirmWidget.getMessage();

		if (!msgFromPage.equals(msg)) {
			throw new ErrorOnPageException(
					"message on confirm widget should be '" + msg + "'");
		}

		if (isOk) {
			confirmWidget.clickOK();
		} else {
			confirmWidget.clickCancel();
		}
        ajax.waitLoading();
		privilegeDetailsPg.clickCancel();
		privilegeListPage.waitLoading();
		lm.gotoProductPricingPageFromListPage("Privilege", privilege.code);
		option = privilegeDetailsPg.getInventoryType();

		if (isOk && !option.equals(otherType)) {
			throw new ErrorOnPageException("the update should work..");
		} else if (!isOk && !option.equals("None")) {
			throw new ErrorOnPageException("the update should not work..");
		}
		logger.info("verify successfully");
	}

}
