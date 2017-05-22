package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 11, 2012
 */
public class InvMgrSlipDetailsPage extends InvMgrSlipDetailsCommonPage {
	
	private static InvMgrSlipDetailsPage _instance = null;
	
	private InvMgrSlipDetailsPage() {}
	
	public static InvMgrSlipDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrSlipDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.LI", ".className", "selected", ".text", "Slips"));
	}
}
