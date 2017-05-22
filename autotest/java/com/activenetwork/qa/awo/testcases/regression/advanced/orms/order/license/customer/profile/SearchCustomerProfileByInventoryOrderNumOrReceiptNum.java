package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SearchCustomerProfileByInventoryOrderNumOrReceiptNum extends LicenseManagerTestCase{
	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer cust_1 = new Customer();
	private Customer cust_2 = new Customer();
	private Customer cust_3 = new Customer();
	private String privilegeOrdNum, receiptNum = "";

	public void execute() {
		lm.loginLicenseManager(login);

		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.gotoHomePage();
		//Make a new privilege order and get order number
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		privilegeOrdNum = lm.processOrderCart(pay,false);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, privilegeOrdNum);
		cust_1.inventoryNum = privilege.inventoryNum;
		
		//Get receiptNum
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		receiptNum = privOrderDetailPage.getReceiptNum();

		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		//Verify search result via Inventory Type and number
		this.verifySearchResult(cust_1);
		//case-insensitive match
		cust_1.inventoryNum  = cust_1.inventoryNum .toLowerCase();
		this.verifySearchResult(cust_1);
		//ignoring dashes
		cust_1.inventoryNum  = cust_1.inventoryNum +"-";
		this.verifySearchResult(cust_1);
		//ignoring embedded spaces
		cust_1.inventoryNum = cust_1.inventoryNum .substring(0, 1)+" "+cust_1.inventoryNum .substring(1);
		this.verifySearchResult(cust_1);

		//Verify search result via privilege Order
		cust_2.orderNum = privilegeOrdNum;
		this.verifySearchResult(cust_2);

		//Verify search result via Receipt
		cust_3.receiptNum = receiptNum;
		this.verifySearchResult(cust_3);

		//Reverse privilege order
		this.reversePrivileges();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		//Customer info
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "111111117";
		cust.residencyStatus = "Non Resident";
		cust.identifier.country = "Canada";

		cust_1.inventoryType = "212-useForSearchCustomer";
		//cust_1.inventoryNum = "0003";

		//Privilege info
		privilege.invType = "212-useForSearchCustomer";
		privilege.purchasingName = "Sea-rchCustomerPrivilege";//S3-212-useForSearchCustomer
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.validFromDate = DateFunctions.getDateAfterToday(1);
		//privilege.inventoryNum = "0003"; //hard code
		privilege.qty = "1";
		privilege.operateNote = "QA Automation";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
	}

	private void setCustInfo(){
		cust.status = "Active";
		cust.hPhone = "128612345";
		cust.address = "QaTest-CusotmerProfile-1 Street";
		cust.supplementalAddress = "QaTest-CusotmerProfile-1 Supp Street";
		cust.city = "University Hall";
		cust.county = "Marion County";
		cust.state = "Mississippi";
		cust.zip = "12020";
		cust.country = "United States";
	}

	private void verifySearchResult(Customer cust){
		custSearchPg.searchCust(cust);
		this.setCustInfo();
		custSearchPg.verifySearchCustomerProfileResult(cust);
	}

	private void reversePrivileges(){
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		custSearchPg.clickHome();
		homePg.waitLoading();

		lm.gotoOrderPageFromOrdersTopMenu(privilegeOrdNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
	}
}
