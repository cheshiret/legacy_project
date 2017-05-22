package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 3, 2013
 */
public class LicMgrChooseHowToReleaseInventoryWidget extends DialogWidget {
	private static LicMgrChooseHowToReleaseInventoryWidget _instance = null;
	
	private LicMgrChooseHowToReleaseInventoryWidget() {
//		super("Choose how to release inventory ");
		super("Choose how to release inventory");//Jane[2014-5-15]:Updated by Jane, no space at the end
	}
	
	public static LicMgrChooseHowToReleaseInventoryWidget getInstance() {
		if(_instance == null) _instance = new LicMgrChooseHowToReleaseInventoryWidget();
		return _instance;
	}
	
	private Property[] doNotReturnRadioButton() {
		return Property.toPropertyArray(".id", new RegularExpression("ReturnTypeHolder-\\d+\\.returnType", false), ".value", "DO_NOT_RETURN");
	}
	
	private Property[] returnToRadiotButton() {
		return Property.toPropertyArray(".id", new RegularExpression("ReturnTypeHolder-\\d+\\.returnType", false), ".value", "RETURN_TO_STORE");
	}
	
	private Property[] returnToTextFiled() {
		return Property.toPropertyArray(".id", new RegularExpression("StoreReleaser-\\d+\\.storeStr", false));
	}
	
	public void selectDoNotReturn() {
		browser.selectRadioButton(doNotReturnRadioButton(), 0);
	}
	
	public void selectReturnTo() {
		browser.selectRadioButton(returnToRadiotButton(), 0);
	}
	
	public void setReturnToStore(String store) {
		browser.setTextField(returnToTextFiled(), store);
	}
	
	public void selectAllReturnTo() {
		IHtmlObject[] objs = browser.getRadioButton(returnToRadiotButton());
		for(int i=0;i<objs.length;i++) {
			browser.selectRadioButton(returnToRadiotButton(), i);
		}
	}
}
