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
 * @Description:This case is used to verify edit customer certification business rule.
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
public class VerifyBusinessRules extends LicenseManagerTestCase{
	private Certification certification = new Certification();
	private boolean pass = true;
	private String actualMessage = "", message1 = "", message2 = "";
	private LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage.getInstance();
	private LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget.getInstance();

	public void execute() {
		//Login license manager		
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoCertificationFromCustomerDetailsPg();
		
		//Add certification
		certification.id = lm.addCustomerCertification(certification, true);
		lm.gotoEditCertificationWidget(certification.id);
		this.verifyBusinessRule();
		editCertificationWidget.clickCancel();
		custCertificationPg.waitLoading();	
		
		//verify expired certification business rule
		String expriedCertificationID = custCertificationPg.getExpiredCertificationID();
		//verify activate expired certification business rule
		lm.activateOrDeactivateCertification("Activate", expriedCertificationID);
		message1 = "The Status of Certification with ID " + expriedCertificationID + " cannot be changed from Expired to Active.";
		actualMessage = custCertificationPg.getErrorMessage();
		this.verifyErrorMessage(actualMessage, message1);
		
		//verify deactivate expired certification business rule
		lm.activateOrDeactivateCertification("Deactivate",expriedCertificationID);
		message2 = "The Status of Certification with ID " + expriedCertificationID + " cannot be changed from Expired to InActive.";
		actualMessage = custCertificationPg.getErrorMessage();
		this.verifyErrorMessage(actualMessage, message2);
				
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
		certification.num = "BUSINESS" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = DateFunctions.getToday();	
	}
	
	private void verifyBusinessRule(){		
		logger.info("Verify business rules");
		
		if(editCertificationWidget.checkCertificationIDIsEditable()){
			pass &= false;
			logger.error("Certification ID should not editable.");
		}else {
			logger.info("Verify certification ID not editable success.");
		}
		
		if(editCertificationWidget.checkCertificationStatusIsEditable()){
			pass &= false;
			logger.error("Certification status should not editable.");
		}else {
			logger.info("Verify certification status not editable success.");
		}
		
		if(editCertificationWidget.checkCertificationTypeIsEditable()){
			pass &= false;
			logger.error("Certification type should not editable.");
		}else {
			logger.info("Verify certification type not editable success.");
		}
		
		if(editCertificationWidget.checkCreateLocationIsEditable()){
			pass &= false;
			logger.error("Certification create location should not editable.");
		}else {
			logger.info("Verify certification create location not editable success.");
		}
		
		if(editCertificationWidget.checkCreateTimeIsEditable()){
			pass &= false;
			logger.error("Certification creation time should not editable.");
		}else {
			logger.info("Verify certification creation time not editable success.");
		}
		
		if(editCertificationWidget.checkCreateUserIsEditable()){
			pass &= false;
			logger.error("Certification creation user should not editable.");
		}else {
			logger.info("Verify certification creation user not editable success.");
		}	
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
