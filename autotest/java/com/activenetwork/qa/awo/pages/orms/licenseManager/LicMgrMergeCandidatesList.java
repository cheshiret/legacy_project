/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Apr 20, 2012
 */
public class LicMgrMergeCandidatesList extends LicMgrCommonTopMenuPage {
	private static LicMgrMergeCandidatesList _instance = null;
	
	public static LicMgrMergeCandidatesList getInstance() {
		if (null == _instance) {
			_instance = new LicMgrMergeCandidatesList();
		}

		return _instance;
	}

	protected LicMgrMergeCandidatesList() {

	}
	@Override
	public boolean exists() {
		return checkLastTrailValueIs("Merge Candidates List");
		
//		return browser.checkHtmlObjectExists(".id", "CustomerProfileView.list");//Quentin[20140331] the last tail is blank which is defect
		
//		return (browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Merge Candidates List"));
//				&&(browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "QA"))); //james: back page marker, doesn't work for none QA environment
		}
	
	public void selectCustomerFirstNumer()	{
		browser.selectRadioButton(".id",new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), 0);
	
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
}
