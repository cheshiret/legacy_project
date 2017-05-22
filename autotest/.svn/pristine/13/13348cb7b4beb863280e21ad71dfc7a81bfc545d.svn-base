package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Feb 29, 2012
 */
public class CallMgrConfirmCustomerPage extends CallMgrCommonTopMenuPage {
	protected final String prefix = "(HF)?CustomerProfileView-\\d+\\.";

	static private CallMgrConfirmCustomerPage _instance = null;
	
	static public CallMgrConfirmCustomerPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrConfirmCustomerPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Confirm Customer");
	}
	
	public void clickEdit() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Edit");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public boolean checkFirstNameEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.firstName",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find first name text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkMidNameEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.middleName",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find middle name text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkLastNameEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.lastName",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find last name text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkSuffixEditable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("^"+prefix+"primaryContact\\.suffix",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find suffix dropdown list on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}

	public boolean checkDateOfBirthEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"dateOfBirth_ForDisplay",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find date of birth text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkHomePhoneEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.homePhone",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find home phone text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkBusinessPhoneEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.businessPhone",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find business phone text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkMobilePhoneEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("^"+prefix+"primaryContact\\.mobilePhone",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find mobile phone text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkGenderEditable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find gender dropdown list on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean checkAddressEditable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find address text field on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public void setDateOfBirth(String date) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false);
		browser.setTextField(".id", idPattern, date);
	}
	
	public void setLastName(String lName) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false);
		browser.setTextField(".id", idPattern, lName);
	}
	
	public boolean checkNewIdentifierExisted(){
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".id", "Existing_Identifier_Panel");
	}
	
	public void setMiddleName(String mName){
		browser.setTextField(".id",  new RegularExpression("^"+prefix+"primaryContact\\.middleName",false), mName);
	}
	
	public String getMiddleName(){
		return browser.getTextFieldValue(".id",  new RegularExpression("^"+prefix+"primaryContact\\.middleName",false));
	}
	
	
	public String getFirstName() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact.firstName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getLastName() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getSuffix(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getDateOfBirth() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public boolean getOverrideReqIdentifier(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		return browser.isCheckBoxSelected(".id", regx);
	}
	
	public String getOverrideReason(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideReason", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getHomePhone() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getBusinessPhome() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getMobilePhone() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getFax() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.fax", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getEmail() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.email", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getPhoneContactPre() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getPreferredContactTime() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerGender() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerEthnicity() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerSolicitationInd() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getCustomerLifetimeSlogan() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5034\\]",
				false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	/**
	 * Verify the customer profile.
	 * @param expectedCust - the customer detail info.
	 * 	 
	 */
	public boolean compareCustInfoSuccess(Customer expectedCust){
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Customer First name", expectedCust.fName, getFirstName());
		pass &= MiscFunctions.compareResult("Customer Last Name", expectedCust.lName, getLastName());
		pass &= MiscFunctions.compareResult("Customer Date of Birth", expectedCust.dateOfBirth, getDateOfBirth());
		pass &= MiscFunctions.compareResult("Customer Home Phone", expectedCust.hPhone, getHomePhone());
		pass &= MiscFunctions.compareResult("Customer Business Phone", expectedCust.bPhone, getBusinessPhome());
		pass &= MiscFunctions.compareResult("Customer Mobile Phone", expectedCust.mPhone, getMobilePhone());
		pass &= MiscFunctions.compareResult("Customer Gendar", expectedCust.custGender, getCustomerGender());
		pass &= MiscFunctions.compareResult("Customer Solicitation Indicator", expectedCust.solicitationIndcator, getCustomerSolicitationInd());
		pass &= MiscFunctions.compareResult("Customer Ethnicith", expectedCust.ethnicity, getCustomerEthnicity());
		pass &= MiscFunctions.compareResult("Customer Suffix", expectedCust.suffix, getSuffix());
		pass &= MiscFunctions.compareResult("Customer Override Required Identifiers", expectedCust.overrideReqId, getOverrideReqIdentifier());
		pass &= MiscFunctions.compareResult("Customer Override Reason", expectedCust.overideReason, getOverrideReason());
		pass &= MiscFunctions.compareResult("Customer Fax", expectedCust.fax, getFax());
		pass &= MiscFunctions.compareResult("Customer Email", expectedCust.email, getEmail());

		return pass;	
	}
	
	  public String getAddress(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.address",false);
	    	String text=browser.getTextFieldValue(".id", regx,index);
	    	return text;
	    }
	  
	    public String getSupplementalAddr(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.supplemental",false);
	    	String text=browser.getTextFieldValue(".id", regx,index);
	    	return text;
	    }
	    
	    public String getCity(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.city",false);
	    	String text=browser.getTextFieldValue(".id", regx,index);
	    	return text;
	    }
	    
	    public String getState(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.state",false);
	    	return browser.getDropdownListValue(".id", regx, index);
	    }
	    
	    public String getCounty(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.county",false);
	    	return browser.getDropdownListValue(".id", regx, index);
	    }
	    
	    public String getZip(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.zipCode",false);
	    	String text=browser.getTextFieldValue(".id", regx,index);
	    	return text;
	    }
	    
	    public String getCountry(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.country",false);
	    	return browser.getDropdownListValue(".id", regx, index);
	    }
	    
	    public String getStatus(int index){
	    	RegularExpression regx=new RegularExpression("AddressView-\\d+\\.verificationStatus.name",false);
	    	String text=browser.getTextFieldValue(".id", regx,index);
	    	return text;
	    }
	
	 public Address getCustomerAddress(int index){
	    	Address addr=new Address();
	    	addr.address=this.getAddress(index);
	    	addr.supplementalAddr=this.getSupplementalAddr(index);
	    	addr.city=this.getCity(index);
	    	addr.state=this.getState(index);
	    	addr.county=this.getCounty(index);
	    	addr.zip=this.getZip(index);
	    	addr.country=this.getCountry(index);
	    	addr.status=this.getStatus(index);
	    	
	    	if(index==0){
	    		addr.addrType="Physical address";
	    	}else if(index==1){
	    		addr.addrType="Mailing Address";
	    	}else{
	    		throw new ErrorOnDataException("Error on Data !");
	    	}
	    	
	    	return addr;
	    }
}
