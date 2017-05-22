package com.activenetwork.qa.awo.testcases.sanity.orms;

import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.MSInternetTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * (1) Vehicle 401 should be assigned to location class '14 - MDWFP Internet', and has 'Original'/'Renewal' pricing, and 'Advanced Renewal Days' should overlap 'Valid Months';
 * **If when failed on code - 0536 when renew, please check the vehicle product's Advanced Renewal Days and Valid Months,
 * 		they must follow below calculation: [Advanced Renewal Days] >= [Valid Months] * 30
 * (2) Existing customer with DOB 1980-09-23 with identifier 'Green Card' 19800923 Canada;
 * @author Jane
 *
 */
public class LM_MSInternet_VehicleRenewal extends MSInternetTestCase {

	private String saleLocation = "LAKE LINCOLN STATE PARK(392)";
	private final String renewalLocation = "MDWFP - INTERNET PORTAL(490)";
	private BoatInfo boat;
	private String prdCode, ordNum, receiptNum, convOrdNum;
	private String amt, convFee;
	private Properties regRespPro = new Properties();
	private Properties renewalRespPro = new Properties();
	
	public void execute() {
		//prepare renewal configure info
		this.prepareRenewalConfigureInfo();
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
		
		lm.loginLicenseManager(login);
		//Data Preparation: Register a boat
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.gotoCustomerSubTabPage("Vehicles");
		lm.registerVehicleInCustDetailPage(boat);
		lm.processOrderCartToOrderSummaryPage(pay, true);
		OrmsOrderSummaryPage summaryPg = OrmsOrderSummaryPage.getInstance();
		boat.registration.miNum = summaryPg.getMINum();
		ordNum = summaryPg.getAllOrdNums().split(" ")[0];
		receiptNum = summaryPg.getReceiptNum();
		lm.finishOrder();
		
		//initial node info for vehicle Inquiry XML file
		initialXMLNodeInfoForVehicleInquiry();
		//dynamically generate XML file
		xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		response = client.post(internetUrl, xml);
//		System.out.println("Response for vehicle inquiry:\n"+response);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		//use XmlParser parse document for vehicle info
		regRespPro = parseXMLForVehicleRegInfo(response);
		//compare customer info and vehicle details with ORMS
		compareVehicleRegInfoInORMS();
		
		//initial node info for vehicle renewal XML file
		initialXMLForVehicleRenewal();
		//dynamically generate XML file
		xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		response = client.post(internetUrl, xml);
//		System.out.println("Response for vehicle renewal:\n"+response);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		//parse response
		renewalRespPro = parseXMLForVehicleRenewalInfo(response);
		receiptNum = renewalRespPro.getProperty("transaction_number");
		ordNum = lm.getOrderNumsByReceiptNum(schema, receiptNum, OrmsConstants.TRANTYPE_RENEW_REGISTER);
		convOrdNum = lm.getOrderNumsByReceiptNum(schema, receiptNum, OrmsConstants.TRANTYPE_APPLY_CONVENIENCE_FEE);
		compareVehicleRenewalInfoInORMS();
		
		//initial node info for vehicle renewal completion transaction XML file
		initialXMLForCompletionTransaction();
		//dynamically generate XML file
		xml=transformer.transformToString(nodesInfo);
		//Use XmlTestClient to link MS Internet, and get response
		response = client.post(internetUrl, xml);
//		System.out.println("Response for completion transaction:\n"+response);
		//get return code from response file
		returnCode = getReturnCode(response);
		parseReturnCode(returnCode, successCode);
		
		//clean up
		lm.gotoHomePage();
		lm.gotoVehicleOrderDetailPage(ordNum);
		lm.reverseVehicleOrderItemOnOrdDetailsPg(boat.operationReason, boat.operationNote, boat.registration.product, RENEWAL_PURCHASE_TYPE);
		lm.postRefundAsCreditFromRefundWidgetToOrdDetailsPg();
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
//		inputFile = AwoUtil.getProjectPath() + casePath + "/"+this.getClass().getSimpleName()+".xml";
//		inputFile = inputFile.replace("/", "\\");
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Sanity5";
		cust.lName = "Test-Sanity5";
		cust.dateOfBirth = "1980-09-23";
		cust.hPhone = "601432810822";
		cust.email = "test@reserveamerica.com";
		cust.custGender = "Male";
		cust.ethnicity = "White";
		
		cust.address = "644 N State St";
		cust.zip = "39202";
		cust.country = "United States";
		cust.city = "Florida";
		cust.state = "New York";
		cust.county = "Hinds";
		
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "19800923";
		cust.identifier.country = "CAN";
		
		boat = new BoatInfo();
		boat.type = "Boat";
		boat.hullIdSerialNum = "MS"+DataBaseFunctions.getEmailSequence();
		boat.manufacturerName = "YAMAHA MOTOR CORP USA";
		boat.modelYear = "1997";
		boat.feet = "15";
		boat.inches = "10";
		boat.hullMaterial = "Steel";
		boat.boatUse = "OTHER";
		boat.propulsion = "Sail";
		boat.fuelType = "Gasoline";
		boat.typeOfBoat = "Open";
		prdCode = "401";
		boat.registration.product = "401 - BOAT REG LESS THAN 16 FT";
		boat.registration.purchaseType = ORIGINAL_PURCHASE_TYPE;
		boat.registration.licenseYear = lm.getFiscalYear(schema);
		boat.operationReason = "14 - Other";
		
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
		body.put("id_type", "3");//Green Card
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
	
	private void initialXMLNodeInfoForVehicleInquiry() {
		//Put transaHead Node
		head.put("transaction_type", OrmsConstants.MS_INTERNET_CODE_VEHICLE_INQUIRY);
		head.put("time_stamp", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());
		nodesInfo.put("head", head);		

		//Body Node
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("vehicle_registration_number", boat.registration.miNum);
		body.put("hull_id", boat.hullIdSerialNum);
		nodesInfo.put("body", body);
		
		//license section
		LinkedHashMap<String, String> license = new LinkedHashMap<String, String>();
		license.put("replace_ind", "Y");
		license.put("priv_code", prdCode);
		license.put("license_year", boat.registration.licenseYear);
		nodesInfo.put("license", license);
	}
	
	private void initialXMLForVehicleRenewal() {
		//Put transaHead Node
		head.put("transaction_type", OrmsConstants.MS_INTERNET_CODE_VEHICLE_RENEWAL);
		head.put("time_stamp", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp());
		head.put("ref_number", DateFunctions.getDateStamp(true)+DateFunctions.getLongTimeStamp()+String.valueOf(new Random().nextInt(99999999)));
		nodesInfo.put("head", head);		

		//Body Node
		LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("vehicle_registration_number", boat.registration.miNum);
		body.put("hull_id", boat.hullIdSerialNum);
		nodesInfo.put("body", body);
		
		//license section
		LinkedHashMap<String, String> license = new LinkedHashMap<String, String>();
		license.put("priv_code", prdCode);
		license.put("license_year", boat.registration.licenseYear);
		license.put("replace_ind", "N");
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
	
	private void compareVehicleRegInfoInORMS() {
		logger.info("Go to customer details page by customer name in License Manager.");
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		lm.gotoCustomerOrderPage();
		verifyOrdPurchaseTypeAndSaleLocInCustOrdPg(boat.registration.licenseYear, ordNum, boat.registration.purchaseType, saleLocation);
		
		lm.gotoCustOrderDetailsPgFromCustDetailsPg(ordNum);//changed
		lm.gotoVehicleDetailsPgFromVehicleOrdDetailsPgByMiNum(boat.registration.miNum);
		compareVehicleRegInfo();	
	}
	
	private void compareVehicleRegInfo() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		
		logger.info("Get vechile registration detail info from vehicle detail page.");
		Properties ormsPro =  new Properties();
		ormsPro.put("vehicle_registration_number", boat.registration.miNum.replace("MI", ""));
		ormsPro.put("hull_id", boat.hullIdSerialNum);
		String vehileExpDate = vehicleDetailsPg.getRegistrationExpiry();
		ormsPro.put("current_exp_date", DateFunctions.formatDate(vehileExpDate, "yyyyMMdd"));
		String status = vehicleDetailsPg.getStatus();
		ormsPro.put("vehicle_status", this.convertVehicleStatus(status));
		String vehileLength = vehicleDetailsPg.getLengthFT();
		ormsPro.put("length", vehileLength);
		boolean pass = this.compareProperties(ormsPro, regRespPro);
		if(!pass)
			throw new TestCaseFailedException("Vehicle registration info was not consistent between response file and ORMS.");
		logger.info("---Verify vehicle registration detail info successfully between response file and ORMS.");
	}

	private void compareVehicleRenwalDetailsInfo() {
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		
		logger.info("Get vechile renewal detail info from vehicle detail page.");
		Properties ormsPro =  new Properties();
			
		ormsPro.put("transaction_number", receiptNum);
		ormsPro.put("tran_amount", formatMoneyType(lm.getOrdPriceByOrdNum(schema, ordNum)));
		ormsPro.put("temp_auth_num", boat.registration.tan);
		
		lm.gotoCustOrderDetailsPgFromCustDetailsPg(ordNum);
		lm.gotoVehicleDetailsPgFromVehicleOrdDetailsPgByMiNum(boat.registration.miNum);
		
		ormsPro.put("old_current_exp_date", regRespPro.getProperty("current_exp_date"));
		String vehileExpDate = vehicleDetailsPg.getRegistrationExpiry();
		ormsPro.put("new_current_exp_date", DateFunctions.formatDate(vehileExpDate, "yyyyMMdd"));
		
		boolean pass = this.compareProperties(ormsPro, renewalRespPro);
		if(!pass)
			throw new TestCaseFailedException("Vehicle renewal info was not consistent between response file and ORMS.");
		logger.info("---Verify vehicle renewal detail info successfully between response file and ORMS.");
	}
	
	private void compareVehicleRenewalInfoInORMS() {
		logger.info("Go to customer details page by customer name in License Manager.");
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		lm.gotoCustomerOrderPage();
		OrderInfo order = verifyOrdPurchaseTypeAndSaleLocInCustOrdPg(boat.registration.licenseYear, ordNum, RENEWAL_PURCHASE_TYPE, renewalLocation);
		boat.registration.tan = order.tan;
		
		compareVehicleRenwalDetailsInfo();	
	}
	
	private void prepareRenewalConfigureInfo(){
		logger.info("Check and update renewal info from DB for Vehicle code = '" + prdCode + "'");
		lm.updateVehicleRenewalDaysInfo(schema, prdCode, "60");
		lm.updateVehicleValidMonthsInfo(schema, prdCode, "1");
	}
}
