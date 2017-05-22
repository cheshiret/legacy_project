
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**  
 * @Description:  Add a new store for a specific vendor
 * @Preconditions:  The vendor exist in our system.
 * @SPEC:  Add store
 * @Task#: Auto-753
 * @author jwang8  
 * @Date  Jan 9, 2012    
 */
public class LicMgrStoreAddPage extends LicMgrStoreDetailsPage{
	
	public static  LicMgrStoreAddPage instance = null;
	
	private LicMgrStoreAddPage(){};
	
	public static LicMgrStoreAddPage getInstance(){
		if(null == instance){
			instance = new LicMgrStoreAddPage();
		}
		
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TR",".text", new RegularExpression("Agent Info( )?Agent Name.*",false)) ||
		browser.checkHtmlObjectExists(".class", "Html.TD",".text", "Agency");
	}
	
	
	/**
	 * Set the store name.
	 * @param name - the value of name.
	 */
	public void setStoreName(String name){
		browser.setTextField(".id", new RegularExpression("StoreView-\\d+\\.name",false), name);
	}
	
	/**
	 * Get the store name.
	 * @return String - the value of name.
	 */
	public String getStoreName(){
		return browser.getTextFieldValue(".id", new RegularExpression("StoreView-\\d+\\.name",false));
	}
	/**
	 * Select the time zone .
	 * @param zone - the value of time zone.
	 */
	public void selectTimeZone(String timeZone){
		browser.selectDropdownList(".id",new RegularExpression("StoreView-\\d+\\.timezoneId", false), timeZone);
	}
	
	/**
	 * Get the time zone.
	 * @return String - the value of time zone.
	 */
	public String getTimeZone(){
		return browser.getDropdownListValue(".id",new RegularExpression("StoreView-\\d+\\.timezoneId", false));
	}
	
	/**
	 * Select the address .
	 * @param address - the value of address.
	 */
	public void setAddress(String address,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address",false), address,index);
	}
	
	/**
	 * Get the address.
	 * @return String - the value of address.
	 */
	public String getAddress(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address",false), index);
	}
	
	/**
	 * Set the supplemental Address .
	 * @param suppleAddress - the value of supplemental.
	 */
	public void setSuppleAddress(String suppleAddress,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), suppleAddress, index);
	}
	
	/**
	 * Get the supplement address.
	 * @return String - the value of supplement address.
	 */
	public String getSuppleAddress(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false), index);
	}
	
	/**
	 * Set the city .
	 * @param city - the value of city.
	 */
	public void setCity(String city, int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city",false), city, index);
	}
	
	/**
	 * Get the city.
	 * @return String - the value of city.
	 */
	public String getCity(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city",false), index);
	}
	
	
	/**
	 * Select the state .
	 * @param state - the value of state.
	 */
	public void selectState(String state, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state",false), state, index);	
	}
	
	/**
	 * Get the state.
	 * @return String - the value of state.
	 */
	public String getState(int index){
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.state",false), index);
	}
	
	/**
	 * Select the county .
	 * @param county - the value of county.
	 */
	public void selectCounty(String county, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), county, index);
	}
	
	/**
	 * Get the county.
	 * @return String - the value of county.
	 */
	public String getCounty(int index){
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.county", false), index);
	}
	
	/**
	 * Set the zip .
	 * @param zip - the value of zip.
	 */
	public void setZip(String zip, int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), zip, index);
	}
	
	/**
	 * Get the zip.
	 * @return String - the value of zip.
	 */
	public String getZip(int index){
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false), index);
	}
	
	/**
	 * Set the country .
	 * @param country - the value of country.
	 */
	public void selectCountry(String country, int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country",false), country,index);		
	}
	
	/**
	 * Get the country.
	 * @return String - the value of country.
	 */
    public String getCountry(int index){
    	return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.country",false), index);
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
	 * Get the contact type.
	 * @return String - the value of contact type.
	 */
    public String getContactType(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.contactType",false),index);
    }
    
    /**
	 * Set first name .
	 * @param name - the value of name.
	 */
    public void setFirstName(String name, int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.firstName", false), name, index);
    }
    
    /**
	 * Get the first name.
	 * @return String - the value of first name.
	 */
    public String getFirstName(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.firstName", false),index);
    }
    
    /**
	 * Set middle name .
	 * @param name - the value of middle name.
	 */
    public void setMiddleName(String midName, int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.middleName",false), midName, index);  	
    }
    
    /**
	 * Get the middle name .
	 * @return String - the value of middle name.
	 */
    public String getMiddleName(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.middleName",false),index);
    }
    
 
    /**
	 * Set last name .
	 * @param name - the value of last name.
	 */
    public void setLastName(String lastName,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.lastName",false), lastName,index);
    }
    
    
    /**
	 * Get the last name .
	 * @return String - the value of last name.
	 */
    public String getLastName(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.lastName",false),index);
    }
    
    /**
	 * select suffix .
	 * @param suffix - the value of last suffix.
	 */
    public void selectSuffix(String suffix,int index){
    	browser.selectDropdownList(".id", new RegularExpression("ContactView-\\d+\\.suffix",false), suffix,index);
    }
    
    /**
	 * Get the suffix .
	 * @return String - the value of suffix.
	 */
    public String getSuffix(int index){
    	return browser.getDropdownListValue(".id", new RegularExpression("ContactView-\\d+\\.suffix",false),index);
    }
    
    /**
	 * set business phone .
	 * @param bPhone - the value of last business phone.
	 */
    public void setBusinessPhone(String bPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false), bPhone,index);
    }
    
    /**
	 * Get the business phone .
	 * @return String - the value of business phone.
	 */
    public String getBusinessPhone(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.businessPhone",false),index);
    }
    
    /**
	 * set home phone .
	 * @param hPhone - the value of home phone.
	 */
    public void setHomePhone(String hPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false), hPhone,index);
    }
    
    /**
	 * Get the home phone .
	 * @return String - the value of home phone.
	 */
    public String getHomePhone(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.homePhone",false),index);
    }
    /**
	 * set mobile phone .
	 * @param hPhone - the value of mobile phone .
	 */
    public void setMobilePhone(String mPhone,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false), mPhone,index);
    }
    
    /**
	 * Get the moblie phone .
	 * @return String - the value of moblie phone.
	 */
    public String getMobilePhone(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.mobilePhone",false),index);
    }
    
    /**
	 * set fax .
	 * @param hPhone - the value of fax .
	 */
    public void setFax(String fax,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.fax",false), fax,index);
    }
    
    /**
	 * Get the fax .
	 * @return String - the value of fax.
	 */
    public String getFax(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.fax",false),index);
    }
    
    
    /**
	 * set email .
	 * @param hPhone - the value of email .
	 */
    public void setEmail(String email,int index){
    	browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.email",false), email,index);
    }
    
    /**
	 * Get the email .
	 * @return String - the value of email.
	 */
    public String getEmail(int index){
    	return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.email",false),index);
    }
    
    /**
	 * set website .
	 * @param hPhone - the value of website .
	 */
   public void setWebSite(String website, int index){
	   browser.setTextField(".id", new RegularExpression("ContactView-\\d+\\.website",false), website, index);
   }
   
   /**
	 * Get the website .
	 * @return String - the value of website.
	 */
   public String getWebSite(int index){
	   return browser.getTextFieldValue(".id", new RegularExpression("ContactView-\\d+\\.website",false),index);
   }
   
   /**
	 * Click add contact .
	 */
  public void clickAddContact(){
	   browser.clickGuiObject(".class","Html.A", ".text", "Add Contact");
  }
  
  /**
	 * Select the primary radio box.
	 * @param index - the index of primary radio box.
	 */
 public void selectPrimary(int index){
	   browser.selectRadioButton(".id", new RegularExpression("ContactView-\\d+\\.primary",false), index);
	   ajax.waitLoading();
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
 public void clickValidate(int index){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Validate", index);	 
 }
 /**
	 * Click OK .
	 */
 public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
 /**
	 * Click Cancle
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	/**
	 * Click apply
	 */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	 /**
	 * Select the add new location radio box.
	 */
	public void selectAddNewLocation(){
		browser.selectRadioButton(".id", "LocationSelectionView.newLocation", 0);
	}
	
	 /**
	 * Select the Exist location radio box.
	 */
	public void selectAssExitLocation(){
		browser.selectRadioButton(".id", "LocationSelectionView.newLocation", 1);
	}
	
	/**
	 * Select the location class.
	 * @param locationClass - the value of location class.
	 */
	public void selectLocationClass(String locationClass){
		browser.selectDropdownList(".id", new RegularExpression("LocationSelectionView-\\d+\\.locationClassId",false), locationClass);
	}
	
	/**
	 * Select the agency.
	 * @param agency - the value of agency.
	 */
	public void selectAgency(String agency){
		browser.selectDropdownList(".id", new RegularExpression("LocationSelectionView-\\d+\\.agencyId",false), agency);
	}
	
	/**
	 * Select the region.
	 * @param region - the value of region.
	 */
	public void selectRegion(String region){
		
		browser.selectDropdownList(".id", new RegularExpression("LocationSelectionView-\\d+\\.regionId",false), region);
	}
	
	/**
	 * Select the location.
	 * @param location - the value of location.
	 */
	public void selectLocation(String location){
		browser.selectDropdownList(".id", new RegularExpression("LocationSelectionView-\\d+\\.facilityId",false), location);
	}
	/**
	 * Click the OK button.
	 */
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	
	/**
	 * Set the contacts info.
	 * @param Contacts -  the contact info.
	 */
	public void setContacts(ArrayList<Contacts> Contacts) {
		for (int i = 0; i < Contacts.size(); i++) {
			this.selectContactType(Contacts.get(i).contactType, i);
			this.selectPrimary(i);
			this.setFirstName(Contacts.get(i).firstName, i);
			this.setMiddleName(Contacts.get(i).midName, i);
			this.setLastName(Contacts.get(i).lastName, i);
			this.selectSuffix(Contacts.get(i).suffix, i);
			this.setBusinessPhone(Contacts.get(i).businessPhone, i);
			this.setHomePhone(Contacts.get(i).homePhone, i);
			this.setMobilePhone(Contacts.get(i).mobilePhone, i);
			this.setFax(Contacts.get(i).fax, i);
			this.setEmail(Contacts.get(i).email, i);
			this.setWebSite(Contacts.get(i).website, i);
			if (i < Contacts.size() - 1) {
				this.clickAddContact();
				ajax.waitLoading();
			}
		}
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
	 * Remove the contacts info.
	 * @param Contacts -  the contact info.
	 */
	public void RemoveContacts(ArrayList<Contacts> Contacts){
		for (int i = 0; i < Contacts.size() - 1; i++) {
			if(Contacts.get(i).isRemove){
				this.clickRemove(i);
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
	public void setAddress(Address address, int index) {
		if(!StringUtil.isEmpty(address.address)) {
			this.setAddress(address.address, index);
		}
		if(!StringUtil.isEmpty(address.supplementalAddr)) {
			this.setSuppleAddress(address.supplementalAddr, index);
		}
		if(!StringUtil.isEmpty(address.city)) {
			this.setCity(address.city, index);
		}
		if(!StringUtil.isEmpty(address.state)) {
			this.selectState(address.state, index);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(address.county)) {
			this.selectCounty(address.county, index);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(address.zip)) {
			this.setZip(address.zip, index);
		}
		if(!StringUtil.isEmpty(address.country)) {
			this.selectCountry(address.country, index);
			ajax.waitLoading();
		}
		if (address.isValidate) {
			this.clickValidate(index);
			ajax.waitLoading();
		}
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
 
	/**
	 * Set the store info
	 * 
	 * @param storeInfo
	 *            - the store info.
	 */
	public void setStoreInfo(StoreInfo storeInfo) {
		if(storeInfo.isNewLocation){
			this.selectAddNewLocation();
			ajax.waitLoading();
		}else{
			this.selectAssExitLocation();
			ajax.waitLoading();
		}
		this.selectLocationClass(storeInfo.locationClass);
		ajax.waitLoading();
		this.selectAgency(storeInfo.agency);
		ajax.waitLoading();
		this.selectRegion(storeInfo.region);
		ajax.waitLoading();
		if(!storeInfo.isNewLocation) {
			this.selectLocation(storeInfo.location);
			ajax.waitLoading();
		}
		this.clickOk();
		ajax.waitLoading();
		this.waitLoading();
		this.setStoreName(storeInfo.storeName);
		if(storeInfo.isNewLocation){
			this.selectTimeZone(storeInfo.timeZone);	
		}
		
		this.setAddress(storeInfo.physicalAddress, 0);
		if (storeInfo.isMailSamePhyAddress) {
			this.selectMailSamePhyAddress();
			ajax.waitLoading();
		} else {
			this.unSelectMailSamePhyAddress();
			ajax.waitLoading();
			this.setAddress(storeInfo.mailingAddress, 1);
		}
		this.editContacts(storeInfo.contactArray);
	}
}
