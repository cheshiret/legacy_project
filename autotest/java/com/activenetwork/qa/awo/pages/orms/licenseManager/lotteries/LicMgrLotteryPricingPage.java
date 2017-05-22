package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.ChoiceFee;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 9, 2013
 */
public class LicMgrLotteryPricingPage extends LicMgrLotteryProductCommonPage implements ILicMgrProductPricingPage{

	private static LicMgrLotteryPricingPage _instance = null;
	
	private LicMgrLotteryPricingPage() {}
	
	public static LicMgrLotteryPricingPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryPricingPage();
		return _instance;
	}
	
	protected Property[] pricingTable() {
		return Property.concatPropertyArray(table(), ".id", "ProductPricingListPanel");
	}
	
	protected Property[] pricingTableGrid() {
		return Property.concatPropertyArray(table(), ".id", "HFProductPricingGrid");
	}
	
	protected Property[] addPricingLink() {
		return Property.concatPropertyArray(a(), ".text", "Add Product Pricing");
	}
	
	protected Property[] showOnlyCurrentCheckBox() {
		return Property.concatPropertyArray(this.input("checkbox"), ".id", showCurrentOnlyRegex);
	}
	
	@Override
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.A",".text", "Add Product Pricing");
		return browser.checkHtmlObjectExists(pricingTable()); //Lesley[20140107]: update due to the button doesn't exist if the role has no such feature assigned.
	}
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String LOCATION_CLASS_COL = "Location Class";
	private static final String LICENSE_YEAR_FROM_COL = "License Year From";
	private static final String LICENSE_YEAR_TO_COL = "License Year To";
	private static final String EFFECTIVE_FROM_DATE_COL = "Effective From Date";
	private static final String EFFECTIVE_TO_DATE_COL = "Effective To Date";
	private static final String STATE_PROVINCES_COL = "State/Provinces";
	private static final String RATE_TYPE_COL = "Rate Type";
	private static final String STATE_FEE_COL = "";
	private static final String VENDOR_FEE_COL = "";
	private static final String TRANSACTION_FEE_COL = "";
	private static final String HOLDING_FEE_COL = "";
	private static final String CUSTOMER_PRICE_COL = "Customer Price";
	private static final String STATE_TRANS_FEE_COL = "";
	private static final String STATE_VENDOR_FEE_COL = "";
	
	@Override
	public void clickAddProductPricing(){
		browser.clickGuiObject(addPricingLink());
	}
	
	public boolean isAddProductPricingExist() {
		return browser.checkHtmlObjectExists(addPricingLink());
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
		return browser.checkHtmlObjectExists(showOnlyCurrentCheckBox());
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
		
		int counter = -1;
		String status = "";
		String locClass, licYearFrom, licYearTo, rateType;
		for(int i = 1; i < table.rowCount(); i++) {
			status = table.getCellValue(i, 1).trim();
			locClass = table.getCellValue(i, 2).trim();
			licYearFrom = table.getCellValue(i, 3).trim();
			licYearTo = table.getCellValue(i, 4).trim();
			rateType = table.getCellValue(i, 8).trim();
			
			if(status.equalsIgnoreCase(pricing.status)
					&& locClass.equalsIgnoreCase(pricing.locationClass)
					&& licYearFrom.equalsIgnoreCase(pricing.licYearFrom)
					&& ((licYearFrom.equalsIgnoreCase("All") ? true:(licYearFrom.equalsIgnoreCase(pricing.licYearFrom) && licYearTo.equals(pricing.licYearTo))))
					&& rateType.equalsIgnoreCase(pricing.rateType)) {
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
			String rateType=table.getCellValue(i, 8).trim();
			if(status.equals(pricing.status)&&locClass.equals(pricing.locationClass)&&licYearFrom.equalsIgnoreCase(pricing.licYearFrom)&&rateType.equals(pricing.rateType)){
				isExists =  true;
				break;
			}
		}
		
		Browser.unregister(objs);
		return isExists;
	}
	
	/**
	 * Search pricing records by conditions: status, purchase type and location class
	 * @param status
	 * @param locationClass
	 */
	public void searchPricingRecords(String status, String locationClass) {
		if(this.isShowCurrentRecordsExist() && isShowCurrentRecordsOnlyChecked()) {
			uncheckShowCurrentRecordsOnly();
			ajax.waitLoading();
		}
//		if(null != status && status.length() > 0) {
			selectStatus(status);
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
		this.searchPricingRecords(status, "");
	}
	
	@Override
	public void searchPricingRecords() {
		this.searchPricingRecords("", "");
	}
	
	@Override
	public String getPricingID(String status,String locClass, String licYearFrom,
			String rateType) {
		
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
			String rateTypeOnPage=table.getCellValue(i, 8);
			
			if(statusOnPage.equals(status)&& locClass.equals(locClassOnPage)&& licYearFrom.equalsIgnoreCase(licYearFromOnPage)&&rateTypeOnPage.equals(rateType)) {
				id=table.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		return id;
	}

	protected Property[] pricingInfoTR(String id) {
		return Property.concatPropertyArray(this.tr(), ".text", new RegularExpression("^"+id+".*", false));
	}
	
	protected Property[] multiStateLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Multi");
	}
	
	public String getMultiStateLinkTitle(String pricingID) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(pricingInfoTR(pricingID), multiStateLink()));
		String title = "";
		if (objs.length < 1) {
			logger.warn("No Multi State link for pricing with id=" + pricingID);
		} else {
			title = objs[0].title().replace("|", "");
		}
		Browser.unregister(objs);
		return title;
	}
	
	public String[] getChoiceStateVendorTransFeeInfo(List<ChoiceFee> fees, String rateType) {
		String titlePrex = "";
		String addStateFee = "";
		String addVendorFee = "";
		String addTransFee = "";
		boolean perChoice = rateType.equals(OrmsConstants.PER_CHOICE_TYPE);
		
		for (int i = 0; i < fees.size(); i++) {
			ChoiceFee choice = fees.get(i);
			if (perChoice) {
				titlePrex = "Add.Per Choice:$";
				if (i == 0) {
					for (int j = 0; j < Integer.valueOf(choice.range)-1; j++) {
						addStateFee += titlePrex + choice.stateFee + " ";
						addVendorFee += titlePrex + choice.vendorFee + " ";
						addTransFee += titlePrex + choice.transFee + " ";
					}
				}
			} else {
				titlePrex = "Range(>=" + choice.range + "):$";
			}
			
			addStateFee += titlePrex + choice.stateFee + " ";
			addVendorFee += titlePrex + choice.vendorFee + " ";
			addTransFee += titlePrex + choice.transFee + " ";
		}
		
		String[] fee = new String[] {addStateFee, addVendorFee, addTransFee};
		return fee;
	}
	
	public boolean isFeeLinkExist(String pricingID, String text, String title) {
//		return browser.checkHtmlObjectExists(Property.atList(pricingInfoTR(pricingID), 
//				Property.concatPropertyArray(this.a(), ".text", text, ".title", title)));
		return browser.checkHtmlObjectExists(Property.atList(pricingInfoTR(pricingID), 
				Property.concatPropertyArray(this.a(), ".text", text)));
	}
	
	@Override
	public boolean comparePricingRecordInfo(PricingInfo pricing) {
		boolean result = true;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "HFProductPricingGrid");
		IHtmlTable table=(IHtmlTable)objs[0];
		
		logger.info("Comprate " + pricing.id + " pricing info.");
		
		int row = table.findRow(0, pricing.id);		
		int col = table.findColumn(0, STATUS_COL);
		result &= MiscFunctions.compareString("Pricing Status", pricing.status, table.getCellValue(row, col).trim());
		
		col = table.findColumn(0, LOCATION_CLASS_COL);
		result &= MiscFunctions.compareString("Pricing Location Class", pricing.locationClass, table.getCellValue(row, col).trim());
		
		String contract = this.getContract();
		String licenseYearLabel = DataBaseFunctions.getLicenseFiscalYearTranslatedLabel(contract);
		col = table.findColumn(0, licenseYearLabel + " From");
		result &= MiscFunctions.compareString("Pricing License year From", pricing.licYearFrom, table.getCellValue(row, col).trim());
		
		col = table.findColumn(0, licenseYearLabel + " To");
		result &= MiscFunctions.compareString("Pricing License year To", pricing.licYearTo, table.getCellValue(row, col).trim());
		
		col = table.findColumn(0, EFFECTIVE_FROM_DATE_COL);
		result &= MiscFunctions.compareResult("Pricing Effective From Date", pricing.effectFrom, table.getCellValue(row, col).trim());
		
		col = table.findColumn(0, EFFECTIVE_TO_DATE_COL);
		result &= MiscFunctions.compareResult("Pricing Effective To Date", pricing.effectTo, table.getCellValue(row, col).trim());
		
		col = table.findColumn(0, STATE_PROVINCES_COL);
		String state = "All";
		if (!pricing.isAppliesToAllState) {
			if (pricing.addState.split(",").length > 1) {
				state = "Multi";
				result &= MiscFunctions.compareString("Multi State link tooltip", 
						pricing.addState.replaceAll(",", ";").replaceAll(" ", ""), 
						this.getMultiStateLinkTitle(pricing.id));
			} else {
				state = pricing.addState;
			}
		}
		result &= MiscFunctions.compareResult("Pricing State/Provinces", state, table.getCellValue(row, col).trim());
	
		col = table.findColumn(0, RATE_TYPE_COL);
		result &= MiscFunctions.compareResult("Pricing Rate Type", pricing.rateType, table.getCellValue(row, col).trim());
		
		// Compare State Fee, Vendor Fee, Transaction Fee for different rate type
		String stateFeeLabel = DataBaseFunctions.getStateFeeTranslatedLabel(contract);
		col = table.findColumn(0, stateFeeLabel);
		String stateFeeFromUI = table.getCellValue(row, col).trim().replaceFirst("\\$", "");		
		
		String vendorFeeLabel = DataBaseFunctions.getVendorFeeTranslatedLabel(contract);
		col = table.findColumn(0, vendorFeeLabel + " Fee");
		String vendorFeeFromUI = table.getCellValue(row, col).trim().replaceFirst("\\$", "");	
		
		String transFeeLabel = DataBaseFunctions.getTransFeeTranslatedLabel(contract);
		col = table.findColumn(0, transFeeLabel);
		String transFeeFromUI = table.getCellValue(row, col).trim().replaceFirst("\\$", "");	
		
		Double custPriceExpected = 0.00;
		
		if(pricing.rateType.equals(OrmsConstants.PER_APPLICATION_TYPE)) {
			result &= MiscFunctions.compareString("State Fee", pricing.stateFee, stateFeeFromUI);
			result &= MiscFunctions.compareString("Vendor Fee", pricing.vendorFee, vendorFeeFromUI);
			result &= MiscFunctions.compareString("Transaction Fee", pricing.transFee, transFeeFromUI);
			
			custPriceExpected = Double.valueOf(pricing.stateFee) + Double.valueOf(pricing.vendorFee)+ Double.valueOf(pricing.transFee) + Double.valueOf(pricing.holdingFee);
		} else if (pricing.rateType.equals(OrmsConstants.PER_CHOICE_TYPE) ||
				pricing.rateType.equals(OrmsConstants.CHOICE_RANGE_TYPE)) {
			ChoiceFee basicChoice = pricing.choiceFees.get(0);
			result &= MiscFunctions.compareString("State Fee", basicChoice.stateFee.trim(), stateFeeFromUI.trim());
			result &= MiscFunctions.compareString("Vendor Fee", basicChoice.vendorFee, vendorFeeFromUI);
			result &= MiscFunctions.compareString("Transaction Fee", basicChoice.transFee, transFeeFromUI);
			
			custPriceExpected = Double.valueOf(basicChoice.stateFee) + Double.valueOf(basicChoice.vendorFee)+ Double.valueOf(basicChoice.transFee) + Double.valueOf(pricing.holdingFee);
			
			String[] fees = this.getChoiceStateVendorTransFeeInfo(pricing.choiceFees, pricing.rateType);
			result &= MiscFunctions.compareResult("State Fee Link exist", true, 
					this.isFeeLinkExist(pricing.id, basicChoice.stateFee, fees[0]));
			
			result &= MiscFunctions.compareResult("Vendor Fee Link exist", true, 
					this.isFeeLinkExist(pricing.id, basicChoice.vendorFee, fees[1]));
			result &= MiscFunctions.compareResult("State Fee Link exist", true, 
					this.isFeeLinkExist(pricing.id, basicChoice.transFee, fees[2]));
		} else {
			logger.error("Wrong Rate Type - " + pricing.rateType);
		}
		
		String holdingFeeLabel = DataBaseFunctions.getHoldingFeeTranslatedLabel(contract);
		col = table.findColumn(0, holdingFeeLabel);
		String holdingFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		result &= MiscFunctions.compareResult(holdingFeeLabel, pricing.holdingFee, holdingFeeFromUI);
		
		col = table.findColumn(0, CUSTOMER_PRICE_COL);
		String custPriceFromUI = table.getCellValue(row, col).trim().substring(1);
		if(Double.valueOf(custPriceFromUI)-custPriceExpected>0.001){
			result &= false;
			logger.error("Expected transaction fee should be " + custPriceExpected 
					+ ", but actually is " + custPriceFromUI);
		}
		
		String stateTransFeeLabel = DataBaseFunctions.getStateTransFeeTranslatedLabel(contract);
		col = table.findColumn(0, stateTransFeeLabel);
		String stateTransFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		result &= MiscFunctions.compareResult("State Transaction Fee", pricing.stateTransFee, stateTransFeeFromUI);
		
		String stateVendorFeeLabel = DataBaseFunctions.getStateVendorFeeTranslatedLabel(contract);
		col = table.findColumn(0, stateVendorFeeLabel);
		String stateVendorFeeFromUI = table.getCellValue(row, col).trim().substring(1);
		result &= MiscFunctions.compareResult(stateVendorFeeLabel, pricing.stateVendorFee, stateVendorFeeFromUI);
		
		return result;
	}

	@Override
	public List<String> getPricingIDs() {
		List<String> pricingIDs = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "HFProductPricingGrid");
		
//		Property[] p = new Property[2];
//		p[0] = new Property(".text",new RegularExpression("^\\d+",false));
//		p[1] = new Property(".class","Html.A");
//		IHtmlObject[] idObjs = browser.getHtmlObject(p, objs[0]);
//		
//		for(int i=0; i<idObjs.length; i++){
//			pricingIDs.add(idObjs[i].text());
//		}
		IHtmlTable table=(IHtmlTable)objs[0];
		pricingIDs = table.getColumnValues(0);
		pricingIDs.remove(0); //remove the column label
		Browser.unregister(objs);
//		Browser.unregister(idObjs);
		return pricingIDs;
	}

	public List<String> getStatusElements() {
		return browser.getDropdownElements(".id", 
				new RegularExpression("^HFProductPricingSearchCriteria-\\d+\\.statusID",false));
	}
	
	public List<String> getLocationClassElements(){
		return browser.getDropdownElements(ID_COL, 
				new RegularExpression("^HFProductPricingSearchCriteria-\\d+\\.locationClassID",false));
	}
	
	@Override
	public void setPricingSearchInfo(PricingInfo pricing) {
//		if(this.isShowCurrentRecordsOnlyChecked()){
//			this.uncheckShowCurrentRecordsOnly();
//			ajax.waitLoading();
//		}
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
	
	public String getFirstPricingID() {
		List<String> ids = this.getColumnValue("ID");
		if (ids == null || ids.size() < 1) {
			logger.warn("There is no any pricing info");
			return "";
		} else {
			return ids.get(1);
		}
	}
	
	public List<String> getStatusColValue(){
		return this.getColumnValue(STATUS_COL);
	}
	
	public List<String> getLocClassColValue(){
		return this.getColumnValue(LOCATION_CLASS_COL);
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
			info.id = table.getCellValue(i, table.findColumn(0, ID_COL));
			info.status = table.getCellValue(i, table.findColumn(0, STATUS_COL));
			info.locationClass = table.getCellValue(i, table.findColumn(0, LOCATION_CLASS_COL));
			info.licYearFrom = table.getCellValue(i, table.findColumn(0, LICENSE_YEAR_FROM_COL));
			info.licYearTo = table.getCellValue(i, table.findColumn(0, LICENSE_YEAR_TO_COL));
			info.effectFrom = table.getCellValue(i, table.findColumn(0, EFFECTIVE_FROM_DATE_COL));
			info.effectTo = table.getCellValue(i, table.findColumn(0, EFFECTIVE_TO_DATE_COL));
			info.stateProvinces = table.getCellValue(i, table.findColumn(0, STATE_PROVINCES_COL));
			info.rateType = table.getCellValue(i, table.findColumn(0, RATE_TYPE_COL));
			info.stateFee = table.getCellValue(i, table.findColumn(0, STATE_FEE_COL));
			info.vendorFee = table.getCellValue(i, table.findColumn(0, VENDOR_FEE_COL));
			info.transFee = table.getCellValue(i, table.findColumn(0, TRANSACTION_FEE_COL));
			info.holdingFee = table.getCellValue(i, table.findColumn(0, HOLDING_FEE_COL));
			info.customerPrice = table.getCellValue(i, table.findColumn(0, CUSTOMER_PRICE_COL));
			info.stateTransFee = table.getCellValue(i, table.findColumn(0, STATE_TRANS_FEE_COL));
			info.stateVendorFee = table.getCellValue(i, table.findColumn(0, STATE_VENDOR_FEE_COL));
						
			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	private IHtmlObject[] getPricingTables() {
		IHtmlObject[] objs = browser.getHtmlObject(this.pricingTableGrid());
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find privilege lottery pricing list table object.");
		}
		
		return objs;
	}
	
	public List<String> getPricingTableLabels() {
		IHtmlObject[] objs = this.getPricingTables();
		List<String> labels = ((IHtmlTable)objs[0]).getRowValues(0);
		Browser.unregister(objs);
		return labels;
	}
}
