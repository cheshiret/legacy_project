package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 8, 2012
 */
public class OrmsPOSProductSetupPage extends OrmsPOSCommonPage {
	/* For Column Names*/
	private final String PROD_ID_COL = "Product ID";
	private final String PROD_NAME_COL = "Product Name";
	private final String ASSIGNED_COL = "Assigned";
	private final String PROD_DES_COL = "Product Description";
	private final String PROD_GROUP_COL = "Product Group";
	private final String UNIT_PRICE_COL = "Unit Price";
	private final String VAR_PRICE_ALLOWED_COL = "Variable Price Allowed";
	private final String PAR_QUTY_ALLOWED_COL = "Partial Quantity Allowed";
	private final String EFF_START_DATE = "Effective Sales Start Date";
	private final String EFF_END_DATE = "Effective Sales End Date";
	public final String EFF_START_DATE_TYPE = "Effective Start Date";
	public final String EFF_END_DATE_TYPE = "Effective End Date";
	
	private static OrmsPOSProductSetupPage _instance = null;

	private OrmsPOSProductSetupPage() {
	}

	public static OrmsPOSProductSetupPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsPOSProductSetupPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id","POSProductSetupView");
	}

	/**
	 * click add pos product.
	 */
	public void clickAddPosProduct() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
	}
	
	/** The following methods are for search criteria setup*/
	public void selectAssignmentStatus(String assignStatus){
		if(null == assignStatus || StringUtil.isEmpty(assignStatus)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", assignStatus);
		}
	}
	
	public void setProductName(String name){
		browser.setTextField(".id", "POSProductSetupSearchCriteria.productName", name);
	}
	
	public void setProductGroup(String group){
		if(!StringUtil.isEmpty(group)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productGroupID", group);
		}
	}
	
	public void selectProductClass(String productClass){
		if(null == productClass || StringUtil.isEmpty(productClass)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", productClass);
		}
	}
	
	public void selectProductSubClass(String subClass){
		if(null == subClass || StringUtil.isEmpty(subClass)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", subClass);
		}
	}
	
	public void selectInventoryType(String type){
		if(null == type || StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", type);
		}
	}
	
	public void setQtyOnHand(String qty){
		browser.setTextField(".id", "POSProductSetupSearchCriteria.qtyOnHand", qty);
	}
	
	public void selectDateSearchType(String dateType){
		if(StringUtil.isEmpty(dateType)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.dateSearchType", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.dateSearchType", dateType);
		}	
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", "POSProductSetupSearchCriteria.startDate_ForDisplay", fromDate);
	}
	
	public void setToDate(String toDate){
		browser.setTextField(".id", "POSProductSetupSearchCriteria.endDate_ForDisplay", toDate);
	}
	
	public void selectVariablePriceCheckBox(){
		browser.selectCheckBox(".id", "POSProductSetupSearchCriteria.variablePrice");
	}
	
	public void unSelectVariablePriceCheckBox(){
		browser.unSelectCheckBox(".id", "POSProductSetupSearchCriteria.variablePrice");
	}
	
	public void selectPartialQuantityAllowedCheckBox(){
		browser.selectCheckBox(".id", "POSProductSetupSearchCriteria.partialQuantity");
	}
	
	public void unSelectPartialQuantityAllowedCheckBox(){
		browser.unSelectCheckBox(".id", "POSProductSetupSearchCriteria.partialQuantity");
	}
	
	public void clickGo(){
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".id", "goAnchor");
		properties[2] = new Property(".text", "Search");
		browser.clickGuiObject(properties);
	}
	
	/**
	 * Search pos product by name
	 * @param name
	 */
	public void searchPOSByName(String name){
		selectAssignmentStatus("");
		setProductName(name);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public IHtmlObject[] getPOSProductSetupTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		if(objs.length<1){
			throw new ErrorOnPageException("Could not get POS product setup list on page.");
		}
		return objs;
	}
	
	public void clickFirstProductID(){
		//javascript:invokeActionWorker( "POSProductSetupDetail.do", "POSSetupWorker", "fieldPosProductDetail", ["137337682"], "transaction", null )
		RegularExpression rex = new RegularExpression("javascript:invokeActionWorker\\(.*\"POSProductSetupDetail.do\",.*\"POSSetupWorker\",.*\"fieldPosProductDetail\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href", rex);
	}
	
	private int getPOSIndex(String posID) {
		IHtmlObject objs [] = browser.getCheckBox(".id", new RegularExpression("\\d+_ck", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find any POS product checkbox object.");
		}
		
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(objs[i].getProperty(".id").startsWith(posID)) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		return index;
	}
	
	private Property[] addPrice() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add Price");
	}
	
	public void clickAddPrice() {
		clickAddPrice(0);
	}
	
	public void clickAddPrice(int index) {
		browser.clickGuiObject(addPrice(), index);
	}
	
	public boolean isUnitPriceDisplayedAsTextField(String id) {
		return browser.checkHtmlObjectExists(".id", id + "_up");
	}
	
	public void setProductUnitPrice(String id, String price){
//		browser.setTextField(".id", id+"_up", price);
		
		//Quentin[20140704] system display 'Add Price' link instead of text field if POS serialization type is 'Boat Launch Permit' 
		Property textFieldPriceProp[] = Property.toPropertyArray(".id", id + "_up");
		if(browser.checkHtmlObjectExists(textFieldPriceProp)) {
			browser.setTextField(textFieldPriceProp, price);
		} else {
			OrmsPOSAddUnitPriceWidget addPriceWidget = OrmsPOSAddUnitPriceWidget.getInstance();
			
			int index = getPOSIndex(id);
			clickAddPrice(index);
			ajax.waitLoading();
			addPriceWidget.waitLoading();
			addPriceWidget.setUnitPrices(price, price);
			addPriceWidget.clickOK();
			ajax.waitLoading();
		}
	}
	
	public void selectVariablePriceAllowed(String id, String value){
		browser.selectDropdownList(".id", id+"_vq", value);
	}
	
	public boolean isPartialQuantityAllowedDropdownListEnabled(String id) {
		return browser.checkHtmlObjectEnabled(".id", id+"_pq");
	}
	
	public void selectPartialQuantityAllowed(String id, String value){
		browser.selectDropdownList(".id", id+"_pq", value);
	}
	
	public void setSalesStartDate(String id, String value){
		browser.setTextField(".id", id+"_effstdate_ForDisplay", value);
	}
	
	public void setSalesEndDate(String id, String value){
		browser.setTextField(".id", id+"_effenddate_ForDisplay", value);
	}
	
	/**
	 * Set up pos details info based on search result
	 * @param pos
	 */
	public void setupPOSDetails(POSInfo pos){
		if(!StringUtil.isEmpty(pos.unitPrice)&&!pos.overrideUnitPrice.equals("No"))
			setProductUnitPrice(pos.productID, pos.unitPrice);
		if(!StringUtil.isEmpty(pos.variablePriceAllowed))
			selectVariablePriceAllowed(pos.productID, pos.variablePriceAllowed);
		if(!StringUtil.isEmpty(pos.partialQuantityAllowed) && isPartialQuantityAllowedDropdownListEnabled(pos.productID))
			selectPartialQuantityAllowed(pos.productID, pos.partialQuantityAllowed);
		if(!StringUtil.isEmpty(pos.effectiveSalesStartDate))
			setSalesStartDate(pos.productID, pos.effectiveSalesStartDate);
		if(!StringUtil.isEmpty(pos.effectiveSalesEndDate))
			setSalesEndDate(pos.productID, pos.effectiveSalesEndDate);
	}
	
	public void selectProductID(String id){
		browser.selectCheckBox(".id", id+"_ck", true);	
	}
	
	public void clickAssignSelectedPOSProducts(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
	}
	
	public void clickUnassignSelectedPOSProducts(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
	}
	
	public void assignPOSProduct(String id){
		selectProductID(id);
		clickAssignSelectedPOSProducts();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Search By POS Name, set up POS details info and assign POS product
	 * @param pos
	 */
	public void setupPOSDetailsBySearchPosName(POSInfo pos){
		logger.info("Go to pos product details by pos name:"+pos.product);
		searchPOSByName(pos.product);
		setupPOSDetails(pos);
		assignPOSProduct(pos.productID);
	}
	
	/**
	 * Get pos QtyOnHand by pos name
	 * @param name
	 * @return
	 */
	public String getQtyOnHandByName(String name){
		IHtmlObject[] objs = getPOSProductSetupTable();
		IHtmlTable posTable  = (IHtmlTable)objs[0];
		int row = posTable.rowCount();
		if(row < 1){
			throw new ErrorOnPageException("There is no pos info by name "+name);
		}
		int col = posTable.findColumn(0, "Qty On Hand");
		String qtyOnHand = posTable.getCellValue(1, col);
		Browser.unregister(objs);
		return qtyOnHand;
	}
	
	/**
	 * Verify POS product qty on hand in list
	 * @param product
	 * @param qtyOnHand
	 */
	public void verifyQtyOnHand(String product, String qtyOnHand){
		String qtyOnHandUI = getQtyOnHandByName(product);
		if(StringUtil.compareNumStrings(qtyOnHand, qtyOnHandUI)!=0){
			throw new ErrorOnPageException("POS Qty On Hand display un-correctly on Pos Product Setup page.",
					qtyOnHand,
					qtyOnHandUI);
		}
		logger.info("POS Qty On Hand display correctly on Pos Product Setup page.");
	}
	
	/**
	 * Verify POS product unit price in list
	 * @param product
	 * @param unitPrice
	 */
	public void verifyUnitPrice(String product, String unitPrice){
		String unitPriceUI = getPOSUnitPrice(product);
		if(StringUtil.compareNumStrings(unitPrice, unitPriceUI)!=0){
			throw new ErrorOnPageException("POS Unit Price display un-correctly on Pos Product Setup page.",
					unitPrice,
					unitPriceUI);
		}
		logger.info("POS Unit Price display correctly on Pos Product Setup page.");
	}
	
	/* Get POS Product Attribute value by the product name and the column name */
	private String getPOSProdAttribute(String name, String colName) {
		String value = null;
	
		IHtmlObject[] objs = this.getPOSProductSetupTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> firstRowValues = table.getRowValues(0);
		int nameCol = firstRowValues.indexOf(PROD_NAME_COL); 
		int row = table.findRow(nameCol, name);
		
		if (row > 0) {
			List<String> values = table.getRowValues(row);
			value = values.get(firstRowValues.indexOf(colName));
		}
		Browser.unregister(objs);
		logger.info("The value of " + colName + " for the POS " + name + " is " + value);
		return value;
	}
	
	/** Get POS product ID by name */
	public String getPOSProductID(String posName) {
		return this.getPOSProdAttribute(posName, PROD_ID_COL);
	}
	
	/** Get POS UnitPrice by pos name */
	public String getPOSUnitPrice(String posName) {
		return this.getPOSProdAttribute(posName, UNIT_PRICE_COL);
	}
	
	/** Get POS Assigned Status */
	public String getPOSAssignedStatus(String posName) {
		return this.getPOSProdAttribute(posName, ASSIGNED_COL);
	}
	
	/** Get POS Product Description */
	public String getPOSProductDescription(String posName) {
		return this.getPOSProdAttribute(posName, PROD_DES_COL);
	}
	
	/** Get POS Product Group */
	public String getPOSProductGroup(String posName) {
		return this.getPOSProdAttribute(posName, PROD_GROUP_COL);
	}
	
	/** Get POS Variable Price Allowed value*/
	public String getPOSVariPriceAllowedValue(String posName) {
		return this.getPOSProdAttribute(posName, VAR_PRICE_ALLOWED_COL);
	}
	
	/** Get POS Partial Quantity Allowed value */
	public String getPOSPartQuantityAllowedValue(String posName) {
		return this.getPOSProdAttribute(posName, PAR_QUTY_ALLOWED_COL);
	}
	
	/* Unassign the POS product with the product id */
	public void unassignPOSProduct(String id) {
		logger.info("unassign POS Product with id=" + id + " from field manager...");
		this.selectProductID(id);
		this.clickUnassignSelectedPOSProducts();
		ajax.waitLoading();
	}
	
	public void clearSearchCriteria() {
		logger.info("Clear All Search Criteria...");
		this.selectAssignmentStatus("");
		this.setProductName("");
		this.setProductGroup("");
		this.selectDateSearchType("");
		this.setFromDate("");
		this.setToDate("");
		this.unSelectPartialQuantityAllowedCheckBox();
		this.unSelectVariablePriceCheckBox();
	}
	
	/**
	 * Setup POS Product Search Criteria
	 * @param pos
	 */
	public void setupPOSProductSearchCriteria(POSInfo pos) {
		this.clearSearchCriteria();
		logger.info("Setup Search Criteria...");
		if (!StringUtil.isEmpty(pos.searchByAssignStatus)) {
			this.selectAssignmentStatus(pos.searchByAssignStatus);
		}
		if (!StringUtil.isEmpty(pos.product)) {
			this.setProductName(pos.product);
		}
		if (!StringUtil.isEmpty(pos.productGroup)) {
			this.setProductGroup(pos.productGroup);
		}
		if (pos.effectiveDateType != null) {
			this.selectDateSearchType(pos.effectiveDateType);
		}
		if (!StringUtil.isEmpty(pos.effectiveStartDate)) {
			this.setFromDate(pos.effectiveStartDate);
		}
		if (!StringUtil.isEmpty(pos.effectiveEndDate)) {
			this.setToDate(pos.effectiveEndDate);
		}
		if (pos.isPartialQuantityAllowed) {
			this.selectPartialQuantityAllowedCheckBox();
		} else {
			this.unSelectPartialQuantityAllowedCheckBox();
		}
		if (pos.isVariablePrice) {
			this.selectVariablePriceCheckBox();
		} else {
			this.unSelectVariablePriceCheckBox();
		}		
	}
	
	/**
	 * Search the POS info
	 * @param pos
	 */
	public void searchPOSProduct(POSInfo pos) {
		logger.info("Search the POS info for " + pos.searchByAssignStatus + " " + pos.product);
		this.setupPOSProductSearchCriteria(pos);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Search unassigned product
	 * @param pos
	 */
	public void searchUnassignedPOSProduct(POSInfo pos) {
		pos.searchByAssignStatus = "Unassigned Products";
		this.searchPOSProduct(pos);
	}
	
	/**
	 * Search assigned product
	 * @param pos
	 */
	public void searchAssignedPOSProduct(POSInfo pos) {
		pos.searchByAssignStatus = "Assigned Products";
		this.searchPOSProduct(pos);
	}
	
	/** Check the POS product effective start date text field exists */
	public boolean isPOSProdEffStartDateTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_effstdate_ForDisplay");
	}
	
	/** Verify the POS product effective start date text field exists */
	public void verifyPOSProdEffStartDateTextFieldExist(String id, boolean exp) {
		boolean act = this.isPOSProdEffStartDateTextFieldExist(id);
		this.compareResults(EFF_START_DATE + " textbox existence", exp, act);
	}
	
	/** Get the text of the effective start date text field */
	public String getPOSProdEffStartDateTextFieldValue(String id) {
		return browser.getTextFieldValue(".id", id+"_effstdate_ForDisplay");
	}

	/** Verify the text of the effective start date text field */
	public void verifyPOSProdEffStartDateTextFieldValue(String id, String expDate) {
		String actDate = this.getPOSProdEffStartDateTextFieldValue(id);
		this.compareDateWithFormat(EFF_START_DATE, expDate, actDate, false);
	}
	
	/** Get the text of the effective start date */
	public String getPOSProdEffectiveStartDate(String name) {
		return this.getPOSProdAttribute(name, EFF_START_DATE);
	}
	
	/** Verify the text of the effective start date */
	public void verifyPOSProdEffectiveStartDate(String name, String expDate) {
		String actDate = this.getPOSProdEffectiveStartDate(name);
		this.compareDateWithFormat(EFF_START_DATE, expDate, actDate, true);
	}
	
	/** Check the POS product effective end date text field exists */
	public boolean isPOSProdEffEndDateTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_effenddate_ForDisplay");
	}
	
	/** Verify the POS product effective end date text field exists */
	public void verifyPOSProdEffEndDateTexFieldExist(String id, boolean exp) {
		boolean act = this.isPOSProdEffEndDateTextFieldExist(id);
		this.compareResults(EFF_END_DATE + " textbox existence", exp, act);
	}
	
	/** Get the text of the effective end date text field */
	public String getPOSProdEffEndDateTextFieldValue(String id) {
		return browser.getTextFieldValue(".id", id+"_effenddate_ForDisplay");
	}

	/** Verify the text of the effective end date text field */
	public void verifyPOSProdEffEndDateTextFieldValue(String id, String expDate) {
		String actDate = this.getPOSProdEffEndDateTextFieldValue(id);
		this.compareDateWithFormat(EFF_END_DATE, expDate, actDate, false);
	}
	
	/** Get the text of the effective end date field*/
	public String getPOSProdEffectiveEndDate(String name) {
		return this.getPOSProdAttribute(name, EFF_END_DATE);
	}
	
	/** Verify the text of the effective end date */
	public void verifyPOSProdEffectiveEndDate(String name, String expDate) {
		String actDate = this.getPOSProdEffectiveEndDate(name);
		this.compareDateWithFormat(EFF_END_DATE, expDate, actDate, true);
	}
	
	/** Check unit price text field exists */
	public boolean isUnitPriceTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_up");
	}

	/** Verify unit price text field exists */
	public void verifyUnitPriceTextFieldExist(String id, boolean exp) {
		boolean act = this.isUnitPriceTextFieldExist(id);
		this.compareResults(UNIT_PRICE_COL + " textbox existence", exp, act);
	}
	
	/** Get the value of the unit price text field */
	public String getUnitPriceTextFieldText(String id) {
		return browser.getTextFieldValue(".id", id+"_up").replace("$", "");
	}
	
	/** Get the value of the variable price allowed dropdown list*/
	public String getVariablePriceAllowedValue(String id) {
		return browser.getDropdownListValue(".id", id+"_vq");
	}
	
	/** Get the value of the partial quantity allowed dropdown list*/
	public String getPartialQuantityAllowed(String id) {
		return browser.getDropdownListValue(".id", id+"_pq");
	}
	
	/* Get all POS info by product name */
	public POSInfo getPOSProdInfo(String name) {
		POSInfo pos = new POSInfo();
		IHtmlObject[] objs = this.getPOSProductSetupTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, PROD_NAME_COL);
		int row = table.findRow(col, name);
		table.getRowValues(row);
		if (row > 0) {
			List<String> values = table.getRowValues(row);
			List<String> firstRowValues = table.getRowValues(0);
			pos.product = name;
			pos.productID = values.get(firstRowValues.indexOf(PROD_ID_COL));
			pos.assignStatus = values.get(firstRowValues.indexOf(ASSIGNED_COL));
			pos.productDescription = values.get(firstRowValues.indexOf(PROD_DES_COL));
			pos.productGroup = values.get(firstRowValues.indexOf(PROD_GROUP_COL));
			if (this.isUnitPriceTextFieldExist(pos.productID)) {
				pos.unitPrice = this.getUnitPriceTextFieldText(pos.productID);
			} else {
				pos.unitPrice = values.get(firstRowValues.indexOf(UNIT_PRICE_COL)).replace("$", "");
			}
			
			if (pos.assignStatus.equalsIgnoreCase(OrmsConstants.YES_STATUS)) {
				pos.variablePriceAllowed = values.get(firstRowValues.indexOf(VAR_PRICE_ALLOWED_COL));
				pos.partialQuantityAllowed = values.get(firstRowValues.indexOf(PAR_QUTY_ALLOWED_COL));		
			} else if (pos.assignStatus.equalsIgnoreCase(OrmsConstants.NO_STATUS)) {
				pos.variablePriceAllowed = this.getVariablePriceAllowedValue(pos.productID);
				pos.partialQuantityAllowed = this.getPartialQuantityAllowed(pos.productID);
			}
			if (this.isPOSProdEffStartDateTextFieldExist(pos.productID)) {
				pos.effectiveSalesStartDate = this.getPOSProdEffStartDateTextFieldValue(pos.productID);
			} else {
				pos.effectiveSalesStartDate = values.get(firstRowValues.indexOf(EFF_START_DATE));
			}
			if (this.isPOSProdEffEndDateTextFieldExist(pos.productID)) {
				pos.effectiveSalesEndDate = this.getPOSProdEffEndDateTextFieldValue(pos.productID);
			} else {
				pos.effectiveSalesEndDate = values.get(firstRowValues.indexOf(EFF_END_DATE));	
			}
		}	
		
		Browser.unregister(objs);
		return pos;	
	}
	
	public boolean comparePOSProdInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSProdInfo(pos.product);
		boolean result = true;
		logger.info("Compare two pos product info...");
		result &= MiscFunctions.compareResult(PROD_ID_COL, pos.productID, actPOS.productID);
		result &= MiscFunctions.compareResult(ASSIGNED_COL + " Status", pos.assignStatus, actPOS.assignStatus);
		result &= MiscFunctions.compareResult(PROD_DES_COL, pos.productDescription, actPOS.productDescription);
		result &= MiscFunctions.compareResult(PROD_GROUP_COL, pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult(UNIT_PRICE_COL, pos.unitPrice, actPOS.unitPrice);
		result &= MiscFunctions.compareResult(VAR_PRICE_ALLOWED_COL, pos.variablePriceAllowed, 
					actPOS.variablePriceAllowed);
		result &= MiscFunctions.compareResult(PAR_QUTY_ALLOWED_COL, pos.partialQuantityAllowed, 
					actPOS.partialQuantityAllowed);
		return result;
	}
	
	/* Verify all POS info */
	public void verifyPOSProdInfo(POSInfo pos) {
		boolean result = this.comparePOSProdInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("The POS setup info is not correct! Please check logger error.");
		}
		logger.info("The POS Setup Info is correct in the POS Product Setup page!");
	}
	
	/** Check POS product ID link exists */
	public boolean isPOSProdIDLinkExist(String id) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", id);
		p[2] = new Property(".href", new RegularExpression("^javascript:invokeActionWorker\\( \"POSProductSetupDetail.do\"", false));
		return browser.checkHtmlObjectExists(p);
	}
	/** Verify POS product ID link exists */
	public void verifyPOSProdIDLinkExist(String id, boolean isLink) {
		boolean actRes = this.isPOSProdIDLinkExist(id);
		this.compareResults("POS Product ID Link existence", isLink, actRes);
	}
	
	private void compareResults(String meg, Object exp, Object act) {
		if (!MiscFunctions.compareResult(meg, exp, act)) {
			throw new ErrorOnPageException("---ERROR! Please check logger error!");
		}
	}
	
	private void compareDateWithFormat(String meg, String expDate, String actDate, boolean isAssigned) {
		if (isAssigned) { // change the exp date format to "MM/dd/yyyy"
			expDate = DateFunctions.formatDate(expDate, "MM/dd/yyyy");
		} else { // change the exp date format to "EEE MMM d yyyy"
			expDate = DateFunctions.formatDate(expDate, "EEE MMM d yyyy");
		}
		if (!expDate.equalsIgnoreCase(actDate)) {
			throw new ErrorOnPageException(meg + " is wrong!", expDate, actDate);
		} else {
			logger.info(meg + " is correct!");
		}
	}

	public void clickProductID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	/**
	 * Verify POS product item info in the list, including the details, and the display of the text fields and 
	 * the product ID link.
	 * @param pos
	 * @param isProdIDLinkShown
	 * @param isTextFieldShown
	 */
	public void verifyPOSProductItemInList(POSInfo pos, boolean isProdIDLinkShown, boolean isTextFieldShown) {
		this.verifyPOSProdInfo(pos);
		this.verifyUnitPriceTextFieldExist(pos.productID, isTextFieldShown);
		this.verifyPOSProdEffStartDateTextFieldExist(pos.productID, isTextFieldShown);
		this.verifyPOSProdEffEndDateTexFieldExist(pos.productID, isTextFieldShown);
		this.verifyPOSProdIDLinkExist(pos.productID, isProdIDLinkShown);
	}
	
	/**
	 * Verify the unassigned POS item in the list. The product id link should not be shown.
	 * If the text field is shown, verify the effective start date. 
	 * @param pos
	 * @param isTextFieldShown
	 */
	public void verifyUnassigedPOSProductItemInList(POSInfo pos, boolean isTextFieldShown) {
		this.verifyPOSProductItemInList(pos, false, isTextFieldShown);
		if (isTextFieldShown) {
			this.verifyPOSProdEffStartDateTextFieldValue(pos.productID, pos.effectiveSalesStartDate);
		}
	}
	
	/**
	 * Verify the assigned POS item in the list. The product id link should be shown. 
	 * And verify the effective sales start and end dates with given format.
	 * @param pos
	 */
	public void verifyAssignedPOSProductItemInList(POSInfo pos) {
		this.verifyPOSProductItemInList(pos, true, false);
		if (!StringUtil.isEmpty(pos.effectiveSalesStartDate)) {
			this.verifyPOSProdEffectiveStartDate(pos.product, pos.effectiveSalesStartDate);
		}
		if (!StringUtil.isEmpty(pos.effectiveSalesEndDate)) {
			this.verifyPOSProdEffectiveEndDate(pos.product, pos.effectiveSalesEndDate);
		}
	}
	
	/* Check if POS product with the given product id exist in the list */
	public boolean isProductExistInList(String name) {
		String prodID = this.getPOSProductID(name);
		return !StringUtil.isEmpty(prodID);
	}
	
	/* Verify the existence of the POS product with the given product name in the list */
	public void verifyProductExistInList(String name, boolean expResult) {
		boolean actResult = this.isProductExistInList(name);
		this.compareResults("POS Product with name=" + name + " existence", expResult, actResult);
	}
	
	public boolean isProductUnitPriceReadOnly(String id){
		IHtmlObject[]  objs = browser.getTextField("id", id+"_up");
		if(objs.length<1){
			throw new ObjectNotFoundException("The object with id: ('"+ id+"_up') can't be found.");
		}
		IText text  = (IText)objs[0];
		Boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}
	
	public String getDefaultUnitPrice(String id){
		IHtmlObject[]  objs = browser.getTextField("id", id+"_up");
		if(objs.length<1){
			throw new ObjectNotFoundException("The object with id: ('"+ id+"_up') can't be found.");
		}

		String defaultPrice = objs[0].getAttributeValue("value");
		Browser.unregister(objs);
		return defaultPrice;
	}
	
	private boolean isExists(String buttonName){
		IHtmlObject top[] = null;
		top = browser.getTableTestObject(".className", "pagingBar");
		boolean exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(buttonName, false),top[0]);
		Browser.unregister(top);
		return exists;
	}
	
	private boolean isNextExist(){
		return this.isExists("Next");
	}
	
	private boolean isFirstExist(){
		return this.isExists("First");
	}
		
	public boolean clickFirst(){
		boolean result = isFirstExist();
		if(result){
			browser.clickGuiObject(".class", "Html.A", ".text", "First");
			ajax.waitLoading();
			this.waitLoading();
		}
		return result;
	}
	
	public boolean clickNext(){
		boolean result = isNextExist();
		if(result){
			browser.clickGuiObject(".class", "Html.A", ".text", "Next");
			ajax.waitLoading();
			this.waitLoading();
		}
		return result;
	}
	
	
	
	public List<String> getColumnValueByNam(String columnName){
		logger.info("Get column value by column name "+columnName);
		List<String> columnList = new ArrayList<String>();

		columnList = this.getColumnValueByNameInPage(columnName);
		int i=2;
		while(this.isNextExist()){
			logger.info(i);
			i++;
			this.clickNext();
			browser.waitExists();
			columnList.addAll(this.getColumnValueByNameInPage(columnName));
		}		
		this.clickFirst();
		browser.waitExists();
		return columnList;
	}
	
	private List<String> getColumnValueByNameInPage(String columnName){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can' find POS Product table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int column = table.findColumn(0, columnName);
		List<String> tempList = table.getColumnValues(column);
		tempList.remove(0);
		return tempList;
	}
}
