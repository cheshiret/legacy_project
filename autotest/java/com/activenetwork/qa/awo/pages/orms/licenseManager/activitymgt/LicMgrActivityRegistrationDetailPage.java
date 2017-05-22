/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCustomerMgrTabPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: this page is Activity Registration Information detail page:
 * https://orms-torqa3.dev.activenetwork.com/HFCustomerMgrPage.page?_PageParam.privInstID=328712778#/ActivityRegistration
 * @author sborjigin
 * @Date  Jul 3, 2014
 */
public class LicMgrActivityRegistrationDetailPage extends LicMgrCustomerMgrTabPage {
	
	private static LicMgrActivityRegistrationDetailPage _instance = null;
	
	protected LicMgrActivityRegistrationDetailPage(){
		
	}
	public static LicMgrActivityRegistrationDetailPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrActivityRegistrationDetailPage();
		}
		
		return _instance;
	}
	Property [] activityRegistrasionDetailForm(){
		return Property.toPropertyArray(".id","ActivityRegistrationDetailsForm",".class","Html.TABLE");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(activityRegistrasionDetailForm());
	}
	


}
