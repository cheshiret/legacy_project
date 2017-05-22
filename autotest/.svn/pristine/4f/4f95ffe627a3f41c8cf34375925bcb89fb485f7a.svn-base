package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditCertificationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description:This case is used to verify cancel edit customer certification.
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
public class VerifyCancel extends LicenseManagerTestCase{

	private Certification certification = new Certification();
	private Certification editCertification = new Certification();
	private Certification actualCertification = new Certification();
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

		editCertification.num = "EDIT" + DateFunctions.getCurrentTime();
		editCertification.id = certification.id;
		editCertification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		editCertification.effectiveTo = DateFunctions.getToday();

		lm.editCustomerCertification(editCertification, false);
		this.verifyCancelAction();

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
		certification.num = "CANCEL" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-2);
		certification.effectiveTo = DateFunctions.getToday();
		certification.creationLocation = "Mississippi Department of Wildlife, Fisheries, and Parks";
		certification.creationDateTime = DateFunctions.getToday();
		certification.creationUser = DataBaseFunctions.getLoginUserName(login.userName);

	}

	private void verifyCancelAction(){
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage.getInstance();
		LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget.getInstance();

		logger.info("Verify cancel action.");
		if(!custCertificationPg.checkCertificationRecordExists(certification) || custCertificationPg.checkCertificationRecordExists(editCertification)){
			pass &= false;
			logger.error("Cancel Action is failed. Prior certification record should exists.");
		}else {
			logger.info("Cancel Action is correct. Prior certification record exists.");
		}

		//certification info should not changed.
		actualCertification = lm.getCertificationInfo(editCertification.id);
		pass &= editCertificationWidget.verifyCertificationDetailsInfo(certification, actualCertification);
	}
}
