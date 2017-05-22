/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ProductStoreAssignment;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;



/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Sep 27, 2011
 */
public class LicMgrProductInactiveAssignmentsWidget extends DialogWidget {
 
	private static LicMgrProductInactiveAssignmentsWidget instance=null;
	
	private  LicMgrProductInactiveAssignmentsWidget(){
		super("Inactive Assignments");
	}
	
	public static LicMgrProductInactiveAssignmentsWidget getInstance(){
		if(instance==null){
			instance=new LicMgrProductInactiveAssignmentsWidget();
		}
		return instance;
	}
	
	public void selectProducts(String productType){
		RegularExpression regx=new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue",false);
	    IHtmlObject[] objs=this.getWidget();
		int index=0;
		if(productType.equalsIgnoreCase("Privilege")){
			index=0;
	    }else if(productType.equalsIgnoreCase("Lottery")){
	    	index=1;
	    }else if(productType.equalsIgnoreCase("Vehicle")){
	    	index=2;
	    }else if(productType.equalsIgnoreCase("Consumable")){
	    	index=3;
	    }else if(productType.equalsIgnoreCase("Supply")){
	    	index=4;
	    }else{
	    	throw new ErrorOnDataException("Unknown product type...");
	    }
		browser.selectRadioButton(new Property[]{new Property(".id",regx)}, true, index, objs[0]);
	    Browser.unregister(objs);
	}

	/**
	 * Check assignment existing or not 
	 */
	public boolean checkAssignmentExist(String id) {
		IHtmlObject[] div=browser.getHtmlObject(this.widgetProperty);
		IHtmlObject[] objs=browser.getTableTestObject(new Property[]{new Property(".id", new RegularExpression("(inactive)?productAssignmentGrid", false))},div[0]);
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("Can't find assignments table");
	    }
	    IHtmlTable table=(IHtmlTable)objs[0];
	    int row=table.findRow(0, id);
	    Browser.unregister(div,objs);
	    if(row<1){
	    	return false;
	    }
	    return true;
	}
	
	public void searchProductInactiveAssignment(String productType){
		this.selectProducts(productType);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public ProductStoreAssignment getPrivilegeInactiveAssignmentInfo(String assignmentID){
		ProductStoreAssignment productStoreAssignment = new ProductStoreAssignment();
		productStoreAssignment = this.getProductInactiveAssignmentInfo(assignmentID, "Privilege");
		return productStoreAssignment;
	}
	
	public ProductStoreAssignment getVehicleInactiveAssignmentInfo(String assignmentID){
		ProductStoreAssignment productStoreAssignment = new ProductStoreAssignment();
		productStoreAssignment = this.getProductInactiveAssignmentInfo(assignmentID, "Vehicle");
		return productStoreAssignment;
	}
	
	public ProductStoreAssignment getConsumableInactiveAssignmentInfo(String assignmentID){
		ProductStoreAssignment productStoreAssignment = new ProductStoreAssignment();
		productStoreAssignment = this.getProductInactiveAssignmentInfo(assignmentID, "Consumable");
		return productStoreAssignment;
	}
	
	public ProductStoreAssignment getSupplyInactiveAssignmentInfo(String assignmentID){
		ProductStoreAssignment productStoreAssignment = new ProductStoreAssignment();
		productStoreAssignment = this.getProductInactiveAssignmentInfo(assignmentID, "Supply");
		return productStoreAssignment;
	}
	
	public List<String> getInactiveAssignmentListName(){
		List<String> inactiveAssignListName = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "productAssignmentGrid");
		if(objs.length<1){
			throw new ObjectNotFoundException("The inative product assignment table did not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];		
		inactiveAssignListName = table.getRowValues(0);
		
		Browser.unregister(objs);
		return inactiveAssignListName;
	}
	
	public ProductStoreAssignment getProductInactiveAssignmentInfo(String assignmentID, String productType){
		ProductStoreAssignment productStoreAssignment = new ProductStoreAssignment();
		
		this.searchProductInactiveAssignment(productType);
		IHtmlObject[] top = browser.getHtmlObject(".id", new RegularExpression("storeprodunassGridRow", false));
		
		IHtmlObject[] objs = browser.getTableTestObject(Property.toPropertyArray(".id", "inactiveProductAssignmentGrid"), top[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("The inative product assignment table did not found.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, "Assign ID");
		int row = table.findRow(col, assignmentID);

		if(productType.equalsIgnoreCase("Privilege")||productType.equalsIgnoreCase("Lottery")){
			col = table.findColumn(0, "Display Category");
			productStoreAssignment.displayCategory = table.getCellValue(row, col).trim();
			
			col = table.findColumn(0, "Display Sub-Category");
			productStoreAssignment.displaySubCategory = table.getCellValue(row, col).trim();
		}else if(productType.equalsIgnoreCase("Vehicle")){
			col = table.findColumn(0, "Vehicle Type");
			productStoreAssignment.vehicleType = table.getCellValue(row, col).trim();
		}else if(productType.equalsIgnoreCase("Consumable")){
			col = table.findColumn(0, "Order Type");
			productStoreAssignment.orderType = table.getCellValue(row, col).trim();
		}
		col =  table.findColumn(0, "Assign ID");
		productStoreAssignment.assignID = table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Code");
		productStoreAssignment.productCode = table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Name");
		productStoreAssignment.productName = table.getCellValue(row, col).trim();
		
		
		if(productType.equalsIgnoreCase("Lottery"))
		{
			col = table.findColumn(0, "Species");
			productStoreAssignment.species = table.getCellValue(row, col).trim();
		}else{
			col = table.findColumn(0, "Product Group");
			productStoreAssignment.productGroup = table.getCellValue(row, col).trim();
		}
		
		col = table.findColumn(0, "Assign Status");
		productStoreAssignment.assignStatus =  table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Creation User");
		productStoreAssignment.creationUser =  table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Creation Date");
		productStoreAssignment.creationDateTime =  table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Last Mod User");
		productStoreAssignment.lastModUser =  table.getCellValue(row, col).trim();
		
		col = table.findColumn(0, "Last Mod Date");
		productStoreAssignment.lastModDate =  table.getCellValue(row, col).trim();
		
		Browser.unregister(objs);
		
		return productStoreAssignment;		
	}
	
	public boolean compareProductInactiveAssignInfo(ProductStoreAssignment expectInfo){
		boolean result = true;
		ProductStoreAssignment actualInfo = new ProductStoreAssignment();
		
		if(expectInfo.productCategory.equalsIgnoreCase("Privilege")){
			actualInfo = this.getPrivilegeInactiveAssignmentInfo(expectInfo.assignID);
		}else if(expectInfo.productCategory.equalsIgnoreCase("Vehicle")){
			actualInfo = this.getVehicleInactiveAssignmentInfo(expectInfo.assignID);
		}else if(expectInfo.productCategory.equalsIgnoreCase("Consumable")){
			actualInfo = this.getConsumableInactiveAssignmentInfo(expectInfo.assignID);
		}else if(expectInfo.productCategory.equalsIgnoreCase("Supply")){
			actualInfo = this.getSupplyInactiveAssignmentInfo(expectInfo.assignID);
		}else {
			throw new ErrorOnPageException("Did not know the product category " + expectInfo.productCategory);
		}
		
		if(!actualInfo.assignID.equals(expectInfo.assignID)){
			result = false;
			logger.info("Expect assign ID should be " + expectInfo.assignID 
					+ ", but actually assign ID is " + actualInfo.assignID);
		}
		
		if(!actualInfo.productCode.equals(expectInfo.productCode)){
			result = false;
			logger.info("Expect product code should be " + expectInfo.productCode
					+ ", but actually product code is " + actualInfo.productCode);
		}
		
		if(!actualInfo.productName.equals(expectInfo.productName)){
			result = false;
			logger.info("Expect product name should be " + expectInfo.productName
					+ ", but actually product name is " + actualInfo.productName);
		}
		
		if(!actualInfo.productGroup.equals(expectInfo.productGroup)){
			result = false;
			logger.info("Expect product group should be " + expectInfo.productGroup
					+ ", but actually product group is " + actualInfo.productGroup);
		}
		
		if(!actualInfo.assignStatus.equals(expectInfo.assignStatus)){
			result = false;
			logger.info("Expect assign status should be " + expectInfo.assignStatus
					+ ", but actually assign status is " + actualInfo.assignStatus);
		}
		
		if(!actualInfo.creationUser.replaceAll("\\s+", StringUtil.EMPTY).equals(expectInfo.creationUser.replaceAll("\\s+", StringUtil.EMPTY))){
			result = false;
			logger.info("Expect creation user should be " + expectInfo.creationUser
					+ ", but actually creation user is " + actualInfo.creationUser);
		}
		
		if(!actualInfo.creationDateTime.equals(expectInfo.creationDateTime)){
			result = false;
			logger.info("Expect creation date should be " + expectInfo.creationDateTime
					+ ", but actually creation date is " + actualInfo.creationDateTime);
		}
		
		if(!actualInfo.lastModUser.replaceAll("\\s+", StringUtil.EMPTY).equals(expectInfo.lastModUser.replaceAll("\\s+", StringUtil.EMPTY))){
			result = false;
			logger.info("Expect last mod user should be " + expectInfo.lastModUser
					+ ", but actually last mod user is " + actualInfo.lastModUser);
		}
		
		if(!actualInfo.lastModDate.equals(expectInfo.lastModDate)){
			result = false;
			logger.info("Expect last mod date should be " + expectInfo.lastModDate
					+ ", but actually last mod date is " + actualInfo.lastModDate);
		}
		
		if(expectInfo.productCategory.equalsIgnoreCase("Privilege")){
			if(!actualInfo.displayCategory.equals(expectInfo.displayCategory)){
				result = false;
				logger.info("Expect display category should be " + expectInfo.displayCategory
						+ ", but acutally display category should be " + actualInfo.displayCategory);
			}
			if(!actualInfo.displaySubCategory.equals(expectInfo.displaySubCategory)){
				result = false;
				logger.info("Expect display sub category should be " + expectInfo.displaySubCategory
						+ ", but acutally sub display category should be " + actualInfo.displaySubCategory);
			}
		}else if(expectInfo.productCategory.equalsIgnoreCase("Vehicle")){
			if(!actualInfo.vehicleType.equals(expectInfo.vehicleType)){
				result = false;
				logger.info("Expect vehicle type should be " + expectInfo.vehicleType
						+ ", but acutally vehicle type should be " + actualInfo.vehicleType);
			}
		}else if(expectInfo.productCategory.equalsIgnoreCase("Consumable")){
			if(!actualInfo.orderType.equals(expectInfo.orderType)){
				result = false;
				logger.info("Expect order type should be " + expectInfo.orderType
						+ ", but acutally order type should be " + actualInfo.orderType);
			}
		}
				
		return result;
	}

	/**
	 * 
	 */
	public void clickGo() {
		IHtmlObject[] objs=this.getWidget();
		browser.clickGuiObject(new Property[]{new Property(".class","Html.A"),new Property(".text","Go")}, true, 0, objs[0]);
		Browser.unregister(objs);
	}
}
