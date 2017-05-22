package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.education;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Verify editing customer education function
 * @Preconditions: An existed customer
 * @SPEC: Edit Customer Education.UCS
 * @Task#: AUTO-739
 * 
 * @author SWang
 * @Date  Dec 22, 2011
 */
public class EditEducation extends LicenseManagerTestCase {
	private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
	private Education education = new Education();
	private Education educationEdit = new Education();
	private Education[] edus;
	private TimeZone zone;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Education");

		//Remove education 
		this.RemoveCustEducation();

		//Add and edit customer education
		this.addCustEducation();
		edus = new Education[]{educationEdit};
		lm.manageEducationsForCustomer("Edit", edus, education);

		//Get education ID and Create date 
		educationEdit.createDate = DateFunctions.getToday("MMM dd,yyyy hh:mm a z", zone);
		educationEdit.id = custEducation.getEducationID(educationEdit.educationType, educationEdit.educationNum);

		//View customer education list
		custEducation.verifyEducatioList(educationEdit);

		//View customer education detail
		lm.verifyCustomerEducationDetailInfo(educationEdit);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		/**
		 * Don't use this customer, will conflict with AddEducation.java
		 */
//		customer.customerClass = "Individual";
//		customer.fName = "QA-Education";
//		customer.lName = "TEST-Operate";
//		customer.dateOfBirth = "Dec 22 2011";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1986/08/12";
		cust.fName = "QA-TransferRule6";
		cust.lName = "TEST-TransferRule6";

		education.status = "Active";
		education.educationType = "HuntEducation";
		education.educationNum = "3C7X4D6W";
		education.state = "Mississippi";
		education.country = "United States";
		education.createApp = "LicenseManager";
		education.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		educationEdit.status = education.status;
		educationEdit.educationType = education.educationType;
		educationEdit.educationNum = "C3X7D4W6";
		educationEdit.state = "Alberta";
		educationEdit.country = "Canada";
		educationEdit.createApp = education.createApp;
		educationEdit.createUser = education.createUser;

		zone=DataBaseFunctions.getContractTimeZone("");
	}

	private void RemoveCustEducation() {
		boolean existed = custEducation.checkEducationExists(educationEdit.educationType, educationEdit.educationNum);
		if(existed){
			lm.manageEducationsForCustomer("Remove", null, educationEdit);
		}
	}
	
	private void addCustEducation(){
		boolean existed = custEducation.checkEducationExists(education.educationType, education.educationNum);
		if(!existed){
			lm.manageEducationsForCustomer("Add", null, education);
		}
	}
}
