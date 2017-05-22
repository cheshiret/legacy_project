package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class LicMgrStoreProductAssignmentsPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreProductAssignmentsPage _instance = null;
	public static String PRIVILEGE_PRD = "Privilege";
	public static String LOTTERY_PRD = "Lottery";
	public static String VEHICLE_PRD = "Vehicle";
	public static String CONSUMABLE_PRD = "Consumable";
	public static String SUPPLY_PRD = "Supply";
	
	
	protected LicMgrStoreProductAssignmentsPage() {}
	
	public static LicMgrStoreProductAssignmentsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreProductAssignmentsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "View Inactive Assignments");
	}
	
	public void checkShowProductsAssignedToStoresWithTheSameLocationClassOnly() {
		browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false), 0);
	}
	
	public void uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false), 0);
	}
	
	public void selectPrivilegeProducts() {
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), 0);
	}
	
	public void selectVehicleProducts() {
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), 2);
	}
	
	public void selectConsumableProducts() {
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), 3);
	}
	
	public void selectLotteryProducts() {
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), ".value", "LOTTERY");
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickAssign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}
	
	public void clickUnassign() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign");
	}
	
	public String getTabInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("^Product Assignments(|\\(\\d+\\))",false));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Product Assignmet label Object.");
		}
		
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public void assginAndUnassignProduct(String prdCode, boolean isAssign){
		IHtmlObject[] td = browser.getHtmlObject(".class", "Html.TD", ".text", prdCode);
		IHtmlObject tr = (IHtmlObject)td[0].getParent();
		
		browser.selectCheckBox(".id", new RegularExpression("HFProductAssignmentView-\\d+\\.assignChbx",false), 0, true, tr);

		if(isAssign)
		{
			this.clickAssign();
		}else{
			this.clickUnassign();
		}
		ajax.waitLoading();
		this.waitLoading();
	
		Browser.unregister(td);
	
	}
	
	public List<String> getSpecificAssignmentInfo(String productCategort, String productCode){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "productAssignmentGrid");
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int colNum = grid.findColumn(0, "Code");
		int rowNum = grid.findRow(colNum, productCode);
		List<String> assignmentInfo = grid.getRowValues(rowNum);
		
		Browser.unregister(objs);
		return assignmentInfo;
	}
	
	public List<ProductStoreAssignment> getAllProductStoreAssignmentsRecords(String prodType) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "productAssignmentGrid");
		
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Product-Store Assignment table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 1) {
			throw new ErrorOnDataException("The Product-Store Assignment table has no data.");
		}
		
		List<ProductStoreAssignment> assignments = new ArrayList<ProductStoreAssignment>();
		ProductStoreAssignment assignment = null;
//		String columnName = table.getCellValue(0, 5).trim();
		
		int offset = 0;
		for(int i = 1; i < table.rowCount(); i ++) {
			assignment = new ProductStoreAssignment();
			assignment.assignID = table.getCellValue(i, 1).trim();
			offset = 0;//initial offset value
			if(prodType.equalsIgnoreCase("Privilege") || prodType.equalsIgnoreCase("Lottery")) {
//				assignment.productCategory = "Privilege";
				assignment.productCategory = prodType;
				offset = 2;
				assignment.displayCategory = table.getCellValue(i, 2).trim();
				assignment.displaySubCategory = table.getCellValue(i, 3).trim();
			}
			assignment.productCode = table.getCellValue(i, 2 + offset).trim();
			assignment.productName = table.getCellValue(i, 3 + offset).trim();
//			assignment.productGroup = table.getCellValue(i, 4 + offset).trim();
			if (prodType.equalsIgnoreCase("Lottery")) { //Lesley[20140114]: Product Group column is replaced by Species column for Lottery Product
				assignment.species = table.getCellValue(i, 4 + offset).trim();
			} else {
				assignment.productGroup = table.getCellValue(i, 4 + offset).trim();
			}
			if(prodType.equalsIgnoreCase("Vehicle")) {
				assignment.productCategory = "Vehicle";
				offset = 1;
				assignment.vehicleType = table.getCellValue(i, 5).trim();
			} else if(prodType.equalsIgnoreCase("Consumable")) {
				assignment.productCategory = "Consumable";
				offset = 1;
				assignment.orderType = table.getCellValue(i, 5).trim();
			} else if(prodType.equalsIgnoreCase("Supply")){
				offset=0;
			}
			assignment.isAssigned = table.getCellValue(i, 5 + offset).trim().equalsIgnoreCase("Yes") ? true:false;
			assignment.creationUser = table.getCellValue(i, 6 + offset).trim();
			assignment.creationDateTime = table.getCellValue(i, 7 + offset).trim();
			
			assignments.add(assignment);
		}
		
		Browser.unregister(objs);
		return assignments;
	}
	
	/**
	 * Parse and get the all ProductStoreAssignment records from assignment table
	 * @return
	 */
	public List<ProductStoreAssignment> getAllProductStoreAssignmentsRecords() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "productAssignmentGrid");
		
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Product-Store Assignment table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 1) {
			throw new ErrorOnDataException("The Product-Store Assignment table has no data.");
		}
		String columnName = table.getCellValue(0, 5).trim();
		String productFlag = columnName.equalsIgnoreCase("Name") ? "Privilege" : columnName.equalsIgnoreCase("Vehicle Type") ? "Vehicle" : "Consumable";
		
		return this.getAllProductStoreAssignmentsRecords(productFlag);
	}
	
	/**
	 * Get all product store assignments records for all three product category
	 * @param productCategory - Privilege/Vehicle/Consumable
	 * @return
	 */
	public List<ProductStoreAssignment> getAllProductStoreAssignments(String productCategory) {
		this.uncheckShowProductsAssignedToStoresWithTheSameLocationClassOnly();
		this.gotoProductAssignmentListByProductCategory(productCategory);
		
		List<ProductStoreAssignment> assignments = this.getAllProductStoreAssignmentsRecords(productCategory);
		return assignments;
	}
	
	/**
	 * Go to product assignment list by product category
	 * @param productCategory
	 */
	public void gotoProductAssignmentListByProductCategory(String productCategory){
		if(productCategory.equalsIgnoreCase("Privilege")) {
			this.selectPrivilegeProducts();
		} else if (productCategory.equalsIgnoreCase("Vehicle")) {
			this.selectVehicleProducts();
		} else if (productCategory.equalsIgnoreCase("Consumable")) {
			this.selectConsumableProducts();
		} else if (productCategory.equalsIgnoreCase("Supply")){
			this.selectSupplyProducts();
		}else if (productCategory.equalsIgnoreCase("Lottery")){
			this.selectLotteryProducts();
		}else{
			throw new ErrorOnDataException("Unknow product category - " + productCategory);
		}
		this.clickGo();
		ajax.waitLoading();
	}
	
	/**
	 * Get a specified Product-Store Assignment identified by product code. If it doesn't exist, throw an exception
	 * @param productCategory
	 * @param productCode - privilege/vehicle/consumable code
	 * @return
	 */
	public ProductStoreAssignment getProductStoreAssignmentByCode(String productCategory, String productCode) {
		List<ProductStoreAssignment> productStoreAssignments = this.getAllProductStoreAssignments(productCategory);
		
		ProductStoreAssignment toReturn = null;
		for(int i = 0; i < productStoreAssignments.size(); i ++) {
			if(productStoreAssignments.get(i).productCode.equalsIgnoreCase(productCode)) {
				toReturn = (ProductStoreAssignment)productStoreAssignments.get(i);
				break;
			}
		}
		
		if(null == toReturn) {
			throw new ErrorOnDataException("Can't find any Product-Store Assignment record by " + productCategory + " code - " + productCode);
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @param productCode
	 * @return
	 */
	public String getProductStoreAssignmentIdByCode(String productCode) {
        IHtmlObject objs[] = browser.getTableTestObject(".id", "productAssignmentGrid");
		
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Product-Store Assignment table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() == 1) {
			throw new ErrorOnDataException("The Product-Store Assignment table has no data.");
		}
		
		int col=table.findColumn(0, "Code");
		int row=table.findRow(col, productCode);
		String assignId=table.getCellValue(row, 1);
		Browser.unregister(objs);
		return assignId;
	}
	
	public ProductStoreAssignment getPrivilegeStoreAssignmentByCode(String privilegeCode) {
		return this.getProductStoreAssignmentByCode("Privilege", privilegeCode);
	}
	
	public ProductStoreAssignment getVehicleStoreAssignmentByCode(String vehicleCode) {
		return this.getProductStoreAssignmentByCode("Vehicle", vehicleCode);
	}
	
	public ProductStoreAssignment getConsumableStoreAssignmentByCode(String consumableCode) {
		return this.getProductStoreAssignmentByCode("Consumable", consumableCode);
	}
	
	public ProductStoreAssignment getSupplyStoreAssignmentByCode(String supplyCode) {
		return this.getProductStoreAssignmentByCode("Supply", supplyCode);
	}
	
	public ProductStoreAssignment getLotteryStoreAssignmentByCode(String lotteryCode) {
		return this.getProductStoreAssignmentByCode("Lottery", lotteryCode);
	}
	
	public boolean verifyProductAssignedToStoreByCode(String productCategory, String productCode) {
		List<ProductStoreAssignment> assignments = this.getAllProductStoreAssignments(productCategory);
		boolean result = false;
		for(ProductStoreAssignment assignment : assignments) {
			if(assignment.productCode.equalsIgnoreCase(productCode) && assignment.isAssigned) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public boolean isAssigned(String prodCode){
		IHtmlObject[] objs=this.getAssignmentTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int codeColumn=table.findColumn(0, "Code");
		int row=table.findRow(codeColumn, prodCode);
		int assginColumn=table.findColumn(0, "Assigned");
		String assigned=table.getCellValue(row,assginColumn);
		Browser.unregister(objs);
		
		if(assigned.equals("Yes")){
			return true;
		}
		
		return false;
	}
	
	private IHtmlObject[] getAssignmentTable(){
		 IHtmlObject[] objs=browser.getTableTestObject(".id", "productAssignmentGrid");
		 if(objs.length<1){
		    throw new ObjectNotFoundException("Can't find Product Assignments Table by id="+"productAssignmentGrid");
		 }
         return objs;
	}
	
	public void clickViewInactiveAssignments(){
		browser.clickGuiObject(".class", "Html.A", ".text", "View Inactive Assignments");
	    ajax.waitLoading();
	}
	
	public void selectProductRecord(String code){
		IHtmlObject[] objs=getAssignmentTable();
	    IHtmlTable table=(IHtmlTable)objs[0];
	    int column=table.findColumn(0, "Code");
	    int index=table.findRow(column, code)-1;
	    if(index<0){
	    	throw new ErrorOnPageException("There isn't any product record for assignment.");
	    }
	    
	    browser.selectCheckBox(".id", new RegularExpression("HFProductAssignmentView-\\d+\\.assignChbx",false), index);
	    
	    Browser.unregister(objs);
	}
	
	public void selectSupplyProducts() {
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue",false),4);
	}
	
	public List<String> getAssignmentListName(){
		List<String> assignmentListName = new ArrayList<String>();
		IHtmlObject[] objs=getAssignmentTable();
	    IHtmlTable table=(IHtmlTable)objs[0];
	    assignmentListName = table.getRowValues(0);
	    
	    Browser.unregister(objs);
		 
		return assignmentListName;
	}
	
	public boolean compareProductStoreAssignmentInfo(ProductStoreAssignment expectAssignmentInfo){
		logger.info("Compare store assignment info.");
		boolean result = true;
		ProductStoreAssignment actualAssignmentInfo = new ProductStoreAssignment();
		
		if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Privilege")){
			actualAssignmentInfo = this.getPrivilegeStoreAssignmentByCode(expectAssignmentInfo.productCode);
		}else if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Vehicle")){
			actualAssignmentInfo = this.getVehicleStoreAssignmentByCode(expectAssignmentInfo.productCode);
		}else if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Consumable")){
			actualAssignmentInfo = this.getConsumableStoreAssignmentByCode(expectAssignmentInfo.productCode);
		}else if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Supply")){
			actualAssignmentInfo = this.getSupplyStoreAssignmentByCode(expectAssignmentInfo.productCode);
		}else {
			throw new ErrorOnPageException("Did not know the product category " + expectAssignmentInfo.productCategory);
		}
		
		if(expectAssignmentInfo.isAssigned){
			if(!actualAssignmentInfo.isAssigned){
				result = false;
				logger.error("Expect assigned should be "+ expectAssignmentInfo.isAssigned
						+ ", but acutally is " + actualAssignmentInfo.isAssigned);
			}
		}
		
		if(expectAssignmentInfo.isAssigned){
			if(0 == actualAssignmentInfo.assignID.trim().length()){
				result = false;
				logger.error("System should generate assginment ID when assign product to store.");
			}
		}else {
			if(0 != actualAssignmentInfo.assignID.trim().length()){
				result = false;
				logger.error("System should not generate assignment ID when did not assign product to store.");
			}
		}
		
		if(!expectAssignmentInfo.productCode.equals(actualAssignmentInfo.productCode)){
			result = false;
			logger.error("Expect product code should be " + expectAssignmentInfo.productCode 
					+ ", but actually is " + actualAssignmentInfo.productCode);
		}
		
		if(!expectAssignmentInfo.productName.equals(actualAssignmentInfo.productName)){
			result = false;
			logger.error("Expect product name should be " + expectAssignmentInfo.productName
					+ ", but actually is " + actualAssignmentInfo.productName);
		}
		
		if(!expectAssignmentInfo.productGroup.equals(actualAssignmentInfo.productGroup)){
			result = false;
			logger.error("Expect product category should be " + expectAssignmentInfo.productGroup
					+ ", but actually is " + actualAssignmentInfo.productGroup);
		}
		
		if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Privileges")){
			if(!expectAssignmentInfo.displayCategory.equals(actualAssignmentInfo.displayCategory)){
				result = false;
				logger.error("Except display category should be " + expectAssignmentInfo.displayCategory
						+ ", but acutally is " + actualAssignmentInfo.displayCategory);
			}
			
			if(!expectAssignmentInfo.displaySubCategory.equals(actualAssignmentInfo.displaySubCategory)){
				result = false;
				logger.error("Expect display sub category should be " + expectAssignmentInfo.displaySubCategory
						+ ", but acutally is " + actualAssignmentInfo.displaySubCategory);
			}
		}
		
		if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Vehicle")){
			if(!expectAssignmentInfo.vehicleType.equals(actualAssignmentInfo.vehicleType)){
				result = false;
				logger.error("Expect vehicle type should be " + expectAssignmentInfo.vehicleType
						+ ", but actually is " + actualAssignmentInfo.vehicleType);
			}
		}
		
		if(expectAssignmentInfo.productCategory.equalsIgnoreCase("Consumable")){
			if(!expectAssignmentInfo.orderType.equals(actualAssignmentInfo.orderType)){
				result = false;
				logger.error("Expect order type should be " + expectAssignmentInfo.orderType 
						+ ", but actually is " + actualAssignmentInfo.orderType);
			}
		}		
		
		if(!expectAssignmentInfo.creationUser.trim().replaceAll("\\s+", StringUtil.EMPTY).equals(actualAssignmentInfo.creationUser.trim().replaceAll("\\s+", StringUtil.EMPTY))){
			result = false;
			logger.error("Expect creation user should be  " + expectAssignmentInfo.creationUser
					+ ", but actually is " + actualAssignmentInfo.creationUser);
		}
		
		if(!actualAssignmentInfo.creationDateTime.trim().contains(expectAssignmentInfo.creationDateTime.trim())){
			result = false;
			logger.error("Expect creation date/time should be " + expectAssignmentInfo.creationDateTime
					+ ", but actually is " + actualAssignmentInfo.creationDateTime);
		}	
		
		return result;
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<ProductStoreAssignment> getAllRecordsOnPage(String type) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<ProductStoreAssignment> records = new ArrayList<ProductStoreAssignment>();
		int rows;
		int columns;
		ProductStoreAssignment info;
		
		
		objs = browser.getTableTestObject(".id", "productAssignmentGrid");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find product assignment table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrStoreProductAssignmentsPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new ProductStoreAssignment();
			info.assignID = table.getCellValue(i, table.findColumn(0, "Assign ID"));
			info.productCode = table.getCellValue(i, table.findColumn(0, "Code"));
			info.productName = table.getCellValue(i, table.findColumn(0, "Name"));
			
			if(type.equalsIgnoreCase(LOTTERY_PRD))
			{
				info.species = table.getCellValue(i, table.findColumn(0, "Species"));
				info.displayCategory = table.getCellValue(i, table.findColumn(0, "Display Category"));
				info.displaySubCategory = table.getCellValue(i, table.findColumn(0, "Display Sub-Category"));
			}
			if(type.equalsIgnoreCase(PRIVILEGE_PRD))
			{
				info.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
				info.displayCategory = table.getCellValue(i, table.findColumn(0, "Display Category"));
				info.displaySubCategory = table.getCellValue(i, table.findColumn(0, "Display Sub-Category"));
			}
			
			if(type.equalsIgnoreCase(VEHICLE_PRD))
			{
				info.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
				info.vehicleType = table.getCellValue(i, table.findColumn(0, "Vehicle Type"));
			}
			
			if(type.equalsIgnoreCase(CONSUMABLE_PRD))
			{
				info.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
				info.orderType = table.getCellValue(i, table.findColumn(0, "Order Type"));
			}
			
			if(type.equalsIgnoreCase(SUPPLY_PRD))
			{
				info.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
			}
			
			info.assignStatus = table.getCellValue(i, table.findColumn(0, "Assigned"));
			info.creationUser = table.getCellValue(i, table.findColumn(0, "Creation User"));
			info.creationDateTime = table.getCellValue(i, table.findColumn(0, "Creation Date/Time"));
			
			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
}
