/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrCreateNewPriviledgePage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCreateNewPriviledgePage extends
		LicMgrPrivilegeProductCommonPage {

	private static LicMgrCreateNewPriviledgePage instance = null;

	private LicMgrCreateNewPriviledgePage() {
	}

	public static LicMgrCreateNewPriviledgePage getInstance() {
		if (instance == null) {
			instance = new LicMgrCreateNewPriviledgePage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "formViewprivilegeUI-new");
	}

	public void setCode(String code) {
		browser.setTextField(prdCode(), code);
	}

	public boolean isPrdCodeExist() {
		return browser.checkHtmlObjectExists(prdCode());
	}
	
	public void selectProductGroup(String group) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeProductView-\\d+\\.productGroup", false);
		browser.selectDropdownList(".id", regx, group);
	}

	/**
	 * Set up all privilege detail information to create a new privilege
	 * 
	 * @param privilege
	 */
	@Override
	public void setupPrivilegeInfo(PrivilegeInfo privilege) {
		setCode(privilege.code);
		selectProductGroup(privilege.productGroup);
		ajax.waitLoading();
		super.setupPrivilegeInfo(privilege);
	}

	public String getHuntsRequiredValue() {
		String value = browser.getDropdownListValue(".id",
				new RegularExpression(
						"PrivilegeProductView-\\d+\\.applicationOnly", false));
		return value;
	}

}
