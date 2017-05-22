package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.instructor;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorAddressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (Update code due to DEFECT-61768 is won't fix because of the zip "12345-0001" which has only one address) 
 * Basic workflow to add instructor to existing customer and then to verify 
 * 1. Instructor info in list page;
 * 2. Instructor info in details page;
 * 3. Instructor address info;
 * 4. Instructor identifier info;
 * 5. Instructor change history info.
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * Add an Instructor from an existing customer [TC:110098]
 * View Instructor History [TC:110616]
 * @Task#:Auto-2047
 * 
 * @author SWang
 * @Date  Mar 14, 2014
 */
public class AddInstructorToExistingCust extends LicenseManagerTestCase {
	private LicMgrInstructorIdentifiersPage instructorIdenListPg = LicMgrInstructorIdentifiersPage.getInstance();
	private LicMgrInstructorChangeHistoryPage instructorChangeHistoryPg = LicMgrInstructorChangeHistoryPage.getInstance();
	private LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
	private LicMgrInstructorDetailsPage instructorDetailsPg = LicMgrInstructorDetailsPage.getInstance();
	private LicMgrInstructorAddressesPage instructorAddressPg = LicMgrInstructorAddressesPage.getInstance();
	private LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
	private Customer cust = new Customer();
	private ChangeHistory ch1, ch2;
	private String user, location;

	public void execute() {
		lm.deleteCustAllIdentExceptCustNum(schema, cust.email);
		lm.loginLicenseManager(login);

		//Add new customer
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPg);
		cust.custNum = lm.finishAddOrEditCustomer();
		cust.instructorNum = cust.custNum;
		
		//Add instructor to added customer
		lm.gotoActivityPgFromHomePg();
		lm.gotoInstructorListPgFromActivityPg();
		lm.addInstructor(cust);
		cust.identifier.id = lm.getCustIdenID(schema, IDEN_MS_DRIVERS_LICENSE_ID, cust.email);

		//Verify instructor in list page
		instructorListPg.searchInstructor(cust);
		instructorListPg.verifyInstructorInListPg(Arrays.asList(cust));

		//Verify in instructor details page
		lm.gotoInstructorDetailsPgFromInstructorListPg(cust.instructorNum);
		instructorDetailsPg.verifyInstructorInfo(cust);

		//Verify addresses
		instructorAddressPg.verifyPhyAddressesInfo(cust);
		instructorAddressPg.verifyMailingAddressesInfo(cust);
		instructorAddressPg.verifyAlternateAddressesInfo(cust);

		//Verify identifier
		lm.gotoInstructorIdenListFromInstructorDetailsPg();
		instructorIdenListPg.verifyIdentifierList(cust.identifier);

		//Verify change history
		lm.gotoInstructorChangeHistoryPgFromInstructorDetailsPg();
		instructorChangeHistoryPg.verifyInstructorChangeHistoryPgInfo(Arrays.asList(ch1, ch2));

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);

		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		//Instructor
		cust.fName = "FName"+DateFunctions.getCurrentTime();
		cust.mName = "MName"+DateFunctions.getCurrentTime();
		cust.lName = "LName"+DateFunctions.getCurrentTime();
		cust.suffix = "JR";
		cust.dateOfBirth = "Jan 01 "+DateFunctions.getYearAfterCurrentYear(-16);
		cust.instructorType = "InstructorType_B";
		cust.hPhone = "4088122485";
		cust.bPhone = "4088654789";
		cust.mPhone = "4088000000";
		cust.fax = "02145689";
		cust.email = "AddInstructorToExistingCust@basic.com";
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

		cust.status="Active";
		cust.creationApplication = "LicenseManager";
		cust.creationDate = DateFunctions.formatDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), "E MMM dd yyyy");
		cust.creationUser = DataBaseFunctions.getLoginUserName(login.userName);

		//Identifier
		cust.identifier.status = OrmsConstants.VERIFIED_STATUS; //ACTIVE_STATUS; DEFECT-64258
		cust.identifier.identifierType = OrmsConstants.IDENT_TYPE_MDL;
		cust.identifier.identifierNum = "TC110098";
		cust.identifier.state = "Mississippi";
		cust.identifier.country = StringUtil.EMPTY;
		cust.identifier.creationApp = cust.creationApplication;
		cust.identifier.createDate = cust.creationDate;
		cust.identifier.createUser = cust.creationUser;

		//Change history
		user = DataBaseFunctions.getLoginUserName(login.userName);
		location = login.location.split("/")[1].trim();
		
		ch1 = new ChangeHistory();
		ch1.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch1.object = "Add Instructor";
		ch1.action = "Add";
		ch1.field = StringUtil.EMPTY;
		ch1.oldValue = StringUtil.EMPTY;
		ch1.newValue = StringUtil.EMPTY;
		ch1.user = user;
		ch1.location = location;
		
		ch2 = new ChangeHistory();
		ch2.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ch2.object = "Customer Identifier-MS Drivers License";
		ch2.action = "Add";
		ch2.field = StringUtil.EMPTY;
		ch2.oldValue = StringUtil.EMPTY;
		ch2.newValue = StringUtil.EMPTY;
		ch2.user = user;
		ch2.location = location;
	}
}

