package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrResidencyStatusSelectionWidget extends DialogWidget{
	private static LicMgrResidencyStatusSelectionWidget _instance = null;
	
	protected LicMgrResidencyStatusSelectionWidget() {
		super("Residency Status Selection");
	}
	
	public static LicMgrResidencyStatusSelectionWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrResidencyStatusSelectionWidget();
		}
		return _instance;
	}
	
	public void selectNonResident() {
		RegularExpression name=new RegularExpression("CustomerResidencyDialog-\\d+\\.resStatusIdentifierModel",false);
		browser.selectRadioButton(".name",name,".value","2");
	}
	
	public void selectResident() {
		RegularExpression name=new RegularExpression("CustomerResidencyDialog-\\d+\\.resStatusIdentifierModel",false);
		browser.selectRadioButton(".name",name,".value","1");
	}
	
	public void selectResident(String residentStatus) {
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", residentStatus);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.option", false));
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public boolean isAdditionalProofDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("CustomerResidencyDialog-\\d+\\.additionalProof", false));
	}
	
	public void selectAdditionalProof(String proof){
		browser.selectDropdownList(".id",new RegularExpression("CustomerResidencyDialog-\\d+\\.additionalProof",false),proof);
	}
}
