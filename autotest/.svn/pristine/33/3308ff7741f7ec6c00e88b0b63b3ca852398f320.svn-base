package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrPrivilegeLotterySelectApplicantTypePage extends LicMgrTopMenuPage {
	
	private static LicMgrPrivilegeLotterySelectApplicantTypePage _instance = null;
	
	private LicMgrPrivilegeLotterySelectApplicantTypePage() {}
	
	public static LicMgrPrivilegeLotterySelectApplicantTypePage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrPrivilegeLotterySelectApplicantTypePage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Please select Applicant Type:", false));
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Cancel", false));
	}
	
	public void clickNext(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Next", false));
	}
	
	public void selectApplicantType(String type){
		// TODO UI change, select the first one.
		if(StringUtil.isEmpty(type)){
			type = "Individual";
		}
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", new RegularExpression(type.replace("(", "\\(").replace(")", "\\)"), false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find radio button by given type "+ type);
		}
		
		String value = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		
		browser.selectRadioButton(".value", value);
	}
}
