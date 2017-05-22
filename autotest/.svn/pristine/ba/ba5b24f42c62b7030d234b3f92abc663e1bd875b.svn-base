package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is the Product Group list page. The page will be shown after select Contract - Configuration in Admin Manager
 * 
 * @author Lesley Wang
 * @Date  July 15, 2013
 */
public class AdmMgrProductGroupListPage extends AdminManagerPage {
	static private AdmMgrProductGroupListPage _instance = null;

	protected AdmMgrProductGroupListPage() {
	}

	static public AdmMgrProductGroupListPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrProductGroupListPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.getPrdGrpListTableProp());
	}
	
	protected Property[] getPrdGrpListTableProp() {
		return Property.toPropertyArray(".class", "Html.Table", ".id", "productgroupList_LIST");
	}
	
	protected Property[] getPrdGrpCatListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "ProductGroupSearchCriteria.prdGrpCatId");
	}
	
	protected Property[] getPrdGrpNameTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "ProductGroupSearchCriteria.prdGrpName");
	}
	
	protected Property[] getPrdGrpStatusListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "ProductGroupSearchCriteria.entityStatusId");
	}
	
	protected Property[] getPrdGrpSearchLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Search");
	}
	
	protected Property[] getPrdGrpAddNewLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add New");
	}
	
	public void selectPrdGrpCategory(String value) {
		browser.selectDropdownList(this.getPrdGrpCatListProp(), value, true);
	}
	
	public void setPrdGrpName(String value) {
		browser.setTextField(this.getPrdGrpNameTextFieldProp(), value, true, 0);
	}
	
	public void selectPrdGrpShowStatus(int index) {
		browser.selectDropdownList(this.getPrdGrpStatusListProp(), index, true);
	}
	
	public void selectPrdGrpShowStatus(String value) {
		browser.selectDropdownList(this.getPrdGrpStatusListProp(), value, true);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(this.getPrdGrpSearchLinkProp());
	}
	
	/** Search product  group by category, name and show statue */
	public void searchPrdGrp(String prdGrpCat, String prdGrpName, String showStatus) {
		this.selectPrdGrpCategory(prdGrpCat);
		this.setPrdGrpName(prdGrpName);
		if (StringUtil.isEmpty(showStatus))
			this.selectPrdGrpShowStatus(0);
		else 
			this.selectPrdGrpShowStatus(showStatus);
		this.clickSearch();
		this.waitLoading();
	}
	
	public void clickFirstPrdGrp() {
		browser.clickGuiObject(Property.atList(this.getPrdGrpListTableProp(), 
				Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("\\d+", false))), true, 0);
	}
}
