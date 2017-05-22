package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 16, 2014
 */
public class LicMgrSubscriberInfoWidget extends DialogWidget {
	private static LicMgrSubscriberInfoWidget _instance = null;
	
	private LicMgrSubscriberInfoWidget() {
		super("Subscriber Info");
	}
	
	public static LicMgrSubscriberInfoWidget getInstance() {
		if(_instance == null) _instance = new LicMgrSubscriberInfoWidget();
		return _instance;
	}
	
	private static String CONTACT_PREFERENCE_PHONE = "PHONE";
	private static String CONTACT_PREFERENCE_EMAIL = "EMAIL";
	private static String CONTACT_PREFERENCE_DECLINE_TO_PROVIDE = "DECLINE_TO_PROVIDE";
	
	private Property[] enterAddress() {
		return Property.toPropertyArray(".id", new RegularExpression("AddSubscriberInfoUI-\\d+\\.selectionType", false), ".value", "ENTER_ADDRESS");
	}
	
	private Property[] copyExistingAddressFromCustomerProfile() {
		return Property.toPropertyArray(".id", new RegularExpression("AddSubscriberInfoUI-\\d+\\.selectionType", false), ".value", "COPY_FROM_CUSTOMER");
	}
	
	private Property[] salutation() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.salutation", false));
	}
	
	private Property[] firstName() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.firstName", false));
	}
	
	private Property[] middleName() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.middleName", false));
	}
	
	private Property[] lastName() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.lastName", false));
	}
	
	private Property[] suffix() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.suffix", false));
	}
	
	private Property[] contactPreference() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.contactPreference", false));
	}
	
	private Property[] phoneEmail() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.(phone|email)", false));
	}
	
	private Property[] address() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.address", false));
	}
	
	private Property[] supplementalAddress() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false));
	}
	
	private Property[] cityTown() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.city", false));
	}
	
	private Property[] state() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.state", false));
	}
	
	private Property[] zipPostal() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false));
	}
	
	private Property[] country() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.country\\.cd", false));
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus\\.name", false));
	}
	
	private Property[] validate() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Validate");
	}
	
	private Property[] giftMessage() {
		return Property.toPropertyArray(".id", new RegularExpression("SubscriberInfoView-\\d+\\.giftMessage", false));
	}
	
	
	public void selectEnterAddress() {
		browser.selectRadioButton(enterAddress(), 0);
	}
	
	public void selectCopyExistingAddressFromCustomerProfile() {
		browser.selectRadioButton(copyExistingAddressFromCustomerProfile(), 0);
	}
	
	public void selectSalutation(String salutation) {
		browser.selectDropdownList(salutation(), salutation);
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(firstName(), fName);
	}
	
	public void setMiddleName(String mName) {
		browser.setTextField(middleName(), mName);
	}
	
	public void setLastName(String lName) {
		browser.setTextField(lastName(), lName);
	}
	
	public void selectSuffix(String suffix) {
		browser.selectDropdownList(suffix(), suffix);
	}
	
	public void selectContactPreference(String preference) {
		browser.selectDropdownList(contactPreference(), preference);
	}
	
	public void setPhoneEmail(String phoneOrEmail) {
		browser.setTextField(phoneEmail(), phoneOrEmail);
	}
	
	public void setAddress(String address) {
		browser.setTextField(address(), address);
	}
	
	public void setSupplementalAddress(String suppleAddress) {
		browser.setTextField(supplementalAddress(), suppleAddress);
	}
	
	public void setCityTown(String city) {
		browser.setTextField(cityTown(), city);
	}
	
	public void selectState(String state) {
		browser.selectDropdownList(state(), state);
	}
	
	public void setZipPostal(String zip) {
		browser.setTextField(zipPostal(), zip);
	}
	
	public String getCountry() {
		return browser.getObjectText(country()).replace("Country", StringUtil.EMPTY).trim();
	}
	
	public String getStatus() {
		return browser.getObjectText(status()).replace("Status", StringUtil.EMPTY).trim();
	}
	
	public void clickValidate() {
		browser.clickGuiObject(validate());
	}
	
	public void setGiftMessage(String msg) {
		browser.setTextArea(giftMessage(), msg);
	}
	
	public void setContactPreference(Customer cust) {
		String phoneEmail = "";
		if(!StringUtil.isEmpty(cust.hPhone)) {
			phoneEmail = cust.hPhone;
			this.selectContactPreference(CONTACT_PREFERENCE_PHONE);
		} else if(!StringUtil.isEmpty(cust.email)) {
			phoneEmail = cust.email;
			this.selectContactPreference(CONTACT_PREFERENCE_EMAIL);
		} else {
			this.selectContactPreference(CONTACT_PREFERENCE_DECLINE_TO_PROVIDE);
		}
		ajax.waitLoading();
		if(!StringUtil.isEmpty(phoneEmail)) this.setPhoneEmail(phoneEmail);
	}
	
	public void enterAddress(Customer cust) {
		this.selectEnterAddress();
		ajax.waitLoading();
		if(!StringUtil.isEmpty(cust.salutation)) this.selectSalutation(cust.salutation);
		this.setFirstName(cust.fName);
		if(!StringUtil.isEmpty(cust.mName)) this.setMiddleName(cust.mName);
		this.setLastName(cust.lName);
		if(!StringUtil.isEmpty(cust.suffix)) this.selectSuffix(cust.suffix);
		
		this.setContactPreference(cust);
		
		this.setAddress(cust.address);
		if(!StringUtil.isEmpty(cust.supplementalAddress)) this.setSupplementalAddress(cust.supplementalAddress);
		this.setCityTown(cust.city);
		this.selectState(cust.state);
		this.setZipPostal(cust.zip);
	}
	
	public void copyExistingAddress(Customer cust) {
		this.selectCopyExistingAddressFromCustomerProfile();
		ajax.waitLoading();
		this.setContactPreference(cust);
	}
	
	public void setSubscriberInfo(Customer cust) {
		if(cust.subscriberInfoEnterAddress) {
			this.enterAddress(cust);
		} else {
			this.copyExistingAddress(cust);
		}
	}
}
