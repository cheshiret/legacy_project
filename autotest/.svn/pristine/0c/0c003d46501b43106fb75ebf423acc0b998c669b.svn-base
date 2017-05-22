package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivilegeQuantityControlCommonWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 8, 2011
 */
public class LicMgrPrivilegeAddQuantityControlWidget extends LicMgrPrivilegeQuantityControlCommonWidget {
	
	private static LicMgrPrivilegeAddQuantityControlWidget _instance = null;
	
	private LicMgrPrivilegeAddQuantityControlWidget() {
		super("Add Product Quantity Controls Popup");
	}
	
	public static LicMgrPrivilegeAddQuantityControlWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeAddQuantityControlWidget();
		}
		
		return _instance;
	}
	
	/**
	 * Set quantity control info
	 * @param quantityControl
	 */
	public void setQuantityControlInfo(QuantityControlInfo quantityControl){
		this.selectLocationClass(quantityControl.locationClass);
		this.selectMultiSalesAllowance(quantityControl.multiSalesAllowance);
		if(this.checkMaxQuantityPerTranIsExists()) {
			this.setMaxQuantityPerTransaction(quantityControl.maxQuantityPerTran);
		}
		if(this.checkMaxAllowedIsExists()) {
			this.setMaxAllowed(quantityControl.maxAllowed);
		}
		if(this.checkMinQuantityPerTranIsExists()) {
			this.setMinQuantityPerTransaction(quantityControl.minQuantityPerTran);
		}
		if(this.checkReplacementMaxAllowedExist()) { // Lesley[20130829]: update due to no replacement max allowed for the privilege with Inventory product group
			this.setReplacementMaxAllowed(quantityControl.replacementMaxAllowed);
		}
	}
	
	/**
	 * The status initial value should be "Active"
	 * @return
	 */
	public String getInitialStatus() {
		return browser.getDropdownListValue(".id", "codebaseDropdown", 0);
	}
	
	/**
	 * Check whether the Status is editable or not
	 * @return
	 */
	public boolean checkQuantityControlStatusIsEditable() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "codebaseDropdown");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Status drop down list object.");
		}
		
		boolean property = Boolean.parseBoolean(objs[0].getProperty("isDisabled"));
		
		Browser.unregister(objs);
		return property ? false:true;
	}
	
	/**
	 * Verify whether the Location Class's options are displayed with the "All" option as the first option in the list,
	 * followed by the Location Classes defined for the Contract sorted by the code in ascending order
	 */
	public void verifyLocationClassDropdownList() {
		List<String> options = browser.getDropdownElements(".id", new RegularExpression("ProductQuantityControlView-\\d+\\.locationClassID", false));
		if(!options.get(0).equals("All")) {
			throw new ErrorOnPageException("The first option should be 'All'.");
		}
		options.remove(0);
		for(int i = 0; i < options.size() - 1; i++) {
			if(Integer.parseInt(options.get(i).split("-")[0].trim()) > Integer.parseInt(options.get(i + 1).split("-")[0].trim())) {
				throw new ErrorOnPageException("Location Class should be sorted in Ascending order.");
			}
		}
	}
}
