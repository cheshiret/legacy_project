package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This script focus on:
    1. Add new customer(needAddNewCustoner: false)
                       --Add identifier(needAddIdentifier: true, false)
                       --Add education(needAddEducation: true, false)
                       --Add suspension(needAddSuspension: true, false)
    2. For existed customer(needAddNewCustoner: true)
                       --Add identifier(needAddIdentifier: true, false)
                       --Add education(needAddEducation: true, false)
                       --Add suspension(needAddSuspension: true, false)

 * @Preconditions: No
 * @SPEC: No
 * 
 * @author Lesley Wang
 * @Date  Nov 21, 2012
 */
public class AddCustomerProfileFunction extends FunctionCase {
	LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
	LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
	LoginInfo login=new LoginInfo();
	LicenseManager lm =LicenseManager.getInstance();
	Customer cust = new Customer();
	private List<CustomerIdentifier> newIdentifiers = new ArrayList<CustomerIdentifier>();
	private Education[] newEducations = null;
	private Suspension[] newSuspensions = null;
	private Certification[] newCertifications = null;

	boolean needAddNewCustoner = false;
	boolean needAddIdentifier = false;
	boolean needAddEducation = false;
	boolean needAddSuspension = false;
	boolean needAddCertification = false;
	boolean isLoggedin = false;
	String IDs;
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	private LicMgrAddIdentifiersPage addIdentifierWidget = LicMgrAddIdentifiersPage.getInstance();

	@Override
	public void wrapParameters(Object[] param) {
		//Initialize login information
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");

		login.contract = (String)param[0];
		login.location = (String)param[1];

		needAddNewCustoner = (Boolean)param[2];
		cust = (Customer)param[3];
		needAddIdentifier = (Boolean)param[4];
		newIdentifiers = (ArrayList<CustomerIdentifier>)param[5];
		needAddEducation = (Boolean)param[6];
		newEducations = (Education[])param[7];
		needAddSuspension = (Boolean)param[8];
		newSuspensions = (Suspension[])param[9];
		needAddCertification = (Boolean)param[10];
		newCertifications = (Certification[])param[11];
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			if(addIdentifierWidget.exists()){
				addIdentifierWidget.clickCancel();
				ajax.waitLoading();
				browser.waitExists();
			}
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		if(!needAddNewCustoner){
			//Goto customer details page
			lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		}else{
			//Add new customer
			lm.gotoNewCustomerPage(cust.customerClass);
			lm.addOrEditCustomerInfo(cust, newCustPg);
			String msg = lm.finishAddOrEditCustomer();
			if (msg.matches("[0-9]+")) {
				logger.info("Successfully add customer profile!");
				if(login.contract.equals("MO")){
					IDs = "Conservation#: " + msg;
				} else {
					IDs = "MDWFP#: " + msg;
				}
				lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
			} else {
				throw new ErrorOnPageException(msg);
			}
		}

		//Add customer identifier 
		if(needAddIdentifier) {
			IDs += " IdentifiersIDs: ";
			lm.gotoIdentifiersFromCustomerDetailsPg();
			String id = "";
			for (int i = 0; i < newIdentifiers.size(); i++) {
				id = lm.safeAddCustomerIdentifier(newIdentifiers.get(i));
				IDs += id + ", ";
			}
			//			lm.addIdentifiersInInditifierPage(newIdentifiers);	
			logger.info("Successfully add customer identifier!");
		}

		//Add customer education
		if(needAddEducation) {
			lm.gotoEducationSubTabFromCustomerDetailsPg();
			lm.manageEducationsForCustomer("Add", null, newEducations);
			IDs += this.verifyNewEducations(newEducations);
		}

		//Add customer suspension
		if(needAddSuspension) {
			IDs += " SuspensionIDs: ";
			lm.gotoSuspensionsFromCustomerDetailsPg();
			//			lm.manageSuspensions("Add", newSuspensions);
			for(Suspension s : newSuspensions) {
				String msg = lm.addCustomerSuspension(s);
				this.verifyID(msg, "customer suspension (" + s.type + ")");
				IDs += msg + ", ";
			}
			logger.info("Successfully add customer suspension!");
		}

		if(needAddCertification) {
			lm.gotoCertificationFromCustomerDetailsPg();
			String[] ids = lm.addCustomerCertification(newCertifications);	
			IDs += this.verifyNewCertifications(ids);
		}		
		newAddValue = IDs;

	}

	private String verifyNewEducations(Education[] educations) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage.getInstance();
		String educationIDs = "EducationsIDs";
		for (int i = 0; i < educations.length; i++) {
			if (!educationPage.checkEducationExists(educations[i].educationType, educations[i].educationNum)) {
				throw new ErrorOnPageException("Failed to add customer education!");
			}
			String id = educationPage.getEducationID(educations[i].educationType, educations[i].educationNum);
			educationIDs += id + ", ";
		}
		logger.info("Successfully add customer education!");
		return educationIDs;
	}

	private String verifyNewCertifications(String[] ids) {
		String certIDs = "CertificationsIDs";
		for (String id : ids) {
			this.verifyID(id, "certification");
			certIDs += id + ", ";
		}
		logger.info("Successfully add customer Certification!");
		return certIDs;
	}

	private void verifyID(String id, String meg) {
		if (!id.matches("[0-9]+")) {
			throw new ErrorOnPageException("Failed to add " + meg + " due to: " + id);
		}
	}
}
