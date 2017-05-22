package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrSelectFulfillmentMethodWidget extends DialogWidget {
	private static LicMgrSelectFulfillmentMethodWidget _instance = null;
	
	protected LicMgrSelectFulfillmentMethodWidget() {
		
	}
	
	public static LicMgrSelectFulfillmentMethodWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSelectFulfillmentMethodWidget();
		}
		
		return _instance;
	}
	
	protected Property[] selectedInvFulfillMethodTable() {
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("InventoryPrompt-\\d+\\.selectedInvFullfillMethod", false));
	}
	
	protected Property[] selectInvQty() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("InventoryPrompt-\\d+\\.selectedInvQty", false));
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(selectedInvFulfillMethodTable());
	}
	
	private IHtmlObject[] getInvFulfillMethodTable() {
		IHtmlObject[] objs = browser.getHtmlObject(selectedInvFulfillMethodTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find inventory fulfillment method on page.");
		return objs;
	}
	
	public void selectInvFulfillMethod(String methodName) {
		IHtmlObject[] objs = getInvFulfillMethodTable();
		ITable table = (ITable)objs[0];
		int index = -1;
		for(int i=0;i<table.rowCount();i++) {
			String content = table.getCellValue(i, 0);
			if(content.contains(methodName)) {
				index = i;
				break;
			}
		}
		if(index<0)
			throw new ItemNotFoundException("Could not find fulfillment method "+methodName);
		browser.selectRadioButton(".id", new RegularExpression("InventoryPrompt-\\d+\\.selectedInvFullfillMethod"+index, false));
		ajax.waitLoading();
		Browser.unregister(objs);
	}
	
	public boolean isInvNumTextFieldExist() {
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("HFInventoryView-\\d+\\.inventoryNumber", false));
	}
	
	private int getInvNumTextFieldNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("HFInventoryView-\\d+\\.inventoryNumber", false));
		int i = objs.length;
		if(i<0)
			throw new ItemNotFoundException("Inventory Number text field was not found on page.");
		Browser.unregister(objs);
		return i;
	}
	
	/**
	 * For test case which need to verify error message, we did not need to validate input numbers
	 * @param numbers
	 */
	public void setInventoryNums(String numbers) {
		setInventoryNums(numbers, true);
	}
	
	public void setInventoryNums(String numbers, boolean validateNums) {
		String[] number = numbers.split(",");
		
		if(validateNums) {
			int textNum = this.getInvNumTextFieldNum();
			if(number.length != textNum)
				throw new ErrorOnDataException("Un-matched data from your numbers:"+number.length+" and text fields:"+textNum+" on page.");
		}
		
		for(int i=0;i<number.length;i++)
			browser.setTextField(Property.getPropertyArray(".class", "Html.Text", ".id", new RegularExpression("HFInventoryView-\\d+\\.inventoryNumber", false)), number[i], true, i);
	}
	
	public List<String> getSelectedInvQtyList() {
		return browser.getDropdownElements(".id", new RegularExpression("InventoryPrompt-\\d+\\.selectedInvQty", false));
	}
	
	public void selectInvQty(String qty) {
		browser.selectDropdownList(selectInvQty(), qty);
	}
}
