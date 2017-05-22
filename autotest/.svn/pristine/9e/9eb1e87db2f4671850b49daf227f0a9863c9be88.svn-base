/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 21, 2011
 */
public class LicMgrSupplyPricingPage extends LicMgrSupplyProductDetailsPage implements ILicMgrProductPricingPage {

	private static LicMgrSupplyPricingPage instance=null;
	
	private LicMgrSupplyPricingPage(){}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "HFProductPricingGrid");
	}
	
	public static LicMgrSupplyPricingPage getInstance(){
		if(instance==null){
			instance=new LicMgrSupplyPricingPage();
		}
		return instance;
	}
	
	public void clickAddProductPricing(){
		browser.clickGuiObject(".class", "Html.A", ".text","Add Product Pricing");
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

	@Override
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", locClassRegex, locationClass);
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
		ajax.waitLoading();
	}

	@Override
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text","Go");
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
		browser.clickGuiObject(".class", "Html.A", ".text", id);
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
		
		int counter = -1;
		String status = "";
		String locClass, licYearFrom, licYearTo;
		for(int i = 1; i < table.rowCount(); i++) {
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();
			licYearFrom = table.getCellValue(i, 3).trim();
			licYearTo = table.getCellValue(i, 4).trim();
			
			if(status.equalsIgnoreCase("Active")
					&& locClass.equalsIgnoreCase(pricing.locationClass)
					&& licYearFrom.equalsIgnoreCase(pricing.licYearFrom)
					&& (licYearFrom.equalsIgnoreCase("All") ? true:licYearTo.equalsIgnoreCase(pricing.licYearTo))) {
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
		boolean isExists = false;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowCount=table.rowCount();
		for(int i=1;i<rowCount;i++){
			String status=table.getCellValue(i, 1).trim();
			String locClass=table.getCellValue(i, 2).trim();
			String licYearFrom=table.getCellValue(i, 3).trim();
			if(status.equals(pricing.status)&&locClass.equals(pricing.locationClass)&&licYearFrom.equals(pricing.licYearFrom)){
				isExists = true;
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
		this.searchPricingRecords(status, "");
	}
	
	@Override
	public void searchPricingRecords() {
		this.searchPricingRecords("", "");
	}
	
	@Override
	public String getPricingID(String status,String locClass, String licYearFrom,
			String hasPurchaseType) {
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
			
			if(statusOnPage.equals(status)&& locClass.equals(locClassOnPage)&& licYearFrom.equals(licYearFromOnPage)) {
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
		
		col = table.findColumn(0, "license Year From");
		if(!table.getCellValue(row, col).trim().equals(pricing.licYearFrom)){
			result &= false;
			logger.error("Expected license Year From should be " + pricing.licYearFrom 
					+ ", but actually is " + table.getCellValue(row, col).trim());
		}
		
		col = table.findColumn(0, "license Year To");
		if(!table.getCellValue(row, col).trim().equals(pricing.licYearTo)){
			result &= false;
			logger.error("Expected license year to should be " + pricing.licYearTo 
					+ ", but actually is " + table.getCellValue(row, col).trim());
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
		String vendorFeeFromUI = table.getCellValue(row,col).trim().substring(1);
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
		if(Double.valueOf(custPriceFromUI)- custPriceExpected > 0.001){
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
		
		// PCR3679 Total Price has been removed
//		col = table.findColumn(0, "Total Price");
//		Double totalPriceExpected = custPriceExpected + Double.valueOf(pricing.stateTransFee);
//		String totalPriceFromUI = table.getCellValue(row, col).trim().substring(1);
//		if(Double.valueOf(totalPriceFromUI) - totalPriceExpected > 0.001){
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

	@Override
	public void setPricingSearchInfo(PricingInfo pricing) {
		if(this.isShowCurrentRecordsOnlyChecked()){
			this.uncheckShowCurrentRecordsOnly();
		}
		this.selectStatus(pricing.status);
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
		
		return result;
	}
	
	public void clickPricingTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Pricing", false));
	}
}
