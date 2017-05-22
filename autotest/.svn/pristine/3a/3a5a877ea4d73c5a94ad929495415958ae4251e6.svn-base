/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description: HF Web Create New Account page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date Feb 19, 2013
 */
public class HFCreateAccountPage extends HFHeaderBar {
	public final String FIELDNAME_EMAIL = "Email Address";
	public final String FIELDNAME_PW = "Password";
	public final String FIELDNAME_REPW = "Retype Password";

	// "Customer Name" related label
	public final String FIELDNAME_FNAME = "First Name";
	public final String FIELDNAME_MNAME = "(Middle Name|Initial)";
	public final String FIELDNAME_LNAME = "Last Name";
	public final String FIELDNAME_SUFFIX = "Suffix";

	public final String FIELDNAME_DOB = "Date of Birth";

	// "Customer attributes" related label
	public final String FIELDNAME_GENDER = "Gender";
	public final String FIELDNAME_EYECOLOR = "Eye Colo(u)?r";
	public final String FIELDNAME_HAIRCOLOR = "Hair Colo(u)?r";
	public final String FIELDNAME_HEIGHT = "Height";
	public final String FIELDNAME_WEIGHT = "Weight \\(kg\\)";

	public final String FIELDNAME_ZIP = "Zip code";
	public final String FIELDNAME_STREET = "Street Address(/P.O. Box/Rural Route)?";
	public final String FIELDNAME_CITY = "City(, Town of)?";
	public final String FIELDNAME_COUNTY = "County";

	// "Contact Numbers" related label
	public final String FIELDNAME_HPHONE = "Home Phone #";
	public final String FIELDNAME_WPHONE = "Work Phone #";
	public final String FIELDNAME_WPHONE_EXTENSION = "Extension";
	public final String FIELDNAME_CPHONE = "Cell Phone #";
	public final String FIELDNAME_FAX = "Fax #";
	public final String FIELDNAME_PREFERREDCONTRACTNUM = "Preferred contact Number";
	public final String FIELDNAME_PREFERREDCONTRACTTIME = "Preferred contact Time";

	public final String FIELDNAME_IDENT_TYPE = "Identification Type";
	public final String FIELDNAME_IDENT_STATE = "(Province/State|State/Province|State)";
	public final String FIELDNAME_IDENT_COUNTRY = "Country";
	private final String IDENT_ID_REGX = "^AcustIdentifiersDynamic_(-)?\\d+";
	private final String FIELDNAME_STREET_LABEL_FOR_REGX = "AmailAddrStreetCity.*";
	private final String DIV_IDEN_ID = "hfProfileKit_custIdentifiersDynamic_attrs";

	private final String preferredContactNumIDRegx = "AprefContactNumber_\\d+";
	private final String preferredContactTimeIDRegx = "AprefContactTime_\\d+";
  
	private final String idenStateLabel = "State/Province";
	
	private static HFCreateAccountPage _instance = null;

	public static HFCreateAccountPage getInstance() {
		if (null == _instance)
			_instance = new HFCreateAccountPage();

		return _instance;
	}

	protected HFCreateAccountPage() {
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "submitForm_submitForm");
	}

	/** Page Object Property Definition Begin */
	protected Property[] custDetailsAttrsDivProp() {
		return Property
				.toPropertyArray(".id", "hfProfileKit_custDetails_attrs");
	}

	protected Property[] emailAddressProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text",
				".className", "EmailBoxRenderer");
	}

	protected Property[] custIdentDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id",
				"custIdentifiersgroup");
	}

	protected Property[] sectionLegendProp() {
		return Property.toPropertyArray(".class", "Html.legend");
	}

	protected Property[] clearLinkDivProp() {
		return Property.toPropertyArray(".id", "clearLnk");
	}

	protected Property[] clearLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Clear");
	}

	protected Property[] dateOfBirth_Year(){
		return Property.toPropertyArray(".id", new RegularExpression("AcustDetails_\\d+_year", false));
	}
	
	protected Property[] dateOfBirth_Month(){
		return Property.toPropertyArray(".id", new RegularExpression("AcustDetails_\\d+_month", false));
	}
	
	protected Property[] dateOfBirth_Day(){
		return Property.toPropertyArray(".id", new RegularExpression("AcustDetails_\\d+_day", false));
	}
	
	public boolean isDateOfBirthYearExist(){
		return browser.checkHtmlObjectExists(dateOfBirth_Year());
	}
	/** Page Object Property Definition END */

	public void clickCreateAccount() {
		browser.clickGuiObject(".id", "submitForm_submitForm");
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
			throw new ErrorOnPageException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}

	public boolean isObjIDByLabelExisting(String labelReg, String labelForReg) {
		IHtmlObject[] objs;
		boolean existing = true;

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
			existing = false;
		}

		Browser.unregister(objs);
		return existing;
	}

	private boolean isObjIDByLabelExisting(String labelReg) {
		return this.isObjIDByLabelExisting(labelReg, null);
	}

	private String getObjIDByLabel(String labelReg) {
		return this.getObjIDByLabel(labelReg, null);
	}

	public boolean isEmailAddressExisting() {
		return browser.checkHtmlObjectExists(".className", "EmailBoxRenderer");
	}

	public boolean isEmailAddressExistInCustDetailsSec() {
		return browser.checkHtmlObjectExists(Property.atList(
				this.custDetailsAttrsDivProp(), this.emailAddressProp()));
	}

	/** The following methods are for setting account information */
	public void setEmailAddress(String email) {
		browser.setTextField(".className", "EmailBoxRenderer", email);
	}

	public void clearPassword() {
		String id = this.getObjIDByLabel(FIELDNAME_PW);
		IHtmlObject[] objs = browser.getPasswordField(".id", id);
		((IText) objs[0]).clear();
		Browser.unregister(objs);
	}

	public boolean isPasswordExisting() {
		String id = StringUtil.EMPTY;
		if (isObjIDByLabelExisting(FIELDNAME_PW)) {
			id = this.getObjIDByLabel(FIELDNAME_PW);
			return browser.checkHtmlObjectExists(".id", id);
		} else
			return false;
	}

	public void setPassword(String pw) {
		browser.setPasswordField(".id", this.getObjIDByLabel(FIELDNAME_PW), pw);
	}

	public boolean isRetryPasswordExisting() {
		String id = StringUtil.EMPTY;
		if (isObjIDByLabelExisting(FIELDNAME_REPW)) {
			id = this.getObjIDByLabel(FIELDNAME_REPW);
			return browser.checkHtmlObjectExists(".id", id);
		} else
			return false;
	}

	public void setRetypePassword(String pw) {
		browser.setPasswordField(".id", this.getObjIDByLabel(FIELDNAME_REPW),
				pw);
	}

	public void setFirstName(String fname) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_FNAME),
				fname);
	}

	public String getFirstName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_FNAME));
	}

	public void setMiddleName(String mName) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_MNAME),
				mName);
	}

	public String getMiddleName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_MNAME));
	}

	public void setLastName(String lname) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_LNAME),
				lname);
	}

	public String getLastName() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_LNAME));
	}

	public void selectSuffix(String suffix) {
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_SUFFIX), suffix);
	}

	public String getSuffix() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_SUFFIX));
	}

	public void verifySuffix(String expectedValue) {
		String actualValue = getSuffix();
		if (!expectedValue.equals(actualValue)) {
			throw new ErrorOnPageException("Suffix is wrong!", expectedValue,
					actualValue);
		}
		logger.info("Suffix is right. Value is " + expectedValue);
	}

	public List<String> getSuffixes() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_SUFFIX));
	}

	public void verifySuffixes(List<String> expectedValues) {
		List<String> actualValues = getSuffixes();
		if (!expectedValues.toString().equals(actualValues.toString())) {
			throw new ErrorOnPageException("Suffixes are wrong!",
					expectedValues.toString(), actualValues.toString());
		}
		logger.info("Suffixes are right as " + expectedValues.toString());
	}

	public void setDateOfBirth(String dateOfBirth) {
		browser.setTextField(".id", this.getObjIDByLabel(FIELDNAME_DOB),
				dateOfBirth, 0, IText.Event.ONCHANGE, IText.Event.LOSEFOCUS);
	}

	public boolean isDOB_YearExist(){
		return browser.checkHtmlObjectExists(dateOfBirth_Year());
	}
	
	public void setDOB_YearMonthDay(String dob){
		String[] dateOfBirth = null;
		if(dob.contains("-")){
			dateOfBirth = dob.split("-");
		}else dateOfBirth = dob.split("|");
		browser.setTextField(dateOfBirth_Year(), dateOfBirth[0]);
		browser.setTextField(dateOfBirth_Month(), dateOfBirth[1]);
		browser.setTextField(dateOfBirth_Day(), dateOfBirth[2]);
	}
	
	public String getDateOfBirth() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_DOB));
	}
	
	/**
	 * Added by Sara[12092013] this method to get DOB format using  "placeholder"
	 * @return
	 */
	public String getDateOfBirthFormat() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",this.getObjIDByLabel(FIELDNAME_DOB));
		if(objs.length<1) throw new ObjectNotFoundException("Date of birth objects can't be found.");
		String value = objs[0].getProperty("placeholder");
		Browser.unregister(objs);
		return value;
	}
	
	public String getZipCode(){
		return browser.getTextFieldValue(".id", getObjIDByLabel(FIELDNAME_ZIP));
	}

	public void verifyDateOfBirth(String value) {
		String actualValue = StringUtil.EMPTY;
		if(value.matches("YYYY-MM-DD")){
			actualValue = getDateOfBirthFormat();//Sara[12092013] the value and format of DOB both can be found in same object, so get different value based on value parameter
		}else actualValue = this.getDateOfBirth();

		if (!value.equals(actualValue)) {
			throw new ErrorOnPageException("Date of birth is wrong!", value,
					actualValue);
		}
		logger.info("Successfully verify date of birth:" + value);
	}

	public void selectGender(String value) {
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_GENDER), value);
	}

	public String getGender() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_GENDER));
	}

	public List<String> getGenders() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_GENDER));
	}
	
	public boolean isEyeColourExisting() {
		return isObjIDByLabelExisting(FIELDNAME_EYECOLOR);
	}

	public void selectEyeColour(String eyeColor) {
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_EYECOLOR), eyeColor);
	}

	public String getEyeColour() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_EYECOLOR));
	}

	public List<String> getEyeColours() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_EYECOLOR));
	}
	
	public boolean isHairColourExisting() {
		return isObjIDByLabelExisting(FIELDNAME_HAIRCOLOR);
	}

	public void selectHairColour(String hairColor) {
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_HAIRCOLOR), hairColor);
	}

	public String getHairColour() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_HAIRCOLOR));
	}

	public List<String> getHairColours() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_HAIRCOLOR));
	}
	
	public boolean isHeightExisting() {
		return isObjIDByLabelExisting(FIELDNAME_HEIGHT);
	}

	public boolean isWeightExisting() {
		return isObjIDByLabelExisting(FIELDNAME_WEIGHT);
	}
	
	public boolean isHomePhoneExisted() {
		return isObjIDByLabelExisting(FIELDNAME_HPHONE);
	}

	public boolean isWorkPhoneExisted() {
		return isObjIDByLabelExisting(FIELDNAME_WPHONE);
	}

	public boolean isWorkPhoneExtensionExisted() {
		return isObjIDByLabelExisting(FIELDNAME_WPHONE_EXTENSION);
	}

	public boolean isCellPhoneExisted() {
		return isObjIDByLabelExisting(FIELDNAME_CPHONE);
	}

	public boolean isFaxExisted() {
		return isObjIDByLabelExisting(FIELDNAME_FAX);
	}

	public boolean isPreferredContractNumExisted() {
		return isObjIDByLabelExisting(FIELDNAME_PREFERREDCONTRACTNUM);
	}

	public boolean isPreferredContractTimeExisted() {
		return isObjIDByLabelExisting(FIELDNAME_PREFERREDCONTRACTTIME);
	}

	public void setHeight(String ft, String in) {
		String id = this.getObjIDByLabel(FIELDNAME_HEIGHT);
		browser.setTextField(".id", new RegularExpression("^" + id + ".*",
				false), ft, 0);
		browser.setTextField(".id", new RegularExpression("^" + id + ".*",
				false), in, 1);
	}

	public void setHeight_FT(String ft){
		String id = this.getObjIDByLabel(FIELDNAME_HEIGHT);
		browser.setTextField(".id", new RegularExpression("^" + id + ".*",
				false), ft, 0);
	}
	
	public void setHeight_IN(String in){
		String id = this.getObjIDByLabel(FIELDNAME_HEIGHT);
		browser.setTextField(".id", new RegularExpression("^" + id + ".*",
				false), in, 0);
	}
	
	public void setWeight(String weight){
		String id = this.getObjIDByLabel(FIELDNAME_WEIGHT);
		browser.setTextField(".id", new RegularExpression("^" + id + ".*",
				false), weight, 0);
	}
	
	public String getHeight_ft() {
		return browser.getTextFieldValue(".id", new RegularExpression("^"
				+ this.getObjIDByLabel(FIELDNAME_HEIGHT) + ".*", false), 0);
	}

	public String getHeight_in() {
		return browser.getTextFieldValue(".id", new RegularExpression("^"
				+ this.getObjIDByLabel(FIELDNAME_HEIGHT) + ".*", false), 1);
	}

	public String getMailingCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"^AmailAddrCountryZip.*", false));
	}

	public void selectMailingCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^AmailAddrCountryZip.*", false), country);
	}

	public String getMailingZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"^AmailAddrCountryZip.*", false));
	}

	public void setMailingZip(String zip) {
		browser.setTextField(".id", new RegularExpression(
				"^AmailAddrCountryZip.*", false), zip);
	}

	public String getMailingStreetAddress() {
		String id = this.getObjIDByLabel(FIELDNAME_STREET,
				FIELDNAME_STREET_LABEL_FOR_REGX);
		return browser.getTextFieldValue(".id", id);
	}

	public void setMailingStreetAddress(String street) {
		String id = this.getObjIDByLabel(FIELDNAME_STREET,
				FIELDNAME_STREET_LABEL_FOR_REGX);
		browser.setTextField(".id", id, street);
	}

	public String getMailingCity() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_CITY, "^AmailAddrStreetCity.*"));
	}

	public void setMailingCity(String city) {
		String id = this.getObjIDByLabel(FIELDNAME_CITY,
				"^AmailAddrStreetCity.*");
		browser.setTextField(".id", id, city);
	}

	public String getMailingState() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"^AmailAddrStateCounty.*", false));
	}

	public void selectMailingState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^AmailAddrStateCounty.*", false), state);
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
		return value;
	}

	public String getMailingCounty() {
		return this.getSelectedAddressCounty(FIELDNAME_COUNTY,
				"^AmailAddrStateCounty.*", "Mailing Address");
	}

	public void selectMailingCounty(String county) {
		String id = this.getObjIDByLabel(FIELDNAME_COUNTY,
				"^AmailAddrStateCounty.*");
		browser.selectDropdownList(".id", id, county);
	}

	public Address getMailingAddress() {
		Address addr = new Address();
		addr.country = this.getMailingCountry();
		addr.zip = this.getMailingZip();
		addr.address = this.getMailingStreetAddress();
		addr.city = this.getMailingCity();
		addr.state = this.getMailingState();
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

	public void selectHomeAddDiffFromMailAdd() {
		browser.selectCheckBox(".id", "LhfProfileKit_phAddrLayout_phAddr");
	}

	public void unselectHomeAddDiffFromMailAdd() {
		browser.unSelectCheckBox(".id", "LhfProfileKit_phAddrLayout_phAddr");
	}

	public String getHomeCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"^AphAddrCountryZip.*", false));
	}

	public void selectHomeCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^AphAddrCountryZip.*", false), country);
	}

	public String getHomeZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"^AphAddrCountryZip.*", false));
	}

	public void setHomeZip(String zip) {
		browser.setTextField(".id", new RegularExpression(
				"^AphAddrCountryZip.*", false), zip);
	}

	public String getHomeStreetAddress() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_STREET, "^AphAddrStreetCity.*"));
	}

	public void setHomeStreetAddress(String street) {
		String id = this.getObjIDByLabel(FIELDNAME_STREET,
				"^AphAddrStreetCity.*");
		browser.setTextField(".id", id, street);
	}

	public String getHomeCity() {
		return browser.getTextFieldValue(".id",
				this.getObjIDByLabel(FIELDNAME_CITY, "^AphAddrStreetCity.*"));
	}

	public void setHomeCity(String city) {
		String id = this
				.getObjIDByLabel(FIELDNAME_CITY, "^AphAddrStreetCity.*");
		browser.setTextField(".id", id, city);
	}

	public String getHomeState() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"^AphAddrStateCounty.*", false));
	}

	public void selectHomeState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(
				"^AphAddrStateCounty.*", false), state);
	}

	public String getHomeCounty() {
		return this.getSelectedAddressCounty(FIELDNAME_COUNTY,
				"^AphAddrStateCounty.*", "Home Address");
	}

	public void selectHomeCounty(String county) {
		String id = this.getObjIDByLabel(FIELDNAME_COUNTY,
				"^AphAddrStateCounty.*");
		browser.selectDropdownList(".id", id, county);
	}

	public Address getHomeAddress() {
		Address addr = new Address();
		addr.country = this.getHomeCountry();
		addr.zip = this.getHomeZip();
		addr.address = this.getHomeStreetAddress();
		addr.city = this.getHomeCity();
		addr.state = this.getHomeState();
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

	public void setHomePhone(String homeP) {
		browser.setTextField(".id", new RegularExpression("^AhomePhone.*",
				false), homeP);
	}

	public void setWorkPhone(String workP) {// Business phone
		browser.setTextField(".id", getObjIDByLabel(FIELDNAME_WPHONE), workP);
	}

	public void setWorkPhoneExtension(String workPExtension) {
		browser.setTextField(".id",
				getObjIDByLabel(FIELDNAME_WPHONE_EXTENSION), workPExtension);
	}

	public void setCellPhone(String cellP) {// Mobile phone
		browser.setTextField(".id", getObjIDByLabel(FIELDNAME_CPHONE), cellP);
	}

	public void setFax(String fax) {
		browser.setTextField(".id",
				new RegularExpression("^AfaxPhone.*", false), fax);
	}

	public boolean isAccntInfoExist() {
		return browser.checkHtmlObjectExists(".id", "accntGroupgroup");
	}

	public void selectSKResidType() {
		browser.selectRadioButton(".id", new RegularExpression(
				"AresidencyGrp.*", false), 0);
	}

	public void selectOtherSKResidType() {
		browser.selectRadioButton(".id", new RegularExpression(
				"AresidencyGrp.*", false), 1);
	}

	public boolean isOtherSkResidTypeExisting() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"AresidencyGrp.*", false));
	}

	public void setupNewAccountInfo(Customer cus) {
		logger.info("Set up New account info...");
		if (isOtherSkResidTypeExisting()) { // HFSK has this, but no in HFMO
			this.selectOtherSKResidType();
		}

		// if (this.isAccntInfoExist()) { //In HFSK, has the account information
		// DIV, includes email address, password and retry password. In HFMO, no
		// the account information DIV, also need the email address
		if (isEmailAddressExisting()) {
			this.setEmailAddress(cus.email);
		}
		if (isPasswordExisting()) {
			this.setPassword(cus.password);
		}
		if (isRetryPasswordExisting()) {
			this.setRetypePassword(cus.retypePassword);
		}
		// }

		// this.setFirstName(cus.fName);
		// this.setMiddleName(cus.mName);
		// this.setLastName(cus.lName);
		// Setup customer names
		setupCustNames(cus);

		// set identification info
		this.selectIdentType(cus.identifier.identifierType);
		this.waitLoading();
		if (isIdentNumFieldExist())
			this.setIdentNum(cus.identifier.identifierNum);

		if (this.isIdentCountryListExist())
			this.selectIdentCountry(cus.identifier.country);
		this.waitLoading();
		if (this.isIdenStateListExist())
			this.selectIdentState(cus.identifier.state);

		// set customer details info
		if(isDOB_YearExist()){
			this.setDOB_YearMonthDay(cus.dateOfBirth);	
		}else this.setDateOfBirth(cus.dateOfBirth);

		this.selectGender(cus.custGender);
		if (isEyeColourExisting()) {
			this.selectEyeColour(cus.eyeColor);
		}
		if (isHairColourExisting()) {
			this.selectHairColour(cus.hairColor);
		}
		if (isHeightExisting()) {
			this.setHeight(cus.heightFt, cus.heightIn);
		}
		
		if(isWeightExisting()){
			this.setWeight(cus.weight);
		}

		this.setupMailingAddress(cus.mailingAddr);
		if (!cus.mailAddrAsPhy) {
			this.selectHomeAddDiffFromMailAdd();
			this.setupHomeAddress(cus.physicalAddr);
		}

		// this.setHomePhone(cus.hPhone);
		setupContactNumbers(cus);
	}

	// Setup customer names related information
	public void setupCustNames(Customer cus) {
		setFirstName(cus.fName);
		setMiddleName(cus.mName);
		setLastName(cus.lName);
		if (StringUtil.notEmpty(cus.suffix)) {
			selectSuffix(cus.suffix);
		}
	}

	// Setup contact numbers related information
	public void setupContactNumbers(Customer cus) {
		setHomePhone(cus.hPhone);
		setWorkPhone(cus.bPhone);
		setWorkPhoneExtension(cus.workExtension);
		setCellPhone(cus.mPhone);
		setFax(cus.fax);
		if (StringUtil.notEmpty(cus.phoneContact)) {
			selectPreferredContactNum(cus.phoneContact);
		}
		if (StringUtil.notEmpty(cus.contactTime)) {
			selectPreferredContactTime(cus.contactTime);
		}
	}

	/** Get the error message on top of page */
	public String getTopErrorMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname",
				"msg topofpage error");
	}

	/** Check if the error message exist */
	public boolean isErrorMsgExist(String msg) {
		return browser
				.checkHtmlObjectExists(".classname", new RegularExpression(
						"(error_item|msg topofpage error)", false), ".text",
						new RegularExpression(msg, false));
	}

	/** Verify Error Message exist */
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should "
					+ info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}

	/** Check if the error message for required info exists on the page */
	public boolean isInfoRequiredErrorMsgExist(String field) {
		return this.isErrorMsgExist(field + " is required.");
	}

	public boolean isElementErrorMesExist(String parentObjID,
			String errorMesObjsClassName, String mes) {
		Property[] p1 = Property.toPropertyArray(".id", new RegularExpression(
				parentObjID, false));
		Property[] p2 = Property.toPropertyArray(".classname",
				errorMesObjsClassName, ".text", new RegularExpression(mes,
						false));
		logger.info("Error Message:"
				+ browser.getObjectText(Property.atList(p1, p2)));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public void verifyElementErrorMes(String parentObjID,
			String errorMesObjsClassName, String errorMes, boolean isExisted) {
		String info = isExisted ? " " : " not ";
		if (this.isElementErrorMesExist(parentObjID, errorMesObjsClassName,
				errorMes) != isExisted) {
			throw new ErrorOnPageException("The message: " + errorMes
					+ " should " + info + "exist!");
		}
		logger.info("The message: " + errorMes + " does " + info + "exist!");
	}

	public void verifyHomePhoneErrorMes(String errorMes, boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_homePhone_attrs", "error_item",
				errorMes, isExisted);
	}

	public void verifyWorkPhoneErrorMes(String errorMes, boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_workPhone_attrs", "error_item",
				errorMes, isExisted);
	}

	public void verifyCellPhoneErrorMes(String errorMes, boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_cellPhone_attrs", "error_item",
				errorMes, isExisted);
	}

	public void verifyFaxErrorMes(String errorMes, boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_faxPhone_attrs", "error_item",
				errorMes, isExisted);
	}

	public void verifyPreferredContactNumberErrorMes(String errorMes,
			boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_prefContactTime_attrs",
				"error_item", errorMes, isExisted);
	}

	public void verifyCustNamesErrorMes(String errorMes, boolean isExisted) {
		verifyElementErrorMes("hfProfileKit_name_attrs", "group_errors",
				errorMes, isExisted);
	}

	/** Get the input email address */
	public String getEmailAddress() {
		String id = this.getObjIDByLabel("^" + FIELDNAME_EMAIL + ".*");
		return browser.getTextFieldValue(".id", id);
	}

	/** Verify the input email address */
	public void verifyEmailAddress(String email) {
		String actual = this.getEmailAddress();
		if (!actual.equals(email)) {
			throw new ErrorOnPageException("The email address is wrong!",
					email, actual);
		}
		logger.info("The email is correct as:" + email);
	}

	public void clickValidateMailingAddressBtn() {
		browser.clickGuiObject(".id", "mailAddraddrsgrp_validate");
	}

	public boolean isValidateMailingAddressExist() {
		return browser
				.checkHtmlObjectExists(".id", "mailAddraddrsgrp_validate");
	}

	/** Verify validate mailing address button exist or not */
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

	/** Verify validate home address button exist or not */
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

	/** Validate Mailing address */
	public void validateMailingAddress() {
		this.clickValidateMailingAddressBtn();
		this.waitLoading();
	}

	/** Input mailing address and validate the address */
	public void validateMailingAddress(Address addr) {
		logger.info("Input mailing address and validate it...");
		this.setupMailingAddress(addr);
		this.validateMailingAddress();
	}

	/** Validate Home address */
	public void validateHomeAddress() {
		this.clickValidateHomeAddressBtn();
		this.waitLoading();
	}

	/** Input home address and validate the address */
	public void validateHomeAddress(Address addr) {
		logger.info("Input home address and validate it...");

		this.setupHomeAddress(addr);
		this.validateHomeAddress();
	}

	/** Verify mailing address info */
	public void verifyMailingAddressInfo(Address addr) {
		Address act = this.getMailingAddress();
		if (!act.equals(addr)) {
			throw new ErrorOnPageException("The mailing address info is wrong!");
		}
		logger.info("The mailing address info is correct!");
	}

	/** Verify home address info */
	public void verifyHomeAddressInfo(Address addr) {
		Address act = this.getHomeAddress();
		if (!act.equals(addr)) {
			throw new ErrorOnPageException("The home address info is wrong!");
		}
		logger.info("The home address info is correct!");
	}

	public List<String> getMailingCountyOptions() {
		return browser.getDropdownElements(".id", this.getObjIDByLabel(
				FIELDNAME_COUNTY, "^AmailAddrStateCounty.*"));
	}

	public boolean isMailingCountyListBlank() {
		List<String> options = this.getMailingCountyOptions();
		return options.size() < 1;
	}

	/** Verify the mailing address county list is blank */
	public void verifyMailingCountyBlank() {
		if (!this.isMailingCountyListBlank()) {
			throw new ErrorOnPageException(
					"Mailing county list should be blank!");
		}
		logger.info("Mailing county list is blank!");
	}

	public List<String> getHomeCountyOptions() {
		return browser
				.getDropdownElements(".id", this.getObjIDByLabel(
						FIELDNAME_COUNTY, "^AphAddrStateCounty.*"));
	}

	public boolean isHomeCountyListBlank() {
		List<String> options = this.getHomeCountyOptions();
		return options.size() < 1;
	}

	/** Verify the home address county list is blank */
	public void verifyHomeCountyBlank() {
		if (!this.isHomeCountyListBlank()) {
			throw new ErrorOnPageException("Home county list should be blank!");
		}
		logger.info("Home county list is blank!");
	}

	/** Identification Section */
	public String getIdentSectionTitle() {
		return browser.getObjectText(Property.atList(this.custIdentDivProp(),
				this.sectionLegendProp()));
	}

	public boolean isIdentClearLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(
				this.clearLinkDivProp(), this.clearLinkProp()));
	}

	public void clickIdentClearLink() {
		browser.clickGuiObject(
				Property.atList(this.clearLinkDivProp(), this.clearLinkProp()),
				true, 0);
	}

	public void clickIdentClearLinkAndWait() {
		this.clickIdentClearLink();
		this.waitLoading();
	}

	public List<String> getIdenTypes(){
		return browser.getDropdownElements(".id", this.getObjIDByLabel(FIELDNAME_IDENT_TYPE));
	}
	
	public void selectIdentType(String identType) {
		if(getIdentTypes().toString().contains(identType +" #")){//Sara[10/11/2013] Different between SK and MO
			identType = identType+" #";
		}
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_IDENT_TYPE), identType);
	}

	public void clickIdentTypeLabel(){
		browser.clickGuiObject(Property.toPropertyArray(".class",
				"HTML.Label", ".text", new RegularExpression(FIELDNAME_IDENT_TYPE,
						false), ".for", new RegularExpression(IDENT_ID_REGX,
						false)));
	}
	public String getIdentType() {
		return browser.getDropdownListValue(".id",
				this.getObjIDByLabel(FIELDNAME_IDENT_TYPE));
	}

	public List<String> getIdentTypes() {
		return browser.getDropdownElements(".id",
				this.getObjIDByLabel(FIELDNAME_IDENT_TYPE));
	}

	public void setIdentNum(String identNum) {
		browser.setTextField(".id",
				new RegularExpression(IDENT_ID_REGX, false), identNum);
	}

	public String getIdentNum() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				IDENT_ID_REGX, false));
	}

	public boolean isIdentNumFieldExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", new RegularExpression(IDENT_ID_REGX, false));
	}

	public void selectIdentState(String identState) {
		String id = this.getObjIDByLabel(FIELDNAME_IDENT_STATE, IDENT_ID_REGX);
		browser.selectDropdownList(".id", id, identState);
	}

	public String getIdentState() {
		String id = this.getObjIDByLabel(FIELDNAME_IDENT_STATE, IDENT_ID_REGX);
		return browser.getDropdownListValue(".id", id);
	}
	
	public List<String> getIdentStates(){
		String id = this.getObjIDByLabel(FIELDNAME_IDENT_STATE, IDENT_ID_REGX);
		return browser.getDropdownElements(".id", id);
	}

	public boolean isIdenStateListExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property
				.toPropertyArray(".id", DIV_IDEN_ID), Property.toPropertyArray(
				".class", "Html.DIV", ".text", new RegularExpression("^"
						+ FIELDNAME_IDENT_STATE + ".*", false))));
	}

	public void verifyIdenStateDDLExist(boolean existed){
		boolean actualValue = isIdenStateListExist();
		if(!MiscFunctions.compareResult("Iden state DDL exist or not", existed, actualValue)){
			throw new ErrorOnPageException("Failed to verify iden state DDL "+(existed?"":"not ")+"exist.");
		}
	}
	
	public boolean isIdentCountryListExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property
				.toPropertyArray(".class", "Html.DIV", ".text",
						new RegularExpression("^" + FIELDNAME_IDENT_COUNTRY
								+ ".*", false)), Property.toPropertyArray(
				".class", "Html.SELECT", ".id", new RegularExpression(
						IDENT_ID_REGX, false))));
	}

	public void verifyIdenCountryDDLExist(boolean existed){
		boolean actualValue = isIdentCountryListExist();
		if(!MiscFunctions.compareResult("Iden country DDL exist or not", existed, actualValue)){
			throw new ErrorOnPageException("Failed to verify iden country DDL "+(existed?"":"not ")+"exist.");
		}
	}
	
	public void selectIdentCountry(String identCountry) {
		browser.selectDropdownList(".id",
				this.getObjIDByLabel(FIELDNAME_IDENT_COUNTRY, IDENT_ID_REGX),
				identCountry);
	}

	public String getIdentCountry() {
		String id = this
				.getObjIDByLabel(FIELDNAME_IDENT_COUNTRY, IDENT_ID_REGX);
		return browser.getDropdownListValue(".id", id);
	}

	public List<String> getIdentCountries() {
		String id = this
				.getObjIDByLabel(FIELDNAME_IDENT_COUNTRY, IDENT_ID_REGX);
		return browser.getDropdownElements(".id", id);
	}
	
	public void selectPreferredContactNum(String contactNum) {
		browser.selectDropdownList(".id", new RegularExpression(
				preferredContactNumIDRegx, false), contactNum);
	}

	public String getPreferredContactNum() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				preferredContactNumIDRegx, false));
	}

	public void verifyPreferredContactNum(String expectedValue) {
		String actualValue = getPreferredContactNum();
		if (!expectedValue.equals(actualValue)) {
			throw new ErrorOnPageException("Preferred contact num is wrong!",
					expectedValue, actualValue);
		}
		logger.info("Successfully verify Preferred contact num:"
				+ expectedValue);
	}

	public List<String> getPreferredContactNums() {
		return browser.getDropdownElements(".id", new RegularExpression(
				preferredContactNumIDRegx, false));
	}

	public void verifyPreferredContactNums(List<String> expectedValues) {
		List<String> actualValues = getPreferredContactNums();
		if (!expectedValues.toString().equals(actualValues.toString())) {
			throw new ErrorOnPageException("Preferred contact nums is wrong!",
					expectedValues.toString(), actualValues.toString());
		}
		logger.info("Successfully verify Preferred contact num:"
				+ expectedValues.toString());
	}

	public void selectPreferredContactTime(String contactTime) {
		browser.selectDropdownList(".id", new RegularExpression(
				preferredContactTimeIDRegx, false), contactTime);
	}

	public String getPreferredContactTime() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				preferredContactTimeIDRegx, false));
	}

	public void verifyPreferredContactTime(String expectedValue) {
		String actualValue = getPreferredContactTime();
		if (!expectedValue.equals(actualValue)) {
			throw new ErrorOnPageException("Preferred contact time is wrong!",
					expectedValue, actualValue);
		}
		logger.info("Successfully verify Preferred contact time:"
				+ expectedValue);
	}

	public List<String> getPreferredContactTimes() {
		return browser.getDropdownElements(".id", new RegularExpression(
				preferredContactTimeIDRegx, false));
	}

	public void verifyPreferredContactTimes(List<String> expectedValues) {
		List<String> actualValues = getPreferredContactTimes();
		if (!expectedValues.toString().equals(actualValues.toString())) {
			throw new ErrorOnPageException("Preferred contact times is wrong!",
					expectedValues.toString(), actualValues.toString());
		}
		logger.info("Successfully verify Preferred contact times:"
				+ expectedValues.toString());
	}
	
	public void waitForStateSync() {
		Property[] p1 = Property.toPropertyArray(".id", "hfProfileKit_custIdentifiersDynamic_attrs");
		Property[] p2 = Property.toPropertyArray(".id", this.getObjIDByLabel(FIELDNAME_IDENT_STATE, IDENT_ID_REGX));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForCountryync() {
		Property[] p1 = Property.toPropertyArray(".id", "hfProfileKit_custIdentifiersDynamic_attrs");
		Property[] p2 = Property.toPropertyArray(".id", this.getObjIDByLabel(FIELDNAME_IDENT_COUNTRY, IDENT_ID_REGX));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void setupIden(CustomerIdentifier iden){
		selectIdentType(iden.identifierType);
		this.waitLoading();
		clickIdentTypeLabel();

		if(StringUtil.notEmpty(iden.identifierNum)){
			setIdentNum(iden.identifierNum);
		}
		if (StringUtil.notEmpty(iden.country)) {
			Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
			waitForCountryync();
			selectIdentCountry(iden.country);
		}
		if(StringUtil.notEmpty(iden.state)){
			Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
			waitForStateSync();
			selectIdentState(iden.state);
		}
	}
	
	public String getIdenInfo() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "hfProfileKit_custIdentifiersStatic_attrs"));
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Identification objects can't be found.");
		}

		String value = objs[0].text().split(idenStateLabel)[1].trim();
		Browser.unregister(objs);
		return value;
	}
	
	public String generateIdensInfo(List<CustomerIdentifier> idens){
		String value = StringUtil.EMPTY;
		for(int i=0; i<idens.size(); i++){
			int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
			int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
			String mask = TestProperty.getProperty("identification.mask.character");
			String maskedNum = StringUtil.encryptString(idens.get(i).identifierNum, mask, hideNum, showNum);
			
			value += idens.get(i).identifierType  +" "
					+ maskedNum +" "
					+ (StringUtil.isEmpty(idens.get(i).country)?"-":idens.get(i).country) +" "
					+ (StringUtil.isEmpty(idens.get(i).state)?"-":idens.get(i).state) +" ";
		}
		
		return value.trim();
	}
	
	public void verifyIdentifierInfo(List<CustomerIdentifier> idens) {
		String expectedValue = generateIdensInfo(idens);
		String actualValue = getIdenInfo();
		if(!MiscFunctions.compareResult("Identifier info", expectedValue, actualValue)){
			throw new ErrorOnPageException("Identifier info is wrong! Please check details from previous logs.");
		}
	}
	
}
