package com.activenetwork.qa.awo.testcases.sanity.orms;

import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.MSInternetTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Pre-condition:
 * (1) Privilege 145 should be assigned to location class '14 - MDWFP Internet', and has 'Original' pricing, license year, business rule(production data);
 * (2) Privilege 145 should limit one order per license year, find setup table D_HF_ADD_QTY_CONTROL id=2660
 * 
 * Jane[2014-6-27]: Add steps for Auto-2240:
 * do a purchase via MSInternet, 
 * do not confirm the purchase, do the same purchase again. 
 * The previous purchase should be automatically voided by the system
 * 
 * @author Jane
 *
 */
public class LM_MSInternet_PrivilegePurchase extends MSInternetTestCase {
	private String saleLoc = "MDWFP - INTERNET PORTAL(490)";//TODO check if it will be changed
	private Properties responsePro = new Properties();
	private String ordNum, convOrdNum, amt, convFee, receiptNum;
	
	public void execute() {
		//initial node info for customer inquiry 
		initialXMLNodeInfoForCustInquiry();
		//generate XML for customer inquiry
		String xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		String response = client.post(internetUrl, xml);
//		System.out.println("Response for customer inquiry:\n"+response);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		//compare CustomerInfo details info with ORMS
		lm.loginLicenseManager(login);
		compareCustInfoInORMS();
		
		//initial node info for privilege sale
		initialXMLNodeInfoForPrivilegeSale();
		//generate XML for Privilege Sale
		xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		response = client.post(internetUrl, xml);
//		System.out.println("Response for privilege sale:\n"+response);	
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);	
		
		//Without confirmation, send request again and get response
		initialXMLNodeInfoForPrivilegeSale();
		xml=transformer.transformToString(nodesInfo);
		response = client.post(internetUrl, xml);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		//Parse response for privilege order details info into properties
		responsePro = parseXMLForPrivOrdInfo(response);
		//compare vehicle details with ORMS
		comparePrivOrdInfoInORMS();
		
		//initial node info for completion transaction
		initialXMLForCompletionTransaction();
		//generate XML for completion transaction
		xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		response = client.post(internetUrl, xml);
//		System.out.println("Response for completion transaction:\n"+response);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		
		//clean up
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(ordNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.postRefundAsCreditFromRefundWidgetToOrdDetailsPg();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		
//		inputFile = AwoUtil.getProjectPath() + casePath + "/"+this.getClass().getSimpleName()+".xml";
//		inputFile = inputFile.replace("/", "\\");
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "Test-SanityTest2";
		cust.lName = "QA-SanityTest2";
		cust.dateOfBirth = "1982-01-23";
		cust.hPhone = "601432810811";
		cust.email = "test@reserveamerica.com";
		cust.custGender = "Male";
		cust.ethnicity = "White";
		
		cust.address = "644 N State St";
		cust.zip = "39202-3303";
		cust.country = "United States";
		cust.city = "Jackson";
		cust.state = "Mississippi";
		cust.county = "Hinds";
		
		cust.identifier.identifierType = "3";//Green Card
		cust.identifier.identifierNum = "19820123";
		cust.identifier.country = "CAN";
		
		privilege.code = "145";
		privilege.name = "NR WMA USER PERMIT";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.operateReason = "14 - Other";
	}
	
	private void initialXMLNodeInfoForCustInquiry() {
		//Put transaHead Node
		head.put("transaction_type", OrmsConstants.MS_INTERNET_CODE_CUSTOMER_INQUIRY);
		head.put("time_stamp", DateFunctions.getDateStamp(true) + DateFunctions.getLongTimeStamp());
		nodesInfo.put("head", head);

		//Body Node
		//Customer will be created if not existing
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("residency", "N");
		body.put("id_type", cust.identifier.identifierType);
		body.put("id_number", cust.identifier.identifierNum);
		body.put("id_country", cust.identifier.country);
		body.put("dob", DateFunctions.formatDate(cust.dateOfBirth, "yyyyMMdd"));
		body.put("first_name", cust.fName);
		body.put("last_name", cust.lName);
		body.put("phone", cust.hPhone);
		body.put("address1", cust.address);
		body.put("city", cust.city);
		body.put("email", cust.email);
		body.put("state", convertAbbreviations(cust.state));
		body.put("zip", cust.zip);
		body.put("race", convertRace(cust.ethnicity));
		body.put("gender", convertGender(cust.custGender));
		nodesInfo.put("body", body);
	}
	
	private void initialXMLNodeInfoForPrivilegeSale() {
		head.put("transaction_type", OrmsConstants.MS_INTERNET_CODE_PURCHASE);
		head.put("time_stamp", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());
		head.put("ref_number", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp()+String.valueOf(new Random().nextInt(99999999)));
		nodesInfo.put("head", head);
		
		//Body Node
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("cust_id", cust.custNum);
		body.put("id_number", cust.identifier.identifierNum);
		body.put("id_type", cust.identifier.identifierType);
		body.put("dob", DateFunctions.formatDate(cust.dateOfBirth, "yyyyMMdd"));
		body.put("first_name", cust.fName);
		body.put("last_name", cust.lName);
		body.put("residency", "N");
		body.put("id_country", cust.identifier.country);
		body.put("license_count", "1");
		body.put("tran_start_date", DateFunctions.formatDate(DateFunctions.getToday(), "yyyyMMdd"));
		nodesInfo.put("body", body);
		
		//license section
		LinkedHashMap<String, String> license = new LinkedHashMap<String, String>();
		license.put("priv_code", privilege.code);
		license.put("license_year", privilege.licenseYear);
		license.put("replace_ind", "N");//Original or Renewal
		nodesInfo.put("license", license);
	}
	
	private void initialXMLForCompletionTransaction() {
		head.put("transaction_type", OrmsConstants.MS_INTERNET_CODE_COMPLETION_TRANSACTION);
		head.put("time_stamp", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());
		head.put("transaction_status", "C");
		head.put("transaction_number", receiptNum);
		amt = lm.getRcptPricePriceByRcptNum(schema, receiptNum);
		head.put("payment_amount", formatMoneyType(amt));
		if(StringUtil.isEmpty(convOrdNum))
			convFee = "000000";
		else{
			convFee = lm.getOrdPriceByOrdNum(schema, convOrdNum);
		}
		head.put("conv_fee", formatMoneyType(convFee));
		nodesInfo.put("head", head);
	}
		
	private void compareCustInfoInORMS() {
		logger.info("Go to customer details page by customer name in License Manager.");
		
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		
		Customer custUI = new Customer();
		custUI = getCustInfoFromORMS();
		
		compareCustInfo(custUI, cust);
	}
	
	private void comparePrivOrdInfoInORMS() {
		logger.info("Go to customer order page from customer detail page in License Manager.");
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		lm.gotoCustomerOrderPage();
		receiptNum = responsePro.getProperty("transaction_number");
		ordNum = lm.getOrderNumsByReceiptNum(schema, receiptNum, OrmsConstants.TRANTYPE_PURCHASE_PRIVILEGE);
		convOrdNum = lm.getOrderNumsByReceiptNum(schema, receiptNum, OrmsConstants.TRANTYPE_APPLY_CONVENIENCE_FEE);
		verifyOrdPurchaseTypeAndSaleLocInCustOrdPg(privilege.licenseYear, ordNum, ORIGINAL_PURCHASE_TYPE, saleLoc);
		Properties ormsPro = this.getPriOrdInfoFromORMS(privilege.licenseYear, ordNum, schema);
		boolean pass = this.compareProperties(ormsPro, responsePro);
		if(!pass)
			throw new ErrorOnPageException("Privilege order info does not match between response file and ORMS. Please check log for more details.");
		logger.info("---Verify privilege order info successfully in ORMS.");
	}

}
