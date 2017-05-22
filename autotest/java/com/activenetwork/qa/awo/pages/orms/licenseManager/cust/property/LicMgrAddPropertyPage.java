package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
 * @Date  Jan 13, 2014
 */
public class LicMgrAddPropertyPage extends LicMgrCommonTopMenuPage {
	static class SingletonHolder {
		protected static LicMgrAddPropertyPage _instance = new LicMgrAddPropertyPage();
	}

	protected LicMgrAddPropertyPage() {}

	public static LicMgrAddPropertyPage getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_PROPERTYID = "Property ID";
	private static String LABEL_CONSERVATIONNUM = "Conservation #";
	private static String LABEL_STATUS = "Status";
	private static String LABEL_CUSTOMERCLASS = "Customer Class";
	private static String LABEL_FIRSTNAME = "First Name";
	private static String LABEL_MIDDLENAME = "Middle Name";
	private static String LABEL_LASTNAME = "Last Name";
	private static String LABEL_SUFFIX = "Suffix";
	private static String LABEL_DATEOFBIRTH = "Date of Birth";
	
	/** Page Object Property Definition Begin */
	protected Property[] custPropertyDetailsDIV(){
		return Property.concatPropertyArray(div(), ".id", "CustomerProfilePropertyDetailPage");
	}

	protected Property[] propertyAddressTR(){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Property Address.*", false));
	}
	
	protected Property[] customerInfoTR(){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Customer Info.*", false));
	}
	
	protected Property[] propertyDetailsTR(){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Property Details.*", false));
	}
	
	protected Property[] labelSPAN(String label){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression("^"+label+".*", false));
	}
	
	protected Property[] textField(){
		return Property.concatPropertyArray(input("text"));
	}
	
	protected Property[] ddlField(){
		return Property.concatPropertyArray(select());
	}
	
	protected Property[] propertyCounty(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.landProperty.county", false));
	}

	protected Property[] propertyAcres(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.landProperty.acres", false));
	}

	protected Property[] propertyComments(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.landProperty.comments", false));
	}

	protected Property[] section(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5062\\]", false));
	}

	protected Property[] location(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5066\\]", false));
	}

	protected Property[] survey(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5061\\]", false));
	}

	protected Property[] parcel(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5065\\]", false));
	}

	protected Property[] range(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5064\\]", false));
	}

	protected Property[] directions(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5067\\]", false));
	}

	protected Property[] address(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.address", false));
	}

	protected Property[] addressZip(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.zipCode", false));
	}

	protected Property[] addressCountry(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.country", false));
	}

	protected Property[] addressStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.verificationStatus.name", false));
	}

	protected Property[] addressValidate(){
		return Property.concatPropertyArray(a(), ".text", "Validate");
	}

	protected Property[] supplementalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.supplemental", false));
	}

	protected Property[] addressCity(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.city", false));
	}

	protected Property[] addressState(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.state", false));
	}

	protected Property[] addressCounty(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+.county", false));
	}

	protected Property[] custID(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.customerProfile.customerNumber", false));
	}

	protected Property[] custDOB(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.customerProfile.dateOfBirth:DATE", false));
	}

	protected Property[] typeOfOwnership(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.ownershipType", false));
	}

	protected Property[] yearOwned(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.yearOwned", false));
	}

	protected Property[] corporation(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.corporation", false));
	}

	protected Property[] ownershipComments(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.comments", false));
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	protected Property[] apply(){
		return Property.concatPropertyArray(a(), ".text", "Apply");
	}
	
	protected Property[] errorMsg(String errorMsg){
		return Property.concatPropertyArray(div(), ".id", "NOTSET", ".className", "message msgerror", ".text", new RegularExpression(errorMsg, false));
	}
	
	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(), ".className", "message msgerror");
	}
	
	protected Property[] validateButton(){
		return Property.concatPropertyArray(a(), ".text", "Validate");
	}
	
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(custPropertyDetailsDIV());
	}

	public IHtmlObject[] getTDObjes(String label, Property[] topP){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(topP, labelSPAN(label)));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find TD with label:"+label);
		}
		return objs;
	}
	
	public IHtmlObject[] getTextFieldObjs(String label, IHtmlObject trTopObj){
		IHtmlObject[] objs = browser.getTextField(ddlField(), trTopObj);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find text field under TR with lable:"+label);
		}
		return objs;
	}
	
	public String getDisabledTextFieldValue(String label, Property[] topP){
		IHtmlObject[] objs = getTDObjes(label, topP);
		IHtmlObject[] objs1 = getTextFieldObjs(label, objs[0]);
		if(objs1[0].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("Text field should be disabled with label:"+label);
		}
		String value  = objs1[0].getProperty(".value");
		logger.info(label+":"+value);
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return value;
	}
	
	public String getDisabledDDLFieldValue(Property[] p){
		IHtmlObject[] objs = browser.getDropdownList(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find DDL");
		}
		if(objs[0].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("DDL field should be disabled");
		}
		String value  = browser.getDropdownListValue(p, 0);
		logger.info("Value:"+value);
		Browser.unregister(objs);
		return value;
	}
	
	public String getPropertyID(){
		return getDisabledTextFieldValue(LABEL_PROPERTYID, propertyDetailsTR());
	}
	
	public String getAddressCountry(){
		return getDisabledDDLFieldValue(addressCountry());
	}
	
	public String getAddressValidateStatus(){
		return getDisabledTextFieldValue(LABEL_STATUS, propertyAddressTR());
	}
	
	public String getAddressState(){
		return getDisabledDDLFieldValue(addressState());
	}
	
	public String getConservationNum(){
		return getDisabledTextFieldValue(LABEL_CONSERVATIONNUM, customerInfoTR());
	}

	public String getCustStatus(){
		return getDisabledTextFieldValue(LABEL_STATUS, customerInfoTR());
	}

	public String getCustomerClass(){
		return getDisabledTextFieldValue(LABEL_CUSTOMERCLASS, customerInfoTR());
	}
	
	public String getFirstName(){
		return getDisabledTextFieldValue(LABEL_FIRSTNAME, customerInfoTR());
	}
	
	public String getLastName(){
		return getDisabledTextFieldValue(LABEL_LASTNAME, customerInfoTR());
	}
	
	public String getMiddleName(){
		return getDisabledTextFieldValue(LABEL_MIDDLENAME, customerInfoTR());
	}
	
	public String getSuffix(){
		return getDisabledTextFieldValue(LABEL_SUFFIX, customerInfoTR());
	}
	
	public String getDateOfBirth(){
		return getDisabledTextFieldValue(LABEL_DATEOFBIRTH, customerInfoTR());
	}
	
	public void selectPropertyCounty(String propertyCounty){
		browser.selectDropdownList(propertyCounty(), propertyCounty);
	}

	public List<String> getPropertyCounties(){
		return browser.getDropdownElements(propertyCounty());
	}
	
	public void setPropertyAcres(String propertyAcres){
		browser.setTextField(propertyAcres(), propertyAcres);
	}

	public void setPropertyComments(String propertyComments){
		browser.setTextArea(propertyComments(), propertyComments);
	}

	public void setSection(String section){
		browser.setTextField(section(), section);
	}

	public void setLocation(String location){
		browser.setTextField(location(), location);
	}

	public void setSurvey(String survey){
		browser.setTextField(survey(), survey);
	}

	public void setParcel(String parcel){
		browser.setTextField(parcel(), parcel);
	}

	public void setRange(String range){
		browser.setTextField(range(), range);
	}

	public void setDirections(String directions){
		browser.setTextArea(directions(), directions);
	}

	public void setAddress(String address){
		browser.setTextField(address(), address);
	}

	public void setAddressZip(String addressZip){
		browser.setTextField(addressZip(), addressZip);
	}

	public void selectAddressCountry(String addressCountry){
		browser.selectDropdownList(addressCountry(), addressCountry);
	}

	public void setSupplementalAddress(String supplementalAddress){
		browser.setTextField(supplementalAddress(), supplementalAddress);
	}

	public void setAddressCity(String addressCity){
		browser.setTextField(addressCity(), addressCity);
	}

	public void selectAddressState(String addressState){
		browser.selectDropdownList(addressState(), addressState);
	}

	public void selectAddressCounty(String addressCounty){
		browser.selectDropdownList(addressCounty(), addressCounty);
	}

	public List<String> getAddressCounties(){
		return browser.getDropdownElements(addressCounty());
	}
	
	public void selectTypeOfOwnership(String typeOfOwnership){
		browser.selectDropdownList(typeOfOwnership(), typeOfOwnership);
	}

	public void setYearOwned(String yearOwned){
		browser.setTextField(yearOwned(), yearOwned);
	}

	public void setCorporation(String corporation){
		browser.setTextField(corporation(), corporation);
	}

	public void setOwnershipComments(String ownershipComments){
		browser.setTextField(ownershipComments(), ownershipComments);
	}

	public void clickOK(){
		browser.clickGuiObject(ok());
	}

	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}

	public void clickApply(){
		browser.clickGuiObject(apply());
	}

	public void clickValidate(){
		browser.clickGuiObject(validateButton());
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
	public boolean isErrorMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}
	
	public void verifyErrorMsgExisted(String msg, boolean isExist) {
		if (this.isErrorMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}
	
	public void setupPropertyDetails(Data<PropertyAttr> cpa){
		//Property Details
		selectPropertyCounty(cpa.stringValue(PropertyAttr.propertyCounty));
		setPropertyAcres(cpa.stringValue(PropertyAttr.propertyAcres));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.propertyComments)))
			setPropertyComments(cpa.stringValue(PropertyAttr.propertyComments));
		//Land Property Attribute Group
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.section)))
			setSection(cpa.stringValue(PropertyAttr.section));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.location)))
			setLocation(cpa.stringValue(PropertyAttr.location));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.survey)))
			setSurvey(cpa.stringValue(PropertyAttr.survey));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.parcel)))
			setParcel(cpa.stringValue(PropertyAttr.parcel));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.range)))
			setRange(cpa.stringValue(PropertyAttr.range));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.directions)))
			setDirections(cpa.stringValue(PropertyAttr.directions));
		//Property Address
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.address)))
			setAddress(cpa.stringValue(PropertyAttr.address));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressZip)))
			setAddressZip(cpa.stringValue(PropertyAttr.addressZip));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressCountry)))
			selectAddressCountry(cpa.stringValue(PropertyAttr.addressCountry));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.supplementalAddress)))
			setSupplementalAddress(cpa.stringValue(PropertyAttr.supplementalAddress));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressCity)))
			setAddressCity(cpa.stringValue(PropertyAttr.addressCity));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressState)))
			selectAddressState(cpa.stringValue(PropertyAttr.addressState));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressCounty))){
			selectAddressCounty(cpa.stringValue(PropertyAttr.addressCounty));
			ajax.waitLoading();
			waitLoading();
		}
		//Ownership Details
		selectTypeOfOwnership(cpa.stringValue(PropertyAttr.typeOfOwnership));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.yearOwned)))
			setYearOwned(cpa.stringValue(PropertyAttr.yearOwned));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.corporation)))
			setCorporation(cpa.stringValue(PropertyAttr.corporation));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.ownershipComments)))
			setOwnershipComments(cpa.stringValue(PropertyAttr.ownershipComments));
	}
}