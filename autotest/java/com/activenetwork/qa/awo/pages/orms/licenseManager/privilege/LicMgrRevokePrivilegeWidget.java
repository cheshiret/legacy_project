package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author ssong
 * @date Feb 12, 2011
 */
public class LicMgrRevokePrivilegeWidget extends DialogWidget{
	private static LicMgrRevokePrivilegeWidget _instance = null;
	
	protected LicMgrRevokePrivilegeWidget() {
		super("Add Suspension");
	}
	
	public static LicMgrRevokePrivilegeWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrRevokePrivilegeWidget();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		boolean exist=super.exists();
		return exist && browser.checkHtmlObjectExists(".id",new RegularExpression("PrivilegeInstanceRevocable-\\d+.toBeRevoked",false));
	}
	
	public void selectAllPrivilegedsToBeRevoked() {
		IHtmlObject[] objs = browser.getCheckBox(".id",new RegularExpression("PrivilegeInstanceRevocable-\\d+.toBeRevoked",false));
		for(int i=0;i<objs.length;i++){
			((ICheckBox)objs[i]).select();
		}
		Browser.unregister(objs);
	}
	
	private IHtmlObject[] getRevokeTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Revoke\\s*Status\\s*License Year\\s*Privilege.*", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find the revoke privilege table");
		}
		return objs;
	}
	
	private String getRevokePrivilegeTextFieldValue(int index, IHtmlObject top) {
		return browser.getTextFieldValue(Property.toPropertyArray(".className", "inputwithrubylabel"), index, top);
	}
	
	private int getRevokeTableRowIndex(PrivilegeInfo privilege) {
		IHtmlObject[] objs = this.getRevokeTables();
		IHtmlTable table = (IHtmlTable)objs[objs.length - 1];
		IHtmlObject[] rows = browser.getHtmlObject(".class", "Html.tr", table);
		int rowIndex = -1;
		for (int i = 1; i < rows.length; i++) {
			boolean result = privilege.name.equalsIgnoreCase(this.getRevokePrivilegeTextFieldValue(2, rows[i]));
			if (StringUtil.notEmpty(privilege.status)) {
				result &= privilege.status.equalsIgnoreCase(this.getRevokePrivilegeTextFieldValue(0, rows[i]));
			}
			if (StringUtil.notEmpty(privilege.licenseYear)) {
				result &= privilege.licenseYear.equalsIgnoreCase(this.getRevokePrivilegeTextFieldValue(1, rows[i]));
			}
			if (result) {
				rowIndex = i;
				break;
			}
		}
		
		Browser.unregister(objs, rows);
		return rowIndex;
	}
	
	public void selectPrivilegeToBeRevoked(PrivilegeInfo privilege) {
		if (StringUtil.isEmpty(privilege.name))
			privilege.name = privilege.purchasingName;
		logger.info("Select privilege, name:"+privilege.name+", license year:"+privilege.licenseYear);
		int index = this.getRevokeTableRowIndex(privilege) - 1;
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeInstanceRevocable-\\d+.toBeRevoked",false), index);
	}
}
