/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description: HF Web Update Account Page.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date Mar 5, 2013
 */
public class HFUpdateAccountPage extends HFMyAccountPanel {
	public final String FIELDNAME_EMAIL = "Email Address";
	public final String FIELDNAME_PW = "Password";
	public final String FIELDNAME_REPW = "Retype Password";
	public final String FIELDNAME_FNAME = "First Name";
	public final String FIELDNAME_LNAME = "Last Name";
	public final String FIELDNAME_MNAME = "Middle Name|Initial";
	public final String FIELDNAME_SUFFIX = "Suffix";
	public final String FIELDNAME_DOB = "Date of Birth";
	public final String FIELDNAME_GENDER = "Gender";
	public final String FIELDNAME_EYECOLOR = "Eye Colour";
	public final String FIELDNAME_HAIRCOLOR = "Hair Colour";
	public final String FIELDNAME_HEIGHT = "Height";
	public final String FIELDNAME_STREET = "Street Address";
	public final String FIELDNAME_SUITE = "Suite, Apt #";
	public final String FIELDNAME_CITY = "City, Town of|City";
	public final String FIELDNAME_COUNTY = "County";
	public final String FIELDNAME_HPHONE = "Home Phone #";
	public final String FIELDNAME_WPHONE = "Work Phone #";
	public final String FIELDNAME_EXT = "Extension";

	private String mailCountryZipReg = "^AmailAddrCountryZip.*";
	private String mailStreetCityReg = "^AmailAddrStreetCity.*";
	private String mailStateCountyReg = "^AmailAddrStateCounty.*";
	private String homeCountryZipReg = "^AphAddrCountryZip.*";
	private String homeStreetCityReg = "^AphAddrStreetCity.*";
	private String homeStateCountyReg = "^AphAddrStateCounty.*";
	private String homePhoneReg = "^AhomePhone.*";
	private String cellPhoneReg = "^AcellPhone.*";
	private String faxReg = "^AfaxPhone.*";
	private String preContactNumReg = "^AprefContactNumber.*";
	private String preContactTimeReg = "^AprefContactTime.*";
	private String homeAddrDiffMailCheckboxId = "LhfProfileKit_phAddrLayout_phAddr";

	private static HFUpdateAccountPage _instance = null;

	public static HFUpdateAccountPage getInstance() {
		if (null == _instance)
			_instance = new HFUpdateAccountPage();

		return _instance;
	}

	protected HFUpdateAccountPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "createProfileForm");
	}

	/**Page Object Property Definition Begin */
	protected Property[] custDetailsAttrsDivProp() {
		return Property.toPropertyArray(".id", "hfProfileKit_custDetails_attrs");
	}
	
	protected Property[] emailAddressProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".className", "EmailBoxRenderer");
	}
	/**Page Object Property Definition END */
	
	/** Check if the error message exist */
	public boolean isMsgExist(String msg) {
		return browser
				.checkHtmlObjectExists(".className", new RegularExpression(
						"(error_item|confirmationMessage|(msg topofpage error))", false), ".text",
						new RegularExpression(msg, false));
	}

	/** Verify Error Message exist */
	public void verifyMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should "
					+ info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}

	/** Get the object id by the related label */
	private String getObjIDByLabel(String labelReg, String labelForReg) {
		IHtmlObject[] objs;
		if (StringUtil.isEmpty(labelForReg)) {
			objs = browser.getHtmlObject(".class", "HTML.Label", ".text",
					new RegularExpression(labelReg, false));
		} else {
			objs = browser.getHtmlObject(Property.toPropertyArray(".class",
					"HTML.Label", ".text", new RegularExpression(labelReg,
							false), ".for", new RegularExpression(labelForReg,
							false)));
		}

		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the div for "
					+ labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}

	private String getObjIDByLabel(String labelReg) {
		return this.getObjIDByLabel(labelReg, null);
	}

	// Get and set Customer info
	public String getFirstName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_FNAME));
	}

	public void setFirstName(String fName) {
		String id = this.getObjIDByLabel(FIELDNAME_FNAME);
		browser.setTextField(".id", id, fName);
	}

	public String getMiddleName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_MNAME));
	}

	public void setMiddleName(String mName) {
		String id = this.getObjIDByLabel(FIELDNAME_MNAME);
		browser.setTextField(".id", id, mName);
	}

	public String getLastName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_LNAME));
	}

	public void setLastName(String lName) {
		String id = this.getObjIDByLabel(FIELDNAME_LNAME);
		browser.setTextField(".id", id, lName);
	}

	public String getSuffix() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_SUFFIX));
	}

	public void selectSuffix(String suffix) {
		String id = this.getObjIDByLabel(FIELDNAME_SUFFIX);
		browser.selectDropdownList(".id", id, suffix);
	}

	/**Customer Details section Begin */
	public void setEmailAddrInCustDetailsSec(String email) {
		browser.setTextField(Property.atList(this.custDetailsAttrsDivProp(), this.emailAddressProp()), email);
	}
	
	public String getEmailAddrInCustDetailsSec() {
		IHtmlObject[] objs = browser.getHtmlObject(this.custDetailsAttrsDivProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("Email Address in Customer Details section doesn't exit!");
		}
		String text = browser.getTextFieldValue(this.emailAddressProp(), objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	public void verifyEmailAddrInCustDetailsSec(String exp) {
		String actual = this.getEmailAddrInCustDetailsSec();
		if (!exp.equals(actual)) {
			throw new ErrorOnPageException("Email address in Customer Details section is wrong!", exp, actual);
		}
		logger.info("---Successfully verify email address in customer details section as " + exp);
	}
	
	public String getDateOfBirth() {
		String id = this.getObjIDByLabel(FIELDNAME_DOB);
		return browser.getTextFieldValue(".id", id);
	}

	public void setDateOfBirth(String dateOfBirth) {
		String id = this.getObjIDByLabel(FIELDNAME_DOB);
		browser.setTextField(".id", id, dateOfBirth, 0, IText.Event.LOSEFOCUS);
	}

	public String getGender() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_GENDER));
	}

	public String getZipCode(){
		return browser.getTextFieldValue(".id", new RegularExpression(
				mailCountryZipReg, false));
	}
	
	public void selectGender(String gender) {
		String id = this.getObjIDByLabel(FIELDNAME_GENDER);
		browser.selectDropdownList(".id", id, gender);
	}

	public String getEyeColor() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_EYECOLOR));
	}

	public void selectEyeColour(String eyeColour) {
		String id = this.getObjIDByLabel(FIELDNAME_EYECOLOR);
		browser.selectDropdownList(".id", id, eyeColour);
	}

	public String getHairColor() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_HAIRCOLOR));
	}

	public void selectHairColour(String hairColour) {
		String id = this.getObjIDByLabel(FIELDNAME_HAIRCOLOR);
		browser.selectDropdownList(".id", id, hairColour);
	}

	public String getHeightFt() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_HEIGHT) + "01");
	}

	public void setHeightFt(String value) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_HEIGHT)
				+ "01", value);
	}

	public String getHeightIn() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_HEIGHT) + "02");
	}

	public void setHeightIn(String value) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_HEIGHT)
				+ "02", value);
	}
	/**Customer Details section END */
	
	public String getMailingCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				mailCountryZipReg, false));
	}

	public void selectMailingCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression(
				mailCountryZipReg, false), country);
	}

	public String getMailingZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				mailCountryZipReg, false));
	}

	public void setMailingZip(String zip) {
		browser.setTextField(".id", new RegularExpression(mailCountryZipReg,
				false), zip);
	}

	public String getMailingStreetAddress() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_STREET, mailStreetCityReg));
	}

	public void setMailingStreetAddress(String street) {
		String id = this.getObjIDByLabel(FIELDNAME_STREET, mailStreetCityReg);
		browser.setTextField(".id", id, street);
	}

	public String getMailingCity() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_CITY, mailStreetCityReg));
	}

	public void setMailingCity(String city) {
		String id = this.getObjIDByLabel(FIELDNAME_CITY, mailStreetCityReg);
		browser.setTextField(".id", id, city);
	}

	public String getMailingState() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				mailStateCountyReg, false));
	}

	public void selectMailingState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(
				mailStateCountyReg, false), state);
	}

	private String getSelectedAddressCounty(String labelReg,
			String labelForReg, String addrType) {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				this.getObjIDByLabel(labelReg, labelForReg));
		String value = "";
		if (objs.length < 1) {
			logger.info("No County dropdown list in the page for " + addrType);
		} else {
			List<String> selectedOptions = ((ISelect) objs[0])
					.getSelectedTexts();
			if (selectedOptions.size() < 1) {
				logger.info("No options in the County dropdown list for "
						+ addrType);
			} else {
				value = selectedOptions.get(0);
			}
		}
		Browser.unregister(objs);
		return value;
	}

	public String getMailingCounty() {
		return this.getSelectedAddressCounty(FIELDNAME_COUNTY,
				mailStateCountyReg, "Mailing Address");
	}

	public void selectMailingCounty(String county) {
		String id = this.getObjIDByLabel(FIELDNAME_COUNTY, mailStateCountyReg);
		browser.selectDropdownList(".id", id, county);
	}

	public Address getMailingAddress() {
		Address addr = new Address();
		addr.country = this.getMailingCountry();
		addr.zip = this.getMailingZip();
		addr.address = this.getMailingStreetAddress();
		addr.city = this.getMailingCity();
		addr.state = this.getMailingState();
		if (addr.country.equalsIgnoreCase("United States"))
			addr.county = this.getMailingCounty();
		return addr;
	}

	public void setupMailingAddress(Address address) {
		this.selectMailingCountry(address.country);
		this.waitLoading();
		this.setMailingZip(address.zip);
		this.setMailingStreetAddress(address.address);
		this.setMailingCity(address.city);
		this.selectMailingState(address.state);
		this.waitLoading();
		if (!StringUtil.isEmpty(address.county))
			this.selectMailingCounty(address.county);
	}

	public boolean isHomeAddrDiffMailAddrChecked() {
		return browser.isCheckBoxSelected(".id", homeAddrDiffMailCheckboxId);
	}

	public void selectHomeAddDiffFromMailAdd() {
		browser.selectCheckBox(".id", homeAddrDiffMailCheckboxId);
	}

	public void unselectHomeAddDiffFromMailAdd() {
		browser.unSelectCheckBox(".id", homeAddrDiffMailCheckboxId);
	}

	public String getHomeCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				homeCountryZipReg, false));
	}

	public void selectHomeCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression(
				homeCountryZipReg, false), country);
	}

	public String getHomeZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				homeCountryZipReg, false));
	}

	public void setHomeZip(String zip) {
		browser.setTextField(".id", new RegularExpression(homeCountryZipReg,
				false), zip);
	}

	public String getHomeStreetAddress() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_STREET, homeStreetCityReg));
	}

	public void setHomeStreetAddress(String street) {
		String id = this.getObjIDByLabel(FIELDNAME_STREET, homeStreetCityReg);
		browser.setTextField(".id", id, street);
	}

	public String getHomeCity() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_CITY, homeStreetCityReg));
	}

	public void setHomeCity(String city) {
		String id = this.getObjIDByLabel(FIELDNAME_CITY, homeStreetCityReg);
		browser.setTextField(".id", id, city);
	}

	public String getHomeState() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				homeStateCountyReg, false));
	}

	public void selectHomeState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(
				homeStateCountyReg, false), state);
	}

	public String getHomeCounty() {
		return this.getSelectedAddressCounty(FIELDNAME_COUNTY,
				homeStateCountyReg, "Home Address");
	}

	public void selectHomeCounty(String county) {
		String id = this.getObjIDByLabel(FIELDNAME_COUNTY, homeStateCountyReg);
		browser.selectDropdownList(".id", id, county);
	}

	public Address getHomeAddress() {
		Address addr = new Address();
		addr.country = this.getHomeCountry();
		addr.zip = this.getHomeZip();
		addr.address = this.getHomeStreetAddress();
		addr.city = this.getHomeCity();
		addr.state = this.getHomeState();
		if (addr.country.equalsIgnoreCase("United States"))
			addr.county = this.getHomeCounty();
		return addr;
	}

	public void setupHomeAddress(Address address) {
		this.selectHomeCountry(address.country);
		this.waitLoading();
		this.setHomeZip(address.zip);
		this.setHomeStreetAddress(address.address);
		this.setHomeCity(address.city);
		this.selectHomeState(address.state);
		this.waitLoading();
		if (!StringUtil.isEmpty(address.county))
			this.selectHomeCounty(address.county);
	}

	public String getHomePhone() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				homePhoneReg, false));
	}

	public void setHomePhone(String value) {
		browser.setTextField(".id", new RegularExpression(homePhoneReg, false),
				value);
	}

	public String getWorkPhone() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_WPHONE));
	}

	public void setWorkPhone(String value) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_WPHONE),
				value);
	}

	public String getWorkExtension() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_EXT));
	}

	public void setWorkExtension(String value) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_EXT), value);
	}

	public String getCellPhone() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				cellPhoneReg, false));
	}

	public void setCellPhone(String value) {
		browser.setTextField(".id", new RegularExpression(cellPhoneReg, false),
				value);
	}

	public String getFax() {
		return browser.getTextFieldValue(".id", new RegularExpression(faxReg,
				false));
	}

	public void setFax(String value) {
		browser.setTextField(".id", new RegularExpression(faxReg, false), value);
	}

	public String getPreContactNumber() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				preContactNumReg, false));
	}

	public void selectPreContactNumber(String value) {
		browser.selectDropdownList(".id", new RegularExpression(
				preContactNumReg, false), value);
	}

	public String getPreContactTime() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				preContactTimeReg, false));
	}

	public void selectPreContactTime(String value) {
		browser.selectDropdownList(".id", new RegularExpression(
				preContactTimeReg, false), value);
	}

	public void clickValidateMailingAddressBtn() {
		browser.clickGuiObject(".id", "mailAddraddrsgrp_validate");
	}

	public boolean isValidateMailingAddressExist() {
		return browser
				.checkHtmlObjectExists(".id", "mailAddraddrsgrp_validate");
	}

	public String getEmail(){
		return browser.getTextFieldValue(".id", this.getObjIDByLabel(FIELDNAME_EMAIL));
	}
	
	public void verifyEmail(String expectedEmail){
		String actualEmail = getEmail();
		if(!expectedEmail.equals(actualEmail)){
			throw new ErrorOnPageException("Email is wrong!", expectedEmail, actualEmail);
		}
		logger.info("Successfully verify email - "+expectedEmail);
	}
	
	public void verifyValidateMailingAddressExist(boolean exp) {
		String msg = exp ? " " : " not ";
		if (exp != this.isValidateMailingAddressExist()) {
			throw new ErrorOnPageException(
					"Validate Address button for Mailing Address should" + msg
							+ "exist!");
		}
		logger.info("Validate Address button for Mailing address " + msg
				+ "exist!");
	}

	public void clickValidateHomeAddressBtn() {
		browser.clickGuiObject(".id", "phAddraddrsgrp_validate");
	}

	public boolean isValidateHomeAddressExist() {
		return browser.checkHtmlObjectExists(".id", "phAddraddrsgrp_validate");
	}

	public void verifyValidateHomeAddressExist(boolean exp) {
		String msg = exp ? " " : " not ";
		if (exp != this.isValidateHomeAddressExist()) {
			throw new ErrorOnPageException(
					"Validate Address button for Home Address should" + msg
							+ "exist!");
		}
		logger.info("Validate Address button for Home address " + msg
				+ "exist!");
	}

	public void validateMailingAddress() {
		this.clickValidateMailingAddressBtn();
		this.waitLoading();
	}

	public void validateMailingAddress(Address addr) {
		logger.info("Input mailing address and validate it...");
		this.setupMailingAddress(addr);
		this.validateMailingAddress();
	}

	public void validateHomeAddress() {
		this.clickValidateHomeAddressBtn();
		this.waitLoading();
	}

	public void validateHomeAddress(Address addr) {
		logger.info("Input home address and validate it...");

		this.setupHomeAddress(addr);
		this.validateHomeAddress();
	}

	public void verifyMailingAddressInfo(Address addr) {
		Address act = this.getMailingAddress();
		if (!act.equals(addr)) {
			throw new ErrorOnPageException("The mailing address info is wrong!");
		}
		logger.info("The mailing address info is correct!");
	}

	public void verifyHomeAddressInfo(Address addr) {
		Address act = this.getHomeAddress();
		if (!act.equals(addr)) {
			throw new ErrorOnPageException("The home address info is wrong!");
		}
		logger.info("The home address info is correct!");
	}

	public List<String> getMailingCountyOptions() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_COUNTY, mailStateCountyReg));
	}

	public boolean isMailingCountyListBlank() {
		List<String> options = this.getMailingCountyOptions();
		return options.size() < 1;
	}

	public void verifyMailingCountyBlank() {
		if (!this.isMailingCountyListBlank()) {
			throw new ErrorOnPageException(
					"Mailing county list should be blank!");
		}
		logger.info("Mailing county list is blank!");
	}

	public List<String> getHomeCountyOptions() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_COUNTY, homeStateCountyReg));
	}

	public boolean isHomeCountyListBlank() {
		List<String> options = this.getHomeCountyOptions();
		return options.size() < 1;
	}

	public void verifyHomeCountyBlank() {
		if (!this.isHomeCountyListBlank()) {
			throw new ErrorOnPageException("Home county list should be blank!");
		}
		logger.info("Home county list is blank!");
	}

	public void verifyDateOfBirth(String value) {
		String actualValue = this.getDateOfBirth();
		if (!value.equals(actualValue)) {
			throw new ErrorOnPageException("Date of birth is wrong!", value,
					actualValue);
		}
		logger.info("Successfully verify date of birth:" + value);
	}

	public void updateCustomerName(String fName, String mName, String lName,
			String suffix) {
		this.setFirstName(fName);
		this.setMiddleName(mName);
		this.setLastName(lName);
		this.selectSuffix(suffix);
	}

	public void updateCustomerDetails(String dateOfBirth, String gender,
			String eyeColour, String hairColour, String heightFt,
			String heightIn) {
		this.setDateOfBirth(dateOfBirth);
		this.selectGender(gender);
		if (this.isEyeColorLabelExist() && this.isEyeColorListExist()) {
			this.selectEyeColour(eyeColour);
		}
		if (this.isHairColorLabelExist() && this.isHairColorListExist()) {
			this.selectHairColour(hairColour);
		}
		if (this.isHeightLabelExist() && this.isHeightFtTextFieldExist()) {
			this.setHeightFt(heightFt);
		}
		if (this.isHeightLabelExist() && this.isHeightInTextFieldExist()) {
			this.setHeightIn(heightIn);
		}
	}

	public void updateContactNumbers(String hPhone, String wPhone,
			String workExt, String cPhone, String fax, String phoContact,
			String contactTime) {
		this.setHomePhone(hPhone);
		this.setWorkPhone(wPhone);
		this.setWorkExtension(workExt);
		this.setCellPhone(cPhone);
		this.setFax(fax);
		this.selectPreContactNumber(phoContact);
		this.selectPreContactTime(contactTime);
	}

	public void clickSubmitButton() {
		browser.clickGuiObject(".id", "submitForm_submitForm");
	}

	public boolean topPageErrorMesDisplays() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"msg topofpage error");
	}

	public void clickAddIdentificationLink() {
		browser.clickGuiObject(".class", "Html.A", ".className", "addIdentLink");
	}

	public void clickUpdateLink(String idenTypeID) {
		Property[] p = Property.toPropertyArray(".class", "Html.A",
				".className", "updateLink", ".href", new RegularExpression(
						"/updateHFIdentifier\\.do\\?identifierTypeID="
								+ idenTypeID + "", false));
		browser.clickGuiObject(p);
	}

	public boolean checkIdenExisting(String idenType) {
		Property[] p1 = Property.toPropertyArray(".id", "identifiers_attrs");
		Property[] p2 = Property.toPropertyArray(".className",
				"attributeField", ".text", new RegularExpression("^" + idenType
						+ ".*", false));
		return browser.checkHtmlObjectDisplayed(Property.atList(p1, p2));
	}

	public void verifyIdenExisting(String idenType, boolean existing) {
		if (existing != checkIdenExisting(idenType)) {
			throw new ErrorOnPageException("Failed to verify idenType:"
					+ idenType + (existing ? " exists" : " doesn't existing"));
		}
		logger.info("Successfully verify idenType:" + idenType
				+ (existing ? " exists" : " doesn't existing"));
	}

	private String getIdenInfo(String idenType) {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				"identifiers_attrs");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV",
				".className", "attributeField", ".text", new RegularExpression(
						idenType + ".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Identification objects can't be found.");
		}

		String value = objs[0].text();
		if (value.contains(":")) {
			value = value.split(":")[1].trim();
		} else {
			value = value.split(idenType)[1].trim();
		}

		Browser.unregister(objs);
		return value;
	}

	public List<String> getAllIdentifiersTypes() {
		List<String> values = new ArrayList<String>();
		Property[] p1 = Property.toPropertyArray(".id", "identifiers_attrs");
		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN",
				".className", "extra");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs.length > 0) {
			for (IHtmlObject obj : objs) {
				values.add(obj.text().replaceAll(":", "").trim());
			}
		} else
			throw new ObjectNotFoundException(
					"Identifiers attrs objects can't be found.");

		Browser.unregister(objs);
		return values;
	}

	public String getIdenNum(String idenType) {
		String value = this.getIdenInfo(idenType);
		if (value.contains(StringUtil.COMMA)) {
			value = value.split(StringUtil.COMMA)[0].trim();
		}
		value = value.replace("Update", "");
		logger.info("Customer#:" + value);
		return value;
	}

	public String getIdenCountryOrState(String idenType) {
		String value = this.getIdenInfo(idenType);
		if (value.contains(StringUtil.COMMA)) {
			value = value.split(StringUtil.COMMA)[1].trim();
			value = value.replace("Update", "");
		}
		logger.info("Identifier - " + idenType + " country or state is" + value);
		return value;
	}

	public String getPageTitle() {
		String title = browser.getObjectText(".class", "Html.DIV", ".id",
				"pagetitle");
		title = title.split("Account")[0] + "Account";
		return title;
	}

	private IHtmlObject[] getUpdateIdenLinks() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".classname", "updateLink");
		if (objs.length < 0) {
			throw new ItemNotFoundException(
					"Can't find Update Identifier Link...");
		}
		return objs;
	}

	public boolean isUpdateIdenLinkHrefExist() {
		IHtmlObject[] objs = this.getUpdateIdenLinks();
		String href = objs[0].getAttributeValue("href");
		Browser.unregister(objs);
		return StringUtil.notEmpty(href);
	}

	public boolean isUpdateIdenLinkTitleExist() {
		IHtmlObject[] objs = this.getUpdateIdenLinks();
		String title = objs[0].title();
		Browser.unregister(objs);
		return StringUtil.notEmpty(title);
	}

	public String getUpdateIdentLinkTitle() {
		IHtmlObject[] objs = this.getUpdateIdenLinks();
		String title = objs[0].title();
		Browser.unregister(objs);
		return title;
	}

	private IHtmlObject[] getAddIdenLinks() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".classname", "addIdentLink");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find Add Identifier Link...");
		}
		return objs;
	}

	public boolean isAddIdenLinkHrefExist() {
		IHtmlObject[] objs = this.getAddIdenLinks();
		String href = objs[0].getAttributeValue("href");
		Browser.unregister(objs);
		return StringUtil.notEmpty(href);
	}

	public boolean isAddIdenLinkTitleExist() {
		IHtmlObject[] objs = this.getAddIdenLinks();
		String title = objs[0].title();
		Browser.unregister(objs);
		return StringUtil.notEmpty(title);
	}

	public String getAddIdentLinkTitle() {
		IHtmlObject[] objs = this.getAddIdenLinks();
		String title = objs[0].title();
		Browser.unregister(objs);
		return title;
	}

	public boolean isAddIdentLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".classname",
				"addIdentLink");
	}

	public String getAddIdentLinkText() {
		return browser.getObjectText(".class", "Html.A", ".classname",
				"addIdentLink");
	}

	/** Below methods are to check elements exist on the page */
	private boolean isObjectLabelExist(String labelReg) {
		return browser.checkHtmlObjectExists(".class", "HTML.Label", ".text",
				new RegularExpression(labelReg, false));
	}
	
	public boolean isSubmitBtnExist() {
		return browser.checkHtmlObjectExists(".id", "submitForm_submitForm");
	}

	public boolean isFirstNameTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_FNAME));
	}

	public boolean isMiddleNameTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_MNAME));
	}

	public boolean isLastNameTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_LNAME));
	}

	public boolean isSuffixListExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_SUFFIX));
	}

	public boolean isDOBTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_DOB));
	}

	public boolean isGenderListExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_GENDER));
	}

	public boolean isEyeColorLabelExist() {
		return this.isObjectLabelExist(FIELDNAME_EYECOLOR);
	}
	
	public boolean isEyeColorListExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_EYECOLOR));
	}
	
	public boolean isHairColorLabelExist() {
		return this.isObjectLabelExist(FIELDNAME_HAIRCOLOR);
	}
	
	public boolean isHairColorListExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_HAIRCOLOR));
	}
	
	public boolean isHeightLabelExist() {
		return this.isObjectLabelExist(FIELDNAME_HEIGHT);
	}
	
	public boolean isHeightFtTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_HEIGHT) + "01");
	}

	public boolean isHeightInTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_HEIGHT) + "02");
	}

	public boolean isMailingCountryListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				mailCountryZipReg, false));
	}

	public boolean isMailingZipTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				mailCountryZipReg, false));
	}

	public boolean isMailingStreetTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_STREET, mailStreetCityReg));
	}

	public boolean isMailingSuiteTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_SUITE, mailStreetCityReg));
	}

	public boolean isMailingCityTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_CITY, mailStreetCityReg));
	}

	public boolean isMailingStateExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				mailStateCountyReg, false));
	}

	public boolean isHomeAddDiffFromMailAddChkEnabled() {
		return browser
				.checkHtmlObjectEnabled(".id", homeAddrDiffMailCheckboxId);
	}

	public boolean isHomePhoneTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				homePhoneReg, false));
	}

	public boolean isWorkPhoneTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_WPHONE));
	}

	public boolean isWorkPhoneExtTextFieldExist() {
		return browser.checkHtmlObjectExists(".id",
				this.getObjIDByLabel(FIELDNAME_EXT));
	}

	public boolean isCellPhoneTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				cellPhoneReg, false));
	}

	public boolean isFaxTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				faxReg, false));
	}

	public boolean isPreContactNumListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				preContactNumReg, false));
	}

	public boolean isPreContactTimeListExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				preContactTimeReg, false));
	}

	public boolean isAllFieldsExist(boolean exp) {
		boolean result = true;
		result &= MiscFunctions.compareResult("first name field editable", exp,
				this.isFirstNameTextFieldExist());
		result &= MiscFunctions.compareResult("middle name field editable",
				exp, this.isMiddleNameTextFieldExist());
		result &= MiscFunctions.compareResult("last name field editable", exp,
				this.isLastNameTextFieldExist());
		result &= MiscFunctions.compareResult("suffix list field editable",
				exp, this.isSuffixListExist());
		result &= MiscFunctions.compareResult("DOB field editable", exp,
				this.isDOBTextFieldExist());
		result &= MiscFunctions.compareResult("Gender field editable", exp,
				this.isGenderListExist());
		result &= MiscFunctions.compareResult("Eye color field editable", exp,
				this.isEyeColorListExist());
		result &= MiscFunctions.compareResult("Hair color field editable", exp,
				this.isHairColorListExist());
		result &= MiscFunctions.compareResult("Height Ft field editable", exp,
				this.isHeightFtTextFieldExist());
		result &= MiscFunctions.compareResult("Height In field editable", exp,
				this.isHeightInTextFieldExist());
		result &= MiscFunctions.compareResult("Mailing Country field editable",
				exp, this.isMailingCountryListExist());
		result &= MiscFunctions.compareResult("Mailing Zip field editable",
				exp, this.isMailingZipTextFieldExist());
		result &= MiscFunctions.compareResult("Mailing Street field editable",
				exp, this.isMailingStreetTextFieldExist());
		result &= MiscFunctions.compareResult("Mailing Suite field editable",
				exp, this.isMailingSuiteTextFieldExist());
		result &= MiscFunctions.compareResult("Mailing City field editable",
				exp, this.isMailingCityTextFieldExist());
		result &= MiscFunctions.compareResult("Mailing State field editable",
				exp, this.isMailingStateExist());
		result &= MiscFunctions.compareResult(
				"Show Home Address checkbox enabled", exp,
				this.isHomeAddDiffFromMailAddChkEnabled());
		result &= MiscFunctions.compareResult("Home phone editable", exp,
				this.isHomePhoneTextFieldExist());
		result &= MiscFunctions.compareResult("Work phone editable", exp,
				this.isWorkPhoneTextFieldExist());
		result &= MiscFunctions.compareResult("Work phone extension editable",
				exp, this.isWorkPhoneExtTextFieldExist());
		result &= MiscFunctions.compareResult("Cell phone editable", exp,
				this.isCellPhoneTextFieldExist());
		result &= MiscFunctions.compareResult("Fax editable", exp,
				this.isFaxTextFieldExist());
		result &= MiscFunctions.compareResult(
				"Preferred contact number editable", exp,
				this.isPreContactNumListExist());
		result &= MiscFunctions.compareResult(
				"Preferred contact time editable", exp,
				this.isPreContactTimeListExist());
		return result;
	}

	public boolean isImportantMsgExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",
				".classname", "msgImportant");
	}

	public String getImportantMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname",
				"msgImportant");
	}

	public Customer getAccountInfo() {
		Customer cus = new Customer();
		cus.fName = this.getFirstName();
		cus.lName = this.getLastName();
		cus.mName = this.getMiddleName();
		cus.suffix = this.getSuffix();
		cus.dateOfBirth = this.getDateOfBirth();
		cus.custGender = this.getGender();
		if (this.isEyeColorLabelExist() && this.isEyeColorListExist()) {
			cus.eyeColor = this.getEyeColor();
		}
		if (this.isHairColorLabelExist() && this.isHairColorListExist()) {
			cus.hairColor = this.getHairColor();
		}
		if (this.isHeightLabelExist() && this.isHeightFtTextFieldExist()) {
			cus.heightFt = this.getHeightFt();
		}
		if (this.isHeightLabelExist() && this.isHeightInTextFieldExist()) {
			cus.heightIn = this.getHeightIn();
		}
		cus.mailingAddr = this.getMailingAddress();
		cus.mailAddrAsPhy = !this.isHomeAddrDiffMailAddrChecked();
		if (!cus.mailAddrAsPhy) {
			cus.physicalAddr = this.getHomeAddress();
		}
		cus.hPhone = this.getHomePhone();
		cus.bPhone = this.getWorkPhone();
		cus.workExtension = this.getWorkExtension();
		cus.mPhone = this.getCellPhone();
		cus.fax = this.getFax();
		cus.contactTime = this.getPreContactTime();
		cus.phoneContact = this.getPreContactNumber();
		return cus;
	}

	public void verifyAccountInfo(Customer cus) {
		Customer actCus = this.getAccountInfo();
		boolean result = true;
		result &= MiscFunctions.compareString("First Name", cus.fName,
				actCus.fName);
		result &= MiscFunctions.compareString("Middle Name", cus.mName,
				actCus.mName);
		result &= MiscFunctions.compareString("Last Name", cus.lName,
				actCus.lName);
		if (StringUtil.notEmpty(cus.suffix))
			result &= MiscFunctions.compareString("Suffix", cus.suffix,
					actCus.suffix);
		result &= MiscFunctions.compareString("DateOfBirth",
//				DateFunctions.formatDate(cus.dateOfBirth, "yyyy-MM-dd"),
				cus.dateOfBirth,
				actCus.dateOfBirth);
		result &= MiscFunctions.compareString("Gender", cus.custGender,
				actCus.custGender);
		result &= MiscFunctions.compareString("Eye Color", cus.eyeColor,
				actCus.eyeColor);
		result &= MiscFunctions.compareString("Hair Color", cus.hairColor,
				actCus.hairColor);
		result &= MiscFunctions.compareString("Height Ft", cus.heightFt,
				actCus.heightFt);
		result &= MiscFunctions.compareString("Height In", cus.heightIn,
				actCus.heightIn);
		result &= cus.mailingAddr.equals(actCus.mailingAddr);
		result &= MiscFunctions.compareResult(
				"Home Address different from mailing address checkbox status",
				cus.mailAddrAsPhy, actCus.mailAddrAsPhy);
		if (!cus.mailAddrAsPhy) {
			result &= cus.physicalAddr.equals(actCus.physicalAddr);
		}
		result &= MiscFunctions.compareString("Home Phone", cus.hPhone,
				actCus.hPhone.replaceAll("\\(|\\)|-|\\s", ""));
		result &= MiscFunctions.compareString("Work Phone", cus.bPhone,
				actCus.bPhone);
		result &= MiscFunctions.compareString("Work Extension",
				cus.workExtension, actCus.workExtension);
		result &= MiscFunctions.compareString("Cell Phone", cus.mPhone,
				actCus.mPhone.replaceAll("\\(|\\)|-|\\s", ""));
		result &= MiscFunctions.compareString("Fax", cus.fax, actCus.fax.replaceAll("\\(|\\)|-|\\s", ""));
		result &= MiscFunctions.compareString("Preferred contact number",
				cus.phoneContact, actCus.phoneContact);
		result &= MiscFunctions.compareString("Preferred contact time",
				cus.contactTime, actCus.contactTime);
		if (!result)
			throw new ErrorOnPageException(
					"Customer info does not match between Create Account and Update Account pages. Please check log for more details.");

		logger.info("---Verify customer info successfully in Update Account Page.");
	}

	public String getHALID() {
		return this.getIdenNum(OrmsConstants.IDENT_TYPE_HAL);
	}

	public void verifyHALID(String num) {
		String actNum = this.getHALID();
		if (!num.equals(actNum))
			throw new ErrorOnPageException("HALID on the page is wrong!", num,
					actNum);
		logger.info("--Verify HALID on Update Account page correctly as " + num);
	}

	public void verifyIdentifierInfo(CustomerIdentifier identifier,
			String expMaskedNum) {
		if (StringUtil.isEmpty(identifier.identTypeFullNm)) {
			identifier.identTypeFullNm = identifier.identifierType;
		}
		String idenInfo = this.getIdenInfo(identifier.identTypeFullNm);
		String expInfo = expMaskedNum;
		if (StringUtil.notEmpty(identifier.stateCode)) {
			expInfo += ", " + identifier.stateCode;
		} else if (StringUtil.notEmpty(identifier.country)) {
			expInfo += ", " + identifier.country;
		}
		if (!idenInfo.contains(expInfo)) {
			throw new ErrorOnPageException(
					"Identifier info is wrong on update account page.",
					expInfo, idenInfo);
		}

		logger.info("--Verify Identifier info is correct!");
	}

	public void setCustProfile(Customer cus) {
		this.updateCustomerName(cus.fName, cus.mName, cus.lName, cus.suffix);
		this.updateCustomerDetails(cus.dateOfBirth, cus.custGender,
				cus.eyeColor, cus.hairColor, cus.heightFt, cus.heightIn);
		this.setupMailingAddress(cus.mailingAddr);
		if (!cus.mailAddrAsPhy) {
			this.selectHomeAddDiffFromMailAdd();
			this.setupHomeAddress(cus.physicalAddr);
		} else {
			this.unselectHomeAddDiffFromMailAdd();
		}
		this.updateContactNumbers(cus.hPhone, cus.bPhone, cus.workExtension,
				cus.mPhone, cus.fax, cus.phoneContact, cus.contactTime);
	}

	public String getIdentifiersFieldText() {
		return browser.getObjectText(".id", "identifiers_attrs");
	}
}
