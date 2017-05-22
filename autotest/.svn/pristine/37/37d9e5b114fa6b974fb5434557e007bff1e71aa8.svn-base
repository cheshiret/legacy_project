package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCorrectDuplicateVehicleTitleProductWidget extends DialogWidget{
	private static LicMgrCorrectDuplicateVehicleTitleProductWidget _instance = null;

	protected LicMgrCorrectDuplicateVehicleTitleProductWidget() {

	}

	public static LicMgrCorrectDuplicateVehicleTitleProductWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrCorrectDuplicateVehicleTitleProductWidget();
		}
		return _instance;
	}

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "vehicleTitleProducts");
		return flag1 && flag2;
	}
	
	public void selectRegistrationProduct(String type, String product){
		IHtmlObject[] productTable = browser.getTableTestObject(".id", "vehicleTitleProducts");	
		
		if(productTable.length<1 ){
			throw new ItemNotFoundException("Did not found product list page.");
		}	
		IHtmlTable table =  (IHtmlTable) productTable [productTable.length-1];

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
}
