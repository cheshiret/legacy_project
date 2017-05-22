package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:(P) Verify search result in Customer search page with document number
 * @Preconditions: Customer has "Canada" Country and identifier (US Drivers License,AutoT13579,Alabama)
 * @LinkSetUp:  
 * d_hf_add_cust_profile:id=2670 --QA-Advanced11
 * d_hf_add_privilege_prd:id=2500
 * d_hf_add_print_doc:id=420
 * d_hf_add_qty_control:id=1680
 * d_hf_assi_pri_to_store:id=1750
 * d_hf_add_pricing:id=3602
 * d_hf_add_prvi_license_year:id=2640
 * @SPEC: 
 * Search Customer Profile - Document Number Search Criteria works fine [TC:068468]
 * @Task#: Auto-2042

 * @author SWang
 * @Date Jun 22, 2014
 */
public class SearchCustByDocumentNum extends LicenseManagerTestCase{
	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private Customer cust_1 = new Customer();
	private String msg1, msg2, custNum, colName;

	public void execute() {
		//Prepare privilege order with document
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		String documentNum= lm.getDocumentNumByOrderNumber(schema, orderNum);

		//Verify error message with initial filter
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		verifyErrorMessage(msg1);

		//Verify error message with fuzzy permit number  
		cust_1.documentNum = documentNum+"%";
		verifyErrorMessage(msg2);

		//Find customer with permit number and country different from the customer's
		cust_1.documentNum = documentNum;
		cust_1.country = "United States";
		verifySearchResult(false);

		//Find customer with permit number and identifier
		cust_1.country = StringUtil.EMPTY;
		cust_1.licenseType = cust.identifier.identifierType;
		cust_1.licenseNum = cust.identifier.identifierNum;
		cust_1.licenseState = cust.identifier.state;
		verifySearchResult(false);

		//Find customer with customer number and not existing permit number
		cust_1.documentNum = documentNum+"%";
		cust_1.licenseType = "Conservation #";
		cust_1.licenseNum = custNum;
		cust_1.licenseState = StringUtil.EMPTY;
		verifySearchResult(true);
		
		//Clear privilege order
//		lm.gotoHomePage();
//		lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MO", env);
		login.contract = "MO Contract";
		login.location = "MO Mod 1 - Auto/MO Auto Store";
		//"MO Mod 1/ACADEMY SPORTS & OUTDOORS(Store Loc)";

		//customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced11";
		cust.lName = "TEST-Advaced11";
		cust.dateOfBirth = "Jan 13 1989";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierType = "US Drivers License";
		cust.identifier.identifierNum = "Auto068468";
		cust.identifier.state = "Alabama";
		custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		colName = "Conservation #";
		System.out.println(custNum);
		//privilege parameters
		privilege.code = "DNS";
		privilege.name = "DocumentNumberSearchPri";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation test";	

		msg1 = "At least one of Identifier/Certification/Education Type and #, Inventory #, Receipt #, Order #, TAN, Last Name, Business Name, Date of Birth, Phone, or ZIP/Postal is required. Please re-enter.";
		msg2 = "No results found matching the search criteria. Please re-enter.";
	}

	private void verifySearchResult(boolean gotoCustDetailsPg){
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
		custSearchPg.searchCust(cust_1, gotoCustDetailsPg);
		if(gotoCustDetailsPg){
			custDetailsPg.waitLoading();
		}else {
			List<String> custs = custSearchPg.getColValue(colName);
			if(custs.size()!=2 || !custs.get(1).equals(custNum)){
				throw new ErrorOnPageException("Search result is wrong!");
			}
		}
	}

	private void verifyErrorMessage(String expectMsg) {
		custSearchPg.searchCust(cust_1);
		String actualMsg = custSearchPg.getWarnMes();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("Error message is wrong!", expectMsg, actualMsg);
		}
	}
}
