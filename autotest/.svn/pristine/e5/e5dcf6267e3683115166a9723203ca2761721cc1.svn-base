package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public abstract class LicMgrConsumableProductCommonPage extends LicMgrProductCommonPage {
	
	/**
	 * Select a consumable product type
	 * @param consumableType - POS Sale/Donation/Subscription Sale
	 */
	public void selectOrderType(String consumableType) {
		browser.selectDropdownList(".id", new RegularExpression("ConsumableProductView-\\d+\\.productSubCategory", false), consumableType);
		ajax.waitLoading();
	}
	
	/**
	 * Set consumable product code
	 * @param code
	 */
	public void setCode(String code) {
		browser.setTextField(".id", new RegularExpression("ConsumableProductView-\\d+\\.productCode", false), code, true);
	}
	
	/**
	 * Set consumable product name
	 * @param name
	 */
	public void setName(String name) {
		browser.setTextField(".id", new RegularExpression("ConsumableProductView-\\d+\\.name", false), name, true);
	}
	
	/**
	 * Set description
	 * @param descrption
	 */
	public void setDescription(String descrption) {
		browser.setTextField(".id", new RegularExpression("ConsumableProductView-\\d+\\.description", false), descrption, true);
	}
	
	/**
	 * Select consumable product group
	 * @param group
	 */
	public void selectProductGroup(String group) {       
		browser.selectDropdownList(".id", new RegularExpression("ConsumableProductView-\\d+\\.productGroup", false), group);
	}
	
	/**
	 * Select an option for Variable Price - Yes or No
	 * @param option
	 */
	public void selectVariablePrice(String option) {
		browser.selectDropdownList(".id", new RegularExpression("ConsumableProductView-\\d+\\.variablePrice", false), option);
	}
	
	/**
	 * Get consumable product id
	 * @return
	 */
	public String getProductID() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^Product ID", false));
		
		IHtmlObject objs[] = browser.getHtmlObject(property);
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Product ID object.");
		}
		String productID = objs[0].getProperty(".text").split("Product ID")[1].trim();
		return productID;
	}
	
	/**
	 * Select product status
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("ConsumableProductView-\\d+\\.active", false), status);
	}
	
	/**
	 * Get consumable product status
	 * @return
	 */
	public String getStatus() {
		return browser.getDropdownListValue(".id", new RegularExpression("ConsumableProductView-\\d+\\.active", false), 0);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	/**
	 * Set detail consumable product info to create a new product
	 * @param consumable
	 */
	public void setupConsumableProductInfo(ConsumableInfo consumable) {
		this.selectOrderType(consumable.orderType);
		this.setCode(consumable.code);
		this.setName(consumable.name);
		this.setDescription(consumable.description);
		this.selectProductGroup(consumable.productGroup);
		this.selectVariablePrice(consumable.variablePrice ? "Yes" : "No");
	}
}
