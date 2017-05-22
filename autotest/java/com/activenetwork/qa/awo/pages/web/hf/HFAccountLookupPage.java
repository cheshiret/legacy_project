package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
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
 * @author SWang
 * @Date  Mar 20, 2013
 */
public class HFAccountLookupPage extends HFHeaderBar {
	private static HFAccountLookupPage _instance = null;

	public static HFAccountLookupPage getInstance() {
		if (null == _instance)
			_instance = new HFAccountLookupPage();

		return _instance;
	}

	protected HFAccountLookupPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "identifycustomercontent");
	}

	/** Start of Page Objects' Properties Definition */
	private String idenGroupAttrsRegx = "CustomerIdentifiersKit_\\d+";
	private String idenGroupRegx = "CustomerIdentifiersKit_CustomerIdentifiersKit_\\d+_attrs";
	private String idenTypeRegx = "LCustomerIdentifiersKit_identifLayout_CustomerIdentifiersKit_\\d+";
	
	private String idenNumIdRegx = "ACustomerIdentifiersKit_\\d+(_number)?_(-)?\\d+";
	
	private String idenConutryIdRegx = "ACustomerIdentifiersKit_\\d+_country_(-)?\\d+";
	private String idenProvinceRegx = "ACustomerIdentifiersKit_\\d+_state_(-)?\\d+";
    private String dateOfBirthIdRegx = "Apersonal_(-)?\\d+";
    
	protected Property[] getSignUpPageLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", "/createHFProfile.do");
	}
    
	protected Property[] getMessageDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message");
	}
	/** End of Page Objects' Properties Definition */
	
	public String getPgTitle(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "pagetitle");
	}
	
	public void selectIdentifierType(String idenType){
		if(idenType.startsWith("Conservation")){
			idenType = idenType.replaceAll("Number", "#");
		}
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", idenType);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenTypeRegx, false));
		browser.selectRadioButton(p2, true, 0, browser.getHtmlObject(p1)[0]);
	}
	
	public void selectIdenTypeCountrySync(String idenType){
		selectIdentifierType(idenType);
		waitForCountrySync(idenType);
	}

	public boolean isIdenTypeSelected(String idenType){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", idenType);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenTypeRegx, false));
		return browser.isRadioButtonSelected(p2, browser.getHtmlObject(p1)[0]);
	}

	public List<String> getAllIdenTypes(){
		List<String> values = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(idenTypeRegx, false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Identifier type objects can't be found.");
		}
		for(int i=0; i<objs.length; i++){
			values.add(objs[i].text().trim());
		}
		Browser.unregister(objs);
		return values;
	}
	public void verifyIdentificationTypeSelected(String idenType, boolean selected){
		if(selected!=this.isIdenTypeSelected(idenType)){
			throw new ErrorOnPageException("Failed to verify identifier type:"+idenType+" is"+(selected?"":" not")+" selected");
		}
		logger.info("Successfully verify identifier type:"+idenType+" is"+(selected?"":" not")+" selected");
	}

	private String getIdenElementID(String idRegx, String idenType){
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenGroupRegx, false));
		IHtmlObject[] parentObjs=browser.getHtmlObject(Property.atList(p1, p2));
		IHtmlObject[] childObjs = null;
		String returnID = "";
		boolean findObjs = false;

		if(parentObjs==null||parentObjs.length<1){
			throw new ObjectNotFoundException("Can't find object with idRegx:"+idenGroupRegx);
		}
		for(IHtmlObject parentObj:parentObjs){
			childObjs = browser.getHtmlObject(".id", new RegularExpression(idRegx,false), parentObj);
			if(childObjs!=null && childObjs.length>0 && parentObj.getAttributeValue("className").endsWith("collapsed")){
				returnID=childObjs[0].getProperty(".id");
			}

			logger.info("ID:"+returnID);
			Browser.unregister(childObjs);
			findObjs = true;
			break;
		}
		if(!findObjs){
			throw new ErrorOnPageException("Can find element when idRegx:"+idRegx+" and idenType:"+idenType);
		}
		Browser.unregister(parentObjs);
		return returnID;
	}

	public boolean isIdenElementDisplayed(String idRegx, String idenType){
		String id = this.getIdenElementID(idRegx, idenType);
		if(StringUtil.notEmpty(id)){
			return browser.checkHtmlObjectExists(".id", id);
		}else{
			return false;
		}
	}

	public void verifyIdenElementDisplaying(String idRegx, String idenType, boolean displaying){
		if(displaying!=this.isIdenElementDisplayed(idRegx, idenType)){
			throw new ErrorOnPageException("Failed to verify identification with id:"+idRegx+", identification type:"+idenType+" is"+(displaying?"":" not")+" displaying");
		}
		logger.info("Successfully verify identification with id:"+idRegx+", identification type:"+idenType+" is"+(displaying?"":" not")+" displaying");
	}

	public void setIdenNum(String num, String idenType){
		if(idenType.startsWith("Conservation")){
			idenType = idenType.replaceAll("Number", "#");
		}
		browser.setTextField(".id", this.getIdenElementID(idenNumIdRegx, idenType), num);
	}

	public String getIdenNum(String idenType){
		return browser.getTextFieldValue(".id", this.getIdenElementID(idenNumIdRegx, idenType));
	}
	
	public void verifyIdenNum(String idenType, String custNum){
		String actualCustNum = this.getIdenNum(idenType);
		if(!custNum.equals(actualCustNum)){
			throw new ErrorOnPageException("Failed to verify identifer number when idenType:"+idenType);
		}
		logger.info("Successfully verify identifer number when idenType:"+idenType);
	}
	
	public void verifyIdenNumDisplaying(String idenType, boolean displaying){
		this.verifyIdenElementDisplaying(idenNumIdRegx, idenType, displaying);
	}

	public void selectCountry(String country, String idenType){
		browser.selectDropdownList(".id", this.getIdenElementID(idenConutryIdRegx, idenType), country);
	}

	public String getCountry(String idenType){
		return browser.getDropdownListValue(".id", this.getIdenElementID(idenConutryIdRegx, idenType));
	}

	public List<String> getCountries(String idenType){
		return browser.getDropdownElements(".id", this.getIdenElementID(idenConutryIdRegx, idenType));
	}

	public void verifyCountry(String idenType, String expectedValue){
		String actualVaue = this.getCountry(idenType);
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("Country is wrong from idenType:"+idenType, expectedValue, actualVaue);
		}
		logger.info("Successfully verify country:"+expectedValue+" for idenType:"+idenType);
	}	

	public void verifyCountries(String idenType, List<String> expectedValues){
		List<String> actualVaues = this.getCountries(idenType);
		if(!expectedValues.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Countries is wrong from idenType:"+idenType, expectedValues.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify country:"+expectedValues.toString()+" for idenType:"+idenType);
	}	

	public void verifyCountryDisplaying(String idenType, boolean displaying){
		this.verifyIdenElementDisplaying(idenConutryIdRegx, idenType, displaying);
	}

	public void selectProvince(String province, String idenType){
		browser.selectDropdownList(".id", this.getIdenElementID(idenProvinceRegx, idenType), province);
	}

	/** Get the selected province by identifier type */
	public String getProvince(String idenType){
		return browser.getDropdownListValue(".id", this.getIdenElementID(idenProvinceRegx, idenType));
	}

	/** Get all provinces in Province drop down list by identifier type */
	public List<String> getProvinces(String idenType) {
		return browser.getDropdownElements(".id", this.getIdenElementID(idenProvinceRegx, idenType));
	}
	
	/** Verify the selected province per identifier type */
	public void verifyProvince(String idenType, String expectedValue){
		String actualVaue = this.getProvince(idenType);
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("Province is wrong from idenType:"+idenType, expectedValue, actualVaue);
		}
		logger.info("Successfully verify Province:"+expectedValue+" for idenType:"+idenType);
	}
	
	/** Verify province drop down list displayed or not */
	public void verifyProvinceDisplaying(String idenType, boolean displaying){
		this.verifyIdenElementDisplaying(idenProvinceRegx, idenType, displaying);
	}

	/** Verify province drop down list all options */
	public void verifyProvinces(String idenType, List<String> expProvinces) {
		List<String> actualVaues = this.getProvinces(idenType);
		if(!expProvinces.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Provinces in Province dropdown list is wrong for " + idenType , expProvinces.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify Province dropdown list options:"+expProvinces.toString());
	}
	
	public void setDateOfBirth(String dateOfBirth){
		browser.setTextField(".id", new RegularExpression(dateOfBirthIdRegx, false), dateOfBirth, 0, IText.Event.LOSEFOCUS);
	}

	public String getDateOfBirth(){
		return browser.getTextFieldValue(".id", new RegularExpression(dateOfBirthIdRegx, false));
	}
	
	public String getDateOfBirthFormat(){
		IHtmlObject[] objs= browser.getHtmlObject(".id", new RegularExpression(dateOfBirthIdRegx, false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find DOB objects.");
		}
		String value = objs[0].getProperty("placeholder");
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyDateOfBirth(String value, boolean isDOBFormat){
		String actualValue = StringUtil.EMPTY;
		if(isDOBFormat){
			actualValue = getDateOfBirthFormat();
		}else actualValue = this.getDateOfBirth();
		
		if(!value.equals(actualValue)){
			throw new ErrorOnPageException("Date of birth is wrong!", value, actualValue);
		}
		logger.info("Successfully verify date of birth:"+value);
	}
	
	public void verifyDateOfBirth(String value){
		verifyDateOfBirth(value, false);
	}
	
	public void clickLookupProfileButton(){
		browser.clickGuiObject(".id", "submitForm_submitForm", ".text", new RegularExpression("LookUp Profile|Continue", false)); //HFSK and HFMO
	}

	public void clickAddIdentificationButton(){
		Property[] p = Property.toPropertyArray(".id", "submitForm_submitForm");
		browser.clickGuiObject(p);
	}

	public void waitForProvinceSync(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenProvinceRegx, false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForCountrySync(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenConutryIdRegx, false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForProvinceDisappears(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenProvinceRegx, false));
		browser.waitDisappear(Browser.SHORT_SLEEP, Property.atList(p1, p2));
	}
	
	/**
	 * Sync province after selecting country 
	 * @param country
	 * @param idenType
	 * @param disappeared true: after select country, province drop down list a
	 */
	public void syncProvinceSelectingCountry(String country, String idenType, boolean disappeared){
		this.selectCountry(country, idenType);
		if(disappeared){
			this.waitForProvinceDisappears(idenType);
		}else{
			this.waitForProvinceSync(idenType);
		}
	}
	
	/**
	 * Lookup account
	 * @param identifierType
	 * @param identifierNum
	 * @param countryOrState
	 * @param dateOfBirth
	 */
	public void lookupAccount(CustomerIdentifier iden, String dateOfBirth){
		logger.info("Look up account by identifier type=" + iden.identifierType +", identifier num=" + iden.identifierNum +", country=" + iden.country +", state="+ iden.state +", dateOfBirth=" + dateOfBirth);
		this.selectIdentifierType(iden.identifierType);
		this.setIdenNum(iden.identifierNum, iden.identifierType);

		if(!StringUtil.isEmpty(iden.country)){
			this.waitForCountrySync(iden.identifierType);
			this.selectCountry(iden.country, iden.identifierType);
		}

		if(!StringUtil.isEmpty(iden.state)){
			this.waitForProvinceSync(iden.identifierType);
			this.selectProvince(iden.state, iden.identifierType);
		}

		this.setDateOfBirth(dateOfBirth);
	}

	public void lookupAccount(Customer cus) {
		this.lookupAccount(cus.identifier, cus.dateOfBirth);
	}
	
	public boolean topPgErrorMesExist(){
		return browser.checkHtmlObjectExists(this.getMessageDivProp());
	}

	public void verifyTopMesExistExsiting(boolean existing){
		if(existing!=topPgErrorMesExist()){
			throw new ErrorOnPageException("Top page error message should "+(existing?"":"not ")+"exist.");
		}
		logger.info("Successfully verify top page error message is "+(existing?"":"not ")+"existing.");
	}
	
	public IHtmlObject[] getTopPgErrorMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message");
		if(objs.length<1){
			throw new ObjectNotFoundException("Top page error message objects can't be found.");
		}
		return objs;
	}

	public String getTopPgErrorMes(){
		IHtmlObject[] objs = this.getTopPgErrorMesObjs();
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;

	}
	
	public void verifyTopPgErrorMes(String expectedValue){
		String actualValue = this.getTopPgErrorMes();
		if(!actualValue.matches(expectedValue)){
			throw new ErrorOnPageException("Top page error message is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify top page error message:"+expectedValue);	
	}

	public List<String> getIdenRelatedErrorMeses(String idenType){
		List<String> errorMeses = new ArrayList<String>();
		Property[] parentProp = Property.toPropertyArray(".className", "group", 
				".id", new RegularExpression(idenGroupAttrsRegx, false), ".text", new RegularExpression("^"+ idenType+".*", false));
		Property[] childProp = Property.toPropertyArray(".class", "Html.DIV", ".className", "group_errors");
		IHtmlObject[] childObjs = browser.getHtmlObject(Property.atList(parentProp, childProp));
		for (IHtmlObject obj:childObjs) {
			errorMeses.add(obj.text().trim());
		}
		Browser.unregister(childObjs);
		return errorMeses;
	}
	
	public String getIdenRelatedErrorMes(String idenType){
		return getIdenRelatedErrorMeses(idenType).get(0);
	}

	public void verifyIdenRelatredErrorMes(String idenType, String expectedValue){
		String actualValue = this.getIdenRelatedErrorMes(idenType);
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Identifier:"+idenType+" related error message is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify identifier:"+idenType+" related error message:"+expectedValue);
	}
	
	public void verifyIdenRelatredErrorMeses(String idenType, List<String> expectedValues){
		List<String> actualValues = this.getIdenRelatedErrorMeses(idenType);
		if(!expectedValues.toString().equals(actualValues.toString())){
			throw new ErrorOnPageException("Identifier:"+idenType+" related error messages are wrong!", expectedValues.toString(), actualValues.toString());
		}
		logger.info("Successfully verify identifier:"+idenType+" related error messages:"+expectedValues);
	}

	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("(message|group_errors)", false), ".text", new RegularExpression(msg, false));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public boolean checkDateOfBirthErrorMesExist(){
		Property[] p1 = Property.toPropertyArray(".id", "CustomerIdentifiersKit_personal_attrs");
		Property[] p2 = Property.toPropertyArray(".className", "group_errors");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	public void verifyDateOfBirthErrorMesExist(boolean existing){
		if(existing!=this.checkDateOfBirthErrorMesExist()){
			throw new ErrorOnPageException("Date of birth error message should "+(existing?"":"not ")+"exist.");
		}
		logger.info("Successfully verify Date of birth error message is "+(existing?"":"not ")+"existing.");
	}
	
	public void verifyDateOfBirthRegxErrorMes(String expectedMes){
		String actualMes = this.getDateOfBirthErrorMes();
		if(!actualMes.matches(expectedMes)){
			throw new ErrorOnPageException("Date of birth error message is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify date of birth error message:"+expectedMes);
	}
	public String getDateOfBirthErrorMes(){
		Property[] p1 = Property.toPropertyArray(".id", "CustomerIdentifiersKit_personal_attrs");
		Property[] p2 = Property.toPropertyArray(".className", "group_errors");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs.length<1){
			throw new ObjectNotFoundException("Date of birth error message objects can't be found.");
		}
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}

	public void verifyDateOfBirthErrorMes(String expectedMes){
		String actualMes = this.getDateOfBirthErrorMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnPageException("Date of birth error message is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify date of birth error message:"+expectedMes);
	}
	
	public void clickCreateANewProfileLink(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/createHFProfile\\.do$", false));
	}

	/** Check if Sign Up Page link exist in Top message */
	public boolean isSignUpPageLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getMessageDivProp(), this.getSignUpPageLinkProp()));
	}
	
	/** Verify Sign Up Page link exist or not in Top message */
	public void verifySignUpPageLinkExist(boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.isSignUpPageLinkExist()) {
			throw new ErrorOnPageException("Sign up Page link in Top message should" + msg + "exist!");
		}
		logger.info("Verify Sign up page link in Top message do" + msg + "exist!");
	}
	
	/**
	 * Verify if identifier type radio is selected, if identifier number text field displays, if identifier country drop down list displays, if identifier province drop down list displays
	 * @param idenType
	 * @param isCustNum true: identifier type has customer#, others normal identifier number
	 * @param idenTypeSelected true: specific identifier type radio is selected
	 * @param hasNum true: specific identifier type has identifier number text field
	 * @param hasCountry true: specific identifier type has identifier country drop down list
	 * @param hasProvince true: specific identifier type has identifier province drop down list
	 */
	public void verifyIdenTypeUI(String idenType, boolean idenTypeSelected, boolean hasNum, boolean hasCountry, boolean hasProvince){
		this.verifyIdentificationTypeSelected(idenType, idenTypeSelected);

		this.verifyIdenNumDisplaying(idenType, hasNum);
		this.verifyCountryDisplaying(idenType, hasCountry);
		this.verifyProvinceDisplaying(idenType, hasProvince);
	}
	
	public void verifyIdenTypeUI(String idenType, boolean idenTypeSelected, boolean hasNum, boolean hasCountry){
		verifyIdenTypeUI(idenType, idenTypeSelected, hasNum, hasCountry, false);
	}
	
	/**
	 * Verify identifier number, country and province values
	 * @param iden
	 */
	public void verifyIdenTypeValues(CustomerIdentifier iden){
		if(!StringUtil.isEmpty(iden.identifierNum))
			this.verifyIdenNum(iden.identifierType, iden.identifierNum);	
		if(!StringUtil.isEmpty(iden.country))
			this.verifyCountry(iden.identifierType, iden.country);
		if(!StringUtil.isEmpty(iden.state))
			this.verifyProvince(iden.identifierType, iden.state);
	}
	
	public String getDOBReminderMes(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "groupLabel");
		if(objs.length<1){
			throw new ObjectNotFoundException("Date of birth reminder message objects can't be found.");
		}
		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}
}
