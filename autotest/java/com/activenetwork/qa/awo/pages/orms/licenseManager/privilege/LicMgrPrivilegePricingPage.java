/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrPrivilegePricingPage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegePricingPage extends LicMgrPrivilegeProductDetailsPage implements ILicMgrProductPricingPage {
	
	private static LicMgrPrivilegePricingPage instance=null;
	
	private LicMgrPrivilegePricingPage(){}
	
	public static LicMgrPrivilegePricingPage getInstance(){
		if(instance==null){
			instance=new LicMgrPrivilegePricingPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text", "Add Product Pricing") && super.exists();
	}
	
	@Override
	public void clickAddProductPricing(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Product Pricing");
	}
	
	public boolean isPricingExisting(){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		if(objs.length<1){
			throw new ObjectNotFoundException("Pricing table is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int count=table.columnCount();
		if(count>1){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isShowCurrentRecordsOnlyChecked() {
		return browser.isCheckBoxSelected(".id", showCurrentOnlyRegex);
	}

	@Override
	public boolean isShowCurrentRecordsExist() {
		return browser.checkHtmlObjectExists(".id", showCurrentOnlyRegex);
	}
	
	@Override
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", showCurrentOnlyRegex);
		ajax.waitLoading();
	}

	public void selectPurchaseType(String purchaseType) {
		if(null != purchaseType && purchaseType.length() > 0) {
			browser.selectDropdownList(".id", typeRegex, purchaseType);
		} else {
			browser.selectDropdownList(".id", typeRegex, 0);
		}
		
	}
	
	@Override
	public void selectLocationClass(String locationClass) {
		if(null != locationClass && locationClass.length() > 0) {
			browser.selectDropdownList(".id", locClassRegex, locationClass);
		} else {
			browser.selectDropdownList(".id", locClassRegex, 0);
		}		
	}

	@Override
	public void selectStatus(String status) {
		if(null != status && status.length() > 0) {
			browser.selectDropdownList(".id", statusRegex, status);
		} else {
			browser.selectDropdownList(".id", statusRegex, 0);
		}
	}

	@Override
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", showCurrentOnlyRegex);
	}

	@Override
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text","Go");
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

	@Override
	public String getPricingID(PricingInfo pricing) {
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Product Pricing Table.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		
		String duplicateLabelInDB = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		pricing.purchaseType = pricing.purchaseType.matches("(Replacement|Duplicate).*") ? duplicateLabelInDB :  pricing.purchaseType;
		
		int counter = -1;
		String status = "";
		String locClass, licYearFrom, licYearTo, purchaseType;
		for(int i = 1; i < table.rowCount(); i++) {
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();
			licYearFrom = table.getCellValue(i, 3).trim();
			licYearTo = table.getCellValue(i, 4).trim();
			purchaseType = table.getCellValue(i, 5).trim();
			if(pricing.purchaseType.matches("(Replacement|Duplicate).*")){
				if(purchaseType.matches("(Replacement|Duplicate).*")){
					purchaseType = pricing.purchaseType;
				}
			}
			if(status.equalsIgnoreCase(pricing.status)
					&& locClass.equalsIgnoreCase(pricing.locationClass)
					&& licYearFrom.equalsIgnoreCase(pricing.licYearFrom)
					&& ((licYearFrom.equalsIgnoreCase("All") ? true:(licYearFrom.equalsIgnoreCase(pricing.licYearFrom) && licYearTo.equals(pricing.licYearTo))))
					&& purchaseType.equalsIgnoreCase(pricing.purchaseType)) {
				counter = i;
				break;
			}
		}
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find the matched pricing id.");
		}
		pricing.status = status;
		String id = table.getCellValue(counter, 0).trim();
		Browser.unregister(objs);
		return id;
	}
	
	@Override
	public boolean checkPricingRecordExists(PricingInfo pricing){
		String duplicateLabelInDB = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement", this.getContract());
		pricing.purchaseType = pricing.purchaseType.matches("Replacement|Duplicate") ? duplicateLabelInDB :  pricing.purchaseType;
		boolean isExists = false;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowCount=table.rowCount();
		for(int i=1;i<rowCount;i++){
			String status=table.getCellValue(i, 1).trim();
			String locClass=table.getCellValue(i, 2).trim();
			String licYearFrom=table.getCellValue(i, 3).trim();
			String purchaseType=table.getCellValue(i, 5).trim();
			if(status.equals(pricing.status)&&locClass.equals(pricing.locationClass)&&licYearFrom.equalsIgnoreCase(pricing.licYearFrom)&&purchaseType.equals(pricing.purchaseType)){
				isExists =  true;
				break;
			}
		}
		
		Browser.unregister(objs);
		return isExists;
	}

	/**
	 * Search pricing records by conditions: status and location class
	 * @param status
	 * @param locationClass
	 */
	public void searchPricingRecords(String status, String locationClass) {
		if(isShowCurrentRecordsOnlyChecked()) {
			uncheckShowCurrentRecordsOnly();
			ajax.waitLoading();
		}
		if(null != status && status.length() > 0) {
			selectStatus(status);
		}
		if(null != locationClass && locationClass.length() > 0) {
			selectLocationClass(locationClass);
		}
		if(!isShowCurrentRecordsOnlyChecked()) {
			clickGo();
			ajax.waitLoading();
		}
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
			ajax.waitLoading();
		}
//		if(null != status && status.length() > 0) {
			selectStatus(status);
			
//		}
//		if(null != purchaseType && purchaseType.length() > 0) {
			selectPurchaseType(purchaseType);
//		}
//		if(null != locationClass && locationClass.length() > 0) {
			selectLocationClass(locationClass);
//		}
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
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
			
			if(statusOnPage.equals(status)&& locClass.equals(locClassOnPage)&& licYearFrom.equalsIgnoreCase(licYearFromOnPage)&&purchaseTypeOnPage.equals(purchaseType)) {
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
		int col = table.findColumn(0, "Status");
		if(!table.getCellValue(row, col).trim().equals(pricing.status)){
			result &= false;
			logger.error("Expected status should be " + pricing.status 
					+ ", but actually is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "Location Class");
		if(!table.getCellValue(row, col).trim().equals(pricing.locationClass)){
			result &= false;
			logger.error("Expected location class should be " + pricing.locationClass 
					+ ", but actually is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "License Year From");
		if(!table.getCellValue(row, col).trim().equals(pricing.licYearFrom)){
			result &= false;
			logger.error("Expected license Year From should be " + pricing.licYearFrom 
					+ ", but actually is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "License Year To");
		if(!table.getCellValue(row, col).trim().equals(pricing.licYearTo)){
			result &= false;
			logger.error("Expected license year to should be " + pricing.licYearTo 
					+ ", but actually is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "Purchase Type");
		if(!table.getCellValue(row, col).equals(pricing.purchaseType)){
			result &= false;
			logger.error("Expected purchase type should be " + pricing.purchaseType 
					+ ", but acutally is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "Effective From Date");
		if(!DateFunctions.formatDate(table.getCellValue(row, col).trim()).equals(pricing.effectFrom)){
			result &= false;
			logger.error("Expected effective from date should be " + pricing.effectFrom 
					+ ", but acutally is " + DateFunctions.formatDate(table.getCellValue(row, col).trim()));
		}
		
		col = table.findColumn(0, "Effective To Date");
		if(!DateFunctions.formatDate(table.getCellValue(row, col).trim()).equals(pricing.effectTo)){
			result &= false;
			logger.error("Expected effective to date should be " + pricing.effectTo 
					+ ", but acutally is " + DateFunctions.formatDate(table.getCellValue(row, col).trim()));
		}
		
		col = table.findColumn(0, "State Fee");
		String stateFeeFromUI = table.getCellValue(row, col).trim().substring(1);		
		if(!Double.valueOf(stateFeeFromUI).equals(Double.valueOf(pricing.stateFee))){
			result &= false;
			logger.error("Expected state fee should be " + pricing.stateFee 
					+ ", but actually is " + stateFeeFromUI);
		}
		
		col = table.findColumn(0, "Vendor Fee");
		String vendorFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		if(!Double.valueOf(vendorFeeFromUI).equals(Double.valueOf(pricing.vendorFee))){
			result &= false;
			logger.error("Expected vendor fee should be " + pricing.vendorFee 
					+ ", but actually is " + vendorFeeFromUI);
		}
		
		col = table.findColumn(0, "Transaction Fee");
		String transFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		if(!Double.valueOf(transFeeFromUI).equals(Double.valueOf(pricing.transFee))){
			result &= false;
			logger.error("Expected transaction fee should be " + pricing.transFee 
					+ ", but actually is " + transFeeFromUI);
		}
		
		col = table.findColumn(0, "Customer Price");
		Double custPriceExpected = Double.valueOf(pricing.stateFee) + Double.valueOf(pricing.vendorFee)+ Double.valueOf(pricing.transFee); 
		String custPriceFromUI = table.getCellValue(row, col).trim().substring(1);
		if(Double.valueOf(custPriceFromUI)-custPriceExpected>0.001){
			result &= false;
			logger.error("Expected transaction fee should be " + custPriceExpected 
					+ ", but actually is " + custPriceFromUI);
		}
		
		col = table.findColumn(0, "State Trans Fee");
		String stateTransFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		if(!Double.valueOf(stateTransFeeFromUI).equals(Double.valueOf(pricing.stateTransFee))){
			result &= false;
			logger.error("Expected state transaction fee should be " + pricing.stateTransFee 
					+ ", but actually is " + stateTransFeeFromUI);
		}
		// Total Price column is removed per PCR 3679	
//		col = table.findColumn(0, "Total Price");
//		Double totalPriceExpected = custPriceExpected + Double.valueOf(pricing.stateTransFee);
//		String totalPriceFromUI = table.getCellValue(row, col).trim().substring(1);
//		if(Double.valueOf(totalPriceFromUI)-(totalPriceExpected)>0.001){
//			result &= false;
//			logger.error("Expected total price should be " + totalPriceExpected 
//					+ ", but actually is " + totalPriceFromUI);
//		}
		
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
		
		Browser.unregister(objs);
		Browser.unregister(idObjs);
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
			ajax.waitLoading();
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
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<PricingInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<PricingInfo> records = new ArrayList<PricingInfo>();
		int rows;
		int columns;
		PricingInfo info;
		
		
		objs = browser.getTableTestObject(".id", "HFProductPricingGrid");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find privilege pricing list table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrPrivilegePricingPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new PricingInfo();
			info.id = table.getCellValue(i, table.findColumn(0, "ID"));
			info.status = table.getCellValue(i, table.findColumn(0, "Status"));
			info.locationClass = table.getCellValue(i, table.findColumn(0, "Location Class"));
			info.licYearFrom = table.getCellValue(i, table.findColumn(0, "License Year From"));
			info.licYearTo = table.getCellValue(i, table.findColumn(0, "License Year To"));
			info.purchaseType = table.getCellValue(i, table.findColumn(0, "Purchase Type"));
			info.effectFrom = table.getCellValue(i, table.findColumn(0, "Effective From Date"));
			info.effectTo = table.getCellValue(i, table.findColumn(0, "Effective To Date"));
			info.stateProvinces = table.getCellValue(i, table.findColumn(0, "State/Provinces"));
			info.stateFee = table.getCellValue(i, table.findColumn(0, "State Fee"));
			info.vendorFee = table.getCellValue(i, table.findColumn(0, "Vendor Fee"));
			info.transFee = table.getCellValue(i, table.findColumn(0, "Transaction Fee"));
			info.customerPrice = table.getCellValue(i, table.findColumn(0, "Customer Price"));
			info.stateTransFee = table.getCellValue(i, table.findColumn(0, "State Trans Fee"));
			// PCR3679 Total Price has been removed.
//			info.totalPrice = table.getCellValue(i, table.findColumn(0, "Total Price"));
			
						
			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	public void clickPricingTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Pricing", false));
	}
}
