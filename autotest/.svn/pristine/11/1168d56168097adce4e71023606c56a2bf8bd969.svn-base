package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Access this page: Search specified vendor by vendor number, or click on vendor id link in vendor search list page.
 * @author vzhao
 *
 */
public class LicMgrVendorDetailsPage extends LicMgrCommonTopMenuPage {
	private static LicMgrVendorDetailsPage instance=null;
	private String prefixReg = "^VendorView-[0-9]*.";
	private String taxReg = "^AttributeValuesWrapper-[0-9]*.";

	protected LicMgrVendorDetailsPage(){}

	public static LicMgrVendorDetailsPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrVendorDetailsPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id",new RegularExpression(prefixReg+"vendorNum", false));
	}

	public void clickAccountBalance() {
		browser.clickGuiObject(".class","Html.A",".text","Account Balance");
	}

	public void clickInvoices() {
		browser.clickGuiObject(".class","Html.A",".text","Invoices");
	}

	public void clickApplication() {
		browser.clickGuiObject(".class","Html.A",".text","Application");
	}

	public void clickChangeHistory() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".className", "link");
		property[2] = new Property(".text", "Change History");
		browser.clickGuiObject(property);
	}

	public void selectVendorStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"statusReason", false), status);
	}

	public void deselectVendorStatus() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"statusReason", false), 0);
	}

	public String getVendorStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefixReg+"statusReason", false), 0);
	}

	public void setVendorName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"vendorName", false), name);
	}

	public void setOwnerName(String name) {
		browser.setTextField(".id", new RegularExpression(prefixReg+"ownerName", false), name);
	}

	public void selectVendorType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"type", false), type);
	}

	public void deselectVendorType() {
		browser.selectDropdownList(".id", new RegularExpression(prefixReg+"type", false), 0);
	}

	public void setTaxId(String taxId){
		browser.setTextField(".id", new RegularExpression(taxReg+"attr\\[5036\\]", false),taxId);
	}

	public void selectTaxIdType(String type){
		browser.selectDropdownList(".id", new RegularExpression(taxReg+"attr\\[5037\\]",false), type);
	}

	public void clickOK() {
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}

	public void clickApply() {
		browser.clickGuiObject(".class","Html.A",".text","Apply");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}

	public void clickAddressesContactsTab() {
		browser.clickGuiObject(".class","Html.A",".text","Addresses & Contacts");
	}

	public void clickAddContract(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Contact");
	}

	public void clickNotesAlertsTab() {
		browser.clickGuiObject(".class","Html.A",".text","Notes & Alerts");
	}

	public void clickStoresTab() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("^Agent.*", false));
	}

	public void clickFinancialConfigTab() {
		browser.clickGuiObject(".class","Html.A",".text", "Financial Config");
	}

	public void clickBankAccountsTab() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Bank Accounts*", false));
	}

	public void clickBankBondsTab() {
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Bonds", false));
	}

	public void clickEFTAdjustmentsTab() {
		browser.clickGuiObject(".class","Html.A",".text", "EFT Adjustments");
	}

	public void clickEFTInvoicePaymentsTab() {
		browser.clickGuiObject(".class","Html.A",".text", "EFT Invoice Payments");
	}

	public void clickUsersTab() {
		browser.clickGuiObject(".class","Html.A",".text", "Users");
	}

	public void updateVendorDetail(String item, String value){
		if(item.equalsIgnoreCase("status")){
			this.selectVendorStatus(value);
			this.clickOK();
			ajax.waitLoading();
		}

		if(item.equalsIgnoreCase("vendorName")){
			this.setVendorName(value);
			this.clickOK();
			ajax.waitLoading();
		}
		if(item.equalsIgnoreCase("ownerName")){
			this.setOwnerName(value);
			this.clickOK();
			ajax.waitLoading();
		}
		if(item.equalsIgnoreCase("vendorType")){
			this.selectVendorType(value);
			this.clickOK();
			ajax.waitLoading();
		}
	}

	public void setVendorBasicInfo(VendorInfo vendor){
		this.selectVendorStatus(vendor.status.replace(" - ", "-"));
		this.setVendorName(vendor.name);
		this.setOwnerName(vendor.ownerName);
		if(vendor.vendorType.trim().length()>0){
			this.selectVendorType(vendor.vendorType);
		}else {
			this.deselectVendorType();
		}

		this.setTaxId(vendor.taxID);
		this.selectTaxIdType(vendor.taxIDType);
	}

	public String getVendorNum(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"vendorNum", false));
	}

	public String getVendorName(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"vendorName", false));
	}

	public String getOwnerName(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"ownerName", false));
	}

	public String getVendorType(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"type", false),0);
	}

	public String getCreationUser(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"createUser.name", false));
	}

	public String getCreationDate(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"createDate", false));
	}

	public String getTaxID(){
		return browser.getTextFieldValue(".id",new RegularExpression(taxReg+"attr\\[5036\\]", false));
	}

	public String getTaxIDType(){
		return browser.getDropdownListValue(".id",new RegularExpression(taxReg+"attr\\[5037\\]", false),0);
	}

	public void verifyVendorStatusFromVendorDetail(String expectStatus){
		logger.info("Verify vendor status from vendor detail page.");

		String actualValue = this.getVendorStatus();
		if(!actualValue.equals(expectStatus.replace(" - ", "-"))){
			throw new ErrorOnPageException("In detail page expect status should be " + expectStatus
					+ ", but actually is " + actualValue);
		}
	}

	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}

	public boolean checkErrorMessageIsExist(){
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}

	public boolean compareVendorBasicInfo(VendorInfo expVendor){
		boolean result = true;
		String actualValue = "";

		actualValue = this.getVendorNum();
		if(!actualValue.equals(expVendor.number)){
			result &= false;
			logger.error("Expect vendor number should be " + expVendor.number
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getVendorStatus();
		if(!actualValue.equals(expVendor.status.replace(" - ", "-"))){
			result &= false;
			logger.error("Expect vendor status should be " + expVendor.status
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getVendorName();
		if(!actualValue.equals(expVendor.name)){
			result &= false;
			logger.error("Expect vendor name should be " + expVendor.name
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getOwnerName();
		if(!actualValue.equals(expVendor.ownerName)){
			result &= false;
			logger.error("Expect vendor owner name should be " + expVendor.ownerName
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getVendorType();
		if(!actualValue.equals(expVendor.vendorType)){
			result &= false;
			logger.error("Expect vendor type should be " + expVendor.vendorType
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getCreationUser();
		if(!actualValue.equals(expVendor.vendorCreationUser)){
			result &= false;
			logger.error("Expect vendor creation user should be " + expVendor.vendorCreationUser
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getCreationDate();
		if(expVendor.vendorCreationDate.length()>0){
			expVendor.vendorCreationDate = DateFunctions.formatDate(expVendor.vendorCreationDate, "E MMM d yyyy");
		}
		if(!actualValue.contains(expVendor.vendorCreationDate)){
			result &= false;
			logger.error("Expect vendor creation date should be " + expVendor.vendorCreationDate
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getTaxID();
		if(!actualValue.equals(expVendor.taxID)){
			result &= false;
			logger.error("Expect vendor tax ID should be " + expVendor.taxID
					+ ", but acutally is " + actualValue);
		}

		actualValue = this.getTaxIDType();
		if(!actualValue.equals(expVendor.taxIDType)){
			result &= false;
			logger.error("Expect vendor tax ID Type should be " + expVendor.taxIDType
					+ ", but acutally is " + actualValue);
		}

		return result;
	}

	
	public void clickEFTReporting(){
		browser.clickGuiObject(".class", "Html.A", ".text", "EFT Reporting");
	}
	
	public boolean isDocumentUploadsTableExisting(){
		return browser.checkHtmlObjectDisplayed(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
	}
	
	public void clickDocumentUploadsTab(){
		browser.clickGuiObject(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
	}
	
	public int getDocumentUploadTabNum(){
		String temp = browser.getObjectText(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
		String text = temp.replaceAll("Document Uploads|\\(|\\)", "").trim();
		int number;
		if(StringUtil.isEmpty(text)){
			number = 0;
		}else{
			number = Integer.valueOf(text);
		}
		
		return number;
	}
	
	public String getDocumentUploadTabName(){
		String temp = browser.getObjectText(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
		String text = temp.split("\\(\\d+\\)")[0];
		return text;
	}
	
	
}
