package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IToggle;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class CallMgrResidencyStatusSelectionWidget extends DialogWidget{
	private static CallMgrResidencyStatusSelectionWidget _instance = null;
	
	protected CallMgrResidencyStatusSelectionWidget() {
	}
	
	public static CallMgrResidencyStatusSelectionWidget getInstance() {
		if(null == _instance) {
			_instance = new CallMgrResidencyStatusSelectionWidget();
		}
		return _instance;
	}
	
	public void selectNonResident() {
		RegularExpression name=new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.option|CustomerResidencyDialog-\\d+\\.resStatusIdentifierModel",false);
		browser.selectRadioButton(".name",name,".value","2");
	}
	
	public void selectResident() {
		RegularExpression name=new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.option",false);
		browser.selectRadioButton(".name",name,".value","1");
	}
	
//	private String getRadioButtonValue(String label) {
//		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", label);
//		if(objs.length < 1) throw new ItemNotFoundException("Cannot find label - " + label);
//		String value = objs[0].getProperty(".for");
//		Browser.unregister(objs);
//		
//		return value;
//	}
	
	public void selectResidency(String option) {
		Property[] p1=Property.toPropertyArray(".class","Html.TD",".text",new RegularExpression(option, false));
		IHtmlObject[] topObjs=browser.getHtmlObject(Property.atList(p1));
		if(topObjs.length<1){
			p1=Property.toPropertyArray(".class","Html.TD",".text",option);
		}
		
		Property[] p2=Property.toPropertyArray(".class","Html.INPUT.radio",".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.option|CustomerResidencyDialog-\\d+\\.resStatusIdentifierModel", false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		
		if(objs.length>0) {
			((IToggle) objs[objs.length-1]).select();
		} else {
			throw new ItemNotFoundException("Failed to find radio button for "+option);
		}
		
		Browser.unregister(objs);		
		Browser.unregister(topObjs);
	}
}
