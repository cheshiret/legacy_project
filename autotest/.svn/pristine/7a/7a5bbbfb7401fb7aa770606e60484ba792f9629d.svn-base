package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.instructor;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (Update code due to DEFECT-61768 is won't fix because of the zip "12345-0001" which has only one address) 
 * Basic workflow to edit instructor and then to verify 
 * 1. Instructor info in list page;
 * 2. Instructor info in details page;
 * 3. Instructor address info;
 * 4. Instructor identifier info;
 * 5. Instructor change history info.
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Edit Instructor [TC:110233]
 * @Task#:Auto-2047
 * 
 * @author SWang
 * @Date  Mar 18, 2014
 */
public class EditInstructor extends LicenseManagerTestCase {
	private LicMgrInstructorChangeHistoryPage instructorChangeHistoryPg = LicMgrInstructorChangeHistoryPage.getInstance();
	private LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
	private LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
	private Customer cust, custEdit;
	private ChangeHistory ch1, ch2, ch3, ch4;
	private String user, location;
	
	public void execute() {
		lm.deleteCustAllIdentExceptCustNum(schema, cust.email);
		lm.deleteCustAllIdentExceptCustNum(schema, custEdit.email);
		lm.loginLicenseManager(login);

		//Add instructor
		lm.gotoActivityPgFromHomePg();
		lm.gotoInstructorListPgFromActivityPg();
		lm.addInstructor(cust);
		cust.instructorNum = lm.getCustomerNumByEmail(cust.email, schema);
		custEdit.instructorNum =  cust.instructorNum;
		cust.identifier.id = lm.getCustIdenID(schema, IDEN_MS_DRIVERS_LICENSE_ID, cust.email);

		//Edit instructor
		instructorListPg.searchInstructor(cust);
		lm.editInstructor(custEdit);

		instructorListPg.searchInstructor(custEdit);
		instructorListPg.verifyInstructorInListPg(Arrays.asList(custEdit));

		//Verify in instructor details page
		lm.gotoInstructorDetailsPgFromInstructorListPg(custEdit.instructorNum);
		instructorDetailsPg.verifyInstructorInfo(custEdit);

		//Verify change history
		lm.gotoInstructorChangeHistoryPgFromInstructorDetailsPg();
		instructorChangeHistoryPg.verifyInstructorChangeHistoryPgInfo(Arrays.asList(ch2, ch3, ch4)); //ch1, 

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);

		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Added Instructor
		cust = new Customer();
		cust.fName = "FName"+DateFunctions.getCurrentTime();
		cust.mName = "MName"+DateFunctions.getCurrentTime();
		cust.lName = "LName"+DateFunctions.getCurrentTime();
		cust.suffix = "JR";
		cust.dateOfBirth = "Jan 01 "+DateFunctions.getYearAfterCurrentYear(-16);
		cust.instructorType = "InstructorType_C";
		cust.hPhone = "4088122485";
		cust.bPhone = "4088654789";
		cust.mPhone = "4088000000";
		cust.fax = "02145689";
		cust.email = "EditInstructor@basic.com";
		cust.phoneContact = "Home Phone";
		cust.contactTime = "Evening";

		cust.physicalAddr.address ="physical Address";
		cust.physicalAddr.supplementalAddr = "physical supplemental Address";
		cust.physicalAddr.city = "Virginia Beach";
		cust.physicalAddr.state="Virginia";
		cust.physicalAddr.county="Virginia Beach city";
		cust.physicalAddr.zip = "23456";
		cust.physicalAddr.country="United States";

		cust.mailAddrAsPhy = false;
		cust.mailingAddr.address ="mailing Address";
		cust.mailingAddr.supplementalAddr = "mailingAddr supplemental Address";
		cust.mailingAddr.city = "Greig";
		cust.mailingAddr.state="New York";
		cust.mailingAddr.county="Lewis";
		cust.mailingAddr.zip = "13345";
		cust.mailingAddr.country="United States";	

		cust.identifier.state = OrmsConstants.VERIFIED_STATUS; //ACTIVE_STATUS; DEFECT-64258
		cust.identifier.identifierType = OrmsConstants.IDENT_TYPE_MDL;
		cust.identifier.identifierNum = "TC110233";

		//Edited instructor
		custEdit = new Customer();
		custEdit.fName = "FName"+DateFunctions.getCurrentTime();
		custEdit.mName = "MName"+DateFunctions.getCurrentTime();
		custEdit.lName = "LName"+DateFunctions.getCurrentTime();
		custEdit.suffix = "SR";
		custEdit.dateOfBirth = cust.dateOfBirth; //This field can't be edited. "Jan 01 "+DateFunctions.getYearAfterCurrentYear(-17);
		custEdit.instructorType = "InstructorType_D";
		custEdit.hPhone = "4088122486";
		custEdit.bPhone = "4088654790";
		custEdit.mPhone = "4088000001";
		custEdit.fax = "02145690";
		custEdit.email = "EditInstructor@basicE.com";
		custEdit.phoneContact = "Work Phone";
		custEdit.contactTime = "Business Hour - Morning";

		custEdit.physicalAddr= cust.physicalAddr;

		custEdit.status="Active";
		custEdit.creationApplication = "LicenseManager";
		custEdit.creationDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "E MMM dd yyyy");
		custEdit.creationUser = DataBaseFunctions.getLoginUserName(login.userName);

		custEdit.identifier.state = "Mississippi";
		custEdit.identifier.country = StringUtil.EMPTY;
		custEdit.identifier.creationApp = cust.creationApplication;
		custEdit.identifier.createDate = cust.creationDate;
		custEdit.identifier.createUser = cust.creationUser;

		//Change history
		user = DataBaseFunctions.getLoginUserName(login.userName);
		location = login.location.split("/")[1].trim();

//		ch1 = new ChangeHistory();
//		ch1.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
//		ch1.object = "Update Instructor";
//		ch1.action = "Update";
//		ch1.field = "Date of Birth";
//		ch1.oldValue = DateFunctions.formatDate(cust.dateOfBirth, "MM/dd/yyyy"); //"01/01/1998";
//		ch1.newValue = DateFunctions.formatDate(custEdit.dateOfBirth, "MM/dd/yyyy"); //"01/01/1997";
//		ch1.user = user;
//		ch1.location = location;

		ch2 = new ChangeHistory();
		ch2.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch2.object = "Update Instructor";
		ch2.action = "Update";
		ch2.field = "Email";
		ch2.oldValue = cust.email;
		ch2.newValue = custEdit.email;
		ch2.user = user;
		ch2.location = location;

		ch3 = new ChangeHistory();
		ch3.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch3.object = "Update Instructor";
		ch3.action = "Update";
		ch3.field = "Instructor Type";
		ch3.oldValue = cust.instructorType;
		ch3.newValue = custEdit.instructorType;
		ch3.user = user;
		ch3.location = location;

		ch4 = new ChangeHistory();
		ch4.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch4.object = "Update Instructor";
		ch4.action = "Update";
		ch4.field = "Home Phone";
		ch4.oldValue = cust.hPhone;
		ch4.newValue = custEdit.hPhone;
		ch4.user = user;
		ch4.location = location;
	}
}
