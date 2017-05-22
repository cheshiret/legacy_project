package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrNewCustomerPage extends LicMgrCommonTopMenuPage {
	private static LicMgrNewCustomerPage _instance = null;

	public static LicMgrNewCustomerPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrNewCustomerPage();
		}

		return _instance;
	}

	protected LicMgrNewCustomerPage() {

	}

	protected Property[] businessNumTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.businessNumber", false));
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("CustomerIdentif(i)?erView-\\d+\\.identifier.identifierType", false));
	}

	public void setBusinessName(String bName) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+.businessName", false);
		browser.setTextField(".id", idPattern, bName);
	}

	public boolean checkBusinessName() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.businessName", false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public boolean isBusinessNumExist() {
		return browser.checkHtmlObjectExists(this.businessNumTextField());
	}
	
	public void setBusinessNum(String num) {
		browser.setTextField(this.businessNumTextField(), num);
	}
	
	public void setFirstName(String fName) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.firstName",
				false);
		browser.setTextField(".id", idPattern, fName);
	}

	public void setMiddleName(String mName) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName",
				false);
		browser.setTextField(".id", idPattern, mName);
	}

	public void setLastName(String lName) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false);
		browser.setTextField(".id", idPattern, lName);
	}

	public void selectSuffix(String suffix) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false);
		browser.selectDropdownList(".id", idPattern, suffix);
	}

	public void setDateOfBirth(String date) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false);
		browser.setTextField(".id", idPattern, date);
	}

	public void selectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		browser.selectCheckBox(".id", idPattern);
		ajax.waitLoading();
	}

	public void unselectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		browser.unSelectCheckBox(".id", idPattern);
	}

	public void setOverrideReason(String reason) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideReason", false);
		
		browser.focusOn(".id", idPattern);
		browser.setTextField(".id", idPattern, reason);
	}

	public void setHomePhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkHomePhone() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setBusinessPhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkBusinessPhone() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setMobilePhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkMobilePhone() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setFax(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.fax", false);
		browser.setTextField(".id", idPattern, phone);
	}

	public void setEmail(String email) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.email", false);
		browser.setTextField(".id", idPattern, email);
	}

	public void selectPhoneContactPreference(String option) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void selectPreferedContactTime(String option) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void selectResident(boolean isResident) {
		String option = isResident ? "Yes" : "No";
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:BOOLEAN_YESNO",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void setDate(String date) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:DATE_ForDisplay",
				false);
		browser.setTextField(".id", idPattern, date);
	}

	public void selectEyeColor(String color) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[(210000001|5040)\\]", false);
		browser.selectDropdownList(".id", idPattern, color);
	}
	
	public void clickGender(){
		RegularExpression idPattern = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		browser.clickGuiObject(".id", idPattern);
	}
	
	public void selectGender(String gender) {
		RegularExpression idPattern = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text","MaleFemaleUnknown"),
		// };
		
		if(StringUtil.isEmpty(gender)) {
			browser.selectDropdownList(".id", idPattern, 1, false);
		} else {
			browser.selectDropdownList(".id", idPattern, gender, false);
		}
	}

	public void selectGender(int index) {
		RegularExpression idPattern = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		browser.selectDropdownList(".id", idPattern, index);
	}
	
	// check if Height text filed exist. By Lesley
	public boolean checkHeightTextFieldExist() {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000102\\]",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}
	
	public void setHeight(String height) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000102\\]", false);
		browser.setTextField(".id", idPattern, height);
	}

	public void setPhysicalAddress(String address) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.address", false);
		browser.setTextField(".id", idPattern, address, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setPhysicalSupplemental(String supplemental) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.supplemental", false);
		browser.setTextField(".id", idPattern, supplemental, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setPhysicalCityTown(String city) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.city", false);
		browser.setTextField(".id", idPattern, city, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectPhysicalState(String state) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, state, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectPhysicalState(int index) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public IHtmlObject[] getPhysicalAddressTable() {
		return browser.getHtmlObject(".id", "physicalAddress");
	}

	public IHtmlObject[] getMaillingAddressTable() {
		return browser.getHtmlObject(".id", "maillingAddress");
	}

	public void selectPhysicalCounty(String county) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.county", false);
		browser.selectDropdownList(".id", idPattern, county, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void setPhysicalZipCode(String zip) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.zipCode", false);
		browser.setTextField(".id", idPattern, zip, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectPhysicalCountry(String country) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, country, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectPhysicalCountry(int index) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void clickPhysicalValidate() {
		IHtmlObject[] table = getPhysicalAddressTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", true,
				0, table[0]);
		Browser.unregister(table);
	}

	public void setMailingAddress(String address) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.address", false);
		browser.setTextField(".id", idPattern, address, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setMailingSupplemental(String supplemental) {
		IHtmlObject[] table = getMaillingAddressTable();
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), supplemental, true, 0, table[0]);
		Browser.unregister(table);
	}
	
	public void setMailingCityTown(String city) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.city", false);
		browser.setTextField(".id", idPattern, city, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectMailingState(String state) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, state, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMailingState(int index) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMailingCounty(String county) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.county", false);
		browser.selectDropdownList(".id", idPattern, county, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void setMailingZipCode(String zip) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.zipCode", false);
		browser.setTextField(".id", idPattern, zip, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectMailingCountry(String country) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, country, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMailingCountry(int index) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void clickMailingValidate() {
		IHtmlObject[] table = getMaillingAddressTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", true,
				0, table[0]);
		Browser.unregister(table);
	}

	public void selectMailingAddressSameAsPhysicalAddress() {
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.sameAddress", false);
		browser.selectCheckBox(".id", idPattern);
	}

	public void unselectMailingAddressSameAsPhysicalAddress() {
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.sameAddress", false);
		browser.unSelectCheckBox(".id", idPattern);
		ajax.waitLoading();
	}

	public void selectIdentifierType(String type, int index) {
		RegularExpression idPattern = new RegularExpression(
				"CustomerIdentif(i)?erView-\\d+\\.identifier\\.identifierType",
				false);
		browser.selectDropdownList(".id", idPattern, type, index);
		ajax.waitLoading();
	}

	public void selectIdentifierType(String type) {
		this.selectIdentifierType(type, 0);
	}

	public void setIdentifierNumber(String number, int index) {
		RegularExpression idPattern = new RegularExpression(
				"CustomerIdentif(i)?erView-\\d+\\.identifier\\.identifierNumber",
				false);
		browser.setTextField(".id", idPattern, number, index);
	}

	public void setIdentifierNumber(String number) {
		this.setIdentifierNumber(number, 0);
	}

	public void removeIdentifier(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index);
	}

	public void addIdentifier() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	public boolean isEthnicityExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false));
	}
	public boolean isSolicitationExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5033\\]", false));
	}

	
	
	public void selectEthnicity(String ethnicity) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text",new
		// RegularExpression("^WhiteBlack.*UnKnown$",false)),
		// };
		if(StringUtil.isEmpty(ethnicity)) {
			browser.selectDropdownList(".id", idPattern, 1);
		} else {
			browser.selectDropdownList(".id", idPattern, ethnicity);
		}
	}

	public void selectEthnicity(int index) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		browser.selectDropdownList(".id", idPattern, index);
	}
	
	public void selectSolicitation(String solicitation) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",
				false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text","NoYes"),
		// };
		browser.selectDropdownList(".id", idPattern, solicitation);
	}

	public boolean isEyeColorListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5040\\]", false));
	}

	public boolean isHairColorListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5041\\]", false));
	}
	
	public void selectHairColor(String value) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5041\\]", false), value);
	}
	
	public boolean isHeightFeetTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5043\\]_feet", false));
	}
	
	public void setHeightFeet(String value) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5043\\]_feet", false), value);
	}
	
	public boolean isHeightInchesTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5043\\]_inches", false));
	}
	
	public void setHeightInches(String value) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5043\\]_inches", false), value);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
		ajax.waitLoading();
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
		ajax.waitLoading();
	}

	public void clickPhyValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");
	}

	public boolean checkPhyValidateExist() {
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Validate");
		if (objs.length < 1) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public boolean checkMailValidateExist() {
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Validate");
		if (objs.length < 2) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public String getPhyStatus() {
		RegularExpression regx = new RegularExpression(
				"AddressView-\\d+\\.verificationStatus\\.name", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getMailStatus() {
		String status = "";
		RegularExpression regx = new RegularExpression(
				"AddressView-\\d+\\.verificationStatus\\.name", false);
		IHtmlObject[] objs = browser.getTextField(".id", regx);

		if (objs.length < 2) {
			throw new ItemNotFoundException(
					"The Status for mailing address is not found~!");
		}

		status = objs[1].getProperty(".text");
		return status;
	}

	public void selectIdentifierCountry(String country, int index) {
//		RegularExpression regx = !MiscFunctions.isQA3()?new RegularExpression("IdentifierView-\\d+\\.country",false):new RegularExpression(
//				"CustomerIdentiferView-\\d+\\.identifier\\.country", false);
		
		RegularExpression regx = new RegularExpression("IdentifierView-\\d+\\.country",false);
		browser.selectDropdownList(".id", regx, country);
	}

	public void selectIdentifierCountry(String country) {
		this.selectIdentifierCountry(country, 0);
	}
	
	public void selectIdentifierState(String state,int index)  {
		RegularExpression regx = new RegularExpression(
				"(CustomerIdentifer|Identifier)View-\\d+(\\.identifier)?\\.stateProvince", false);
		browser.selectDropdownList(".id", regx, state);
	}
	
	public void selectIdentifierState(String state) {
		selectIdentifierState(state,0);
	}

	public boolean checkIdentifierCountryExist() {
//		RegularExpression regx = !MiscFunctions.isQA3()?new RegularExpression("IdentifierView-\\d+\\.country",false):new RegularExpression(
//				"CustomerIdentiferView-\\d+\\.identifier\\.country", false);

		RegularExpression regx = new RegularExpression("IdentifierView-\\d+\\.country",false);
		return browser.checkHtmlObjectExists(".id", regx);
	}
	
	public boolean checkIdentifierStateExist() {
		RegularExpression regx = new RegularExpression(
				"(CustomerIdentifer|Identifier)View-\\d+(\\.identifier)?\\.stateProvince", false);
		return browser.checkHtmlObjectExists(".id", regx);
	}

	/** click 'Add' to add Identifier or Additional Contacts*/
	public void clickAdd(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", index);
		ajax.waitLoading();
	}
	
    /**
     * click 'Add' to add Identifier 
     * @param index --0: add customer identifier when customer class is Individual
     *              --1: add customer identifier when customer class is other than Individual
     *                   Because it exists addition address which has Add button
     */
	public void clickAddIdentifier(int index) {
		this.clickAdd(index);
	}
	
	/** click 'Add' to add Additional Contacts */
	public void clickAddAdditionalContacts () {
		this.clickAdd(0);
	}

	/** Get customer number */
	public String getCustomerNum() {
		return browser.getObjectText(".class","Html.SPAN",".text", new RegularExpression("^MDWFP #.*",false)).replaceAll("MDWFP #", "").trim();
	}

	/** Get customer class */
	public String getCustomerClass() {
		//James[20140314] Customer Class field now is <div title="Individual" class="fakeField" id="CustomerProfileView-2140767272.customerClass.name_fakeField" style="width: 118px;">Individual</div>
		String tmp = browser.getObjectText(".id", new RegularExpression("CustomerProfileView-\\d+\\.customerClass\\.name(_fakeField)?",false)).trim();
		if(StringUtil.isEmpty(tmp)) {
			tmp = browser.getTextFieldValue(".id", new RegularExpression("CustomerProfileView-\\d+\\.customerClass\\.name", false));//Quentin[20131106] 3.05 ui changes
		}
		return tmp;
	}

	/** Get creation application */
	public String getCreationApplication() {
		return browser.getObjectText(".class","Html.SPAN",".text", new RegularExpression("^Creation Application.*",false)).replaceAll("Creation Application", "").trim();
	}

	/** Get creation date */
	public String getCreationDate() {
		return browser.getObjectText(".class","Html.SPAN",".text", new RegularExpression("^Creation Date.*",false)).replaceAll("Creation Date", "").trim();
	}

	/** Get creation user */
	public String getCreationUser() {
		return browser.getObjectText(".class","Html.SPAN",".text", new RegularExpression("^Creation User.*",false)).replaceAll("Creation User", "").trim();
	}
	
	/** Click the validate */
	public void clickValidate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");	
	}
	
	/** set Identifier Info */
	public void setIdentifiers(Customer cust) {
		List<CustomerIdentifier> tempList = new ArrayList<CustomerIdentifier>();
		tempList.addAll(cust.identifiers);
		if (cust.identifier.identifierType != null
				&& cust.identifier.identifierType.trim().length() > 0) {
			if(!tempList.contains(cust.identifier)) {
				tempList.add(cust.identifier);
			}
		}
		if(tempList.size()>0) {
			int available=getNumberOfIdentifierDropdownList();
			if (available<tempList.size()) {
				int size=tempList.size()-available;
				for(int i=0;i<size;i++) {
					if(cust.customerClass.equalsIgnoreCase("Individual")){
						this.clickAddIdentifier(0);
					}else{
						this.clickAddIdentifier(1);
					}
					ajax.waitLoading();
				}
			}
			for (int i = 0; i < tempList.size(); i++) {				
				this.selectIdentifierType(tempList.get(i).identifierType, i);
				ajax.waitLoading();
				this.setIdentifierNumber(tempList.get(i).identifierNum, i);

				if(this.checkIdentifierStateExist()) {
					this.selectIdentifierState(tempList.get(i).state, i);
				}
				
				if (this.checkIdentifierCountryExist()) {
					this.selectIdentifierCountry(tempList.get(i).country, i);
				}
			}
		}
	}
	
	public int getNumberOfIdentifierDropdownList() {
		RegularExpression idpattern=new RegularExpression("CustomerIdentiferView-\\d+.identifier.identifierType",false);
		IHtmlObject[] objs=browser.getDropdownList(".id",idpattern);
		
		int size=objs.length;
		
		Browser.unregister(objs);
		return size;
	}

	/**
	 * Get warning message
	 * 
	 * @return warning message
	 */
	public String getErrorMsg() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "message msgerror");
		if (objs.length > 0) {
			warnMes = objs[0].getProperty(".text");
		} else {
			throw new com.activenetwork.qa.testapi.ObjectNotFoundException("Object can't find.");
		}

		Browser.unregister(objs);
		return warnMes;
	}

	public void setCustomerDetails(Customer cust) {
		if (this.checkBusinessName() && null != cust.businessName
				&& cust.businessName.length() > 0) {
			this.setBusinessName(cust.businessName);
		}
		if (this.isBusinessNumExist() && StringUtil.notEmpty(cust.businessNum)) {
			this.setBusinessNum(cust.businessNum);
		}
		this.setFirstName(cust.fName);
		if(null!=cust.mName && cust.mName.length()>0){
			this.setMiddleName(cust.mName);
		}
		this.setLastName(cust.lName);
		this.setDateOfBirth(cust.dateOfBirth);
		if (this.checkHomePhone() && null != cust.hPhone
				&& cust.hPhone.length() > 0) {
			this.setHomePhone(cust.hPhone);
		} 
		
		if (this.checkBusinessPhone() && null != cust.bPhone
				&& cust.bPhone.length() > 0) {
			this.setBusinessPhone(cust.bPhone);
		}
		if (this.checkMobilePhone() && null != cust.mPhone
				&& cust.mPhone.length() > 0) {
			this.setMobilePhone(cust.mPhone);
		}
		if(!cust.phoneContact.equalsIgnoreCase("No preference")){
			this.selectPhoneContactPreference(cust.phoneContact);
		}
		
		// It the customer is individual, set the gender, ethnicity, solicitation indicator, lifetime slogan info. Update by lesley
		if (this.getCustomerClass().equalsIgnoreCase(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS)) {
			this.selectGender(cust.custGender);  
			if(this.isEthnicityExists()) {
				this.selectEthnicity(cust.ethnicity);
			}
			if(this.isEthnicityExists()&&""!=cust.solicitationIndcator) {
				this.selectSolicitation(cust.solicitationIndcator);
			}
			//updated by Summer, date:2014/6/11 reason: threre are no UI for EyeColor and HairColor setting
			if(this.isHairColorListExist()&&""!=cust.eyeColor){
				this.selectEyeColor(cust.eyeColor);
			}
			if(this.isHairColorListExist()&&""!=cust.hairColor){
				this.selectHairColor(cust.hairColor);
			}
			if(StringUtil.notEmpty(cust.heightFt)){
				this.setHeightFeet(cust.heightFt);
			}
			if(StringUtil.notEmpty(cust.heightIn)){
				this.setHeightInches(cust.heightIn);
			}
		}
	
		//Jane[20140514]:For AB contract, outfitter customer, it has gender,height info as mandatory field
		if(isGenderListExist())
			selectGender(cust.custGender);
		if(isHeightTextFieldExist()) {
			if(StringUtil.notEmpty(cust.heightFt)){
				this.setHeightFeet(cust.heightFt);
			}
			if(StringUtil.notEmpty(cust.heightIn)){
				this.setHeightInches(cust.heightIn);
			}
		}
		//Jane[20140514]:For AB contract, outfitter customer, it has gender,height info as mandatory field
		
		this.selectSuffix(cust.suffix);
		if(cust.overrideReqId){
			this.selectOverrideRequiredIdentifiers();
			this.setOverrideReason(cust.overideReason);
		}else{
			this.unselectOverrideRequiredIdentifiers();
		}
		this.setFax(cust.fax);
		this.setEmail(cust.email);
		
		if (this.checkHeightTextFieldExist() && null != cust.height
				&& cust.height.length() > 0) {
			this.setHeight(cust.height);
		}
//		this.setHeight(cust.height);
		
		if (this.isWeightTextFieldExist() && StringUtil.notEmpty(cust.weight)) {
			this.setWeight(cust.weight);
		}
		
		this.selectPhysicalCountry(cust.physicalAddr.country);
		this.setPhysicalAddress(cust.physicalAddr.address);
		if (StringUtil.notEmpty(cust.physicalAddr.supplementalAddr)) // add by Lesley
			this.setPhysicalSupplemental(cust.physicalAddr.supplementalAddr);
		this.setPhysicalCityTown(cust.physicalAddr.city);
		this.selectPhysicalState(cust.physicalAddr.state);
		ajax.waitLoading();
		if (null != cust.physicalAddr.county
				&& cust.physicalAddr.county.length() > 0) {
			this.selectPhysicalCounty(cust.physicalAddr.county);
			ajax.waitLoading();
		}
		this.setPhysicalZipCode(cust.physicalAddr.zip);
		ajax.waitLoading();
		if(cust.physicalAddr.isValidate) {
			this.clickPhysicalValidate();
			ajax.waitLoading();
		}
		if (cust.mailAddrAsPhy) {
			this.selectMailingAddressSameAsPhysicalAddress();
		} else {
			this.unselectMailingAddressSameAsPhysicalAddress();
		}
		ajax.waitLoading();
		if(!cust.mailAddrAsPhy) {
			//mailing address info
			this.setMailingAddress(cust.mailingAddr.address);
			this.setMailingSupplemental(cust.mailingAddr.supplementalAddr);
			this.setMailingCityTown(cust.mailingAddr.city);
			ajax.waitLoading();
			if(StringUtil.notEmpty(cust.mailingAddr.state)){
				this.selectMailingState(cust.mailingAddr.state);
			}
			ajax.waitLoading();
			if(cust.mailingAddr.county.length() > 0) {
				this.selectMailingCounty(cust.mailingAddr.county);
				ajax.waitLoading();
			}
			this.setMailingZipCode(cust.mailingAddr.zip);
			if(StringUtil.notEmpty(cust.mailingAddr.country)){
				this.selectMailingCountry(cust.mailingAddr.country);
			}
			
			ajax.waitLoading();
			if(cust.mailingAddr.isValidate) {
				this.clickMailingValidate();
				ajax.waitLoading();
			}
		}
		//set customer identifier info
		this.setIdentifiers(cust);
		if(cust.physicalAddr.isValidate){
			this.clickValidate();
		}
		ajax.waitLoading();
	}

	/**
	 * Get date of birth value
	 * @return
	 */
	public String getDateOfBirth() {
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false));
	}
	
	/**
	 * Verify if the date of birth input value is valid or not
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyDateOfBirthTextFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("HFCustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false))[0], invalidDates);
	}
	
	public String getFirstName(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.firstName",false));
	}
	
	public String getMiddleName(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName",false));
	}
	
	public String getLastName(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName",false));
	}
	
	public String getSuffix(){
		return browser.getDropdownListValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix",false));
	}
	
	public String getOverrideReason(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.overrideReason",false));
	}
	
	public String getHomePhone(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone",false));
	}
	
	public String getBusinessPhone(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",false));
	}
	
	public String getMobilePhone(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",false));
	}
	
	public String getFax(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.fax",false));
	}
	
	public String getEmail(){
		return browser.getTextFieldValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.email",false));
	}
	
	public String getContactPreference(){
		return browser.getDropdownListValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference",false));
	}
	
	public String getPreferredContactTime(){
		return browser.getDropdownListValue(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime",false));
	}
	
	public String getGender(){
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]",false));
	}
	
	public String getEthnicity(){
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5032\\]",false));
	}
	
	public String getSolicitationIndicator(){
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",false));
	}
	
	public String getLifetimeSlogan(){
		return browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5034\\]",false));
	}
	
	public String getPhyAddress(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.address",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyZip(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyCountry(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text = browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.country",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyValidStatus(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus\\.name",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhySuppAddress(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyCity(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.city",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyState(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.state",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getPhyCounty(){
		IHtmlObject[] table = getPhysicalAddressTable();
		String text =browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.county",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailAddress(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.address",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailZip(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailCountry(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text = browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.country",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailValidStatus(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus\\.name",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailSuppAddress(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailCity(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getTextFieldValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.city",false)), table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailState(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.state",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getMailCounty(){
		IHtmlObject[] table = getMaillingAddressTable();
		String text =browser.getDropdownListValue(Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.county",false)), 0, table[0]);
		Browser.unregister(table);
		return text;
	}
	
	public String getIdentifierType(){
		return browser.getDropdownListValue(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType",false));
	}
	
	public String getIdentifierNum(){
		return browser.getTextFieldValue(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber",false));
	}
	
	public String getIdentifierCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression("(CustomerIdentifierView)?IdentifierView-\\d+(\\.identifier)?\\.country",false));
	}
	
	public String getIdentifierState(){
		return browser.getDropdownListValue(".id", new RegularExpression("(CustomerIdentifer|Identifier)View-\\d+(\\.identifier)?\\.stateProvince",false));
	}
	
	public Customer getCustomerInfo(){
		Customer custInfo = new Customer();
		custInfo.customerClass = this.getCustomerClass();
		custInfo.fName = this.getFirstName();
		custInfo.mName = this.getMiddleName();
		custInfo.lName = this.getLastName();
		custInfo.suffix = this.getSuffix();
		custInfo.dateOfBirth = this.getDateOfBirth();
		custInfo.overideReason = this.getOverrideReason();
		custInfo.hPhone = this.getHomePhone();
		custInfo.bPhone = this.getBusinessPhone();
		custInfo.mPhone = this.getMobilePhone();
		custInfo.fax = this.getFax();
		custInfo.email = this.getEmail();
		custInfo.custGender = this.getGender();
		custInfo.ethnicity = this.getEthnicity();
		custInfo.solicitationIndcator = this.getSolicitationIndicator();
		
		custInfo.physicalAddr.address = this.getPhyAddress();
		custInfo.physicalAddr.supplementalAddr = this.getPhySuppAddress();
		custInfo.physicalAddr.city = this.getPhyCity();
		custInfo.physicalAddr.county = this.getPhyCounty();
		custInfo.physicalAddr.state = this.getPhyState();
		custInfo.physicalAddr.zip = this.getPhyZip();
		custInfo.physicalAddr.country = this.getPhyCountry();
		custInfo.physicalAddr.status = this.getPhyValidStatus();
		
		custInfo.mailingAddr.address = this.getMailAddress();
		custInfo.mailingAddr.supplementalAddr = this.getMailSuppAddress();
		custInfo.mailingAddr.city = this.getMailCity();
		custInfo.mailingAddr.county = this.getMailCounty();
		custInfo.mailingAddr.state = this.getMailState();
		custInfo.mailingAddr.zip = this.getMailZip();
		custInfo.mailingAddr.country = this.getMailCountry();
		custInfo.mailingAddr.status = this.getMailValidStatus();
		
		custInfo.identifier.identifierType = this.getIdentifierType();
		custInfo.identifier.identifierNum = this.getIdentifierNum();
		if(this.checkIdentifierCountryExist()){
			custInfo.identifier.country = this.getIdentifierCountry();
		}
		
		if(this.checkIdentifierStateExist()){
			custInfo.identifier.state = this.getIdentifierState();
		}
		
		return custInfo;
	}
	
	public boolean compareCustomerNameInfo(Customer custInfo){
		boolean result = true;
		Customer actCust = this.getCustomerInfo();
		
		result &= MiscFunctions.compareResult("Customer Class", custInfo.customerClass,actCust.customerClass);
		result &= MiscFunctions.compareResult("First Name", custInfo.fName , actCust.fName);
		result &= MiscFunctions.compareResult("Middle Name", custInfo.mName, actCust.mName);
		result &= MiscFunctions.compareResult("Last Name", custInfo.lName, actCust.lName);
		result &= MiscFunctions.compareResult("Suffix", custInfo.suffix, actCust.suffix);
		result &= MiscFunctions.compareResult("Override Reason", custInfo.overideReason, actCust.overideReason);
//		if(custInfo.hPhone.matches("\\d{10}")){
//			custInfo.hPhone = "(" + custInfo.hPhone.substring(0, 3)+")" + " " 
//			               + custInfo.hPhone.substring(3, 6) + "-"
//			               + custInfo.hPhone.substring(6);
//		}
		result &= MiscFunctions.compareResult("Home Phone", custInfo.hPhone,actCust.hPhone);
//		if(custInfo.bPhone.matches("\\d{10}")){
//			custInfo.bPhone = "(" + custInfo.bPhone.substring(0, 3)+")" + " " 
//			               + custInfo.bPhone.substring(3, 6) + "-"
//			               + custInfo.bPhone.substring(6);
//		}
		result &= MiscFunctions.compareResult("Business Phone", custInfo.bPhone,actCust.bPhone );
//		if(custInfo.mPhone.matches("\\d{10}")){
//			custInfo.mPhone = "(" + custInfo.mPhone.substring(0, 3)+")" + " " 
//			               + custInfo.mPhone.substring(3, 6) + "-"
//			               + custInfo.mPhone.substring(6);
//		}
		result &= MiscFunctions.compareResult("Mobile Phone", actCust.mPhone, custInfo.mPhone);
		result &= MiscFunctions.compareResult("Fax", custInfo.fax, actCust.fax);
		result &= MiscFunctions.compareResult("Email", custInfo.email, actCust.email);
		result &= MiscFunctions.compareResult("Gender", custInfo.custGender, actCust.custGender);
		result &= MiscFunctions.compareResult("Ethnicity", custInfo.ethnicity, actCust.ethnicity);
		result &= MiscFunctions.compareResult("Solicitation Indicator", custInfo.solicitationIndcator, actCust.solicitationIndcator);
		result &= MiscFunctions.compareResult("Physical Address", custInfo.physicalAddr.address, actCust.physicalAddr.address);
		result &= MiscFunctions.compareResult("Physical Zip", custInfo.physicalAddr.zip, actCust.physicalAddr.zip);
		result &= MiscFunctions.compareResult("Physical Country", custInfo.physicalAddr.country, actCust.physicalAddr.country);
		result &= MiscFunctions.compareResult("Physical status", custInfo.physicalAddr.status, actCust.physicalAddr.status);
		result &= MiscFunctions.compareResult("Physical Supplemental Address", custInfo.physicalAddr.supplementalAddr, actCust.physicalAddr.supplementalAddr);
		result &= MiscFunctions.compareResult("Physical City", custInfo.physicalAddr.city, actCust.physicalAddr.city);
		result &= MiscFunctions.compareResult("Physical State", custInfo.physicalAddr.state, actCust.physicalAddr.state);
		result &= MiscFunctions.compareResult("Physical County", custInfo.physicalAddr.county, actCust.physicalAddr.county);
		result &= MiscFunctions.compareResult("Mailing Address", custInfo.mailingAddr.address, actCust.mailingAddr.address);
		result &= MiscFunctions.compareResult("Mailing Zip", custInfo.mailingAddr.zip, actCust.mailingAddr.zip);
		result &= MiscFunctions.compareResult("Mailing Country", custInfo.mailingAddr.country, actCust.mailingAddr.country);
		result &= MiscFunctions.compareResult("Mailing status", custInfo.mailingAddr.status, actCust.mailingAddr.status);
		result &= MiscFunctions.compareResult("Mailing Supplemental Address", custInfo.mailingAddr.supplementalAddr, actCust.mailingAddr.supplementalAddr);
		result &= MiscFunctions.compareResult("Mailing City", custInfo.mailingAddr.city, actCust.mailingAddr.city);
		result &= MiscFunctions.compareResult("Mailing State", custInfo.mailingAddr.state, actCust.mailingAddr.state);
		result &= MiscFunctions.compareResult("Mailing County", custInfo.mailingAddr.county, actCust.mailingAddr.county);
		result &= MiscFunctions.compareResult("Identifier Type", custInfo.identifier.identifierType, actCust.identifier.identifierType);
		result &= MiscFunctions.compareResult("Identifier Number", custInfo.identifier.identifierNum, actCust.identifier.identifierNum);
		result &= MiscFunctions.compareResult("Identifier Country", custInfo.identifier.country, actCust.identifier.country);
		result &= MiscFunctions.compareResult("Identifier State", custInfo.identifier.state, actCust.identifier.state);
		
		return result;
	}
		
	// check if Weight text filed exist for AB Contract. By Jane
	public boolean isWeightTextFieldExist() {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5042\\]", false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setWeight(String weight) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5042\\]", false);
		browser.setTextField(".id", idPattern, weight);
	}
	
	public boolean isGenderListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false));
	}
	
	public boolean isHeightTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5043\\]", false));
	}
}

