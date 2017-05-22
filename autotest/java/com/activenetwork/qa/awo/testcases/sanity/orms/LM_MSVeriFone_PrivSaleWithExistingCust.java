package com.activenetwork.qa.awo.testcases.sanity.orms;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.MSVeriFoneTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Pre-condtion:
 * (1) Check registration id(4112280) in store(id:332) details page, modify if it has been changed, and also update password to '000000001' in D_LOC_REGISTRY table.
 * (2) Existing customer with DOB 1980-02-23 and Verified identifier 'MS Driver License' IDF111110 Mississippi;
 * Note: This customer has been added into datapool, please run script 'supportscripts.qasetup.license.AddCustomerProfile' for record id 1620
 * (3) Privilege product 104 should have 'Original' pricing, license year and business rule;
 * 
 * @author Jane
 *
 */
public class LM_MSVeriFone_PrivSaleWithExistingCust extends
		MSVeriFoneTestCase {

	private String receiptNum, ordNum, tan;
	private String saleLoc = "WAL MART 305(332)";//TODO check if it will be changed
	private String custIdenType = "MS Drivers License";
	private Properties originalResponse = new Properties();
	private Properties voidResponse = new Properties();
	
	public void execute() {
		//construct privilege original sale nodesinfo
		logger.info("Step#1 purchase privilege via Verifone.");
		initialPrivSaleRequestNodesInfo();
		//construct request file
		request=constructRequestInCSV(nodesInfo);
		//send request to verifone
		logger.info("send request to verifone");
		response=verifone.request(request, null, null, null, false);
//		System.out.println("Response for original sale:\n"+response.toString());
		//get and parse return code
		String returnCode = getCompletionCode(response);
		parseReturnCode(returnCode, successCode);
		//parse response file
		originalResponse = parseResponseForOriginalSaleOrderInfo(response);
		
		//check in ORMS
		logger.info("Verify Verifone transaction in AWO");
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
		compareCustInfo();
		lm.gotoCustomerOrderPage();
		comparePrivilegeOrderInfo(originalResponse);

		//construct void privilege sale nodesinfo
		logger.info("Step#2 void privilege sale via Verifone");
		initialVoidTransactionNodesInfo();
		//construct request file
		request=constructRequestInCSV(nodesInfo);
		//send request to verifone
//		response.clear();
		logger.info("send request to verifone");
		response = verifone.request(request, null, null, null, false);
//		System.out.println("Response for void sale:\n"+response.toString());
		//get and parse return code
		returnCode = getCompletionCode(response);
		parseReturnCode(returnCode, successCode);
		//parse response file
		voidResponse = parseResponseForVoidOrderInfo(response);
		//check in ORMS
		logger.info("Verify Verifone transaction in AWO");
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerOrderPage();
		comparePrivilegeOrderInfo(voidResponse);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		
//		request = AwoUtil.getProjectPath() + casePath + "/"+this.getClass().getSimpleName()+".vfone";
//		request = request.replace("/", "\\");
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.lName = "Test-Sanity1";
		cust.fName = "QA-Sanity1";
		cust.dateOfBirth = "1980-02-25";
		cust.hPhone = "601432810811";
//		cust.email = "test@reserveamerica.com";//TODO check if it needs
		cust.custGender = "Male";
		cust.ethnicity = "White";
		
		cust.address = "643 N State St";
		cust.zip = "39202-3304";
		cust.country = "United States";
		cust.city = "Jackson";
		cust.state = "Mississippi";
		cust.county = "Hinds";
		
		cust.identifier.identifierType = custIdenType;
		cust.identifier.identifierNum = "738774133";//"IDF111110";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		privilege.code = "110";//as original 104 product was removed from verifone device
		privilege.licenseYear = lm.getFiscalYear(schema);
		
	}
	
	private void initialPrivSaleRequestNodesInfo() {
		logger.info("construct privilege original sale nodesinfo");
		nodesInfo.put(c_contract, "MS");//contract
		nodesInfo.put(c_transType, OrmsConstants.MS_VERIFONE_CODE_ORIGINAL_SALE);//transaction set
		nodesInfo.put(c_regID, "4112280");//register id
		nodesInfo.put(c_pwd, "000000001");//password
		nodesInfo.put(c_processDateTime, DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());//processing date/time
		nodesInfo.put(c_tan, DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp()+"00001");//check if it was mandatory or not
		
		nodesInfo.put(c_idType, "1");//MS Drivers License 
		nodesInfo.put(c_idState, "MS");
		nodesInfo.put(c_idNum, cust.identifier.identifierNum);
		nodesInfo.put(c_phone, cust.hPhone);
//		nodesInfo.put(c_email, cust.email);//TODO check if it needs
		nodesInfo.put(c_residency, "R");//residency indicator
		nodesInfo.put(c_dob, DateFunctions.formatDate(cust.dateOfBirth, "yyyyMMdd"));
		nodesInfo.put(c_add_res_proof, "1");
		
		nodesInfo.put(c_licCount, "01");//number of product loops
		nodesInfo.put(c_tran_start_date, DateFunctions.getDateStamp(true));//transaction valid from date
		
		nodesInfo.put(c_privCode, privilege.code);//product code
		nodesInfo.put(c_licYear, privilege.licenseYear);//license fiscal year
		nodesInfo.put(c_replace_ind, "N");//replacement
	}
	
	private void initialVoidTransactionNodesInfo() {
		logger.info("construct void transaction nodesinfo");
		nodesInfo.clear();
		nodesInfo.put(c_contract, "MS");//contract
		nodesInfo.put(c_transType, OrmsConstants.MS_VERIFONE_CODE_VOID_TRANSACTION);//transaction set
		nodesInfo.put(c_software_version, "20000");//software version
		nodesInfo.put(c_regID, "4112280");//register id
		nodesInfo.put(c_pwd, "000000001");//password
		nodesInfo.put(c_processDateTime, DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());//processing date/time
		nodesInfo.put(c_reason_code, "14");//reason code
		nodesInfo.put(c_tan, tan);
	}
	
	private Properties parseResponseForOriginalSaleOrderInfo(List<Map<String, String>> response) {
		receiptNum = getReturnFieldByCode(response, c_receiptNum);
		tan = getReturnFieldByCode(response, c_tan);
		ordNum = lm.getOrderNumsByReceiptNum(schema, receiptNum, OrmsConstants.TRANTYPE_PURCHASE_PRIVILEGE);
		
		Properties responsePro = new Properties();
		
		responsePro.put("transaction_number", receiptNum);
		responsePro.put("temp_auth_num", "");//TODO check about result file
		responsePro.put("priv_code", getReturnFieldByCode(response, c_privCode));
		responsePro.put("purchase_type", ORIGINAL_PURCHASE_TYPE);
		responsePro.put("status", OrmsConstants.ACTIVE_STATUS);
		responsePro.put("sale_location", saleLoc);
		
		return responsePro;
	}
	
	private Properties parseResponseForVoidOrderInfo(List<Map<String, String>> response) {
		Properties responsePro = new Properties();
		
//		responsePro.put("transaction_number", receiptNum);//DOn't need to check, cause from response file, the receipt num should be orignal one
		responsePro.put("temp_auth_num", "");//TODO check about result file
		responsePro.put("priv_code", getReturnFieldByCode(response, c_privCode));
		responsePro.put("purchase_type", ORIGINAL_PURCHASE_TYPE);
		responsePro.put("status", OrmsConstants.VOID_STATUS);
		responsePro.put("sale_location", saleLoc);
		
		return responsePro;
	}
		
	private void comparePrivilegeOrderInfo(Properties responsePro) {
		Properties ormsPro = this.getPriOrdInfoFromORMS(privilege.licenseYear, ordNum, schema);
		boolean pass = this.compareProperties(ormsPro, responsePro);
		if(!pass)
			throw new TestCaseFailedException("Privilege orginal purchase order info was not consistent between response file and ORMS.");
		logger.info("---Verify privilege orginal purchase order detail info successfully between response file and ORMS.");
	}
	
	private void compareCustInfo() {
		Customer custUI = new Customer();
		custUI = getCustInfoFromORMS();
		compareCustInfo(custUI, cust);
	}
	
	private Customer getCustInfoFromORMS() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerAdressesPage custAddePg = LicMgrCustomerAdressesPage.getInstance();
		
		logger.info("Get customer info from customer detail page.");
		Customer cust = new Customer();
		cust.custNum = custDetailPg.getCustomerNumber();
		cust.fName = custDetailPg.getFirstName();
		cust.mName =  custDetailPg.getMiddleName();
		cust.lName = custDetailPg.getLastName();
		cust.suffix = custDetailPg.getSuffix();
		cust.dateOfBirth = custDetailPg.getDateOfBirth();
		cust.address = custAddePg.getAddress(0);
		cust.zip = custAddePg.getZip(0);
		cust.country = custAddePg.getCountry(0);
		cust.city = custAddePg.getCity(0);
		cust.state = custAddePg.getState(0);
		cust.hPhone = custDetailPg.getHomePhone();
		cust.email = custDetailPg.getEmail();
		cust.custGender = custDetailPg.getCustomerGender();
		cust.ethnicity = custDetailPg.getCustomerEthnicity();
		
		return cust;
	}
	
	private void compareCustInfo(Customer custORMS, Customer cust) {
		boolean pass = true;
		pass &= MiscFunctions.compareString("Customer Number", custORMS.custNum, cust.custNum);
		pass &= MiscFunctions.compareString("Customer LastName", custORMS.lName, cust.lName);
		pass &= MiscFunctions.compareString("Customer MiddleName", custORMS.mName,cust.mName);
		pass &= MiscFunctions.compareString("Customer FirstName", custORMS.fName, cust.fName);
		pass &= MiscFunctions.compareString("Customer Suffix", custORMS.suffix, cust.suffix);
		pass &= MiscFunctions.compareString("Physical Address", custORMS.address, cust.address);
		pass &= MiscFunctions.compareString("DateOfBirth", DateFunctions.formatDate(custORMS.dateOfBirth, "yyyyMMdd"), DateFunctions.formatDate(cust.dateOfBirth, "yyyyMMdd"));
		pass &= MiscFunctions.compareString("Physical Zip", custORMS.zip, cust.zip);
		pass &= MiscFunctions.compareString("Physical Country", custORMS.country, cust.country);
		pass &= MiscFunctions.compareString("Physical City", custORMS.city, cust.city);
		pass &= MiscFunctions.compareString("Physical State", custORMS.state, cust.state);
		pass &= MiscFunctions.compareString("Home Phone", custORMS.hPhone, cust.hPhone);
		pass &= MiscFunctions.compareString("Email", custORMS.email, cust.email);
		pass &= MiscFunctions.compareString("Gender", custORMS.custGender, cust.custGender);
		pass &= MiscFunctions.compareString("Race", custORMS.ethnicity, cust.ethnicity);
		
		if(!pass)
			throw new ErrorOnPageException("Customer info does not match between response file and ORMS. Please check log for more details.");
		
		logger.info("---Verify customer info successfully in ORMS.");
	}
	

}
