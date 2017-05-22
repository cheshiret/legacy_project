package com.activenetwork.qa.awo.pages.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
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
 * @Date  Mar 19, 2013
 */
public class HFAddIdentificationPage extends  HFHeaderBar {

	private static HFAddIdentificationPage _instance = null;

	public static HFAddIdentificationPage getInstance() {
		if (null == _instance)
			_instance = new HFAddIdentificationPage();

		return _instance;
	}

	protected HFAddIdentificationPage() {
	}
	
	private String idenNumIdRegx = "ACustomerIdentifiersKit_\\d+_(number_)?(-)?\\d+";
	private String idenConutryIdRegx = "ACustomerIdentifiersKit_\\d+_country_(-)?\\d+";
	private String idenProvinceRegx = "ACustomerIdentifiersKit_\\d+_state_(-)?\\d+";
	private String idenTypeRegx = "LCustomerIdentifiersKit_identifLayout_CustomerIdentifiersKit_\\d+";
	private String idenGroupRegx = "CustomerIdentifiersKit_CustomerIdentifiersKit_\\d+_attrs";
	private String dobRegx = "^Adob_(-)?\\d+";
	
	public boolean exists() {
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "pagetitle", ".text", new com.activenetwork.qa.testapi.util.RegularExpression("^Add Identification.*", false));
		return browser.checkHtmlObjectDisplayed(p);
	}

	public void selectIdentifierType(String identificationTypeName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", identificationTypeName);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("LCustomerIdentifiersKit_identifLayout_CustomerIdentifiersKit_\\d+", false));
		browser.selectRadioButton(p2, true, 0, browser.getHtmlObject(p1)[0]);
	}
	
	public boolean checkIdenTypeExists(String identificationTypeName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", identificationTypeName);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("LCustomerIdentifiersKit_identifLayout_CustomerIdentifiersKit_\\d+", false));
		return browser.checkHtmlObjectDisplayed(Property.atList(p1, p2));
	}

	public void verifyIdenTypeExists(String idenType, boolean existed){
		if(this.checkIdenTypeExists(idenType)!=existed){
			throw new ErrorOnPageException("Identification type:"+idenType+" should  "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully veirfy Identification type:"+idenType+" is "+(existed?"":"not ")+"existing.");
	}
	
	public String getIdenElementID(String idRegx, String idenType){
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenGroupRegx, false));
		IHtmlObject[] parentObjs=browser.getHtmlObject(Property.atList(p1, p2));
		IHtmlObject[] childObjs = null;
		String returnID = "";
		boolean findObjs = false;

		if(parentObjs==null||parentObjs.length<1){
			throw new ObjectNotFoundException("Can't find object with idRegx:"+idRegx);
		}
		for(IHtmlObject obj:parentObjs){
			childObjs = browser.getHtmlObject(".id", new RegularExpression(idRegx,false), obj);
			if(childObjs!=null && childObjs.length>0 && obj.getAttributeValue("className").endsWith("collapsed")){
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

	public void setIdentificationNum(String num, String idenType){
		String id = this.getIdenElementID(idenNumIdRegx, idenType);
		browser.setTextField(".id", id, num);
	}

	public String getIdenNum(String idenType){
		return browser.getTextFieldValue(".id", this.getIdenElementID(idenNumIdRegx, idenType));
	}
	
	public String getIdenMaxLength(String idenType){
		IHtmlObject[] objs = browser.getTextField(".id", this.getIdenElementID(idenNumIdRegx, idenType));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find idenType:"+idenType+" number");
		}
		String maxLength = objs[0].getProperty("maxLength").trim();
		Browser.unregister(objs);
		return maxLength;
	}
	
	public void verifyIdenNum(String idenType, String custNum){
		String actualCustNum = this.getIdenNum(idenType);
		if(!custNum.equals(actualCustNum)){
			throw new ErrorOnPageException("Failed to verify identifer number when idenType:"+idenType);
		}
		logger.info("Successfully verify identifer number when idenType:"+idenType);
	}
	
	public void selectCountry(String country, String idenType){
		browser.selectDropdownList(".id", this.getIdenElementID(idenConutryIdRegx, idenType), country);
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
	
	public String getCountry(String idenType){
		return browser.getDropdownListValue(".id", this.getIdenElementID(idenConutryIdRegx, idenType));
	}

	public List<String> getCountries(String idenType){
		return browser.getDropdownElements(".id", this.getIdenElementID(idenConutryIdRegx, idenType));
	}
	
	public void verifyCountries(String idenType, List<String> expectedValues){
		List<String> actualVaues = this.getCountries(idenType);
		if(!expectedValues.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Countries is wrong from idenType:"+idenType, expectedValues.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify country:"+expectedValues.toString()+" for idenType:"+idenType);
	}
	
	public void verifyCountry(String idenType, String expectedValue){
		String actualVaue = this.getCountry(idenType);
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("Country is wrong from idenType:"+idenType, expectedValue, actualVaue);
		}
		logger.info("Successfully verify country:"+expectedValue+" for idenType:"+idenType);
	}	

	public void selectState(String province, String idenType){
		browser.selectDropdownList(".id", this.getIdenElementID(idenProvinceRegx, idenType), province);
	}

	public String getState(String idenType){
		return browser.getDropdownListValue(".id", this.getIdenElementID(idenProvinceRegx, idenType));
	}

	public void verifyState(String idenType, String expectedValue){
		String actualVaue = this.getState(idenType);
		if(!expectedValue.equals(actualVaue)){
			throw new ErrorOnPageException("Province is wrong from idenType:"+idenType, expectedValue, actualVaue);
		}
		logger.info("Successfully verify Province:"+expectedValue+" for idenType:"+idenType);
	}	
	
	public List<String> getStates(String idenType){
		return browser.getDropdownElements(".id", this.getIdenElementID(idenProvinceRegx, idenType));
	}
	
	public void verifyStates(String idenType, List<String> expectedValues){
		List<String> actualVaues = this.getStates(idenType);
		if(!expectedValues.toString().equals(actualVaues.toString())){
			throw new ErrorOnPageException("Province is wrong from idenType:"+idenType, expectedValues.toString(), actualVaues.toString());
		}
		logger.info("Successfully verify Province:"+expectedValues.toString()+" for idenType:"+idenType);
	}
	
	public void clickAddIdentificationButton(){
		browser.clickGuiObject(".id", "submitForm_submitForm");
	}

	public void clickCancelLink(){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
		browser.clickGuiObject(p);
	}

	public void waitForCountrySync(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenConutryIdRegx, false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForProvinceSync(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenProvinceRegx, false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void waitForProvinceDisappears(String idenType) {
		Property[] p1 = Property.toPropertyArray(".className", "group", ".id", new RegularExpression("CustomerIdentifiersKit_\\d+", false), ".text", new RegularExpression(idenType+".*", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenProvinceRegx, false));
		browser.waitDisappear(Browser.SHORT_SLEEP, Property.atList(p1, p2));
	}
	
	public void addIdentification(CustomerIdentifier iden){
		this.selectIdentifierType(iden.identifierType);
		this.setIdentificationNum(iden.identifierNum, iden.identifierType);
		if(!StringUtil.isEmpty(iden.country)){
			this.selectCountry(iden.country, iden.identifierType);
			Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
		}
		if(!StringUtil.isEmpty(iden.state)){
			this.waitForProvinceSync(iden.identifierType);
			this.selectState(iden.state, iden.identifierType);
		}
	}

	public void verifyIdenTypeValues(CustomerIdentifier iden){
		if(!StringUtil.isEmpty(iden.identifierNum))
			this.verifyIdenNum(iden.identifierType, iden.identifierNum);	
		if(!StringUtil.isEmpty(iden.country))
			this.verifyCountry(iden.identifierType, iden.country);
		if(!StringUtil.isEmpty(iden.state))
			this.verifyState(iden.identifierType, iden.state);
	}
	
	public boolean topPgErrorMesExists(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message");
	}
	
	public void verifyTopErrorMesExsits(boolean existed){
		if(existed!=this.topPgErrorMesExists()){
			throw new ErrorOnPageException("Top page error message should "+(existed?"":"not ")+"be existed.");
		}
		logger.info("Successfully verify top page error message is "+(existed?"":"not ")+"existing.");
	}
	
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("(message|error_item)", false), ".text", new RegularExpression(msg, false));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public boolean isIdenElementDisplayed(String idRegx, String idenType){
		String id = this.getIdenElementID(idRegx, idenType);
		if(!id.equals(StringUtil.EMPTY)){
			return browser.checkHtmlObjectExists(".id", id);
		}else{
			return false;
		}
	}

	public boolean isCountryDisplaying(String idenType) {
		return this.isIdenElementDisplayed(idenConutryIdRegx, idenType);
	}
	
	public boolean isStateDisplaying(String idenType) {
		return this.isIdenElementDisplayed(idenProvinceRegx, idenType);
	}
	
	public void verifyIdenElementDisplayed(String idRegx, String idenType, boolean displaying){
		if(displaying!=this.isIdenElementDisplayed(idRegx, idenType)){
			throw new ErrorOnPageException("Failed to verify identification with id:"+idRegx+", identification type:"+idenType+" is"+(displaying?"":" not")+" displaying");
		}
		logger.info("Successfully verify identification with id:"+idRegx+", identification type:"+idenType+" is"+(displaying?"":" not")+" displaying");
	}
	
	public boolean isIdenTypeSelected(String idenType){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "control RADIOSET", ".text", idenType);
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression(idenTypeRegx, false));
		return browser.isRadioButtonSelected(p2, browser.getHtmlObject(p1)[0]);
	}
	
	public void verifyIdentificationTypeSelected(String idenType, boolean selected){
		if(selected!=this.isIdenTypeSelected(idenType)){
			throw new ErrorOnPageException("Failed to verify identifier type:"+idenType+" is"+(selected?"":" not")+" selected");
		}
		logger.info("Successfully verify identifier type:"+idenType+" is"+(selected?"":" not")+" selected");
	}
	
	public void verifyIdenNumDisplayed(String idenType, boolean displaying){
		this.verifyIdenElementDisplayed(idenNumIdRegx, idenType, displaying);
	}
	
	public void verifyCountryDisplayed(String idenType, boolean displaying){
		this.verifyIdenElementDisplayed(idenConutryIdRegx, idenType, displaying);
	}
	
	public void verifyStateDisplayed(String idenType, boolean displaying){
		this.verifyIdenElementDisplayed(idenProvinceRegx, idenType, displaying);
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
		this.verifyIdenNumDisplayed(idenType, hasNum);
		this.verifyCountryDisplayed(idenType, hasCountry);
		this.verifyStateDisplayed(idenType, hasProvince);
	}
	
	public void verifyIdenTypeUI(String idenType, boolean idenTypeSelected, boolean hasNum, boolean hasCountry){
		verifyIdenTypeUI(idenType, idenTypeSelected, hasNum, hasCountry, false);
	}
	
	public void verifyIdenTypeUI(String idenType, boolean idenTypeSelected, boolean hasNum){
		verifyIdenTypeUI(idenType, idenTypeSelected, hasNum, false, false);
	}
	
	public String getPageTitle() {
		return browser.getObjectText(".className", "pagetitle");
	}
	
	private IHtmlObject[] getIdentTypeLabels() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".for", new RegularExpression(idenTypeRegx, false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Identifier type labels can't be found.");
		}
		return objs;
	}
	
	public List<String> getAllIdenTypes(){
		List<String> values = new ArrayList<String>();
		IHtmlObject[] objs = this.getIdentTypeLabels();
		for(int i=0; i<objs.length; i++){
			values.add(objs[i].text().trim());
		}
		Browser.unregister(objs);
		return values;
	}
	
	public boolean isAddIdentBtnExist() {
		return browser.checkHtmlObjectExists(".id", "submitForm_submitForm", ".text", "Add Identification");
	}
	
	public boolean isCancelLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel");
	}
	
	private IHtmlObject[] getOptRadioBtns() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression(idenTypeRegx, false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find options radio buttons");
		}
		return objs;
	}
	
	public boolean isNoneRadioBtnSelected() {
		IHtmlObject[] objs = this.getOptRadioBtns();
		boolean result = true;
		for (IHtmlObject obj : objs) {
			result &= !((IRadioButton)obj).isSelected();
		}
		Browser.unregister(objs);
		return result;
	}
	
	public boolean isIdentNumLableExist() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".for", new RegularExpression(idenNumIdRegx, false));
	}
	
	public String  getIdentStateLable() {
		return browser.getObjectText(".class", "Html.LABEL", ".for", new RegularExpression(idenProvinceRegx, false));
	}
	
	public String getIdentCountryLable() {
		return browser.getObjectText(".class", "Html.LABEL", ".for", new RegularExpression(idenConutryIdRegx, false));
	}
	
	public String getDateOfBirth() {
		return browser.getTextFieldValue(".id", new RegularExpression(dobRegx, false));
	}
	
	public void setDateOfBirth(String value) {
		browser.setTextField(".id", new RegularExpression(dobRegx, false), value);
	}
	
	public void verifyDateOfBirth(String value) {
		String actualValue = this.getDateOfBirth();
		if(!value.equals(actualValue)){
			throw new ErrorOnPageException("Date of birth is wrong!", value, actualValue);
		}
		logger.info("Successfully verify date of birth:"+value);
	}
	
	public void clickContactCallCenterLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "error_item"), 
				Property.toPropertyArray(".class", "Html.A", ".text", "contact the call center")), true, 0);
	}
}

