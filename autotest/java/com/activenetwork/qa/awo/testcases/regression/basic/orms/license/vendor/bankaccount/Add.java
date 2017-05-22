/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bankaccount;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description: Verify add vendor vendor bank account successfully.
 * @Preconditions:
 * @SPEC: Add vendor bank account
 * Add Vendor Bank Account [TC:004159]
 * @Task#: Auto-870
 * @author jwang8
 * @Date  Feb 17, 2012
 */
public class Add extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage
	.getInstance();
	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		if(!vendorSearchPg.checkVendorInfoIsExistInListPg(vendor.number)){
			lm.addVendor(vendor);
		}
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//go to bank accounts sub page.
		lm.gotoVendorBankAccountPage();
		//add vendor bank account.
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		this.verifyVendorBankAccountListInfo(vendor.bankAccount);
		lm.logOutLicenseManager();
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vendor.appReceivedDate = DateFunctions.getDateAfterToday(-1);
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "9052867777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		vendor.vendorCreationUser = login.userName;


		vendor.number = "StatusVendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.status = "Active";
		vendor.ownerName = "Basic Test Case";
		vendor.vendorType = "Government";

		vendor.taxID = "000000001";
		vendor.taxIDType = "Federal Tax ID";

		vendor.phyAddress = "address";
		vendor.phySuppAddress = "address";
		vendor.phyCityTown = "Simi Valley";
		vendor.phyStateProvince = "California";
		vendor.phyCounty = "Ventura";
		vendor.phyZipPostal = "93063";
		vendor.phyCountry = "United States";
		vendor.phyAddStatus = "Zip Only";

		vendor.mailingAddress = "address";
		vendor.mailingSuppAddress = "address";
		vendor.mailingCityTown = "Simi Valley";
		vendor.mailingStateProvince = "California";
		vendor.mailingCounty = "Ventura";
		vendor.mailingZipPostal = "93063";
		vendor.mailingCountry = "United States";
		vendor.mailingAddStatus = "Zip Only";


		vendor.isMailingAddSameAsPhysicalAdd = true;

		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = true;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		contact.homePhone ="905286777";
		contact.mobilePhone = "9052867777";
		contact.fax = "9052867777";
		contact.email = "12345@activenetwork.com";
		contact.website = "www.activenetwor";
		vendor.contacts.add(contact);

		vendor.specifyDefault = "Weekly EFT";
		vendor.isFillValues = true;

		vendor.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = true;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "";
		appStatusCheck.comments = "";

		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = true;
		appStatusCheck1.statusCheck = "Owner Suspensions Check";
		appStatusCheck1.dateCompleted = "";
		appStatusCheck1.completedBy = "";
		appStatusCheck1.comments = "";

		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked = true;
		appStatusCheck2.statusCheck = "Law enforcement A check";
		appStatusCheck2.dateCompleted = "";
		appStatusCheck2.completedBy = "";
		appStatusCheck2.comments = "";

		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked = true;
		appStatusCheck3.statusCheck = "Law Enforcement Background";
		appStatusCheck3.dateCompleted = "";
		appStatusCheck3.completedBy = "";
		appStatusCheck3.comments = "";
		vendor.applicationStatusCheck.add(appStatusCheck);
		vendor.applicationStatusCheck.add(appStatusCheck1);
		vendor.applicationStatusCheck.add(appStatusCheck2);
		vendor.applicationStatusCheck.add(appStatusCheck3);

		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		vendor.bankAccount.numOfAssignedStore = 0;
		vendor.bankAccount.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		vendor.bankAccount.setBankName("China Bank");
		vendor.bankAccount.setBankBranchName("Keji Road");
	}
	//Verify the vendor bank account info.
	private void verifyVendorBankAccountListInfo(VendorBankAccountInfo expectedBankInfo){
		LicMgrVendorBankAccountsSubPage vendorBankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();
		boolean pass = vendorBankAccountSubPg.compareBanckAccountListInfo(expectedBankInfo);
		if(!pass){
			throw new ErrorOnPageException("Add vendor bank account error");
		}else{
			logger.info("Add vendor bank account correct");
		}
	}

}
