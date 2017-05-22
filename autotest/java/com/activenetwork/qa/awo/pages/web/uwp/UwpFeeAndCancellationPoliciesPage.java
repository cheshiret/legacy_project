package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.util.Property;

public class UwpFeeAndCancellationPoliciesPage extends UwpHeaderBar{
	static class SingletonHolder {
		protected static UwpFeeAndCancellationPoliciesPage _instance = new UwpFeeAndCancellationPoliciesPage();
	}

	protected UwpFeeAndCancellationPoliciesPage() {
	}

	public static UwpFeeAndCancellationPoliciesPage getInstance() {
		return SingletonHolder._instance;
	}
	
	private Property[] pageTitle(){
		return Property.concatPropertyArray(td(), ".className", "PageTitle", ".text", "Fees & Cancellation Policies" );
	}
	
	private Property[] commonExtendedPage(){
		return Property.concatPropertyArray(div(), ".id", "commonExtendedPage");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(pageTitle());
	}
	
	public String getCommonExtendedMes(){
		return browser.getObjectText(commonExtendedPage());
	}
}
