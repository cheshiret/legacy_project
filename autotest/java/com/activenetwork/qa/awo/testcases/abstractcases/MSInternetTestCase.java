package com.activenetwork.qa.awo.testcases.abstractcases;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.w3c.dom.Node;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.XMLAutoClient;
import com.activenetwork.qa.awo.util.XmlTransformer;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.XmlParser;

public abstract class MSInternetTestCase extends MSEquipmentTestCase {
	protected XMLAutoClient client;
	protected String internetUrl;//MS Internet URL
//	protected String inputFile;//input XML file name
	protected XmlTransformer transformer;
	protected Hashtable<String, LinkedHashMap<String, String>> nodesInfo;
	protected LinkedHashMap<String, String> head;
	protected String returnCode;
	protected final String successCode = "0000";
	
	
	public MSInternetTestCase(){
		super();
		
		System.setProperty("javax.net.ssl.trustStore", AwoUtil.PROPERTY_PATH+File.separator+"jssecacerts");
		
		internetUrl = AwoUtil.getOrmsURL(env, "msinternet");
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		transformer = new XmlTransformer();
		nodesInfo = new Hashtable<String, LinkedHashMap<String, String>>();
		//Root Node
		LinkedHashMap<String, String> root = new LinkedHashMap<String, String>();
		root.put("root", "ALS_XTN");
		nodesInfo.put("root", root);
		//Head Node
		head = new LinkedHashMap<String, String>();
		head.put("project_id", "MS");
		head.put("register_id", "4190013");
		head.put("password", "000000000");
		
		client = new XMLAutoClient();
	}
	
	protected String getReturnCode(String xml){
		XmlParser xmlparse = new XmlParser(xml);
		Property p = new Property("tag","completion_code");
		Node node=xmlparse.getNodeByAttribute(p);
		String code = node.getFirstChild().getTextContent();
		logger.info("Get ORMS return code "+code+"-"+getCodeMsg(code));
		return code;
	}
		
	protected String convertGender(String gender) {
		String genderCode = "";
		
		if(gender.equals("Male"))
			genderCode = "M";
		else if(gender.equals("Female"))
			genderCode = "F";
		else if(gender.equals("Unknown"))
			genderCode = "U";
		else
			genderCode = gender;
		
		return genderCode;
	}
	
	protected String convertRace(String race) {
		String raceCode = "";
		
		if(race.equals("White"))
			raceCode = "1";
		else if(race.equals("Black"))
			raceCode = "2";
		else if(race.equals("Hispanic/Latino"))
			raceCode = "3";
		else if(race.equals("Asian"))
			raceCode = "4";
		else if(race.equals("Native American"))	
			raceCode = "5";
		else if(race.equals("Other"))
			raceCode = "6";
		else
			raceCode = race;
		return raceCode;			
	}
	
	protected String convertIdType(String idCode) {
		int code = Integer.parseInt(idCode);
		String idType = "";
		
		switch(code) {
			case 1:
				idType = "US Driver\ufffds License";
				break;
			case 2:
				idType = "Non-US Driver\ufffds License";
				break;
			case 3:
				idType = "Green Card";
				break;
			case 4:
				idType = "Passport";
				break;
			case 5:
				idType = "VISA";
				break;
			case 6:
				idType = "Other";
				break;
			default:
				throw new ErrorOnDataException("Unhandled id code "+idCode);
		}
		
		return idType;
	}
	
	protected String convertVehicleStatus(String status) {
		String returnCode = "";
		
		if(status.equals("Active"))
			returnCode = "A";
		else if(status.equals("Sold"))
			returnCode = "S";
		else if(status.equals("Moved"))
			returnCode = "M";
		else if(status.equals("Destroyed"))
			returnCode = "D";
		else if(status.equals("Stolen"))	
			returnCode = "S";
		else
			returnCode = status;
		return returnCode;			
	}
	
	/**
	 * 
	 * @param money
	 * @return
	 */
	protected String formatMoneyType(String money) {
		Double d = Double.parseDouble(money);
		DecimalFormat df = new DecimalFormat("#.00");
		df.format(d);
		String str = String.format("%.2f", d).replace(".", "");
		String result = String.format("%1$06d", Integer.parseInt(str));

		return result;
	}
	
//	public Customer parseXMLForCustInfo(String response){
//		XmlParser xmlparse = new XmlParser(response);
//		Customer cust = new Customer();
//		
//		logger.info("Parse XML file for Customer info.");
//		cust.custNum = xmlparse.getNodeContentByAttribute(new Property("tag", "cust_id"));
//		
//		String idType = xmlparse.getNodeContentByAttribute(new Property("tag", "id_type"));
//		if(!StringUtil.isEmpty(idType)){
//			cust.identifier.identifierType = this.convertIdType(idType);
//			cust.identifier.identifierNum = xmlparse.getNodeContentByAttribute(new Property("tag", "id_number"));
//		}
//		
//		cust.fName = xmlparse.getNodeContentByAttribute(new Property("tag", "first_name"));
//		cust.mName = xmlparse.getNodeContentByAttribute(new Property("tag", "middle_name"));
//		cust.lName = xmlparse.getNodeContentByAttribute(new Property("tag", "last_name"));
//		cust.suffix = xmlparse.getNodeContentByAttribute(new Property("tag", "suffix"));
//		cust.dateOfBirth = xmlparse.getNodeContentByAttribute(new Property("tag", "dob"));
//		cust.address = xmlparse.getNodeContentByAttribute(new Property("tag", "address1"));
//		cust.city = xmlparse.getNodeContentByAttribute(new Property("tag", "city"));
//		cust.email = xmlparse.getNodeContentByAttribute(new Property("tag", "email"));
//		cust.state = xmlparse.getNodeContentByAttribute(new Property("tag", "state"));
//		cust.country = xmlparse.getNodeContentByAttribute(new Property("tag", "country"));
//		cust.zip = xmlparse.getNodeContentByAttribute(new Property("tag", "zip"));
//		cust.ethnicity = xmlparse.getNodeContentByAttribute(new Property("tag", "race"));
//		cust.custGender = xmlparse.getNodeContentByAttribute(new Property("tag", "gender"));
//		cust.hPhone = xmlparse.getNodeContentByAttribute(new Property("tag", "phone"));	
//		
//		return cust;
//	}
	
	public Customer getCustInfoFromORMS() {
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
	
	public Properties getPriOrdInfoFromORMS(String licenseyear, String ordNum, String schema) {
		LicMgrCustomerOrdersPage orderPg = LicMgrCustomerOrdersPage.getInstance();
		
		logger.info("Get privilage order info in license year "+licenseyear+" from customer order page.");
		orderPg.searchOrderInLicenseYear(licenseyear);
		
		OrderInfo order = new OrderInfo();
		order = orderPg.getOrderInfosByOrderNum(ordNum);
		
		if(order == null)
			return null;
		
		Properties privilegeInfo = new Properties();
		privilegeInfo.put("transaction_number", order.receiptNum);
		privilegeInfo.put("temp_auth_num", order.tan);
		privilegeInfo.put("priv_code", order.productCode);
		privilegeInfo.put("license_year", licenseyear);
		privilegeInfo.put("amount", order.orderPrice);
//		//TODO
//		privilegeInfo.put("agent_comm", "");
//		privilegeInfo.put("cust_tran_fee", "");
//		privilegeInfo.put("start_date", "");
//		privilegeInfo.put("privilege_eff_end_date", "");	
		
		return privilegeInfo;
	}
	
	public void compareCustInfo(Customer custORMS, Customer cust) {
		compareCustInfo(custORMS, cust, false);
	}
	
	/**
	 * refactor this method because of vehicle renewal response file does not contain DOB info
	 * @param custORMS
	 * @param cust
	 * @param ignoreDOB
	 */
	public void compareCustInfo(Customer custORMS, Customer cust, boolean ignoreDOB) {
		boolean pass = true;
		pass &= MiscFunctions.compareString("Customer LastName", custORMS.lName, cust.lName);
		pass &= MiscFunctions.compareString("Customer MiddleName", custORMS.mName,cust.mName);
		pass &= MiscFunctions.compareString("Customer FirstName", custORMS.fName, cust.fName);
		pass &= MiscFunctions.compareString("Customer Suffix", custORMS.suffix, cust.suffix);
		pass &= MiscFunctions.compareString("Physical Address", custORMS.address, cust.address);
		if(!ignoreDOB)
		 pass &= MiscFunctions.compareString("DateOfBirth", DateFunctions.formatDate(custORMS.dateOfBirth, "yyyyMMdd"), DateFunctions.formatDate(cust.dateOfBirth, "yyyyMMdd"));
		if((custORMS.zip.contains("-") && cust.zip.contains("-")) || (!custORMS.zip.contains("-") && !cust.zip.contains("-"))) {
				pass &= MiscFunctions.compareString("Physical Zip", custORMS.zip, cust.zip);	
		} else {
			pass &= MiscFunctions.compareString("Physical Zip", custORMS.zip.replaceAll("-\\d+", ""), cust.zip.replaceAll("-\\d+", ""));
		}
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
	
	public Properties parseXMLForVehicleRegInfo(String response){
		XmlParser xmlparse = new XmlParser(response);
		Properties vehicleInfo = new Properties();
		
		logger.info("Parse XML file for Vehicle registration info.");
		
		String vehicleRegNum = xmlparse.getNodeContentByAttribute(new Property("tag", "vehicle_registration_number"));
		vehicleInfo.put("vehicle_registration_number", vehicleRegNum);
		String vehicleHullId = xmlparse.getNodeContentByAttribute(new Property("tag", "hull_id"));
		vehicleInfo.put("hull_id", vehicleHullId);
		String vehicleExpDate = xmlparse.getNodeContentByAttribute(new Property("tag", "current_exp_date"));
		vehicleInfo.put("current_exp_date", vehicleExpDate);
		String vehicleStatus = xmlparse.getNodeContentByAttribute(new Property("tag", "vehicle_status"));
		vehicleInfo.put("vehicle_status", vehicleStatus);
		String vehicleLength = xmlparse.getNodeContentByAttribute(new Property("tag", "length"));
		vehicleInfo.put("length", vehicleLength);
		
		return vehicleInfo;
	}
	
	public Properties parseXMLForVehicleRenewalInfo(String response){
		XmlParser xmlparse = new XmlParser(response);
		Properties vehicleInfo = new Properties();
		
		logger.info("Parse XML file for Vehicle renewal info.");
		
		String transNum = xmlparse.getNodeContentByAttribute(new Property("tag", "transaction_number"));
		vehicleInfo.put("transaction_number", transNum);
		String transAmt = xmlparse.getNodeContentByAttribute(new Property("tag", "tran_amount"));
		vehicleInfo.put("tran_amount", transAmt);
		String tan = xmlparse.getNodeContentByAttribute(new Property("tag", "temp_auth_num"));
		vehicleInfo.put("temp_auth_num", tan);
//		String privCode = xmlparse.getNodeContentByAttribute(new Property("tag", "priv_code"));
//		vehicleInfo.put("priv_code", privCode);
//		String licenseYear = xmlparse.getNodeContentByAttribute(new Property("tag", "license_year"));
//		vehicleInfo.put("license_year", licenseYear);
		String oldExpDate = xmlparse.getNodeContentByAttribute(new Property("tag", "old_current_exp_date"));
		vehicleInfo.put("old_current_exp_date", oldExpDate);
		String newExpDate = xmlparse.getNodeContentByAttribute(new Property("tag", "new_current_exp_date"));
		vehicleInfo.put("new_current_exp_date", newExpDate);
		
		return vehicleInfo;
	}
	
	public Properties parseXMLForPrivOrdInfo(String response) {
		XmlParser xmlparse = new XmlParser(response);
		
		logger.info("Parse XML file for Privilege info.");
		
		Properties privilegeInfo = new Properties();
		String transaction_number = xmlparse.getNodeContentByAttribute(new Property("tag", "transaction_number"));
		privilegeInfo.put("transaction_number", transaction_number);
//		String tran_amount = xmlparse.getNodeContentByAttribute(new Property("tag", "tran_amount"));
//		privilegeInfo.put("tran_amount", tran_amount);
//		String status = xmlparse.getNodeContentByAttribute(new Property("tag", "status"));
//		privilegeInfo.put("status", status);
		String temp_auth_num = xmlparse.getNodeContentByAttribute(new Property("tag", "temp_auth_num"));
		privilegeInfo.put("temp_auth_num", temp_auth_num);
		String priv_code = xmlparse.getNodeContentByAttribute(new Property("tag", "priv_code"));
		privilegeInfo.put("priv_code", priv_code);
		String license_year = xmlparse.getNodeContentByAttribute(new Property("tag", "license_year"));
		privilegeInfo.put("license_year", license_year);
		String amount = xmlparse.getNodeContentByAttribute(new Property("tag", "amount"));
		privilegeInfo.put("amount", amount);
		//TODO
//		String agent_comm = xmlparse.getNodeContentByAttribute(new Property("tag", "agent_comm"));
//		privilegeInfo.put("agent_comm", agent_comm);
//		String cust_tran_fee = xmlparse.getNodeContentByAttribute(new Property("tag", "cust_tran_fee"));
//		privilegeInfo.put("cust_tran_fee", cust_tran_fee);
//		String product_name = xmlparse.getNodeContentByAttribute(new Property("tag", "product_name"));
//		privilegeInfo.put("product_name", product_name);
//		//TODO
//		String start_date = xmlparse.getNodeContentByAttribute(new Property("tag", "start_date"));
//		privilegeInfo.put("start_date", start_date);
//		String privilege_eff_end_date = xmlparse.getNodeContentByAttribute(new Property("tag", "privilege_eff_end_date"));
//		privilegeInfo.put("privilege_eff_end_date", privilege_eff_end_date);
		
		return privilegeInfo;
	}
	
	public boolean compareProperties(Properties ormsPro, Properties responsePro) {
		logger.info("Compare properties detail info between ORMS and response file.");
		
		Enumeration<Object> enums = responsePro.keys();
		boolean pass = true;
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			if(!ormsPro.containsKey(key)){
				pass &= false;
				logger.error("Response file does not contain value for key "+key);
				continue;
			}
			pass &= MiscFunctions.compareString(key, ormsPro.getProperty(key), responsePro.getProperty(key));
		}

		return pass;
	}
	
	public OrderInfo verifyOrdPurchaseTypeAndSaleLocInCustOrdPg(String licenseyear, String ordNum, String purchaseType, String salesLocation) {
		LicMgrCustomerOrdersPage orderPg = LicMgrCustomerOrdersPage.getInstance();
		
		logger.info("Get vehicle order info in license year "+licenseyear+" from customer order page.");
		orderPg.searchOrderInLicenseYear(licenseyear);
		
		OrderInfo order = new OrderInfo();
		order = orderPg.getOrderInfosByOrderNum(ordNum);
		
		if(order == null)
			throw new ErrorOnPageException("Could not get order info on customer order page.");
		
		if(!order.productPurchaseType.equalsIgnoreCase(purchaseType))
			throw new ErrorOnPageException("Product order purchase type display wrong on customer order page.", purchaseType, order.productPurchaseType);
		
		if(!order.saleLocation.equalsIgnoreCase(salesLocation))
			throw new ErrorOnPageException("Product sales location display wrong on customr order page.", salesLocation, order.saleLocation);
		
		logger.info("---Verify order purchase type and sales location in cust order page successfully.");
		return order;
		
	}
	
	
}
