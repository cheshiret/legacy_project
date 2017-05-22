package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Document Fulfillment common page in License Manager. Admin -> Document Fulfillment
 * 
 * @author Lesley Wang
 * @Date  Sep 10, 2013
 */
public class LicMgrDocumentFulfillmentCommonPage extends
		LicMgrCommonTopMenuPage {

	private static LicMgrDocumentFulfillmentCommonPage instance=null;
	
	protected LicMgrDocumentFulfillmentCommonPage(){}
	
	public static LicMgrDocumentFulfillmentCommonPage getInstance(){
		if(instance == null){
			instance=new LicMgrDocumentFulfillmentCommonPage();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] docFulfillmentMainTabDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "DocumentFulfillmentTabs");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.docFulfillmentMainTabDiv());
	}
}
