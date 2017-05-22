/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: https://orms-torqa3.dev.activenetwork.com/HFCustomerMgrPage.page?_PageParam.privInstID=328712778#/ActivityRegistration  -> Activity Registration History Tab

 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author sborjigin
 * @Date  Jul 3, 2014
 */
public class LicMgrActivityRegistrationHistoryPage extends LicMgrActivityRegistrationDetailPage{
	private static LicMgrActivityRegistrationHistoryPage _instance = null;

	protected LicMgrActivityRegistrationHistoryPage(){
		
	}
	public static LicMgrActivityRegistrationHistoryPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrActivityRegistrationHistoryPage();
		}
		
		return _instance;
	}
	Property [] activityRegistrasionHistoryTab(){
		return Property.concatPropertyArray(div(),Property.toPropertyArray(".id","tab_history",".text","Activity Registration History"));
	}
	Property [] orderHistoryGrid () {
		return Property.concatPropertyArray(div(),Property.toPropertyArray(".id","orderhistoryGrid"));
	}
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(activityRegistrasionHistoryTab());
	}
	

	public void verifyReceipNumLinkExistInAcitivityHisTab(String receiptNum){
		logger.info("Verify receipt#"+receiptNum+" exist in activity history table..");
		IHtmlObject[] historyTableObjs=browser.getTableTestObject(orderHistoryGrid());
		boolean result = browser.checkHtmlObjectDisplayed(".text",receiptNum,".class","Html.A", historyTableObjs[historyTableObjs.length-1]);
		if(!result){
			throw new ErrorOnPageException("Receipt Number link is not exist in the activity historry table");
		}
		Browser.unregister(historyTableObjs);
	}

}
