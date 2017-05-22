package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RegistrationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 29, 2012
 */
public class LicMgrRegistrationDetailsPage extends LicMgrCommonTopMenuPage {

	private static LicMgrRegistrationDetailsPage _instance = null;

	public static LicMgrRegistrationDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrRegistrationDetailsPage();
		}

		return _instance;
	}

	protected LicMgrRegistrationDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".id", "VehicleREgistrationDetailsTabs");
	}
	
	public List<RegistrationInfo> getRegisHistory(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "vehicleRegistrationHistoryGrid");
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find registration history info...");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		RegistrationInfo historyInfo = new RegistrationInfo();
		List<RegistrationInfo> historyInfoList = new ArrayList<RegistrationInfo>();
		for(int i=1; i<table.rowCount(); i++){
			historyInfo.dateTime = table.getCellValue(i, 0);
			historyInfo.transaction = table.getCellValue(i, 1);
			historyInfo.infoAtTimeOfTras = table.getCellValue(i, 2);
			historyInfo.transLocation = table.getCellValue(i, 3);
			historyInfo.user = table.getCellValue(i, 4);
			historyInfoList.add(historyInfo);
		}
		return historyInfoList;
	}
	
	public String getHullID(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleSerialNum", false));
		if(objs.length <= 0){
			throw new ItemNotFoundException("Can't find Hull ID/Serial #");
		}
		
		return objs[0].getProperty(".text").replaceAll("Hull ID/Serial #", "").trim();
	}
	
	public String getOrderID(){
		String orderID = "";
		List<RegistrationInfo> historyInfoList = this.getRegisHistory();
		RegistrationInfo resigInfo = new RegistrationInfo();
		for(int i =0; i<historyInfoList.size(); i++){
			resigInfo = historyInfoList.get(i);
			if(resigInfo.infoAtTimeOfTras.contains("Order")){
				orderID = resigInfo.infoAtTimeOfTras.split(",")[0].replaceAll("Order: ", "").trim();
			}
		}
		return orderID;
	}
	
	/* The following methods are for getting Registration info on Registration Details page*/
	public String getRegistrationID() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.instanceId", false));
		return RegularExpression.getMatches(text, "\\d+")[0];
	}

	public String getRegistrationStatus() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.status:CB_TO_NAME", false));
		return StringUtil.getSubString(text, "Status");
	}
	
	public String getRegistrationProdNm() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.productInfo", false));
		return StringUtil.getSubString(text, "Product");
	}
	
	public String getRegistrationValidFrom() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.validFrom", false));
		return StringUtil.getSubString(text, "Valid From");
	}
	
	public String getRegistrationValidTo() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.validTo", false));
		return StringUtil.getSubString(text, "Valid To");
	}
	
	public String getRegistrationNumOfDup() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.duplicatesCount", false));
		return StringUtil.getSubString(text, "# of Duplicates");
	}
	
	public String getRegistrationCreationPrice() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.creationPrice:CURRENCY", false));
		return StringUtil.getSubString(text, "Creation Price").replace("$", "");
	}
	
	/* The following methods are for getting Vehicle info on Registration Details page*/
	public String getVehicleMINum() {
		String text = browser.getObjectText(".class", "Html.A", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleNum", false));
		return text;
	}
	
	public void clickVehicleMINum() {
		browser.clickGuiObject(".class", "Html.A", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleNum", false));
	}
	
	public String getVehicleStatus() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleStatus:CB_TO_NAME", false));
		return StringUtil.getSubString(text, "Status");
	}
	
	public String getVehicleHullID() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleSerialNum", false));
		return StringUtil.getSubString(text, "Hull ID/Serial #");
	}
	
	public String getVehicleManuNm() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleManufacturerName", false));
		return StringUtil.getSubString(text, "Manufacturer Name");
	}
	
	public String getVehicleManuPrintNm() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.vehicleManufacturerPrintName", false));
		return StringUtil.getSubString(text, "Manufacturer Print Name");
	}
	
	public String getVehicleYearBuilt() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.buildyear", false));
		return StringUtil.getSubString(text, "Year Built");
	}
	
	public String getVehicleModelYear() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.modelYear", false));
		return StringUtil.getSubString(text, "Model Year");
	}
	
	/* The following methods are for getting Vehicle customer info*/
	public String getVehicleCustNum() {
		String text = browser.getObjectText(".class", "Html.A", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerNumber", false));
		return text;
	}
	
	public void clickVehicleCustNum() {
		browser.clickGuiObject(".class", "Html.A", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerNumber", false));
	}
	
	public String getVehicleCustStatus() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerStatus:CB_TO_NAME", false));
		return StringUtil.getSubString(text, "Status");
	}
	
	public String getVehicleCustClass() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerClassName", false));
		return StringUtil.getSubString(text, "Customer Class");
	}
	
	public String getVehicleCustFName() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerFirstName", false));
		return StringUtil.getSubString(text, "First Name");
	}
	
	public String getVehicleCustLName() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerLastName", false));
		return StringUtil.getSubString(text, "Last Name");
	}
	
	public String getVehicleCustMName() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerMiddleName", false));
		return StringUtil.getSubString(text, "Middle Name");
	}
	
	public String getVehicleCustSuffix() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerSuffix", false));
		return StringUtil.getSubString(text, "Suffix");
	}
	
	public String getVehicleCustBirthDate() {
		String text = browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression("VehicleDetailsRegistrationView-\\d+.customerDob", false));
		return StringUtil.getSubString(text, "Date of Birth");
	}
	
	public RegistrationInfo getRegistrationDetails() {
		RegistrationInfo regis = new RegistrationInfo();
		regis.id = this.getRegistrationID();
		regis.status = this.getRegistrationStatus();
		regis.product = this.getRegistrationProdNm();
		regis.validFromDate = this.getRegistrationValidFrom();
		regis.validToDate = this.getRegistrationValidTo();
		regis.numOfDuplicates = this.getRegistrationNumOfDup();
		regis.creationPrice = this.getRegistrationCreationPrice();
		
		return regis;
	}
	
	public Customer getVehicleCustomerDetails() {
		Customer cust = new Customer();
		cust.custId = this.getVehicleCustNum();
		cust.status = this.getVehicleCustStatus();
		cust.customerClass = this.getVehicleCustClass();
		cust.fName = this.getVehicleCustFName();
		cust.lName = this.getVehicleCustLName();
		cust.mName = this.getVehicleCustMName();
		cust.suffix = this.getVehicleCustSuffix();
		cust.dateOfBirth = this.getVehicleCustBirthDate();
	
		return cust;
	}
	
	/**
	 * Get Boat Info Details for Boat on Registration Details page
	 * @return
	 * @author Lesley Wang
	 * @date Jun 11, 2012
	 */
	public BoatInfo getBoatInfoDetails() {
		BoatInfo v = new BoatInfo();
		v.id = this.getVehicleMINum();
		v.status = this.getVehicleStatus();
		v.hullIdSerialNum = this.getVehicleHullID();
		v.manufacturerName = this.getVehicleManuNm();
		v.modelYear = this.getVehicleModelYear();
		v.manufacturerPrintName = this.getVehicleManuPrintNm();
		return v;
	}
	
	public DealerInfo getDealerDetails() {
		DealerInfo v = new DealerInfo();
		v.id = this.getVehicleMINum();
		v.status = this.getVehicleStatus();	
		return v;
	}
	
	public boolean checkVehicleType(String type) {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", 
				new RegularExpression("^"+type+" Info", false));
	}
	
//	public boolean compareCreationPrice(String price, String actPrice) {
//		if (!StringUtil.isEmpty(price) && StringUtil.compareNumStrings(actPrice, price) != 0) {
//			logger.error("---The creation price displayed on the page " + actPrice + " is wrong!" +
//					" The expected one is " + price);
//			return false;
//		} 
//		return true;
//	}
	
	public boolean verifyRegisDetails(RegistrationInfo regis) {
		RegistrationInfo actRegis = this.getRegistrationDetails();
		
		boolean result = true;
		if (!StringUtil.isEmpty(regis.id)) {
			result &= MiscFunctions.compareResult("registration id", regis.id, actRegis.id);
		} 
		if (!StringUtil.isEmpty(regis.status)) {
			result &= MiscFunctions.compareResult("registraiton status", regis.status, actRegis.status);
		} 
		if (!StringUtil.isEmpty(regis.product)) {
			result &= MiscFunctions.compareResult("registration product name", regis.product, actRegis.product);
		} 
		if (!StringUtil.isEmpty(regis.validFromDate)) {
			result &= MiscFunctions.compareResult("registration valid from date", regis.validFromDate, actRegis.validFromDate);
		} 
		if (!StringUtil.isEmpty(regis.validFromDate)) {
			result &= MiscFunctions.compareResult("registration valid to date", regis.validToDate, actRegis.validToDate);
		} 
		if (!StringUtil.isEmpty(regis.numOfDuplicates)) {
			result &= MiscFunctions.compareResult("registration num of duplicates", regis.numOfDuplicates, actRegis.numOfDuplicates);
		}
		return result;
	}
	
	public boolean verifyVehicleCustomerDetails(Customer cust) {
		Customer actCust = this.getVehicleCustomerDetails();
		
		boolean result = true;
		if (!StringUtil.isEmpty(cust.custId)) {
			result &= MiscFunctions.compareResult("customer id", cust.custId, actCust.custId);
		}
		if (!StringUtil.isEmpty(cust.status)) {
			result &= MiscFunctions.compareResult("customer status", cust.status, actCust.status);
		}
		if (!StringUtil.isEmpty(cust.customerClass)) {
			result &= MiscFunctions.compareResult("customer class", cust.customerClass, actCust.customerClass);
		}
		if (!StringUtil.isEmpty(cust.fName)) {
			result &= MiscFunctions.compareResult("customer first name", cust.fName, actCust.fName);
		}
		if (!StringUtil.isEmpty(cust.lName)) {
			result &= MiscFunctions.compareResult("customer last name", cust.lName, actCust.lName);
		}
		if (!StringUtil.isEmpty(cust.mName)) {
			result &= MiscFunctions.compareResult("customer middle name", cust.mName, actCust.mName);
		}
		if (!StringUtil.isEmpty(cust.suffix)) {
			result &= MiscFunctions.compareResult("customer suffix", cust.suffix, actCust.suffix);
		}
		if (!StringUtil.isEmpty(cust.custId)) {
			result &= MiscFunctions.compareResult("customer date of birth", cust.dateOfBirth, actCust.dateOfBirth);
		}
		return result;
	}
	
	public boolean verifyBoatInfo(BoatInfo boat) {
		BoatInfo actBoat = this.getBoatInfoDetails();
		
		boolean result = true;
		if (!StringUtil.isEmpty(boat.id)) {
			result &= MiscFunctions.compareResult("vehicle id", boat.id, actBoat.id);
		}
		if (!StringUtil.isEmpty(boat.status)) {
			result &= MiscFunctions.compareResult("vehicle status", boat.status, actBoat.status);
		}
		if (!StringUtil.isEmpty(boat.hullIdSerialNum)) {
			result &= MiscFunctions.compareResult("vehicle hull id", boat.hullIdSerialNum, actBoat.hullIdSerialNum);
		}
		if (!StringUtil.isEmpty(boat.manufacturerName)) {
			result &= MiscFunctions.compareResult("vehicle manufacturer", boat.manufacturerName, actBoat.manufacturerName);
		}
		if (!StringUtil.isEmpty(boat.manufacturerPrintName)) {
			result &= MiscFunctions.compareResult("vehicle manufPrintNm", boat.manufacturerPrintName, actBoat.manufacturerPrintName);
		}
		if (!StringUtil.isEmpty(boat.modelYear)) {
			result &= MiscFunctions.compareResult("vehicle model year", boat.modelYear, actBoat.modelYear); 
		}
		result &= this.checkVehicleType("Boat");
		result &= this.verifyRegisDetails(boat.registration);
		
		return result;
	}
	
	public boolean verifyDealerInfo(DealerInfo dealer) {
		DealerInfo actDealer = this.getDealerDetails();
		
		boolean result = true;
		if (!StringUtil.isEmpty(dealer.id)) {
			result &= MiscFunctions.compareResult("dealer id", dealer.id, actDealer.id);
		}
		if (!StringUtil.isEmpty(dealer.status)) {
			result &= MiscFunctions.compareResult("dealer status", dealer.status, actDealer.status);
		}
		result &= this.checkVehicleType("Dealer");
		result &= this.verifyRegisDetails(dealer.registration);
		
		return result;
	}
	
	public void verifyAllVehicleRegisInfo(Vehicle v, Customer cust) {
		boolean result = true;
		
		if (v instanceof BoatInfo) {
			result &= this.verifyBoatInfo((BoatInfo)v);
		} else if (v instanceof DealerInfo) {
			result &= this.verifyDealerInfo((DealerInfo)v);
		}
		result &= this.verifyVehicleCustomerDetails(cust);
		
		if (!result) {
			throw new ErrorOnPageException("The registration details are displayed wrongly on the page! " +
					"Please check the logger for more details!");
		} else {
			logger.info("The registration details are CORRECT!");
		}
	}
	
	/**
	 * verify registration product name on the Registration Details page
	 */
	public boolean verifyRenewRegProductName(String prodName) {
		String prodName1 = this.getRegistrationProdNm();
		return MiscFunctions.compareResult("registration product name", prodName, prodName1);
	} 
	
	/**
	 * verify registration price on the Registration Details page
	 */
	public boolean verifyRenewRegPrice(String price) {
		String price1 = this.getRegistrationCreationPrice();
		return MiscFunctions.compareResult("Registration price", new BigDecimal(price), 
				new BigDecimal(price1));
	}
	
	/**
	 * verify registration valid from date on the Registration Details page
	 */
	public boolean verifyRenewRegValidFromDates(String validFromDate) {
		String validFromDate1 = this.getRegistrationValidFrom();
		return MiscFunctions.compareResult("Registration valid from date", validFromDate, validFromDate1);
	}
	
	/**
	 * verify registration valid to date on the Registration Details page
	 */
	public boolean verifyRenewRegValidToDates(String validToDate) {
		String validToDate1 = this.getRegistrationValidTo();
		return MiscFunctions.compareResult("Registration valid to date", validToDate, validToDate1);
	}
	
	/**
	 * verify registration status on the Registration Details page
	 */
	public boolean verifyRegStatus(String status) {
		String actStatus = this.getRegistrationStatus();
		return MiscFunctions.compareResult("Registration Status", status, actStatus);
	}
	
	/**
	 * verify registration status on the Registration Details page
	 */
	public boolean verifyRegNumOfDup(String expNum) {
		String actNum = this.getRegistrationNumOfDup();
		return MiscFunctions.compareResult("Registration Num of Duplicate", expNum, actNum);
	}
}
