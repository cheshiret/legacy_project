package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.dateofbirth;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the error message on customer detail page, when activate customer identifier
 * @Preconditions: 
 * @SPEC: PCR 2926, <<Activate Customer Identifier.doc>>, TC003840 step8-13
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 9, 2012
 */
public class ActivateCust extends LicenseManagerTestCase {
	private LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPage = LicMgrCustomerDetailsPage.getInstance();
	private boolean result = true;
	private String description, expected1, expected2, expected3, expected4,orgDOB,msgOnPage;
	private TimeZone timeZone;
	
	@Override
	public void execute() {
		// update customer class - Business optional indicator as FALSE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		lm.loginLicenseManager(login);
		if(!lm.checkCustomerExists(cust.fName, cust.lName, schema)) {
			lm.gotoNewCustomerPage(cust.customerClass);
			lm.addOrEditCustomerInfo(cust, newCustPage);
			lm.finishAddOrEditCustomer();
		} else {
			cust.dateOfBirth = "";
			lm.gotoCustomerDetailFromTopMenu(cust);
		}
		
		//1. activate customer identifier, don't input date of birth for the specific(MS Drivers License) customer profile
		cust.dateOfBirth = "";
		this.updateCustDOB(cust.dateOfBirth);
		lm.gotoCustomerIdentifierSubTab();
		cust.identifier.id = LicMgrCustomerIdentifiersPage.getInstance().getIdentifierID(cust.identifier.identifierType, cust.identifier.identifierNum);
//		String msgOnPage = lm.getWarnMesWhenChangeIdentifierStatus(cust.identifier.id, "Activate");
//		result &= MiscFunctions.compareResult(description, expected1, msgOnPage);
		this.checkMsgIsExistingWhenActiveIdentifierRecord(cust.identifier.id, expected1);
		
		//for clear cache
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, false);
		cust.dateOfBirth = orgDOB;
		this.updateCustDOB(cust.dateOfBirth);
		
		//2. date of birth has not been specified
		cust.dateOfBirth = "";
		this.updateCustDOB(cust.dateOfBirth);
		msgOnPage = lm.finishAddOrEditCustomer();
		result &= MiscFunctions.compareResult(description, expected2, msgOnPage);
		
		//3. input date of birth while it's not valid date
		result &= custDetailsPage.verifyDateOfBirthTextFieldValid(new String[]{"asd123", "zxcvbnbnm", ")(*&^%$#@!~"});
		
		//4. input date of birth while it's greater than the current date in contract time zone
		cust.dateOfBirth = DateFunctions.getDateAfterToday(2, timeZone);
		lm.addOrEditCustomerInfo(cust, custDetailsPage);
		msgOnPage = lm.finishAddOrEditCustomer();
		result &= MiscFunctions.compareResult(description, expected3, msgOnPage);
		
		//5. input date of birth while it's 150 or more years in the past
		cust.identifier.identifierType = "MS Drivers License";
		cust.dateOfBirth = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), -(12 * 151));
		lm.addOrEditCustomerInfo(cust, custDetailsPage);
		msgOnPage = lm.finishAddOrEditCustomer();
		result &= MiscFunctions.compareResult(description, expected4, msgOnPage);
		
		//finally
		if(!result) {
			throw new ErrorOnPageException("The checkpoints are NOT all passed. Please refer log for details.");
		} else logger.info("All checkpoints are PASSED.");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//customer info
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.fName = "Activate";
		cust.mName = "Middle";
		cust.lName = "Customer";
		cust.businessName = "ActivateCustomerDOB";
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.includeAreaCode = true;
		cust.email = "hunting@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		
		orgDOB = "1981-06-07";
		cust.dateOfBirth = orgDOB;
		cust.identifier.identifierType = "MS Drivers License";
		cust.identifier.identifierNum = "RGU888888";
		cust.identifier.state = "Mississippi";
		
		description = "Date of Birth";
		
		expected1 = "Date of Birth is required for Identifier \"" + cust.identifier.identifierType + "\". Please input the Date of Birth.";
		expected2 = "Date Of Birth is required. Please specify the Date Of Birth.";
		expected3 = "Date of Birth must be today or in the past. Please re-enter.";
		expected4 = "The Date of Birth must be less than 150 years ago.";
	}
	
	private void updateCustDOB(String dob) {
		custDetailsPage.clickEdit();
		ajax.waitLoading();
		custDetailsPage.waitLoading();
		custDetailsPage.setDateOfBirth(dob);
		custDetailsPage.clickApply();
		ajax.waitLoading();
	}
	
	private void checkMsgIsExistingWhenActiveIdentifierRecord(String idenID,String message) {
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
				.getInstance(true);

		logger.info("Active a identifier that ID=" + idenID);
		identifierPage.selecRadioButtonViaID(idenID);
		identifierPage.clickActivate();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
		if(!confirmDialogWidget.checkContentIsExisting(message)){
			throw new ErrorOnPageException("The error message should display: " + message);
		}
		confirmDialogWidget.clickCancel();
		ajax.waitLoading();
		identifierPage.waitLoading();
	}
}
