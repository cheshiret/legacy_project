/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
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
 * @Description: This page could be used as 'Call Center POS Product Setup' page
 *  and also 'Web POS Product Setup' page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jun 19, 2012
 */
public class FinMgrPosProductAssignmentPage extends OrmsPage {

	/* For Column Names*/
	private final String PROD_ID_COL = "Product ID";
	private final String ASSIGNED_COL = "Assigned";
	private final String PROD_NAME_COL = "Product Name";
	private final String PROD_DES_COL = "Product Description";
	private final String CONTRACT_AGENCY_COL = "Contract/Agency";
	private final String PROD_GROUP_COL = "Product Group";
	private final String UNIT_PRICE_COL = "Unit Price";
	private final String VAR_PRICE_ALLOWED_COL = "Variable Price Allowed";
	private final String PAR_QUTY_ALLOWED_COL = "Partial Quantity Allowed";
	private final String EFF_START_DATE = "Effective Sales Start Date";
	private final String EFF_END_DATE = "Effective Sales End Date";
	
	public final String EFF_START_DATE_TYPE = "Effective Start Date";
	public final String EFF_END_DATE_TYPE = "Effective End Date";
	
	static private FinMgrPosProductAssignmentPage _instance = null;
	
	static public FinMgrPosProductAssignmentPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrPosProductAssignmentPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "POSProductSetupView");
	}
	
	public void clickCallCenterPosProductSetup(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Call Center POS Product Setup");
	}
	/**
	 * check call center POS product link exist or not.
	 * @return
	 */
	public boolean checkCallCenterPosProductLink(){
		boolean isExist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Call Center POS Product Setup");
		if(objs.length>=1){
			isExist= true;
		}
		return isExist;
	}
	
	public void clickWebPosProductSetup(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Web POS Product Setup");
	}
	/**
	 * check web POS product link exist or not.
	 * @return
	 */
	public boolean checkWebPosProdutLink(){
		boolean isExist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Web POS Product Setup");
		if(objs.length>=1){
			isExist = true;
		}
		Browser.unregister(objs);
		return isExist;
	}
	/**
	 * check assign select POS Products 
	 * @return
	 */
	public boolean checkAssignSelectPosProducts(){
		boolean isExist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Assign Selected POS Products");
		if(objs.length>=1){
			isExist = true;
		}
		Browser.unregister(objs);
		return isExist;
	}
	
	public void clickAssignSelectedPosProducts(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
	}
	/**
	 * check unassign selected POS product.
	 * @return
	 */
	public boolean checkUnassignSelectedPosProducts(){
		boolean isExist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Assign Selected POS Products");
		if(objs.length>=1){
			isExist = true;
		}
		Browser.unregister(objs);
		return isExist;
	}
	public void clickUnassignSelectedPosProducts(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
	}
	
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

	public void setBarcode(String code){
		browser.setTextField(".id", "POSProductSetupSearchCriteria.productUPC", code);
	}
	
	public void setProductGroup(String group){
		if(StringUtil.isEmpty(group)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productGroupID", 0);
		} else {
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productGroupID", group);
		}
	}
	
	public void selectContractAgency(String contAgen){
		if(StringUtil.isEmpty(contAgen)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.contractAgencyID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.contractAgencyID", contAgen);
		}
	}
	
	
	public void selectDateSearchType(String dateType){
		if(null == dateType || StringUtil.isEmpty(dateType)){
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
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
//	public void clickAssignSelectedPOSProducts(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
//	}
//	
//	public void clickUnassignSelectedPOSProducts(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
//	}
	
	public void selectProductID(String id){
		browser.selectCheckBox(".id", id+"_ck", true);	
	}
	/**
	 * unselect product id.
	 * @param id
	 */
	public void unselectProductId(String id){
		browser.unSelectCheckBox(".id", id+"_ck");
	}
	

	public void clickProductID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	/**
	 * Search pos product by name
	 * @param name
	 */
	public void searchPOSByName(String name){
		logger.info("Search pos by name "+name);
		selectAssignmentStatus("");
		setProductName(name);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isPOSAssigned(String name){
		this.searchPOSByName(name);
		String status=this.getColumnValueByNam("Assigned").get(0);
		if("No".equals(status)){
			return false;
		}else{
			return true;
		}
		
	}
	
	public void setProductUnitPrice(String id, String price){
		browser.setTextField("id", id+"_up", price);
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
	
	
	
	public void selectVariablePriceAllowed(String id, String value){
		browser.selectDropdownList(".id", id+"_vq", value);
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
		if(!StringUtil.isEmpty(pos.unitPrice))
			setProductUnitPrice(pos.productID, pos.unitPrice);
		if(!StringUtil.isEmpty(pos.variablePriceAllowed))
			selectVariablePriceAllowed(pos.productID, pos.variablePriceAllowed);
		if(!StringUtil.isEmpty(pos.partialQuantityAllowed))
			selectPartialQuantityAllowed(pos.productID, pos.partialQuantityAllowed);
		if(!StringUtil.isEmpty(pos.effectiveStartDate))
			setSalesStartDate(pos.productID, pos.effectiveStartDate);
		if(!StringUtil.isEmpty(pos.effectiveEndDate))
			setSalesEndDate(pos.productID, pos.effectiveEndDate);
		if(!StringUtil.isEmpty(pos.effectiveSalesStartDate))
			setSalesStartDate(pos.productID, pos.effectiveSalesStartDate);
		if(!StringUtil.isEmpty(pos.effectiveSalesEndDate))
			setSalesEndDate(pos.productID, pos.effectiveSalesEndDate);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void assignPOSProduct(String id){
		logger.info("Assign POS Product with id=" + id);
		
		selectProductID(id);
		clickAssignSelectedPosProducts();
		ajax.waitLoading();
		this.waitLoading();
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean getPOSProductAssignStatus(String product){
		searchPOSByName(product);	
		return this.getPOSProdAssignStatus(product);
	}
	
	public void clearAllSearchCriteria() {
		logger.info("Clear All Search Criteria...");
		this.selectAssignmentStatus("");
		this.setProductName("");
		this.setProductGroup("");
		this.selectContractAgency("");
		this.selectDateSearchType("");
		this.setFromDate("");
		this.setToDate("");
		this.unSelectPartialQuantityAllowedCheckBox();
		this.unSelectVariablePriceCheckBox();
		if (this.isProductClassListExist()) {
			this.selectProductClass("");
		}
		if (this.isProductSubClassListExist()) {
			this.selectProductSubClass("");
		}
		if (this.isInventoryTypeListEixst()) {
			this.selectInventoryType("");
		}
	}
	
	public void searchPOSByProductName(String name, boolean isAssigned) {
		this.clearAllSearchCriteria();
		logger.info("Setup Search Criteria...");
		if(isAssigned)
		{
			this.selectAssignmentStatus("Assigned Products");
		}else{
			this.selectAssignmentStatus("Unassigned Products");
		}
		this.setProductName(name);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	
	/**
	 * 
	 * @param pos
	 * @author Lesley Wang
	 * @date Jul 19, 2012
	 */
	public void setupPOSProductSearchCriteria(POSInfo pos) {
		this.clearAllSearchCriteria();
		logger.info("Setup Search Criteria...");
		this.selectAssignmentStatus(pos.searchByAssignStatus);
		
		if (pos.barcodeList.size()>0) {
			this.setBarcode(pos.barcodeList.get(0).barCode);
		}
		
		if (!StringUtil.isEmpty(pos.product)) {
			this.setProductName(pos.product);
		}
		if (!StringUtil.isEmpty(pos.productGroup)) {
			this.setProductGroup(pos.productGroup);
		}
		if (pos.availableLocation != null) {
			this.selectContractAgency(pos.availableLocation);
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
		// added by Nicole, PCR 2956
		// --start
		if(!StringUtil.isEmpty(pos.productClass)){
			this.selectProductClass(pos.productClass);
		}
		
		if (!StringUtil.isEmpty(pos.productSubClass)) {
			this.selectProductSubClass(pos.productSubClass);
		}

		if (!StringUtil.isEmpty(pos.inventoryType)) {
			this.selectInventoryType(pos.inventoryType);
		}
		
		if (pos.isShowProductPackagesOnly) {
			this.selectShowProductPackagesOnlyCheckBox();
		} else {
			this.unSelectShowProductPackagesOnlyCheckBox();
		}
		
		// --end
	}
	
	/**
	 * 
	 * @param pos
	 * @author Lesley Wang
	 * @date Jul 19, 2012
	 */
	public void searchPOSProduct(POSInfo pos) {
		logger.info("Search the POS info for " + pos.searchByAssignStatus + " " + pos.product);
		this.setupPOSProductSearchCriteria(pos);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * 
	 * @param pos
	 * @author Lesley Wang
	 * @date Jul 19, 2012
	 */
	public void searchUnassignedPOSProduct(POSInfo pos) {
		pos.searchByAssignStatus = "Unassigned Products";
		this.searchPOSProduct(pos);
	}
	
	/**
	 * 
	 * @param pos
	 * @author Lesley Wang
	 * @date Jul 19, 2012
	 */
	public void searchAssignedPOSProduct(POSInfo pos) {
		pos.searchByAssignStatus = "Assigned Products";
		this.searchPOSProduct(pos);
	}
	
	/* Get POS Product Info by the product name and the column name */
	private String getPOSProdInfo(String name, String colName) {
		String value = null;
		PagingComponent turnPageComponent = new PagingComponent();
		int i=1;
		do{
			if(i>1){
				turnPageComponent.clickNext();
				browser.waitExists();
			}
			i++;
			IHtmlObject[] objs = this.getPOSProductSetupTable();
			IHtmlTable table = (IHtmlTable)objs[0];
			List<String> firstRowValue = table.getRowValues(0);
			int col = firstRowValue.indexOf(PROD_NAME_COL); 
			int row = table.findRow(col, name);
			if (row > 0) {
				List<String> values = table.getRowValues(row);
				value = values.get(firstRowValue.indexOf(colName));
				Browser.unregister(objs);
				logger.info("The value of " + colName + " for the POS " + name + " is " + value);
				break;
			}
		}while(turnPageComponent.nextExists(true));
		
		if(turnPageComponent.firstExists()){
			turnPageComponent.clickFirst();
			browser.waitExists();
		}
		
		return value;		
	}
	
	/* Get all POS info by product name */
	private POSInfo getPOSProdInfo(String name) {
		POSInfo pos = new POSInfo();
		
		IHtmlObject[] objs = this.getPOSProductSetupTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> firstRowValue = table.getRowValues(0);
		int col = firstRowValue.indexOf(PROD_NAME_COL); 
		int row = table.findRow(col, name);
		if (row > 0) {
			List<String> values = table.getRowValues(row);
			pos.product = name;
			pos.productID = values.get(firstRowValue.indexOf(PROD_ID_COL));
			pos.assignStatus = values.get(firstRowValue.indexOf(ASSIGNED_COL));
			pos.productDescription = values.get(firstRowValue.indexOf(PROD_DES_COL));
			pos.availableLocation = values.get(firstRowValue.indexOf( CONTRACT_AGENCY_COL));
			pos.productGroup = values.get(firstRowValue.indexOf(PROD_GROUP_COL));
			if (this.isUnitPriceTextFieldExist(pos.productID)) {
				pos.unitPrice = this.getUnitPriceTextFieldText(pos.productID);
				pos.variablePriceAllowed = this.getVariablePriceAllowedValue(pos.productID);
				pos.partialQuantityAllowed = this.getPartialQuantityAllowed(pos.productID);
			} else {
				pos.unitPrice = values.get(firstRowValue.indexOf(UNIT_PRICE_COL)).replace("$", "");
				pos.variablePriceAllowed = values.get(firstRowValue.indexOf(VAR_PRICE_ALLOWED_COL));
				pos.partialQuantityAllowed = values.get(firstRowValue.indexOf(PAR_QUTY_ALLOWED_COL));	
			}				
			if (this.isPOSProdEffStartDateTextFieldExist(pos.productID)) {
				pos.effectiveSalesStartDate = this.getPOSProdEffStartDateTextFieldValue(pos.productID);
			} else {
				pos.effectiveSalesStartDate = values.get(firstRowValue.indexOf(EFF_START_DATE));
			}
			if (this.isPOSProdEffEndDateTextFieldExist(pos.productID)) {
				pos.effectiveSalesEndDate = this.getPOSProdEffEndDateTextFieldValue(pos.productID);
			} else {
				pos.effectiveSalesEndDate = values.get(firstRowValue.indexOf(EFF_END_DATE));	
			}
		}
		Browser.unregister(objs);
		return pos;	
	}
	
	/* Check if POS product with the given product id exist in the list */
	public boolean checkProductExistInListByName(String name) {
//		boolean result = true;
		IHtmlObject[] objs = this.getPOSProductSetupTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean result = table.rowCount() > 1;
//		if (table.rowCount() > 1) {
//			String prodID = this.getPOSProdID(name);
//			result = !StringUtil.isEmpty(prodID);
//		} else {
//			result = false;
//		}
		Browser.unregister(objs);
		return result;
	}
	
	/* Verify the existence of the POS product with the given product name in the list */
	public void verifyProductExistInListByName(String name, boolean expResult) {
		boolean actResult = this.checkProductExistInListByName(name);
		String meg1 = "The pos product with name=" + name; 
		String meg2 = (expResult ? "" : "NOT") + " exist in the list";
		if (actResult != expResult) {
			throw new ErrorOnPageException(meg1 + " should " + meg2);
		} else {
			logger.info(meg1 + " does " + meg2);
		}
	}
	
	/* Get POS product assign status by name */
	public Boolean getPOSProdAssignStatus(String name) {
		String status = this.getPOSProdInfo(name, ASSIGNED_COL);
		Boolean assigned = false;
		if(status.equalsIgnoreCase("Yes")){
			assigned = true;
		}else if(status.equalsIgnoreCase("No")){
			return false;
		}else {
			throw new ErrorOnDataException("Could not parse the assigned status as "+status);
		}
		return assigned;
	}
	
	/* Get POS product ID by name */
	public String getPOSProdID(String name) {
		return this.getPOSProdInfo(name, PROD_ID_COL);
	}
	
	/* Unassign the POS product with the product id */
	public void unassignPOSProduct(String id) {
		this.selectProductID(id);
		this.clickUnassignSelectedPosProducts();
		ajax.waitLoading();
	}
	
	public boolean isPOSProdEffStartDateTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_effstdate_ForDisplay");
	}
	
	public void verifyPOSProdEffStartDateTextFieldExist(String id, boolean isExist) {
		boolean act = this.isPOSProdEffStartDateTextFieldExist(id);
		this.compareResults("The existence of Effective Start Date Text Field", isExist, act);
	}
	
	public String getPOSProdEffStartDateTextFieldValue(String id) {
		return browser.getTextFieldValue(".id", id+"_effstdate_ForDisplay");
	}

	public void verifyPOSProdEffStartDateTextFieldValue(String id, String expDate) {
		String actDate = this.getPOSProdEffStartDateTextFieldValue(id);
		this.compareResults(EFF_START_DATE, expDate, actDate);
	}
	
	public String getPOSProdEffectiveStartDate(String name) {
		return this.getPOSProdInfo(name, EFF_START_DATE);
	}
	
	public void verifyPOSProdEffectiveStartDate(String name, String expDate) {
		String actDate = this.getPOSProdEffectiveStartDate(name);
		this.compareResults(EFF_START_DATE, expDate, actDate);
	}
	
	public boolean isPOSProdEffEndDateTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_effenddate_ForDisplay");
	}
	
	public void verifyPOSProdEffEndDateTexFieldExist(String id, boolean isExist) {
		boolean act = this.isPOSProdEffEndDateTextFieldExist(id);
		this.compareResults("The existence of Effective End Date Text Field", isExist, act);
	}
	
	public String getPOSProdEffEndDateTextFieldValue(String id) {
		return browser.getTextFieldValue(".id", id+"_effenddate_ForDisplay");
	}

	public void verifyPOSProdEffEndDateTextFieldValue(String id, String expDate) {
		String actDate = this.getPOSProdEffEndDateTextFieldValue(id);
		this.compareResults(EFF_END_DATE, expDate, actDate);
	}
	
	public String getPOSProdEffectiveEndDate(String name) {
		return this.getPOSProdInfo(name, EFF_END_DATE);
	}
	
	public void verifyPOSProdEffectiveEndDate(String name, String expDate) {
		String actDate = this.getPOSProdEffectiveEndDate(name);
		this.compareResults(EFF_END_DATE, expDate, actDate);
	}
	
	/* Assign POS product with pos details */
	public void assignPOSProd(POSInfo pos) {
		if (StringUtil.isEmpty(pos.productID)) {
			pos.productID = this.getPOSProdID(pos.product);
		}
		this.setupPOSDetails(pos);
		this.assignPOSProduct(pos.productID);
	}
	
	/* Unassign POS product with pos details */
	public void unassignPOSProd(POSInfo pos) {
		if (StringUtil.isEmpty(pos.productID)) {
			pos.productID = this.getPOSProdID(pos.product);
		}
		this.unassignPOSProduct(pos.productID);
	}
	
	
	/** Compare POS Product Setup Info*/
	public boolean comparePOSProdInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSProdInfo(pos.product);
		boolean result = true;
		result &= MiscFunctions.compareResult(PROD_ID_COL, pos.productID, actPOS.productID);
		result &= MiscFunctions.compareResult(ASSIGNED_COL + " Status", pos.assignStatus, actPOS.assignStatus);
		result &= MiscFunctions.compareResult(PROD_DES_COL, pos.productDescription, actPOS.productDescription);
		result &= MiscFunctions.compareResult(CONTRACT_AGENCY_COL, pos.availableLocation, actPOS.availableLocation);
		result &= MiscFunctions.compareResult(PROD_GROUP_COL, pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult(UNIT_PRICE_COL, pos.unitPrice, actPOS.unitPrice);
		result &= MiscFunctions.compareResult(VAR_PRICE_ALLOWED_COL, pos.variablePriceAllowed, 
				actPOS.variablePriceAllowed);
		result &= MiscFunctions.compareResult(PAR_QUTY_ALLOWED_COL, pos.partialQuantityAllowed, 
				actPOS.partialQuantityAllowed);
		result &= this.compareDateWithFormat(EFF_START_DATE, pos.effectiveSalesStartDate, 
				actPOS.effectiveSalesStartDate, pos.assignStatus);
		result &= this.compareDateWithFormat(EFF_END_DATE, pos.effectiveSalesEndDate, 
				actPOS.effectiveSalesEndDate, pos.assignStatus);	
		return result;
	}
	/* Verify all POS info */
	public void verifyPOSProdInfo(POSInfo pos) {
		boolean result = this.comparePOSProdInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("The POS Setup Info is wrong! Please check logger error!");
		}
		logger.info("The POS Setup Info is correct!");
	}
	
	public boolean isPOSProdIDLinkExist(String id) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", id);
		p[2] = new Property(".href", new RegularExpression("^javascript:invokeActionWorker\\( \"(CallCenter|Web)POSProductSetupDetails.do\"", false));
		return browser.checkHtmlObjectExists(p);
	}
	
	public void verifyPOSProdIDLinkExist(String id, boolean isLink) {
		boolean actRes = this.isPOSProdIDLinkExist(id);
		if (isLink != actRes) {
			throw new ErrorOnPageException("The existence of the POS product link is wrong!", 
					String.valueOf(isLink), String.valueOf(actRes));
		} 
		logger.info("The POS Product ID (" + id + ") link do " + (isLink ? "" : "NOT") + " exist!");
	}
	
	private void compareResults(String meg, Object exp, Object act) {
		if (!MiscFunctions.compareResult(meg, exp, act)) {
			throw new ErrorOnPageException("---ERROR! Please check logger error!");
		}
	}
	
	public IHtmlObject[] getPOSProductSetupTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		if(objs.length<1){
			throw new ErrorOnPageException("Could not get POS product setup list on page.");
		}
		return objs;
	}
	
	/**
	 * Get pos UnitPrice by pos name
	 * @param name
	 * @return
	 */
	public String getUnitPrice(String name){
		return this.getPOSProdInfo(name, UNIT_PRICE_COL);
//		HtmlObject[] objs = getPOSProductSetupTable();
//		ITable posTable  = (ITable)objs[0];
//		int row = posTable.rowCount();
//		if(row < 1){
//			throw new ErrorOnPageException("There is no pos info by name "+name);
//		}
//		int col = posTable.findColumn(0, "Unit Price");
//		String unitPrice = posTable.getCellValue(1, col);
//		Browser.unregister(objs);
//		return unitPrice;
	}
	
	/**
	 * Verify POS product unit price in list
	 * @param product
	 * @param unitPrice
	 */
	public void verifyUnitPrice(String product, String unitPrice){
		searchPOSByName(product);
		String unitPriceUI = getUnitPrice(product);
		if(StringUtil.compareNumStrings(unitPrice, unitPriceUI)!=0){
			throw new ErrorOnPageException("POS Unit Price display un-correctly on Pos Product Setup page.",
					unitPrice,
					unitPriceUI);
		}
		logger.info("POS Unit Price display correctly on Pos Product Setup page.");
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
		this.verifyPOSProdEffStartDateTextFieldExist(pos.productID, isTextFieldShown);
		this.verifyPOSProdEffEndDateTexFieldExist(pos.productID, isTextFieldShown);
		this.verifyPOSProdIDLinkExist(pos.productID, isProdIDLinkShown);
	}
	
	/**
	 * Verify the assigned POS item in the list. The product id link should be shown. 
	 * And verify the effective sales start and end dates with given format.
	 * @param pos
	 */
	public void verifyAssignedPOSProductItemInList(POSInfo pos, boolean isProductIDLinkExist) {
		this.verifyPOSProductItemInList(pos, isProductIDLinkExist, false);
	}
	
	/**
	 * Verify the unassigned POS item in the list. The product id link should not be shown.
	 * If the text field is shown, verify the effective start date. 
	 * @param pos
	 * @param isTextFieldShown
	 */
	public void verifyUnassigedPOSProductItemInList(POSInfo pos, boolean isTextFieldShown) {
		this.verifyPOSProductItemInList(pos, false, isTextFieldShown);
	}
	
	/** Check unit price text field exists */
	public boolean isUnitPriceTextFieldExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", id+"_up");
	}
	
	/** Get the value of the unit price text field */
	public String getUnitPriceTextFieldText(String id) {
		return browser.getTextFieldValue(".id", id+"_up").replace("$", "");
	}
	
	public boolean isVariablePriceDropdownListExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", id+"_vq");
	}
	
	/** Get the value of the variable price allowed dropdown list*/
	public String getVariablePriceAllowedValue(String id) {
		return browser.getDropdownListValue(".id", id+"_vq");
	}
	
	public boolean isPartialQuantityDropdownListExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", id+"_pq");
	}
	
	/** Get the value of the partial quantity allowed dropdown list*/
	public String getPartialQuantityAllowed(String id) {
		return browser.getDropdownListValue(".id", id+"_pq");
	}
	
	private boolean compareDateWithFormat(String meg, String expDate, String actDate, String assignedStatus) {
		if (assignedStatus.equalsIgnoreCase(OrmsConstants.YES_STATUS)) { // change the exp date format to "MM/dd/yyyy"
			expDate = DateFunctions.formatDate(expDate, "MM/dd/yyyy");
		} else { // change the exp date format to "EEE MMM d yyyy"
			expDate = DateFunctions.formatDate(expDate, "EEE MMM d yyyy");
		}
		logger.info("Compare expDate:"+expDate + " with actural date:" + actDate);
		return expDate.equalsIgnoreCase(actDate);
	}
	
	/**
	 * Check if product class dropdown list exist
	 */
	public boolean isProductClassListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "POSProductSetupSearchCriteria.productClassID");
	}
	
	/**
	 * Check if product sub class dropdown list exist
	 * @return
	 */
	public boolean isProductSubClassListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "POSProductSetupSearchCriteria.productSubClassID");
	}
	
	/**
	 * Check if inventory type dropdown list exist
	 * @return
	 */
	public boolean isInventoryTypeListEixst() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "POSProductSetupSearchCriteria.inventoryTypeID");
	}
	/**
	 * Select Product Class
	 * Added by Nicole, PCR 2956
	 * @param productClass
	 */
	public void selectProductClass(String productClass){
		if(StringUtil.isEmpty(productClass)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", productClass);
		}
	}
	
	/**
	 * Select Product Sub-Class
	 * Added by Nicole, PCR 2956
	 * @param productSubClass
	 */
	public void selectProductSubClass(String productSubClass){
		if(StringUtil.isEmpty(productSubClass)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", productSubClass);
		}
	}
	
	/**
	 * Select Product Inventory Type
	 * Added by Nicole, PCR 2956
	 * @param invType
	 */
	public void selectInventoryType(String invType){
		if(StringUtil.isEmpty(invType)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", 0, true);
		}else{
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", invType);
		}
	}
	
	public List<String> getInventoryTypeElements(){
		List<String> invTypeList = browser.getDropdownElements(".id", "POSProductSetupSearchCriteria.inventoryTypeID");
		invTypeList.remove(0);
		return invTypeList;
	}
	
	/**
	 * Check if Show Product Packages Only Check Box selected.
	 * Added by Nicole, PCR 2956
	 */
	public boolean isShowProductPackagesOnlySelected(){
		return browser.isCheckBoxSelected(".id", "POSProductSetupSearchCriteria.prdPckg");
	}
	
	/**
	 * Select Show Product Packages Only Check Box
	 * Added by Nicole, PCR 2956
	 */
	public void selectShowProductPackagesOnlyCheckBox(){
		browser.selectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg", true);
	}

	/**
	 * Unselect Show Product Packages Only Check Box
	 * Added by Nicole, PCR 2956
	 */
	public void unSelectShowProductPackagesOnlyCheckBox(){
		browser.unSelectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg");
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
	
	
	public String getWarningMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find any message.");
		}
		String msg = objs[0].getProperty(".text");
		return msg;
	}
	
	public String getErrorMsg(){
		return this.getWarningMsg();
	}
	
	/**
	 * click product name columns.
	 */
	public void clickProductNameColums(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Product Name");
	}
	/**
	 * click Product description columns.
	 */
	public void clickProductId(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Product ID");
	}
	/**
	 * click Product description columns.
	 */
	public void clickProductDscr(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Product Description");
	}
	/**
	 * select all check box.
	 */
	public void selectAllCheckBox(){
		browser.selectCheckBox(".value", "all");
	}
	/**
	 * unselect all check box.
	 */
	public void unselectAllCheckBox(){
		browser.unSelectCheckBox(".value", "all");
	}
	/**
	 * verify all the check box was checked.
	 *//*
	public void verifyCheckBoxChecked(){
		this.verifyCheckBoxChecked(this.getPrdIdArray());
	}
	*//**
	 * verify checkBox uncheked;
	 *//*
	public void verifyCheckBoxUnChecked(){
		this.verifyCheckBoxUnChecked(this.getPrdIdArray());
	}*/
	/**
	 * get product id array;
	 * @return
	 */
	private String[] getPrdIdArray(){
		List<String> array = this.getColumnValueByNam("Product ID");
		String[] prdIdArray = new String[array.size()];
		for(int i =0;i<array.size();i++){
			prdIdArray[i] = array.get(i);
		}
		return prdIdArray;
	}
	/**
	 * get the check box is select value.
	 * @return
	 */
	public List<Boolean> getCheckBoxIsSelectValue(String ... prdId){
		List<Boolean> isSelectList = new ArrayList<Boolean>();
		for(int i =0;i<prdId.length;i++){
			boolean isChecked = browser.isCheckBoxSelected("id", prdId[i]+"_ck");
			isSelectList.add(isChecked);
		}
		return isSelectList;
	}
	/**
	 * verify check box status.
	 */
	public void verifyCheckBoxChecked(String ... prdId){
		boolean isChecked = true;
		List<Boolean> checkBoxStatusList = this.getCheckBoxIsSelectValue(prdId);
		for(int i=0;i<checkBoxStatusList.size();i++){
			if(!checkBoxStatusList.get(i)){
				isChecked &= false;
			}
		}
		if(!isChecked){
			throw new ErrorOnPageException("The check box should be checked");
		}
	}
	/**
	 * verify check box unchecked.
	 * @param prdId
	 */
	public void verifyCheckBoxUnChecked(String ... prdId){
		boolean isChecked = true;
		List<Boolean> checkBoxStatusList = this.getCheckBoxIsSelectValue(prdId);
		for(int i=0;i<checkBoxStatusList.size();i++){
			if(checkBoxStatusList.get(i)){
				isChecked &= false;
			}
		}
		if(!isChecked){
			throw new ErrorOnPageException("The check box should be unchecked");
		}
	}
	/**
	 * Verify checked and unchecked check box.
	 * @param prdName
	 */
	public void checkSelectAndUnselectedSingleCheckBox(String prdName){
		String id = this.getPOSProdID(prdName);
		this.selectProductID(id);
		this.verifyCheckBoxChecked(id);
		
		this.unselectProductId(id);
		this.verifyCheckBoxUnChecked(id);
	}
	/**
	 * verify checked and unchecked  all check box.
	 */
	public void checkSelectAndUnselectedAllCheckBox(){
		String [] prdList = this.getPrdIdArray();
		this.selectAllCheckBox();
		this.verifyCheckBoxChecked(prdList);
		
		this.unselectAllCheckBox();
		this.verifyCheckBoxUnChecked(prdList);
	}

	public String getPropOfEditPrice(String productID) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", productID+"_up");
		String topParentClassName = objs[0].getParent().getProperty(".className");
		
		Browser.unregister(objs);
		if(topParentClassName.contains("readonly")){
			return "false";
		}
		
		return "true";
		//Shane[20131128],UI changed in 3.05 build
//		String property = "";
//		if(objs.length<1)
//		{
//			Browser.unregister(objs);
//			throw new ErrorOnPageException("Cannot find editbox of Price for product ID-->"+productID);
//		}else{
//			property = objs[0].getProperty(".readOnly");
//			Browser.unregister(objs);
//			if(null==property){
//				return "false";
//			}else{
//				return property.equalsIgnoreCase(StringUtil.EMPTY)?"true":property;
//			}
//			
//		}
		
		
	}
	
	 public List<POSInfo> getAllRecordsOnPage() {
			IHtmlObject objs[] = null;
			IHtmlTable table = null;
			List<POSInfo> records = new ArrayList<POSInfo>();
			int rows;
			int columns;
			POSInfo info;
			
			
			do{
				objs = browser.getTableTestObject(".id", "POSProductSetupView_LIST");;
				
				if(objs.length < 1) {
							throw new ItemNotFoundException("Can't rule POSProductSetupView_LIST table object.");
						}
				
				table = (IHtmlTable)objs[0];
				rows = table.rowCount();
				columns = table.columnCount();
				logger.info("Find record on page FinMgrPosProductAssignmentPage, "+rows+" rows, "+columns+" columns.");
				
				for(int i = 1; i < rows; i ++) {
					info = new POSInfo();
					info.productID = table.getCellValue(i, table.findColumn(0, "Product ID"));
					info.assignStatus = table.getCellValue(i, table.findColumn(0, "Assigned"));
					info.product = table.getCellValue(i, table.findColumn(0, "Product Name"));
					info.productDescription = table.getCellValue(i, table.findColumn(0, "Product Description"));
					info.availableLocation = table.getCellValue(i, table.findColumn(0, "Contract/Agency"));
					info.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
					//info.productClass = table.getCellValue(i, table.findColumn(0, "Product Class"));
					//info.productSubClass = table.getCellValue(i, table.findColumn(0, "Product Sub-Class"));
					info.inventoryType = table.getCellValue(i, table.findColumn(0, "Inventory Type"));
					info.qtyOnHand = table.getCellValue(i, table.findColumn(0, "Qty On Hand"));
					if(this.isUnitPriceTextFieldExist(info.productID))
					{
						info.unitPrice = this.getUnitPriceTextFieldText(info.productID);	
					}else{
						info.unitPrice = table.getCellValue(i, table.findColumn(0, "Unit Price"));
					}
					
					
					if(this.isVariablePriceDropdownListExist(info.productID))
					{
						info.variablePriceAllowed = this.getVariablePriceAllowedValue(info.productID);
					}else{
						info.variablePriceAllowed = table.getCellValue(i, table.findColumn(0, "Variable Price Allowed"));
					}
					
					
					if(this.isPartialQuantityDropdownListExist(info.productID))
					{
						info.partialQuantityAllowed = this.getPartialQuantityAllowed(info.productID);
					}else{
						info.partialQuantityAllowed = table.getCellValue(i, table.findColumn(0, "Partial Quantity Allowed"));
					}
					
					info.effectiveSalesStartDate = table.getCellValue(i, table.findColumn(0, "Effective Sales Start Date"));
					info.effectiveSalesEndDate = table.getCellValue(i, table.findColumn(0, "Effective Sales End Date"));				
					
					
					records.add(info);			
				}

			}while(gotoNext());
			
			Browser.unregister(objs);
			
			return records;
		}
		
		
		/**
		 * Check whether gotonext button exist, if exit,click it.
		 * @return
		 */
	 public boolean gotoNext() {
			IHtmlObject[] pageBar = browser.getHtmlObject(".class", "Html.TABLE", ".className", "pagingBar");
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next", pageBar[0] );
			boolean toReturn = false;

			if (objs.length > 0) {
				toReturn = true;
				objs[0].click();
				ajax.waitLoading();
			}

			Browser.unregister(pageBar);
			Browser.unregister(objs);
			this.waitLoading();

			return toReturn;
		}
	
}
