package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddCustomerProfileFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: This support script focus on:
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
 * @Task#: AUTO-578
 * 
 * @author SWang5
 * @Date  May 27, 2011
 */
public class AddCustomerProfile extends SetupCase {
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
	String contract = "";
	private Object[] args = new Object[12];
	private AddCustomerProfileFunction func = new AddCustomerProfileFunction();
	
	public void wrapParameters(Object[] param) {
    	dataTableName = "d_hf_add_cust_profile";
	}

	public void executeSetup() {
		func.execute(args);
	}

	public void readDataFromDatabase() {
		args[0]=datasFromDB.get("contract") + " Contract";
		args[1]=datasFromDB.get("location");
		
		args[2] = Boolean.parseBoolean(datasFromDB.get("needAddNewCust"));
		//Customer profile information
		cust.customerClass = datasFromDB.get("custClass");
		cust.businessName = datasFromDB.get("bName");
		cust.businessNum = datasFromDB.get("bNumber");
		cust.fName = datasFromDB.get("fName");
		cust.mName = datasFromDB.get("mName");
		cust.lName = datasFromDB.get("lName");
		cust.dateOfBirth = datasFromDB.get("dateOfBirth");
		cust.hPhone = datasFromDB.get("hPhone");
		cust.bPhone = datasFromDB.get("bPhone");
		cust.mPhone = datasFromDB.get("mPhone");
		cust.email = datasFromDB.get("Email");
		cust.custGender = datasFromDB.get("gender");
		cust.ethnicity = datasFromDB.get("ethnicity");
		cust.eyeColor = datasFromDB.get("EyeColour");
		cust.hairColor = datasFromDB.get("HairColour");
		cust.heightFt = datasFromDB.get("HeightFt");
		cust.heightIn = datasFromDB.get("HeightIn");
		cust.weight = datasFromDB.get("weight");
		
		cust.physicalAddr.address = datasFromDB.get("pAddress");
		cust.physicalAddr.supplementalAddr = datasFromDB.get("pSuppAddress");
		cust.physicalAddr.city = datasFromDB.get("pCityTown");
		cust.physicalAddr.state = datasFromDB.get("pState");
		cust.physicalAddr.county = datasFromDB.get("pCounty");
		cust.physicalAddr.zip = datasFromDB.get("pZip");
		cust.physicalAddr.country = datasFromDB.get("pCountry");
		cust.physicalAddr.isValidate = Boolean.parseBoolean(datasFromDB.get("isValidatePhysicalAddress"));
		
		cust.mailAddrAsPhy = Boolean.parseBoolean(datasFromDB.get("maddressSameAsPaddress"));
		cust.mailingAddr.address = datasFromDB.get("mAddress");
		cust.mailingAddr.supplementalAddr = datasFromDB.get("mSuppAddress");
		cust.mailingAddr.city = datasFromDB.get("mCityTown");
		cust.mailingAddr.state = datasFromDB.get("mState");
		cust.mailingAddr.county = datasFromDB.get("mCounty");
		cust.mailingAddr.zip = datasFromDB.get("mZip");
		cust.mailingAddr.country = datasFromDB.get("mCountry");
		cust.mailingAddr.isValidate = Boolean.parseBoolean(datasFromDB.get("isValidateMailingAddress"));
		
		args[3] = cust;
		
		//Customer identifier information
		this.readCustIdentifierData();
		args[4] = needAddIdentifier;
		args[5] = newIdentifiers;
		
		//Customer education information
		this.readCustEducationData();
		args[6] = needAddEducation;
		args[7] = newEducations;
		
		//Customer suspension information
		this.readCustSuspensionData();
		args[8] = needAddSuspension;
		args[9] = newSuspensions;
		
		//Customer certification information
		this.readCustCertificationData();
		args[10] = needAddCertification;
		args[11] = newCertifications;
	}

	private void readCustIdentifierData() {
		needAddIdentifier = Boolean.parseBoolean(datasFromDB.get("needAddIdentifier"));
		String tempTypes, tempNums, tempStates, tempCountries;
		if(needAddIdentifier) {
			newIdentifiers.clear();//IMPORTANT
			tempTypes = datasFromDB.get("idType").trim();
			int identifierLength = tempTypes.split(",").length;
			tempNums = datasFromDB.get("idNum").trim();
			tempStates = datasFromDB.get("idState").trim();
			tempCountries = datasFromDB.get("idCountry").trim();
			
			for(int i = 0; i < identifierLength; i++) {
				CustomerIdentifier identifier = new CustomerIdentifier();
				identifier.identifierType = StringUtil.splitByComma(tempTypes)[i];
				identifier.identifierNum = StringUtil.splitByComma(tempNums)[i];
				if(tempStates.length() > 0 && StringUtil.splitByComma(tempStates).length > i) {
					identifier.state = StringUtil.splitByComma(tempStates)[i];
				}
				if(tempCountries.length() > 0 && StringUtil.splitByComma(tempCountries).length > i) {
					identifier.country = StringUtil.splitByComma(tempCountries)[i];
				}
				newIdentifiers.add(identifier);
			}	
		}
	}
	
	private void readCustEducationData() {
		needAddEducation = Boolean.parseBoolean(datasFromDB.get("needAddEducation"));
		if(needAddEducation) {
			String tempTypes, tempNums, tempStates, tempCountries;
			tempTypes = datasFromDB.get("eduType").trim();
			tempNums = datasFromDB.get("eduNum").trim();
			tempStates = datasFromDB.get("eduState").trim();
			tempCountries = datasFromDB.get("eduCountry").trim();
			int educationLength = StringUtil.splitByComma(tempTypes).length;
			newEducations = new Education[educationLength];//IMPORTANT
			
			for(int i = 0; i < educationLength; i++) {
				Education edu = new Education();
				edu.educationType = StringUtil.splitByComma(tempTypes)[i];
				edu.educationNum = StringUtil.splitByComma(tempNums)[i];
				if(tempStates.length() > 0) {
					edu.state = StringUtil.splitByComma(tempStates)[i];
				}
				if(tempCountries.length() > 0) {
					edu.country = StringUtil.splitByComma(tempCountries)[i];
				}
				
				newEducations[i] = edu;
			}	
		}
	}
	
	private void readCustSuspensionData() {
		needAddSuspension = Boolean.parseBoolean(datasFromDB.get("needAddSuspension"));
		if(needAddSuspension) {
			String tempSuspTypes, tempSuspBeginDates, tempSuspEndDates, tempSuspDatePosteds, tempSuspComments, tempSuspsIdensTypes, tempSuspsIdensNums;
			tempSuspTypes = datasFromDB.get("susType").trim();
			tempSuspBeginDates = datasFromDB.get("susBeginDate").trim();
			tempSuspEndDates = datasFromDB.get("susEndDate").trim();
			tempSuspDatePosteds = datasFromDB.get("susDatePosted").trim();
			tempSuspComments = datasFromDB.get("susComment").trim();
			
			//identifier(s) which needs to be UNSELECTED during adding suspension
			tempSuspsIdensTypes = datasFromDB.get("susIdenTypes").trim();
			tempSuspsIdensNums = datasFromDB.get("susIdenNums").trim();
			
			int suspensionLength = StringUtil.splitByComma(tempSuspTypes).length;
			newSuspensions = new Suspension[suspensionLength];//IMPORTANT
			
			for(int i = 0; i < suspensionLength; i++) {
				Suspension sus = new Suspension();
				sus.type = StringUtil.splitByComma(tempSuspTypes)[i];
				if(tempSuspBeginDates.length() > 0) {
					sus.beginDate = StringUtil.splitByComma(tempSuspBeginDates)[i];
				}
				if(sus.beginDate.length() < 1) {
					sus.beginDate = DateFunctions.getToday();
				}
				if(tempSuspEndDates.length() > 0) {
				 sus.endDate = StringUtil.splitByComma(tempSuspEndDates)[i];
				}
				if(sus.endDate.length() < 1) {
					sus.endDate = DateFunctions.getDateAfterToday(365);
				}
				if(tempSuspDatePosteds.length() > 0) {
					sus.datePosted = StringUtil.splitByComma(tempSuspDatePosteds)[i];
				}
				if(sus.datePosted.length() < 1) {
					sus.datePosted = DateFunctions.getDateAfterToday(-1);
				}
				if(tempSuspComments.length() > 0) {
					sus.comment = StringUtil.splitByComma(tempSuspComments)[i];
				}
				if(sus.comment.length() < 1) {
					sus.comment = "Added by Support Script - AddCustomerProfile_" + DateFunctions.getShortDateStamp();
				}
				
				//parse identifiers info of suspension[i]
				String subTempSuspIdensTypes[] = null;
				if(tempSuspsIdensTypes.length() > 0) {
					subTempSuspIdensTypes = StringUtil.splitBy(tempSuspsIdensTypes, ";");
				}
				String subTempSuspIdensNums[] = null;
				if(tempSuspsIdensNums.length() > 0) {
					subTempSuspIdensNums = StringUtil.splitBy(tempSuspsIdensNums, ";");
				}
				
				if(subTempSuspIdensTypes[i].length() > 0) {
					String tempIdenTypes[] = new String[StringUtil.splitByComma(subTempSuspIdensTypes[i]).length];
					String tempIdenNums[] = new String[StringUtil.splitByComma(subTempSuspIdensNums[i]).length];
					tempIdenTypes = StringUtil.splitByComma(subTempSuspIdensTypes[i]);
					tempIdenNums = StringUtil.splitByComma(subTempSuspIdensNums[i]);
					int suspIdentifierLength = tempIdenTypes.length;
					sus.identifiersTypes = new String[suspIdentifierLength];
					sus.identifiersNums = new String[suspIdentifierLength];
					for(int j = 0; j < suspIdentifierLength; j++) {	
						sus.identifiersTypes[j] = tempIdenTypes[j].trim();
						sus.identifiersNums[j] = tempIdenNums[j].trim();
					}
				}
				newSuspensions[i] = sus;
			}	
		}
	}
	
	private void readCustCertificationData() {
		needAddCertification = Boolean.parseBoolean(datasFromDB.get("needAddCertification"));
		if(needAddCertification) {
			String tempCertTypes, tempCertNums, tempCertEffeFromDate, tempCertEffeToDate;
			tempCertTypes = datasFromDB.get("certificationType").trim();
			tempCertNums = datasFromDB.get("certificationNum").trim();
			tempCertEffeFromDate = datasFromDB.get("certificationEffectFrom").trim();
			tempCertEffeToDate = datasFromDB.get("certificationEffectTo").trim();
			
			int certificationLength = StringUtil.splitByComma(tempCertTypes).length;
			newCertifications = new Certification[certificationLength];
			
			for(int i = 0; i < certificationLength; i++) {
				Certification certification = new Certification();
				certification.type = StringUtil.splitByComma(tempCertTypes)[i];
				certification.num = StringUtil.splitByComma(tempCertNums)[i];
				if(tempCertEffeFromDate.length() > 0) {
					certification.effectiveFrom = StringUtil.splitByComma(tempCertEffeFromDate)[i];
				}
				if(certification.effectiveFrom.length() < 1) {
					certification.effectiveFrom = DateFunctions.getToday();
				}
				if(tempCertEffeToDate.length() > 0) {
					certification.effectiveTo = StringUtil.splitByComma(tempCertEffeToDate)[i];
				}
				if(certification.effectiveTo.length() < 1) {
					certification.effectiveTo = DateFunctions.getDateAfterToday(365);
				}
				certification.status = ACTIVE_STATUS;
				newCertifications[i] = certification;
			}
		}
	}

}
