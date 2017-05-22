/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date Jan 13, 2012
 */
public class LicMgrVehicleRegistrationWidget extends DialogWidget {
	private static LicMgrVehicleRegistrationWidget _instance = null;

	protected LicMgrVehicleRegistrationWidget() {

	}

	public static LicMgrVehicleRegistrationWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrVehicleRegistrationWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.TD", ".text", new RegularExpression("(Registration|Title|Inspection) Product\\(s\\)", false));
		boolean flag3 = browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("(Registration|Title|Inspection) Product(\\()?s(\\)?)", false));
		return flag1 && (flag2|flag3);
	}
	
	
//	public void clickOK(){
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("OK|k",false), 1);
//	}
	
	public void selectRegistrationProduct(String product){
		RegularExpression reg = new RegularExpression("(grid_\\d+)|(reg_list)", false);
		IHtmlObject[] productTable = browser.getTableTestObject(".id", reg);	
		IHtmlTable table = null;
		for(int i=productTable.length;i>0;i--){
			table = (IHtmlTable) productTable[productTable.length-i];
			if(null!= table){
//				for(int j =0;j<table.rowCount();j++){
//					System.out.println(table.getRowValues(j));
//				}
				int row = 1;
				row = table.findRow(1, 2, product);
				if(row >0){
					//throw new ErrorOnDataException("Could not find product:"+product+" from registration widget");
					//logger.info(""Could not find product:"+product+" from registration widget"");
					//browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), row-1);
					Property[] property = new Property[1];
					property[0] = new Property(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
					browser.selectRadioButton(property, false, row-1, productTable[productTable.length-i]);
					break;
				}
			}
		}
		
		Browser.unregister(productTable);
	}
	
	/**
	 * Verify the register product info in some action type.
	 */
	public void verifyTransferRegistprocutListType(String type){
		RegularExpression reg = new RegularExpression("grid_\\d+", false);
		IHtmlObject[] productTable = browser.getTableTestObject(".id", reg);			
		if(productTable.length<1){
			throw new ErrorOnDataException("Could not find produc from registration widget");
		}
		IHtmlTable table = (IHtmlTable) productTable[productTable.length-1];
		List<String> list = table.getColumnValues(1);
		for(int i=1;i<list.size();i++){
			if(!list.get(i).equals(type)){
				throw new ErrorOnDataException("The transfer regist product in list info error",type,list.get(i));
			}
		}
		Browser.unregister(productTable);
	}
	
	public boolean checkRegistProductInList(String productName){
		IHtmlObject[] productTable = browser.getTableTestObject(".className", "gridView");			
		if(productTable.length<1) {
			throw new ItemNotFoundException("Could not find produc from registration widget");
		}
		IHtmlTable table = (IHtmlTable) productTable[productTable.length-1];
		List<String> namesOnPage = table.getColumnValues(2);
		Browser.unregister(productTable);
		
		String regx = "(\\s)?-(\\s)?";
		for(int i = 0; i < namesOnPage.size(); i++) {
			if(namesOnPage.get(i).replaceFirst(regx, "-").equals(productName.replaceFirst(regx, "-"))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * verify the expected product should exist in registration product list.
	 * @param productName
	 */
	public void verifyProductExistingInList(String productName, boolean expectedExists) {
		boolean isExist = this.checkRegistProductInList(productName);
		if(isExist != expectedExists) {
			throw new ErrorOnPageException("The product - '" + productName + "' should" + (expectedExists?"":" NOT") + " exists in the registration/title list.");
		} else logger.info("The product - '" + productName + "' really " + (expectedExists ? "":" NOT") + " exists in the registration/title list.");
	}
	
	/**
	 * Check the product with the given name and type in the product list 
	 * @param productName
	 * @param purchaseType
	 * @return
	 * @author Lesley Wang
	 * @date Jul 17, 2012
	 */
	public boolean checkRegistProductInList(String productName, String purchaseType){
		if (purchaseType.equals(OrmsConstants.CORRECTED_PURCHASE_TYPE) || 
				purchaseType.equals(OrmsConstants.DUPLICATE_PURCHASE_TYPE)) {
			productName = productName.replace(" - ", "-");
		}
		IHtmlObject[] prodTR = browser.getHtmlObject(".class", "Html.TR", ".text", 
				new RegularExpression("^" + purchaseType + "\\s*" + productName + ".*", false));
		boolean result = true;
		if(prodTR.length < 1) {
			result = false;
		}
		Browser.unregister(prodTR);
		return result;
	}
	
	/**
	 * verify the product with the given name and type in registration product list.
	 * @param productName
	 * @param purchaseType
	 * @param expectedExists
	 */
	public void verifyProductExistingInList(String productName, String purchaseType, boolean expectedExists) {
		boolean isExist = this.checkRegistProductInList(productName, purchaseType);
		String meg = "The product with name=" + productName + " and type=" + purchaseType;
		if(isExist != expectedExists) {
			throw new ErrorOnPageException(meg + " should" + (expectedExists?"":" NOT") + " exist in the registration/title list.");
		} else logger.info(meg + "' really " + (expectedExists ? "":" NOT") + " exists in the registration/title list.");
	}
	
	public void selectRegistrationProduct(String type, String product){
		IHtmlObject[] objs  = browser.getHtmlObject(Property.atList(widgetProperty,Property.toPropertyArray(".class", "Html.TABLE", ".className", "gridView")));
		IHtmlTable table=(IHtmlTable)objs[0];

		int col_prod=table.findColumn(0, "Product");
		int col_type=table.findColumn(0, "Type");
		
		String temp[] = product.split("-");
		RegularExpression regex = new RegularExpression(temp[0].trim() + "(\\s)?-(\\s)?" + temp[1].trim(), false);
		
		int[] rows=table.findRows(col_prod, regex);
		boolean found=false;
		for(int row: rows) {
			String actual_type=table.getCellValue(row, col_type);
			if(actual_type.equalsIgnoreCase(type)) {
				browser.selectRadioButton(".id",new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), row-1);
				found=true;
			}
		}
		
		if(!found) {
			throw new ItemNotFoundException("Failed find product '"+product+"' with type '"+type+"'");
		}
		
		Browser.unregister(objs);
	}
	
	/**
	 * Get the price per the product name and type
	 * @param prodName
	 * @param prodType
	 * @return
	 * @author Lesley Wang
	 * @date Jun 12, 2012
	 */
	public String getRegisProdPrice(String prodName, String prodType) {
		String text = browser.getObjectText(".class", "Html.TR", ".text", 
				new RegularExpression("^" + prodType + "\\s*" + prodName + ".*", false));

		if (text == null) {
			throw new ErrorOnPageException("Can't Find the vehicle product " + prodName + " for " + prodType);
		}
		return RegularExpression.getMatches(text, "\\d+\\.\\d+")[0];
	}
}

