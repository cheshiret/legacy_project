package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 28, 2014
 */
public class LicMgrPrivilegeOrderItemsPage extends LicMgrPrivilegeOrderDetailsPage{
	private static LicMgrPrivilegeOrderItemsPage _instance = null;

	protected LicMgrPrivilegeOrderItemsPage() {}

	public static LicMgrPrivilegeOrderItemsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeOrderItemsPage();
		}

		return _instance;
	}

	protected Property[] licenceNum(String licenceNum){
		return Property.concatPropertyArray(a(), ".text", licenceNum);
	}

	protected Property[] licenceNum(){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("\\d+", false));
	}
	
	protected Property[] privOrderItemsListTable(){
		return Property.concatPropertyArray(table(), ".id", "privilegeOrderItemsList");
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(privOrderItemsListTable());
	}

	public void clickLicenseNum(String licenceNum){
		browser.clickGuiObject(Property.atList(privOrderItemsListTable(), licenceNum(licenceNum)));
	}
	
	public void clickLilcenceNum(){
		browser.clickGuiObject(Property.atList(privOrderItemsListTable(), licenceNum()));
	}
}
