package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 4, 2014
 */
public class OrmsPOSAddUnitPriceWidget extends DialogWidget {
	
	private static OrmsPOSAddUnitPriceWidget _instance = null;
	
	private OrmsPOSAddUnitPriceWidget() {
		super("Add Unit Price");
	}
	
	public static OrmsPOSAddUnitPriceWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsPOSAddUnitPriceWidget();
		}
		
		return _instance;
	}
	
	private Property[] inStateUnitPrice() {
		return Property.toPropertyArray(".id", "POSProductSetupView.inStateUnitPriceStr");
	}
	
	private Property[] outOfStateUnitPrice() {
		return Property.toPropertyArray(".id", "POSProductSetupView.outOfStateUnitPriceStr");
	}
	
	public void setInStateUnitPrice(String price) {
		browser.setTextField(inStateUnitPrice(), price);
	}
	
	public void setOutOfStateUnitPrice(String price) {
		browser.setTextField(outOfStateUnitPrice(), price);
	}
	
	public void setUnitPrices(String inStatePrice, String outOfStatePrice) {
		this.setInStateUnitPrice(inStatePrice);
		this.setOutOfStateUnitPrice(outOfStatePrice);
	}
}
