package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.certification;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditCertificationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case is used to add a new customer certification.
 * @Preconditions: Need an existing customer.
 * @SPEC: Add Customer Certification.UCS && Remove Customer Certification.UCS
 * @Task#: Auto-843
 * 
 * @author nding1
 * @Date  2011-12-23
 */
public class AddAndRemoveCustomerCertification extends LicenseManagerTestCase {
	private Customer customer = new Customer();
	private Certification certification = new Certification();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(customer);
		
		// view Customer Certification
		lm.gotoCertificationFromCustomerDetailsPg();
		
		// add new Certification
		certification.id = lm.addCustomerCertification(certification);

		// verify certification info on certifications page.
		this.verifyCertificationInfo();
			
		// click link to view details of the certification and verify each field
		this.verifyCertificationDetailInfo();
		
		// remove
		lm.removeCustomerCertification(certification.id, true);
		
		// verify
		this.verifyRemovedCertification();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		customer.customerClass = "Individual";
		customer.identifier.identifierType = "Green Card";
		customer.identifier.identifierNum = "AutoBasic00001";

		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTO" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = DateFunctions.getDateAfterToday(2);
		certification.creationLocation = login.location.split("/")[1].trim();
		certification.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		certification.creationDateTime = DateFunctions.getToday();
	}
	
	/**
	 * verify certification info on certifications page.
	 */
	private void verifyCertificationInfo() {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		
		logger.info("Verify the details info of the certification on certifications page.");
		custCertificationPg.verifyCertificationInfo(certification);
	}
	
	/**
	 * verify if the certification was removed successfully or not.
	 * @param listSizeOld The number of certification before moving.
	 */
	private void verifyRemovedCertification() {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		
		logger.info("verify if the certification was removed successfully or not.");
		
		List<String> idList = custCertificationPg.getColumnValues("ID");
		
		if(idList.contains(certification.id)) {
			throw new ErrorOnPageException("The certification with Id is:"+"ID"+"has not been removed!");
		}
		
		logger.info("The certification is moved successfully.");
	}
	
	/**
	 * verify certification info on edit certification widget.
	 * 
	 */
	private void verifyCertificationDetailInfo() {
		logger.info("verify certification info on edit certification widget.");

		// click id to go to edit widget page
		lm.gotoEditCertificationWidget(certification.id);

		LicMgrEditCertificationWidget editWidget = LicMgrEditCertificationWidget.getInstance();
		Certification certificationOnPg = editWidget.getCertificationInfo();
		boolean result = editWidget.verifyCertificationDetailsInfo(certification, certificationOnPg);

		if(!result) {
			throw new ErrorOnPageException("Not all the check points were passed, please check the log.");
		}
		
		editWidget.clickCancel();
		ajax.waitLoading();
	}
}
