/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: The widget will be shown when reverse a order/order item in the location which is different
 * from where the order was original processed, and the item's payment type is in Deferred Payment Group. 
 * 
 * @author Lesley Wang
 * @Date  Jun 19, 2012
 */
public class LicMgrRefundWidget extends DialogWidget {

	private static LicMgrRefundWidget _instance = null;
	
	protected LicMgrRefundWidget() {
		super("Refund");
	}
	
	public static LicMgrRefundWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrRefundWidget();
		}
		return _instance;
	}	
	
	public void selectIssueRefundToCustomerRadio() {
		browser.selectRadioButton(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false), 0);
	}
	
	public void selectPostRefundAsCreditRadio() {
		browser.selectRadioButton(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false), 1);
	}
	
	private boolean isRadioButtonEnabled(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false));
		boolean enabled = objs[index].isEnabled();
		
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean isIssueRefundToCustomerRadioEnabled() {
		return this.isRadioButtonEnabled(0);
	}
	
	/**
	 * Verify 'Issue this Refund to the Customer now' radio button selection should NOT exist
	 * @return
	 */
	public boolean verifyIssueRefundToCustomerRadioNOTExist() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false));
		boolean actual = true;
		
		// there should be only one selection: Post Refund As Credit
		if(null == objs){
			throw new ErrorOnPageException("There should be only one selection: Post Refund As Credit");
		} else {
			logger.info("Issue refund to customer radio button should NOT exist!");
			
			for(int i=0; i<objs.length;i++){
				String text = objs[i].getProperty(".text").trim();
				if(text.contains("Issue this Refund to the Customer now")){
					actual = false;
				} else {
					actual = true;
				}
			}
			return actual;
		}
	}
	
	public int getRefundMethodCount(){
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false));
		int length = objs.length;
		
		Browser.unregister(objs);
		return length;
	}
	
	/**
	 * Verify 'Post Refund As Credit' radio button selection should NOT exist
	 * @return
	 */
	public boolean verifyPostRefundAsCreditRadioNOTExist() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("VoidReasonSelection-\\d+.voidReverse",false));
		boolean actual = true;
		
		// there should be only one selection: Issue this Refund to the Customer now
		if(null == objs){
			throw new ErrorOnPageException("There should be only one selection: Issue this Refund to the Customer now");
		} else {
			logger.info("Post Refund As Credit radio button should NOT exist!");
			
			for(int i=0; i<objs.length;i++){
				String text = objs[i].getProperty(".text").trim();
				if(text.contains("Post Refund As Credit")){
					actual = false;
				} else {
					actual = true;
				}
			}
			return actual;
		}
	}
	
	public boolean isPostRefundAsCreditRadioEnabled() {
		return this.isRadioButtonEnabled(1);
	}
	
	public boolean compareIssueRefundToCustRadioStatus(boolean exp) {
		if (exp != this.isIssueRefundToCustomerRadioEnabled()) {
			logger.error("------The issue refund to customer radio status is wrong! It should be " + (exp? "enabled" : "disable"));
			return false;
		} else {
			logger.info("The issue refund to customer radio status is correct!");
			return true;
		}
	}
	
	public boolean comparePostRefundAsCreditRadioStatus(boolean exp) {
		if (exp != this.isPostRefundAsCreditRadioEnabled()) {
			logger.error("------The post refund as credit radio status is wrong! It should be " + (exp? "enabled" : "disable"));
			return false;
		} else {
			logger.info("The post refund as credit radio status is correct!");
			return true;
		}
	}
	
	private String getAllTextOnTheWidget() {
		return browser.getObjectText(".class", "Html.DIV", ".id", new RegularExpression("Dialog\\d+", false)).trim();
	}
	
	public boolean compareMessage(String expRefundAmt) {
		String text = this.getAllTextOnTheWidget();
		String refundMsg1 = "There is a $";
		String refundMsg2 = " Refund due to the Customer as a result of this ";
		String refundMsg3 = "reverse transaction.";
		String refundMsg4 = "void/reversal transaction";
		String expMsg = refundMsg1 + expRefundAmt + refundMsg2;
		if (text.startsWith(expMsg + refundMsg3) || text.startsWith(expMsg + refundMsg4)) {
			logger.info("The message in the Refund widget is correct!");
			return true;
		} else {
			logger.error("---The message in the refund widget is wrong! Expected:" + expMsg + "; Actual:" + text);
			return false;
		}
	}
	
	public boolean compareStoreInfo(String expStoreID, String expStoreNm) {
		String text = this.getAllTextOnTheWidget();
		text = StringUtil.getSubString(text, "Post the Refund");
		String actStoreID = StringUtil.getSubstring(text, "Credit to", "-");
		String actStoreNm = StringUtil.getSubstring(text, "-", "where");
		boolean result = MiscFunctions.compareResult("Store ID", expStoreID, actStoreID);
		result &= MiscFunctions.compareResult("Store Name", expStoreNm, actStoreNm);
		
		return result;
	}
	
	public void verifyStoreInfoShouldNotExists(String storeID, String storeName){
		logger.info("Verify store info should not exists.");
		String text = this.getAllTextOnTheWidget();
		boolean isExists = text.contains(storeID) || text.contains(storeName);
		if(isExists){
			throw new ErrorOnPageException("Store info should not exists.");
		}else logger.info("Store info is not exists.");
	}
	
	public void verifyIssueRefundMethodNotExists(String issueMethod){
		logger.info("Verify issue refund method not exist: " + issueMethod);
		String text = this.getAllTextOnTheWidget();
		boolean isExists = text.contains(issueMethod);
		if(isExists){
			throw new ErrorOnPageException(issueMethod + ", this issue refund method should not exists.");
		}else logger.info(issueMethod + ", this issue refund method is not exists.");
	}
}
