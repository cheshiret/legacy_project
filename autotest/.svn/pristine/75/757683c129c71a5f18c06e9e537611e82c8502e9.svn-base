/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:it is for My Account Panel, shown on the left of all My Account pages after sign in
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class HFMyAccountPanel extends HFHeaderBar {
	private static HFMyAccountPanel _instance = null;

	public static HFMyAccountPanel getInstance() {
		if (null == _instance)
			_instance = new HFMyAccountPanel();

		return _instance;
	}
	
	protected HFMyAccountPanel() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "myAccount");
	}
	
	/** Page Object Property Definition Begin */
	Property[] updateEmailProp() {
		return Property.toPropertyArray(".id", "updateEmail");
	}
	
	Property[] updatePasswordProp() {
		return Property.toPropertyArray(".id", "updatePassword");
	}
	
	protected Property[] pointsLink() {
		return Property.concatPropertyArray(a(), ".id", "hfPoints");
	}
	
	private Property[] recentOrders(){
		return Property.concatPropertyArray(div(), ".id", "recentOrders");
	}
	
	private Property[] prderHisItems(String orderNum){
		return Property.concatPropertyArray(div(), ".className", "orderHist_item", ".text", new RegularExpression("^Licence Order #: "+orderNum+".*", false));
	}
	
	private Property[] orderItem(String privName, String ly){
		return Property.concatPropertyArray(div(), ".className", "order_item", ".id", "orderItem", ".text", new RegularExpression(privName.replace("(", "\\(").replace(")", "\\)")+" \\("+ly+"\\).*", false));
	}
	
	/** Page Object Property Definition END */
	
	public void clickAccountOverView(){
		browser.clickGuiObject(".id", "accountOverview");
	}
	
	public void clickOrderHistory() {
		browser.clickGuiObject(".id", "orderHistory");
	}
	
	public void clickBigGameDraw() {
		browser.clickGuiObject(".id", "lotteryApplications");
	}
	
	public void clickLicences() {
		browser.clickGuiObject(".id", "hfLicenses");
	}
	
	public void clickHarvestReports() {
		browser.clickGuiObject(".id", "harvestReports");
	}
	
	public void clickReplaceLicenses() {
		browser.clickGuiObject(".id", "replaceLicenses");
	}
	
	public void clickRedeemableVouchers() {
		browser.clickGuiObject(".id", "redeemableVouchers");
	}
	
	public void clickPoints() {
		browser.clickGuiObject(".id", "hfPoints");
	}
	
	public void clickUpdateProfile() {
		browser.clickGuiObject(".id", "updateProfile");
	}
	
	public void clickUpdateEmail(){
		browser.clickGuiObject(this.updateEmailProp());
	}
	
	public boolean isUpdateEmailExist() {
		return browser.checkHtmlObjectExists(this.updateEmailProp());
	}
	
	public void clickUpdatePassword(){
		browser.clickGuiObject(this.updatePasswordProp());
	}
	
	public boolean isUpdatePasswordExist() {
		return browser.checkHtmlObjectExists(this.updatePasswordProp());
	}
	
	public boolean isUpdateProfileLinkExist() {
		return browser.checkHtmlObjectExists(".id", "updateProfile", ".text", "Update Profile");
	}
	
	public boolean isViewProfileLinkExist() {
		return browser.checkHtmlObjectExists(".id", "updateProfile", ".text", "View Profile");
	}
	
	public boolean checkOrderNumExisted(String orderNum){
	     return browser.checkHtmlObjectDisplayed(Property.atList(recentOrders(), prderHisItems(orderNum)));
	}
	
	public boolean checkOrderItemExisted(String privName, String ly){
		return browser.checkHtmlObjectDisplayed(Property.atList(recentOrders(), orderItem(privName, ly)));
	}
	
	public void verifyOrderAndOrderItemsExisted(String ordNum, PrivilegeInfo...privileges){
		boolean result = MiscFunctions.compareResult("Order number existing or not", true, checkOrderNumExisted(ordNum));
		for(PrivilegeInfo priv: privileges){
			result &= MiscFunctions.compareResult(priv.name+" item existing or not", true, checkOrderItemExisted(priv.name, priv.licenseYear));
		}
		if(!result){
			throw new ErrorOnPageException("Not all order and order itmes are correct in my account recent order section. Please check details from previous logs.");
		}
	}
}
