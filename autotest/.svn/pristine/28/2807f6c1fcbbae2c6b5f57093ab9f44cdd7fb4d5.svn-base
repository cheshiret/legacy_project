package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrPOSSupplierProductAssignmentDetailsPage extends InvMgrPOSSupplierCommonPage {
	private static InvMgrPOSSupplierProductAssignmentDetailsPage _instance = null;

	private InvMgrPOSSupplierProductAssignmentDetailsPage() {

	}

	public static InvMgrPOSSupplierProductAssignmentDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrPOSSupplierProductAssignmentDetailsPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierProductCode",false));
	}
	
	public String getProductID() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductLightView-\\d+\\.id", false));
	}
	
	public String getProductName() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductLightView-\\d+\\.productName", false));
	}
	
	public String getProductDescription() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductLightView-\\d+\\.productDescription", false));
	}
	
	public String getProductGroup() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductLightView-\\d+\\.productGroupName", false));
	}
	
	public String getSupplierProductCode() {
		return browser.getTextFieldValue(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierProductCode", false));
	}
	
	public String getSupplierUnitCost() {
		return browser.getTextFieldValue(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierUnitCost", false));
	}
	
	/**
	 * set supplier product code.
	 * @param productCode
	 */
	public void setSupplierProductCode(String productCode){
		browser.setTextField(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierProductCode",false), productCode);
	}
	
	/**
	 * set supplier unit cost.
	 * @param cost
	 */
	public void setSupplierUnitCost(String cost){
		browser.setTextField(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierUnitCost",false), cost);
	}
	
	/**
	 * click OK button.
	 */
	public void clickOkButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancelButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public POSInfo getPOSInfo() {
		POSInfo pos = new POSInfo();
		pos.productID = getProductID();
		pos.product = getProductName();
		pos.productDescription = getProductDescription();
		pos.productGroup = getProductGroup();
		pos.supplierProductCode = getSupplierProductCode();
		pos.unitCost = getSupplierUnitCost();
		
		return pos;
	}
	
	public boolean compareSupplierPOSAssignmentDetailsInfo(String supplierId, String supplierName, POSInfo pos) {
		String actualSupplierId = getSupplierId();
		String actualSupplierName = getSupplierName();
		POSInfo actualPOS = getPOSInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("POS Supplier ID", supplierId, actualSupplierId);
		result &= MiscFunctions.compareResult("POS Supplier Name", supplierName, actualSupplierName);
		result &= MiscFunctions.compareResult("POS Product ID", pos.productID, actualPOS.productID);
		result &= MiscFunctions.compareResult("POS Product Name", pos.product, actualPOS.product);
		result &= MiscFunctions.compareResult("POS Product Description", pos.productDescription, actualPOS.productDescription);
		result &= MiscFunctions.compareResult("POS Product Group", pos.productGroup, actualPOS.productGroup);
		result &= comparePOSAssignmentEditInfo(pos.supplierProductCode, pos.unitCost);
		
		return result;
	}
	
	/**
	 * Compare POS assignment edit info.
	 * @param productCode -  the product code.
	 * @param unitCost - the unit cost.
	 */
	public boolean comparePOSAssignmentEditInfo(String productCode, String unitCost){
      boolean pass = true;
      pass &= MiscFunctions.compareResult("POS Supplier Product Code", productCode, getSupplierProductCode());
      pass &= MiscFunctions.compareResult("POS Supplier Unit Cost", unitCost, getSupplierUnitCost());
      
      return pass;
    }
}
