package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 14, 2014
 */
public class LicMgrInstructorDetailsPage extends LicMgrActivityMGTCommonPage{
	static class SingletonHolder {
		protected static LicMgrInstructorDetailsPage _instance = new LicMgrInstructorDetailsPage();
	}

	protected LicMgrInstructorDetailsPage() {
	}

	public static LicMgrInstructorDetailsPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] instructorNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.customerNumber", false));
	}
	
	protected Property[] instructorStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.status", false));
	}
	
	protected Property[] creationApplication(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.creationInfo\\.applicationName", false));
	}
	
	protected Property[] creationDate(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.creationInfo\\.date", false));
	}
	
	protected Property[] creationUser(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.creationInfo\\.userName", false));
	}
	
	protected Property[] firstName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.firstName", false));
	}

	protected Property[] middleName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+.profile\\.primaryContact\\.middleName", false));
	}

	protected Property[] lastName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.lastName", false));
	}

	protected Property[] suffix(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.suffix", false));
	}

	protected Property[] dateOfBirth(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.dateOfBirth_ForDisplay", false));
	}

	protected Property[] instructorType(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.instructorType", false));
	}

	protected Property[] addNew(){
		return Property.concatPropertyArray(a(), ".text", "Add New");
	}

	protected Property[] homePhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.homePhone", false));
	}

	protected Property[] businessPhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.businessPhone", false));
	}

	protected Property[] mobilePhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.mobilePhone", false));
	}

	protected Property[] fax(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.fax", false));
	}

	protected Property[] email(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.email", false));
	}

	protected Property[] phoneContactPreference(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+.profile\\.primaryContact\\.phoneContactPreference", false));
	}

	protected Property[] preferredContactTime(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.preferredContactTime", false));
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
	
	protected Property[] changeHistory(){
		return Property.concatPropertyArray(a(), ".text", "Change History");
	}
	
	
	protected Property[] identifiersTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("customerTabs_\\d+", false), ".text", "Identifiers");
	}
	
	protected Property[] activityListTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("customerTabs_\\d+", false), ".text", "Activity List");
	}
	
	protected Property[] addressesTab(){
		return Property.concatPropertyArray(a(), ".id", new RegularExpression("customerTabs_\\d+", false), ".text", "Addresses");
	}
	
	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(),".className", new RegularExpression("message msg(error|success)", false), ".text", new RegularExpression(msg, false));
	}

	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(),".className", "message msgerror");
	}
	
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(instructorNumber());
	}

	public String getInstructorNum(){
		return browser.getTextFieldValue(instructorNumber());
	}
	
	public String getInstructorStatus(){
		return browser.getDropdownListValue(instructorStatus(), 0);
	}
	
	public String getCreationApplication(){
		return browser.getTextFieldValue(creationApplication());
	}
	
	public String getCreationDate(){
		return browser.getTextFieldValue(creationDate());
	}
	
	public String getCreationUser(){
		return browser.getTextFieldValue(creationUser());
	}
	
	public void setFirstName(String firstName){
		browser.setTextField(firstName(), firstName);
	}

	public String getFirstName(){
		return browser.getTextFieldValue(firstName());
	}
	
	public void setMiddleName(String middleName){
		browser.setTextField(middleName(), middleName);
	}

	public String getMiddleName(){
		return browser.getTextFieldValue(middleName());
	}
	
	public void setLastName(String lastName){
		browser.setTextField(lastName(), lastName);
	}

	public String getLastName(){
		return browser.getTextFieldValue(lastName());
	}
	
	public void selectSuffix(String suffix){
		browser.selectDropdownList(suffix(), suffix);
	}

	public String getSuffix(){
		return browser.getDropdownListValue(suffix(), 0);
	}
	
	public void setDateOfBirth(String dateOfBirth){
		browser.setTextField(dateOfBirth(), dateOfBirth);
	}

	public String getDateOfBirth(){
		return browser.getTextFieldValue(dateOfBirth());
	}
	
	public List<String> getInstructorTypes(){
		return browser.getDropdownElements(instructorType());
	}

	public void selectInstructorType(String instructorType){
		browser.selectDropdownList(instructorType(), instructorType);
	}

	public String getInstructorType(){
		return browser.getDropdownListValue(instructorType(), 0);
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(addNew());
	}

	public void setHomePhone(String homePhone){
		browser.setTextField(homePhone(), homePhone);
	}

	public String getHomePhone(){
		return browser.getTextFieldValue(homePhone());
	}
	
	public void setBusinessPhone(String businessPhone){
		browser.setTextField(businessPhone(), businessPhone);
	}

	public String getBusinessPhone(){
		return browser.getTextFieldValue(businessPhone());
	}
	
	public void setMobilePhone(String mobilePhone){
		browser.setTextField(mobilePhone(), mobilePhone);
	}

	public String getMobilePhone(){
		return browser.getTextFieldValue(mobilePhone());
	}
	
	public void setFax (String fax){
		browser.setTextField(fax(), fax);
	}

	public String getFax(){
		return browser.getTextFieldValue(fax());
	}
	
	public void setEmail(String email){
		browser.setTextField(email(), email);
	}

	public String getEmail(){
		return browser.getTextFieldValue(email());
	}
	
	public void selectPhoneContactPreference(String phoneContactPreference){
		browser.selectDropdownList(phoneContactPreference(), phoneContactPreference);
	}

	public String getPhoneContactPreference(){
		return browser.getDropdownListValue(phoneContactPreference(), 0);
	}
	
	public void selectPreferredContactTime(String preferredContactTime){
		browser.selectDropdownList(preferredContactTime(), preferredContactTime);
	}

	public String getPreferredContactTime(){
		return browser.getDropdownListValue(preferredContactTime(), 0);
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
		browser.clickGuiObject(changeHistory());
	}
	
	public void clickAddressesTab(){
		browser.clickGuiObject(addressesTab());
	}
	
	public void clickIdentifiersTab(){
		browser.clickGuiObject(identifiersTab());
	}
	
	public void clickActivityListTab(){
		browser.clickGuiObject(activityListTab());
	}
	
	public void updateInstructor(Customer customer){
		//Name/DOB
		setFirstName(customer.fName);
		setMiddleName(customer.mName);
		setLastName(customer.lName);
		if(StringUtil.notEmpty(customer.suffix))
			selectSuffix(customer.suffix);
		setDateOfBirth(customer.dateOfBirth);
		if(StringUtil.notEmpty(customer.instructorType))
			selectInstructorType(customer.instructorType);

		//Phone/Email
		if(StringUtil.isEmpty(customer.hPhone) && StringUtil.isEmpty(customer.bPhone) && StringUtil.isEmpty(customer.mPhone)) {
			customer.hPhone="9052866600";
		}
		setHomePhone(customer.hPhone);
		setBusinessPhone(customer.bPhone);
		setMobilePhone(customer.mPhone);
		setFax(customer.fax);
		setEmail(customer.email);
		if(StringUtil.notEmpty(customer.phoneContact))
			selectPhoneContactPreference(customer.phoneContact);
		if(StringUtil.notEmpty(customer.contactTime))
			selectPreferredContactTime(customer.contactTime);
	}

	public Customer getInstructorData() {
		Customer instructor = new Customer();
		instructor.instructorNum = getInstructorNum();
		instructor.status = getInstructorStatus();
		instructor.creationApplication = getCreationApplication();
		instructor.creationDate = getCreationDate();
		instructor.creationUser = getCreationUser();
		instructor.fName = getFirstName();
		instructor.mName = getMiddleName();
		instructor.lName = getLastName();
		instructor.suffix = getSuffix();
		instructor.dateOfBirth = getDateOfBirth();
		instructor.instructorType = getInstructorType();
		instructor.hPhone = getHomePhone();
		instructor.bPhone = getBusinessPhone();
		instructor.mPhone = getMobilePhone();
		instructor.fax = getFax();
		instructor.email = getEmail();
		instructor.phoneContact = getPhoneContactPreference();
		instructor.contactTime = getPreferredContactTime();
		return instructor;
	}
	
	public void verifyInstructorInfo(Customer expectedInstructor) {
		boolean result = true;
		Customer actualInstructor = getInstructorData();
		result &= MiscFunctions.compareResult("Instructor Number", expectedInstructor.instructorNum, actualInstructor.instructorNum);
		result &= MiscFunctions.compareResult("Instructor status", expectedInstructor.status, actualInstructor.status);
		result &= MiscFunctions.compareResult("Creation Application", expectedInstructor.creationApplication, actualInstructor.creationApplication);
		result &= MiscFunctions.compareResult("Creation Date", expectedInstructor.creationDate, actualInstructor.creationDate);
		result &= MiscFunctions.compareResult("Creation User", expectedInstructor.creationUser, actualInstructor.creationUser);
		result &= MiscFunctions.compareResult("First Name", expectedInstructor.fName, actualInstructor.fName);
		result &= MiscFunctions.compareResult("Middle Name", expectedInstructor.mName, actualInstructor.mName);
		result &= MiscFunctions.compareResult("Last Name", expectedInstructor.lName, actualInstructor.lName);
		result &= MiscFunctions.compareResult("Suffix", expectedInstructor.suffix, actualInstructor.suffix);
		result &= MiscFunctions.compareResult("Date of birth", expectedInstructor.dateOfBirth, actualInstructor.dateOfBirth);
		result &= MiscFunctions.compareResult("Instructor Type", expectedInstructor.instructorType, actualInstructor.instructorType);
		result &= MiscFunctions.compareResult("Home Phone", expectedInstructor.hPhone, actualInstructor.hPhone);
		result &= MiscFunctions.compareResult("Business Phone", expectedInstructor.bPhone, actualInstructor.bPhone);
		result &= MiscFunctions.compareResult("Mobile Phone", expectedInstructor.mPhone, actualInstructor.mPhone);
		result &= MiscFunctions.compareResult("Fax", expectedInstructor.fax, actualInstructor.fax);
		result &= MiscFunctions.compareResult("Email", expectedInstructor.email, actualInstructor.email);
		result &= MiscFunctions.compareResult("Phone Contact Preference", expectedInstructor.phoneContact, actualInstructor.phoneContact);
		result &= MiscFunctions.compareResult("Preferred Contact Time", expectedInstructor.contactTime, actualInstructor.contactTime);
		if(!result){
			throw new ErrorOnPageException("Failed to verify all check points are passed in instructor details page. Please check the details from previous page.");
		}
		logger.info("Successfully verify all check points are passed in instructor details page.");
	} 
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}

	public boolean isErrorMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}

	public void verifyErrorMsgExisted(String msg, boolean isExist) {
		if (isErrorMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}
}
