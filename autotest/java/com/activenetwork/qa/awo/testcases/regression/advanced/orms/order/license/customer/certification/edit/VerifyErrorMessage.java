package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditCertificationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description:This case is used to verify edit customer certification error message.
 * @Preconditions:Need to run AddCustomerProfile support script, customer info as the below,
 * and this customer should have a expired certification
 *      customerClass = "INDIVIDUAL";
		licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Edit1";
		cust.mName = "QaTest-Certification3";
		cust.lName = "TEST-Active3";
		dateOfBirth = "Jan 01 1980";
 * @SPEC:Edit Customer Certification.UCS
 * @Task#:Auto-710
 *
 * @author VZhang
 * @Date  Aug 22, 2011
 */
public class VerifyErrorMessage extends LicenseManagerTestCase{
	private LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget.getInstance();
	private LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage.getInstance();
	private Certification certification = new Certification();
	private Certification editCertification = new Certification();
	private Certification duplicateCertification = new Certification();
	private String actualMessage = "", message1 = "", message2 = "", message3 = "", message4 ="";
	private boolean pass = true;

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoCertificationFromCustomerDetailsPg();

		//Add certification
		certification.id = lm.addCustomerCertification(certification, true);
		duplicateCertification.id = lm.addCustomerCertification(duplicateCertification, true);

		editCertification = certification;
		editCertification.num = "";
		//verify certification number is not specified
		actualMessage = lm.editCustomerCertification(editCertification, true);
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		this.verifyErrorMessage(actualMessage, message1);//spec: message3

		editCertification.num = duplicateCertification.num;
		editCertification.effectiveFrom = "";
		//verify effective from date is not specified
		actualMessage = lm.editCustomerCertification(editCertification, true);
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		this.verifyErrorMessage(actualMessage, message2);//spec: message4

		editCertification.effectiveFrom = "auto";
		//verify effective from date is no valid
		actualMessage = lm.editCustomerCertification(editCertification, true);
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		this.verifyErrorMessage(actualMessage, message2);//spec: message4

		editCertification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		editCertification.effectiveTo = DateFunctions.getDateAfterToday(-2);
		//verify effective to date is less than effective from date
		actualMessage = lm.editCustomerCertification(editCertification, true);
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		this.verifyErrorMessage(actualMessage, message3);//spec: message8

		editCertification.effectiveTo = DateFunctions.getToday();
		//verify existing 'Non-Deleted' certification
		actualMessage = lm.editCustomerCertification(editCertification, true);
		editCertificationWidget.clickCancel();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		this.verifyErrorMessage(actualMessage, message4);//spec: message9

		lm.removeCustomerCertification(certification.id, true);

		if(!pass){
			throw new ErrorOnPageException("Some check pionts are not correct, please check.");
		}
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Edit1";
		cust.mName = "QaTest-Certification3";
		cust.lName = "TEST-Active3";
		cust.dateOfBirth = "Jan 01 1980";

		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "MESSAGE" + DateFunctions.getCurrentTime();;
		certification.effectiveFrom = DateFunctions.getToday();
		certification.effectiveTo = DateFunctions.getDateAfterToday(30);

		duplicateCertification.status = "Active";
		duplicateCertification.type = "Trapper Certification";
		duplicateCertification.num = "DUPLICATE" + DateFunctions.getCurrentTime();
		duplicateCertification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		duplicateCertification.effectiveTo = DateFunctions.getToday();

		message1 = "Certification Number is required. Please specify the Certification Number.";
		message2 = "Effective From Date is required. Please specify the Effective From Date.";
		message3 = "Effective To Date must be on or after Effective From Date. Please re-enter.";
		message4 = "A Certification with the same Certification Type, Certification Number, Effective From Date and Effective To Date already exists for this Customer. Please verify.";
	}

	private void verifyErrorMessage(String actuMessage, String expMessage){
		logger.info("Verify whether the error message is displayed correctly or not.");

		if(!expMessage.equalsIgnoreCase(actuMessage)) {
			pass &= false;
			logger.error("Error message - Expected message should be " + expMessage
					+ " , but acutally is " + actuMessage);
		} else {
			logger.info("Error message - " + actuMessage + " is displayed correctly.");
		}
	}
}
