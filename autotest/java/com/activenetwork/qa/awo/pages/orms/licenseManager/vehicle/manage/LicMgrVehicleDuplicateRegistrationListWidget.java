package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

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
 * @author pzhu
 * @Date Jun 4, 2012
 */
public class LicMgrVehicleDuplicateRegistrationListWidget extends DialogWidget {
	private static LicMgrVehicleDuplicateRegistrationListWidget _instance = null;

	protected LicMgrVehicleDuplicateRegistrationListWidget() {
		super("Registration Products");
	}

	public static LicMgrVehicleDuplicateRegistrationListWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrVehicleDuplicateRegistrationListWidget();
		}
		return _instance;
	}

			
	public void selectRegistrationProduct(String product){
		RegularExpression reg = new RegularExpression("reg_list", false);
		IHtmlObject[] productTable = browser.getTableTestObject(".id", reg);			 
		IHtmlTable table = (IHtmlTable) productTable[productTable.length-1];
				
		int prdColumn = table.findColumn(0, "Product"); 
		int rowNums[] = table.findRows(prdColumn, product);
		
		if(rowNums.length <1){
			throw new ErrorOnDataException("Could not find product:"+product+" from duplicate registration list widget");
		}
		
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), (rowNums.length-1));
		
		Browser.unregister(productTable);
	}
	
	public List<String> getAllRegistrationInstance(){
		List<String> prdList;
		RegularExpression reg = new RegularExpression("reg_list", false);
		IHtmlObject[] productTable = browser.getTableTestObject(".id", reg);			 
		IHtmlTable table = (IHtmlTable) productTable[productTable.length-1];
		
		prdList = table.getColumnValues(table.findColumn(0, "Product"));
		
		Browser.unregister(productTable);
		return prdList;
	}
}
