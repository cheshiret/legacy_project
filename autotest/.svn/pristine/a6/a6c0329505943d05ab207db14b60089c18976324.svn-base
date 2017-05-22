package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
 * @Date  Jan 14, 2014
 */
public class LicMgrPropertyDetailsPage extends LicMgrCommonTopMenuPage{
	static class SingletonHolder {
		protected static LicMgrPropertyDetailsPage _instance = new LicMgrPropertyDetailsPage();
	}

	protected LicMgrPropertyDetailsPage() {}

	public static LicMgrPropertyDetailsPage getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_PROPERTYID = "Property ID";
	private static String LABEL_CREATIONAPPLICATION = "Creation Application";
	private static String LABEL_CREATIONDATE = "Creation Date";
	private static String LABEL_CREATIONUSER = "Creation User";

	/** Page Object Property Definition Begin */
	public Property[] custPropertyDetailsDIV(){
		return Property.concatPropertyArray(div(), ".id", "CustomerProfilePropertyDetailPage");
	}

	protected Property[] physicalAddressTable(){
		return Property.concatPropertyArray(table(), ".id", "physicalAddress");
	}
	
	protected Property[] labelTD(String label){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression("^"+label+"$", false));
	}

	protected Property[] textField(){
		return Property.concatPropertyArray(input("text"));
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

	protected Property[] validateButton(){
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
		return Property.concatPropertyArray(div(), ".className", "message msgerror", ".text", new RegularExpression(errorMsg, false));
	}
	
	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(), ".className", "message msgerror");
	}
	
	protected Property[] changeHsitory(){
		return Property.concatPropertyArray(a(), ".text", "Change History");
	}
	
	protected Property[] auditsTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("propertyTabs_\\d+", false), ".text", new RegularExpression("Audits(\\(\\d+\\))?", false));
	}
	
	protected Property[] ownersTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("propertyTabs_\\d+", false), ".text", new RegularExpression("Owners(\\(\\d+\\))?", false));
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(custPropertyDetailsDIV());
	}

	public String getDisabledTextFieldValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(labelTD(label), textField()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find TD objects which label is "+label);
		}
		if(objs[0].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("Text field should be disabled with label:"+label);
		}
		String value = objs[0].getProperty(".value");
		logger.info(label+":"+value);
		Browser.unregister(objs);
		return value;
	}

	public String getPropertyID(){
		return getDisabledTextFieldValue(LABEL_PROPERTYID);
	}

	public void selectPropertyCounty(String propertyCounty){
		browser.selectDropdownList(propertyCounty(), propertyCounty);
	}

	public void selectPropertyCounty(int index){
		browser.selectDropdownList(propertyCounty(), index);
	}
	
	public String getPropertyCounty(){
		return browser.getDropdownListValue(propertyCounty(), 0);
	}

	public void setPropertyAcres(String propertyAcres){
		browser.setTextField(propertyAcres(), propertyAcres);
	}

	public String getPropertyAcres(){
		return browser.getTextFieldValue(propertyAcres());
	}

	public String getCreationApplication(){
		return getDisabledTextFieldValue(LABEL_CREATIONAPPLICATION);
	}

	public String getCreationData(){
		return getDisabledTextFieldValue(LABEL_CREATIONDATE);
	}

	public String getCreationUser(){
		return getDisabledTextFieldValue(LABEL_CREATIONUSER);
	}

	public void setPropertyComments(String propertyComments){
		browser.setTextArea(propertyComments(), propertyComments);
	}

	public String getPropertyComments(){
		return browser.getTextAreaValue(propertyComments());
	}

	public void setSection(String section){
		browser.setTextField(section(), section);
	}

	public String getSection(){
		return browser.getTextFieldValue(section());
	}

	public void setLocation(String location){
		browser.setTextField(location(), location);
	}

	public String getLocation(){
		return browser.getTextFieldValue(location());
	}

	public void setSurvey(String survey){
		browser.setTextField(survey(), survey);
	}

	public String getSurvey(){
		return browser.getTextFieldValue(survey());
	}

	public void setParcel(String parcel){
		browser.setTextField(parcel(), parcel);
	}

	public String getParcel(){
		return browser.getTextFieldValue(parcel());
	}

	public void setRange(String range){
		browser.setTextField(range(), range);
	}

	public String getRange(){
		return browser.getTextFieldValue(range());
	}

	public void setDirections(String directions){
		browser.setTextArea(directions(), directions);
	}

	public String getDirections(){
		return browser.getTextAreaValue(directions());
	}

	public void setAddress(String address){
		browser.setTextField(address(), address);
	}

	public String getAddress(){
		return browser.getTextFieldValue(address());
	}

	public void setAddressZip(String addressZip){
		browser.setTextField(addressZip(), addressZip);
	}

	public String getAddressZip(){
		return browser.getTextFieldValue(addressZip());
	}

	public void selectAddressCountry(String addressCountry){
		browser.selectDropdownList(addressCountry(), addressCountry);
	}

	public String getAddressCountry(){
		return browser.getDropdownListValue(addressCountry(), 0);
	}

	public String getAddressValidateStatus(){
		return browser.getTextFieldValue(addressStatus());
	}

	public void clickValidate(){
		browser.clickGuiObject(Property.atList(physicalAddressTable(),validateButton()));
	}
	
	public void setSupplementalAddress(String supplementalAddress){
		browser.setTextField(supplementalAddress(), supplementalAddress);
	}

	public String getSupplementalAddress(){
		return browser.getTextFieldValue(supplementalAddress());
	}

	public void setAddressCity(String addressCity){
		browser.setTextField(addressCity(), addressCity);
	}

	public String getAddressCity(){
		return browser.getTextFieldValue(addressCity());
	}

	public void selectAddressState(String addressState){
		browser.selectDropdownList(addressState(), addressState);
	}

	public String getAddressState(){
		return browser.getDropdownListValue(addressState(), 0);
	}

	public void selectAddressCounty(String addressCounty){
		browser.selectDropdownList(addressCounty(), addressCounty);
	}

	public String getAddressCounty(){
		return browser.getDropdownListValue(addressCounty(), 0);
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

	public void clickChangeHistory(){
		browser.clickGuiObject(changeHsitory());
	}
	
	public void clickOwnersTab(){
		browser.clickGuiObject(ownersTab());
	}
	
	public int getOwnersNum(){
		String ownerTab = browser.getObjectText(ownersTab());
		String[] number = RegularExpression.getMatches(ownerTab, "\\d+");
		if(number==null | number.length<1){
			return 0;
		}else return Integer.valueOf(number[0]);
	}
	
	public void clickAuditsTab(){
		browser.clickGuiObject(auditsTab());
	}
	
	public int getAuditsNum(){
		String auditsNum = browser.getObjectText(auditsTab());
		String[] number = RegularExpression.getMatches(auditsNum, "\\d+");
		if(number==null | number.length<1){
			return 0;
		}else return Integer.valueOf(number[0]);
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
	
	public void updatePropertyDetails(Data<PropertyAttr> cpa){
		//Property Details
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.propertyCounty)))
			selectPropertyCounty(cpa.stringValue(PropertyAttr.propertyCounty));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.propertyAcres)))
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
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.supplementalAddress)))
			setSupplementalAddress(cpa.stringValue(PropertyAttr.supplementalAddress));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressCity)))
			setAddressCity(cpa.stringValue(PropertyAttr.addressCity));
		if(StringUtil.notEmpty(cpa.stringValue(PropertyAttr.addressCounty))){
			if(getAddressCounty().toString().contains(cpa.stringValue(PropertyAttr.addressCounty))){
				selectAddressCounty(cpa.stringValue(PropertyAttr.addressCounty));
			}else {
				clickValidate();
				ajax.waitLoading();
			}
		}
	}

	public Data<PropertyAttr> getCustPropertyData() {
		Data<PropertyAttr> cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyID, getPropertyID());
		cpa.put(PropertyAttr.propertyCounty, getPropertyCounty());
		cpa.put(PropertyAttr.propertyAcres, getPropertyAcres());
		cpa.put(PropertyAttr.creationApplication, getCreationApplication());
		cpa.put(PropertyAttr.creationData, getCreationData());
		cpa.put(PropertyAttr.creationUser, getCreationUser());
		cpa.put(PropertyAttr.propertyComments, getPropertyComments());
		cpa.put(PropertyAttr.section, getSection());
		cpa.put(PropertyAttr.location, getLocation());
		cpa.put(PropertyAttr.survey, getSurvey());
		cpa.put(PropertyAttr.parcel, getParcel());
		cpa.put(PropertyAttr.range, getRange());
		cpa.put(PropertyAttr.directions, getDirections());
		cpa.put(PropertyAttr.address, getAddress());
		cpa.put(PropertyAttr.addressZip, getAddressZip());
		cpa.put(PropertyAttr.addressCountry, getAddressCountry());
		cpa.put(PropertyAttr.addressInvalidateStatus, getAddressValidateStatus());
		cpa.put(PropertyAttr.supplementalAddress, getSupplementalAddress());
		cpa.put(PropertyAttr.addressCity, getAddressCity());
		cpa.put(PropertyAttr.addressState, getAddressState());
		cpa.put(PropertyAttr.addressCounty, getAddressCounty());
		return cpa;
	}

	public void verifyCustPropertInfo(Data<PropertyAttr> cpa){
		Data<PropertyAttr> cpaFromUI = getCustPropertyData();		
		//Property Details
		boolean result = MiscFunctions.compareResult("Property ID", cpa.stringValue(PropertyAttr.propertyID), cpaFromUI.stringValue(PropertyAttr.propertyID));
		result &= MiscFunctions.compareResult("Property County", cpa.stringValue(PropertyAttr.propertyCounty), cpaFromUI.stringValue(PropertyAttr.propertyCounty));
		result &= MiscFunctions.compareResult("Property Acres", cpa.stringValue(PropertyAttr.propertyAcres), cpaFromUI.stringValue(PropertyAttr.propertyAcres));
		result &= MiscFunctions.compareResult("Creation Application", cpa.stringValue(PropertyAttr.creationApplication), cpaFromUI.stringValue(PropertyAttr.creationApplication));
		result &= MiscFunctions.compareResult("Creation Data", cpa.stringValue(PropertyAttr.creationData), cpaFromUI.stringValue(PropertyAttr.creationData));
		result &= MiscFunctions.compareResult("Creation User", cpa.stringValue(PropertyAttr.creationUser), cpaFromUI.stringValue(PropertyAttr.creationUser));
		result &= MiscFunctions.compareResult("Property Comments", cpa.stringValue(PropertyAttr.propertyComments), cpaFromUI.stringValue(PropertyAttr.propertyComments));
		//Land Property Attribute Group
		result &= MiscFunctions.compareResult("Section", cpa.stringValue(PropertyAttr.section), cpaFromUI.stringValue(PropertyAttr.section));
		result &= MiscFunctions.compareResult("Location", cpa.stringValue(PropertyAttr.location), cpaFromUI.stringValue(PropertyAttr.location));
		result &= MiscFunctions.compareResult("Survey", cpa.stringValue(PropertyAttr.survey), cpaFromUI.stringValue(PropertyAttr.survey));
		result &= MiscFunctions.compareResult("Parcel", cpa.stringValue(PropertyAttr.parcel), cpaFromUI.stringValue(PropertyAttr.parcel));
		result &= MiscFunctions.compareResult("Range", cpa.stringValue(PropertyAttr.range), cpaFromUI.stringValue(PropertyAttr.range));
		result &= MiscFunctions.compareResult("Direction", cpa.stringValue(PropertyAttr.directions), cpaFromUI.stringValue(PropertyAttr.directions));
		//Property Address
		result &= MiscFunctions.compareResult("Address", cpa.stringValue(PropertyAttr.address), cpaFromUI.stringValue(PropertyAttr.address));
		result &= MiscFunctions.compareResult("Address zip", cpa.stringValue(PropertyAttr.addressZip), cpaFromUI.stringValue(PropertyAttr.addressZip));
		result &= MiscFunctions.compareResult("Address Country", cpa.stringValue(PropertyAttr.addressCountry), cpaFromUI.stringValue(PropertyAttr.addressCountry));
		result &= MiscFunctions.compareResult("Address Validate Status", cpa.stringValue(PropertyAttr.addressInvalidateStatus), cpaFromUI.stringValue(PropertyAttr.addressInvalidateStatus));
		result &= MiscFunctions.compareResult("Supplemental Address", cpa.stringValue(PropertyAttr.supplementalAddress), cpaFromUI.stringValue(PropertyAttr.supplementalAddress));
		result &= MiscFunctions.compareResult("Address City", cpa.stringValue(PropertyAttr.addressCity), cpaFromUI.stringValue(PropertyAttr.addressCity));
		result &= MiscFunctions.compareResult("Address State", cpa.stringValue(PropertyAttr.addressState), cpaFromUI.stringValue(PropertyAttr.addressState));
		result &= MiscFunctions.compareResult("Address County", cpa.stringValue(PropertyAttr.addressCounty), cpaFromUI.stringValue(PropertyAttr.addressCounty));

        if(!result){
        	throw new ErrorOnPageException("Not all check points are passed in customer property details page.");
        }
        logger.info("Successfully verify all check points in customer property details page.");
	}
}