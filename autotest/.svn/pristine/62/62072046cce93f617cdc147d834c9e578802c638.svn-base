/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Mar 2, 2012
 */
public class CallMgrVehicleRegistrationWidget extends DialogWidget {
	private static CallMgrVehicleRegistrationWidget _instance = null;

	protected CallMgrVehicleRegistrationWidget() {
	}

	public static CallMgrVehicleRegistrationWidget getInstance() {
		if (_instance == null) {
			_instance = new CallMgrVehicleRegistrationWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("(Registration |Title |Inspection )Product.*", false));
		return flag1 && flag2;
	}
	
	public List<String> getProductList(){
		List<String> productList = new ArrayList<String>();
		IHtmlObject[] productTable = browser.getTableTestObject(".id", new RegularExpression("reg_list|grid_\\d+", false));		 
		IHtmlTable table = (IHtmlTable) productTable[0];
		
		int count = table.rowCount();
		for(int i=1; i<count; i++){
			productList.add(table.getCellValue(i, 2));
		}
		return productList;		
	}

	public void selectRegistrationProduct(String type,String product){
		IHtmlObject[] productTable = browser.getTableTestObject(".id", new RegularExpression("reg_list|grid_\\d+", false));		 
		IHtmlTable table = (IHtmlTable) productTable[0];
		
		int row = -1;
		int count = table.rowCount();
		for(int i=1; i<count; i++){
			String actType = table.getCellValue(i, 1);
			String actProduct = table.getCellValue(i, 2);
			
			if(actType.equals(type) && actProduct.equals(product)){
				row = i;			
				break;
			}
		}
		if(row <1){
			throw new ErrorOnDataException("Could not find product:"+product+" from registration widget");
		}		
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), row-1);
		
		Browser.unregister(productTable);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("OK", false),1);
	}
}
