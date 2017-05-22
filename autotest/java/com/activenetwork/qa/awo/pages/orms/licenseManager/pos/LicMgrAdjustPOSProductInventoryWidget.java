/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: The page class describes the widget 'Adjust POS Product Inventory'.
 * The widget opens after click Adjust button on Supplies Product list page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Sep 1, 2012
 */
public class LicMgrAdjustPOSProductInventoryWidget extends DialogWidget {
	private static LicMgrAdjustPOSProductInventoryWidget _instance = null;
	
	private LicMgrAdjustPOSProductInventoryWidget() {
		super("Adjust POS Product Inventory");
	}
	
	public static LicMgrAdjustPOSProductInventoryWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAdjustPOSProductInventoryWidget();
		}
		
		return _instance;
	}
	
	public String getQuantityOnHand() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression("SupplyProductView-\\d+\\.qtyOnHand", false));
	}
	
	public void setDateSupplyReceived(String date) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyProductView-\\d+\\.dateSupplyReceived_ForDisplay", false),  date);
	}
	
	public void selectAdjustmentType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"SupplyProductView-\\d+\\.adjustmentType", false),  type);
	}
	
	public void setAdjustmentQuantity(String quantity) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyProductView-\\d+\\.newAdjQuantity", false),  quantity);
	}
	
	public void setAdjustmentNotes(String notes) {
		browser.setTextArea(".id", new RegularExpression(
				"SupplyProductView-\\d+\\.adjNotes", false),  notes);
	}
	
	public void clickProductCodeTextField() {
		browser.clickGuiObject(".class", "Html.Input.text", ".id", new RegularExpression(
				"SupplyProductView-\\d+\\.productCode", false));
	}
	
	public void setupAdjustmentInfo(SupplyInfo supply) {
		this.setDateSupplyReceived(supply.supplyReceivedDate);
		this.clickProductCodeTextField(); // close date picker
		this.selectAdjustmentType(supply.adjustmentType);
		this.setAdjustmentQuantity(supply.adjustmentQuantity);
		this.setAdjustmentNotes(supply.adjustmentNotes);
	}
}
