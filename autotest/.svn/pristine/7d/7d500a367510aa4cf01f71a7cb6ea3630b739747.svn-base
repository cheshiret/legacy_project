package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import java.util.List;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
/**
 * This page occured just after click 'Add New' in fee schedule search/list page 
 * @author Phoebe
 */
public class LicMgrSelectFeeTypePage extends LicMgrFeeScheduleCommonPage {
	static private LicMgrSelectFeeTypePage _instance = null;

	protected LicMgrSelectFeeTypePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrSelectFeeTypePage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrSelectFeeTypePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "finance.fee.detail.form");
	}
	
	protected Property[] productCategoryDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "prd_grp_cat_id");
	}
	
	protected Property[] feeTypeDropdownListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "fee_type");
	}
	
	protected Property[] cancelBtn() {
		return Property.concatPropertyArray(this.a(), ".text","Cancel");
	}

	public void selectProductCategory(String category){
	  browser.selectDropdownList(this.productCategoryDropDownList(),category);
	}

	public void selectFeeType(String feeType) {
		browser.selectDropdownList(this.feeTypeDropdownListProp(), feeType);
	}

	public List<String> getAllProductCategory(){
		return browser.getDropdownElements(this.productCategoryDropDownList());
	}
	
	public List<String> getAllFeeType(){
		return browser.getDropdownElements(this.feeTypeDropdownListProp());
	}
	
	public void setUpProductCatAndFeeType(String category, String feeType){
		this.selectProductCategory(category);
		this.selectFeeType(feeType);
	}
}
