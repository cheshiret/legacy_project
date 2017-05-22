package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.remove;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This cases is used to verify remove customer certification
 * @Preconditions:Need run support script AddCustomerProfile info, when changed schedule
 * customer profile1:
 *      cust.fName = "QA-Remove1";
 *		cust.mName = "QaTest-Certification1";
 *		cust.lName = "TEST-Active1";
 * @SPEC:Remove customer certificaiton.USC
 * @Task#:Auto-718
 * 
 * @author VZhang
 * @Date  Aug 17, 2011
 */
public class VerifyByCustStatus extends LicenseManagerTestCase{
	private LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage.getInstance();
	private LicMgrCustomerChangeHistoryPage changeHistoryPg = LicMgrCustomerChangeHistoryPage.getInstance();
	private Certification certification = new Certification();
	private ChangeHistory history = new ChangeHistory();
	private boolean pass = true;
	
	public void execute() {
		//Login license manager		
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		
		cust.identifier.identifierNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.identifier.identifierType, cust.identifier.identifierNum, cust.customerClass);
		
		cust.status = certificationPg.getCustomerStatus();
		if(!cust.status.equals("Active")){
			lm.editCustomerStatus(cust);
		}
		
		lm.gotoCertificationFromCustomerDetailsPg();
		//Add certification
		certification.id = lm.addCustomerCertification(certification, true);
		
		/**
		 * Verify remove certification when customer's status is 'Deceased'
		 */
		cust.status = "Deceased";
		lm.editCustomerStatus(cust);
		lm.gotoCertificationFromCustomerDetailsPg();
		this.verifyCertificationRecordWhetherExists(certification, true);
		this.verifyCertificationCannotBeOperated();
		
		/**
		 * Verify remove certification when customer's status is 'Inactive'
		 */
		//update customer's status Inactive
		cust.status = "Inactive";
		lm.editCustomerStatus(cust);
		lm.gotoCertificationFromCustomerDetailsPg();
		
		//verify cancel action and message
		String expectMessage ="Once removed, the Certification with ID " + certification.id + " will no longer be visible. Proceed with the remove?";
		String actualMessage = lm.removeCustomerCertification(certification.id, false);
		this.verifyConfrimMessage(expectMessage, actualMessage);
		this.verifyCertificationRecordWhetherExists(certification, true);
		
		//verify remove success and message
		history.changeDate = DateFunctions.getToday();
		history.object = "Customer Certification-" + certification.type + " (" + certification.id + ")";
		actualMessage = lm.removeCustomerCertification(certification.id, true);
		this.verifyConfrimMessage(expectMessage, actualMessage);
		this.verifyCertificationRecordWhetherExists(certification, false);
		this.verifyAuditLog(history);
		
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCuscomerDetailsFromSearchPg(cust.identifier.identifierType, cust.identifier.identifierNum, cust.customerClass);
		
		
		/**
		 * Verify remove certification when customer's status is 'Active'
		 */
		cust.status = "Active";
		lm.editCustomerStatus(cust);
		lm.gotoCertificationFromCustomerDetailsPg();
		certification.id = lm.addCustomerCertification(certification, true);
		
		//verify cancel action and message
		expectMessage ="Once removed, the Certification with ID " + certification.id + " will no longer be visible. Proceed with the remove?";
		actualMessage = lm.removeCustomerCertification(certification.id, false);
		this.verifyConfrimMessage(expectMessage, actualMessage);
		this.verifyCertificationRecordWhetherExists(certification, true);
		
		//verify remove success and message
		history.changeDate = DateFunctions.getToday();
		history.object = "Customer Certification-" + certification.type + " (" + certification.id + ")";
		actualMessage = lm.removeCustomerCertification(certification.id, true);
		this.verifyConfrimMessage(expectMessage, actualMessage);
		this.verifyCertificationRecordWhetherExists(certification, false);
		this.verifyAuditLog(history);
		
		if(!pass){
			throw new ErrorOnPageException("Some check point is not correct, please check error log.");
		}
		
		lm.logOutLicenseManager();			
	}

	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "INDIVIDUAL";
		cust.identifier.identifierType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Remove1";
		cust.mName = "QaTest-Certification1";
		cust.lName = "TEST-Active1";
		cust.dateOfBirth = "Jan 01 1980";
		
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "REMOVE" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getToday();
		certification.effectiveTo = DateFunctions.getDateAfterToday(30);
		
		history.field="Removed";
		history.action="Update";
		history.oldValue = "No";
		history.newValue = "Yes";
		history.user="Test-Auto, QA-Auto";
		history.location=login.location.split("/")[1];		
	}
	
	private void verifyConfrimMessage(String expectMessage, String actualMessage) {
		logger.info("Verify confrimation message.");
		
		if(!expectMessage.equals(actualMessage)){
			pass &= false;
			logger.error("Expected message should be " + expectMessage 
					+ ", but actually is " + actualMessage);
		}else {
			logger.info("Message " + actualMessage + " is correct.");
		}
	}
	
	private void verifyCertificationRecordWhetherExists(Certification certification, boolean isExists){
		logger.info("Verify certificaiton record whether exists.");
		
		if(isExists){
			if(!certificationPg.checkCertificationRecordExists(certification)){
				pass &= false;
				logger.error("Certification records " + certification.id + " should exists.");
			}else {
				logger.info("Verify certification records " + certification.id + " exists successfully.");
			}
		}else{
			if(certificationPg.checkCertificationRecordExists(certification)){
				pass &= false;
				logger.error("Certification records " + certification.id + " should not exists.");
			}else {
				logger.info("Verify certification records " + certification.id + " not exists successfully.");
			}
		}		
	}
	
	private void verifyAuditLog(ChangeHistory expectHistory){
		logger.info("Verify change history.");
		
		certificationPg.clickChangeHistory();
		changeHistoryPg.waitLoading();
		List<ChangeHistory> list= changeHistoryPg.getChangeHistoryInfo();
		boolean find = false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).object.equals(expectHistory.object)&&list.get(i).field.equals(expectHistory.field)){
				ChangeHistory historyOnPage=list.get(i);
				historyOnPage.changeDate = StringUtil.findString(historyOnPage.changeDate, "[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0];
				if(!historyOnPage.equals(expectHistory)){
					throw new ErrorOnPageException("History record is wrong.");
				}
				find = true;
				break;
			}
		}
		if(!find){
			throw new ErrorOnPageException("Not Found Given history for "+expectHistory.field);
		}
	}
	
	private void verifyCertificationCannotBeOperated(){
		logger.info("Verify certification can not be operated.");
		
		if(certificationPg.isAddCertificationButtonEnabled()){
			pass &= false;
			logger.error("Add Certification button should not enabled.");
		}
		
		if(certificationPg.isAllCertificationCheckBoxesEnabled()){
			pass &= false;
			logger.error("All check box should not enabled.");
		}
		
		if(certificationPg.isActivateButtonEnabled()){
			pass &= false;
			logger.error("Activate button should not enabled.");
		}
		
		if(certificationPg.isDeactivateButtonEnabled()){
			pass &= false;
			logger.error("Deactivate button should not enabled.");
		}
		
		if(certificationPg.isRemoveButtonEnabled()){
			pass &= false;
			logger.error("Remove button should not enabled.");
		}
	}
}
