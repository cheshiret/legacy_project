/**
 * LicMgrVendorDetailAddAndContractsPage.java
 * Jul 20, 2011
 * QA
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author eliang
 * @Date  Jul 20, 2011
 */
public class LicMgrVendorDetailAddAndContractsPage extends LicMgrVendorDetailsPage{
	private static LicMgrVendorDetailAddAndContractsPage instance=null;
	private String prefixReg = "^AddressView-[0-9]*.";
	private String prefixReg1 = "^ContactView-[0-9]*.";
	
	protected LicMgrVendorDetailAddAndContractsPage(){}
	
	public static LicMgrVendorDetailAddAndContractsPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrVendorDetailAddAndContractsPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Addresses & Contacts");
	}
	
	public void setPhyAddress(String add){
		browser.setTextField(".id",new RegularExpression(prefixReg+"address", false), add,0);
	}
	
	public void setPhySuppAddress(String add){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), add, 0);
	}
	
	public void setPhyCityTown(String city){
		browser.setTextField(".id",new RegularExpression(prefixReg+"city", false), city,0);
	}
	
	public void selectPhyState(String state){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"state", false), state,0);
	}
	
	public void deSelectPhyState(){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"state", false), 0, 0);
	}
	
	public void selectPhyCounty(String county){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"county", false), county,0);
	}
	
	public void deSelectPhyCounty(){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"county", false), 0,0);
	}
	
	public void setPhyZip(String zip){
		browser.setTextField(".id",new RegularExpression(prefixReg+"zipCode", false), zip,0);
	}
	
	/**select physical country*/
	public void selectPhyCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country, 0);
	}
	
	public void deSelectPhyCountry(){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), 0, 0);
	}
	
	public void clickValidateOfPhysicalAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 0);
	}
	
	public void uncheckMailingAdd(){
		browser.unSelectCheckBox(".id",new RegularExpression(prefixReg+"sameAddress", false));
	}
	
	/**check the mailing address checkbox*/
	public void checkMaillingAdd(){
		browser.selectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress",false));
	}
	
	public void setMailingAdd(String mailingAdd){
		browser.setTextField(".id",new RegularExpression(prefixReg+"address", false), mailingAdd,1);
	}
	
	public void setMailingSuppAddress(String add){
		browser.setTextField(".id",  new RegularExpression("AddressView-\\d+\\.supplemental",false), add,1);
	}
	
	public void setMailingCity(String mailingCity){
		browser.setTextField(".id",new RegularExpression(prefixReg+"city", false),mailingCity,1);
	}
	
	public void selectMailingState(String state){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"state", false), state,1);
	}
	
	public void deSelectMailingState(){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"state", false), 0,1);
	}
	
	public void selectMailingCounty(String county){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"county", false), county,1);
	}
	
	public void deSelectMailingCounty(){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"county", false), 0,1);
	}
	
	public void setMailingZip(String zipCode){
		browser.setTextField(".id",new RegularExpression(prefixReg+"zipCode", false), zipCode,1);
	}
	
	/**select mailing country*/
	public void selectMailingCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country,1);
	}
	
	public void deSelectMailingCountry(){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), 0,1);
	}
	
	public void clickValidateOfMailingAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 1);
	}
	
	public void setAlterAddress(String add){
		browser.setTextField(".id",new RegularExpression(prefixReg+"address", false), add,2);
	}
	
	public void setAlterSuppAddress(String add){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), add, 2);
	}
	
	public void setAlterCityTown(String city){
		browser.setTextField(".id",new RegularExpression(prefixReg+"city", false), city,2);
	}
	
	public void selectAlterState(String state){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"state", false), state,2);
	}
	
	public void selectAlterCounty(String county){
		browser.selectDropdownList(".id",new RegularExpression(prefixReg+"county", false), county,2);
	}
	
	public void setAlterZip(String zip){
		browser.setTextField(".id",new RegularExpression(prefixReg+"zipCode", false), zip,2);
	}
	
	/**select physical country*/
	public void selectAlterCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country, 2);
	}
	
	public void clickValidateOfAlterAddress(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 2);
	}
	
	//-----------Get methods------------
	
	public String getPhyAddress(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"address", false),0);
	}
	
	public String getPhySupplAddress(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"supplemental", false),0);
	}
	
	public String getPhyCityTown(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"city", false),0);
	}
	
	public String getPhyState(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"state", false),0);
	}
	
	public String getPhyCounty(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"county", false),0);
	}
	
	public String getPhyZip(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"zipCode", false),0);
	}
	
	public String getPhyCountry(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"country", false), 0);
	}
	
	public String getPhyStatus(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"verificationStatus.name", false),0);
	}
	
	public boolean getMailingSameAsPhy(){
		return browser.isCheckBoxSelected(".id",new RegularExpression(prefixReg+"sameAddress", false));
	}
	
	public String getMailingAdd(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"address", false),1);
	}
	
	public String getMailingSupplAddress(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"supplemental", false),1);
	}
	
	public String getMailingCityTown(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"city", false),1);
	}
	
	public String getMailingState(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"state", false),1);
	}
	
	public String getMailingCounty(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"county", false),1);
	}
	
	public String getMailingZip(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"zipCode", false),1);
	}
	
	public String getMailingCountry(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"country", false), 1);
	}
	
	public String getMailingStatus(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"verificationStatus.name", false),1);
	}
	
	public String getAlterAddress(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"address", false),2);
	}
	
	public String getAlterSupplAddress(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"supplemental", false),2);
	}
	
	public String getAlterCityTown(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"city", false),2).trim();
	}
	
	public String getAlterState(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"state", false),2);
	}
	
	public String getAlterCounty(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"county", false),2);
	}
	
	public String getAlterZip(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"zipCode", false),2);
	}
	
	public String getAlterCountry(){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg+"country", false), 2);
	}
	
	public String getAlterStatus(){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg+"verificationStatus.name", false),2);
	}

	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public String getContractType(int i){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg1+"contactType", false),i);
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public boolean getPrimary(int i){
		IHtmlObject objs[] = browser.getRadioButton(".id",new RegularExpression(prefixReg1+"primary", false));
		
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no radio button which id = prefixReg1+"+"primary");
		}
		
		if(objs.length-1<i){
			throw new ObjectNotFoundException("The num of radio button you search is less than "+i);
		}
		
		boolean selected = ((IRadioButton)objs[i]).isSelected();
		
		Browser.unregister(objs);
		return selected;
	}
	
	public String getContractFirstName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"firstName", false),i);
	}
	
	public String getContractMidName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"middleName", false),i);
	}
	
	public String getContractLastName(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"lastName", false),i);
	}
	
	public String getContractSuffix(int i){
		return browser.getDropdownListValue(".id",new RegularExpression(prefixReg1+"suffix", false),i);
	}
	
	public String getBusinessPhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"businessPhone", false),i);
	}
	
	public String getHomePhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"homePhone", false),i);
	}
	
	public String getMobilePhone(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"mobilePhone", false),i);
	}
	
	public String getFax(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"fax", false),i);
	}
	
	public String getEmail(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"email", false),i);
	}
	
	public String getWebsite(int i){
		return browser.getTextFieldValue(".id",new RegularExpression(prefixReg1+"website", false),i);
	}
	
	public boolean isContactRemoveButtonOfIndexExist(int index){
		boolean isExist;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contact_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Contact table object not found.");
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		IHtmlObject[] removeObjs = browser.getHtmlObject(p, objs[0]);
		
		if(index < removeObjs.length){
			isExist = true;
		}else {
			isExist = false;
		}

		Browser.unregister(removeObjs);
		Browser.unregister(objs);
		return isExist;
	}
	
	/**select contact type*/
	public void selectContactType(String type, int index){
		browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.contactType",false), type,index);
	}
	
	public void selectPrimary(int index){
		browser.selectRadioButton(".id", new RegularExpression("ContactView-\\d+\\.primary",false), index);
	}
	
	/**set contact first name*/
	public void setContactFirstName(String fname,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.firstName",false), fname,index);
	}
	
	public void setContactMiddleName(String midName,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.middleName",false), midName, index);
	}
	
	/**set the contact last name*/
	public void setContactLastName(String lname,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.lastName",false), lname,index);
	}
	
	public void selectContactSuffix(String suffix, int index){
		browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.suffix",false), suffix,index);
	}
	
	/**set business phone*/
	public void setContactBusinessPhone(String phone,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false), phone,index);
	}
	
	public void setContactHomePhone(String phone, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false), phone, index);
	}
	
	public void setContactMobilePhone(String phone, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false), phone, index);
	}
	
	/**set contract fax*/
	public void setContactFax(String fax,int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.fax",false),fax,index);
	}
	
	/**set contact email*/
	public void setContactEmail(String email, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.email",false),email,index);
	}

	public void setContactWebsite(String website, int index){
		browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.website",false), website, index);
	}
	
	public void removeContactInfoByIndex(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contact_table");
		if(objs.length<1){
			throw new ItemNotFoundException("Contact table object not found.");
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.A");
		p[1] = new Property(".text","Remove");
		browser.clickGuiObject(p, true, index, objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean checkContactTypeIsEditable(int index){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("ContactView-\\d+.contactType",false));
		
		if(objs.length<1 && objs.length<index){
			throw new ItemNotFoundException("Contact type object " + index + " is not found.");
		}
		boolean enabled = ((ISelect)objs[index]).isEnabled();
		
		Browser.unregister(objs);
		return enabled;
	}
	
	public void clickSave(){
		browser.clickGuiObject(".class", "Html.A", ".text","Save");
	}
	
	public int getContactIndex(Contacts contact){
		List<Contacts> contactList = this.getContractsInfo();
		int index = -1;
		for(int i=0; i<contactList.size();i++){
			if(contactList.get(i).contactType.equals(contact.contactType) &&
					String.valueOf(contactList.get(i).isPrimary).equals(String.valueOf(contact.isPrimary)) &&
					contactList.get(i).firstName.equals(contact.firstName) &&
					contactList.get(i).midName.equals(contact.midName) &&
					contactList.get(i).lastName.equals(contact.lastName) &&
					contactList.get(i).suffix.equals(contact.suffix) &&
					contactList.get(i).businessPhone.equals(contact.businessPhone) &&
					contactList.get(i).homePhone.equals(contact.homePhone) &&
					contactList.get(i).mobilePhone.equals(contact.mobilePhone) &&
					contactList.get(i).fax.equals(contact.fax) &&
					contactList.get(i).email.equals(contact.email) &&
					contactList.get(i).website.equals(contact.website)){
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public void setupVendorAddressAndContactInfo(VendorInfo vendor){
		//setup physical address info
		this.setupVendorPhyAddressInfo(vendor);		
		//setup mailing address info
		this.setupVendorMailingAddressInfo(vendor);		
		//setup alter address info
		this.setupVendorAlterAddressInfo(vendor);		
		//setup contact info
		this.setupVendorContactInfo(vendor.contacts, vendor.removedContacts);
	}
	
	public void setupVendorPhyAddressInfo(VendorInfo vendor){
		
		this.setPhyAddress(vendor.phyAddress);
		if(null != vendor.phySuppAddress && vendor.phySuppAddress.length()>0){
			this.setPhySuppAddress(vendor.phySuppAddress);
		}
		this.setPhyCityTown(vendor.phyCityTown);
		
		if(vendor.phyCountry.trim().length()>0){
			this.selectPhyCountry(vendor.phyCountry);
		}else {
			this.deSelectPhyCountry();
		}
		ajax.waitLoading();	
		
		if(vendor.phyStateProvince.trim().length()>0){
			this.selectPhyState(vendor.phyStateProvince);
		}else {
			this.deSelectPhyState();
		}
		ajax.waitLoading();
		if(vendor.phyCounty.trim().length()>0){
			this.selectPhyCounty(vendor.phyCounty);
		}else {
			this.deSelectPhyCounty();
		}
		ajax.waitLoading();
		
		this.setPhyZip(vendor.phyZipPostal);
	
		if(vendor.isValidatePhysicalAdd){
			this.clickValidateOfPhysicalAdd();
			ajax.waitLoading();
		}
	}
	
	public void setupVendorMailingAddressInfo(VendorInfo vendor){
		if(!vendor.isMailingAddSameAsPhysicalAdd){
			this.uncheckMailingAdd();
			ajax.waitLoading();
			
			this.setMailingAdd(vendor.mailingAddress);
			if(null != vendor.mailingSuppAddress && vendor.mailingSuppAddress.length()>0){
				this.setMailingSuppAddress(vendor.mailingSuppAddress);
			}
			this.setMailingCity(vendor.mailingCityTown);
			
			if(vendor.mailingCountry.trim().length()>0){
				this.selectMailingCountry(vendor.mailingCountry);
			}else {
				this.deSelectMailingCountry();
			}
			ajax.waitLoading();	
			
			if(vendor.mailingStateProvince.trim().length()>0){
				this.selectMailingState(vendor.mailingStateProvince);
			}else {
				this.deSelectMailingState();
			}
			ajax.waitLoading();
			if(vendor.mailingCounty.trim().length()>0){
				this.selectMailingCounty(vendor.mailingCounty);
			}else {
				this.deSelectMailingCounty();
			}
			ajax.waitLoading();
			this.setMailingZip(vendor.mailingZipPostal);
			
			
			if(vendor.isValidateMailingAdd){
				this.clickValidateOfMailingAdd();
				ajax.waitLoading();
			}		
		}else {
			this.checkMaillingAdd();
			ajax.waitLoading();
		}
	}
	
	public void setupVendorAlterAddressInfo(VendorInfo vendor){
		this.setAlterAddress(vendor.alterAddress);
		if(null != vendor.alterSuppAddress && vendor.alterSuppAddress.length()>0){
			this.setAlterSuppAddress(vendor.alterSuppAddress);
		}
		this.selectAlterCountry(vendor.alterCountry);
		ajax.waitLoading();		
		this.setAlterCityTown(vendor.alterCityTown);
		this.selectAlterState(vendor.alterStateProvince);
		ajax.waitLoading();
		this.selectAlterCounty(vendor.alterCounty);
		ajax.waitLoading();
		this.setAlterZip(vendor.alterZipPostal);
		if(vendor.isValidateAlterAdd){
			this.clickValidateOfAlterAddress();
			ajax.waitLoading();
		}
	}
	
	public void setupVendorContactInfo(List<Contacts> contacts,List<Contacts> removedContacts){
		for(int i=0; i<contacts.size(); i++){
			if(0 != i && !this.isContactRemoveButtonOfIndexExist(i)){
				this.clickAddContract();
				ajax.waitLoading();
			}
			if(this.checkContactTypeIsEditable(i)){
				this.selectContactType(contacts.get(i).contactType, i);
			}
			if(contacts.get(i).isPrimary){
				this.selectPrimary(i);
				ajax.waitLoading();
			}
			this.setContactFirstName(contacts.get(i).firstName, i);
			if(null != contacts.get(i).midName){
				this.setContactMiddleName(contacts.get(i).midName, i);
			}
			this.setContactLastName(contacts.get(i).lastName, i);
			if(null != contacts.get(i).suffix){
				this.selectContactSuffix(contacts.get(i).suffix, i);
			}
			if(null != contacts.get(i).businessPhone){
				this.setContactBusinessPhone(contacts.get(i).businessPhone, i);
			}
			if(null != contacts.get(i).homePhone){
				this.setContactHomePhone(contacts.get(i).homePhone, i);
			}
			if(null != contacts.get(i).mobilePhone){
				this.setContactMobilePhone(contacts.get(i).mobilePhone, i);
			}
			if(null != contacts.get(i).fax){
				this.setContactFax(contacts.get(i).fax, i);
			}
			if(null != contacts.get(i).email){
				this.setContactEmail(contacts.get(i).email, i);
			}
			if(null != contacts.get(i).website){
				this.setContactWebsite(contacts.get(i).website, i);
			}
		}
		//whether remove contacts info, if need to remove, base on remove index, could remove contacts info
		
		if(null != removedContacts && removedContacts.size()>0){
			for(int i=0; i<removedContacts.size(); i++){
				int index = this.getContactIndex(removedContacts.get(i));
				if(index != -1){
					this.removeContactInfoByIndex(index);
					ajax.waitLoading();
				}else {
					throw new ErrorOnPageException("Expect removed contact not exists.");
				}
			}
		}
	}
	
	public List<Contacts> getContractsInfo(){
		List<Contacts> list = new ArrayList<Contacts>();
		IHtmlObject objs[] = browser.getRadioButton(".id", new RegularExpression(prefixReg1+"primary", false));
		
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is no contract id = prefixReg1"+"primary");
		}
		int num = objs.length;
		
		for(int i=0;i<num;i++){
			Contacts contract = new Contacts();
			contract.contactType=getContractType(i);
			contract.isPrimary=getPrimary(i);
			contract.firstName=getContractFirstName(i);
			contract.midName=getContractMidName(i);
			contract.lastName=getContractLastName(i);
			contract.suffix=getContractSuffix(i);
			contract.businessPhone=getBusinessPhone(i);
			contract.homePhone=StringUtil.formatPhoneNumToJustNumbers(getHomePhone(i));
			contract.mobilePhone=StringUtil.formatPhoneNumToJustNumbers(getMobilePhone(i));
			contract.fax=StringUtil.formatPhoneNumToJustNumbers(getFax(i));
			contract.email=getEmail(i);
			contract.website=getWebsite(i);
			
			list.add(contract);
		}
		
		Browser.unregister(objs);
		return list;
	}
	
	public boolean compareVendorAddressAndContactInfo(VendorInfo expVendor){
		boolean result = true;
		String acutalValue = "";
		
		acutalValue = this.getPhyAddress();
		if(!acutalValue.equals(expVendor.phyAddress)){
			result &= false;
			logger.error("Expect vendor physical address should be " + expVendor.phyAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhySupplAddress();
		if(!acutalValue.equals(expVendor.phySuppAddress)){
			result &= false;
			logger.error("Expect vendor physical supplement address should be " + expVendor.phySuppAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyCityTown();
		if(!acutalValue.equals(expVendor.phyCityTown)){
			result &= false;
			logger.error("Expect vendor physical city town should be " + expVendor.phyCityTown 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyState();
		if(!acutalValue.equals(expVendor.phyStateProvince)){
			result &= false;
			logger.error("Expect vendor physical state province should be " + expVendor.phyStateProvince 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyCounty();
		if(!acutalValue.equals(expVendor.phyCounty)){
			result &= false;
			logger.error("Expect vendor physical county should be " + expVendor.phyCounty 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyZip();
		if(!acutalValue.equals(expVendor.phyZipPostal)){
			result &= false;
			logger.error("Expect vendor physical zip postal should be " + expVendor.phyZipPostal 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyCountry();
		if(!acutalValue.equals(expVendor.phyCountry)){
			result &= false;
			logger.error("Expect vendor physical country should be " + expVendor.phyCountry 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getPhyStatus();
		if(!expVendor.phyAddStatus.contains(acutalValue)) {
			result &= false;
			logger.error("Expect vendor physical address status should be " + expVendor.phyAddStatus 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingAdd();
		if(!acutalValue.equals(expVendor.mailingAddress)){
			result &= false;
			logger.error("Expect vendor mailing address should be " + expVendor.mailingAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingSupplAddress();
		if(!acutalValue.equals(expVendor.mailingSuppAddress)){
			result &= false;
			logger.error("Expect vendor mailing supplement address should be " + expVendor.mailingSuppAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingCityTown();
		if(!acutalValue.equals(expVendor.mailingCityTown)){
			result &= false;
			logger.error("Expect vendor mailing city town should be " + expVendor.mailingCityTown 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingState();
		if(!acutalValue.equals(expVendor.mailingStateProvince)){
			result &= false;
			logger.error("Expect vendor mailing state province should be " + expVendor.mailingStateProvince 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingCounty();
		if(!acutalValue.equals(expVendor.mailingCounty)){
			result &= false;
			logger.error("Expect vendor mailing county should be " + expVendor.mailingCounty 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingZip();
		if(!acutalValue.equals(expVendor.mailingZipPostal)){
			result &= false;
			logger.error("Expect vendor mailing zip postal should be " + expVendor.mailingZipPostal 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingCountry();
		if(!acutalValue.equals(expVendor.mailingCountry)){
			result &= false;
			logger.error("Expect vendor mailing country should be " + expVendor.mailingCountry 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getMailingStatus();
		if(!expVendor.mailingAddStatus.contains(acutalValue)){
			result &= false;
			logger.error("Expect vendor mailing address status should be " + expVendor.mailingAddStatus 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterAddress();
		if(!acutalValue.equals(expVendor.alterAddress)){
			result &= false;
			logger.error("Expect vendor alter address should be " + expVendor.alterAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterSupplAddress();
		if(!acutalValue.equals(expVendor.alterSuppAddress)){
			result &= false;
			logger.error("Expect vendor alter supplement address should be " + expVendor.alterSuppAddress 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterCityTown();
		if(!acutalValue.equals(expVendor.alterCityTown)){
			result &= false;
			logger.error("Expect vendor alter city town should be " + expVendor.alterCityTown 
					+ ", but acutally is " + acutalValue + ".");
		}
		
		acutalValue = this.getAlterState();
		if(!acutalValue.trim().equals(expVendor.alterStateProvince)){
			result &= false;
			logger.error("Expect vendor alter state province should be " + expVendor.alterStateProvince 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterCounty();
		if(!acutalValue.equals(expVendor.alterCounty)){
			result &= false;
			logger.error("Expect vendor alter county should be " + expVendor.alterCounty 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterZip();
		if(!acutalValue.equals(expVendor.alterZipPostal)){
			result &= false;
			logger.error("Expect vendor alter zip postal should be " + expVendor.alterZipPostal 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterCountry();
		if(!acutalValue.equals(expVendor.alterCountry)){
			result &= false;
			logger.error("Expect vendor alter country should be " + expVendor.alterCountry 
					+ ", but acutally is " + acutalValue);
		}
		
		acutalValue = this.getAlterStatus();
		if(!expVendor.alterAddStatus.contains(acutalValue)) {
			result &= false;
			logger.error("Expect vendor alter address status should be " + expVendor.alterAddStatus 
					+ ", but acutally is " + acutalValue);
		}
		
		List<Contacts> auctualContacts = this.getContractsInfo();
		if(auctualContacts.size() != expVendor.contacts.size()){
			result &= false;
			logger.error("Expect vendor contact info should have " + expVendor.contacts.size()
					+ ", but acutally is " +auctualContacts.size());
		}else{
			for(int i=0; i<expVendor.contacts.size(); i++){
				if(!auctualContacts.get(i).contactType.equals(expVendor.contacts.get(i).contactType)){
					result &= false;
					logger.error("Expect the " + i +" contact type should be " + expVendor.contacts.get(i).contactType
							+ ", but actually is " + auctualContacts.get(i).contactType);
				}
				if(auctualContacts.get(i).isPrimary){
					if(!expVendor.contacts.get(i).isPrimary){
						result &= false;
						logger.error("Expect the " + i +" contact should not be primary, but acutally is primary.");
					}
				}else {
					if(expVendor.contacts.get(i).isPrimary){
						result &= false;
						logger.error("Expect the " + i +" contact should  be primary, but acutally is not primary.");
					}
				}
				if(!auctualContacts.get(i).firstName.equals(expVendor.contacts.get(i).firstName)){
					result &= false;
					logger.error("Expect the " + i + " contact first name should be " + expVendor.contacts.get(i).firstName
							+ ", but acutally is " + auctualContacts.get(i).firstName);
				}
				if(!auctualContacts.get(i).midName.equals(expVendor.contacts.get(i).midName)){
					result &= false;
					logger.error("Expect the " + i + " contact middle name should be " + expVendor.contacts.get(i).midName
							+ ", but acutally is " + auctualContacts.get(i).midName);
				}
				if(!auctualContacts.get(i).lastName.equals(expVendor.contacts.get(i).lastName)){
					result &= false;
					logger.error("Expect the " + i + " contact last name should be " + expVendor.contacts.get(i).lastName
							+ ", but acutally is " + auctualContacts.get(i).lastName);
				}
				if(!auctualContacts.get(i).suffix.equals(expVendor.contacts.get(i).suffix)){
					result &= false;
					logger.error("Expect the " + i + " contact suffix should be " + expVendor.contacts.get(i).suffix
							+ ", but acutally is " + auctualContacts.get(i).suffix);
				}
				if(!auctualContacts.get(i).businessPhone.equals(expVendor.contacts.get(i).businessPhone)){
					result &= false;
					logger.error("Expect the " + i + " contact business phone should be " + expVendor.contacts.get(i).businessPhone
							+ ", but acutally is " + auctualContacts.get(i).businessPhone);
				}
				if(!auctualContacts.get(i).homePhone.equals(expVendor.contacts.get(i).homePhone)){
					result &= false;
					logger.error("Expect the " + i + " contact home phone should be " + expVendor.contacts.get(i).homePhone
							+ ", but acutally is " + auctualContacts.get(i).homePhone);
				}
				if(!auctualContacts.get(i).mobilePhone.equals(expVendor.contacts.get(i).mobilePhone)){
					result &= false;
					logger.error("Expect the " + i + " contact mobile phone should be " + expVendor.contacts.get(i).mobilePhone
							+ ", but acutally is " + auctualContacts.get(i).mobilePhone);
				}
				if(!auctualContacts.get(i).fax.equals(expVendor.contacts.get(i).fax)){
					result &= false;
					logger.error("Expect the " + i + " contact fax should be " + expVendor.contacts.get(i).fax
							+ ", but acutally is " + auctualContacts.get(i).fax);
				}
				if(!auctualContacts.get(i).email.equals(expVendor.contacts.get(i).email)){
					result &= false;
					logger.error("Expect the " + i + " contact email should be " + expVendor.contacts.get(i).email
							+ ", but acutally is " + auctualContacts.get(i).email);
				}
				if(!auctualContacts.get(i).website.equals(expVendor.contacts.get(i).website)){
					result &= false;
					logger.error("Expect the " + i + " contact web site should be " + expVendor.contacts.get(i).website
							+ ", but acutally is " + auctualContacts.get(i).website);
				}
			}
			
		}
		
		return result;
	}
}
