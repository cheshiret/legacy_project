
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpCreateAccountPage extends UwpPage {
	private static UwpCreateAccountPage _instance = null;

	public static UwpCreateAccountPage getInstance() {
		if (null == _instance)
			_instance = new UwpCreateAccountPage();

		return _instance;
	}

	public UwpCreateAccountPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "createaccountbtn");
	}
	/**
	 * Fill in emial field
	 * @param email - email account
	 */
	public void setEmail(String email) {
		browser.setTextField(".id", "email", email);
	}
	/**
	 * Fill in password
	 * @param pw - password
	 */
	public void setPassword(String pw) {
		browser.setPasswordField(".id", "pw", pw);
	}
	/**
	 * Fill in password again
	 * @param pw - password
	 */
	public void setRetypePassword(String pw) {
		browser.setPasswordField(".id", "repw", pw);
	}
	/**
	 * Fill in first name
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "fname", fName);
	}
	/**
	 * Fill in last name
	 * @param lName - last naem
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lname", lName);
	}
	/**
	 * Fill in phone number
	 * @param phone - phone number
	 */
	public void setPhone(String phone) {
		browser.setTextField(".id", "hphone", phone);
	}
	/**
	 * Fill in address
	 * @param address - address
	 */
	public void setAddress(String address) {
		browser.setTextField(".id", "address", address);
	}
	/**
	 * Fill in user's city
	 * @param city - city
	 */
	public void setCity(String city) {
		browser.setTextField(".id", "city", city);
	}
	/**
	 * Fill im user's province
	 * @param province - province
	 */
	public void selectProvince(String province) {
		//.name = stateProvince for orms 286 or older
		//.name = state for orms 3.0 or newer
		RegularExpression namePattern=new RegularExpression("^state(Province)?$",false);
		browser.selectDropdownList(".name", namePattern, province);
	}
	/**
	 * Fill in user city's post code
	 * @param zip - post code
	 */
	public void setPostCode(String zip) {
		browser.setTextField(".id", "zip", zip);
	}
	/**
	 * Select user's country
	 * @param country - country
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", "country", country);
	}
	/**
	 * Click on 'Create Account' button
	 */
	public void clickCreateAccountButton() {
		browser.clickGuiObject(".id", "createaccountbtn");
	}
	/**
	 * Select Keep me informed checkbox
	 */
	public void selectCheckBoxKeepMeInfored() {
		browser.selectCheckBox(".id", "keepme");
	}
	/**
	 * 	Deselect Keep me informed checkbox
	 */
	public void deselectCheckBoxKeepMeInfored() {
		browser.unSelectCheckBox(".id", "keepme");
	}
	/**
	 * Create a new customer account.
	 * @param cust - customer details info
	 */
	public void createNewMemberAccount(Customer cust) {
		// Mandatory fields 
		this.setEmail(cust.email);
		this.setPassword(cust.password);
		this.setRetypePassword(cust.password);
		this.setFirstName(cust.fName);
		this.setLastName(cust.lName);
		this.setPhone(cust.hPhone);
		this.setAddress(cust.address);
		this.setCity(cust.city);
		this.selectProvince(cust.state);
		this.setPostCode(cust.zip);
		this.selectCountry(cust.country);

		if (cust.optIn != null && cust.optIn != "") {
			if ((cust.optIn.toLowerCase()).matches("(yes)|(true)|(y)")) {
				this.selectCheckBoxKeepMeInfored();
			} else if ((cust.optIn.toLowerCase()).matches("(no)|(false)|(n)")) {
				this.deselectCheckBoxKeepMeInfored();
			} else
				System.out.println("optIn can't be recongnized!");
		}
		this.clickCreateAccountButton();
	}
	
	/** Verify the error message on page when using an existing email to sign up */
	public void verifyEmailExistError(){
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "infochangeform");
	  
	  	String content = objs[0].getProperty(".text");
	  	if(content.matches(".*email address you have entered is already in our system.*")||
	  	  content.matches(".*email address has already been registered.*")){
	  	  	logger.info(" --- The account is already in our system.");
	  	}else {
	  	  	throw new ErrorOnPageException("Account does not exist in our system.");
	  	}
	  
	  	Browser.unregister(objs);
	}
	
	public boolean checkNameRestrictNoteExist(){
		return browser.checkHtmlObjectExists(".text", new RegularExpression(".*Names cannot be changed online once.*",false));
	}
	
	public String getNameRestrictNote(){
//		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Name.*Title.*",false));
//		if(trs==null || trs.length<1){
//			throw new ObjectNotFoundException("Can't find the Trs for Name");
//		}
		
		IHtmlObject[] notes=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "restrnotecreate"));
		if(notes==null || notes.length<1){
			throw new ObjectNotFoundException("can't find note div");
		}
		String note=notes[0].text();
		Browser.unregister(notes);
		return note;
	}

	/**
	 * REC.gov
	 */
	public void clickPrivacyStatementLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "....Privacy Statement");
	}
}
