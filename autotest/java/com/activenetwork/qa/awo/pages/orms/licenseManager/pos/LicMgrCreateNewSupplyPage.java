package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCreateNewSupplyPage extends LicMgrOrderDetailsCommonPage {
	public static final String APPS_ALL = "All";
	public static final String APPS_LICENSE_MANAGER = "License Manager";
	public static final String APPS_LICENSE_MANAGER_TOUCH = "License Manager Touch";
	public static final String APPS_VERIFONE = "verifone";

	private String prefixReg = "^SupplyProductView-[0-9]*.";
	private static LicMgrCreateNewSupplyPage instance = null;


	private LicMgrCreateNewSupplyPage() {
	}

	public static LicMgrCreateNewSupplyPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrCreateNewSupplyPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		RegularExpression reg = new RegularExpression(prefixReg+"productCode", false);
		return browser.checkHtmlObjectExists(".id", reg);
	}

	/**Retrieve the error message displays in top of page.*/
	public String getErrorMessage() {
		return browser.getObjectText(".id","NOTSET");
	}

	/**
	 * Select Product Group by given value.
	 * @param group - Product Group
	 */
	public void selectProductGroup(String group) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productGroupID", false), group);
	}

	/**De-select product group.*/
	public void deselectProductGroup() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"productGroupID", false), 0);
	}

	/**Retrieve the product id.*/
	public String getProductID() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "addConsumable");
		IHtmlObject[] spans = browser.getHtmlObject(".class", "Html.SPAN", objs[0]);
		String prodID = spans[0].text();
		Browser.unregister(objs);
		Browser.unregister(spans);
		return prodID;
	}

	/**Set product code.*/
	public void setProductCode(String code) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"productCode", false), code);
	}

	/**Set product name.*/
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"name", false), name);
	}

	/**Set product description.*/
	public void setProductDescription(String description) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"description", false), description);
	}

	public void setNumberOfPanels(String numberOfPanels) {
		browser.setTextField(".id", new RegularExpression(prefixReg + "noOfPanels", false), numberOfPanels);
	}

	/**
	 * Select Fulfillment party by given value.
	 * @param party - Fulfillment party
	 */
	public void selectFulfillParty(String party) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"fulfillmentPartyID", false), party);
	}

	/**Set Supply Cost.*/
	public void setSupplyCost(String cost) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"supplyCost", false), cost);
	}

	/**Set supply shipping Cost.*/
	public void setShippingCost(String cost) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"shippingCost", false), cost);
	}

	/**Set max Daily Order.*/
	public void setMaxDailyOrder(String num) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"maxDailyOrder", false), num);
	}

	/**Set reorderThreshold.*/
	public void setReorderThreshold(String threshold) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"reorderThreshold", false), threshold);
	}

	/**Set reorder Email.*/
	public void setReorderEmail(String email) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"reorderEmail", false), email);
	}

	/**Set quantity on hold.*/
	public void setQuantityOnHold(String qty) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"qtyOnHand", false), qty);
	}

	/***
	 * Select available to equipment type check box.
	 * @param index - 0, All; 1, Web-POS; 2, Touch-POS; 3, Verifone
	 * @param isCheck - check or un-check
	 */
	public void selectAvailToEquipType(int index, boolean isCheck) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.checkbox", ".id", new RegularExpression(prefixReg+"applications_[0-9]*", false));
		ICheckBox checkbox = (ICheckBox) objs[0];
		if(checkbox.isEnabled()) {
			if(isCheck) {
				if(!checkbox.isSelected()) {
					objs[index].click();
				}
			} else {
				if(checkbox.isSelected()) {
					objs[index].click();
				}
			}
			ajax.waitLoading();
		} else {
			throw new ErrorOnPageException("Check box is disabled.");
		}
		Browser.unregister(objs);
	}

	public void selectAvailToEquipType(Map<String, Boolean> availableApps) {
		Set<String> keySet = availableApps.keySet();
		if (keySet.contains(APPS_ALL)) {
			if (availableApps.get(APPS_ALL)) {
				selectAllApps();
			} else {
				deselectAllApps();
			}
			ajax.waitLoading();
		}

		if (keySet.contains(APPS_LICENSE_MANAGER)) {
			if (availableApps.get(APPS_LICENSE_MANAGER)) {
				selectLicenseManagerApp();
			} else {
				deselectLicenseManagerApp();
			}
		}

		if (keySet.contains(APPS_LICENSE_MANAGER_TOUCH)) {
			if (availableApps.get(APPS_LICENSE_MANAGER)) {
				selectLicenseManagerTouch();
			} else {
				deselectLicenseManagerTouch();
			}
		}

		if (keySet.contains(APPS_VERIFONE)) {
			if (availableApps.get(APPS_VERIFONE)) {
				selectVerifoneEquipType();
			} else {
				deselectVerifoneEquipType();
			}
		}
	}

	/**Select 'All' in Available to Equipment Type check box*/
	public void selectAllApps() {
		selectAvailToEquipType(0, true);
	}

	/**De-select 'All' in Available to Equipment Type check box*/
	public void deselectAllApps() {
		selectAvailToEquipType(0, false);
	}

	/**Select 'Web POS' in Available to Equipment Type check box*/
	public void selectLicenseManagerApp() {
		selectAvailToEquipType(1, true);
	}

	/**De-select 'Web POS' in Available to Equipment Type check box*/
	public void deselectLicenseManagerApp() {
		selectAvailToEquipType(1, false);
	}

	/**Select 'Touch POS' in Available to Equipment Type check box*/
	public void selectLicenseManagerTouch() {
		selectAvailToEquipType(2, true);
	}

	/**De-select 'Touch POS' in Available to Equipment Type check box*/
	public void deselectLicenseManagerTouch() {
		selectAvailToEquipType(2, false);
	}

	/**Select 'Verifone' in Available to Equipment Type check box*/
	public void selectVerifoneEquipType() {
		selectAvailToEquipType(3, true);
	}

	/**De-select 'Verifone' in Available to Equipment Type check box*/
	public void deselectVerifoneEquipType() {
		selectAvailToEquipType(3, false);
	}

	/**
	 * Verify whether the supply order status field is disable or not.
	 * @return
	 */
	public boolean isSupplyOrderStatusDisable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefixReg+"active", false));
		boolean toReturn  = false;
		if(objs[0].getProperty(".disabled").equals("true")) {
			toReturn  = true;
		}
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Retrieve the product status, active or inactive
	 * @return
	 */
	public String getProductStatus() {
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression(prefixReg+"active", false));
		ISelect pStatus = (ISelect) objs[0];

		String status = pStatus.getSelectedText();
		Browser.unregister(objs);
		return status;
	}

	/**Click OK button.*/
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
		ajax.waitLoading();
	}

	/**Click Cancel button.*/
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}

	/**Click Apply button to apply changes.*/
	public void clickApply(){
		browser.clickGuiObject(".class","Html.A",".text","Apply");
		ajax.waitLoading();
	}
	
	public void selectStauts(String status){
		browser.selectDropdownList(".id", new RegularExpression("SupplyProductView-\\d+\\.active",false), status);
	}

}
