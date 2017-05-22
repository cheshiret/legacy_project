package com.activenetwork.qa.awo.pages.web.hf;

import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 19, 2013
 */
public class HFUpdateIdentificationPage extends  HFHeaderBar {

	private static HFUpdateIdentificationPage _instance = null;

	public static HFUpdateIdentificationPage getInstance() {
		if (null == _instance)
			_instance = new HFUpdateIdentificationPage();

		return _instance;
	}

	protected HFUpdateIdentificationPage() {
	}

	private static String NUMBER_ID_REGX = "Anumber_-\\d+"; 
	private static String DOB_ID_REGX = "Adob_\\d+";
	private static String COUNTRY_ID_REGX = "Acountry_-\\d+";
	private static String STATE_ID_REGX = "Astate_(-)?\\d+";
	
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(".id", "updateIdentifierPage") && browser.checkHtmlObjectExists(".id", "submitForm_submitForm");
	}

	public void selectIdentifierType(String identificationTypeName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", identificationTypeName);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("LCustomerIdentifiersKit_identifLayout_CustomerIdentifiersKit_\\d+", false));
		browser.selectRadioButton(p2, true, 0, browser.getHtmlObject(p1)[0]);
	}

	public String getPageTitle(){
		return browser.getObjectText(".className", "pagetitle");
	}
	
	public String getIdenTypeMes(){
		return browser.getObjectText(".id", "updateIdentifierKit_typemsg_attrs").trim();
	}
	
	public String getIdenElementLabel(String labelParentObjsClassName, String labelfParentObjsID){
		Property[] p1 = Property.toPropertyArray(".className", labelParentObjsClassName, ".id", labelfParentObjsID);
		Property[] p2 = Property.toPropertyArray(".className", "groupLabel");
		return browser.getObjectText(Property.atList(p1, p2));
	}
	
	public String getNumberLabel(){
		return getIdenElementLabel("group", "number");
	}
	public String getCountryLabel(){
		return getIdenElementLabel("group", "country");
	}
	public String getProvinceLabel(){
		return getIdenElementLabel("group", "state");
	}
	public String getDateOfBirthLabel(){
		return getIdenElementLabel("personal", "dob");
	}
	public void clearNumber() {
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression(NUMBER_ID_REGX, false));
		if(objs.length>0){
			((IText)objs[0]).clear();
		}else
			throw new ObjectNotFoundException("'Length(ft)' object can't find.");
		Browser.unregister(objs);
	}
	
	public void setNumber(String num){
		clearNumber();
		browser.setTextField(".id", new RegularExpression(NUMBER_ID_REGX, false), num);
	}
	
	public String getNumber(){
		return browser.getTextFieldValue(".id", new RegularExpression(NUMBER_ID_REGX, false));
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression(COUNTRY_ID_REGX, false), country);
	}
	
	public void removeFocus(){
		browser.clickGuiObject(".className", "pagetitle");
	}
	
	public void synStateSelectingCountry(String country, boolean disappeared){
		this.selectCountry(country);
		removeFocus();
		
		if(disappeared){
			this.waitForStateDisappears();
		}else{
			this.waitForStateSync();
		}
	}
	
	public String getCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression(COUNTRY_ID_REGX, false));
	}
	
	public List<String> getCountries(){
		return browser.getDropdownElements(".id", new RegularExpression(COUNTRY_ID_REGX, false));
	}
	
	public void verifyCountries(List<String> expectedValues){
		List<String> actualVaues = this.getCountries();
		if(!expectedValues.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Countries is wrong!", expectedValues.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify country:"+expectedValues.toString());
	}
	
	public void verifyCountry(String expectedValue){
		String actualVaue = this.getCountry();
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("Country is wrong",  expectedValue, actualVaue);
		}
		logger.info("Successfully verify country:"+expectedValue);
	}	
	
	public boolean checkCountryDDLExists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(COUNTRY_ID_REGX, false));
	}
	
	public void verifyCountryExists(boolean existed){
		String info = existed ? " " : " not ";
		if(checkCountryDDLExists()!=existed){
			throw new ErrorOnPageException("Country drop down list should " + info + "exist!");
		}
		logger.info("Country drop down list does " + info + "exist!");
	}

	public void waitForStateSync() {
		Property[] p1 = Property.toPropertyArray(".id", "updateIdentifierForm");
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(STATE_ID_REGX, false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForStateDisappears() {
		Property[] p1 = Property.toPropertyArray(".id", "updateIdentifierForm");
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(STATE_ID_REGX, false));
		browser.waitDisappear(Browser.SHORT_SLEEP, Property.atList(p1, p2));
	}
	
	public void selectState(String state) {
		browser.selectDropdownList(".id", new RegularExpression(STATE_ID_REGX, false), state);
	}
	
	public String getState(){
		return browser.getDropdownListValue(".id", new RegularExpression(STATE_ID_REGX, false));
	}

	public List<String> getStates(){
		return browser.getDropdownElements(".id", new RegularExpression(STATE_ID_REGX, false));
	}
	
	public void verifyState(String expectedValue){
		String actualVaue = this.getState();
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("State is wrong!", expectedValue, actualVaue);
		}
		logger.info("Successfully verify State:"+expectedValue);
	}	
	
	public void verifyStates(List<String> expectedValues){
		List<String> actualVaues = this.getStates();
		if(!expectedValues.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Province is wrong!", expectedValues.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify Province:"+expectedValues.toString());
	}
	
	public boolean checkStateDDLExists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(STATE_ID_REGX, false));
	}
	
	public void verifyStateExists(boolean existed){
		String info = existed ? " " : " not ";
		if(checkStateDDLExists()!=existed){
			throw new ErrorOnPageException("State drop down list should " + info + "exist!");
		}
		logger.info("State drop down list does " + info + "exist!");
	}
	
	public void setDOB(String dateOfBirth){
		browser.setTextField(".id", new RegularExpression(DOB_ID_REGX, false), dateOfBirth);
	}
	
	public String getDOB(){
		return browser.getTextFieldValue(".id", new RegularExpression(DOB_ID_REGX, false));
	}
	
	public void clickSaveChangesButton(){
		Property[] p1 = Property.toPropertyArray(".id", "updateIdentifierForm");
		Property[] p2 = Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Save Changes");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	public boolean isSaveChangesButtonExisting(){
		return browser.checkHtmlObjectExists(".id", "submitForm_submitForm", ".text", "Save Changes");
	}
	
	public void clickCancelLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public boolean isCancelLinkExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel");
	}
	
	public void updateIdentification(String identifierNum, String country){
		this.setNumber(identifierNum);
		this.selectCountry(country);
	}
	
	public boolean topPgErrorMesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "msg topofpage error");
	}
	
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("(msg topofpage error|error_item)", false), ".text", new RegularExpression(msg, false));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public void verifyIdenNum(String custNum){
		String actualCustNum = this.getNumber();
		if(!custNum.equals(actualCustNum)){
			throw new ErrorOnPageException("Failed to verify identifer number!", custNum, actualCustNum);
		}
		logger.info("Successfully verify identifer number as " + custNum);
	}
	
	public void clickContactCallCenterLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "error_item"), 
				Property.toPropertyArray(".class", "Html.A", ".text", "contact the call center")), true, 0);
	}
}

