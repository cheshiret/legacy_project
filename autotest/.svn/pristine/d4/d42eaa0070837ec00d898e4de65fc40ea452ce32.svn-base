package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.pages.orms.OrmsPage;
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
 * @Date  Apr 16, 2014
 */
public class OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage extends OrmsPage {
	private static OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage _instance = null;
	
	private OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage() {}
	
	public static OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage getInstance() {
		if(_instance == null) _instance = new OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(program());
	}
	
	private Property[] program() {
		return Property.toPropertyArray(".id", new RegularExpression("SectionData-\\d+\\.program", false));
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	public void selectProgram(String program) {
		browser.selectDropdownList(program(), program);
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
}
