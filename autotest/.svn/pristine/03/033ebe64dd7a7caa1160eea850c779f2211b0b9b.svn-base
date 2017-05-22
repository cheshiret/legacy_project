package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrSelectCustomerClassWidget extends DialogWidget {
	private static LicMgrSelectCustomerClassWidget _instance=null;
	
	protected LicMgrSelectCustomerClassWidget() {
		
	}
	
	public static LicMgrSelectCustomerClassWidget getInstance() {
		if(null==_instance) {
			_instance=new LicMgrSelectCustomerClassWidget();
		}
		
		return _instance;
	}
	
	public void selectIndividual() {
		selectCustomerClass("Individual");
	}
	
	public void selectOrganization() {
		selectCustomerClass("Organization");
	}
	
	public void selectCustomerClass(String type) {
		int classType=-1;
		
		if(type.equalsIgnoreCase("Individual")) {
			classType=0;
		} else if(type.equalsIgnoreCase("Business") || type.equalsIgnoreCase("Outfitter")) { //Lesley[20130926]: Outfitter for SK Contract
			classType=1;
		} else if(type.equalsIgnoreCase("Trapper")) {
			classType=2;
		} else {
			throw new ItemNotFoundException("Unknown class type: "+type);
		}
		
		RegularExpression idPattern=new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue",false);
		browser.selectRadioButton(".id",idPattern,classType);
	}
}
