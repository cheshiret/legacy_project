package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Privacy Policy page for RA site. 
 * @Preconditions:
 * @SPEC: 
 * 
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jul 31, 2013
 */
public class RAPrivacyPolicyPage extends UwpPage {
	static class SingletonHolder {
		protected static RAPrivacyPolicyPage _instance = new RAPrivacyPolicyPage();
	}

	protected RAPrivacyPolicyPage() {
	}

	public static RAPrivacyPolicyPage getInstance() {
		return SingletonHolder._instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.getPageContentDivProp()) &&
				browser.checkHtmlObjectExists(this.getPrivacyPolicyDivProp());
	}

	/** Page Object Property Definition Begin*/
	protected Property[] getPageContentDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "mark_page");
	}
	
	protected Property[] getPrivacyPolicyDivProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "GearStore", ".text", "PRIVACY POLICY");
	}
	/** Page Object Property Definition END*/
}
