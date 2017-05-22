package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */
public class UwpUpdateProfilePage extends UwpPage {
	private static UwpUpdateProfilePage _instance = null;

	private String pageName = "Update Profile";

	public static UwpUpdateProfilePage getInstance() {
		if (null == _instance)
			_instance = new UwpUpdateProfilePage();

		return _instance;
	}

	public UwpUpdateProfilePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("[updateProfile|Update Profile]",false),".className","accountside in");
	}
	
	public String getNameRestrictNote(){
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Name.*",false));
		if(trs==null || trs.length<1){
			throw new ObjectNotFoundException("Can't find the Trs for Name");
		}
		
		IHtmlObject[] notes=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "restrnoteupdate"), trs[0]);
		if(notes==null || notes.length<1){
			throw new ObjectNotFoundException("can't find note div");
		}
		String note=notes[0].text();
		Browser.unregister(notes,trs);
		return note;
	}
	
	public void setNameInfo(String title,String fName,String initial,String lName){
		setFName(fName);
		setLName(lName);
		selectTitle(title);
		setInitial(initial);
	}


	/**
	 * Fill in First Name.
	 * @param fname - first name
	 */
	public void setFName(String fname) {
		browser.setTextField(".id", "fname", fname, true);
	}

	/**
	 * Fill in Last Name.
	 * @param lname - last name
	 */
	public void setLName(String lname) {
		browser.setTextField(".id", "lname", lname, true);
	}

	/**
	 * Fill in Home Phone.
	 * @param phone - phone number
	 */
	public void setHomePhone(String phone) {
		browser.setTextField(".id", "hphone", phone, true);
	}

	public void setWorkPhone(String bPhone) {
		browser.setTextField(".id", "wphone", bPhone);
	}
	
	public void setWorkExtension(String workExt) {
		browser.setTextField(".id", "ext", workExt);
	}
	/**
	 * Fill in organization name 
	 * @param orgNm
	 */
	public void setOrganization(String orgNm) {
		browser.setTextField(".id", new RegularExpression("organization(Name|)", false), orgNm);
	}
	/**
	 * Fill in customer address.
	 * @param address - address
	 */
	public void setAddress(String address) {
		browser.setTextField(".id", "address", address, true);
	}

	/**
	 * Fill in City field
	 * @param city - city
	 */
	public void setCity(String city) {
		browser.setTextField(".id", "city", city, true);
	}

	/**
	 * Fill in city zip field.
	 * @param zip - city zip
	 */
	public void setZip(String zip) {
		browser.setTextField(".id", "zip", zip, true);
	}

	/**
	 * Select state dropdown list.
	 * @param state - state
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".name", "state", state, true);
	}

	public void selectState(int index) {
		browser.selectDropdownList(".name", "state", index, true);
	}
	/**
	 * Select country dropdown list.
	 * @param country - country
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", "country", country, true);
	}

	/**
	 * Click button to save changes made.
	 */
	public void clickSaveChanges() {
//		RegularExpression reg = new RegularExpression(".*Save Changes",false);
		browser.clickGuiObject  (".text", "Save Changes",".type", "submit",true);
	}

	/**
	 * Fill in all fields in update profile page.
	 * @param memberProfile - customer info
	 */
	public void updateProfile(Customer memberProfile) {
		if(isNameChangable()) {
			this.setFName(memberProfile.fName);
			this.setLName(memberProfile.lName);
		}
		this.setHomePhone(memberProfile.hPhone);
		// Lesley[20130805]Add code to cover work phone/ext./organization field 
		this.setWorkPhone(memberProfile.bPhone);
		this.setWorkExtension(memberProfile.workExtension);
		this.setOrganization(memberProfile.organization);
		this.setAddress(memberProfile.address);
		this.setCity(memberProfile.city);
		if (StringUtil.isEmpty(memberProfile.state)) { //Lesley[20131202]: update to be able to select the empty state
			this.selectState(0);
		} else {
			this.selectState(memberProfile.state);
		}
		this.setZip(memberProfile.zip);
		this.selectCountry(memberProfile.country);
		this.clickSaveChanges();
	}
	
	/**
	 * detect if the customer name can be changed from UI
	 * @return
	 */
	public boolean isNameChangable() {
		String text=browser.getObjectText(".class","Html.DIV",".id","restrnoteupdate");
		// Names cannot be changed online once your Account has been created. Name changes must be done through our Call Center.
		return !(text!=null && text.contains("Names cannot be changed online"));
	}

	/**
	 * Retrieve customer first name.
	 * @return - first name
	 */
	public String getFName() {
		return browser.getTextFieldValue(".id", "fname");
	}

	/**
	 * Retrieve customer last name.
	 * @return - last name
	 */
	public String getLName() {
		return browser.getTextFieldValue(".id", "lname");
	}

	/**
	 * Retrieve customer home phone.
	 * @return - home phone
	 */
	public String getHomePhone() {
		return browser.getTextFieldValue(".id", "hphone");
	}

	/**
	 * Retrieve customer address.
	 * @return - address
	 */
	public String getAddress() {
		return browser.getTextFieldValue(".id", "address");
	}

	/**
	 * Retrieve customer city.
	 * @return - city
	 */
	public String getCity() {
		return browser.getTextFieldValue(".id", "city");
	}

	/**
	 * Retrieve customer city zip.
	 * @return - city zip
	 */
	public String getZip() {
		return browser.getTextFieldValue(".id", "zip");
	}

	/**
	 * Retrieve customer state.
	 * @return - state
	 */
	public String getState() {
		return browser.getDropdownListValue(".name", "state", 0);
	}

	/**
	 * Retrieve customer country.
	 * @return - country
	 */
	public String getCountry() {
		return browser.getDropdownListValue(".id", "country", 0);
	}

	public Address getMailingAddress() {
		Address addr = new Address();
		addr.address = this.getAddress();
		addr.city = this.getCity();
		addr.state = this.getState();
		addr.zip = this.getZip();
		addr.country = this.getCountry();
		return addr;
	}
	
	public String getWorkPhone() {
		return browser.getTextFieldValue(".id", "wphone");
	}
	
	public String getWorkExtension() {
		return browser.getTextFieldValue(".id", "ext");
	}
	
	public String getCellPhone() {
		return browser.getTextFieldValue(".id", "cphone");
	}
	
	public String getOrganizationNm() {
		return browser.getTextFieldValue(".id", new RegularExpression("organization(Name|)", false));
	}
	
	public String getOrganizationType() {
		return browser.getDropdownListValue(".id", "organizationType");
	}
	/**
	 * Verify customer info with given info
	 * @param memberProfile - customer info
	 */
	public void verifyProfile(Customer memberProfile) {
		String error = "";
		boolean nameChangable=this.isNameChangable();
		if (nameChangable && !this.getFName().equalsIgnoreCase(memberProfile.fName)) {
			error = (pageName + ":First names don't match!");
		} else if (nameChangable && !this.getLName().equalsIgnoreCase(memberProfile.lName)) {
			error = (pageName + ":Last names don't match!");
		} else if (!convert(this.getHomePhone()).equalsIgnoreCase(
				convert(memberProfile.hPhone))) {
			error = (pageName + ":Phone numbers don't match!");
		} else if (!this.getAddress().equalsIgnoreCase(memberProfile.address)) {
			error = (pageName + ":Addresses don't match!");
		} else if (!this.getCity().equalsIgnoreCase(memberProfile.city)) {
			error = (pageName + ":cities don't match!");
		} else if (!this.getState().equalsIgnoreCase(memberProfile.state)) {
			error = (pageName + ":states don't match!");
		} else if (!convert(this.getZip()).equalsIgnoreCase(
				convert(memberProfile.zip))) {
			error = (pageName + ":zip code don't match!");
		} else if (!this.getCountry().equalsIgnoreCase(memberProfile.country)) {
			error = (pageName + ":countries don't match!");
		} else if (StringUtil.notEmpty(memberProfile.bPhone) &&
				!convert(this.getWorkPhone()).equalsIgnoreCase((convert(memberProfile.bPhone)))) {//Lesley[20130806]: udpate to verify work phone/ext/mobile phone/organization
			error = (pageName + ":work phone don't match!");
		} else if (StringUtil.notEmpty(memberProfile.workExtension) && 
				!this.getWorkExtension().equalsIgnoreCase(memberProfile.workExtension)) {
			error = (pageName + ":work extension don't match!");
		} else if (StringUtil.notEmpty(memberProfile.mPhone) && 
				!convert(this.getCellPhone()).equalsIgnoreCase(convert(memberProfile.mPhone))) {
			error = (pageName + ":cell phone don't match!");
		} else if (StringUtil.notEmpty(memberProfile.organization) && 
				!this.getOrganizationNm().equalsIgnoreCase(memberProfile.organization)) {
			error = (pageName + ":organization don't match!");
		} else if (StringUtil.notEmpty(memberProfile.organizationType) && !this.getOrganizationType().equalsIgnoreCase(memberProfile.organizationType)) {
			error = (pageName + ":organization type don't match!");
		}
		
		if (error.length() > 0)
			throw new ItemNotFoundException(error);
	}

	/**
	 * Replace target string ' ' or '-' to null.
	 * @param str - target string
	 * @return - new string
	 */
	private String convert(String str) {
		str = str.replaceAll(" |-", "");
		return str;
	}
    /**
     *Astra LoadTest
     * @return
     */
	public String getNameInfo() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Name.*",false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find name TR");
		}
		IHtmlObject[] divs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "left",".text",new RegularExpression("[A-Za-z]+",false)),objs[0]);
		if(divs==null || divs.length<1){
			throw new ObjectNotFoundException("Can't find DIVs for Names");
		}
		String names="";
		for(IHtmlObject o: divs){
			names+=o.text()+" ";
		}
		Browser.unregister(objs,divs);
		return names.trim();
	}

	public boolean checkNameRestrictNoteExist(){
		return browser.checkHtmlObjectExists(".text", new RegularExpression(".*Names cannot be changed online once.*",false));
	}

	public String getInitial() {
		return browser.getTextFieldValue(".id", "initial");
	}

	public String getTitle() {
		return browser.getDropdownListValue(".id", "title");
	}

	public void selectTitle(String tempTitle) {
		browser.selectDropdownList(".id", "title", tempTitle);
	}

	public void setInitial(String initial) {
		browser.setTextField(".id", "initial", initial);
	}
}
