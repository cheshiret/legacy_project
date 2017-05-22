package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.activenetwork.qa.awo.apiclient.verifone.VerifoneAutoClient;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class MSVeriFoneTestCase extends MSEquipmentTestCase {
	
	protected String host;
	protected int port;
	protected VerifoneAutoClient verifone;
	protected String request;
	protected LinkedHashMap<String, String> nodesInfo;
	protected List<Map<String, String>> response;
	
	protected final String successCode = "0000";
	
	protected final String c_contract = "000A";
	protected final String c_transType = "0011";
	protected final String c_software_version = "0050";
	protected final String c_regID = "1052";
	protected final String c_pwd = "1055";
	protected final String c_processDateTime = "1061";
	protected final String c_tan = "1999";//TODO
	
	protected final String c_completion = "1201";
	protected final String c_receiptNum = "4005";
	protected final String c_privCode = "4201";
	protected final String c_licYear = "4205";
	protected final String c_replace_ind = "4285";
	protected final String c_amount = "4211";//state fee
	protected final String c_agent_comm = "4212";//vendor fee
	protected final String c_trans_fee = "4214";//transaction fee
	
	protected final String c_cust_id = "2001";
	protected final String c_ssn = "2051";
	protected final String c_idType = "2071";
	protected final String c_idState = "2072";
	protected final String c_idNum = "2073";
	protected final String c_idCountry = "2082";
	protected final String c_fullName = "3000";//3005 + 3001 + 3002 + 3003 + 3006 separated with spaces.
	protected final String c_fName = "3001";
	protected final String c_mName = "3002";
	protected final String c_lName = "3003";
	protected final String c_prefix = "3005";
	protected final String c_suffix = "3006";
	protected final String c_addr = "3201";
	protected final String c_city = "3203";
	protected final String c_state = "3204";
	protected final String c_zip = "3205";
	protected final String c_country = "3215";
	protected final String c_phone = "3401";
	protected final String c_email = "3451";
	protected final String c_residency = "3501";
	protected final String c_add_res_proof = "3503";
	protected final String c_dob = "3601";
	protected final String c_gender = "3611";
	protected final String c_ethnicity = "3612";
	protected final String c_licCount = "4000";
	protected final String c_tran_start_date = "4001";
	
	protected final String c_reason_code = "1501";
	
	
	public MSVeriFoneTestCase() {
		super();
		host = AwoUtil.getOrmsURL(env).replaceAll("https://", "").split(":")[0];
		port = 7070;
		verifone = new VerifoneAutoClient(host, port);
		nodesInfo = new LinkedHashMap<String, String>();
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
	}
	
	protected void resetVerifoneClient() {
		verifone = new VerifoneAutoClient(host, port);
		verifone.resetLogger(logger.getLogger());
	}
		
	protected String constructRequestInCSV(LinkedHashMap<String, String> properties) {
		logger.info("Constructing verifone Request in CSV format");
		StringBuffer sb = new StringBuffer();
		for(Iterator<String> it =  properties.keySet().iterator();it.hasNext();){
			String key = (String) it.next();
			String value = (String) properties.get(key);
			sb.append(key+":"+value);
			sb.append(",");
		}
		logger.debug(sb.toString());
		return sb.toString();
//		PrintWriter pw;
//		try {
//			pw = new PrintWriter(filepath);
//			pw.write(sb.toString());
//			pw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	
	protected String getReturnFieldByCode(List<Map<String, String>> response, String attrID) {
		String returnCode = null;
		for(int i=0; i<response.size(); i++){
			Map<String, String> map = response.get(i);
			Set<String> key = map.keySet();
			if(key.contains(attrID)) {
				returnCode=map.get(attrID);
				break;
			}
		}
		logger.info("Get return code for attribute ID "+attrID+" from response as "+returnCode);
		return returnCode;
	}

	protected String getCompletionCode(List<Map<String, String>> response) {
		return getReturnFieldByCode(response, c_completion);
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
	
	protected Properties getPriOrdInfoFromORMS(String licenseyear, String ordNum, String schema) {
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
		privilegeInfo.put("purchase_type", order.productPurchaseType);
		privilegeInfo.put("status", order.status);
		privilegeInfo.put("sale_location", order.saleLocation);
		
		return privilegeInfo;
	}
	
	protected boolean compareProperties(Properties ormsPro, Properties responsePro) {
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
}
