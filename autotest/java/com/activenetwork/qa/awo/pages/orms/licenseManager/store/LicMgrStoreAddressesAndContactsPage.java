package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 19, 2011
 */
public class LicMgrStoreAddressesAndContactsPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreAddressesAndContactsPage _instance = null;
	
	protected LicMgrStoreAddressesAndContactsPage() {}
	
	public static LicMgrStoreAddressesAndContactsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreAddressesAndContactsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false));
	}
	
	/*****************Physical Address info*******************/
	public String getPhysicalAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address", false), 0);
	}
	
	public String getPhysicalSupplementalAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), 0);
	}
	
	public String getPhysicalCityTown() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city", false), 0);
	}
	
	public String getPhysicalStateProvince() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.state", false), 0);
	}
	
	public String getPhysicalCounty() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.county", false), 0);
	}
	
	public String getPhysicalZipPostal() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false), 0);
	}
	
	public String getPhysicalCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.country", false), 0);
	}
	
	public String getPhysicalStatus() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus.name", false), 0);
	}
	
	public void clickPhysicalValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 0);
	}
	
	public boolean isMailingAddressSamesAsPhysicalAddressSelected() {
		return browser.isCheckBoxSelected(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
	}
	
	/**
	 * Check the 'Mailing Address same as Physical Address' check box
	 */
	public void checkMailingAddressSameAsPhysicalAddress() {
		browser.selectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
		ajax.waitLoading();
	}
	
	/**
	 * Un-check the 'Mailing Address same as Physical Address' check box
	 */
	public void uncheckMailingAddressSameAsPhysicalAddress() {
		browser.unSelectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
		ajax.waitLoading();
	}
	
	/*****************Mailing Address info*******************/
	public String getMailingAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address", false), 1);
	}
	
	public String getMailingSupplementalAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), 1);
	}
	
	public String getMailingCityTown() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city", false), 1);
	}
	
	public String getMailingStateProvince() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.state", false), 1);
	}
	
	public String getMailingCounty() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.county", false), 1);
	}
	
	public String getMailingZipPostal() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false), 1);
	}
	
	public String getMailingCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.country", false), 1);
	}
	
	public String getMailingStatus() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus.name", false), 1);
	}
	
	public void clickMailingValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 1);
	}
	
	/*****************Alternate Address info*******************/
	public String getAlternateAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address", false), 2);
	}
	
	public String getAlternateSupplementalAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), 2);
	}
	
	public String getAlternateCityTown() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city", false), 2);
	}
	
	public String getAlternateStateProvince() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.state", false), 2);
	}
	
	public String getAlternateCounty() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.county", false), 2);
	}
	
	public String getAlternateZipPostal() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false), 2);
	}
	
	public String getAlternateCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.country", false), 2);
	}
	
	public String getAlternateStatus() {
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus.name", false), 2);
	}
	
	public void clickAlternateValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 2);
	}
	
	/*******************Contacts Info*********************/
	public String getContactType() {
		return browser.getDropdownListValue(".id", new RegularExpression("ContactView-\\d+\\.contactType", false), 0);
	}
	
	
	
	public String getFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.firstName", false), 0);
	}
	
	public String getMiddleName() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.firstName", false), 0);
	}
	
	public String getLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.lastName", false), 0);
	}
	
	public String getSuffix() {
		return browser.getDropdownListValue(".id", new RegularExpression("ContactView-\\d+\\.suffix", false), 0);
	}
	
	public String getBusinessPhone() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.businessPhone", false), 0);
	}
	
	public String getHomePhone() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.homePhone", false), 0);
	}
	
	public String getMobilePhone() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone", false), 0);
	}
	
	public String getFax() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.fax", false), 0);
	}
	
	public String getEmail() {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.email", false), 0);
	}
		
	public String getWebsite(int index) {
		return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.website", false), 0);
	}
	
	public void clickRemove() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	public void clickAddContact() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Contact", false));
		ajax.waitLoading();
	}
	
	public void clickSave() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save");
	}
	
	/**
	 * Get the fax .
	 * @return String - the value of fax.
	 */
    public String getFax(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.fax",false),index);
    }
    
    /**
	 * Get the email .
	 * @return String - the value of email.
	 */
    public String getEmail(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.email",false),index);
    }
    
    /**
	 * Get the website .
	 * @return String - the value of website.
	 */
   public String getWebSite(int index){
	   return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.website",false),index);
   }
   
   /**
	 * Get the moblie phone .
	 * @return String - the value of moblie phone.
	 */
   public String getMobilePhone(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false),index);
   }
   
   /**
	 * Get the home phone .
	 * @return String - the value of home phone.
	 */
   public String getHomePhone(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false),index);
   }
   
   /**
	 * Get the business phone .
	 * @return String - the value of business phone.
	 */
   public String getBusinessPhone(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false),index);
   }
   
   /**
	 * Get the suffix .
	 * @return String - the value of suffix.
	 */
   public String getSuffix(int index){
   	return browser.getDropdownListValue(".id", new RegularExpression("ContactView-\\d+\\.suffix",false),index);
   }
   
   /**
	 * Get the last name .
	 * @return String - the value of last name.
	 */
   public String getLastName(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.lastName",false),index);
   }
   
   /**
	 * Get the middle name .
	 * @return String - the value of middle name.
	 */
   public String getMiddleName(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.middleName",false),index);
   }
   
   /**
	 * Get the contact type.
	 * @return String - the value of contact type.
	 */
   public String getContactType(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.contactType",false),index);
   }
  
   
   /**
	 * Get the first name.
	 * @return String - the value of first name.
	 */
   public String getFirstName(int index){
   	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.firstName", false),index);
   }
   
   /**
	 * Select the address .
	 * @param address - the value of address.
	 */
	public void setAddress(String address,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false), address,index);
	}
	
	
	/**
	 * Select the state .
	 * @param state - the value of state.
	 */
	public void selectState(String state, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state",false), state, index);	
	}
	/**
	 * Set the city .
	 * @param city - the value of city.
	 */
	public void setCity(String city, int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city",false), city, index);
	}
	/**
	 * Set the supplemental Address .
	 * @param suppleAddress - the value of supplemental.
	 */
	public void setSuppleAddress(String suppleAddress,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), suppleAddress, index);
	}

   
	/**
	 * Select the county .
	 * @param county - the value of county.
	 */
	public void selectCounty(String county, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), county, index);
	}
	
	/**
	 * Set the zip .
	 * @param zip - the value of zip.
	 */
	public void setZip(String zip, int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), zip, index);
	}
	
	/**
	 * Set the country .
	 * @param country - the value of country.
	 */
	public void selectCountry(String country, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country,index);		
	}
	
	/**
	 * Set the physical address info
	 * @param phyAddress - the address info.
	 */
	public void setPhyAddress(Address phyAddress) {
		this.setAddress(phyAddress, 0);
	}

 /**
	 * Set the mailing address info
	 * @param mailingAddress - the address info.
	 */
	public void setMailingAddress(Address mailingAddress) {
		this.setAddress(mailingAddress, 1);
	}
	
	public void setAlternateaddress(Address alternateAddress){
		this.setAddress(alternateAddress, 2);
	}
    
    
    /**
	 * Set first name .
	 * @param name - the value of name.
	 */
    public void setFirstName(String name, int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.firstName", false), name, index);
    }
    
    /**
	 * Set middle name .
	 * @param name - the value of middle name.
	 */
    public void setMiddleName(String midName, int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.middleName",false), midName, index);  	
    }
    
 
    /**
	 * Set last name .
	 * @param name - the value of last name.
	 */
    public void setLastName(String lastName,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.lastName",false), lastName,index);
    }
 
    /**
	 * select suffix .
	 * @param suffix - the value of last suffix.
	 */
    public void selectSuffix(String suffix,int index){
    	browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.suffix",false), suffix,index);
    }
    
    
    /**
	 * set business phone .
	 * @param bPhone - the value of last business phone.
	 */
    public void setBusinessPhone(String bPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false), bPhone,index);
    }
    
    /**
	 * set home phone .
	 * @param hPhone - the value of home phone.
	 */
    public void setHomePhone(String hPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false), hPhone,index);
    }
    
    /**
	 * set mobile phone .
	 * @param hPhone - the value of mobile phone .
	 */
    public void setMobilePhone(String mPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false), mPhone,index);
    }
    
    
    /**
	 * set fax .
	 * @param hPhone - the value of fax .
	 */
    public void setFax(String fax,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.fax",false), fax,index);
    }
    
    
    /**
	 * set email .
	 * @param hPhone - the value of email .
	 */
    public void setEmail(String email,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.email",false), email,index);
    }
    
    
    /**
	 * set website .
	 * @param hPhone - the value of website .
	 */
   public void setWebSite(String website, int index){
	   browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.website",false), website, index);
   }
   
   
  
  /**
	 * Select the primary radio box.
	 * @param index - the index of primary radio box.
	 */
 public void selectPrimary(int index){
	   browser.selectRadioButton(".id", new RegularExpression("ContactView-\\d+\\.primary",false), index);
 }
 
 /**
	 * Click remove button .
	 */
 public void clickRemove(int index){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);	   
 }
 
	/**
	 * Click validate button .
	 */
	public void clickValidate(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", index);
	}
 
 /**
	 * Select the check box of Mailing Address same as Physical Address.
	 */
	public void selectMailSamePhyAddress(){
		browser.selectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress",false));
	}
	
	/**
	 * unSelect the check box of Mailing Address same as Physical Address.
	 */
	public void unSelectMailSamePhyAddress(){
		browser.unSelectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress",false));
	}
	/**
	 * Select the contactType .
	 * @param contactType - the value of contactType.
	 */
    public void selectContactType(String contactType,int index){
    	browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.contactType",false), contactType,index);
    }
    
	
	/**
	 * Set the contacts info.
	 * @param Contacts -  the contact info.
	 */
	public void setContacts(ArrayList<Contacts> Contacts) {
		for (int i = 0; i < Contacts.size(); i++) {
			if(Contacts.get(i).contactType.length()>0){
				this.selectContactType(Contacts.get(i).contactType,i);
			}
			if(Contacts.get(i).firstName.length()>0){
				this.setFirstName(Contacts.get(i).firstName, i);
			}
			if(Contacts.get(i).midName.length()>0){
				this.setMiddleName(Contacts.get(i).midName, i);
			}
			if(Contacts.get(i).lastName.length()>0){
				this.setLastName(Contacts.get(i).lastName, i);
			}
			if(Contacts.get(i).suffix.length() > 0){
				this.selectSuffix(Contacts.get(i).suffix, i);
			}
			if(Contacts.get(i).businessPhone.length()>0){
				this.setBusinessPhone(Contacts.get(i).businessPhone, i);
			}
			if(Contacts.get(i).homePhone.length()>0){
				this.setHomePhone(Contacts.get(i).homePhone, i);
			}
			if(Contacts.get(i).mobilePhone.length()>0){
				this.setMobilePhone(Contacts.get(i).mobilePhone, i);
			}
			if(Contacts.get(i).fax.length()>0){
				this.setFax(Contacts.get(i).fax, i);
			}
			if(Contacts.get(i).email.length()>0){
				this.setEmail(Contacts.get(i).email, i);
			}
			if(Contacts.get(i).website.length()>0){
				this.setWebSite(Contacts.get(i).website, i);
			}
			this.selectPrimary(i);
			if (i < Contacts.size() - 1) {
				this.clickAddContact();
				ajax.waitLoading();
			}
		}
	}
	
	/**
	 * Set the address info
	 * 
	 * @param address
	 *            - the address info.
	 * @param index
	 *            - the index of address.
	 */
	private void setAddress(Address address, int index) {
		if(address.address.length() > 0){
			this.setAddress(address.address, index);
		}
	    if(address.supplementalAddr.length() > 0){
	    	this.setSuppleAddress(address.supplementalAddr, index);
	    }
	    
		if(address.country.length()>0){
			this.selectCountry(address.country, index);
			ajax.waitLoading();
		}
		
	   if(address.city.length() >0){
		   this.setCity(address.city, index);  
	   }
		if(address.state.length()>0){
			this.selectState(address.state, index);
			ajax.waitLoading();
		}
		if(address.county.length()>0){
			this.selectCounty(address.county, index);
			ajax.waitLoading();
		}
		if(address.zip.length() > 0){
			this.setZip(address.zip, index);
		}
		if (address.isValidate) {
			this.clickValidate(index);
		}
		ajax.waitLoading();
	}
	/**
	 * Edit the contacts info including set and remove contacts info.
	 * @param Contacts -  the contact info.
	 */
	public void editContacts(ArrayList<Contacts> Contacts) {
		this.setContacts(Contacts);
		this.RemoveContacts(Contacts);
	}
	/**
	 * Remove the contacts.
	 * @param Contacts -  the contact info.
	 */
	public void RemoveContacts(ArrayList<Contacts> Contacts){
		for (int i = 0; i < Contacts.size() - 1; i++) {
			this.clickRemove(i);
			ajax.waitLoading();
		}
	}
	/**
	 * Set the store info
	 * 
	 * @param storeInfo
	 *            - the store info.
	 */
	public void setAddressAndContactsInfo(StoreInfo storeInfo) {
		this.setAddress(storeInfo.physicalAddress, 0);
		if (storeInfo.isMailSamePhyAddress) {
			if(isMailingAddressSamesAsPhysicalAddressSelected()) {
				this.unSelectMailSamePhyAddress();
				ajax.waitLoading();
			}
			this.selectMailSamePhyAddress();
			ajax.waitLoading();
		} else {
			this.unSelectMailSamePhyAddress();
			ajax.waitLoading();
			this.setAddress(storeInfo.mailingAddress, 1);
		}
		
		this.setAddress(storeInfo.AlterAddress, 2);
		this.editContacts(storeInfo.contactArray);
	}
	
	/**
	 * Compare the contacts and address info.
	 * 
	 * @param storeInfo- the expected store info.
	 */
	public boolean CompareStoreAddAndContactsInfo(StoreInfo expectedStore){
		boolean pass = true;
		
		pass &= MiscFunctions.compareResult("Physical Address", expectedStore.physicalAddress.address, getPhysicalAddress());
		pass &= MiscFunctions.compareResult("Physical Address Supplemental Address", expectedStore.physicalAddress.supplementalAddr, getPhysicalSupplementalAddress());
		pass &= MiscFunctions.compareResult("Physical Address City", expectedStore.physicalAddress.city, getPhysicalCityTown());
		pass &= MiscFunctions.compareResult("Physical Address State", expectedStore.physicalAddress.state, getPhysicalStateProvince());
		pass &= MiscFunctions.compareResult("Physical Address County", expectedStore.physicalAddress.county, getPhysicalCounty());
		pass &= MiscFunctions.compareResult("Physical Address Zip", expectedStore.physicalAddress.zip, getPhysicalZipPostal());
		pass &= MiscFunctions.compareResult("Physical Address Country", expectedStore.physicalAddress.country, getPhysicalCountry());
		
		pass &= MiscFunctions.compareResult("Mailing Address", expectedStore.mailingAddress.address, getMailingAddress());
		pass &= MiscFunctions.compareResult("Mailing Address Supplemental Address", expectedStore.mailingAddress.supplementalAddr, getMailingSupplementalAddress());
		pass &= MiscFunctions.compareResult("Mailing Address City", expectedStore.mailingAddress.city, getMailingCityTown());
		pass &= MiscFunctions.compareResult("Mailing Address State", expectedStore.mailingAddress.state, getMailingStateProvince());
		pass &= MiscFunctions.compareResult("Mailing Address County", expectedStore.mailingAddress.county, getMailingCounty());
		pass &= MiscFunctions.compareResult("Mailing Address Zip", expectedStore.mailingAddress.zip, getMailingZipPostal());
		pass &= MiscFunctions.compareResult("Mailing Address Country", expectedStore.mailingAddress.country, getMailingCountry());

		pass &= MiscFunctions.compareResult("Alternate Address", expectedStore.AlterAddress.address, getAlternateAddress());
		pass &= MiscFunctions.compareResult("Alternate Address Supplemental Address", expectedStore.AlterAddress.supplementalAddr, getAlternateSupplementalAddress());
		pass &= MiscFunctions.compareResult("Alternate Address City", expectedStore.AlterAddress.city, getAlternateCityTown());
		pass &= MiscFunctions.compareResult("Alternate Address State", expectedStore.AlterAddress.state, getAlternateStateProvince());
		pass &= MiscFunctions.compareResult("Alternate Address County", expectedStore.AlterAddress.county, getAlternateCounty());
		pass &= MiscFunctions.compareResult("Alternate Address Zip", expectedStore.AlterAddress.zip, getAlternateZipPostal());
		pass &= MiscFunctions.compareResult("Alternate Address Country", expectedStore.AlterAddress.country, getAlternateCountry());
		
		for(int i =0;i<expectedStore.contactArray.size();i++){
			pass &= MiscFunctions.compareResult("First Name", expectedStore.contactArray.get(i).firstName, getFirstName(i));
			pass &= MiscFunctions.compareResult("Middle Name", expectedStore.contactArray.get(i).midName, getMiddleName(i));
			pass &= MiscFunctions.compareResult("Last Name", expectedStore.contactArray.get(i).lastName, getLastName(i));
			pass &= MiscFunctions.compareResult("Suffix", expectedStore.contactArray.get(i).suffix, getSuffix(i));
			pass &= MiscFunctions.compareResult("Business Phone", expectedStore.contactArray.get(i).businessPhone, getBusinessPhone(i));
			pass &= MiscFunctions.compareResult("Home Phone", expectedStore.contactArray.get(i).homePhone, getHomePhone(i));
			pass &= MiscFunctions.compareResult("Mobile Phone", expectedStore.contactArray.get(i).mobilePhone, getMobilePhone(i));
			pass &= MiscFunctions.compareResult("Fax", expectedStore.contactArray.get(i).fax, getFax(i));
			pass &= MiscFunctions.compareResult("Email", expectedStore.contactArray.get(i).email, getEmail(i));
			pass &= MiscFunctions.compareResult("Website", expectedStore.contactArray.get(i).website, getWebsite(i));
		}
		return pass;
	}
}
