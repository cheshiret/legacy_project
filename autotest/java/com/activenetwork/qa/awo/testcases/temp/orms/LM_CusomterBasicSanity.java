/**
 * 
 */
package com.activenetwork.qa.awo.testcases.temp.orms;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName LM_CusomterBasicSanity.java
 * @Date:Dec 24, 2010
 * @Description:
 * @author asun
 */
public class LM_CusomterBasicSanity extends LicenseManagerTestCase {

	Customer cust0 = new Customer();
	List<CustomerIdentifier> newIdentifiers = new ArrayList<CustomerIdentifier>();
	Education edu_1 = null;
	Education edu_2 = null;
	Education editEdu = null;
	Certification c1=null; 
	Certification c2=null; 
    Suspension s1 = null;
    Suspension s2 = null;
    Suspension s3 = null;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// create new customer
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, LicMgrNewCustomerPage.getInstance());
		lm.validateStatus(LicMgrNewCustomerPage.getInstance(), cust.physicalAddr.status);
		lm.finishAddOrEditCustomer();
		this.verifyCustomerInfoInDb(schema, cust);

		// edit customer profile and Address
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		lm.addOrEditCustomerInfo(cust0, LicMgrCustomerDetailsPage.getInstance());
		lm.addOrEditCustomerInfo(cust0, LicMgrCustomerAdressesPage.getInstance());
		lm.finishAddOrEditCustomer();
		this.verifyCustomerInfoInDb(schema, cust0);

		// Edit Identifiers
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		// verify the first identifier is edited
		lm.editIdentifierInfo(cust0.identifiers.get(0));
		lm.addIdentifiersInInditifierPage(newIdentifiers);
		cust0.identifiers.addAll(newIdentifiers);
		lm.verifyIdentifierFromDb(cust0, schema);
		// verify the 1st new identifier is Inactive
		lm.changeIdentifierStatus(cust0.identifiers.get(1).identifierType,cust0.identifiers.get(1).identifierNum, "Deactivate");
		cust0.identifiers.get(1).status = "Inactive";
		lm.verifyIdentifierFromDb(cust0, schema);
		// verify the 1st new identifier is Removed
		lm.changeIdentifierStatus(cust0.identifiers.get(1).identifierType, cust0.identifiers.get(1).identifierNum, "Remove");
		cust0.identifiers.remove(1);
		lm.verifyIdentifierFromDb(cust0, schema);
		// verify the 2nd new identifier is Inactive
		lm.changeIdentifierStatus(cust0.identifiers.get(1).identifierType, cust0.identifiers.get(1).identifierNum, "Deactivate");
		cust0.identifiers.get(1).status="Inactive";
		lm.verifyIdentifierFromDb(cust0, schema);
		// verify the 2nd new identifier is active
		lm.changeIdentifierStatus(cust0.identifiers.get(1).identifierType, cust0.identifiers.get(1).identifierNum, "activate");
		cust0.identifiers.get(1).status = "Active";
		lm.verifyIdentifierFromDb(cust0, schema);
		// verify the 2nd new identifier is Removed
		lm.changeIdentifierStatus(cust0.identifiers.get(1).identifierType, cust0.identifiers.get(1).identifierNum,"Remove");
		cust0.identifiers.remove(1);
		lm.verifyIdentifierFromDb(cust0, schema);

		
		// Edit Educations
		// add and verify Educations
		lm.manageEducationsForCustomer("add", null, edu_1,edu_2);
		lm.verifyEducations(edu_1,edu_2);
		// Manually verify the first education and verify its status
		edu_1.status = "Manually Verified";
		lm.manageEducationsForCustomer("Manually Verify", null, edu_1);
		lm.verifyEducations(edu_1,edu_2);
		// Deactive those two educations
		edu_1.status = "Inactive";
		edu_2.status = "Inactive";
		lm.manageEducationsForCustomer("deactivate", null, edu_1,edu_2);
		lm.verifyEducations(edu_1,edu_2);
		// Activate the 1st education
		edu_1.status = "Active";
		lm.manageEducationsForCustomer("activate", null, edu_1);
		lm.verifyEducations(edu_1,edu_2);
		// Edit the first education
		lm.manageEducationsForCustomer("Edit", new Education[]{editEdu}, edu_1);
		edu_1.educationNum = editEdu.educationNum;
		edu_1.state = editEdu.state;
		lm.verifyEducations(edu_1,edu_2);
		// remove those two educations and verify their status are removed
		edu_1.status = "Removed";
		edu_2.status = "Removed";
		lm.manageEducationsForCustomer("Remove", null, edu_1,edu_2);
		lm.verifyEducations(edu_1,edu_2);

		// Edit Certification
		lm.manageCertificationForCustomer("Add", null, c1, c2);
		lm.verifyCertifications(c1, c2);
		// Deactivate those two certifications
		lm.manageCertificationForCustomer("Deactivate", null, c1, c2);
		c1.status = "InActive";
		c2.status = "InActive";
		lm.verifyCertifications(c1, c2);
		// Activate the first certification
		c1.status = "Active";
		lm.manageCertificationForCustomer("Activate", null,c1);
		lm.verifyCertifications(c1, c2);
		// remove those two certifications
		c1.status = "Removed";
		c2.status = "Removed";
		lm.manageCertificationForCustomer("Remove", null, c1, c2);
		lm.verifyCertifications(c1, c2);

		// Edit Suspensions
		// add two suspensions
		Suspension[] suspensions = lm.manageSuspensions("Add",s1,s2);
		s1=suspensions[0];
		s2=suspensions[1];
		lm.verifyCustomerSuspensions(s1,s2);
		// deactivate those two suspensions
		s1.status = "Inactive";
		s2.status = "Inactive";
		lm.manageSuspensions("Deactivate",s1,s2);
		lm.verifyCustomerSuspensions(s1,s2);
		// activate the 1st suspension
		s1.status = "Active";
		lm.manageSuspensions("Activate",  s1);
		lm.verifyCustomerSuspensions(s1,s2);
		// edit the 1st suspension
		s3.id=s1.id;s1=s3;
		lm.manageSuspensions("Edit", s1);
		lm.verifyCustomerSuspensions(s1,s2);
		// remove those two suspensions
		s1.status = "Removed";
		s2.status = "Removed";
		lm.manageSuspensions("Remove", s1,s2);
		lm.verifyCustomerSuspensions(s1,s2);
		
		//The End
		this.gotoHomePageFromCustomerDetailsPage();
		lm.logOutLicenseManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login Infomation
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "LM - Chart of Accounts/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// Customer profile Infomation
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-" + cust.seq;
		cust.lName = "AUTO-" + cust.seq;
		cust.dateOfBirth = "2000/01/01";
		// customer attribute info
		cust.custGender = "Male";
		cust.ethnicity = "white";
		cust.solicitationIndcator = "No";
		cust.custProfileStatus = "Active";
		// address info
		cust.physicalAddr.addrType = "Physical Address";
		cust.physicalAddr.address = "40 South St";
		cust.physicalAddr.city = "Ballston Spa";
		cust.physicalAddr.state = "New York";
		cust.physicalAddr.county = "Saratoga County";
		cust.physicalAddr.zip = "12020-1029";
		cust.physicalAddr.country = "United States";
		cust.physicalAddr.status = "Valid";
		cust.mailAddrAsPhy = true;
		cust.mailingAddr.addrType = "Mailing Address";
		cust.mailingAddr.address = "40 South St";
		cust.mailingAddr.city = "Ballston Spa";
		cust.mailingAddr.state = "New York";
		cust.mailingAddr.county = "Saratoga County";
		cust.mailingAddr.zip = "12020-1029";
		cust.mailingAddr.country = "United States";
		cust.mailingAddr.status = "Valid";

		// Identifier Info
		CustomerIdentifier idetifier = new CustomerIdentifier();
		idetifier.identifierType = "Non-US DL Number";
		idetifier.identifierNum = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		idetifier.country = "Canada";
		idetifier.status = "Active";
		cust.identifiers.add(idetifier);

		// another customer for edit costomer profile
		cust0.seq = DataBaseFunctions.getEmailSequence() + "";
		cust0.customerClass = "INDIVIDUAL";
		cust0.fName = "QA-" + cust.seq;
		cust0.lName = "AUTO-" + cust.seq;
		cust0.dateOfBirth = "2000/01/01";
		cust0.hPhone = "9052866600";
		cust0.bPhone = "9052866611";
		cust0.mPhone = "9052866622";
		cust0.fax = "9052866633";
		cust0.email = "qaormstest@reserveamerica.com";
		cust0.phoneContact = "Home Phone";
		cust0.contactTime = "Evening";
		// customer attribute info
		cust0.custGender = "Male";
		cust0.ethnicity = "white";
		cust0.solicitationIndcator = "No";
		cust0.custProfileStatus = "Active";

		// address info
		cust0.physicalAddr.addrType = "Physical Address";
		cust0.physicalAddr.address = "40 South St";
		cust0.physicalAddr.city = "Ballston Spa";
		cust0.physicalAddr.state = "New York";
		cust0.physicalAddr.county = "Saratoga County";
		cust0.physicalAddr.zip = "12020-1029";
		cust0.physicalAddr.country = "United States";
		cust0.physicalAddr.status = "Valid";

		cust0.mailAddrAsPhy = false;
		cust0.mailingAddr.addrType = "Mailing Address";
		cust0.mailingAddr.address = "2480 Meadowvale Blvd";
		cust0.mailingAddr.city = "Mississauga";
		cust0.mailingAddr.state = "Ontario";
		cust0.mailingAddr.zip = "L5N8M6";
		cust0.mailingAddr.country = "Canada";
		cust0.mailingAddr.status = "";

		cust0.alternateAddr.addrType = "Alternate Address";
		cust0.alternateAddr.address = "10182 Telesis Ct";
		cust0.alternateAddr.city = "San Diego";
		cust0.alternateAddr.state = "California";
		cust0.alternateAddr.zip = "92121-4777";
		cust0.alternateAddr.county = "San Diego County";
		cust0.alternateAddr.country = "United States";
		cust0.alternateAddr.status = "Valid";
		// Identifier Info
		CustomerIdentifier idetifier0 = new CustomerIdentifier();
		idetifier0.identifierType = "Non-US DL Number";
		idetifier0.identifierNum = "AUTO0000"
				+ DataBaseFunctions.getEmailSequence();
		idetifier0.country = "Canada";
		idetifier.status = "Active";
		idetifier.verifyStatus = "Not Applicable";
		cust0.identifiers.add(idetifier);

		// new identifiers for edit identifier
		CustomerIdentifier identifier_1 = new CustomerIdentifier();
		identifier_1.identifierType = "US Drivers License";
		identifier_1.identifierNum = "AUTO0000"
				+ DataBaseFunctions.getEmailSequence();
		identifier_1.state = "Alabama";
		identifier_1.status = "Active";
		CustomerIdentifier identifier_2 = new CustomerIdentifier();
		identifier_2.identifierType = "OTHER";
		identifier_2.identifierNum = "AUTO0000"
				+ DataBaseFunctions.getEmailSequence();
		identifier_2.country = "Canada";
		identifier_2.status = "Active";
		newIdentifiers.add(identifier_1);
		newIdentifiers.add(identifier_2);

		// Educations Info
		edu_1 = new Education();
		edu_1.status = "Active";
		edu_1.educationType = "HuntEducation";
		edu_1.educationNum = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		edu_1.state = "Mississippi";
		edu_1.country = "United States";
		edu_2 = new Education();
		edu_2.status = "Manually Verified";
		edu_2.educationType = "FishEducation";
		edu_2.educationNum = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		edu_2.state = "Ontario";
		edu_2.country = "Canada";
		editEdu=new Education();
		editEdu.educationNum = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		editEdu.state = "Ohio";

		// Certification info
		c1 = new Certification();
		c1.type = "disabledCert";
		c1.num = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		c1.effectiveFrom = DateFunctions.getDateAfterToday(-100);
		c1.status = "Active";
		c2 = new Certification();
		c2.type = "disabledCert";
		c2.num = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		c2.effectiveFrom = DateFunctions.getDateAfterToday(-100);
		c2.effectiveTo = DateFunctions.getDateAfterToday(100);
		c2.status = "Active";
		

		// Edit suspensions
		s1 = new Suspension();
		s1.status = "Active";
		s1.type = "suspension1";
		s1.beginDate = DateFunctions.getToday();
		s1.datePosted = DateFunctions.getDateAfterToday(-1);// DateFunctions.getToday();
		s1.comment = "QA Auto Sanity test";
		s2 = new Suspension();
		s2.status = "Active";
		s2.type = "suspension2";
		s2.beginDate = DateFunctions.getToday();
		s2.endDate = DateFunctions.getDateAfterToday(30);
		s2.datePosted = DateFunctions.getDateAfterToday(-1);// DateFunctions.getToday();
		s2.comment = "QA Auto Sanity test"
				+ DataBaseFunctions.getEmailSequence();
		
		s3 = new Suspension();
		s3.status = "Active";
		s3.type = "suspension1";
		s3.beginDate = DateFunctions.getDateAfterToday(2);
		s3.endDate = DateFunctions.getDateAfterToday(16);
		s3.datePosted = DateFunctions.getDateAfterToday(-1); // DateFunctions.getToday();
		s3.comment = "QA Auto Sanity test"
				+ DataBaseFunctions.getEmailSequence();
          
	}

	/**
	 * verify customer infomation in db
	 * 
	 * @param schema
	 * @param cust
	 * @Return void
	 * @Throws
	 */
	public void verifyCustomerInfoInDb(String schema, Customer cust) {
		cust.custId = lm.getCustomerIdByCustName(cust.lName, cust.fName, schema);

		logger.info("Verify customer info in DB after a new customer is created...");
		if (cust.custId == null || cust.custId.trim().length() < 1) {
			throw new ItemNotFoundException("The customer last name="
					+ cust.lName + ",fName=" + cust.fName);
		}

		lm.verifyCustomerProfile(cust, schema);
		lm.verifyAddressInDb(cust, schema);
		lm.verifyCustAttrFromDB(cust, schema);
		lm.verifyIdentifierFromDb(cust, schema);
		lm.verifyPhoneEmailContactInDb(cust, schema);
		logger.info("verify sucessfully !");
	}
	
	/**
	 * goto license manager home page from customer details page
	 * @Return void
	 * @Throws
	 */
	public void gotoHomePageFromCustomerDetailsPage(){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage
		.getInstance();
		
		logger.info("goto license manager home page from customer details page....");
		custDetailsPg.clickOK();
		customerPg.waitLoading();
		customerPg.clickHome();
		homePg.waitLoading();
	}
}
