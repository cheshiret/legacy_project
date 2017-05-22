package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.add;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: This case is used to verify the System works correctly or not when user cancels or proceeds the process of adding certification
 * @Preconditions: Need an existing customer.
 * @SPEC: <<Add Customer Certification.UCS>>
 * @Task#: AUTO-709
 * 
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class CancelAndSuccess extends LicenseManagerTestCase {
	private Customer customer = new Customer();
	private Certification certification = new Certification();
	private LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
	private long realAddTime;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		
		//user confirm the entries and cancels, system aborts the add Customer Certification process
		this.cancelOrProceed(false);
		
		//user confirm the entries and proceeds, system should add a new certification record
		this.cancelOrProceed(true);
		realAddTime = DateFunctions.getCurrentTime();
		Certification actualCertification = lm.getCertificationInfo(certification.id);
		boolean runningResult = this.verifyCertificationDetailsInfo(certification, actualCertification);
		
		//clean up environment
		lm.removeCustomerCertification(certification.id, true);
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("Checkpoints are NOT all passed, please refer the log for detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		customer.customerClass = "Individual";
		customer.fName = "QA-Advanced1";
		customer.lName = "TEST-Advanced1";
		customer.identifier.identifierType = "Tax ID";
		customer.identifier.identifierNum = "111111";
		
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTO" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = certification.effectiveFrom;
		certification.creationLocation = login.location.split("/")[1].trim();
		certification.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	/**
	 * Verify the operation(cancel or proceed) result
	 * @param isSuccess
	 */
	private void cancelOrProceed(boolean isSuccess) {
		certification.id = lm.addCustomerCertification(certification, isSuccess);
		boolean actualResult = certificationPage.checkCertificationRecordExists(certification);
			
		if(isSuccess != actualResult) {	
			if(isSuccess) {
				throw new ErrorOnPageException("User proceeds. System shold add a new Customer Certification record.");
			} else {
				throw new ErrorOnPageException("User cancels. System should abort the add Customer Certification process.");
			}
		} else {
			logger.info((isSuccess ? "Add" : "Cancel") + " successfully.");
		}
	}
	
	/**
	 * Verify whether the actual added certification detail information match with expected
	 * @param expectedCertification
	 * @param actualCertification
	 * @return
	 */
	private boolean verifyCertificationDetailsInfo(Certification expectedCertification, Certification actualCertification) {
		boolean result = true;
		
		logger.info("Verify whether the actaul certification details are correct with expected.");
		if(!actualCertification.status.equals("Active")) {
			logger.error("Certification Status isn't Active");
			result &= false;
		} else {
			logger.info("Certification Status is really Active.");
		}
		
		if(!actualCertification.type.equals(expectedCertification.type)) {
			logger.error("Certification Type doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Type is correct.");
		}
		
		if(!actualCertification.num.equals(expectedCertification.num)) {
			logger.error("Certification Number doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Number is correct.");
		}
		
		if(DateFunctions.compareDates(actualCertification.effectiveFrom, expectedCertification.effectiveFrom) != 0) {
			logger.error("Certification Effective From Date doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Effective From Date is correct.");
		}
		
		if(DateFunctions.compareDates(actualCertification.effectiveTo, expectedCertification.effectiveTo) != 0) {
			logger.error("Certification Effective To Date doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Effective To Date is correct.");
		}
		
		if(!actualCertification.creationLocation.equals(expectedCertification.creationLocation)) {
			logger.error("Certification Creation Location doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Creation Location is correct.");
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("E MMM d yyyy hh:mm aa zz");
		Date actualDate = null;
		try {
			actualDate = formatter.parse(actualCertification.creationDateTime + " EDT");
		} catch(ParseException e) {
			throw new ErrorOnDataException(e);
		}
		
		//allow there is 5 minutes relative errors between creation date time from UI and from case
		if((actualDate.getTime() - realAddTime)/1000 > 300) {
			logger.error("Certification Creation Date/Time doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Creation Date/Time is correct.");
		}
		
		if(!actualCertification.creationUser.equals(expectedCertification.creationUser)) {
			logger.error("Certification Creation User doesn't match.");
			result &= false;
		} else {
			logger.info("Certification Creation User is correct.");
		}
		
		return result;
	}
}
