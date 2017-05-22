package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountStoreAssignmentsDetailsWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify assign a new bank account to a new agent, and view assignment detail info in view list
 * @Preconditions: We need to prepare vendor with num 'VendorForBankAccountBasic'
 * @SPEC: View Vendor Bank Account - Store Assignment List.doc(Cover all the verify points of spec assign bank account to store)
 * @Task#: Auto - 769
 * routingNum = "084201278"
 *
 * Note: This case will be blocked by defect on ORMS build3.01
 *
 * @author jwang7
 * @Date  Dec 26, 2011
 */
public class ViewBankAccountStoreAssignment extends LicenseManagerTestCase{
	private StoreInfo storeInfo = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	private LicMgrVendorBankAccountStoreAssignmentsDetailsWidget assignmentDetailPg = LicMgrVendorBankAccountStoreAssignmentsDetailsWidget.getInstance();

	private boolean pass;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(storeInfo.belongedVendorID);

		// Add a new agent in Agent sub page
		lm.gotoVendorAgentsPg();
		storeInfo.storeID = lm.addVendorAgents(storeInfo);
		logger.info("Add a new agent:"+storeInfo.storeID);

		lm.gotoVendorBankAccountPage();
		//Add a new bank account in Bank Accounts sub page
		bankAccount.accountID = lm.addVendorBankAccount(bankAccount, true);
		//assign bank account to agent
		bankAccount.assignID = lm.assignVendorBankAccountToStore(storeInfo.storeName, bankAccount.accountRegx, bankAccount.effectiveDate, true);
		bankAccount.assignAgentName = storeInfo.storeName;
		bankAccount.assignStatus = "Active";

		//verify asssignment list
		lm.gotoVendorBankAccountStoreAssignmentList();
		assignmentDetailPg.setupFilter(false,true,true,bankAccount.assignAgentName,bankAccount.accountRegx);
		verifyBankAccountStoreAssignmentList(true,true,bankAccount);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		storeInfo.belongedVendorID = "VendorForBankAccountBasic";
		storeInfo.isNewLocation = true;
		storeInfo.locationClass = "Commercial Agent";
		storeInfo.agency = "MSHF";
		storeInfo.region = "Central Region";
		storeInfo.storeName = "AgentForBankAccountBasic"+ DateFunctions.getLongTimeStamp();
		storeInfo.timeZone = "Africa/Accra";
		storeInfo.physicalAddress.address = "P.O. Box 110001";
		storeInfo.physicalAddress.city = "Montgomery";
		storeInfo.physicalAddress.state = "Mississippi";
		storeInfo.physicalAddress.county = "Adams";
		storeInfo.physicalAddress.zip = "36191";
		storeInfo.physicalAddress.country = "United States";
		storeInfo.physicalAddress.isValidate = false;
		storeInfo.isMailSamePhyAddress = true;

		Contacts contact = new Contacts();
		contact.contactType = "Operation Mgr";
		contact.isPrimary = true;
		contact.firstName = "QA";
		contact.lastName = "Test";
		contact.businessPhone = "3342880375";

		storeInfo.contactArray.add(contact);

		bankAccount.accountPrenoteStatus = "Pending";
		bankAccount.accountStatus = "Active";
		bankAccount.accountType = "Checking";
		bankAccount.routingNum = "026009593";
		bankAccount.accountNum = "c" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();
		bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		bankAccount.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		bankAccount.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		bankAccount.accountRegx = bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccount.accountNum.substring(0, 4);

		pass=true;
	}

	/**
	 * verify bank account store assignment list for each column
	 * @param activeOnly
	 * @param inActiveOnly
	 * @param store
	 * @param account
	 */
	private void verifyBankAccountStoreAssignmentList(boolean activeOnly, boolean inActiveOnly, VendorBankAccountInfo bankAccount) {

		List<VendorBankAccountInfo> comparedAssignments = new ArrayList<VendorBankAccountInfo>();
		comparedAssignments = assignmentDetailPg.getAssignmentsInfo();

		for(int i=0;i<comparedAssignments.size();i++){
			if(activeOnly && !inActiveOnly && comparedAssignments.get(i).assignStatus.equalsIgnoreCase("Inactive")){
				logger.error("Assignment ID#:"+comparedAssignments.get(i).assignID+" should be Active");
				pass &= false;
				break;
			}

			if(!activeOnly && inActiveOnly && comparedAssignments.get(i).assignStatus.equalsIgnoreCase("Active")){
				logger.error("Assignment ID#:"+comparedAssignments.get(i).assignID+" should be Inactive");
				pass &= false;
				break;
			}

			if(!comparedAssignments.get(i).assignAgentName.startsWith(bankAccount.assignAgentName)){
				logger.error("Assignment agent displays uncorrectly, check assignment#:"+comparedAssignments.get(i).assignID);
				pass &= false;
				break;
			}

			if(DateFunctions.compareDates(comparedAssignments.get(i).effectiveDate,bankAccount.effectiveDate) !=0 ){
				logger.error("Assignment effective date displays uncorrectly, check assignment#:"+comparedAssignments.get(i).assignID);
				pass &= false;
				break;
			}

			if(!comparedAssignments.get(i).accountNum.startsWith(bankAccount.accountNum.substring(0, 4))){
				logger.error("Assignment account num displays uncorrectly, check assignment#:"+comparedAssignments.get(i).assignID);
				pass &= false;
				break;
			}

			if(DateFunctions.compareDates(comparedAssignments.get(i).creationDateTime,bankAccount.creationDateTime) !=0 ){
				logger.error("Assignment last modified date displays uncorrectly, check assignment#:"+comparedAssignments.get(i).assignID);
				pass &= false;
				break;
			}

			if(!comparedAssignments.get(i).creationUser.startsWith(bankAccount.creationUser)){
				logger.error("Assignment creation user displays uncorrectly, check assignment#:"+comparedAssignments.get(i).assignID);
				pass &= false;
				break;
			}

			if(!pass){
				throw new com.activenetwork.qa.testapi.ErrorOnDataException("Assignment#:"+comparedAssignments.get(i).assignID+" displays incorrectly");
			}
			logger.info("Assignment#:"+comparedAssignments.get(i).assignID+" displays correctly");
		}
		assignmentDetailPg.clickOK();
		ajax.waitLoading();
	}

}
