package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @author Jane
 * @Date  Jan 29, 2014
 */
public class LicMgrPrivilegePurchaseAuthorizationWidget extends DialogWidget {
	private static LicMgrPrivilegePurchaseAuthorizationWidget _instance = null;
	
	protected LicMgrPrivilegePurchaseAuthorizationWidget(){	
		super("(Privilege|Licence) Purchase Authorization");
	}
	
	public static LicMgrPrivilegePurchaseAuthorizationWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrPrivilegePurchaseAuthorizationWidget();
		}
		return _instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] purchaseAuthorizationTable() {
		return Property.concatPropertyArray(table(), ".id", "PurchaseAuthorizationForm");
	}
	
	protected Property[] authTypeDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("CustomerPurchaseAuthorizationListView-\\d+\\.authType", false));
	}
	
	protected Property[] authedPrivilegeDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeLicenseYearLightView-\\d+\\.privilegeProductID", false));
	}
	
	protected Property[] authedPrivLicYearDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeLicenseYearLightView-\\d+\\.licenseYear", false));
	}
	
	protected Property[] authedReasonTextArea() {
		return Property.concatPropertyArray(textarea(), ".id", new RegularExpression("CustomerPurchaseAuthorizationListView-\\d+\\.reason", false));
	}
	
	protected Property[] hunterHostNumText() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("CustomerPurchaseAuthorizationListView-\\d+\\.hunterHostNum", false));
	}
	
	protected Property[] hunterHostRelationshipDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("CustomerPurchaseAuthorizationListView-\\d+\\.relationship", false));
	}
	
	protected Property[] errorMsg() {
		return Property.concatPropertyArray(select(), ".id", "NOTSET", ".className", "message msgerror");
	}
	/** Page Object Property Definition END */
	
	public void selectAuthType(String type) {
		browser.selectDropdownList(authTypeDropdownList(), type);
	}
	
	public void selectAuthedPrivilege(String authedPriv) {
		browser.selectDropdownList(authedPrivilegeDropdownList(), authedPriv);
	}
	
	public void selectAuthedPrivLicYear(String authedPrivLicYear) {
		browser.selectDropdownList(authedPrivLicYearDropdownList(), authedPrivLicYear);
	}
	
	public void setAuthedReason(String reason) {
		browser.setTextArea(authedReasonTextArea(), reason);
	}
	
	public boolean isHunterHostNumTextExisted(){
		return browser.checkHtmlObjectExists(hunterHostNumText());
	}
	
	public void setHunterHostNum(String num) {
		browser.setTextField(hunterHostNumText(), num);
	}
	
	public void clickSearch() {
		clickButtonByText("Search");
	}
	
	public void selectHunterHostRelationship(String relationship) {
		browser.selectDropdownList(hunterHostRelationshipDropdownList(), relationship);
	}
	
	public void setPurchaseAuthorizationInfo(Data<PrivilegePurchaseAuthorization> purchaseAuth) {
		this.selectAuthType(PrivilegePurchaseAuthorization.AuthorizationType.getStrValue(purchaseAuth));
		ajax.waitLoading();
		this.selectAuthedPrivilege(PrivilegePurchaseAuthorization.AuthedPrivilege.getStrValue(purchaseAuth));
		ajax.waitLoading();
		this.selectAuthedPrivLicYear(PrivilegePurchaseAuthorization.AuthedPrivLicenseYear.getStrValue(purchaseAuth));
		this.setAuthedReason(PrivilegePurchaseAuthorization.AuthedReason.getStrValue(purchaseAuth));
		if(isHunterHostNumTextExisted()){
			this.setHunterHostNum(PrivilegePurchaseAuthorization.HunterHostNum.getStrValue(purchaseAuth));
			this.clickSearch();
			ajax.waitLoading();
			this.selectHunterHostRelationship(PrivilegePurchaseAuthorization.HunterHostRelationship.getStrValue(purchaseAuth));
		}
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
}
