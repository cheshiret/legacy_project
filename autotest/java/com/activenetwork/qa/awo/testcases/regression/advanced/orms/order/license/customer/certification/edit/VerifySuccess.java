package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.edit;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditCertificationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify edit customer certification success.
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
public class VerifySuccess extends LicenseManagerTestCase{
	private Certification certification = new Certification();
	private Certification editCertification = new Certification();
	private Certification actualCertification = new Certification();
	private ChangeHistory history = new ChangeHistory();
	private LicMgrEditCertificationWidget editCertificationWidget = LicMgrEditCertificationWidget.getInstance();
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
		
		//Edit certification
		this.prepareEditingData();
		editCertification.id = lm.editCustomerCertification(editCertification, true);
		this.verifyCertificationIDNotChange();
		actualCertification  = lm.getCertificationInfo(editCertification.id);
		//verify editing info
		pass &= editCertificationWidget.verifyCertificationDetailsInfo(editCertification, actualCertification);
		
		//deactivate certification
		lm.activateOrDeactivateCertification("Deactivate", editCertification.id);
		editCertification.status = OrmsConstants.INACTIVE_STATUS;
		actualCertification = lm.getCertificationInfo(editCertification.id);
		pass &= editCertificationWidget.verifyCertificationDetailsInfo(editCertification, actualCertification);
		//verify changed history
		history.object = "Customer Certification-" + certification.type + " (" + editCertification.id + ")";
		this.verifyAuditLogger(history);
		
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoCertificationFromCustomerDetailsPg();
		
		//activate certification
		lm.activateOrDeactivateCertification("Activate", editCertification.id);
		editCertification.status = "Active";
		actualCertification = lm.getCertificationInfo(editCertification.id);
		pass &= editCertificationWidget.verifyCertificationDetailsInfo(editCertification, actualCertification);
		//verify changed history
		history.object = "Customer Certification-" + certification.type + " (" + editCertification.id + ")";
		history.oldValue = "InActive";
		history.newValue = OrmsConstants.ACTIVE_STATUS;
		this.verifyAuditLogger(history);
		
		//clean up environment
		lm.removeCustomerCertification(editCertification.id, true);
		
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
		
		certification.status = OrmsConstants.ACTIVE_STATUS;
		certification.type = "Trapper Certification";
		certification.num = "SUCCESS" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getToday();
		certification.effectiveTo = DateFunctions.getDateAfterToday(30);	
		
		history.field = "Status";
		history.action = "Update";
		history.changeDate = DateFunctions.getToday().toString();
		history.oldValue = OrmsConstants.ACTIVE_STATUS;
		history.newValue = "InActive";
		history.user = DataBaseFunctions.getLoginUserName(login.userName);
		history.location = login.location.split("/")[1];
	}
	
	private void prepareEditingData() {
		editCertification = certification;
		editCertification.num = "EDIT" + DateFunctions.getCurrentTime();
		editCertification.effectiveFrom = DateFunctions.getDateAfterToday(-1);	
		editCertification.effectiveTo = DateFunctions.getToday();
		editCertification.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		editCertification.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		editCertification.creationLocation = login.location.split("/")[1];
	}
	
	private void verifyCertificationIDNotChange(){
		logger.info("Verify eidt success.");
		if(!editCertification.id.equals(certification.id)){
			pass &= false;
			logger.error("Certification ID should not change.");
		}else {
			logger.info("Certification ID not changed.");
		}		
	}
	
	private void verifyAuditLogger(ChangeHistory expectHistory){
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage.getInstance();
		LicMgrCustomerChangeHistoryPage changeHistoryPg = LicMgrCustomerChangeHistoryPage.getInstance();
		
		logger.info("Verify Audit Logger.");
		custCertificationPg.clickChangeHistory();
		changeHistoryPg.waitLoading();
		List<ChangeHistory> list= changeHistoryPg.getChangeHistoryInfo();
		ChangeHistory historyOnPage=list.get(0);
		historyOnPage.changeDate = StringUtil.findString(historyOnPage.changeDate, "[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0];
		if(!historyOnPage.equals(expectHistory)){
			throw new ErrorOnPageException("History record is wrong.");
		}
		
		changeHistoryPg.clickReturnToCustomerDetail();
		custCertificationPg.waitLoading();
	}
}
