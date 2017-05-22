package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.education.edit;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 1.Verify warning message when the same education has added to the same customer
 *               2.Verify Failed edit customer education
 *               3.Verify all entries are valid
 *               4.Verify successfully edit education
 * @Preconditions: Have Individual customer with fName: QA-Customer5, mName:QaTest-CusotmerProfile-5,lName:TEST-Profile5 and date of birth is '1984-04-30'
 * @SPEC: Edit Customer Education.UCS
 * @Task#: AUTO-569
 * 
 * @author SWang
 * @Date  May 26, 2011
 */
public class EditCustomerEducationForIndividual extends LicenseManagerTestCase{
	private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
	private LicMgrEditEducationPage editEducation = LicMgrEditEducationPage.getInstance();
	private LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
	private Education edu = new Education();
	private Education editEdu = new Education();
	private Education[] editEduList = new Education[1];
	private TimeZone zone = null;
	private boolean pass = true;

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoEducationSubTabFromCustomerDetailsPg();

		//Add education
		lm.manageEducationsForCustomer("Add", null, edu);

		//Get education id
		edu.id = custEducation.getEducationID(edu.educationType, edu.educationNum);

		//		//Verify warning message when the same education has added to the same customer
		//		this.initializeWarnMess(edu_1.id);
		//		lm.gotoCustomerEditEducationWiget(edu_1.educationNum);
		//		this.setupEduInfo(edu_2.educationNum, edu_2.state, edu_2.country);
		//		this.verifyWarnMess(str);

		//Verify all entries are valid
		//Education#
		lm.gotoCustomerEditEducationWiget(edu.educationNum);
		this.setupEduInfo("", edu.state, edu.country);
		this.verifyWarnMess(str1); 
		//State/Province
		this.setupEduInfo(edu.educationNum, "", edu.country);
		this.verifyWarnMess(str2); 
	//if not select contry, then state drop down can not finish loading,so skip below check points
//		//Country
//		this.setupEduInfo(edu.educationNum, edu.state, "");
//		this.verifyWarnMess(str3); 

		//Verify Failed edit customer education
		this.setupEduInfo(editEdu.educationNum, editEdu.state, editEdu.country);
		this.verifyFailedEditEducation();

		//Verify successfully edit education
		lm.manageEducationsForCustomer("Edit", editEduList, edu);
		editEdu.createDate = DateFunctions.getToday("MMM dd,yyyy hh:mm a z", zone);
		editEdu.id = edu.id;
		custEducation.verifyEducatioList(editEdu);

		//Verify verify-status in DB
		lm.verifyEducationVerifyStatusFromDB(editEdu.id, "Verified", "Verified", schema);

		//Remove Customer education
		lm.manageEducationsForCustomer("Remove", null, editEdu);

		//Throw exception if it exists
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
		}

		//Logout
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		zone=DataBaseFunctions.getContractTimeZone("");

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";
		cust.fName = "QA-Customer3";
		cust.mName = "QaTest-CusotmerProfile-3";
		cust.lName = "TEST-Profile3";
		cust.dateOfBirth = "1984-04-30";

		edu.status = OrmsConstants.ACTIVE_STATUS;
		edu.educationType = "HuntEducation";
		edu.educationNum = "EDU76352";
		edu.state = "Alberta";
		edu.country = "Canada";

		editEdu.status = OrmsConstants.VERIFIED_STATUS;
		editEdu.educationType = "HuntEducation";
		editEdu.educationNum = "381020030";
		editEdu.state = "Mississippi";
		editEdu.country = "United States";
		editEdu.createApp = "LicenseManager";
		editEdu.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		editEduList[0] = editEdu;
	}

	/**
	 * SetUp Education Information
	 * @param educationNum
	 * @param state
	 * @param country
	 */
	private void setupEduInfo(String educationNum, String state, String country){
		Education edu = new Education();
		edu.educationNum = educationNum;
		edu.state = state;
		edu.country = country;
		editEducation.setEducation(edu);
		if(country.trim().length()<1){
			editEducation.selectCountry(0);
		}
	}

	public void verifyFailedEditEducation(){
		editEducation.clickCancel();
		ajax.waitLoading();
		custEducation.waitLoading();
		String educationNum = custEducation.getEducationNumById(edu.id);
//		int row = custEducation.getEducationRowByNum(editEdu.educationNum);
		if(educationNum.equals(editEdu.educationNum)){
			throw new ErrorOnDataException("Education "+editEdu.educationNum+" should not be edited successfully! For Iducation ID = " + edu.id);
		}
	}

	/**
	 * Verify warning message when editing education info
	 * @param expectMsg
	 */
	private void verifyWarnMess(String expectMsg){
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
		String actualMsg = "";
		//Get warning message
		editEducation.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, editEducation);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}else if(page == editEducation  && page != confirmDialogWidget){
			actualMsg = editEducation.getWarnMes();
		}
		//Verify warning message
		if(!actualMsg.matches(expectMsg)){
			pass &=false;
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
		//Return edit education page
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickCancel();
			custEducation.waitLoading();
		}
	}


	//	private void initializeWarnMess(String eduID){
	//		str = "An \"Active\" or \"Manually Verified\" or \"Verified\" Education record with Type \"HuntEducation\" already exists for this Customer and will be set to \"Inactive\" when the Education record with ID \""+eduID+"\" is updated. Proceed?";
	//	}
	private String str1 = "Education Number is required. ?Please specify the Education Number.";
	private String str2 = "Education State/Province is required. Please specify the Education State/Province.";
	private String str3 = "Education Country is required. ?Please specify the Education Country.";
}
