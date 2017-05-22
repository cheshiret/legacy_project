package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class LicMgrVehiclePricingPage extends LicMgrEditVehicleDetailsPage implements ILicMgrProductPricingPage {
	
	private static LicMgrVehiclePricingPage _instance = null;
	
	protected LicMgrVehiclePricingPage() {}
	
	public static LicMgrVehiclePricingPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVehiclePricingPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Product Pricing");
	}
	
	public void clickAddProductPricing() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Product Pricing");
	}

	@Override
	public boolean isShowCurrentRecordsOnlyChecked() {
		return browser.isCheckBoxSelected(".id", showCurrentOnlyRegex);
	}

	@Override
	public boolean isShowCurrentRecordsExist() {
		return browser.checkHtmlObjectExists(".id", showCurrentOnlyRegex);
	}
	
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.showCurrentRecordsOnly", false));
		ajax.waitLoading();
	}
	
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.showCurrentRecordsOnly", false));
		ajax.waitLoading();
	}
	
	public void selectStatus(String status) {
		if(null != status && status.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.statusID", false), status);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.statusID", false), 0);
		}
	}
	
	
	public void selectPurchaseType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.purchaseTypeID", false), type);
	}
	
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.locationClassID", false), locationClass);
	}
	
	/**
	 * Click Go button to search pricing records
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go", false));
		ajax.waitLoading();
	}
	
	@Override
	public int getRowCount() {
		int rowCount=0;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		rowCount=table.rowCount();
		Browser.unregister(objs);
		return rowCount;
	}

	@Override
	public void clickPricingID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	@Override
	public boolean checkPricingRecordExists(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	public boolean checkPricingRecordExists(PricingInfo pricing){
		String duplicateLabelInDB = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		pricing.purchaseType = pricing.purchaseType.matches("Replacement|Duplicate") ? duplicateLabelInDB :  pricing.purchaseType;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowCount=table.rowCount();
		for(int i=1;i<rowCount;i++){
			String status=table.getCellValue(i, 1).trim();
			String locClass=table.getCellValue(i, 2).trim();
			String licYearFrom=table.getCellValue(i, 3).trim();
			String purchaseType=table.getCellValue(i, 5).trim();
			if(status.equals(pricing.status)&&locClass.equals(pricing.locationClass)&&licYearFrom.equalsIgnoreCase(pricing.licYearFrom)&&purchaseType.equals(pricing.purchaseType)){
				return true;
			}
		}
		
		return false;
	}

	public String getPricingID(PricingInfo pricing) {
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Pricing Table.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];

		String duplicateLabelInDB = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		pricing.purchaseType = pricing.purchaseType.matches("Replacement|Duplicate") ? duplicateLabelInDB :  pricing.purchaseType;
		
		int counter = -1;
		String status = "";
		String locClass, licYearFrom, licYearTo, purchaseType;
		for(int i = 1; i < table.rowCount(); i++) {
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();
			licYearFrom = table.getCellValue(i, 3).trim();
			licYearTo = table.getCellValue(i, 4).trim();
			purchaseType = table.getCellValue(i, 5).trim();
			if(status.equalsIgnoreCase(pricing.status)
					&& locClass.equalsIgnoreCase(pricing.locationClass)
					&& licYearFrom.equalsIgnoreCase(pricing.licYearFrom)
					&& (licYearFrom.equalsIgnoreCase("All") ? true:(licYearFrom.equalsIgnoreCase(pricing.licYearFrom) && licYearTo.equals(pricing.licYearTo)))
					&& purchaseType.equalsIgnoreCase(pricing.purchaseType)) {
				counter = i;
				break;
			}
		}
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find the matched pricing id.");
		}
		String id = table.getCellValue(counter, 0).trim();
		Browser.unregister(objs);
		return id;
	}

	/**
	 * Search pricing records by conditions: status, purchase type and location class
	 * @param status
	 * @param purchaseType
	 * @param locationClass
	 */
	public void searchPricingRecords(String status, String purchaseType, String locationClass) {
		if(isShowCurrentRecordsOnlyChecked()) {
			uncheckShowCurrentRecordsOnly();
		}
		if(null != status && status.length() > 0) {
			selectStatus(status);
		}
		if(null != purchaseType && purchaseType.length() > 0) {
			selectPurchaseType(purchaseType);
		}
		if(null != locationClass && locationClass.length() > 0) {
			selectLocationClass(locationClass);
		}
		clickGo();
	}

	/**
	 * Search pricing records by conditions: status and location class
	 * @param status
	 * @param locationClass
	 */
	public void searchPricingRecords(String status, String locationClass) {
		if(isShowCurrentRecordsOnlyChecked()) {
			uncheckShowCurrentRecordsOnly();
		}
		if(null != status && status.length() > 0) {
			selectStatus(status);
		}
		if(null != locationClass && locationClass.length() > 0) {
			selectLocationClass(locationClass);
		}
		if(!isShowCurrentRecordsOnlyChecked()) {
			clickGo();
		}
	}
	
	@Override
	public void searchPricingRecords(String status) {
		this.searchPricingRecords(status, "", "");
	}
	
	@Override
	public void searchPricingRecords() {
		this.searchPricingRecords("", "", "");
	}
	
	@Override
	public String getPricingID(String status,String locClass, String licYearFrom,
			String purchaseType) {
		
		String id="";
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Pricing Table.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
	
		for(int i = 1; i < table.rowCount(); i++) {
			String statusOnPage = table.getCellValue(i, 1).trim();
			String locClassOnPage = table.getCellValue(i, 2).trim();
			String licYearFromOnPage = table.getCellValue(i, 3).trim();
			String purchaseTypeOnPage=table.getCellValue(i, 5);
			
			if(statusOnPage.equals(status)&& locClass.equals(locClassOnPage)&& licYearFrom.equals(licYearFromOnPage)&&purchaseTypeOnPage.equals(purchaseType)) {
				id=table.getCellValue(i, 0);
				break;
			}
		}
		Browser.unregister(objs);
		return id;
	}

	@Override
	public boolean comparePricingRecordInfo(PricingInfo pricing) {
		boolean result = true;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		
		logger.info("Comprate " + pricing.id + " pricing info.");
		
		int row = table.findRow(0, pricing.id);
		//compare status
		if(!table.getCellValue(row, 1).trim().equals(pricing.status)){
			result &= false;
			logger.error("Expected status should be " + pricing.status 
					+ ", but actually is " + table.getCellValue(row, 1).trim());
		}
		//compare location class
		if(!table.getCellValue(row, 2).trim().equals(pricing.locationClass)){
			result &= false;
			logger.error("Expected location class should be " + pricing.locationClass 
					+ ", but actually is " + table.getCellValue(row, 2).trim());
		}
		//compare license year from
		if(!table.getCellValue(row, 3).trim().equals(pricing.licYearFrom)){
			result &= false;
			logger.error("Expected license Year From should be " + pricing.licYearFrom 
					+ ", but actually is " + table.getCellValue(row, 3).trim());
		}
		//compare license year to
		if(!table.getCellValue(row, 4).trim().equals(pricing.licYearTo)){
			result &= false;
			logger.error("Expected license year to should be " + pricing.licYearTo 
					+ ", but actually is " + table.getCellValue(row, 4).trim());
		}
		//compare purchase type
		if(!table.getCellValue(row, 5).equals(pricing.purchaseType)){
			result &= false;
			logger.error("Expected purchase type should be " + pricing.purchaseType 
					+ ", but acutally is " + table.getCellValue(row, 5).trim());
		}
		//compare effective from date
		if(!DateFunctions.formatDate(table.getCellValue(row, 6).trim()).equals(pricing.effectFrom)){
			result &= false;
			logger.error("Expected effective from date should be " + pricing.effectFrom 
					+ ", but acutally is " + DateFunctions.formatDate(table.getCellValue(row, 6).trim()));
		}
		//compare effective to date
		if(!DateFunctions.formatDate(table.getCellValue(row, 7).trim()).equals(pricing.effectTo)){
			result &= false;
			logger.error("Expected effective to date should be " + pricing.effectTo 
					+ ", but acutally is " + DateFunctions.formatDate(table.getCellValue(row, 7).trim()));
		}
		//compare state fee
		String stateFeeFromUI = table.getCellValue(row, 8).trim().substring(1);		
		if(!Double.valueOf(stateFeeFromUI).equals(Double.valueOf(pricing.stateFee))){
			result &= false;
			logger.error("Expected state fee should be " + pricing.stateFee 
					+ ", but actually is " + stateFeeFromUI);
		}
		//compare vendor fee
		String vendorFeeFromUI = table.getCellValue(row, 9).trim().substring(1);
		if(!Double.valueOf(vendorFeeFromUI).equals(Double.valueOf(pricing.vendorFee))){
			result &= false;
			logger.error("Expected vendor fee should be " + pricing.vendorFee 
					+ ", but actually is " + vendorFeeFromUI);
		}
		//compare transaction fee
		String transFeeFromUI = table.getCellValue(row, 10).trim().substring(1);
		if(!Double.valueOf(transFeeFromUI).equals(Double.valueOf(pricing.transFee))){
			result &= false;
			logger.error("Expected transaction fee should be " + pricing.transFee 
					+ ", but actually is " + transFeeFromUI);
		}
		//compare customer prices should equals state fee plus vendor fee
		Double custPriceExpected = Double.valueOf(pricing.stateFee) + Double.valueOf(pricing.vendorFee)+ Double.valueOf(pricing.transFee); 
		String custPriceFromUI = table.getCellValue(row, 11).trim().substring(1);
		if(Double.valueOf(custPriceFromUI)-custPriceExpected>0.001){
			result &= false;
			logger.error("Expected transaction fee should be " + custPriceExpected 
					+ ", but actually is " + custPriceFromUI);
		}
		//compare state transaction fee
		String stateTransFeeFromUI = table.getCellValue(row, 12).trim().substring(1);
		if(!Double.valueOf(stateTransFeeFromUI).equals(Double.valueOf(pricing.stateTransFee))){
			result &= false;
			logger.error("Expected state transaction fee should be " + pricing.stateTransFee 
					+ ", but actually is " + stateTransFeeFromUI);
		}
		
		// PCR3679 Total Price has been removed
//		//compare total prices
//		Double totalPriceExpected = custPriceExpected + Double.valueOf(pricing.stateTransFee);
//		String totalPriceFromUI = table.getCellValue(row, 13).trim().substring(1);
//		if(Double.valueOf(totalPriceFromUI)-(totalPriceExpected)>0.001){
//			result &= false;
//			logger.error("Expected total price should be " + totalPriceExpected 
//					+ ", but actually is " + totalPriceFromUI);
//		}
		
		Browser.unregister(objs);
		
		return result;
	}

	@Override
	public List<String> getPricingIDs() {
		List<String> pricingIDs = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "HFProductPricingGrid");
		
		Property[] p = new Property[2];
		p[0] = new Property(".text",new RegularExpression("^\\d+",false));
		p[1] = new Property(".class","Html.A");
		IHtmlObject[] idObjs = browser.getHtmlObject(p, objs[0]);
		
		for(int i=0; i<idObjs.length; i++){
			pricingIDs.add(idObjs[i].text());
		}
		
		return pricingIDs;
	}

	public List<String> getStatusElements() {
		return browser.getDropdownElements(".id", 
				new RegularExpression("^HFProductPricingSearchCriteria-\\d+\\.statusID",false));
	}
	
	public List<String> getLocationClassElements(){
		return browser.getDropdownElements("id", 
				new RegularExpression("^HFProductPricingSearchCriteria-\\d+\\.locationClassID",false));
	}
	
	public List<String> getPurchaseTypeElements(){
		return browser.getDropdownElements(".id", 
				new RegularExpression("^HFProductPricingSearchCriteria-\\d+\\.purchaseTypeID", false));
	}

	@Override
	public void setPricingSearchInfo(PricingInfo pricing) {
		if(this.isShowCurrentRecordsOnlyChecked()){
			this.uncheckShowCurrentRecordsOnly();
		}
		this.selectStatus(pricing.status);
		this.selectPurchaseType(pricing.purchaseType);
		this.selectLocationClass(pricing.locationClass);		
	}

	public List<String> getColumnValue(String colName){		
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		
		int col = table.findColumn(0, colName);
		List<String> colValues = table.getColumnValues(col);
		Browser.unregister(objs);
		
		return colValues;
	}
	
	public List<String> getStatusColValue(){
		return this.getColumnValue("Status");
	}
	
	public List<String> getLocClassColValue(){
		return this.getColumnValue("Location Class");
	}
	
	public List<String> getPurchaseTypeColValue(){
		return this.getColumnValue("Purchase Type");
	}


	public boolean verifySearchResult(PricingInfo pricing) {
		boolean result = true;
		List<String> statusCloValues = this.getStatusColValue();
		for(int i=1; i<statusCloValues.size(); i++){
			if(!statusCloValues.get(i).equals(pricing.status)){
				result &= false;
				logger.error("Pricing status all should be " + pricing.status 
						+ ", but this actually is " + statusCloValues.get(i));
				break;
			}
		}
		
		List<String> locClassCloValues = this.getLocClassColValue();
		for(int i=1; i<locClassCloValues.size(); i++){
			if(!locClassCloValues.get(i).equals(pricing.locationClass)){
				result &= false;
				logger.error("Pricing location class all should be " + pricing.locationClass 
						+ ", but actually this is " + locClassCloValues.get(i));
				break;
			}
		}
		
		List<String> purchaseTypeCloValues = this.getPurchaseTypeColValue();
		for(int i=1; i<purchaseTypeCloValues.size(); i++){
			if(!purchaseTypeCloValues.get(i).equals(pricing.purchaseType)){
				result &= false;
				logger.error("Pricing purchase type all should be " + pricing.purchaseType 
						+ ", but actually this is " + purchaseTypeCloValues.get(i));
				break;
			}
		}
		
		return result;
	}

	public void clickPricingTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Pricing", false));
	}
}
