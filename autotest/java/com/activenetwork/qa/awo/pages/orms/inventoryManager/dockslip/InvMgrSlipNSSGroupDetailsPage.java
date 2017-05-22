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
 * @Date  Sep 10, 2012
 */
public class InvMgrSlipNSSGroupDetailsPage extends InvMgrSlipDetailsCommonPage {
	
	private static InvMgrSlipNSSGroupDetailsPage _instance = null;
	
	private InvMgrSlipNSSGroupDetailsPage(){}
	
	public static InvMgrSlipNSSGroupDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrSlipNSSGroupDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.LI", ".className", "selected", ".text", "NSS Groups"));
	}
}
