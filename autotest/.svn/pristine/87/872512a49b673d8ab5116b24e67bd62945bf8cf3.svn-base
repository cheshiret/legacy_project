package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.education;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify change history when operation customer education:
 * @Preconditions: No
 * @SPEC: Have Individual customer with fName: QA-Customer5, mName:QaTest-CusotmerProfile-5,lName:TEST-Profile5 and date of birth is 'Jan 11 1979'
 * @Task#: AUTO_568
 * 
 * @author SWang
 * @Date  May 24, 2011
 */
public class VerifyChangeHistoryForCustEducation extends LicenseManagerTestCase{
	private LicMgrCustomerEducationPage custEducationPg = LicMgrCustomerEducationPage.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerChangeHistoryPage custChangeHistory = LicMgrCustomerChangeHistoryPage.getInstance();
	private ChangeHistory changeH = new ChangeHistory();
	private Education edu = new Education();
	private Education[] edus = new Education[1];
	private Education edu_1 = new Education();

	public void execute() {
		//Login license manager and goto identifiers page
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Go to customer details page
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		custDetailsPg.changeDateOfBirth(cust.dateOfBirth);

		//Add customer education
		lm.manageEducationsForCustomer("Add", null, edu);
		//No need 
		//this.getChangeHistoryDate();
		edu.id = custEducationPg.getEducationID(edu.educationType, edu.educationNum);
		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-"+edu.educationType +"("+edu.id+")","Add","","","",edu.createUser,changeH.location);
		verifyChangeHistoryForCustEducation(changeH);

		//Update "Active" to "Inactive"
		lm.manageEducationsForCustomer("Deactivate", null, edu);
		//this.getChangeHistoryDate();
		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-"+edu.educationType +" ("+edu.id+")","Update","Status","Active","Inactive",edu.createUser,changeH.location);
		verifyChangeHistoryForCustEducation(changeH);

//		//Update "Manually Verified" to "Inactive"  -----DEFECT-33120, won'r fix
//		lm.manageEducationsForCustomer("Manually Verify", null, edu);
//		lm.manageEducationsForCustomer("Deactivate", null, edu);
//		this.getChangeHistoryDate();
//		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-HuntEducation("+edu.id+")","Update","Status","Manually Verify","Inactive",edu.createUser,changeH.location);
//		verifyChangeHistoryForCustEducation(changeH);

		//Edit customer education
		edu_1.educationNum = "377333764";// verified
		edu_1.state = "Mississippi";
		edu_1.country = "United States";
		edus[0] = edu_1;
		lm.manageEducationsForCustomer("Edit", edus, edu);
		//this.getChangeHistoryDate();
		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-HuntEducation ("+edu.id+")","Update","Education Number",edu.educationNum,edu_1.educationNum,edu.createUser,changeH.location);
		verifyChangeHistoryForCustEducation(changeH);

		//Update "Verified" to "Inactive"
		edu.educationNum = "377333764";// verified
		lm.manageEducationsForCustomer("Activate", null, edu);
		lm.manageEducationsForCustomer("Deactivate", null, edu);
		//this.getChangeHistoryDate();
		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-HuntEducation ("+edu.id+")","Update",/*"Education Status"*/"Status","Verified","Inactive",edu.createUser,changeH.location);
		verifyChangeHistoryForCustEducation(changeH);

		//Remove customer education
		lm.manageEducationsForCustomer("Remove", null, edu);
		//this.getChangeHistoryDate();
		changeH.setChangeHistoryInfo(changeH.changeDate,"Customer Education-HuntEducation ("+edu.id+")","Update","Removed","No","Yes",edu.createUser,changeH.location);
		verifyChangeHistoryForCustEducation(changeH);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.customerClass = "Individual";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer7";
		cust.mName = "QaTest-CusotmerProfile-7";
		cust.lName = "TEST-Profile7";
		cust.dateOfBirth = "Jan 11 1979";

		//Customer education info
		edu.educationType = "HuntEducation";
		edu.educationNum = "111111145";// active
		edu.state = "Mississippi";
		edu.country = "United States";
		edu.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		changeH.location = login.location.split("/")[1];
		changeH.changeDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(""));
	}

	private void verifyChangeHistoryForCustEducation(ChangeHistory expected){
		LicMgrCustomerDetailsPage custDetails = LicMgrCustomerDetailsPage.getInstance();
		custEducationPg.clickChangeHistory();
		ajax.waitLoading();
		custChangeHistory.waitLoading();
		//Verify change history information
		List<ChangeHistory> changeHistoryInfo = custChangeHistory.getChangeHistoryInfo();
		ChangeHistory actual = null;
		for(ChangeHistory c : changeHistoryInfo) {
			if(c.object.equalsIgnoreCase(expected.object) && c.action.equalsIgnoreCase(expected.action)) {
				actual = c;
				break;
			}
		}
		
		if(!actual.equals(expected)){
			throw new ErrorOnDataException("Change History information for education is incorrect.");
		}
		//Return Customer education page
		custChangeHistory.clickReturnToCustomerDetail();
		ajax.waitLoading();
		custDetails.waitLoading();
		custDetails.clickEducationTab();
		ajax.waitLoading();
		custEducationPg.waitLoading();
	}
}
