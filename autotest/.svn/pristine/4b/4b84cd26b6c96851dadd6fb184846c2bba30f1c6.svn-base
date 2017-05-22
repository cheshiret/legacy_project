/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It was master pos setup page includes search section and search result list
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jun 18, 2012
 */
public class InvMgrMasterPosSearchPage extends InvMgrCommonTopMenuPage {

	/** Column Names */
	private final String PRODUCT_ORDER_TYPE = "Order Type";
	private final String PRODUCT_STATUS = "Status";
	private final String PRODUCT_ID = "Product ID";
	private final String PRODUCT_NAME = "Product Name";
	private final String PRODUCT_DESCR = "Description";
	private final String PRODUCT_CLASS = "Product Class";
	private final String PRODUCT_SUB_CLASS = "Product Sub-Class";
	private final String PRODUCT_GROUP = "Product Group";
	private final String PRODUCT_AVAILABLE_LOC = "Product Available Location";
	private final String PRODUCT_INVENTORY_TYPE = "Inventory Type";
	private final String PRODUCT_REVENUE_LOC = "Revenue Location";
	
	private static InvMgrMasterPosSearchPage instance = null;
	
    private InvMgrMasterPosSearchPage(){};
    
    public static InvMgrMasterPosSearchPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrMasterPosSearchPage();
    	}
    	return instance;
    }
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("^Order Type.*", false));
	}
	
	/**
	 * click add pos product.
	 */
	public void clickAddPosProduct(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.status", false), status);
	}
	
	public void setProductId(String id) {
		browser.setTextField(".id", new RegularExpression("Criteria-\\d+\\.productId", false), id);
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression("Criteria-\\d+\\.name", false), name);
	}
	
	public void setProductDescription(String dscr) {
		browser.setTextField(".id", new RegularExpression("Criteria-\\d+\\.description", false), dscr);
	}
	
	public void setBarcodeUPC(String barcode) {
		browser.setTextField(".id", new RegularExpression("Criteria-\\d+\\.barCode", false), barcode);
	}
	
	public void selectOrderType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.productSubCategory", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.productSubCategory", false), type);
		}
		
	}
	
	public void selectProductGroup(String group) {
		if(StringUtil.isEmpty(group)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.group", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.group", false), group);
		}
	}
	
	public void selectProductClass(String clazz) {
		if(StringUtil.isEmpty(clazz)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.prdClass", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.prdClass", false), clazz);
		}
	}
	
	public void selectProductSubClass(String subClass) {
		if(StringUtil.isEmpty(subClass)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.prdSubClass", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.prdSubClass", false), subClass);
		}
	}
	
	public void selectInventoryType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.inventoryType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.inventoryType", false), type);
		}
	}
	
	public void selectProductAvailableLocation(String location) {
		if(StringUtil.isEmpty(location)){
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.availableLoc", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("Criteria-\\d+\\.availableLoc", false), location);
		}
	}
	
	public void setupSearchCriteria(POSInfo pos) {
		if(!StringUtil.isEmpty(pos.status)) {
			selectStatus(pos.status);
		}
		if(null != pos.productID) {
			setProductId(pos.productID);
		}
		if(null != pos.product) {
			setProductName(pos.product);
		}
		if(null != pos.productDescription) {
			setProductDescription(pos.productDescription);
		}
		if(null != pos.barcode) {
			setBarcodeUPC(pos.barcode);
		}
		if(null != pos.orderType) {
			selectOrderType(pos.orderType);
		}
		if(null != pos.productGroup) {
			selectProductGroup(pos.productGroup);
		}
		if(null != pos.productClass) {
			selectProductClass(pos.productClass);
		}
		if(null != pos.productSubClass) {
			selectProductSubClass(pos.productSubClass);
		}
		if(null != pos.inventoryType) {
			selectInventoryType(pos.inventoryType);
		}
		if(null != pos.availableLocation) {
			selectProductAvailableLocation(pos.availableLocation);
		}
	}
	
	public void searchPOS(POSInfo pos) {
		setupSearchCriteria(pos);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchPOSById(String id) {
		setProductId(id);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchPOSByName(String name) {
		setProductName(name);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickActivate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}
	
	public void clickDeactivate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void selectAllCheckboxes() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	private void selectPOSCheckBox(int index) {
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), index);
	}
	
	public int getRowIndexById(String id) {
//		HtmlObject objs[] = getTableObjectById(new RegularExpression("grid\\_\\d+\\_LIST", false));
//		if(objs.length < 1) {
//			throw new ItemNotFoundException("Can't find POS list table object.");
//		}
		IHtmlObject objs[] = this.getPOSProductListTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(2, id);
		Browser.unregister(objs);
		return rowIndex;
	}
	
	public int getRowIndexByName(String name) {
		IHtmlObject objs[] = this.getPOSProductListTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(4, name);
		Browser.unregister(objs);
		return rowIndex;
	}
	
	public void selectPOSCheckBox(String id) {
		int rowIndex = getRowIndexById(id);
		selectPOSCheckBox(rowIndex - 1);
	}
	
	public void deactivatePOS(String id) {
		searchPOSById(id);
		selectPOSCheckBox(id);
		clickDeactivate();
		ajax.waitLoading();
	}
	
	public void activatePOS(String id) {
		logger.info("Activate POS with id=" + id);
		searchPOSById(id);
		selectPOSCheckBox(id);
		clickActivate();
		ajax.waitLoading();
	}
	
	private IHtmlObject[] getPOSProductListTables() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression("grid\\_\\d+\\_LIST", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS list table object.");
		}
		return objs;
	}
	
	public POSInfo getPOSInfoByID(String id) {
		IHtmlObject[] objs = this.getPOSProductListTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> colNames = table.getRowValues(0);
		int rowIndex = table.findRow(colNames.indexOf(PRODUCT_ID), id);
		if (rowIndex < 1) {
			throw new ItemNotFoundException("Can's find the POS Product with id=" + id);
		}
		List<String> rowValues = table.getRowValues(rowIndex);
		POSInfo pos = new POSInfo();
		pos.orderType = rowValues.get(colNames.indexOf(PRODUCT_ORDER_TYPE));
		pos.productID = id;
		pos.status = rowValues.get(colNames.indexOf(PRODUCT_STATUS));
		pos.product = rowValues.get(colNames.indexOf(PRODUCT_NAME));
		pos.productDescription = rowValues.get(colNames.indexOf(PRODUCT_DESCR));
		if (colNames.contains(PRODUCT_CLASS)) {
			pos.productClass = rowValues.get(colNames.indexOf(PRODUCT_CLASS));
			pos.productSubClass = rowValues.get(colNames.indexOf(PRODUCT_SUB_CLASS));
		}
		pos.productGroup = rowValues.get(colNames.indexOf(PRODUCT_GROUP));
		pos.availableLocation = rowValues.get(colNames.indexOf(PRODUCT_AVAILABLE_LOC));
		pos.inventoryType = rowValues.get(colNames.indexOf(PRODUCT_INVENTORY_TYPE));
		String revLoc = rowValues.get(colNames.indexOf(PRODUCT_REVENUE_LOC));
		if (revLoc.equalsIgnoreCase("Where the product is sold")) {
			pos.specificLocation = false;
		} else {
			pos.specificLocation = true;
			pos.revLocation.agency = revLoc;
			pos.revLocation.region = revLoc;
			pos.revLocation.facility = revLoc;
		}
		
		Browser.unregister(objs);
		return pos;
	}
	
	public boolean comparePOSInList(POSInfo pos) {
		POSInfo actPOS = this.getPOSInfoByID(pos.productID);
		boolean result = true;
		result &= MiscFunctions.compareResult(PRODUCT_NAME, pos.product, actPOS.product);
		result &= MiscFunctions.compareResult(PRODUCT_ORDER_TYPE, pos.orderType, actPOS.orderType);
		result &= MiscFunctions.compareResult(PRODUCT_STATUS, pos.status, actPOS.status);
		result &= MiscFunctions.compareResult(PRODUCT_DESCR, pos.productDescription, actPOS.productDescription);
		result &= MiscFunctions.compareResult(PRODUCT_CLASS, pos.productClass, actPOS.productClass);
		result &= MiscFunctions.compareResult(PRODUCT_SUB_CLASS, pos.productSubClass, actPOS.productSubClass);
		result &= MiscFunctions.compareResult(PRODUCT_GROUP, pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult(PRODUCT_AVAILABLE_LOC, pos.availableLocation, actPOS.availableLocation);
		result &= MiscFunctions.compareResult(PRODUCT_INVENTORY_TYPE, pos.inventoryType, actPOS.inventoryType);
		result &= MiscFunctions.compareResult("Specific Revenue Location", pos.specificLocation, actPOS.specificLocation);
		if (pos.specificLocation) {
			if (pos.revLocation.agency.equalsIgnoreCase(actPOS.revLocation.agency) || 
					pos.revLocation.region.equalsIgnoreCase(actPOS.revLocation.region) ||
					pos.revLocation.facility.equalsIgnoreCase(actPOS.revLocation.facility) ) { // the actual POS revenue location's agency, region and facility is the same
				result &= true;
				logger.info(PRODUCT_REVENUE_LOC + " is correct! " + actPOS.revLocation.facility);
			} else {
				result &= false;
				logger.error(PRODUCT_REVENUE_LOC + " is wrong! The actual revenue location " + actPOS.revLocation.facility +
						" should be same as " + pos.revLocation.agency + " or " + pos.revLocation.region + " or " + pos.revLocation.facility);
			}
		}
		return result;
	}
	
	public void verifyPOSInList(POSInfo pos) {
		boolean result = this.comparePOSInList(pos);
		if (!result) {
			throw new ErrorOnPageException("The POS Info is wrong in the list!");
		}
		logger.info("The POS Info is correct in the list!");
	}
	
	public void clickProductID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public boolean isPOSProductExistInList(String id) {
		int index = this.getRowIndexById(id);
		return index > 0;
	}
	
	public boolean isPOSProductExistByName(String name) {
		this.searchPOSByName(name);
		int index = this.getRowIndexByName(name);
		return index > 0;
	}
	
	public String getIDByName(String name){
		this.searchPOSByName(name);
		IHtmlObject[] objs = this.getPOSProductListTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		return table.getCellValue(1, 2);
	}
	
	public void verifyPOSProductExistInList(String id, boolean exp) {
		boolean act = this.isPOSProductExistInList(id);
		String meg = exp ? " " : " NO ";
		if (exp != act) {
			throw new ErrorOnPageException("The product with id=" + id + " should" + meg + "be in the list!");
		}
		logger.info("The product with id=" + id + " is" + meg + "in the list!");
	}
	
	/** Get column value by POS product ID and column name */
	private String getPOSInfoByID(String id, String colName) {
		IHtmlObject[] objs = this.getPOSProductListTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, PRODUCT_ID);
		int rowIndex = table.findRow(colIndex, id);
		if (rowIndex < 1) {
			throw new ItemNotFoundException("Can's find the POS Product with id=" + id);
		}
		String value = table.getCellValue(rowIndex, table.findColumn(0, colName));
		Browser.unregister(objs);
		return value;
	}
	
	public String getPOSProductStatus(String productID) {
		return this.getPOSInfoByID(productID, PRODUCT_STATUS);
	}
	
	public void clickProductIDColumnName() {
		browser.clickGuiObject(".class", "Html.A", ".text", PRODUCT_ID);
	}
	
	public void clickMasterPOSSetupTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Master POS Setup");
	}
}
